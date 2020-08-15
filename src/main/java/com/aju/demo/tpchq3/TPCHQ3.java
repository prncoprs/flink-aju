package com.aju.demo.tpchq3;


import com.aju.demo.TPCHDataSources;
import com.aju.demo.relations.AssertionKeyValue;
import com.aju.demo.relations.RelationUnit;
import com.aju.demo.relations.RelationsManager;
import com.aju.demo.tpchdata.CustomerTuple;
import com.aju.demo.tpchdata.LineitemTuple;
import com.aju.demo.tpchdata.OrdersTuple;
import com.aju.demo.tpchq1.Q1SelectResult;
import com.aju.demo.tpchq1.RelationHolder;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import javax.sql.DataSource;

/*
 * {{{
 * SELECT
 *      l_orderkey,
 *      SUM(l_extendedprice*(1-l_discount)) AS revenue,
 *      o_orderdate,
 *      o_shippriority
 * FROM customer,
 *      orders,
 *      lineitem
 * WHERE
 *      c_mktsegment = '[SEGMENT]'
 *      AND c_custkey = o_custkey
 *      AND l_orderkey = o_orderkey
 *      AND o_orderdate < date '[DATE]'
 *      AND l_shipdate > date '[DATE]'
 * GROUP BY
 *      l_orderkey,
 *      o_orderdate,
 *      o_shippriority
 * ORDER BY
 *      revenue desc,
 *      o_orderdate;
 * }}}
 */
public class TPCHQ3 {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment sEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<LineitemTuple> lineitem = TPCHDataSources.getLineitemTupleDataStream(sEnv);
        DataStream<OrdersTuple> orders = TPCHDataSources.getOrdersTupleDataStream(sEnv);
        DataStream<CustomerTuple> customer = TPCHDataSources.getCustomerTupleDataStream(sEnv);

        DataStream<UpdateAction> q3UpdateSeq1 = customer.map(
                new MapFunction<CustomerTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(CustomerTuple customerTuple) throws Exception {
                        return new UpdateAction("insert",
                                "customer",
                                customerTuple.c_custkey,
                                (Object) customerTuple);
                    }
                });
        DataStream<UpdateAction> q3UpdateSeq2 = orders.map(
                new MapFunction<OrdersTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(OrdersTuple ordersTuple) throws Exception {
                        return new UpdateAction("insert",
                                "orders",
                                ordersTuple.o_custkey,
                                (Object) ordersTuple);
                    }
                }
        );
        DataStream<UpdateAction> q3UpdateSeq3 = lineitem.map(
                new MapFunction<LineitemTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(LineitemTuple lineitemTuple) throws Exception {
                        return new UpdateAction("insert",
                                "lineitem",
                                lineitemTuple.get_primaryKey(),
                                (Object) lineitemTuple);
                    }
                }
        );
        DataStream<UpdateAction> q3UpdateSeq = q3UpdateSeq1.union(q3UpdateSeq2).union(q3UpdateSeq3);


        q3UpdateSeq.print();

        sEnv.execute();
    }

    public static class Q3AjuAlgo extends
            KeyedProcessFunction<Tuple, UpdateAction, Q3Result> {

        private ValueState<Q3Result> resultState;
        private ValueState<RelationsManager> relationState;

        @Override
        public void open(Configuration parameters) throws Exception {

            resultState = getRuntimeContext().getState(
                    new ValueStateDescriptor<Q3Result>("resultState", Q3Result.class));
            relationState = getRuntimeContext().getState(
                    new ValueStateDescriptor<RelationsManager>("relationState", RelationsManager.class));
        }

        @Override
        public void processElement(UpdateAction updateAction,
                                   Context context,
                                   Collector<Q3Result> collector) throws Exception {

            // init the result state
            Q3Result curResult = resultState.value();
            if (curResult == null) {
                curResult = new Q3Result();
            }

            // init relations manager
            RelationsManager curRelationsManager = relationState.value();
            if (curRelationsManager == null) {
                curRelationsManager = new RelationsManager();
                curRelationsManager.setQueryNum(3);
            }


            // get the relation unit
            RelationUnit curRelationUnit =
                    curRelationsManager.getRelationMapPool().get(updateAction.getRelationName());


            if (updateAction.actionFlag == "insert") {

                // insert algo
                if (!curRelationUnit.isLeaf) {
                    // s <- 0
                    curRelationUnit.s_counter.put(updateAction.getPrimaryKey(), 0);

                    // foreach Rc ∈ C(R) do
                    for (int i = 0; i < curRelationUnit.childRelationsNum; i++) {
                        // I(R, Rc ) ← I(R, Rc ) + (πPK(Rc )t → πPK(R),PK(Rc )t)
                        curRelationUnit.indexOfRandRc.get(i).indexRAndRc.put(
                                updateAction.getPrimaryKey(), updateAction.getPrimaryKeyOfRc(i));
                        // if πPK(Rc )t ∈ I(Rc ) then s(t) ← s(t) + 1
                        if(curRelationUnit.childRelations.get(i).tuplesIndex.containsKey(updateAction.getPrimaryKeyOfRc(i))) {
                            int tmp = curRelationUnit.s_counter.get(updateAction.getPrimaryKey());
                            curRelationUnit.s_counter.put(updateAction.getPrimaryKey(), tmp+1);
                        }
                    }

                    // if s(t) = |C(R)| then
                    if(curRelationUnit.s_counter.get(updateAction.getPrimaryKey()) == curRelationUnit.childRelationsNum) {
                        // foreach Rc ∈ C(R) do
                        for (int i = 0; i < curRelationUnit.childRelationsNum; i++) {
                            // tc ← look up I(Rc ) with key πPK(Rc )t
                            Object tc = curRelationUnit.childRelations.get(i).tuplesIndex.get(updateAction.getPrimaryKeyOfRc(i));
                            // foreach assertion key x of R do
                            for(int j = 0; j < curRelationUnit.assertionKeyNum; j++) {
                                // if x ∈ Rc then
                                if(curRelationUnit.childRelationAndAssertionKeyPool.get(i) == j) {
                                    // if πx t = NULL then πx t ← πx tc
                                    if(curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()) == null) {
                                        curRelationUnit.assertionKey.get(j).put(
                                                updateAction.getPrimaryKey(),
                                                curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKeyOfRc(i)));
                                    }
                                    // else if πx t !=  πx tc, or πx t = ⊥
                                    else if ( (curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()) !=
                                            curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKeyOfRc(i)))
                                    || (curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()).isFALSUM){
                                        // then πx t ← ⊥
                                        curRelationUnit.assertionKey.get(j).put(updateAction.getPrimaryKey(),
                                                new AssertionKeyValue(false, true, null));
                                    }
                                }
                            }
                        }
                    }

                }
                boolean allAssertionKeysAreNotFalsum = true;
                for (int i = 0; i < curRelationUnit.assertionKey.size(); i++) {
                    for(AssertionKeyValue akv : curRelationUnit.assertionKey.get(i).values()) {
                        if(akv.isFALSUM) {
                            allAssertionKeysAreNotFalsum = false;
                        }
                    }
                }

                // if R is a leaf or (s(t) = |C(R)| and all assertion keys are not ⊥) then
                if(curRelationUnit.isLeaf ||
                        (curRelationUnit.s_counter.get(updateAction.getPrimaryKey()) == curRelationUnit.childRelationsNum )
                        && allAssertionKeysAreNotFalsum) {
                    // Insert-Update Algo

                    // (L(R)) ← I(L(R)) + (πPK(R)t → t)
                    curRelationUnit.liveTuplesIndex.put(updateAction.getPrimaryKey(), updateAction.tupleData);
                    // if R is the root of T then
                    if(curRelationUnit.isRoot) {
                        // ∆Q ← ∆Q ∪ {join_result }

                    }
                    else {

                    }

                }

            } else if (updateAction.actionFlag == "delete") {
                // delete algo
            }
            else {
                throw new RuntimeException("update flag does not exist!");
            }


        }
    }
}

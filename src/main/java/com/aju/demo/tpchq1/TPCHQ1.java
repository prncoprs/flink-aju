/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aju.demo.tpchq1;

import com.aju.demo.TPCHDataSources;
import com.aju.demo.tpchdata.LineitemTuple;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;


/**
 * @ClassName TPCHQ1
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/20
 */
public class TPCHQ1 {


    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment sEnv = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<LineitemTuple> lineitem = TPCHDataSources.getLineitemTupleDataStream(sEnv);

        DataStream<Q1LineitemUpdateAction> q1UpdateSequence = lineitem
                .map(new MapFunction<LineitemTuple, Q1LineitemUpdateAction>() {
                    @Override
                    public Q1LineitemUpdateAction map(LineitemTuple value) throws Exception {
                        return new Q1LineitemUpdateAction(
                                value.l_orderkey * 10 + value.l_linenumber,
                                value.l_quantity,
                                value.l_extendedprice,
                                value.l_returnflag,
                                value.l_linestatus,
                                value.l_shipdate);
                    }
                });

        q1UpdateSequence
                .keyBy("data.l_returnflag", "data.l_linestatus")
//                .keyBy("l_returnflag")
//                .keyBy("l_linestatus")
                .process(new Q1AjuAlgo())
                .print();


        sEnv.execute();
    }

    public static class Q1AjuAlgo extends
            KeyedProcessFunction<Tuple, Q1LineitemUpdateAction, Q1SelectResultTuple> {

        private ValueState<Q1SelectResult> resultState;
        private ValueState<RelationHolder> relationState;

        public Q1AjuAlgo() {
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            resultState = getRuntimeContext().getState(
                    new ValueStateDescriptor<Q1SelectResult>("resultState", Q1SelectResult.class));
            relationState = getRuntimeContext().getState(
                    new ValueStateDescriptor<RelationHolder>("relationState", RelationHolder.class));
        }

        @Override
        public void processElement(
                Q1LineitemUpdateAction value,
                Context ctx,
                Collector<Q1SelectResultTuple> out) throws Exception {

            // init state
            Q1SelectResult currentResult = resultState.value();
            if (currentResult == null) {
                currentResult = new Q1SelectResult();
            }

            RelationHolder currentRelation = relationState.value();
            if (currentRelation == null) {
                currentRelation = new RelationHolder(1, "lineitem");
            }

            // Algo 1: insert
            if (!currentRelation.isLeaf) {
                if (currentRelation.s_counter != null) {
                    currentRelation.s_counter.put(value.primaryKeyOrderkeyLinenum, 0L);
                } else {
                    throw new RuntimeException("relation s_counter is null.");
                }

                if (currentRelation.childRelations != null) {
                    for (RelationHolder r : currentRelation.childRelations) {
//                        currentRelation.childRelationsIndex.get()
                        // TODO
                        if (r.tuplesIndex.containsKey(value.primaryKeyOrderkeyLinenum)) {
                            long tmp = currentRelation.s_counter.get(value.primaryKeyOrderkeyLinenum);
                            currentRelation.s_counter.put(value.primaryKeyOrderkeyLinenum, tmp + 1L);
                        }
                    }

                }

                if (currentRelation.s_counter.get(value.primaryKeyOrderkeyLinenum) == currentRelation.childRelationsNum) {
                    for (RelationHolder r : currentRelation.childRelations) {
                        // TODO
                    }
                }

            }

            // TODO
            if (currentRelation.isLeaf) {
                // Algo 2: insert-update
                if (currentRelation.isRoot) {
                    Q1SelectResultTuple tmpResultTuple;
                    if (currentResult.result.isEmpty()) {
                        tmpResultTuple = new Q1SelectResultTuple();
                        tmpResultTuple.countAll = 1;
                        tmpResultTuple.l_returnflag = value.data.l_returnflag;
                        tmpResultTuple.l_linestatus = value.data.l_linestatus;
                        tmpResultTuple.sumOfQuantity = value.data.l_quantity;
                        tmpResultTuple.sumOfExtendedprice = value.data.l_extendedprice;
                        tmpResultTuple.avgOfQuantity = value.data.l_quantity;
                        tmpResultTuple.avgOfExtendedprice = value.data.l_extendedprice;
                        currentResult.result.add(tmpResultTuple);
                    } else {
                        tmpResultTuple = new Q1SelectResultTuple();
                        Q1SelectResultTuple lastResultTuple = currentResult.result.get(currentResult.result.size() - 1);
                        tmpResultTuple.countAll = lastResultTuple.countAll + 1;
                        tmpResultTuple.l_returnflag = value.data.l_returnflag;
                        tmpResultTuple.l_linestatus = value.data.l_linestatus;
                        tmpResultTuple.sumOfQuantity = lastResultTuple.sumOfQuantity + value.data.l_quantity;
                        tmpResultTuple.sumOfExtendedprice = lastResultTuple.sumOfExtendedprice + value.data.l_extendedprice;
                        tmpResultTuple.avgOfQuantity = tmpResultTuple.sumOfQuantity / tmpResultTuple.countAll;
                        tmpResultTuple.avgOfExtendedprice = value.data.l_extendedprice / tmpResultTuple.countAll;
                        currentResult.result.add(tmpResultTuple);
                    }

                    resultState.update(currentResult);
                    relationState.update(currentRelation);
                    out.collect(tmpResultTuple);
                } else {
                    // TODO
                }

            } else {
                currentRelation.nonliveTuplesIndex.put(new Long(value.primaryKeyOrderkeyLinenum), value.data);
            }


        }
    }


}

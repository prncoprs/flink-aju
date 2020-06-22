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
package com.aju.demo;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import com.aju.demo.tpchdata.*;
import org.apache.flink.types.Row;

import java.lang.reflect.Field;

/**
 * @ClassName TPCHQ3
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class TPCHQ3 {
    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
//        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        BatchTableEnvironment btEnv = BatchTableEnvironment.create(env);

        final StreamExecutionEnvironment sEnv = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamTableEnvironment stEnv = StreamTableEnvironment.create(sEnv);
//        EnvironmentSettings es = EnvironmentSettings.newInstance().build();
//        TableEnvironment tEnv = TableEnvironment.create(es);

//        DataSet<CustomerTuple> customer = TPCHDataSources.getCustomerDataSet(env);
//        DataSet<LineitemTuple> lineitem = TPCHDataSources.getLineitemDataSet(env);
//        DataSet<NationTuple> nation = TPCHDataSources.getNationDataSet(env);
//        DataSet<OrdersTuple> orders = TPCHDataSources.getOrdersDataSet(env);
//        DataSet<PartsuppTuple> partsupp = TPCHDataSources.getPartsuppDataSet(env);
//        DataSet<PartTuple> part = TPCHDataSources.getPartDataSet(env);
//        DataSet<RegionTuple> region = TPCHDataSources.getRegionDataSet(env);
//        DataSet<SupplierTuple> supplier = TPCHDataSources.getSupplierDataSet(env);

        DataStream<CustomerTuple> d = TPCHDataSources.getCustomerTupleDataStream(sEnv);
//        DataStream
//        Table customerTable = btEnv.fromDataSet(customer);


//        sEnv.execute();
//        env.execute();

    }


}

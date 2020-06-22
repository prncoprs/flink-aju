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

/**
 * @ClassName Q1SelectTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/22
 */
public class Q1SelectTuple {
    public long primaryKey;
    public double l_quantity;
    public double l_extendedprice;
    public String l_returnflag;
    public String l_linestatus;
    public String l_shipdate;

    public Q1SelectTuple() {
    }

    public Q1SelectTuple(long primaryKey, double l_quantity, double l_extendedprice, String l_returnflag, String l_linestatus, String l_shipdate) {
        this.primaryKey = primaryKey;
        this.l_quantity = l_quantity;
        this.l_extendedprice = l_extendedprice;
        this.l_returnflag = l_returnflag;
        this.l_linestatus = l_linestatus;
        this.l_shipdate = l_shipdate;
    }

    @Override
    public String toString() {
        return "Q1SelectTuple{" +
                "primaryKey=" + primaryKey +
                ", l_quantity=" + l_quantity +
                ", l_extendedprice=" + l_extendedprice +
                ", l_returnflag='" + l_returnflag + '\'' +
                ", l_linestatus='" + l_linestatus + '\'' +
                ", l_shipdate='" + l_shipdate + '\'' +
                '}';
    }
}

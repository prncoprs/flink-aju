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
 * @ClassName Q1SelectResultTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/22
 */
public class Q1SelectResultTuple {
    public String l_returnflag;
    public String l_linestatus;
    public double sumOfQuantity;
    public double sumOfExtendedprice;
    public double avgOfQuantity;
    public double avgOfExtendedprice;
    public long countAll;

    public Q1SelectResultTuple() {
    }

    @Override
    public String toString() {
        return "Q1SelectResultTuple{" +
                "l_returnflag='" + l_returnflag + '\'' +
                ", l_linestatus='" + l_linestatus + '\'' +
                ", sumOfQuantity=" + sumOfQuantity +
                ", sumOfExtendedprice=" + sumOfExtendedprice +
                ", avgOfQuantity=" + avgOfQuantity +
                ", avgOfExtendedprice=" + avgOfExtendedprice +
                ", countAll=" + countAll +
                '}';
    }
}

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

import java.util.ArrayList;

/**
 * @ClassName Q1SelectResult
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/22
 */
public class Q1SelectResult {

    public ArrayList<Q1SelectResultTuple> result;

    public Q1SelectResult(ArrayList<Q1SelectResultTuple> result) {
        this.result = result;
    }

    public Q1SelectResult() {
        this.result = new ArrayList<Q1SelectResultTuple>();
    }

    public boolean addTuple(Q1SelectResultTuple t) {
        return result.add(t);
    }

    @Override
    public String toString() {
        return "Q1SelectResult{" +
                "result=" + result +
                '}';
    }
}

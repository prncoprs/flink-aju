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
 * @ClassName Q1LineitemUpdateAction
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/20
 */
public class Q1LineitemUpdateAction {

/*    Rules for POJO types
    Flink recognizes a data type as a POJO type (and allows “by-name” field referencing) if the following conditions are fulfilled:

    The class is public and standalone (no non-static inner class)
    The class has a public no-argument constructor
    All non-static, non-transient fields in the class (and all superclasses) are either public (and non-final) or have a public getter- and a setter- method that follows the Java beans naming conventions for getters and setters.
    Note that when a user-defined data type can’t be recognized as a POJO type, it must be processed as GenericType and serialized with Kryo.*/

    public String actionFlag;
    public String relationName;
    public long primaryKeyOrderkeyLinenum;

    public Q1SelectTuple data;

    public Q1LineitemUpdateAction() {
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public void setPrimaryKeyOrderkeyLinenum(long primaryKeyOrderkeyLinenum) {
        this.primaryKeyOrderkeyLinenum = primaryKeyOrderkeyLinenum;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public String getRelationName() {
        return relationName;
    }

    public long getPrimaryKeyOrderkeyLinenum() {
        return primaryKeyOrderkeyLinenum;
    }


    public Q1LineitemUpdateAction(long primaryKeyOrderkeyLinenum,
                                  double l_quantity,
                                  double l_extendedprice,
                                  String l_returnflag,
                                  String l_linestatus,
                                  String l_shipdate) {
        this.actionFlag = "insert";
        this.relationName = "lineitem";
        this.primaryKeyOrderkeyLinenum = primaryKeyOrderkeyLinenum;

        this.data = new Q1SelectTuple(
                primaryKeyOrderkeyLinenum,
                l_quantity,
                l_extendedprice,
                l_returnflag,
                l_linestatus,
                l_shipdate);
    }

    @Override
    public String toString() {
        return "Q1LineitemUpdateAction{" +
                "actionFlag='" + actionFlag + '\'' +
                ", relationName='" + relationName + '\'' +
                ", primaryKeyOrderkeyLinenum=" + primaryKeyOrderkeyLinenum +
                ", data=" + data +
                '}';
    }

    /*    A type cannot be a key if:

            it is a POJO type but does not override the hashCode() method and relies on the Object.hashCode() implementation.
            it is an array of any type.*/
    @Override
    public int hashCode() {
//        return super.hashCode();
        return (int) primaryKeyOrderkeyLinenum;
    }
}

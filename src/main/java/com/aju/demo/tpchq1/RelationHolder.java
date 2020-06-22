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
import java.util.Hashtable;

/**
 * @ClassName RelationHolder
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/22
 */
public class RelationHolder {
    public boolean isRoot;
    public boolean isLeaf;
    public RelationHolder parentRelation;
    public ArrayList<RelationHolder> childRelations;
    public int childRelationsNum;

    public String relationName;
    public String primaryKeyName;
    public int relationID;

    public Hashtable<Long, Q1SelectTuple> tuplesIndex;
    public Hashtable<Long, Q1SelectTuple> liveTuplesIndex;
    public Hashtable<Long, Q1SelectTuple> nonliveTuplesIndex;
    public Hashtable<Long, Long> s_counter;

    public ArrayList<Hashtable> childRelationsIndex;

    public int getRelationID() {
        return relationID;
    }

    public void setRelationID(int relationID) {
        this.relationID = relationID;
    }

    public RelationHolder() {
    }

    public RelationHolder(int tpchQueryNum, String name) {

        switch (tpchQueryNum) {
            case 1:
                tpchQ1RelationInit(name);
                break;
            case 3:
                tpchQ3RelationInit(name);
                break;
            default:
                break;
        }

    }

    public void tpchQ1RelationInit(String name) {
        if (name.equals("lineitem")) {
            this.isLeaf = true;
            this.isRoot = true;
            this.parentRelation = null;
            this.childRelations = null;
            this.childRelationsNum = 0;
            this.relationName = name;
            this.relationID = 0;
            this.primaryKeyName = "primaryKeyOrderkeyLinenum";
            this.tuplesIndex = new Hashtable<>();
            this.liveTuplesIndex = new Hashtable<>();
            this.nonliveTuplesIndex = new Hashtable<>();
            this.s_counter = new Hashtable<>();
            this.childRelationsIndex = null;

        } else {
            throw new RuntimeException("Q1 Relation does not have this relation name. ");
        }
    }

    public void tpchQ3RelationInit(String name) {
        if (name.equals("lineitem")) {
            this.isLeaf = false;
            this.isRoot = true;
            this.parentRelation = null;
            this.childRelations = null;
            this.childRelationsNum = 1;
            this.relationName = name;
            this.relationID = 0;
            this.primaryKeyName = "primaryKeyOrderkeyLinenum";
            this.liveTuplesIndex = new Hashtable<>();
            this.nonliveTuplesIndex = null;
            this.childRelationsIndex = null;
        } else if (name.equals("orders")) {
            this.isLeaf = false;
            this.isRoot = false;
            this.parentRelation = null;
            this.childRelations = null;
            this.childRelationsNum = 1;
            this.relationName = name;
            this.relationID = 1;
            this.primaryKeyName = "primaryKeyOrderkey";
            this.liveTuplesIndex = new Hashtable<>();
            this.nonliveTuplesIndex = null;
            this.childRelationsIndex = null;
        } else if (name.equals("customer")) {
            this.isLeaf = true;
            this.isRoot = false;
            this.parentRelation = null;
            this.childRelations = null;
            this.childRelationsNum = 0;
            this.relationName = name;
            this.relationID = 2;
            this.primaryKeyName = "primaryKeyCustkey";
            this.liveTuplesIndex = new Hashtable<>();
            this.nonliveTuplesIndex = null;
            this.childRelationsIndex = null;
        } else {
            throw new RuntimeException("Q3 Relations do not have this relation name. ");
        }
    }

}

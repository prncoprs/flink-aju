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
package com.aju.demo.relations;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @ClassName RelationUnit
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/26
 */
public class RelationUnit {

    public String relationName;
    public String primaryKeyName;

    public boolean isRoot;
    public boolean isLeaf;

    public RelationUnit parentRelation;
    public ArrayList<RelationUnit> childRelations;
    public int childRelationsNum;
    public ArrayList<IndexOfRelationAndChildRelation> indexOfRandRc;

    public Hashtable<Long, Object> tuplesIndex;
    public Hashtable<Long, Object> liveTuplesIndex;
    public Hashtable<Long, Object> nonliveTuplesIndex;
    public Hashtable<Long, Integer> s_counter;

    public int assertionKeyNum;
    public ArrayList<Hashtable<Long, AssertionKeyValue>> assertionKey;
    public Hashtable<Integer, Integer> childRelationAndAssertionKeyPool;

    public int getAssertionKeyNum() {
        return assertionKeyNum;
    }

    public void setAssertionKeyNum(int assertionKeyNum) {
        this.assertionKeyNum = assertionKeyNum;
    }

    public ArrayList<Hashtable<Long, AssertionKeyValue>> getAssertionKey() {
        return assertionKey;
    }

    public void setAssertionKey(ArrayList<Hashtable<Long, AssertionKeyValue>> assertionKey) {
        this.assertionKey = assertionKey;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public RelationUnit getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(RelationUnit parentRelation) {
        this.parentRelation = parentRelation;
    }

    public ArrayList<RelationUnit> getChildRelations() {
        return childRelations;
    }

    public void setChildRelations(ArrayList<RelationUnit> childRelations) {
        this.childRelations = childRelations;
    }

    public int getChildRelationsNum() {
        return childRelationsNum;
    }

    public void setChildRelationsNum(int childRelationsNum) {
        this.childRelationsNum = childRelationsNum;
    }

    public ArrayList<IndexOfRelationAndChildRelation> getIndexOfRandRc() {
        return indexOfRandRc;
    }

    public void setIndexOfRandRc(ArrayList<IndexOfRelationAndChildRelation> indexOfRandRc) {
        this.indexOfRandRc = indexOfRandRc;
    }

    public Hashtable<Long, Object> getTuplesIndex() {
        return tuplesIndex;
    }

    public void setTuplesIndex(Hashtable<Long, Object> tuplesIndex) {
        this.tuplesIndex = tuplesIndex;
    }

    public Hashtable<Long, Object> getLiveTuplesIndex() {
        return liveTuplesIndex;
    }

    public void setLiveTuplesIndex(Hashtable<Long, Object> liveTuplesIndex) {
        this.liveTuplesIndex = liveTuplesIndex;
    }

    public Hashtable<Long, Object> getNonliveTuplesIndex() {
        return nonliveTuplesIndex;
    }

    public void setNonliveTuplesIndex(Hashtable<Long, Object> nonliveTuplesIndex) {
        this.nonliveTuplesIndex = nonliveTuplesIndex;
    }

    public Hashtable<Long, Integer> getS_counter() {
        return s_counter;
    }

    public void setS_counter(Hashtable<Long, Integer> s_counter) {
        this.s_counter = s_counter;
    }


}

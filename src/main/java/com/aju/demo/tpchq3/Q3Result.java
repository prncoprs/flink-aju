package com.aju.demo.tpchq3;

import java.util.ArrayList;

public class Q3Result {
    public ArrayList<Q3ResultTuple> result;

    public Q3Result() {
        result = new ArrayList<>();
    }

    public Q3Result(ArrayList<Q3ResultTuple> result) {
        this.result = result;
    }

    public void setResult(ArrayList<Q3ResultTuple> result) {
        this.result = result;
    }

    public ArrayList<Q3ResultTuple> getResult() {
        return result;
    }

    public ArrayList<Q3ResultTuple> addResultTuple(Q3ResultTuple q3ResultTuple) {
        this.result.add(q3ResultTuple);
        return this.result;
    }

    public void clearResult() {
        this.result.clear();
    }
}

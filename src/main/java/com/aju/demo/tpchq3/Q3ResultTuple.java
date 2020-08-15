package com.aju.demo.tpchq3;


/* *      l_orderkey,
 *      SUM(l_extendedprice*(1-l_discount)) AS revenue,
 *      o_orderdate,
 *      o_shippriority*/
public class Q3ResultTuple {
    public long l_orderkey;
    public double revenue;
    public String o_orderdate;
    public String o_orderpriority;

    public Q3ResultTuple() {
    }

    public Q3ResultTuple(long l_orderkey, double revenue, String o_orderdate, String o_orderpriority) {
        this.l_orderkey = l_orderkey;
        this.revenue = revenue;
        this.o_orderdate = o_orderdate;
        this.o_orderpriority = o_orderpriority;
    }

    public long getL_orderkey() {
        return l_orderkey;
    }

    public void setL_orderkey(long l_orderkey) {
        this.l_orderkey = l_orderkey;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getO_orderdate() {
        return o_orderdate;
    }

    public void setO_orderdate(String o_orderdate) {
        this.o_orderdate = o_orderdate;
    }

    public String getO_orderpriority() {
        return o_orderpriority;
    }

    public void setO_orderpriority(String o_orderpriority) {
        this.o_orderpriority = o_orderpriority;
    }

    @Override
    public String toString() {
        return "Q3ResultTuple{" +
                "l_orderkey=" + l_orderkey +
                ", revenue=" + revenue +
                ", o_orderdate='" + o_orderdate + '\'' +
                ", o_orderpriority='" + o_orderpriority + '\'' +
                '}';
    }
}

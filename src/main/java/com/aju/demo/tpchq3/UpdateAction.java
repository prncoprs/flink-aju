package com.aju.demo.tpchq3;

public class UpdateAction {

    // a flag indicating whether it is an insertion or deletion
    public String actionFlag;

    // which relation this update is applied to
    public String relationName;

    // the primary key in case of a deletion
    public Long primaryKey;

    // the attributes of the tuple to be inserted
    public Object tupleData;

    public UpdateAction() {
    }

    public UpdateAction(String actionFlag, String relationName, Long primaryKey, Object tupleData) {
        this.actionFlag = actionFlag;
        this.relationName = relationName;
        this.primaryKey = primaryKey;
        this.tupleData = tupleData;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Object getTupleData() {
        return tupleData;
    }

    public void setTupleData(Object tupleData) {
        this.tupleData = tupleData;
    }

    public Long getPrimaryKeyOfRc(int i) {
        // TODO

    }

    @Override
    public String toString() {
        return "UpdateAction{" +
                "actionFlag='" + actionFlag + '\'' +
                ", relationName='" + relationName + '\'' +
                ", primaryKey=" + primaryKey +
                ", tupleData=" + tupleData +
                '}';
    }
}

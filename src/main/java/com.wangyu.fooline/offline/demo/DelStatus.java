package com.wangyu.fooline.offline.demo;


public enum DelStatus {
    VALID(0), //有效
    DELETED(1), //已删除
    ;

    private Integer status;

    DelStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public boolean isEqual(Integer status) {
        return this.status.equals(status);
    }
}

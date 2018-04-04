package com.wangyu.fooline.offline.demo;

/**
 * Created by wangyu21 on 2016/10/21.
 */
public enum DownloadStatus {
    ACCOMPLISH(3, "可下载"),
    ERROR(2, "导出失败"),
    WAIT(1, "等待"),
    INIT(0, "等待");//


    private Integer status; //状态值
    private String description; //字面描述

    DownloadStatus(Integer status, String description) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }

    public boolean isEqual(Integer status) {
        return this.status.equals(status);
    }

    public static DownloadStatus getDownloadStatus(Integer status){
        for (DownloadStatus ds : DownloadStatus.values()) {
            if (ds.isEqual(status)) {
                return ds;
            }
        }
        return null;
    }
}

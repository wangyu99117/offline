package com.wangyu.fooline.offline;

/**
 * Created by wangyu21 on 2016/12/21.
 */
public enum FileType {
    CSV("csv");

    /**
     * 文件后缀
     */
    private String ext;

    FileType(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return ext;
    }

}

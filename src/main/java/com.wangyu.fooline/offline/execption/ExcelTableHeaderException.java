package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2017/8/24.
 */
public class ExcelTableHeaderException extends Exception {

    public ExcelTableHeaderException(String message) {
        super(message);
    }

    public ExcelTableHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelTableHeaderException(Throwable cause) {
        super(cause);
    }
}

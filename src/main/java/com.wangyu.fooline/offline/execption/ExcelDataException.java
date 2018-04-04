package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2017/8/24.
 */
public class ExcelDataException extends Exception {
    public ExcelDataException(String message) {
        super(message);
    }

    public ExcelDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelDataException(Throwable cause) {
        super(cause);
    }
}

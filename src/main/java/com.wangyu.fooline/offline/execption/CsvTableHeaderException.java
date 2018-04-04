package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2017/2/7.
 */
public class CsvTableHeaderException extends Exception {

    public CsvTableHeaderException(String message) {
        super(message);
    }

    public CsvTableHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvTableHeaderException(Throwable cause) {
        super(cause);
    }
}

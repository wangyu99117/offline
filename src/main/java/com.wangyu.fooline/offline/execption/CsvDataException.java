package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2017/2/7.
 */
public class CsvDataException extends Exception {
    public CsvDataException(String message) {
        super(message);
    }

    public CsvDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvDataException(Throwable cause) {
        super(cause);
    }
}

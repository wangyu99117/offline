package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2017/1/11.
 */
public class OfflineDataException extends Exception {

    public OfflineDataException(String message) {
        super(message);
    }

    public OfflineDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfflineDataException(Throwable cause) {
        super(cause);
    }
}

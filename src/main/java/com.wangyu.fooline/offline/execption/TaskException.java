package com.wangyu.fooline.offline.execption;

/**
 * Created by wangyu21 on 2016/12/21.
 */
public class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }


    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}

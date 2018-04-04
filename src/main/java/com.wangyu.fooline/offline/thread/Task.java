package com.wangyu.fooline.offline.thread;

/**
 * Created by wangyu21 on 2016/12/21.
 */
public interface Task {

    /**
     * 开始下载任务
     */
    void start();

    /**
     * 任务准备
     */
    void beforeTask(final Long pid);

    /**
     * 任务结束
     */
    void afterTask(final Long pid);

    /**
     * 任务
     */
    void task(final Long pid, final String taskTitle);
}

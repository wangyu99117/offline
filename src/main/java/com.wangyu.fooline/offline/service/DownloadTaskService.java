package com.wangyu.fooline.offline.service;


import com.wangyu.fooline.offline.demo.OfflineDownloadRecordPO;
import com.wangyu.fooline.offline.thread.Task;

/**
 * Created by wangyu21 on 2016/12/21.
 */
public interface DownloadTaskService {

    Task createTask(OfflineDownloadRecordPO taskInfo);
}

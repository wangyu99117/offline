package com.wangyu.fooline.offline.service.impl;

import com.wangyu.fooline.offline.FileGenerateFactory;
import com.wangyu.fooline.offline.demo.DelStatus;
import com.wangyu.fooline.offline.demo.DownloadStatus;
import com.wangyu.fooline.offline.demo.OfflineDownloadRecordDao;
import com.wangyu.fooline.offline.demo.OfflineDownloadRecordPO;
import com.wangyu.fooline.offline.execption.TaskException;
import com.wangyu.fooline.offline.service.DownloadTaskService;
import com.wangyu.fooline.offline.thread.Task;
import com.wangyu.fooline.offline.thread.impl.DownloadTask;
import com.wangyu.test.exception.MongoOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by wangyu21 on 2016/12/20.
 */
@Service("downloadTaskService")
public class DownloadTaskServiceImpl implements DownloadTaskService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadTaskServiceImpl.class);

    @Resource
    private Map<Integer, FileGenerateFactory> fileGenerateFactoryMap;
    @Resource
    private ThreadPoolTaskExecutor uploadFileTaskExecutor;
    @Resource
    private OfflineDownloadRecordDao offlineDownloadRecordDao;

    @Override
    public Task createTask(OfflineDownloadRecordPO taskInfo){

        FileGenerateFactory fileGenerateFactory = fileGenerateFactoryMap.get(taskInfo.getType());

        return new DownloadTask(taskInfo, fileGenerateFactory) {

            @Override
            public void beforeTask(final Long pid) {
                //更改任务状态
                OfflineDownloadRecordPO obj = new OfflineDownloadRecordPO();
                obj.setDownloadStatus(DownloadStatus.WAIT.getStatus());
                obj.setId(pid);

                boolean modifyStatusSuccess = false;
                try {
                    modifyStatusSuccess = offlineDownloadRecordDao.update(obj);
                } catch (MongoOperationException e) {
                    logger.error("MongoOperation error!", e);
                }

                if(modifyStatusSuccess){
                    setStart(true);
                }else{
                    logger.error("...............DownloadTask ...............任务开始时，将任务状态更改失败...... ...........pid = {}", pid);
                    setStart(false);
                }
            }

            @Override
            public void task(final Long pid, final String parentFileName) {
                try{
                    for(Integer fileIndex = 1; ; fileIndex++){
                        logger.info("task step1 ......pid = {} ......fileIndex = {}", pid, fileIndex);
                        OfflineDownloadRecordPO record = generateAndUpload(fileIndex, parentFileName);

                        logger.info("task step2..........pid = {} ......fileIndex = {}", pid, fileIndex);

                        record.setDeleteStatus(DelStatus.VALID.getStatus());
                        record.setDownloadStatus(DownloadStatus.ACCOMPLISH.getStatus());
                        record.setCreated(Calendar.getInstance().getTime());
                        record.setModified(Calendar.getInstance().getTime());
                        offlineDownloadRecordDao.save(record);
                        logger.info("task step3 .....offlineDownloadRecordDao.save success..........pid = {} ......fileIndex = {}", pid, fileIndex);
                        if(!hasNext()){
                            logger.info("task step4.......没有下一个..........pid = {} ......fileIndex = {}", pid, fileIndex);
                            break;
                        }
                        logger.info("task step4.......下一个..........pid = {} ......fileIndex = {}", pid, fileIndex+1);
                    }

                    logger.info("task step5.......pid = {} ......parentFileName = {}", pid, parentFileName);
                    OfflineDownloadRecordPO obj = new OfflineDownloadRecordPO();
                    obj.setDownloadStatus(DownloadStatus.ACCOMPLISH.getStatus());
                    obj.setId(pid);
                    logger.info("task step6.......pid = {} ......parentFileName = {}", pid, parentFileName);
                    try {
                        offlineDownloadRecordDao.update(obj);
                    } catch (MongoOperationException ex) {
                        logger.error("MongoOperation error!", ex);
                    }
                    logger.info("task step7 success.......pid = {} ......parentFileName = {}", pid, parentFileName);
                } catch (TaskException e) {
                    logger.info("task step7 fail.........pid = {}", pid, e);
                    OfflineDownloadRecordPO obj = new OfflineDownloadRecordPO();
                    obj.setDownloadStatus(DownloadStatus.ERROR.getStatus());
                    obj.setErrMsg(e.getMessage());
                    obj.setId(pid);
                    logger.info("task step8 fail.........pid = {}", pid, e);
                    try {
                        offlineDownloadRecordDao.update(obj);
                    } catch (MongoOperationException ex) {
                        logger.error("MongoOperation error!", ex);
                    }
                }
                logger.info("task end ...pid = {} ... parentFileName = {}", pid, parentFileName);
            }

            @Override
            public void start() {
                uploadFileTaskExecutor.execute(this);
            }
        };
    }



}
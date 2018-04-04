package com.wangyu.fooline.offline.thread.impl;


import com.wangyu.fooline.offline.FileGenerate;
import com.wangyu.fooline.offline.FileGenerateFactory;
import com.wangyu.fooline.offline.demo.JssUtils;
import com.wangyu.fooline.offline.demo.OfflineDownloadRecordPO;
import com.wangyu.fooline.offline.execption.OfflineDataException;
import com.wangyu.fooline.offline.execption.TaskException;
import com.wangyu.fooline.offline.thread.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangyu21 on 2017/8/17.
 */
public abstract class DowmloadScanTask implements Runnable, Task {
    private static final Logger logger = LoggerFactory.getLogger(DowmloadScanTask.class);

    private OfflineDownloadRecordPO recordInfo;
    private FileGenerateFactory fileGenerateFactory;

    private boolean hasNext;//是否还可以继续循环
    private boolean start = false;//任务开始时更改状态是否成功

    public DowmloadScanTask(FileGenerateFactory fileGenerateFactory, OfflineDownloadRecordPO recordInfo) {
        this.fileGenerateFactory = fileGenerateFactory;
        this.recordInfo = recordInfo;
    }

    @Override
    public void run() {
        Long pid = recordInfo.getPid();
        /*ClientInfo clientInfo = new ClientInfo();
        clientInfo.setUsers(recordInfo.getUsers());
        try(UserSession.LoginUser login = UserSession.initSession(clientInfo)){
            beforeTask(pid);
            if(!start){
                return ;
            }

            task(pid, recordInfo.getFileName());

            afterTask(pid);
        }*/
    }

    @Override
    public void afterTask(Long pid) {
        logger.info("...............DownloadTask ...............end...... ...........pid = {}", pid);
    }

    public void setStart(boolean isStart){
        start = isStart;
    }

    public OfflineDownloadRecordPO generateAndUpload(final Integer fileIndex, final String parentFileName) throws TaskException {
        try{
            logger.info("generate file start...fileIndex={}", fileIndex);
            FileGenerate fileGenerate = fileGenerateFactory.getFileGenerator(recordInfo.getEntityId(), recordInfo.getSource());
            logger.info("generate file step1 getFileGenerator success .........fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            fileGenerate.generate();//真正生成文件的 代码
            logger.info("generate file step2 generate .......success.....fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            logger.info("upload file step3 .........start.....fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            String cloudKey = JssUtils.upload(fileGenerate.getBucket(), fileGenerate.getTempFile());
            logger.info("upload file step4 .........success.....fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            recordInfo.setCloudKey(fileGenerate.getBucket() + "/" + cloudKey);
            recordInfo.setFileSize(fileGenerate.getFileSize());
            recordInfo.setFileName(parentFileName +"_" + fileIndex);
            recordInfo.setFileIndex(fileIndex);

            hasNext = fileGenerate.hashNext();//设置当前任务是否有下一部分
            logger.info("generateAndUpload step5 ......set....hasNext= {}", hasNext);
            return recordInfo;
        } catch (OfflineDataException e){
            logger.error("generateAndUpload file step7.1  ---...generate..fileIndex = {}", fileIndex);
            logger.error("generateAndUpload file step7.2  ---.....generate......fail..***fail to get data***.......fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            throw new TaskException(e);
        } catch (Exception e){
            logger.error("generateAndUpload file step7......uploadFile.........fail.....fileIndex={}....pid = {}", fileIndex, recordInfo.getPid());
            throw new TaskException(e);
        }
    }
}

package com.wangyu.fooline.offline.csv;


import com.wangyu.fooline.offline.FileGenerate;
import com.wangyu.fooline.offline.utils.excel.CVSData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangyu21 on 2016/10/19.
 */
public class CsvFileGenerate extends FileGenerate {

    private List<Object> resList;
    private LinkedHashMap<String, String> colName;
    private static final int FILEMAXSIZE = 100000;

    public CsvFileGenerate(LinkedHashMap<String, String> colName, List<Object> resList) {
        this.colName = colName;
        this.resList = resList;
    }

    @Override
    public void generate(){
        try{
            String uuid = UUID.randomUUID().toString();
            String tempFileName = uuid.replace("-", "");//uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
            String fileName = SERVICETEMPPATH + tempFileName +".csv";
            tempFile = new File(fileName);
            if(!tempFile.exists()) tempFile.createNewFile();
            FileOutputStream out = new FileOutputStream(tempFile);
            new CVSData().export(colName, resList ,out, false);
            out.close();
            logger.info("--------------------生成临时文件！--------------tempFile={}-------", tempFile.getName());
            fileSize = tempFile.length();
            logger.info("--------------------生成临时文件！------------------fileSize={}---", fileSize);
        }catch (Exception e){
            logger.error("--------------------生成临时文件出错！---------------------", e);
            throw new RuntimeException("生成临时文件出错！", e);
        }
    }


    @Override
    public String getBucket() {
        return "form-data-file";
    }

    @Override
    public boolean hashNext() {
        if(resList.size() < FILEMAXSIZE){
            return false;
        }else{
            return true;
        }
    }

}


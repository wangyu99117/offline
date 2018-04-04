package com.wangyu.fooline.offline.excel;


import com.wangyu.fooline.offline.FileGenerate;
import com.wangyu.fooline.offline.utils.excel.POIExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangyu21 on 2017/8/23.
 */
public class ExcelFileGenerate<T> extends FileGenerate {

    private List<Sheet<T>> sheets = new LinkedList<>();


    public ExcelFileGenerate() {
    }

    public  ExcelFileGenerate(LinkedHashMap<String, String> colName, List<T> resList) {
        Sheet<T> sheet1 = new Sheet(colName, resList);
        sheets.add(sheet1);
    }

    public  void addSheet(String sheetName, LinkedHashMap<String, String> colName, List<T> resList){
        Sheet<T> sheet1 = new Sheet(sheetName, colName, resList);
        sheets.add(sheet1);
    }

    public void addSheet(Sheet<T> sheet){
        sheets.add(sheet);
    }

    @Override
    public void generate(){
        try{
            String uuid = UUID.randomUUID().toString();
            String tempFileName = uuid.replace("-", "");//uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
            String fileName = SERVICETEMPPATH + tempFileName +".xlsx";
            tempFile = new File(fileName);
            if(!tempFile.exists()) tempFile.createNewFile();
            FileOutputStream out = new FileOutputStream(tempFile);
            new POIExcel().export(sheets, out);
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

    @Override//永远返回false ，这个暂时不考虑分文件
    public boolean hashNext() {
        return false;
    }
}

package com.wangyu.fooline.offline.excel;

import com.wangyu.fooline.offline.FileGenerate;
import com.wangyu.fooline.offline.FileGenerateFactory;
import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.execption.ExcelDataException;
import com.wangyu.fooline.offline.execption.ExcelTableHeaderException;
import com.wangyu.fooline.offline.execption.OfflineDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangyu21 on 2017/8/23.
 */
@Service("statistDataGenerateFactory")
public class StatistDataGenerateFactory implements FileGenerateFactory {

    private static final Logger logger = LoggerFactory.getLogger(StatistDataGenerateFactory.class);

    @Resource(name = "statistDataMustObtained")
    private ExcelFileMustObtained excelFileMustObtained;

    @Override
    public FileGenerate getFileGenerator(Long entityId, Integer source, PageQuery page) throws IOException, OfflineDataException {
        return null;
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException {
        ExcelFileGenerate fileGenerate = new ExcelFileGenerate();
        try{
            switch (source){
                case 101 :
                    Sheet unReadSheet = getUnReadSheet(entityId);
                    fileGenerate.addSheet(unReadSheet);
                    Sheet hadReadSheet = getHadReadSheet(entityId);
                    fileGenerate.addSheet(hadReadSheet);
                    break;
                case 102 :
                    Sheet hadOverSheet = getHadOverSheet(entityId);
                    fileGenerate.addSheet(hadOverSheet);
                    Sheet unOverSheet = getUnOverSheet(entityId);
                    fileGenerate.addSheet(unOverSheet);
                    break;
                default:
            }
        }catch (ExcelTableHeaderException e){
            logger.error("excelGenerator step4 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------excelFileMustObtained.getColInfos";
            throw new OfflineDataException(errorMsg, e);
        }catch (ExcelDataException e){
            logger.error("excelGenerator step5 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错-----excelFileMustObtained.find";
            throw new OfflineDataException(errorMsg, e);
        }catch (Exception e){
            logger.error("excelGenerator step3 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------excelFileMustObtained.getColInfos-----excelFileMustObtained.find";
            logger.error(errorMsg);
            throw new OfflineDataException(errorMsg, e);
        }
        return fileGenerate;
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId) throws IOException, OfflineDataException {
        ExcelFileGenerate fileGenerate = new ExcelFileGenerate();
        try{
            Sheet unReadSheet = getUnReadSheet(entityId);
            fileGenerate.addSheet(unReadSheet);
            Sheet hadReadSheet = getHadReadSheet(entityId);
            fileGenerate.addSheet(hadReadSheet);
            Sheet hadOverSheet = getHadOverSheet(entityId);
            fileGenerate.addSheet(hadOverSheet);
            Sheet unOverSheet = getUnOverSheet(entityId);
            fileGenerate.addSheet(unOverSheet);
        }catch (ExcelTableHeaderException e){
            logger.error("excelGenerator step4 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------excelFileMustObtained.getColInfos";
            throw new OfflineDataException(errorMsg, e);
        }catch (ExcelDataException e){
            logger.error("excelGenerator step5 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错-----excelFileMustObtained.find";
            throw new OfflineDataException(errorMsg, e);
        }catch (Exception e){
            logger.error("excelGenerator step3 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------excelFileMustObtained.getColInfos-----excelFileMustObtained.find";
            logger.error(errorMsg);
            throw new OfflineDataException(errorMsg, e);
        }
        return fileGenerate;
    }

    private Sheet getUnReadSheet(Long entityId) throws ExcelTableHeaderException, ExcelDataException {
        Sheet unReadSheet = new Sheet();
        unReadSheet.setName("未读");
        LinkedHashMap<String, String> colNames =  excelFileMustObtained.getColInfos(entityId, 1);
        unReadSheet.setColName(colNames);

        List<Object> list = excelFileMustObtained.find(entityId, 1);
        unReadSheet.setResList(list);
        return unReadSheet;
    }

    private Sheet getHadReadSheet(Long entityId) throws ExcelTableHeaderException, ExcelDataException {
        Sheet hadReadSheet = new Sheet();
        hadReadSheet.setName("已读");
        LinkedHashMap<String, String> colNames =  excelFileMustObtained.getColInfos(entityId, 2);
        hadReadSheet.setColName(colNames);

        List<Object> list = excelFileMustObtained.find(entityId, 2);
        hadReadSheet.setResList(list);
        return hadReadSheet;
    }

    private Sheet getHadOverSheet(Long entityId) throws ExcelTableHeaderException, ExcelDataException {
        Sheet sheet = new Sheet();
        sheet.setName("已完成");
        LinkedHashMap<String, String> colNames =  excelFileMustObtained.getColInfos(entityId, 3);
        sheet.setColName(colNames);

        List<Object> list = excelFileMustObtained.find(entityId, 3);
        sheet.setResList(list);
        return sheet;
    }

    private Sheet getUnOverSheet(Long entityId) throws ExcelTableHeaderException, ExcelDataException {
        Sheet sheet = new Sheet();
        sheet.setName("待完成");
        LinkedHashMap<String, String> colNames =  excelFileMustObtained.getColInfos(entityId, 4);
        sheet.setColName(colNames);

        List<Object> list = excelFileMustObtained.find(entityId, 4);
        sheet.setResList(list);
        return sheet;
    }


    @Override
    public FileGenerate getExcelFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException{
        return getFileGenerator(entityId,source);
    }

}

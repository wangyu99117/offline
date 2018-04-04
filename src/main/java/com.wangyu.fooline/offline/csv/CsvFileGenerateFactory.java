package com.wangyu.fooline.offline.csv;


import com.wangyu.fooline.offline.FileGenerate;
import com.wangyu.fooline.offline.FileGenerateFactory;
import com.wangyu.fooline.offline.demo.ElementType;
import com.wangyu.fooline.offline.demo.FormElement;
import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.excel.ExcelFileGenerate;
import com.wangyu.fooline.offline.execption.CsvDataException;
import com.wangyu.fooline.offline.execption.CsvTableHeaderException;
import com.wangyu.fooline.offline.execption.OfflineDataException;
import com.wangyu.fooline.offline.utils.excel.AttachPathUtil;
import com.wangyu.fooline.offline.utils.excel.ImgPathUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangyu21 on 2016/10/20.
 * 表单文件生成器的工厂
 * 不是表单文件的工厂，是生成器的工厂。一个生成器，根据所给参数 生成一个表单文件
 */
@Service("csvFileGenerateFactory")
public class CsvFileGenerateFactory implements FileGenerateFactory {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileGenerateFactory.class);
    @Resource
    private CsvFileMustObtained csvFileMustObtained;

    @Override
    public FileGenerate getExcelFileGenerator(Long formId, Integer source) throws IOException, OfflineDataException {
        final List<String> imgPaths = new ArrayList<>();
        final List<String> filePaths = new ArrayList<>();

        try{
            LinkedHashMap<String, String> colName = csvFileMustObtained.getColInfos(formId, new FormElementFormat(){
                public void format(List<FormElement> formElements) {
                    for(FormElement ele: formElements){
                        if(ele.getType() == ElementType.IMAGE.getType().intValue()){
                            imgPaths.add(ele.getCode());
                        }
                        if(ele.getType() == ElementType.ATTACH.getType().intValue()){
                            filePaths.add(ele.getCode());
                        }
                    }
                }
            });

            List<Object> resList = csvFileMustObtained.find(formId, source, new PageQuery(1,10000));
            if(resList.size() > 0){
                //logger.info("csvGenerator step1-----------param---------scrollId = {}", endId);
            }
            logger.info("csvToExcelGenerator step2-----------queryFormDataDetail---------resLis.size() = {}", resList.size());


            for(Iterator<Object> iterator = resList.iterator(); iterator.hasNext();){
                Map<String, Object> detailmap = (Map<String, Object>)iterator.next();

                for(String imgPath : imgPaths){
                    Object oimgPath = detailmap.get(imgPath);
                    String imgShowPath = "";
                    if(oimgPath != null && StringUtils.isNotBlank((String)oimgPath)) {
                        imgShowPath = ImgPathUtil.convert(oimgPath);
                    }
                    detailmap.put(imgPath, imgShowPath);
                }
                for(String filePath : filePaths){
                    Object ofilePath = detailmap.get(filePath);
                    String fileShowPath = "";
                    if(ofilePath != null && StringUtils.isNotBlank((String)ofilePath)) {
                        fileShowPath = AttachPathUtil.convert(ofilePath);
                    }

                    detailmap.put(filePath, fileShowPath);
                }
            }

            ExcelFileGenerate fileGenerate = new ExcelFileGenerate();
            fileGenerate.addSheet("全部",colName,resList);
            return fileGenerate;
        } catch (Exception e){
            logger.error("csvToExcelGenerator step3 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------csvToExcelFileMustObtained.getColInfos-----csvToExcelFileMustObtained.find";
            logger.error(errorMsg);
            throw new OfflineDataException(errorMsg, e);
        }
    }
    @Override
    public FileGenerate getFileGenerator(Long formId, Integer source, PageQuery page) throws IOException, OfflineDataException {
        final List<String> imgPaths = new ArrayList<>();
        final List<String> filePaths = new ArrayList<>();

        try{
            LinkedHashMap<String, String> colName = csvFileMustObtained.getColInfos(formId, new FormElementFormat(){
                @Override
                public void format(List<FormElement> formElements) {
                    for(FormElement ele: formElements){
                        if(ele.getType() == ElementType.IMAGE.getType().intValue()){
                            imgPaths.add(ele.getCode());
                        }
                        if(ele.getType() == ElementType.ATTACH.getType().intValue()){
                            filePaths.add(ele.getCode());
                        }
                    }
                }
            });

            logger.info("csvGenerator-----------queryFormDataDetail------start----startId={} ----endId ={}", page.getStartId(),page.getEndId());
            List<Object> resList = csvFileMustObtained.find(formId, source, page);
            if(resList.size() > 0){
                Map<String, Object> startData =(Map<String, Object>) resList.get(resList.size()-1);
                Map<String, Object> endData =(Map<String, Object>) resList.get(0);
                Long startId = (Long)startData.get("id");
                Long endId = (Long)endData.get("id");
                //page.setStartId(startId).setEndId(endId);
                logger.info("csvGenerator step1-----------param---------startId = {}", startId);
                logger.info("csvGenerator step1-----------param---------endId = {}", endId);
            }
            logger.info("csvGenerator step2-----------queryFormDataDetail---------resLis.size() = {}", resList.size());


            for(Iterator<Object> iterator = resList.iterator(); iterator.hasNext();){
                Map<String, Object> detailmap = (Map<String, Object>)iterator.next();

                for(String imgPath : imgPaths){
                    Object oimgPath = detailmap.get(imgPath);
                    String imgShowPath = "";
                    if(oimgPath != null && StringUtils.isNotBlank((String)oimgPath)) {
                        imgShowPath = ImgPathUtil.convert(oimgPath);
                    }
                    detailmap.put(imgPath, imgShowPath);
                }
                for(String filePath : filePaths){
                    Object ofilePath = detailmap.get(filePath);
                    String fileShowPath = "";
                    if(ofilePath != null && StringUtils.isNotBlank((String)ofilePath)) {
                        fileShowPath = AttachPathUtil.convert(ofilePath);
                    }

                    detailmap.put(filePath, fileShowPath);
                }
            }

            CsvFileGenerate fileGenerate = new CsvFileGenerate(colName, resList);
            return fileGenerate;
        } catch (CsvTableHeaderException e){
            logger.error("csvGenerator step4 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------csvFileMustObtained.getColInfos";
            throw new OfflineDataException(errorMsg, e);
        } catch (CsvDataException e){
            logger.error("csvGenerator step5 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错-----csvFileMustObtained.find";
            throw new OfflineDataException(errorMsg, e);
        } catch (Exception e){
            logger.error("csvGenerator step3 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------csvFileMustObtained.getColInfos-----csvFileMustObtained.find";
            logger.error(errorMsg);
            throw new OfflineDataException(errorMsg, e);
        }
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException {
        final List<String> imgPaths = new ArrayList<>();
        final List<String> filePaths = new ArrayList<>();

        try{
            LinkedHashMap<String, String> colName = csvFileMustObtained.getColInfos(entityId, new FormElementFormat(){
                @Override
                public void format(List<FormElement> formElements) {
                    for(FormElement ele: formElements){
                        if(ele.getType() == ElementType.IMAGE.getType().intValue()){
                            imgPaths.add(ele.getCode());
                        }
                        if(ele.getType() == ElementType.ATTACH.getType().intValue()){
                            filePaths.add(ele.getCode());
                        }
                    }
                }
            });

            List<Object> resList = csvFileMustObtained.find(entityId, source, null);
            if(resList.size() > 0){
                //logger.info("csvGenerator step1-----------param---------scrollId = {}", endId);
            }
            logger.info("csvGenerator step2-----------queryFormDataDetail---------resLis.size() = {}", resList.size());


            for(Iterator<Object> iterator = resList.iterator(); iterator.hasNext();){
                Map<String, Object> detailmap = (Map<String, Object>)iterator.next();

                for(String imgPath : imgPaths){
                    Object oimgPath = detailmap.get(imgPath);
                    String imgShowPath = "";
                    if(oimgPath != null && StringUtils.isNotBlank((String)oimgPath)) {
                        imgShowPath = ImgPathUtil.convert(oimgPath);
                    }
                    detailmap.put(imgPath, imgShowPath);
                }
                for(String filePath : filePaths){
                    Object ofilePath = detailmap.get(filePath);
                    String fileShowPath = "";
                    if(ofilePath != null && StringUtils.isNotBlank((String)ofilePath)) {
                        fileShowPath = AttachPathUtil.convert(ofilePath);
                    }

                    detailmap.put(filePath, fileShowPath);
                }
            }

            CsvFileGenerate fileGenerate = new CsvFileGenerate(colName, resList);
            return fileGenerate;
        } catch (CsvTableHeaderException e){
            logger.error("csvGenerator step4 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------csvFileMustObtained.getColInfos";
            throw new OfflineDataException(errorMsg, e);
        } catch (CsvDataException e){
            logger.error("csvGenerator step5 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错-----csvFileMustObtained.find";
            throw new OfflineDataException(errorMsg, e);
        } catch (Exception e){
            logger.error("csvGenerator step3 原始异常信息！", e);
            String errorMsg = "离线下载数据查询失败！出错原因是本方法逻辑不健全，或者是两个方法返回值出错------csvFileMustObtained.getColInfos-----csvFileMustObtained.find";
            logger.error(errorMsg);
            throw new OfflineDataException(errorMsg, e);
        }
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId) throws IOException, OfflineDataException {
        return null;
    }
}

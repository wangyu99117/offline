package com.wangyu.fooline.offline;

import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.execption.OfflineDataException;

import java.io.IOException;

/**
 * Created by wangyu21 on 2016/10/19.
 *  经过仔细核对，这是一个 工厂方法设计模式的应用。
 *  这个接口是一个 工厂接口
 *   这个接口生产的对象都是 FileGenerate
 *   具体生产哪种 FileGenerate 由其实现类决定
 */
public interface FileGenerateFactory {

    /**
     *
     * @return
     * @throws IOException
     */
    FileGenerate getFileGenerator(Long entityId, Integer source, PageQuery page) throws IOException, OfflineDataException;

    FileGenerate getFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException;

    FileGenerate getFileGenerator(Long entityId) throws IOException, OfflineDataException;

    FileGenerate getExcelFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException;



}

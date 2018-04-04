package com.wangyu.fooline.offline;


import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.execption.OfflineDataException;

import java.io.IOException;

/**
 * Created by wangyu21 on 2017/8/29.
 */
public class AbstractFileGenerateFactory implements FileGenerateFactory{

    @Override
    public FileGenerate getExcelFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException {
        return null;
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId, Integer source, PageQuery page) throws IOException, OfflineDataException {
        return null;
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId, Integer source) throws IOException, OfflineDataException {
        return null;
    }

    @Override
    public FileGenerate getFileGenerator(Long entityId) throws IOException, OfflineDataException {
        return null;
    }
}

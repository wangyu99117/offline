package com.wangyu.fooline.offline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangyu21 on 2016/10/19.
 */
public abstract class FileGenerate {

    protected static final String SERVICETEMPPATH = "/export/Logs/pop-form.jd.local/tempdata/";
    protected static final Logger logger = LoggerFactory.getLogger(FileGenerate.class);

    protected File tempFile;

    protected Long fileSize;

    static {
        File dir = new File(SERVICETEMPPATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public abstract void generate() throws IOException;

    public abstract String getBucket();

    public Long getFileSize() {
        return fileSize;
    }

    public abstract boolean hashNext();

    public File getTempFile() {
        return tempFile;
    }

}

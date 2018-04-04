package com.wangyu.fooline.offline.utils.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wangyu21 on 2016/10/12.
 */
public class AttachPathUtil {

    private static final String ATTACHPREFIX = "//storage.apache.com/";

    public static String convert(Object dbPath){
        if(dbPath == null || StringUtils.isBlank((String)dbPath)) {
            return "";
        }

        JSONObject dbObj =  JSON.parseObject(dbPath.toString());
        return ATTACHPREFIX + dbObj.get("bucketName") + "/" + dbObj.get("path");
    }
}

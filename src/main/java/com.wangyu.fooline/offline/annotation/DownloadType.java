package com.wangyu.fooline.offline.annotation;



import com.wangyu.demo.DownloadTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangyu21 on 2016/12/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DownloadType {

    DownloadTypeEnum downloadType();
}

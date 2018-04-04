package com.wangyu.fooline.offline.csv;

/**
 * Created by wangyu21 on 2017/6/26.
 */
public class ParamQuery {

    /**
     * 查询属性名称
     */
    private String attributeName;

    /**
     * 查询属性值
     */
    private String attributeValue;

    public ParamQuery() {
    }

    public ParamQuery(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}

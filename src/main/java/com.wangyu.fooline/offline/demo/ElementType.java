package com.wangyu.fooline.offline.demo;

/**
 * Created by wuxiaoyan on 2016/9/19.
 */
public enum ElementType {
    INPUT("文本", 1),
    SELECT("下拉列表", 2),
    IMAGE("图片", 3),
    ATTACH("附件", 4),
    NUMBER("数字", 5),
    LINK("链接", 6),
    DATE("日期", 8);

    //DATE_PICKER("单选", 7),


    private String name;
    private Integer type;

    private ElementType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

package com.wangyu.fooline.offline.csv;



import com.wangyu.fooline.offline.demo.FormElement;

import java.util.List;

/**
 * Created by wangyu21 on 2016/10/24.
 */
public interface FormElementFormat {

    // FormElement  元素需要 具体处理的 字段
    void format(List<FormElement> formElements);
}

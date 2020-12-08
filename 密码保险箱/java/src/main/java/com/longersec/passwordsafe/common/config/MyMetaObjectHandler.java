package com.longersec.passwordsafe.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.longersec.passwordsafe.common.utils.Constant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * @author wxy
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String[] setterNames = metaObject.getSetterNames();
        HashSet<String> setterNameSet = new HashSet<>(Arrays.asList(setterNames));
        //属性名称，不是字段名称
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        if (setterNameSet.contains("deleted")) {
            //默认未删除
            setFieldValByName("deleted", Constant.DATA_NOT_DELETED, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}

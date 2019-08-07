package com.guli.common.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//自动填充
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("start insert fill ....");
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("is_Deleted", false, metaObject);
        this.setFieldValByName("version", 1L, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("start update fill ....");
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}

package com.guli.edu.microservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.microservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.query.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-03-19
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    void pageList(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}

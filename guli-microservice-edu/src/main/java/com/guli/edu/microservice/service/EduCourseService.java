package com.guli.edu.microservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.CourseWebVo;
import com.guli.edu.microservice.entity.Form.CoursePublishVo;
import com.guli.edu.microservice.entity.Form.CourseInfoForm;
import com.guli.edu.microservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-03-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    Boolean publishCourseById(String id);

    List<EduCourse> selectByTeacherId(String teacherId);

    Map<String,Object> pageListWeb(Page<EduCourse> pageParam);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);

}

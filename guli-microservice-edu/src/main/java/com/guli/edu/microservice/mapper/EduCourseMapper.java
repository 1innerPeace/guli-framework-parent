package com.guli.edu.microservice.mapper;


import com.guli.common.vo.CourseWebVo;
import com.guli.edu.microservice.entity.Form.CoursePublishVo;
import com.guli.edu.microservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2019-03-27
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishVoById(String id);

    CourseWebVo selectInfoWebById(String courseId);

}

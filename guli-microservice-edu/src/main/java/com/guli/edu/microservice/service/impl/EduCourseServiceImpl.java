package com.guli.edu.microservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.CourseWebVo;
import com.guli.edu.microservice.entity.Form.CoursePublishVo;
import com.guli.edu.microservice.mapper.EduCourseMapper;
import com.guli.edu.microservice.service.EduChapterService;
import com.guli.edu.microservice.service.EduVideoService;
import com.guli.edu.microservice.entity.EduCourse;
import com.guli.edu.microservice.entity.EduCourseDescription;
import com.guli.edu.microservice.entity.Form.CourseInfoForm;
import com.guli.edu.microservice.service.EduCourseDescriptionService;
import com.guli.edu.microservice.service.EduCourseService;
import com.guli.common.handler.EduException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-03-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //注入课程描述service
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //1 首先把课程基本信息，添加到课程表中
        EduCourse eduCourse = new EduCourse();
        //把courseInfoForm表单对象里面数据复制到EduCourse对象里面
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        //实现添加课程基本信息到课程表
        int count = baseMapper.insert(eduCourse);
        if(count == 0){
            //添加失败
            throw new EduException(20001,"添加课程基本信息失败");
        }

        //2 其次把课程描述，添加到课程描述表
        //从表单对象里面获取课程描述
        String description = courseInfoForm.getDescription();
        //描述表里面需要课程表id值(一对一)
        String id = eduCourse.getId();
        //创建课程描述对象
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //向对象设置数据
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(description);
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if(!save) {
            throw new EduException(20001,"添加课程描述失败");
        }
        return id;
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        EduCourse course = this.getById(id);
        if(course == null){
            throw new GuliException(20001, "数据不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(id);
        if(courseDescription == null){
            throw new GuliException(20001, "无该课程详情");
        }
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {

        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new GuliException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = eduCourseDescriptionService.updateById(courseDescription);
        if(!resultDescription){
            throw new GuliException(20001, "课程详情信息保存失败");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
         return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public Boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        Integer count = baseMapper.updateById(course);
        return null != count && count>0;
    }


    public boolean removeCourseById(String id) {

        //根据id删除所有视频
        eduVideoService.removeByCourseId(id);

        //根据id删除所有章节
        eduChapterService.removeByCourseId(id);

        //根据id删除所有课程详情
        eduCourseDescriptionService.removeById(id);

        //删除封面 TODO 独立完成

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    /**
     * 根据讲师id查询当前讲师的课程列表
     * @param teacherId
     * @return
     */
    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    //分页查询课程列表
    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Transactional
    @Override
    public CourseWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

    //分页查询已发布的课程

}

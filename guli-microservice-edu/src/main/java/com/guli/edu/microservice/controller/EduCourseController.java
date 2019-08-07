package com.guli.edu.microservice.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.ChapterDto;
import com.guli.common.vo.CourseWebVo;
import com.guli.common.vo.R;
import com.guli.edu.microservice.entity.Form.CoursePublishVo;
import com.guli.edu.microservice.service.EduChapterService;
import com.guli.edu.microservice.service.EduCourseService;
import com.guli.edu.microservice.entity.EduCourse;
import com.guli.edu.microservice.entity.Form.CourseInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-03-27
 */
@Api(description="添加课程信息")
@CrossOrigin //跨域
@RestController
@RequestMapping("/course/admin")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;

    //添加课程基本信息的方法
    @PostMapping("/info")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String id = eduCourseService.insertCourseInfo(courseInfoForm);
        return R.ok().data("courseId",id);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(id);

        //查询当前课程的章节信息
        List<ChapterDto> chapterVoList = eduChapterService.getAllChapterVedio(id);
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoForm).data("chapterVoList", chapterVoList);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
                                  @RequestBody CourseInfoForm courseInfoForm,

                                  @ApiParam(name = "id", value = "课程ID", required = true)
                                  @PathVariable String id){

        eduCourseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(@ApiParam(name = "id", value = "课程ID", required = true)
                                     @PathVariable String id){

        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return  R.ok().data("item",courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(@ApiParam(name = "id", value = "课程ID", required = true)
                                @PathVariable String id){

        eduCourseService.publishCourseById(id);
        return R.ok();
    }

    //实现查询所有课程
    @GetMapping("/getAll")
    public R getAllCourse(){
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("items",list);
    }


    @ApiOperation(value = "分页课程列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);

        Map<String, Object> map = eduCourseService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }



}


package com.guli.edu.microservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.microservice.entity.EduCourse;
import com.guli.edu.microservice.entity.EduTeacher;
import com.guli.edu.microservice.service.EduCourseService;
import com.guli.edu.microservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-03-19
 */
@Api(description="讲师管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/list")
    public R getlist(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }


    //逻辑删除
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/delete/{id}")
    public R removeTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
             return R.ok();
         }else{
             return R.error();
         }

    }

    //新增
    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        eduTeacherService.save(teacher);
        return R.ok();
    }

    //根据ID查询
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/query/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return R.ok().data("item", teacher).data("courseList", courseList);
    }


    //根据ID修改
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("/update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return R.ok();
    }

    //登录方法
    @PostMapping("/login")
    public R login(){
        //{"code":20000,"data":{"token":"admin"}}
        return R.ok().data("token","admin");
    }

    //info方法获取登录信息
    @GetMapping("/info")
    public R info(){
        //{"code":20000,"data":{"token":"admin"}}
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);

        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }


}


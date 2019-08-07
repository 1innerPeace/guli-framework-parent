package com.guli.edu.microservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.microservice.entity.EduTeacher;
import com.guli.edu.microservice.service.EduTeacherService;
import com.guli.edu.query.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="带条件分页查询")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin1/teacher1")
public class TeacherAdminController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private TeacherQuery teacherQuery;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
             TeacherQuery teacherQuery){



        Page<EduTeacher> pageParam = new Page<>(page, limit);



        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();



        return  R.ok().data("total", total).data("rows", records);
    }

 /*   public R pageQuery(){

        if(page <= 0 || limit <= 0){
            //throw new GuliException(21003, "参数不正确1");
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }
    ......
    }
*/
     @ApiOperation(value = "分页查询讲师列表")
     @PostMapping("/query/{page}/{limit}")
     public R pageQuery(@ApiParam(name = "page", value = "当前页码", required = true)
                             @PathVariable Long page,

                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                             @PathVariable Long limit,

                        @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                             @RequestBody TeacherQuery teacherQuery){

         Page<EduTeacher> pageParam = new Page<>(page, limit);
         eduTeacherService.pageList(pageParam,teacherQuery);
         List<EduTeacher> records = pageParam.getRecords();
         long total = pageParam.getTotal();


         return R.ok().data("total", total).data("rows", records);
     }





}

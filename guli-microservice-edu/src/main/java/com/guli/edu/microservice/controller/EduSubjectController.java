package com.guli.edu.microservice.controller;


import com.guli.common.vo.R;
import com.guli.common.vo.SubjectOneLevel;
import com.guli.edu.microservice.entity.EduSubject;
import com.guli.edu.microservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-03-25
 */
@Api(description="课程分类管理3")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin2/edu/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

 /*   @ApiOperation(value = "Excel批量导入")
    @PostMapping("/import")
    private R addUser() throws Exception {
        return addUser();
    }
*/
    //删除方法
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        boolean flag = eduSubjectService.deleteById(id);
        if(flag){
            return R.ok();

        }else{
            return R.error();
        }
    }

    //返回所有分类数据 按照要求格式返回
    // dto方式
    @GetMapping("/formate")
    public R getSubjectList() {
        List<SubjectOneLevel> list = eduSubjectService.getListSubject();
        return R.ok().data("items",list);
    }


    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/import2")
    private R addUser(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        List<String> msg = eduSubjectService.importData(file);
        if (msg.size() == 0) {
            return R.ok().message("批量导入成功");
        } else {
            return R.error().message("部分数据导入失败").data("messageList", msg);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/nested")
    public R nestedList() {

        List<SubjectOneLevel> subjectNestedVoList = eduSubjectService.getListSubject();
        return R.ok().data("items", subjectNestedVoList);
    }

    //添加一级分类
    @ApiOperation(value = "新增一级分类")
    @PostMapping("/saveSubjectOneLeve")
    public R getSubjectOneLeve (
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject eduSubject
            ){
        boolean result = eduSubjectService.addLevelOne(eduSubject);
        if(result){
            return R.ok().message("上传成功");
        }else{
            return R.error().message("上传失败");
        }

    }

    @ApiOperation(value = "新增二级分类")
    @PostMapping("/saveSubjectTwoLeve")
    public R saveLevelTwo(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject eduSubject){
        boolean result = eduSubjectService.addLevelTwo(eduSubject);
        if(result){
            return R.ok();
        }else{
            return R.error().message("保存失败");
        }
    }

}
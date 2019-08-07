package com.guli.edu.microservice.controller;


import com.guli.common.vo.ChapterDto;
import com.guli.common.vo.R;
import com.guli.edu.microservice.entity.EduChapter;
import com.guli.edu.microservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-03-29
 */
@Api(description = "章节管理")
@RestController
@CrossOrigin //跨域
@RequestMapping("/chapter/admin")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

     //查询课程里面所有的章节和每个章节里面的小结
     @ApiOperation(value = "根据courseId查询其下所有章节")
     @GetMapping("/nested-list/{courseId}")
     public R getChapterVideo(@ApiParam(name = "courseId", value = "课程ID", required = true)
             @PathVariable String courseId){
         List<ChapterDto> chapterVoList = eduChapterService.getAllChapterVedio(courseId);
         return R.ok().data("items",chapterVoList);
     }

     @ApiOperation(value = "新增章节")
     @PostMapping("/addChapter")
     public R save(
         @ApiParam(name = "chapterVo", value = "章节对象", required = true)
         @RequestBody EduChapter chapter ){
         eduChapterService.save(chapter);
         return R.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("/getById/{id}")
    public R getById( @ApiParam(name = "id", value = "章节ID", required = true)
                       @PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);
        return R.ok().data("item",chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("/updateById/{id}")
    public R updateById( @ApiParam(name = "id", value = "章节ID", required = true)
                          @PathVariable String id,

                          @ApiParam(name = "chapter", value = "章节对象", required = true)
                          @RequestBody EduChapter chapter){

        chapter.setId(id);
        eduChapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("/delete/{id}")
    public R removeById( @ApiParam(name = "id", value = "章节ID", required = true)
                          @PathVariable String id){

        boolean result = eduChapterService.removeChapterById(id);
        if (result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }



}


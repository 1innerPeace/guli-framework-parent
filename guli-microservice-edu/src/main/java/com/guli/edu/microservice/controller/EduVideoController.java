package com.guli.edu.microservice.controller;


import com.guli.common.vo.R;
import com.guli.edu.microservice.entity.Form.VideoInfoForm;
import com.guli.edu.microservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-03-29
 */
@Api(description="课时管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/video/admin")
public class EduVideoController {

   @Autowired
   private EduVideoService eduVideoService;

   @ApiOperation(value = "新增课时")
   @PostMapping("/save")
   public R save(@ApiParam(name = "videoForm", value = "课时对象", required = true)
                    @RequestBody VideoInfoForm videoInfoForm){

       eduVideoService.saveVideoInfo(videoInfoForm);
       return R.ok();
   }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("/query/{id}")
    public R getVideoInfoById(@ApiParam(name = "id", value = "课时ID", required = true)
                               @PathVariable String id){

        VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(id);
        return  R.ok().data("items",videoInfoForm);
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "课时ID", required = true)
                         @PathVariable String id){

        boolean result = eduVideoService.removeVideoById(id);
        if (result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("update/{id}")
    public R updateCourseInfoById(@ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
                                   @RequestBody VideoInfoForm videoInfoForm,

                                   @ApiParam(name = "id", value = "课时ID", required = true)
                                   @PathVariable String id){

        eduVideoService.updateVideoInfoById(videoInfoForm);
        return R.ok();

    }



}


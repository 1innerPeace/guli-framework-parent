package com.guli.edu.microservice.service;

import com.guli.edu.microservice.entity.EduVideo;
import com.guli.edu.microservice.entity.Form.VideoInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-03-29
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String id);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);


    boolean removeByCourseId(String id);
}

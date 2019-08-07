package com.guli.edu.microservice.service;

import com.guli.common.vo.ChapterDto;
import com.guli.edu.microservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-03-29
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterDto> getAllChapterVedio(String CourseId);

    boolean removeChapterById(String id);

    void removeByCourseId(String id);
}

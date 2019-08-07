package com.guli.edu.microservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.microservice.entity.EduChapter;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.ChapterDto;
import com.guli.common.vo.VideoDto;
import com.guli.edu.microservice.entity.EduVideo;
import com.guli.edu.microservice.mapper.EduChapterMapper;
import com.guli.edu.microservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.microservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-03-29
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    //查询所有章节
    @Override
    public List<ChapterDto> getAllChapterVedio(String courseId) {

        //最终要的到的数据列表
        List<ChapterDto> chapterVoArrayList = new ArrayList<>();
        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        queryWrapper1.orderByAsc("sort","id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);
        //获取视频信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> videos = eduVideoService.list(queryWrapper2);

        //填充章节vo数据
        int count1 = chapters.size();
        for (int i = 0; i <count1 ; i++) {
            EduChapter chapter = chapters.get(i);
            //创建章节vo对象
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterVoArrayList.add(chapterDto);

            //填充视频vo数据
            ArrayList<VideoDto> videoVoArrayList = new ArrayList<>();

            int count2 = videos.size();
            for (int j = 0; j <count2 ; j++) {
                EduVideo eduVideo = videos.get(j);

                if(chapter.getId().equals(eduVideo.getChapterId())){
                    //创建视频vo对象
                    VideoDto videoDto = new VideoDto();
                    BeanUtils.copyProperties(eduVideo,videoDto);
                    videoVoArrayList.add(videoDto);

                }
            }
            chapterDto.setChildren(videoVoArrayList);

        }

        return chapterVoArrayList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if(eduVideoService.getCountByChapterId(id)){
            throw new GuliException(20001,"该份章节下存在视频课程，请先删除视频课程");

        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result>0;
    }

    @Override
    public void removeByCourseId(String id) {

    }
}

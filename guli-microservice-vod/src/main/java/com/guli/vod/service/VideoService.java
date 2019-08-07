package com.guli.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId) throws ClientException;

    void removeVideoList(List<String> videoIdList) throws ClientException;
}

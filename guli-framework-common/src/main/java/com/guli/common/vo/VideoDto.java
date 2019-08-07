package com.guli.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ApiModel(value = "小节信息")
@Data
public class VideoDto {

    private String id;
    private String title;
    private Boolean isFree;
    private String videoSourceId;
    private String videoOriginalName;

}

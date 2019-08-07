package com.guli.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "章节信息")
@Data
public class ChapterDto {

    private String id;//章节id

    private String title;//章节名称

    //多个小结
    private List children =new ArrayList<>();

}

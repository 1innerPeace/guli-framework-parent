package com.guli.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectOneLevel {

    private String id;
    private String title;
    private List<SubjectTwoLevel> children = new ArrayList<>();
}

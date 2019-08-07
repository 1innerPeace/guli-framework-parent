package com.guli.edu.microservice.service;

import com.guli.common.vo.SubjectOneLevel;
import com.guli.edu.microservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-03-25
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importData(MultipartFile file);

    List<SubjectOneLevel> getListSubject();

    boolean deleteById(String id);

    boolean addLevelOne(EduSubject eduSubject);

    boolean addLevelTwo(EduSubject eduSubject);
}

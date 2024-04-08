package com.wyd.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.xuecheng.content.model.dto.CourseTeacherDTO;
import com.wyd.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author wyd
 * @since 2024-02-01
 */
public interface CourseTeacherService extends IService<CourseTeacher> {

    List<CourseTeacherDTO> queryCourseTeacher(Long courseId);

    CourseTeacherDTO saveCourseTeacher(CourseTeacherDTO teacherDTO);

    void deleteCourseTeacher(Long courseId, Long teacherId);
}

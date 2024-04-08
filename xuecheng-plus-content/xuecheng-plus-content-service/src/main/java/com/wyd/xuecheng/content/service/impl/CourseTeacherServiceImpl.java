package com.wyd.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyd.xuecheng.content.mapper.CourseTeacherMapper;
import com.wyd.xuecheng.content.model.dto.CourseTeacherDTO;
import com.wyd.xuecheng.content.model.po.CourseTeacher;
import com.wyd.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author wyd
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

    @Resource
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacherDTO> queryCourseTeacher(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(queryWrapper);
        return courseTeachers.stream().map(r -> {
            CourseTeacherDTO dto = new CourseTeacherDTO();
            BeanUtils.copyProperties(r, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CourseTeacherDTO saveCourseTeacher(CourseTeacherDTO teacherDTO) {
        Long id = teacherDTO.getId();
        if (id != null) {
            // 更新内容
            CourseTeacher courseTeacher = courseTeacherMapper.selectById(id);
            BeanUtils.copyProperties(teacherDTO, courseTeacher);
            courseTeacherMapper.updateById(courseTeacher);
            return teacherDTO;
        } else {
            // 新增内容
            CourseTeacher courseTeacher = new CourseTeacher();
            BeanUtils.copyProperties(teacherDTO, courseTeacher);
            courseTeacherMapper.insert(courseTeacher);
            teacherDTO.setId(courseTeacher.getId());
            return teacherDTO;
        }
    }

    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        queryWrapper.eq(CourseTeacher::getId, teacherId);
        courseTeacherMapper.delete(queryWrapper);
    }
}

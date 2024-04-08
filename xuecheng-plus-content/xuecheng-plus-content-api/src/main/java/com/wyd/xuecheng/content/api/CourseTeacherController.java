package com.wyd.xuecheng.content.api;

import com.wyd.xuecheng.content.model.dto.CourseTeacherDTO;
import com.wyd.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: xuecheng-plus-content
 * @author: Stone
 * @create: 2024-04-07 21:07
 */
@Api(value = "课程教师编辑接口",tags = "课程教师编辑接口")
@RestController
public class CourseTeacherController {

    @Resource
    private CourseTeacherService courseTeacherService;

    @ApiOperation("查询课程教师信息")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacherDTO> list(@PathVariable Long courseId){
        return courseTeacherService.queryCourseTeacher(courseId);
    }

    @ApiOperation("添加或者修改课程教师信息")
    @PostMapping("/courseTeacher")
    public CourseTeacherDTO saveCourseTeacher(@RequestBody CourseTeacherDTO teacherDTO){
        return courseTeacherService.saveCourseTeacher(teacherDTO);
    }

    @ApiOperation("删除课程教师信息")
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        courseTeacherService.deleteCourseTeacher(courseId, teacherId);
    }


}
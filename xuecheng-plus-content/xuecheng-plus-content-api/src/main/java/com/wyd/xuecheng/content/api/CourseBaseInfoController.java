package com.wyd.xuecheng.content.api;

import com.wyd.xuecheng.base.exception.ValidationGroups;
import com.wyd.xuecheng.content.model.dto.AddCourseDto;
import com.wyd.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.wyd.xuecheng.content.model.dto.EditCourseDto;
import com.wyd.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.wyd.xuecheng.content.model.po.CourseBase;
import com.wyd.xuecheng.content.service.CourseBaseInfoService;
import com.wyd.xuecheng.base.model.PageParams;
import com.wyd.xuecheng.base.model.PageResult;
import com.wyd.xuecheng.content.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @description 课程信息编辑接口
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
@RestController
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
public class CourseBaseInfoController {

    @Resource
    private CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")//拥有课程列表查询的权限方可访问
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody (required=false) QueryCourseParamsDto queryCourseParams){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String companyId = user.getCompanyId();
       return courseBaseInfoService.queryCourseBaseList(companyId, pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated({ValidationGroups.Inster.class, Default.class}) AddCourseDto addCourseDto){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String companyId = user.getCompanyId();
        return courseBaseInfoService.createCourseBase(Long.parseLong(companyId), addCourseDto);
    }

    @ApiOperation("根据课程id查询课程基础信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){

        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改课程基础信息")
    @PutMapping("/course")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated EditCourseDto editCourseDto){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String companyId = user.getCompanyId();
        return courseBaseInfoService.updateCourseBase(Long.parseLong(companyId),editCourseDto);
    }

    @ApiOperation("根据课程id删除课程信息")
    @DeleteMapping ("/course/{courseId}")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")
    public void deleteCourseById(@PathVariable Long courseId){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String companyId = user.getCompanyId();
        courseBaseInfoService.deleteCourseById(Long.parseLong(companyId), courseId);
    }

}

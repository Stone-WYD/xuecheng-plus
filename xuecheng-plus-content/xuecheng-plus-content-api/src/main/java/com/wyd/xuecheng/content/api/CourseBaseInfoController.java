package com.wyd.xuecheng.content.api;

import com.wyd.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.wyd.xuecheng.content.model.po.CourseBase;
import com.wyd.xuecheng.content.service.CourseBaseInfoService;
import com.wyd.xuecheng.model.PageParams;
import com.wyd.xuecheng.model.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody (required=false) QueryCourseParamsDto queryCourseParams){
       return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
    }

}

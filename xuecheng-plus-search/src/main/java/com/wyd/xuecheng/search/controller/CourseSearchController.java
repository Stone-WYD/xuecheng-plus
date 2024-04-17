package com.wyd.xuecheng.search.controller;

import com.wyd.xuecheng.base.model.PageParams;
import com.wyd.xuecheng.search.dto.SearchPageResultDto;
import com.wyd.xuecheng.search.service.CourseSearchService;
import com.wyd.xuecheng.search.dto.SearchCourseParamDto;
import com.wyd.xuecheng.search.po.CourseIndex;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 课程搜索接口
 * @author Mr.M
 * @date 2022/9/24 22:31
 * @version 1.0
 */
@Api(value = "课程搜索接口",tags = "课程搜索接口")
 @RestController
 @RequestMapping("/course")
public class CourseSearchController {

 @Autowired
 CourseSearchService courseSearchService;


 @ApiOperation("课程搜索列表")
  @GetMapping("/list")
 public SearchPageResultDto<CourseIndex> list(PageParams pageParams, SearchCourseParamDto searchCourseParamDto){

    return courseSearchService.queryCoursePubIndex(pageParams,searchCourseParamDto);
   
  }
}

package com.wyd.xuecheng.content.service;

import com.wyd.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.wyd.xuecheng.content.model.po.CourseBase;
import com.wyd.xuecheng.model.PageParams;
import com.wyd.xuecheng.model.PageResult;

public interface CourseBaseInfoService  {

    /*
     * @description 课程查询接口
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 条件条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @author Mr.M
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

}

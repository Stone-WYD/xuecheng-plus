package com.wyd.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.wyd.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author wyd
 * @since 2024-02-01
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 课程分类树形结构查询
     *
     * @return
     */
    List<CourseCategoryTreeDto> queryTreeNodes(String id);
}

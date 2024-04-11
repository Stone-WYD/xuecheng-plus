package com.wyd.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.xuecheng.content.model.dto.CoursePreviewDto;
import com.wyd.xuecheng.content.model.po.CoursePublish;

/**
 * <p>
 * 课程发布 服务类
 * </p>
 *
 * @author wyd
 * @since 2024-02-01
 */
public interface CoursePublishService extends IService<CoursePublish> {

    /**
     * @description 获取课程预览信息
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @author Mr.M
     * @date 2022/9/16 15:36
     */
    CoursePreviewDto getCoursePreviewInfo(Long courseId);

}

package com.wyd.xuecheng.learning.service;

import com.wyd.xuecheng.base.model.PageResult;
import com.wyd.xuecheng.learning.model.dto.MyCourseTableParams;
import com.wyd.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.wyd.xuecheng.learning.model.dto.XcCourseTablesDto;
import com.wyd.xuecheng.learning.model.po.XcCourseTables;

/**
 * @author xh
 * @date 2024-11-14
 * @Description:
 */
public interface MyCourseTablesService {

    /**
     * @description 添加选课
     * @param userId 用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author Mr.M
     * @date 2022/10/24 17:33
     */
    XcChooseCourseDto addChooseCourse(String userId, Long courseId);


    /**
     * @description 判断学习资格
     * @param userId
     * @param courseId
     * @return XcCourseTablesDto 学习资格状态 [{"code":"702001","desc":"正常学习"},{"code":"702002","desc":"没有选课或选课后没有支付"},{"code":"702003","desc":"已过期需要申请续期或重新支付"}]
     * @author Stoone
     * @date 2022/10/3 7:37
     */
    XcCourseTablesDto getLearningStatus(String userId, Long courseId);

    PageResult<XcCourseTables> mycoursetable(MyCourseTableParams params);
}

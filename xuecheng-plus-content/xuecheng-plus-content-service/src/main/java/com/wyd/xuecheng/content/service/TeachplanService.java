package com.wyd.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.xuecheng.content.model.dto.SaveTeachplanDto;
import com.wyd.xuecheng.content.model.dto.TeachplanDto;
import com.wyd.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author wyd
 * @since 2024-02-01
 */
public interface TeachplanService extends IService<Teachplan> {

    /**
     * @description 查询课程计划树型结构
     * @param courseId  课程id
     * @return List<TeachplanDto>
     * @author Mr.M
     * @date 2022/9/9 11:13
     */
    List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * @description 新增课程计划
     * @param teachplanDto  课程计划信息
     * @return void
     * @author Mr.M
     * @date 2022/9/9 13:39
     */
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * @author: Stone
     * @description: 删除课程计划，删除一级章节需要没有二级章节，删除二级目录需要把资源文件也删除
     * @date: 2024/3/29 17:15
    */
    void deleteTeachplan(Long id);
}

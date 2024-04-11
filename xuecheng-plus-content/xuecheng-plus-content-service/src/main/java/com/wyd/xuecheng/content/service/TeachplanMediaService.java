package com.wyd.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyd.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.wyd.xuecheng.content.model.po.TeachplanMedia;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyd
 * @since 2024-02-01
 */
public interface TeachplanMediaService extends IService<TeachplanMedia> {

    /**
     * @description 教学计划绑定媒资
     * @param bindTeachplanMediaDto
     * @return com.xuecheng.content.model.po.TeachplanMedia
     * @author Mr.M
     * @date 2022/9/14 22:20
     */
    TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);

    void unbindMedia(Long teachPlanId, String mediaId);
}

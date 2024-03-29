package com.wyd.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyd.xuecheng.content.model.po.TeachplanMedia;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyd
 */
public interface TeachplanMediaMapper extends BaseMapper<TeachplanMedia> {

    void deleteByTeachplanId(Long teachplanId);
}

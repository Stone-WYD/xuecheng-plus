package com.wyd.xuecheng.content.model.dto;

import com.wyd.xuecheng.content.model.po.Teachplan;
import com.wyd.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @program: xuecheng-plus-content
 * @description: 课程计划树形结构DTO
 * @author: Stone
 * @create: 2024-03-29 14:42
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TeachplanDto extends Teachplan {

    private TeachplanMedia teachplanMedia;

    private List<TeachplanDto> teachPlanTreeNodes;

}


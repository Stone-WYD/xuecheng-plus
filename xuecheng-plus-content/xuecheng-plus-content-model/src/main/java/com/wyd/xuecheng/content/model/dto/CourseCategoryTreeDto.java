package com.wyd.xuecheng.content.model.dto;

import com.wyd.xuecheng.content.model.po.CourseCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 课程分类树型结点dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    List<CourseCategoryTreeDto> childrenTreeNodes;

}

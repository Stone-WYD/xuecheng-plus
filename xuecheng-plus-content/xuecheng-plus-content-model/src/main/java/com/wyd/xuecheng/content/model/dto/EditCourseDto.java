package com.wyd.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: xuecheng-plus-content
 * @description: 修改课程信息 DTO
 * @author: Stone
 * @create: 2024-03-29 14:12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class EditCourseDto extends AddCourseDto{

    @ApiModelProperty(value = "课程id", required = true)
    private Long id;

}


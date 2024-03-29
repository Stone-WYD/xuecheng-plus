package com.wyd.xuecheng.content.api;

import com.wyd.xuecheng.content.model.dto.SaveTeachplanDto;
import com.wyd.xuecheng.content.model.dto.TeachplanDto;
import com.wyd.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: xuecheng-plus-content
 * @description: 课程计划编辑接口
 * @author: Stone
 * @create: 2024-03-29 14:44
 **/
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程基础Id值",required = true,dataType = "Long",paramType = "path")
    @GetMapping("teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan( @RequestBody SaveTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("删除课程计划")
    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachplan(@PathVariable Long id){
        teachplanService.deleteTeachplan(id);
    }

}


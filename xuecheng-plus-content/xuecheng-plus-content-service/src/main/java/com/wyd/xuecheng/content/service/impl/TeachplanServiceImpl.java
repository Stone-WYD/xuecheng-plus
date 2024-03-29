package com.wyd.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyd.xuecheng.base.exception.XueChengPlusException;
import com.wyd.xuecheng.content.mapper.TeachplanMapper;
import com.wyd.xuecheng.content.mapper.TeachplanMediaMapper;
import com.wyd.xuecheng.content.model.dto.SaveTeachplanDto;
import com.wyd.xuecheng.content.model.dto.TeachplanDto;
import com.wyd.xuecheng.content.model.po.Teachplan;
import com.wyd.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.wyd.xuecheng.base.exception.CommonError.PARAMS_ERROR;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author wyd
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        //课程计划id
        Long id = teachplanDto.getId();
        if(id!=null){
            //修改课程计划
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }else{
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count+1);
            BeanUtils.copyProperties(teachplanDto,teachplanNew);
            teachplanMapper.insert(teachplanNew);
        }
    }

    @Transactional
    @Override
    public void deleteTeachplan(Long id) {
        Teachplan teachplan = getById(id);
        // 校验参数
        if (!Optional.ofNullable(teachplan).isPresent()) {
            XueChengPlusException.cast(PARAMS_ERROR);
        }
        Integer grade = teachplan.getGrade();
        if (grade.equals(1)) {
            // 删除一级章节，需要没有子章节
            int count = getTeachplanCount(teachplan.getCourseId(), id);
            if (count == 0) {
                teachplanMapper.deleteById(id);
            } else XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
        } else if (grade.equals(2)) {
            // 删除二级章节，需要连着资源记录一起删除
            teachplanMapper.deleteById(id);
            teachplanMediaMapper.deleteByTeachplanId(id);
        }
    }


    /**
     * @description 获取最新的排序号
     * @param courseId  课程id
     * @param parentId  父课程计划id
     * @return int 最新排序号
     * @author Mr.M
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }
}

package com.wyd.xuecheng.media.api;

import com.wyd.xuecheng.base.exception.XueChengPlusException;
import com.wyd.xuecheng.base.model.RestResponse;
import com.wyd.xuecheng.media.model.po.MediaFiles;
import com.wyd.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: xuecheng-plus-media
 * @author: Stone
 * @create: 2024-04-11 21:52
 */
@Api(value = "媒资文件管理接口",tags = "媒资文件管理接口")
@RestController
@RequestMapping("/open")
public class MediaOpenController {

    @Autowired
    MediaFileService mediaFileService;

    @ApiOperation("预览文件")
    @GetMapping("/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId(@PathVariable String mediaId){

        MediaFiles mediaFiles = mediaFileService.getFileById(mediaId);
        if(mediaFiles == null || StringUtils.isEmpty(mediaFiles.getUrl())){
            XueChengPlusException.cast("视频还没有转码处理");
        }
        return RestResponse.success(mediaFiles.getUrl());
    }
}
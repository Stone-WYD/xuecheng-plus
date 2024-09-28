package com.wyd.xuecheng.content.feignclient;

import com.wyd.xuecheng.content.config.MultipartSupportConfig;
import com.wyd.xuecheng.content.feignclient.fallback.MediaServiceClientFallbackFactory;
import com.wyd.xuecheng.content.feignclient.fallback.SearchServiceClientFallbackFactory;
import com.wyd.xuecheng.content.model.dto.CourseIndex;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "search", fallbackFactory = SearchServiceClientFallbackFactory.class)
public interface SearchServiceClient {

    @PostMapping(value = "search/index/course", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean add(@RequestBody CourseIndex courseIndex);

}

package com.wyd.xuecheng.ucenter.feignclient;

import com.wyd.xuecheng.ucenter.feignclient.fallback.CheckCodeServiceClientFallbackFactory;
import com.xuecheng.checkcode.model.CheckCodeParamsDto;
import com.xuecheng.checkcode.model.CheckCodeResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "checkcode", fallbackFactory = CheckCodeServiceClientFallbackFactory.class)
public interface CheckCodeServiceClient {

    @PostMapping(value = "checkcode/verify")
    Boolean verify(String key, String code);

}

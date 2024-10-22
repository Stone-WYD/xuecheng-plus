package com.wyd.xuecheng.ucenter.feignclient.fallback;

import com.wyd.xuecheng.ucenter.feignclient.CheckCodeServiceClient;
import com.xuecheng.checkcode.model.CheckCodeParamsDto;
import com.xuecheng.checkcode.model.CheckCodeResultDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: xuecheng-plus-content
 * @author: Stone
 * @create: 2024-04-19 21:53
 */
@Component
@Slf4j
public class CheckCodeServiceClientFallbackFactory implements FallbackFactory<CheckCodeServiceClient> {
    @Override
    public CheckCodeServiceClient create(Throwable throwable) {
        return new CheckCodeServiceClient() {

            @Override
            public Boolean verify(String key, String code) {
                throwable.printStackTrace();
                log.debug("调用验证码校验接口熔断走降级方法,熔断异常:", throwable.getMessage());
                return false;
            }
        };
    }
}
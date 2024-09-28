package com.wyd.xuecheng.content.feignclient.fallback;

import com.wyd.xuecheng.content.feignclient.SearchServiceClient;
import com.wyd.xuecheng.content.model.dto.CourseIndex;
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
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable throwable) {
        return new SearchServiceClient() {
            @Override
            public Boolean add(CourseIndex courseIndex) {
                throwable.printStackTrace();
                log.debug("调用搜索发生熔断走降级方法,熔断异常:", throwable.getMessage());
                return false;
            }
        };
    }
}
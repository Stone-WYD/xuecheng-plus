package com.wyd.xuecheng.content;

import com.wyd.xuecheng.content.config.MultipartSupportConfig;
import com.wyd.xuecheng.content.feignclient.MediaServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @program: xuecheng-plus-content
 * @author: Stone
 * @create: 2024-04-13 20:23
 */
@SpringBootTest
public class FeignUploadTest {

    @Autowired
    MediaServiceClient mediaServiceClient;

    //远程调用，上传文件
    @Test
    public void test() {

        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(new File("D:\\develop\\test.html"));
        mediaServiceClient.uploadFile(multipartFile,"course/test.html");
    }
}
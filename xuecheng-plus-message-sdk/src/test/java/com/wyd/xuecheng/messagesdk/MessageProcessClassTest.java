package com.wyd.xuecheng.messagesdk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @program: xuecheng-plus-message-sdk
 * @author: Stone
 * @create: 2024-04-13 10:30
 */
@SpringBootTest
public class MessageProcessClassTest {

    @Autowired
    MessageProcessClass messageProcessClass;

    @Test
    public void test() throws InterruptedException {

        System.out.println("开始执行-----》" + LocalDateTime.now());
        messageProcessClass.process(0, 1, "test", 5, 30);
        System.out.println("结束执行-----》" + LocalDateTime.now());
        Thread.sleep(9000000);
    }
}
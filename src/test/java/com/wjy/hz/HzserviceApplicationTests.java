package com.wjy.hz;

import com.wjy.hz.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HzserviceApplicationTests {

    @Resource
    ChatService chatService;

    @Test
    void contextLoads() {
    }

    @Test
    void testChat() {
        System.out.println(chatService.sendText(1, "我测试一下聊天接口，如何根据上下文获得数据？"));
    }

}

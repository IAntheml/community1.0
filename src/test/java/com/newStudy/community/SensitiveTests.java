package com.newStudy.community;

import com.newStudy.community.util.MailClient;
import com.newStudy.community.util.SensitiveFilter;
import io.netty.util.NettyRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
/**
 * @author shkstart
 * @create 2020-02-21-14:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void testSensitiveFilter(){
        String text = "这里可以#！赌@博%，这里可以%嫖%娼%，这里可以%开%票@，哈哈哈";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}

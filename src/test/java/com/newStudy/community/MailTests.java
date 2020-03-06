package com.newStudy.community;

import com.newStudy.community.util.MailClient;
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
public class MailTests {
    @Autowired
    private MailClient mailClient;
    //在测试类中需要手动注入Spring整合的Thymleaf模板引擎
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testTextMail(){
        mailClient.sendMail("393089025@qq.com","TEST","Welcome");
    }

    @Test
    public void testHtmlMail(){
        //创建Context对象，为模板引擎的模板demo.html中的username变量注入数据
        Context context = new Context();
        context.setVariable("username","Sunday");
        //注入数据
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        //通过邮件发送
        mailClient.sendMail("393089025@qq.com","html",content);
    }
}

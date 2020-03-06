package com.newStudy.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author shkstart
 * @create 2020-02-21-14:03
 */
@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    //注入Spring的核心组件
    @Autowired
    private JavaMailSender mailSender;
    //直接从配置文件中获取邮件的发送者是谁
    @Value("${spring.mail.username}")
    private String from;

    public  void sendMail(String to,String subject,String content){
        try {
            //MimeMessage：邮件的模板
            MimeMessage message = mailSender.createMimeMessage();
            //通过MimeMessageHelper将详细内容放入createMimeMessage创建的模板对象中。
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //不加第二个参数，默认为文本，加true后可以接受html
            helper.setText(content,true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败"+e.getMessage());
        } finally {
        }
    }
}

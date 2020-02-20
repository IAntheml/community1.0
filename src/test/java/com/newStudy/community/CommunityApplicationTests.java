package com.newStudy.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//使用CommunityApplication配置类以方便获取主包下的bean
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware{
//如果一个类想要获取Spring容器，则实现ApplicationContextAware
	private ApplicationContext applicationContext;             //ApplicationContext实际上就是Spring容器的类型(接口)
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		/*如果一个类实现了ApplicationContextAware接口，Spring容器会检测到，
		然后调用重写的setApplicationContext()方法，并将Spring容器自身传递给形参applicationContext*/
		this.applicationContext = applicationContext;
	}

	@Test
	public void textApplicationContext() {//测试Spring容器
		System.out.println(applicationContext);//打印出ApplicationContext的实现类。
	}
}

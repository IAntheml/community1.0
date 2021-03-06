package com.newStudy.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

//SpringBootApplication标识的类是一个配置文件
@SpringBootApplication
public class CommunityApplication {


	@PostConstruct
	public void  init(){
		//解决ES和Redis的netty启动冲突的问题
		//通过Netty4Utils.setAvailableProcessors()找到解决方法
		System.setProperty("es.set.netty.runtime.available.processors","false");
	}

	public static void main(String[] args) {
		//启动Tomcat并自动创建Spring容器，进而通过Spring容器自动扫描配置类所在包以及子包下的bean装配到容器中。
		SpringApplication.run(CommunityApplication.class, args);
	}
}

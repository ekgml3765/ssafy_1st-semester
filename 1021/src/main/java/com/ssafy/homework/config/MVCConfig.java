package com.ssafy.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//이건 환경설정파일입니다.
@Configuration
@EnableWebMvc // annotation - driven
@ComponentScan("com.ssafy.homework.controller")
public class MVCConfig implements WebMvcConfigurer{

	
	//정적 리소스 위치 등록.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/res/**") //화면에서 요청하는 리소스 위치.
		.addResourceLocations("/resources/") //실제로 그 리소스들이 있는 물리적 경로.
		.setCachePeriod(60*60*24*365); // cache로 남겨둘 기간 설정. 
		//이미지 같은게 자주 바뀌진 않으니까 매번 받아올 필요 없으므로 기존꺼 써라~ = 캐쉬
	}
	
	//라이브러리의 클래스를 빈으로 써야한다. 소스 편집은 불가능하므로 @Component 설정불가
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/" );
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}

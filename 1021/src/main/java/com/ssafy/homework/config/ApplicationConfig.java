package com.ssafy.homework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //이건 환경설정 담당이에요.
@ComponentScan("com.ssafy.homework.model") //여기가서 빈좀 찾아주세요.
public class ApplicationConfig {

	
}

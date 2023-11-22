package com.gdsc.waffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication으로 인해 스프링부트의 자동설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정된다.
public class Application {
    public static void main(String[]args) {SpringApplication.run(Application.class,args);}
}

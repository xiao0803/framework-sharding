package com.xlj.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * 程序入口
 *
 * @author xlj
 * @since 0.0.1
 */
@SpringBootApplication
/**
 * 使用注解@ServletComponentScan
 * 如果不是使用配置的方式，注解@WebServlet/@WebFilter添加Servlet或Filter，需要添加这个注解启用Servlet扫描
 * @see com.xlj.shardingjdbc.druid.DruidStatsConfig
 * @see com.xlj.shardingjdbc.druid.DruidStatsServlet
 * @see com.xlj.shardingjdbc.druid.DruidStatsFilter
 */
public class Application {

	/*public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}*/
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}

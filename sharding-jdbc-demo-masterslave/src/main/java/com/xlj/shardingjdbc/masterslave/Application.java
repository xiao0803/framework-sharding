package com.xlj.shardingjdbc.masterslave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 *
 * @author xlj
 * @since 0.0.1
 */
@SpringBootApplication
//@MapperScan("com.xlj.shardingjdbc.masterslave.mapper")
public class Application {

	/*public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}*/
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
	
}

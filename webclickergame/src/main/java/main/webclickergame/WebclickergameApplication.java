package main.webclickergame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller","repository","service"})
public class WebclickergameApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclickergameApplication.class, args);
	}

}

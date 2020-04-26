package api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"api"})
@EnableJpaRepositories(basePackages  = "api.dao")
@EntityScan( basePackages = {"entities"} )
@ComponentScan("api")
public class MainController {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);

    }

}
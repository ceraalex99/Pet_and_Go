package api.controller;

import helpers.ActualizadorPuntos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages= {"api"})
@EnableJpaRepositories(basePackages  = "api.dao")
@EntityScan( basePackages = {"entities"} )
@ComponentScan(basePackages = {"api","helpers"} )
@EnableScheduling
public class MainController {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainController.class, args);
        context.getBean(ActualizadorPuntos.class).init();
    }

}

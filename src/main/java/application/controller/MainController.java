package application.controller;

import domain.services.ActualizadorPuntos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages= {"application"})
@EnableScheduling
public class MainController {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainController.class, args);
        context.getBean(ActualizadorPuntos.class).init();
    }

}
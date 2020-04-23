package api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages= {"api"})

public class MainController {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }

}
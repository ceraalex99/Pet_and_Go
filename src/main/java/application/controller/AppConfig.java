package application.controller;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages  = "infrastructure.dao")
@EntityScan( basePackages = {"domain"} )
@ComponentScan(basePackages = {"application","helpers"} )
public class AppConfig {
}

package com.si3ri.ForoHub.infra.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FlywayConfig {

    @Autowired
    private Flyway flyway;

    @Bean
    public void repairFlyway() {
        flyway.repair();
    }
}

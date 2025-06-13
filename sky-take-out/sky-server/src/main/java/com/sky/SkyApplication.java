package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement // Enable Transactional Management
@Slf4j
@EnableCaching
@EnableScheduling
public class SkyApplication {

    @Autowired
    private Environment env;


    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);

        log.info("server started");
    }

    @PostConstruct
    public void logActiveProfile() {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            log.warn("No active Spring profile set!");
        } else {
            for (String profile : profiles) {
                log.info("Active Spring Profile: {}", profile);
            }
        }
    }


}

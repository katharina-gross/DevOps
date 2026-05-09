package com.jkaninda.simpleapi.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StartupValidator implements ApplicationRunner {

    private final SecurityProperties securityProperties;

    public StartupValidator(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void run(ApplicationArguments args) {
        securityProperties.validate();
    }
}
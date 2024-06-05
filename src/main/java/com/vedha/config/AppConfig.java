package com.vedha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync // Enable async support for the application context (Spring will create a proxy to handle the method calls asynchronously)
public class AppConfig {
}

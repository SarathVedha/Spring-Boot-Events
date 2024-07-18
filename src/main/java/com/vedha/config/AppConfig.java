package com.vedha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
// Enable async support for the application context (Spring will create a proxy to handle the method calls asynchronously)
public class AppConfig implements AsyncConfigurer {

    // Executor bean for async tasks
    // Executor is an interface that represents an object capable of executing submitted Runnable tasks
    // ThreadPoolTaskExecutor is an implementation of Executor by spring that sets up a thread pool
    // Executors.newFixedThreadPool() is an implementation of Executor by java that sets up a thread pool
    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("async-thread-");
        executor.initialize();

//        return Executors.newFixedThreadPool(5);
        return executor;
    }
}

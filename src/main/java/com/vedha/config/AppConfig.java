package com.vedha.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@EnableAsync
// Enable async support for the application context (Spring will create a proxy to handle the method calls asynchronously)
public class AppConfig implements AsyncConfigurer {

    @Bean
    ExecutorService initExecutorService() {

        int cores = Runtime.getRuntime().availableProcessors();
        log.warn("Available cores: {}", cores);
        log.warn("Fixed Threads: {}", cores * 2);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        return Executors.newFixedThreadPool(cores * 2, r -> {

            Thread thread = new Thread(r, "multi-thread-" + atomicInteger.incrementAndGet());
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        });
    }

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

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return (ex, method, params) -> {

            log.error("Exception in {} message {} ", method.getName(), ex.getMessage());
            for (Object param : params) {
                log.error("Parameter value {} ", param);
            }
        };
    }
}

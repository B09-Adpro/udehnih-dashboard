package id.ac.ui.cs.advprog.udehnihdashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ThreadPoolConfig {

    private final int CORE_POOL_SIZE = 32;
    private final String THREAD_NAME_PREFIX = "thread-";

    @Bean(name = "threadPool", destroyMethod = "shutdown")
    public ExecutorService authInfoThreadPool() {
         return Executors.newFixedThreadPool(CORE_POOL_SIZE, new ThreadFactory() {
             private final AtomicInteger threadNumber = new AtomicInteger(1);
             @Override
             public Thread newThread(Runnable r) {
                 return new Thread(r, THREAD_NAME_PREFIX + threadNumber.getAndIncrement());
             }
         });
    }
}
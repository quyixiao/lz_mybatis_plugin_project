package com.admin.crawler.executor;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class MyThreadPoolTaskExecutor_back extends ThreadPoolTaskExecutor {

    @Override
    protected ExecutorService initializeExecutor(
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        ExecutorService executorService = super.initializeExecutor(threadFactory, rejectedExecutionHandler);
        return TtlExecutors.getTtlExecutorService(executorService);
    }

    @Override
    public void execute(Runnable task) {
        Executor executor = getThreadPoolExecutor();
        try {
            executor.execute(TtlRunnable.get(MyRunnable.get(task)));
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }




    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = getThreadPoolExecutor();
        try {
            return executor.submit(TtlCallable.get(task));
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

}

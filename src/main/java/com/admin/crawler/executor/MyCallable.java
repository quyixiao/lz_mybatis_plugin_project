package com.admin.crawler.executor;

import com.admin.crawler.aspect.LogAspect;

import java.util.concurrent.Callable;

public final class MyCallable<V> implements Callable {


    private final Callable<V> callable;

    public MyCallable(Callable<V> callable) {
        this.callable = callable;
    }

    public static <T> MyCallable<T> get(Callable callable) {
        return new MyCallable(callable);
    }

    @Override
    public V call() throws Exception {
        try {
            return callable.call();
        } finally {
            LogAspect.myThreadLocalNo.remove();
        }
    }

}

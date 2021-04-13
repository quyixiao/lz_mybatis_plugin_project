package com.admin.crawler.executor;

import com.admin.crawler.aspect.LogAspect;

public final class MyRunnable implements Runnable {


    private final Runnable runnable;

    public MyRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public static MyRunnable get(Runnable runnable) {
        return new MyRunnable(runnable);
    }

    @Override
    public void run() {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LogAspect.myThreadLocalNo.remove();
        }

    }
}

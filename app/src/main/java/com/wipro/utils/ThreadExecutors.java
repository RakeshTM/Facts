package com.wipro.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class ThreadExecutors {

    private final Executor mDiskIO_;

    private final Executor mNetworkIO_;

    private final Executor mUiThread_;

    private ThreadExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.mDiskIO_ = diskIO;
        this.mNetworkIO_ = networkIO;
        this.mUiThread_ = mainThread;
    }

    @Inject
    public ThreadExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor(),
                new MainThreadExecutor());
    }

    public Executor diskIO() {
        return mDiskIO_;
    }

    public Executor networkIO() {
        return mNetworkIO_;
    }

    public Executor mainThread() {
        return mUiThread_;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}

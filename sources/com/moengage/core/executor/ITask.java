package com.moengage.core.executor;

import android.support.annotation.WorkerThread;

public interface ITask {
    @WorkerThread
    TaskResult execute();

    String getTaskTag();

    boolean isSynchronous();

    void onPostExecute(TaskResult taskResult);
}

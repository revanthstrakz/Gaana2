package com.moengage.core.executor;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class TaskProcessor {
    private static TaskProcessor _INSTANCE;
    private final Object lock = new Object();
    private ITask mActive;
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private ArrayList<WeakReference<OnTaskCompleteListener>> mTaskCompleteListeners = new ArrayList();
    private BlockingDeque<ITask> mTaskQueue = new LinkedBlockingDeque();

    private TaskProcessor() {
    }

    public static TaskProcessor getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new TaskProcessor();
        }
        return _INSTANCE;
    }

    public void addTask(ITask iTask) {
        if (iTask != null) {
            this.mTaskQueue.add(iTask);
            startExecution();
        }
    }

    public void addTaskToFront(ITask iTask) {
        if (iTask != null) {
            this.mTaskQueue.addFirst(iTask);
            startExecution();
        }
    }

    public void startExecution() {
        if (this.mActive == null) {
            scheduleNext();
        }
    }

    private void scheduleNext() {
        ITask iTask = (ITask) this.mTaskQueue.poll();
        this.mActive = iTask;
        if (iTask != null) {
            this.mExecutorService.submit(new Runnable() {
                public void run() {
                    TaskProcessor.this.executeTask(TaskProcessor.this.mActive);
                    TaskProcessor.this.scheduleNext();
                }
            });
        }
    }

    private void executeTask(ITask iTask) {
        TaskResult execute = iTask.execute();
        String taskTag = iTask.getTaskTag();
        if (!TextUtils.isEmpty(taskTag)) {
            notifyListener(taskTag, execute);
        }
        iTask.onPostExecute(execute);
    }

    public void removeOnTaskCompleteListener(@NonNull OnTaskCompleteListener onTaskCompleteListener) {
        if (this.mTaskCompleteListeners != null && onTaskCompleteListener != null) {
            int indexOf = this.mTaskCompleteListeners.indexOf(onTaskCompleteListener);
            if (indexOf != -1) {
                this.mTaskCompleteListeners.remove(indexOf);
            }
        }
    }

    public void setOnTaskCompleteListener(OnTaskCompleteListener onTaskCompleteListener) {
        this.mTaskCompleteListeners.add(new WeakReference(onTaskCompleteListener));
    }

    public void notifyListener(String str, TaskResult taskResult) {
        synchronized (this.lock) {
            if (this.mTaskCompleteListeners != null) {
                Iterator it = this.mTaskCompleteListeners.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (weakReference.get() != null) {
                        ((OnTaskCompleteListener) weakReference.get()).onTaskComplete(str, taskResult);
                    }
                }
            }
        }
    }
}

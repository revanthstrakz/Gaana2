package com.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.library.helpers.TaskActivityMap;
import com.library.managers.TaskManager.TaskListner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class h {
    private static ArrayList<TaskActivityMap> a = new ArrayList();
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GaanaTaskManager #");
            stringBuilder.append(this.a.getAndIncrement());
            Thread thread = new Thread(runnable, stringBuilder.toString());
            thread.setPriority(10);
            return thread;
        }
    };
    private final String b;
    private final int c;
    private final ExecutorService d;

    private static class a {
        private static final h a = new h();
    }

    static class b extends Handler {
        private final TaskListner a;
        private int b = -1;

        b(TaskListner taskListner, int i) {
            super(Looper.getMainLooper());
            this.a = taskListner;
            this.b = i;
        }

        public void handleMessage(Message message) {
            if (this.a != null && !h.c(this.b).booleanValue()) {
                this.a.onBackGroundTaskCompleted();
            }
        }
    }

    /* synthetic */ h(AnonymousClass1 anonymousClass1) {
        this();
    }

    private h() {
        this.b = "Task_Manager";
        this.c = 3;
        this.d = Executors.newFixedThreadPool(3, e);
    }

    public static h a() {
        return a.a;
    }

    private static Boolean c(int i) {
        if (i == -1) {
            return Boolean.valueOf(false);
        }
        if (i != -1) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                if (((TaskActivityMap) it.next()).getTaskId() == i) {
                    return Boolean.valueOf(false);
                }
            }
        }
        return Boolean.valueOf(true);
    }

    public void a(TaskListner taskListner, int i) {
        a(taskListner, i, false);
    }

    public void a(final TaskListner taskListner, int i, final boolean z) {
        final b bVar = new b(taskListner, i);
        a(i, taskListner);
        this.d.submit(new Runnable() {
            public void run() {
                taskListner.doBackGroundTask();
                Message obtain = Message.obtain();
                obtain.obj = "Task Performed";
                bVar.sendMessage(obtain);
            }

            public boolean equals(Object obj) {
                if (z) {
                    return false;
                }
                return super.equals(obj);
            }
        });
    }

    public void a(int i) {
        Iterator it = a.iterator();
        int i2 = -1;
        while (it.hasNext()) {
            TaskActivityMap taskActivityMap = (TaskActivityMap) it.next();
            if (taskActivityMap.getTaskId() == i) {
                i2 = a.indexOf(taskActivityMap);
            }
        }
        if (i2 != -1) {
            a.remove(i2);
        }
    }

    private void a(int i, TaskListner taskListner) {
        if (i != -1) {
            Boolean valueOf = Boolean.valueOf(false);
            Iterator it = a.iterator();
            while (it.hasNext()) {
                TaskActivityMap taskActivityMap = (TaskActivityMap) it.next();
                if (taskActivityMap.getTaskId() == i) {
                    taskActivityMap.getArrLstTaskListner().add(taskListner);
                    valueOf = Boolean.valueOf(true);
                    break;
                }
            }
            if (!valueOf.booleanValue()) {
                TaskActivityMap taskActivityMap2 = new TaskActivityMap();
                taskActivityMap2.setTaskId(i);
                taskActivityMap2.getArrLstTaskListner().add(taskListner);
                a.add(taskActivityMap2);
            }
        }
    }
}

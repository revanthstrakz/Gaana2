package com.library.managers;

import android.os.Handler;
import android.os.Message;

public class TaskManager {
    private static TaskManager mInstanse;
    private final String TAG = "Task_Manager";

    public interface TaskListner {
        void doBackGroundTask();

        void onBackGroundTaskCompleted();
    }

    static class TaskHandler extends Handler {
        private final TaskListner taskListner;

        TaskHandler(TaskListner taskListner) {
            this.taskListner = taskListner;
        }

        public void handleMessage(Message message) {
            if (this.taskListner != null) {
                this.taskListner.onBackGroundTaskCompleted();
            }
        }
    }

    public static TaskManager getInstanse() {
        if (mInstanse == null) {
            mInstanse = new TaskManager();
        }
        return mInstanse;
    }
}

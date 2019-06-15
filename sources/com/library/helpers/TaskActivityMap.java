package com.library.helpers;

import com.library.managers.TaskManager.TaskListner;
import java.util.ArrayList;

public class TaskActivityMap {
    private ArrayList<TaskListner> arrLstTaskListner;
    private int taskId = -1;

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int i) {
        this.taskId = i;
    }

    public ArrayList<TaskListner> getArrLstTaskListner() {
        if (this.arrLstTaskListner == null) {
            this.arrLstTaskListner = new ArrayList();
        }
        return this.arrLstTaskListner;
    }

    public void setArrLstUrl(ArrayList<TaskListner> arrayList) {
        this.arrLstTaskListner = arrayList;
    }
}

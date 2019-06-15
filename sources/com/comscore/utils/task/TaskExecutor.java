package com.comscore.utils.task;

import com.comscore.analytics.Core;
import com.comscore.utils.CSLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskExecutor {
    private static final long b = 50000;
    Core a;
    private TaskThread c = new TaskThread(this);
    private BlockingQueue<a> d = new LinkedBlockingQueue();

    public TaskExecutor(Core core) {
        this.a = core;
        this.c.start();
    }

    /* Access modifiers changed, original: 0000 */
    public long a() {
        long j = b;
        for (a a : this.d) {
            j = Math.min(j, a.a());
        }
        return j;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(a aVar) {
        this.d.remove(aVar);
    }

    /* Access modifiers changed, original: 0000 */
    public a b() {
        for (a aVar : this.d) {
            if (aVar.f() <= System.currentTimeMillis()) {
                return aVar;
            }
        }
        return null;
    }

    public boolean containsTask(Runnable runnable) {
        for (Runnable runnable2 : this.d) {
            if (runnable2.i() == runnable || ((runnable instanceof a) && runnable2 == runnable)) {
                return true;
            }
        }
        return false;
    }

    public boolean execute(Runnable runnable, long j) {
        return execute(runnable, j, false, 0);
    }

    public boolean execute(Runnable runnable, long j, boolean z, long j2) {
        for (a aVar : this.d) {
            if (aVar == null) {
                Runnable runnable2 = runnable;
            } else if (aVar.i() == runnable) {
                return false;
            }
        }
        this.d.add(new a(runnable, this.a, j, z, j2));
        this.c.c();
        return true;
    }

    public boolean execute(Runnable runnable, boolean z) {
        if (!this.a.isEnabled()) {
            return false;
        }
        if (z) {
            execute(runnable, 0);
            return true;
        }
        try {
            runnable.run();
            return true;
        } catch (Exception e) {
            CSLog.e(getClass(), "Unexpected error: ");
            CSLog.printStackTrace(e);
            return true;
        }
    }

    public void removeAllEnqueuedTasks() {
        this.d.clear();
    }

    public void removeEnqueuedTask(Runnable runnable) {
        if (runnable != null) {
            for (a aVar : this.d) {
                if (aVar.i() == runnable) {
                    this.d.remove(aVar);
                    break;
                }
            }
        }
    }

    public int size() {
        return this.d.size();
    }

    public void waitForLastNonDelayedTaskToFinish() {
        Runnable runnable;
        a[] aVarArr = new a[this.d.size()];
        this.d.toArray(aVarArr);
        int length = aVarArr.length - 1;
        while (length >= 0) {
            if (aVarArr[length] != null && !aVarArr[length].d()) {
                runnable = aVarArr[length];
                break;
            }
            length--;
        }
        runnable = null;
        waitForTaskToFinish(runnable, 0);
    }

    public void waitForTaskToFinish(Runnable runnable, long j) {
        Object obj;
        if (runnable instanceof a) {
            obj = (a) runnable;
        } else {
            obj = null;
            for (a aVar : this.d) {
                if (aVar.i() == runnable) {
                    obj = aVar;
                }
            }
        }
        if (obj != null) {
            long currentTimeMillis = System.currentTimeMillis();
            while (this.d.contains(obj)) {
                if (j <= 0 || System.currentTimeMillis() < currentTimeMillis + j) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException unused) {
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void waitForTasks() {
        while (this.d.size() != 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException unused) {
            }
        }
    }
}

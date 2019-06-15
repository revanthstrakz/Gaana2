package com.google.android.exoplayer2.offline;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadAction.Deserializer;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public final class DownloadManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_MAX_SIMULTANEOUS_DOWNLOADS = 1;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    private static final String TAG = "DownloadManager";
    private final ActionFile actionFile;
    private final ArrayList<Task> activeDownloadTasks;
    private final Deserializer[] deserializers;
    private final DownloaderConstructorHelper downloaderConstructorHelper;
    private boolean downloadsStopped;
    private final Handler fileIOHandler;
    private final HandlerThread fileIOThread;
    private final Handler handler;
    private boolean initialized;
    private final CopyOnWriteArraySet<Listener> listeners;
    private final int maxActiveDownloadTasks;
    private final int minRetryCount;
    private int nextTaskId;
    private boolean released;
    private final ArrayList<Task> tasks;

    public interface Listener {
        void onIdle(DownloadManager downloadManager);

        void onInitialized(DownloadManager downloadManager);

        void onTaskStateChanged(DownloadManager downloadManager, TaskState taskState);
    }

    private static final class Task implements Runnable {
        public static final int STATE_QUEUED_CANCELING = 5;
        public static final int STATE_STARTED_CANCELING = 6;
        public static final int STATE_STARTED_STOPPING = 7;
        private final DownloadAction action;
        private volatile int currentState;
        private final DownloadManager downloadManager;
        private volatile Downloader downloader;
        private Throwable error;
        private final int id;
        private final int minRetryCount;
        private Thread thread;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface InternalState {
        }

        private Task(int i, DownloadManager downloadManager, DownloadAction downloadAction, int i2) {
            this.id = i;
            this.downloadManager = downloadManager;
            this.action = downloadAction;
            this.currentState = 0;
            this.minRetryCount = i2;
        }

        public TaskState getDownloadState() {
            return new TaskState(this.id, this.action, getExternalState(), getDownloadPercentage(), getDownloadedBytes(), this.error);
        }

        public boolean isFinished() {
            return this.currentState == 4 || this.currentState == 2 || this.currentState == 3;
        }

        public boolean isActive() {
            return this.currentState == 5 || this.currentState == 1 || this.currentState == 7 || this.currentState == 6;
        }

        public float getDownloadPercentage() {
            return this.downloader != null ? this.downloader.getDownloadPercentage() : -1.0f;
        }

        public long getDownloadedBytes() {
            return this.downloader != null ? this.downloader.getDownloadedBytes() : 0;
        }

        public String toString() {
            return super.toString();
        }

        private static String toString(byte[] bArr) {
            if (bArr.length > 100) {
                return "<data is too long>";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('\'');
            stringBuilder.append(Util.fromUtf8Bytes(bArr));
            stringBuilder.append('\'');
            return stringBuilder.toString();
        }

        private String getStateString() {
            switch (this.currentState) {
                case 5:
                case 6:
                    return "CANCELING";
                case 7:
                    return "STOPPING";
                default:
                    return TaskState.getStateString(this.currentState);
            }
        }

        private int getExternalState() {
            switch (this.currentState) {
                case 5:
                    return 0;
                case 6:
                case 7:
                    return 1;
                default:
                    return this.currentState;
            }
        }

        private void start() {
            if (changeStateAndNotify(0, 1)) {
                this.thread = new Thread(this);
                this.thread.start();
            }
        }

        private boolean canStart() {
            return this.currentState == 0;
        }

        private void cancel() {
            if (changeStateAndNotify(0, 5)) {
                this.downloadManager.handler.post(new DownloadManager$Task$$Lambda$0(this));
            } else if (changeStateAndNotify(1, 6)) {
                cancelDownload();
            }
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$cancel$0$DownloadManager$Task() {
            changeStateAndNotify(5, 3);
        }

        private void stop() {
            if (changeStateAndNotify(1, 7)) {
                DownloadManager.logd("Stopping", this);
                cancelDownload();
            }
        }

        private boolean changeStateAndNotify(int i, int i2) {
            return changeStateAndNotify(i, i2, null);
        }

        private boolean changeStateAndNotify(int i, int i2, Throwable th) {
            boolean z = false;
            if (this.currentState != i) {
                return false;
            }
            this.currentState = i2;
            this.error = th;
            if (this.currentState != getExternalState()) {
                z = true;
            }
            if (!z) {
                this.downloadManager.onTaskStateChange(this);
            }
            return true;
        }

        private void cancelDownload() {
            if (this.downloader != null) {
                this.downloader.cancel();
            }
            this.thread.interrupt();
        }

        public void run() {
            long j;
            int i;
            Throwable th;
            DownloadManager.logd("Task is started", this);
            try {
                this.downloader = this.action.createDownloader(this.downloadManager.downloaderConstructorHelper);
                if (this.action.isRemoveAction) {
                    this.downloader.remove();
                } else {
                    j = -1;
                    i = 0;
                    while (!Thread.interrupted()) {
                        this.downloader.download();
                    }
                }
                th = null;
            } catch (IOException e) {
                long downloadedBytes = this.downloader.getDownloadedBytes();
                if (downloadedBytes != j) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Reset error count. downloadedBytes = ");
                    stringBuilder.append(downloadedBytes);
                    DownloadManager.logd(stringBuilder.toString(), this);
                    i = 0;
                    j = downloadedBytes;
                }
                if (this.currentState == 1) {
                    i++;
                    if (i <= this.minRetryCount) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Download error. Retry ");
                        stringBuilder2.append(i);
                        DownloadManager.logd(stringBuilder2.toString(), this);
                        Thread.sleep((long) getRetryDelayMillis(i));
                    }
                }
                throw e;
            } catch (Throwable th2) {
                th = th2;
            }
            this.downloadManager.handler.post(new DownloadManager$Task$$Lambda$1(this, th));
        }

        /* Access modifiers changed, original: final|synthetic */
        public final /* synthetic */ void lambda$run$1$DownloadManager$Task(Throwable th) {
            if (!changeStateAndNotify(1, th != null ? 4 : 2, th) && !changeStateAndNotify(6, 3) && !changeStateAndNotify(7, 0)) {
                throw new IllegalStateException();
            }
        }

        private int getRetryDelayMillis(int i) {
            return Math.min((i - 1) * 1000, 5000);
        }
    }

    public static final class TaskState {
        public static final int STATE_CANCELED = 3;
        public static final int STATE_COMPLETED = 2;
        public static final int STATE_FAILED = 4;
        public static final int STATE_QUEUED = 0;
        public static final int STATE_STARTED = 1;
        public final DownloadAction action;
        public final float downloadPercentage;
        public final long downloadedBytes;
        public final Throwable error;
        public final int state;
        public final int taskId;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface State {
        }

        public static String getStateString(int i) {
            switch (i) {
                case 0:
                    return "QUEUED";
                case 1:
                    return "STARTED";
                case 2:
                    return "COMPLETED";
                case 3:
                    return "CANCELED";
                case 4:
                    return "FAILED";
                default:
                    throw new IllegalStateException();
            }
        }

        private TaskState(int i, DownloadAction downloadAction, int i2, float f, long j, Throwable th) {
            this.taskId = i;
            this.action = downloadAction;
            this.state = i2;
            this.downloadPercentage = f;
            this.downloadedBytes = j;
            this.error = th;
        }
    }

    private static void logd(String str) {
    }

    public DownloadManager(Cache cache, Factory factory, File file, Deserializer... deserializerArr) {
        this(new DownloaderConstructorHelper(cache, factory), file, deserializerArr);
    }

    public DownloadManager(DownloaderConstructorHelper downloaderConstructorHelper, File file, Deserializer... deserializerArr) {
        this(downloaderConstructorHelper, 1, 5, file, deserializerArr);
    }

    public DownloadManager(DownloaderConstructorHelper downloaderConstructorHelper, int i, int i2, File file, Deserializer... deserializerArr) {
        this.downloaderConstructorHelper = downloaderConstructorHelper;
        this.maxActiveDownloadTasks = i;
        this.minRetryCount = i2;
        this.actionFile = new ActionFile(file);
        if (deserializerArr.length <= 0) {
            deserializerArr = DownloadAction.getDefaultDeserializers();
        }
        this.deserializers = deserializerArr;
        this.downloadsStopped = true;
        this.tasks = new ArrayList();
        this.activeDownloadTasks = new ArrayList();
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        this.handler = new Handler(myLooper);
        this.fileIOThread = new HandlerThread("DownloadManager file i/o");
        this.fileIOThread.start();
        this.fileIOHandler = new Handler(this.fileIOThread.getLooper());
        this.listeners = new CopyOnWriteArraySet();
        loadActions();
        logd("Created");
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void startDownloads() {
        Assertions.checkState(this.released ^ 1);
        if (this.downloadsStopped) {
            this.downloadsStopped = false;
            maybeStartTasks();
            logd("Downloads are started");
        }
    }

    public void stopDownloads() {
        Assertions.checkState(this.released ^ 1);
        if (!this.downloadsStopped) {
            this.downloadsStopped = true;
            for (int i = 0; i < this.activeDownloadTasks.size(); i++) {
                ((Task) this.activeDownloadTasks.get(i)).stop();
            }
            logd("Downloads are stopping");
        }
    }

    public int handleAction(byte[] bArr) throws IOException {
        Assertions.checkState(this.released ^ 1);
        return handleAction(DownloadAction.deserializeFromStream(this.deserializers, new ByteArrayInputStream(bArr)));
    }

    public int handleAction(DownloadAction downloadAction) {
        Assertions.checkState(this.released ^ 1);
        Task addTaskForAction = addTaskForAction(downloadAction);
        if (this.initialized) {
            saveActions();
            maybeStartTasks();
            if (addTaskForAction.currentState == 0) {
                notifyListenersTaskStateChange(addTaskForAction);
            }
        }
        return addTaskForAction.id;
    }

    public int getTaskCount() {
        Assertions.checkState(this.released ^ 1);
        return this.tasks.size();
    }

    public int getDownloadCount() {
        int i = 0;
        int i2 = 0;
        while (i < this.tasks.size()) {
            if (!((Task) this.tasks.get(i)).action.isRemoveAction) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    @Nullable
    public TaskState getTaskState(int i) {
        Assertions.checkState(this.released ^ 1);
        for (int i2 = 0; i2 < this.tasks.size(); i2++) {
            Task task = (Task) this.tasks.get(i2);
            if (task.id == i) {
                return task.getDownloadState();
            }
        }
        return null;
    }

    public TaskState[] getAllTaskStates() {
        Assertions.checkState(this.released ^ 1);
        TaskState[] taskStateArr = new TaskState[this.tasks.size()];
        for (int i = 0; i < taskStateArr.length; i++) {
            taskStateArr[i] = ((Task) this.tasks.get(i)).getDownloadState();
        }
        return taskStateArr;
    }

    public boolean isInitialized() {
        Assertions.checkState(this.released ^ 1);
        return this.initialized;
    }

    public boolean isIdle() {
        Assertions.checkState(this.released ^ 1);
        if (!this.initialized) {
            return false;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            if (((Task) this.tasks.get(i)).isActive()) {
                return false;
            }
        }
        return true;
    }

    public void release() {
        if (!this.released) {
            this.released = true;
            for (int i = 0; i < this.tasks.size(); i++) {
                ((Task) this.tasks.get(i)).stop();
            }
            ConditionVariable conditionVariable = new ConditionVariable();
            Handler handler = this.fileIOHandler;
            conditionVariable.getClass();
            handler.post(DownloadManager$$Lambda$0.get$Lambda(conditionVariable));
            conditionVariable.block();
            this.fileIOThread.quit();
            logd("Released");
        }
    }

    private Task addTaskForAction(DownloadAction downloadAction) {
        int i = this.nextTaskId;
        this.nextTaskId = i + 1;
        Task task = new Task(i, this, downloadAction, this.minRetryCount);
        this.tasks.add(task);
        logd("Task is added", task);
        return task;
    }

    private void maybeStartTasks() {
        if (this.initialized && !this.released) {
            Object obj = (this.downloadsStopped || this.activeDownloadTasks.size() == this.maxActiveDownloadTasks) ? 1 : null;
            Object obj2 = obj;
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = (Task) this.tasks.get(i);
                if (task.canStart()) {
                    DownloadAction access$300 = task.action;
                    boolean z = access$300.isRemoveAction;
                    if (z || obj2 == null) {
                        Object obj3 = 1;
                        for (int i2 = 0; i2 < i; i2++) {
                            Task task2 = (Task) this.tasks.get(i2);
                            if (task2.action.isSameMedia(access$300)) {
                                if (z) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(task);
                                    stringBuilder.append(" clashes with ");
                                    stringBuilder.append(task2);
                                    logd(stringBuilder.toString());
                                    task2.cancel();
                                    obj3 = null;
                                } else if (task2.action.isRemoveAction) {
                                    obj2 = 1;
                                    obj3 = null;
                                    break;
                                }
                            }
                        }
                        if (obj3 != null) {
                            task.start();
                            if (!z) {
                                this.activeDownloadTasks.add(task);
                                obj2 = this.activeDownloadTasks.size() == this.maxActiveDownloadTasks ? 1 : null;
                            }
                        }
                    }
                }
            }
        }
    }

    private void maybeNotifyListenersIdle() {
        if (isIdle()) {
            logd("Notify idle state");
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onIdle(this);
            }
        }
    }

    private void onTaskStateChange(Task task) {
        if (!this.released) {
            int isActive = task.isActive() ^ 1;
            if (isActive != 0) {
                this.activeDownloadTasks.remove(task);
            }
            notifyListenersTaskStateChange(task);
            if (task.isFinished()) {
                this.tasks.remove(task);
                saveActions();
            }
            if (isActive != 0) {
                maybeStartTasks();
                maybeNotifyListenersIdle();
            }
        }
    }

    private void notifyListenersTaskStateChange(Task task) {
        logd("Task state is changed", task);
        TaskState downloadState = task.getDownloadState();
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).onTaskStateChanged(this, downloadState);
        }
    }

    private void loadActions() {
        this.fileIOHandler.post(new DownloadManager$$Lambda$1(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$loadActions$1$DownloadManager() {
        DownloadAction[] load;
        try {
            load = this.actionFile.load(this.deserializers);
            logd("Action file is loaded.");
        } catch (Throwable th) {
            Log.e(TAG, "Action file loading failed.", th);
            load = new DownloadAction[0];
        }
        this.handler.post(new DownloadManager$$Lambda$3(this, load));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$null$0$DownloadManager(DownloadAction[] downloadActionArr) {
        if (!this.released) {
            ArrayList arrayList = new ArrayList(this.tasks);
            this.tasks.clear();
            int i = 0;
            for (DownloadAction addTaskForAction : downloadActionArr) {
                addTaskForAction(addTaskForAction);
            }
            logd("Tasks are created.");
            this.initialized = true;
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onInitialized(this);
            }
            if (!arrayList.isEmpty()) {
                this.tasks.addAll(arrayList);
                saveActions();
            }
            maybeStartTasks();
            while (i < this.tasks.size()) {
                Task task = (Task) this.tasks.get(i);
                if (task.currentState == 0) {
                    notifyListenersTaskStateChange(task);
                }
                i++;
            }
        }
    }

    private void saveActions() {
        if (!this.released) {
            DownloadAction[] downloadActionArr = new DownloadAction[this.tasks.size()];
            for (int i = 0; i < this.tasks.size(); i++) {
                downloadActionArr[i] = ((Task) this.tasks.get(i)).action;
            }
            this.fileIOHandler.post(new DownloadManager$$Lambda$2(this, downloadActionArr));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$saveActions$2$DownloadManager(DownloadAction[] downloadActionArr) {
        try {
            this.actionFile.store(downloadActionArr);
            logd("Actions persisted.");
        } catch (IOException e) {
            Log.e(TAG, "Persisting actions failed.", e);
        }
    }

    private static void logd(String str, Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(task);
        logd(stringBuilder.toString());
    }
}

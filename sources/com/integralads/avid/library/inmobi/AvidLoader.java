package com.integralads.avid.library.inmobi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.integralads.avid.library.inmobi.DownloadAvidTask.DownloadAvidTaskListener;
import com.integralads.avid.library.inmobi.utils.NetworkUtils;

public class AvidLoader implements DownloadAvidTaskListener {
    private static final String AVID_URL = "https://mobile-static.adsafeprotected.com/avid-v2.js";
    private static final int DOWNLOAD_ATTEMPT_PERIOD = 2000;
    private static AvidLoader avidLoader = new AvidLoader();
    private DownloadAvidTask activeTask;
    private final Runnable avidDownloadRunnable = new Runnable() {
        public void run() {
            if (AvidLoader.this.context == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.context)) {
                AvidLoader.this.repeatLoading();
            } else {
                AvidLoader.this.loadAvidJSFromUrl();
            }
        }
    };
    private Context context;
    private AvidLoaderListener listener;
    private TaskExecutor taskExecutor = new TaskExecutor();
    private TaskRepeater taskRepeater;

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public class TaskExecutor {
        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (VERSION.SDK_INT >= 11) {
                AvidLoader.this.activeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{AvidLoader.AVID_URL});
                return;
            }
            AvidLoader.this.activeTask.execute(new String[]{AvidLoader.AVID_URL});
        }
    }

    public class TaskRepeater {
        private Handler handler = new Handler();

        public void repeatLoading() {
            this.handler.postDelayed(AvidLoader.this.avidDownloadRunnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        public void cleanup() {
            this.handler.removeCallbacks(AvidLoader.this.avidDownloadRunnable);
        }
    }

    public static AvidLoader getInstance() {
        return avidLoader;
    }

    public void registerAvidLoader(Context context) {
        this.context = context;
        this.taskRepeater = new TaskRepeater();
        loadAvidJSFromUrl();
    }

    public void unregisterAvidLoader() {
        if (this.taskRepeater != null) {
            this.taskRepeater.cleanup();
            this.taskRepeater = null;
        }
        this.listener = null;
        this.context = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.listener = avidLoaderListener;
    }

    public AvidLoaderListener getListener() {
        return this.listener;
    }

    private void loadAvidJSFromUrl() {
        if (!AvidBridge.isAvidJsReady() && this.activeTask == null) {
            this.activeTask = new DownloadAvidTask();
            this.activeTask.setListener(this);
            this.taskExecutor.executeTask(this.activeTask);
        }
    }

    private void repeatLoading() {
        if (this.taskRepeater != null) {
            this.taskRepeater.repeatLoading();
        }
    }

    public void onLoadAvid(String str) {
        this.activeTask = null;
        AvidBridge.setAvidJs(str);
        if (this.listener != null) {
            this.listener.onAvidLoaded();
        }
    }

    public void failedToLoadAvid() {
        this.activeTask = null;
        repeatLoading();
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public DownloadAvidTask getAsyncTask() {
        return this.activeTask;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public TaskRepeater getTaskRepeater() {
        return this.taskRepeater;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setTaskRepeater(TaskRepeater taskRepeater) {
        this.taskRepeater = taskRepeater;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @VisibleForTesting
    static void setAvidLoaderInstance(AvidLoader avidLoader) {
        avidLoader = avidLoader;
    }
}

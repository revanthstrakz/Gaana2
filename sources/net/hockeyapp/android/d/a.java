package net.hockeyapp.android.d;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import java.util.concurrent.Executor;

public class a {
    private static Executor a;

    @SuppressLint({"InlinedApi"})
    public static void a(AsyncTask<Void, ?, ?> asyncTask) {
        if (VERSION.SDK_INT <= 12) {
            asyncTask.execute(new Void[0]);
        } else {
            asyncTask.executeOnExecutor(a != null ? a : AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}

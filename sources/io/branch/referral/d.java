package io.branch.referral;

import android.os.AsyncTask;
import android.os.Build.VERSION;

public abstract class d<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    public AsyncTask<Params, Progress, Result> a(Params... paramsArr) {
        if (VERSION.SDK_INT >= 11) {
            return executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramsArr);
        }
        return execute(paramsArr);
    }
}

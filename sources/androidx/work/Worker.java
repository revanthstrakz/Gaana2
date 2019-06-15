package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import androidx.work.ListenableWorker.a;
import androidx.work.impl.utils.futures.b;
import com.google.common.util.concurrent.ListenableFuture;

public abstract class Worker extends ListenableWorker {
    b<a> a;

    @WorkerThread
    @NonNull
    public abstract a k();

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public Worker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @NonNull
    public final ListenableFuture<a> d() {
        this.a = b.d();
        i().execute(new Runnable() {
            public void run() {
                try {
                    Worker.this.a.a(Worker.this.k());
                } catch (Throwable th) {
                    Worker.this.a.a(th);
                }
            }
        });
        return this.a;
    }
}

package com.facebook.ads.internal.r.b.a;

import android.util.Log;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class e implements a {
    private final ExecutorService a = Executors.newSingleThreadExecutor();

    private class a implements Callable<Void> {
        private final File b;

        public a(File file) {
            this.b = file;
        }

        /* renamed from: a */
        public Void call() {
            e.this.b(this.b);
            return null;
        }
    }

    e() {
    }

    private void a(List<File> list) {
        long b = b((List) list);
        int size = list.size();
        for (File file : list) {
            if (!a(file, b, size)) {
                long length = file.length();
                if (file.delete()) {
                    size--;
                    long j = b - length;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Cache file ");
                    stringBuilder.append(file);
                    stringBuilder.append(" is deleted because it exceeds cache limit");
                    Log.i("ProxyCache", stringBuilder.toString());
                    b = j;
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Error deleting file ");
                    stringBuilder2.append(file);
                    stringBuilder2.append(" for trimming cache");
                    Log.e("ProxyCache", stringBuilder2.toString());
                }
            }
        }
    }

    private long b(List<File> list) {
        long j = 0;
        for (File length : list) {
            j += length.length();
        }
        return j;
    }

    private void b(File file) {
        d.c(file);
        a(d.b(file.getParentFile()));
    }

    public void a(File file) {
        this.a.submit(new a(file));
    }

    public abstract boolean a(File file, long j, int i);
}

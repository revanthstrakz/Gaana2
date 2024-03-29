package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.IOException;

public abstract class a<T> implements b<T> {
    private final String a;
    private final AssetManager b;
    private T c;

    public abstract T a(AssetManager assetManager, String str) throws IOException;

    public abstract void a(T t) throws IOException;

    public void b() {
    }

    public a(AssetManager assetManager, String str) {
        this.b = assetManager;
        this.a = str;
    }

    public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super T> aVar) {
        try {
            this.c = a(this.b, this.a);
            aVar.a(this.c);
        } catch (IOException e) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e);
            }
            aVar.a(e);
        }
    }

    public void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public DataSource c() {
        return DataSource.LOCAL;
    }
}

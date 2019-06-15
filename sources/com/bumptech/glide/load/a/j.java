package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.a.b.a;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class j<T> implements b<T> {
    private final Uri a;
    private final ContentResolver b;
    private T c;

    public abstract void a(T t) throws IOException;

    public abstract T b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    public void b() {
    }

    public j(ContentResolver contentResolver, Uri uri) {
        this.b = contentResolver;
        this.a = uri;
    }

    public final void a(Priority priority, a<? super T> aVar) {
        try {
            this.c = b(this.a, this.b);
            aVar.a(this.c);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e);
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

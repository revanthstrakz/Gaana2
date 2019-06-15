package com.bumptech.glide.load.b;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e.c;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.f;
import java.io.File;
import java.io.FileNotFoundException;

public final class k implements n<Uri, File> {
    private final Context a;

    public static final class a implements o<Uri, File> {
        private final Context a;

        public a(Context context) {
            this.a = context;
        }

        public n<Uri, File> a(r rVar) {
            return new k(this.a);
        }
    }

    private static class b implements com.bumptech.glide.load.a.b<File> {
        private static final String[] a = new String[]{"_data"};
        private final Context b;
        private final Uri c;

        public void a() {
        }

        public void b() {
        }

        b(Context context, Uri uri) {
            this.b = context;
            this.c = uri;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super File> aVar) {
            Cursor query = this.b.getContentResolver().query(this.c, a, null, null, null);
            CharSequence charSequence = null;
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        charSequence = query.getString(query.getColumnIndexOrThrow("_data"));
                    }
                    query.close();
                } catch (Throwable th) {
                    query.close();
                }
            }
            if (TextUtils.isEmpty(charSequence)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to find file path for: ");
                stringBuilder.append(this.c);
                aVar.a(new FileNotFoundException(stringBuilder.toString()));
                return;
            }
            aVar.a(new File(charSequence));
        }

        @NonNull
        public Class<File> d() {
            return File.class;
        }

        @NonNull
        public DataSource c() {
            return DataSource.LOCAL;
        }
    }

    k(Context context) {
        this.a = context;
    }

    public com.bumptech.glide.load.b.n.a<File> a(Uri uri, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(new c(uri), new b(this.a, uri));
    }

    public boolean a(Uri uri) {
        return com.bumptech.glide.load.a.a.b.a(uri);
    }
}

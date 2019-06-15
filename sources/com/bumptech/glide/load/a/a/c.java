package com.bumptech.glide.load.a.a;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.MediaStore.Video;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e;
import com.bumptech.glide.load.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class c implements com.bumptech.glide.load.a.b<InputStream> {
    private final Uri a;
    private final e b;
    private InputStream c;

    static class a implements d {
        private static final String[] b = new String[]{"_data"};
        private final ContentResolver a;

        a(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        public Cursor a(Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return this.a.query(Thumbnails.EXTERNAL_CONTENT_URI, b, "kind = 1 AND image_id = ?", new String[]{lastPathSegment}, null);
        }
    }

    static class b implements d {
        private static final String[] b = new String[]{"_data"};
        private final ContentResolver a;

        b(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        public Cursor a(Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return this.a.query(Video.Thumbnails.EXTERNAL_CONTENT_URI, b, "kind = 1 AND video_id = ?", new String[]{lastPathSegment}, null);
        }
    }

    public void b() {
    }

    public static c a(Context context, Uri uri) {
        return a(context, uri, new a(context.getContentResolver()));
    }

    public static c b(Context context, Uri uri) {
        return a(context, uri, new b(context.getContentResolver()));
    }

    private static c a(Context context, Uri uri, d dVar) {
        return new c(uri, new e(e.b(context).i().a(), dVar, e.b(context).c(), context.getContentResolver()));
    }

    c(Uri uri, e eVar) {
        this.a = uri;
        this.b = eVar;
    }

    public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super InputStream> aVar) {
        try {
            this.c = e();
            aVar.a(this.c);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e);
            }
            aVar.a(e);
        }
    }

    private InputStream e() throws FileNotFoundException {
        InputStream b = this.b.b(this.a);
        int a = b != null ? this.b.a(this.a) : -1;
        return a != -1 ? new com.bumptech.glide.load.a.e(b, a) : b;
    }

    public void a() {
        if (this.c != null) {
            try {
                this.c.close();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public Class<InputStream> d() {
        return InputStream.class;
    }

    @NonNull
    public DataSource c() {
        return DataSource.LOCAL;
    }
}

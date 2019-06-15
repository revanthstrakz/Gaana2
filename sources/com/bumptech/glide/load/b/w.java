package com.bumptech.glide.load.b;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a.g;
import com.bumptech.glide.load.a.l;
import com.bumptech.glide.load.f;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class w<Data> implements n<Uri, Data> {
    private static final Set<String> a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"file", "android.resource", "content"})));
    private final b<Data> b;

    public interface b<Data> {
        com.bumptech.glide.load.a.b<Data> a(Uri uri);
    }

    public static class a implements o<Uri, ParcelFileDescriptor>, b<ParcelFileDescriptor> {
        private final ContentResolver a;

        public a(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        public com.bumptech.glide.load.a.b<ParcelFileDescriptor> a(Uri uri) {
            return new g(this.a, uri);
        }

        public n<Uri, ParcelFileDescriptor> a(r rVar) {
            return new w(this);
        }
    }

    public static class c implements o<Uri, InputStream>, b<InputStream> {
        private final ContentResolver a;

        public c(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        public com.bumptech.glide.load.a.b<InputStream> a(Uri uri) {
            return new l(this.a, uri);
        }

        public n<Uri, InputStream> a(r rVar) {
            return new w(this);
        }
    }

    public w(b<Data> bVar) {
        this.b = bVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(Uri uri, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(new com.bumptech.glide.e.c(uri), this.b.a(uri));
    }

    public boolean a(Uri uri) {
        return a.contains(uri.getScheme());
    }
}

package com.bumptech.glide.load.b;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a.f;
import com.bumptech.glide.load.a.k;
import java.io.InputStream;

public class a<Data> implements n<Uri, Data> {
    private static final int a = "file:///android_asset/".length();
    private final AssetManager b;
    private final a<Data> c;

    public interface a<Data> {
        com.bumptech.glide.load.a.b<Data> a(AssetManager assetManager, String str);
    }

    public static class b implements a<ParcelFileDescriptor>, o<Uri, ParcelFileDescriptor> {
        private final AssetManager a;

        public b(AssetManager assetManager) {
            this.a = assetManager;
        }

        public n<Uri, ParcelFileDescriptor> a(r rVar) {
            return new a(this.a, this);
        }

        public com.bumptech.glide.load.a.b<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new f(assetManager, str);
        }
    }

    public static class c implements a<InputStream>, o<Uri, InputStream> {
        private final AssetManager a;

        public c(AssetManager assetManager) {
            this.a = assetManager;
        }

        public n<Uri, InputStream> a(r rVar) {
            return new a(this.a, this);
        }

        public com.bumptech.glide.load.a.b<InputStream> a(AssetManager assetManager, String str) {
            return new k(assetManager, str);
        }
    }

    public a(AssetManager assetManager, a<Data> aVar) {
        this.b = assetManager;
        this.c = aVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.f fVar) {
        return new com.bumptech.glide.load.b.n.a(new com.bumptech.glide.e.c(uri), this.c.a(this.b, uri.toString().substring(a)));
    }

    public boolean a(Uri uri) {
        if ("file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0))) {
            return true;
        }
        return false;
    }
}

package com.bumptech.glide.load.b;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.f;
import java.io.File;
import java.io.InputStream;

public class u<Data> implements n<String, Data> {
    private final n<Uri, Data> a;

    public static class a implements o<String, ParcelFileDescriptor> {
        public n<String, ParcelFileDescriptor> a(r rVar) {
            return new u(rVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class b implements o<String, InputStream> {
        public n<String, InputStream> a(r rVar) {
            return new u(rVar.a(Uri.class, InputStream.class));
        }
    }

    public boolean a(String str) {
        return true;
    }

    public u(n<Uri, Data> nVar) {
        this.a = nVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(String str, int i, int i2, f fVar) {
        Uri b = b(str);
        if (b == null) {
            return null;
        }
        return this.a.a(b, i, i2, fVar);
    }

    @Nullable
    private static Uri b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri c;
        if (str.startsWith("/")) {
            c = c(str);
        } else {
            Uri parse = Uri.parse(str);
            c = parse.getScheme() == null ? c(str) : parse;
        }
        return c;
    }

    private static Uri c(String str) {
        return Uri.fromFile(new File(str));
    }
}

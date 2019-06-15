package com.bumptech.glide.load.b;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.f;
import java.io.InputStream;

public class s<Data> implements n<Integer, Data> {
    private final n<Uri, Data> a;
    private final Resources b;

    public static class a implements o<Integer, ParcelFileDescriptor> {
        private final Resources a;

        public a(Resources resources) {
            this.a = resources;
        }

        public n<Integer, ParcelFileDescriptor> a(r rVar) {
            return new s(this.a, rVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class b implements o<Integer, InputStream> {
        private final Resources a;

        public b(Resources resources) {
            this.a = resources;
        }

        public n<Integer, InputStream> a(r rVar) {
            return new s(this.a, rVar.a(Uri.class, InputStream.class));
        }
    }

    public static class c implements o<Integer, Uri> {
        private final Resources a;

        public c(Resources resources) {
            this.a = resources;
        }

        public n<Integer, Uri> a(r rVar) {
            return new s(this.a, v.a());
        }
    }

    public boolean a(Integer num) {
        return true;
    }

    public s(Resources resources, n<Uri, Data> nVar) {
        this.b = resources;
        this.a = nVar;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(Integer num, int i, int i2, f fVar) {
        Uri b = b(num);
        if (b == null) {
            return null;
        }
        return this.a.a(b, i, i2, fVar);
    }

    @Nullable
    private Uri b(Integer num) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android.resource://");
            stringBuilder.append(this.b.getResourcePackageName(num.intValue()));
            stringBuilder.append('/');
            stringBuilder.append(this.b.getResourceTypeName(num.intValue()));
            stringBuilder.append('/');
            stringBuilder.append(this.b.getResourceEntryName(num.intValue()));
            return Uri.parse(stringBuilder.toString());
        } catch (NotFoundException e) {
            if (Log.isLoggable("ResourceLoader", 5)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Received invalid resource id: ");
                stringBuilder2.append(num);
                Log.w("ResourceLoader", stringBuilder2.toString(), e);
            }
            return null;
        }
    }
}

package com.bumptech.glide.load.resource.b;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.IOException;
import java.util.List;

public class e implements g<Uri, Drawable> {
    private final Context a;

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    public boolean a(Uri uri, f fVar) throws IOException {
        return uri.getScheme().equals("android.resource");
    }

    @NonNull
    public q<Drawable> a(Uri uri, int i, int i2, f fVar) throws IOException {
        Context context;
        i = a(uri);
        String authority = uri.getAuthority();
        if (authority.equals(this.a.getPackageName())) {
            context = this.a;
        } else {
            context = a(uri, authority);
        }
        return d.a(a.a(context, i));
    }

    @NonNull
    private Context a(Uri uri, String str) {
        try {
            return this.a.createPackageContext(str, 0);
        } catch (NameNotFoundException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to obtain context or unrecognized Uri format for: ");
            stringBuilder.append(uri);
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        }
    }

    @DrawableRes
    private int a(Uri uri) {
        Integer valueOf;
        List pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            valueOf = Integer.valueOf(this.a.getResources().getIdentifier(str2, str, uri.getAuthority()));
        } else {
            if (pathSegments.size() == 1) {
                try {
                    valueOf = Integer.valueOf((String) pathSegments.get(0));
                } catch (NumberFormatException unused) {
                }
            }
            valueOf = null;
        }
        StringBuilder stringBuilder;
        if (valueOf == null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unrecognized Uri format: ");
            stringBuilder.append(uri);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (valueOf.intValue() != 0) {
            return valueOf.intValue();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to obtain resource id for: ");
            stringBuilder.append(uri);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}

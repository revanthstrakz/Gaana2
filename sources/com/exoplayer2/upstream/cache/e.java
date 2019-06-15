package com.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSpec;

public final class e {
    public static final c a = f.a;

    public static String a(Uri uri) {
        return uri.toString();
    }

    public static String a(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : a(dataSpec.uri);
    }
}

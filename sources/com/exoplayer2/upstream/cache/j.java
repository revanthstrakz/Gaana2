package com.exoplayer2.upstream.cache;

import android.net.Uri;
import android.support.annotation.Nullable;

final class j {
    public static long a(i iVar) {
        return iVar.a("exo_len", -1);
    }

    public static void a(k kVar, long j) {
        kVar.a("exo_len", j);
    }

    @Nullable
    public static Uri b(i iVar) {
        String a = iVar.a("exo_redir", (String) null);
        if (a == null) {
            return null;
        }
        return Uri.parse(a);
    }

    public static void a(k kVar, Uri uri) {
        kVar.a("exo_redir", uri.toString());
    }

    public static void a(k kVar) {
        kVar.a("exo_redir");
    }
}

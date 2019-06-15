package com.til.colombia.android.commons.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;

@TargetApi(12)
public final class h {
    public static LruCache<String, Bitmap> a;

    private static void a() {
        if (a == null) {
            a = new i(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8);
        }
    }

    public static void a(String str, Bitmap bitmap) {
        if (a(str) == null) {
            a.put(str, bitmap);
        }
    }

    public static Bitmap a(String str) {
        if (a == null) {
            a = new i(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8);
        }
        return (Bitmap) a.get(str);
    }
}

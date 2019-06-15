package com.library.custom_glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.bumptech.glide.e;
import java.io.File;

public final class GlideApp {
    private GlideApp() {
    }

    @Nullable
    public static File getPhotoCacheDir(Context context) {
        return e.a(context);
    }

    @Nullable
    public static File getPhotoCacheDir(Context context, String str) {
        return e.a(context, str);
    }

    public static e get(Context context) {
        return e.b(context);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void init(e eVar) {
        e.a(eVar);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void tearDown() {
        e.a();
    }

    public static GlideRequests with(Context context) {
        return (GlideRequests) e.c(context);
    }

    public static GlideRequests with(Activity activity) {
        return (GlideRequests) e.a(activity);
    }

    public static GlideRequests with(FragmentActivity fragmentActivity) {
        return (GlideRequests) e.a(fragmentActivity);
    }

    public static GlideRequests with(Fragment fragment) {
        return (GlideRequests) e.a(fragment);
    }

    public static GlideRequests with(android.support.v4.app.Fragment fragment) {
        return (GlideRequests) e.a(fragment);
    }

    public static GlideRequests with(View view) {
        return (GlideRequests) e.a(view);
    }
}

package com.helpshift.support;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.helpshift.executors.ActionExecutor;
import java.util.Map;

public class l implements com.helpshift.a.a {

    private static class b {
        static final l a = new l();
    }

    /* renamed from: com.helpshift.support.l$1 */
    static class AnonymousClass1 extends com.helpshift.util.a.e.b<Integer> {
        public void run() {
            this.a = m.a();
        }
    }

    public static class a extends com.helpshift.support.m.a {
    }

    public ActionExecutor a() {
        return null;
    }

    /* synthetic */ l(AnonymousClass1 anonymousClass1) {
        this();
    }

    private l() {
    }

    public static l b() {
        return b.a;
    }

    public static void a(final String str) {
        com.helpshift.util.a.b.a().a(new Runnable() {
            public void run() {
                m.a(str);
            }
        });
    }

    public static void a(final Activity activity) {
        com.helpshift.util.a.b.a().c(new Runnable() {
            public void run() {
                m.a(activity);
            }
        });
    }

    public static void a(final Activity activity, final String str) {
        com.helpshift.util.a.b.a().c(new Runnable() {
            public void run() {
                m.a(activity, str);
            }
        });
    }

    public static void b(final Activity activity, final String str) {
        com.helpshift.util.a.b.a().c(new Runnable() {
            public void run() {
                m.b(activity, str);
            }
        });
    }

    public static void b(final Activity activity) {
        com.helpshift.util.a.b.a().c(new Runnable() {
            public void run() {
                m.b(activity);
            }
        });
    }

    public static void a(final i iVar) {
        com.helpshift.util.a.b.a().a(new Runnable() {
            public void run() {
                m.a(iVar);
            }
        });
    }

    public void a(Application application, String str, String str2, String str3, Map<String, Object> map) {
        m.a(application, str, str2, str3, map);
    }

    public void b(@NonNull Application application, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Map<String, Object> map) {
        m.b(application, str, str2, str3, map);
    }

    public void a(String str, String str2) {
        m.a(str, str2);
    }

    public void a(@NonNull Context context, @NonNull String str) {
        m.a(context, str);
    }

    public void a(Context context, Intent intent) {
        m.a(context, intent);
    }
}

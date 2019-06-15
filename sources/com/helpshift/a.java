package com.helpshift;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.helpshift.exceptions.InstallException;
import com.helpshift.executors.ActionExecutor;
import java.util.Map;

public class a {

    public interface a {
        ActionExecutor a();

        void a(Application application, String str, String str2, String str3, Map<String, Object> map);

        void a(Context context, Intent intent);

        void a(Context context, String str);

        void a(String str, String str2);

        void b(Application application, String str, String str2, String str3, Map<String, Object> map);
    }

    public static void a(a aVar) {
        c.a(aVar);
    }

    public static void a(String str, String str2) {
        c.a(str, str2);
    }

    @Deprecated
    public static void a(Application application, String str, String str2, String str3, Map<String, Object> map) throws InstallException {
        c.a(application, str, str2, str3, map);
    }

    public static void a(Context context, String str) {
        c.a(context, str);
    }

    public static void a(Context context, Intent intent) {
        c.a(context, intent);
    }
}

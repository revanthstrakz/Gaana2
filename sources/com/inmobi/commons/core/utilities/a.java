package com.inmobi.commons.core.utilities;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a {
    private static final String a = "a";
    private static List<b> b = new ArrayList();
    private static Object c = null;
    private static boolean d = false;
    private static HandlerThread e;
    private static final Object f = new Object();
    private static volatile a g;

    static class a extends Handler {
        boolean a = true;

        a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (!a.d) {
                if (message.what == 1001 && this.a) {
                    this.a = false;
                    a.a(false);
                    a.a;
                    return;
                }
                if (message.what == 1002 && !this.a) {
                    this.a = true;
                    a.a(true);
                    a.a;
                }
            }
        }
    }

    public interface b {
        void a(boolean z);
    }

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    public final void a(b bVar) {
        b.add(bVar);
        if (b.size() == 1 && com.inmobi.commons.a.a.a()) {
            HandlerThread handlerThread = new HandlerThread("ApplicationFocusChangeObserverHandler");
            e = handlerThread;
            handlerThread.start();
            Class cls = null;
            for (Class cls2 : Application.class.getDeclaredClasses()) {
                if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                    new Class[1][0] = cls2;
                    cls = cls2;
                }
            }
            c = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                private final Handler b = new a(a.e.getLooper());
                private WeakReference<Activity> c;

                public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (objArr != null) {
                        String name = method.getName();
                        int i = -1;
                        int hashCode = name.hashCode();
                        if (hashCode != 195654633) {
                            if (hashCode != 1495889555) {
                                if (hashCode == 1508755423 && name.equals("onActivityStopped")) {
                                    i = 2;
                                }
                            } else if (name.equals("onActivityStarted")) {
                                i = 0;
                            }
                        } else if (name.equals("onActivityResumed")) {
                            i = 1;
                        }
                        Activity activity;
                        switch (i) {
                            case 0:
                            case 1:
                                activity = (Activity) objArr[0];
                                if (this.c == null || this.c.get() != activity) {
                                    this.c = new WeakReference(activity);
                                }
                                this.b.removeMessages(1001);
                                this.b.sendEmptyMessage(1002);
                                break;
                            case 2:
                                activity = (Activity) objArr[0];
                                if (this.c != null && this.c.get() == activity) {
                                    this.b.sendEmptyMessageDelayed(1001, 3000);
                                    break;
                                }
                        }
                    }
                    return null;
                }
            });
            Application application = (Application) com.inmobi.commons.a.a.b();
            if (c != null) {
                try {
                    Application.class.getMethod("registerActivityLifecycleCallbacks", new Class[]{cls}).invoke(application, new Object[]{c});
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                } catch (Exception e) {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("type", "GenericException");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(e.getMessage());
                        hashMap.put("message", stringBuilder.toString());
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
                    } catch (Exception unused2) {
                        StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                        stringBuilder2.append(e.getMessage());
                        stringBuilder2.append(")");
                    }
                }
            }
        }
    }

    public static void b() {
        d = true;
    }

    public static void c() {
        d = false;
    }
}

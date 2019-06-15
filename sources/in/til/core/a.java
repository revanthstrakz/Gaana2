package in.til.core;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import in.til.core.integrations.TILSDKExceptionDto;
import in.til.core.integrations.b;
import in.til.core.integrations.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a {
    private static final Handler a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown handler message received: ");
            stringBuilder.append(message.what);
            throw new AssertionError(stringBuilder.toString());
        }
    };
    private static volatile a b;
    private final Application c;
    private List<in.til.core.integrations.b.a> d;
    private Map<String, b<?>> e;
    private final ExecutorService f;

    public static class a {
        private final Application a;
        private List<in.til.core.integrations.b.a> b;

        public a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.a = (Application) context.getApplicationContext();
            if (this.a == null) {
                throw new IllegalArgumentException("Application context must not be null.");
            }
            this.b = new ArrayList();
        }

        public a a(in.til.core.integrations.b.a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("Factory must not be null.");
            }
            this.b.add(aVar);
            return this;
        }

        public a a(HashMap<String, HashMap<String, String>> hashMap) {
            ArrayList arrayList = new ArrayList(1 + this.b.size());
            arrayList.addAll(this.b);
            return new a(this.a, Executors.newSingleThreadExecutor(), hashMap, arrayList, null);
        }
    }

    /* synthetic */ a(Application application, ExecutorService executorService, HashMap hashMap, List list, AnonymousClass1 anonymousClass1) {
        this(application, executorService, hashMap, list);
    }

    private a(Application application, ExecutorService executorService, final HashMap<String, HashMap<String, String>> hashMap, List<in.til.core.integrations.b.a> list) {
        this.c = application;
        this.d = Collections.unmodifiableList(list);
        a((HashMap) hashMap, false);
        this.f = executorService;
        this.f.submit(new Runnable() {
            public void run() {
                a.a.post(new Runnable() {
                    public void run() {
                        a.this.a(hashMap, true);
                    }
                });
            }
        });
    }

    public Application a() {
        return this.c;
    }

    public static a b() {
        return b;
    }

    public static void a(a aVar) {
        synchronized (a.class) {
            if (b != null) {
                throw new IllegalStateException("Singleton instance already exists.");
            }
            b = aVar;
        }
    }

    private void a(HashMap<String, HashMap<String, String>> hashMap, boolean z) {
        if (this.e == null) {
            this.e = new LinkedHashMap(this.d.size());
        }
        for (int i = 0; i < this.d.size(); i++) {
            in.til.core.integrations.b.a aVar = (in.til.core.integrations.b.a) this.d.get(i);
            String key = aVar.key();
            HashMap hashMap2 = (HashMap) hashMap.get(key);
            b create;
            if ((key.equalsIgnoreCase("nsso") || key.equalsIgnoreCase("tp") || key.equalsIgnoreCase("tildmp") || key.equalsIgnoreCase("tpr")) && !z) {
                create = aVar.create(hashMap2, this);
                if (create != null) {
                    this.e.put(key, create);
                }
            } else if (!(key.equalsIgnoreCase("nsso") || key.equalsIgnoreCase("tildmp") || key.equalsIgnoreCase("tp") || key.equalsIgnoreCase("tpr") || !z)) {
                create = aVar.create(hashMap2, this);
                if (create != null) {
                    this.e.put(key, create);
                }
            }
        }
        if (z) {
            this.d = null;
        }
    }

    public boolean a(c cVar) {
        return c("nsso", cVar).nSSOcheckIfSdkInitialized();
    }

    public void a(String str, String str2, c cVar) {
        c("nsso", cVar).nSSOresendSignUpOtp(str, str2, cVar);
    }

    public void a(Context context, String str, String str2, String str3, c cVar) {
        c("nsso", cVar).nSSOinitializeSDK(context, str, str2, str3, cVar);
    }

    public void b(String str, String str2, c cVar) {
        c("nsso", cVar).nSSOloginWithEmail(str, str2, cVar);
    }

    public void a(Context context, boolean z, c cVar) {
        c("nsso", cVar).nSSOsignOutUser(context, z, cVar);
    }

    public void b(c cVar) {
        c("nsso", cVar).nSSOgetUserDetails(cVar);
    }

    public void a(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8, c cVar) {
        c cVar2 = cVar;
        c("nsso", cVar2).nSSOsignUpUser(str, str2, str3, str4, str5, z, str6, str7, str8, cVar2);
    }

    public void a(String str, String str2, String str3, c cVar) {
        c("nsso", cVar).nSSOverifySignUpUser(str, str2, str3, cVar);
    }

    public void c(c cVar) {
        c("nsso", cVar).nSSOgetAppSession(cVar);
    }

    public void a(String str, c cVar) {
        c("nsso", cVar).nSSOmigrateCurrentSession(str, cVar);
    }

    public void b(String str, c cVar) {
        c("nsso", cVar).nSSOcreateUnverifiedSession(str, cVar);
    }

    public void a(String str, String str2, String str3, boolean z, c cVar) {
        c("nsso", cVar).nSSOloginWithSocial(str, str2, str3, z, cVar);
    }

    public void a(Context context, String str, String str2, String str3, String str4, String str5, c cVar) {
        c cVar2 = cVar;
        c("tp", cVar2).tpInit(context, str, str2, str3, str4, str5, cVar2);
    }

    public void d(c cVar) {
        c("tp", cVar).tpApplicationPaused(cVar);
    }

    public void e(c cVar) {
        c("tp", cVar).tpLogout(cVar);
    }

    public void b(String str, String str2, String str3, c cVar) {
        c("tp", cVar).tpLogActivityWithCode(str, str2, str3, cVar);
    }

    private b<?> c(String str, c cVar) {
        try {
            return (b) this.e.get(str);
        } catch (Exception unused) {
            cVar.onSdkFailure(new TILSDKExceptionDto(str));
            return null;
        }
    }
}

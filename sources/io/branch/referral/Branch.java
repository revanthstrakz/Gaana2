package io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import io.branch.referral.Defines.Jsonkey;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Branch implements a, io.branch.referral.i.b {
    private static boolean d = false;
    private static Branch f = null;
    private static boolean t = false;
    private static boolean u = false;
    private static CUSTOM_REFERRABLE_SETTINGS x = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private boolean A = false;
    final Object a;
    WeakReference<Activity> b;
    private JSONObject c;
    private boolean e = true;
    private g g;
    private m h;
    private ag i;
    private Context j;
    private Timer k;
    private Timer l;
    private boolean m;
    private Semaphore n;
    private y o;
    private int p;
    private boolean q;
    private Map<f, String> r;
    private ScheduledFuture<?> s;
    private SESSION_STATE v = SESSION_STATE.UNINITIALISED;
    private ShareLinkManager w;
    private boolean y = false;
    private final ConcurrentHashMap<String, String> z;

    /* renamed from: io.branch.referral.Branch$1 */
    class AnonymousClass1 extends TimerTask {
        final /* synthetic */ Branch a;

        public void run() {
            if (this.a.b != null) {
                this.a.b.clear();
            }
            this.a.i();
        }
    }

    private enum CUSTOM_REFERRABLE_SETTINGS {
        USE_DEFAULT,
        REFERRABLE,
        NON_REFERRABLE
    }

    private enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    @TargetApi(14)
    private class a implements ActivityLifecycleCallbacks {
        private int b;

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private a() {
            this.b = 0;
        }

        /* synthetic */ a(Branch branch, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (i.a().b(activity.getApplicationContext())) {
                i.a().a((Context) activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            if (this.b < 1) {
                if (h.a(Branch.this.j)) {
                    Branch.this.b();
                }
                Uri uri = null;
                if (activity.getIntent() != null) {
                    uri = activity.getIntent().getData();
                }
                Branch.this.a(uri, activity);
            }
            this.b++;
        }

        public void onActivityResumed(Activity activity) {
            Branch.this.b = new WeakReference(activity);
        }

        public void onActivityPaused(Activity activity) {
            if (Branch.this.w != null) {
                Branch.this.w.a(true);
            }
        }

        public void onActivityStopped(Activity activity) {
            this.b--;
            if (this.b < 1) {
                Branch.this.h();
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (Branch.this.b != null && Branch.this.b.get() == activity) {
                Branch.this.b.clear();
            }
            i.a().a(activity);
        }
    }

    public interface b {
        void a(String str, e eVar);
    }

    public interface c {
        void a(JSONArray jSONArray, e eVar);
    }

    public interface e {
        void onInitFinished(JSONObject jSONObject, e eVar);
    }

    public interface f {
        void a(boolean z, e eVar);
    }

    public interface g {
        boolean a();
    }

    public interface h {
        void a(boolean z, e eVar);
    }

    public static class i {
        private Drawable a;
        private String b;
        private Drawable c;
        private String d;

        public Drawable a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public Drawable c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }
    }

    private class d extends d<Void, Void, af> {
        int a = 0;
        ServerRequest b;

        public d(ServerRequest serverRequest) {
            this.b = serverRequest;
            this.a = Branch.this.h.b();
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public af doInBackground(Void... voidArr) {
            if (this.b instanceof w) {
                ((w) this.b).o();
            }
            Branch branch = Branch.this;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.b.d());
            stringBuilder.append("-");
            stringBuilder.append(Jsonkey.Queue_Wait_Time.getKey());
            branch.a(stringBuilder.toString(), String.valueOf(this.b.k()));
            if (this.b.g()) {
                this.b.a(Branch.this.i);
            }
            if (this.b.a()) {
                return Branch.this.g.a(this.b.e(), this.b.h(), this.b.d(), this.a);
            }
            return Branch.this.g.a(this.b.a(Branch.this.z), this.b.e(), this.b.d(), this.a);
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(af afVar) {
            super.onPostExecute(afVar);
            if (afVar != null) {
                try {
                    int a = afVar.a();
                    Branch.this.q = true;
                    if (a != 200) {
                        if (this.b instanceof w) {
                            Branch.this.v = SESSION_STATE.UNINITIALISED;
                        }
                        if (a == 409) {
                            Branch.this.o.b(this.b);
                            if (this.b instanceof q) {
                                ((q) this.b).o();
                            } else {
                                Log.i("BranchSDK", "Branch API Error: Conflicting resource error code from API");
                                Branch.this.a(0, a);
                            }
                        } else {
                            Branch.this.q = false;
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < Branch.this.o.a(); i++) {
                                arrayList.add(Branch.this.o.a(i));
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ServerRequest serverRequest = (ServerRequest) it.next();
                                if (serverRequest == null || !serverRequest.c()) {
                                    Branch.this.o.b(serverRequest);
                                }
                            }
                            Branch.this.p = 0;
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                ServerRequest serverRequest2 = (ServerRequest) it2.next();
                                if (serverRequest2 != null) {
                                    serverRequest2.a(a, afVar.d());
                                    if (serverRequest2.c()) {
                                        serverRequest2.b();
                                    }
                                }
                            }
                        }
                    } else {
                        Branch.this.q = true;
                        if (this.b instanceof q) {
                            if (afVar.b() != null) {
                                Branch.this.r.put(((q) this.b).m(), afVar.b().getString("url"));
                            }
                        } else if (this.b instanceof x) {
                            Branch.this.r.clear();
                            Branch.this.o.d();
                        }
                        Branch.this.o.b();
                        if (!(this.b instanceof w)) {
                            if (!(this.b instanceof v)) {
                                this.b.a(afVar, Branch.f);
                            }
                        }
                        JSONObject b = afVar.b();
                        if (b != null) {
                            boolean z;
                            if (b.has(Jsonkey.SessionID.getKey())) {
                                Branch.this.h.d(b.getString(Jsonkey.SessionID.getKey()));
                                z = true;
                            } else {
                                z = false;
                            }
                            if (b.has(Jsonkey.IdentityID.getKey())) {
                                if (!Branch.this.h.j().equals(b.getString(Jsonkey.IdentityID.getKey()))) {
                                    Branch.this.r.clear();
                                    Branch.this.h.e(b.getString(Jsonkey.IdentityID.getKey()));
                                    z = true;
                                }
                            }
                            if (b.has(Jsonkey.DeviceFingerprintID.getKey())) {
                                Branch.this.h.c(b.getString(Jsonkey.DeviceFingerprintID.getKey()));
                                z = true;
                            }
                            if (z) {
                                Branch.this.l();
                            }
                            if (this.b instanceof w) {
                                Branch.this.v = SESSION_STATE.INITIALISED;
                                this.b.a(afVar, Branch.f);
                                Branch.this.y = ((w) this.b).m();
                                if (!((w) this.b).a(afVar)) {
                                    Branch.this.s();
                                }
                            } else {
                                this.b.a(afVar, Branch.f);
                            }
                        }
                    }
                    Branch.this.p = 0;
                    if (Branch.this.q && Branch.this.v != SESSION_STATE.UNINITIALISED) {
                        Branch.this.k();
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    public void b(String str, String str2) {
    }

    private Branch(@NonNull Context context) {
        this.h = m.a(context);
        this.g = new g(context);
        this.i = new ag(context);
        this.o = y.a(context);
        this.n = new Semaphore(1);
        this.k = new Timer();
        this.l = new Timer();
        this.a = new Object();
        this.m = false;
        this.p = 0;
        this.q = true;
        this.r = new HashMap();
        this.z = new ConcurrentHashMap();
        this.A = this.i.a((a) this);
    }

    @TargetApi(14)
    public static Branch a() {
        if (f == null) {
            Log.e("BranchSDK", "Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (t && !u) {
            Log.e("BranchSDK", "Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return f;
    }

    private static Branch a(@NonNull Context context, boolean z) {
        if (f == null) {
            f = d(context);
            String a = f.h.a(z);
            if (a == null || a.equalsIgnoreCase("bnc_no_value")) {
                a = null;
                try {
                    Resources resources = context.getResources();
                    a = resources.getString(resources.getIdentifier("io.branch.apiKey", "string", context.getPackageName()));
                } catch (Exception unused) {
                }
                if (TextUtils.isEmpty(a)) {
                    Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's Manifest file!");
                    z = f.h.b("bnc_no_value");
                } else {
                    z = f.h.b(a);
                }
            } else {
                z = f.h.b(a);
            }
            if (z) {
                f.r.clear();
                f.o.d();
            }
        }
        f.j = context.getApplicationContext();
        if (context instanceof BranchApp) {
            t = true;
            f.a((Application) context);
        }
        return f;
    }

    public static Branch a(@NonNull Context context) {
        return a(context, true);
    }

    public static Branch b(@NonNull Context context) {
        return a(context, false);
    }

    @TargetApi(14)
    public static Branch c(@NonNull Context context) {
        t = true;
        x = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        a(context, 1 ^ h.a(context));
        f.a((Application) context);
        return f;
    }

    private static Branch d(@NonNull Context context) {
        return new Branch(context.getApplicationContext());
    }

    @Deprecated
    public void b() {
        this.h.A();
    }

    public static boolean c() {
        return d;
    }

    public boolean a(e eVar, Activity activity) {
        boolean z = true;
        if (x == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            a(eVar, activity, true);
        } else {
            if (x != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                z = false;
            }
            a(eVar, activity, z);
        }
        return false;
    }

    public boolean a(e eVar, @NonNull Uri uri, Activity activity) {
        boolean b = b(uri, activity);
        a(eVar, activity);
        return b;
    }

    public boolean a(Uri uri, Activity activity) {
        boolean b = b(uri, activity);
        a((e) null, activity);
        return b;
    }

    private void a(e eVar, Activity activity, boolean z) {
        if (activity != null) {
            this.b = new WeakReference(activity);
        }
        if (r() && p() && this.v == SESSION_STATE.INITIALISED) {
            if (eVar != null) {
                if (!t) {
                    eVar.onInitFinished(new JSONObject(), null);
                } else if (this.y) {
                    eVar.onInitFinished(new JSONObject(), null);
                } else {
                    eVar.onInitFinished(f(), null);
                    this.y = true;
                }
            }
            m();
            o();
            return;
        }
        if (z) {
            this.h.w();
        } else {
            this.h.x();
        }
        if (this.v != SESSION_STATE.INITIALISING) {
            this.v = SESSION_STATE.INITIALISING;
            a(eVar);
        } else if (eVar != null) {
            this.o.a(eVar);
        }
    }

    private void h() {
        i();
        if (this.h.C() && this.s == null) {
            j();
        }
    }

    private void i() {
        if (this.v != SESSION_STATE.UNINITIALISED) {
            if (!this.q) {
                ServerRequest c = this.o.c();
                if ((c != null && (c instanceof ab)) || (c instanceof ac)) {
                    this.o.b();
                }
            } else if (!this.o.e()) {
                a(new aa(this.j));
            }
            this.v = SESSION_STATE.UNINITIALISED;
        }
    }

    private boolean b(Uri uri, Activity activity) {
        String string;
        if (uri != null) {
            try {
                this.h.h(uri.toString());
            } catch (Exception unused) {
            }
        }
        if (!(activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
            Bundle extras = activity.getIntent().getExtras();
            Set<String> keySet = extras.keySet();
            if (keySet.size() > 0) {
                JSONObject jSONObject = new JSONObject();
                for (String str : keySet) {
                    jSONObject.put(str, extras.get(str));
                }
                this.h.i(jSONObject.toString());
            }
        }
        if (!(activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
            try {
                string = activity.getIntent().getExtras().getString(Jsonkey.AndroidPushNotificationKey.getKey());
                if (string != null && string.length() > 0) {
                    this.h.l(string);
                    return false;
                }
            } catch (Exception unused2) {
            }
        }
        if (!(uri == null || !uri.isHierarchical() || activity == null)) {
            try {
                StringBuilder stringBuilder;
                String stringBuilder2;
                if (uri.getQueryParameter(Jsonkey.LinkClickID.getKey()) != null) {
                    this.h.j(uri.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("link_click_id=");
                    stringBuilder.append(uri.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                    string = stringBuilder.toString();
                    String str2 = null;
                    if (activity.getIntent() != null) {
                        str2 = activity.getIntent().getDataString();
                    }
                    StringBuilder stringBuilder3;
                    if (uri.getQuery().length() == string.length()) {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("\\?");
                        stringBuilder3.append(string);
                        stringBuilder2 = stringBuilder3.toString();
                    } else if (str2 == null || str2.length() - string.length() != str2.indexOf(string)) {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(string);
                        stringBuilder3.append("&");
                        stringBuilder2 = stringBuilder3.toString();
                    } else {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("&");
                        stringBuilder3.append(string);
                        stringBuilder2 = stringBuilder3.toString();
                    }
                    if (str2 != null) {
                        activity.getIntent().setData(Uri.parse(str2.replaceFirst(stringBuilder2, "")));
                    } else {
                        Log.w("BranchSDK", "Branch Warning. URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                    }
                    return true;
                }
                string = uri.getScheme();
                if (string != null && activity.getIntent() != null && (activity.getIntent().getFlags() & 1048576) == 0 && ((string.equalsIgnoreCase("http") || string.equalsIgnoreCase("https")) && uri.getHost() != null && uri.getHost().length() > 0 && uri.getQueryParameter(Jsonkey.AppLinkUsed.getKey()) == null)) {
                    this.h.k(uri.toString());
                    stringBuilder2 = uri.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(stringBuilder2);
                    stringBuilder.append(stringBuilder2.contains("?") ? "&" : "?");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(stringBuilder2);
                    stringBuilder.append(Jsonkey.AppLinkUsed.getKey());
                    stringBuilder.append("=true");
                    activity.getIntent().setData(Uri.parse(stringBuilder.toString()));
                    return false;
                }
            } catch (Exception unused3) {
            }
        }
        return false;
    }

    public void d() {
        this.A = false;
        this.o.a(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        k();
    }

    public JSONObject e() {
        return a(a(this.h.t()));
    }

    public JSONObject f() {
        return a(a(this.h.s()));
    }

    private JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (this.c != null) {
                    if (this.c.length() > 0) {
                        Log.w("BranchSDK", "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator keys = this.c.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        jSONObject.put(str, this.c.get(str));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    private JSONObject a(String str) {
        if (str.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            try {
                return new JSONObject(new String(c.a(str.getBytes(), 2)));
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
                return new JSONObject();
            }
        }
    }

    private void j() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        AnonymousClass2 anonymousClass2 = new Runnable() {
            public void run() {
                ServerRequest adVar = new ad(Branch.this.j);
                if (!adVar.e && !adVar.a(Branch.this.j)) {
                    Branch.this.a(adVar);
                }
            }
        };
        Date date = new Date();
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(date);
        int i = 7;
        int i2 = 7 - instance.get(7);
        int i3 = 2 - instance.get(11);
        if (i2 != 0 || i3 >= 0) {
            i = i2;
        }
        this.s = scheduledThreadPoolExecutor.scheduleAtFixedRate(anonymousClass2, (long) ((((i * 24) + i3) * 60) * 60), (long) 604800, TimeUnit.SECONDS);
    }

    private void k() {
        try {
            this.n.acquire();
            if (this.p != 0 || this.o.a() <= 0) {
                this.n.release();
                return;
            }
            this.p = 1;
            ServerRequest c = this.o.c();
            this.n.release();
            if (c == null) {
                this.o.b(null);
            } else if (c.l()) {
                this.p = 0;
            } else if (!(c instanceof ab) && !r()) {
                Log.i("BranchSDK", "Branch Error: User session has not been initialized!");
                this.p = 0;
                a(this.o.a() - 1, -101);
            } else if ((c instanceof w) || (p() && q())) {
                new d(c).a(new Void[0]);
            } else {
                this.p = 0;
                a(this.o.a() - 1, -101);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(int i, int i2) {
        ServerRequest a;
        if (i >= this.o.a()) {
            a = this.o.a(this.o.a() - 1);
        } else {
            a = this.o.a(i);
        }
        a(a, i2);
    }

    private void a(ServerRequest serverRequest, int i) {
        if (serverRequest != null) {
            serverRequest.a(i, "");
        }
    }

    private void l() {
        int i = 0;
        while (i < this.o.a()) {
            try {
                ServerRequest a = this.o.a(i);
                JSONObject f = a.f();
                if (f != null) {
                    if (f.has(Jsonkey.SessionID.getKey())) {
                        a.f().put(Jsonkey.SessionID.getKey(), this.h.i());
                    }
                    if (f.has(Jsonkey.IdentityID.getKey())) {
                        a.f().put(Jsonkey.IdentityID.getKey(), this.h.j());
                    }
                    if (f.has(Jsonkey.DeviceFingerprintID.getKey())) {
                        a.f().put(Jsonkey.DeviceFingerprintID.getKey(), this.h.h());
                    }
                }
                i++;
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
    }

    private void m() {
        if (this.l != null) {
            this.l.cancel();
            this.l.purge();
            this.l = new Timer();
        }
    }

    private void n() {
        if (this.k != null) {
            this.k.cancel();
            this.k.purge();
            this.k = new Timer();
        }
    }

    private void o() {
        this.m = true;
        synchronized (this.a) {
            n();
            this.k.schedule(new TimerTask() {
                public void run() {
                    new Thread(new Runnable() {
                        public void run() {
                            Branch.this.m = false;
                        }
                    }).start();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    private boolean p() {
        return this.h.i().equals("bnc_no_value") ^ 1;
    }

    private boolean q() {
        return this.h.h().equals("bnc_no_value") ^ 1;
    }

    private boolean r() {
        return this.h.j().equals("bnc_no_value") ^ 1;
    }

    private void b(ServerRequest serverRequest) {
        if (this.p == 0) {
            this.o.a(serverRequest, 0);
        } else {
            this.o.a(serverRequest, 1);
        }
    }

    private void a(ServerRequest serverRequest, e eVar) {
        if (this.o.f()) {
            if (eVar != null) {
                this.o.a(eVar);
            }
            this.o.a(serverRequest, this.p, eVar);
        } else {
            b(serverRequest);
        }
        k();
    }

    private void a(e eVar) {
        if ((this.h.g() == null || this.h.g().equalsIgnoreCase("bnc_no_value")) && (this.h.f() == null || this.h.f().equalsIgnoreCase("bnc_no_value"))) {
            this.v = SESSION_STATE.UNINITIALISED;
            if (eVar != null) {
                eVar.onInitFinished(null, new e("Trouble initializing Branch.", -1234));
            }
            Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.h.g() != null && this.h.g().startsWith("key_test_")) {
            Log.i("BranchSDK", "Branch Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (!this.h.n().equals("bnc_no_value") || !this.e) {
            a(eVar, null);
        } else if (j.a(this.j, new io.branch.referral.j.a() {
            public void a(String str) {
                Branch.this.h.a(Boolean.valueOf(true));
                if (str != null) {
                    str = Uri.parse(str).getQueryParameter(Jsonkey.LinkClickID.getKey());
                    if (!TextUtils.isEmpty(str)) {
                        Branch.this.h.j(str);
                    }
                }
                Branch.this.o.a(PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
                Branch.this.k();
            }
        }).booleanValue()) {
            a(eVar, PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            a(eVar, null);
        }
    }

    private void a(e eVar, PROCESS_WAIT_LOCK process_wait_lock) {
        ServerRequest acVar;
        if (r()) {
            acVar = new ac(this.j, eVar, this.g.a());
        } else {
            acVar = new ab(this.j, eVar, this.g.a(), InstallListener.a());
        }
        acVar.a(process_wait_lock);
        if (this.A) {
            acVar.a(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        a(acVar, eVar);
    }

    public void a(ServerRequest serverRequest) {
        if (!(this.v == SESSION_STATE.INITIALISED || (serverRequest instanceof w))) {
            if (serverRequest instanceof x) {
                serverRequest.a(-101, "");
                Log.i("BranchSDK", "Branch is not initialized, cannot logout");
                return;
            } else if (serverRequest instanceof aa) {
                Log.i("BranchSDK", "Branch is not initialized, cannot close session");
                return;
            } else {
                Activity activity = this.b != null ? (Activity) this.b.get() : null;
                boolean z = true;
                if (x == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    a((e) null, activity, true);
                } else {
                    if (x != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                        z = false;
                    }
                    a((e) null, activity, z);
                }
            }
        }
        this.o.a(serverRequest);
        serverRequest.j();
        k();
    }

    @TargetApi(14)
    private void a(Application application) {
        try {
            a aVar = new a(this, null);
            application.unregisterActivityLifecycleCallbacks(aVar);
            application.registerActivityLifecycleCallbacks(aVar);
            u = true;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            u = false;
            t = false;
            Log.w("BranchSDK", new e("", -108).a());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:? A:{SYNTHETIC, Splitter:B:1:0x0005, ExcHandler: NameNotFoundException (unused android.content.pm.PackageManager$NameNotFoundException)} */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A:{SYNTHETIC, Splitter:B:1:0x0005, ExcHandler: JSONException (unused org.json.JSONException)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:52:0x0104, code skipped:
            android.util.Log.i("BranchSDK", "Branch Warning: Please make sure Activity names set for auto deep link are correct!");
     */
    private void s() {
        /*
        r9 = this;
        r0 = r9.f();
        r1 = 0;
        r2 = io.branch.referral.Defines.Jsonkey.Clicked_Branch_Link;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.getKey();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r0.has(r2);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 == 0) goto L_0x00ec;
    L_0x0011:
        r2 = io.branch.referral.Defines.Jsonkey.Clicked_Branch_Link;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.getKey();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r0.getBoolean(r2);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 != 0) goto L_0x001f;
    L_0x001d:
        goto L_0x00ec;
    L_0x001f:
        r2 = r0.length();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 <= 0) goto L_0x010b;
    L_0x0025:
        r2 = r9.j;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = r9.j;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = r3.getPackageName();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r2 = r2.getApplicationInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = r2.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r4 = 0;
        if (r3 == 0) goto L_0x0047;
    L_0x003c:
        r2 = r2.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = "io.branch.sdk.auto_link_disable";
        r2 = r2.getBoolean(r3, r4);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 == 0) goto L_0x0047;
    L_0x0046:
        return;
    L_0x0047:
        r2 = r9.j;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = r9.j;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = r3.getPackageName();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        r2 = r2.getPackageInfo(r3, r5);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.activities;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r3 = 1501; // 0x5dd float:2.103E-42 double:7.416E-321;
        if (r2 == 0) goto L_0x009b;
    L_0x005f:
        r5 = r2.length;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
    L_0x0060:
        if (r4 >= r5) goto L_0x009b;
    L_0x0062:
        r6 = r2[r4];	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r6 == 0) goto L_0x0098;
    L_0x0066:
        r7 = r6.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r7 == 0) goto L_0x0098;
    L_0x006a:
        r7 = r6.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r8 = "io.branch.sdk.auto_link_keys";
        r7 = r7.getString(r8);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r7 != 0) goto L_0x007e;
    L_0x0074:
        r7 = r6.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r8 = "io.branch.sdk.auto_link_path";
        r7 = r7.getString(r8);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r7 == 0) goto L_0x0098;
    L_0x007e:
        r7 = r9.a(r0, r6);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r7 != 0) goto L_0x008a;
    L_0x0084:
        r7 = r9.b(r0, r6);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r7 == 0) goto L_0x0098;
    L_0x008a:
        r2 = r6.name;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r1 = r6.metaData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x0096, JSONException -> 0x010b }
        r4 = "io.branch.sdk.auto_link_request_code";
        r3 = r1.getInt(r4, r3);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x0096, JSONException -> 0x010b }
        r1 = r2;
        goto L_0x009b;
    L_0x0096:
        r1 = r2;
        goto L_0x00ed;
    L_0x0098:
        r4 = r4 + 1;
        goto L_0x0060;
    L_0x009b:
        if (r1 == 0) goto L_0x010b;
    L_0x009d:
        r2 = r9.b;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 == 0) goto L_0x010b;
    L_0x00a1:
        r2 = r9.b;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = r2.get();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r2 = (android.app.Activity) r2;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r2 == 0) goto L_0x00e4;
    L_0x00ab:
        r4 = new android.content.Intent;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = java.lang.Class.forName(r1);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r4.<init>(r2, r5);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = "io.branch.sdk.auto_linked";
        r6 = "true";
        r4.putExtra(r5, r6);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = io.branch.referral.Defines.Jsonkey.ReferringData;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = r5.getKey();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r6 = r0.toString();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r4.putExtra(r5, r6);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r5 = r0.keys();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
    L_0x00cc:
        r6 = r5.hasNext();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        if (r6 == 0) goto L_0x00e0;
    L_0x00d2:
        r6 = r5.next();	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r6 = (java.lang.String) r6;	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r7 = r0.getString(r6);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        r4.putExtra(r6, r7);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        goto L_0x00cc;
    L_0x00e0:
        r2.startActivityForResult(r4, r3);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        goto L_0x010b;
    L_0x00e4:
        r0 = "BranchSDK";
        r2 = "No activity reference to launch deep linked activity";
        android.util.Log.w(r0, r2);	 Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, JSONException -> 0x010b }
        goto L_0x010b;
    L_0x00ec:
        return;
    L_0x00ed:
        r0 = "BranchSDK";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Branch Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity ";
        r2.append(r3);
        r2.append(r1);
        r1 = r2.toString();
        android.util.Log.i(r0, r1);
        goto L_0x010b;
    L_0x0104:
        r0 = "BranchSDK";
        r1 = "Branch Warning: Please make sure Activity names set for auto deep link are correct!";
        android.util.Log.i(r0, r1);
    L_0x010b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.s():void");
    }

    private boolean a(JSONObject jSONObject, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") != null) {
            for (String has : activityInfo.metaData.getString("io.branch.sdk.auto_link_keys").split(",")) {
                if (jSONObject.has(has)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004f  */
    private boolean b(org.json.JSONObject r5, android.content.pm.ActivityInfo r6) {
        /*
        r4 = this;
        r0 = 0;
        r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath;	 Catch:{ JSONException -> 0x0030 }
        r1 = r1.getKey();	 Catch:{ JSONException -> 0x0030 }
        r1 = r5.has(r1);	 Catch:{ JSONException -> 0x0030 }
        if (r1 == 0) goto L_0x0019;
    L_0x000d:
        r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath;	 Catch:{ JSONException -> 0x0030 }
        r1 = r1.getKey();	 Catch:{ JSONException -> 0x0030 }
        r5 = r5.getString(r1);	 Catch:{ JSONException -> 0x0030 }
    L_0x0017:
        r0 = r5;
        goto L_0x0030;
    L_0x0019:
        r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath;	 Catch:{ JSONException -> 0x0030 }
        r1 = r1.getKey();	 Catch:{ JSONException -> 0x0030 }
        r1 = r5.has(r1);	 Catch:{ JSONException -> 0x0030 }
        if (r1 == 0) goto L_0x0030;
    L_0x0025:
        r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath;	 Catch:{ JSONException -> 0x0030 }
        r1 = r1.getKey();	 Catch:{ JSONException -> 0x0030 }
        r5 = r5.getString(r1);	 Catch:{ JSONException -> 0x0030 }
        goto L_0x0017;
    L_0x0030:
        r5 = r6.metaData;
        r1 = "io.branch.sdk.auto_link_path";
        r5 = r5.getString(r1);
        r1 = 0;
        if (r5 == 0) goto L_0x0060;
    L_0x003b:
        if (r0 == 0) goto L_0x0060;
    L_0x003d:
        r5 = r6.metaData;
        r6 = "io.branch.sdk.auto_link_path";
        r5 = r5.getString(r6);
        r6 = ",";
        r5 = r5.split(r6);
        r6 = r5.length;
        r2 = r1;
    L_0x004d:
        if (r2 >= r6) goto L_0x0060;
    L_0x004f:
        r3 = r5[r2];
        r3 = r3.trim();
        r3 = r4.e(r3, r0);
        if (r3 == 0) goto L_0x005d;
    L_0x005b:
        r5 = 1;
        return r5;
    L_0x005d:
        r2 = r2 + 1;
        goto L_0x004d;
    L_0x0060:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.b(org.json.JSONObject, android.content.pm.ActivityInfo):boolean");
    }

    private boolean e(String str, String str2) {
        boolean z = false;
        String[] split = str.split("\\?")[0].split("/");
        String[] split2 = str2.split("\\?")[0].split("/");
        if (split.length != split2.length) {
            return false;
        }
        int i = 0;
        while (i < split.length && i < split2.length) {
            String str3 = split[i];
            if (!str3.equals(split2[i]) && !str3.contains(CBConstant.DEFAULT_PAYMENT_URLS)) {
                break;
            }
            i++;
        }
        z = true;
        return z;
    }

    public void a(String str, String str2) {
        this.z.put(str, str2);
    }

    public void c(String str, String str2) {
        if (w.a(str)) {
            s();
        }
    }

    public void d(String str, String str2) {
        if (w.a(str)) {
            s();
        }
    }

    public void a(int i, String str, String str2) {
        if (w.a(str2)) {
            s();
        }
    }
}

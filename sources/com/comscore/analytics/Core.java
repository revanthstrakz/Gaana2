package com.comscore.analytics;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.comscore.applications.ApplicationMeasurement;
import com.comscore.applications.EventType;
import com.comscore.applications.KeepAlive;
import com.comscore.measurement.Measurement;
import com.comscore.measurement.MeasurementDispatcher;
import com.comscore.utils.CSLog;
import com.comscore.utils.CacheFlusher;
import com.comscore.utils.ConnectivityChangeReceiver;
import com.comscore.utils.Constants;
import com.comscore.utils.CustomExceptionHandler;
import com.comscore.utils.Date;
import com.comscore.utils.DispatchQueue;
import com.comscore.utils.OfflineMeasurementsCache;
import com.comscore.utils.Storage;
import com.comscore.utils.TransmissionMode;
import com.comscore.utils.Utils;
import com.comscore.utils.id.IdHelper;
import com.comscore.utils.task.TaskExecutor;
import com.til.colombia.android.internal.d;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class Core {
    protected static final long x = 300;
    protected AtomicInteger A = new AtomicInteger(0);
    protected AtomicInteger B = new AtomicInteger(0);
    protected long C;
    protected long D;
    protected long E;
    protected long F;
    protected long G;
    protected long H;
    protected long I;
    protected long J;
    protected long K;
    protected SessionState L = SessionState.INACTIVE;
    protected long M;
    protected long N;
    protected long O;
    protected int P;
    protected int Q;
    protected int R;
    protected long S;
    protected long T;
    protected long U;
    protected int V;
    protected long W;
    protected long X;
    protected Runnable Y;
    protected String Z;
    OfflineMeasurementsCache a;
    String aa;
    Context ab;
    protected final HashMap<String, String> ac = new HashMap();
    protected final HashMap<String, String> ad = new HashMap();
    boolean ae = true;
    protected long af = 0;
    protected boolean ag = false;
    protected UncaughtExceptionHandler ah = Thread.getDefaultUncaughtExceptionHandler();
    boolean ai;
    TransmissionMode aj;
    TransmissionMode ak;
    String[] al;
    private IdHelper am;
    private boolean an = true;
    private boolean ao;
    Storage b;
    KeepAlive c;
    CacheFlusher d;
    @Deprecated
    DispatchQueue e;
    TaskExecutor f;
    MeasurementDispatcher g;
    ConnectivityChangeReceiver h;
    protected Runnable i;
    protected Runnable j;
    protected long k;
    protected boolean l = true;
    protected boolean m = true;
    boolean n = false;
    AtomicInteger o = new AtomicInteger();
    long p;
    AtomicInteger q = new AtomicInteger(0);
    long r;
    long s;
    String t;
    String u;
    boolean v = true;
    String w;
    protected ApplicationState y = ApplicationState.INACTIVE;
    protected AtomicInteger z = new AtomicInteger(0);

    public class UserInteractionTask implements Runnable {
        public void run() {
            if (Core.this.an && Core.this.Y != null) {
                Core.this.f.removeEnqueuedTask(Core.this.Y);
                Core.this.Y = null;
                Core.this.n();
            }
        }
    }

    public Core() {
        reset();
    }

    private void A() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Constants.LAST_APPLICATION_ACCUMULATION_TIMESTAMP_KEY);
        arrayList.add(Constants.LAST_SESSION_ACCUMULATION_TIMESTAMP_KEY);
        a("lastActivityTime", arrayList);
        a("ns_ap_fg", Constants.FOREGROUND_TRANSITION_COUNT_KEY);
        a("installTime", Constants.INSTALL_ID_KEY);
        a("ns_ap_ver", Constants.PREVIOUS_VERSION_KEY);
    }

    private String a(String str, Properties properties, boolean z) {
        if (properties != null) {
            String property = properties.getProperty(str);
            if (property != null) {
                this.b.set(str, property);
                return property;
            }
        }
        return (z && this.b.has(str).booleanValue()) ? this.b.get(str) : null;
    }

    private void a(TransmissionMode transmissionMode) {
        if (this.an) {
            this.aj = transmissionMode;
        }
    }

    private void b(TransmissionMode transmissionMode) {
        if (this.an) {
            this.ak = transmissionMode;
        }
    }

    private void b(String str) {
        if (this.an && this.am != null) {
            this.am.setPublisherSecret(str);
            this.am.generateIds();
        }
    }

    private void b(String str, String str2) {
        if (this.an) {
            this.ac.put(str, str2);
        }
    }

    private void c(String str) {
        if (this.an) {
            this.aa = str;
            if (this.b != null) {
                this.b.set(Storage.APP_NAME_KEY, this.aa);
            }
        }
    }

    private void d(String str) {
        if (this.an) {
            a(isSecure() ? Constants.CENSUS_URL_SECURE : Constants.CENSUS_URL);
            b("c2", str);
        }
    }

    @Deprecated
    public static Core getInstance() {
        return comScore.getCore();
    }

    /* Access modifiers changed, original: protected */
    public Measurement a(EventType eventType, HashMap<String, String> hashMap, String str) {
        return ApplicationMeasurement.newApplicationMeasurement(this, eventType, hashMap, str);
    }

    /* Access modifiers changed, original: protected */
    public IdHelper a(Context context, Storage storage) {
        return new IdHelper(context, storage, this);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        this.b = b();
        this.g = e();
        a(this.b);
        this.e = c();
        this.c = f();
        this.a = g();
        this.d = h();
        this.h = i();
        j();
        this.am = a(this.ab, this.b);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, boolean z) {
        if (this.an) {
            w();
            if (i < 60) {
                i = 60;
            }
            this.l = z;
            this.k = (long) (i * 1000);
            if (this.y == ApplicationState.FOREGROUND || (this.y == ApplicationState.BACKGROUND_UX_ACTIVE && !this.l)) {
                v();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ApplicationState applicationState) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Leaving application state: ");
        stringBuilder.append(applicationState);
        CSLog.d((Object) this, stringBuilder.toString());
        switch (ab.a[applicationState.ordinal()]) {
            case 1:
                this.h.start();
                this.c.start(3000);
                this.ab.registerReceiver(this.h, new IntentFilter(d.a));
                this.d.start();
                return;
            case 2:
                break;
            case 3:
                setCurrentActivityName(null);
                break;
            default:
                return;
        }
        w();
    }

    /* Access modifiers changed, original: protected */
    public void a(ApplicationState applicationState, ApplicationState applicationState2) {
        if (this.an && applicationState2 != ApplicationState.INACTIVE && isAutoStartEnabled() && !this.n) {
            notify(EventType.START, this.ad, false);
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:14:0x004a, code skipped:
            r8.T = r0;
     */
    /* JADX WARNING: Missing block: B:15:0x004c, code skipped:
            r8.S = r0;
     */
    /* JADX WARNING: Missing block: B:16:0x004e, code skipped:
            return;
     */
    public void a(com.comscore.analytics.SessionState r9) {
        /*
        r8 = this;
        r0 = r8.an;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Leaving session state: ";
        r0.append(r1);
        r0.append(r9);
        r0 = r0.toString();
        com.comscore.utils.CSLog.d(r8, r0);
        r0 = com.comscore.utils.Date.unixTime();
        r2 = com.comscore.analytics.ab.b;
        r9 = r9.ordinal();
        r9 = r2[r9];
        switch(r9) {
            case 1: goto L_0x003a;
            case 2: goto L_0x004a;
            case 3: goto L_0x004c;
            case 4: goto L_0x0029;
            default: goto L_0x0028;
        };
    L_0x0028:
        return;
    L_0x0029:
        r9 = r8.p();
        if (r9 != 0) goto L_0x004e;
    L_0x002f:
        r2 = r8.M;
        r4 = r8.X;
        r6 = r0 - r4;
        r0 = r2 + r6;
        r8.M = r0;
        return;
    L_0x003a:
        r9 = r8.Y;
        if (r9 == 0) goto L_0x0048;
    L_0x003e:
        r9 = r8.f;
        r2 = r8.Y;
        r9.removeEnqueuedTask(r2);
        r9 = 0;
        r8.Y = r9;
    L_0x0048:
        r8.U = r0;
    L_0x004a:
        r8.T = r0;
    L_0x004c:
        r8.S = r0;
    L_0x004e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.analytics.Core.a(com.comscore.analytics.SessionState):void");
    }

    /* Access modifiers changed, original: protected */
    public void a(SessionState sessionState, SessionState sessionState2) {
    }

    /* Access modifiers changed, original: 0000 */
    public void a(EventType eventType, HashMap<String, String> hashMap) {
        if (!this.an) {
            return;
        }
        if (z()) {
            x();
            return;
        }
        y();
        if (!(this.n || eventType == EventType.START)) {
            this.g.sendMeasurmement(a(EventType.START, new HashMap(), this.Z), false);
        }
        if (eventType != EventType.CLOSE) {
            this.g.sendMeasurmement(a(eventType, Utils.mapOfStrings(hashMap), this.Z), false);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(Storage storage) {
        A();
        this.g.loadEventData();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        if (this.an) {
            int indexOf = str.indexOf(63);
            if (indexOf < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append('?');
                str = stringBuilder.toString();
            } else if (indexOf < str.length() - 1) {
                indexOf++;
                for (String split : str.substring(indexOf).split("&")) {
                    String split2;
                    String str2;
                    String[] split3 = split2.split("=");
                    if (split3.length == 2) {
                        str2 = split3[0];
                        split2 = split3[1];
                    } else if (split3.length == 1) {
                        str2 = "name";
                        split2 = split3[0];
                    } else {
                    }
                    setLabel(str2, split2, false);
                }
                str = str.substring(0, indexOf);
            }
            this.Z = str;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        a(str, arrayList);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, ArrayList<String> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            String str3 = this.b.get(str);
            String str4 = this.b.get(str2);
            if (Utils.isNotEmpty(str3) && Utils.isEmpty(str4)) {
                this.b.set(str2, str3);
            }
        }
        this.b.remove(str);
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        if (this.an) {
            long unixTime = Date.unixTime();
            long j = unixTime - this.K;
            switch (ab.a[this.y.ordinal()]) {
                case 1:
                    this.H += j;
                    this.E += j;
                    break;
                case 2:
                    this.F += j;
                    this.D += j;
                    break;
                case 3:
                    this.G += j;
                    this.C += j;
                    break;
            }
            this.K = unixTime;
            if (z) {
                this.b.set(Constants.LAST_APPLICATION_ACCUMULATION_TIMESTAMP_KEY, Long.toString(this.K));
                this.b.set(Constants.FOREGROUND_TRANSITION_COUNT_KEY, Long.toString((long) this.B.get()));
                this.b.set(Constants.ACCUMULATED_FOREGROUND_TIME_KEY, Long.toString(this.G));
                this.b.set(Constants.ACCUMULATED_BACKGROUND_TIME_KEY, Long.toString(this.F));
                this.b.set(Constants.ACCUMULATED_INACTIVE_TIME_KEY, Long.toString(this.H));
                this.b.set(Constants.TOTAL_FOREGROUND_TIME_KEY, Long.toString(this.C));
                this.b.set(Constants.TOTAL_BACKGROUND_TIME_KEY, Long.toString(this.D));
                this.b.set(Constants.TOTAL_INACTIVE_TIME_KEY, Long.toString(this.E));
            }
        }
    }

    public void allowLiveTransmission(TransmissionMode transmissionMode, boolean z) {
        if (!this.an || transmissionMode == null) {
            return;
        }
        if (z) {
            if (!(this.f == null || getLiveTransmissionMode() == transmissionMode)) {
                this.f.execute(new i(this, transmissionMode), z);
            }
            return;
        }
        a(transmissionMode);
    }

    public void allowOfflineTransmission(TransmissionMode transmissionMode, boolean z) {
        if (!this.an || transmissionMode == null) {
            return;
        }
        if (z) {
            if (!(this.f == null || getOfflineTransmissionMode() == transmissionMode)) {
                this.f.execute(new j(this, transmissionMode), z);
            }
            return;
        }
        b(transmissionMode);
    }

    /* Access modifiers changed, original: protected */
    public Storage b() {
        return new Storage(this.ab);
    }

    /* Access modifiers changed, original: protected */
    public void b(ApplicationState applicationState) {
        if (this.an) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Entering application state: ");
            stringBuilder.append(applicationState);
            CSLog.d((Object) this, stringBuilder.toString());
            switch (ab.a[applicationState.ordinal()]) {
                case 1:
                    this.h.stop();
                    this.c.stop();
                    this.d.stop();
                    try {
                        this.ab.unregisterReceiver(this.h);
                    } catch (IllegalArgumentException unused) {
                    }
                    w();
                    break;
                case 2:
                    if (!this.l) {
                        v();
                        return;
                    }
                    break;
                case 3:
                    v();
                    this.B.getAndIncrement();
                    return;
                default:
                    return;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(SessionState sessionState) {
        if (this.an) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Entering session state: ");
            stringBuilder.append(sessionState);
            CSLog.d((Object) this, stringBuilder.toString());
            switch (ab.b[sessionState.ordinal()]) {
                case 1:
                    q();
                    o();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    return;
            }
            r();
            p();
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:6:0x0023, code skipped:
            r8.N += r4;
            r8.T = r0;
     */
    /* JADX WARNING: Missing block: B:7:0x002b, code skipped:
            r8.M += r4;
            r8.S = r0;
     */
    /* JADX WARNING: Missing block: B:8:0x0033, code skipped:
            r8.X = r0;
     */
    /* JADX WARNING: Missing block: B:9:0x0035, code skipped:
            if (r9 == false) goto L_0x00ef;
     */
    /* JADX WARNING: Missing block: B:10:0x0037, code skipped:
            r8.b.set(com.comscore.utils.Constants.LAST_SESSION_ACCUMULATION_TIMESTAMP_KEY, java.lang.Long.toString(r8.X));
            r8.b.set(com.comscore.utils.Constants.LAST_APPLICATION_SESSION_TIMESTAMP_KEY, java.lang.Long.toString(r8.S));
            r8.b.set(com.comscore.utils.Constants.LAST_USER_SESSION_TIMESTAMP_KEY, java.lang.Long.toString(r8.T));
            r8.b.set(com.comscore.utils.Constants.LAST_ACTIVE_USER_SESSION_TIMESTAMP_KEY, java.lang.Long.toString(r8.U));
            r8.b.set(com.comscore.utils.Constants.ACCUMULATED_APPLICATION_SESSION_TIME_KEY, java.lang.Long.toString(r8.M));
            r8.b.set(com.comscore.utils.Constants.ACCUMULATED_ACTIVE_USER_SESSION_TIME_KEY, java.lang.Long.toString(r8.O));
            r8.b.set(com.comscore.utils.Constants.ACCUMULATED_USER_SESSION_TIME_KEY, java.lang.Long.toString(r8.N));
            r8.b.set(com.comscore.utils.Constants.ACTIVE_USER_SESSION_COUNT_KEY, java.lang.Long.toString((long) r8.R));
            r8.b.set(com.comscore.utils.Constants.USER_SESSION_COUNT_KEY, java.lang.Long.toString((long) r8.Q));
            r8.b.set(com.comscore.utils.Constants.LAST_USER_INTERACTION_TIMESTAMP_KEY, java.lang.Long.toString(r8.W));
            r8.b.set(com.comscore.utils.Constants.USER_INTERACTION_COUNT_KEY, java.lang.Integer.toString(r8.V));
            r8.b.set(com.comscore.utils.Constants.PREVIOUS_GENESIS_KEY, java.lang.Long.toString(r8.J));
            r8.b.set(com.comscore.utils.Constants.GENESIS_KEY, java.lang.Long.toString(r8.I));
            r8.b.set(com.comscore.utils.Constants.APPLICATION_SESSION_COUNT_KEY, java.lang.Integer.toString(r8.P));
     */
    /* JADX WARNING: Missing block: B:11:0x00ef, code skipped:
            return;
     */
    public void b(boolean r9) {
        /*
        r8 = this;
        r0 = r8.an;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = com.comscore.utils.Date.unixTime();
        r2 = r8.X;
        r4 = r0 - r2;
        r2 = com.comscore.analytics.ab.b;
        r3 = r8.L;
        r3 = r3.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x001b;
            case 2: goto L_0x0023;
            case 3: goto L_0x002b;
            default: goto L_0x001a;
        };
    L_0x001a:
        goto L_0x0033;
    L_0x001b:
        r2 = r8.O;
        r6 = r2 + r4;
        r8.O = r6;
        r8.U = r0;
    L_0x0023:
        r2 = r8.N;
        r6 = r2 + r4;
        r8.N = r6;
        r8.T = r0;
    L_0x002b:
        r2 = r8.M;
        r6 = r2 + r4;
        r8.M = r6;
        r8.S = r0;
    L_0x0033:
        r8.X = r0;
        if (r9 == 0) goto L_0x00ef;
    L_0x0037:
        r9 = r8.b;
        r0 = "lastSessionAccumulationTimestamp";
        r1 = r8.X;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "lastApplicationSessionTimestamp";
        r1 = r8.S;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "lastUserSessionTimestamp";
        r1 = r8.T;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "lastActiveUserSessionTimestamp";
        r1 = r8.U;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "accumulatedApplicationSessionTime";
        r1 = r8.M;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "accumulatedActiveUserSessionTime";
        r1 = r8.O;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "accumulatedUserSessionTime";
        r1 = r8.N;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "activeUserSessionCount";
        r1 = r8.R;
        r1 = (long) r1;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "userSessionCount";
        r1 = r8.Q;
        r1 = (long) r1;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "lastUserInteractionTimestamp";
        r1 = r8.W;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "userInteractionCount";
        r1 = r8.V;
        r1 = java.lang.Integer.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "previousGenesis";
        r1 = r8.J;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "genesis";
        r1 = r8.I;
        r1 = java.lang.Long.toString(r1);
        r9.set(r0, r1);
        r9 = r8.b;
        r0 = "applicationSessionCountKey";
        r1 = r8.P;
        r1 = java.lang.Integer.toString(r1);
        r9.set(r0, r1);
    L_0x00ef:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.analytics.Core.b(boolean):void");
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public DispatchQueue c() {
        return new DispatchQueue(this);
    }

    /* Access modifiers changed, original: 0000 */
    public void c(boolean z) {
        this.ae = z;
    }

    /* Access modifiers changed, original: protected */
    public TaskExecutor d() {
        return new TaskExecutor(this);
    }

    /* Access modifiers changed, original: protected */
    public void d(boolean z) {
        if (this.an) {
            this.n = z;
        }
    }

    public void disableAutoUpdate() {
        if (this.an) {
            w();
            this.l = true;
            this.k = -1;
        }
    }

    /* Access modifiers changed, original: protected */
    public MeasurementDispatcher e() {
        return new MeasurementDispatcher(this);
    }

    public void enableAutoUpdate(int i, boolean z, boolean z2) {
        if (!this.an) {
            return;
        }
        if (!z2) {
            a(i, z);
        } else if (this.f != null) {
            this.f.execute(new x(this, i, z), z2);
        }
    }

    /* Access modifiers changed, original: protected */
    public KeepAlive f() {
        return new KeepAlive(this, MeasurementDispatcher.MILLIS_PER_DAY);
    }

    public void flush(boolean z) {
        if (this.an && this.f != null) {
            this.f.execute(new v(this), z);
        }
    }

    /* Access modifiers changed, original: protected */
    public OfflineMeasurementsCache g() {
        return new OfflineMeasurementsCache(this);
    }

    public int getActiveUserSessionCountDelta(boolean z) {
        int i;
        if (this.R >= 0) {
            i = this.R;
            if (z && this.an) {
                this.R = 0;
                this.b.set(Constants.ACTIVE_USER_SESSION_COUNT_KEY, Integer.toString(this.R));
                return i;
            }
        }
        i = -1;
        return i;
    }

    public long getActiveUserSessionTimeDelta(boolean z) {
        long j = this.O;
        if (z && this.an) {
            this.O = 0;
            this.b.set(Constants.ACCUMULATED_ACTIVE_USER_SESSION_TIME_KEY, Long.toString(this.O));
        }
        return j;
    }

    public Context getAppContext() {
        return this.ab;
    }

    public String getAppName() {
        if ((this.aa == null || this.aa.length() == 0) && this.ab != null) {
            String packageName = this.ab.getPackageName();
            PackageManager packageManager = this.ab.getPackageManager();
            try {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0));
                if (applicationLabel != null) {
                    setAppName(applicationLabel.toString(), false);
                }
            } catch (NameNotFoundException unused) {
                this.aa = this.b.get(Storage.APP_NAME_KEY);
            }
        }
        return this.aa;
    }

    public int getApplicationSessionCountDelta(boolean z) {
        int i = this.P;
        if (z && this.an) {
            this.P = 0;
            this.b.set(Constants.APPLICATION_SESSION_COUNT_KEY, Integer.toString(this.P));
        }
        return i;
    }

    public long getApplicationSessionTimeDelta(boolean z) {
        long j = this.M;
        if (z && this.an) {
            this.M = 0;
            this.b.set(Constants.ACCUMULATED_APPLICATION_SESSION_TIME_KEY, Long.toString(this.M));
        }
        return j;
    }

    public ApplicationState getApplicationState() {
        return this.y;
    }

    public String getAutoStartLabel(String str) {
        return (String) this.ad.get(str);
    }

    public HashMap<String, String> getAutoStartLabels() {
        return this.ad;
    }

    public long getAutoUpdateInterval() {
        return this.k;
    }

    public long getBackgroundTimeDelta(boolean z) {
        long j = this.F;
        if (z && this.an) {
            this.F = 0;
            this.b.set(Constants.ACCUMULATED_BACKGROUND_TIME_KEY, Long.toString(this.F));
        }
        return j;
    }

    public long getBackgroundTotalTime(boolean z) {
        long j = this.D;
        if (z && this.an) {
            this.D = 0;
            this.b.set(Constants.TOTAL_BACKGROUND_TIME_KEY, Long.toString(this.D));
        }
        return j;
    }

    public CacheFlusher getCacheFlusher() {
        return this.d;
    }

    public long getCacheFlushingInterval() {
        return this.af;
    }

    public int getCacheMaxBatchFiles() {
        return this.a != null ? this.a.getCacheMaxBatchFiles() : 100;
    }

    public int getCacheMaxFlushesInARow() {
        return this.a != null ? this.a.getCacheMaxPosts() : 10;
    }

    public int getCacheMaxMeasurements() {
        return this.a != null ? this.a.getCacheMaxMeasurements() : 2000;
    }

    public long getCacheMeasurementExpiry() {
        return this.a != null ? this.a.getCacheMeasurementExpiry() : 31;
    }

    public long getCacheMinutesToRetry() {
        return this.a != null ? this.a.getCacheWaitMinutes() : 30;
    }

    public int getColdStartCount() {
        return this.q.get();
    }

    public long getColdStartId() {
        return this.p;
    }

    public ConnectivityChangeReceiver getConnectivityReceiver() {
        return this.h;
    }

    public String getCrossPublisherId() {
        return this.am == null ? null : this.am.getCrossPublisherId();
    }

    public String getCurrentActivityName() {
        return this.w;
    }

    public String getCurrentVersion() {
        return this.t;
    }

    public String getCustomerC2() {
        return (String) getLabels().get("c2");
    }

    public boolean getErrorHandlingEnabled() {
        return this.ag;
    }

    public long getFirstInstallId() {
        return this.s;
    }

    public long getForegroundTimeDelta(boolean z) {
        long j = this.G;
        if (z && this.an) {
            this.G = 0;
            this.b.set(Constants.ACCUMULATED_FOREGROUND_TIME_KEY, Long.toString(this.G));
        }
        return j;
    }

    public long getForegroundTotalTime(boolean z) {
        long j = this.C;
        if (z && this.an) {
            this.C = 0;
            this.b.set(Constants.TOTAL_FOREGROUND_TIME_KEY, Long.toString(this.C));
        }
        return j;
    }

    public int getForegroundTransitionsCountDelta(boolean z) {
        int i = this.B.get();
        if (z && this.an) {
            this.B.set(0);
            this.b.set(Constants.FOREGROUND_TRANSITION_COUNT_KEY, Long.toString((long) this.B.get()));
        }
        return i;
    }

    public long getGenesis() {
        return this.I;
    }

    public IdHelper getIdHelper() {
        return this.am;
    }

    public long getInactiveTimeDelta(boolean z) {
        long j = this.H;
        if (z && this.an) {
            this.H = 0;
            this.b.set(Constants.ACCUMULATED_INACTIVE_TIME_KEY, Long.toString(this.H));
        }
        return j;
    }

    public long getInactiveTotalTime(boolean z) {
        long j = this.E;
        if (z && this.an) {
            this.E = 0;
            this.b.set(Constants.TOTAL_INACTIVE_TIME_KEY, Long.toString(this.E));
        }
        return j;
    }

    public long getInstallId() {
        return this.r;
    }

    public KeepAlive getKeepAlive() {
        return this.c;
    }

    public String getLabel(String str) {
        return (String) this.ac.get(str);
    }

    public HashMap<String, String> getLabels() {
        return this.ac;
    }

    public TransmissionMode getLiveTransmissionMode() {
        return this.aj;
    }

    public MeasurementDispatcher getMeasurementDispatcher() {
        return this.g;
    }

    public String[] getMeasurementLabelOrder() {
        return this.al;
    }

    public OfflineMeasurementsCache getOfflineCache() {
        return this.a;
    }

    public TransmissionMode getOfflineTransmissionMode() {
        return this.ak;
    }

    public String getPixelURL() {
        return this.Z;
    }

    public long getPreviousGenesis() {
        return this.J;
    }

    public String getPreviousVersion() {
        String str = this.u;
        if (this.u != null && this.u.length() > 0) {
            this.b.remove(Constants.PREVIOUS_VERSION_KEY);
            this.u = null;
        }
        return str;
    }

    public String getPublisherSecret() {
        return this.am == null ? "" : this.am.getPublisherSecret();
    }

    public DispatchQueue getQueue() {
        return this.e;
    }

    public int getRunsCount() {
        return this.o.get();
    }

    @Deprecated
    public String getSalt() {
        return getPublisherSecret();
    }

    public SessionState getSessionState() {
        return this.L;
    }

    public Storage getStorage() {
        return this.b;
    }

    public TaskExecutor getTaskExecutor() {
        return this.f;
    }

    public int getUserInteractionCount(boolean z) {
        int i = this.V;
        if (z && this.an) {
            this.V = 0;
            this.b.set(Constants.USER_INTERACTION_COUNT_KEY, Integer.toString(this.V));
        }
        return i;
    }

    public int getUserSessionCountDelta(boolean z) {
        int i;
        if (this.Q >= 0) {
            i = this.Q;
            if (z && this.an) {
                this.Q = 0;
                this.b.set(Constants.USER_SESSION_COUNT_KEY, Integer.toString(this.Q));
                return i;
            }
        }
        i = -1;
        return i;
    }

    public long getUserSessionTimeDelta(boolean z) {
        long j = this.N;
        if (z && this.an) {
            this.N = 0;
            this.b.set(Constants.ACCUMULATED_USER_SESSION_TIME_KEY, Long.toString(this.N));
        }
        return j;
    }

    public String getVersion() {
        return Constants.SDK_VERSION;
    }

    public String getVisitorId() {
        return this.am == null ? null : this.am.getVisitorId();
    }

    /* Access modifiers changed, original: protected */
    public CacheFlusher h() {
        return new CacheFlusher(this);
    }

    public boolean handleColdStart() {
        if (!this.an || this.n) {
            return false;
        }
        this.n = true;
        this.q.getAndIncrement();
        this.b.set(Constants.COLD_START_COUNT_KEY, String.valueOf(this.q));
        this.p = Date.unixTime();
        return true;
    }

    /* Access modifiers changed, original: protected */
    public ConnectivityChangeReceiver i() {
        return new ConnectivityChangeReceiver(this);
    }

    public void incrementRunsCount() {
        if (this.an) {
            this.o.getAndIncrement();
            this.b.set(Constants.RUNS_COUNT_KEY, Long.toString((long) this.o.get()));
        }
    }

    public boolean isAutoStartEnabled() {
        return this.v;
    }

    public boolean isAutoUpdateEnabled() {
        return this.k > 0;
    }

    public boolean isEnabled() {
        return this.an;
    }

    public boolean isKeepAliveEnabled() {
        return this.ae;
    }

    public boolean isSecure() {
        return this.ai;
    }

    /* Access modifiers changed, original: protected */
    public void j() {
        Storage storage;
        String str;
        String valueOf;
        this.K = Utils.getLong(this.b.get(Constants.LAST_APPLICATION_ACCUMULATION_TIMESTAMP_KEY), -1);
        this.X = Utils.getLong(this.b.get(Constants.LAST_SESSION_ACCUMULATION_TIMESTAMP_KEY), -1);
        this.S = Utils.getLong(this.b.get(Constants.LAST_APPLICATION_SESSION_TIMESTAMP_KEY), -1);
        this.T = Utils.getLong(this.b.get(Constants.LAST_USER_SESSION_TIMESTAMP_KEY), -1);
        this.U = Utils.getLong(this.b.get(Constants.LAST_ACTIVE_USER_SESSION_TIMESTAMP_KEY), -1);
        this.B.set(Utils.getInteger(this.b.get(Constants.FOREGROUND_TRANSITION_COUNT_KEY)));
        this.G = Utils.getLong(this.b.get(Constants.ACCUMULATED_FOREGROUND_TIME_KEY));
        this.F = Utils.getLong(this.b.get(Constants.ACCUMULATED_BACKGROUND_TIME_KEY));
        this.H = Utils.getLong(this.b.get(Constants.ACCUMULATED_INACTIVE_TIME_KEY));
        this.C = Utils.getLong(this.b.get(Constants.TOTAL_FOREGROUND_TIME_KEY));
        this.D = Utils.getLong(this.b.get(Constants.TOTAL_BACKGROUND_TIME_KEY));
        this.E = Utils.getLong(this.b.get(Constants.TOTAL_INACTIVE_TIME_KEY));
        this.M = Utils.getLong(this.b.get(Constants.ACCUMULATED_APPLICATION_SESSION_TIME_KEY));
        this.O = Utils.getLong(this.b.get(Constants.ACCUMULATED_ACTIVE_USER_SESSION_TIME_KEY));
        this.N = Utils.getLong(this.b.get(Constants.ACCUMULATED_USER_SESSION_TIME_KEY));
        this.R = Utils.getInteger(this.b.get(Constants.ACTIVE_USER_SESSION_COUNT_KEY), -1);
        this.Q = Utils.getInteger(this.b.get(Constants.USER_SESSION_COUNT_KEY), -1);
        this.W = Utils.getLong(this.b.get(Constants.LAST_USER_INTERACTION_TIMESTAMP_KEY), -1);
        this.V = Utils.getInteger(this.b.get(Constants.USER_INTERACTION_COUNT_KEY), 0);
        this.P = Utils.getInteger(this.b.get(Constants.APPLICATION_SESSION_COUNT_KEY), 0);
        this.t = k();
        this.J = Utils.getLong(this.b.get(Constants.PREVIOUS_GENESIS_KEY), 0);
        this.I = Utils.getLong(this.b.get(Constants.GENESIS_KEY), -1);
        if (this.I < 0) {
            this.I = Date.unixTime();
            this.J = 0;
            this.S = this.I;
            this.P++;
        } else {
            if (!p()) {
                this.M += Date.unixTime() - this.X;
                this.b.set(Constants.ACCUMULATED_APPLICATION_SESSION_TIME_KEY, Long.toString(this.M));
            }
            this.S = this.I;
        }
        this.s = Utils.getLong(this.b.get(Constants.FIRST_INSTALL_ID_KEY), -1);
        if (this.s < 0) {
            this.s = this.I;
            this.r = this.I;
            this.b.set(Constants.CURRENT_VERSION_KEY, this.t);
            this.b.set(Constants.FIRST_INSTALL_ID_KEY, String.valueOf(this.s));
            storage = this.b;
            str = Constants.INSTALL_ID_KEY;
            valueOf = String.valueOf(this.r);
        } else {
            if (this.b.has(Constants.PREVIOUS_VERSION_KEY).booleanValue()) {
                this.u = this.b.get(Constants.PREVIOUS_VERSION_KEY);
            }
            String str2 = this.b.get(Constants.CURRENT_VERSION_KEY);
            if (str2.equals(this.t)) {
                this.r = Utils.getLong(this.b.get(Constants.INSTALL_ID_KEY), -1);
            } else {
                this.u = str2;
                this.b.set(Constants.PREVIOUS_VERSION_KEY, this.u);
                this.r = this.I;
                this.b.set(Constants.INSTALL_ID_KEY, String.valueOf(this.r));
            }
            storage = this.b;
            str = Constants.CURRENT_VERSION_KEY;
            valueOf = this.t;
        }
        storage.set(str, valueOf);
        this.b.set(Constants.GENESIS_KEY, Long.toString(this.I));
        this.b.set(Constants.PREVIOUS_GENESIS_KEY, Long.toString(this.J));
        long unixTime = Date.unixTime();
        if (this.K >= 0) {
            long j = unixTime - this.K;
            this.H += j;
            this.b.set(Constants.ACCUMULATED_INACTIVE_TIME_KEY, Long.toString(this.H));
            this.E += j;
            this.b.set(Constants.TOTAL_INACTIVE_TIME_KEY, Long.toString(this.E));
        }
        this.K = unixTime;
        this.X = unixTime;
        this.b.set(Constants.LAST_APPLICATION_ACCUMULATION_TIMESTAMP_KEY, Long.toString(this.K));
        this.b.set(Constants.LAST_SESSION_ACCUMULATION_TIMESTAMP_KEY, Long.toString(this.X));
        this.b.set(Constants.LAST_APPLICATION_SESSION_TIMESTAMP_KEY, Long.toString(this.S));
        if (!this.b.has(Constants.RUNS_COUNT_KEY).booleanValue()) {
            this.b.set(Constants.RUNS_COUNT_KEY, "0");
        }
        this.o.set(Utils.getInteger(this.b.get(Constants.RUNS_COUNT_KEY)));
        this.q.set(Utils.getInteger(this.b.get(Constants.COLD_START_COUNT_KEY)));
    }

    /* Access modifiers changed, original: protected */
    public String k() {
        try {
            return this.ab.getPackageManager().getPackageInfo(this.ab.getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
            return "0";
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a1 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|23|24) */
    /* JADX WARNING: Missing block: B:55:0x0159, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:57:0x015c, code skipped:
            if (com.comscore.utils.Constants.DEBUG != false) goto L_0x015e;
     */
    /* JADX WARNING: Missing block: B:58:0x015e, code skipped:
            com.comscore.utils.CSLog.printStackTrace(r0);
     */
    public void l() {
        /*
        r9 = this;
        r0 = r9.ab;
        if (r0 == 0) goto L_0x0161;
    L_0x0004:
        r0 = r9.ab;
        r0 = r0.getResources();
        r0 = r0.getAssets();
        r1 = "comScore.properties";
        r0 = r0.open(r1);	 Catch:{ IOException -> 0x0159 }
        r1 = new java.util.Properties;	 Catch:{ IOException -> 0x0159 }
        r1.<init>();	 Catch:{ IOException -> 0x0159 }
        r1.load(r0);	 Catch:{ IOException -> 0x0159 }
        r0 = "Debug";
        r2 = 0;
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0);	 Catch:{ IOException -> 0x0159 }
        com.comscore.utils.Constants.DEBUG = r0;	 Catch:{ IOException -> 0x0159 }
        r0 = "Secure";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0);	 Catch:{ IOException -> 0x0159 }
        r9.ai = r0;	 Catch:{ IOException -> 0x0159 }
        r0 = "PublisherSecret";
        r3 = 1;
        r0 = r9.a(r0, r1, r3);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x0041;
    L_0x003e:
        r9.b(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x0041:
        r0 = "AppName";
        r0 = r9.a(r0, r1, r3);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x004c;
    L_0x0049:
        r9.c(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x004c:
        r0 = "CustomerC2";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x0057;
    L_0x0054:
        r9.d(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x0057:
        r0 = "PixelURL";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x0062;
    L_0x005f:
        r9.a(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x0062:
        r0 = "OfflineURL";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x006f;
    L_0x006a:
        r4 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r4.setUrl(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x006f:
        r0 = "LiveTransmissionMode";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x008a;
    L_0x0077:
        r4 = java.util.Locale.getDefault();	 Catch:{ IllegalArgumentException -> 0x0086 }
        r0 = r0.toUpperCase(r4);	 Catch:{ IllegalArgumentException -> 0x0086 }
        r0 = com.comscore.utils.TransmissionMode.valueOf(r0);	 Catch:{ IllegalArgumentException -> 0x0086 }
        r9.aj = r0;	 Catch:{ IllegalArgumentException -> 0x0086 }
        goto L_0x008a;
    L_0x0086:
        r0 = com.comscore.utils.TransmissionMode.DEFAULT;	 Catch:{ IOException -> 0x0159 }
        r9.aj = r0;	 Catch:{ IOException -> 0x0159 }
    L_0x008a:
        r0 = "OfflineTransmissionMode";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x00a5;
    L_0x0092:
        r4 = java.util.Locale.getDefault();	 Catch:{ IllegalArgumentException -> 0x00a1 }
        r0 = r0.toUpperCase(r4);	 Catch:{ IllegalArgumentException -> 0x00a1 }
        r0 = com.comscore.utils.TransmissionMode.valueOf(r0);	 Catch:{ IllegalArgumentException -> 0x00a1 }
        r9.ak = r0;	 Catch:{ IllegalArgumentException -> 0x00a1 }
        goto L_0x00a5;
    L_0x00a1:
        r0 = com.comscore.utils.TransmissionMode.DEFAULT;	 Catch:{ IOException -> 0x0159 }
        r9.ak = r0;	 Catch:{ IOException -> 0x0159 }
    L_0x00a5:
        r0 = "KeepAliveEnabled";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0, r3);	 Catch:{ IOException -> 0x0159 }
        r9.ae = r0;	 Catch:{ IOException -> 0x0159 }
        r0 = "CacheMaxSize";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r4 = -1;
        r0 = com.comscore.utils.Utils.getInteger(r0, r4);	 Catch:{ IOException -> 0x0159 }
        if (r0 < 0) goto L_0x00c3;
    L_0x00be:
        r5 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r5.setCacheMaxMeasurements(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x00c3:
        r0 = "CacheMaxBatchSize";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getInteger(r0, r4);	 Catch:{ IOException -> 0x0159 }
        if (r0 < 0) goto L_0x00d4;
    L_0x00cf:
        r5 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r5.setCacheMaxBatchFiles(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x00d4:
        r0 = "CacheMaxFlushesInARow";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getInteger(r0, r4);	 Catch:{ IOException -> 0x0159 }
        if (r0 < 0) goto L_0x00e5;
    L_0x00e0:
        r5 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r5.setCacheMaxPosts(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x00e5:
        r0 = "CacheMinutesToRetry";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getInteger(r0, r4);	 Catch:{ IOException -> 0x0159 }
        if (r0 < 0) goto L_0x00f6;
    L_0x00f1:
        r5 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r5.setCacheWaitMinutes(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x00f6:
        r0 = "CacheExpiryInDays";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getInteger(r0, r4);	 Catch:{ IOException -> 0x0159 }
        if (r0 < 0) goto L_0x0107;
    L_0x0102:
        r5 = r9.a;	 Catch:{ IOException -> 0x0159 }
        r5.setCacheMeasurementExpiry(r0);	 Catch:{ IOException -> 0x0159 }
    L_0x0107:
        r0 = "CacheFlushingInterval";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r5 = -1;
        r5 = com.comscore.utils.Utils.getLong(r0, r5);	 Catch:{ IOException -> 0x0159 }
        r7 = 0;
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r0 < 0) goto L_0x0124;
    L_0x0119:
        r9.af = r5;	 Catch:{ IOException -> 0x0159 }
        r0 = r9.d;	 Catch:{ IOException -> 0x0159 }
        if (r0 == 0) goto L_0x0124;
    L_0x011f:
        r0 = r9.d;	 Catch:{ IOException -> 0x0159 }
        r0.update();	 Catch:{ IOException -> 0x0159 }
    L_0x0124:
        r0 = "ErrorHandlingEnabled";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0);	 Catch:{ IOException -> 0x0159 }
        r9.setErrorHandlingEnabled(r0);	 Catch:{ IOException -> 0x0159 }
        r0 = "AutoStartEnabled";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0, r3);	 Catch:{ IOException -> 0x0159 }
        r9.v = r0;	 Catch:{ IOException -> 0x0159 }
        r0 = "AutoUpdateInForegroundOnly";
        r0 = r9.a(r0, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r0 = com.comscore.utils.Utils.getBoolean(r0, r3);	 Catch:{ IOException -> 0x0159 }
        r3 = "AutoUpdateInterval";
        r1 = r9.a(r3, r1, r2);	 Catch:{ IOException -> 0x0159 }
        r1 = com.comscore.utils.Utils.getInteger(r1, r4);	 Catch:{ IOException -> 0x0159 }
        r2 = 60;
        if (r1 < r2) goto L_0x0161;
    L_0x0155:
        r9.a(r1, r0);	 Catch:{ IOException -> 0x0159 }
        return;
    L_0x0159:
        r0 = move-exception;
        r1 = com.comscore.utils.Constants.DEBUG;
        if (r1 == 0) goto L_0x0161;
    L_0x015e:
        com.comscore.utils.CSLog.printStackTrace(r0);
    L_0x0161:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.analytics.Core.l():void");
    }

    /* Access modifiers changed, original: protected */
    public Context m() {
        return this.ab;
    }

    /* Access modifiers changed, original: protected */
    public void n() {
        if (this.an) {
            if (this.f.containsTask(this.j)) {
                this.f.removeEnqueuedTask(this.j);
                this.j = null;
            }
            long unixTime = Date.unixTime();
            ApplicationState applicationState = this.z.get() > 0 ? ApplicationState.FOREGROUND : this.A.get() > 0 ? ApplicationState.BACKGROUND_UX_ACTIVE : ApplicationState.INACTIVE;
            SessionState sessionState = unixTime - this.W < Constants.USER_SESSION_INACTIVE_PERIOD ? SessionState.ACTIVE_USER : this.A.get() > 0 ? SessionState.USER : this.z.get() > 0 ? SessionState.APPLICATION : SessionState.INACTIVE;
            SessionState sessionState2 = sessionState;
            ApplicationState applicationState2 = this.y;
            SessionState sessionState3 = this.L;
            if (!(applicationState == applicationState2 && sessionState2 == sessionState3)) {
                this.j = new af(this, applicationState2, applicationState, sessionState3, sessionState2);
                if (!this.m || applicationState == ApplicationState.FOREGROUND) {
                    this.j.run();
                    this.j = null;
                } else {
                    this.f.execute(this.j, 300);
                }
            }
        }
    }

    public void notify(EventType eventType, HashMap<String, String> hashMap, boolean z) {
        if (!this.an) {
            return;
        }
        if (!z) {
            a(eventType, (HashMap) hashMap);
        } else if (this.f != null) {
            this.f.execute(new z(this, eventType, hashMap), z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void o() {
        if (this.an) {
            if (this.Y != null) {
                this.f.removeEnqueuedTask(this.Y);
                this.Y = null;
            }
            this.Y = new UserInteractionTask();
            this.f.execute(this.Y, (long) Constants.USER_SESSION_INACTIVE_PERIOD);
        }
    }

    public void onEnterForeground() {
        if (this.an && this.f != null) {
            this.f.execute(new ac(this), true);
        }
    }

    public void onExitForeground() {
        if (this.an && this.f != null) {
            this.f.execute(new ad(this), true);
        }
    }

    public void onUserInteraction() {
        if (this.an && this.f != null) {
            this.f.execute(new ae(this), true);
        }
    }

    public void onUxActive() {
        if (this.an && this.f != null) {
            this.f.execute(new l(this), true);
        }
    }

    public void onUxInactive() {
        if (this.an && this.f != null) {
            this.f.execute(new w(this), true);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean p() {
        boolean z = false;
        if (!this.an) {
            return false;
        }
        long unixTime = Date.unixTime();
        if (unixTime - this.S > Constants.SESSION_INACTIVE_PERIOD) {
            this.J = this.I;
            this.I = unixTime;
            this.P++;
            z = true;
        }
        this.S = unixTime;
        return z;
    }

    /* Access modifiers changed, original: protected */
    public void q() {
        if (this.an) {
            long unixTime = Date.unixTime();
            if (unixTime - this.U >= Constants.USER_SESSION_INACTIVE_PERIOD) {
                this.R++;
            }
            this.U = unixTime;
        }
    }

    /* Access modifiers changed, original: protected */
    public void r() {
        if (this.an) {
            long unixTime = Date.unixTime();
            if (unixTime - this.T >= Constants.USER_SESSION_INACTIVE_PERIOD) {
                this.Q++;
            }
            this.T = unixTime;
        }
    }

    public void reset() {
        this.aj = TransmissionMode.DEFAULT;
        this.ak = TransmissionMode.DEFAULT;
        this.ai = false;
        this.al = Constants.LABELS_ORDER;
        this.y = ApplicationState.INACTIVE;
        this.L = SessionState.INACTIVE;
        this.n = false;
        this.o.set(0);
        this.p = -1;
        this.q.set(0);
        this.s = -1;
        this.r = -1;
        this.t = null;
        this.u = null;
        this.z.set(0);
        this.A.set(0);
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.M = 0;
        this.O = 0;
        this.N = 0;
        this.I = -1;
        this.J = 0;
        this.R = -1;
        this.Q = -1;
        this.V = 0;
        this.W = -1;
        this.K = -1;
        this.X = -1;
        this.S = -1;
        this.T = -1;
        this.U = -1;
        this.r = -1;
        this.s = -1;
        disableAutoUpdate();
        if (this.j != null) {
            this.f.removeEnqueuedTask(this.j);
            this.j = null;
        }
        if (this.Y != null) {
            this.f.removeEnqueuedTask(this.Y);
            this.Y = null;
        }
        if (this.c != null) {
            this.c.cancel();
        }
        if (this.d != null) {
            this.d.stop();
        }
        if (this.f != null) {
            this.f.removeAllEnqueuedTasks();
        }
        if (this.b != null) {
            this.b.close();
        }
    }

    public void resetVisitorId() {
        this.f.execute(new a(this), true);
    }

    /* Access modifiers changed, original: protected */
    public void s() {
        a(true);
    }

    public void setAppContext(Context context) {
        if (this.ab == null && context != null) {
            this.ab = context;
            this.f = d();
            this.f.execute(new ag(this), true);
        }
    }

    public void setAppName(String str, boolean z) {
        if (!this.an) {
            return;
        }
        if (!z) {
            c(str);
        } else if (this.f != null) {
            this.f.execute(new e(this, str), z);
        }
    }

    public void setAutoStartEnabled(boolean z, boolean z2) {
        if (this.an) {
            this.f.execute(new n(this, z), z2);
        }
    }

    public void setAutoStartLabel(String str, String str2) {
        if (this.an) {
            this.ad.put(str, str2);
        }
    }

    public void setAutoStartLabels(HashMap<String, String> hashMap) {
        if (this.an && hashMap != null) {
            this.ad.putAll(Utils.mapOfStrings(hashMap));
        }
    }

    public void setCacheFlushingInterval(long j, boolean z) {
        if (!(!this.an || this.f == null || this.af == j)) {
            this.f.execute(new t(this, j), z);
        }
    }

    public void setCacheMaxBatchFiles(int i, boolean z) {
        if (!(!this.an || this.f == null || this.a == null)) {
            this.f.execute(new p(this, i), z);
        }
    }

    public void setCacheMaxFlushesInARow(int i, boolean z) {
        if (!(!this.an || this.f == null || this.a == null)) {
            this.f.execute(new q(this, i), z);
        }
    }

    public void setCacheMaxMeasurements(int i, boolean z) {
        if (!(!this.an || this.f == null || this.a == null)) {
            this.f.execute(new o(this, i), z);
        }
    }

    public void setCacheMeasurementExpiry(int i, boolean z) {
        if (!(!this.an || this.f == null || this.a == null)) {
            this.f.execute(new s(this, i), z);
        }
    }

    public void setCacheMinutesToRetry(int i, boolean z) {
        if (!(!this.an || this.f == null || this.a == null)) {
            this.f.execute(new r(this, i), z);
        }
    }

    public void setCurrentActivityName(String str) {
        this.w = str;
    }

    public void setCustomerC2(String str, boolean z) {
        if (this.an && str != null && str.length() != 0) {
            if (!z) {
                d(str);
            } else if (this.f != null) {
                this.f.execute(new h(this, str), z);
            }
        }
    }

    public void setDebug(boolean z) {
        if (this.an) {
            this.f.execute(new m(this, z), true);
        }
    }

    public void setEnabled(boolean z) {
        this.f.execute(new aa(this, z), true);
    }

    public void setErrorHandlingEnabled(boolean z) {
        if (this.an) {
            UncaughtExceptionHandler customExceptionHandler;
            this.ag = z;
            if (z) {
                customExceptionHandler = new CustomExceptionHandler(this);
            } else if (Thread.getDefaultUncaughtExceptionHandler() != this.ah) {
                customExceptionHandler = this.ah;
            } else {
                return;
            }
            Thread.setDefaultUncaughtExceptionHandler(customExceptionHandler);
        }
    }

    public void setKeepAliveEnabled(boolean z, boolean z2) {
        if (!this.an) {
            return;
        }
        if (!z2) {
            c(z);
        } else if (this.f != null) {
            this.f.execute(new c(this, z), z2);
        }
    }

    public void setLabel(String str, String str2, boolean z) {
        if (!this.an) {
            return;
        }
        if (!z) {
            b(str, str2);
        } else if (this.f != null) {
            this.f.execute(new g(this, str, str2), z);
        }
    }

    public void setLabels(HashMap<String, String> hashMap, boolean z) {
        if (this.an && hashMap != null && this.f != null) {
            this.f.execute(new f(this, hashMap), z);
        }
    }

    public void setMeasurementLabelOrder(String[] strArr, boolean z) {
        if (!(!this.an || this.f == null || strArr == this.al || strArr == null || strArr.length <= 0)) {
            this.f.execute(new u(this, strArr), z);
        }
    }

    public void setOfflineURL(String str) {
        if (this.an && str != null && str.length() != 0 && this.f != null) {
            this.f.execute(new b(this, str), true);
        }
    }

    public void setPixelURL(String str, boolean z) {
        if (this.an && str != null && str.length() != 0) {
            if (!z) {
                a(str);
            } else if (this.f != null) {
                this.f.execute(new ah(this, str), z);
            }
        }
    }

    public void setPublisherSecret(String str, boolean z) {
        if (this.an && str != null && str.length() != 0 && this.f != null) {
            this.f.execute(new d(this, str), z);
        }
    }

    public void setSecure(boolean z, boolean z2) {
        if (!this.an) {
            return;
        }
        if (!z2) {
            this.ai = z;
        } else if (this.f != null) {
            this.f.execute(new k(this, z), z2);
        }
    }

    /* Access modifiers changed, original: protected */
    public void t() {
        b(true);
    }

    /* Access modifiers changed, original: protected */
    public OfflineMeasurementsCache u() {
        return this.a;
    }

    public void update() {
        update(true);
    }

    public void update(boolean z) {
        if (this.an) {
            if (this.f.containsTask(this.j)) {
                this.f.removeEnqueuedTask(this.j);
                this.j.run();
                this.j = null;
            }
            a(z);
            b(z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void v() {
        if (this.an) {
            w();
            if (this.k >= 60000) {
                this.i = new y(this);
                this.f.execute(this.i, this.k, true, this.k);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void w() {
        if (this.an && this.i != null) {
            this.f.removeEnqueuedTask(this.i);
            this.i = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void x() {
    }

    /* Access modifiers changed, original: protected */
    public void y() {
    }

    /* Access modifiers changed, original: 0000 */
    public boolean z() {
        return this.ab == null || this.am.isPublisherSecretEmpty() || this.Z == null || this.Z.length() == 0;
    }
}

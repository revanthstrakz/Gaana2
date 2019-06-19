package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.n;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.android.exoplayer2.C;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

public class jd implements com.google.ads.interactivemedia.v3.internal.je.a {
    private Map<String, d> a = new HashMap();
    private Map<String, a> b = new HashMap();
    private final Set<String> c = new HashSet();
    private Map<String, b> d = new HashMap();
    private Map<String, e> e = new HashMap();
    private Map<String, jo> f = new HashMap();
    private Map<String, BaseDisplayContainer> g = new HashMap();
    private final Context h;
    private final je i;
    private jf j;
    private boolean k = false;
    private final Queue<jc> l = new ArrayDeque();
    private long m;
    private TestingConfiguration n;
    private String o;
    private AdsRenderingSettings p;

    public interface a {
        void a(String str, String str2);

        void a(String str, String str2, String str3);
    }

    public interface b {
        void a(String str, AdErrorType adErrorType, int i, String str2);

        void a(String str, AdErrorType adErrorType, AdErrorCode adErrorCode, String str2);

        void a(String str, jf jfVar, String str2, boolean z);

        void a(String str, jf jfVar, List<Float> list, SortedSet<Float> sortedSet, boolean z);
    }

    public interface d {
        void a(AdErrorType adErrorType, int i, String str);

        void a(AdErrorType adErrorType, AdErrorCode adErrorCode, String str);

        void a(c cVar);

        void a(Map<String, CompanionData> map);
    }

    public static class c {
        public final AdEventType a;
        public final com.google.ads.interactivemedia.v3.impl.data.b b;
        public Map<String, String> c;
        public List<CuePoint> d;
        AdProgressInfo e;
        public String f;

        public c(AdEventType adEventType, com.google.ads.interactivemedia.v3.impl.data.b bVar) {
            this.a = adEventType;
            this.b = bVar;
        }

        public boolean equals(Object obj) {
            return lx.a((Object) this, obj, new String[0]);
        }

        public int hashCode() {
            return lz.a(this, new String[0]);
        }
    }

    public interface e {
        void a(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str);
    }

    /* Access modifiers changed, original: protected */
    public Uri a(Uri uri, ImaSdkSettings imaSdkSettings) {
        Builder appendQueryParameter = uri.buildUpon().appendQueryParameter("sdk_version", "a.3.10.2").appendQueryParameter("hl", imaSdkSettings.getLanguage()).appendQueryParameter("omv", jh.e()).appendQueryParameter("omvx", imaSdkSettings.getEnableOmidExperimentally() ? "1" : "0").appendQueryParameter("app", this.h.getApplicationContext().getPackageName());
        if (this.n != null) {
            appendQueryParameter.appendQueryParameter(TestingConfiguration.PARAMETER_KEY, new ga().a(new kk()).a(new kj()).a().a(this.n));
        }
        return appendQueryParameter.build();
    }

    public jd(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration) {
        this.h = context;
        this.n = testingConfiguration;
        this.i = new je(context, (com.google.ads.interactivemedia.v3.internal.je.a) this);
        this.o = a(uri, imaSdkSettings).toString();
    }

    public void a() {
        this.m = SystemClock.elapsedRealtime();
        this.i.a(this.o);
    }

    public void a(AdsRenderingSettings adsRenderingSettings) {
        this.p = adsRenderingSettings;
    }

    public WebView b() {
        return this.i.a();
    }

    /* Access modifiers changed, original: 0000 */
    public TestingConfiguration c() {
        return this.n;
    }

    public void a(jc jcVar) {
        p pVar = (p) jcVar.c();
        String d = jcVar.d();
        com.google.ads.interactivemedia.v3.internal.jc.b b = jcVar.b();
        switch (jcVar.a()) {
            case adsManager:
                f(b, d, pVar);
                return;
            case activityMonitor:
                g(b, d, pVar);
                return;
            case videoDisplay:
                e(b, d, pVar);
                return;
            case adsLoader:
                d(b, d, pVar);
                return;
            case displayContainer:
                b(b, d, pVar);
                return;
            case i18n:
                c(b, d, pVar);
                return;
            case omid:
                a(b);
                return;
            case webViewLoaded:
            case log:
                a(b, d, pVar);
                return;
            default:
                String valueOf = String.valueOf(jcVar.a());
                StringBuilder stringBuilder = new StringBuilder(25 + String.valueOf(valueOf).length());
                stringBuilder.append("Unknown message channel: ");
                stringBuilder.append(valueOf);
                Log.e("IMASDK", stringBuilder.toString());
                return;
        }
    }

    private void a(com.google.ads.interactivemedia.v3.internal.jc.b bVar) {
        switch (bVar) {
            case omidReady:
                jh.b();
                return;
            case omidUnavailable:
                jh.c();
                return;
            default:
                return;
        }
    }

    private void a(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        switch (bVar) {
            case initialized:
                this.j = new jf(pVar.adTimeUpdateMs);
                this.k = true;
                a(SystemClock.elapsedRealtime() - this.m, str);
                d();
                return;
            case log:
                if (pVar.ln == null || pVar.n == null || pVar.m == null) {
                    str = String.valueOf(pVar);
                    StringBuilder stringBuilder = new StringBuilder(30 + String.valueOf(str).length());
                    stringBuilder.append("Invalid logging message data: ");
                    stringBuilder.append(str);
                    Log.e("IMASDK", stringBuilder.toString());
                    return;
                }
                String str2 = "SDK_LOG:";
                str = String.valueOf(pVar.n);
                str2 = str.length() != 0 ? str2.concat(str) : new String(str2);
                switch (pVar.ln.charAt(0)) {
                    case 'D':
                        Log.d(str2, pVar.m);
                        return;
                    case 'E':
                    case 'S':
                        Log.e(str2, pVar.m);
                        return;
                    case 'I':
                        Log.i(str2, pVar.m);
                        return;
                    case 'V':
                        Log.v(str2, pVar.m);
                        return;
                    case 'W':
                        Log.w(str2, pVar.m);
                        return;
                    default:
                        str = "IMASDK";
                        String str3 = "Unrecognized log level: ";
                        String valueOf = String.valueOf(pVar.ln);
                        Log.w(str, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                        Log.w(str2, pVar.m);
                        return;
                }
            default:
                a(FacebookRequestErrorClassification.KEY_OTHER, bVar);
                return;
        }
    }

    private void a(long j, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("webViewLoadingTime", Long.valueOf(j));
        b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.webViewLoaded, com.google.ads.interactivemedia.v3.internal.jc.b.csi, str, hashMap));
    }

    private void b(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        iq iqVar = (iq) this.g.get(str);
        d dVar = (d) this.a.get(str);
        jo joVar = (jo) this.f.get(str);
        if (iqVar == null || dVar == null || joVar == null) {
            String valueOf = String.valueOf(bVar);
            StringBuilder stringBuilder = new StringBuilder((60 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder.append("Received displayContainer message: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" for invalid session id: ");
            stringBuilder.append(str);
            Log.e("IMASDK", stringBuilder.toString());
            return;
        }
        if (!joVar.b(bVar, pVar)) {
            if (AnonymousClass2.b[bVar.ordinal()] != 5) {
                a(com.google.ads.interactivemedia.v3.internal.jc.a.displayContainer.toString(), bVar);
            } else if (pVar == null || pVar.companions == null) {
                dVar.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Display companions message requires companions in data.");
            } else {
                Map a = a(iqVar, pVar.companions.keySet());
                dVar.a(pVar.companions);
                if (a == null) {
                    dVar.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Display requested for invalid companion slot.");
                } else if (this.p == null || this.p.isRenderCompanions()) {
                    for (String str2 : a.keySet()) {
                        a((ViewGroup) a.get(str2), (CompanionData) pVar.companions.get(str2), str, (CompanionAdSlot) iqVar.a().get(str2));
                    }
                }
            }
        }
    }

    private void c(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        e eVar = (e) this.e.get(str);
        if (eVar != null) {
            eVar.a(bVar, pVar.translation);
        }
    }

    private Map<String, ViewGroup> a(iq iqVar, Set<String> set) {
        HashMap hashMap = new HashMap(set.size());
        for (String str : set) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) iqVar.a().get(str);
            if (companionAdSlot.getContainer() == null) {
                return null;
            }
            hashMap.put(str, companionAdSlot.getContainer());
        }
        return hashMap;
    }

    private void d(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        b bVar2 = (b) this.d.get(str);
        String valueOf;
        if (bVar2 == null) {
            valueOf = String.valueOf(bVar);
            StringBuilder stringBuilder = new StringBuilder((51 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder.append("Received request message: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" for invalid session id: ");
            stringBuilder.append(str);
            Log.e("IMASDK", stringBuilder.toString());
            return;
        }
        switch (bVar) {
            case adsLoaded:
                if (pVar != null) {
                    bVar2.a(str, this.j, pVar.adCuePoints, pVar.internalCuePoints, pVar.monitorAppLifecycle);
                    break;
                } else {
                    bVar2.a(str, AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "adsLoaded message did not contain cue points.");
                    break;
                }
            case streamInitialized:
                bVar2.a(str, this.j, pVar.streamId, pVar.monitorAppLifecycle);
                valueOf = "IMASDK";
                str = "Stream initialized with streamId: ";
                String valueOf2 = String.valueOf(pVar.streamId);
                Log.i(valueOf, valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                break;
            case error:
                bVar2.a(str, AdErrorType.LOAD, pVar.errorCode, b(pVar.errorMessage, pVar.innerError));
                break;
            default:
                a(com.google.ads.interactivemedia.v3.internal.jc.a.adsLoader.toString(), bVar);
                break;
        }
    }

    private void e(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        jo joVar = (jo) this.f.get(str);
        if (joVar == null) {
            String valueOf = String.valueOf(bVar);
            StringBuilder stringBuilder = new StringBuilder((56 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder.append("Received videoDisplay message: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" for invalid session id: ");
            stringBuilder.append(str);
            Log.w("IMASDK", stringBuilder.toString());
            return;
        }
        joVar.a(bVar, pVar);
    }

    private void f(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        d dVar = (d) this.a.get(str);
        if (dVar == null) {
            String valueOf = String.valueOf(bVar);
            StringBuilder stringBuilder = new StringBuilder((51 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder.append("Received manager message: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" for invalid session id: ");
            stringBuilder.append(str);
            Log.e("IMASDK", stringBuilder.toString());
            return;
        }
        com.google.ads.interactivemedia.v3.impl.data.b bVar2 = (pVar == null || pVar.adData == null) ? null : pVar.adData;
        int i = AnonymousClass2.b[bVar.ordinal()];
        c cVar;
        if (i != 4) {
            switch (i) {
                case 8:
                    dVar.a(AdErrorType.PLAY, pVar.errorCode, b(pVar.errorMessage, pVar.innerError));
                    break;
                case 9:
                case 31:
                case 32:
                case 33:
                case 34:
                    break;
                case 10:
                    if (bVar2 == null) {
                        Log.e("IMASDK", "Ad loaded message requires adData");
                        dVar.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Ad loaded message did not contain adData.");
                        break;
                    }
                    dVar.a(new c(AdEventType.LOADED, bVar2));
                    break;
                case 11:
                    dVar.a(new c(AdEventType.CONTENT_PAUSE_REQUESTED, null));
                    break;
                case 12:
                    dVar.a(new c(AdEventType.CONTENT_RESUME_REQUESTED, null));
                    break;
                case 13:
                    dVar.a(new c(AdEventType.COMPLETED, bVar2));
                    break;
                case 14:
                    dVar.a(new c(AdEventType.ALL_ADS_COMPLETED, null));
                    break;
                case 15:
                    cVar = new c(AdEventType.CUEPOINTS_CHANGED, null);
                    cVar.d = new ArrayList();
                    for (n nVar : pVar.cuepoints) {
                        cVar.d.add(new iw(nVar.start(), nVar.end(), nVar.played()));
                    }
                    dVar.a(cVar);
                    break;
                case 16:
                    dVar.a(new c(AdEventType.SKIPPED, bVar2));
                    break;
                case 17:
                    dVar.a(new c(AdEventType.STARTED, bVar2));
                    break;
                case 18:
                    dVar.a(new c(AdEventType.PAUSED, bVar2));
                    break;
                case 19:
                    dVar.a(new c(AdEventType.RESUMED, bVar2));
                    break;
                case 20:
                    dVar.a(new c(AdEventType.FIRST_QUARTILE, bVar2));
                    break;
                case 21:
                    dVar.a(new c(AdEventType.MIDPOINT, bVar2));
                    break;
                case 22:
                    dVar.a(new c(AdEventType.THIRD_QUARTILE, bVar2));
                    break;
                case 23:
                    dVar.a(new c(AdEventType.CLICKED, bVar2));
                    break;
                case 24:
                    dVar.a(new c(AdEventType.SKIPPABLE_STATE_CHANGED, bVar2));
                    break;
                case 25:
                    dVar.a(new c(AdEventType.TAPPED, bVar2));
                    break;
                case 26:
                    cVar = new c(AdEventType.ICON_TAPPED, null);
                    cVar.f = pVar.clickThroughUrl;
                    dVar.a(cVar);
                    break;
                case 27:
                    cVar = new c(AdEventType.AD_PROGRESS, bVar2);
                    cVar.e = new ig(pVar.currentTime, pVar.duration, pVar.adPosition, pVar.totalAds, pVar.adBreakDuration);
                    dVar.a(cVar);
                    break;
                case 28:
                    cVar = new c(AdEventType.AD_BREAK_READY, null);
                    cVar.c = new ArrayMap(1);
                    cVar.c.put("adBreakTime", pVar.adBreakTime);
                    dVar.a(cVar);
                    break;
                case 29:
                    dVar.a(new c(AdEventType.AD_BREAK_STARTED, bVar2));
                    break;
                case 30:
                    dVar.a(new c(AdEventType.AD_BREAK_ENDED, bVar2));
                    break;
                default:
                    a(com.google.ads.interactivemedia.v3.internal.jc.a.adsManager.toString(), bVar);
                    break;
            }
        }
        cVar = new c(AdEventType.LOG, bVar2);
        cVar.c = pVar.logData.constructMap();
        dVar.a(cVar);
    }

    private void g(com.google.ads.interactivemedia.v3.internal.jc.b bVar, String str, p pVar) {
        if (!this.c.contains(str)) {
            a aVar = (a) this.b.get(str);
            String valueOf;
            StringBuilder stringBuilder;
            if (aVar == null) {
                valueOf = String.valueOf(bVar);
                stringBuilder = new StringBuilder((51 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
                stringBuilder.append("Received monitor message: ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" for invalid session id: ");
                stringBuilder.append(str);
                Log.e("IMASDK", stringBuilder.toString());
            } else if (pVar == null) {
                valueOf = String.valueOf(bVar);
                stringBuilder = new StringBuilder((56 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
                stringBuilder.append("Received monitor message: ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" for session id: ");
                stringBuilder.append(str);
                stringBuilder.append(" with no data");
                Log.e("IMASDK", stringBuilder.toString());
            } else {
                switch (bVar) {
                    case getViewability:
                        aVar.a(pVar.queryId, pVar.eventId);
                        break;
                    case reportVastEvent:
                        aVar.a(pVar.queryId, pVar.eventId, pVar.vastEvent);
                        break;
                    default:
                        a(com.google.ads.interactivemedia.v3.internal.jc.a.activityMonitor.toString(), bVar);
                        break;
                }
            }
        }
    }

    private void a(String str, com.google.ads.interactivemedia.v3.internal.jc.b bVar) {
        String valueOf = String.valueOf(bVar);
        StringBuilder stringBuilder = new StringBuilder((43 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
        stringBuilder.append("Illegal message type ");
        stringBuilder.append(valueOf);
        stringBuilder.append(" received for ");
        stringBuilder.append(str);
        stringBuilder.append(" channel");
        Log.i("IMASDK", stringBuilder.toString());
    }

    private String b(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder((12 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append(" Caused by: ");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public void a(b bVar, String str) {
        this.d.put(str, bVar);
    }

    public void a(d dVar, String str) {
        this.a.put(str, dVar);
    }

    public void a(a aVar, String str) {
        this.b.put(str, aVar);
    }

    public void a(String str) {
        this.b.remove(str);
        this.c.add(str);
    }

    public void a(jo joVar, String str) {
        this.f.put(str, joVar);
    }

    public void a(BaseDisplayContainer baseDisplayContainer, String str) {
        this.g.put(str, baseDisplayContainer);
    }

    public void b(String str) {
        this.a.remove(str);
        this.g.remove(str);
        this.f.remove(str);
    }

    public void b(jc jcVar) {
        this.l.add(jcVar);
        d();
    }

    private void d() {
        while (this.k && !this.l.isEmpty()) {
            this.i.a((jc) this.l.remove());
        }
    }

    public void c(final String str) {
        if (str != null && str.length() > 0) {
            new AsyncTask<Void, Void, Void>() {
                /* Access modifiers changed, original: protected|varargs */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    if (!(jd.this.h instanceof Activity)) {
                        intent.setFlags(C.ENCODING_PCM_MU_LAW);
                    }
                    jd.this.h.startActivity(intent);
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    private void a(ViewGroup viewGroup, CompanionData companionData, String str, CompanionAdSlot companionAdSlot) {
        View a;
        viewGroup.removeAllViews();
        is isVar = (is) companionAdSlot;
        List a2 = isVar.a();
        switch (companionData.type()) {
            case Html:
            case IFrame:
                a = a(viewGroup.getContext(), companionData, a2);
                break;
            case Static:
                a = a(viewGroup.getContext(), companionData, str, a2);
                break;
            default:
                a = null;
                break;
        }
        a.setTag(str);
        isVar.a(str);
        viewGroup.addView(a);
    }

    public void a(String str, String str2) {
        if (!ks.a(str) && !ks.a(str2)) {
            HashMap hashMap = new HashMap();
            hashMap.put("companionId", str);
            b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.displayContainer, com.google.ads.interactivemedia.v3.internal.jc.b.companionView, str2, hashMap));
        }
    }

    /* Access modifiers changed, original: protected */
    public View a(Context context, CompanionData companionData, List<ClickListener> list) {
        return new it(context, this, companionData, list);
    }

    /* Access modifiers changed, original: protected */
    public View a(Context context, CompanionData companionData, String str, List<ClickListener> list) {
        jb jbVar = new jb(context, this, companionData, str, list);
        jbVar.a();
        return jbVar;
    }
}

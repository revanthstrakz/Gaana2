package com.til.colombia.android.service;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.toolbox.m;
import com.til.colombia.android.commons.CmError;
import com.til.colombia.android.internal.a.l;
import com.til.colombia.android.network.d;
import com.til.colombia.android.network.h;
import com.til.colombia.android.network.q;
import com.til.colombia.android.service.listener.FeedJsonListener;
import com.til.colombia.android.service.listener.FeedListener;
import com.til.colombia.android.utils.CmFeedUtil;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public final class CmManager {
    private static Context appContext;
    private static CmManager mInstance;
    private CmFeedUtil cmFeedUtil = new CmFeedUtil();
    private com.til.colombia.android.network.a cmImpressionNotifier = new com.til.colombia.android.network.a();
    private WeakHashMap<String, ColombiaNativeVideoAdView> videoViews;

    private static class a extends AsyncTask<Void, Void, Object> {
        com.til.colombia.android.service.listener.a a;
        byte[] b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        a(com.til.colombia.android.service.listener.a aVar, byte[] bArr) {
            this.a = aVar;
            this.b = bArr;
        }

        private Object a() {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject(new String(this.b));
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (this.a instanceof FeedJsonListener) {
                return jSONObject;
            }
            if (this.a instanceof FeedListener) {
                return new CmResponse(jSONObject, com.til.colombia.android.internal.a.b(CmManager.appContext) ^ 1);
            }
            return null;
        }

        /* Access modifiers changed, original: protected|final */
        public final void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            int optInt;
            if (this.a instanceof FeedJsonListener) {
                if (obj == null) {
                    this.a.onFeedFailed(CmError.WRONG_FORMAT_ERROR);
                    return;
                }
                try {
                    JSONObject jSONObject = (JSONObject) obj;
                    optInt = jSONObject.optInt("status");
                    if (optInt == 1) {
                        ((FeedJsonListener) this.a).onFeedSuccess(jSONObject);
                    } else if (optInt == 102) {
                        this.a.onFeedFailed(CmError.NO_FILL_ERROR);
                    } else if (optInt == 101) {
                        this.a.onFeedFailed(CmError.SERVER_ERROR);
                    } else {
                        this.a.onFeedFailed(CmError.UNKNOWN);
                    }
                } catch (Exception unused) {
                    this.a.onFeedFailed(CmError.UNKNOWN);
                }
            } else if (this.a instanceof FeedListener) {
                if (obj == null) {
                    this.a.onFeedFailed(CmError.UNKNOWN);
                    return;
                }
                try {
                    CmResponse cmResponse = (CmResponse) obj;
                    optInt = cmResponse.status();
                    if (optInt == 1) {
                        ((FeedListener) this.a).onFeedSuccess(cmResponse);
                    } else if (optInt == 102) {
                        this.a.onFeedFailed(CmError.NO_FILL_ERROR);
                    } else if (optInt == 101) {
                        this.a.onFeedFailed(CmError.SERVER_ERROR);
                    } else {
                        this.a.onFeedFailed(CmError.UNKNOWN);
                    }
                } catch (Exception unused2) {
                    this.a.onFeedFailed(CmError.UNKNOWN);
                }
            }
        }
    }

    private CmManager(Context context) {
        appContext = context;
    }

    /* JADX WARNING: Missing block: B:10:0x0017, code skipped:
            return;
     */
    public static synchronized void initialize(android.content.Context r2) {
        /*
        r0 = com.til.colombia.android.service.CmManager.class;
        monitor-enter(r0);
        if (r2 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r0);
        return;
    L_0x0007:
        r1 = mInstance;	 Catch:{ all -> 0x0018 }
        if (r1 != 0) goto L_0x0016;
    L_0x000b:
        r1 = new com.til.colombia.android.service.CmManager;	 Catch:{ all -> 0x0018 }
        r2 = r2.getApplicationContext();	 Catch:{ all -> 0x0018 }
        r1.<init>(r2);	 Catch:{ all -> 0x0018 }
        mInstance = r1;	 Catch:{ all -> 0x0018 }
    L_0x0016:
        monitor-exit(r0);
        return;
    L_0x0018:
        r2 = move-exception;
        monitor-exit(r0);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.CmManager.initialize(android.content.Context):void");
    }

    /* Access modifiers changed, original: protected|final */
    public final com.til.colombia.android.network.a getCmImpressionNotifier() {
        return this.cmImpressionNotifier;
    }

    public static synchronized CmManager getInstance() {
        CmManager cmManager;
        synchronized (CmManager.class) {
            cmManager = mInstance;
        }
        return cmManager;
    }

    public final CmFeedUtil getCmFeedUtil() {
        return this.cmFeedUtil;
    }

    public final CmResponse requestFeedResponse(CmFeedRequest cmFeedRequest) {
        try {
            JSONObject requestFeedJson = requestFeedJson(cmFeedRequest);
            if (requestFeedJson == null) {
                return null;
            }
            return new CmResponse(requestFeedJson, com.til.colombia.android.internal.a.b(appContext) ^ 1);
        } catch (Exception unused) {
            return null;
        }
    }

    public final JSONObject requestFeedJson(CmFeedRequest cmFeedRequest) {
        try {
            byte[] requestFeedBytes = requestFeedBytes(cmFeedRequest);
            if (requestFeedBytes == null) {
                return null;
            }
            return new JSONObject(new String(requestFeedBytes));
        } catch (Exception unused) {
            return null;
        }
    }

    public final byte[] requestFeedBytes(CmFeedRequest cmFeedRequest) {
        try {
            return requestSync(q.a(cmFeedRequest), cmFeedRequest.a.timeout);
        } catch (Exception unused) {
            return null;
        }
    }

    private byte[] requestSync(String str, long j) throws ExecutionException, InterruptedException, TimeoutException {
        m a = m.a();
        Request hVar = new h(0, str, a, a);
        if (!com.til.colombia.android.internal.a.H() && com.til.colombia.android.internal.a.b(appContext)) {
            d.b().d().a(q.a(str), true);
            d.b().d().b(q.a(str));
        }
        d.b().a(hVar);
        return (byte[]) a.get(j, TimeUnit.MILLISECONDS);
    }

    public final void requestFeedAsync(CmFeedRequest cmFeedRequest, FeedListener feedListener) {
        requestAsync(q.a(cmFeedRequest), feedListener);
    }

    public final void requestFeedJsonAsync(CmFeedRequest cmFeedRequest, FeedJsonListener feedJsonListener) {
        requestAsync(q.a(cmFeedRequest), feedJsonListener);
    }

    private void requestAsync(String str, com.til.colombia.android.service.listener.a aVar) {
        if (appContext == null) {
            aVar.onFeedFailed(CmError.UNKNOWN);
            return;
        }
        Request hVar = new h(0, str, new ad(this, aVar), new ae(this, aVar));
        if (!com.til.colombia.android.internal.a.H() && com.til.colombia.android.internal.a.b(appContext)) {
            d.b().d().a(q.a(str), true);
            d.b().d().b(q.a(str));
        }
        hVar.setTag(d.c);
        d.b().a(hVar);
    }

    public final void performClick(CmItem cmItem) {
        if (!cmItem.isAd()) {
            bi.a();
            bi.a((Item) cmItem, true);
        }
    }

    public final void performClick(String str, String str2) {
        if (getCmFeedUtil() != null) {
            CmEntity cmEntity = getCmFeedUtil().getCmEntity(str);
            if (cmEntity != null) {
                for (CmItem cmItem : cmEntity.getCmItems()) {
                    if (cmItem.getUID().equalsIgnoreCase(str2) && !cmItem.isAd()) {
                        bi.a();
                        bi.a((Item) cmItem, true);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isVisible(View view) {
        return l.a(view);
    }

    /* Access modifiers changed, original: protected|final */
    public final void addVideoView(String str, ColombiaNativeVideoAdView colombiaNativeVideoAdView) {
        if (this.videoViews == null) {
            this.videoViews = new WeakHashMap();
        }
        this.videoViews.put(str, colombiaNativeVideoAdView);
    }

    /* Access modifiers changed, original: protected|final */
    public final ColombiaNativeVideoAdView getColombiaNativeVideoAdView(String str) {
        if (this.videoViews == null) {
            return null;
        }
        return (ColombiaNativeVideoAdView) this.videoViews.get(str);
    }

    public final String getItemId(String str, String str2) {
        if (getInstance().getCmFeedUtil() != null) {
            CmEntity cmEntity = getInstance().getCmFeedUtil().getCmEntity(str);
            if (cmEntity != null) {
                for (CmItem cmItem : cmEntity.getCmItems()) {
                    if (cmItem.getUID().equalsIgnoreCase(str2)) {
                        return cmItem.getItemId();
                    }
                }
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final void cleanResources() {
        if (d.a != null) {
            d.a.a(d.c);
        }
        if (d.b != null) {
            d.b.a((Object) "click");
        }
        if (this.cmFeedUtil != null) {
            this.cmFeedUtil.clear();
        }
        if (this.cmImpressionNotifier != null) {
            this.cmImpressionNotifier.b();
        }
    }
}

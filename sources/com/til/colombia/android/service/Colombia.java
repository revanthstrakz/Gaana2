package com.til.colombia.android.service;

import android.content.Context;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import com.til.colombia.android.internal.ColombiaException;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.Log.INTERNAL_LOG_LEVEL;
import com.til.colombia.android.internal.d;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.g;
import com.til.colombia.android.internal.h;
import com.til.colombia.android.network.q;
import com.til.colombia.android.persona.PersonaManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Colombia {
    private static final String LOG_TAG = "Col:aos:4.0.0";
    private static bi colombia;
    private static d connBroadcastReciever;

    public enum LOG_LEVEL {
        NONE(0),
        DEBUG(1),
        INTERNAL(2);
        
        private final int a;

        private LOG_LEVEL(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    private static class a extends AsyncTask<Void, Void, Void> {
        URL a;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        a() {
        }

        private Void a() {
            if (com.til.colombia.android.internal.a.F()) {
                try {
                    this.a = new URL(q.a());
                } catch (MalformedURLException unused) {
                }
                if (this.a == null) {
                    return null;
                }
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) this.a.openConnection();
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    String str = e.c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(System.getProperty("http.agent"));
                    stringBuilder.append("Col:aos:4.0.0,");
                    stringBuilder.append("colombia-dmp-aos:1.2.1");
                    httpURLConnection.setRequestProperty(str, stringBuilder.toString());
                    int i = 0;
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine);
                        stringBuilder2.append("\n");
                    }
                    inputStream.close();
                    if (TextUtils.isEmpty(stringBuilder2.toString())) {
                        com.til.colombia.android.internal.a.E();
                        com.til.colombia.android.internal.a.i();
                        return null;
                    }
                    JSONArray optJSONArray = new JSONObject(stringBuilder2.toString()).optJSONArray("ntwDims");
                    if (optJSONArray != null) {
                        while (i < optJSONArray.length()) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            String optString = optJSONObject.optString("ntwkId");
                            if (optString.equalsIgnoreCase(g.f)) {
                                com.til.colombia.android.internal.a.c(optJSONObject.optString("dimId"));
                            } else if (optString.equalsIgnoreCase(g.e)) {
                                com.til.colombia.android.internal.a.b(optJSONObject.optString("dimId"));
                            }
                            i++;
                        }
                    }
                    com.til.colombia.android.internal.a.E();
                    com.til.colombia.android.internal.a.i();
                } catch (Exception unused2) {
                } catch (Throwable th) {
                    com.til.colombia.android.internal.a.i();
                }
            } else {
                com.til.colombia.android.internal.a.i();
            }
            return null;
        }
    }

    private static class b extends AsyncTask<Void, Void, Void> {
        URL a;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        b() {
        }

        private Void a() {
            if (com.til.colombia.android.internal.a.w()) {
                try {
                    this.a = new URL(q.b());
                } catch (MalformedURLException unused) {
                }
                if (this.a == null) {
                    return null;
                }
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) this.a.openConnection();
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    String str = e.c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(System.getProperty("http.agent"));
                    stringBuilder.append("Col:aos:4.0.0,");
                    stringBuilder.append("colombia-dmp-aos:1.2.1");
                    httpURLConnection.setRequestProperty(str, stringBuilder.toString());
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    inputStream.close();
                    if (TextUtils.isEmpty(stringBuilder.toString())) {
                        com.til.colombia.android.internal.a.v();
                        if (!com.til.colombia.android.internal.a.u()) {
                            com.til.colombia.android.internal.a.m();
                        }
                        return null;
                    }
                    com.til.colombia.android.internal.a.a(new JSONObject(stringBuilder.toString()));
                    com.til.colombia.android.internal.a.v();
                } catch (Exception unused2) {
                    if (!com.til.colombia.android.internal.a.u()) {
                        com.til.colombia.android.internal.a.m();
                    }
                }
            } else if (!com.til.colombia.android.internal.a.u()) {
                com.til.colombia.android.internal.a.m();
            }
            return null;
        }
    }

    private static class c extends AsyncTask<Void, Void, Void> {
        Context a;
        CmInitListener b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Void voidR = (Void) obj;
            if (com.til.colombia.android.internal.a.d()) {
                this.b.onSuccess();
            } else {
                this.b.onFailed();
            }
            super.onPostExecute(voidR);
        }

        c(Context context, CmInitListener cmInitListener) {
            this.a = context;
            this.b = cmInitListener;
        }

        private Void a() {
            Colombia.initialize(this.a);
            return null;
        }

        private void a(Void voidR) {
            if (com.til.colombia.android.internal.a.d()) {
                this.b.onSuccess();
            } else {
                this.b.onFailed();
            }
            super.onPostExecute(voidR);
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Colombia.initialize(this.a);
            return null;
        }
    }

    public static String getVersion() {
        return "aos:4.0.0";
    }

    @Deprecated
    public static void recordImpression(ItemResponse itemResponse, View view) throws ColombiaException {
    }

    private Colombia() {
    }

    protected static bi getInstance() {
        if (colombia == null && com.til.colombia.android.internal.a.d()) {
            colombia = bi.a();
        }
        return colombia;
    }

    private static void initializeColombia(Context context) {
        setLogLevel(h.k.getValue());
        if (context == null) {
            try {
                Log.b("Col:aos:4.0.0", "context can not be NULL");
                return;
            } catch (Exception e) {
                android.util.Log.i("Col:aos:4.0.0", "Exception in colombia init", e);
                return;
            }
        }
        com.til.colombia.android.internal.a.a(context.getApplicationContext());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.getProperty("http.agent"));
        stringBuilder.append("Col:aos:4.0.0");
        com.til.colombia.android.internal.a.a(stringBuilder.toString());
        if (com.til.colombia.android.internal.a.d()) {
            colombia = bi.a();
            android.util.Log.i("Col:aos:4.0.0", "Colombia init success");
        } else {
            android.util.Log.i("Col:aos:4.0.0", "Colombia init failed");
        }
        if (connBroadcastReciever == null) {
            connBroadcastReciever = new d();
        }
        com.til.colombia.android.internal.a.a().registerReceiver(connBroadcastReciever, new IntentFilter(d.a));
    }

    public static void setLogLevel(int i) {
        if (i == LOG_LEVEL.NONE.getValue()) {
            Log.a(INTERNAL_LOG_LEVEL.NONE);
        } else if (i == LOG_LEVEL.DEBUG.getValue()) {
            Log.a(INTERNAL_LOG_LEVEL.DEBUG);
        } else {
            if (i == LOG_LEVEL.INTERNAL.getValue()) {
                Log.a(INTERNAL_LOG_LEVEL.INTERNAL);
            }
        }
    }

    @Deprecated
    public static void initialize(Context context, Integer num) {
        initialize(context);
    }

    public static void initAsync(Context context, CmInitListener cmInitListener) {
        if (VERSION.SDK_INT >= 11) {
            new c(context, cmInitListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new c(context, cmInitListener).execute(new Void[0]);
        }
    }

    public static void initialize(Context context) {
        initializeColombia(context);
        CmManager.initialize(context);
        initRootConfig();
        initializeDmp();
        initAdExConfig();
    }

    private static void initializeDmp() {
        try {
            PersonaManager.getInstance();
        } catch (Exception unused) {
        }
    }

    public static void getNativeAds(ColombiaAdRequest colombiaAdRequest) throws ColombiaException {
        if (getInstance() == null) {
            throw new ColombiaException("getNativeAds :colombia is not initialized.");
        } else if (colombiaAdRequest == null) {
            throw new ColombiaException("ColombiaAdRequest can not be NULL.");
        } else {
            cl clVar = new cl(getInstance().a, colombiaAdRequest);
            clVar.e();
            StringBuilder stringBuilder = new StringBuilder("requesting ad.");
            stringBuilder.append(clVar.toString());
            android.util.Log.i("Col:aos:4.0.0", stringBuilder.toString());
        }
    }

    private static void initRootConfig() {
        if (VERSION.SDK_INT >= 11) {
            new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new b().execute(new Void[0]);
        }
    }

    private static void initAdExConfig() {
        if (VERSION.SDK_INT >= 11) {
            new a().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new a().execute(new Void[0]);
        }
    }

    public static void disableNetworkCache() {
        com.til.colombia.android.internal.a.a(true);
    }

    public static void UseCmFeedUtil() {
        com.til.colombia.android.internal.a.b(true);
    }
}

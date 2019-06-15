package com.til.colombia.dmp.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public final class DmpManager {
    private static final String CID = "cid";
    private static final String DUMMY_REFERER_KEY = "cm_ref";
    private static final String EVENTS = "val_101";
    private static final String EVENTS_TYPE = "val_120";
    private static final String EVENTS_TYPE_BEHAVIOUR = "1";
    private static final String EVENTS_TYPE_PERSONA = "2";
    private static final String LITE = "lite";
    private static final String MESSAGE = "message";
    private static final String REFERER = "val_124";
    private static final String UUID = "uuid";
    private static DmpManager dmpManager = null;
    private static AtomicBoolean isFirstPersonaEvent = new AtomicBoolean(false);
    private static final int maxEventSize = 50;
    private a dmpAuds = null;
    private AtomicInteger eventCount;
    private final Context mContext;
    private PackageManager mPackageManager = null;
    private List<String> mPersonaEvents;

    private class a extends AsyncTask<Void, Void, JSONObject> {
        private Context b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            a((JSONObject) obj);
        }

        a(Context context) {
            this.b = context;
        }

        private JSONObject a() {
            return DmpManager.this.getFeedContent(Utils.getFeedUrl());
        }

        /* JADX WARNING: Missing block: B:57:0x01c2, code skipped:
            return;
     */
        private synchronized void a(org.json.JSONObject r8) {
            /*
            r7 = this;
            monitor-enter(r7);
            if (r8 != 0) goto L_0x0005;
        L_0x0003:
            monitor-exit(r7);
            return;
        L_0x0005:
            r0 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01c5 }
            r1.<init>();	 Catch:{ Exception -> 0x01c5 }
            r0.mPersonaEvents = r1;	 Catch:{ Exception -> 0x01c5 }
            r0 = new com.til.colombia.dmp.android.e;	 Catch:{ Exception -> 0x01c5 }
            r0.<init>(r8);	 Catch:{ Exception -> 0x01c5 }
            r8 = r0.a;	 Catch:{ Exception -> 0x01c5 }
            if (r8 == 0) goto L_0x01a8;
        L_0x0018:
            r8 = r0.a;	 Catch:{ Exception -> 0x01c5 }
            r8 = r8.size();	 Catch:{ Exception -> 0x01c5 }
            if (r8 <= 0) goto L_0x01a8;
        L_0x0020:
            r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01c5 }
            r1 = r0.a;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.size();	 Catch:{ Exception -> 0x01c5 }
            r8.<init>(r1);	 Catch:{ Exception -> 0x01c5 }
            r0 = r0.a;	 Catch:{ Exception -> 0x01c5 }
            r0 = r0.iterator();	 Catch:{ Exception -> 0x01c5 }
        L_0x0031:
            r1 = r0.hasNext();	 Catch:{ Exception -> 0x01c5 }
            if (r1 == 0) goto L_0x0043;
        L_0x0037:
            r1 = r0.next();	 Catch:{ Exception -> 0x01c5 }
            r1 = (com.til.colombia.dmp.android.e.a) r1;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.a;	 Catch:{ Exception -> 0x01c5 }
            r8.add(r1);	 Catch:{ Exception -> 0x01c5 }
            goto L_0x0031;
        L_0x0043:
            r0 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r1 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.getPackageManager();	 Catch:{ Exception -> 0x01c5 }
            r0.mPackageManager = r1;	 Catch:{ Exception -> 0x01c5 }
            r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01c5 }
            r0.<init>();	 Catch:{ Exception -> 0x01c5 }
            r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01c5 }
            r1.<init>();	 Catch:{ Exception -> 0x01c5 }
            r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01c5 }
            r2.<init>();	 Catch:{ Exception -> 0x01c5 }
            r3 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r4 = "ColombiaDMPPref";
            r5 = "fPersona";
            r3 = com.til.colombia.dmp.android.Utils.getIntPreferences(r3, r4, r5);	 Catch:{ Exception -> 0x01c5 }
            r4 = 1;
            if (r3 != r4) goto L_0x00c1;
        L_0x006a:
            r3 = com.til.colombia.dmp.android.DmpManager.isFirstPersonaEvent;	 Catch:{ Exception -> 0x01c5 }
            r4 = 0;
            r3.set(r4);	 Catch:{ Exception -> 0x01c5 }
            r3 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r4 = "ColombiaDMPPref";
            r5 = "installed";
            r3 = com.til.colombia.dmp.android.Utils.getPreferences(r3, r4, r5);	 Catch:{ Exception -> 0x01c5 }
            r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x01c5 }
            if (r4 != 0) goto L_0x008c;
        L_0x0082:
            r0 = ",";
            r0 = r3.split(r0);	 Catch:{ Exception -> 0x01c5 }
            r0 = java.util.Arrays.asList(r0);	 Catch:{ Exception -> 0x01c5 }
        L_0x008c:
            r3 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r4 = "ColombiaDMPPref";
            r5 = "updated";
            r3 = com.til.colombia.dmp.android.Utils.getPreferences(r3, r4, r5);	 Catch:{ Exception -> 0x01c5 }
            r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x01c5 }
            if (r4 != 0) goto L_0x00a6;
        L_0x009c:
            r1 = ",";
            r1 = r3.split(r1);	 Catch:{ Exception -> 0x01c5 }
            r1 = java.util.Arrays.asList(r1);	 Catch:{ Exception -> 0x01c5 }
        L_0x00a6:
            r3 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r4 = "ColombiaDMPPref";
            r5 = "uninstalled";
            r3 = com.til.colombia.dmp.android.Utils.getPreferences(r3, r4, r5);	 Catch:{ Exception -> 0x01c5 }
            r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x01c5 }
            if (r4 != 0) goto L_0x00ce;
        L_0x00b6:
            r2 = ",";
            r2 = r3.split(r2);	 Catch:{ Exception -> 0x01c5 }
            r2 = java.util.Arrays.asList(r2);	 Catch:{ Exception -> 0x01c5 }
            goto L_0x00ce;
        L_0x00c1:
            r0 = r7.b;	 Catch:{ Exception -> 0x01c5 }
            r0 = com.til.colombia.dmp.android.Utils.getInstalledApps(r0);	 Catch:{ Exception -> 0x01c5 }
            r3 = com.til.colombia.dmp.android.DmpManager.isFirstPersonaEvent;	 Catch:{ Exception -> 0x01c5 }
            r3.set(r4);	 Catch:{ Exception -> 0x01c5 }
        L_0x00ce:
            r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r0 == 0) goto L_0x0120;
        L_0x00d2:
            r4 = r0.size();	 Catch:{ Exception -> 0x01c5 }
            if (r4 <= 0) goto L_0x0120;
        L_0x00d8:
            r4 = new java.util.HashSet;	 Catch:{ Exception -> 0x01c5 }
            r4.<init>(r8);	 Catch:{ Exception -> 0x01c5 }
            r4.retainAll(r0);	 Catch:{ Exception -> 0x01c5 }
            r0 = r4.iterator();	 Catch:{ Exception -> 0x01c5 }
        L_0x00e4:
            r4 = r0.hasNext();	 Catch:{ Exception -> 0x01c5 }
            if (r4 == 0) goto L_0x0120;
        L_0x00ea:
            r4 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r4 = r4.mPackageManager;	 Catch:{ Exception -> 0x01c5 }
            r5 = r0.next();	 Catch:{ Exception -> 0x01c5 }
            r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x01c5 }
            r4 = r4.getPackageInfo(r5, r3);	 Catch:{ Exception -> 0x01c5 }
            if (r4 == 0) goto L_0x00e4;
        L_0x00fc:
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01c5 }
            r6 = "installed:app.android.";
            r5.<init>(r6);	 Catch:{ Exception -> 0x01c5 }
            r6 = r4.packageName;	 Catch:{ Exception -> 0x01c5 }
            r5.append(r6);	 Catch:{ Exception -> 0x01c5 }
            r6 = ":Ver-";
            r5.append(r6);	 Catch:{ Exception -> 0x01c5 }
            r4 = r4.versionName;	 Catch:{ Exception -> 0x01c5 }
            r5.append(r4);	 Catch:{ Exception -> 0x01c5 }
            r4 = r5.toString();	 Catch:{ Exception -> 0x01c5 }
            r5 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r5 = r5.mPersonaEvents;	 Catch:{ Exception -> 0x01c5 }
            r5.add(r4);	 Catch:{ Exception -> 0x01c5 }
            goto L_0x00e4;
        L_0x0120:
            if (r1 == 0) goto L_0x0170;
        L_0x0122:
            r0 = r1.size();	 Catch:{ Exception -> 0x01c5 }
            if (r0 <= 0) goto L_0x0170;
        L_0x0128:
            r0 = new java.util.HashSet;	 Catch:{ Exception -> 0x01c5 }
            r0.<init>(r8);	 Catch:{ Exception -> 0x01c5 }
            r0.retainAll(r1);	 Catch:{ Exception -> 0x01c5 }
            r0 = r0.iterator();	 Catch:{ Exception -> 0x01c5 }
        L_0x0134:
            r1 = r0.hasNext();	 Catch:{ Exception -> 0x01c5 }
            if (r1 == 0) goto L_0x0170;
        L_0x013a:
            r1 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.mPackageManager;	 Catch:{ Exception -> 0x01c5 }
            r4 = r0.next();	 Catch:{ Exception -> 0x01c5 }
            r4 = (java.lang.String) r4;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.getPackageInfo(r4, r3);	 Catch:{ Exception -> 0x01c5 }
            if (r1 == 0) goto L_0x0134;
        L_0x014c:
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01c5 }
            r5 = "updated:app.android.";
            r4.<init>(r5);	 Catch:{ Exception -> 0x01c5 }
            r5 = r1.packageName;	 Catch:{ Exception -> 0x01c5 }
            r4.append(r5);	 Catch:{ Exception -> 0x01c5 }
            r5 = ":Ver-";
            r4.append(r5);	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.versionName;	 Catch:{ Exception -> 0x01c5 }
            r4.append(r1);	 Catch:{ Exception -> 0x01c5 }
            r1 = r4.toString();	 Catch:{ Exception -> 0x01c5 }
            r4 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r4 = r4.mPersonaEvents;	 Catch:{ Exception -> 0x01c5 }
            r4.add(r1);	 Catch:{ Exception -> 0x01c5 }
            goto L_0x0134;
        L_0x0170:
            if (r2 == 0) goto L_0x01a8;
        L_0x0172:
            r0 = r2.size();	 Catch:{ Exception -> 0x01c5 }
            if (r0 <= 0) goto L_0x01a8;
        L_0x0178:
            r0 = new java.util.HashSet;	 Catch:{ Exception -> 0x01c5 }
            r0.<init>(r8);	 Catch:{ Exception -> 0x01c5 }
            r0.retainAll(r2);	 Catch:{ Exception -> 0x01c5 }
            r8 = r0.iterator();	 Catch:{ Exception -> 0x01c5 }
        L_0x0184:
            r0 = r8.hasNext();	 Catch:{ Exception -> 0x01c5 }
            if (r0 == 0) goto L_0x01a8;
        L_0x018a:
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01c5 }
            r1 = "uninstalled:app.android.";
            r0.<init>(r1);	 Catch:{ Exception -> 0x01c5 }
            r1 = r8.next();	 Catch:{ Exception -> 0x01c5 }
            r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x01c5 }
            r0.append(r1);	 Catch:{ Exception -> 0x01c5 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x01c5 }
            r1 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r1 = r1.mPersonaEvents;	 Catch:{ Exception -> 0x01c5 }
            r1.add(r0);	 Catch:{ Exception -> 0x01c5 }
            goto L_0x0184;
        L_0x01a8:
            r8 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r8 = r8.mPersonaEvents;	 Catch:{ Exception -> 0x01c5 }
            if (r8 == 0) goto L_0x01c1;
        L_0x01b0:
            r8 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r8 = r8.mPersonaEvents;	 Catch:{ Exception -> 0x01c5 }
            r8 = r8.size();	 Catch:{ Exception -> 0x01c5 }
            if (r8 <= 0) goto L_0x01c1;
        L_0x01bc:
            r8 = com.til.colombia.dmp.android.DmpManager.this;	 Catch:{ Exception -> 0x01c5 }
            r8.sendPersonaEvents();	 Catch:{ Exception -> 0x01c5 }
        L_0x01c1:
            monitor-exit(r7);
            return;
        L_0x01c3:
            r8 = move-exception;
            goto L_0x01cb;
        L_0x01c5:
            r8 = move-exception;
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);	 Catch:{ all -> 0x01c3 }
            monitor-exit(r7);
            return;
        L_0x01cb:
            monitor-exit(r7);
            throw r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.dmp.android.DmpManager$a.a(org.json.JSONObject):void");
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return DmpManager.this.getFeedContent(Utils.getFeedUrl());
        }
    }

    private class b extends AsyncTask<Void, Void, Void> {
        URL a;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        b() {
        }

        private Void a() {
            if (Utils.isRootConfigExpired(DmpManager.this.mContext)) {
                try {
                    this.a = new URL(Utils.getRootConfigUrl(DmpManager.this.mContext));
                } catch (MalformedURLException unused) {
                }
                if (this.a == null) {
                    return null;
                }
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) this.a.openConnection();
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    String str = com.til.colombia.android.internal.e.c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(System.getProperty("http.agent"));
                    stringBuilder.append(Utils.LOG_TAG_VER);
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
                        Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.ROOT_CONFIG_TIMESTAMP, System.currentTimeMillis() / 1000);
                        return null;
                    }
                    f.a(DmpManager.this.mContext, new JSONObject(stringBuilder.toString()));
                    Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.ROOT_CONFIG_TIMESTAMP, System.currentTimeMillis() / 1000);
                } catch (Exception unused2) {
                }
            }
            f.a(DmpManager.this.mContext);
            return null;
        }
    }

    private class c extends AsyncTask<Void, Void, Boolean> {
        List<String> a;
        List<String> b = new ArrayList();

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Boolean bool = (Boolean) obj;
            if (bool.booleanValue()) {
                for (int i = 0; i < this.b.size(); i++) {
                    this.a.remove(0);
                }
                if (this.a.size() > 0) {
                    new c(this.a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                if (Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_LAST_UPDATED) + Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_UPDATE_REFRESH_TIME) <= System.currentTimeMillis() / 1000) {
                    DmpManager.this.updateAuds();
                }
            } else {
                if (TextUtils.isEmpty(Utils.getPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS))) {
                    Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS, Utils.join(this.a, ","));
                } else {
                    Context access$200 = DmpManager.this.mContext;
                    String str = Utils.DMP_PREF;
                    String str2 = Utils.DMP_INTERESTS;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Utils.join(this.a, ","));
                    stringBuilder.append(",");
                    stringBuilder.append(Utils.getPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS));
                    Utils.setPreferences(access$200, str, str2, stringBuilder.toString());
                }
                DmpManager.this.eventCount.set(this.a.size());
            }
            super.onPostExecute(bool);
        }

        c(List<String> list) {
            this.a = new ArrayList(list);
            if (this.a.size() > 50) {
                for (int i = 0; i < 50; i++) {
                    this.b.add(this.a.get(i));
                }
                return;
            }
            this.b.addAll(this.a);
        }

        private Boolean a() {
            return Boolean.valueOf(DmpManager.this.sendColombiaEvents(DmpManager.this.createInterestJson(this.b)));
        }

        private void a(Boolean bool) {
            if (bool.booleanValue()) {
                for (int i = 0; i < this.b.size(); i++) {
                    this.a.remove(0);
                }
                if (this.a.size() > 0) {
                    new c(this.a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
                if (Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_LAST_UPDATED) + Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_UPDATE_REFRESH_TIME) <= System.currentTimeMillis() / 1000) {
                    DmpManager.this.updateAuds();
                }
            } else {
                if (TextUtils.isEmpty(Utils.getPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS))) {
                    Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS, Utils.join(this.a, ","));
                } else {
                    Context access$200 = DmpManager.this.mContext;
                    String str = Utils.DMP_PREF;
                    String str2 = Utils.DMP_INTERESTS;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Utils.join(this.a, ","));
                    stringBuilder.append(",");
                    stringBuilder.append(Utils.getPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS));
                    Utils.setPreferences(access$200, str, str2, stringBuilder.toString());
                }
                DmpManager.this.eventCount.set(this.a.size());
            }
            super.onPostExecute(bool);
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return Boolean.valueOf(DmpManager.this.sendColombiaEvents(DmpManager.this.createInterestJson(this.b)));
        }
    }

    private class d extends AsyncTask<Void, Void, Boolean> {
        private d() {
        }

        /* synthetic */ d(DmpManager dmpManager, byte b) {
            this();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Boolean bool = (Boolean) obj;
            if (bool.booleanValue()) {
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.INSTALLED_APPS, null);
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.UPDATED_APPS, null);
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.UNINSTALLED_APPS, null);
                if (DmpManager.isFirstPersonaEvent.get()) {
                    Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.IS_FIRST_PERSONA_EVENT_REPORTED, 1);
                }
                if (Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_LAST_UPDATED) + Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_UPDATE_REFRESH_TIME) <= System.currentTimeMillis() / 1000) {
                    DmpManager.this.updateAuds();
                }
            }
            DmpManager.this.mPersonaEvents.clear();
            super.onPostExecute(bool);
        }

        private Boolean a() {
            return Boolean.valueOf(DmpManager.this.sendColombiaEvents(DmpManager.this.createPersonaJson()));
        }

        private void a(Boolean bool) {
            if (bool.booleanValue()) {
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.INSTALLED_APPS, null);
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.UPDATED_APPS, null);
                Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.UNINSTALLED_APPS, null);
                if (DmpManager.isFirstPersonaEvent.get()) {
                    Utils.setPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.IS_FIRST_PERSONA_EVENT_REPORTED, 1);
                }
                if (Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_LAST_UPDATED) + Utils.getLongPreferences(DmpManager.this.mContext, Utils.DMP_PREF, Utils.DMP_AUDS_UPDATE_REFRESH_TIME) <= System.currentTimeMillis() / 1000) {
                    DmpManager.this.updateAuds();
                }
            }
            DmpManager.this.mPersonaEvents.clear();
            super.onPostExecute(bool);
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return Boolean.valueOf(DmpManager.this.sendColombiaEvents(DmpManager.this.createPersonaJson()));
        }
    }

    private class e extends AsyncTask<Void, Void, Void> {
        private String b;
        private String c;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        e(String str, String str2) {
            this.c = str;
            this.b = str2;
        }

        private Void a() {
            if (!Utils.checkNetworkAvailibility(DmpManager.this.mContext)) {
                return null;
            }
            HttpURLConnection access$1100 = DmpManager.this.connectPartner(this.c, this.b);
            if (access$1100 != null) {
                try {
                    if (access$1100.getResponseCode() / 10 != 20) {
                        HttpURLConnection access$11002 = DmpManager.this.connectPartner(this.c, this.b);
                        if (access$11002 != null) {
                            access$11002.disconnect();
                        }
                    }
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Throwable th) {
                    access$1100.disconnect();
                }
                access$1100.disconnect();
            }
            return null;
        }
    }

    @Deprecated
    public static void disableDMP(Context context) {
    }

    @Deprecated
    public static void enableDMP(Context context) {
    }

    public static synchronized void initialize(Context context) {
        synchronized (DmpManager.class) {
            if (context == null) {
                Log.i(Utils.LOG_TAG_VER, "Initilaize fail : Context can not be null.");
                return;
            }
            if (dmpManager == null) {
                dmpManager = new DmpManager(context);
            }
            dmpManager.requestRootConfig();
        }
    }

    @Deprecated
    public static synchronized void initialize(Context context, Integer num) {
        synchronized (DmpManager.class) {
            initialize(context);
        }
    }

    private DmpManager(Context context) {
        this.mContext = context;
        this.eventCount = new AtomicInteger(0);
        this.dmpAuds = new a(this.mContext);
        sendEventsOnInit();
    }

    private void requestRootConfig() {
        new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void sendEventsOnInit() {
        try {
            String preferences = Utils.getPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS);
            if (!TextUtils.isEmpty(preferences)) {
                this.eventCount.set(preferences.split(",").length);
            }
            sendInterestEvents(true);
        } catch (Exception unused) {
        }
    }

    private void sendEvents() {
        try {
            String preferences = Utils.getPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS);
            if (!TextUtils.isEmpty(preferences)) {
                this.eventCount.set(preferences.split(",").length);
            }
            if (Utils.getIntPreferences(this.mContext, Utils.DMP_PREF, Utils.IS_FIRST_PERSONA_EVENT_REPORTED) != 1 && Utils.getIntPreferences(this.mContext, Utils.DMP_PREF, Utils.PERSONA_DISABLE) == 0) {
                process(this.mContext);
            }
            sendInterestEvents(true);
        } catch (Exception unused) {
        }
    }

    public static DmpManager getInstance() {
        return dmpManager;
    }

    public final void completeSession() {
        sendInterestEvents(false);
        if (Utils.getIntPreferences(this.mContext, Utils.DMP_PREF, Utils.PERSONA_DISABLE) == 0 && this.mPersonaEvents != null && this.mPersonaEvents.size() > 0) {
            sendPersonaEvents();
        }
    }

    public final void addMultipleEvents(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.i(Utils.LOG_TAG_VER, "Empty key");
        } else if (TextUtils.isEmpty(str2)) {
            Log.i(Utils.LOG_TAG_VER, "Empty value");
        } else {
            String[] split = str2.split(",");
            for (String addEvents : split) {
                addEvents(str, addEvents);
            }
        }
    }

    public final void addReferer(String str) {
        addEvents(DUMMY_REFERER_KEY, str);
    }

    public final void addEvents(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.i(Utils.LOG_TAG_VER, "Empty key");
            return;
        }
        str = str.replace(",", " ");
        if (TextUtils.isEmpty(str2)) {
            Log.i(Utils.LOG_TAG_VER, "Empty value");
            return;
        }
        str2 = str2.replace(",", " ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        str = stringBuilder.toString();
        if (TextUtils.isEmpty(Utils.getPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS))) {
            Utils.setPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS, str);
        } else {
            Context context = this.mContext;
            String str3 = Utils.DMP_PREF;
            String str4 = Utils.DMP_INTERESTS;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(",");
            stringBuilder2.append(Utils.getPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS));
            Utils.setPreferences(context, str3, str4, stringBuilder2.toString());
        }
        this.eventCount.incrementAndGet();
        if (this.eventCount.get() >= 10) {
            sendInterestEvents(false);
        }
    }

    private synchronized void sendInterestEvents(boolean z) {
        try {
            this.eventCount.set(0);
            ArrayList arrayList = new ArrayList();
            String preferences = Utils.getPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS);
            if (!TextUtils.isEmpty(preferences)) {
                arrayList.addAll(Arrays.asList(preferences.split(",")));
            }
            Utils.setPreferences(this.mContext, Utils.DMP_PREF, Utils.DMP_INTERESTS, null);
            if (arrayList.size() > 0 || z) {
                new c(arrayList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        } catch (Exception unused) {
        }
    }

    private JSONObject createInterestJson(List<String> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cid", Utils.getApplicationInfo(this.mContext).packageName);
            jSONObject.put(UUID, Utils.getAAID(this.mContext));
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String str = (String) list.get(i);
                if (str.contains(DUMMY_REFERER_KEY)) {
                    try {
                        jSONArray2.put(str.substring(str.indexOf(58) + 1, str.length()));
                    } catch (Exception unused) {
                    }
                } else {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject.put(EVENTS, jSONArray);
            jSONObject.put(EVENTS_TYPE, "1");
            jSONObject.put(REFERER, jSONArray2);
            jSONObject.put("lite", Utils.getLite().toString());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("message", jSONObject);
            return jSONObject2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private void sendPersonaEvents() {
        try {
            new d(this, (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } catch (Exception unused) {
        }
    }

    private JSONObject createPersonaJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cid", Utils.getApplicationInfo(this.mContext).packageName);
            jSONObject.put(UUID, Utils.getAAID(this.mContext));
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.mPersonaEvents.size(); i++) {
                jSONArray.put(this.mPersonaEvents.get(i));
            }
            jSONObject.put(EVENTS, jSONArray);
            jSONObject.put(EVENTS_TYPE, "2");
            jSONObject.put("lite", Utils.getLite().toString());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("message", jSONObject);
            return jSONObject2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* Access modifiers changed, original: protected|final|declared_synchronized */
    public final synchronized void process(Context context) {
        new a(context).execute(new Void[0]);
    }

    private JSONObject getFeedContent(String str) {
        CharSequence preferences = Utils.getLongPreferences(this.mContext, Utils.FEED_PREF, Utils.FEED_TIMESTAMP) > (System.currentTimeMillis() / 1000) - 86400 ? Utils.getPreferences(this.mContext, Utils.FEED_PREF, Utils.FEED_JSON) : null;
        if (!TextUtils.isEmpty(preferences)) {
            try {
                return new JSONObject(preferences);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            String str2 = com.til.colombia.android.internal.e.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(System.getProperty("http.agent"));
            stringBuilder.append(Utils.LOG_TAG_VER);
            httpURLConnection.setRequestProperty(str2, stringBuilder.toString());
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
                return null;
            }
            JSONObject jSONObject = new JSONObject(stringBuilder.toString());
            Utils.setPreferences(this.mContext, Utils.FEED_PREF, Utils.FEED_JSON, jSONObject.toString());
            Utils.setPreferences(this.mContext, Utils.FEED_PREF, Utils.FEED_TIMESTAMP, System.currentTimeMillis() / 1000);
            return jSONObject;
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        }
    }

    public final void updateAuds() {
        if (this.dmpAuds != null) {
            new a(this.dmpAuds, (byte) 0).execute(new Void[0]);
        }
    }

    public final String getAuds() {
        return this.dmpAuds != null ? this.dmpAuds.b() : null;
    }

    public final String[] getAudsArray() {
        if (this.dmpAuds == null) {
            return new String[0];
        }
        a aVar = this.dmpAuds;
        if (aVar.b() == null) {
            return new String[0];
        }
        return aVar.b().split(",");
    }

    public static void enablePersona(Context context) {
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.PERSONA_DISABLE, 0);
        if (getInstance() == null) {
            initialize(context);
        }
    }

    public static void disablePersona(Context context) {
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.PERSONA_DISABLE, 1);
    }

    public final void syncSSO(String str) {
        if (this.mContext != null && str != null && Utils.getAAID(this.mContext) != null && !Utils.getAAID(this.mContext).isEmpty() && !str.isEmpty()) {
            new e(str, Utils.getAAID(this.mContext)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private HttpURLConnection connectPartner(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(buildSsoSyncUrl(str, str2)).openConnection();
            try {
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                String str3 = com.til.colombia.android.internal.e.c;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(System.getProperty("http.agent"));
                stringBuilder.append(Utils.LOG_TAG_VER);
                httpURLConnection.setRequestProperty(str3, stringBuilder.toString());
                httpURLConnection.setRequestProperty("_col_uuid", str2);
                httpURLConnection.setRequestMethod(HttpMethods.GET);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.connect();
                httpURLConnection.getResponseCode();
                return httpURLConnection;
            } catch (Exception unused) {
                return httpURLConnection;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    private String buildSsoSyncUrl(String str, String str2) {
        return new Builder().encodedPath(Utils.getSsoSyncUrl()).appendQueryParameter("pid", Utils.PARTNER_ID).appendQueryParameter("cid", str).appendQueryParameter("time", String.valueOf(System.currentTimeMillis())).build().toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a8  */
    private boolean sendColombiaEvents(org.json.JSONObject r7) {
        /*
        r6 = this;
        r0 = r6.mContext;
        r0 = com.til.colombia.dmp.android.Utils.checkNetworkAvailibility(r0);
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r0 = 0;
        r2 = new java.net.URL;	 Catch:{ Exception -> 0x009c }
        r3 = com.til.colombia.dmp.android.Utils.getDmpUrl();	 Catch:{ Exception -> 0x009c }
        r2.<init>(r3);	 Catch:{ Exception -> 0x009c }
        r2 = r2.openConnection();	 Catch:{ Exception -> 0x009c }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ Exception -> 0x009c }
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2.setConnectTimeout(r0);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2.setReadTimeout(r0);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r0 = 1;
        r2.setDoOutput(r0);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = "User-Agent";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4.<init>();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r5 = "http.agent";
        r5 = java.lang.System.getProperty(r5);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4.append(r5);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r5 = "dmp-aos:1.6.0";
        r4.append(r5);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = "Content-Type";
        r4 = "application/json; charset=UTF-8";
        r2.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = "POST";
        r2.setRequestMethod(r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2.setUseCaches(r1);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = r2.getOutputStream();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3.write(r7);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3.flush();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2.connect();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r7 = r2.getResponseCode();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r7 = r7 / 10;
        r3 = 20;
        if (r7 != r3) goto L_0x008e;
    L_0x0071:
        r7 = "dmp-aos:1.6.0";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = "DMP EVENTS PUBLISHED.";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = r2.getResponseCode();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3.append(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        android.util.Log.i(r7, r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        if (r2 == 0) goto L_0x008d;
    L_0x008a:
        r2.disconnect();
    L_0x008d:
        return r0;
    L_0x008e:
        if (r2 == 0) goto L_0x00a5;
    L_0x0090:
        r2.disconnect();
        goto L_0x00a5;
    L_0x0094:
        r7 = move-exception;
        goto L_0x00a6;
    L_0x0096:
        r7 = move-exception;
        r0 = r2;
        goto L_0x009d;
    L_0x0099:
        r7 = move-exception;
        r2 = r0;
        goto L_0x00a6;
    L_0x009c:
        r7 = move-exception;
    L_0x009d:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);	 Catch:{ all -> 0x0099 }
        if (r0 == 0) goto L_0x00a5;
    L_0x00a2:
        r0.disconnect();
    L_0x00a5:
        return r1;
    L_0x00a6:
        if (r2 == 0) goto L_0x00ab;
    L_0x00a8:
        r2.disconnect();
    L_0x00ab:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.dmp.android.DmpManager.sendColombiaEvents(org.json.JSONObject):boolean");
    }
}

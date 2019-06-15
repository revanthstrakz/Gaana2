package com.timespointssdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class g extends a {
    private static String e = "";
    private static e f;

    private static class a extends AsyncTask<String, String, JSONObject> {
        private a() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public JSONObject doInBackground(String... strArr) {
            g gVar = new g();
            String str = strArr[0];
            String str2 = strArr[1];
            return g.a(str, str2, 1, g.b("accesstoken"), g.d(str2));
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (g.b()) {
                g.a(true);
            } else if (jSONObject != null && jSONObject.length() != 0) {
                try {
                    Integer valueOf = Integer.valueOf(jSONObject.getInt("code"));
                    String string = jSONObject.getString(NativeProtocol.WEB_DIALOG_PARAMS);
                    if (valueOf.intValue() != 404) {
                        JSONObject jSONObject2 = new JSONObject(jSONObject.getString(CBConstant.RESPONSE));
                        String string2 = jSONObject2.getString("status");
                        String string3 = jSONObject2.getString("responseCode");
                        if (jSONObject.getInt("code") != 200) {
                            if (jSONObject.getInt("code") != 202) {
                                if (g.b("logout").equals("yes")) {
                                    g.a("logoutinonecall");
                                }
                                g.h(string);
                            }
                        }
                        if (string2.equalsIgnoreCase("Success")) {
                            g.e = "success";
                            if (g.b("logout").equals("yes")) {
                                if (!g.b("logoutinonecall").equals("yes")) {
                                    g.a(g.f.d(), g.f.c(), g.f.b(), g.f.e());
                                }
                                g.a("logout");
                                g.a("logoutinonecall");
                            } else if (g.b("activitypaused").equals("yes")) {
                                g.a("activitypaused");
                                g.a("logout");
                                g.a("logoutinonecall");
                            } else {
                                g.a("logoutinonecall");
                            }
                        } else if (string3.equals("TP_106")) {
                            if (a.a.booleanValue()) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("GetAccessToken onPostExecute message = ");
                                stringBuilder.append(jSONObject2.getString("message"));
                                Log.d("TimesPointsConnect", stringBuilder.toString());
                            }
                            g.h(string);
                            g.d();
                        } else if (g.b("logout").equals("yes")) {
                            g.a("activityqueue");
                            d.b().clear();
                            if (!g.b("logoutinonecall").equals("yes")) {
                                g.a(g.f.d(), g.f.c(), g.f.b(), g.f.e());
                            }
                            g.a("logout");
                            g.a("logoutinonecall");
                        } else if (g.b("activitypaused").equals("yes")) {
                            g.h(string);
                            g.a("activitypaused");
                            g.a("logout");
                            g.a("logoutinonecall");
                        } else {
                            g.h(string);
                        }
                    } else {
                        g.a("logoutinonecall");
                        g.h(string);
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }
    }

    private static class b extends AsyncTask<String, String, JSONObject> {
        private b() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public JSONObject doInBackground(String... strArr) {
            g gVar = new g();
            String str = strArr[0];
            String str2 = strArr[1];
            return g.a(str, str2, 2, "", g.d(str2));
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject != null && jSONObject.length() != 0) {
                try {
                    if (jSONObject.getInt("code") != 200) {
                        if (jSONObject.getInt("code") != 200) {
                            g.e = "failiur";
                        }
                    }
                    JSONObject jSONObject2 = new JSONObject(jSONObject.getString(CBConstant.RESPONSE));
                    String string = jSONObject2.getString("status");
                    String string2 = jSONObject2.getString("responseCode");
                    if (g.f == null) {
                        g.f = d.c();
                    }
                    StringBuilder stringBuilder;
                    if (string.equalsIgnoreCase("Success")) {
                        g.e = "success";
                        jSONObject = new JSONObject(jSONObject2.getString(CBConstant.RESPONSE));
                        g.a(jSONObject.getString("authAccessToken"), "accesstoken");
                        g.a(jSONObject.getString("refreshToken"), "refreshtoken");
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(jSONObject.getLong("issuedTime"));
                        stringBuilder2.append("");
                        g.a(stringBuilder2.toString(), "issuetime");
                        g.c();
                    } else if (string2.equals("TP_107")) {
                        if (a.a.booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("GetAccessToken onPostExecute message = ");
                            stringBuilder.append(jSONObject2.getString("message"));
                            Log.d("TimesPointsConnect", stringBuilder.toString());
                        }
                        string = g.b("userid");
                        String b = g.f.b();
                        if (!(!g.b("logout").equals("yes") || string == null || b == null || string.equals(b))) {
                            g.a("activityqueue");
                            d.b().clear();
                            g.a("logout");
                            g.a("logoutinonecall");
                            g.a(g.f.d(), g.f.c(), g.f.b(), g.f.e());
                        }
                    } else if (string2.equals("TP_116")) {
                        if (a.a.booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("GetAccessToken onPostExecute message = ");
                            stringBuilder.append(jSONObject2.getString("message"));
                            Log.d("TimesPointsConnect", stringBuilder.toString());
                        }
                        g.j();
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }
    }

    private static class c extends AsyncTask<String, String, JSONObject> {
        private c() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public JSONObject doInBackground(String... strArr) {
            String str = strArr[0];
            String str2 = strArr[1];
            g gVar = new g();
            return g.a(str, str2, 2, "", g.d(str2));
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject != null && jSONObject.length() != 0) {
                try {
                    JSONObject jSONObject2;
                    StringBuilder stringBuilder;
                    if (jSONObject.getInt("code") != 200) {
                        if (jSONObject.getInt("code") != 200) {
                            jSONObject2 = new JSONObject(jSONObject.getString(CBConstant.RESPONSE));
                            if (a.a.booleanValue()) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Error = ");
                                stringBuilder.append(jSONObject2.getString("responseCode"));
                                stringBuilder.append(" message = ");
                                stringBuilder.append(jSONObject2.getString("message"));
                                Log.d("TimesPointsConnect", stringBuilder.toString());
                            }
                        }
                    }
                    jSONObject2 = new JSONObject(jSONObject.getString(CBConstant.RESPONSE));
                    if (jSONObject2.getString("status").equalsIgnoreCase("SUCCESS")) {
                        if (a.a.booleanValue()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("parentResponse.toString() = ");
                            stringBuilder.append(jSONObject2.toString());
                            Log.d("TimesPointsConnect", stringBuilder.toString());
                        }
                        jSONObject = new JSONObject(jSONObject2.getString(CBConstant.RESPONSE));
                        if (g.f == null) {
                            g.f = d.c();
                        }
                        g.f.a(jSONObject.optJSONArray("priority"));
                        g.a(jSONObject.getString("accessToken"), "accesstoken");
                        g.a(jSONObject.getString("refreshToken"), "refreshtoken");
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(jSONObject.getLong("issuedTime"));
                        stringBuilder2.append("");
                        g.a(stringBuilder2.toString(), "issuetime");
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(jSONObject.getLong("serverTime"));
                        stringBuilder2.append("");
                        g.a(stringBuilder2.toString(), "servertime");
                        g.a(jSONObject.getString("profileUrl"), "profileLink");
                        JSONArray jSONArray = jSONObject.getJSONArray("tParams");
                        int length = jSONArray.length();
                        String str = "";
                        for (int i = 0; i < length; i++) {
                            StringBuilder stringBuilder3;
                            if (i == length - 1) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append(str);
                                stringBuilder3.append(jSONArray.getString(i));
                                str = stringBuilder3.toString();
                            } else {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append(str);
                                stringBuilder3.append(jSONArray.getString(i));
                                stringBuilder3.append(",");
                                str = stringBuilder3.toString();
                            }
                        }
                        g.a(str, "tParams");
                        Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("clearQueue"));
                        if (valueOf != null && valueOf.booleanValue()) {
                            d.b().clear();
                            g.a("activityqueue");
                        }
                        g.f.a(Integer.valueOf(jSONObject.getInt("thCount")));
                        g.f.b(Integer.valueOf(jSONObject.getInt("thTime")));
                        g.c();
                        g.e(jSONObject.getString("initData"));
                    } else if (a.a.booleanValue()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Error = ");
                        stringBuilder.append(jSONObject2.getString("responseCode"));
                        stringBuilder.append(" message = ");
                        stringBuilder.append(jSONObject2.getString("message"));
                        Log.d("TimesPointsConnect", stringBuilder.toString());
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }
    }

    g() {
    }

    protected static void a(String str, String str2) {
        SharedPreferences a = a(d.d());
        if (a != null) {
            a.edit().putString(str2, str).apply();
        } else {
            Log.e("-->", "sharedPreference is NULL");
        }
    }

    protected static void a() {
        SharedPreferences sharedPreferences = d.d().getSharedPreferences("LogoutSharedPref", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean("islogoutclick", true).apply();
        } else {
            Log.e("-->", "sharedPreference is NULL");
        }
    }

    protected static boolean b() {
        SharedPreferences sharedPreferences = d.d().getSharedPreferences("LogoutSharedPref", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("islogoutclick", false);
        }
        Log.e("-->", "sharedPreference is NULL");
        return false;
    }

    protected static void a(String str) {
        SharedPreferences a = a(d.d());
        if (a != null) {
            a.edit().remove(str).apply();
        }
    }

    protected static void a(boolean z) {
        SharedPreferences sharedPreferences = d.d().getSharedPreferences("LogoutSharedPref", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
            Log.e("TimesPointsConnect", "removeAllSharedPref-1");
        }
        if (a(d.d()) != null) {
            a("userid");
            f();
            Log.e("TimesPointsConnect", "removeAllSharedPref-0");
            if (z) {
                j();
            }
        }
    }

    protected static String b(String str) {
        SharedPreferences a = a(d.d());
        if (a != null) {
            return a.getString(str, "");
        }
        Log.e("-->", "sharedPreference is NULL");
        return "";
    }

    private static SharedPreferences a(Context context) {
        if (context == null) {
            context = d.d();
        }
        return context != null ? PreferenceManager.getDefaultSharedPreferences(context) : null;
    }

    protected static String c() {
        if (a.a.booleanValue()) {
            Log.d("TimesPointsConnect", "flushQUEUEToServer");
        }
        if (f == null) {
            f = d.c();
        }
        Queue b = d.b();
        if (b == null || b.size() <= 0) {
            if (a.a.booleanValue()) {
                Log.d("TimesPointsConnect", "TPCReceiver onReceive nothing to flush");
            }
            if (b()) {
                a(true);
            } else if (b("logout").equals("yes")) {
                a("logout");
                if (!b("logoutinonecall").equals("yes")) {
                    a(f.d(), f.c(), f.b(), f.e());
                }
            }
        } else {
            String str = "https://tpapi.timespoints.com/v1/activity/bpush";
            String h = h();
            a aVar = new a();
            if (VERSION.SDK_INT >= 11) {
                Log.d("TimesPointsConnect", "call to executor");
                aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str, h});
            } else {
                aVar.execute(new String[]{str, h});
            }
        }
        return e;
    }

    protected static String d() {
        if (a.a.booleanValue()) {
            Log.d("TimesPointsConnect", "upDateAccessToken");
        }
        String str = "https://tpapi.timespoints.com/v1/token/refresh";
        String i = i();
        b bVar = new b();
        if (VERSION.SDK_INT >= 11) {
            bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str, i});
        } else {
            bVar.execute(new String[]{str, i});
        }
        return e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094 A:{LOOP_END, LOOP:2: B:27:0x0092->B:28:0x0094, Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac, all -> 0x00b5 }} */
    protected static org.json.JSONArray e() throws java.lang.NullPointerException {
        /*
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = f;
        if (r1 != 0) goto L_0x000f;
    L_0x0009:
        r1 = com.timespointssdk.d.c();
        f = r1;
    L_0x000f:
        r1 = com.timespointssdk.d.b();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = "logout";
        r2 = b(r2);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r3 = "activitypaused";
        r3 = b(r3);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r4 = 0;
        if (r2 == 0) goto L_0x003e;
    L_0x0022:
        r5 = "";
        r2 = r2.equals(r5);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        if (r2 != 0) goto L_0x003e;
    L_0x002a:
        r2 = r1.size();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
    L_0x002e:
        if (r4 >= r2) goto L_0x00a2;
    L_0x0030:
        r3 = r0.length();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r5 = r1.remove();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r0.put(r3, r5);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r4 = r4 + 1;
        goto L_0x002e;
    L_0x003e:
        if (r3 == 0) goto L_0x005c;
    L_0x0040:
        r2 = "";
        r2 = r3.equals(r2);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        if (r2 != 0) goto L_0x005c;
    L_0x0048:
        r2 = r1.size();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
    L_0x004c:
        if (r4 >= r2) goto L_0x00a2;
    L_0x004e:
        r3 = r0.length();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r5 = r1.remove();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r0.put(r3, r5);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r4 = r4 + 1;
        goto L_0x004c;
    L_0x005c:
        r2 = com.timespointssdk.a.d;	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2.intValue();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = f;	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        if (r2 == 0) goto L_0x008e;
    L_0x0065:
        r2 = f;	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = r2.g();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        if (r2 != 0) goto L_0x006e;
    L_0x006d:
        goto L_0x008e;
    L_0x006e:
        r2 = f;	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = r2.f();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = r2.intValue();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r3 = r1.size();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        if (r2 <= r3) goto L_0x0083;
    L_0x007e:
        r2 = r1.size();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        goto L_0x0092;
    L_0x0083:
        r2 = f;	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = r2.f();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = r2.intValue();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        goto L_0x0092;
    L_0x008e:
        r2 = r1.size();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
    L_0x0092:
        if (r4 >= r2) goto L_0x00a2;
    L_0x0094:
        r3 = r0.length();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r5 = r1.remove();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r0.put(r3, r5);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r4 = r4 + 1;
        goto L_0x0092;
    L_0x00a2:
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        r2 = "activityqueue";
        a(r1, r2);	 Catch:{ JSONException -> 0x00b1, Exception -> 0x00ac }
        return r0;
    L_0x00ac:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x00b5 }
        return r0;
    L_0x00b1:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x00b5 }
    L_0x00b5:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.timespointssdk.g.e():org.json.JSONArray");
    }

    protected static void f() throws NullPointerException {
        Queue b = d.b();
        if (!b.isEmpty()) {
            b.clear();
        }
        a(b.toString(), "activityqueue");
    }

    protected static Queue<JSONObject> c(String str) throws NullPointerException {
        LinkedList linkedList = new LinkedList();
        if (str != null) {
            try {
                if (!str.equals("")) {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        linkedList.add(jSONArray.getJSONObject(i));
                    }
                    return linkedList;
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            } catch (Throwable unused) {
            }
        }
        return linkedList;
    }

    protected static String g() {
        JSONObject jSONObject = new JSONObject();
        if (f == null) {
            f = d.c();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("uid", b("userid"));
            jSONObject2.put("pcode", b("pcode"));
            jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
            jSONObject.put("deviceId", b("deviceid"));
            jSONObject.put("platform", "android");
            String b = b("userid");
            if (b == null || b.equals("")) {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "PREAUTH");
            } else {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "AUTH2");
            }
            jSONObject.put("appVersion", "1.0");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return jSONObject.toString();
    }

    protected static String h() {
        JSONObject jSONObject = new JSONObject();
        if (f == null) {
            f = d.c();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("uid", b("userid"));
            jSONObject2.put("pcode", b("pcode"));
            jSONObject2.put("scode", b("scode"));
            jSONObject2.put("activity", e());
            jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
            jSONObject.put("deviceId", f.e());
            jSONObject.put("platform", "android");
            jSONObject.put("appVersion", "1.0");
            String b = b("userid");
            if (b == null || b.equals("")) {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "PREAUTH");
            } else {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "AUTH2");
            }
            if (a.a.booleanValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getBulkPushParams = ");
                stringBuilder.append(jSONObject.toString());
                Log.d("TimesPointsConnect", stringBuilder.toString());
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return jSONObject.toString();
    }

    protected static String i() {
        if (f == null) {
            f = d.c();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("uid", b("userid"));
            jSONObject2.put("pcode", b("pcode"));
            jSONObject2.put("issuedTime", b("issuetime"));
            jSONObject2.put("refreshToken", b("refreshtoken"));
            jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
            jSONObject.put("deviceId", f.e());
            jSONObject.put("platform", "android");
            jSONObject.put("appVersion", "1.0");
            String b = b("userid");
            if (b == null || b.equals("")) {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "PREAUTH");
            } else {
                jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "AUTH2");
            }
            if (a.a.booleanValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getUpdateAccessTokenURL = ");
                stringBuilder.append(jSONObject.toString());
                Log.d("TimesPointsConnect", stringBuilder.toString());
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0153=Splitter:B:35:0x0153, B:53:0x01ab=Splitter:B:53:0x01ab, B:44:0x017f=Splitter:B:44:0x017f} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01fa  */
    protected static org.json.JSONObject a(java.lang.String r5, java.lang.String r6, int r7, java.lang.String r8, java.lang.String r9) {
        /*
        r0 = com.timespointssdk.a.a;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0026;
    L_0x0008:
        r0 = "TimesPointsConnect";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "URL = ";
        r1.append(r2);
        r1.append(r5);
        r2 = " urlParameters = ";
        r1.append(r2);
        r1.append(r6);
        r1 = r1.toString();
        android.util.Log.d(r0, r1);
    L_0x0026:
        r0 = f;
        if (r0 != 0) goto L_0x0030;
    L_0x002a:
        r0 = com.timespointssdk.d.c();
        f = r0;
    L_0x0030:
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = 0;
        r3 = "code";
        r4 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        r0.put(r3, r4);	 Catch:{ JSONException -> 0x01aa, MalformedURLException -> 0x017e, Exception -> 0x0152 }
        r3 = new java.net.URL;	 Catch:{ JSONException -> 0x01aa, MalformedURLException -> 0x017e, Exception -> 0x0152 }
        r3.<init>(r5);	 Catch:{ JSONException -> 0x01aa, MalformedURLException -> 0x017e, Exception -> 0x0152 }
        r5 = r3.openConnection();	 Catch:{ JSONException -> 0x01aa, MalformedURLException -> 0x017e, Exception -> 0x0152 }
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ JSONException -> 0x01aa, MalformedURLException -> 0x017e, Exception -> 0x0152 }
        r2 = 1;
        r5.setDoOutput(r2);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r5.setDoInput(r2);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r3 = "Content-Type";
        r4 = "application/json";
        r5.setRequestProperty(r3, r4);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r3 = "Accept";
        r4 = "application/json";
        r5.setRequestProperty(r3, r4);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        if (r7 != r2) goto L_0x0074;
    L_0x0064:
        r7 = "authAccessToken";
        r5.setRequestProperty(r7, r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = "hmac";
        r5.setRequestProperty(r7, r9);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = "params";
        r0.put(r7, r6);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        goto L_0x007e;
    L_0x0074:
        r7 = "hmac";
        r5.setRequestProperty(r7, r9);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = "params";
        r0.put(r7, r6);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
    L_0x007e:
        r7 = "POST";
        r5.setRequestMethod(r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r5.setReadTimeout(r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r5.setConnectTimeout(r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = new java.io.OutputStreamWriter;	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = r5.getOutputStream();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.<init>(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.write(r6);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.flush();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6 = new java.io.BufferedReader;	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = new java.io.InputStreamReader;	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = r5.getInputStream();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.<init>(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6.<init>(r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
    L_0x00a9:
        r7 = r6.readLine();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        if (r7 == 0) goto L_0x00b3;
    L_0x00af:
        r1.append(r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        goto L_0x00a9;
    L_0x00b3:
        r6 = "code";
        r7 = r5.getResponseCode();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r0.put(r6, r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6 = "response";
        r7 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r0.put(r6, r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6 = "TimesPointsConnect";
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.<init>();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = "responseOutput.toString(1) : ";
        r7.append(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r9 = 0;
        r2 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r2 = r2.length();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r2 = r2 / 2;
        r8 = r8.substring(r9, r2);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.append(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = r7.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        android.util.Log.e(r6, r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6 = "TimesPointsConnect";
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.<init>();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = "responseOutput.toString(2) : ";
        r7.append(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r9 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r9 = r9.length();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r9 = r9 / 2;
        r1 = r1.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r1 = r1.length();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r8 = r8.substring(r9, r1);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7.append(r8);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r7 = r7.toString();	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        android.util.Log.e(r6, r7);	 Catch:{ JSONException -> 0x014c, MalformedURLException -> 0x0149, Exception -> 0x0146, all -> 0x01d6 }
        r6 = com.timespointssdk.a.a;
        r6 = r6.booleanValue();
        if (r6 == 0) goto L_0x0140;
    L_0x0126:
        r6 = "TimesPointsConnect";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "output = ";
        r7.append(r8);
        r8 = r0.toString();
        r7.append(r8);
        r7 = r7.toString();
        android.util.Log.d(r6, r7);
    L_0x0140:
        if (r5 == 0) goto L_0x0145;
    L_0x0142:
        r5.disconnect();
    L_0x0145:
        return r0;
    L_0x0146:
        r6 = move-exception;
        r2 = r5;
        goto L_0x0153;
    L_0x0149:
        r6 = move-exception;
        r2 = r5;
        goto L_0x017f;
    L_0x014c:
        r6 = move-exception;
        r2 = r5;
        goto L_0x01ab;
    L_0x014f:
        r5 = r2;
        goto L_0x01d6;
    L_0x0152:
        r6 = move-exception;
    L_0x0153:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x014f }
        r5 = com.timespointssdk.a.a;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x0178;
    L_0x015e:
        r5 = "TimesPointsConnect";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "output = ";
        r6.append(r7);
        r7 = r0.toString();
        r6.append(r7);
        r6 = r6.toString();
        android.util.Log.d(r5, r6);
    L_0x0178:
        if (r2 == 0) goto L_0x017d;
    L_0x017a:
        r2.disconnect();
    L_0x017d:
        return r0;
    L_0x017e:
        r6 = move-exception;
    L_0x017f:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x014f }
        r5 = com.timespointssdk.a.a;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x01a4;
    L_0x018a:
        r5 = "TimesPointsConnect";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "output = ";
        r6.append(r7);
        r7 = r0.toString();
        r6.append(r7);
        r6 = r6.toString();
        android.util.Log.d(r5, r6);
    L_0x01a4:
        if (r2 == 0) goto L_0x01a9;
    L_0x01a6:
        r2.disconnect();
    L_0x01a9:
        return r0;
    L_0x01aa:
        r6 = move-exception;
    L_0x01ab:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x014f }
        r5 = com.timespointssdk.a.a;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x01d0;
    L_0x01b6:
        r5 = "TimesPointsConnect";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "output = ";
        r6.append(r7);
        r7 = r0.toString();
        r6.append(r7);
        r6 = r6.toString();
        android.util.Log.d(r5, r6);
    L_0x01d0:
        if (r2 == 0) goto L_0x01d5;
    L_0x01d2:
        r2.disconnect();
    L_0x01d5:
        return r0;
    L_0x01d6:
        r6 = com.timespointssdk.a.a;
        r6 = r6.booleanValue();
        if (r6 == 0) goto L_0x01f8;
    L_0x01de:
        r6 = "TimesPointsConnect";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "output = ";
        r7.append(r8);
        r8 = r0.toString();
        r7.append(r8);
        r7 = r7.toString();
        android.util.Log.d(r6, r7);
    L_0x01f8:
        if (r5 == 0) goto L_0x01fd;
    L_0x01fa:
        r5.disconnect();
    L_0x01fd:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.timespointssdk.g.a(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String):org.json.JSONObject");
    }

    protected static void a(String str, String str2, String str3, String str4) {
        if (f == null) {
            f = d.c();
        }
        if (f != null) {
            a(str, "pcode");
            a(str3, "userid");
            a(str2, "scode");
            a(str4, "deviceid");
            f.d(str4);
            if (a.b.booleanValue()) {
                Calendar instance = Calendar.getInstance();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Starting init server call for user = ");
                stringBuilder.append(str3);
                stringBuilder.append(" at time ");
                stringBuilder.append(instance.getTimeInMillis());
                Log.d("TimesPointsConnect_API", stringBuilder.toString());
            }
            j();
        }
    }

    protected static void j() {
        c cVar = new c();
        if (VERSION.SDK_INT >= 11) {
            cVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"https://tpapi.timespoints.com/v1/init/", g()});
            return;
        }
        cVar.execute(new String[]{"https://tpapi.timespoints.com/v1/init/", g()});
    }

    private static void h(String str) {
        if (a.a.booleanValue()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("addToBackOFQUEUE params = ");
            stringBuilder.append(str);
            Log.d("TimesPointsConnect", stringBuilder.toString());
        }
        if (str != null) {
            try {
                if (!str.equals("")) {
                    JSONArray jSONArray = new JSONObject(str).getJSONObject(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID).getJSONArray("activity");
                    Queue b = d.b();
                    if (jSONArray != null && jSONArray.length() > 0) {
                        int length = jSONArray.length();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("addToBackOFQUEUE length = ");
                        stringBuilder2.append(length);
                        Log.d("TimesPointsConnect", stringBuilder2.toString());
                        for (int i = 0; i < length; i++) {
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("addToBackOFQUEUE jsonArraySentActivities.getJSONObject(i) = ");
                            stringBuilder3.append(jSONArray.getJSONObject(i));
                            Log.d("TimesPointsConnect", stringBuilder3.toString());
                            b.add(jSONArray.getJSONObject(i));
                        }
                    }
                    a(b.toString(), "activityqueue");
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    protected static String d(String str) {
        String str2 = "057050102050056053";
        String str3 = "052054048097057";
        String str4 = "107105054050100";
        String str5 = "056048056049048053";
        String str6 = "051048050053097099";
        String str7 = "102049056100051053";
        Long valueOf = Long.valueOf(Long.parseLong(str2));
        String str8 = "";
        String str9 = str2;
        Long valueOf2 = Long.valueOf(Long.parseLong("0530051114627"));
        Long valueOf3 = Long.valueOf(Long.parseLong(str3));
        String str10 = str3;
        Long valueOf4 = Long.valueOf(Long.parseLong("102100841712618615"));
        Long valueOf5 = Long.valueOf(Long.parseLong(str4));
        String str11 = str4;
        Long valueOf6 = Long.valueOf(Long.parseLong("101163369664947316"));
        Long valueOf7 = Long.valueOf(Long.parseLong(str5));
        String str12 = str5;
        Long valueOf8 = Long.valueOf(Long.parseLong("115857683891753071"));
        Long valueOf9 = Long.valueOf(Long.parseLong(str6));
        String str13 = str6;
        Long valueOf10 = Long.valueOf(Long.parseLong("051077257571527366"));
        String str14 = str7;
        Long valueOf11 = Long.valueOf(Long.parseLong(str7));
        Long valueOf12 = Long.valueOf(Long.parseLong("102102944105832510"));
        valueOf2 = Long.valueOf(valueOf.longValue() ^ valueOf2.longValue());
        valueOf3 = Long.valueOf(valueOf3.longValue() ^ valueOf4.longValue());
        valueOf4 = Long.valueOf(valueOf5.longValue() ^ valueOf6.longValue());
        valueOf5 = Long.valueOf(valueOf7.longValue() ^ valueOf8.longValue());
        valueOf6 = Long.valueOf(valueOf9.longValue() ^ valueOf10.longValue());
        valueOf7 = Long.valueOf(valueOf11.longValue() ^ valueOf12.longValue());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str9);
        stringBuilder.append("0");
        stringBuilder.append(valueOf2);
        stringBuilder.append(str10);
        stringBuilder.append(valueOf3);
        stringBuilder.append(str11);
        stringBuilder.append(valueOf4);
        stringBuilder.append(str12);
        stringBuilder.append("0");
        stringBuilder.append(valueOf5);
        stringBuilder.append(str13);
        stringBuilder.append("0");
        stringBuilder.append(valueOf6);
        stringBuilder.append(str14);
        stringBuilder.append("0");
        stringBuilder.append(valueOf7);
        String[] strArr = new String[68];
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder.toString());
        int i = 0;
        while (i < 68) {
            strArr[i] = stringBuilder2.substring(0, 3);
            i++;
            stringBuilder2 = new StringBuilder(stringBuilder2.subSequence(3, stringBuilder2.length()));
        }
        str3 = str8;
        for (String parseInt : strArr) {
            int parseInt2 = Integer.parseInt(parseInt);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str3);
            stringBuilder3.append(Character.valueOf((char) parseInt2));
            str3 = stringBuilder3.toString();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str3.getBytes(), "HmacSHA256");
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 0);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Exception hashing payload", e);
        }
    }

    static void e(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = new JSONObject(jSONArray.optString(i));
                String optString = jSONObject.optString("type");
                String optString2 = jSONObject.optString("html");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(optString);
                stringBuilder.append("====>");
                stringBuilder.append(optString2);
                Log.e("TimesPointsConnect", stringBuilder.toString());
                a(optString2, optString);
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("saveTimeToSharedPref key : ");
        stringBuilder.append(str);
        stringBuilder.append(" , value : ");
        stringBuilder.append(str2);
        Log.e("TimesPointsConnect", stringBuilder.toString());
        SharedPreferences a = a(d.d());
        if (a != null) {
            a.edit().putString(str, str2).apply();
        }
    }

    public static String k() {
        SharedPreferences a = a(d.d());
        return a != null ? a.getString("starttimeforintervalapi", "") : "";
    }

    public static String l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Calendar.getInstance().getTimeInMillis());
        stringBuilder.append("");
        return stringBuilder.toString();
    }

    public static void m() {
        b("starttimeforintervalapi", l());
    }
}

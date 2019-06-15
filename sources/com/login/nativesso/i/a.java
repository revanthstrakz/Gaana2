package com.login.nativesso.i;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.s;
import com.login.nativesso.d.c;
import com.login.nativesso.g.b;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    static class a extends AsyncTask<String, Void, String> {
        a() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public String doInBackground(String... strArr) {
            try {
                String l = Long.valueOf(System.currentTimeMillis() / 1000).toString();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://ade.clmbtech.com/uid/syncPartner.htm?pid=7558&cid=");
                stringBuilder.append(b.a().a("TICKETID", c.a().d()));
                stringBuilder.append("&time=");
                stringBuilder.append(l);
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(stringBuilder.toString()).openConnection();
                httpURLConnection.setRequestMethod(HttpMethods.GET);
                httpURLConnection.setRequestProperty("_col_uuid", b.a().a("TGID", c.a().d()));
                int responseCode = httpURLConnection.getResponseCode();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(responseCode);
                stringBuilder2.append("");
                return stringBuilder2.toString();
            } catch (Exception unused) {
                return "";
            }
        }
    }

    public static void a(Context context, com.login.nativesso.a.c cVar) {
        com.login.nativesso.b.a.a("CheckSkInitialzeCb", cVar);
        String b = b(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("content://");
        stringBuilder.append(b);
        stringBuilder.append("/tgid_file.txt");
        b = b(context, Uri.parse(stringBuilder.toString()));
        b a = b.a();
        s sVar;
        if (c.a(b)) {
            if (c.a(a.a("TGID", context))) {
                b = c.b(context);
                if (b == null) {
                    sVar = (s) com.login.nativesso.b.a.a("SdkInitializeCb");
                    if (sVar != null) {
                        sVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                        com.login.nativesso.b.a.b("SdkInitializeCb");
                        ((com.login.nativesso.a.c) com.login.nativesso.b.a.a("CheckSkInitialzeCb")).a();
                        com.login.nativesso.b.a.b("CheckSkInitialzeCb");
                    }
                    return;
                }
                a.a(context, "TGID", b);
            }
            try {
                b(context, a.a(context));
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                Log.e("NATIVESSO", "Error while writing CP Initialization");
                sVar = (s) com.login.nativesso.b.a.a("SdkInitializeCb");
                if (sVar != null) {
                    sVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                    com.login.nativesso.b.a.b("SdkInitializeCb");
                    ((com.login.nativesso.a.c) com.login.nativesso.b.a.a("CheckSkInitialzeCb")).a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                    com.login.nativesso.b.a.b("CheckSkInitialzeCb");
                    return;
                }
            }
        }
        try {
            String string;
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("TGID");
            try {
                string = jSONObject.getString("SSECID");
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
                string = null;
            }
            a.a(context, "TGID", b);
            if (c.a(a.a("LAST_SESSION_SRC", context))) {
                boolean a2 = c.a(string);
            }
        } catch (JSONException unused) {
            sVar = (s) com.login.nativesso.b.a.a("SdkInitializeCb");
            if (sVar != null) {
                sVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                com.login.nativesso.b.a.b("SdkInitializeCb");
                ((com.login.nativesso.a.c) com.login.nativesso.b.a.a("CheckSkInitialzeCb")).a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                com.login.nativesso.b.a.b("CheckSkInitialzeCb");
                return;
            }
        }
        c a3 = c.a();
        a3.getClass();
        a3.a(1);
        ((com.login.nativesso.a.c) com.login.nativesso.b.a.a("CheckSkInitialzeCb")).a();
        com.login.nativesso.b.a.b("CheckSkInitialzeCb");
    }

    public static JSONObject a(Context context) {
        String b = b(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("content://");
        stringBuilder.append(b);
        stringBuilder.append("/tgid_file.txt");
        String b2 = b(context, Uri.parse(stringBuilder.toString()));
        if (c.a(b2)) {
            return null;
        }
        try {
            return new JSONObject(b2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Error while parsing Json in readFromCP");
            return null;
        }
    }

    public static String b(Context context) {
        b a = b.a();
        String a2 = a.a("SSO_AUTHORITY", context);
        if (!c.a(a2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(a2);
            stringBuilder.append("/tgid_file.txt");
            if (a(context, Uri.parse(stringBuilder.toString()))) {
                return a2;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(8)) {
            ProviderInfo[] providerInfoArr = packageInfo.providers;
            if (providerInfoArr != null) {
                arrayList.addAll(Arrays.asList(providerInfoArr));
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            ProviderInfo providerInfo = (ProviderInfo) arrayList.get(i);
            if (providerInfo != null) {
                String str = providerInfo.authority;
                if (!c.a(str) && str.contains("androidcontentprovidersso.tg")) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("content://");
                    stringBuilder2.append(str);
                    stringBuilder2.append("/tgid_file.txt");
                    Uri parse = Uri.parse(stringBuilder2.toString());
                    if (a(context, providerInfo) && a(context, parse)) {
                        a.a(context, "SSO_AUTHORITY", str);
                        return str;
                    }
                }
            }
        }
        a2 = a.a("APP_AUTHORITY", context);
        a.a(context, "SSO_AUTHORITY", a2);
        return a2;
    }

    private static boolean a(Context context, ProviderInfo providerInfo) {
        ApplicationInfo applicationInfo = providerInfo.applicationInfo;
        if (applicationInfo != null) {
            String str = applicationInfo.packageName;
            if (!c.a(str)) {
                return c.b(context, str);
            }
        }
        return false;
    }

    public static boolean a(Context context, Uri uri) {
        return c.a(b(context, uri)) ^ 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0049 A:{Catch:{ all -> 0x003f }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0066 A:{SYNTHETIC, Splitter:B:40:0x0066} */
    public static java.lang.String b(android.content.Context r2, android.net.Uri r3) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r2 = r2.getContentResolver();	 Catch:{ Exception -> 0x0041 }
        r2 = r2.openInputStream(r3);	 Catch:{ Exception -> 0x0041 }
        if (r2 != 0) goto L_0x0025;
    L_0x0010:
        r3 = r0.toString();	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        if (r2 == 0) goto L_0x001e;
    L_0x0016:
        r2.close();	 Catch:{ IOException -> 0x001a }
        goto L_0x001e;
    L_0x001a:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
    L_0x001e:
        return r3;
    L_0x001f:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0064;
    L_0x0022:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0042;
    L_0x0025:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0022, all -> 0x001f }
    L_0x002f:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        if (r1 == 0) goto L_0x0039;
    L_0x0035:
        r0.append(r1);	 Catch:{ Exception -> 0x0022, all -> 0x001f }
        goto L_0x002f;
    L_0x0039:
        if (r2 == 0) goto L_0x005b;
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x005b;
    L_0x003f:
        r3 = move-exception;
        goto L_0x0064;
    L_0x0041:
        r3 = move-exception;
    L_0x0042:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);	 Catch:{ all -> 0x003f }
        r2 = r3 instanceof com.login.nativesso.exception.ServerException;	 Catch:{ all -> 0x003f }
        if (r2 == 0) goto L_0x0051;
    L_0x0049:
        r2 = new com.login.nativesso.exception.ServerException;	 Catch:{ all -> 0x003f }
        r3 = "SERVER_ERROR";
        r2.<init>(r3);	 Catch:{ all -> 0x003f }
        throw r2;	 Catch:{ all -> 0x003f }
    L_0x0051:
        if (r1 == 0) goto L_0x005b;
    L_0x0053:
        r1.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x005b;
    L_0x0057:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
    L_0x005b:
        r2 = r0.toString();
        r2 = r2.trim();
        return r2;
    L_0x0064:
        if (r1 == 0) goto L_0x006e;
    L_0x0066:
        r1.close();	 Catch:{ IOException -> 0x006a }
        goto L_0x006e;
    L_0x006a:
        r2 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
    L_0x006e:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.login.nativesso.i.a.b(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static void a(Context context, JSONObject jSONObject) throws IOException {
        b(context);
        b(context, jSONObject);
        try {
            String string = jSONObject.getString("TICKETID");
            if (string != null && string.length() > 0) {
                new a().execute(new String[0]);
            }
        } catch (Exception unused) {
        }
    }

    public static void b(Context context, JSONObject jSONObject) throws IOException {
        FileOutputStream openFileOutput;
        b a = b.a();
        String a2 = a.a("SSO_AUTHORITY", context);
        if (c.a(a2)) {
            a2 = b(context);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("content://");
        stringBuilder.append(a2);
        stringBuilder.append("/tgid_file.txt");
        a2 = stringBuilder.toString();
        String a3 = a.a("APP_AUTHORITY", context);
        stringBuilder = new StringBuilder();
        stringBuilder.append("content://");
        stringBuilder.append(a3);
        stringBuilder.append("/tgid_file.txt");
        a3 = stringBuilder.toString();
        Uri parse = Uri.parse(a2);
        if (a2.equals(a3)) {
            openFileOutput = context.openFileOutput("tgid_file.txt", 0);
        } else {
            openFileOutput = (FileOutputStream) context.getContentResolver().openOutputStream(parse, "rwt");
        }
        openFileOutput.write(jSONObject.toString().getBytes());
        openFileOutput.close();
    }

    public static String a(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }
}

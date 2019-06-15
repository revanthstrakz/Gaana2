package net.hockeyapp.android.c;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.gaana.login.LoginManager;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import net.hockeyapp.android.a;
import net.hockeyapp.android.d.e;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends c<Void, Void, Boolean> {
    private final int a;
    private final String b;
    private final Map<String, String> c;
    private Context d;
    private Handler e;
    private ProgressDialog f;
    private boolean g = true;

    public f(Context context, Handler handler, String str, int i, Map<String, String> map) {
        this.d = context;
        this.e = handler;
        this.b = str;
        this.a = i;
        this.c = map;
        if (context != null) {
            a.a(context);
        }
    }

    public void a(Context context, Handler handler) {
        this.d = context;
        this.e = handler;
    }

    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        if ((this.f == null || !this.f.isShowing()) && this.g) {
            this.f = ProgressDialog.show(this.d, "", "Please wait...", true, false);
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041 A:{Catch:{ UnsupportedEncodingException -> 0x0042, IOException -> 0x0038, all -> 0x0033, all -> 0x0054 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0057  */
    /* JADX WARNING: Missing block: B:12:0x002c, code skipped:
            if (r0 != null) goto L_0x004b;
     */
    /* JADX WARNING: Missing block: B:26:0x0049, code skipped:
            if (r0 != null) goto L_0x004b;
     */
    /* renamed from: a */
    public java.lang.Boolean doInBackground(java.lang.Void... r4) {
        /*
        r3 = this;
        r4 = 0;
        r0 = r3.a;	 Catch:{ UnsupportedEncodingException -> 0x0042, IOException -> 0x0038, all -> 0x0033 }
        r1 = r3.c;	 Catch:{ UnsupportedEncodingException -> 0x0042, IOException -> 0x0038, all -> 0x0033 }
        r0 = r3.a(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x0042, IOException -> 0x0038, all -> 0x0033 }
        r0.connect();	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        r4 = r0.getResponseCode();	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 != r1) goto L_0x002c;
    L_0x0014:
        r4 = net.hockeyapp.android.c.c.a(r0);	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        r1 = android.text.TextUtils.isEmpty(r4);	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        if (r1 != 0) goto L_0x002c;
    L_0x001e:
        r4 = r3.a(r4);	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ UnsupportedEncodingException -> 0x0031, IOException -> 0x002f }
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r0.disconnect();
    L_0x002b:
        return r4;
    L_0x002c:
        if (r0 == 0) goto L_0x004e;
    L_0x002e:
        goto L_0x004b;
    L_0x002f:
        r4 = move-exception;
        goto L_0x003c;
    L_0x0031:
        r4 = move-exception;
        goto L_0x0046;
    L_0x0033:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x0055;
    L_0x0038:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
    L_0x003c:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x004e;
    L_0x0041:
        goto L_0x004b;
    L_0x0042:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
    L_0x0046:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x004e;
    L_0x004b:
        r0.disconnect();
    L_0x004e:
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        return r4;
    L_0x0054:
        r4 = move-exception;
    L_0x0055:
        if (r0 == 0) goto L_0x005a;
    L_0x0057:
        r0.disconnect();
    L_0x005a:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.c.f.doInBackground(java.lang.Void[]):java.lang.Boolean");
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        if (this.f != null) {
            try {
                this.f.dismiss();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (this.e != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putBoolean("success", bool.booleanValue());
            message.setData(bundle);
            this.e.sendMessage(message);
        }
    }

    private HttpURLConnection a(int i, Map<String, String> map) throws IOException {
        if (i == 1) {
            return new e(this.b).a(HttpMethods.POST).a((Map) map).a();
        }
        if (i == 2) {
            return new e(this.b).a(HttpMethods.POST).b((String) map.get("email"), (String) map.get(LoginManager.TAG_PASSWORD)).a();
        }
        StringBuilder stringBuilder;
        if (i == 3) {
            String str = (String) map.get("type");
            String str2 = (String) map.get("id");
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append("?");
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(str2);
            return new e(stringBuilder.toString()).a();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Login mode ");
        stringBuilder.append(i);
        stringBuilder.append(" not supported.");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private boolean a(String str) {
        SharedPreferences sharedPreferences = this.d.getSharedPreferences("net.hockeyapp.android.login", 0);
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.getString("status");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (this.a == 1) {
                if (str.equals("identified")) {
                    str = jSONObject.getString("iuid");
                    if (!TextUtils.isEmpty(str)) {
                        sharedPreferences.edit().putString("iuid", str).apply();
                        return true;
                    }
                }
            } else if (this.a == 2) {
                if (str.equals("authorized")) {
                    str = jSONObject.getString("auid");
                    if (!TextUtils.isEmpty(str)) {
                        sharedPreferences.edit().putString("auid", str).apply();
                        return true;
                    }
                }
            } else if (this.a != 3) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Login mode ");
                stringBuilder.append(this.a);
                stringBuilder.append(" not supported.");
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (str.equals("validated")) {
                return true;
            } else {
                sharedPreferences.edit().remove("iuid").remove("auid").apply();
            }
            return false;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }
}

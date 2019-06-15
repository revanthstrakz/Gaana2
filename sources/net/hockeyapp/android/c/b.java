package net.hockeyapp.android.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import net.hockeyapp.android.a;
import net.hockeyapp.android.d.d;
import net.hockeyapp.android.d.j;
import net.hockeyapp.android.d.k;
import net.hockeyapp.android.l;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask<Void, String, JSONArray> {
    protected String a;
    protected String b;
    protected Boolean c;
    protected l d;
    private Context e;
    private long f;

    /* Access modifiers changed, original: protected */
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int a() {
        return Integer.parseInt(a.b);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public JSONArray doInBackground(Void... voidArr) {
        try {
            int a = a();
            JSONArray jSONArray = new JSONArray(j.a(this.e));
            if (b() && a(jSONArray, a)) {
                d.a("HockeyUpdate", "Returning cached JSON");
                return jSONArray;
            }
            URLConnection a2 = a(new URL(a("json")));
            a2.connect();
            InputStream bufferedInputStream = new BufferedInputStream(a2.getInputStream());
            String a3 = a(bufferedInputStream);
            bufferedInputStream.close();
            JSONArray jSONArray2 = new JSONArray(a3);
            if (a(jSONArray2, a)) {
                return b(jSONArray2);
            }
            return null;
        } catch (IOException | JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public URLConnection a(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        openConnection.addRequestProperty(e.c, "HockeySDK/Android 4.1.1");
        if (VERSION.SDK_INT <= 9) {
            openConnection.setRequestProperty("connection", "close");
        }
        return openConnection;
    }

    private boolean a(JSONArray jSONArray, int i) {
        int i2 = 0;
        boolean z = i2;
        while (i2 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                boolean z2 = jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) > i;
                boolean z3 = jSONObject.getInt(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION) == i && k.a(this.e, jSONObject.getLong(AvidJSONUtil.KEY_TIMESTAMP));
                boolean z4 = k.a(jSONObject.getString("minimum_os_version"), k.a(VERSION.RELEASE)) <= 0;
                if ((z2 || z3) && z4) {
                    if (jSONObject.has("mandatory")) {
                        this.c = Boolean.valueOf(this.c.booleanValue() | jSONObject.getBoolean("mandatory"));
                    }
                    z = true;
                }
                i2++;
            } catch (JSONException unused) {
                return false;
            }
        }
        return z;
    }

    private JSONArray b(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < Math.min(jSONArray.length(), 25); i++) {
            try {
                jSONArray2.put(jSONArray.get(i));
            } catch (JSONException unused) {
            }
        }
        return jSONArray2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(JSONArray jSONArray) {
        if (jSONArray != null) {
            d.a("HockeyUpdate", "Received Update Info");
            if (this.d != null) {
                this.d.a(jSONArray, a("apk"));
                return;
            }
            return;
        }
        d.a("HockeyUpdate", "No Update Info available");
        if (this.d != null) {
            this.d.a();
        }
    }

    /* Access modifiers changed, original: protected */
    public String a(String str) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.a);
        stringBuilder2.append("api/2/apps/");
        stringBuilder2.append(this.b != null ? this.b : this.e.getPackageName());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("?format=");
        stringBuilder3.append(str);
        stringBuilder2.append(stringBuilder3.toString());
        if (!TextUtils.isEmpty(Secure.getString(this.e.getContentResolver(), "android_id"))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("&udid=");
            stringBuilder.append(b(Secure.getString(this.e.getContentResolver(), "android_id")));
            stringBuilder2.append(stringBuilder.toString());
        }
        SharedPreferences sharedPreferences = this.e.getSharedPreferences("net.hockeyapp.android.login", 0);
        String string = sharedPreferences.getString("auid", null);
        if (!TextUtils.isEmpty(string)) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("&auid=");
            stringBuilder4.append(b(string));
            stringBuilder2.append(stringBuilder4.toString());
        }
        str = sharedPreferences.getString("iuid", null);
        if (!TextUtils.isEmpty(str)) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("&iuid=");
            stringBuilder3.append(b(str));
            stringBuilder2.append(stringBuilder3.toString());
        }
        stringBuilder2.append("&os=Android");
        stringBuilder = new StringBuilder();
        stringBuilder.append("&os_version=");
        stringBuilder.append(b(a.e));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&device=");
        stringBuilder.append(b(a.g));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&oem=");
        stringBuilder.append(b(a.h));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&app_version=");
        stringBuilder.append(b(a.b));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&sdk=");
        stringBuilder.append(b("HockeySDK"));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&sdk_version=");
        stringBuilder.append(b("4.1.1"));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&lang=");
        stringBuilder.append(b(Locale.getDefault().getLanguage()));
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("&usage_time=");
        stringBuilder.append(this.f);
        stringBuilder2.append(stringBuilder.toString());
        return stringBuilder2.toString();
    }

    private String b(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(readLine);
                    stringBuilder2.append("\n");
                    stringBuilder.append(stringBuilder2.toString());
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
                inputStream.close();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
                throw th;
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }
}

package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.appsflyer.y.AnonymousClass3;
import com.google.api.client.http.HttpMethods;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

final class ab extends AsyncTask<String, Void, String> {
    Map<String, String> a;
    String b;
    private boolean c = false;
    private String d = "";
    private boolean e = false;
    private WeakReference<Context> f;
    private URL g;
    private boolean h;
    private HttpURLConnection i;
    private boolean j;

    /* Access modifiers changed, original: protected|final */
    public final void onCancelled() {
    }

    ab(Context context, boolean z) {
        this.f = new WeakReference(context);
        this.h = true;
        this.j = true;
        this.c = z;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onPreExecute() {
        if (this.b == null) {
            this.b = new JSONObject(this.a).toString();
        }
    }

    /* Access modifiers changed, original: protected|final|varargs */
    /* renamed from: a */
    public final String doInBackground(String... strArr) {
        if (this.c) {
            return null;
        }
        StringBuilder stringBuilder;
        try {
            int length;
            this.g = new URL(strArr[0]);
            if (this.h) {
                ah.a().a(this.g.toString(), this.b);
                length = this.b.getBytes("UTF-8").length;
                stringBuilder = new StringBuilder("call = ");
                stringBuilder.append(this.g);
                stringBuilder.append("; size = ");
                stringBuilder.append(length);
                stringBuilder.append(" byte");
                stringBuilder.append(length > 1 ? "s" : "");
                stringBuilder.append("; body = ");
                stringBuilder.append(this.b);
                AnonymousClass3.b(stringBuilder.toString());
            }
            this.i = (HttpURLConnection) this.g.openConnection();
            this.i.setReadTimeout(30000);
            this.i.setConnectTimeout(30000);
            this.i.setRequestMethod(HttpMethods.POST);
            this.i.setDoInput(true);
            this.i.setDoOutput(true);
            this.i.setRequestProperty(MIME.CONTENT_TYPE, "application/json");
            OutputStream outputStream = this.i.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(this.b);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            this.i.connect();
            length = this.i.getResponseCode();
            if (this.j) {
                h.c();
                this.d = h.a(this.i);
            }
            if (this.h) {
                ah.a().a(this.g.toString(), length, this.d);
            }
            if (length == 200) {
                AFLogger.d("Status 200 ok");
                Context context = (Context) this.f.get();
                if (this.g.toString().startsWith(q.b(h.b)) && context != null) {
                    Editor edit = context.getSharedPreferences("appsflyer-data", 0).edit();
                    edit.putBoolean("sentRegisterRequestToAF", true);
                    edit.apply();
                    AFLogger.c("Successfully registered for Uninstall Tracking");
                }
            } else {
                this.e = true;
            }
        } catch (Throwable th) {
            stringBuilder = new StringBuilder("Error while calling ");
            stringBuilder.append(this.g.toString());
            AFLogger.a(stringBuilder.toString(), th);
            this.e = true;
        }
        return this.d;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void onPostExecute(String str) {
        if (this.e) {
            AFLogger.d("Connection error: ".concat(String.valueOf(str)));
        } else {
            AFLogger.d("Connection call succeeded: ".concat(String.valueOf(str)));
        }
    }

    /* Access modifiers changed, original: final */
    public final void a() {
        this.h = false;
    }
}

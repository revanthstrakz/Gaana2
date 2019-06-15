package net.hockeyapp.android.d;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.api.client.http.HttpMethods;
import com.payu.custombrowser.util.CBConstant;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.mime.MIME;

public class e {
    private final String a;
    private String b;
    private String c;
    private h d;
    private int e = CBConstant.VERIFY_HTTP_TIMEOUT;
    private final Map<String, String> f;

    public e(String str) {
        this.a = str;
        this.f = new HashMap();
        this.f.put(com.til.colombia.android.internal.e.c, "HockeySDK/Android 4.1.1");
    }

    public e a(String str) {
        this.b = str;
        return this;
    }

    public e b(String str) {
        this.c = str;
        return this;
    }

    public e a(Map<String, String> map) {
        try {
            String a = a((Map) map, "UTF-8");
            a(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            b(a);
            return this;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public e a(Map<String, String> map, Context context, List<Uri> list) {
        try {
            String lastPathSegment;
            this.d = new h();
            this.d.b();
            for (String lastPathSegment2 : map.keySet()) {
                this.d.a(lastPathSegment2, (String) map.get(lastPathSegment2));
            }
            for (int i = 0; i < list.size(); i++) {
                Uri uri = (Uri) list.get(i);
                boolean z = true;
                if (i != list.size() - 1) {
                    z = false;
                }
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                lastPathSegment2 = uri.getLastPathSegment();
                h hVar = this.d;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("attachment");
                stringBuilder.append(i);
                hVar.a(stringBuilder.toString(), lastPathSegment2, openInputStream, z);
            }
            this.d.c();
            String str = MIME.CONTENT_TYPE;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("multipart/form-data; boundary=");
            stringBuilder2.append(this.d.a());
            a(str, stringBuilder2.toString());
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public e a(String str, String str2) {
        this.f.put(str, str2);
        return this;
    }

    public e b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Basic ");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(":");
        stringBuilder2.append(str2);
        stringBuilder.append(b.a(stringBuilder2.toString().getBytes(), 2));
        a("Authorization", stringBuilder.toString());
        return this;
    }

    public HttpURLConnection a() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
        httpURLConnection.setConnectTimeout(this.e);
        httpURLConnection.setReadTimeout(this.e);
        if (VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        if (!TextUtils.isEmpty(this.b)) {
            httpURLConnection.setRequestMethod(this.b);
            if (!TextUtils.isEmpty(this.c) || this.b.equalsIgnoreCase(HttpMethods.POST) || this.b.equalsIgnoreCase(HttpMethods.PUT)) {
                httpURLConnection.setDoOutput(true);
            }
        }
        for (String str : this.f.keySet()) {
            httpURLConnection.setRequestProperty(str, (String) this.f.get(str));
        }
        if (!TextUtils.isEmpty(this.c)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bufferedWriter.write(this.c);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        if (this.d != null) {
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.d.d()));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(this.d.e().toByteArray());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return httpURLConnection;
    }

    private static String a(Map<String, String> map, String str) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            String str3 = (String) map.get(str2);
            String str22 = URLEncoder.encode(str22, str);
            str3 = URLEncoder.encode(str3, str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str22);
            stringBuilder.append("=");
            stringBuilder.append(str3);
            arrayList.add(stringBuilder.toString());
        }
        return TextUtils.join("&", arrayList);
    }
}

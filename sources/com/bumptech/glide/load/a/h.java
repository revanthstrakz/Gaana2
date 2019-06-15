package com.bumptech.glide.load.a;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.f.d;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.b.g;
import com.til.colombia.android.internal.e;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class h implements b<InputStream> {
    static final b a = new a();
    private final g b;
    private final int c;
    private final b d;
    private HttpURLConnection e;
    private InputStream f;
    private volatile boolean g;

    interface b {
        HttpURLConnection a(URL url) throws IOException;
    }

    private static class a implements b {
        a() {
        }

        public HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    public h(g gVar, int i) {
        this(gVar, i, a);
    }

    h(g gVar, int i, b bVar) {
        this.b = gVar;
        this.c = i;
        this.d = bVar;
    }

    public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super InputStream> aVar) {
        long a = d.a();
        try {
            Object a2 = a(this.b.a(), 0, null, this.b.b());
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Finished http url fetcher fetch in ");
                stringBuilder.append(d.a(a));
                stringBuilder.append(" ms and loaded ");
                stringBuilder.append(a2);
                Log.v("HttpUrlFetcher", stringBuilder.toString());
            }
            aVar.a(a2);
        } catch (IOException e) {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Failed to load data for url", e);
            }
            aVar.a(e);
        }
    }

    private InputStream a(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        if (i >= 5) {
            throw new HttpException("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new HttpException("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.e = this.d.a(url);
        for (Entry entry : map.entrySet()) {
            this.e.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        this.e.setConnectTimeout(this.c);
        this.e.setReadTimeout(this.c);
        this.e.setUseCaches(false);
        this.e.setDoInput(true);
        this.e.setInstanceFollowRedirects(false);
        this.e.connect();
        this.f = this.e.getInputStream();
        if (this.g) {
            return null;
        }
        int responseCode = this.e.getResponseCode();
        int i2 = responseCode / 100;
        if (i2 == 2) {
            return a(this.e);
        }
        if (i2 == 3) {
            String headerField = this.e.getHeaderField(e.e);
            if (TextUtils.isEmpty(headerField)) {
                throw new HttpException("Received empty or null redirect url");
            }
            URL url3 = new URL(url, headerField);
            a();
            return a(url3, i + 1, url, map);
        } else if (responseCode == -1) {
            throw new HttpException(responseCode);
        } else {
            throw new HttpException(this.e.getResponseMessage(), responseCode);
        }
    }

    private InputStream a(HttpURLConnection httpURLConnection) throws IOException {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.f = com.bumptech.glide.f.b.a(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Got non empty content encoding: ");
                stringBuilder.append(httpURLConnection.getContentEncoding());
                Log.d("HttpUrlFetcher", stringBuilder.toString());
            }
            this.f = httpURLConnection.getInputStream();
        }
        return this.f;
    }

    public void a() {
        if (this.f != null) {
            try {
                this.f.close();
            } catch (IOException unused) {
            }
        }
        if (this.e != null) {
            this.e.disconnect();
        }
        this.e = null;
    }

    public void b() {
        this.g = true;
    }

    @NonNull
    public Class<InputStream> d() {
        return InputStream.class;
    }

    @NonNull
    public DataSource c() {
        return DataSource.REMOTE;
    }
}

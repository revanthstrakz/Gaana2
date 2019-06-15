package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public abstract class am extends AsyncTask<Object, Void, String> {
    private a a;
    protected final b d;

    public interface a {
        void a(am amVar);
    }

    public interface b {
        void a(JSONObject jSONObject);

        JSONObject b();
    }

    public am(b bVar) {
        this.d = bVar;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(ThreadPoolExecutor threadPoolExecutor) {
        executeOnExecutor(threadPoolExecutor, new Object[0]);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (this.a != null) {
            this.a.a(this);
        }
    }
}

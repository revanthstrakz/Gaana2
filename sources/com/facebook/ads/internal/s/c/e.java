package com.facebook.ads.internal.s.c;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.r.a.n;
import com.facebook.ads.internal.r.a.p;
import com.facebook.ads.internal.s.a.k;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class e extends AsyncTask<String, Void, f> {
    private static final String a = "e";
    private static final Set<String> b = new HashSet();
    private Context c;
    private Map<String, String> d;
    private Map<String, String> e;
    private n f;
    private a g;

    public interface a {
        void a();

        void a(f fVar);
    }

    static {
        b.add("#");
        b.add("null");
    }

    public e(Context context) {
        this(context, null, null);
    }

    public e(Context context, Map<String, String> map) {
        this(context, map, null);
    }

    public e(Context context, Map<String, String> map, Map<String, String> map2) {
        this.c = context;
        Map map3 = null;
        this.d = map != null ? new HashMap(map) : null;
        if (map2 != null) {
            map3 = new HashMap(map2);
        }
        this.e = map3;
    }

    private String a(String str, String str2, String str3) {
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            if (TextUtils.isEmpty(str3)) {
                return str;
            }
            String str4 = str.contains("?") ? "&" : "?";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str4);
            stringBuilder.append(str2);
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(str3));
            str = stringBuilder.toString();
        }
        return str;
    }

    private boolean a(String str) {
        com.facebook.ads.internal.r.a.a a = d.a(this.c);
        boolean z = false;
        try {
            n b;
            if (this.e != null) {
                if (this.e.size() != 0) {
                    p pVar = new p();
                    pVar.a(this.e);
                    b = a.b(str, pVar);
                    this.f = b;
                    if (this.f != null && this.f.a() == 200) {
                        z = true;
                    }
                    return z;
                }
            }
            b = a.a(str, null);
            this.f = b;
            z = true;
            return z;
        } catch (Exception e) {
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error opening url: ");
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString(), e);
            return false;
        }
    }

    private String b(String str) {
        try {
            return a(str, "analog", k.a(com.facebook.ads.internal.i.a.a()));
        } catch (Exception unused) {
            return str;
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public f doInBackground(String... strArr) {
        CharSequence charSequence = strArr[0];
        if (TextUtils.isEmpty(charSequence) || b.contains(charSequence)) {
            return null;
        }
        String b = b(charSequence);
        if (!(this.d == null || this.d.isEmpty())) {
            for (Entry entry : this.d.entrySet()) {
                b = a(b, (String) entry.getKey(), (String) entry.getValue());
            }
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                break;
            } else if (a(b)) {
                return new f(this.f);
            } else {
                i = i2;
            }
        }
        return null;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(f fVar) {
        if (this.g != null) {
            this.g.a(fVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled() {
        if (this.g != null) {
            this.g.a();
        }
    }
}

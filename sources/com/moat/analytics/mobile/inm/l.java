package com.moat.analytics.mobile.inm;

import android.os.Build.VERSION;
import com.til.colombia.android.internal.e;
import org.json.JSONArray;
import org.json.JSONObject;

class l {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private int d = 200;
    private int e = 10;

    l(String str) {
        a(str);
    }

    private void a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                str = jSONObject.getString(e.ak);
                boolean equals = str.equals("c334ae83accfebb8da23104450c896463c9cfab7");
                boolean equals2 = str.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if (!((!str.equals("on") && !equals && !equals2) || a(jSONObject) || b(jSONObject))) {
                    this.a = true;
                    this.b = equals;
                    this.c = equals2;
                    if (this.c) {
                        this.b = true;
                    }
                }
                if (jSONObject.has("in")) {
                    int i = jSONObject.getInt("in");
                    if (i >= 100 && i <= 1000) {
                        this.d = i;
                    }
                }
                if (jSONObject.has("es")) {
                    this.e = jSONObject.getInt("es");
                }
                if (c(jSONObject)) {
                    ((k) MoatAnalytics.getInstance()).c = true;
                }
            } catch (Exception e) {
                this.a = false;
                this.b = false;
                this.d = 200;
                m.a(e);
            }
        }
    }

    private boolean a(JSONObject jSONObject) {
        try {
            if (16 > VERSION.SDK_INT) {
                return true;
            }
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean b(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ap")) {
                String b = s.d().b();
                JSONArray jSONArray = jSONObject.getJSONArray("ap");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(b)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
        return false;
    }

    private boolean c(JSONObject jSONObject) {
        try {
            if (jSONObject.has("al")) {
                String b = s.d().b();
                JSONArray jSONArray = jSONObject.getJSONArray("al");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(b)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public int c() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public int d() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public d e() {
        return this.a ? d.ON : d.OFF;
    }
}

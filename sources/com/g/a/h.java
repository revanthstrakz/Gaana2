package com.g.a;

import android.content.Context;
import com.android.b.a.a;
import com.android.b.a.c;
import com.android.b.a.d;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map;

public class h {
    Context a = null;
    d b = null;

    public h(Context context) {
        this.a = context;
    }

    public String a() {
        try {
            a a = a.a(this.a).a();
            a.a(new c() {
                public void onInstallReferrerServiceDisconnected() {
                }

                public void onInstallReferrerSetupFinished(int i) {
                    if (i == 0) {
                    }
                }
            });
            this.b = a.c();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_1930");
        }
        return "";
    }

    /* Access modifiers changed, original: 0000 */
    public String b() {
        try {
            if (this.b != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.c());
                stringBuilder.append("");
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_1951");
        }
        return "un_available";
    }

    /* Access modifiers changed, original: 0000 */
    public String c() {
        try {
            if (this.b != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.a());
                stringBuilder.append("");
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_1952");
        }
        return "un_available";
    }

    /* Access modifiers changed, original: 0000 */
    public String d() {
        try {
            if (this.b != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.b());
                stringBuilder.append("");
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_1953");
        }
        return "un_available";
    }

    public Map<String, String> a(Map<String, String> map) {
        try {
            j.a("Collect datapoints E");
            a();
            String b = b();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b);
            map.put("sdk_install_begin_timestamp", stringBuilder.toString());
            b = c();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b);
            map.put("sdk_install_referrer_url", stringBuilder.toString());
            b = d();
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b);
            map.put("sdk_referrer_click_timestamp", stringBuilder.toString());
            return map;
        } catch (Exception unused) {
            j.a("Exception while collecting datapoints E");
            return map;
        }
    }
}

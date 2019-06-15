package com.g.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class d {
    Context a = null;
    String b = "mFilterItData";

    public d(Context context) {
        this.a = context;
    }

    public boolean a(String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MFilterIt : setConfiguration ");
            stringBuilder.append(str);
            j.a(stringBuilder.toString());
            Editor edit = this.a.getSharedPreferences(this.b, 0).edit();
            edit.putString(str, str2);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Set app data ");
            stringBuilder2.append(str2);
            j.a(stringBuilder2.toString());
            edit.apply();
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in setConfiguration");
            return false;
        }
    }

    public String b(String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MFilterIt : getConfiguration ");
            stringBuilder.append(str);
            j.a(stringBuilder.toString());
            str = this.a.getSharedPreferences(this.b, 0).getString(str, str2);
            stringBuilder = new StringBuilder();
            stringBuilder.append("Inside configuration ");
            stringBuilder.append(str);
            j.a(stringBuilder.toString());
            return str;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in getConfiguration");
            return str2;
        }
    }
}

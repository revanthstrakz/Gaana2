package net.hockeyapp.android.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class g {
    private SharedPreferences a;
    private Editor b;
    private SharedPreferences c;
    private Editor d;

    private static class a {
        public static final g a = new g();
    }

    private g() {
    }

    public static g a() {
        return a.a;
    }

    public void a(Context context, String str) {
        if (context != null) {
            this.a = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
            if (this.a != null) {
                this.b = this.a.edit();
                this.b.putString("net.hockeyapp.android.prefs_key_feedback_token", str);
                this.b.apply();
            }
        }
    }

    public String a(Context context) {
        if (context == null) {
            return null;
        }
        this.a = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
        if (this.a == null) {
            return null;
        }
        return this.a.getString("net.hockeyapp.android.prefs_key_feedback_token", null);
    }

    public void a(Context context, String str, String str2, String str3) {
        if (context != null) {
            this.c = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
            if (this.c != null) {
                this.d = this.c.edit();
                if (str == null || str2 == null || str3 == null) {
                    this.d.putString("net.hockeyapp.android.prefs_key_name_email", null);
                } else {
                    this.d.putString("net.hockeyapp.android.prefs_key_name_email", String.format("%s|%s|%s", new Object[]{str, str2, str3}));
                }
                this.d.apply();
            }
        }
    }

    public String b(Context context) {
        if (context == null) {
            return null;
        }
        this.c = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
        if (this.c == null) {
            return null;
        }
        return this.c.getString("net.hockeyapp.android.prefs_key_name_email", null);
    }
}

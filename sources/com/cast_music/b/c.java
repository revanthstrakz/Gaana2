package com.cast_music.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class c {
    private final SharedPreferences a;

    public c(Context context) {
        this.a = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void a(String str, String str2) {
        if (str2 == null) {
            this.a.edit().remove(str).apply();
        } else {
            this.a.edit().putString(str, str2).apply();
        }
    }

    public void a(String str, Long l) {
        if (l == null) {
            this.a.edit().remove(str).apply();
        } else {
            this.a.edit().putLong(str, l.longValue()).apply();
        }
    }

    public String a(String str) {
        return b(str, null);
    }

    public String b(String str, String str2) {
        return this.a.getString(str, str2);
    }

    public long a(String str, long j) {
        return this.a.getLong(str, j);
    }
}

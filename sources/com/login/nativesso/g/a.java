package com.login.nativesso.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class a {
    private static a b;
    private static Gson c = new Gson();
    Type a = new TypeToken<Object>() {
    }.getType();
    private Context d;
    private SharedPreferences e;
    private Editor f;

    private a(Context context, String str, int i) {
        this.d = context;
        if (str == null || str.equals("")) {
            str = "complex_preferences";
        }
        this.e = context.getSharedPreferences(str, i);
        this.f = this.e.edit();
    }

    public static a a(Context context, String str, int i) {
        b = new a(context, str, i);
        return b;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object is null");
        } else if (str.equals("") || str == null) {
            throw new IllegalArgumentException("key is empty or null");
        } else {
            this.f.putString(str, c.toJson(obj));
        }
    }

    public void a() {
        this.f.commit();
    }

    public void b() {
        this.f.clear();
    }

    public <T> T a(String str, Class<T> cls) {
        String string = this.e.getString(str, null);
        if (string == null) {
            return null;
        }
        try {
            return c.fromJson(string, (Class) cls);
        } catch (Exception unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Object storaged with key ");
            stringBuilder.append(str);
            stringBuilder.append(" is instanceof other class");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}

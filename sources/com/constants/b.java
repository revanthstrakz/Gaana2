package com.constants;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import java.util.Locale;

public class b {
    private final Context a;
    private final AssetManager b;
    private final DisplayMetrics c;
    private final Configuration d;
    private Locale e;
    private final Locale f;

    private final class a extends Resources {
        public a(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
            super(assetManager, displayMetrics, configuration);
        }

        public String[] getStringArray(int i) throws NotFoundException {
            return super.getStringArray(i);
        }

        @NonNull
        public String getString(int i, Object... objArr) throws NotFoundException {
            return super.getString(i, objArr);
        }

        @NonNull
        public String getString(int i) throws NotFoundException {
            return super.getString(i);
        }
    }

    public b(@NonNull Context context, @NonNull Locale locale, @NonNull Locale locale2) {
        this.a = context;
        Resources resources = this.a.getResources();
        this.b = resources.getAssets();
        this.c = resources.getDisplayMetrics();
        this.d = new Configuration(resources.getConfiguration());
        this.e = locale2;
        this.f = locale;
    }

    public void a(Locale locale) {
        this.e = locale;
    }

    public String a(int i) {
        if (VERSION.SDK_INT >= 17) {
            this.d.setLocale(this.e);
            return this.a.createConfigurationContext(this.d).getResources().getString(i);
        }
        this.d.locale = this.e;
        String string = new a(this.b, this.c, this.d).getString(i);
        this.d.locale = this.f;
        a aVar = new a(this.b, this.c, this.d);
        return string;
    }
}

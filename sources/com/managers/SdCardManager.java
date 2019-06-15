package com.managers;

import android.os.Environment;
import android.os.StatFs;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.cache.CacheResult;
import com.library.util.StorageUtils;
import com.services.d;
import com.utilities.i;
import java.io.File;
import java.io.FilenameFilter;

public class SdCardManager {
    private static SdCardManager a = null;
    private static String b = null;
    private static String c = null;
    private static String d = "/.gaana";
    private static String e;
    private static CacheResult g;
    private boolean f = false;

    public enum STORAGE_TYPE {
        INTERNAL_STORAGE,
        SD_CARD,
        PRIMARY_STORAGE
    }

    public class a implements FilenameFilter {
        private String b;

        public a(String str) {
            this.b = str;
        }

        public boolean accept(File file, String str) {
            return str.endsWith(this.b);
        }
    }

    private SdCardManager() {
        g = d();
    }

    public static SdCardManager a() {
        if (a == null) {
            a = new SdCardManager();
        }
        if (!(g == null || g.isSuccess().booleanValue())) {
            g = a.d();
        }
        return a;
    }

    public static int a(String str) {
        try {
            if (str.contains(d)) {
                str = str.replace(d, "");
            }
            StatFs statFs = new StatFs(str);
            return (int) ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
        } catch (RuntimeException e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public static boolean a(File file) {
        try {
            if (EnvironmentCompat.getStorageState(file).equals("mounted")) {
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public String a(STORAGE_TYPE storage_type) {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        if (b == null || storage_type != STORAGE_TYPE.SD_CARD || a(b) <= 200) {
            return c;
        }
        return b;
    }

    public String b() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(d);
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder2.append(d);
        return stringBuilder2.toString();
    }

    public boolean b(String str) {
        return e(str) != null;
    }

    public boolean c(String str) {
        try {
            d a = d.a();
            int b = a.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true);
            int b2 = a.b("PREFERENCE_KEY_LAST_SELECTED_SYNC_QUALITY", -1, false);
            if (b2 == -1) {
                a.a("PREFERENCE_KEY_LAST_SELECTED_SYNC_QUALITY", b, false);
                b2 = b;
            }
            if (b2 == b) {
                return false;
            }
            if (!TextUtils.isEmpty(c)) {
                a(c, str);
            }
            if (!TextUtils.isEmpty(b)) {
                a(b, str);
            }
            a.a("PREFERENCE_KEY_LAST_SELECTED_SYNC_QUALITY", b, false);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return true;
        }
    }

    public void d(String str) {
        try {
            if (!TextUtils.isEmpty(c)) {
                a(c, str);
            }
            if (!TextUtils.isEmpty(b)) {
                a(b, str);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(String str, String str2) {
        String[] list = new File(str).list(new a(str2));
        if (list != null && list.length != 0) {
            for (String g : list) {
                g(g);
            }
        }
    }

    public String e(String str) {
        String str2;
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(b) || !new File(b, str).exists()) {
            str2 = null;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(b);
            stringBuilder.append("/");
            stringBuilder.append(str);
            str2 = stringBuilder.toString();
        }
        if (!TextUtils.isEmpty(str2) || TextUtils.isEmpty(c) || !new File(c, str).exists()) {
            return str2;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append("/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String f(String str) {
        String b = b();
        if (!(b == null || TextUtils.isEmpty(b))) {
            File file = new File(b, str);
            if (file.exists()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(file);
                stringBuilder.append("/");
                stringBuilder.append(str);
                return stringBuilder.toString();
            }
        }
        return null;
    }

    public void c() {
        if (!TextUtils.isEmpty(c)) {
            StorageUtils.delete(new File(c));
        }
        if (!TextUtils.isEmpty(b)) {
            StorageUtils.delete(new File(b));
        }
    }

    public void g(String str) {
        boolean z;
        String str2;
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(c)) {
            z = false;
        } else {
            str2 = c;
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(i.a);
            z = StorageUtils.delete(new File(str2, stringBuilder.toString()));
            if (!z) {
                z = StorageUtils.delete(new File(c, str));
            }
            if (!z) {
                String str3 = c;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(".temp");
                StorageUtils.delete(new File(str3, stringBuilder2.toString()));
            }
        }
        if (!z && !TextUtils.isEmpty(b)) {
            str2 = b;
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(i.a);
            z = StorageUtils.delete(new File(str2, stringBuilder.toString()));
            if (!z) {
                z = StorageUtils.delete(new File(b, str));
            }
            if (!z) {
                str2 = b;
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(".temp");
                StorageUtils.delete(new File(str2, stringBuilder.toString()));
            }
        }
    }

    public CacheResult d() {
        StringBuilder stringBuilder;
        CacheResult cacheResult = null;
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null);
        File file = externalFilesDirs[0];
        File file2 = externalFilesDirs.length > 1 ? externalFilesDirs[1] : null;
        if (file != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(d);
            c = stringBuilder.toString();
        }
        this.f = Environment.isExternalStorageRemovable();
        StringBuilder stringBuilder2;
        if (this.f) {
            if (file != null) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(file.getAbsolutePath());
                stringBuilder2.append(d);
                b = stringBuilder2.toString();
                cacheResult = b(file);
            }
            if (file2 != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(file2.getAbsolutePath());
                stringBuilder.append(d);
                c = stringBuilder.toString();
                if (cacheResult == null || !cacheResult.isSuccess().booleanValue()) {
                    cacheResult = b(file2);
                }
            }
        } else if (file2 != null) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file2.getAbsolutePath());
            stringBuilder2.append(d);
            b = stringBuilder2.toString();
            cacheResult = b(file2);
        } else {
            b = null;
        }
        if (TextUtils.isEmpty(b) && TextUtils.isEmpty(c)) {
            file = Environment.getExternalStorageDirectory();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(file.getAbsolutePath());
            stringBuilder3.append(d);
            c = stringBuilder3.toString();
        }
        return (cacheResult == null || !cacheResult.isSuccess().booleanValue()) ? b(file) : cacheResult;
    }

    public CacheResult e() {
        StringBuilder stringBuilder;
        CacheResult cacheResult = null;
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null);
        File file = externalFilesDirs[0];
        File file2 = externalFilesDirs.length > 1 ? externalFilesDirs[1] : null;
        if (file != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(d);
            c = stringBuilder.toString();
        }
        this.f = Environment.isExternalStorageRemovable();
        StringBuilder stringBuilder2;
        if (this.f) {
            if (file != null) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(file.getAbsolutePath());
                stringBuilder2.append(d);
                b = stringBuilder2.toString();
                cacheResult = c(file);
            }
            if (file2 != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(file2.getAbsolutePath());
                stringBuilder.append(d);
                c = stringBuilder.toString();
                if (cacheResult == null || !cacheResult.isSuccess().booleanValue()) {
                    cacheResult = c(file2);
                }
            }
        } else if (file2 != null) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file2.getAbsolutePath());
            stringBuilder2.append(d);
            b = stringBuilder2.toString();
            cacheResult = c(file2);
        } else {
            b = null;
        }
        if (TextUtils.isEmpty(b) && TextUtils.isEmpty(c)) {
            file = Environment.getExternalStorageDirectory();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(file.getAbsolutePath());
            stringBuilder3.append(d);
            c = stringBuilder3.toString();
        }
        return (cacheResult == null || !cacheResult.isSuccess().booleanValue()) ? c(file) : cacheResult;
    }

    private CacheResult b(File file) {
        if (!a(file)) {
            return new CacheResult(2);
        }
        if (a(file.getAbsolutePath()) < 200) {
            return new CacheResult(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getAbsolutePath());
        stringBuilder.append(d);
        e = stringBuilder.toString();
        return new CacheResult(0);
    }

    private CacheResult c(File file) {
        if (!a(file)) {
            return new CacheResult(2);
        }
        if (a(file.getAbsolutePath()) < 1024) {
            return new CacheResult(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getAbsolutePath());
        stringBuilder.append(d);
        e = stringBuilder.toString();
        return new CacheResult(0);
    }
}

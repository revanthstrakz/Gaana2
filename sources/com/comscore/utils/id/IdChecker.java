package com.comscore.utils.id;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.comscore.utils.Constants;
import com.comscore.utils.Storage;
import com.comscore.utils.Utils;
import com.gaana.cardoption.AssetsHelper.CARD;

public class IdChecker {
    private static final String[] a = new String[]{"0123456789ABCDEF", "0123456789abcdef", "9774d56d682e549c", "9774D56D682E549C", "unknown", CARD.UNKNOWN, "android_id", "ANDROID_ID"};
    private Context b;
    private boolean c = false;
    private String d;
    private Storage e;

    public IdChecker(Context context, Storage storage) {
        this.b = context;
        this.e = storage;
    }

    private boolean a(String str, String str2) {
        String[] split = this.e.get(str2).split(";");
        for (String equals : split) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String b() {
        return Integer.valueOf(VERSION.SDK_INT).intValue() >= 9 ? Build.SERIAL : null;
    }

    private void b(String str, String str2) {
        String str3 = this.e.get(str2);
        if (str3.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(";");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        this.e.set(str2, str);
    }

    private String c() {
        if (Integer.valueOf(VERSION.SDK_INT).intValue() >= 3) {
            String string = Secure.getString(this.b.getContentResolver(), "android_id");
            if (string != null && string.length() > 0) {
                return string;
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        if (this.c) {
            return this.d;
        }
        boolean z = true;
        this.c = true;
        Object b = b();
        if (b == null || b.length() == 0) {
            b = c();
            if (b == null || b.length() == 0) {
                return null;
            }
        }
        for (String equals : a) {
            if (equals.equals(b)) {
                break;
            }
        }
        z = false;
        if (z) {
            this.d = b;
            return b;
        }
        return null;
    }

    public boolean checkCrossPublisherId(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        String a = a();
        if (a == null || a(a, Constants.ADID_CHECK_DATA)) {
            return true;
        }
        b(a, Constants.ADID_CHECK_DATA);
        if (Utils.md5(a).equals(str)) {
            return false;
        }
        return true;
    }

    public boolean checkVisitorId() {
        String a = a();
        if (a == null || a(a, Constants.ID_CHECK_DATA)) {
            return true;
        }
        b(a, Constants.ID_CHECK_DATA);
        return false;
    }
}

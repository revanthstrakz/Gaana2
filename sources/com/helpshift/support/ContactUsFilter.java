package com.helpshift.support;

import android.content.Context;
import com.helpshift.support.m.a;
import com.helpshift.util.o;
import java.util.HashMap;

public final class ContactUsFilter {
    private static d a;
    private static Integer b;

    public enum LOCATION {
        ACTION_BAR,
        SEARCH_FOOTER,
        QUESTION_FOOTER,
        QUESTION_ACTION_BAR,
        SEARCH_RESULT_ACTIVITY_HEADER
    }

    public static void a(Context context) {
        if (a == null) {
            a = new d(context);
            b = Integer.valueOf(o.d().m().d().getValue());
        }
    }

    protected static void a(HashMap hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        Object obj = hashMap.get("enableContactUs");
        if (obj instanceof Integer) {
            b = (Integer) hashMap.get("enableContactUs");
        } else if (!(obj instanceof Boolean)) {
        } else {
            if (((Boolean) obj).booleanValue()) {
                b = a.a;
            } else {
                b = a.b;
            }
        }
    }

    public static boolean a(LOCATION location) {
        boolean z = false;
        if (AnonymousClass1.a[location.ordinal()] == 1) {
            return false;
        }
        if (a.a.equals(b)) {
            return true;
        }
        if (a.b.equals(b)) {
            return false;
        }
        if (a.c.equals(b)) {
            switch (location) {
                case SEARCH_FOOTER:
                    return true;
                case QUESTION_FOOTER:
                    return true;
                case QUESTION_ACTION_BAR:
                    return true;
                case ACTION_BAR:
                    if (o.d().a() != null) {
                        z = true;
                    }
                    return z;
                default:
                    return true;
            }
        } else if (!a.d.equals(b)) {
            return true;
        } else {
            switch (location) {
                case SEARCH_FOOTER:
                    return false;
                case QUESTION_FOOTER:
                    return true;
                case QUESTION_ACTION_BAR:
                case ACTION_BAR:
                    if (o.d().a() != null) {
                        z = true;
                    }
                    return z;
                default:
                    return true;
            }
        }
    }
}

package com.helpshift.util;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.helpshift.j.c.a;
import com.helpshift.j.c.d;
import com.helpshift.n.c;
import java.util.ArrayList;
import java.util.List;

public class g {
    private static String a = "HS_ErrorReport";

    public static List<a> a(Context context, Thread thread) {
        ArrayList arrayList = new ArrayList();
        try {
            String str;
            String str2;
            arrayList.add(d.a("appId", context.getPackageName()));
            arrayList.add(d.a("nt", n.b(context)));
            c b = com.helpshift.n.a.b();
            if (b == null) {
                str = "";
            } else {
                str = b.b();
            }
            if (str != null) {
                arrayList.add(d.a("funnel", str));
            }
            if (b == null) {
                str2 = "";
            } else {
                str2 = b.a();
            }
            if (!w.a(str2)) {
                arrayList.add(d.a("actconvid", str2));
            }
            str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            if (thread != null) {
                str2 = thread.toString();
            }
            arrayList.add(d.a("thread", str2));
        } catch (Exception e) {
            l.c(a, "Error creating error report", e);
        }
        return arrayList;
    }
}

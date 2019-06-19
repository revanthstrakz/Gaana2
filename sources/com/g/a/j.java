package com.g.a;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class j {
    public static String a = "";
    static boolean b;

    public static void a(String str) {
        try {
            a = str;
            Log.d("[[mFilterIt]]:", str);
            if (b) {
                Intent intent = new Intent();
                intent.putExtra("TYPE", str);
                intent.setAction("NOW");
                LocalBroadcastManager.getInstance(a.a).sendBroadcast(intent);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

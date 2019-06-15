package io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Defines.Jsonkey;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class InstallListener extends BroadcastReceiver {
    private static String a = "bnc_no_value";

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (stringExtra != null) {
            try {
                stringExtra = URLDecoder.decode(stringExtra, "UTF-8");
                HashMap hashMap = new HashMap();
                for (String split : stringExtra.split("&")) {
                    String[] split2 = split.split("=");
                    if (split2.length > 1) {
                        hashMap.put(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    }
                }
                if (hashMap.containsKey(Jsonkey.LinkClickID.getKey())) {
                    a = (String) hashMap.get(Jsonkey.LinkClickID.getKey());
                }
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
            } catch (IllegalArgumentException e2) {
                ThrowableExtension.printStackTrace(e2);
                Log.w("BranchSDK", "Illegal characters in url encoded string");
            }
        }
    }

    public static String a() {
        return a;
    }
}

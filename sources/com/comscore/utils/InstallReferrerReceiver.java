package com.comscore.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.comscore.analytics.comScore;
import java.util.HashMap;

public class InstallReferrerReceiver extends BroadcastReceiver {
    public static final String CS_NONE = "CS_NONE";
    public static final String CS_REFERRER_PREF_KEY = "CS_REFERRER_PREF_KEY";
    public static final String REFERRER_LABEL = "ns_ap_referrer";
    private static final String a = "InstallReferrerReceiver";

    private void a(String str, Context context) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(CS_REFERRER_PREF_KEY, str);
        edit.commit();
        CSLog.d(a, "Stored data");
    }

    public static HashMap<String, String> retrieveReferrerLabels(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences.contains(CS_REFERRER_PREF_KEY)) {
            Editor edit = defaultSharedPreferences.edit();
            String string = defaultSharedPreferences.getString(CS_REFERRER_PREF_KEY, CS_NONE);
            Object str = comScore.getAppName().toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("referrer was set as: '");
            stringBuilder.append(string);
            stringBuilder.append("'");
            CSLog.d(str, stringBuilder.toString());
            edit.remove(CS_REFERRER_PREF_KEY);
            edit.commit();
            if (!(string == null || string.length() <= 0 || string.equals(CS_NONE))) {
                return splitReferrer(string);
            }
        }
        return null;
    }

    public static HashMap<String, String> splitReferrer(String str) {
        HashMap hashMap = new HashMap();
        if (str != null && str.length() > 0) {
            for (String str2 : str.split("&")) {
                int indexOf = str2.indexOf("=");
                if (indexOf >= 0) {
                    hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                } else {
                    hashMap.put(REFERRER_LABEL, str2);
                }
            }
            if (hashMap.size() > 0 && !hashMap.containsKey(REFERRER_LABEL)) {
                hashMap.put(REFERRER_LABEL, "1");
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007a  */
    public void onReceive(android.content.Context r6, android.content.Intent r7) {
        /*
        r5 = this;
        r0 = "InstallReferrerReceiver";
        r1 = "onReceive()";
        com.comscore.utils.CSLog.d(r0, r1);
        r0 = "CS_NONE";
        if (r7 == 0) goto L_0x005b;
    L_0x000b:
        r1 = r7.getAction();	 Catch:{ Exception -> 0x0040 }
        r2 = "com.android.vending.INSTALL_REFERRER";
        r1 = r1.equals(r2);	 Catch:{ Exception -> 0x0040 }
        if (r1 == 0) goto L_0x005b;
    L_0x0017:
        r1 = "referrer";
        r7 = r7.getStringExtra(r1);	 Catch:{ Exception -> 0x0040 }
        if (r7 == 0) goto L_0x005b;
    L_0x001f:
        r1 = "UTF-8";
        r7 = java.net.URLDecoder.decode(r7, r1);	 Catch:{ Exception -> 0x0040 }
        r0 = "referrer";
        r1 = 0;
        r0 = r6.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x003b }
        r0 = r0.edit();	 Catch:{ Exception -> 0x003b }
        r1 = "referrer";
        r0 = r0.putString(r1, r7);	 Catch:{ Exception -> 0x003b }
        r0.commit();	 Catch:{ Exception -> 0x003b }
        r0 = r7;
        goto L_0x005b;
    L_0x003b:
        r0 = move-exception;
        r4 = r0;
        r0 = r7;
        r7 = r4;
        goto L_0x0041;
    L_0x0040:
        r7 = move-exception;
    L_0x0041:
        r1 = "InstallReferrerReceiver";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "onReceive()";
        r2.append(r3);
        r7 = r7.getMessage();
        r2.append(r7);
        r7 = r2.toString();
        com.comscore.utils.CSLog.e(r1, r7);
    L_0x005b:
        r7 = "InstallReferrerReceiver";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Received referrer: '";
        r1.append(r2);
        r1.append(r0);
        r2 = "'";
        r1.append(r2);
        r1 = r1.toString();
        com.comscore.utils.CSLog.d(r7, r1);
        r7 = "CS_NONE";
        if (r0 == r7) goto L_0x009a;
    L_0x007a:
        r7 = com.comscore.analytics.comScore.getCore();
        if (r7 == 0) goto L_0x0097;
    L_0x0080:
        r1 = r7.getAppContext();
        if (r1 == 0) goto L_0x0097;
    L_0x0086:
        r1 = r7.getColdStartCount();
        if (r1 <= 0) goto L_0x0097;
    L_0x008c:
        r6 = com.comscore.applications.EventType.HIDDEN;
        r0 = splitReferrer(r0);
        r1 = 1;
        r7.notify(r6, r0, r1);
        return;
    L_0x0097:
        r5.a(r0, r6);
    L_0x009a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.InstallReferrerReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}

package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.utilities.b.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

final class a {
    @SuppressLint({"SdCardPath"})
    public static boolean a(Context context) {
        int i;
        List asList = Arrays.asList(new String[]{"carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref"});
        for (i = 0; i < asList.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder("/data/data/");
            stringBuilder.append(context.getPackageName());
            stringBuilder.append("/shared_prefs/");
            stringBuilder.append((String) asList.get(i));
            stringBuilder.append(".xml");
            File file = new File(stringBuilder.toString());
            if (file.exists()) {
                file.delete();
            }
        }
        asList = Arrays.asList(new String[]{c.a("carb_store"), c.a("config_store"), c.a("aes_key_store"), c.a("mraid_js_store"), g.a()});
        for (i = 0; i < asList.size(); i++) {
            StringBuilder stringBuilder2 = new StringBuilder("/data/data/");
            stringBuilder2.append(context.getPackageName());
            stringBuilder2.append("/shared_prefs/");
            stringBuilder2.append((String) asList.get(i));
            stringBuilder2.append(".xml");
            File file2 = new File(stringBuilder2.toString());
            if (file2.exists()) {
                file2.delete();
            }
        }
        asList = Arrays.asList(new String[]{"inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getCacheDir() != null) {
                File file3 = new File(context.getCacheDir(), (String) asList.get(i));
                if (file3.exists()) {
                    file3.delete();
                }
            }
        }
        asList = Arrays.asList(new String[]{"eventlog", "imai_click_events"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getDir("data", 0) != null) {
                File file4 = new File(context.getDir("data", 0), (String) asList.get(i));
                if (file4.exists()) {
                    file4.delete();
                }
            }
        }
        return b(context).size() != 0;
    }

    private static boolean a(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        return databasePath == null || !databasePath.exists() || context.deleteDatabase(str);
    }

    public static List<String> b(Context context) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        hashSet.add("adcache.db");
        hashSet.add("appengage.db");
        hashSet.add("im.db");
        hashSet.add("ltvp.db");
        hashSet.add("analytics.db");
        hashSet.add("com.im.db");
        String[] databaseList = context.databaseList();
        if (databaseList != null && databaseList.length > 0) {
            for (Object obj : databaseList) {
                if (hashSet.contains(obj) && !a(context, obj)) {
                    arrayList.add(obj);
                } else if (!(!obj.matches("com\\.im_([0-9]+\\.){3}db") || obj.equals(com.inmobi.commons.core.d.a.a) || a(context, obj))) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }
}

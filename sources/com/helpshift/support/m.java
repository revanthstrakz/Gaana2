package com.helpshift.support;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.helpshift.j.c.d;
import com.helpshift.support.activities.ParentActivity;
import com.helpshift.support.h.j;
import com.helpshift.support.util.b;
import com.helpshift.util.e;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class m {
    private static d a;
    private static g b;
    private static Context c;

    public static class a {
        public static final Integer a = Integer.valueOf(0);
        public static final Integer b = Integer.valueOf(1);
        public static final Integer c = Integer.valueOf(2);
        public static final Integer d = Integer.valueOf(3);
        public static final HashSet e = a();

        private static HashSet<Integer> a() {
            HashSet hashSet = new HashSet();
            hashSet.add(a);
            hashSet.add(b);
            hashSet.add(c);
            hashSet.add(d);
            return hashSet;
        }
    }

    private static void a(Application application) {
        b(application.getApplicationContext());
    }

    private static void a(Context context) {
        b(context.getApplicationContext());
    }

    private static void b(Context context) {
        if (c == null) {
            a = new d(context);
            b = a.b;
            ContactUsFilter.a(context);
            c = context;
        }
    }

    private static void b() {
        String e = b.e();
        if (e.length() > 0 && !e.equals("6.4.0")) {
            j jVar = new j(b);
            jVar.a();
            a.k();
            b.a();
            o.c().f().a();
            o.c().m().a();
            jVar.b();
            c();
        }
        b.g("6.4.0");
    }

    private static void c() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c.getFilesDir());
            stringBuilder.append(File.separator);
            stringBuilder.append("__hs_supportkvdb_lock");
            File file = new File(stringBuilder.toString());
            if (file.exists()) {
                file.delete();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(c.getFilesDir());
            stringBuilder.append(File.separator);
            stringBuilder.append("__hs_kvdb_lock");
            file = new File(stringBuilder.toString());
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Error on deleting lock file: ");
            stringBuilder2.append(e);
            l.c("Helpshift_SupportInter", stringBuilder2.toString());
        }
    }

    public static void a(Application application, String str, String str2, String str3, Map map) {
        o.a(application.getApplicationContext());
        o.a(str, str2, str3);
        f a = f.a();
        application.unregisterActivityLifecycleCallbacks(a);
        application.registerActivityLifecycleCallbacks(a);
    }

    @TargetApi(14)
    public static void b(Application application, String str, String str2, String str3, Map<String, Object> map) {
        a(application);
        com.helpshift.n.a.a(new com.helpshift.support.providers.a());
        com.helpshift.support.d.a.a();
        Map map2 = (HashMap) b.b;
        if (map != null) {
            map2.putAll(map);
        }
        b();
        Object obj = map2.get("font");
        if (obj instanceof String) {
            com.helpshift.k.b.a().a.a((String) obj);
        } else {
            com.helpshift.k.b.a().a.a(null);
        }
        obj = map2.get("screenOrientation");
        if (obj instanceof Integer) {
            com.helpshift.k.b.a().a.a((Integer) obj);
        } else {
            com.helpshift.k.b.a().a.a(Integer.valueOf(-1));
        }
        e.b("__hs__db_profiles");
        obj = map2.get("supportNotificationChannelId");
        if (obj instanceof String) {
            map2.put("supportNotificationChannelId", (String) obj);
        }
        obj = map2.get("notificationIcon");
        if (obj != null && (obj instanceof String)) {
            map2.put("notificationIcon", Integer.valueOf(application.getResources().getIdentifier((String) obj, "drawable", application.getPackageName())));
        }
        obj = map2.get("notificationSound");
        if (obj != null && (obj instanceof String)) {
            map2.put("notificationSound", Integer.valueOf(application.getResources().getIdentifier((String) obj, "raw", application.getPackageName())));
        }
        obj = map2.get("disableAnimations");
        if (obj instanceof Boolean) {
            com.helpshift.k.b.a().a.a((Boolean) obj);
        } else {
            com.helpshift.k.b.a().a.a(Boolean.valueOf(false));
        }
        String a = com.helpshift.util.b.a(c);
        if (!b.f().equals(a)) {
            a.g();
            o.d().m().a(false);
            b.h(a);
        }
        o.d().a(map2);
        a.a(str, str2, str3);
        application.deleteDatabase("__hs__db_error_reports");
    }

    public static Integer a() {
        return Integer.valueOf(o.d().o());
    }

    public static void a(String str, String str2) {
        str = str == null ? "" : str.trim();
        if (str2 == null) {
            str2 = "";
        } else {
            str2 = str2.trim();
        }
        com.helpshift.b d = o.d();
        if (TextUtils.isEmpty(str) || com.helpshift.util.m.b(str)) {
            d.a("");
        } else {
            d.a(str);
        }
        if (TextUtils.isEmpty(str2) || !com.helpshift.util.m.a(str2)) {
            d.b("");
        } else {
            d.b(str2);
        }
    }

    public static void a(String str) {
        if (str != null) {
            o.d().j().b(str.trim());
        }
    }

    public static void a(Context context, String str) {
        a(context);
        if (str != null) {
            o.d().c(str);
        } else {
            l.c("Helpshift_SupportInter", "Device Token is null");
        }
    }

    public static void a(Activity activity) {
        a(activity, new HashMap());
    }

    public static void a(Activity activity, Map<String, Object> map) {
        l.a("Helpshift_SupportInter", "Show conversation : ", d.a("Config", (Map) new HashMap(map)));
        Intent intent = new Intent(activity, ParentActivity.class);
        intent.putExtra("support_mode", 1);
        intent.putExtra("decomp", true);
        intent.putExtras(c(b(r0)));
        intent.putExtra("showInFullScreen", com.helpshift.util.a.a(activity));
        intent.putExtra("chatLaunchSource", "support");
        intent.putExtra("isRoot", true);
        intent.putExtra("search_performed", false);
        activity.startActivity(intent);
    }

    public static void a(Activity activity, String str) {
        a(activity, str, new HashMap());
    }

    public static void a(Activity activity, String str, Map<String, Object> map) {
        if (!b(str)) {
            str = null;
        }
        HashMap hashMap = new HashMap(map);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Show FAQ section : Publish Id : ");
        stringBuilder.append(str);
        l.a("Helpshift_SupportInter", stringBuilder.toString(), d.a("Config", (Map) hashMap));
        Intent intent = new Intent(activity, ParentActivity.class);
        intent.putExtra("support_mode", 2);
        intent.putExtras(c(a(hashMap)));
        intent.putExtra("sectionPublishId", str);
        intent.putExtra("showInFullScreen", com.helpshift.util.a.a(activity));
        intent.putExtra("decomp", true);
        intent.putExtra("isRoot", true);
        activity.startActivity(intent);
    }

    public static void b(Activity activity, String str) {
        b(activity, str, new HashMap());
    }

    public static void b(Activity activity, String str, Map<String, Object> map) {
        if (!b(str)) {
            str = null;
        }
        HashMap hashMap = new HashMap(map);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Show single FAQ : Publish Id : ");
        stringBuilder.append(str);
        l.a("Helpshift_SupportInter", stringBuilder.toString(), d.a("Config", (Map) hashMap));
        Intent intent = new Intent(activity, ParentActivity.class);
        intent.putExtra("support_mode", 3);
        intent.putExtras(c(a(hashMap)));
        intent.putExtra("questionPublishId", str);
        intent.putExtra("showInFullScreen", com.helpshift.util.a.a(activity));
        intent.putExtra("decomp", true);
        intent.putExtra("isRoot", true);
        activity.startActivity(intent);
    }

    public static void a(b bVar) {
        o.d().k().a((com.helpshift.meta.b) bVar);
    }

    public static void a(final i iVar) {
        a(new b() {
            /* renamed from: b */
            public HashMap a() {
                h call = iVar.call();
                return call != null ? new HashMap(call.a()) : null;
            }
        });
    }

    private static void d(final HashMap hashMap) {
        if (hashMap.containsKey("hs-custom-metadata")) {
            a(new b() {
                /* renamed from: b */
                public HashMap a() {
                    return hashMap.get("hs-custom-metadata") instanceof HashMap ? (HashMap) hashMap.get("hs-custom-metadata") : null;
                }
            });
        }
    }

    public static void b(Activity activity) {
        b(activity, new HashMap());
    }

    public static void b(Activity activity, Map<String, Object> map) {
        l.a("Helpshift_SupportInter", "Show FAQs : ", d.a("Config", (Map) new HashMap(map)));
        Intent intent = new Intent(activity, ParentActivity.class);
        intent.putExtras(c(a(r0)));
        intent.putExtra("showInFullScreen", com.helpshift.util.a.a(activity));
        intent.putExtra("decomp", false);
        intent.putExtra("isRoot", true);
        activity.startActivity(intent);
    }

    public static HashMap<String, Object> a(HashMap<String, Object> hashMap) {
        HashMap hashMap2 = new HashMap(hashMap);
        hashMap2.remove("conversationPrefillText");
        return hashMap2;
    }

    public static HashMap<String, Object> b(HashMap<String, Object> hashMap) {
        HashMap hashMap2 = new HashMap(hashMap);
        hashMap2.remove("enableContactUs");
        return hashMap2;
    }

    private static boolean b(String str) {
        return str != null && str.trim().length() > 0 && str.matches("\\d+");
    }

    public static Bundle c(HashMap<String, Object> hashMap) {
        Map hashMap2 = new HashMap(b.a);
        hashMap2.putAll(hashMap);
        ContactUsFilter.a((HashMap) hashMap2);
        Bundle bundle = new Bundle();
        d(hashMap2);
        JSONObject jSONObject = new JSONObject(hashMap2);
        o.d().b(hashMap2);
        a(hashMap2);
        try {
            if (jSONObject.has("conversationPrefillText") && !jSONObject.getString("conversationPrefillText").equals("null") && jSONObject.has("hs-custom-metadata")) {
                bundle.putBoolean("dropMeta", true);
            }
            if (jSONObject.has("toolbarId")) {
                bundle.putInt("toolbarId", jSONObject.getInt("toolbarId"));
            }
        } catch (JSONException e) {
            l.a("Helpshift_SupportInter", "JSON exception while parsing config : ", e);
        }
        bundle.putBoolean("showSearchOnNewConversation", jSONObject.optBoolean("showSearchOnNewConversation", false));
        bundle.putSerializable("withTagsMatching", a(hashMap2.get("withTagsMatching")));
        com.helpshift.support.f.b.a((List) hashMap2.get("customContactUsFlows"));
        return bundle;
    }

    private static void a(Map<String, Object> map) {
        Map map2;
        if (map.containsKey("hs-custom-issue-field")) {
            Object obj = map.get("hs-custom-issue-field");
            if (obj instanceof Map) {
                try {
                    map2 = (Map) obj;
                } catch (Exception e) {
                    l.c("Helpshift_SupportInter", "Exception while parsing CIF data : ", e);
                }
                o.d().l().a(map2);
            }
        }
        map2 = null;
        o.d().l().a(map2);
    }

    private static FaqTagFilter a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            Map map = (Map) obj;
            String str = (String) map.get("operator");
            if (!TextUtils.isEmpty(str)) {
                str = str.trim().toLowerCase(Locale.US);
                String[] strArr = (String[]) map.get("tags");
                if (strArr != null && strArr.length > 0) {
                    if (str.equals("and")) {
                        return new FaqTagFilter("and", strArr);
                    }
                    if (str.equals("or")) {
                        return new FaqTagFilter("or", strArr);
                    }
                    if (str.equals("not")) {
                        return new FaqTagFilter("not", strArr);
                    }
                }
            }
        } catch (ClassCastException e) {
            l.c("Helpshift_SupportInter", "Invalid FaqTagFilter object in config", e);
        }
        return null;
    }

    public static void a(Context context, Intent intent) {
        a(context);
        String a = a(intent);
        Bundle extras = intent.getExtras();
        String string = (extras == null || !extras.containsKey(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING)) ? null : extras.getString(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING);
        o.d().a(a, string);
    }

    public static String a(Intent intent) {
        return intent == null ? null : intent.getExtras().getString("issue_id");
    }
}

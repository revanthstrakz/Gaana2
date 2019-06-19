package com.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.e.a.d;
import com.e.a.d.a;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.services.i;
import com.services.j;
import com.services.l.af;
import com.services.l.aw;
import com.services.n;
import com.utilities.Util;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class o implements a {
    private static o a = null;
    private static HashMap<String, BusinessObject> b = new HashMap();
    private static HashMap<String, BusinessObject> c = new HashMap();
    private static HashMap<String, String> d = new HashMap();
    private static String i = null;
    private static String j = null;
    private static String k = ".temp";
    private final String e = "times_internet_0";
    private d f;
    private j g = new j();
    private LinkedHashMap<String, SoftReference<Bitmap>> h = new LinkedHashMap();

    private o() {
        if (this.f == null) {
            this.f = new d(GaanaApplication.getContext());
            this.f.a((a) this);
        }
        i = Environment.getExternalStorageDirectory().toString();
        j = "/App_Images";
    }

    public static o a() {
        if (a == null) {
            a = new o();
        }
        return a;
    }

    public Class<?> a(BusinessObjectType businessObjectType) {
        Class<?> d = Util.d(businessObjectType);
        return d == null ? String.class : d;
    }

    public void b() {
        a = null;
        b.clear();
        c.clear();
        d.clear();
        this.f.a();
        n.a().b();
        com.i.j.a().d();
    }

    public void c() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null) {
            a(new File(file.getAbsolutePath(), "media_cache"));
            com.services.d.a().b("PREFERENCE_READ_BUFFER_HASH_MAP", false);
        }
    }

    public void b(String str) {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null) {
            String absolutePath = file.getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("media_cache/");
            stringBuilder.append(str);
            a(new File(absolutePath, stringBuilder.toString()));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File a : listFiles) {
                a(a);
            }
        }
        file.delete();
    }

    public void d() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(j);
            final File file = new File(stringBuilder.toString());
            if (file.exists() && file.isDirectory()) {
                com.i.d.a(new Runnable() {
                    public void run() {
                        o.b(file);
                    }
                });
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX WARNING: Missing block: B:16:0x0033, code skipped:
            return r3;
     */
    public static synchronized boolean b(java.io.File r6) {
        /*
        r0 = com.managers.o.class;
        monitor-enter(r0);
        r1 = r6.exists();	 Catch:{ all -> 0x0036 }
        r2 = 0;
        if (r1 == 0) goto L_0x0034;
    L_0x000a:
        r1 = r6.isDirectory();	 Catch:{ all -> 0x0036 }
        r3 = 1;
        if (r1 == 0) goto L_0x0027;
    L_0x0011:
        r1 = r6.listFiles();	 Catch:{ all -> 0x0036 }
        r4 = r1.length;	 Catch:{ all -> 0x0036 }
    L_0x0016:
        if (r2 >= r4) goto L_0x0022;
    L_0x0018:
        r5 = r1[r2];	 Catch:{ all -> 0x0036 }
        r5 = b(r5);	 Catch:{ all -> 0x0036 }
        r3 = r3 & r5;
        r2 = r2 + 1;
        goto L_0x0016;
    L_0x0022:
        r1 = r6.delete();	 Catch:{ all -> 0x0036 }
        r3 = r3 & r1;
    L_0x0027:
        r1 = r6.isFile();	 Catch:{ all -> 0x0036 }
        if (r1 == 0) goto L_0x0032;
    L_0x002d:
        r6 = r6.delete();	 Catch:{ all -> 0x0036 }
        r3 = r3 & r6;
    L_0x0032:
        monitor-exit(r0);
        return r3;
    L_0x0034:
        monitor-exit(r0);
        return r2;
    L_0x0036:
        r6 = move-exception;
        monitor-exit(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.o.b(java.io.File):boolean");
    }

    public String a(URLManager uRLManager, boolean z) {
        String k;
        if (uRLManager.k() != null) {
            k = uRLManager.k();
        } else {
            k = z ? "https://api.gaana.com/user.php?" : "https://api.gaana.com/index.php?";
            if (!(uRLManager.h() == null || uRLManager.h().keySet() == null)) {
                Object[] toArray = uRLManager.h().keySet().toArray();
                for (int i = 0; i < uRLManager.h().size(); i++) {
                    StringBuilder stringBuilder;
                    if (i == uRLManager.h().size() - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(k);
                        stringBuilder.append(toArray[i]);
                        stringBuilder.append("=");
                        stringBuilder.append((String) uRLManager.h().get(toArray[i].toString()));
                        k = stringBuilder.toString();
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(k);
                        stringBuilder.append(toArray[i]);
                        stringBuilder.append("=");
                        stringBuilder.append((String) uRLManager.h().get(toArray[i].toString()));
                        stringBuilder.append("&");
                        k = stringBuilder.toString();
                    }
                }
                uRLManager.a(k);
            }
        }
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || k.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder2;
            if (k.contains("?")) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(k);
                stringBuilder2.append("&");
                k = stringBuilder2.toString();
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(k);
                stringBuilder2.append("?");
                k = stringBuilder2.toString();
            }
            if (currentUser.getAuthToken() != null) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(k);
                stringBuilder2.append("token=");
                stringBuilder2.append(currentUser.getAuthToken());
                k = stringBuilder2.toString();
            }
        }
        return k.replace(" ", "%20");
    }

    public BusinessObject a(URLManager uRLManager) {
        Boolean valueOf = Boolean.valueOf(false);
        if (uRLManager.g() == 1) {
            valueOf = Boolean.valueOf(true);
        }
        String a = a(uRLManager, valueOf.booleanValue());
        String f = f(a);
        if (f == null) {
            f = a;
        }
        BusinessObject businessObject = null;
        if (uRLManager.f().booleanValue()) {
            businessObject = b(uRLManager);
        }
        if (businessObject == null || a(uRLManager, f).booleanValue()) {
            return a(uRLManager, valueOf.booleanValue(), a, f);
        }
        businessObject.getUrlManager().a(f);
        return businessObject;
    }

    private BusinessObject b(URLManager uRLManager) {
        Boolean valueOf = Boolean.valueOf(false);
        if (uRLManager.g() == 1) {
            valueOf = Boolean.valueOf(true);
        }
        Object a = a(uRLManager, valueOf.booleanValue());
        String f = f(a);
        if (f != null) {
            a = f;
        }
        HashMap hashMap = valueOf.booleanValue() ? c : b;
        return hashMap.containsKey(a) ? (BusinessObject) hashMap.get(a) : null;
    }

    public BusinessObject a(URLManager uRLManager, boolean z, String str, String str2) {
        Gson create = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();
        HashMap hashMap = z ? c : b;
        try {
            i a = this.g.a(str, uRLManager.w());
            if (a.b().booleanValue()) {
                String a2 = a.a();
                if (TextUtils.isEmpty(a2)) {
                    return i();
                }
                try {
                    Serializable serializable = (BusinessObject) create.fromJson(a2, Util.d(uRLManager.i()));
                    serializable.setBusinessObjType(uRLManager.i());
                    boolean checkAuthTokenStatus = ((GaanaApplication) GaanaApplication.getContext()).checkAuthTokenStatus((Object) serializable);
                    if (!checkAuthTokenStatus) {
                        BusinessObject businessObject = (BusinessObject) hashMap.get(str2);
                        if (businessObject != null) {
                            businessObject.setBusinessObjType(uRLManager.i());
                            serializable = businessObject;
                        }
                    }
                    if (serializable.getArrListBusinessObj() != null) {
                        Iterator it = serializable.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            ((BusinessObject) next).setBusinessObjType(uRLManager.i());
                            if (uRLManager.l() != null) {
                                ((BusinessObject) next).setParentBusinessObjType(uRLManager.l());
                            }
                        }
                    }
                    uRLManager.c(Boolean.valueOf(false));
                    uRLManager.a(str);
                    serializable.setUrlManager(uRLManager);
                    if (serializable != null && checkAuthTokenStatus && uRLManager.f().booleanValue()) {
                        hashMap.put(str2, serializable);
                        d.put(str2, String.valueOf(new Date().getTime()));
                        try {
                            this.f.a(str2, n.a(serializable), Boolean.valueOf(z), uRLManager.p());
                        } catch (Exception unused) {
                        }
                    }
                    return serializable;
                } catch (JsonSyntaxException e) {
                    ThrowableExtension.printStackTrace(e);
                    return i();
                } catch (JsonParseException e2) {
                    ThrowableExtension.printStackTrace(e2);
                    return i();
                } catch (NullPointerException e3) {
                    ThrowableExtension.printStackTrace(e3);
                    return i();
                }
            }
        } catch (Exception e4) {
            ThrowableExtension.printStackTrace(e4);
        }
        return i();
    }

    private BusinessObject i() {
        BusinessObject businessObject = new BusinessObject();
        businessObject.setVolleyError(new VolleyError());
        return businessObject;
    }

    public BusinessObject a(String str, BusinessObjectType businessObjectType) {
        Gson create = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();
        try {
            i a = this.g.a(str, true);
            if (a.b().booleanValue()) {
                Serializable serializable = (BusinessObject) create.fromJson(a.a(), Util.d(businessObjectType));
                if (serializable != null) {
                    serializable.setBusinessObjType(businessObjectType);
                    if (serializable.getArrListBusinessObj() != null) {
                        Iterator it = serializable.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            ((BusinessObject) it.next()).setBusinessObjType(businessObjectType);
                        }
                    }
                    b.put(str, serializable);
                    this.f.a(str, n.a(serializable), Boolean.valueOf(false));
                }
                return serializable;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return null;
    }

    public i a(String str, HashMap<String, String> hashMap) {
        Object[] toArray = hashMap.keySet().toArray();
        String str2 = str;
        for (int i = 0; i < hashMap.size(); i++) {
            String str3 = (String) hashMap.get(toArray[i].toString());
            if (str3 != null) {
                StringBuilder stringBuilder;
                if (i == hashMap.size() - 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(toArray[i]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str3));
                    str2 = stringBuilder.toString();
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(toArray[i]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str3));
                    stringBuilder.append("&");
                    str2 = stringBuilder.toString();
                }
            }
        }
        return this.g.a(str2, false);
    }

    public i a(String str, HashMap<String, String> hashMap, boolean z, int i) {
        Object[] toArray = hashMap.keySet().toArray();
        for (int i2 = 0; i2 < hashMap.size(); i2++) {
            String str2 = (String) hashMap.get(toArray[i2].toString());
            if (str2 != null) {
                StringBuilder stringBuilder;
                if (i2 == hashMap.size() - 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(toArray[i2]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str2));
                    str = stringBuilder.toString();
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(toArray[i2]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str2));
                    stringBuilder.append("&");
                    str = stringBuilder.toString();
                }
            }
        }
        return this.g.a(str, i, z);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:363:0x07b3=Splitter:B:363:0x07b3, B:316:0x0645=Splitter:B:316:0x0645, B:291:0x057a=Splitter:B:291:0x057a, B:342:0x070c=Splitter:B:342:0x070c} */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0540 A:{ExcHandler: InterruptedIOException | SocketException (e java.lang.Throwable), Splitter:B:75:0x0201} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0169 A:{ExcHandler: InterruptedIOException | SocketException (e java.lang.Throwable), Splitter:B:38:0x014a} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0540 A:{ExcHandler: InterruptedIOException | SocketException (e java.lang.Throwable), Splitter:B:75:0x0201} */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x053b A:{ExcHandler: IOException (e java.io.IOException), Splitter:B:75:0x0201} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0527 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:75:0x0201} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x07ac A:{ExcHandler: InterruptedIOException | SocketException (r0_95 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x07ac A:{ExcHandler: InterruptedIOException | SocketException (r0_95 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0705 A:{ExcHandler: IOException (r0_93 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0569 A:{ExcHandler: all (r0_88 'th' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0169 A:{ExcHandler: InterruptedIOException | SocketException (e java.lang.Throwable), Splitter:B:38:0x014a} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0161 A:{ExcHandler: Exception (e java.lang.Exception), Splitter:B:38:0x014a} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0159 A:{ExcHandler: OutOfMemoryError (e java.lang.OutOfMemoryError), Splitter:B:38:0x014a} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0151 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:38:0x014a} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x07ac A:{ExcHandler: InterruptedIOException | SocketException (r0_95 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x07ac A:{ExcHandler: InterruptedIOException | SocketException (r0_95 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x0705 A:{ExcHandler: IOException (r0_93 'e' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0569 A:{ExcHandler: all (r0_88 'th' java.lang.Throwable), Splitter:B:2:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:366:0x07e0 A:{SYNTHETIC, Splitter:B:366:0x07e0} */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x07ec A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x07f1 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x07f6 A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x07fe A:{Catch:{ Exception -> 0x07e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x074c A:{SYNTHETIC, Splitter:B:345:0x074c} */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x0758 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x075d A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0762 A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x076a A:{Catch:{ Exception -> 0x0753 }} */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x0679 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x065b A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06a4 A:{SYNTHETIC, Splitter:B:323:0x06a4} */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x06b0 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06b5 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x06ba A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06c2 A:{Catch:{ Exception -> 0x06ab }} */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x05ae A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0590 A:{Catch:{ all -> 0x0840 }} */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x05dc A:{SYNTHETIC, Splitter:B:298:0x05dc} */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05e8 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x05ed A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05f2 A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05fa A:{Catch:{ Exception -> 0x05e3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0844 A:{SYNTHETIC, Splitter:B:385:0x0844} */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x0853 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0858 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x085d A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0865 A:{Catch:{ Exception -> 0x084e }} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:40:0x0151, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:44:0x0159, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:48:0x0161, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:52:0x0169, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:73:0x01fd, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:243:0x0519, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:244:0x051a, code skipped:
            r4 = r9;
     */
    /* JADX WARNING: Missing block: B:247:0x0520, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:248:0x0521, code skipped:
            r4 = r9;
     */
    /* JADX WARNING: Missing block: B:251:0x0527, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:252:0x0528, code skipped:
            r4 = r9;
     */
    /* JADX WARNING: Missing block: B:261:0x053b, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:262:0x053c, code skipped:
            r4 = r9;
     */
    /* JADX WARNING: Missing block: B:265:0x0540, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:266:0x0541, code skipped:
            r4 = r9;
     */
    /* JADX WARNING: Missing block: B:282:0x0561, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:283:0x0562, code skipped:
            r15 = r8;
     */
    /* JADX WARNING: Missing block: B:284:0x0565, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:285:0x0566, code skipped:
            r15 = r8;
     */
    /* JADX WARNING: Missing block: B:286:0x0569, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:287:0x056a, code skipped:
            r2 = r0;
            r3 = null;
            r4 = null;
            r5 = null;
            r7 = null;
            r9 = null;
     */
    /* JADX WARNING: Missing block: B:288:0x0572, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:289:0x0573, code skipped:
            r2 = r0;
            r3 = null;
            r4 = null;
            r5 = null;
            r7 = null;
            r8 = -1;
     */
    /* JADX WARNING: Missing block: B:294:0x0590, code skipped:
            r12 = new java.lang.StringBuilder();
            r12.append("Error code : ");
            r12.append(r8);
            r12.append(" Caught Message : ");
            r12.append(r2.getMessage());
            r2 = r12.toString();
     */
    /* JADX WARNING: Missing block: B:295:0x05ae, code skipped:
            r2 = r2.getMessage();
     */
    /* JADX WARNING: Missing block: B:299:?, code skipped:
            r3.flush();
            r3.close();
     */
    /* JADX WARNING: Missing block: B:300:0x05e3, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:301:0x05e4, code skipped:
            r2 = r0;
     */
    /* JADX WARNING: Missing block: B:303:0x05e8, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:305:0x05ed, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:307:0x05f2, code skipped:
            r7.flush();
            r7.close();
     */
    /* JADX WARNING: Missing block: B:309:0x05fa, code skipped:
            r5.flush();
            r5.close();
     */
    /* JADX WARNING: Missing block: B:311:?, code skipped:
            r5 = new java.lang.StringBuilder();
            r5.append("Media not fetched - ");
            r5.append(r2.getMessage());
            r2 = r5.toString();
            r5 = new java.lang.StringBuilder();
            r5.append(r1);
            r5.append(" - ");
            r5.append(com.utilities.Util.Q());
            com.managers.u.a().a("DownloadFailure", r2, r5.toString());
     */
    /* JADX WARNING: Missing block: B:312:0x0638, code skipped:
            com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);
     */
    /* JADX WARNING: Missing block: B:313:0x063d, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:314:0x063e, code skipped:
            r2 = r0;
            r3 = null;
            r4 = null;
            r5 = null;
            r7 = null;
            r8 = -1;
     */
    /* JADX WARNING: Missing block: B:319:0x065b, code skipped:
            r12 = new java.lang.StringBuilder();
            r12.append("Error code : ");
            r12.append(r8);
            r12.append(" Caught Message : ");
            r12.append(r2.getMessage());
            r2 = r12.toString();
     */
    /* JADX WARNING: Missing block: B:320:0x0679, code skipped:
            r2 = r2.getMessage();
     */
    /* JADX WARNING: Missing block: B:324:?, code skipped:
            r3.flush();
            r3.close();
     */
    /* JADX WARNING: Missing block: B:325:0x06ab, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:326:0x06ac, code skipped:
            r2 = r0;
     */
    /* JADX WARNING: Missing block: B:328:0x06b0, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:330:0x06b5, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:332:0x06ba, code skipped:
            r7.flush();
            r7.close();
     */
    /* JADX WARNING: Missing block: B:334:0x06c2, code skipped:
            r5.flush();
            r5.close();
     */
    /* JADX WARNING: Missing block: B:336:?, code skipped:
            r5 = new java.lang.StringBuilder();
            r5.append("Media not fetched - ");
            r5.append(r2.getMessage());
            r2 = r5.toString();
            r5 = new java.lang.StringBuilder();
            r5.append(r1);
            r5.append(" - ");
            r5.append(com.utilities.Util.Q());
            com.managers.u.a().a("DownloadFailure", r2, r5.toString());
     */
    /* JADX WARNING: Missing block: B:340:0x0705, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:341:0x0706, code skipped:
            r2 = r0;
            r3 = null;
            r4 = null;
            r5 = null;
            r7 = null;
            r9 = null;
     */
    /* JADX WARNING: Missing block: B:346:?, code skipped:
            r3.flush();
            r3.close();
     */
    /* JADX WARNING: Missing block: B:347:0x0753, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:348:0x0754, code skipped:
            r3 = r0;
     */
    /* JADX WARNING: Missing block: B:350:0x0758, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:352:0x075d, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:354:0x0762, code skipped:
            r7.flush();
            r7.close();
     */
    /* JADX WARNING: Missing block: B:356:0x076a, code skipped:
            r5.flush();
            r5.close();
     */
    /* JADX WARNING: Missing block: B:358:?, code skipped:
            r6 = new java.lang.StringBuilder();
            r6.append("Media not fetched - ");
            r6.append(r3.getMessage());
            r3 = r6.toString();
            r6 = new java.lang.StringBuilder();
            r6.append(r1);
            r6.append(" - ");
            r6.append(com.utilities.Util.Q());
            com.managers.u.a().a("DownloadFailure", r3, r6.toString());
            com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);
     */
    /* JADX WARNING: Missing block: B:361:0x07ac, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:362:0x07ad, code skipped:
            r2 = r0;
            r3 = null;
            r4 = null;
            r5 = null;
            r7 = null;
            r9 = null;
     */
    /* JADX WARNING: Missing block: B:367:?, code skipped:
            r3.flush();
            r3.close();
     */
    /* JADX WARNING: Missing block: B:368:0x07e7, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:369:0x07e8, code skipped:
            r3 = r0;
     */
    /* JADX WARNING: Missing block: B:371:0x07ec, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:373:0x07f1, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:375:0x07f6, code skipped:
            r7.flush();
            r7.close();
     */
    /* JADX WARNING: Missing block: B:377:0x07fe, code skipped:
            r5.flush();
            r5.close();
     */
    /* JADX WARNING: Missing block: B:379:?, code skipped:
            r6 = new java.lang.StringBuilder();
            r6.append("Media not fetched - ");
            r6.append(r3.getMessage());
            r3 = r6.toString();
            r6 = new java.lang.StringBuilder();
            r6.append(r1);
            r6.append(" - ");
            r6.append(com.utilities.Util.Q());
            com.managers.u.a().a("DownloadFailure", r3, r6.toString());
            com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);
     */
    /* JADX WARNING: Missing block: B:386:?, code skipped:
            r3.flush();
            r3.close();
     */
    /* JADX WARNING: Missing block: B:389:0x084e, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:390:0x084f, code skipped:
            r3 = r0;
     */
    /* JADX WARNING: Missing block: B:392:0x0853, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:394:0x0858, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:396:0x085d, code skipped:
            r7.flush();
            r7.close();
     */
    /* JADX WARNING: Missing block: B:398:0x0865, code skipped:
            r5.flush();
            r5.close();
     */
    /* JADX WARNING: Missing block: B:400:?, code skipped:
            r6 = new java.lang.StringBuilder();
            r6.append("Media not fetched - ");
            r6.append(r3.getMessage());
            r3 = r6.toString();
            r6 = new java.lang.StringBuilder();
            r6.append(r1);
            r6.append(" - ");
            r6.append(com.utilities.Util.Q());
            com.managers.u.a().a("DownloadFailure", r3, r6.toString());
            com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);
     */
    public synchronized com.managers.DownloadManager.DownloadHTTPStatus a(java.lang.String r23, java.lang.String r24) {
        /*
        r22 = this;
        r1 = r24;
        monitor-enter(r22);
        r4 = com.managers.DownloadManager.c();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r5 = java.lang.Integer.parseInt(r24);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = r4.o(r5);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = r4.booleanValue();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        if (r4 == 0) goto L_0x0019;
    L_0x0015:
        r4 = com.managers.DownloadManager.DownloadHTTPStatus.SUCCESS;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        monitor-exit(r22);
        return r4;
    L_0x0019:
        r4 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r5 = com.managers.SdCardManager.STORAGE_TYPE.SD_CARD;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = r4.a(r5);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r5 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r5.<init>(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r5.mkdirs();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r7 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = r6.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = r4.e(r6);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = android.text.TextUtils.isEmpty(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        if (r6 == 0) goto L_0x0061;
    L_0x004a:
        r4 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r7 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = r6.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4.<init>(r5, r6);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        goto L_0x008a;
    L_0x0061:
        r6 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r7 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = r6.c(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        if (r6 == 0) goto L_0x0084;
    L_0x006d:
        r4 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r7 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = r6.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4.<init>(r5, r6);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        goto L_0x008a;
    L_0x0084:
        r6 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.<init>(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r4 = r6;
    L_0x008a:
        r6 = new java.net.URL;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r7 = r23;
        r6.<init>(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6 = r6.openConnection();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r8 = "User-Agent";
        r9 = com.utilities.Util.R();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.setRequestProperty(r8, r9);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r8 = "Range";
        r9 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r9.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r10 = "bytes=";
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r10 = r4.length();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r10 = "-";
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r9 = r9.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.setRequestProperty(r8, r9);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r6.setReadTimeout(r8);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r6.connect();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r8 = r6;
        r8 = (java.net.HttpURLConnection) r8;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r8 = r8.getResponseCode();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x063d, OutOfMemoryError -> 0x0572, all -> 0x0569 }
        r9 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r8 != r9) goto L_0x0111;
    L_0x00d0:
        r4 = com.managers.DownloadManager.c();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4.d(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4 = com.managers.u.a();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r5 = "DownloadFailure";
        r6 = "Media not fetched - 404";
        r9 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r10 = " - ";
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r10 = com.utilities.Util.Q();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9 = r9.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4.a(r5, r6, r9);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4 = com.managers.DownloadManager.DownloadHTTPStatus.FAILED;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        monitor-exit(r22);
        return r4;
    L_0x0101:
        r0 = move-exception;
    L_0x0102:
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        goto L_0x0579;
    L_0x0109:
        r0 = move-exception;
    L_0x010a:
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        goto L_0x0644;
    L_0x0111:
        r9 = r6.getContentLength();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0565, OutOfMemoryError -> 0x0561, all -> 0x0569 }
        r10 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        if (r9 >= r10) goto L_0x0140;
    L_0x0119:
        r4 = com.managers.u.a();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r5 = "DownloadFailure";
        r6 = "Media not fetched - Media content is less than 1KB ";
        r9 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r10 = " - ";
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r10 = com.utilities.Util.Q();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r9 = r9.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4.a(r5, r6, r9);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        r4 = com.managers.DownloadManager.DownloadHTTPStatus.FAILED;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0109, OutOfMemoryError -> 0x0101, all -> 0x0569 }
        monitor-exit(r22);
        return r4;
    L_0x0140:
        r9 = new java.io.BufferedInputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0565, OutOfMemoryError -> 0x0561, all -> 0x0569 }
        r11 = r6.getInputStream();	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0565, OutOfMemoryError -> 0x0561, all -> 0x0569 }
        r9.<init>(r11);	 Catch:{ InterruptedIOException | SocketException -> 0x07ac, InterruptedIOException | SocketException -> 0x07ac, IOException -> 0x0705, Exception -> 0x0565, OutOfMemoryError -> 0x0561, all -> 0x0569 }
        r11 = 1;
        r12 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0171, InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>(r4, r11);	 Catch:{ IOException -> 0x0171, InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        goto L_0x0201;
    L_0x0151:
        r0 = move-exception;
    L_0x0152:
        r2 = r0;
        r3 = 0;
    L_0x0154:
        r4 = 0;
    L_0x0155:
        r5 = 0;
        r7 = 0;
        goto L_0x0842;
    L_0x0159:
        r0 = move-exception;
    L_0x015a:
        r2 = r0;
        r3 = 0;
    L_0x015c:
        r4 = 0;
    L_0x015d:
        r5 = 0;
        r7 = 0;
        goto L_0x057a;
    L_0x0161:
        r0 = move-exception;
    L_0x0162:
        r2 = r0;
        r3 = 0;
    L_0x0164:
        r4 = 0;
    L_0x0165:
        r5 = 0;
        r7 = 0;
        goto L_0x0645;
    L_0x0169:
        r0 = move-exception;
    L_0x016a:
        r2 = r0;
        r3 = 0;
    L_0x016c:
        r4 = 0;
    L_0x016d:
        r5 = 0;
        r7 = 0;
        goto L_0x07b3;
    L_0x0171:
        r0 = move-exception;
        r12 = r0;
        r12 = r12.getMessage();	 Catch:{ InterruptedIOException | SocketException -> 0x055d, InterruptedIOException | SocketException -> 0x055d, IOException -> 0x0554, Exception -> 0x054f, OutOfMemoryError -> 0x054a, all -> 0x0546 }
        r13 = "open failed: EACCES (Permission denied)";
        r12 = r12.contains(r13);	 Catch:{ InterruptedIOException | SocketException -> 0x055d, InterruptedIOException | SocketException -> 0x055d, IOException -> 0x0554, Exception -> 0x054f, OutOfMemoryError -> 0x054a, all -> 0x0546 }
        if (r12 == 0) goto L_0x0200;
    L_0x017f:
        r5 = r4.exists();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        if (r5 == 0) goto L_0x0188;
    L_0x0185:
        r4.delete();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
    L_0x0188:
        r4 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4 = r4.b();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r5 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r5.<init>(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r5.mkdirs();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r13 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r13);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = r12.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4 = r4.f(r12);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = android.text.TextUtils.isEmpty(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        if (r12 == 0) goto L_0x01ce;
    L_0x01b7:
        r4 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r13 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r13);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = r12.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4.<init>(r5, r12);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        goto L_0x01f7;
    L_0x01ce:
        r12 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r13 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = r12.c(r13);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        if (r12 == 0) goto L_0x01f1;
    L_0x01da:
        r4 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r13 = k;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.append(r13);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12 = r12.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4.<init>(r5, r12);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        goto L_0x01f7;
    L_0x01f1:
        r12 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r4 = r12;
    L_0x01f7:
        r12 = new java.io.FileOutputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        r12.<init>(r4, r11);	 Catch:{ InterruptedIOException | SocketException -> 0x0169, InterruptedIOException | SocketException -> 0x0169, IOException -> 0x01fd, Exception -> 0x0161, OutOfMemoryError -> 0x0159, all -> 0x0151 }
        goto L_0x0201;
    L_0x01fd:
        r0 = move-exception;
        goto L_0x0556;
    L_0x0200:
        r12 = 0;
    L_0x0201:
        r11 = new byte[r10];	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r13 = r4.length();	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r13 = (int) r13;	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r6 = r6.getContentLength();	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r14 = "kb";
        r3 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        if (r6 < r3) goto L_0x0225;
    L_0x0212:
        r14 = "mb";
        r10 = r3;
        goto L_0x0225;
    L_0x0216:
        r0 = move-exception;
        goto L_0x0529;
    L_0x0219:
        r0 = move-exception;
        goto L_0x0530;
    L_0x021c:
        r0 = move-exception;
        goto L_0x0537;
    L_0x021f:
        r0 = move-exception;
        goto L_0x053d;
    L_0x0222:
        r0 = move-exception;
        goto L_0x0542;
    L_0x0225:
        r6 = r6 + r13;
        r3 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r3 = com.managers.j.a(r3);	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r2 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0534, OutOfMemoryError -> 0x052d, all -> 0x0527 }
        r15 = r8;
        r8 = 2131821172; // 0x7f110274 float:1.927508E38 double:1.053259604E-314;
        r2 = r2.getString(r8);	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r3.a(r6, r13, r2);	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r2 = (float) r6;	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r3 = (float) r10;	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r2 = r2 / r3;
        r10 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r2 = r2 * r10;
        r2 = java.lang.Math.round(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r2 = (float) r2;	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        r2 = r2 / r10;
        r8 = 0;
        r16 = r8;
    L_0x024c:
        r10 = r9.read(r11);	 Catch:{ InterruptedIOException | SocketException -> 0x0540, InterruptedIOException | SocketException -> 0x0540, IOException -> 0x053b, Exception -> 0x0520, OutOfMemoryError -> 0x0519, all -> 0x0527 }
        if (r10 <= 0) goto L_0x02e4;
    L_0x0252:
        r18 = com.services.FileDownloadService.a();	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        if (r18 == 0) goto L_0x02e4;
    L_0x0258:
        r12.write(r11, r8, r10);	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r13 = r13 + r10;
        r10 = r13 * 100;
        r10 = r10 / r6;
        r18 = r10 - r16;
        if (r18 <= 0) goto L_0x02b6;
    L_0x0263:
        r8 = (float) r13;	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r8 = r8 / r3;
        r17 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r8 * r17;
        r8 = java.lang.Math.round(r8);	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r8 = (float) r8;	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r8 = r8 / r17;
        r19 = r3;
        r3 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r3 = com.managers.j.a(r3);	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r20 = r11;
        r11 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r11.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r7 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ InterruptedIOException | SocketException -> 0x02df, InterruptedIOException | SocketException -> 0x02df, IOException -> 0x02da, Exception -> 0x02d5, OutOfMemoryError -> 0x02d0, all -> 0x02cb }
        r21 = r9;
        r9 = 2131821172; // 0x7f110274 float:1.927508E38 double:1.053259604E-314;
        r7 = r7.getString(r9);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r11.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r11.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r7 = "% (";
        r11.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r11.append(r8);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r7 = "/";
        r11.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r11.append(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r11.append(r14);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r7 = ")";
        r11.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r7 = r11.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r3.a(r6, r13, r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r16 = r10;
        goto L_0x02c1;
    L_0x02b6:
        r19 = r3;
        r21 = r9;
        r20 = r11;
        r9 = 2131821172; // 0x7f110274 float:1.927508E38 double:1.053259604E-314;
        r17 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
    L_0x02c1:
        r3 = r19;
        r11 = r20;
        r9 = r21;
        r7 = r23;
        r8 = 0;
        goto L_0x024c;
    L_0x02cb:
        r0 = move-exception;
        r21 = r9;
        goto L_0x0529;
    L_0x02d0:
        r0 = move-exception;
        r21 = r9;
        goto L_0x051b;
    L_0x02d5:
        r0 = move-exception;
        r21 = r9;
        goto L_0x0522;
    L_0x02da:
        r0 = move-exception;
        r21 = r9;
        goto L_0x053d;
    L_0x02df:
        r0 = move-exception;
        r21 = r9;
        goto L_0x0542;
    L_0x02e4:
        r21 = r9;
        r2 = com.services.FileDownloadService.a();	 Catch:{ InterruptedIOException | SocketException -> 0x0513, InterruptedIOException | SocketException -> 0x0513, IOException -> 0x050d, Exception -> 0x0507, OutOfMemoryError -> 0x0501, all -> 0x04fb }
        if (r2 == 0) goto L_0x04a0;
    L_0x02ec:
        r2 = com.utilities.d.b();	 Catch:{ InterruptedIOException | SocketException -> 0x0513, InterruptedIOException | SocketException -> 0x0513, IOException -> 0x050d, Exception -> 0x0507, OutOfMemoryError -> 0x0501, all -> 0x04fb }
        if (r2 == 0) goto L_0x03fb;
    L_0x02f2:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = new byte[r2];	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r6 = new java.io.FileInputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r6.<init>(r4);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r3.<init>(r6);	 Catch:{ InterruptedIOException | SocketException -> 0x03f4, InterruptedIOException | SocketException -> 0x03f4, IOException -> 0x03ed, Exception -> 0x03e5, OutOfMemoryError -> 0x03dd, all -> 0x03d6 }
        r6 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r8 = com.utilities.i.b;	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7.append(r8);	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7 = r7.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r6.<init>(r5, r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r7 = new java.io.FileOutputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r8 = 0;
        r7.<init>(r6, r8);	 Catch:{ InterruptedIOException | SocketException -> 0x03ce, InterruptedIOException | SocketException -> 0x03ce, IOException -> 0x03c6, Exception -> 0x03bd, OutOfMemoryError -> 0x03b4, all -> 0x03ac }
        r9 = com.utilities.i.a(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x03a3, InterruptedIOException | SocketException -> 0x03a3, IOException -> 0x039a, Exception -> 0x0390, OutOfMemoryError -> 0x0386, all -> 0x037d }
    L_0x0320:
        r10 = r3.read(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        if (r10 <= 0) goto L_0x0330;
    L_0x0326:
        r11 = com.services.FileDownloadService.a();	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        if (r11 == 0) goto L_0x0330;
    L_0x032c:
        r9.write(r2, r8, r10);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        goto L_0x0320;
    L_0x0330:
        r2 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r8 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r8.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r8.append(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r10 = com.utilities.i.a;	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r8.append(r10);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r8 = r8.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r2.<init>(r5, r8);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r6.renameTo(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        r4.delete();	 Catch:{ InterruptedIOException | SocketException -> 0x0374, InterruptedIOException | SocketException -> 0x0374, IOException -> 0x036b, Exception -> 0x0361, OutOfMemoryError -> 0x0357, all -> 0x034e }
        goto L_0x0406;
    L_0x034e:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r5 = r9;
        r3 = r12;
        r9 = r21;
        goto L_0x0842;
    L_0x0357:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r5 = r9;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x057a;
    L_0x0361:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r5 = r9;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x0645;
    L_0x036b:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r5 = r9;
        r3 = r12;
        r9 = r21;
        goto L_0x070c;
    L_0x0374:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r5 = r9;
        r3 = r12;
        r9 = r21;
        goto L_0x07b3;
    L_0x037d:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        r5 = 0;
        goto L_0x0842;
    L_0x0386:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        r5 = 0;
        goto L_0x057a;
    L_0x0390:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        r5 = 0;
        goto L_0x0645;
    L_0x039a:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        r5 = 0;
        goto L_0x070c;
    L_0x03a3:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        r5 = 0;
        goto L_0x07b3;
    L_0x03ac:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        goto L_0x0155;
    L_0x03b4:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x015d;
    L_0x03bd:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x0165;
    L_0x03c6:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        goto L_0x0559;
    L_0x03ce:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r3 = r12;
        r9 = r21;
        goto L_0x016d;
    L_0x03d6:
        r0 = move-exception;
        r2 = r0;
        r3 = r12;
        r9 = r21;
        goto L_0x0154;
    L_0x03dd:
        r0 = move-exception;
        r2 = r0;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x015c;
    L_0x03e5:
        r0 = move-exception;
        r2 = r0;
        r3 = r12;
        r8 = r15;
        r9 = r21;
        goto L_0x0164;
    L_0x03ed:
        r0 = move-exception;
        r2 = r0;
        r3 = r12;
        r9 = r21;
        goto L_0x0558;
    L_0x03f4:
        r0 = move-exception;
        r2 = r0;
        r3 = r12;
        r9 = r21;
        goto L_0x016c;
    L_0x03fb:
        r2 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0513, InterruptedIOException | SocketException -> 0x0513, IOException -> 0x050d, Exception -> 0x0507, OutOfMemoryError -> 0x0501, all -> 0x04fb }
        r2.<init>(r5, r1);	 Catch:{ InterruptedIOException | SocketException -> 0x0513, InterruptedIOException | SocketException -> 0x0513, IOException -> 0x050d, Exception -> 0x0507, OutOfMemoryError -> 0x0501, all -> 0x04fb }
        r4.renameTo(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x0513, InterruptedIOException | SocketException -> 0x0513, IOException -> 0x050d, Exception -> 0x0507, OutOfMemoryError -> 0x0501, all -> 0x04fb }
        r3 = 0;
        r7 = 0;
        r9 = 0;
    L_0x0406:
        r2 = com.managers.DownloadManager.DownloadHTTPStatus.SUCCESS;	 Catch:{ InterruptedIOException | SocketException -> 0x0496, InterruptedIOException | SocketException -> 0x0496, IOException -> 0x048c, Exception -> 0x0481, OutOfMemoryError -> 0x0476, all -> 0x046c }
        if (r12 == 0) goto L_0x0414;
    L_0x040a:
        r12.flush();	 Catch:{ Exception -> 0x0411 }
        r12.close();	 Catch:{ Exception -> 0x0411 }
        goto L_0x0414;
    L_0x0411:
        r0 = move-exception;
        r3 = r0;
        goto L_0x0431;
    L_0x0414:
        if (r21 == 0) goto L_0x041b;
    L_0x0416:
        r4 = r21;
        r4.close();	 Catch:{ Exception -> 0x0411 }
    L_0x041b:
        if (r3 == 0) goto L_0x0420;
    L_0x041d:
        r3.close();	 Catch:{ Exception -> 0x0411 }
    L_0x0420:
        if (r7 == 0) goto L_0x0428;
    L_0x0422:
        r7.flush();	 Catch:{ Exception -> 0x0411 }
        r7.close();	 Catch:{ Exception -> 0x0411 }
    L_0x0428:
        if (r9 == 0) goto L_0x046a;
    L_0x042a:
        r9.flush();	 Catch:{ Exception -> 0x0411 }
        r9.close();	 Catch:{ Exception -> 0x0411 }
        goto L_0x046a;
    L_0x0431:
        r4 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r5 = "DownloadFailure";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r7 = "Media not fetched - ";
        r6.append(r7);	 Catch:{ all -> 0x084b }
        r3 = r3.getMessage();	 Catch:{ all -> 0x084b }
        r6.append(r3);	 Catch:{ all -> 0x084b }
        r3 = r6.toString();	 Catch:{ all -> 0x084b }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r6.toString();	 Catch:{ all -> 0x084b }
        r4.a(r5, r3, r1);	 Catch:{ all -> 0x084b }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
    L_0x046a:
        monitor-exit(r22);
        return r2;
    L_0x046c:
        r0 = move-exception;
        r4 = r21;
        r2 = r0;
        r5 = r9;
        r9 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0842;
    L_0x0476:
        r0 = move-exception;
        r4 = r21;
        r2 = r0;
        r5 = r9;
        r8 = r15;
        r9 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x057a;
    L_0x0481:
        r0 = move-exception;
        r4 = r21;
        r2 = r0;
        r5 = r9;
        r8 = r15;
        r9 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0645;
    L_0x048c:
        r0 = move-exception;
        r4 = r21;
        r2 = r0;
        r5 = r9;
        r9 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x070c;
    L_0x0496:
        r0 = move-exception;
        r4 = r21;
        r2 = r0;
        r5 = r9;
        r9 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x07b3;
    L_0x04a0:
        r4 = r21;
        r2 = com.managers.DownloadManager.DownloadHTTPStatus.CONNECTION_RESET;	 Catch:{ InterruptedIOException | SocketException -> 0x04f9, InterruptedIOException | SocketException -> 0x04f9, IOException -> 0x04f7, Exception -> 0x04f5, OutOfMemoryError -> 0x04f3, all -> 0x04f1 }
        if (r12 == 0) goto L_0x04b0;
    L_0x04a6:
        r12.flush();	 Catch:{ Exception -> 0x04ad }
        r12.close();	 Catch:{ Exception -> 0x04ad }
        goto L_0x04b0;
    L_0x04ad:
        r0 = move-exception;
        r3 = r0;
        goto L_0x04b6;
    L_0x04b0:
        if (r4 == 0) goto L_0x04ef;
    L_0x04b2:
        r4.close();	 Catch:{ Exception -> 0x04ad }
        goto L_0x04ef;
    L_0x04b6:
        r4 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r5 = "DownloadFailure";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r7 = "Media not fetched - ";
        r6.append(r7);	 Catch:{ all -> 0x084b }
        r3 = r3.getMessage();	 Catch:{ all -> 0x084b }
        r6.append(r3);	 Catch:{ all -> 0x084b }
        r3 = r6.toString();	 Catch:{ all -> 0x084b }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r6.toString();	 Catch:{ all -> 0x084b }
        r4.a(r5, r3, r1);	 Catch:{ all -> 0x084b }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
    L_0x04ef:
        monitor-exit(r22);
        return r2;
    L_0x04f1:
        r0 = move-exception;
        goto L_0x04fe;
    L_0x04f3:
        r0 = move-exception;
        goto L_0x0504;
    L_0x04f5:
        r0 = move-exception;
        goto L_0x050a;
    L_0x04f7:
        r0 = move-exception;
        goto L_0x0510;
    L_0x04f9:
        r0 = move-exception;
        goto L_0x0516;
    L_0x04fb:
        r0 = move-exception;
        r4 = r21;
    L_0x04fe:
        r2 = r0;
        r9 = r4;
        goto L_0x052a;
    L_0x0501:
        r0 = move-exception;
        r4 = r21;
    L_0x0504:
        r2 = r0;
        r9 = r4;
        goto L_0x051c;
    L_0x0507:
        r0 = move-exception;
        r4 = r21;
    L_0x050a:
        r2 = r0;
        r9 = r4;
        goto L_0x0523;
    L_0x050d:
        r0 = move-exception;
        r4 = r21;
    L_0x0510:
        r2 = r0;
        r9 = r4;
        goto L_0x053e;
    L_0x0513:
        r0 = move-exception;
        r4 = r21;
    L_0x0516:
        r2 = r0;
        r9 = r4;
        goto L_0x0543;
    L_0x0519:
        r0 = move-exception;
        r4 = r9;
    L_0x051b:
        r2 = r0;
    L_0x051c:
        r3 = r12;
        r8 = r15;
        goto L_0x015c;
    L_0x0520:
        r0 = move-exception;
        r4 = r9;
    L_0x0522:
        r2 = r0;
    L_0x0523:
        r3 = r12;
        r8 = r15;
        goto L_0x0164;
    L_0x0527:
        r0 = move-exception;
        r4 = r9;
    L_0x0529:
        r2 = r0;
    L_0x052a:
        r3 = r12;
        goto L_0x0154;
    L_0x052d:
        r0 = move-exception;
        r15 = r8;
        r4 = r9;
    L_0x0530:
        r2 = r0;
        r3 = r12;
        goto L_0x015c;
    L_0x0534:
        r0 = move-exception;
        r15 = r8;
        r4 = r9;
    L_0x0537:
        r2 = r0;
        r3 = r12;
        goto L_0x0164;
    L_0x053b:
        r0 = move-exception;
        r4 = r9;
    L_0x053d:
        r2 = r0;
    L_0x053e:
        r3 = r12;
        goto L_0x0558;
    L_0x0540:
        r0 = move-exception;
        r4 = r9;
    L_0x0542:
        r2 = r0;
    L_0x0543:
        r3 = r12;
        goto L_0x016c;
    L_0x0546:
        r0 = move-exception;
        r4 = r9;
        goto L_0x0152;
    L_0x054a:
        r0 = move-exception;
        r15 = r8;
        r4 = r9;
        goto L_0x015a;
    L_0x054f:
        r0 = move-exception;
        r15 = r8;
        r4 = r9;
        goto L_0x0162;
    L_0x0554:
        r0 = move-exception;
        r4 = r9;
    L_0x0556:
        r2 = r0;
        r3 = 0;
    L_0x0558:
        r4 = 0;
    L_0x0559:
        r5 = 0;
        r7 = 0;
        goto L_0x070c;
    L_0x055d:
        r0 = move-exception;
        r4 = r9;
        goto L_0x016a;
    L_0x0561:
        r0 = move-exception;
        r15 = r8;
        goto L_0x0102;
    L_0x0565:
        r0 = move-exception;
        r15 = r8;
        goto L_0x010a;
    L_0x0569:
        r0 = move-exception;
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        r9 = 0;
        goto L_0x0842;
    L_0x0572:
        r0 = move-exception;
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        r8 = -1;
    L_0x0579:
        r9 = 0;
    L_0x057a:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0840 }
        r6 = com.managers.u.a();	 Catch:{ all -> 0x0840 }
        r10 = "DownloadFailure";
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r11.<init>();	 Catch:{ all -> 0x0840 }
        r12 = "Media not fetched - ";
        r11.append(r12);	 Catch:{ all -> 0x0840 }
        r12 = -1;
        if (r8 == r12) goto L_0x05ae;
    L_0x0590:
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r12.<init>();	 Catch:{ all -> 0x0840 }
        r13 = "Error code : ";
        r12.append(r13);	 Catch:{ all -> 0x0840 }
        r12.append(r8);	 Catch:{ all -> 0x0840 }
        r8 = " Caught Message : ";
        r12.append(r8);	 Catch:{ all -> 0x0840 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0840 }
        r12.append(r2);	 Catch:{ all -> 0x0840 }
        r2 = r12.toString();	 Catch:{ all -> 0x0840 }
        goto L_0x05b2;
    L_0x05ae:
        r2 = r2.getMessage();	 Catch:{ all -> 0x0840 }
    L_0x05b2:
        r11.append(r2);	 Catch:{ all -> 0x0840 }
        r2 = r11.toString();	 Catch:{ all -> 0x0840 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r8.<init>();	 Catch:{ all -> 0x0840 }
        r8.append(r1);	 Catch:{ all -> 0x0840 }
        r11 = " - ";
        r8.append(r11);	 Catch:{ all -> 0x0840 }
        r11 = com.utilities.Util.Q();	 Catch:{ all -> 0x0840 }
        r8.append(r11);	 Catch:{ all -> 0x0840 }
        r8 = r8.toString();	 Catch:{ all -> 0x0840 }
        r6.a(r10, r2, r8);	 Catch:{ all -> 0x0840 }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x0840 }
        java.lang.System.gc();	 Catch:{ all -> 0x0840 }
        if (r3 == 0) goto L_0x05e6;
    L_0x05dc:
        r3.flush();	 Catch:{ Exception -> 0x05e3 }
        r3.close();	 Catch:{ Exception -> 0x05e3 }
        goto L_0x05e6;
    L_0x05e3:
        r0 = move-exception;
        r2 = r0;
        goto L_0x0602;
    L_0x05e6:
        if (r9 == 0) goto L_0x05eb;
    L_0x05e8:
        r9.close();	 Catch:{ Exception -> 0x05e3 }
    L_0x05eb:
        if (r4 == 0) goto L_0x05f0;
    L_0x05ed:
        r4.close();	 Catch:{ Exception -> 0x05e3 }
    L_0x05f0:
        if (r7 == 0) goto L_0x05f8;
    L_0x05f2:
        r7.flush();	 Catch:{ Exception -> 0x05e3 }
        r7.close();	 Catch:{ Exception -> 0x05e3 }
    L_0x05f8:
        if (r5 == 0) goto L_0x0701;
    L_0x05fa:
        r5.flush();	 Catch:{ Exception -> 0x05e3 }
        r5.close();	 Catch:{ Exception -> 0x05e3 }
        goto L_0x0701;
    L_0x0602:
        r3 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r4 = "DownloadFailure";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r5.<init>();	 Catch:{ all -> 0x084b }
        r6 = "Media not fetched - ";
        r5.append(r6);	 Catch:{ all -> 0x084b }
        r2 = r2.getMessage();	 Catch:{ all -> 0x084b }
        r5.append(r2);	 Catch:{ all -> 0x084b }
        r2 = r5.toString();	 Catch:{ all -> 0x084b }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r5.<init>();	 Catch:{ all -> 0x084b }
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r5.toString();	 Catch:{ all -> 0x084b }
        r3.a(r4, r2, r1);	 Catch:{ all -> 0x084b }
    L_0x0638:
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
        goto L_0x0701;
    L_0x063d:
        r0 = move-exception;
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        r8 = -1;
    L_0x0644:
        r9 = 0;
    L_0x0645:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0840 }
        r6 = com.managers.u.a();	 Catch:{ all -> 0x0840 }
        r10 = "DownloadFailure";
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r11.<init>();	 Catch:{ all -> 0x0840 }
        r12 = "Media not fetched - ";
        r11.append(r12);	 Catch:{ all -> 0x0840 }
        r12 = -1;
        if (r8 == r12) goto L_0x0679;
    L_0x065b:
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r12.<init>();	 Catch:{ all -> 0x0840 }
        r13 = "Error code : ";
        r12.append(r13);	 Catch:{ all -> 0x0840 }
        r12.append(r8);	 Catch:{ all -> 0x0840 }
        r8 = " Caught Message : ";
        r12.append(r8);	 Catch:{ all -> 0x0840 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0840 }
        r12.append(r2);	 Catch:{ all -> 0x0840 }
        r2 = r12.toString();	 Catch:{ all -> 0x0840 }
        goto L_0x067d;
    L_0x0679:
        r2 = r2.getMessage();	 Catch:{ all -> 0x0840 }
    L_0x067d:
        r11.append(r2);	 Catch:{ all -> 0x0840 }
        r2 = r11.toString();	 Catch:{ all -> 0x0840 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r8.<init>();	 Catch:{ all -> 0x0840 }
        r8.append(r1);	 Catch:{ all -> 0x0840 }
        r11 = " - ";
        r8.append(r11);	 Catch:{ all -> 0x0840 }
        r11 = com.utilities.Util.Q();	 Catch:{ all -> 0x0840 }
        r8.append(r11);	 Catch:{ all -> 0x0840 }
        r8 = r8.toString();	 Catch:{ all -> 0x0840 }
        r6.a(r10, r2, r8);	 Catch:{ all -> 0x0840 }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x0840 }
        if (r3 == 0) goto L_0x06ae;
    L_0x06a4:
        r3.flush();	 Catch:{ Exception -> 0x06ab }
        r3.close();	 Catch:{ Exception -> 0x06ab }
        goto L_0x06ae;
    L_0x06ab:
        r0 = move-exception;
        r2 = r0;
        goto L_0x06c9;
    L_0x06ae:
        if (r9 == 0) goto L_0x06b3;
    L_0x06b0:
        r9.close();	 Catch:{ Exception -> 0x06ab }
    L_0x06b3:
        if (r4 == 0) goto L_0x06b8;
    L_0x06b5:
        r4.close();	 Catch:{ Exception -> 0x06ab }
    L_0x06b8:
        if (r7 == 0) goto L_0x06c0;
    L_0x06ba:
        r7.flush();	 Catch:{ Exception -> 0x06ab }
        r7.close();	 Catch:{ Exception -> 0x06ab }
    L_0x06c0:
        if (r5 == 0) goto L_0x0701;
    L_0x06c2:
        r5.flush();	 Catch:{ Exception -> 0x06ab }
        r5.close();	 Catch:{ Exception -> 0x06ab }
        goto L_0x0701;
    L_0x06c9:
        r3 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r4 = "DownloadFailure";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r5.<init>();	 Catch:{ all -> 0x084b }
        r6 = "Media not fetched - ";
        r5.append(r6);	 Catch:{ all -> 0x084b }
        r2 = r2.getMessage();	 Catch:{ all -> 0x084b }
        r5.append(r2);	 Catch:{ all -> 0x084b }
        r2 = r5.toString();	 Catch:{ all -> 0x084b }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r5.<init>();	 Catch:{ all -> 0x084b }
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r5.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r5.toString();	 Catch:{ all -> 0x084b }
        r3.a(r4, r2, r1);	 Catch:{ all -> 0x084b }
        goto L_0x0638;
    L_0x0701:
        r1 = com.managers.DownloadManager.DownloadHTTPStatus.FAILED;	 Catch:{ all -> 0x084b }
        monitor-exit(r22);
        return r1;
    L_0x0705:
        r0 = move-exception;
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        r9 = 0;
    L_0x070c:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0840 }
        r6 = com.managers.u.a();	 Catch:{ all -> 0x0840 }
        r8 = "DownloadFailure";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r10.<init>();	 Catch:{ all -> 0x0840 }
        r11 = "Media not fetched - IO Failure - ";
        r10.append(r11);	 Catch:{ all -> 0x0840 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0840 }
        r10.append(r2);	 Catch:{ all -> 0x0840 }
        r2 = r10.toString();	 Catch:{ all -> 0x0840 }
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r10.<init>();	 Catch:{ all -> 0x0840 }
        r10.append(r1);	 Catch:{ all -> 0x0840 }
        r11 = " - ";
        r10.append(r11);	 Catch:{ all -> 0x0840 }
        r11 = com.utilities.Util.Q();	 Catch:{ all -> 0x0840 }
        r10.append(r11);	 Catch:{ all -> 0x0840 }
        r10 = r10.toString();	 Catch:{ all -> 0x0840 }
        r6.a(r8, r2, r10);	 Catch:{ all -> 0x0840 }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x0840 }
        r2 = com.managers.DownloadManager.DownloadHTTPStatus.CONNECTION_RESET;	 Catch:{ all -> 0x0840 }
        if (r3 == 0) goto L_0x0756;
    L_0x074c:
        r3.flush();	 Catch:{ Exception -> 0x0753 }
        r3.close();	 Catch:{ Exception -> 0x0753 }
        goto L_0x0756;
    L_0x0753:
        r0 = move-exception;
        r3 = r0;
        goto L_0x0771;
    L_0x0756:
        if (r9 == 0) goto L_0x075b;
    L_0x0758:
        r9.close();	 Catch:{ Exception -> 0x0753 }
    L_0x075b:
        if (r4 == 0) goto L_0x0760;
    L_0x075d:
        r4.close();	 Catch:{ Exception -> 0x0753 }
    L_0x0760:
        if (r7 == 0) goto L_0x0768;
    L_0x0762:
        r7.flush();	 Catch:{ Exception -> 0x0753 }
        r7.close();	 Catch:{ Exception -> 0x0753 }
    L_0x0768:
        if (r5 == 0) goto L_0x07aa;
    L_0x076a:
        r5.flush();	 Catch:{ Exception -> 0x0753 }
        r5.close();	 Catch:{ Exception -> 0x0753 }
        goto L_0x07aa;
    L_0x0771:
        r4 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r5 = "DownloadFailure";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r7 = "Media not fetched - ";
        r6.append(r7);	 Catch:{ all -> 0x084b }
        r3 = r3.getMessage();	 Catch:{ all -> 0x084b }
        r6.append(r3);	 Catch:{ all -> 0x084b }
        r3 = r6.toString();	 Catch:{ all -> 0x084b }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r6.toString();	 Catch:{ all -> 0x084b }
        r4.a(r5, r3, r1);	 Catch:{ all -> 0x084b }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
    L_0x07aa:
        monitor-exit(r22);
        return r2;
    L_0x07ac:
        r0 = move-exception;
        r2 = r0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r7 = 0;
        r9 = 0;
    L_0x07b3:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x0840 }
        r2 = com.managers.u.a();	 Catch:{ all -> 0x0840 }
        r6 = "DownloadFailure";
        r8 = "Media not fetched - Network Failure";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0840 }
        r10.<init>();	 Catch:{ all -> 0x0840 }
        r10.append(r1);	 Catch:{ all -> 0x0840 }
        r11 = " - ";
        r10.append(r11);	 Catch:{ all -> 0x0840 }
        r11 = com.utilities.Util.Q();	 Catch:{ all -> 0x0840 }
        r10.append(r11);	 Catch:{ all -> 0x0840 }
        r10 = r10.toString();	 Catch:{ all -> 0x0840 }
        r2.a(r6, r8, r10);	 Catch:{ all -> 0x0840 }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x0840 }
        r2 = com.managers.DownloadManager.DownloadHTTPStatus.CONNECTION_RESET;	 Catch:{ all -> 0x0840 }
        if (r3 == 0) goto L_0x07ea;
    L_0x07e0:
        r3.flush();	 Catch:{ Exception -> 0x07e7 }
        r3.close();	 Catch:{ Exception -> 0x07e7 }
        goto L_0x07ea;
    L_0x07e7:
        r0 = move-exception;
        r3 = r0;
        goto L_0x0805;
    L_0x07ea:
        if (r9 == 0) goto L_0x07ef;
    L_0x07ec:
        r9.close();	 Catch:{ Exception -> 0x07e7 }
    L_0x07ef:
        if (r4 == 0) goto L_0x07f4;
    L_0x07f1:
        r4.close();	 Catch:{ Exception -> 0x07e7 }
    L_0x07f4:
        if (r7 == 0) goto L_0x07fc;
    L_0x07f6:
        r7.flush();	 Catch:{ Exception -> 0x07e7 }
        r7.close();	 Catch:{ Exception -> 0x07e7 }
    L_0x07fc:
        if (r5 == 0) goto L_0x083e;
    L_0x07fe:
        r5.flush();	 Catch:{ Exception -> 0x07e7 }
        r5.close();	 Catch:{ Exception -> 0x07e7 }
        goto L_0x083e;
    L_0x0805:
        r4 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r5 = "DownloadFailure";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r7 = "Media not fetched - ";
        r6.append(r7);	 Catch:{ all -> 0x084b }
        r3 = r3.getMessage();	 Catch:{ all -> 0x084b }
        r6.append(r3);	 Catch:{ all -> 0x084b }
        r3 = r6.toString();	 Catch:{ all -> 0x084b }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r6.toString();	 Catch:{ all -> 0x084b }
        r4.a(r5, r3, r1);	 Catch:{ all -> 0x084b }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
    L_0x083e:
        monitor-exit(r22);
        return r2;
    L_0x0840:
        r0 = move-exception;
        r2 = r0;
    L_0x0842:
        if (r3 == 0) goto L_0x0851;
    L_0x0844:
        r3.flush();	 Catch:{ Exception -> 0x084e }
        r3.close();	 Catch:{ Exception -> 0x084e }
        goto L_0x0851;
    L_0x084b:
        r0 = move-exception;
        r1 = r0;
        goto L_0x08a6;
    L_0x084e:
        r0 = move-exception;
        r3 = r0;
        goto L_0x086c;
    L_0x0851:
        if (r9 == 0) goto L_0x0856;
    L_0x0853:
        r9.close();	 Catch:{ Exception -> 0x084e }
    L_0x0856:
        if (r4 == 0) goto L_0x085b;
    L_0x0858:
        r4.close();	 Catch:{ Exception -> 0x084e }
    L_0x085b:
        if (r7 == 0) goto L_0x0863;
    L_0x085d:
        r7.flush();	 Catch:{ Exception -> 0x084e }
        r7.close();	 Catch:{ Exception -> 0x084e }
    L_0x0863:
        if (r5 == 0) goto L_0x08a5;
    L_0x0865:
        r5.flush();	 Catch:{ Exception -> 0x084e }
        r5.close();	 Catch:{ Exception -> 0x084e }
        goto L_0x08a5;
    L_0x086c:
        r4 = com.managers.u.a();	 Catch:{ all -> 0x084b }
        r5 = "DownloadFailure";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r7 = "Media not fetched - ";
        r6.append(r7);	 Catch:{ all -> 0x084b }
        r3 = r3.getMessage();	 Catch:{ all -> 0x084b }
        r6.append(r3);	 Catch:{ all -> 0x084b }
        r3 = r6.toString();	 Catch:{ all -> 0x084b }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x084b }
        r6.<init>();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = " - ";
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = com.utilities.Util.Q();	 Catch:{ all -> 0x084b }
        r6.append(r1);	 Catch:{ all -> 0x084b }
        r1 = r6.toString();	 Catch:{ all -> 0x084b }
        r4.a(r5, r3, r1);	 Catch:{ all -> 0x084b }
        com.gaana.analytics.UninstallIO.sendDownloadFailureEvent(r23);	 Catch:{ all -> 0x084b }
    L_0x08a5:
        throw r2;	 Catch:{ all -> 0x084b }
    L_0x08a6:
        monitor-exit(r22);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.o.a(java.lang.String, java.lang.String):com.managers.DownloadManager$DownloadHTTPStatus");
    }

    private Boolean a(String str, int i) {
        if (!d.containsKey(str)) {
            return Boolean.valueOf(false);
        }
        int time = (int) ((new Date().getTime() - Long.parseLong((String) d.get(str))) / 1000);
        if (i != -1 && time > i * 60) {
            return Boolean.valueOf(true);
        }
        if (time > 36000) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    private Boolean a(URLManager uRLManager, String str) {
        Boolean valueOf = Boolean.valueOf(false);
        if (((GaanaApplication) GaanaApplication.getContext()).isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
            return valueOf;
        }
        if (uRLManager.c() && j()) {
            valueOf = Boolean.valueOf(true);
        }
        return (uRLManager.m().booleanValue() || a(str, uRLManager.d()).booleanValue() || !uRLManager.f().booleanValue()) ? Boolean.valueOf(true) : valueOf;
    }

    private boolean j() {
        com.services.d a = com.services.d.a();
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(new Date(a.b(0, "PREFERENCE_HOURLY_PLAYLIST_LAST_TIMESTAMP", false)));
        int i = instance.get(7);
        int i2 = instance.get(11);
        Calendar instance2 = GregorianCalendar.getInstance();
        instance2.setTime(new Date());
        int i3 = instance2.get(7);
        int i4 = instance2.get(11);
        if (i3 == i && i4 == i2) {
            return false;
        }
        a.a(instance2.getTimeInMillis(), "PREFERENCE_HOURLY_PLAYLIST_LAST_TIMESTAMP", false);
        return true;
    }

    public void a(String... strArr) {
        String b = b(strArr);
        if (b.remove(b) != null) {
            this.f.a(b);
        }
    }

    public void c(String str) {
        if (b.remove(str) != null || c.remove(str) != null) {
            this.f.a(str);
        }
    }

    private String b(String... strArr) {
        int i;
        Boolean valueOf = Boolean.valueOf(false);
        for (String str : c.keySet()) {
            int length = strArr.length;
            Boolean bool = valueOf;
            i = 0;
            while (i < length && str.contains(strArr[i])) {
                bool = Boolean.valueOf(true);
                i++;
            }
            if (bool.booleanValue()) {
                valueOf = bool;
                break;
            }
            valueOf = bool;
        }
        String str2 = null;
        if (valueOf.booleanValue()) {
            return str2;
        }
        for (String str3 : b.keySet()) {
            int length2 = strArr.length;
            Boolean bool2 = valueOf;
            i = 0;
            while (i < length2 && str3.contains(strArr[i])) {
                bool2 = Boolean.valueOf(true);
                i++;
            }
            if (bool2.booleanValue()) {
                return str3;
            }
            valueOf = bool2;
        }
        return str2;
    }

    private String f(String str) {
        String str2 = null;
        for (String str3 : str.split("&")) {
            String str4 = str3.split("=")[0];
            if (str4.compareToIgnoreCase(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE) == 0) {
                str2 = str3.split("=")[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&");
                stringBuilder.append(str4);
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = str.replace(stringBuilder.toString(), " ").trim();
            }
        }
        return str2;
    }

    public void a(String str) {
        if (b != null) {
            b.remove(str);
        }
        if (d != null) {
            d.remove(str);
        }
        if (c != null) {
            c.remove(str);
        }
    }

    public ArrayList<PlayerTrack> e() {
        try {
            String b = this.f.b("http://dummy.com/playerqueue");
            if (b != null) {
                BusinessObject businessObject = (BusinessObject) n.a(b);
                if (businessObject != null) {
                    return businessObject.getArrListBusinessObj();
                }
            }
        } catch (Exception unused) {
            a(new ArrayList());
            this.f.a("http://dummy.com/playerqueue", n.a(new BusinessObject()), false, 2);
        }
        return null;
    }

    public void a(final af afVar) {
        com.i.d.a(new Runnable() {
            public void run() {
                try {
                    String b = o.this.f.b("http://dummy.com/playerqueue");
                    if (b != null) {
                        BusinessObject businessObject = (BusinessObject) n.a(b);
                        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                            afVar.onErrorResponse(null);
                            return;
                        }
                        o.this.k();
                        afVar.onRetreivalComplete(businessObject.getArrListBusinessObj());
                    }
                } catch (Exception unused) {
                    o.this.a(new ArrayList());
                    Serializable businessObject2 = new BusinessObject();
                    o.this.f.a("http://dummy.com/playerqueue", n.a(businessObject2), false, 2);
                    afVar.onErrorResponse(businessObject2);
                }
            }
        });
    }

    private void k() {
        Context context = GaanaApplication.getContext();
        if (PlayerManager.a(context).n() == null) {
            ArrayList e = a().e();
            int b = com.services.d.a().b("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", 0, true);
            if (e == null || e.size() <= 0) {
                com.player_framework.o.h(context);
                return;
            }
            if (b < 0 || b > e.size() - 1 || b > Constants.dg - 1) {
                b = 0;
            }
            PlayerManager.a(GaanaApplication.getContext()).a(e, (PlayerTrack) e.get(b));
            a(context);
            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA, context);
            PlayerStatus.a(context, PlayerStates.STOPPED);
            com.player_framework.o.d(context);
            PlayerManager.a = false;
            return;
        }
        PlayerManager.a(context).a(PlayerManager.a(context).a(-1));
        GaanaApplication.getInstance().setPlayerStatus(true);
    }

    private void a(Context context) {
        com.services.d a = com.services.d.a();
        if (a.b("PREFERENCE_KEY_SHUFFLE_STATUS", false, true)) {
            ArrayList f = a().f();
            if (f == null || f.size() <= 0) {
                a.a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            } else {
                PlayerManager.a(context).a(f);
            }
        }
        int b = a.b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
        if (b == 1) {
            PlayerManager.a(context).b(true);
        } else if (b == 2) {
            PlayerManager.a(context).c(true);
        }
    }

    public void a(final ArrayList<PlayerTrack> arrayList, final int i, final aw awVar) {
        if (!Constants.cY) {
            try {
                com.i.d.a(new Runnable() {
                    public void run() {
                        Serializable businessObject = new BusinessObject();
                        ArrayList arrayList = new ArrayList();
                        if (arrayList == null || arrayList.size() <= 0) {
                            businessObject.setArrList(arrayList);
                        } else {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                PlayerTrack playerTrack = (PlayerTrack) it.next();
                                playerTrack.c(false);
                                arrayList.add(playerTrack);
                            }
                            businessObject.setArrList(arrayList);
                        }
                        o.this.f.a("http://dummy.com/playerqueue", n.a(businessObject), false, 2);
                        com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", i, true);
                        if (awVar != null) {
                            awVar.onPlayerQueueSavingCompleted();
                        }
                    }
                });
            } catch (Exception unused) {
                this.f.a("http://dummy.com/playerqueue", n.a(new BusinessObject()), false, 2);
                com.services.d.a().a("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", i, true);
                if (awVar != null) {
                    awVar.onPlayerQueueSavingCompleted();
                }
            }
        }
    }

    public ArrayList<String> f() {
        String b = this.f.b("http://dummy.com/playerqueuebeforeshuffle");
        if (b != null) {
            BusinessObject businessObject = (BusinessObject) n.a(b);
            if (businessObject != null) {
                return businessObject.getArrListBusinessObj();
            }
        }
        return null;
    }

    public void a(final ArrayList<String> arrayList) {
        com.i.d.a(new Runnable() {
            public void run() {
                Serializable businessObject = new BusinessObject();
                businessObject.setArrList(arrayList);
                o.this.f.a("http://dummy.com/playerqueuebeforeshuffle", n.a(businessObject), false, 2);
            }
        });
    }

    public void d(final String str) {
        com.i.d.a(new Runnable() {
            public void run() {
                o.this.f.a("scheduleDownloadTrackIds", str, false, 2);
            }
        });
    }

    public String g() {
        String b = this.f.b("scheduleDownloadTrackIds");
        return (b == null || b.length() <= 0) ? null : b;
    }

    public void e(String str) {
        this.f.a(str);
    }

    public void h() {
        this.f.b();
    }
}

package com.logging;

import android.content.Context;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.services.h;
import com.utilities.Util;

public class c implements a {
    private static c a;

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public void a(final Context context) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                GaanaLogger.a().a(null, context);
                Util.z();
            }

            public void doBackGroundTask() {
                c.this.b(context);
            }
        }, -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x006c  */
    private void b(android.content.Context r15) {
        /*
        r14 = this;
        r0 = com.services.d.a();
        r1 = "PREFERENCE_KEY_LAST_INSERT_ID";
        r2 = 0;
        r3 = 0;
        r0 = r0.b(r1, r2, r3);
        r1 = com.services.d.a();
        r4 = "PREFERENCE_KEY_LAST_TRACK_ID";
        r1.b(r4, r2, r3);
        r1 = com.services.d.a();
        r2 = "incognito_config";
        r1 = r1.b(r2, r3, r3);
        com.constants.Constants.y = r1;
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r2 = com.logging.GaanaLogger.a();
        r2 = r2.b();
        if (r2 != 0) goto L_0x0030;
    L_0x002f:
        return;
    L_0x0030:
        r4 = r2.g();
        r5 = android.text.TextUtils.isEmpty(r0);
        r6 = 0;
        if (r5 != 0) goto L_0x005f;
    L_0x003c:
        r5 = r2.h();
        r8 = java.lang.Long.parseLong(r5);
        r10 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x004f;
    L_0x004a:
        if (r0 == 0) goto L_0x005f;
    L_0x004c:
        r8 = 30;
        goto L_0x0060;
    L_0x004f:
        r5 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x0060;
    L_0x0053:
        r5 = com.services.d.a();
        r8 = "PREFERENCE_KEY_LAST_PLAYED_DURATION";
        r5 = r5.b(r8, r3, r3);
        r8 = (long) r5;
        goto L_0x0060;
    L_0x005f:
        r8 = r6;
    L_0x0060:
        r5 = "0";
        r10 = r2.b();
        r5 = r5.equalsIgnoreCase(r10);
        if (r5 == 0) goto L_0x007b;
    L_0x006c:
        r15 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r15 <= 0) goto L_0x007a;
    L_0x0070:
        r15 = com.services.d.a();
        r0 = "PREFERENCE_KEY_LAST_PLAYED_DURATION";
        r1 = (int) r8;
        r15.a(r0, r1, r3);
    L_0x007a:
        return;
    L_0x007b:
        r5 = r2.i();
        r10 = java.lang.Long.parseLong(r5);
        r5 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x0088;
    L_0x0087:
        r10 = r8;
    L_0x0088:
        r5 = new com.services.j;
        r5.<init>();
        r6 = "";
        r7 = new com.utilities.k;
        r12 = com.constants.Constants.br;
        r13 = com.constants.Constants.bs;
        r7.<init>(r12, r13);
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b7 }
        r12.<init>();	 Catch:{ Exception -> 0x00b7 }
        r13 = r2.b();	 Catch:{ Exception -> 0x00b7 }
        r12.append(r13);	 Catch:{ Exception -> 0x00b7 }
        r13 = "_android";
        r12.append(r13);	 Catch:{ Exception -> 0x00b7 }
        r12 = r12.toString();	 Catch:{ Exception -> 0x00b7 }
        r7 = r7.b(r12);	 Catch:{ Exception -> 0x00b7 }
        r7 = com.utilities.k.a(r7);	 Catch:{ Exception -> 0x00b7 }
        r6 = r7;
        goto L_0x00bb;
    L_0x00b7:
        r7 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);
    L_0x00bb:
        r7 = r2.l();
        r12 = android.text.TextUtils.isEmpty(r7);
        if (r12 != 0) goto L_0x0106;
    L_0x00c5:
        r12 = com.managers.GaanaSearchManager.a();
        r12 = r12.n();
        if (r12 == 0) goto L_0x00ed;
    L_0x00cf:
        r12 = r14.a(r7);
        if (r12 == 0) goto L_0x00ed;
    L_0x00d5:
        r12 = com.managers.GaanaSearchManager.a();
        r12.b(r3);
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r12.append(r7);
        r7 = "_FIRST";
        r12.append(r7);
        r7 = r12.toString();
    L_0x00ed:
        r12 = "_";
        r13 = "";
        r7 = r7.replaceAll(r12, r13);
        r12 = "-";
        r13 = "";
        r7 = r7.replaceAll(r12, r13);
        r12 = " ";
        r13 = "";
        r7 = r7.replaceAll(r12, r13);
        goto L_0x010c;
    L_0x0106:
        r7 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.OTHERS;
        r7 = r7.name();
    L_0x010c:
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "https://listened.gaana.com/log.php?last_track_insert_id=";
        r12.append(r13);
        r12.append(r0);
        r0 = "&track_id=";
        r12.append(r0);
        r0 = r2.b();
        r12.append(r0);
        r0 = "&last_track_played=";
        r12.append(r0);
        r12.append(r8);
        r0 = "&source=";
        r12.append(r0);
        r0 = r2.d();
        r12.append(r0);
        r0 = "&page_id=";
        r12.append(r0);
        r0 = r2.k();
        r12.append(r0);
        r0 = "&section_id=";
        r12.append(r0);
        r12.append(r7);
        r0 = "&playout_method=";
        r12.append(r0);
        r0 = r2.m();
        r12.append(r0);
        r0 = "&source_id=";
        r12.append(r0);
        r0 = r2.e();
        r12.append(r0);
        r0 = "&songtime=";
        r12.append(r0);
        r0 = r2.a();
        r12.append(r0);
        r0 = "&platform=android";
        r12.append(r0);
        r0 = "&data=";
        r12.append(r0);
        r12.append(r6);
        r0 = "&seek_position=";
        r12.append(r0);
        r12.append(r10);
        r0 = "&incognito=";
        r12.append(r0);
        r0 = com.constants.Constants.y;
        r12.append(r0);
        r0 = "&content_type=";
        r12.append(r0);
        r0 = r2.f();
        r12.append(r0);
        r0 = "&play_source=";
        r12.append(r0);
        r12.append(r4);
        r0 = r12.toString();
        r4 = r2.d();
        r6 = com.logging.GaanaLogger.SOURCE_TYPE.CF_TRACK;
        r6 = r6.ordinal();
        r6 = java.lang.String.valueOf(r6);
        if (r4 != r6) goto L_0x01d0;
    L_0x01b8:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r0);
        r0 = "&seed_track_id=";
        r4.append(r0);
        r0 = r2.c();
        r4.append(r0);
        r0 = r4.toString();
    L_0x01d0:
        r2 = r1.getCurrentUser();
        r2 = r2.getLoginStatus();
        if (r2 == 0) goto L_0x01f6;
    L_0x01da:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r0);
        r0 = "&token=";
        r2.append(r0);
        r0 = r1.getCurrentUser();
        r0 = r0.getAuthToken();
        r2.append(r0);
        r0 = r2.toString();
    L_0x01f6:
        r0 = r5.a(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        if (r0 == 0) goto L_0x0242;
    L_0x01fc:
        r15 = new org.json.JSONObject;	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r15.<init>(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r0 = "insert_id";
        r0 = r15.has(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        if (r0 == 0) goto L_0x022a;
    L_0x0209:
        r0 = "insert_id";
        r0 = r15.getString(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r1 = com.services.d.a();	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r2 = "PREFERENCE_KEY_LAST_INSERT_ID";
        r1.a(r2, r0, r3);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r0 = com.services.d.a();	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r1 = "PREFERENCE_KEY_LAST_PLAYED_DURATION";
        r0.b(r1, r3);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r0 = com.services.d.a();	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r1 = "PREFERENCE_KEY_LAST_TRACK_PLAYOUT_SOURCE";
        r0.b(r1, r3);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
    L_0x022a:
        r0 = "track_id";
        r0 = r15.has(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        if (r0 == 0) goto L_0x024f;
    L_0x0232:
        r0 = "track_id";
        r15 = r15.getString(r0);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r0 = com.services.d.a();	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        r1 = "PREFERENCE_KEY_LAST_TRACK_ID";
        r0.a(r1, r15, r3);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        goto L_0x024f;
    L_0x0242:
        r14.c(r15);	 Catch:{ Exception -> 0x024b, AppException -> 0x0246 }
        goto L_0x024f;
    L_0x0246:
        r15 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r15);
        goto L_0x024f;
    L_0x024b:
        r15 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r15);
    L_0x024f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.logging.c.b(android.content.Context):void");
    }

    private boolean a(String str) {
        return str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.VOICE_AUTO_SUGGEST.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.VOICEINT_AUTOPLAY.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.VOICEINT_PLAY.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.QUICK_SEARCH.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.RECENT_SEARCH.name()) || str.equalsIgnoreCase(PLAYOUT_SECTION_TYPE.TRENDING_SEARCH.name());
    }

    private void c(Context context) {
        b.a().a(context);
    }
}

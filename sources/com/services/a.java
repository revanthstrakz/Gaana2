package com.services;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.facebook.messenger.MessengerUtils;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog.Mode;
import com.gaana.R;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.google.android.gms.plus.PlusShare;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import java.util.ArrayList;
import java.util.List;

public class a {
    private Context a;
    private b b;

    public a(Context context) {
        this.a = context;
        a();
    }

    private void a() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        this.b = new b((Activity) this.a, a(this.a.getPackageManager().queryIntentActivities(intent, 0)).toArray());
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        Builder builder = new Builder(this.a);
        builder.setTitle(R.string.share_title);
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        builder.setAdapter(this.b, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a.this.a((ResolveInfo) a.this.b.getItem(i), str7, str8, str9, str10, str11, str12);
            }
        });
        builder.create().show();
    }

    public void a(ResolveInfo resolveInfo, String str, String str2, String str3, String str4, String str5, String str6) {
        String str7;
        this.a.getString(R.string.mode_other);
        String str8 = "Other";
        if (resolveInfo.activityInfo.packageName.equals("com.facebook.katana")) {
            if (!TextUtils.isEmpty(str6)) {
                str2 = str6;
            }
            a(resolveInfo, str, str2, str4);
            this.a.getString(R.string.mode_facebook);
            str7 = "Facebook";
        } else if (resolveInfo.activityInfo.packageName.equals("com.google.android.apps.plus")) {
            a(str, str2, str4);
            this.a.getString(R.string.mode_googleplus);
            str7 = "GooglePlus";
        } else if (resolveInfo.activityInfo.packageName.endsWith("com.twitter.android")) {
            this.a.getString(R.string.mode_twitter);
            str4 = "Twitter";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(" @gaana");
            str2 = stringBuilder.toString();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", str);
            if (TextUtils.isEmpty(str3)) {
                intent.putExtra("android.intent.extra.TEXT", str2);
            } else {
                intent.putExtra("android.intent.extra.TEXT", str3);
            }
            ((Activity) this.a).startActivity(intent);
            str7 = str4;
        } else {
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            intent2.setType("text/plain");
            if (!TextUtils.isEmpty(str)) {
                intent2.putExtra("android.intent.extra.SUBJECT", str);
            }
            if (resolveInfo.activityInfo.packageName.equals(MessengerUtils.PACKAGE_NAME)) {
                this.a.getString(R.string.mode_fb_messenger);
                str8 = "Fb Messenger";
            } else if (resolveInfo.activityInfo.packageName.equals("com.android.mms")) {
                this.a.getString(R.string.mode_sms);
                str8 = "SMS";
            } else if (resolveInfo.activityInfo.packageName.equals("com.whatsapp")) {
                this.a.getString(R.string.mode_whatsapp);
                str8 = "Whatsapp";
            } else if (resolveInfo.activityInfo.packageName.equals(MessengerUtils.PACKAGE_NAME)) {
                this.a.getString(R.string.mode_messenger);
                str8 = "Messanger";
            } else if (resolveInfo.activityInfo.packageName.equals("com.google.android.gm")) {
                this.a.getString(R.string.mode_gmail);
                str8 = "Gmail";
            }
            str7 = str8;
            intent2.putExtra("android.intent.extra.TEXT", str2);
            ((Activity) this.a).startActivity(intent2);
        }
        if (str5 != null && !TextUtils.isEmpty(str5)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str5);
            stringBuilder2.append(" shared");
            u.a().a("Share widget", stringBuilder2.toString(), str7);
        }
    }

    private void a(ResolveInfo resolveInfo, String str, String str2, String str3) {
        if (ap.a().c()) {
            b(resolveInfo, str, str2, str3);
            return;
        }
        final ResolveInfo resolveInfo2 = resolveInfo;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        new g().a((Activity) this.a, new com.services.g.a() {
            public String OnAuthrizationSuccess() {
                a.this.b(resolveInfo2, str4, str5, str6);
                return str5;
            }

            public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                aj.a().a(a.this.a, a.this.a.getString(R.string.facebook_login_error));
            }
        }, false);
    }

    private void b(ResolveInfo resolveInfo, String str, String str2, String str3) {
        g.a(this.a, Mode.FEED, ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str2))).build());
    }

    private void a(String str, String str2, String str3) {
        ((Activity) this.a).startActivityForResult(new PlusShare.Builder((Activity) this.a).setText(str).setType("text/plain").setContentUrl(Uri.parse(str2)).setContentDeepLinkId(str2).getIntent(), 0);
    }

    private List<ResolveInfo> a(List<ResolveInfo> list) {
        ArrayList arrayList = new ArrayList();
        ResolveInfo[] resolveInfoArr = new ResolveInfo[6];
        for (ResolveInfo resolveInfo : list) {
            int a = a(resolveInfo.activityInfo.packageName);
            if (a > -1) {
                resolveInfoArr[a] = resolveInfo;
            }
        }
        for (Object obj : resolveInfoArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        for (ResolveInfo resolveInfo2 : list) {
            if (!arrayList.contains(resolveInfo2)) {
                arrayList.add(resolveInfo2);
            }
        }
        return arrayList;
    }

    private int a(java.lang.String r9) {
        /*
        r8 = this;
        r0 = r9.hashCode();
        r1 = 3;
        r2 = 2;
        r3 = 4;
        r4 = 1;
        r5 = 0;
        r6 = 5;
        r7 = -1;
        switch(r0) {
            case -1547699361: goto L_0x0041;
            case -695601689: goto L_0x0037;
            case -543674259: goto L_0x002d;
            case 10619783: goto L_0x0023;
            case 714499313: goto L_0x0019;
            case 908140028: goto L_0x000f;
            default: goto L_0x000e;
        };
    L_0x000e:
        goto L_0x004b;
    L_0x000f:
        r0 = "com.facebook.orca";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x0017:
        r9 = r6;
        goto L_0x004c;
    L_0x0019:
        r0 = "com.facebook.katana";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x0021:
        r9 = r5;
        goto L_0x004c;
    L_0x0023:
        r0 = "com.twitter.android";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x002b:
        r9 = r4;
        goto L_0x004c;
    L_0x002d:
        r0 = "com.google.android.gm";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x0035:
        r9 = r3;
        goto L_0x004c;
    L_0x0037:
        r0 = "com.android.mms";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x003f:
        r9 = r2;
        goto L_0x004c;
    L_0x0041:
        r0 = "com.whatsapp";
        r9 = r9.equals(r0);
        if (r9 == 0) goto L_0x004b;
    L_0x0049:
        r9 = r1;
        goto L_0x004c;
    L_0x004b:
        r9 = r7;
    L_0x004c:
        switch(r9) {
            case 0: goto L_0x0059;
            case 1: goto L_0x0057;
            case 2: goto L_0x005a;
            case 3: goto L_0x0055;
            case 4: goto L_0x0053;
            case 5: goto L_0x0051;
            default: goto L_0x004f;
        };
    L_0x004f:
        r1 = r7;
        goto L_0x005a;
    L_0x0051:
        r1 = r6;
        goto L_0x005a;
    L_0x0053:
        r1 = r3;
        goto L_0x005a;
    L_0x0055:
        r1 = r2;
        goto L_0x005a;
    L_0x0057:
        r1 = r4;
        goto L_0x005a;
    L_0x0059:
        r1 = r5;
    L_0x005a:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.a.a(java.lang.String):int");
    }
}

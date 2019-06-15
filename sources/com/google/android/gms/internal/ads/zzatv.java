package com.google.android.gms.internal.ads;

import android.location.Location;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatv {
    /* JADX WARNING: Removed duplicated region for block: B:311:0x0847 A:{Catch:{ JSONException -> 0x0981 }} */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0844 A:{Catch:{ JSONException -> 0x0981 }} */
    @android.support.annotation.Nullable
    public static org.json.JSONObject zza(android.content.Context r23, com.google.android.gms.internal.ads.zzato r24) {
        /*
        r1 = r24;
        r2 = r1.zzeag;
        r3 = r1.zzcjj;
        r4 = r1.zzeah;
        r5 = r1.zzdwm;
        r6 = r1.zzeai;
        r8 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0981 }
        r8.<init>();	 Catch:{ JSONException -> 0x0981 }
        r9 = "extra_caps";
        r10 = com.google.android.gms.internal.ads.zzaan.zzcty;	 Catch:{ JSONException -> 0x0981 }
        r11 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r10 = r11.zzd(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r9, r10);	 Catch:{ JSONException -> 0x0981 }
        r9 = r1.zzdwt;	 Catch:{ JSONException -> 0x0981 }
        r9 = r9.size();	 Catch:{ JSONException -> 0x0981 }
        if (r9 <= 0) goto L_0x0035;
    L_0x0028:
        r9 = "eid";
        r10 = ",";
        r11 = r1.zzdwt;	 Catch:{ JSONException -> 0x0981 }
        r10 = android.text.TextUtils.join(r10, r11);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r9, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0035:
        r9 = r2.zzdwf;	 Catch:{ JSONException -> 0x0981 }
        if (r9 == 0) goto L_0x0040;
    L_0x0039:
        r9 = "ad_pos";
        r10 = r2.zzdwf;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r9, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0040:
        r9 = r2.zzdwg;	 Catch:{ JSONException -> 0x0981 }
        com.google.android.gms.internal.ads.zzaxu.zzyy();	 Catch:{ JSONException -> 0x0981 }
        r10 = new java.text.SimpleDateFormat;	 Catch:{ JSONException -> 0x0981 }
        r11 = "yyyyMMdd";
        r12 = java.util.Locale.US;	 Catch:{ JSONException -> 0x0981 }
        r10.<init>(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = r9.zzcjb;	 Catch:{ JSONException -> 0x0981 }
        r13 = -1;
        r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r15 == 0) goto L_0x0066;
    L_0x0056:
        r11 = "cust_age";
        r12 = new java.util.Date;	 Catch:{ JSONException -> 0x0981 }
        r13 = r9.zzcjb;	 Catch:{ JSONException -> 0x0981 }
        r12.<init>(r13);	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.format(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0066:
        r10 = r9.extras;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x0071;
    L_0x006a:
        r10 = "extras";
        r11 = r9.extras;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r11);	 Catch:{ JSONException -> 0x0981 }
    L_0x0071:
        r10 = r9.zzcjc;	 Catch:{ JSONException -> 0x0981 }
        r11 = -1;
        if (r10 == r11) goto L_0x0081;
    L_0x0076:
        r10 = "cust_gender";
        r12 = r9.zzcjc;	 Catch:{ JSONException -> 0x0981 }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r12);	 Catch:{ JSONException -> 0x0981 }
    L_0x0081:
        r10 = r9.zzcjd;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x008c;
    L_0x0085:
        r10 = "kw";
        r12 = r9.zzcjd;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r12);	 Catch:{ JSONException -> 0x0981 }
    L_0x008c:
        r10 = r9.zzcjf;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == r11) goto L_0x009b;
    L_0x0090:
        r10 = "tag_for_child_directed_treatment";
        r12 = r9.zzcjf;	 Catch:{ JSONException -> 0x0981 }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r12);	 Catch:{ JSONException -> 0x0981 }
    L_0x009b:
        r10 = r9.zzcje;	 Catch:{ JSONException -> 0x0981 }
        r12 = 1;
        if (r10 == 0) goto L_0x00a9;
    L_0x00a0:
        r10 = "test_request";
        r13 = java.lang.Boolean.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r13);	 Catch:{ JSONException -> 0x0981 }
    L_0x00a9:
        r10 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r13 = 2;
        if (r10 < r13) goto L_0x00ca;
    L_0x00ae:
        r10 = r9.zzcjg;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x00bb;
    L_0x00b2:
        r10 = "d_imp_hdr";
        r14 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r14);	 Catch:{ JSONException -> 0x0981 }
    L_0x00bb:
        r10 = r9.zzcjh;	 Catch:{ JSONException -> 0x0981 }
        r10 = android.text.TextUtils.isEmpty(r10);	 Catch:{ JSONException -> 0x0981 }
        if (r10 != 0) goto L_0x00ca;
    L_0x00c3:
        r10 = "ppid";
        r14 = r9.zzcjh;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r14);	 Catch:{ JSONException -> 0x0981 }
    L_0x00ca:
        r10 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r14 = 3;
        if (r10 < r14) goto L_0x00da;
    L_0x00cf:
        r10 = r9.zzcjk;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x00da;
    L_0x00d3:
        r10 = "url";
        r14 = r9.zzcjk;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r14);	 Catch:{ JSONException -> 0x0981 }
    L_0x00da:
        r10 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r14 = 5;
        if (r10 < r14) goto L_0x0100;
    L_0x00df:
        r10 = r9.zzcjm;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x00ea;
    L_0x00e3:
        r10 = "custom_targeting";
        r15 = r9.zzcjm;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r15);	 Catch:{ JSONException -> 0x0981 }
    L_0x00ea:
        r10 = r9.zzcjn;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x00f5;
    L_0x00ee:
        r10 = "category_exclusions";
        r15 = r9.zzcjn;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r15);	 Catch:{ JSONException -> 0x0981 }
    L_0x00f5:
        r10 = r9.zzcjo;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x0100;
    L_0x00f9:
        r10 = "request_agent";
        r15 = r9.zzcjo;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r15);	 Catch:{ JSONException -> 0x0981 }
    L_0x0100:
        r10 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r15 = 6;
        if (r10 < r15) goto L_0x0110;
    L_0x0105:
        r10 = r9.zzcjp;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x0110;
    L_0x0109:
        r10 = "request_pkg";
        r7 = r9.zzcjp;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r7);	 Catch:{ JSONException -> 0x0981 }
    L_0x0110:
        r7 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r10 = 7;
        if (r7 < r10) goto L_0x0120;
    L_0x0115:
        r7 = "is_designed_for_families";
        r10 = r9.zzcjq;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0120:
        r7 = r9.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r10 = 8;
        if (r7 < r10) goto L_0x0140;
    L_0x0126:
        r7 = r9.zzcjs;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == r11) goto L_0x0135;
    L_0x012a:
        r7 = "tag_for_under_age_of_consent";
        r10 = r9.zzcjs;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0135:
        r7 = r9.zzcjt;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x0140;
    L_0x0139:
        r7 = "max_ad_content_rating";
        r9 = r9.zzcjt;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r9);	 Catch:{ JSONException -> 0x0981 }
    L_0x0140:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.zzckm;	 Catch:{ JSONException -> 0x0981 }
        if (r7 != 0) goto L_0x015d;
    L_0x0146:
        r7 = "format";
        r10 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.zzckk;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.zzcko;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x0193;
    L_0x0155:
        r7 = "fluid";
        r10 = "height";
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0193;
    L_0x015d:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.zzckm;	 Catch:{ JSONException -> 0x0981 }
        r10 = r7.length;	 Catch:{ JSONException -> 0x0981 }
        r15 = 0;
        r16 = 0;
        r17 = 0;
    L_0x0167:
        if (r15 >= r10) goto L_0x0193;
    L_0x0169:
        r14 = r7[r15];	 Catch:{ JSONException -> 0x0981 }
        r12 = r14.zzcko;	 Catch:{ JSONException -> 0x0981 }
        if (r12 != 0) goto L_0x017a;
    L_0x016f:
        if (r16 != 0) goto L_0x017a;
    L_0x0171:
        r12 = "format";
        r13 = r14.zzckk;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r12, r13);	 Catch:{ JSONException -> 0x0981 }
        r16 = 1;
    L_0x017a:
        r12 = r14.zzcko;	 Catch:{ JSONException -> 0x0981 }
        if (r12 == 0) goto L_0x0189;
    L_0x017e:
        if (r17 != 0) goto L_0x0189;
    L_0x0180:
        r12 = "fluid";
        r13 = "height";
        r8.put(r12, r13);	 Catch:{ JSONException -> 0x0981 }
        r17 = 1;
    L_0x0189:
        if (r16 == 0) goto L_0x018d;
    L_0x018b:
        if (r17 != 0) goto L_0x0193;
    L_0x018d:
        r15 = r15 + 1;
        r12 = 1;
        r13 = 2;
        r14 = 5;
        goto L_0x0167;
    L_0x0193:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.width;	 Catch:{ JSONException -> 0x0981 }
        if (r7 != r11) goto L_0x01a0;
    L_0x0199:
        r7 = "smart_w";
        r10 = "full";
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x01a0:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.height;	 Catch:{ JSONException -> 0x0981 }
        r10 = -2;
        if (r7 != r10) goto L_0x01ae;
    L_0x01a7:
        r7 = "smart_h";
        r12 = "auto";
        r8.put(r7, r12);	 Catch:{ JSONException -> 0x0981 }
    L_0x01ae:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.zzckm;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x021c;
    L_0x01b4:
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0981 }
        r7.<init>();	 Catch:{ JSONException -> 0x0981 }
        r12 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r12 = r12.zzckm;	 Catch:{ JSONException -> 0x0981 }
        r13 = r12.length;	 Catch:{ JSONException -> 0x0981 }
        r14 = 0;
        r15 = 0;
    L_0x01c0:
        if (r14 >= r13) goto L_0x0202;
    L_0x01c2:
        r9 = r12[r14];	 Catch:{ JSONException -> 0x0981 }
        r10 = r9.zzcko;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x01ca;
    L_0x01c8:
        r15 = 1;
        goto L_0x01fd;
    L_0x01ca:
        r10 = r7.length();	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x01d5;
    L_0x01d0:
        r10 = "|";
        r7.append(r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x01d5:
        r10 = r9.width;	 Catch:{ JSONException -> 0x0981 }
        if (r10 != r11) goto L_0x01e1;
    L_0x01d9:
        r10 = r9.widthPixels;	 Catch:{ JSONException -> 0x0981 }
        r10 = (float) r10;	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzbwv;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10 / r11;
        r10 = (int) r10;	 Catch:{ JSONException -> 0x0981 }
        goto L_0x01e3;
    L_0x01e1:
        r10 = r9.width;	 Catch:{ JSONException -> 0x0981 }
    L_0x01e3:
        r7.append(r10);	 Catch:{ JSONException -> 0x0981 }
        r10 = "x";
        r7.append(r10);	 Catch:{ JSONException -> 0x0981 }
        r10 = r9.height;	 Catch:{ JSONException -> 0x0981 }
        r11 = -2;
        if (r10 != r11) goto L_0x01f8;
    L_0x01f0:
        r9 = r9.heightPixels;	 Catch:{ JSONException -> 0x0981 }
        r9 = (float) r9;	 Catch:{ JSONException -> 0x0981 }
        r10 = r4.zzbwv;	 Catch:{ JSONException -> 0x0981 }
        r9 = r9 / r10;
        r9 = (int) r9;	 Catch:{ JSONException -> 0x0981 }
        goto L_0x01fa;
    L_0x01f8:
        r9 = r9.height;	 Catch:{ JSONException -> 0x0981 }
    L_0x01fa:
        r7.append(r9);	 Catch:{ JSONException -> 0x0981 }
    L_0x01fd:
        r14 = r14 + 1;
        r10 = -2;
        r11 = -1;
        goto L_0x01c0;
    L_0x0202:
        if (r15 == 0) goto L_0x0217;
    L_0x0204:
        r9 = r7.length();	 Catch:{ JSONException -> 0x0981 }
        if (r9 == 0) goto L_0x0211;
    L_0x020a:
        r9 = "|";
        r10 = 0;
        r7.insert(r10, r9);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0212;
    L_0x0211:
        r10 = 0;
    L_0x0212:
        r9 = "320x50";
        r7.insert(r10, r9);	 Catch:{ JSONException -> 0x0981 }
    L_0x0217:
        r9 = "sz";
        r8.put(r9, r7);	 Catch:{ JSONException -> 0x0981 }
    L_0x021c:
        r7 = r2.zzdwl;	 Catch:{ JSONException -> 0x0981 }
        r9 = 24;
        if (r7 == 0) goto L_0x029e;
    L_0x0222:
        r7 = "native_version";
        r10 = r2.zzdwl;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "native_templates";
        r10 = r2.zzbtt;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "native_image_orientation";
        r10 = r2.zzbti;	 Catch:{ JSONException -> 0x0981 }
        if (r10 != 0) goto L_0x023d;
    L_0x023a:
        r10 = "any";
        goto L_0x024d;
    L_0x023d:
        r10 = r10.zzdct;	 Catch:{ JSONException -> 0x0981 }
        switch(r10) {
            case 0: goto L_0x024b;
            case 1: goto L_0x0248;
            case 2: goto L_0x0245;
            default: goto L_0x0242;
        };	 Catch:{ JSONException -> 0x0981 }
    L_0x0242:
        r10 = "not_set";
        goto L_0x024d;
    L_0x0245:
        r10 = "landscape";
        goto L_0x024d;
    L_0x0248:
        r10 = "portrait";
        goto L_0x024d;
    L_0x024b:
        r10 = "any";
    L_0x024d:
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "native_multiple_images";
        r10 = r2.zzbti;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x025c;
    L_0x0256:
        r10 = r10.zzdcu;	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x025c;
    L_0x025a:
        r10 = 1;
        goto L_0x025d;
    L_0x025c:
        r10 = 0;
    L_0x025d:
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = r2.zzdwu;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.isEmpty();	 Catch:{ JSONException -> 0x0981 }
        if (r7 != 0) goto L_0x0273;
    L_0x026c:
        r7 = "native_custom_templates";
        r10 = r2.zzdwu;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0273:
        r7 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        if (r7 < r9) goto L_0x0282;
    L_0x0277:
        r7 = "max_num_ads";
        r10 = r2.zzdxq;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x0282:
        r7 = r2.zzdxo;	 Catch:{ JSONException -> 0x0981 }
        r7 = android.text.TextUtils.isEmpty(r7);	 Catch:{ JSONException -> 0x0981 }
        if (r7 != 0) goto L_0x029e;
    L_0x028a:
        r7 = "native_advanced_settings";
        r10 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0297 }
        r11 = r2.zzdxo;	 Catch:{ JSONException -> 0x0297 }
        r10.<init>(r11);	 Catch:{ JSONException -> 0x0297 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0297 }
        goto L_0x029e;
    L_0x0297:
        r0 = move-exception;
        r7 = r0;
        r10 = "Problem creating json from native advanced settings";
        com.google.android.gms.internal.ads.zzbbd.zzc(r10, r7);	 Catch:{ JSONException -> 0x0981 }
    L_0x029e:
        r7 = r2.zzbtn;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x02df;
    L_0x02a2:
        r7 = r2.zzbtn;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.size();	 Catch:{ JSONException -> 0x0981 }
        if (r7 <= 0) goto L_0x02df;
    L_0x02aa:
        r7 = r2.zzbtn;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.iterator();	 Catch:{ JSONException -> 0x0981 }
    L_0x02b0:
        r10 = r7.hasNext();	 Catch:{ JSONException -> 0x0981 }
        if (r10 == 0) goto L_0x02df;
    L_0x02b6:
        r10 = r7.next();	 Catch:{ JSONException -> 0x0981 }
        r10 = (java.lang.Integer) r10;	 Catch:{ JSONException -> 0x0981 }
        r11 = r10.intValue();	 Catch:{ JSONException -> 0x0981 }
        r12 = 2;
        if (r11 != r12) goto L_0x02ce;
    L_0x02c3:
        r10 = "iba";
        r11 = 1;
        r12 = java.lang.Boolean.valueOf(r11);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r12);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x02b0;
    L_0x02ce:
        r10 = r10.intValue();	 Catch:{ JSONException -> 0x0981 }
        r11 = 1;
        if (r10 != r11) goto L_0x02b0;
    L_0x02d5:
        r10 = "ina";
        r12 = java.lang.Boolean.valueOf(r11);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r10, r12);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x02b0;
    L_0x02df:
        r7 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r7 = r7.zzckp;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x02ef;
    L_0x02e5:
        r7 = "ene";
        r10 = 1;
        r11 = java.lang.Boolean.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r11);	 Catch:{ JSONException -> 0x0981 }
    L_0x02ef:
        r7 = r2.zzbtl;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x030a;
    L_0x02f3:
        r7 = "is_icon_ad";
        r10 = 1;
        r11 = java.lang.Boolean.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r11);	 Catch:{ JSONException -> 0x0981 }
        r7 = "icon_ad_expansion_behavior";
        r10 = r2.zzbtl;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.zzcly;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x030a:
        r7 = r2.zzbtk;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x034a;
    L_0x030e:
        r7 = "ia_var";
        r10 = r2.zzbtk;	 Catch:{ JSONException -> 0x0981 }
        r11 = r10.zzdgp;	 Catch:{ JSONException -> 0x0981 }
        switch(r11) {
            case 1: goto L_0x031d;
            case 2: goto L_0x031a;
            default: goto L_0x0317;
        };	 Catch:{ JSONException -> 0x0981 }
    L_0x0317:
        r10 = r10.zzdgp;	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0320;
    L_0x031a:
        r10 = "p";
        goto L_0x033d;
    L_0x031d:
        r10 = "l";
        goto L_0x033d;
    L_0x0320:
        r11 = 52;
        r12 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0981 }
        r12.<init>(r11);	 Catch:{ JSONException -> 0x0981 }
        r11 = "Instream ad video aspect ratio ";
        r12.append(r11);	 Catch:{ JSONException -> 0x0981 }
        r12.append(r10);	 Catch:{ JSONException -> 0x0981 }
        r10 = " is wrong.";
        r12.append(r10);	 Catch:{ JSONException -> 0x0981 }
        r10 = r12.toString();	 Catch:{ JSONException -> 0x0981 }
        com.google.android.gms.internal.ads.zzbbd.e(r10);	 Catch:{ JSONException -> 0x0981 }
        r10 = "l";
    L_0x033d:
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "instr";
        r10 = 1;
        r11 = java.lang.Boolean.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r11);	 Catch:{ JSONException -> 0x0981 }
    L_0x034a:
        r7 = "slotname";
        r10 = r2.zzbsn;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "pn";
        r10 = r2.applicationInfo;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.packageName;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = r2.zzdwh;	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x036b;
    L_0x035e:
        r7 = "vc";
        r10 = r2.zzdwh;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
    L_0x036b:
        r7 = "ms";
        r10 = r1.zzdwi;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "seq_num";
        r10 = r2.zzdwj;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "session_id";
        r10 = r2.zzclm;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = "js";
        r10 = r2.zzbsp;	 Catch:{ JSONException -> 0x0981 }
        r10 = r10.zzdp;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r7, r10);	 Catch:{ JSONException -> 0x0981 }
        r7 = r2.zzdxg;	 Catch:{ JSONException -> 0x0981 }
        r10 = r1.zzeac;	 Catch:{ JSONException -> 0x0981 }
        r11 = "am";
        r12 = r4.zzecp;	 Catch:{ JSONException -> 0x0981 }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = "cog";
        r12 = r4.zzecq;	 Catch:{ JSONException -> 0x0981 }
        r12 = zzag(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = "coh";
        r12 = r4.zzecr;	 Catch:{ JSONException -> 0x0981 }
        r12 = zzag(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzecs;	 Catch:{ JSONException -> 0x0981 }
        r11 = android.text.TextUtils.isEmpty(r11);	 Catch:{ JSONException -> 0x0981 }
        if (r11 != 0) goto L_0x03bd;
    L_0x03b6:
        r11 = "carrier";
        r12 = r4.zzecs;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
    L_0x03bd:
        r11 = "gl";
        r12 = r4.zzect;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzecu;	 Catch:{ JSONException -> 0x0981 }
        if (r11 == 0) goto L_0x03d2;
    L_0x03c8:
        r11 = "simulator";
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
    L_0x03d2:
        r11 = "is_latchsky";
        r12 = r4.zzecv;	 Catch:{ JSONException -> 0x0981 }
        r12 = java.lang.Boolean.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r12);	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzecw;	 Catch:{ JSONException -> 0x0981 }
        if (r11 == 0) goto L_0x03ec;
    L_0x03e1:
        r11 = "is_sidewinder";
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r12);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x03ed;
    L_0x03ec:
        r12 = 1;
    L_0x03ed:
        r11 = "ma";
        r13 = r4.zzecx;	 Catch:{ JSONException -> 0x0981 }
        r13 = zzag(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = "sp";
        r13 = r4.zzecy;	 Catch:{ JSONException -> 0x0981 }
        r13 = zzag(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = "hl";
        r13 = r4.zzecz;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzeda;	 Catch:{ JSONException -> 0x0981 }
        r11 = android.text.TextUtils.isEmpty(r11);	 Catch:{ JSONException -> 0x0981 }
        if (r11 != 0) goto L_0x0419;
    L_0x0412:
        r11 = "mv";
        r13 = r4.zzeda;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
    L_0x0419:
        r11 = "muv";
        r13 = r4.zzedc;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = r4.zzedd;	 Catch:{ JSONException -> 0x0981 }
        r13 = -2;
        if (r11 == r13) goto L_0x0434;
    L_0x0429:
        r11 = "cnt";
        r13 = r4.zzedd;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
    L_0x0434:
        r11 = "gnt";
        r13 = r4.zzede;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = "pt";
        r13 = r4.zzedf;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = "rm";
        r13 = r4.zzedg;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = "riv";
        r13 = r4.zzedh;	 Catch:{ JSONException -> 0x0981 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r11, r13);	 Catch:{ JSONException -> 0x0981 }
        r11 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r11.<init>();	 Catch:{ JSONException -> 0x0981 }
        r13 = "build_build";
        r14 = r4.zzedm;	 Catch:{ JSONException -> 0x0981 }
        r11.putString(r13, r14);	 Catch:{ JSONException -> 0x0981 }
        r13 = "build_device";
        r14 = r4.zzedn;	 Catch:{ JSONException -> 0x0981 }
        r11.putString(r13, r14);	 Catch:{ JSONException -> 0x0981 }
        r13 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r13.<init>();	 Catch:{ JSONException -> 0x0981 }
        r14 = "is_charging";
        r15 = r4.zzedj;	 Catch:{ JSONException -> 0x0981 }
        r13.putBoolean(r14, r15);	 Catch:{ JSONException -> 0x0981 }
        r14 = "battery_level";
        r18 = r10;
        r9 = r4.zzedi;	 Catch:{ JSONException -> 0x0981 }
        r13.putDouble(r14, r9);	 Catch:{ JSONException -> 0x0981 }
        r9 = "battery";
        r11.putBundle(r9, r13);	 Catch:{ JSONException -> 0x0981 }
        r9 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r9.<init>();	 Catch:{ JSONException -> 0x0981 }
        r10 = "active_network_state";
        r13 = r4.zzedl;	 Catch:{ JSONException -> 0x0981 }
        r9.putInt(r10, r13);	 Catch:{ JSONException -> 0x0981 }
        r10 = "active_network_metered";
        r13 = r4.zzedk;	 Catch:{ JSONException -> 0x0981 }
        r9.putBoolean(r10, r13);	 Catch:{ JSONException -> 0x0981 }
        r10 = "network";
        r11.putBundle(r10, r9);	 Catch:{ JSONException -> 0x0981 }
        r9 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r9.<init>();	 Catch:{ JSONException -> 0x0981 }
        r10 = "is_browser_custom_tabs_capable";
        r13 = r4.zzedo;	 Catch:{ JSONException -> 0x0981 }
        r9.putBoolean(r10, r13);	 Catch:{ JSONException -> 0x0981 }
        r10 = "browser";
        r11.putBundle(r10, r9);	 Catch:{ JSONException -> 0x0981 }
        if (r7 == 0) goto L_0x0577;
    L_0x04b8:
        r9 = "android_mem_info";
        r10 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r10.<init>();	 Catch:{ JSONException -> 0x0981 }
        r13 = "runtime_free";
        r14 = "runtime_free_memory";
        r20 = r13;
        r12 = -1;
        r14 = r7.getLong(r14, r12);	 Catch:{ JSONException -> 0x0981 }
        r14 = java.lang.Long.toString(r14);	 Catch:{ JSONException -> 0x0981 }
        r15 = r20;
        r10.putString(r15, r14);	 Catch:{ JSONException -> 0x0981 }
        r14 = "runtime_max";
        r15 = "runtime_max_memory";
        r21 = r5;
        r22 = r6;
        r5 = r7.getLong(r15, r12);	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Long.toString(r5);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r14, r5);	 Catch:{ JSONException -> 0x0981 }
        r5 = "runtime_total";
        r6 = "runtime_total_memory";
        r12 = r7.getLong(r6, r12);	 Catch:{ JSONException -> 0x0981 }
        r6 = java.lang.Long.toString(r12);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r5, r6);	 Catch:{ JSONException -> 0x0981 }
        r5 = "web_view_count";
        r6 = "web_view_count";
        r12 = 0;
        r6 = r7.getInt(r6, r12);	 Catch:{ JSONException -> 0x0981 }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r5, r6);	 Catch:{ JSONException -> 0x0981 }
        r5 = "debug_memory_info";
        r5 = r7.getParcelable(r5);	 Catch:{ JSONException -> 0x0981 }
        r5 = (android.os.Debug.MemoryInfo) r5;	 Catch:{ JSONException -> 0x0981 }
        if (r5 == 0) goto L_0x0573;
    L_0x0510:
        r6 = "debug_info_dalvik_private_dirty";
        r7 = r5.dalvikPrivateDirty;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_dalvik_pss";
        r7 = r5.dalvikPss;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_dalvik_shared_dirty";
        r7 = r5.dalvikSharedDirty;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_native_private_dirty";
        r7 = r5.nativePrivateDirty;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_native_pss";
        r7 = r5.nativePss;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_native_shared_dirty";
        r7 = r5.nativeSharedDirty;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_other_private_dirty";
        r7 = r5.otherPrivateDirty;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_other_pss";
        r7 = r5.otherPss;	 Catch:{ JSONException -> 0x0981 }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "debug_info_other_shared_dirty";
        r5 = r5.otherSharedDirty;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Integer.toString(r5);	 Catch:{ JSONException -> 0x0981 }
        r10.putString(r6, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x0573:
        r11.putBundle(r9, r10);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x057c;
    L_0x0577:
        r21 = r5;
        r22 = r6;
        r12 = 0;
    L_0x057c:
        r5 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r5.<init>();	 Catch:{ JSONException -> 0x0981 }
        r6 = "parental_controls";
        r7 = r18;
        r5.putBundle(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = r4.zzedb;	 Catch:{ JSONException -> 0x0981 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ JSONException -> 0x0981 }
        if (r6 != 0) goto L_0x0597;
    L_0x0590:
        r6 = "package_version";
        r7 = r4.zzedb;	 Catch:{ JSONException -> 0x0981 }
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
    L_0x0597:
        r6 = "play_store";
        r11.putBundle(r6, r5);	 Catch:{ JSONException -> 0x0981 }
        r5 = "device";
        r8.put(r5, r11);	 Catch:{ JSONException -> 0x0981 }
        r5 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r5.<init>();	 Catch:{ JSONException -> 0x0981 }
        r6 = "doritos";
        r7 = r1.zzead;	 Catch:{ JSONException -> 0x0981 }
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "doritos_v2";
        r7 = r1.zzeae;	 Catch:{ JSONException -> 0x0981 }
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = com.google.android.gms.internal.ads.zzaan.zzcro;	 Catch:{ JSONException -> 0x0981 }
        r7 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r6 = r7.zzd(r6);	 Catch:{ JSONException -> 0x0981 }
        r6 = (java.lang.Boolean) r6;	 Catch:{ JSONException -> 0x0981 }
        r6 = r6.booleanValue();	 Catch:{ JSONException -> 0x0981 }
        if (r6 == 0) goto L_0x0604;
    L_0x05c6:
        r6 = r1.zzeaf;	 Catch:{ JSONException -> 0x0981 }
        if (r6 == 0) goto L_0x05d7;
    L_0x05ca:
        r6 = r1.zzeaf;	 Catch:{ JSONException -> 0x0981 }
        r7 = r6.getId();	 Catch:{ JSONException -> 0x0981 }
        r6 = r1.zzeaf;	 Catch:{ JSONException -> 0x0981 }
        r9 = r6.isLimitAdTrackingEnabled();	 Catch:{ JSONException -> 0x0981 }
        goto L_0x05d9;
    L_0x05d7:
        r9 = r12;
        r7 = 0;
    L_0x05d9:
        r6 = android.text.TextUtils.isEmpty(r7);	 Catch:{ JSONException -> 0x0981 }
        if (r6 != 0) goto L_0x05f1;
    L_0x05df:
        r6 = "rdid";
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        r6 = "is_lat";
        r5.putBoolean(r6, r9);	 Catch:{ JSONException -> 0x0981 }
        r6 = "idtype";
        r7 = "adid";
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0604;
    L_0x05f1:
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ JSONException -> 0x0981 }
        r6 = com.google.android.gms.internal.ads.zzbat.zzbg(r23);	 Catch:{ JSONException -> 0x0981 }
        r7 = "pdid";
        r5.putString(r7, r6);	 Catch:{ JSONException -> 0x0981 }
        r6 = "pdidtype";
        r7 = "ssaid";
        r5.putString(r6, r7);	 Catch:{ JSONException -> 0x0981 }
    L_0x0604:
        r6 = "pii";
        r8.put(r6, r5);	 Catch:{ JSONException -> 0x0981 }
        r5 = "platform";
        r6 = android.os.Build.MANUFACTURER;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r5, r6);	 Catch:{ JSONException -> 0x0981 }
        r5 = "submodel";
        r6 = android.os.Build.MODEL;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r5, r6);	 Catch:{ JSONException -> 0x0981 }
        if (r3 == 0) goto L_0x061d;
    L_0x0619:
        zza(r8, r3);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0631;
    L_0x061d:
        r3 = r2.zzdwg;	 Catch:{ JSONException -> 0x0981 }
        r3 = r3.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 2;
        if (r3 < r5) goto L_0x0631;
    L_0x0624:
        r3 = r2.zzdwg;	 Catch:{ JSONException -> 0x0981 }
        r3 = r3.zzcjj;	 Catch:{ JSONException -> 0x0981 }
        if (r3 == 0) goto L_0x0631;
    L_0x062a:
        r3 = r2.zzdwg;	 Catch:{ JSONException -> 0x0981 }
        r3 = r3.zzcjj;	 Catch:{ JSONException -> 0x0981 }
        zza(r8, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0631:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 2;
        if (r3 < r5) goto L_0x063d;
    L_0x0636:
        r3 = "quality_signals";
        r5 = r2.zzdwk;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x063d:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 4;
        if (r3 < r5) goto L_0x0651;
    L_0x0642:
        r3 = r2.zzdwn;	 Catch:{ JSONException -> 0x0981 }
        if (r3 == 0) goto L_0x0651;
    L_0x0646:
        r3 = "forceHttps";
        r5 = r2.zzdwn;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x0651:
        if (r21 == 0) goto L_0x065a;
    L_0x0653:
        r3 = "content_info";
        r5 = r21;
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x065a:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 5;
        if (r3 < r5) goto L_0x0681;
    L_0x065f:
        r3 = "u_sd";
        r4 = r2.zzbwv;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Float.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
        r3 = "sh";
        r4 = r2.zzdwp;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
        r3 = "sw";
        r4 = r2.zzdwo;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x06a2;
    L_0x0681:
        r3 = "u_sd";
        r5 = r4.zzbwv;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Float.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
        r3 = "sh";
        r5 = r4.zzdwp;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
        r3 = "sw";
        r4 = r4.zzdwo;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x06a2:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r4 = 6;
        if (r3 < r4) goto L_0x06ce;
    L_0x06a7:
        r3 = r2.zzdwq;	 Catch:{ JSONException -> 0x0981 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r3 != 0) goto L_0x06c3;
    L_0x06af:
        r3 = "view_hierarchy";
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x06bc }
        r5 = r2.zzdwq;	 Catch:{ JSONException -> 0x06bc }
        r4.<init>(r5);	 Catch:{ JSONException -> 0x06bc }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x06bc }
        goto L_0x06c3;
    L_0x06bc:
        r0 = move-exception;
        r3 = r0;
        r4 = "Problem serializing view hierarchy to JSON";
        com.google.android.gms.internal.ads.zzbbd.zzc(r4, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x06c3:
        r3 = "correlation_id";
        r4 = r2.zzdwr;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x06ce:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r4 = 7;
        if (r3 < r4) goto L_0x06da;
    L_0x06d3:
        r3 = "request_id";
        r4 = r2.zzdws;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x06da:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r4 = 12;
        if (r3 < r4) goto L_0x06ef;
    L_0x06e0:
        r3 = r2.zzdww;	 Catch:{ JSONException -> 0x0981 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r3 != 0) goto L_0x06ef;
    L_0x06e8:
        r3 = "anchor";
        r4 = r2.zzdww;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x06ef:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r4 = 13;
        if (r3 < r4) goto L_0x0700;
    L_0x06f5:
        r3 = "android_app_volume";
        r4 = r2.zzdwx;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Float.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x0700:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r4 = 18;
        if (r3 < r4) goto L_0x0711;
    L_0x0706:
        r3 = "android_app_muted";
        r5 = r2.zzdxd;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x0711:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 14;
        if (r3 < r5) goto L_0x0726;
    L_0x0717:
        r3 = r2.zzdwy;	 Catch:{ JSONException -> 0x0981 }
        if (r3 <= 0) goto L_0x0726;
    L_0x071b:
        r3 = "target_api";
        r5 = r2.zzdwy;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x0726:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 15;
        if (r3 < r5) goto L_0x073e;
    L_0x072c:
        r3 = "scroll_index";
        r5 = r2.zzdwz;	 Catch:{ JSONException -> 0x0981 }
        r6 = -1;
        if (r5 != r6) goto L_0x0734;
    L_0x0733:
        goto L_0x0737;
    L_0x0734:
        r11 = r2.zzdwz;	 Catch:{ JSONException -> 0x0981 }
        r6 = r11;
    L_0x0737:
        r5 = java.lang.Integer.valueOf(r6);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x073e:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r5 = 16;
        if (r3 < r5) goto L_0x074f;
    L_0x0744:
        r3 = "_activity_context";
        r5 = r2.zzdxa;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x074f:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        if (r3 < r4) goto L_0x077a;
    L_0x0753:
        r3 = r2.zzdxe;	 Catch:{ JSONException -> 0x0981 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r3 != 0) goto L_0x076f;
    L_0x075b:
        r3 = "app_settings";
        r5 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0768 }
        r6 = r2.zzdxe;	 Catch:{ JSONException -> 0x0768 }
        r5.<init>(r6);	 Catch:{ JSONException -> 0x0768 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0768 }
        goto L_0x076f;
    L_0x0768:
        r0 = move-exception;
        r3 = r0;
        r5 = "Problem creating json from app settings";
        com.google.android.gms.internal.ads.zzbbd.zzc(r5, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x076f:
        r3 = "render_in_browser";
        r5 = r2.zzdlv;	 Catch:{ JSONException -> 0x0981 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r5);	 Catch:{ JSONException -> 0x0981 }
    L_0x077a:
        r3 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        if (r3 < r4) goto L_0x0789;
    L_0x077e:
        r3 = "android_num_video_cache_tasks";
        r4 = r2.zzdxf;	 Catch:{ JSONException -> 0x0981 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x0981 }
    L_0x0789:
        r3 = r2.zzbsp;	 Catch:{ JSONException -> 0x0981 }
        r4 = r2.zzdxr;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.zzeaj;	 Catch:{ JSONException -> 0x0981 }
        r5 = r2.zzdxt;	 Catch:{ JSONException -> 0x0981 }
        r6 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r6.<init>();	 Catch:{ JSONException -> 0x0981 }
        r7 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r7.<init>();	 Catch:{ JSONException -> 0x0981 }
        r9 = "cl";
        r10 = "221522000";
        r7.putString(r9, r10);	 Catch:{ JSONException -> 0x0981 }
        r9 = "rapid_rc";
        r10 = "dev";
        r7.putString(r9, r10);	 Catch:{ JSONException -> 0x0981 }
        r9 = "rapid_rollup";
        r10 = "HEAD";
        r7.putString(r9, r10);	 Catch:{ JSONException -> 0x0981 }
        r9 = "build_meta";
        r6.putBundle(r9, r7);	 Catch:{ JSONException -> 0x0981 }
        r7 = "mf";
        r9 = com.google.android.gms.internal.ads.zzaan.zzcua;	 Catch:{ JSONException -> 0x0981 }
        r10 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r9 = r10.zzd(r9);	 Catch:{ JSONException -> 0x0981 }
        r9 = (java.lang.Boolean) r9;	 Catch:{ JSONException -> 0x0981 }
        r9 = r9.booleanValue();	 Catch:{ JSONException -> 0x0981 }
        r9 = java.lang.Boolean.toString(r9);	 Catch:{ JSONException -> 0x0981 }
        r6.putString(r7, r9);	 Catch:{ JSONException -> 0x0981 }
        r7 = "instant_app";
        r6.putBoolean(r7, r4);	 Catch:{ JSONException -> 0x0981 }
        r4 = "lite";
        r3 = r3.zzeox;	 Catch:{ JSONException -> 0x0981 }
        r6.putBoolean(r4, r3);	 Catch:{ JSONException -> 0x0981 }
        r3 = "local_service";
        r6.putBoolean(r3, r1);	 Catch:{ JSONException -> 0x0981 }
        r1 = "is_privileged_process";
        r6.putBoolean(r1, r5);	 Catch:{ JSONException -> 0x0981 }
        r1 = "container_version";
        r3 = 12451009; // 0xbdfcc1 float:1.744758E-38 double:6.151616E-317;
        r6.putInt(r1, r3);	 Catch:{ JSONException -> 0x0981 }
        r1 = "sdk_env";
        r8.put(r1, r6);	 Catch:{ JSONException -> 0x0981 }
        r1 = "cache_state";
        r3 = r22;
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
        r1 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r3 = 19;
        if (r1 < r3) goto L_0x0805;
    L_0x07fe:
        r1 = "gct";
        r3 = r2.zzdxh;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0805:
        r1 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r3 = 21;
        if (r1 < r3) goto L_0x0816;
    L_0x080b:
        r1 = r2.zzdxi;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0816;
    L_0x080f:
        r1 = "de";
        r3 = "1";
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0816:
        r1 = com.google.android.gms.internal.ads.zzaan.zzcrz;	 Catch:{ JSONException -> 0x0981 }
        r3 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r1 = r3.zzd(r1);	 Catch:{ JSONException -> 0x0981 }
        r1 = (java.lang.Boolean) r1;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.booleanValue();	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x085c;
    L_0x0828:
        r1 = r2.zzbst;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.zzckk;	 Catch:{ JSONException -> 0x0981 }
        r3 = "interstitial_mb";
        r3 = r1.equals(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r3 != 0) goto L_0x083f;
    L_0x0834:
        r3 = "reward_mb";
        r1 = r1.equals(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x083d;
    L_0x083c:
        goto L_0x083f;
    L_0x083d:
        r1 = r12;
        goto L_0x0840;
    L_0x083f:
        r1 = 1;
    L_0x0840:
        r3 = r2.zzdxj;	 Catch:{ JSONException -> 0x0981 }
        if (r3 == 0) goto L_0x0847;
    L_0x0844:
        r19 = 1;
        goto L_0x0849;
    L_0x0847:
        r19 = r12;
    L_0x0849:
        if (r1 == 0) goto L_0x085c;
    L_0x084b:
        if (r19 == 0) goto L_0x085c;
    L_0x084d:
        r1 = new android.os.Bundle;	 Catch:{ JSONException -> 0x0981 }
        r1.<init>();	 Catch:{ JSONException -> 0x0981 }
        r4 = "interstitial_pool";
        r1.putBundle(r4, r3);	 Catch:{ JSONException -> 0x0981 }
        r3 = "counters";
        r8.put(r3, r1);	 Catch:{ JSONException -> 0x0981 }
    L_0x085c:
        r1 = r2.zzdxk;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0867;
    L_0x0860:
        r1 = "gmp_app_id";
        r3 = r2.zzdxk;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0867:
        r1 = r2.zzdxl;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x088d;
    L_0x086b:
        r1 = "TIME_OUT";
        r3 = r2.zzdxl;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.equals(r3);	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0885;
    L_0x0875:
        r1 = "sai_timeout";
        r3 = com.google.android.gms.internal.ads.zzaan.zzcqu;	 Catch:{ JSONException -> 0x0981 }
        r4 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r3 = r4.zzd(r3);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0894;
    L_0x0885:
        r1 = "fbs_aiid";
        r3 = r2.zzdxl;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0894;
    L_0x088d:
        r1 = "fbs_aiid";
        r3 = "";
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0894:
        r1 = r2.zzdxm;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x089f;
    L_0x0898:
        r1 = "fbs_aeid";
        r3 = r2.zzdxm;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x089f:
        r1 = r2.zzdxw;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x08aa;
    L_0x08a3:
        r1 = "apm_id_origin";
        r3 = r2.zzdxw;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x08aa:
        r1 = r2.versionCode;	 Catch:{ JSONException -> 0x0981 }
        r3 = 24;
        if (r1 < r3) goto L_0x08bb;
    L_0x08b0:
        r1 = "disable_ml";
        r3 = r2.zzdxs;	 Catch:{ JSONException -> 0x0981 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x08bb:
        r1 = com.google.android.gms.internal.ads.zzaan.zzcpj;	 Catch:{ JSONException -> 0x0981 }
        r3 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r1 = r3.zzd(r1);	 Catch:{ JSONException -> 0x0981 }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0902;
    L_0x08c9:
        r3 = r1.isEmpty();	 Catch:{ JSONException -> 0x0981 }
        if (r3 != 0) goto L_0x0902;
    L_0x08cf:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ JSONException -> 0x0981 }
        r4 = com.google.android.gms.internal.ads.zzaan.zzcpk;	 Catch:{ JSONException -> 0x0981 }
        r5 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r4 = r5.zzd(r4);	 Catch:{ JSONException -> 0x0981 }
        r4 = (java.lang.Integer) r4;	 Catch:{ JSONException -> 0x0981 }
        r4 = r4.intValue();	 Catch:{ JSONException -> 0x0981 }
        if (r3 < r4) goto L_0x0902;
    L_0x08e3:
        r3 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0981 }
        r3.<init>();	 Catch:{ JSONException -> 0x0981 }
        r4 = ",";
        r1 = r1.split(r4);	 Catch:{ JSONException -> 0x0981 }
        r4 = r1.length;	 Catch:{ JSONException -> 0x0981 }
    L_0x08ef:
        if (r12 >= r4) goto L_0x08fd;
    L_0x08f1:
        r5 = r1[r12];	 Catch:{ JSONException -> 0x0981 }
        r6 = com.google.android.gms.internal.ads.zzbar.zzeh(r5);	 Catch:{ JSONException -> 0x0981 }
        r3.put(r5, r6);	 Catch:{ JSONException -> 0x0981 }
        r12 = r12 + 1;
        goto L_0x08ef;
    L_0x08fd:
        r1 = "video_decoders";
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0902:
        r1 = com.google.android.gms.internal.ads.zzaan.zzcwy;	 Catch:{ JSONException -> 0x0981 }
        r3 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ JSONException -> 0x0981 }
        r1 = r3.zzd(r1);	 Catch:{ JSONException -> 0x0981 }
        r1 = (java.lang.Boolean) r1;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.booleanValue();	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0923;
    L_0x0914:
        r1 = "omid_v";
        r3 = com.google.android.gms.ads.internal.zzbv.zzlw();	 Catch:{ JSONException -> 0x0981 }
        r4 = r23;
        r3 = r3.getVersion(r4);	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0923:
        r1 = r2.zzdxu;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0936;
    L_0x0927:
        r1 = r2.zzdxu;	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.isEmpty();	 Catch:{ JSONException -> 0x0981 }
        if (r1 != 0) goto L_0x0936;
    L_0x092f:
        r1 = "android_permissions";
        r3 = r2.zzdxu;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0936:
        r1 = r2.zzdxv;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x0941;
    L_0x093a:
        r1 = "consent_string";
        r3 = r2.zzdxv;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r3);	 Catch:{ JSONException -> 0x0981 }
    L_0x0941:
        r1 = r2.zzdxv;	 Catch:{ JSONException -> 0x0981 }
        if (r1 == 0) goto L_0x094c;
    L_0x0945:
        r1 = "iab_consent_info";
        r2 = r2.zzdxx;	 Catch:{ JSONException -> 0x0981 }
        r8.put(r1, r2);	 Catch:{ JSONException -> 0x0981 }
    L_0x094c:
        r1 = 2;
        r2 = com.google.android.gms.internal.ads.zzbbd.isLoggable(r1);	 Catch:{ JSONException -> 0x0981 }
        if (r2 == 0) goto L_0x0978;
    L_0x0953:
        r2 = com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ JSONException -> 0x0981 }
        r2 = r2.zzn(r8);	 Catch:{ JSONException -> 0x0981 }
        r1 = r2.toString(r1);	 Catch:{ JSONException -> 0x0981 }
        r2 = "Ad Request JSON: ";
        r1 = java.lang.String.valueOf(r1);	 Catch:{ JSONException -> 0x0981 }
        r3 = r1.length();	 Catch:{ JSONException -> 0x0981 }
        if (r3 == 0) goto L_0x0970;
    L_0x096b:
        r1 = r2.concat(r1);	 Catch:{ JSONException -> 0x0981 }
        goto L_0x0975;
    L_0x0970:
        r1 = new java.lang.String;	 Catch:{ JSONException -> 0x0981 }
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0981 }
    L_0x0975:
        com.google.android.gms.internal.ads.zzaxz.v(r1);	 Catch:{ JSONException -> 0x0981 }
    L_0x0978:
        r1 = com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ JSONException -> 0x0981 }
        r1 = r1.zzn(r8);	 Catch:{ JSONException -> 0x0981 }
        return r1;
    L_0x0981:
        r0 = move-exception;
        r1 = r0;
        r2 = "Problem serializing ad request to JSON: ";
        r1 = r1.getMessage();
        r1 = java.lang.String.valueOf(r1);
        r3 = r1.length();
        if (r3 == 0) goto L_0x0998;
    L_0x0993:
        r1 = r2.concat(r1);
        goto L_0x099d;
    L_0x0998:
        r1 = new java.lang.String;
        r1.<init>(r2);
    L_0x099d:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatv.zza(android.content.Context, com.google.android.gms.internal.ads.zzato):org.json.JSONObject");
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put(LocationConstants.PARAM_LAT, valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static Integer zzag(boolean z) {
        return Integer.valueOf(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x016a A:{Catch:{ JSONException -> 0x029b }} */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0161 A:{Catch:{ JSONException -> 0x029b }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e7 A:{Catch:{ JSONException -> 0x029b }} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e1 A:{Catch:{ JSONException -> 0x029b }} */
    public static com.google.android.gms.internal.ads.zzasm zza(android.content.Context r59, com.google.android.gms.internal.ads.zzasi r60, java.lang.String r61) {
        /*
        r10 = r60;
        r15 = 0;
        r11 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x029b }
        r1 = r61;
        r11.<init>(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "ad_base_url";
        r12 = 0;
        r1 = r11.optString(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r2 = "ad_url";
        r4 = r11.optString(r2, r12);	 Catch:{ JSONException -> 0x029b }
        r2 = "ad_size";
        r13 = r11.optString(r2, r12);	 Catch:{ JSONException -> 0x029b }
        r2 = "ad_slot_size";
        r40 = r11.optString(r2, r13);	 Catch:{ JSONException -> 0x029b }
        if (r10 == 0) goto L_0x002c;
    L_0x0025:
        r2 = r10.zzdwl;	 Catch:{ JSONException -> 0x029b }
        if (r2 == 0) goto L_0x002c;
    L_0x0029:
        r24 = 1;
        goto L_0x002e;
    L_0x002c:
        r24 = r15;
    L_0x002e:
        r2 = "ad_json";
        r2 = r11.optString(r2, r12);	 Catch:{ JSONException -> 0x029b }
        if (r2 != 0) goto L_0x003c;
    L_0x0036:
        r2 = "ad_html";
        r2 = r11.optString(r2, r12);	 Catch:{ JSONException -> 0x029b }
    L_0x003c:
        if (r2 != 0) goto L_0x0044;
    L_0x003e:
        r2 = "body";
        r2 = r11.optString(r2, r12);	 Catch:{ JSONException -> 0x029b }
    L_0x0044:
        if (r2 != 0) goto L_0x0052;
    L_0x0046:
        r3 = "ads";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x029b }
        if (r3 == 0) goto L_0x0052;
    L_0x004e:
        r2 = r11.toString();	 Catch:{ JSONException -> 0x029b }
    L_0x0052:
        r3 = "debug_dialog";
        r19 = r11.optString(r3, r12);	 Catch:{ JSONException -> 0x029b }
        r3 = "debug_signals";
        r42 = r11.optString(r3, r12);	 Catch:{ JSONException -> 0x029b }
        r3 = "interstitial_timeout";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x029b }
        r8 = -1;
        if (r3 == 0) goto L_0x0079;
    L_0x0068:
        r3 = "interstitial_timeout";
        r5 = r11.getDouble(r3);	 Catch:{ JSONException -> 0x029b }
        r16 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r5 = r5 * r16;
        r5 = (long) r5;	 Catch:{ JSONException -> 0x029b }
        r16 = r5;
        goto L_0x007b;
    L_0x0079:
        r16 = r8;
    L_0x007b:
        r3 = "orientation";
        r3 = r11.optString(r3, r12);	 Catch:{ JSONException -> 0x029b }
        r5 = "portrait";
        r5 = r5.equals(r3);	 Catch:{ JSONException -> 0x029b }
        r7 = -1;
        if (r5 == 0) goto L_0x0095;
    L_0x008a:
        r3 = com.google.android.gms.ads.internal.zzbv.zzlh();	 Catch:{ JSONException -> 0x029b }
        r3 = r3.zzzx();	 Catch:{ JSONException -> 0x029b }
    L_0x0092:
        r18 = r3;
        goto L_0x00a8;
    L_0x0095:
        r5 = "landscape";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x029b }
        if (r3 == 0) goto L_0x00a6;
    L_0x009d:
        r3 = com.google.android.gms.ads.internal.zzbv.zzlh();	 Catch:{ JSONException -> 0x029b }
        r3 = r3.zzzw();	 Catch:{ JSONException -> 0x029b }
        goto L_0x0092;
    L_0x00a6:
        r18 = r7;
    L_0x00a8:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ JSONException -> 0x029b }
        if (r3 == 0) goto L_0x00d9;
    L_0x00ae:
        r3 = android.text.TextUtils.isEmpty(r4);	 Catch:{ JSONException -> 0x029b }
        if (r3 != 0) goto L_0x00d9;
    L_0x00b4:
        r1 = r10.zzbsp;	 Catch:{ JSONException -> 0x029b }
        r3 = r1.zzdp;	 Catch:{ JSONException -> 0x029b }
        r5 = 0;
        r6 = 0;
        r20 = 0;
        r21 = 0;
        r22 = 0;
        r1 = r10;
        r2 = r59;
        r14 = r7;
        r7 = r20;
        r8 = r21;
        r9 = r22;
        r1 = com.google.android.gms.internal.ads.zzatq.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ JSONException -> 0x029b }
        r2 = r1.zzbde;	 Catch:{ JSONException -> 0x029b }
        r3 = r1.zzdyb;	 Catch:{ JSONException -> 0x029b }
        r4 = r1.zzdyh;	 Catch:{ JSONException -> 0x029b }
        r20 = r4;
        r4 = r3;
        r3 = r2;
        goto L_0x00df;
    L_0x00d9:
        r14 = r7;
        r3 = r1;
        r4 = r2;
        r1 = r12;
        r20 = -1;
    L_0x00df:
        if (r4 != 0) goto L_0x00e7;
    L_0x00e1:
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ JSONException -> 0x029b }
        r1.<init>(r15);	 Catch:{ JSONException -> 0x029b }
        return r1;
    L_0x00e7:
        r2 = "click_urls";
        r2 = r11.optJSONArray(r2);	 Catch:{ JSONException -> 0x029b }
        if (r1 != 0) goto L_0x00f1;
    L_0x00ef:
        r5 = r12;
        goto L_0x00f3;
    L_0x00f1:
        r5 = r1.zzdlq;	 Catch:{ JSONException -> 0x029b }
    L_0x00f3:
        if (r2 == 0) goto L_0x00fa;
    L_0x00f5:
        r2 = zza(r2, r5);	 Catch:{ JSONException -> 0x029b }
        r5 = r2;
    L_0x00fa:
        r2 = "impression_urls";
        r2 = r11.optJSONArray(r2);	 Catch:{ JSONException -> 0x029b }
        if (r1 != 0) goto L_0x0104;
    L_0x0102:
        r6 = r12;
        goto L_0x0106;
    L_0x0104:
        r6 = r1.zzdlr;	 Catch:{ JSONException -> 0x029b }
    L_0x0106:
        if (r2 == 0) goto L_0x010d;
    L_0x0108:
        r2 = zza(r2, r6);	 Catch:{ JSONException -> 0x029b }
        r6 = r2;
    L_0x010d:
        r2 = "downloaded_impression_urls";
        r2 = r11.optJSONArray(r2);	 Catch:{ JSONException -> 0x029b }
        if (r1 != 0) goto L_0x0117;
    L_0x0115:
        r7 = r12;
        goto L_0x0119;
    L_0x0117:
        r7 = r1.zzdls;	 Catch:{ JSONException -> 0x029b }
    L_0x0119:
        if (r2 == 0) goto L_0x0122;
    L_0x011b:
        r2 = zza(r2, r7);	 Catch:{ JSONException -> 0x029b }
        r48 = r2;
        goto L_0x0124;
    L_0x0122:
        r48 = r7;
    L_0x0124:
        r2 = "manual_impression_urls";
        r2 = r11.optJSONArray(r2);	 Catch:{ JSONException -> 0x029b }
        if (r1 != 0) goto L_0x012e;
    L_0x012c:
        r7 = r12;
        goto L_0x0130;
    L_0x012e:
        r7 = r1.zzdyf;	 Catch:{ JSONException -> 0x029b }
    L_0x0130:
        if (r2 == 0) goto L_0x0139;
    L_0x0132:
        r2 = zza(r2, r7);	 Catch:{ JSONException -> 0x029b }
        r22 = r2;
        goto L_0x013b;
    L_0x0139:
        r22 = r7;
    L_0x013b:
        if (r1 == 0) goto L_0x0151;
    L_0x013d:
        r2 = r1.orientation;	 Catch:{ JSONException -> 0x029b }
        if (r2 == r14) goto L_0x0145;
    L_0x0141:
        r2 = r1.orientation;	 Catch:{ JSONException -> 0x029b }
        r18 = r2;
    L_0x0145:
        r7 = r1.zzdyc;	 Catch:{ JSONException -> 0x029b }
        r25 = 0;
        r2 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1));
        if (r2 <= 0) goto L_0x0151;
    L_0x014d:
        r1 = r1.zzdyc;	 Catch:{ JSONException -> 0x029b }
        r7 = r1;
        goto L_0x0153;
    L_0x0151:
        r7 = r16;
    L_0x0153:
        r1 = "active_view";
        r23 = r11.optString(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "ad_is_javascript";
        r25 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        if (r25 == 0) goto L_0x016a;
    L_0x0161:
        r1 = "ad_passback_url";
        r1 = r11.optString(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r26 = r1;
        goto L_0x016c;
    L_0x016a:
        r26 = r12;
    L_0x016c:
        r1 = "mediation";
        r9 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "custom_render_allowed";
        r27 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "content_url_opted_out";
        r2 = 1;
        r28 = r11.optBoolean(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "content_vertical_opted_out";
        r43 = r11.optBoolean(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "prefetch";
        r29 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "refresh_interval_milliseconds";
        r56 = r3;
        r2 = -1;
        r16 = r11.optLong(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "mediation_config_cache_time_milliseconds";
        r30 = r11.optLong(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "gws_query_id";
        r2 = "";
        r32 = r11.optString(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "height";
        r2 = "fluid";
        r3 = "";
        r2 = r11.optString(r2, r3);	 Catch:{ JSONException -> 0x029b }
        r33 = r1.equals(r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "native_express";
        r34 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "video_start_urls";
        r1 = r11.optJSONArray(r1);	 Catch:{ JSONException -> 0x029b }
        r35 = zza(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r1 = "video_complete_urls";
        r1 = r11.optJSONArray(r1);	 Catch:{ JSONException -> 0x029b }
        r36 = zza(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r1 = "rewards";
        r1 = r11.optJSONArray(r1);	 Catch:{ JSONException -> 0x029b }
        r37 = com.google.android.gms.internal.ads.zzawd.zza(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "use_displayed_impression";
        r38 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "auto_protection_configuration";
        r1 = r11.optJSONObject(r1);	 Catch:{ JSONException -> 0x029b }
        r39 = com.google.android.gms.internal.ads.zzaso.zzl(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "set_cookie";
        r2 = "";
        r41 = r11.optString(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "remote_ping_urls";
        r1 = r11.optJSONArray(r1);	 Catch:{ JSONException -> 0x029b }
        r44 = zza(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r1 = "safe_browsing";
        r1 = r11.optJSONObject(r1);	 Catch:{ JSONException -> 0x029b }
        r45 = com.google.android.gms.internal.ads.zzawo.zzo(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "render_in_browser";
        r2 = r10.zzdlv;	 Catch:{ JSONException -> 0x029b }
        r46 = r11.optBoolean(r1, r2);	 Catch:{ JSONException -> 0x029b }
        r1 = "custom_close_blocked";
        r47 = r11.optBoolean(r1);	 Catch:{ JSONException -> 0x029b }
        r1 = "enable_omid";
        r49 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = "omid_settings";
        r50 = r11.optString(r1, r12);	 Catch:{ JSONException -> 0x029b }
        r1 = "disable_closable_area";
        r51 = r11.optBoolean(r1, r15);	 Catch:{ JSONException -> 0x029b }
        r1 = r10.zzdwg;	 Catch:{ JSONException -> 0x029b }
        r1 = r1.zzcjl;	 Catch:{ JSONException -> 0x029b }
        r2 = com.google.ads.mediation.admob.AdMobAdapter.class;
        r2 = r2.getName();	 Catch:{ JSONException -> 0x029b }
        r1 = r1.getBundle(r2);	 Catch:{ JSONException -> 0x029b }
        if (r1 == 0) goto L_0x023c;
    L_0x0231:
        r2 = "is_analytics_logging_enabled";
        r1 = r1.getBoolean(r2, r15);	 Catch:{ JSONException -> 0x029b }
        if (r1 == 0) goto L_0x023c;
    L_0x0239:
        r52 = 1;
        goto L_0x023e;
    L_0x023c:
        r52 = r15;
    L_0x023e:
        r53 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ JSONException -> 0x029b }
        r14 = r10.zzdwn;	 Catch:{ JSONException -> 0x029b }
        r12 = r10.zzdxb;	 Catch:{ JSONException -> 0x029b }
        r11 = r10.zzdxn;	 Catch:{ JSONException -> 0x029b }
        r54 = 0;
        r55 = "";
        r1 = r53;
        r2 = r10;
        r3 = r56;
        r56 = r11;
        r10 = r30;
        r57 = r12;
        r12 = r22;
        r22 = r13;
        r30 = r14;
        r13 = r16;
        r15 = r18;
        r16 = r22;
        r17 = r20;
        r20 = r25;
        r21 = r26;
        r22 = r23;
        r23 = r27;
        r25 = r30;
        r26 = r28;
        r27 = r29;
        r28 = r32;
        r29 = r33;
        r30 = r34;
        r31 = r37;
        r32 = r35;
        r33 = r36;
        r34 = r38;
        r35 = r39;
        r36 = r57;
        r37 = r41;
        r38 = r44;
        r39 = r46;
        r41 = r45;
        r44 = r56;
        r45 = r47;
        r46 = r54;
        r47 = r49;
        r49 = r51;
        r51 = r55;
        r1.<init>(r2, r3, r4, r5, r6, r7, r9, r10, r12, r13, r15, r16, r17, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52);	 Catch:{ JSONException -> 0x029b }
        return r53;
    L_0x029b:
        r0 = move-exception;
        r1 = r0;
        r2 = "Could not parse the inline ad response: ";
        r1 = r1.getMessage();
        r1 = java.lang.String.valueOf(r1);
        r3 = r1.length();
        if (r3 == 0) goto L_0x02b2;
    L_0x02ad:
        r1 = r2.concat(r1);
        goto L_0x02b7;
    L_0x02b2:
        r1 = new java.lang.String;
        r1.<init>(r2);
    L_0x02b7:
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);
        r1 = new com.google.android.gms.internal.ads.zzasm;
        r2 = 0;
        r1.<init>(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatv.zza(android.content.Context, com.google.android.gms.internal.ads.zzasi, java.lang.String):com.google.android.gms.internal.ads.zzasm");
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        List list2;
        if (list2 == null) {
            list2 = new ArrayList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list2.add(jSONArray.getString(i));
        }
        return list2;
    }

    public static JSONObject zzb(zzasm zzasm) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (zzasm.zzbde != null) {
            jSONObject.put("ad_base_url", zzasm.zzbde);
        }
        if (zzasm.zzdyg != null) {
            jSONObject.put("ad_size", zzasm.zzdyg);
        }
        jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, zzasm.zzckn);
        if (zzasm.zzckn) {
            jSONObject.put("ad_json", zzasm.zzdyb);
        } else {
            jSONObject.put("ad_html", zzasm.zzdyb);
        }
        if (zzasm.zzdyi != null) {
            jSONObject.put("debug_dialog", zzasm.zzdyi);
        }
        if (zzasm.zzdyy != null) {
            jSONObject.put("debug_signals", zzasm.zzdyy);
        }
        if (zzasm.zzdyc != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzasm.zzdyc) / 1000.0d);
        }
        if (zzasm.orientation == zzbv.zzlh().zzzx()) {
            jSONObject.put("orientation", "portrait");
        } else if (zzasm.orientation == zzbv.zzlh().zzzw()) {
            jSONObject.put("orientation", "landscape");
        }
        if (zzasm.zzdlq != null) {
            jSONObject.put("click_urls", zzm(zzasm.zzdlq));
        }
        if (zzasm.zzdlr != null) {
            jSONObject.put("impression_urls", zzm(zzasm.zzdlr));
        }
        if (zzasm.zzdls != null) {
            jSONObject.put("downloaded_impression_urls", zzm(zzasm.zzdls));
        }
        if (zzasm.zzdyf != null) {
            jSONObject.put("manual_impression_urls", zzm(zzasm.zzdyf));
        }
        if (zzasm.zzdyl != null) {
            jSONObject.put("active_view", zzasm.zzdyl);
        }
        jSONObject.put("ad_is_javascript", zzasm.zzdyj);
        if (zzasm.zzdyk != null) {
            jSONObject.put("ad_passback_url", zzasm.zzdyk);
        }
        jSONObject.put("mediation", zzasm.zzdyd);
        jSONObject.put("custom_render_allowed", zzasm.zzdym);
        jSONObject.put("content_url_opted_out", zzasm.zzdyn);
        jSONObject.put("content_vertical_opted_out", zzasm.zzdyz);
        jSONObject.put("prefetch", zzasm.zzdyo);
        if (zzasm.zzdlx != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzasm.zzdlx);
        }
        if (zzasm.zzdye != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzasm.zzdye);
        }
        if (!TextUtils.isEmpty(zzasm.zzcgx)) {
            jSONObject.put("gws_query_id", zzasm.zzcgx);
        }
        jSONObject.put("fluid", zzasm.zzcko ? "height" : "");
        jSONObject.put("native_express", zzasm.zzckp);
        if (zzasm.zzdys != null) {
            jSONObject.put("video_start_urls", zzm(zzasm.zzdys));
        }
        if (zzasm.zzdyt != null) {
            jSONObject.put("video_complete_urls", zzm(zzasm.zzdyt));
        }
        if (zzasm.zzdyr != null) {
            zzawd zzawd = zzasm.zzdyr;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rb_type", zzawd.type);
            jSONObject2.put("rb_amount", zzawd.zzefo);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("rewards", jSONArray);
        }
        jSONObject.put("use_displayed_impression", zzasm.zzdyu);
        jSONObject.put("auto_protection_configuration", zzasm.zzdyv);
        jSONObject.put("render_in_browser", zzasm.zzdlv);
        jSONObject.put("disable_closable_area", zzasm.zzbpi);
        return jSONObject;
    }

    @Nullable
    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}

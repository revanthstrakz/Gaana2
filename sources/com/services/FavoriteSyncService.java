package com.services;

import android.app.IntentService;
import android.text.TextUtils;
import com.constants.Constants;
import com.e.a.c;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums;
import com.gaana.models.Artists;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions;
import com.gaana.models.Playlists;
import com.gaana.models.Radios;
import com.gaana.models.Tracks;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.n;
import com.managers.o;
import com.utilities.Util;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoriteSyncService extends IntentService {
    private int a = 0;

    public FavoriteSyncService() {
        super("FavoriteSyncService");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(android.content.Intent r6) {
        /*
        r5 = this;
        if (r6 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r6.getExtras();
        r1 = 0;
        if (r0 == 0) goto L_0x0011;
    L_0x000a:
        r2 = "extra_refresh";
        r0 = r0.getBoolean(r2, r1);
        goto L_0x0012;
    L_0x0011:
        r0 = r1;
    L_0x0012:
        r2 = r6.getAction();
        r3 = -1;
        r4 = r2.hashCode();
        switch(r4) {
            case -2006864533: goto L_0x0070;
            case -1866048565: goto L_0x0066;
            case -1855796027: goto L_0x005c;
            case -1850674857: goto L_0x0052;
            case -1848324441: goto L_0x0048;
            case -1343596810: goto L_0x003e;
            case 869313913: goto L_0x0034;
            case 1758418006: goto L_0x0029;
            case 1846263819: goto L_0x001f;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x007a;
    L_0x001f:
        r4 = "sync_occasion";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0027:
        r2 = 7;
        goto L_0x007b;
    L_0x0029:
        r4 = "extra_sync_local";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0031:
        r2 = 8;
        goto L_0x007b;
    L_0x0034:
        r4 = "sync_favorite_delta";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x003c:
        r2 = 1;
        goto L_0x007b;
    L_0x003e:
        r4 = "sync_playlist";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0046:
        r2 = 2;
        goto L_0x007b;
    L_0x0048:
        r4 = "sync_track";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0050:
        r2 = 5;
        goto L_0x007b;
    L_0x0052:
        r4 = "sync_radio";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x005a:
        r2 = 4;
        goto L_0x007b;
    L_0x005c:
        r4 = "sync_login";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0064:
        r2 = r1;
        goto L_0x007b;
    L_0x0066:
        r4 = "sync_album";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x006e:
        r2 = 3;
        goto L_0x007b;
    L_0x0070:
        r4 = "sync_artist";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x007a;
    L_0x0078:
        r2 = 6;
        goto L_0x007b;
    L_0x007a:
        r2 = r3;
    L_0x007b:
        switch(r2) {
            case 0: goto L_0x00db;
            case 1: goto L_0x00d7;
            case 2: goto L_0x00c9;
            case 3: goto L_0x00bb;
            case 4: goto L_0x00ad;
            case 5: goto L_0x009f;
            case 6: goto L_0x0091;
            case 7: goto L_0x0083;
            case 8: goto L_0x007f;
            default: goto L_0x007e;
        };
    L_0x007e:
        goto L_0x00de;
    L_0x007f:
        r5.e();
        goto L_0x00de;
    L_0x0083:
        r2 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x0091:
        r2 = com.managers.URLManager.BusinessObjectType.Artists;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x009f:
        r2 = com.managers.URLManager.BusinessObjectType.Tracks;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x00ad:
        r2 = com.managers.URLManager.BusinessObjectType.Radios;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x00bb:
        r2 = com.managers.URLManager.BusinessObjectType.Albums;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x00c9:
        r2 = com.managers.URLManager.BusinessObjectType.Playlists;
        r0 = com.constants.Constants.a(r2, r0);
        r0 = r0.c();
        r5.a(r0);
        goto L_0x00de;
    L_0x00d7:
        r5.b();
        goto L_0x00de;
    L_0x00db:
        r5.a();
    L_0x00de:
        r0 = r6.getAction();
        r2 = "extra_sync_local";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00ed;
    L_0x00ea:
        r5.d();
    L_0x00ed:
        r0 = "extra_result_receiver";
        r6 = r6.getParcelableExtra(r0);
        r6 = (android.os.ResultReceiver) r6;
        if (r6 == 0) goto L_0x00ff;
    L_0x00f7:
        r0 = new android.os.Bundle;
        r0.<init>();
        r6.send(r1, r0);
    L_0x00ff:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.FavoriteSyncService.onHandleIntent(android.content.Intent):void");
    }

    private void a() {
        this.a = d.a().b("favorite_sync_flag", 0, false);
        if ((this.a & 1) != 1) {
            a(Constants.a(BusinessObjectType.Tracks).c());
        }
        if ((this.a & 2) != 2) {
            a(Constants.a(BusinessObjectType.Playlists).c());
        }
        if ((this.a & 4) != 4) {
            a(Constants.a(BusinessObjectType.Albums).c());
        }
        if ((this.a & 16) != 16) {
            a(Constants.a(BusinessObjectType.Artists).c());
        }
        if ((this.a & 8) != 8) {
            a(Constants.a(BusinessObjectType.Radios).c());
        }
        if ((this.a & 32) != 32) {
            a(Constants.a(BusinessObjectType.FavoriteOccasions).c());
        }
        c();
    }

    private void b() {
        e();
        a(Constants.a(BusinessObjectType.Tracks, false).c());
        a(Constants.a(BusinessObjectType.Playlists, false).c());
        a(Constants.a(BusinessObjectType.Albums, false).c());
        a(Constants.a(BusinessObjectType.Artists, false).c());
        a(Constants.a(BusinessObjectType.Radios, false).c());
        a(Constants.a(BusinessObjectType.FavoriteOccasions, false).c());
    }

    private void a(URLManager uRLManager) {
        if (uRLManager != null && Util.j(GaanaApplication.getContext()) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            if (!uRLManager.k().contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE)) {
                uRLManager.a(o.a().a(uRLManager, true));
            }
            BusinessObject a = o.a().a(uRLManager, true, uRLManager.k(), uRLManager.k());
            if (!(a == null || a.getVolleyError() != null || a(a, uRLManager) || a.getUrlManager() == null || !a.getUrlManager().e().booleanValue())) {
                uRLManager = a.getUrlManager();
                uRLManager.a(b(uRLManager.k()));
                a(uRLManager);
            }
        }
    }

    private boolean a(BusinessObject businessObject, URLManager uRLManager) {
        boolean z = true;
        if (businessObject != null) {
            List arrListBusinessObj = businessObject.getArrListBusinessObj();
            if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                c.a().a(arrListBusinessObj);
                if (arrListBusinessObj.size() >= 100) {
                    z = false;
                }
            }
            a(businessObject, z);
        }
        return z;
    }

    private void a(BusinessObject businessObject, boolean z) {
        String unfavorite;
        if (businessObject instanceof Tracks) {
            unfavorite = ((Tracks) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                c.a().a(unfavorite, BusinessObjectType.Tracks);
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_tracks", false);
            }
            if (z) {
                this.a |= 1;
            }
        } else if (businessObject instanceof Playlists) {
            unfavorite = ((Playlists) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                c.a().a(unfavorite, BusinessObjectType.Playlists);
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_playlist", false);
            }
            if (z) {
                this.a |= 2;
            }
        } else if (businessObject instanceof Albums) {
            unfavorite = ((Albums) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                c.a().a(unfavorite, BusinessObjectType.Albums);
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_album", false);
            }
            if (z) {
                this.a |= 4;
            }
        } else if (businessObject instanceof Radios) {
            unfavorite = ((Radios) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                String d = d(unfavorite);
                String e = e(unfavorite);
                if (!TextUtils.isEmpty(d)) {
                    c.a().c(unfavorite, "RD_M");
                }
                if (!TextUtils.isEmpty(e)) {
                    c.a().c(unfavorite, "RD_L");
                }
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_radios", false);
            }
            if (z) {
                this.a |= 8;
            }
        } else if (businessObject instanceof Artists) {
            unfavorite = ((Artists) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                c.a().a(unfavorite, BusinessObjectType.Artists);
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_artist", false);
            }
            if (z) {
                this.a |= 16;
            }
        } else if (businessObject instanceof FavoriteOccasions) {
            unfavorite = ((FavoriteOccasions) businessObject).getUnfavorite();
            if (!TextUtils.isEmpty(unfavorite)) {
                c.a().a(unfavorite, BusinessObjectType.FavoriteOccasions);
            }
            if (businessObject.getResponseTime() > 0) {
                d.a().a(businessObject.getResponseTime(), "favorite_sync_occasions", false);
            }
            if (z) {
                this.a |= 32;
            }
        }
    }

    private void c() {
        if ((this.a & 63) == 63) {
            d.a().a("favorite_sync_login", true, false);
        }
        d.a().a("favorite_sync_flag", this.a, false);
    }

    private void d() {
        n.a().c();
    }

    private String a(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("Status")) {
                str2 = jSONObject.getString("Status");
            }
            if (jSONObject.has("status")) {
                return jSONObject.getString("status");
            }
            return str2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    private java.lang.String b(java.lang.String r6) {
        /*
        r5 = this;
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 != 0) goto L_0x000a;
    L_0x0006:
        r6 = r5.c(r6);
    L_0x000a:
        r0 = "limit";
        r0 = r6.contains(r0);
        r1 = 100;
        r2 = 0;
        if (r0 == 0) goto L_0x0086;
    L_0x0015:
        r0 = "limit=";
        r0 = r6.split(r0);
        r3 = 1;
        r4 = r0.length;
        if (r4 <= r3) goto L_0x0033;
    L_0x001f:
        r0 = r0[r3];
        r4 = ",";
        r0 = r0.split(r4);
        r4 = r0.length;
        if (r4 <= r3) goto L_0x0033;
    L_0x002a:
        r0 = r0[r3];
        r0 = java.lang.Integer.parseInt(r0);
        r1 = r0 + 100;
        goto L_0x0034;
    L_0x0033:
        r0 = r2;
    L_0x0034:
        r3 = "?limit";
        r3 = r6.contains(r3);
        if (r3 == 0) goto L_0x0061;
    L_0x003c:
        r3 = "\\?limit";
        r6 = r6.split(r3);
        r6 = r6[r2];
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r6);
        r6 = "?limit=";
        r2.append(r6);
        r2.append(r0);
        r6 = ",";
        r2.append(r6);
        r2.append(r1);
        r6 = r2.toString();
        return r6;
    L_0x0061:
        r3 = "&limit";
        r6 = r6.split(r3);
        r6 = r6[r2];
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r6);
        r6 = "&limit=";
        r2.append(r6);
        r2.append(r0);
        r6 = ",";
        r2.append(r6);
        r2.append(r1);
        r6 = r2.toString();
        return r6;
    L_0x0086:
        r0 = "?";
        r0 = r6.contains(r0);
        if (r0 == 0) goto L_0x00ab;
    L_0x008e:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r6);
        r6 = "&limit=";
        r0.append(r6);
        r0.append(r2);
        r6 = ",";
        r0.append(r6);
        r0.append(r1);
        r6 = r0.toString();
        return r6;
    L_0x00ab:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r6);
        r6 = "?limit=";
        r0.append(r6);
        r0.append(r2);
        r6 = ",";
        r0.append(r6);
        r0.append(r1);
        r6 = r0.toString();
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.FavoriteSyncService.b(java.lang.String):java.lang.String");
    }

    private String c(String str) {
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
        return str2 == null ? str : str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
    private void e() {
        /*
        r27 = this;
        r0 = com.e.a.c.a();
        r0 = r0.c();
        r1 = r0.size();
        if (r1 <= 0) goto L_0x0358;
    L_0x000e:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = new java.lang.StringBuilder;
        r14.<init>();
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r16 = r3;
        r3 = new org.json.JSONObject;
        r3.<init>();
        r17 = 0;
        r19 = r2;
        r18 = r3;
        r3 = r17;
    L_0x0063:
        if (r3 >= r1) goto L_0x019c;
    L_0x0065:
        r20 = r0.get(r3);
        r2 = r20;
        r2 = (com.gaana.models.FavoriteItem) r2;
        r21 = r0;
        r0 = r2.getId();
        r22 = r1;
        r1 = r2.getType();
        r2 = r2.isFavorite();
        r20 = -1;
        r23 = r3;
        r3 = r1.hashCode();
        r24 = r7;
        r7 = 2091; // 0x82b float:2.93E-42 double:1.033E-320;
        if (r3 == r7) goto L_0x00dc;
    L_0x008b:
        r7 = 2097; // 0x831 float:2.939E-42 double:1.036E-320;
        if (r3 == r7) goto L_0x00d2;
    L_0x008f:
        r7 = 2516; // 0x9d4 float:3.526E-42 double:1.243E-320;
        if (r3 == r7) goto L_0x00c8;
    L_0x0093:
        r7 = 2556; // 0x9fc float:3.582E-42 double:1.263E-320;
        if (r3 == r7) goto L_0x00be;
    L_0x0097:
        r7 = 2686; // 0xa7e float:3.764E-42 double:1.327E-320;
        if (r3 == r7) goto L_0x00b3;
    L_0x009b:
        switch(r3) {
            case 2511231: goto L_0x00a9;
            case 2511232: goto L_0x009f;
            default: goto L_0x009e;
        };
    L_0x009e:
        goto L_0x00e6;
    L_0x009f:
        r3 = "RD_M";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00a7:
        r1 = 2;
        goto L_0x00e8;
    L_0x00a9:
        r3 = "RD_L";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00b1:
        r1 = 5;
        goto L_0x00e8;
    L_0x00b3:
        r3 = "TR";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00bb:
        r1 = r17;
        goto L_0x00e8;
    L_0x00be:
        r3 = "PL";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00c6:
        r1 = 1;
        goto L_0x00e8;
    L_0x00c8:
        r3 = "OC";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00d0:
        r1 = 6;
        goto L_0x00e8;
    L_0x00d2:
        r3 = "AR";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00da:
        r1 = 4;
        goto L_0x00e8;
    L_0x00dc:
        r3 = "AL";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x00e6;
    L_0x00e4:
        r1 = 3;
        goto L_0x00e8;
    L_0x00e6:
        r1 = r20;
    L_0x00e8:
        switch(r1) {
            case 0: goto L_0x0171;
            case 1: goto L_0x0157;
            case 2: goto L_0x0143;
            case 3: goto L_0x012f;
            case 4: goto L_0x011b;
            case 5: goto L_0x0107;
            case 6: goto L_0x00f3;
            default: goto L_0x00eb;
        };
    L_0x00eb:
        r7 = r16;
        r2 = r19;
        r3 = r24;
        goto L_0x018e;
    L_0x00f3:
        if (r2 == 0) goto L_0x00fe;
    L_0x00f5:
        r1 = ",";
        r14.append(r1);
        r14.append(r0);
        goto L_0x00eb;
    L_0x00fe:
        r1 = ",";
        r15.append(r1);
        r15.append(r0);
        goto L_0x00eb;
    L_0x0107:
        if (r2 == 0) goto L_0x0112;
    L_0x0109:
        r1 = ",";
        r12.append(r1);
        r12.append(r0);
        goto L_0x00eb;
    L_0x0112:
        r1 = ",";
        r13.append(r1);
        r13.append(r0);
        goto L_0x00eb;
    L_0x011b:
        if (r2 == 0) goto L_0x0126;
    L_0x011d:
        r1 = ",";
        r8.append(r1);
        r8.append(r0);
        goto L_0x00eb;
    L_0x0126:
        r1 = ",";
        r9.append(r1);
        r9.append(r0);
        goto L_0x00eb;
    L_0x012f:
        if (r2 == 0) goto L_0x013a;
    L_0x0131:
        r1 = ",";
        r4.append(r1);
        r4.append(r0);
        goto L_0x00eb;
    L_0x013a:
        r1 = ",";
        r5.append(r1);
        r5.append(r0);
        goto L_0x00eb;
    L_0x0143:
        if (r2 == 0) goto L_0x014e;
    L_0x0145:
        r1 = ",";
        r10.append(r1);
        r10.append(r0);
        goto L_0x00eb;
    L_0x014e:
        r1 = ",";
        r11.append(r1);
        r11.append(r0);
        goto L_0x00eb;
    L_0x0157:
        if (r2 == 0) goto L_0x0162;
    L_0x0159:
        r1 = ",";
        r6.append(r1);
        r6.append(r0);
        goto L_0x00eb;
    L_0x0162:
        r1 = ",";
        r3 = r24;
        r3.append(r1);
        r3.append(r0);
        r7 = r16;
        r2 = r19;
        goto L_0x018e;
    L_0x0171:
        r3 = r24;
        if (r2 == 0) goto L_0x0182;
    L_0x0175:
        r1 = ",";
        r2 = r19;
        r2.append(r1);
        r2.append(r0);
        r7 = r16;
        goto L_0x018e;
    L_0x0182:
        r2 = r19;
        r1 = ",";
        r7 = r16;
        r7.append(r1);
        r7.append(r0);
    L_0x018e:
        r0 = r23 + 1;
        r19 = r2;
        r16 = r7;
        r1 = r22;
        r7 = r3;
        r3 = r0;
        r0 = r21;
        goto L_0x0063;
    L_0x019c:
        r21 = r0;
        r22 = r1;
        r3 = r7;
        r7 = r16;
        r2 = r19;
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r2 = r2.toString();	 Catch:{ Exception -> 0x0358 }
        r25 = r15;
        r15 = ",";
        r26 = r14;
        r14 = "";
        r2 = r2.replaceFirst(r15, r14);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r2 = r7.toString();	 Catch:{ Exception -> 0x0358 }
        r7 = ",";
        r14 = "";
        r2 = r2.replaceFirst(r7, r14);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x0358 }
        r1 = "track";
        r2 = r18;
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r4 = r4.toString();	 Catch:{ Exception -> 0x0358 }
        r7 = ",";
        r14 = "";
        r4 = r4.replaceFirst(r7, r14);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r4);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r4 = r5.toString();	 Catch:{ Exception -> 0x0358 }
        r5 = ",";
        r7 = "";
        r4 = r4.replaceFirst(r5, r7);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r4);	 Catch:{ Exception -> 0x0358 }
        r1 = "album";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r4 = r6.toString();	 Catch:{ Exception -> 0x0358 }
        r5 = ",";
        r6 = "";
        r4 = r4.replaceFirst(r5, r6);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r4);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r3 = r3.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "playlist";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r3 = r8.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r3 = r9.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "artist";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r3 = r10.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r3 = r11.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "radio_m";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r3 = r12.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r3 = r13.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "radio_l";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0358 }
        r0.<init>();	 Catch:{ Exception -> 0x0358 }
        r1 = "add";
        r3 = r26;
        r3 = r3.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "remove";
        r3 = r25;
        r3 = r3.toString();	 Catch:{ Exception -> 0x0358 }
        r4 = ",";
        r5 = "";
        r3 = r3.replaceFirst(r4, r5);	 Catch:{ Exception -> 0x0358 }
        r0.put(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = "occasion";
        r2.put(r1, r0);	 Catch:{ Exception -> 0x0358 }
        r0 = "https://api.gaana.com/user.php?type=set_all_favourite_items";
        r1 = new com.managers.URLManager;	 Catch:{ Exception -> 0x0358 }
        r1.<init>();	 Catch:{ Exception -> 0x0358 }
        r1.a(r0);	 Catch:{ Exception -> 0x0358 }
        r0 = com.managers.o.a();	 Catch:{ Exception -> 0x0358 }
        r3 = 1;
        r0 = r0.a(r1, r3);	 Catch:{ Exception -> 0x0358 }
        r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0358 }
        r1.<init>();	 Catch:{ Exception -> 0x0358 }
        r3 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x0358 }
        r4 = "favourite_items";
        r2 = r2.toString();	 Catch:{ Exception -> 0x0358 }
        r3.<init>(r4, r2);	 Catch:{ Exception -> 0x0358 }
        r1.add(r3);	 Catch:{ Exception -> 0x0358 }
        r2 = new com.services.j;	 Catch:{ Exception -> 0x0358 }
        r2.<init>();	 Catch:{ Exception -> 0x0358 }
        r0 = r2.a(r0, r1);	 Catch:{ Exception -> 0x0358 }
        r1 = r27;
        r0 = r1.a(r0);	 Catch:{ Exception -> 0x035a }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x035a }
        if (r0 != 0) goto L_0x035a;
    L_0x031e:
        r2 = r17;
        r0 = r22;
    L_0x0322:
        if (r2 >= r0) goto L_0x035a;
    L_0x0324:
        r3 = r21;
        r4 = r3.get(r2);	 Catch:{ Exception -> 0x035a }
        r4 = (com.gaana.models.FavoriteItem) r4;	 Catch:{ Exception -> 0x035a }
        r5 = r4.isFavorite();	 Catch:{ Exception -> 0x035a }
        if (r5 == 0) goto L_0x0343;
    L_0x0332:
        r5 = com.e.a.c.a();	 Catch:{ Exception -> 0x035a }
        r6 = r4.getId();	 Catch:{ Exception -> 0x035a }
        r4 = r4.getType();	 Catch:{ Exception -> 0x035a }
        r7 = 1;
        r5.a(r6, r4, r7);	 Catch:{ Exception -> 0x035a }
        goto L_0x0353;
    L_0x0343:
        r7 = 1;
        r5 = com.e.a.c.a();	 Catch:{ Exception -> 0x035a }
        r6 = r4.getId();	 Catch:{ Exception -> 0x035a }
        r4 = r4.getType();	 Catch:{ Exception -> 0x035a }
        r5.a(r6, r4);	 Catch:{ Exception -> 0x035a }
    L_0x0353:
        r2 = r2 + 1;
        r21 = r3;
        goto L_0x0322;
    L_0x0358:
        r1 = r27;
    L_0x035a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.FavoriteSyncService.e():void");
    }

    private String d(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("rm")) {
                return jSONObject.getString("rm");
            }
            return str2;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private String e(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("rl")) {
                return jSONObject.getString("rl");
            }
            return str2;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

package com.managers;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.constants.Constants.VIEW_SIZE;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.helpshift.common.platform.network.c;
import com.models.PlayerTrack;
import com.payu.custombrowser.util.CBConstant;
import com.services.d;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.internal.ColombiaException;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.Colombia;
import com.til.colombia.android.service.ColombiaAdManager;
import com.til.colombia.android.service.ColombiaAdManager.DFP_ITEM_TYPE;
import com.til.colombia.android.service.ColombiaAdManager.GENDER;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ColombiaAdRequest.Builder;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ColombiaManager {
    private static ColombiaManager b;
    ArrayList<AdConfig> a = null;
    private GENDER c = GENDER.MALE;
    private Date d = null;
    private ColombiaAdManager e;
    private boolean f = false;
    private c g = null;

    public interface b {
        void a();

        void a(boolean z);

        void b();

        void c();

        void d();

        void e();
    }

    public enum ADSTATUS {
        LOADING,
        FAILED,
        LOADED,
        REFRESH
    }

    public interface a {
        void onItemLoaded(Item item);

        void onItemRequestFailed(Exception exception);
    }

    public void a(String str, String str2) {
        String currentSponsoredOccassion = GaanaApplication.getInstance().getCurrentSponsoredOccassion();
        if (currentSponsoredOccassion != null) {
            this.g = new c("OC", currentSponsoredOccassion);
        } else {
            this.g = new c(str, str2);
        }
    }

    public void a() {
        if (this.g != null && !TextUtils.isEmpty(this.g.a) && !this.g.a.equalsIgnoreCase("OP")) {
            this.g = null;
        }
    }

    public static ColombiaManager b() {
        if (b == null) {
            synchronized (ColombiaManager.class) {
                if (b == null) {
                    b = new ColombiaManager();
                }
            }
        }
        return b;
    }

    private ColombiaManager() {
    }

    public void a(Context context) {
        if (context != null) {
            if (this.e != null) {
                this.e.destroy();
            }
            this.e = ColombiaAdManager.create(context);
        }
    }

    public void c() {
        if (this.e != null) {
            this.e.reset();
        }
    }

    public void d() {
        if (!GaanaApplication.getInstance().getColombiaSdkInit()) {
            try {
                Colombia.initialize(GaanaApplication.getContext());
                GaanaApplication.getInstance().setColombiaSdkinit(true);
            } catch (Exception unused) {
                GaanaApplication.getInstance().setColombiaSdkinit(false);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:283:0x0394 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x038d  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0385  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0394 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x038d  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0385  */
    public void a(com.gaana.models.SDKConfig r19) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r19.getSponsored_content();
        com.managers.e.ad = r1;
        r1 = r19.getColombiaAdCode();
        if (r1 == 0) goto L_0x0018;
    L_0x000e:
        r1 = r19.getColombiaAdCode();
        r1 = r1.getAd_config();
        r0.a = r1;
    L_0x0018:
        r1 = r19.getDfpAdCode();
        r2 = 0;
        if (r1 == 0) goto L_0x004c;
    L_0x001f:
        r1 = r19.getDfpAdCode();
        r2 = r1.fallback_image_url;
        com.managers.e.T = r2;
        r2 = r1.fallback_deeplink_url;
        com.managers.e.U = r2;
        r2 = r1.top_banner_hp_height;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x003b;
    L_0x0033:
        r1 = r1.top_banner_hp_height;
        r1 = java.lang.Integer.parseInt(r1);
        com.managers.e.S = r1;
    L_0x003b:
        r1 = r19.getDfpAdCode();
        r2 = r1.getDfpConfig();
        r1 = r19.getDfpAdCode();
        r1 = r1.getDfpMediaConfig();
        goto L_0x004d;
    L_0x004c:
        r1 = r2;
    L_0x004d:
        r3 = r19.getConfig();
        r4 = 0;
        r5 = 1;
        if (r3 == 0) goto L_0x00ce;
    L_0x0055:
        r3 = r19.getConfig();
        r3 = r3.getExcl_IMA();
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x008e;
    L_0x0063:
        r3 = r19.getConfig();
        r3 = r3.getExcl_IMA();
        r3 = r3.trim();
        r6 = "\\s*,\\s*";
        r3 = r3.split(r6);
        r6 = new java.util.HashMap;
        r6.<init>();
        com.constants.Constants.cj = r6;
        r6 = r4;
    L_0x007d:
        r7 = r3.length;
        if (r6 >= r7) goto L_0x008e;
    L_0x0080:
        r7 = com.constants.Constants.cj;
        r8 = r3[r6];
        r9 = java.lang.Boolean.valueOf(r5);
        r7.put(r8, r9);
        r6 = r6 + 1;
        goto L_0x007d;
    L_0x008e:
        r3 = r19.getConfig();
        r3 = r3.getExcl_display();
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x00ce;
    L_0x009c:
        r3 = r19.getConfig();
        r3 = r3.getExcl_display();
        r3 = r3.trim();
        r6 = "\\s*,\\s*";
        r3 = r3.split(r6);
        r6 = new java.util.HashMap;
        r6.<init>();
        com.constants.Constants.ck = r6;
        r6 = r4;
    L_0x00b6:
        r7 = r3.length;
        if (r6 >= r7) goto L_0x00c7;
    L_0x00b9:
        r7 = com.constants.Constants.ck;
        r8 = r3[r6];
        r9 = java.lang.Boolean.valueOf(r5);
        r7.put(r8, r9);
        r6 = r6 + 1;
        goto L_0x00b6;
    L_0x00c7:
        r3 = com.gaana.application.GaanaApplication.getInstance();
        r3.setNetworkExtrasBundle();
    L_0x00ce:
        r11 = 7;
        r12 = 16;
        r13 = 13;
        r14 = 3;
        r15 = -1;
        if (r2 == 0) goto L_0x032d;
    L_0x00d7:
        r3 = r4;
    L_0x00d8:
        r6 = r2.size();
        if (r3 >= r6) goto L_0x032d;
    L_0x00de:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getAdTitle();
        r16 = r2.get(r3);
        r7 = r16;
        r7 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r7;
        r7 = r7.getAdCode();
        r16 = r2.get(r3);
        r8 = r16;
        r8 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r8;
        r8 = r8.getStatus();
        r16 = r2.get(r3);
        r9 = r16;
        r9 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r9;
        r9 = r9.getAdServer();
        r9 = r9.intValue();
        r16 = r2.get(r3);
        r10 = r16;
        r10 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r10;
        r10 = r10.getMediation();
        r10 = r10.intValue();
        if (r8 == 0) goto L_0x0329;
    L_0x0122:
        r8 = r8.intValue();
        if (r8 == 0) goto L_0x0329;
    L_0x0128:
        r8 = r6.hashCode();
        switch(r8) {
            case -2137736366: goto L_0x021c;
            case -2117018696: goto L_0x0211;
            case -2050191939: goto L_0x0206;
            case -1184125465: goto L_0x01fb;
            case -1085221641: goto L_0x01f0;
            case -826013206: goto L_0x01e6;
            case -811571055: goto L_0x01dc;
            case -493187139: goto L_0x01d1;
            case -480564511: goto L_0x01c6;
            case -239580146: goto L_0x01ba;
            case -239063943: goto L_0x01ae;
            case 113110: goto L_0x01a2;
            case 3208415: goto L_0x0197;
            case 79114525: goto L_0x018b;
            case 127119171: goto L_0x017f;
            case 604727084: goto L_0x0173;
            case 611110765: goto L_0x0168;
            case 1351977127: goto L_0x015d;
            case 1385528717: goto L_0x0152;
            case 1718596328: goto L_0x0147;
            case 1895932181: goto L_0x013c;
            case 1946825943: goto L_0x0131;
            default: goto L_0x012f;
        };
    L_0x012f:
        goto L_0x0226;
    L_0x0131:
        r8 = "bottom_banner_ros";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0139:
        r6 = r14;
        goto L_0x0227;
    L_0x013c:
        r8 = "radio_detail";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0144:
        r6 = r13;
        goto L_0x0227;
    L_0x0147:
        r8 = "radio_mirchi_top";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x014f:
        r6 = r12;
        goto L_0x0227;
    L_0x0152:
        r8 = "gaana_live_radio";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x015a:
        r6 = r11;
        goto L_0x0227;
    L_0x015d:
        r8 = "extended_player";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0165:
        r6 = r4;
        goto L_0x0227;
    L_0x0168:
        r8 = "top_banner_ros";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0170:
        r6 = 2;
        goto L_0x0227;
    L_0x0173:
        r8 = "interstitial";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x017b:
        r6 = 20;
        goto L_0x0227;
    L_0x017f:
        r8 = "gaana_live_radio_detail";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0187:
        r6 = 14;
        goto L_0x0227;
    L_0x018b:
        r8 = "gaana_live_radio_bottom";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0193:
        r6 = 9;
        goto L_0x0227;
    L_0x0197:
        r8 = "home";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x019f:
        r6 = 5;
        goto L_0x0227;
    L_0x01a2:
        r8 = "ros";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01aa:
        r6 = 10;
        goto L_0x0227;
    L_0x01ae:
        r8 = "dedication_bottom_banner";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01b6:
        r6 = 11;
        goto L_0x0227;
    L_0x01ba:
        r8 = "rewarded";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01c2:
        r6 = 21;
        goto L_0x0227;
    L_0x01c6:
        r8 = "special_event_top_banner";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01ce:
        r6 = 12;
        goto L_0x0227;
    L_0x01d1:
        r8 = "sponsored_content_ad_code";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01d9:
        r6 = 18;
        goto L_0x0227;
    L_0x01dc:
        r8 = "top_banner_hp";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01e4:
        r6 = r5;
        goto L_0x0227;
    L_0x01e6:
        r8 = "top_banner_player";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01ee:
        r6 = 4;
        goto L_0x0227;
    L_0x01f0:
        r8 = "ros_dfp_100";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x01f8:
        r6 = 15;
        goto L_0x0227;
    L_0x01fb:
        r8 = "bottom_banner_hp";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0203:
        r6 = 17;
        goto L_0x0227;
    L_0x0206:
        r8 = "detail_page";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x020e:
        r6 = 19;
        goto L_0x0227;
    L_0x0211:
        r8 = "radio_mirchi_bottom";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0219:
        r6 = 8;
        goto L_0x0227;
    L_0x021c:
        r8 = "radio_mirchi";
        r6 = r6.equals(r8);
        if (r6 == 0) goto L_0x0226;
    L_0x0224:
        r6 = 6;
        goto L_0x0227;
    L_0x0226:
        r6 = r15;
    L_0x0227:
        switch(r6) {
            case 0: goto L_0x0323;
            case 1: goto L_0x031c;
            case 2: goto L_0x0315;
            case 3: goto L_0x030e;
            case 4: goto L_0x0307;
            case 5: goto L_0x0300;
            case 6: goto L_0x02f9;
            case 7: goto L_0x02f6;
            case 8: goto L_0x02ef;
            case 9: goto L_0x02ec;
            case 10: goto L_0x02e5;
            case 11: goto L_0x02de;
            case 12: goto L_0x02d7;
            case 13: goto L_0x02d0;
            case 14: goto L_0x02cd;
            case 15: goto L_0x02c6;
            case 16: goto L_0x02c2;
            case 17: goto L_0x02be;
            case 18: goto L_0x02ba;
            case 19: goto L_0x02b2;
            case 20: goto L_0x024e;
            case 21: goto L_0x022c;
            default: goto L_0x022a;
        };
    L_0x022a:
        goto L_0x0329;
    L_0x022c:
        com.constants.Constants.ch = r7;
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getTimeInterval();
        if (r6 == 0) goto L_0x0329;
    L_0x023a:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getTimeInterval();
        r6 = r6.intValue();
        r6 = r6 * 1000;
        com.constants.Constants.ci = r6;
        goto L_0x0329;
    L_0x024e:
        com.managers.e.R = r7;
        com.managers.e.av = r10;
        com.managers.e.au = r9;
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getFrequency();
        if (r6 == 0) goto L_0x0270;
    L_0x0260:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getFrequency();
        r6 = r6.intValue();
        com.constants.Constants.cm = r6;
    L_0x0270:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getStartTime();
        if (r6 == 0) goto L_0x028f;
    L_0x027c:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getStartTime();
        r6 = r6.intValue();
        r6 = r6 * 1000;
        r8 = (long) r6;
        com.constants.Constants.cn = r8;
    L_0x028f:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getTimeInterval();
        if (r6 == 0) goto L_0x02ae;
    L_0x029b:
        r6 = r2.get(r3);
        r6 = (com.gaana.models.SDKConfig.DfpAdCode.DfpConfig) r6;
        r6 = r6.getTimeInterval();
        r6 = r6.intValue();
        r6 = r6 * 1000;
        r8 = (long) r6;
        com.constants.Constants.co = r8;
    L_0x02ae:
        com.constants.Constants.cl = r7;
        goto L_0x0329;
    L_0x02b2:
        com.managers.e.Q = r7;
        com.managers.e.at = r10;
        com.managers.e.as = r9;
        goto L_0x0329;
    L_0x02ba:
        com.managers.e.P = r7;
        goto L_0x0329;
    L_0x02be:
        com.managers.e.O = r7;
        goto L_0x0329;
    L_0x02c2:
        com.constants.Constants.cg = r7;
        goto L_0x0329;
    L_0x02c6:
        com.managers.e.N = r7;
        com.managers.e.ap = r10;
        com.managers.e.ao = r9;
        goto L_0x0329;
    L_0x02cd:
        com.managers.e.L = r7;
        goto L_0x0329;
    L_0x02d0:
        com.managers.e.K = r7;
        com.managers.e.ap = r10;
        com.managers.e.ao = r9;
        goto L_0x0329;
    L_0x02d7:
        com.managers.e.J = r7;
        com.managers.e.ap = r10;
        com.managers.e.ao = r9;
        goto L_0x0329;
    L_0x02de:
        com.managers.e.M = r7;
        com.managers.e.ar = r10;
        com.managers.e.aq = r9;
        goto L_0x0329;
    L_0x02e5:
        com.managers.e.G = r7;
        com.managers.e.am = r9;
        com.managers.e.an = r10;
        goto L_0x0329;
    L_0x02ec:
        com.managers.e.I = r7;
        goto L_0x0329;
    L_0x02ef:
        com.managers.e.F = r7;
        com.managers.e.aj = r9;
        com.managers.e.al = r10;
        goto L_0x0329;
    L_0x02f6:
        com.managers.e.H = r7;
        goto L_0x0329;
    L_0x02f9:
        com.managers.e.E = r7;
        com.managers.e.ai = r9;
        com.managers.e.ak = r10;
        goto L_0x0329;
    L_0x0300:
        com.managers.e.D = r7;
        com.managers.e.ag = r9;
        com.managers.e.ah = r10;
        goto L_0x0329;
    L_0x0307:
        com.managers.e.C = r7;
        com.managers.e.ae = r9;
        com.managers.e.af = r10;
        goto L_0x0329;
    L_0x030e:
        com.managers.e.A = r7;
        com.managers.e.X = r9;
        com.managers.e.ab = r10;
        goto L_0x0329;
    L_0x0315:
        com.managers.e.B = r7;
        com.managers.e.Y = r9;
        com.managers.e.ac = r10;
        goto L_0x0329;
    L_0x031c:
        com.managers.e.z = r7;
        com.managers.e.V = r9;
        com.managers.e.Z = r10;
        goto L_0x0329;
    L_0x0323:
        com.managers.e.y = r7;
        com.managers.e.W = r9;
        com.managers.e.aa = r10;
    L_0x0329:
        r3 = r3 + 1;
        goto L_0x00d8;
    L_0x032d:
        if (r1 == 0) goto L_0x0397;
    L_0x032f:
        r2 = r4;
    L_0x0330:
        r3 = r1.size();
        if (r2 >= r3) goto L_0x0397;
    L_0x0336:
        r3 = r1.get(r2);
        r3 = (com.gaana.models.SDKConfig.DfpAdCode.DfpMediaConfig) r3;
        r6 = r3.getAdTitle();
        r3.getAdCode();
        r7 = r3.getStatus();
        r8 = r3.getAdServer();
        r8.intValue();
        r8 = r3.getMediation();
        r8.intValue();
        if (r7 == 0) goto L_0x0394;
    L_0x0357:
        r7 = r7.intValue();
        if (r7 == 0) goto L_0x0394;
    L_0x035d:
        r7 = r6.hashCode();
        r8 = -1893604140; // 0xffffffff8f21e4d4 float:-7.9819836E-30 double:NaN;
        if (r7 == r8) goto L_0x0376;
    L_0x0366:
        r8 = -1265443681; // 0xffffffffb492dc9f float:-2.7355142E-7 double:NaN;
        if (r7 == r8) goto L_0x036c;
    L_0x036b:
        goto L_0x0380;
    L_0x036c:
        r7 = "foreground_ad";
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x0380;
    L_0x0374:
        r6 = r4;
        goto L_0x0381;
    L_0x0376:
        r7 = "background_ad";
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x0380;
    L_0x037e:
        r6 = r5;
        goto L_0x0381;
    L_0x0380:
        r6 = r15;
    L_0x0381:
        switch(r6) {
            case 0: goto L_0x038d;
            case 1: goto L_0x0385;
            default: goto L_0x0384;
        };
    L_0x0384:
        goto L_0x0394;
    L_0x0385:
        r6 = com.managers.f.v();
        r6.a(r3, r5);
        goto L_0x0394;
    L_0x038d:
        r6 = com.managers.f.v();
        r6.a(r3, r4);
    L_0x0394:
        r2 = r2 + 1;
        goto L_0x0330;
    L_0x0397:
        r1 = r0.a;
        if (r1 == 0) goto L_0x04fc;
    L_0x039b:
        r1 = r4;
    L_0x039c:
        r2 = r0.a;
        r2 = r2.size();
        if (r1 >= r2) goto L_0x04fc;
    L_0x03a4:
        r2 = r0.a;
        r2 = r2.get(r1);
        r2 = (com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig) r2;
        r2 = r2.getAd_title();
        r3 = r0.a;
        r3 = r3.get(r1);
        r3 = (com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig) r3;
        r3 = r3.getAd_code();
        r6 = java.lang.Long.parseLong(r3);
        r3 = r0.a;
        r3 = r3.get(r1);
        r3 = (com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig) r3;
        r3 = r3.getAd_status();
        if (r3 == 0) goto L_0x04f8;
    L_0x03ce:
        r8 = "1";
        r3 = r3.equals(r8);
        if (r3 == 0) goto L_0x04f8;
    L_0x03d6:
        r3 = r2.hashCode();
        switch(r3) {
            case -2039262860: goto L_0x049a;
            case -1974385023: goto L_0x048f;
            case -1939451094: goto L_0x0485;
            case -1914568213: goto L_0x047b;
            case -1042788915: goto L_0x0470;
            case -905810697: goto L_0x0466;
            case -388329587: goto L_0x045c;
            case -234302088: goto L_0x0451;
            case -161128530: goto L_0x0447;
            case 125052862: goto L_0x043c;
            case 132321725: goto L_0x0430;
            case 238985816: goto L_0x0424;
            case 534406381: goto L_0x0419;
            case 1055811561: goto L_0x040d;
            case 1071145194: goto L_0x0402;
            case 1222669524: goto L_0x03f6;
            case 1648780285: goto L_0x03ea;
            case 1850829983: goto L_0x03df;
            default: goto L_0x03dd;
        };
    L_0x03dd:
        goto L_0x04a4;
    L_0x03df:
        r3 = "FOREGROUND_AD";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x03e7:
        r2 = r13;
        goto L_0x04a5;
    L_0x03ea:
        r3 = "RADIO_MIRCHI_320X100";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x03f2:
        r2 = 11;
        goto L_0x04a5;
    L_0x03f6:
        r3 = "BACKGROUND_AD";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x03fe:
        r2 = 17;
        goto L_0x04a5;
    L_0x0402:
        r3 = "FAVOURITES";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x040a:
        r2 = r12;
        goto L_0x04a5;
    L_0x040d:
        r3 = "DISCOVER";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0415:
        r2 = 14;
        goto L_0x04a5;
    L_0x0419:
        r3 = "LEFT_DRAWER_NATIVE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0421:
        r2 = r14;
        goto L_0x04a5;
    L_0x0424:
        r3 = "Gaana_AOS_ROS_CTN_NAT_250X182";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x042c:
        r2 = 10;
        goto L_0x04a5;
    L_0x0430:
        r3 = "DETAIL_PAGE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0438:
        r2 = 8;
        goto L_0x04a5;
    L_0x043c:
        r3 = "BOTTOM_LAYOUT";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0444:
        r2 = r5;
        goto L_0x04a5;
    L_0x0447:
        r3 = "DOWNLOAD_NATIVE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x044f:
        r2 = 2;
        goto L_0x04a5;
    L_0x0451:
        r3 = "Gaana_AOS_HP_CTN_NAT_304X98";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0459:
        r2 = 9;
        goto L_0x04a5;
    L_0x045c:
        r3 = "PROFILE_NATIVE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0464:
        r2 = 4;
        goto L_0x04a5;
    L_0x0466:
        r3 = "PLAYLIST_LISTING";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x046e:
        r2 = r11;
        goto L_0x04a5;
    L_0x0470:
        r3 = "ARTIST_SECTION";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0478:
        r2 = 15;
        goto L_0x04a5;
    L_0x047b:
        r3 = "NOTIFICATION_NATIVE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0483:
        r2 = 6;
        goto L_0x04a5;
    L_0x0485:
        r3 = "POPUP_NATIVE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x048d:
        r2 = 5;
        goto L_0x04a5;
    L_0x048f:
        r3 = "GAANA_RADIO_320X100";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x0497:
        r2 = 12;
        goto L_0x04a5;
    L_0x049a:
        r3 = "TOP_LAYOUT";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x04a4;
    L_0x04a2:
        r2 = r4;
        goto L_0x04a5;
    L_0x04a4:
        r2 = r15;
    L_0x04a5:
        switch(r2) {
            case 0: goto L_0x04f6;
            case 1: goto L_0x04f3;
            case 2: goto L_0x04f0;
            case 3: goto L_0x04ed;
            case 4: goto L_0x04ea;
            case 5: goto L_0x04e7;
            case 6: goto L_0x04e4;
            case 7: goto L_0x04e1;
            case 8: goto L_0x04de;
            case 9: goto L_0x04db;
            case 10: goto L_0x04d8;
            case 11: goto L_0x04d5;
            case 12: goto L_0x04d2;
            case 13: goto L_0x04c2;
            case 14: goto L_0x04bf;
            case 15: goto L_0x04bc;
            case 16: goto L_0x04b9;
            case 17: goto L_0x04a9;
            default: goto L_0x04a8;
        };
    L_0x04a8:
        goto L_0x04f8;
    L_0x04a9:
        r2 = com.managers.f.v();
        r3 = r0.a;
        r3 = r3.get(r1);
        r3 = (com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig) r3;
        r2.b(r3);
        goto L_0x04f8;
    L_0x04b9:
        com.managers.e.k = r6;
        goto L_0x04f8;
    L_0x04bc:
        com.managers.e.j = r6;
        goto L_0x04f8;
    L_0x04bf:
        com.managers.e.i = r6;
        goto L_0x04f8;
    L_0x04c2:
        r2 = com.managers.f.v();
        r3 = r0.a;
        r3 = r3.get(r1);
        r3 = (com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig) r3;
        r2.a(r3);
        goto L_0x04f8;
    L_0x04d2:
        com.managers.e.h = r6;
        goto L_0x04f8;
    L_0x04d5:
        com.managers.e.g = r6;
        goto L_0x04f8;
    L_0x04d8:
        com.managers.e.f = r6;
        goto L_0x04f8;
    L_0x04db:
        com.managers.e.e = r6;
        goto L_0x04f8;
    L_0x04de:
        com.managers.e.d = r6;
        goto L_0x04f8;
    L_0x04e1:
        com.managers.e.c = r6;
        goto L_0x04f8;
    L_0x04e4:
        com.managers.e.u = r6;
        goto L_0x04f8;
    L_0x04e7:
        com.managers.e.t = r6;
        goto L_0x04f8;
    L_0x04ea:
        com.managers.e.s = r6;
        goto L_0x04f8;
    L_0x04ed:
        com.managers.e.r = r6;
        goto L_0x04f8;
    L_0x04f0:
        com.managers.e.v = r6;
        goto L_0x04f8;
    L_0x04f3:
        com.managers.e.x = r6;
        goto L_0x04f8;
    L_0x04f6:
        com.managers.e.w = r6;
    L_0x04f8:
        r1 = r1 + 1;
        goto L_0x039c;
    L_0x04fc:
        r0.f = r5;
        r1 = b();
        r1.d();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.ColombiaManager.a(com.gaana.models.SDKConfig):void");
    }

    public boolean e() {
        return this.f;
    }

    public void a(String str) {
        if (str.equals("F")) {
            this.c = GENDER.FEMALE;
        } else {
            this.c = GENDER.MALE;
        }
    }

    public void b(String str) {
        try {
            this.d = new SimpleDateFormat("dd/MM/yyyy").parse(str);
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void a(b bVar) {
        f.v().a(bVar);
        e.a().a(bVar);
    }

    public void a(int i, Context context, int i2, long j, View view, String str, a aVar, String str2) {
        a(i, context, i2, VIEW_SIZE.SCROLL_GENERIC.getNumVal(), j, view, false, str, aVar, str2);
    }

    /* JADX WARNING: Missing block: B:47:0x0126, code skipped:
            return;
     */
    public void a(int r15, android.content.Context r16, int r17, int r18, long r19, android.view.View r21, boolean r22, java.lang.String r23, com.managers.ColombiaManager.a r24, java.lang.String r25) {
        /*
        r14 = this;
        r0 = r14;
        r4 = r16;
        r5 = r17;
        r1 = com.services.d.a();
        r2 = "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME";
        r3 = 0;
        r6 = 0;
        r1 = r1.b(r6, r2, r3);
        r8 = com.services.d.a();
        r9 = "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME";
        r8 = r8.b(r6, r9, r3);
        r10 = java.lang.System.currentTimeMillis();
        r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1));
        if (r3 == 0) goto L_0x002f;
    L_0x0024:
        r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r3 == 0) goto L_0x002f;
    L_0x0028:
        r6 = r10 - r1;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 >= 0) goto L_0x002f;
    L_0x002e:
        return;
    L_0x002f:
        r1 = r0.e;
        if (r1 == 0) goto L_0x0126;
    L_0x0033:
        if (r4 != 0) goto L_0x0037;
    L_0x0035:
        goto L_0x0126;
    L_0x0037:
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r1 = r1.getColombiaSdkInit();
        if (r1 != 0) goto L_0x0042;
    L_0x0041:
        return;
    L_0x0042:
        r2 = new com.til.colombia.android.service.ColombiaAdRequest$Builder;
        r1 = r0.e;
        r2.<init>(r1);
        r1 = r0.c;
        if (r1 == 0) goto L_0x0052;
    L_0x004d:
        r1 = r0.c;
        r2.addGender(r1);
    L_0x0052:
        r1 = r0.d;
        if (r1 == 0) goto L_0x005b;
    L_0x0056:
        r1 = r0.d;
        r2.addBirthDay(r1);
    L_0x005b:
        r1 = 4;
        if (r5 != r1) goto L_0x0076;
    L_0x005e:
        r1 = com.services.d.a();
        r1 = r1.b();
        r3 = r16.getResources();
        r6 = 2131165641; // 0x7f0701c9 float:1.7945505E38 double:1.052935729E-314;
        r3 = r3.getDimensionPixelSize(r6);
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x0076:
        r1 = 25;
        if (r5 != r1) goto L_0x0094;
    L_0x007a:
        r1 = r16.getResources();
        r3 = 2131165539; // 0x7f070163 float:1.7945298E38 double:1.0529356784E-314;
        r1 = r1.getDimensionPixelSize(r3);
        r3 = r16.getResources();
        r6 = 2131165538; // 0x7f070162 float:1.7945296E38 double:1.052935678E-314;
        r3 = r3.getDimensionPixelSize(r6);
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x0094:
        r1 = 27;
        r3 = 100;
        if (r5 == r1) goto L_0x00fb;
    L_0x009a:
        r1 = 28;
        if (r5 != r1) goto L_0x009f;
    L_0x009e:
        goto L_0x00fb;
    L_0x009f:
        r1 = 29;
        r6 = 2131165268; // 0x7f070054 float:1.7944748E38 double:1.0529355445E-314;
        r7 = 2;
        if (r5 != r1) goto L_0x00c8;
    L_0x00a7:
        r1 = com.services.d.a();
        r1 = r1.b();
        r3 = r16.getResources();
        r3 = r3.getDimensionPixelSize(r6);
        r7 = r7 * r3;
        r1 = r1 - r7;
        r3 = r16.getResources();
        r6 = 2131165699; // 0x7f070203 float:1.7945622E38 double:1.0529357575E-314;
        r3 = r3.getDimensionPixelSize(r6);
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x00c8:
        r1 = 30;
        if (r5 != r1) goto L_0x00e6;
    L_0x00cc:
        r1 = com.services.d.a();
        r1 = r1.b();
        r3 = r16.getResources();
        r3 = r3.getDimensionPixelSize(r6);
        r7 = r7 * r3;
        r1 = r1 - r7;
        r3 = r0.b(r4);
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x00e6:
        r1 = 7;
        if (r5 != r1) goto L_0x00f1;
    L_0x00e9:
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r3 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x00f1:
        r1 = 8;
        if (r5 != r1) goto L_0x0100;
    L_0x00f5:
        r1 = 320; // 0x140 float:4.48E-43 double:1.58E-321;
        r2.addAdSize(r1, r3);
        goto L_0x0100;
    L_0x00fb:
        r1 = 728; // 0x2d8 float:1.02E-42 double:3.597E-321;
        r2.addAdSize(r1, r3);
    L_0x0100:
        r1 = r0.g;
        if (r1 == 0) goto L_0x010f;
    L_0x0104:
        r1 = r0.g;
        r1 = r1.a;
        r3 = r0.g;
        r3 = r3.b;
        r2.addCustomAudience(r1, r3);
    L_0x010f:
        r1 = com.managers.e.a();
        r3 = r15;
        r6 = r18;
        r7 = r19;
        r9 = r21;
        r10 = r22;
        r11 = r23;
        r12 = r24;
        r13 = r25;
        r1.a(r2, r3, r4, r5, r6, r7, r9, r10, r11, r12, r13);
        return;
    L_0x0126:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.ColombiaManager.a(int, android.content.Context, int, int, long, android.view.View, boolean, java.lang.String, com.managers.ColombiaManager$a, java.lang.String):void");
    }

    public void a(Context context, long j, final a aVar) {
        Builder f = b().f();
        if (f != null) {
            f.enabledGoogleAdFormats(new DFP_ITEM_TYPE[]{DFP_ITEM_TYPE.APP});
            f.addAdSize(1, 1);
            try {
                Colombia.getNativeAds(f.addRequest(Long.valueOf(j), 0, CBConstant.DEFAULT_VALUE, new AdListener() {
                    public void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
                        Item item = (Item) itemResponse.getPaidItems().get(0);
                        if (aVar != null) {
                            aVar.onItemLoaded(item);
                        }
                    }

                    public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
                        if (aVar != null) {
                            aVar.onItemRequestFailed(exception);
                        }
                    }
                }).addReferer("https://api.gaana.com/").addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).enableRecordManualImpression(true).downloadImageBitmap(true).build());
            } catch (ColombiaException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int b(Context context) {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public Builder f() {
        return this.e != null ? new Builder(this.e) : null;
    }

    public void a(Context context, PlayerTrack playerTrack) {
        long b = d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        Builder builder = null;
        if (!(b == 0 || b2 == 0)) {
            long j = currentTimeMillis - b;
            if (j < b2) {
                if (j >= b2 - 180000) {
                    f.v().G();
                }
                f.v().a(builder, playerTrack, context);
            }
        }
        if (!(this.e == null || context == null || !GaanaApplication.getInstance().getColombiaSdkInit())) {
            builder = new Builder(this.e);
            if (this.c != null) {
                builder.addGender(this.c);
            }
            if (this.d != null) {
                builder.addBirthDay(this.d);
            }
        }
        f.v().a(builder, playerTrack, context);
    }

    public void g() {
        if (this.e != null) {
            this.e.destroy();
        }
    }
}

package com.helpshift.configuration.a;

import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.b;
import com.helpshift.common.domain.network.d;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.l;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.network.h;
import com.helpshift.common.platform.o;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.dto.RootApiConfig;
import com.helpshift.configuration.dto.RootApiConfig.EnableContactUs;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class a extends Observable {
    private final e a;
    private final p b;
    private final h c;
    private final o d;

    public a(e eVar, p pVar) {
        this.a = eVar;
        this.b = pVar;
        this.c = pVar.j();
        this.d = pVar.m();
    }

    public void a() {
        String str = "/config/";
        g c = new f(new b(new l(new d(str, this.a, this.b), this.b), this.b, str)).c(new HashMap());
        if (c.b == null) {
            com.helpshift.util.l.a("Helpshift_SDKConfigDM", "SDK config data fetched but nothing to update.");
            return;
        }
        com.helpshift.util.l.a("Helpshift_SDKConfigDM", "SDK config data updated successfully");
        a(this.c.b(c.b));
    }

    public void a(com.helpshift.configuration.b.b bVar) {
        this.d.a("requireNameAndEmail", Boolean.valueOf(bVar.a));
        this.d.a("profileFormEnable", Boolean.valueOf(bVar.b));
        this.d.a("showAgentName", Boolean.valueOf(bVar.c));
        this.d.a("customerSatisfactionSurvey", Boolean.valueOf(bVar.d));
        this.d.a("disableInAppConversation", Boolean.valueOf(bVar.e));
        this.d.a("disableHelpshiftBrandingAgent", Boolean.valueOf(bVar.f));
        this.d.a("debugLogLimit", Integer.valueOf(bVar.g));
        this.d.a("breadcrumbLimit", Integer.valueOf(bVar.h));
        this.d.a("reviewUrl", bVar.i);
        com.helpshift.configuration.b.a aVar = bVar.j;
        if (aVar == null) {
            aVar = new com.helpshift.configuration.b.a(false, 0, null);
        }
        this.d.a("periodicReviewEnabled", Boolean.valueOf(aVar.a));
        this.d.a("periodicReviewInterval", Integer.valueOf(aVar.b));
        this.d.a("periodicReviewType", aVar.c);
        setChanged();
        notifyObservers();
    }

    public boolean a(java.lang.String r4) {
        /*
        r3 = this;
        r0 = r4.hashCode();
        r1 = 0;
        r2 = 1;
        switch(r0) {
            case -591814160: goto L_0x0032;
            case -338380156: goto L_0x0028;
            case 1262906910: goto L_0x001e;
            case 1952413875: goto L_0x0014;
            case 2068322546: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x003c;
    L_0x000a:
        r0 = "showConversationResolutionQuestion";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x0012:
        r0 = 4;
        goto L_0x003d;
    L_0x0014:
        r0 = "showAgentName";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x001c:
        r0 = r2;
        goto L_0x003d;
    L_0x001e:
        r0 = "defaultFallbackLanguageEnable";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x0026:
        r0 = 3;
        goto L_0x003d;
    L_0x0028:
        r0 = "enableInAppNotification";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x0030:
        r0 = 2;
        goto L_0x003d;
    L_0x0032:
        r0 = "profileFormEnable";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x003a:
        r0 = r1;
        goto L_0x003d;
    L_0x003c:
        r0 = -1;
    L_0x003d:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0041;
            case 2: goto L_0x0041;
            case 3: goto L_0x0041;
            case 4: goto L_0x0041;
            default: goto L_0x0040;
        };
    L_0x0040:
        goto L_0x0042;
    L_0x0041:
        r1 = r2;
    L_0x0042:
        r0 = r3.d;
        r1 = java.lang.Boolean.valueOf(r1);
        r4 = r0.b(r4, r1);
        r4 = r4.booleanValue();
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.configuration.a.a.a(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029  */
    public java.lang.Integer b(java.lang.String r3) {
        /*
        r2 = this;
        r0 = r3.hashCode();
        r1 = -71624118; // 0xfffffffffbbb1a4a float:-1.9429854E36 double:NaN;
        if (r0 == r1) goto L_0x0019;
    L_0x0009:
        r1 = 1384494456; // 0x5285b578 float:2.87137595E11 double:6.840311476E-315;
        if (r0 == r1) goto L_0x000f;
    L_0x000e:
        goto L_0x0023;
    L_0x000f:
        r0 = "breadcrumbLimit";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0023;
    L_0x0017:
        r0 = 1;
        goto L_0x0024;
    L_0x0019:
        r0 = "debugLogLimit";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0023;
    L_0x0021:
        r0 = 0;
        goto L_0x0024;
    L_0x0023:
        r0 = -1;
    L_0x0024:
        switch(r0) {
            case 0: goto L_0x0029;
            case 1: goto L_0x0029;
            default: goto L_0x0027;
        };
    L_0x0027:
        r0 = 0;
        goto L_0x002f;
    L_0x0029:
        r0 = 100;
        r0 = java.lang.Integer.valueOf(r0);
    L_0x002f:
        r1 = r2.d;
        r3 = r1.b(r3, r0);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.configuration.a.a.b(java.lang.String):java.lang.Integer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    public java.lang.String c(java.lang.String r3) {
        /*
        r2 = this;
        r0 = r3.hashCode();
        r1 = -340534862; // 0xffffffffebb3d9b2 float:-4.348515E26 double:NaN;
        if (r0 == r1) goto L_0x0028;
    L_0x0009:
        r1 = 493025015; // 0x1d62f6f7 float:3.0038529E-21 double:2.435867225E-315;
        if (r0 == r1) goto L_0x001e;
    L_0x000e:
        r1 = 1948062356; // 0x741d1294 float:4.9778285E31 double:9.62470686E-315;
        if (r0 == r1) goto L_0x0014;
    L_0x0013:
        goto L_0x0032;
    L_0x0014:
        r0 = "sdkType";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = 2;
        goto L_0x0033;
    L_0x001e:
        r0 = "reviewUrl";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r0 = 0;
        goto L_0x0033;
    L_0x0028:
        r0 = "sdkLanguage";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r0 = 1;
        goto L_0x0033;
    L_0x0032:
        r0 = -1;
    L_0x0033:
        switch(r0) {
            case 0: goto L_0x003b;
            case 1: goto L_0x003b;
            case 2: goto L_0x0038;
            default: goto L_0x0036;
        };
    L_0x0036:
        r0 = 0;
        goto L_0x003d;
    L_0x0038:
        r0 = "android";
        goto L_0x003d;
    L_0x003b:
        r0 = "";
    L_0x003d:
        r1 = r2.d;
        r3 = r1.b(r3, r0);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.configuration.a.a.c(java.lang.String):java.lang.String");
    }

    public com.helpshift.configuration.b.a b() {
        return new com.helpshift.configuration.b.a(this.d.b("periodicReviewEnabled", Boolean.valueOf(false)).booleanValue(), this.d.b("periodicReviewInterval", Integer.valueOf(0)).intValue(), this.d.b("periodicReviewType", ""));
    }

    public void a(Map<String, Object> map) {
        a(new com.helpshift.configuration.dto.a.a().a(map).a());
    }

    public void a(com.helpshift.configuration.dto.a aVar) {
        if (aVar.a != null) {
            this.d.a("enableInAppNotification", aVar.a);
        }
        if (aVar.c != null) {
            this.d.a("inboxPollingEnable", aVar.c);
        }
        if (aVar.b != null) {
            this.d.a("defaultFallbackLanguageEnable", aVar.b);
        }
        if (aVar.d != null) {
            this.d.a("notificationMute", aVar.d);
        }
        if (aVar.f != null) {
            this.d.a("disableAnimations", aVar.f);
        }
        if (aVar.e != null) {
            this.d.a("disableHelpshiftBranding", aVar.e);
        }
        if (aVar.g != null) {
            this.d.a("disableErrorLogging", aVar.g);
        }
        if (aVar.j != null) {
            this.d.a("notificationSoundId", aVar.j);
        }
        if (aVar.h != null) {
            this.d.a("notificationIconId", aVar.h);
        }
        if (aVar.i != null) {
            this.d.a("notificationLargeIconId", aVar.i);
        }
        if (aVar.l != null) {
            this.d.a("sdkType", aVar.l);
        }
        if (aVar.m != null) {
            this.d.a("pluginVersion", aVar.m);
        }
        if (aVar.n != null) {
            this.d.a("runtimeVersion", aVar.n);
        }
        this.d.a("supportNotificationChannelId", aVar.o);
        this.d.a("fontPath", aVar.k);
    }

    public boolean c() {
        if (this.d.b("disableHelpshiftBranding", Boolean.valueOf(false)).booleanValue() || this.d.b("disableHelpshiftBrandingAgent", Boolean.valueOf(false)).booleanValue()) {
            return true;
        }
        return false;
    }

    public void b(Map<String, Object> map) {
        a(new com.helpshift.configuration.dto.RootApiConfig.a().a(map).a());
    }

    public void a(RootApiConfig rootApiConfig) {
        if (rootApiConfig.d != null) {
            this.d.a("fullPrivacy", rootApiConfig.d);
        }
        if (rootApiConfig.c != null) {
            this.d.a("hideNameAndEmail", rootApiConfig.c);
        }
        if (rootApiConfig.b != null) {
            this.d.a("requireEmail", rootApiConfig.b);
        }
        if (rootApiConfig.e != null) {
            this.d.a("showSearchOnNewConversation", rootApiConfig.e);
        }
        if (rootApiConfig.a != null) {
            this.d.a("gotoConversationAfterContactUs", rootApiConfig.a);
        }
        if (rootApiConfig.f != null) {
            this.d.a("showConversationResolutionQuestion", rootApiConfig.f);
        }
        if (rootApiConfig.g != null) {
            this.d.a("enableContactUs", Integer.valueOf(rootApiConfig.g.getValue()));
        }
        if (rootApiConfig.i != null) {
            this.d.a("showConversationInfoScreen", rootApiConfig.i);
        }
        if (rootApiConfig.j != null) {
            this.d.a("enableTypingIndicator", rootApiConfig.j);
        }
        this.d.a("conversationPrefillText", rootApiConfig.h);
    }

    public EnableContactUs d() {
        return EnableContactUs.fromInt(this.d.b("enableContactUs", Integer.valueOf(0)).intValue());
    }

    public void a(boolean z) {
        this.d.a("app_reviewed", Boolean.valueOf(z));
    }

    public boolean e() {
        return a("fullPrivacy") || !((a("requireNameAndEmail") && a("hideNameAndEmail")) || a("profileFormEnable"));
    }

    public int f() {
        return this.b.v();
    }
}

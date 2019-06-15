package com.helpshift.configuration.dto;

import java.util.Map;

public class a {
    public final Boolean a;
    public final Boolean b;
    public final Boolean c;
    public final Boolean d;
    public final Boolean e;
    public final Boolean f;
    public final Boolean g;
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;

    public static final class a {
        private Boolean a;
        private Boolean b;
        private Boolean c;
        private Boolean d;
        private Boolean e;
        private Boolean f;
        private Boolean g;
        private Integer h;
        private Integer i;
        private Integer j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;

        public a a(Map<String, Object> map) {
            if (map.get("enableInAppNotification") instanceof Boolean) {
                this.a = (Boolean) map.get("enableInAppNotification");
            }
            if (map.get("enableDefaultFallbackLanguage") instanceof Boolean) {
                this.b = (Boolean) map.get("enableDefaultFallbackLanguage");
            }
            if (map.get("enableInboxPolling") instanceof Boolean) {
                this.c = (Boolean) map.get("enableInboxPolling");
            }
            if (map.get("enableNotificationMute") instanceof Boolean) {
                this.d = (Boolean) map.get("enableNotificationMute");
            }
            if (map.get("disableHelpshiftBranding") instanceof Boolean) {
                this.e = (Boolean) map.get("disableHelpshiftBranding");
            }
            if (map.get("disableErrorLogging") instanceof Boolean) {
                this.g = (Boolean) map.get("disableErrorLogging");
            }
            if (map.get("disableAnimations") instanceof Boolean) {
                this.f = (Boolean) map.get("disableAnimations");
            }
            if (map.get("notificationIcon") instanceof Integer) {
                this.h = (Integer) map.get("notificationIcon");
            }
            if (map.get("largeNotificationIcon") instanceof Integer) {
                this.i = (Integer) map.get("largeNotificationIcon");
            }
            if (map.get("notificationSound") instanceof Integer) {
                this.j = (Integer) map.get("notificationSound");
            }
            if (map.get("font") instanceof String) {
                this.k = (String) map.get("font");
            }
            if (map.get("sdkType") instanceof String) {
                this.l = (String) map.get("sdkType");
            }
            if (map.get("pluginVersion") instanceof String) {
                this.m = (String) map.get("pluginVersion");
            }
            if (map.get("runtimeVersion") instanceof String) {
                this.n = (String) map.get("runtimeVersion");
            }
            if (map.get("supportNotificationChannelId") instanceof String) {
                this.o = (String) map.get("supportNotificationChannelId");
            }
            return this;
        }

        public a a() {
            return new a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
        }
    }

    public a(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Integer num, Integer num2, Integer num3, String str, String str2, String str3, String str4, String str5) {
        this.a = bool;
        this.d = bool4;
        this.e = bool5;
        this.f = bool6;
        this.g = bool7;
        this.h = num;
        this.i = num2;
        this.j = num3;
        this.b = bool2;
        this.c = bool3;
        this.k = str;
        this.l = str2;
        this.m = str3;
        this.n = str4;
        this.o = str5;
    }
}

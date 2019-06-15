package com.gaana.cardoption;

import android.support.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Keep
public class AssetsHelper {

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface BRANDING {
        public static final String BRANDING_FOOTER = "trust-footer-v1";
        public static final String BRANDING_WALLET_FOOTER = "footer_wallet_branding";
        public static final String CITRUS_LOGO = "citrus-logo";
        public static final String WALLET_LOGO = "citrus-cash-logo";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface CARD {
        public static final String AMEX = "AMEX";
        public static final String CIRRUS = "CIRRUS";
        public static final String DEFAULT = "DEFAULT";
        public static final String DINERCLUB = "DINER";
        public static final String DISCOVER = "DISCOVER";
        public static final String JCB = "JCB";
        public static final String MAESTRO = "MTRO";
        public static final String MCRD = "MCRD";
        public static final String RUPAY = "RPAY";
        public static final String UNKNOWN = "UNKNOWN";
        public static final String VISA = "VISA";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface LARGEBANK {
        public static final String AXIS = "CID002";
        public static final String CITI = "CID003";
        public static final String DEFAULT = "CIDOOO";
        public static final String HDFC = "CID010";
        public static final String ICICI = "CID001";
        public static final String KOTAK = "CID033";
        public static final String SBI = "CID005";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface LARGECARD {
        public static final String MCRD = "MCRD";
        public static final String VISA = "VISA";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface MASTERPASS {
        public static final String BUTTON = "MPASSW";
        public static final String COLORBG = "Masterpass_color";
        public static final String GRAYBG = "Masterpass_bw";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface SDK_TYPE {
        public static final String CORE_SDK = "CORE_SDK";
        public static final String FLASH_SDK = "FLASH_SDK";
        public static final String PLUG_N_PLAY = "PLUG_N_PLAY";
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenDensity {
        public static final String HDPI = "HDPI";
        public static final String XHDPI = "XHDPI";
        public static final String XXHDPI = "XXHDPI";
    }

    public static java.lang.String getCard(java.lang.String r1) {
        /*
        r0 = r1.hashCode();
        switch(r0) {
            case -891831603: goto L_0x0059;
            case 73257: goto L_0x004e;
            case 2012639: goto L_0x0044;
            case 2521846: goto L_0x003a;
            case 2634817: goto L_0x0030;
            case 1055811561: goto L_0x0026;
            case 1545480463: goto L_0x001c;
            case 1988094532: goto L_0x0012;
            case 2016591933: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0063;
    L_0x0008:
        r0 = "DINERS";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0010:
        r1 = 5;
        goto L_0x0064;
    L_0x0012:
        r0 = "CIRRUS";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x001a:
        r1 = 7;
        goto L_0x0064;
    L_0x001c:
        r0 = "MAESTRO";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0024:
        r1 = 4;
        goto L_0x0064;
    L_0x0026:
        r0 = "DISCOVER";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x002e:
        r1 = 2;
        goto L_0x0064;
    L_0x0030:
        r0 = "VISA";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0038:
        r1 = 6;
        goto L_0x0064;
    L_0x003a:
        r0 = "RPAY";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0042:
        r1 = 3;
        goto L_0x0064;
    L_0x0044:
        r0 = "AMEX";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x004c:
        r1 = 0;
        goto L_0x0064;
    L_0x004e:
        r0 = "JCB";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0056:
        r1 = 8;
        goto L_0x0064;
    L_0x0059:
        r0 = "MASTER_CARD";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0063;
    L_0x0061:
        r1 = 1;
        goto L_0x0064;
    L_0x0063:
        r1 = -1;
    L_0x0064:
        switch(r1) {
            case 0: goto L_0x0082;
            case 1: goto L_0x007f;
            case 2: goto L_0x007c;
            case 3: goto L_0x0079;
            case 4: goto L_0x0076;
            case 5: goto L_0x0073;
            case 6: goto L_0x0070;
            case 7: goto L_0x006d;
            case 8: goto L_0x006a;
            default: goto L_0x0067;
        };
    L_0x0067:
        r1 = "UNKNOWN";
        return r1;
    L_0x006a:
        r1 = "JCB";
        return r1;
    L_0x006d:
        r1 = "CIRRUS";
        return r1;
    L_0x0070:
        r1 = "VISA";
        return r1;
    L_0x0073:
        r1 = "DINER";
        return r1;
    L_0x0076:
        r1 = "MTRO";
        return r1;
    L_0x0079:
        r1 = "RPAY";
        return r1;
    L_0x007c:
        r1 = "DISCOVER";
        return r1;
    L_0x007f:
        r1 = "MCRD";
        return r1;
    L_0x0082:
        r1 = "AMEX";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.cardoption.AssetsHelper.getCard(java.lang.String):java.lang.String");
    }
}

package com.helpshift.enums;

public enum ACTION_TYPE {
    OPEN_DEEP_LINK,
    SHOW_FAQS,
    SHOW_FAQ_SECTION,
    SHOW_CONVERSATION,
    SHOW_SINGLE_FAQ,
    SHOW_ALERT_TO_RATE_APP,
    LAUNCH_APP,
    SHOW_INBOX;

    public static com.helpshift.enums.ACTION_TYPE getEnum(java.lang.String r1) {
        /*
        r0 = r1.hashCode();
        switch(r0) {
            case 49: goto L_0x004e;
            case 50: goto L_0x0044;
            case 51: goto L_0x003a;
            case 52: goto L_0x0030;
            case 53: goto L_0x0026;
            case 54: goto L_0x001c;
            case 55: goto L_0x0012;
            case 56: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0058;
    L_0x0008:
        r0 = "8";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0010:
        r1 = 7;
        goto L_0x0059;
    L_0x0012:
        r0 = "7";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x001a:
        r1 = 6;
        goto L_0x0059;
    L_0x001c:
        r0 = "6";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0024:
        r1 = 5;
        goto L_0x0059;
    L_0x0026:
        r0 = "5";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x002e:
        r1 = 4;
        goto L_0x0059;
    L_0x0030:
        r0 = "4";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0038:
        r1 = 3;
        goto L_0x0059;
    L_0x003a:
        r0 = "3";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0042:
        r1 = 2;
        goto L_0x0059;
    L_0x0044:
        r0 = "2";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x004c:
        r1 = 1;
        goto L_0x0059;
    L_0x004e:
        r0 = "1";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0056:
        r1 = 0;
        goto L_0x0059;
    L_0x0058:
        r1 = -1;
    L_0x0059:
        switch(r1) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0071;
            case 2: goto L_0x006e;
            case 3: goto L_0x006b;
            case 4: goto L_0x0068;
            case 5: goto L_0x0065;
            case 6: goto L_0x0062;
            case 7: goto L_0x005f;
            default: goto L_0x005c;
        };
    L_0x005c:
        r1 = LAUNCH_APP;
        return r1;
    L_0x005f:
        r1 = SHOW_INBOX;
        return r1;
    L_0x0062:
        r1 = LAUNCH_APP;
        return r1;
    L_0x0065:
        r1 = SHOW_ALERT_TO_RATE_APP;
        return r1;
    L_0x0068:
        r1 = SHOW_SINGLE_FAQ;
        return r1;
    L_0x006b:
        r1 = SHOW_CONVERSATION;
        return r1;
    L_0x006e:
        r1 = SHOW_FAQ_SECTION;
        return r1;
    L_0x0071:
        r1 = SHOW_FAQS;
        return r1;
    L_0x0074:
        r1 = OPEN_DEEP_LINK;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.enums.ACTION_TYPE.getEnum(java.lang.String):com.helpshift.enums.ACTION_TYPE");
    }

    public static ACTION_TYPE getEnum(int i) {
        switch (i) {
            case 1:
                return OPEN_DEEP_LINK;
            case 2:
                return SHOW_FAQS;
            case 3:
                return SHOW_FAQ_SECTION;
            case 4:
                return SHOW_CONVERSATION;
            case 5:
                return SHOW_SINGLE_FAQ;
            case 6:
                return SHOW_ALERT_TO_RATE_APP;
            case 7:
                return LAUNCH_APP;
            case 8:
                return SHOW_INBOX;
            default:
                return LAUNCH_APP;
        }
    }
}

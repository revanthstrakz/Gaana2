package com.simpl.android.fingerprint.a;

import java.util.EnumSet;

enum k {
    DISABLE_APPS("SIMPL-apps"),
    DISABLE_IP_ADDRESS("SIMPL-ip_add"),
    DISABLE_WIFI("SIMPL-wifi"),
    DISABLE_ACCOUNTS("SIMPL-acc"),
    DISABLE_DEVICE_ID("SIMPL-device_id"),
    DISABLE_LOCATION("SIMPL-loc"),
    DISABLE_BATTERY("SIMPL-bat"),
    DISABLE_BLUETOOTH("SIMPL-blu");
    
    String i;

    private k(String str) {
        this.i = str;
    }

    public static EnumSet<k> a(String... strArr) {
        EnumSet noneOf = EnumSet.noneOf(k.class);
        for (Object obj : strArr) {
            for (k kVar : values()) {
                if (kVar.i.equals(obj)) {
                    noneOf.add(kVar);
                }
            }
        }
        return noneOf;
    }
}

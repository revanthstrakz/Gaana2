package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class d {
    private Map<String, Boolean> a;

    public d(Map<String, Boolean> map) {
        this.a = map;
    }

    public final Map<String, String> a(String str, boolean z) {
        HashMap hashMap = new HashMap();
        String str2 = null;
        try {
            if (((Boolean) this.a.get("GPID")).booleanValue()) {
                c.a();
                a f = c.f();
                if (f != null) {
                    String str3 = f.a;
                    if (str3 != null) {
                        if (z) {
                            try {
                                str2 = a(str3, str);
                            } catch (Exception unused) {
                                str2 = str3;
                            }
                        } else {
                            str2 = str3;
                        }
                        hashMap.put("GPID", str2);
                    } else {
                        str2 = str3;
                    }
                }
            }
            a(str, z, hashMap, str2);
        } catch (Exception unused2) {
            d.class.getSimpleName();
            a(str, z, hashMap, str2);
            return hashMap;
        }
        return hashMap;
    }

    private void a(String str, boolean z, Map<String, String> map, String str2) {
        try {
            if (((Boolean) this.a.get("UM5")).booleanValue() && str2 == null) {
                c.a();
                c.a();
                Object a = c.a(c.e(), "MD5");
                if (z) {
                    a = a((String) a, str);
                }
                map.put("UM5", a);
            }
            if (((Boolean) this.a.get("O1")).booleanValue() && str2 == null) {
                c.a();
                c.a();
                Object a2 = c.a(c.e(), "SHA-1");
                if (z) {
                    a2 = a((String) a2, str);
                }
                map.put("O1", a2);
            }
        } catch (Exception unused) {
            d.class.getSimpleName();
        }
    }

    private static String a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, 2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}

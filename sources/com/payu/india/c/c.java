package com.payu.india.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import com.payu.india.Model.PostData;
import com.payu.india.e.d;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

public class c {
    public static String a;
    public static Set<String> b = new HashSet();
    private static String c;

    static {
        b.add("504435");
        b.add("504645");
        b.add("504775");
        b.add("504809");
        b.add("504993");
        b.add("600206");
        b.add("603845");
        b.add("622018");
        b.add("504774");
    }

    public Boolean a(String str) {
        if (str.length() < 12) {
            return Boolean.valueOf(false);
        }
        if (c(str).contentEquals("RUPAY") && str.length() == 16) {
            return b(str);
        }
        if (c(str).contentEquals("VISA") && str.length() == 16) {
            return b(str);
        }
        if (c(str).contentEquals("MAST") && str.length() == 16) {
            return b(str);
        }
        if ((c(str).contentEquals("MAES") || c(str).contentEquals("SMAE")) && str.length() >= 12 && str.length() <= 19) {
            return b(str);
        }
        if (c(str).contentEquals("DINR") && str.length() == 14) {
            return b(str);
        }
        if (c(str).contentEquals(CARD.AMEX) && str.length() == 15) {
            return b(str);
        }
        if (c(str).contentEquals(CARD.JCB) && str.length() == 16) {
            return b(str);
        }
        return Boolean.valueOf(false);
    }

    public Boolean b(String str) {
        int i = 0;
        int i2 = i;
        for (int length = str.length() - 1; length >= 0; length--) {
            int parseInt = Integer.parseInt(str.substring(length, length + 1));
            if (i != 0) {
                parseInt *= 2;
                if (parseInt > 9) {
                    parseInt = (parseInt % 10) + 1;
                }
            }
            i2 += parseInt;
            i ^= 1;
        }
        if (i2 % 10 == 0) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public String c(String str) {
        if (str.startsWith("4")) {
            return "VISA";
        }
        if (str.matches("^508[5-9][0-9][0-9]|60698[5-9]|60699[0-9]|607[0-8][0-9][0-9]|6079[0-7][0-9]|60798[0-4]|(?!608000)608[0-4][0-9][0-9]|608500|6521[5-9][0-9]|652[2-9][0-9][0-9]|6530[0-9][0-9]|6531[0-4][0-9]")) {
            return "RUPAY";
        }
        if (str.matches("^((6304)|(6706)|(6771)|(6709))[\\d]+")) {
            return "LASER";
        }
        if (str.matches("6(?:011|5[0-9]{2})[0-9]{12}[\\d]+")) {
            return "LASER";
        }
        if (str.matches("(5[06-8]|6\\d)\\d{14}(\\d{2,3})?[\\d]+") || str.matches("(5[06-8]|6\\d)[\\d]+") || str.matches("((504([435|645|774|775|809|993]))|(60([0206]|[3845]))|(622[018])\\d)[\\d]+")) {
            return (str.length() < 6 || !b.contains(str.substring(0, 6))) ? "MAES" : "SMAE";
        } else {
            if (str.matches("^5[1-5][\\d]+")) {
                return "MAST";
            }
            if (str.matches("^3[47][\\d]+")) {
                return CARD.AMEX;
            }
            if (str.startsWith("36") || str.matches("^30[0-5][\\d]+") || str.matches("2(014|149)[\\d]+")) {
                return "DINR";
            }
            return str.matches("^35(2[89]|[3-8][0-9])[\\d]+") ? CARD.JCB : "";
        }
    }

    public boolean a(String str, String str2) {
        str = c(str);
        if (str.contentEquals("SMAE")) {
            return true;
        }
        if (str.contentEquals("")) {
            return false;
        }
        if ((str.contentEquals(CARD.AMEX) & (str2.length() == 4 ? 1 : 0)) != 0) {
            return true;
        }
        if (str.contentEquals(CARD.AMEX) || str2.length() != 3) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:14:0x002f, code skipped:
            return false;
     */
    public boolean a(int r6, int r7) {
        /*
        r5 = this;
        r0 = java.util.Calendar.getInstance();
        r1 = 0;
        r2 = 1;
        if (r6 < r2) goto L_0x002f;
    L_0x0008:
        r3 = 12;
        if (r6 > r3) goto L_0x002f;
    L_0x000c:
        r3 = java.lang.String.valueOf(r7);
        r3 = r3.length();
        r4 = 4;
        if (r3 == r4) goto L_0x0018;
    L_0x0017:
        goto L_0x002f;
    L_0x0018:
        r3 = r0.get(r2);
        if (r3 > r7) goto L_0x002e;
    L_0x001e:
        r3 = r0.get(r2);
        if (r3 != r7) goto L_0x002d;
    L_0x0024:
        r7 = 2;
        r7 = r0.get(r7);
        r7 = r7 + r2;
        if (r7 <= r6) goto L_0x002d;
    L_0x002c:
        goto L_0x002e;
    L_0x002d:
        return r2;
    L_0x002e:
        return r1;
    L_0x002f:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.india.c.c.a(int, int):boolean");
    }

    /* Access modifiers changed, original: protected */
    public String b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public PostData d(String str) {
        return a(5001, "ERROR", str);
    }

    /* Access modifiers changed, original: protected */
    public PostData a(int i, String str) {
        return a(i, "ERROR", str);
    }

    /* Access modifiers changed, original: protected */
    public PostData a(int i, String str, String str2) {
        PostData postData = new PostData();
        postData.a(i);
        postData.a(str);
        postData.b(str2);
        return postData;
    }

    /* Access modifiers changed, original: protected */
    public String e(String str) {
        return str.charAt(str.length() + -1) == '&' ? str.substring(0, str.length() - 1) : str;
    }

    static String a(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.isConnected()) {
                        if (activeNetworkInfo.getType() == 1) {
                            return "WIFI";
                        }
                        if (activeNetworkInfo.getType() == 0) {
                            switch (activeNetworkInfo.getSubtype()) {
                                case 1:
                                    return "GPRS";
                                case 2:
                                    return "EDGE";
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                    return "HSPA";
                                case 4:
                                    return "CDMA";
                                case 7:
                                case 11:
                                    return "2G";
                                case 12:
                                case 14:
                                case 15:
                                    return "3G";
                                case 13:
                                    return "4G";
                                default:
                                    return "?";
                            }
                        }
                    }
                }
                return "Not connected";
            } catch (Exception unused) {
                return "?";
            }
        }
        return "?";
    }

    private String b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getDisplayMetrics().densityDpi);
        stringBuilder.append("");
        return stringBuilder.toString();
    }

    private int c(Context context) {
        try {
            NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int length = allNetworkInfo.length;
            int i = 0;
            int i2 = i;
            while (i < length) {
                NetworkInfo networkInfo = allNetworkInfo[i];
                if (VERSION.SDK_INT >= 18 && networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                    for (CellInfo cellInfo : telephonyManager.getAllCellInfo()) {
                        if (cellInfo.isRegistered()) {
                            if (cellInfo instanceof CellInfoGsm) {
                                i2 = ((CellInfoGsm) cellInfo).getCellSignalStrength().getDbm();
                            } else if (cellInfo instanceof CellInfoCdma) {
                                i2 = ((CellInfoCdma) cellInfo).getCellSignalStrength().getDbm();
                            } else if (cellInfo instanceof CellInfoLte) {
                                i2 = ((CellInfoLte) cellInfo).getCellSignalStrength().getDbm();
                            } else if (cellInfo instanceof CellInfoWcdma) {
                                i2 = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getDbm();
                            }
                        }
                    }
                }
                i++;
            }
            return i2;
        } catch (Exception unused) {
            return 0;
        }
    }

    public void a(Context context, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b(context));
            stringBuilder.append("");
            jSONObject.put("resolution", stringBuilder.toString());
            jSONObject.put("device_manufacturer", Build.MANUFACTURER);
            jSONObject.put("device_model", Build.MODEL);
            if (c == null || c.trim().equals("")) {
                jSONObject.put("merchant_key", str);
            } else {
                jSONObject.put("merchant_key", c);
            }
            jSONObject.put(CBConstant.TXN_ID, str2);
            jSONObject.put("sdk_version", "4.4.3");
            jSONObject.put("cb_version", a);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(VERSION.SDK_INT);
            stringBuilder2.append("");
            jSONObject.put("os_version", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(a(context));
            stringBuilder2.append("");
            jSONObject.put("network_info", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(c(context));
            stringBuilder2.append("");
            jSONObject.put("network_strength", stringBuilder2.toString());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        new d(context, "sdk_local_cache_device").a(jSONObject.toString());
    }

    public static void a(String str, HashMap<String, String> hashMap, String str2) {
        try {
            Class cls = Class.forName(str);
            for (String str3 : hashMap.keySet()) {
                try {
                    Field declaredField = cls.getDeclaredField(str3);
                    declaredField.setAccessible(true);
                    declaredField.set(null, hashMap.get(str3));
                    declaredField.setAccessible(false);
                } catch (Exception unused) {
                }
            }
            Field declaredField2 = cls.getDeclaredField(str2);
            declaredField2.setAccessible(true);
            a = (String) declaredField2.get(null);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        try {
            return ((TelephonyManager) a.a().b().getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return CBConstant.DEFAULT_VALUE;
        }
    }

    /* Access modifiers changed, original: protected */
    public String b() {
        try {
            return Secure.getString(a.a().b().getContentResolver(), "android_id");
        } catch (Exception unused) {
            return CBConstant.DEFAULT_VALUE;
        }
    }
}

package com.appsflyer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.moe.pushlibrary.MoEWorker;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class t {
    private static char[] a = new char[]{'a', 22900, 45648, 2876, 25607, 48619, 5880, 28568, 51364, 8591, 31592, 54395, 11592, 34362, 57091, 14568, 37337, 60052, 17280, 40075, 63076, 20295, 43084, 318, 23071, 46052, 3293, 26099, 48825, 6044, 29037, 51777, 8997, 31784, 28340, 18285, 'y', 22883, 45645, 2871, 25669, 48591, 5841, 28571, 51380, 8590, 31579, 54358, 11632, 34367, 57089, 14581, 37331, 60128, 'b', 22907, 45654, 2859, 25609, 48615, 5804, 28547, 51433, 8590, 31537, 54317, 11609, 34352, 57173, 14517, 37268, 60124, 17376, 40075, 63036, 20295, 43016, 358, 23060, 46003, 3221, 26072, 48874, 6082, 28989, 51735, 'a', 22900, 45648, 2876, 25607, 48619, 5880, 28568, 51391, 8601, 31530, 54364, 11609, 34342, 57112, 14563, 37330, 60099, 17305, 40079, 63078, 20291, 43099, 307, 23042, 'a', 22900, 45648, 2876, 25607, 48619, 5880, 28568, 51384, 8587, 31606, 54394, 11599, 34355, 57118, 14563, 37262, 60137, 17329, 40064, 63099, 20301, 43086, 62659, 44502, 18162, 65438, 37029, 18761, 57946, 39738, 15389, 54587, 36744, 8445, 55785, 29321, 11168, 52295, 25942, 7801, 46853, 26663, 'a', 22900, 45648, 2876, 25607, 48619, 5880, 28568, 51390, 8591, 31600, 54320, 11629, 34336, 57093, '.', '\\', 22836, 50057, 39576, 29089, 51404, 42917, 32264, 54544, 44155, 2887, 57956, 47255, 6041, 61106, 17859, '/', 22905, 45653, 2861, 25600, 48615, 43274, 61460, 6975, 41526, 52543, 5260, 49040, 50876, 25054, 35047, 57432, 47465, 21066, 60214, 33816, 24042, 63218, 36800, 10382, 49545, 39804, 13408, 52563, 26173, 16158, 55538, 29141, 'F', 22907, 45661, 2850, 25613, 48614, 5820, 28610, 51391, 8650, 31587, 54395, 11596, 34418, 57103, 14567, 37315, 60114, 17329, 40142, 63096, 20291, 43080, 318, 23120, 46077, 3277, 26058, 48816, 6098, 29033, 51806, 8995, 31807, 54532, 12026, 34753, 57517, 14770, 37580, 60464, 62983, 44831, 17440, 64871, 37478, 19345, 57480, 39351, 16092, 55270, 36097, 8730, 56089, 28738, 10620, 52874, 26537, 7353, 46549, 27386, 1, 47405, 24114, 63301, 'C', 22898, 45649, 2861, 25603, 48625, 5865, 28635, 51330, 8591, 31586, 54386, 11613, 34353, 57112, 14531, 37336, 60121, 17329, 40094, 63100, 20299, 43091, 312, 14605, 24624, 35606, 12905, 23878, 33965, 12279, 22164, 61941, 6359, 16928, 60734, 5142, 48953, 58965, 424, 43149, 54173, 31482, 42438, 53047, 30220, 37139, 14397, 25430, 35492, 13723, 23709, 34812, 11997, 18535, 62234, 6754, 17765, 60503, 6117, 48774, 55793, 244, 43992, 54571, 31765, 42854, 52858, 26973, 37091, 15303};
    private static long b = 8000305794244106522L;
    private static int c = 0;
    private static int d = 1;

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01b0  */
    @android.support.annotation.Nullable
    static java.lang.String a(android.content.Context r16, long r17) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = 34;
        r4 = 0;
        r5 = a(r3, r4, r4);
        r5 = r5.intern();
        r5 = a(r5);
        r6 = 18269; // 0x475d float:2.56E-41 double:9.026E-320;
        r7 = 35;
        r8 = 28293; // 0x6e85 float:3.9647E-41 double:1.39786E-319;
        r9 = 1;
        if (r5 == 0) goto L_0x0030;
    L_0x0027:
        r5 = a(r9, r3, r8);
    L_0x002b:
        r5 = r5.intern();
        goto L_0x0035;
    L_0x0030:
        r5 = a(r9, r7, r6);
        goto L_0x002b;
    L_0x0035:
        r1.append(r5);
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r10 = r16.getPackageName();
        r11 = b(r10);
        r12 = a(r9, r3, r8);
        r12 = r12.intern();
        r1.append(r12);
        r5.append(r11);
        r11 = a(r16);
        r12 = 4;
        r13 = 23;
        if (r11 != 0) goto L_0x005e;
    L_0x005c:
        r11 = r13;
        goto L_0x005f;
    L_0x005e:
        r11 = r12;
    L_0x005f:
        r14 = 2;
        if (r11 == r12) goto L_0x007a;
    L_0x0062:
        r11 = c;
        r11 = r11 + 55;
        r12 = r11 % 128;
        d = r12;
        r11 = r11 % r14;
        r11 = a(r9, r7, r6);
        r11 = r11.intern();
        r1.append(r11);
        r5.append(r10);
        goto L_0x0088;
    L_0x007a:
        r11 = a(r9, r3, r8);
        r11 = r11.intern();
        r1.append(r11);
        r5.append(r10);
    L_0x0088:
        r11 = b(r16);
        if (r11 != 0) goto L_0x00a6;
    L_0x008e:
        r11 = a(r9, r7, r6);
        r11 = r11.intern();
        r1.append(r11);
        r5.append(r10);
        r11 = c;
        r11 = r11 + 71;
        r12 = r11 % 128;
        d = r12;
        r11 = r11 % r14;
        goto L_0x00b4;
    L_0x00a6:
        r12 = a(r9, r3, r8);
        r12 = r12.intern();
        r1.append(r12);
        r5.append(r11);
    L_0x00b4:
        r11 = r16;
        r10 = a(r11, r10);
        r5.append(r10);
        r5 = r5.toString();
        r0.append(r5);
        r5 = r16.getPackageManager();	 Catch:{ NameNotFoundException -> 0x021c }
        r10 = r16.getPackageName();	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = r5.getPackageInfo(r10, r4);	 Catch:{ NameNotFoundException -> 0x021c }
        r10 = r5.firstInstallTime;	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = 18;
        r12 = 36;
        r5 = a(r5, r12, r4);	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = r5.intern();	 Catch:{ NameNotFoundException -> 0x021c }
        r12 = new java.text.SimpleDateFormat;	 Catch:{ NameNotFoundException -> 0x021c }
        r15 = java.util.Locale.US;	 Catch:{ NameNotFoundException -> 0x021c }
        r12.<init>(r5, r15);	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = "UTC";
        r5 = java.util.TimeZone.getTimeZone(r5);	 Catch:{ NameNotFoundException -> 0x021c }
        r12.setTimeZone(r5);	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = new java.util.Date;	 Catch:{ NameNotFoundException -> 0x021c }
        r5.<init>(r10);	 Catch:{ NameNotFoundException -> 0x021c }
        r5 = r12.format(r5);	 Catch:{ NameNotFoundException -> 0x021c }
        r0.append(r5);	 Catch:{ NameNotFoundException -> 0x021c }
        r10 = r17;
        r0.append(r10);
        r5 = 25;
        r12 = 86;
        r5 = a(r5, r12, r4);
        r5 = r5.intern();
        r5 = a(r5);
        if (r5 == 0) goto L_0x0113;
    L_0x0111:
        r5 = r4;
        goto L_0x0114;
    L_0x0113:
        r5 = r9;
    L_0x0114:
        if (r5 == r9) goto L_0x011f;
    L_0x0116:
        r5 = a(r9, r3, r8);
        r5 = r5.intern();
        goto L_0x0130;
    L_0x011f:
        r5 = a(r9, r7, r6);
        r5 = r5.intern();
        r12 = d;
        r12 = r12 + 13;
        r15 = r12 % 128;
        c = r15;
        r12 = r12 % r14;
    L_0x0130:
        r2.append(r5);
        r5 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r12 = a(r13, r5, r4);
        r12 = r12.intern();
        r12 = a(r12);
        if (r12 == 0) goto L_0x0145;
    L_0x0143:
        r12 = r4;
        goto L_0x0146;
    L_0x0145:
        r12 = r9;
    L_0x0146:
        if (r12 == r9) goto L_0x0151;
    L_0x0148:
        r12 = a(r9, r3, r8);
    L_0x014c:
        r12 = r12.intern();
        goto L_0x0156;
    L_0x0151:
        r12 = a(r9, r7, r6);
        goto L_0x014c;
    L_0x0156:
        r2.append(r12);
        r12 = 20;
        r13 = 134; // 0x86 float:1.88E-43 double:6.6E-322;
        r15 = 62626; // 0xf4a2 float:8.7758E-41 double:3.09414E-319;
        r12 = a(r12, r13, r15);
        r12 = r12.intern();
        r12 = a(r12);
        if (r12 == 0) goto L_0x018e;
    L_0x016e:
        r12 = d;
        r12 = r12 + r5;
        r5 = r12 % 128;
        c = r5;
        r12 = r12 % r14;
        r5 = 60;
        if (r12 == 0) goto L_0x017c;
    L_0x017a:
        r12 = r5;
        goto L_0x017d;
    L_0x017c:
        r12 = r14;
    L_0x017d:
        if (r12 == r5) goto L_0x0184;
    L_0x017f:
        r5 = a(r9, r3, r8);
        goto L_0x0192;
    L_0x0184:
        r5 = 3;
        r5 = a(r4, r5, r8);
        r5 = r5.intern();
        goto L_0x0196;
    L_0x018e:
        r5 = a(r9, r7, r6);
    L_0x0192:
        r5 = r5.intern();
    L_0x0196:
        r2.append(r5);
        r5 = 15;
        r12 = 154; // 0x9a float:2.16E-43 double:7.6E-322;
        r5 = a(r5, r12, r4);
        r5 = r5.intern();
        r5 = a(r5);
        if (r5 == 0) goto L_0x01ad;
    L_0x01ab:
        r5 = r9;
        goto L_0x01ae;
    L_0x01ad:
        r5 = r4;
    L_0x01ae:
        if (r5 == 0) goto L_0x01be;
    L_0x01b0:
        r5 = d;
        r5 = r5 + 93;
        r6 = r5 % 128;
        c = r6;
        r5 = r5 % r14;
        r3 = a(r9, r3, r8);
        goto L_0x01c2;
    L_0x01be:
        r3 = a(r9, r7, r6);
    L_0x01c2:
        r3 = r3.intern();
        r2.append(r3);
        r0 = r0.toString();
        r0 = com.appsflyer.ad.c(r0);
        r0 = com.appsflyer.ad.b(r0);
        r1 = r1.toString();
        r3 = new java.lang.StringBuilder;
        r3.<init>(r0);
        r0 = 17;
        r1 = java.lang.Integer.parseInt(r1, r14);
        r5 = 16;
        r1 = java.lang.Integer.toString(r1, r5);
        r1 = r1.charAt(r4);
        r3.setCharAt(r0, r1);
        r0 = r3.toString();
        r1 = r2.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>(r0);
        r0 = 27;
        r1 = java.lang.Integer.parseInt(r1, r14);
        r1 = java.lang.Integer.toString(r1, r5);
        r1 = r1.charAt(r4);
        r2.setCharAt(r0, r1);
        r0 = r2.toString();
        r1 = java.lang.Long.valueOf(r17);
        r0 = a(r0, r1);
        return r0;
    L_0x021c:
        r0 = 32;
        r1 = 54;
        r0 = a(r0, r1, r4);
        r0 = r0.intern();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.t.a(android.content.Context, long):java.lang.String");
    }

    /* JADX WARNING: Missing block: B:6:0x0018, code skipped:
            if ((r10 != null ? 1 : 0) != 1) goto L_0x00e5;
     */
    /* JADX WARNING: Missing block: B:7:0x001c, code skipped:
            if (r10 != null) goto L_0x001e;
     */
    private static java.lang.String a(java.lang.String r10, java.lang.Long r11) {
        /*
        r0 = d;
        r0 = r0 + 123;
        r1 = r0 % 128;
        c = r1;
        r0 = r0 % 2;
        r1 = 32;
        r2 = 1;
        r3 = 0;
        if (r0 == 0) goto L_0x001c;
    L_0x0010:
        r0 = 75;
        r0 = r0 / r3;
        if (r10 == 0) goto L_0x0017;
    L_0x0015:
        r0 = r2;
        goto L_0x0018;
    L_0x0017:
        r0 = r3;
    L_0x0018:
        if (r0 == r2) goto L_0x001e;
    L_0x001a:
        goto L_0x00e5;
    L_0x001c:
        if (r10 == 0) goto L_0x00e5;
    L_0x001e:
        if (r11 == 0) goto L_0x00e5;
    L_0x0020:
        r0 = c;
        r0 = r0 + 91;
        r4 = r0 % 128;
        d = r4;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0035;
    L_0x002c:
        r0 = r10.length();
        r4 = 40;
        if (r0 != r4) goto L_0x00e5;
    L_0x0034:
        goto L_0x003b;
    L_0x0035:
        r0 = r10.length();
        if (r0 != r1) goto L_0x00e5;
    L_0x003b:
        r0 = new java.lang.StringBuilder;
        r0.<init>(r10);
        r10 = r11.toString();
        r4 = 0;
        r11 = d;
        r11 = r11 + 105;
        r1 = r11 % 128;
        c = r1;
        r11 = r11 % 2;
        r11 = r3;
        r1 = r11;
    L_0x0052:
        r6 = r10.length();
        if (r11 >= r6) goto L_0x005a;
    L_0x0058:
        r6 = r2;
        goto L_0x005b;
    L_0x005a:
        r6 = r3;
    L_0x005b:
        if (r6 == 0) goto L_0x0073;
    L_0x005d:
        r6 = d;
        r6 = r6 + 49;
        r7 = r6 % 128;
        c = r7;
        r6 = r6 % 2;
        r6 = r10.charAt(r11);
        r6 = java.lang.Character.getNumericValue(r6);
        r1 = r1 + r6;
        r11 = r11 + 1;
        goto L_0x0052;
    L_0x0073:
        r10 = java.lang.Integer.toHexString(r1);
        r11 = r10.length();
        r1 = 7;
        r11 = r11 + r1;
        r0.replace(r1, r11, r10);
        r10 = r3;
    L_0x0081:
        r11 = r0.length();
        if (r10 >= r11) goto L_0x00af;
    L_0x0087:
        r11 = d;
        r11 = r11 + 71;
        r1 = r11 % 128;
        c = r1;
        r11 = r11 % 2;
        if (r11 == 0) goto L_0x00a0;
    L_0x0093:
        r11 = r0.charAt(r10);
        r11 = java.lang.Character.getNumericValue(r11);
        r6 = (long) r11;
        r4 = r4 / r6;
        r10 = r10 + 30;
        goto L_0x0081;
    L_0x00a0:
        r11 = r0.charAt(r10);
        r11 = java.lang.Character.getNumericValue(r11);
        r6 = (long) r11;
        r8 = r4 + r6;
        r10 = r10 + 1;
        r4 = r8;
        goto L_0x0081;
    L_0x00af:
        r10 = 100;
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 <= 0) goto L_0x00b7;
    L_0x00b5:
        r4 = r4 % r10;
        goto L_0x00af;
    L_0x00b7:
        r10 = (int) r4;
        r11 = 23;
        r0.insert(r11, r10);
        r6 = 10;
        r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r10 >= 0) goto L_0x00c4;
    L_0x00c3:
        goto L_0x00c5;
    L_0x00c4:
        r3 = r2;
    L_0x00c5:
        if (r3 == r2) goto L_0x00e0;
    L_0x00c7:
        r10 = 35;
        r1 = 18269; // 0x475d float:2.56E-41 double:9.026E-320;
        r10 = a(r2, r10, r1);
        r10 = r10.intern();
        r0.insert(r11, r10);
        r10 = d;
        r10 = r10 + 113;
        r11 = r10 % 128;
        c = r11;
        r10 = r10 % 2;
    L_0x00e0:
        r10 = r0.toString();
        return r10;
    L_0x00e5:
        r10 = 54;
        r10 = a(r1, r10, r3);
        r10 = r10.intern();
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.t.a(java.lang.String, java.lang.Long):java.lang.String");
    }

    private static boolean a(String str) {
        int i = c + 117;
        d = i % 128;
        if ((i % 2 == 0 ? 50 : 33) != 50) {
            try {
                Class.forName(str);
                return true;
            } catch (ClassNotFoundException unused) {
                return false;
            }
        }
        Class.forName(str);
        return false;
    }

    private static String b(String str) {
        if (!str.contains(a(1, 169, 0).intern())) {
            return str;
        }
        String[] split = str.split(a(2, 170, 0).intern());
        int length = split.length;
        StringBuilder stringBuilder = new StringBuilder();
        length--;
        stringBuilder.append(split[length]);
        stringBuilder.append(a(1, 169, 0).intern());
        int i = 1;
        while (true) {
            if ((i < length ? 1 : 0) != 1) {
                break;
            }
            int i2 = c + 67;
            d = i2 % 128;
            if (i2 % 2 == 0) {
                stringBuilder.append(split[i]);
                stringBuilder.append(a(1, 17948, 0).intern());
                i += 6;
            } else {
                stringBuilder.append(split[i]);
                stringBuilder.append(a(1, 169, 0).intern());
                i++;
            }
        }
        stringBuilder.append(split[0]);
        str = stringBuilder.toString();
        int i3 = d + 55;
        c = i3 % 128;
        if ((i3 % 2 != 0 ? 76 : 57) == 57) {
            return str;
        }
        Object obj = null;
        super.hashCode();
        return str;
    }

    private static String a(Context context) {
        String str = null;
        if ((System.getProperties().containsKey(a(14, 172, 50147).intern()) ? 67 : 6) == 67) {
            try {
                Matcher matcher = Pattern.compile(a(10, PsExtractor.AUDIO_STREAM, 43300).intern()).matcher(context.getCacheDir().getPath().replace(a(6, 186, 0).intern(), ""));
                if ((matcher.find() ? 32 : 88) == 32) {
                    int i = d + 5;
                    c = i % 128;
                    i %= 2;
                    String group = matcher.group(1);
                    int i2 = c + 117;
                    d = i2 % 128;
                    i2 %= 2;
                    str = group;
                }
            } catch (Exception e) {
                ah a = ah.a();
                String intern = a(17, 202, 57371).intern();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a(41, 219, 0).intern());
                stringBuilder.append(e);
                a.b(intern, stringBuilder.toString());
                return null;
            }
        }
        return str;
    }

    private static String b(Context context) {
        int i = c + 27;
        d = i % 128;
        i %= 2;
        Object[] objArr = null;
        try {
            int i2 = 0;
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            int i3 = d + MoEWorker.REQ_CODE_SEND_DATA;
            c = i3 % 128;
            if (i3 % 2 == 0) {
                i2 = 1;
            }
            if (i2 != 1) {
                i = objArr.length;
            }
            return str;
        } catch (NameNotFoundException unused) {
            return objArr;
        }
    }

    private static String a(Context context, String str) {
        ah a;
        String intern;
        StringBuilder stringBuilder;
        try {
            Iterator it = ((List) PackageManager.class.getDeclaredMethod(a(24, CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, 63072).intern(), new Class[]{Integer.TYPE}).invoke(context.getPackageManager(), new Object[]{Integer.valueOf(0)})).iterator();
            while (true) {
                if ((it.hasNext() ? 1 : 0) == 0) {
                    int i = c + 107;
                    d = i % 128;
                    i %= 2;
                    break;
                }
                int i2 = c + 93;
                d = i2 % 128;
                if ((i2 % 2 == 0 ? 91 : 9) == 9) {
                    if ((((ApplicationInfo) it.next()).packageName.equals(str) ? 1 : 0) == 1) {
                        break;
                    }
                } else {
                    boolean equals = ((ApplicationInfo) it.next()).packageName.equals(str);
                    Object obj = null;
                    super.hashCode();
                    if (equals) {
                        break;
                    }
                }
            }
            return Boolean.TRUE.toString();
        } catch (IllegalAccessException e) {
            a = ah.a();
            intern = a(24, 284, 0).intern();
            stringBuilder = new StringBuilder();
            stringBuilder.append(a(47, 308, 14667).intern());
            stringBuilder.append(e);
            a.b(intern, stringBuilder.toString());
        } catch (NoSuchMethodException e2) {
            a = ah.a();
            intern = a(24, 284, 0).intern();
            stringBuilder = new StringBuilder();
            stringBuilder.append(a(47, 308, 14667).intern());
            stringBuilder.append(e2);
            a.b(intern, stringBuilder.toString());
        } catch (InvocationTargetException e3) {
            a = ah.a();
            intern = a(24, 284, 0).intern();
            stringBuilder = new StringBuilder();
            stringBuilder.append(a(47, 308, 14667).intern());
            stringBuilder.append(e3);
            a.b(intern, stringBuilder.toString());
        }
        return Boolean.FALSE.toString();
    }

    private static String a(int i, int i2, char c) {
        char[] cArr = new char[i];
        int i3 = c + 23;
        d = i3 % 128;
        i3 %= 2;
        int i4 = 0;
        while (true) {
            if ((i4 < i ? 1 : null) != 1) {
                String str = new String(cArr);
                i2 = d + 87;
                c = i2 % 128;
                i2 %= 2;
                return str;
            }
            int i5 = d + 103;
            c = i5 % 128;
            if ((i5 % 2 != 0 ? 80 : 61) != 61) {
                cArr[i4] = (char) ((int) ((((long) a[i2 >>> i4]) + (((long) i4) % b)) | ((long) c)));
            } else {
                cArr[i4] = (char) ((int) ((((long) a[i2 + i4]) ^ (((long) i4) * b)) ^ ((long) c)));
            }
            i4++;
        }
    }
}

package com.google.android.gms.internal.clearcut;

import com.payu.custombrowser.util.CBConstant;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzdr {
    static String zza(zzdo zzdo, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ");
        stringBuilder.append(str);
        zza(zzdo, stringBuilder, 0);
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARNING: Missing block: B:76:0x01f8, code skipped:
            if (((java.lang.Boolean) r6).booleanValue() == false) goto L_0x01fa;
     */
    /* JADX WARNING: Missing block: B:82:0x020a, code skipped:
            if (((java.lang.Integer) r6).intValue() == 0) goto L_0x01fa;
     */
    /* JADX WARNING: Missing block: B:86:0x021b, code skipped:
            if (((java.lang.Float) r6).floatValue() == 0.0f) goto L_0x01fa;
     */
    /* JADX WARNING: Missing block: B:90:0x022d, code skipped:
            if (((java.lang.Double) r6).doubleValue() == 0.0d) goto L_0x01fa;
     */
    private static void zza(com.google.android.gms.internal.clearcut.zzdo r12, java.lang.StringBuilder r13, int r14) {
        /*
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = new java.util.TreeSet;
        r2.<init>();
        r3 = r12.getClass();
        r3 = r3.getDeclaredMethods();
        r4 = 0;
        r5 = r3.length;
        r6 = r4;
    L_0x001a:
        if (r6 >= r5) goto L_0x0049;
    L_0x001c:
        r7 = r3[r6];
        r8 = r7.getName();
        r1.put(r8, r7);
        r8 = r7.getParameterTypes();
        r8 = r8.length;
        if (r8 != 0) goto L_0x0046;
    L_0x002c:
        r8 = r7.getName();
        r0.put(r8, r7);
        r8 = r7.getName();
        r9 = "get";
        r8 = r8.startsWith(r9);
        if (r8 == 0) goto L_0x0046;
    L_0x003f:
        r7 = r7.getName();
        r2.add(r7);
    L_0x0046:
        r6 = r6 + 1;
        goto L_0x001a;
    L_0x0049:
        r2 = r2.iterator();
    L_0x004d:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x027b;
    L_0x0053:
        r3 = r2.next();
        r3 = (java.lang.String) r3;
        r5 = "get";
        r6 = "";
        r5 = r3.replaceFirst(r5, r6);
        r6 = "List";
        r6 = r5.endsWith(r6);
        r7 = 1;
        if (r6 == 0) goto L_0x00c7;
    L_0x006a:
        r6 = "OrBuilderList";
        r6 = r5.endsWith(r6);
        if (r6 != 0) goto L_0x00c7;
    L_0x0072:
        r6 = "List";
        r6 = r5.equals(r6);
        if (r6 != 0) goto L_0x00c7;
    L_0x007a:
        r6 = r5.substring(r4, r7);
        r6 = r6.toLowerCase();
        r6 = java.lang.String.valueOf(r6);
        r8 = r5.length();
        r8 = r8 + -4;
        r8 = r5.substring(r7, r8);
        r8 = java.lang.String.valueOf(r8);
        r9 = r8.length();
        if (r9 == 0) goto L_0x009f;
    L_0x009a:
        r6 = r6.concat(r8);
        goto L_0x00a5;
    L_0x009f:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x00a5:
        r8 = r0.get(r3);
        r8 = (java.lang.reflect.Method) r8;
        if (r8 == 0) goto L_0x00c7;
    L_0x00ad:
        r9 = r8.getReturnType();
        r10 = java.util.List.class;
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x00c7;
    L_0x00b9:
        r3 = zzj(r6);
        r5 = new java.lang.Object[r4];
        r5 = com.google.android.gms.internal.clearcut.zzcg.zza(r8, r12, r5);
        zza(r13, r14, r3, r5);
        goto L_0x004d;
    L_0x00c7:
        r6 = "Map";
        r6 = r5.endsWith(r6);
        if (r6 == 0) goto L_0x0137;
    L_0x00cf:
        r6 = "Map";
        r6 = r5.equals(r6);
        if (r6 != 0) goto L_0x0137;
    L_0x00d7:
        r6 = r5.substring(r4, r7);
        r6 = r6.toLowerCase();
        r6 = java.lang.String.valueOf(r6);
        r8 = r5.length();
        r8 = r8 + -3;
        r8 = r5.substring(r7, r8);
        r8 = java.lang.String.valueOf(r8);
        r9 = r8.length();
        if (r9 == 0) goto L_0x00fc;
    L_0x00f7:
        r6 = r6.concat(r8);
        goto L_0x0102;
    L_0x00fc:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x0102:
        r3 = r0.get(r3);
        r3 = (java.lang.reflect.Method) r3;
        if (r3 == 0) goto L_0x0137;
    L_0x010a:
        r8 = r3.getReturnType();
        r9 = java.util.Map.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0137;
    L_0x0116:
        r8 = java.lang.Deprecated.class;
        r8 = r3.isAnnotationPresent(r8);
        if (r8 != 0) goto L_0x0137;
    L_0x011e:
        r8 = r3.getModifiers();
        r8 = java.lang.reflect.Modifier.isPublic(r8);
        if (r8 == 0) goto L_0x0137;
    L_0x0128:
        r5 = zzj(r6);
        r6 = new java.lang.Object[r4];
        r3 = com.google.android.gms.internal.clearcut.zzcg.zza(r3, r12, r6);
        zza(r13, r14, r5, r3);
        goto L_0x004d;
    L_0x0137:
        r3 = "set";
        r6 = java.lang.String.valueOf(r5);
        r8 = r6.length();
        if (r8 == 0) goto L_0x0148;
    L_0x0143:
        r3 = r3.concat(r6);
        goto L_0x014e;
    L_0x0148:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x014e:
        r3 = r1.get(r3);
        r3 = (java.lang.reflect.Method) r3;
        if (r3 == 0) goto L_0x004d;
    L_0x0156:
        r3 = "Bytes";
        r3 = r5.endsWith(r3);
        if (r3 == 0) goto L_0x0185;
    L_0x015e:
        r3 = "get";
        r6 = r5.length();
        r6 = r6 + -5;
        r6 = r5.substring(r4, r6);
        r6 = java.lang.String.valueOf(r6);
        r8 = r6.length();
        if (r8 == 0) goto L_0x0179;
    L_0x0174:
        r3 = r3.concat(r6);
        goto L_0x017f;
    L_0x0179:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x017f:
        r3 = r0.containsKey(r3);
        if (r3 != 0) goto L_0x004d;
    L_0x0185:
        r3 = r5.substring(r4, r7);
        r3 = r3.toLowerCase();
        r3 = java.lang.String.valueOf(r3);
        r6 = r5.substring(r7);
        r6 = java.lang.String.valueOf(r6);
        r8 = r6.length();
        if (r8 == 0) goto L_0x01a4;
    L_0x019f:
        r3 = r3.concat(r6);
        goto L_0x01aa;
    L_0x01a4:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x01aa:
        r6 = "get";
        r8 = java.lang.String.valueOf(r5);
        r9 = r8.length();
        if (r9 == 0) goto L_0x01bb;
    L_0x01b6:
        r6 = r6.concat(r8);
        goto L_0x01c1;
    L_0x01bb:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x01c1:
        r6 = r0.get(r6);
        r6 = (java.lang.reflect.Method) r6;
        r8 = "has";
        r5 = java.lang.String.valueOf(r5);
        r9 = r5.length();
        if (r9 == 0) goto L_0x01d8;
    L_0x01d3:
        r5 = r8.concat(r5);
        goto L_0x01dd;
    L_0x01d8:
        r5 = new java.lang.String;
        r5.<init>(r8);
    L_0x01dd:
        r5 = r0.get(r5);
        r5 = (java.lang.reflect.Method) r5;
        if (r6 == 0) goto L_0x004d;
    L_0x01e5:
        r8 = new java.lang.Object[r4];
        r6 = com.google.android.gms.internal.clearcut.zzcg.zza(r6, r12, r8);
        if (r5 != 0) goto L_0x0264;
    L_0x01ed:
        r5 = r6 instanceof java.lang.Boolean;
        if (r5 == 0) goto L_0x01ff;
    L_0x01f1:
        r5 = r6;
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
        if (r5 != 0) goto L_0x01fd;
    L_0x01fa:
        r5 = r7;
        goto L_0x025e;
    L_0x01fd:
        r5 = r4;
        goto L_0x025e;
    L_0x01ff:
        r5 = r6 instanceof java.lang.Integer;
        if (r5 == 0) goto L_0x020d;
    L_0x0203:
        r5 = r6;
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        if (r5 != 0) goto L_0x01fd;
    L_0x020c:
        goto L_0x01fa;
    L_0x020d:
        r5 = r6 instanceof java.lang.Float;
        if (r5 == 0) goto L_0x021e;
    L_0x0211:
        r5 = r6;
        r5 = (java.lang.Float) r5;
        r5 = r5.floatValue();
        r8 = 0;
        r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1));
        if (r5 != 0) goto L_0x01fd;
    L_0x021d:
        goto L_0x01fa;
    L_0x021e:
        r5 = r6 instanceof java.lang.Double;
        if (r5 == 0) goto L_0x0230;
    L_0x0222:
        r5 = r6;
        r5 = (java.lang.Double) r5;
        r8 = r5.doubleValue();
        r10 = 0;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 != 0) goto L_0x01fd;
    L_0x022f:
        goto L_0x01fa;
    L_0x0230:
        r5 = r6 instanceof java.lang.String;
        if (r5 == 0) goto L_0x023b;
    L_0x0234:
        r5 = "";
    L_0x0236:
        r5 = r6.equals(r5);
        goto L_0x025e;
    L_0x023b:
        r5 = r6 instanceof com.google.android.gms.internal.clearcut.zzbb;
        if (r5 == 0) goto L_0x0242;
    L_0x023f:
        r5 = com.google.android.gms.internal.clearcut.zzbb.zzfi;
        goto L_0x0236;
    L_0x0242:
        r5 = r6 instanceof com.google.android.gms.internal.clearcut.zzdo;
        if (r5 == 0) goto L_0x0250;
    L_0x0246:
        r5 = r6;
        r5 = (com.google.android.gms.internal.clearcut.zzdo) r5;
        r5 = r5.zzbe();
        if (r6 != r5) goto L_0x01fd;
    L_0x024f:
        goto L_0x01fa;
    L_0x0250:
        r5 = r6 instanceof java.lang.Enum;
        if (r5 == 0) goto L_0x01fd;
    L_0x0254:
        r5 = r6;
        r5 = (java.lang.Enum) r5;
        r5 = r5.ordinal();
        if (r5 != 0) goto L_0x01fd;
    L_0x025d:
        goto L_0x01fa;
    L_0x025e:
        if (r5 != 0) goto L_0x0262;
    L_0x0260:
        r5 = r7;
        goto L_0x0270;
    L_0x0262:
        r5 = r4;
        goto L_0x0270;
    L_0x0264:
        r7 = new java.lang.Object[r4];
        r5 = com.google.android.gms.internal.clearcut.zzcg.zza(r5, r12, r7);
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
    L_0x0270:
        if (r5 == 0) goto L_0x004d;
    L_0x0272:
        r3 = zzj(r3);
        zza(r13, r14, r3, r6);
        goto L_0x004d;
    L_0x027b:
        r0 = r12 instanceof com.google.android.gms.internal.clearcut.zzcg.zzd;
        if (r0 == 0) goto L_0x02bc;
    L_0x027f:
        r0 = r12;
        r0 = (com.google.android.gms.internal.clearcut.zzcg.zzd) r0;
        r0 = r0.zzjv;
        r0 = r0.iterator();
    L_0x0288:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x02bc;
    L_0x028e:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        r2 = r1.getKey();
        r2 = (com.google.android.gms.internal.clearcut.zzcg.zze) r2;
        r2 = r2.number;
        r3 = 13;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "[";
        r4.append(r3);
        r4.append(r2);
        r2 = "]";
        r4.append(r2);
        r2 = r4.toString();
        r1 = r1.getValue();
        zza(r13, r14, r2, r1);
        goto L_0x0288;
    L_0x02bc:
        r12 = (com.google.android.gms.internal.clearcut.zzcg) r12;
        r0 = r12.zzjp;
        if (r0 == 0) goto L_0x02c7;
    L_0x02c2:
        r12 = r12.zzjp;
        r12.zza(r13, r14);
    L_0x02c7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzdr.zza(com.google.android.gms.internal.clearcut.zzdo, java.lang.StringBuilder, int):void");
    }

    static final void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(stringBuilder, i, str, zza);
            }
        } else if (obj instanceof Map) {
            for (Entry zza2 : ((Map) obj).entrySet()) {
                zza(stringBuilder, i, str, zza2);
            }
        } else {
            stringBuilder.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(str);
            if (obj instanceof String) {
                stringBuilder.append(": \"");
                stringBuilder.append(zzet.zzc(zzbb.zzf((String) obj)));
                stringBuilder.append('\"');
            } else if (obj instanceof zzbb) {
                stringBuilder.append(": \"");
                stringBuilder.append(zzet.zzc((zzbb) obj));
                stringBuilder.append('\"');
            } else if (obj instanceof zzcg) {
                stringBuilder.append(" {");
                zza((zzcg) obj, stringBuilder, i + 2);
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else if (obj instanceof Entry) {
                stringBuilder.append(" {");
                Entry entry = (Entry) obj;
                int i4 = i + 2;
                zza(stringBuilder, i4, CBConstant.KEY, entry.getKey());
                zza(stringBuilder, i4, "value", entry.getValue());
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else {
                stringBuilder.append(": ");
                stringBuilder.append(obj.toString());
            }
        }
    }

    private static final String zzj(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append("_");
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }
        return stringBuilder.toString();
    }
}

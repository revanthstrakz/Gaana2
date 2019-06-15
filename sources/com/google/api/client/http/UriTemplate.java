package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UriTemplate {
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    private enum CompositeOutput {
        PLUS(Character.valueOf('+'), "", ",", false, true),
        HASH(Character.valueOf('#'), "#", ",", false, true),
        DOT(Character.valueOf('.'), ".", ".", false, false),
        FORWARD_SLASH(Character.valueOf('/'), "/", "/", false, false),
        SEMI_COLON(Character.valueOf(';'), ";", ";", true, false),
        QUERY(Character.valueOf('?'), "?", "&", true, false),
        AMP(Character.valueOf('&'), "&", "&", true, false),
        SIMPLE(null, "", ",", false, false);
        
        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        private CompositeOutput(Character ch, String str, String str2, boolean z, boolean z2) {
            this.propertyPrefix = ch;
            this.outputPrefix = (String) Preconditions.checkNotNull(str);
            this.explodeJoiner = (String) Preconditions.checkNotNull(str2);
            this.requiresVarAssignment = z;
            this.reservedExpansion = z2;
            if (ch != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(ch, this);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public String getOutputPrefix() {
            return this.outputPrefix;
        }

        /* Access modifiers changed, original: 0000 */
        public String getExplodeJoiner() {
            return this.explodeJoiner;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean requiresVarAssignment() {
            return this.requiresVarAssignment;
        }

        /* Access modifiers changed, original: 0000 */
        public int getVarNameStartIndex() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        /* Access modifiers changed, original: 0000 */
        public String getEncodedValue(String str) {
            if (this.reservedExpansion) {
                return CharEscapers.escapeUriPath(str);
            }
            return CharEscapers.escapeUri(str);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean getReservedExpansion() {
            return this.reservedExpansion;
        }
    }

    static {
        CompositeOutput.values();
    }

    static CompositeOutput getCompositeOutput(String str) {
        CompositeOutput compositeOutput = (CompositeOutput) COMPOSITE_PREFIXES.get(Character.valueOf(str.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    private static Map<String, Object> getMap(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Entry entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (!(value == null || Data.isNull(value))) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    public static String expand(String str, String str2, Object obj, boolean z) {
        if (str2.startsWith("/")) {
            GenericUrl genericUrl = new GenericUrl(str);
            genericUrl.setRawPath(null);
            str = String.valueOf(genericUrl.build());
            str2 = String.valueOf(str2);
            if (str2.length() != 0) {
                str = str.concat(str2);
            } else {
                str2 = new String(str);
                return expand(str2, obj, z);
            }
        }
        if (!(str2.startsWith("http://") || str2.startsWith("https://"))) {
            str = String.valueOf(str);
            str2 = String.valueOf(str2);
            if (str2.length() != 0) {
                str = str.concat(str2);
            } else {
                str2 = new String(str);
            }
        }
        return expand(str2, obj, z);
        str2 = str;
        return expand(str2, obj, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0135  */
    public static java.lang.String expand(java.lang.String r13, java.lang.Object r14, boolean r15) {
        /*
        r14 = getMap(r14);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r13.length();
        r2 = 0;
        r3 = r2;
    L_0x000f:
        if (r3 >= r1) goto L_0x0133;
    L_0x0011:
        r4 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r4 = r13.indexOf(r4, r3);
        r5 = -1;
        if (r4 != r5) goto L_0x0028;
    L_0x001a:
        if (r3 != 0) goto L_0x001f;
    L_0x001c:
        if (r15 != 0) goto L_0x001f;
    L_0x001e:
        return r13;
    L_0x001f:
        r13 = r13.substring(r3);
        r0.append(r13);
        goto L_0x0133;
    L_0x0028:
        r3 = r13.substring(r3, r4);
        r0.append(r3);
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r5 = r4 + 2;
        r3 = r13.indexOf(r3, r5);
        r5 = r3 + 1;
        r4 = r4 + 1;
        r3 = r13.substring(r4, r3);
        r4 = getCompositeOutput(r3);
        r6 = 44;
        r6 = com.google.api.client.repackaged.com.google.common.base.Splitter.on(r6);
        r3 = r6.splitToList(r3);
        r3 = r3.listIterator();
        r6 = 1;
        r7 = r6;
    L_0x0053:
        r8 = r3.hasNext();
        if (r8 == 0) goto L_0x0130;
    L_0x0059:
        r8 = r3.next();
        r8 = (java.lang.String) r8;
        r9 = "*";
        r9 = r8.endsWith(r9);
        r10 = r3.nextIndex();
        if (r10 != r6) goto L_0x0070;
    L_0x006b:
        r10 = r4.getVarNameStartIndex();
        goto L_0x0071;
    L_0x0070:
        r10 = r2;
    L_0x0071:
        r11 = r8.length();
        if (r9 == 0) goto L_0x0079;
    L_0x0077:
        r11 = r11 + -1;
    L_0x0079:
        r8 = r8.substring(r10, r11);
        r10 = r14.remove(r8);
        if (r10 != 0) goto L_0x0084;
    L_0x0083:
        goto L_0x0053;
    L_0x0084:
        if (r7 != 0) goto L_0x008e;
    L_0x0086:
        r11 = r4.getExplodeJoiner();
        r0.append(r11);
        goto L_0x0096;
    L_0x008e:
        r7 = r4.getOutputPrefix();
        r0.append(r7);
        r7 = r2;
    L_0x0096:
        r11 = r10 instanceof java.util.Iterator;
        if (r11 == 0) goto L_0x00a2;
    L_0x009a:
        r10 = (java.util.Iterator) r10;
        r8 = getListPropertyValue(r8, r10, r9, r4);
        goto L_0x012b;
    L_0x00a2:
        r11 = r10 instanceof java.lang.Iterable;
        if (r11 != 0) goto L_0x011f;
    L_0x00a6:
        r11 = r10.getClass();
        r11 = r11.isArray();
        if (r11 == 0) goto L_0x00b2;
    L_0x00b0:
        goto L_0x011f;
    L_0x00b2:
        r11 = r10.getClass();
        r11 = r11.isEnum();
        r12 = 2;
        if (r11 == 0) goto L_0x00e6;
    L_0x00bd:
        r9 = r10;
        r9 = (java.lang.Enum) r9;
        r9 = com.google.api.client.util.FieldInfo.of(r9);
        r9 = r9.getName();
        if (r9 == 0) goto L_0x00e4;
    L_0x00ca:
        r9 = r4.requiresVarAssignment();
        if (r9 == 0) goto L_0x00dc;
    L_0x00d0:
        r9 = "%s=%s";
        r11 = new java.lang.Object[r12];
        r11[r2] = r8;
        r11[r6] = r10;
        r10 = java.lang.String.format(r9, r11);
    L_0x00dc:
        r8 = r10.toString();
        r10 = com.google.api.client.util.escape.CharEscapers.escapeUriPath(r8);
    L_0x00e4:
        r8 = r10;
        goto L_0x012b;
    L_0x00e6:
        r11 = com.google.api.client.util.Data.isValueOfPrimitiveType(r10);
        if (r11 != 0) goto L_0x00f5;
    L_0x00ec:
        r10 = getMap(r10);
        r8 = getMapPropertyValue(r8, r10, r9, r4);
        goto L_0x012b;
    L_0x00f5:
        r9 = r4.requiresVarAssignment();
        if (r9 == 0) goto L_0x0107;
    L_0x00fb:
        r9 = "%s=%s";
        r11 = new java.lang.Object[r12];
        r11[r2] = r8;
        r11[r6] = r10;
        r10 = java.lang.String.format(r9, r11);
    L_0x0107:
        r8 = r4.getReservedExpansion();
        if (r8 == 0) goto L_0x0116;
    L_0x010d:
        r8 = r10.toString();
        r8 = com.google.api.client.util.escape.CharEscapers.escapeUriPathWithoutReserved(r8);
        goto L_0x012b;
    L_0x0116:
        r8 = r10.toString();
        r8 = com.google.api.client.util.escape.CharEscapers.escapeUriPath(r8);
        goto L_0x012b;
    L_0x011f:
        r10 = com.google.api.client.util.Types.iterableOf(r10);
        r10 = r10.iterator();
        r8 = getListPropertyValue(r8, r10, r9, r4);
    L_0x012b:
        r0.append(r8);
        goto L_0x0053;
    L_0x0130:
        r3 = r5;
        goto L_0x000f;
    L_0x0133:
        if (r15 == 0) goto L_0x013c;
    L_0x0135:
        r13 = r14.entrySet();
        com.google.api.client.http.GenericUrl.addQueryParams(r13, r0);
    L_0x013c:
        r13 = r0.toString();
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.UriTemplate.expand(java.lang.String, java.lang.Object, boolean):java.lang.String");
    }

    private static String getListPropertyValue(String str, Iterator<?> it, boolean z, CompositeOutput compositeOutput) {
        if (!it.hasNext()) {
            return "";
        }
        String explodeJoiner;
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            explodeJoiner = compositeOutput.getExplodeJoiner();
        } else {
            explodeJoiner = ",";
            if (compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
        }
        while (it.hasNext()) {
            if (z && compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
            stringBuilder.append(compositeOutput.getEncodedValue(it.next().toString()));
            if (it.hasNext()) {
                stringBuilder.append(explodeJoiner);
            }
        }
        return stringBuilder.toString();
    }

    private static String getMapPropertyValue(String str, Map<String, Object> map, boolean z, CompositeOutput compositeOutput) {
        if (map.isEmpty()) {
            return "";
        }
        String str2;
        String str3;
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            str = compositeOutput.getExplodeJoiner();
            str2 = "=";
        } else {
            str2 = ",";
            str3 = ",";
            if (compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
            str = str2;
            str2 = str3;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String encodedValue = compositeOutput.getEncodedValue((String) entry.getKey());
            str3 = compositeOutput.getEncodedValue(entry.getValue().toString());
            stringBuilder.append(encodedValue);
            stringBuilder.append(str2);
            stringBuilder.append(str3);
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}

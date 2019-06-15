package com.helpshift.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class k {
    private static final b a = new b() {
        public final boolean a(CharSequence charSequence, int i, int i2) {
            return i == 0 || charSequence.charAt(i - 1) != '@';
        }
    };

    public interface a {
        void a(String str);
    }

    public interface b {
        boolean a(CharSequence charSequence, int i, int i2);
    }

    public interface c {
        String a(Matcher matcher, String str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0165 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0179 A:{LOOP_START, SYNTHETIC, Splitter:B:51:0x0179, PHI: r5 r6 , LOOP:5: B:51:0x0179->B:111:0x0179} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0179 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:51|52|(2:54|(2:112|56)(8:57|58|59|60|61|62|115|111))(1:113)) */
    private static boolean a(android.text.Spannable r19, int r20, com.helpshift.util.k.a r21) {
        /*
        r0 = r19;
        r2 = 0;
        if (r20 != 0) goto L_0x0006;
    L_0x0005:
        return r2;
    L_0x0006:
        r3 = r19.length();
        r4 = android.text.style.URLSpan.class;
        r3 = r0.getSpans(r2, r3, r4);
        r3 = (android.text.style.URLSpan[]) r3;
        r4 = 1;
        r5 = r3.length;
        r5 = r5 - r4;
    L_0x0015:
        if (r5 < 0) goto L_0x001f;
    L_0x0017:
        r6 = r3[r5];
        r0.removeSpan(r6);
        r5 = r5 + -1;
        goto L_0x0015;
    L_0x001f:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r5 = r20 & 1;
        if (r5 == 0) goto L_0x00d5;
    L_0x0028:
        r5 = android.util.Patterns.WEB_URL;
        r5 = r5.matcher(r0);
    L_0x002e:
        r6 = r5.find();
        if (r6 == 0) goto L_0x00d5;
    L_0x0034:
        r6 = r5.start();
        r7 = r5.end();
        r8 = a;
        if (r8 == 0) goto L_0x0048;
    L_0x0040:
        r8 = a;
        r8 = r8.a(r0, r6, r7);
        if (r8 == 0) goto L_0x002e;
    L_0x0048:
        r8 = new com.helpshift.util.q;
        r8.<init>();
        r15 = r5.group(r2);
        r9 = 3;
        r14 = new java.lang.String[r9];
        r9 = "http://";
        r14[r2] = r9;
        r9 = "https://";
        r14[r4] = r9;
        r9 = "rtsp://";
        r10 = 2;
        r14[r10] = r9;
        r13 = r2;
    L_0x0062:
        r9 = r14.length;
        if (r13 >= r9) goto L_0x00b4;
    L_0x0065:
        r10 = 1;
        r11 = 0;
        r12 = r14[r13];
        r16 = 0;
        r9 = r14[r13];
        r17 = r9.length();
        r9 = r15;
        r18 = r13;
        r13 = r16;
        r16 = r14;
        r14 = r17;
        r9 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r9 == 0) goto L_0x00af;
    L_0x0080:
        r10 = 0;
        r11 = 0;
        r12 = r16[r18];
        r13 = 0;
        r9 = r16[r18];
        r14 = r9.length();
        r9 = r15;
        r9 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r9 != 0) goto L_0x00ad;
    L_0x0092:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r16[r18];
        r9.append(r10);
        r10 = r16[r18];
        r10 = r10.length();
        r10 = r15.substring(r10);
        r9.append(r10);
        r15 = r9.toString();
    L_0x00ad:
        r9 = r4;
        goto L_0x00b7;
    L_0x00af:
        r13 = r18 + 1;
        r14 = r16;
        goto L_0x0062;
    L_0x00b4:
        r16 = r14;
        r9 = r2;
    L_0x00b7:
        if (r9 != 0) goto L_0x00ca;
    L_0x00b9:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r16[r2];
        r9.append(r10);
        r9.append(r15);
        r15 = r9.toString();
    L_0x00ca:
        r8.a = r15;
        r8.b = r6;
        r8.c = r7;
        r3.add(r8);
        goto L_0x002e;
    L_0x00d5:
        r5 = r20 & 2;
        if (r5 == 0) goto L_0x0170;
    L_0x00d9:
        r5 = android.util.Patterns.EMAIL_ADDRESS;
        r5 = r5.matcher(r0);
    L_0x00df:
        r6 = r5.find();
        if (r6 == 0) goto L_0x0170;
    L_0x00e5:
        r6 = r5.start();
        r7 = r5.end();
        r8 = new com.helpshift.util.q;
        r8.<init>();
        r15 = r5.group(r2);
        r14 = new java.lang.String[r4];
        r9 = "mailto:";
        r14[r2] = r9;
        r13 = r2;
    L_0x00fd:
        r9 = r14.length;
        if (r13 >= r9) goto L_0x014f;
    L_0x0100:
        r10 = 1;
        r11 = 0;
        r12 = r14[r13];
        r16 = 0;
        r9 = r14[r13];
        r17 = r9.length();
        r9 = r15;
        r18 = r13;
        r13 = r16;
        r16 = r14;
        r14 = r17;
        r9 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r9 == 0) goto L_0x014a;
    L_0x011b:
        r10 = 0;
        r11 = 0;
        r12 = r16[r18];
        r13 = 0;
        r9 = r16[r18];
        r14 = r9.length();
        r9 = r15;
        r9 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r9 != 0) goto L_0x0148;
    L_0x012d:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r16[r18];
        r9.append(r10);
        r10 = r16[r18];
        r10 = r10.length();
        r10 = r15.substring(r10);
        r9.append(r10);
        r15 = r9.toString();
    L_0x0148:
        r9 = r4;
        goto L_0x0152;
    L_0x014a:
        r13 = r18 + 1;
        r14 = r16;
        goto L_0x00fd;
    L_0x014f:
        r16 = r14;
        r9 = r2;
    L_0x0152:
        if (r9 != 0) goto L_0x0165;
    L_0x0154:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r16[r2];
        r9.append(r10);
        r9.append(r15);
        r15 = r9.toString();
    L_0x0165:
        r8.a = r15;
        r8.b = r6;
        r8.c = r7;
        r3.add(r8);
        goto L_0x00df;
    L_0x0170:
        r5 = r20 & 8;
        if (r5 == 0) goto L_0x01b7;
    L_0x0174:
        r5 = r19.toString();
        r6 = r2;
    L_0x0179:
        r7 = android.webkit.WebView.findAddress(r5);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        if (r7 == 0) goto L_0x01b7;
    L_0x017f:
        r8 = r5.indexOf(r7);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        if (r8 >= 0) goto L_0x0186;
    L_0x0185:
        goto L_0x01b7;
    L_0x0186:
        r9 = new com.helpshift.util.q;	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r9.<init>();	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r10 = r7.length();	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r10 = r10 + r8;
        r8 = r8 + r6;
        r9.b = r8;	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r6 = r6 + r10;
        r9.c = r6;	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r5 = r5.substring(r10);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r8 = "UTF-8";
        r7 = java.net.URLEncoder.encode(r7, r8);	 Catch:{ UnsupportedEncodingException -> 0x0179 }
        r8 = new java.lang.StringBuilder;	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r8.<init>();	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r10 = "geo:0,0?q=";
        r8.append(r10);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r8.append(r7);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r7 = r8.toString();	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r9.a = r7;	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        r3.add(r9);	 Catch:{ UnsupportedOperationException -> 0x01b7 }
        goto L_0x0179;
    L_0x01b7:
        r1 = r20 & 4;
        if (r1 == 0) goto L_0x01fa;
    L_0x01bb:
        r1 = android.util.Patterns.PHONE;
        r1 = r1.matcher(r0);
    L_0x01c1:
        r5 = r1.find();
        if (r5 == 0) goto L_0x01fa;
    L_0x01c7:
        r5 = r1.group();
        r6 = r5.length();
        r7 = 6;
        if (r6 < r7) goto L_0x01c1;
    L_0x01d2:
        r6 = new com.helpshift.util.q;
        r6.<init>();
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "tel:";
        r7.append(r8);
        r7.append(r5);
        r5 = r7.toString();
        r6.a = r5;
        r5 = r1.start();
        r6.b = r5;
        r5 = r1.end();
        r6.c = r5;
        r3.add(r6);
        goto L_0x01c1;
    L_0x01fa:
        r1 = new com.helpshift.util.k$2;
        r1.<init>();
        java.util.Collections.sort(r3, r1);
        r1 = r3.size();
        r5 = r1;
        r1 = r2;
    L_0x0208:
        r6 = r5 + -1;
        if (r1 >= r6) goto L_0x0255;
    L_0x020c:
        r6 = r3.get(r1);
        r6 = (com.helpshift.util.q) r6;
        r7 = r1 + 1;
        r8 = r3.get(r7);
        r8 = (com.helpshift.util.q) r8;
        r9 = r6.b;
        r10 = r8.b;
        if (r9 > r10) goto L_0x0253;
    L_0x0220:
        r9 = r6.c;
        r10 = r8.b;
        if (r9 <= r10) goto L_0x0253;
    L_0x0226:
        r9 = r8.c;
        r10 = r6.c;
        r11 = -1;
        if (r9 > r10) goto L_0x022f;
    L_0x022d:
        r6 = r7;
        goto L_0x024b;
    L_0x022f:
        r9 = r6.c;
        r10 = r6.b;
        r9 = r9 - r10;
        r10 = r8.c;
        r12 = r8.b;
        r10 = r10 - r12;
        if (r9 <= r10) goto L_0x023c;
    L_0x023b:
        goto L_0x022d;
    L_0x023c:
        r9 = r6.c;
        r6 = r6.b;
        r9 = r9 - r6;
        r6 = r8.c;
        r8 = r8.b;
        r6 = r6 - r8;
        if (r9 >= r6) goto L_0x024a;
    L_0x0248:
        r6 = r1;
        goto L_0x024b;
    L_0x024a:
        r6 = r11;
    L_0x024b:
        if (r6 == r11) goto L_0x0253;
    L_0x024d:
        r3.remove(r6);
        r5 = r5 + -1;
        goto L_0x0208;
    L_0x0253:
        r1 = r7;
        goto L_0x0208;
    L_0x0255:
        r1 = r3.size();
        if (r1 != 0) goto L_0x025c;
    L_0x025b:
        return r2;
    L_0x025c:
        r1 = r3.iterator();
    L_0x0260:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x027f;
    L_0x0266:
        r2 = r1.next();
        r2 = (com.helpshift.util.q) r2;
        r3 = new com.helpshift.util.HSLinkify$3;
        r5 = r2.a;
        r6 = r21;
        r3.<init>(r5, r6, r2);
        r5 = r2.b;
        r2 = r2.c;
        r7 = 33;
        r0.setSpan(r3, r5, r2, r7);
        goto L_0x0260;
    L_0x027f:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.k.a(android.text.Spannable, int, com.helpshift.util.k$a):boolean");
    }

    public static boolean a(TextView textView, int i, a aVar) {
        if (i == 0) {
            return false;
        }
        CharSequence text = textView.getText();
        MovementMethod movementMethod;
        if (!(text instanceof Spannable)) {
            Spannable valueOf = SpannableString.valueOf(text);
            if (!a(valueOf, i, aVar)) {
                return false;
            }
            movementMethod = textView.getMovementMethod();
            if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            textView.setText(valueOf);
            return true;
        } else if (!a((Spannable) text, i, aVar)) {
            return false;
        } else {
            movementMethod = textView.getMovementMethod();
            if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            return true;
        }
    }

    public static void a(TextView textView, Pattern pattern, String str, b bVar, c cVar, a aVar) {
        CharSequence valueOf = SpannableString.valueOf(textView.getText());
        if (a((Spannable) valueOf, pattern, str, bVar, cVar, aVar)) {
            textView.setText(valueOf);
            MovementMethod movementMethod = textView.getMovementMethod();
            if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0098  */
    private static boolean a(android.text.Spannable r19, java.util.regex.Pattern r20, java.lang.String r21, com.helpshift.util.k.b r22, com.helpshift.util.k.c r23, com.helpshift.util.k.a r24) {
        /*
        r0 = r19;
        r1 = r21;
        r2 = r22;
        r3 = r23;
        if (r1 != 0) goto L_0x0010;
    L_0x000a:
        r1 = "";
    L_0x000c:
        r4 = r1;
        r1 = r20;
        goto L_0x0017;
    L_0x0010:
        r4 = java.util.Locale.ROOT;
        r1 = r1.toLowerCase(r4);
        goto L_0x000c;
    L_0x0017:
        r1 = r1.matcher(r0);
        r5 = 0;
        r6 = r5;
    L_0x001d:
        r7 = r1.find();
        if (r7 == 0) goto L_0x00bc;
    L_0x0023:
        r7 = r1.start();
        r8 = r1.end();
        r9 = 1;
        if (r2 == 0) goto L_0x0033;
    L_0x002e:
        r10 = r2.a(r0, r7, r8);
        goto L_0x0034;
    L_0x0033:
        r10 = r9;
    L_0x0034:
        if (r10 == 0) goto L_0x00b8;
    L_0x0036:
        r6 = r1.group(r5);
        r10 = new java.lang.String[r9];
        r10[r5] = r4;
        if (r3 == 0) goto L_0x0044;
    L_0x0040:
        r6 = r3.a(r1, r6);
    L_0x0044:
        r15 = r5;
    L_0x0045:
        r11 = r10.length;
        if (r15 >= r11) goto L_0x0094;
    L_0x0048:
        r12 = 1;
        r13 = 0;
        r14 = r10[r15];
        r16 = 0;
        r11 = r10[r15];
        r17 = r11.length();
        r11 = r6;
        r18 = r15;
        r15 = r16;
        r16 = r17;
        r11 = r11.regionMatches(r12, r13, r14, r15, r16);
        if (r11 == 0) goto L_0x0091;
    L_0x0061:
        r12 = 0;
        r13 = 0;
        r14 = r10[r18];
        r15 = 0;
        r11 = r10[r18];
        r16 = r11.length();
        r11 = r6;
        r11 = r11.regionMatches(r12, r13, r14, r15, r16);
        if (r11 != 0) goto L_0x008e;
    L_0x0073:
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = r10[r18];
        r11.append(r12);
        r12 = r10[r18];
        r12 = r12.length();
        r6 = r6.substring(r12);
        r11.append(r6);
        r6 = r11.toString();
    L_0x008e:
        r11 = r6;
        r6 = r9;
        goto L_0x0096;
    L_0x0091:
        r15 = r18 + 1;
        goto L_0x0045;
    L_0x0094:
        r11 = r6;
        r6 = r5;
    L_0x0096:
        if (r6 != 0) goto L_0x00a9;
    L_0x0098:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r10 = r10[r5];
        r6.append(r10);
        r6.append(r11);
        r11 = r6.toString();
    L_0x00a9:
        r6 = new com.helpshift.util.HSLinkify$4;
        r10 = r24;
        r6.<init>(r11, r10, r11);
        r11 = 33;
        r0.setSpan(r6, r7, r8, r11);
        r6 = r9;
        goto L_0x001d;
    L_0x00b8:
        r10 = r24;
        goto L_0x001d;
    L_0x00bc:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.k.a(android.text.Spannable, java.util.regex.Pattern, java.lang.String, com.helpshift.util.k$b, com.helpshift.util.k$c, com.helpshift.util.k$a):boolean");
    }
}

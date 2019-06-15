package com.helpshift.support.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ProgressBar;
import com.helpshift.e.b;
import com.helpshift.e.d;
import com.helpshift.support.Faq;
import com.helpshift.util.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class h {
    public static int a(Context context, int i) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        i = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        return i;
    }

    public static void a(Context context, Drawable drawable, boolean z) {
        v.a(context, drawable, z ? b.colorAccent : 16842906);
    }

    public static void a(Context context, Drawable drawable) {
        v.a(context, drawable, b.hs__chatBubbleAdminBackgroundColor);
    }

    public static void b(Context context, Drawable drawable) {
        v.a(context, drawable, b.hs__chatBubbleUserBackgroundColor);
    }

    public static void c(Context context, Drawable drawable) {
        v.a(context, drawable, b.colorAccent);
    }

    public static void a(Context context, ProgressBar progressBar) {
        v.a(progressBar.getIndeterminateDrawable(), ContextCompat.getColor(context, d.hs__color_FFFFFFFF));
    }

    public static void d(Context context, Drawable drawable) {
        v.a(drawable, ContextCompat.getColor(context, d.hs__color_FF000000));
    }

    public static Faq a(Context context, Faq faq, ArrayList<String> arrayList) {
        Faq faq2 = faq;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        String toLowerCase;
        String a;
        String toLowerCase2;
        Collections.sort(arrayList);
        Collections.reverse(arrayList);
        String str = faq2.b;
        String str2 = faq2.e;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String b = v.b(context, b.hs__searchHighlightColor);
        Object obj = (d.a(str).equals(str) && d.a(str2).equals(str2)) ? 1 : null;
        Iterator it;
        if (obj == null) {
            String str3;
            int i;
            int length = str.length();
            ArrayList arrayList2 = new ArrayList();
            String str4 = "";
            int i2 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(charAt);
                stringBuilder.append("");
                String a2 = d.a(stringBuilder.toString());
                str3 = str4;
                for (i = 0; i < a2.length(); i++) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str3);
                    stringBuilder2.append(a2.charAt(i));
                    str3 = stringBuilder2.toString();
                    arrayList2.add(Integer.valueOf(i2));
                }
                i2++;
                str4 = str3;
            }
            toLowerCase = str4.toLowerCase();
            i2 = str2.length();
            d.a(str2);
            ArrayList arrayList3 = new ArrayList();
            str3 = "";
            i = 0;
            while (i < i2) {
                char charAt2 = str2.charAt(i);
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(charAt2);
                stringBuilder3.append("");
                a = d.a(stringBuilder3.toString());
                String str5 = str3;
                for (int i3 = 0; i3 < a.length(); i3++) {
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(str5);
                    stringBuilder4.append(a.charAt(i3));
                    str5 = stringBuilder4.toString();
                    arrayList3.add(Integer.valueOf(i));
                }
                i++;
                str3 = str5;
            }
            toLowerCase2 = str3.toLowerCase();
            it = arrayList.iterator();
            while (it.hasNext()) {
                a = (String) it.next();
                if (a.length() >= 3) {
                    a = a.toLowerCase();
                    for (i = TextUtils.indexOf(toLowerCase, a, 0); i >= 0; i = TextUtils.indexOf(toLowerCase, a, i + a.length())) {
                        linkedHashSet.add(str.substring(((Integer) arrayList2.get(i)).intValue(), ((Integer) arrayList2.get((a.length() + i) - 1)).intValue() + 1));
                    }
                    for (i = TextUtils.indexOf(toLowerCase2, a, 0); i >= 0; i = TextUtils.indexOf(toLowerCase2, a, i + a.length())) {
                        linkedHashSet.add(str2.substring(((Integer) arrayList3.get(i)).intValue(), ((Integer) arrayList3.get((a.length() + i) - 1)).intValue() + 1));
                    }
                }
            }
        } else {
            it = arrayList.iterator();
            while (it.hasNext()) {
                toLowerCase = (String) it.next();
                if (toLowerCase.length() >= 3) {
                    linkedHashSet.add(toLowerCase);
                }
            }
        }
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(">");
        stringBuilder5.append(str2);
        stringBuilder5.append("<");
        CharSequence stringBuilder6 = stringBuilder5.toString();
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append(">");
        stringBuilder7.append(str);
        stringBuilder7.append("<");
        CharSequence stringBuilder8 = stringBuilder7.toString();
        Pattern compile = Pattern.compile(">[^<]+<");
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            String substring;
            StringBuilder stringBuilder9;
            String stringBuilder10;
            StringBuilder stringBuilder11;
            toLowerCase = (String) it2.next();
            Matcher matcher = compile.matcher(stringBuilder8);
            a = stringBuilder8;
            while (matcher.find()) {
                substring = stringBuilder8.substring(matcher.start(), matcher.end());
                stringBuilder9 = new StringBuilder();
                stringBuilder9.append("(?i)(");
                stringBuilder9.append(toLowerCase);
                stringBuilder9.append(")");
                stringBuilder10 = stringBuilder9.toString();
                stringBuilder11 = new StringBuilder();
                stringBuilder11.append("<span style=\"background-color: ");
                stringBuilder11.append(b);
                stringBuilder11.append("\">$1</span>");
                a = a.replace(substring, substring.replaceAll(stringBuilder10, stringBuilder11.toString()));
            }
            Matcher matcher2 = compile.matcher(stringBuilder6);
            toLowerCase2 = stringBuilder6;
            while (matcher2.find()) {
                substring = stringBuilder6.substring(matcher2.start(), matcher2.end());
                stringBuilder9 = new StringBuilder();
                stringBuilder9.append("(?i)(");
                stringBuilder9.append(toLowerCase);
                stringBuilder9.append(")");
                stringBuilder10 = stringBuilder9.toString();
                stringBuilder11 = new StringBuilder();
                stringBuilder11.append("<span style=\"background-color: ");
                stringBuilder11.append(b);
                stringBuilder11.append("\">$1</span>");
                toLowerCase2 = toLowerCase2.replace(substring, substring.replaceAll(stringBuilder10, stringBuilder11.toString()));
            }
            Object stringBuilder62 = toLowerCase2;
            Object stringBuilder82 = a;
        }
        return new Faq(1, faq.a(), faq2.c, faq2.d, stringBuilder82.substring(1, stringBuilder82.length() - 1), stringBuilder62.substring(1, stringBuilder62.length() - 1), faq2.f, faq2.g, faq.b(), faq.c());
    }
}

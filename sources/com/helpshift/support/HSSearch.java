package com.helpshift.support;

import android.text.Html;
import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.support.e.a;
import com.helpshift.support.model.FaqSearchIndex;
import com.helpshift.support.model.FuzzySearchToken;
import com.helpshift.support.model.TfIdfSearchToken;
import com.helpshift.support.search.a.b;
import com.helpshift.support.util.d;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HSSearch {
    private static a a = new a();
    private static boolean b;
    private static boolean c;
    private static HashMap<String, String[]> d;
    private static final Pattern e = Pattern.compile("[a-zA-Z0-9]+");

    public enum HS_SEARCH_OPTIONS {
        FULL_SEARCH,
        METAPHONE_SEARCH,
        KEYWORD_SEARCH
    }

    public static int a(int i) {
        return i == 20 ? 5 : i == 30 ? 3 : 1;
    }

    public static final int a(int i, int i2) {
        return i == 1 ? 5 : 40 == i2 ? i : 10 == i2 ? 30 : 50 == i2 ? 1 : 20 == i2 ? HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES : 30 == i2 ? 150 : 1;
    }

    private static int a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    private static boolean b(int i, int i2) {
        boolean z = true;
        if (50 != i && 50 != i2) {
            return true;
        }
        if (i != i2) {
            z = false;
        }
        return z;
    }

    private static int c(int i, int i2) {
        return i2 < i ? i2 : i;
    }

    public static void a() {
        if (!b) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    d.b();
                }
            }, "HS-trnsltrtr");
            thread.setDaemon(true);
            thread.start();
        }
    }

    public static void b() {
        if (b) {
            c = true;
        } else {
            d.c();
        }
        d = null;
    }

    public static String a(String str) {
        return Html.fromHtml(str).toString();
    }

    public static String b(String str) {
        return str.replaceAll("<[^<>]+>", "");
    }

    public static ArrayList<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = e.matcher(str);
        while (matcher.find()) {
            if (matcher.group(0).length() > 2) {
                arrayList.add(matcher.group(0));
            }
        }
        return arrayList;
    }

    public static ArrayList<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = e.matcher(str);
        while (matcher.find()) {
            if (matcher.group(0).length() > 2 || str.length() > 2) {
                arrayList.add(matcher.group(0));
            }
        }
        return arrayList;
    }

    public static ArrayList<TfIdfSearchToken> a(String str, int i) {
        HashSet hashSet = new HashSet();
        hashSet.add(new TfIdfSearchToken(str, i));
        str = a.a(str, false);
        if (str != null) {
            hashSet.add(new TfIdfSearchToken(str.toLowerCase(), 50));
        }
        return new ArrayList(hashSet);
    }

    public static ArrayList<TfIdfSearchToken> e(String str) {
        return a(str, 10);
    }

    public static String f(String str) {
        return d.a(a(b(str)).toLowerCase());
    }

    private static void a(com.helpshift.support.search.tfidf.a aVar, String str, String str2, List<String> list, int i) {
        a(aVar, c(f(str)), 20, i);
        ArrayList arrayList = new ArrayList();
        for (String c : list) {
            arrayList.addAll(c(c));
        }
        a(aVar, arrayList, 30, i);
        a(aVar, c(f(str2)), 10, i);
    }

    private static void a(com.helpshift.support.search.tfidf.a aVar, List<String> list, int i, int i2) {
        for (String a : list) {
            Iterator it = a(a, i).iterator();
            while (it.hasNext()) {
                TfIdfSearchToken tfIdfSearchToken = (TfIdfSearchToken) it.next();
                aVar.a(tfIdfSearchToken.a, tfIdfSearchToken.b, i2);
            }
        }
    }

    public static FaqSearchIndex a(ArrayList<Faq> arrayList) {
        if (b) {
            return null;
        }
        if (!d.a()) {
            d.b();
            c = true;
        }
        b = true;
        Map c = c((ArrayList) arrayList);
        b((ArrayList) arrayList);
        FaqSearchIndex faqSearchIndex = new FaqSearchIndex(c);
        b = false;
        if (c) {
            b();
            c = false;
        }
        return faqSearchIndex;
    }

    protected static ArrayList<TfIdfSearchToken> a(ArrayList<TfIdfSearchToken> arrayList, HS_SEARCH_OPTIONS hs_search_options) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            TfIdfSearchToken tfIdfSearchToken = (TfIdfSearchToken) it.next();
            int i = tfIdfSearchToken.b;
            if (hs_search_options == HS_SEARCH_OPTIONS.FULL_SEARCH) {
                arrayList2.add(tfIdfSearchToken);
            } else if (hs_search_options == HS_SEARCH_OPTIONS.METAPHONE_SEARCH && 50 == i) {
                arrayList2.add(tfIdfSearchToken);
            } else if (hs_search_options == HS_SEARCH_OPTIONS.KEYWORD_SEARCH && (10 == i || 40 == i)) {
                arrayList2.add(tfIdfSearchToken);
            }
        }
        return arrayList2;
    }

    public static ArrayList<HashMap> a(String str, HS_SEARCH_OPTIONS hs_search_options) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it = d(f(str)).iterator();
        while (it.hasNext()) {
            arrayList.addAll(a(e((String) it.next()), hs_search_options));
        }
        com.helpshift.support.search.a b = b.b();
        HashSet hashSet = null;
        if (b != null) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                TfIdfSearchToken tfIdfSearchToken = (TfIdfSearchToken) it2.next();
                String str2 = tfIdfSearchToken.a;
                int i = tfIdfSearchToken.b;
                com.helpshift.support.search.b a = b.a(str2);
                if (a != null && b(a.b, tfIdfSearchToken.b)) {
                    Map map = a.c;
                    HashMap hashMap3 = new HashMap();
                    for (Integer num : map.keySet()) {
                        hashMap3.put(String.valueOf(num), map.get(num));
                    }
                    if (hashMap3 != null && hashMap3.keySet().size() > 0) {
                        for (String str3 : hashMap3.keySet()) {
                            ArrayList arrayList2 = (ArrayList) hashMap2.get(str3);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            if (str2.length() > 0) {
                                arrayList2.add(str2);
                            }
                            hashMap2.put(str3, arrayList2);
                            Double d = (Double) hashMap.get(str3);
                            Double valueOf = Double.valueOf(((Double) hashMap3.get(str3)).doubleValue() * ((double) a(str2.length(), i)));
                            if (d != null) {
                                hashMap.put(str3, Double.valueOf(d.doubleValue() + valueOf.doubleValue()));
                            } else {
                                hashMap.put(str3, valueOf);
                            }
                        }
                        HashSet hashSet2 = new HashSet();
                        hashSet2.addAll(hashMap3.keySet());
                        if (hashSet == null || hashSet.isEmpty()) {
                            hashSet = new HashSet(hashSet2);
                        } else {
                            hashSet.addAll(hashSet2);
                        }
                    }
                }
            }
        }
        HashMap hashMap4;
        if (hashSet == null || hashSet.isEmpty()) {
            TreeMap treeMap = new TreeMap(new k(hashMap));
            treeMap.putAll(hashMap);
            return a(treeMap, hashMap2);
        } else if (hashSet.size() == 1) {
            hashMap4 = new HashMap();
            ArrayList arrayList3 = new ArrayList();
            String str4 = (String) hashSet.iterator().next();
            hashMap4.put("f", str4);
            hashMap4.put("t", hashMap2.get(str4));
            arrayList3.add(hashMap4);
            return arrayList3;
        } else {
            hashMap4 = new HashMap();
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                String str5 = (String) it3.next();
                hashMap4.put(str5, hashMap.get(str5));
            }
            TreeMap treeMap2 = new TreeMap(new k(hashMap4));
            treeMap2.putAll(hashMap4);
            return a(treeMap2, hashMap2);
        }
    }

    private static ArrayList<HashMap> a(TreeMap treeMap, HashMap hashMap) {
        ArrayList arrayList = new ArrayList();
        for (String str : treeMap.keySet()) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("f", str);
            hashMap2.put("t", hashMap.get(str));
            arrayList.add(hashMap2);
        }
        return arrayList;
    }

    protected static void b(ArrayList<Faq> arrayList) {
        int size = arrayList.size();
        com.helpshift.support.search.tfidf.a aVar = new com.helpshift.support.search.tfidf.a(size);
        for (int i = 0; i < size; i++) {
            Faq faq = (Faq) arrayList.get(i);
            a(aVar, faq.b, faq.e, faq.b(), i);
        }
        aVar.a();
    }

    protected static Map<String, List<FuzzySearchToken>> c(ArrayList<Faq> arrayList) {
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator it2 = c(f(((Faq) it.next()).b)).iterator();
            while (it2.hasNext()) {
                String toLowerCase = ((String) it2.next()).toLowerCase();
                if (toLowerCase.length() > 3) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i);
                    stringBuilder.append("");
                    FuzzySearchToken fuzzySearchToken = new FuzzySearchToken(toLowerCase, stringBuilder.toString());
                    String substring = toLowerCase.substring(0, 1);
                    List list = (List) hashMap.get(substring);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(fuzzySearchToken);
                    hashMap.put(substring, list);
                    toLowerCase = toLowerCase.substring(1, 2);
                    List list2 = (List) hashMap.get(toLowerCase);
                    if (list2 == null) {
                        list2 = new ArrayList();
                    }
                    list2.add(fuzzySearchToken);
                    hashMap.put(toLowerCase, list2);
                }
            }
            i++;
        }
        return hashMap;
    }

    public static ArrayList<HashMap> a(String str, Map<String, List<FuzzySearchToken>> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            HashMap hashMap = new HashMap();
            Iterator it = c(f(str)).iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                String substring = str2.substring(0, 1);
                ArrayList<String> arrayList2 = new ArrayList(g(substring));
                arrayList2.add(substring);
                for (String str3 : arrayList2) {
                    ArrayList arrayList3 = (ArrayList) map.get(str3);
                    if (arrayList3 != null) {
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            FuzzySearchToken fuzzySearchToken = (FuzzySearchToken) it2.next();
                            String str4 = fuzzySearchToken.a;
                            if (((double) a(str4, str2)) > 0.7d) {
                                String str5 = fuzzySearchToken.b;
                                ArrayList arrayList4 = (ArrayList) hashMap.get(str5);
                                if (arrayList4 == null) {
                                    arrayList4 = new ArrayList();
                                }
                                arrayList4.add(str4);
                                hashMap.put(str5, arrayList4);
                            }
                        }
                    }
                }
            }
            for (String str6 : hashMap.keySet()) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("f", str6);
                hashMap2.put("t", hashMap.get(str6));
                arrayList.add(hashMap2);
            }
        }
        return arrayList;
    }

    private static List<String> g(String str) {
        HashMap c = c();
        if (c == null || !c.containsKey(str)) {
            return new ArrayList();
        }
        return Arrays.asList((Object[]) c.get(str));
    }

    static HashMap<String, String[]> c() {
        if (d == null) {
            d = new HashMap();
            d.put("a", new String[]{"q", e.G, "s", "z"});
            d.put(com.helpshift.support.webkit.b.a, new String[]{"v", "h", "n"});
            d.put("c", new String[]{AvidJSONUtil.KEY_X, "f", "v"});
            d.put("d", new String[]{"s", "z", AvidJSONUtil.KEY_X});
            d.put(com.facebook.ads.internal.g.e.a, new String[]{e.G, "s", "d", e.o});
            d.put("f", new String[]{"d", "g", "c", AvidJSONUtil.KEY_X});
            d.put("g", new String[]{"h", "f", "v", com.helpshift.support.webkit.b.a});
            d.put("h", new String[]{"g", "j", com.helpshift.support.webkit.b.a, "n"});
            d.put("i", new String[]{"u", "o", "k", "j"});
            d.put("j", new String[]{"m", "n", "h", "k"});
            d.put("k", new String[]{"j", "l", "m"});
            d.put("l", new String[]{"k", TtmlNode.TAG_P, "m"});
            d.put("m", new String[]{"n", com.helpshift.support.webkit.b.a, "l"});
            d.put("n", new String[]{com.helpshift.support.webkit.b.a, "j", "m"});
            d.put("o", new String[]{"l", "k", TtmlNode.TAG_P});
            d.put(TtmlNode.TAG_P, new String[]{"l", "o"});
            d.put("q", new String[]{e.G, "a"});
            d.put(e.o, new String[]{"s", "d", com.facebook.ads.internal.g.e.a, "f"});
            d.put("s", new String[]{"a", "z", "d"});
            d.put("t", new String[]{e.o, "f", "g", AvidJSONUtil.KEY_Y});
            d.put("u", new String[]{"j", "h", "i", AvidJSONUtil.KEY_Y});
            d.put("v", new String[]{"c", "g", com.helpshift.support.webkit.b.a});
            d.put(e.G, new String[]{"q", "a", "s"});
            d.put(AvidJSONUtil.KEY_X, new String[]{"z", "s", "c"});
            d.put(AvidJSONUtil.KEY_Y, new String[]{"g", "h", "t", "u"});
            d.put("z", new String[]{"a", "s", AvidJSONUtil.KEY_X});
        }
        return d;
    }

    static float a(String str, String str2) {
        String trim = str.trim();
        String trim2 = str2.trim();
        trim = trim.toLowerCase();
        trim2 = trim2.toLowerCase();
        int length = trim.length();
        int length2 = trim2.length();
        int i = length + 1;
        if (length != 0) {
            length = length2 + 1;
            if (length2 != 0) {
                int i2;
                length2 = i * length;
                int[] iArr = new int[length2];
                for (i2 = 0; i2 < i; i2++) {
                    iArr[i2] = i2;
                }
                for (i2 = 0; i2 < length; i2++) {
                    iArr[i2 * i] = i2;
                }
                int i3 = 1;
                while (i3 < i) {
                    int i4 = 1;
                    while (i4 < length) {
                        int i5 = i3 - 1;
                        int i6 = i4 - 1;
                        int i7 = trim.charAt(i5) == trim2.charAt(i6) ? 0 : 1;
                        int i8 = (i4 * i) + i3;
                        int i9 = (i6 * i) + i3;
                        iArr[i8] = a(iArr[i9] + 1, iArr[i8 - 1] + 1, iArr[i9 - 1] + i7);
                        if (i3 > 1 && i4 > 1) {
                            char charAt = trim.charAt(i5);
                            i5 = i4 - 2;
                            if (charAt == trim2.charAt(i5) && trim.charAt(i3 - 2) == trim2.charAt(i6)) {
                                iArr[i8] = c(iArr[i8], iArr[((i5 * i) + i3) - 2] + i7);
                            }
                        }
                        i4++;
                    }
                    i3++;
                }
                int i10 = iArr[length2 - 1];
                if (i > length) {
                    length = i;
                }
                return 1.0f - (((float) i10) / ((float) length));
            }
        }
        return 0.0f;
    }
}

package com.helpshift.support;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

public class FaqTagFilter implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;
    private String a = "undefined";
    private String[] b = new String[0];

    public static class a {
        public static final HashSet<String> a = a();

        private static HashSet<String> a() {
            HashSet hashSet = new HashSet();
            hashSet.add("and");
            hashSet.add("or");
            hashSet.add("not");
            return hashSet;
        }
    }

    public FaqTagFilter(String str, String[] strArr) {
        if (a.a.contains(str)) {
            this.a = str;
        }
        this.b = strArr;
    }

    public String a() {
        return this.a;
    }

    public String[] b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FaqTagFilter) {
            FaqTagFilter faqTagFilter = (FaqTagFilter) obj;
            if (this.a.equals(faqTagFilter.a) && Arrays.equals(this.b, faqTagFilter.b)) {
                return true;
            }
        }
        return false;
    }
}

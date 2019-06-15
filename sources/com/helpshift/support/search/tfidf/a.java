package com.helpshift.support.search.tfidf;

import android.util.Pair;
import android.util.SparseArray;
import com.helpshift.support.HSSearch;
import com.helpshift.support.search.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a {
    private final int a;
    private PageIndexTrieNode b = new PageIndexTrieNode(0);
    private List<b> c = new ArrayList();
    private com.helpshift.support.search.a d;

    public a(int i) {
        this.a = i;
    }

    public void a(String str, int i, int i2) {
        if (str != null && 50 >= str.length()) {
            int length = str.length();
            PageIndexTrieNode pageIndexTrieNode = this.b;
            int i3 = 0;
            while (i3 < length) {
                char charAt = str.charAt(i3);
                PageIndexTrieNode a = pageIndexTrieNode.a(charAt);
                if (a == null) {
                    a = new PageIndexTrieNode(charAt);
                    pageIndexTrieNode.a(a);
                }
                pageIndexTrieNode = a;
                if (i != 50 && i3 > 1 && i3 < 10 && i3 + 1 != length) {
                    pageIndexTrieNode.b = true;
                    pageIndexTrieNode.a(i2, (HSSearch.a(i3, i) * i3) / length, i);
                }
                i3++;
            }
            pageIndexTrieNode.b = true;
            pageIndexTrieNode.a(i2, HSSearch.a(length, i), i);
        }
    }

    public void a() {
        this.d = com.helpshift.support.search.a.b.b();
        char[] cArr = new char[50];
        for (PageIndexTrieNode a : this.b.a()) {
            a(a, cArr, 0);
        }
        if (this.c.size() > 0) {
            this.d.a(this.c);
        }
    }

    private void a(PageIndexTrieNode pageIndexTrieNode, char[] cArr, int i) {
        if (pageIndexTrieNode != null) {
            cArr[i] = pageIndexTrieNode.a;
            if (pageIndexTrieNode.b) {
                this.c.add(a(new String(cArr, 0, i + 1), pageIndexTrieNode));
                if (this.c.size() > 1000) {
                    this.d.a(this.c);
                    this.c.clear();
                }
            }
            for (PageIndexTrieNode a : pageIndexTrieNode.a()) {
                a(a, cArr, i + 1);
            }
            pageIndexTrieNode.f();
        }
    }

    private b a(String str, PageIndexTrieNode pageIndexTrieNode) {
        int d = pageIndexTrieNode.d();
        int b = pageIndexTrieNode.b();
        HashMap hashMap = new HashMap();
        SparseArray c = pageIndexTrieNode.c();
        int i = -1;
        for (int i2 = 0; i2 < c.size(); i2++) {
            Pair pair = (Pair) c.valueAt(i2);
            hashMap.put(Integer.valueOf(c.keyAt(i2)), Double.valueOf(((((double) ((Integer) pair.first).intValue()) / ((double) d)) * Math.log10(((double) this.a) / ((double) b))) * ((double) HSSearch.a(((Integer) pair.second).intValue()))));
            i = Math.max(i, ((Integer) pair.second).intValue());
        }
        pageIndexTrieNode.e();
        return new b(str, i, hashMap);
    }
}

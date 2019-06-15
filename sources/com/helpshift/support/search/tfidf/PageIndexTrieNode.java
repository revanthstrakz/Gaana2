package com.helpshift.support.search.tfidf;

import android.util.Pair;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageIndexTrieNode implements Serializable {
    public final char a;
    public boolean b;
    private SparseArray<Pair<Integer, Integer>> c = new SparseArray();
    private List<PageIndexTrieNode> d = new ArrayList();

    public PageIndexTrieNode(char c) {
        this.a = c;
    }

    public List<PageIndexTrieNode> a() {
        return this.d;
    }

    public PageIndexTrieNode a(char c) {
        if (this.d != null) {
            for (PageIndexTrieNode pageIndexTrieNode : this.d) {
                if (pageIndexTrieNode.a == c) {
                    return pageIndexTrieNode;
                }
            }
        }
        return null;
    }

    public int b() {
        return this.c.size();
    }

    public SparseArray<Pair<Integer, Integer>> c() {
        return this.c;
    }

    public int d() {
        int i = 0;
        int i2 = 0;
        while (i < this.c.size()) {
            i2 = Math.max(i2, ((Integer) ((Pair) this.c.valueAt(i)).first).intValue());
            i++;
        }
        return i2;
    }

    public void a(int i, int i2, int i3) {
        Object pair;
        Pair pair2 = (Pair) this.c.get(i);
        if (pair2 == null) {
            pair = new Pair(Integer.valueOf(i2), Integer.valueOf(i3));
        } else {
            pair = new Pair(Integer.valueOf(((Integer) pair2.first).intValue() + i2), Integer.valueOf(((Integer) pair2.second).intValue()));
        }
        this.c.put(i, pair);
    }

    public void a(PageIndexTrieNode pageIndexTrieNode) {
        this.d.add(pageIndexTrieNode);
    }

    public void e() {
        this.c = null;
    }

    public void f() {
        this.d = null;
    }
}

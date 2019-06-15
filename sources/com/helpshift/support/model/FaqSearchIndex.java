package com.helpshift.support.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FaqSearchIndex implements Serializable {
    private static final long serialVersionUID = 2;
    public final Map<String, List<FuzzySearchToken>> a;

    public FaqSearchIndex(Map<String, List<FuzzySearchToken>> map) {
        this.a = map;
    }
}

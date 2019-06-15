package com.helpshift.views;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.View;

public class HSSearchView extends SearchView {
    public HSSearchView(Context context) {
        super(context);
        a();
    }

    public HSSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        a.a((View) this);
    }
}

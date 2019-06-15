package com.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class b extends ItemDecoration {
    private int a;

    public b(int i) {
        this.a = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        rect.left = this.a;
        rect.right = this.a;
        rect.bottom = this.a;
    }
}

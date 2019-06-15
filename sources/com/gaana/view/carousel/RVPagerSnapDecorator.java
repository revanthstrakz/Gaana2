package com.gaana.view.carousel;

import android.graphics.Rect;
import android.support.annotation.Px;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import kotlin.jvm.internal.c;

public class RVPagerSnapDecorator extends ItemDecoration {
    private final int mItemPadding;
    private final int mLeftRightPadding;

    private final boolean isFirstItem(int i) {
        return i == 0;
    }

    public RVPagerSnapDecorator(@Px int i, @Px int i2) {
        this.mLeftRightPadding = i2;
        this.mItemPadding = i / 2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        if (recyclerView == null) {
            c.a();
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        boolean isFirstItem = isFirstItem(childAdapterPosition);
        boolean isLastItem = isLastItem(childAdapterPosition, recyclerView);
        int i = isFirstItem ? this.mLeftRightPadding : this.mItemPadding;
        childAdapterPosition = isLastItem ? this.mLeftRightPadding : this.mItemPadding;
        if (rect == null) {
            c.a();
        }
        rect.set(i, 0, childAdapterPosition, 0);
    }

    private final boolean isLastItem(int i, RecyclerView recyclerView) {
        Object adapter = recyclerView.getAdapter();
        c.a(adapter, "recyclerView.adapter");
        return i == adapter.getItemCount() - 1;
    }
}

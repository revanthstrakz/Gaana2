package com.facebook.ads.internal.view.d.a;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

public final class c extends RecyclerView {
    public c(Context context) {
        super(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        super.setLayoutManager(linearLayoutManager);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) super.getLayoutManager();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
    }
}

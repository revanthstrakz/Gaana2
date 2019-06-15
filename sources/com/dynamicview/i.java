package com.dynamicview;

import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public class i extends RecycledViewPool {
    public ViewHolder getRecycledView(int i) {
        ViewHolder recycledView = super.getRecycledView(i);
        if (!(recycledView == null || recycledView.itemView.getParent() == null)) {
            ((ViewGroup) recycledView.itemView.getParent()).removeView(recycledView.itemView);
        }
        return recycledView;
    }

    public void putRecycledView(ViewHolder viewHolder) {
        if (!(viewHolder == null || viewHolder.itemView.getParent() == null)) {
            ((ViewGroup) viewHolder.itemView.getParent()).removeView(viewHolder.itemView);
        }
        super.putRecycledView(viewHolder);
    }
}

package com.gaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public class GaanaViewAdapterNew extends Adapter<ViewHolder> {
    private int mCount = -1;
    private OnGetViewCalledListener mListener;

    public interface OnGetViewCalledListener {
        int getItemViewType(int i);

        void onBindViewHolder(ViewHolder viewHolder, int i);

        ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i);
    }

    public GaanaViewAdapterNew(Context context, int i, OnGetViewCalledListener onGetViewCalledListener) {
        this.mCount = i;
        this.mListener = onGetViewCalledListener;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public void updateGaanaAdapter(int i) {
        this.mCount = i;
        notifyDataSetChanged();
    }

    public void updateItemInserted(int i) {
        this.mCount++;
        notifyItemInserted(i);
    }

    public int getItemCount() {
        return this.mCount;
    }

    public int getItemViewType(int i) {
        return this.mListener != null ? this.mListener.getItemViewType(i) : 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mListener != null ? this.mListener.onCreateViewHolder(viewGroup, i) : null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mListener != null) {
            this.mListener.onBindViewHolder(viewHolder, i);
        }
    }
}

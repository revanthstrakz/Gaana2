package com.gaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public class MoreInfoListAdapter extends Adapter<ViewHolder> {
    String _moreInfoListType;
    private IAddListItemView iAddListItemView;
    private int mCount;

    public interface IAddListItemView {
        View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup, String str);

        ViewHolder createViewHolder(ViewGroup viewGroup, int i);

        int getItemViewType(int i);

        void showHideEmtpyView(boolean z);
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public MoreInfoListAdapter(Context context) {
    }

    public void setParamaters(int i, IAddListItemView iAddListItemView, String str) {
        this.mCount = i;
        this.iAddListItemView = iAddListItemView;
        this._moreInfoListType = str;
    }

    public void updateAdapterCount(int i) {
        this.mCount = i;
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.iAddListItemView.createViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        this.iAddListItemView.addListItemView(i, viewHolder, null, this._moreInfoListType);
    }

    public int getItemCount() {
        return this.mCount;
    }
}

package com.gaana;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.c.c;
import com.fragments.BaseGaanaFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.library.controls.CrossFadeImageView;
import com.managers.af;
import com.utilities.Util;
import java.util.ArrayList;

public class TrendingSongItemView extends Adapter<TrendingSongItemViewHolder> {
    private ArrayList<Item> mBusinessobjList;
    private Context mContext;
    private BaseGaanaFragment mFragment;

    public class TrendingSongItemViewHolder extends ViewHolder {
        protected CrossFadeImageView crossFadeImageView;
        protected TextView songNameText;

        public TrendingSongItemViewHolder(View view) {
            super(view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.trendingArtWork);
            this.songNameText = (TextView) view.findViewById(R.id.songNameText);
        }
    }

    public TrendingSongItemView(Context context, BaseGaanaFragment baseGaanaFragment, ArrayList<Item> arrayList) {
        this.mContext = context;
        this.mBusinessobjList = arrayList;
        this.mFragment = baseGaanaFragment;
    }

    public TrendingSongItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TrendingSongItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_trendingsong_list_view, null));
    }

    public void onBindViewHolder(TrendingSongItemViewHolder trendingSongItemViewHolder, int i) {
        final Item item = (Item) this.mBusinessobjList.get(i);
        trendingSongItemViewHolder.crossFadeImageView.bindImage(item.getArtwork());
        trendingSongItemViewHolder.songNameText.setText(item.getName());
        trendingSongItemViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (item != null && item.getEntityType().equals(c.c)) {
                    af.a(TrendingSongItemView.this.mContext, TrendingSongItemView.this.mFragment).a((int) R.id.playMenu, TrendingSongItemView.this.populateTrackClicked(item));
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateTrackClicked(Item item) {
        return Util.g(item);
    }

    public int getItemCount() {
        return this.mBusinessobjList != null ? this.mBusinessobjList.size() : 0;
    }
}

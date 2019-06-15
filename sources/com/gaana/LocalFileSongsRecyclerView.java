package com.gaana;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.BaseItemView;
import com.logging.d;
import com.managers.PlayerManager;
import com.models.PlayerTrack;
import java.util.ArrayList;
import java.util.Collections;

public class LocalFileSongsRecyclerView extends BaseItemView {
    public static int TRENDING_LOCALFILE_ITEM_VIEW = 7;
    private ArrayList<BusinessObject> mBusinessobjList;
    private Context mContext;
    View mView;

    public static class ItemRowHolder extends ViewHolder {
        protected TextView playallbutton;
        protected RecyclerView recycler_view_list;
        protected TextView trendingSongsText;

        public ItemRowHolder(View view) {
            super(view);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.playallbutton = (TextView) view.findViewById(R.id.playAllButton);
            this.trendingSongsText = (TextView) view.findViewById(R.id.trendingSongsText);
        }
    }

    public LocalFileSongsRecyclerView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
    }

    public View getPoplatedView(ViewHolder viewHolder, boolean z, ArrayList<?> arrayList) {
        if (z) {
            initView((ItemRowHolder) viewHolder, arrayList);
        }
        return viewHolder.itemView;
    }

    private void initView(ItemRowHolder itemRowHolder, final ArrayList<?> arrayList) {
        if (itemRowHolder.itemView != null && itemRowHolder.recycler_view_list != null) {
            TrendingSongItemView trendingSongItemView = new TrendingSongItemView(this.mContext, this.mFragment, arrayList);
            itemRowHolder.recycler_view_list.setHasFixedSize(true);
            itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
            itemRowHolder.recycler_view_list.setAdapter(trendingSongItemView);
            itemRowHolder.playallbutton.setTypeface(null, 1);
            itemRowHolder.trendingSongsText.setTypeface(null, 1);
            itemRowHolder.playallbutton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ArrayList a = d.a().a(LocalFileSongsRecyclerView.this.mFragment, arrayList);
                    if (Constants.ab) {
                        Collections.shuffle(a);
                    }
                    if (a != null && a.size() > 0) {
                        ((PlayerTrack) a.get(0)).d(true);
                    }
                    PlayerManager.a(LocalFileSongsRecyclerView.this.mContext).b(a, LocalFileSongsRecyclerView.this.mContext);
                }
            });
        }
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        if (this.mView == null) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.localfile_trending_horizontal_item, null);
        }
        return this.mView;
    }
}

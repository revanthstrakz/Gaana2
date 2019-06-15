package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;

public class GaanaIn2016View extends BaseItemView {
    private BaseGaanaFragment baseGaana = null;
    public boolean isVisible = false;
    private a mDynamicView;

    public View populateView(int i, View view, BusinessObject businessObject, String str, String str2) {
        return view;
    }

    public GaanaIn2016View(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.baseGaana = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(this.mInflater.inflate(R.layout.view_gaana_in_2016, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return populateView(i, viewHolder.itemView, new BusinessObject(), "", "");
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }
}

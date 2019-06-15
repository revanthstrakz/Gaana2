package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Items;
import com.gaana.view.item.BaseItemView.GridItemHolder;
import com.gaana.view.item.BaseItemView.TwoCrossTwoGridItemHolder;
import com.gaana.view.item.DiscoverItemView;
import com.i.i;
import com.i.j;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.services.l.s;
import com.utilities.Util;

public class TwoItemGridView extends BaseItemView {
    BaseGaanaFragment mBaseGaanaFragment;
    private BusinessObject mBusinessObject;
    a mDynamicView;

    public TwoItemGridView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public TwoItemGridView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public TwoItemGridView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment, aVar);
        this.mBaseGaanaFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TwoCrossTwoGridItemHolder(LayoutInflater.from(this.mContext).inflate(R.layout.grid_two_cross_two_item_view, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (this.mBusinessObject != null) {
            getDefaultPoplatedView((TwoCrossTwoGridItemHolder) viewHolder, this.mBusinessObject);
        } else {
            getTwoGridData(viewHolder);
        }
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        super.onClick(view);
    }

    private void getTwoGridData(ViewHolder viewHolder) {
        if (Util.j(this.mContext)) {
            fetchDynamicViewData(this.mDynamicView.l(), null, false, viewHolder);
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void fetchDynamicViewData(String str, String str2, boolean z, final ViewHolder viewHolder) {
        URLManager dynamciOccasionUrl = getDynamciOccasionUrl(str);
        if (str2 != null) {
            dynamciOccasionUrl.a(Integer.parseInt(str2));
        }
        dynamciOccasionUrl.c(Boolean.valueOf(z));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                TwoItemGridView.this.mBusinessObject = businessObject;
                TwoItemGridView.this.getDefaultPoplatedView((TwoCrossTwoGridItemHolder) viewHolder, businessObject);
            }
        }, dynamciOccasionUrl);
    }

    public View getDefaultPoplatedView(TwoCrossTwoGridItemHolder twoCrossTwoGridItemHolder, BusinessObject businessObject) {
        TwoCrossTwoGridItemHolder twoCrossTwoGridItemHolder2 = twoCrossTwoGridItemHolder;
        BusinessObject businessObject2 = businessObject;
        if (businessObject2 instanceof Items) {
            Items items = (Items) businessObject2;
        }
        View view = twoCrossTwoGridItemHolder2.itemView;
        TextView textView = (TextView) view.findViewById(R.id.f55header.text);
        GridItemHolder gridItemHolder = twoCrossTwoGridItemHolder2.firstHolder;
        ViewHolder viewHolder = twoCrossTwoGridItemHolder2.secondHolder;
        GridItemHolder gridItemHolder2 = twoCrossTwoGridItemHolder2.thirdHolder;
        ViewHolder viewHolder2 = twoCrossTwoGridItemHolder2.fourthHolder;
        DiscoverItemView discoverItemView = new DiscoverItemView(this.mContext, this.mBaseGaanaFragment);
        if (businessObject2 == null || businessObject.getArrListBusinessObj().size() < 4) {
            view.setVisibility(8);
        } else {
            int i;
            DiscoverItemView discoverItemView2;
            view.setVisibility(0);
            if (gridItemHolder != null) {
                discoverItemView.setItemPosition(0);
                discoverItemView.getViewAllGriditemView(gridItemHolder, (BusinessObject) businessObject.getArrListBusinessObj().get(0), null, 0, null);
            }
            if (viewHolder != null) {
                discoverItemView.setItemPosition(1);
                discoverItemView.getViewAllGriditemView(viewHolder, (BusinessObject) businessObject.getArrListBusinessObj().get(1), null, 1, null);
            }
            if (gridItemHolder2 != null) {
                discoverItemView.setItemPosition(2);
                i = 0;
                discoverItemView2 = discoverItemView;
                discoverItemView.getViewAllGriditemView(gridItemHolder2, (BusinessObject) businessObject.getArrListBusinessObj().get(2), null, 2, null);
            } else {
                i = 0;
                discoverItemView2 = discoverItemView;
            }
            if (viewHolder2 != null) {
                discoverItemView2.setItemPosition(3);
                discoverItemView2.getViewAllGriditemView(viewHolder2, (BusinessObject) businessObject.getArrListBusinessObj().get(3), null, 3, null);
            }
            textView.setText(this.mDynamicView.k());
            textView.setTypeface(Util.i(this.mContext));
            textView.setVisibility(i);
        }
        return view;
    }

    public void cancelRequest() {
        j.a().a((Object) "2x2GridDynamicApi");
    }

    private URLManager getDynamciOccasionUrl(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.a(str);
        return uRLManager;
    }
}

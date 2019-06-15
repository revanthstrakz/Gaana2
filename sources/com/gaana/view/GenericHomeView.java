package com.gaana.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.moengage.widgets.NudgeView;

public class GenericHomeView extends BaseItemView {
    private a mDynamicView;

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return null;
    }

    public GenericHomeView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public GenericHomeView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public GenericHomeView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mDynamicView == null) {
            return new ItemAdViewHolder(getEmptyLayout());
        }
        String m = this.mDynamicView.m();
        if (m.equals(DynamicViewType.nudge.name())) {
            View inflate = this.mInflater.inflate(R.layout.nudge_view_home, viewGroup, false);
            ((NudgeView) inflate.findViewById(R.id.nudge)).setVisibility(8);
            ((NudgeView) inflate.findViewById(R.id.nudge)).initialiseNudgeView((Activity) this.mContext);
            inflate.setVisibility(8);
            return new ItemAdViewHolder(inflate);
        } else if (m.equals(DynamicViewType.dummy_view.name())) {
            return new ItemAdViewHolder(this.mInflater.inflate(R.layout.dummy_layout, viewGroup, false));
        } else {
            return new ItemAdViewHolder(getEmptyLayout());
        }
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }
}

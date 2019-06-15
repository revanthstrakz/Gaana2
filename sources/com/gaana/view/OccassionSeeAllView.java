package com.gaana.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.b.i;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.c;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.managers.aj;
import com.managers.ap;
import com.services.l.ag;
import com.utilities.Util;

public class OccassionSeeAllView extends BaseItemView {
    public OccassionSeeAllView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public OccassionSeeAllView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public OccassionSeeAllView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment, aVar);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemAdViewHolder itemAdViewHolder = new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_occassion_see_all, viewGroup, false));
        TextView textView = (TextView) itemAdViewHolder.itemView.findViewById(R.id.txt_view_more);
        textView.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        textView.setTypeface(textView.getTypeface(), 1);
        return itemAdViewHolder;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        ((TextView) viewHolder.itemView.findViewById(R.id.txt_view_more)).setText(this.mDynamicView.k());
        ((TextView) viewHolder.itemView.findViewById(R.id.txt_view_more)).setOnClickListener(this);
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        if (Util.j(this.mContext)) {
            c.a().a(new ag() {
                public void onOccasionResponse() {
                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(JukeSeeAllFragment.EXTRA_ARG_TITLE, OccassionSeeAllView.this.mDynamicView.A());
                    bundle.putString("OCCASION_URL", OccassionSeeAllView.this.mDynamicView.l());
                    bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                    dynamicOccasionFragment.setArguments(bundle);
                    ((GaanaActivity) OccassionSeeAllView.this.mContext).displayFragment(dynamicOccasionFragment);
                }

                public void onOccasionError() {
                    aj.a().a(OccassionSeeAllView.this.mContext, OccassionSeeAllView.this.getResources().getString(R.string.error_download_no_internet));
                }
            }, this.mDynamicView.l(), null, false);
        } else {
            ap.a().f(this.mContext);
        }
    }
}

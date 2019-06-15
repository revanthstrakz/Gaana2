package com.gaana;

import android.content.Context;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.comscore.measurement.MeasurementDispatcher;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.view.BaseItemView;
import com.managers.ColombiaManager;
import com.managers.ap;
import com.services.d;

public class HomeSponsorAdView extends BaseItemView {

    public static class HomeSponsorAdViewHolder extends ViewHolder {
        public HomeSponsorAdViewHolder(View view) {
            super(view);
        }

        public void setVisibility(boolean z) {
            LayoutParams layoutParams = (LayoutParams) this.itemView.getLayoutParams();
            Context context = this.itemView.getContext();
            if (z) {
                layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.home_sponsored_ad_height);
                layoutParams.width = -1;
                this.itemView.setVisibility(0);
            } else {
                this.itemView.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public HomeSponsorAdView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeSponsorAdViewHolder(this.mInflater.inflate(R.layout.home_sponsor_add_view, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        long parseLong = Long.parseLong(d.a().b("PREFERENCE_KEY_HOME_SPONSOR_AD", "0", false));
        if (((parseLong != 0 && valueOf.longValue() - parseLong >= MeasurementDispatcher.MILLIS_PER_DAY) || parseLong == 0) && ap.a().c(this.mContext) && this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
            initiateSponsorAd(i, viewHolder, viewHolder.itemView, viewGroup, valueOf.toString());
        }
        return viewHolder.itemView;
    }

    private void initiateSponsorAd(int i, ViewHolder viewHolder, View view, ViewGroup viewGroup, String str) {
        HomeSponsorAdViewHolder homeSponsorAdViewHolder = (HomeSponsorAdViewHolder) viewHolder;
        View view2 = view;
        ColombiaManager.b().a(1, this.mContext, 24, -1, view2, this.mFragment.getClass().getSimpleName(), null, "");
    }
}

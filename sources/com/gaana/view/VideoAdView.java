package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.managers.ap;
import com.managers.f;
import com.til.colombia.android.service.ColombiaNativeVideoAdView;

public class VideoAdView extends BaseItemView implements OnClickListener {
    private long adUnitCode = -1;
    private a mDynamicView;
    private ViewHolder mViewHolder;
    ColombiaNativeVideoAdView videoAdView;

    public VideoAdView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public VideoAdView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
        baseGaanaFragment.addFragmentListener(Constants.ah, new BaseGaanaFragment.a() {
            public void onDestroy() {
                if (VideoAdView.this.videoAdView != null) {
                    VideoAdView.this.videoAdView.clear();
                    VideoAdView.this.videoAdView.removeAllViews();
                }
            }
        });
    }

    public long getAdUnitCode() {
        return this.adUnitCode;
    }

    public void setAdUnitCode(long j) {
        this.adUnitCode = j;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (ap.a().b(this.mContext)) {
            if (this.mViewHolder == null) {
                this.mViewHolder = new ItemAdViewHolder(getNewView(0, viewGroup));
            }
            return this.mViewHolder;
        }
        this.mViewHolder = new ItemAdViewHolder(getEmptyLayout());
        return this.mViewHolder;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (ap.a().b(this.mContext)) {
            return getPopulatedView(i, viewHolder.itemView, viewGroup, null);
        }
        viewHolder.itemView.setVisibility(8);
        return viewHolder.itemView;
    }

    public View getPopulatedView(int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        if (getDynamicView().u().equalsIgnoreCase("columbia")) {
            setAdUnitCode(Long.parseLong(getDynamicView().q()));
            f.v().a(new f.a() {
                public void adPopulated(ColombiaNativeVideoAdView colombiaNativeVideoAdView) {
                    VideoAdView.this.videoAdView = colombiaNativeVideoAdView;
                }
            });
            f.v().a(this.adUnitCode, view, this.mContext);
        }
        return view;
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }
}

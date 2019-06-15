package com.gaana.revampeddetail.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.R;
import com.gaana.revampeddetail.adapter.RevampedCarouselPagerAdapter;
import com.gaana.revampeddetail.manager.RevampedDetailObjectManager;
import com.gaana.revampeddetail.model.RevampedCarouselData;
import com.gaana.revampeddetail.model.RevampedDetailObject.RevampedSectionData;
import com.gaana.view.BaseItemView;
import com.i.i;
import com.managers.URLManager;
import java.util.ArrayList;

public class RevampedDetailCarouselView extends BaseItemView implements OnClickListener, a, b<Object> {
    private RevampedSectionData carousalSectionData;
    View carouselParentView;
    private int itemBottomPadding = 0;
    private RevampedCarouselItemView mCarouselItemView;
    private Context mContext = null;
    private int mDetailType;
    private BaseGaanaFragment mFragment;
    private int mItemClickedPosition = -1;
    private boolean mRefreshCarousel = true;
    private RevampedDetailObjectManager mSectionViewHelper;
    private URLManager mUrlManager = null;
    private int viewSizeHeight = 0;

    public RevampedDetailCarouselView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.mContext = context;
        updateData(false);
        this.mDetailType = this.mSectionViewHelper.getDetailType();
        this.mCarouselItemView = new RevampedCarouselItemView(this.mContext, this.mFragment, this.mDetailType);
        this.carouselParentView = this.mCarouselItemView.getNewView(R.layout.revamped_detail_carousal_view, null);
        this.viewSizeHeight = (int) getResources().getDimension(R.dimen.dp230);
    }

    public void updateData(boolean z) {
        this.mSectionViewHelper = ((RevampedDetailListing) this.mFragment).h();
        this.carousalSectionData = this.mSectionViewHelper.getCarousalSection();
        this.mUrlManager = getCarouselUrlmanager(z);
        this.mRefreshCarousel = true;
    }

    public View getPopulatedView() {
        this.carouselParentView.getLayoutParams().height = this.viewSizeHeight;
        if (this.mRefreshCarousel) {
            this.mRefreshCarousel = false;
            if (this.mUrlManager == null || this.carousalSectionData == null) {
                onResponse(this.mSectionViewHelper.getDummyMetaCarouselData());
            } else {
                i.a().a(this.mUrlManager, this.mFragment.toString(), (b) this, (a) this);
            }
        }
        return this.carouselParentView;
    }

    public void onResponse(Object obj) {
        if (obj != null && (obj instanceof RevampedCarouselData)) {
            ArrayList carousel_data = ((RevampedCarouselData) obj).getCarousel_data();
            if (carousel_data != null && carousel_data.size() > 0) {
                if (!(this.carouselParentView == null || this.carouselParentView.getLayoutParams() == null)) {
                    this.carouselParentView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.dp230);
                }
                this.mCarouselItemView.getPopulatedView(carousel_data);
            } else if (this.carouselParentView != null && this.carouselParentView.getLayoutParams() != null) {
                this.carouselParentView.getLayoutParams().height = 0;
            }
        } else if (this.carouselParentView != null && this.carouselParentView.getLayoutParams() != null) {
            this.carouselParentView.getLayoutParams().height = 0;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.mRefreshCarousel = true;
        if (this.mUrlManager != null) {
            this.mUrlManager.c(Boolean.valueOf(false));
        }
    }

    private URLManager getCarouselUrlmanager(boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(RevampedCarouselData.class);
        if (this.carousalSectionData == null || TextUtils.isEmpty(this.carousalSectionData.getSection_data_url())) {
            return uRLManager;
        }
        uRLManager.a(this.carousalSectionData.getSection_data_url());
        uRLManager.c(Boolean.valueOf(z));
        uRLManager.b(Boolean.valueOf(true));
        return uRLManager;
    }

    public RevampedCarouselPagerAdapter getCarouselPagerAdapter() {
        return this.mCarouselItemView.getCarouselPagerAdapter();
    }
}

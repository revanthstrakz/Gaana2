package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.ReferralScreenFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.services.l.ad;

public class GetFreeDownloadView extends BaseItemView implements OnClickListener {
    protected GaanaApplication mAppState;
    private Context mContext;
    private BaseGaanaFragment mFragment;

    public static class GetFreeDownloadViewHolder extends ViewHolder {
        public RelativeLayout mContainerLayout;

        public GetFreeDownloadViewHolder(View view, boolean z) {
            super(view);
            if (z) {
                this.mContainerLayout = (RelativeLayout) view.findViewById(R.id.llParentLayout);
            }
        }
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return null;
    }

    public void getPopulatedView(int i, ViewHolder viewHolder, DynamicHomeFragment dynamicHomeFragment) {
    }

    public GetFreeDownloadView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public GetFreeDownloadView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public GetFreeDownloadView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Constants.S = true;
        if (!Constants.S) {
            return new ItemAdViewHolder(getEmptyLayout());
        }
        if (this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() == 3) {
            return new ItemAdViewHolder(getEmptyLayout());
        }
        GetFreeDownloadViewHolder getFreeDownloadViewHolder = new GetFreeDownloadViewHolder(getNewView(R.layout.get_free_downloads_view, this.mFragment), true);
        getFreeDownloadViewHolder.mContainerLayout.setOnClickListener(this);
        return getFreeDownloadViewHolder;
    }

    public View getNewView(int i, BaseGaanaFragment baseGaanaFragment) {
        return LayoutInflater.from(this.mContext).inflate(i, null);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.llParentLayout) {
            ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ((GaanaActivity) GetFreeDownloadView.this.mContext).displayFragment(new ReferralScreenFragment());
                }
            }, this.mContext.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_REFERRALS));
        }
    }
}

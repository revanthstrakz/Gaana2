package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.ReferralUserActivities.ReferralUserActivity;
import com.gaana.view.item.BaseItemView;
import com.library.controls.CrossFadeImageView;

public class ReferralActivityItemView extends BaseItemView {
    public ReferralActivityItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.referral_activity_item;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        return getPoplatedViewListing(view, businessObject, viewGroup);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) this.mView.findViewById(R.id.friend_pic);
        ReferralUserActivity referralUserActivity = (ReferralUserActivity) businessObject;
        ((TextView) this.mView.findViewById(R.id.friend_activity)).setText(referralUserActivity.getMessage());
        crossFadeImageView.bindImage(referralUserActivity.getInviteeArtwork());
        return this.mView;
    }

    public View getPoplatedViewListing(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.referral_activity_item, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.friend_pic);
        ReferralUserActivity referralUserActivity = (ReferralUserActivity) businessObject;
        ((TextView) view.findViewById(R.id.friend_activity)).setText(referralUserActivity.getMessage());
        crossFadeImageView.bindImage(referralUserActivity.getInviteeArtwork());
        return view;
    }
}

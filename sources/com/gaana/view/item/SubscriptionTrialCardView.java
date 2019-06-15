package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.constants.Constants.ACTION_TYPE;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.SubscriptionCard;
import com.gaana.models.TrialProductFeature;
import com.gaana.view.ImageCardView;
import com.gaana.view.ImageCardView.ImageCardViewHolder;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.ap;
import com.managers.u;
import com.services.l.af;
import com.utilities.Util;

public class SubscriptionTrialCardView extends ImageCardView {
    private int imageDownloadStatus = -1;
    String imageName = "";
    public boolean isVisible = false;
    private BaseItemView mBaseItemView = null;
    private a mDynamicView;
    private BaseGaanaFragment mFragment = null;
    private boolean mIsRefreshed = false;
    private int mLayoutId = R.layout.view_featured_album_item;
    TrialProductFeature mSubscriptionTrialCard = null;
    private View mView = null;

    public SubscriptionTrialCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment, aVar);
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.imageDownloadStatus = -1;
        return new ImageCardViewHolder(getNewView(R.layout.image_card_view, viewGroup), true);
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return populateCardView(i, viewHolder.itemView, viewHolder);
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View populateCardView(int i, View view, ViewHolder viewHolder) {
        int f = this.mDynamicView.f();
        if (f == ACTION_TYPE.SUBSCRIPTION_TRIAL_CARD.getNumVal()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&no_downloads=");
            stringBuilder.append(DownloadManager.c().B());
            stringBuilder.append(DownloadManager.c().K());
            stringBuilder.append(ag.a(this.mContext).d());
            checkTrialCard(stringBuilder.toString(), viewHolder, i);
        } else if (f == ACTION_TYPE.SUBSCRIPTION_CARD.getNumVal() && GaanaApplication.getInstance().getCurrentUser().getLoginStatus() && !ap.a().e()) {
            checkTrialCard("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_subs_card", viewHolder, i);
        }
        return view;
    }

    public void onClick(View view) {
        ((Integer) view.getTag()).intValue();
        int f = this.mDynamicView.f();
        if (f == ACTION_TYPE.SUBSCRIPTION_TRIAL_CARD.getNumVal()) {
            if (this.mSubscriptionTrialCard == null) {
                openPaymentProductPage();
            } else {
                Util.a(this.mContext, this.mSubscriptionTrialCard, null, null);
            }
        } else if (f == ACTION_TYPE.SUBSCRIPTION_CARD.getNumVal()) {
            sendSubcriptionCardGA(false);
            openPaymentProductPage();
        }
    }

    public void checkTrialCard(String str, final ViewHolder viewHolder, final int i) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.b(1);
        int f = this.mDynamicView.f();
        if (f == ACTION_TYPE.SUBSCRIPTION_TRIAL_CARD.getNumVal()) {
            uRLManager.a(BusinessObjectType.TrialProductFeature);
        } else if (f == ACTION_TYPE.SUBSCRIPTION_CARD.getNumVal()) {
            uRLManager.a(BusinessObjectType.SubscriptionCard);
        }
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    if (obj instanceof TrialProductFeature) {
                        TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                        if (trialProductFeature.getIs_trial()) {
                            ((ImageCardViewHolder) viewHolder).itemView.setVisibility(0);
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setVisibility(0);
                            SubscriptionTrialCardView.this.downloadImage(viewHolder, i, trialProductFeature.getImg_url());
                            SubscriptionTrialCardView.this.mSubscriptionTrialCard = trialProductFeature;
                            if (SubscriptionTrialCardView.this.mSubscriptionTrialCard == null || TextUtils.isEmpty(SubscriptionTrialCardView.this.mSubscriptionTrialCard.getCard_identifier())) {
                                u.a().b("A/B Testing", "Generic");
                                return;
                            } else {
                                u.a().b("A/B Testing", SubscriptionTrialCardView.this.mSubscriptionTrialCard.getCard_identifier());
                                return;
                            }
                        }
                    }
                    if (obj instanceof SubscriptionCard) {
                        SubscriptionCard subscriptionCard = (SubscriptionCard) obj;
                        if (subscriptionCard.getIs_subs_card()) {
                            ((ImageCardViewHolder) viewHolder).itemView.setVisibility(0);
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setVisibility(0);
                            String img_url = subscriptionCard.getImg_url();
                            SubscriptionTrialCardView.this.downloadImage(viewHolder, i, img_url);
                            if (!TextUtils.isEmpty(img_url)) {
                                SubscriptionTrialCardView.this.imageName = img_url.substring(img_url.lastIndexOf(47) + 1);
                            }
                            if (TextUtils.isEmpty(subscriptionCard.getCard_identifier())) {
                                u.a().b("A/B Testing", "Generic");
                            } else {
                                u.a().b("A/B Testing", subscriptionCard.getCard_identifier());
                            }
                            SubscriptionTrialCardView.this.sendSubcriptionCardGA(true);
                            return;
                        }
                    }
                    ((ImageCardViewHolder) viewHolder).llImgParentLayout.removeAllViews();
                    LayoutParams layoutParams = (LayoutParams) ((ImageCardViewHolder) viewHolder).itemView.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.height = 0;
                    ((ImageCardViewHolder) viewHolder).llImgParentLayout.setLayoutParams(layoutParams);
                    ((ImageCardViewHolder) viewHolder).itemView.setVisibility(8);
                    ((ImageCardViewHolder) viewHolder).llImgParentLayout.setVisibility(8);
                }
            }
        }, uRLManager);
    }

    private void sendSubcriptionCardGA(boolean z) {
        String str;
        String str2 = ap.a().i() ? "Trial User" : "Free User";
        GaanaActivity gaanaActivity = (GaanaActivity) this.mContext;
        String str3 = "Subscription Card";
        if (z) {
            str = "View";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Click");
            stringBuilder.append(this.imageName);
            str = stringBuilder.toString();
        }
        gaanaActivity.sendGAEvent(str3, str2, str);
    }

    private void openPaymentProductPage() {
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_SETTINGS", 1);
        bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
        bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
        this.mFragment = new SettingsDetailFragment();
        this.mFragment.setArguments(bundle);
        ((GaanaActivity) this.mContext).displayFragment(this.mFragment);
    }
}

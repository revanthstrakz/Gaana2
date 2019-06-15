package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fragments.BaseGaanaFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.ads.rewarded.IRewardAdType;
import com.gaana.ads.rewarded.IRewardedVideoAdRequestCallBack;
import com.gaana.ads.rewarded.RewardedVideoAdRequest;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.PremiumContentTextConfig;
import com.gaana.models.Tracks.Track;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.ag;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
import com.services.l.au;
import com.services.l.ba;
import com.utilities.Util;

public class PremiumContentPopUpView extends BottomSheetDialog implements OnClickListener {
    private boolean isOnDialogClickHandled = false;
    private Context mContext;
    private PremiumContentTextConfig mPremiumContentTextConfig;
    private ba mPremiumSongClickedListener;
    private Track mTrackClicked;
    private View mViewClick;
    private RewardedVideoAd rewardedVideoAd;
    IRewardAdType rewardedVideoAdType = null;

    public PremiumContentPopUpView(Context context, PremiumContentTextConfig premiumContentTextConfig, Track track, View view, ba baVar) {
        super(context);
        this.mContext = context;
        this.mPremiumContentTextConfig = premiumContentTextConfig;
        this.mPremiumSongClickedListener = baVar;
        this.mTrackClicked = track;
        this.mViewClick = view;
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popup_premium_content_view_layout, null);
        Button button = (Button) inflate.findViewById(R.id.watchAdBtn);
        Button button2 = (Button) inflate.findViewById(R.id.tryGaanPlusBtn);
        TextView textView = (TextView) inflate.findViewById(R.id.topHeaderTitle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.additionalText);
        setContentView(inflate);
        BottomSheetBehavior.from((RelativeLayout) inflate.findViewById(R.id.layout)).setState(3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        if (!(this.mPremiumContentTextConfig == null || this.mPremiumContentTextConfig.getPremium_text_config() == null)) {
            textView.setText(this.mPremiumContentTextConfig.getPremium_text_config().getHeader_txt());
            if (this.mPremiumContentTextConfig.getPremium_text_config().getAd_details() != null) {
                textView2.setText(this.mPremiumContentTextConfig.getPremium_text_config().getAd_details().getMsg_txt());
                button.setText(this.mPremiumContentTextConfig.getPremium_text_config().getAd_details().getCta_txt());
            }
            if (this.mPremiumContentTextConfig.getPremium_text_config().getGplus_details() != null) {
                button2.setText(this.mPremiumContentTextConfig.getPremium_text_config().getGplus_details().getCta_txt());
            }
            if (TextUtils.isEmpty(this.mPremiumContentTextConfig.getPremium_text_config().getCard_identifier())) {
                u.a().b("A/B Testing", "Generic");
            } else {
                u.a().b("A/B Testing", this.mPremiumContentTextConfig.getPremium_text_config().getCard_identifier());
            }
        }
        u.a().a("Premium pop-up", "View", d.a().c("premium_content_track_id", false));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tryGaanPlusBtn) {
            this.isOnDialogClickHandled = true;
            handleButtonclick();
            dismiss();
        } else if (id == R.id.watchAdBtn) {
            this.isOnDialogClickHandled = true;
            this.rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this.mContext);
            if (this.mContext.getResources() != null) {
                ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getResources().getString(R.string.please_wait));
            }
            this.rewardedVideoAdType = new RewardedVideoAdRequest().buildRewardedVideoAd(this.rewardedVideoAd).buildLocation(((GaanaActivity) this.mContext).getLocation()).buildAdRequestCallBack(new IRewardedVideoAdRequestCallBack() {
                public void onRewardedVideoAdOpened() {
                    if (PremiumContentPopUpView.this.mContext != null) {
                        ((GaanaActivity) PremiumContentPopUpView.this.mContext).hideProgressDialog();
                        o.a(PremiumContentPopUpView.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                    }
                }

                public void destroyRewardVideoAd() {
                    if (PremiumContentPopUpView.this.mContext != null) {
                        if (((GaanaActivity) PremiumContentPopUpView.this.mContext).getLifecycle() != null) {
                            ((GaanaActivity) PremiumContentPopUpView.this.mContext).getLifecycle().b(PremiumContentPopUpView.this.rewardedVideoAdType);
                        }
                        if (PremiumContentPopUpView.this.rewardedVideoAd != null) {
                            PremiumContentPopUpView.this.rewardedVideoAd.destroy(PremiumContentPopUpView.this.mContext);
                        }
                        PremiumContentPopUpView.this.rewardedVideoAdType = null;
                    }
                }

                public void playSong(boolean z) {
                    if (PremiumContentPopUpView.this.mContext != null) {
                        ((GaanaActivity) PremiumContentPopUpView.this.mContext).hideProgressDialog();
                    }
                    if (!(PremiumContentPopUpView.this.mViewClick == null || PremiumContentPopUpView.this.mPremiumSongClickedListener == null)) {
                        PremiumContentPopUpView.this.mPremiumSongClickedListener.onPlaySong(PremiumContentPopUpView.this.mViewClick, PremiumContentPopUpView.this.mTrackClicked);
                        PremiumContentPopUpView.this.mViewClick = null;
                    }
                    if (z && PremiumContentPopUpView.this.mContext != null && PremiumContentPopUpView.this.mContext.getResources() != null) {
                        Toast.makeText(GaanaApplication.getInstance(), PremiumContentPopUpView.this.mContext.getResources().getString(R.string.premium_content_unlocked_msg), 0).show();
                    }
                }
            }).build();
            ((GaanaActivity) this.mContext).getLifecycle().a(this.rewardedVideoAdType);
            this.rewardedVideoAdType.loadAndShow();
            dismiss();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void handleButtonclick() {
        ProductItem pg_product = this.mPremiumContentTextConfig.getPremium_text_config().getPg_product();
        if (pg_product == null || TextUtils.isEmpty(pg_product.getAction())) {
            ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
        } else if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(pg_product.getAction())) {
            u.a().a(pg_product, pg_product.getItem_id());
            ag.a(this.mContext).a(this.mContext, pg_product, new a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    ag.a(PremiumContentPopUpView.this.mContext).a("", "", "success");
                    u.a().a("Premium pop-up", "Try Gaana Plus", "Success");
                    ((BaseActivity) PremiumContentPopUpView.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) PremiumContentPopUpView.this.mContext).hideProgressDialog();
                            ap.a().a(PremiumContentPopUpView.this.mContext);
                            Util.aa();
                            aj.a().a(PremiumContentPopUpView.this.mContext, PremiumContentPopUpView.this.mContext.getString(R.string.enjoy_using_gaana_plus));
                            if (((GaanaActivity) PremiumContentPopUpView.this.mContext).getCurrentSongSelectedView() != null) {
                                ((GaanaActivity) PremiumContentPopUpView.this.mContext).getCurrentSongSelectedView().callOnClick();
                            }
                        }
                    });
                }

                public void onFailure(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        aj.a().a(PremiumContentPopUpView.this.mContext, str);
                    }
                    ag.a(PremiumContentPopUpView.this.mContext).a(str, "", str2);
                    u.a().a("Premium pop-up", "Try Gaana Plus", "Failure");
                }
            }, pg_product.getItem_id(), pg_product.getDesc());
        } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(pg_product.getAction())) {
            BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
            paymentDetailFragment.a(pg_product);
            ((GaanaActivity) this.mContext).displayFragment(paymentDetailFragment);
        } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(pg_product.getAction()) && !TextUtils.isEmpty(pg_product.getWeb_url())) {
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", pg_product.getWeb_url());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.mContext.startActivity(intent);
        } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(pg_product.getAction())) {
            u.a().a(pg_product, pg_product.getItem_id());
            u.a().a(pg_product, pg_product.getDesc(), pg_product.getItem_id(), 0);
            ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
        }
        dismiss();
    }

    public void dismiss() {
        if (!this.isOnDialogClickHandled) {
            u.a().b("Premium pop-up", "Dismiss");
        }
        super.dismiss();
    }
}

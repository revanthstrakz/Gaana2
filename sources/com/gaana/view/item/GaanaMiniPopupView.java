package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.TrialProductFeature;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.PurchaseGoogleManager.c;
import com.managers.PurchaseGoogleManager.d;
import com.managers.ag;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.i;
import com.managers.u;
import com.services.l.as;
import com.services.l.au;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;

public class GaanaMiniPopupView extends BottomSheetDialog implements OnClickListener {
    private TextView additionalText;
    private BLOCK_ACTION mBlockAction;
    private TrialProductFeature mBusinessObj;
    private Context mContext;
    private ProductItem mProduct;
    private View mView;
    private as onTrialSuccess;
    private String souceType;
    private TextView topHeaderTitle;

    public GaanaMiniPopupView(Context context, TrialProductFeature trialProductFeature, BLOCK_ACTION block_action) {
        super(context);
        this.mContext = context;
        this.mBlockAction = block_action;
        this.mBusinessObj = trialProductFeature;
        init(context, trialProductFeature);
    }

    public GaanaMiniPopupView(Context context, TrialProductFeature trialProductFeature, as asVar, String str) {
        super(context);
        this.mContext = context;
        this.mBusinessObj = trialProductFeature;
        this.onTrialSuccess = asVar;
        this.souceType = str;
        init(context, trialProductFeature);
    }

    private void init(Context context, final TrialProductFeature trialProductFeature) {
        if (trialProductFeature != null) {
            an.a().a("click", "ac", "", "download", "", "subscription popup", "", "");
            this.mView = LayoutInflater.from(context).inflate(R.layout.popup_google_trial_view_layout, null);
            TextView textView = (TextView) this.mView.findViewById(R.id.terms_conditions_text);
            final Button button = (Button) this.mView.findViewById(R.id.payNowButton);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.mView.findViewById(R.id.coordinator_layout);
            setContentView(this.mView);
            BottomSheetBehavior.from((RelativeLayout) this.mView.findViewById(R.id.layout)).setState(3);
            this.mProduct = trialProductFeature.getPg_product();
            this.topHeaderTitle = (TextView) this.mView.findViewById(R.id.topHeaderTitle);
            ((TextView) this.mView.findViewById(R.id.subTitleText)).setText(trialProductFeature.getMessage_text());
            if (trialProductFeature.getIs_default_pack() == 1) {
                button.setVisibility(0);
                button.setOnClickListener(this);
            }
            if (trialProductFeature.getIs_more_option() == 1) {
                TextView textView2 = (TextView) this.mView.findViewById(R.id.moreOptionText);
                textView2.setVisibility(0);
                textView2.setOnClickListener(this);
                textView2.setText("Delete Old Downloads");
            }
            if (!TextUtils.isEmpty(trialProductFeature.getCta_text())) {
                button.setText(trialProductFeature.getCta_text());
            }
            this.additionalText = (TextView) this.mView.findViewById(R.id.additionalText);
            textView.setVisibility(8);
            if (this.souceType.equals("pl")) {
                u.a().a("Gaana Plus Mini", "View", "bulklimit");
            } else {
                u.a().a("Gaana Plus Mini", "View", "tracklimit");
            }
            final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
            String str = "";
            if (this.mProduct != null && !TextUtils.isEmpty(this.mProduct.getP_payment_mode()) && this.mProduct.getP_payment_mode().equalsIgnoreCase("android")) {
                str = this.mProduct.getP_id();
            } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || this.mProduct == null || TextUtils.isEmpty(this.mProduct.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !this.mProduct.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                str = e.getIntro_config().getIntro_p_id();
            }
            if (trialProductFeature.getHeader_text() != null && trialProductFeature.getHeader_text().contains("&&&&") && !TextUtils.isEmpty(str)) {
                PurchaseGoogleManager.a(this.mContext, null).a(str, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        if (cVar != null) {
                            CharSequence c = cVar.a() ? cVar.c() : cVar.b();
                            if (!TextUtils.isEmpty(c)) {
                                GaanaMiniPopupView.this.topHeaderTitle.setText(trialProductFeature.getHeader_text().replace("&&&&", c));
                            }
                            if (cVar.a() && e != null) {
                                String intro_cta_text = e.getIntro_config().getIntro_cta_text();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    button.setText(intro_cta_text);
                                }
                                e.getIntro_config().getIntro_tnc_link();
                                return;
                            }
                            return;
                        }
                        GaanaMiniPopupView.this.topHeaderTitle.setText(trialProductFeature.getHeader_text().replace("&&&&", GaanaMiniPopupView.this.mProduct.getP_cost()));
                    }
                });
            } else if (trialProductFeature.getHeader_text() == null || !trialProductFeature.getHeader_text().contains("&&&&")) {
                this.topHeaderTitle.setText(trialProductFeature.getHeader_text());
            } else {
                this.topHeaderTitle.setText(trialProductFeature.getHeader_text().replace("&&&&", this.mProduct.getP_cost()));
            }
            if (TextUtils.isEmpty(trialProductFeature.getAdditional_text())) {
                this.additionalText.setVisibility(8);
            } else if (!trialProductFeature.getAdditional_text().contains("&&&&")) {
                this.additionalText.setText(trialProductFeature.getAdditional_text());
            } else if (!TextUtils.isEmpty(str)) {
                PurchaseGoogleManager.a(this.mContext, null).a(str, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        if (cVar != null) {
                            CharSequence c = cVar.a() ? cVar.c() : cVar.b();
                            if (!TextUtils.isEmpty(c)) {
                                GaanaMiniPopupView.this.additionalText.setText(trialProductFeature.getAdditional_text().replace("&&&&", c));
                            }
                            if (cVar.a() && e != null) {
                                String intro_cta_text = e.getIntro_config().getIntro_cta_text();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    button.setText(intro_cta_text);
                                }
                                e.getIntro_config().getIntro_tnc_link();
                                return;
                            }
                            return;
                        }
                        GaanaMiniPopupView.this.additionalText.setText(trialProductFeature.getAdditional_text().replace("&&&&", GaanaMiniPopupView.this.mProduct.getP_cost()));
                    }
                });
            }
            if (this.mBlockAction != null && this.mBlockAction == BLOCK_ACTION.SKIP) {
                u.a().a("Skip Count", "Paid Pop Up", "Default");
            }
        }
    }

    public void setSourceType(String str) {
        this.souceType = str;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.moreOptionText) {
            if (this.souceType.equals("pl")) {
                u.a().a("Gaana Plus Mini", "Delete", "bulklimit");
            } else {
                u.a().a("Gaana Plus Mini", "Delete", "tracklimit");
            }
            BaseGaanaFragment downloadDetailsFragment = new DownloadDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("DOWNLOAD_EDIT_PARAM", true);
            i.a().d();
            downloadDetailsFragment.setArguments(bundle);
            ((GaanaActivity) this.mContext).displayFragment(downloadDetailsFragment);
            dismiss();
        } else if (id == R.id.payNowButton) {
            if (this.souceType.equals("pl")) {
                u.a().a("Gaana Plus Mini", "Upgrade", "bulklimit");
            } else {
                u.a().a("Gaana Plus Mini", "Upgrade", "tracklimit");
            }
            if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                Constants.k = true;
            }
            Util.a(this.mContext, this.mBusinessObj, this.mBlockAction, this.onTrialSuccess);
            dismiss();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void startPayment() {
        ag.a(this.mContext).a("Trial Popup", "Gaana Plus");
        if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(this.mProduct.getAction())) {
            if (this.mBusinessObj.getIs_trial()) {
                u.a().a("Free Gaana+ pop up", "Click", "Default Plan");
            } else {
                u.a().a("Gaana+ subscription pop up", "Click", "Default Plan");
            }
            ag.a(this.mContext).a(this.mContext, this.mProduct, new a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    StringBuilder stringBuilder;
                    ag.a(GaanaMiniPopupView.this.mContext).a("", "", "success");
                    ((BaseActivity) GaanaMiniPopupView.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) GaanaMiniPopupView.this.mContext).hideProgressDialog();
                            ap.a().a(GaanaMiniPopupView.this.mContext);
                            Util.aa();
                            aj.a().a(GaanaMiniPopupView.this.mContext, GaanaMiniPopupView.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                            if (Util.v(GaanaMiniPopupView.this.mContext)) {
                                Intent intent = new Intent(GaanaMiniPopupView.this.mContext, GaanaActivity.class);
                                intent.setFlags(71303168);
                                GaanaMiniPopupView.this.mContext.startActivity(intent);
                            }
                        }
                    });
                    if (!(Util.s() == null || TextUtils.isEmpty(GaanaMiniPopupView.this.mProduct.getP_payment_mode()))) {
                        String p_payment_mode = GaanaMiniPopupView.this.mProduct.getP_payment_mode();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Success; ");
                        stringBuilder.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                    }
                    if (GaanaMiniPopupView.this.mBlockAction != null && GaanaMiniPopupView.this.mBlockAction == BLOCK_ACTION.SKIP) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Plan_");
                        stringBuilder.append(GaanaMiniPopupView.this.mProduct.getDesc());
                        stringBuilder.append("_Success");
                        u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
                    }
                }

                public void onFailure(String str, String str2) {
                    StringBuilder stringBuilder;
                    ag.a(GaanaMiniPopupView.this.mContext).a(str, "", str2);
                    aj.a().a(GaanaMiniPopupView.this.mContext, str);
                    if (!(Util.s() == null || GaanaMiniPopupView.this.mProduct == null || TextUtils.isEmpty(GaanaMiniPopupView.this.mProduct.getP_payment_mode()))) {
                        String p_payment_mode = GaanaMiniPopupView.this.mProduct.getP_payment_mode();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failure; ");
                        stringBuilder.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                    }
                    if (GaanaMiniPopupView.this.mBlockAction != null && GaanaMiniPopupView.this.mBlockAction == BLOCK_ACTION.SKIP) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Default Plan_");
                        stringBuilder.append(GaanaMiniPopupView.this.mProduct.getDesc());
                        stringBuilder.append("_Fail");
                        u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
                    }
                }
            }, this.mProduct.getItem_id(), this.mProduct.getDesc());
        }
    }

    private void openProductListingScreen() {
        if (this.mBlockAction != null && this.mBlockAction == BLOCK_ACTION.SKIP) {
            u.a().a("Skip Count", "Paid Pop Up", "Subscription Screen");
        }
        if (this.mBusinessObj.getIs_trial()) {
            u.a().a("Free Gaana+ pop up", "Click", "More options");
        } else {
            u.a().a("Gaana+ subscription pop up", "Click", "More options");
        }
        GaanaApplication.getInstance().setSidebarActiveBtn(R.id.upgradeButtonLayout);
        ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
    }
}

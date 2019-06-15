package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
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
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
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
import com.managers.u;
import com.services.l.as;
import com.services.l.au;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;

public class GoogleTrialPopUpView extends BottomSheetDialog implements OnClickListener {
    private TextView additionalText;
    private BLOCK_ACTION mBlockAction;
    private TrialProductFeature mBusinessObj;
    private Context mContext;
    private ProductItem mProduct;
    private View mView;
    private as onTrialSuccess;
    private String souceType;
    private TextView topHeaderTitle;

    public GoogleTrialPopUpView(Context context, TrialProductFeature trialProductFeature, BLOCK_ACTION block_action) {
        super(context);
        this.mContext = context;
        this.mBlockAction = block_action;
        this.mBusinessObj = trialProductFeature;
        init(context, trialProductFeature);
    }

    public GoogleTrialPopUpView(Context context, TrialProductFeature trialProductFeature, as asVar) {
        super(context);
        this.mContext = context;
        this.mBusinessObj = trialProductFeature;
        this.onTrialSuccess = asVar;
        init(context, trialProductFeature);
    }

    private void init(Context context, TrialProductFeature trialProductFeature) {
        if (trialProductFeature != null) {
            final TrialProductFeature trialProductFeature2;
            final GoogleIntroductoryPriceConfig googleIntroductoryPriceConfig;
            final Button button;
            final TextView textView;
            an.a().a("click", "ac", "", "download", "", "subscription popup", "", "");
            this.mView = LayoutInflater.from(context).inflate(R.layout.popup_google_trial_view_layout, null);
            TextView textView2 = (TextView) this.mView.findViewById(R.id.terms_conditions_text);
            Button button2 = (Button) this.mView.findViewById(R.id.payNowButton);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.mView.findViewById(R.id.coordinator_layout);
            setContentView(this.mView);
            BottomSheetBehavior.from((RelativeLayout) this.mView.findViewById(R.id.layout)).setState(3);
            if (trialProductFeature.getIs_trial()) {
                u.a().a("Free gaana+ pop up", "View", "Default Plan");
            } else {
                u.a().a("Gaana+ subscription pop up", "View", "Default Plan");
            }
            this.mProduct = trialProductFeature.getPg_product();
            this.topHeaderTitle = (TextView) this.mView.findViewById(R.id.topHeaderTitle);
            ((TextView) this.mView.findViewById(R.id.subTitleText)).setText(trialProductFeature.getMessage_text());
            if (trialProductFeature.getIs_default_pack() == 1) {
                button2.setVisibility(0);
                button2.setOnClickListener(this);
            }
            if (trialProductFeature.getIs_more_option() == 1) {
                TextView textView3 = (TextView) this.mView.findViewById(R.id.moreOptionText);
                textView3.setVisibility(0);
                textView3.setOnClickListener(this);
            }
            if (!TextUtils.isEmpty(trialProductFeature.getCta_text())) {
                button2.setText(trialProductFeature.getCta_text());
            }
            this.additionalText = (TextView) this.mView.findViewById(R.id.additionalText);
            if (trialProductFeature.getTermAndCondition() == null || TextUtils.isEmpty(trialProductFeature.getTermAndCondition())) {
                textView2.setVisibility(8);
            } else {
                setTandCButton(textView2, trialProductFeature.getTermAndCondition());
            }
            GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
            String str = "";
            if (this.mProduct != null && !TextUtils.isEmpty(this.mProduct.getP_payment_mode()) && this.mProduct.getP_payment_mode().equalsIgnoreCase("android")) {
                str = this.mProduct.getP_id();
            } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || this.mProduct == null || TextUtils.isEmpty(this.mProduct.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !this.mProduct.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                str = e.getIntro_config().getIntro_p_id();
            }
            String str2 = str;
            if (trialProductFeature.getHeader_text() != null && trialProductFeature.getHeader_text().contains("&&&&") && !TextUtils.isEmpty(str2)) {
                trialProductFeature2 = trialProductFeature;
                googleIntroductoryPriceConfig = e;
                button = button2;
                textView = textView2;
                PurchaseGoogleManager.a(this.mContext, null).a(str2, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        if (cVar != null) {
                            CharSequence c = cVar.a() ? cVar.c() : cVar.b();
                            if (!TextUtils.isEmpty(c)) {
                                GoogleTrialPopUpView.this.topHeaderTitle.setText(trialProductFeature2.getHeader_text().replace("&&&&", c));
                            }
                            if (cVar.a() && googleIntroductoryPriceConfig != null) {
                                String intro_cta_text = googleIntroductoryPriceConfig.getIntro_config().getIntro_cta_text();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    button.setText(intro_cta_text);
                                }
                                intro_cta_text = googleIntroductoryPriceConfig.getIntro_config().getIntro_tnc_link();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    GoogleTrialPopUpView.this.setTandCButton(textView, intro_cta_text);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        GoogleTrialPopUpView.this.topHeaderTitle.setText(trialProductFeature2.getHeader_text().replace("&&&&", GoogleTrialPopUpView.this.mProduct.getP_cost()));
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
            } else if (!TextUtils.isEmpty(str2)) {
                trialProductFeature2 = trialProductFeature;
                googleIntroductoryPriceConfig = e;
                button = button2;
                textView = textView2;
                PurchaseGoogleManager.a(this.mContext, null).a(str2, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        if (cVar != null) {
                            CharSequence c = cVar.a() ? cVar.c() : cVar.b();
                            if (!TextUtils.isEmpty(c)) {
                                GoogleTrialPopUpView.this.additionalText.setText(trialProductFeature2.getAdditional_text().replace("&&&&", c));
                            }
                            if (cVar.a() && googleIntroductoryPriceConfig != null) {
                                String intro_cta_text = googleIntroductoryPriceConfig.getIntro_config().getIntro_cta_text();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    button.setText(intro_cta_text);
                                }
                                intro_cta_text = googleIntroductoryPriceConfig.getIntro_config().getIntro_tnc_link();
                                if (!TextUtils.isEmpty(intro_cta_text)) {
                                    GoogleTrialPopUpView.this.setTandCButton(textView, intro_cta_text);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        GoogleTrialPopUpView.this.additionalText.setText(trialProductFeature2.getAdditional_text().replace("&&&&", GoogleTrialPopUpView.this.mProduct.getP_cost()));
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

    private void setTandCButton(TextView textView, final String str) {
        textView.setVisibility(0);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GoogleTrialPopUpView.this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", str);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                GoogleTrialPopUpView.this.mContext.startActivity(intent);
            }
        });
    }

    public void onClick(View view) {
        String str = "";
        if (this.souceType.equalsIgnoreCase("Download")) {
            str = "Download";
        } else if (this.souceType.equalsIgnoreCase("HDQuality")) {
            str = "Quality";
        }
        String str2 = str;
        str = "Not Available!";
        if (this.mBusinessObj.getPg_product() != null) {
            str = this.mBusinessObj.getPg_product().getDesc();
        }
        int id = view.getId();
        StringBuilder stringBuilder;
        if (id == R.id.moreOptionText) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Description: ");
            stringBuilder.append(str);
            an.a().e("click", "ac", "", str2, stringBuilder.toString(), "PYMT_PLAN", "", "");
            openProductListingScreen();
            dismiss();
        } else if (id == R.id.payNowButton) {
            if (!this.mBusinessObj.getPaymentMode().equalsIgnoreCase("simpl")) {
                if (this.mBusinessObj.getIs_trial()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Description: ");
                    stringBuilder.append(str);
                    an.a().e("click", "ac", "", str2, stringBuilder.toString(), "TRIAL", "", "");
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Description: ");
                    stringBuilder.append(str);
                    an.a().e("click", "ac", "", str2, stringBuilder.toString(), "PG", "", "");
                }
                if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                    Constants.k = true;
                }
                Util.a(this.mContext, this.mBusinessObj, this.mBlockAction, this.onTrialSuccess);
            } else if (Constants.bT) {
                new SimplPayBottomSheet(this.mContext, this.mBusinessObj.getPg_product()).show();
            }
            dismiss();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.mBlockAction != null && this.mBlockAction == BLOCK_ACTION.SKIP) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Default Plan+");
            stringBuilder.append(this.mProduct.getDesc());
            stringBuilder.append("_Abort");
            u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
        }
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
                    ag.a(GoogleTrialPopUpView.this.mContext).a("", "", "success");
                    ((BaseActivity) GoogleTrialPopUpView.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) GoogleTrialPopUpView.this.mContext).hideProgressDialog();
                            ap.a().a(GoogleTrialPopUpView.this.mContext);
                            Util.aa();
                            aj.a().a(GoogleTrialPopUpView.this.mContext, GoogleTrialPopUpView.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                            if (Util.v(GoogleTrialPopUpView.this.mContext)) {
                                Intent intent = new Intent(GoogleTrialPopUpView.this.mContext, GaanaActivity.class);
                                intent.setFlags(71303168);
                                GoogleTrialPopUpView.this.mContext.startActivity(intent);
                            }
                        }
                    });
                    if (!(Util.s() == null || TextUtils.isEmpty(GoogleTrialPopUpView.this.mProduct.getP_payment_mode()))) {
                        String p_payment_mode = GoogleTrialPopUpView.this.mProduct.getP_payment_mode();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Success; ");
                        stringBuilder.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                    }
                    if (GoogleTrialPopUpView.this.mBlockAction != null && GoogleTrialPopUpView.this.mBlockAction == BLOCK_ACTION.SKIP) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Plan_");
                        stringBuilder.append(GoogleTrialPopUpView.this.mProduct.getDesc());
                        stringBuilder.append("_Success");
                        u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
                    }
                }

                public void onFailure(String str, String str2) {
                    StringBuilder stringBuilder;
                    ag.a(GoogleTrialPopUpView.this.mContext).a(str, "", str2);
                    aj.a().a(GoogleTrialPopUpView.this.mContext, str);
                    if (!(Util.s() == null || GoogleTrialPopUpView.this.mProduct == null || TextUtils.isEmpty(GoogleTrialPopUpView.this.mProduct.getP_payment_mode()))) {
                        String p_payment_mode = GoogleTrialPopUpView.this.mProduct.getP_payment_mode();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failure; ");
                        stringBuilder.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                    }
                    if (GoogleTrialPopUpView.this.mBlockAction != null && GoogleTrialPopUpView.this.mBlockAction == BLOCK_ACTION.SKIP) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Default Plan_");
                        stringBuilder.append(GoogleTrialPopUpView.this.mProduct.getDesc());
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

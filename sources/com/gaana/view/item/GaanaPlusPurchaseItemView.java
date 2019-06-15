package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.i.b;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaMiniPurchaseFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.BaseItemView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.library.controls.CrossFadeImageView;
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
import com.models.GaanaMiniProduct;
import com.services.l.ad;
import com.services.l.au;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GaanaPlusPurchaseItemView extends BaseItemView implements OnClickListener {
    private String curr_symbol = "";
    private a mCallbacks = null;
    private int mCouponlayout = R.layout.purchase_coupen_layout;
    private int mLayoutResourceId = R.layout.gaana_plus_purchase_item_view;
    private ProductItem mProduct;
    List<String> mPurchaselist;
    private int position;
    private boolean showCouponApplyLayout;

    public GaanaPlusPurchaseItemView(Context context, BaseGaanaFragment baseGaanaFragment, boolean z, ProductItem productItem) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.showCouponApplyLayout = z;
        this.mProduct = productItem;
    }

    public View getPopulatedViewForCoupon(ViewGroup viewGroup, BusinessObject businessObject, int i, String str) {
        View newView = super.getNewView(this.mCouponlayout, viewGroup);
        newView.setTag(businessObject);
        initCouponUi(newView, str);
        return newView;
    }

    public View getPopulatedView(ViewGroup viewGroup, BusinessObject businessObject, int i, List<String> list) {
        View newView = super.getNewView(this.mLayoutResourceId, viewGroup);
        newView.setOnClickListener(this);
        newView.setTag(businessObject);
        this.position = i;
        this.mPurchaselist = list;
        initUI(newView);
        return newView;
    }

    public View getPopulatedViewGaanaMini(ViewGroup viewGroup, BusinessObject businessObject, a aVar, int i) {
        View newView = super.getNewView(R.layout.gaana_mini_purchase_item_view, viewGroup);
        newView.setOnClickListener(this);
        newView.setTag(businessObject);
        this.position = i;
        this.mCallbacks = aVar;
        initUI(newView);
        return newView;
    }

    private void hideSoftkeyBoard(View view) {
        EditText editText = (EditText) view.findViewById(R.id.couponEditText);
        if (editText != null) {
            ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    private void initCouponUi(final View view, String str) {
        StringBuilder stringBuilder;
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            ((RelativeLayout) view.findViewById(R.id.editTextLayout)).setVisibility(0);
            final EditText editText = (EditText) view.findViewById(R.id.couponEditText);
            TextView textView = (TextView) view.findViewById(R.id.couponApplyButton);
            textView.setTypeface(null, 1);
            changeButtonTheme(editText, textView);
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String obj = editText.getText().toString();
                    if (obj.length() > 0) {
                        GaanaPlusPurchaseItemView.this.hideSoftkeyBoard(view);
                        ((PaymentDetailFragment) GaanaPlusPurchaseItemView.this.mFragment).b(obj);
                    }
                }
            });
            if (!(str == null || TextUtils.isEmpty(str))) {
                editText.setText(str);
                if (str.length() >= 10) {
                    textView.setBackgroundColor(this.mContext.getResources().getColor(R.color.on_board_language_save_button_orange_color));
                    textView.setTextColor(this.mContext.getResources().getColor(R.color.white));
                }
            }
        } else {
            ((RelativeLayout) view.findViewById(R.id.editTextLayout)).setVisibility(8);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.actual_product_cost_text);
        textView2.setTypeface(null, 1);
        final TextView textView3 = (TextView) view.findViewById(R.id.actual_product_cost);
        textView3.setTypeface(null, 1);
        final ProductItem productItem = (ProductItem) view.getTag();
        if (!(productItem == null || TextUtils.isEmpty(productItem.getP_cost_curr()))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(productItem.getP_cost_curr());
            stringBuilder.append(" ");
            this.curr_symbol = stringBuilder.toString();
        }
        String str2 = "";
        GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
        if (this.mProduct != null && !TextUtils.isEmpty(this.mProduct.getP_payment_mode()) && this.mProduct.getP_payment_mode().equalsIgnoreCase("android")) {
            str2 = this.mProduct.getP_id();
        } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || this.mProduct == null || TextUtils.isEmpty(this.mProduct.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !this.mProduct.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
            str2 = e.getIntro_config().getIntro_p_id();
        }
        if (!(productItem == null || TextUtils.isEmpty(productItem.getP_cost()))) {
            if (TextUtils.isEmpty(str2)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.curr_symbol);
                stringBuilder.append(productItem.getP_cost());
                textView3.setText(stringBuilder.toString());
            } else {
                PurchaseGoogleManager.a(this.mContext, null).a(str2, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        CharSequence charSequence = "";
                        if (cVar != null) {
                            charSequence = cVar.a() ? cVar.c() : cVar.b();
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            TextView textView = textView3;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(GaanaPlusPurchaseItemView.this.curr_symbol);
                            stringBuilder.append(productItem.getP_cost());
                            textView.setText(stringBuilder.toString());
                            return;
                        }
                        textView3.setText(charSequence);
                    }
                });
            }
            textView3.setVisibility(0);
        }
        if (!TextUtils.isEmpty(productItem.getP_cost())) {
            textView2.setText(productItem.getDesc());
        }
    }

    private void changeButtonTheme(EditText editText, final TextView textView) {
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 10) {
                    textView.setBackgroundResource(R.drawable.rounded_apply_orange_button);
                    textView.setTextColor(GaanaPlusPurchaseItemView.this.mContext.getResources().getColor(R.color.white));
                } else if (editable.length() >= 10 && editable.length() != 0) {
                } else {
                    if (Constants.l) {
                        textView.setBackgroundResource(R.drawable.rounded_apply_button_white);
                        textView.setTextColor(GaanaPlusPurchaseItemView.this.mContext.getResources().getColor(R.color.black_alfa_50));
                        return;
                    }
                    textView.setBackgroundResource(R.drawable.rounded_apply_button);
                    textView.setTextColor(GaanaPlusPurchaseItemView.this.mContext.getResources().getColor(R.color.black_alfa_50));
                }
            }
        });
    }

    private void initUI(View view) {
        StringBuilder stringBuilder;
        ProductItem productItem = (ProductItem) view.getTag();
        if (!(productItem == null || TextUtils.isEmpty(productItem.getP_cost_curr()))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(productItem.getP_cost_curr());
            stringBuilder.append(" ");
            this.curr_symbol = stringBuilder.toString();
        }
        TextView textView = (TextView) view.findViewById(R.id.purchase_name);
        TextView textView2 = (TextView) view.findViewById(R.id.purchase_special_offer);
        ImageView imageView = (CrossFadeImageView) view.findViewById(R.id.purchase_image);
        TextView textView3 = (TextView) view.findViewById(R.id.purchase_Reduce_cost);
        TextView textView4 = (TextView) view.findViewById(R.id.purchase_final_cost);
        TextView textView5 = (TextView) view.findViewById(R.id.purchase_final_cost_text);
        if (!(productItem == null || TextUtils.isEmpty(productItem.getP_payment_mode()))) {
            this.mPurchaselist.add(productItem.getP_payment_mode());
            if (productItem.getP_payment_mode().equalsIgnoreCase("android")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_playstore));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("hermes_android")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_hermes));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("paytm")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_paytm));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("operator")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_operator));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("citrus")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_citrus_wallet));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("ccdc")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ccdc_icon));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("netbanking")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.net_banking_icon));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("fc_wallet")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_freecharge_wallet));
            } else if (productItem.getP_payment_mode().equalsIgnoreCase("paypal")) {
                imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.icon_paypal));
            }
            if (!(com.services.d.a().b("PREFERENCE_KEY_DATA_SAVE_MODE", false, false) || TextUtils.isEmpty(productItem.getProductArtwork()))) {
                i.a().a(imageView, productItem.getProductArtwork());
            }
        }
        if (productItem == null || TextUtils.isEmpty(productItem.getP_pay_desc())) {
            textView.setVisibility(8);
        } else {
            textView.setText(productItem.getP_pay_desc());
            textView.setVisibility(0);
        }
        if (productItem == null || TextUtils.isEmpty(productItem.getP_discounted_cost())) {
            textView5.setVisibility(8);
            textView4.setVisibility(8);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.curr_symbol);
            stringBuilder.append(productItem.getP_discounted_cost());
            textView4.setText(stringBuilder.toString());
            textView5.setVisibility(0);
            textView4.setVisibility(0);
        }
        if (productItem == null || TextUtils.isEmpty(productItem.getP_discounted_text())) {
            textView3.setVisibility(8);
        } else {
            textView3.setText(productItem.getP_discounted_text());
            textView3.setVisibility(0);
        }
        String str = "";
        GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
        if (this.mProduct != null && !TextUtils.isEmpty(this.mProduct.getP_payment_mode()) && this.mProduct.getP_payment_mode().equalsIgnoreCase("android")) {
            str = this.mProduct.getP_id();
        } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || this.mProduct == null || TextUtils.isEmpty(this.mProduct.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !this.mProduct.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
            str = e.getIntro_config().getIntro_p_id();
        }
        if (!(productItem == null || TextUtils.isEmpty(productItem.getP_cost()) || TextUtils.isEmpty(str))) {
            PurchaseGoogleManager.a(this.mContext, null).a(str, new d() {
                public void onGoolgeProductPriceQueryConpleted(c cVar) {
                    if (cVar == null) {
                        return;
                    }
                    if (cVar.a()) {
                        cVar.c();
                    } else {
                        cVar.b();
                    }
                }
            });
        }
        if (productItem == null || TextUtils.isEmpty(productItem.getP_spec_offer())) {
            textView2.setVisibility(8);
            return;
        }
        textView2.setText(productItem.getP_spec_offer());
        textView2.setVisibility(0);
    }

    private void setDurationValue(ProductItem productItem) {
        if (this.mProduct != null && !TextUtils.isEmpty(this.mProduct.getDuration_days())) {
            productItem.setDurationDays(this.mProduct.getDuration_days());
        }
    }

    public void onClick(View view) {
        setDurationValue((ProductItem) view.getTag());
        final ProductItem productItem = (ProductItem) view.getTag();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Payment Mode: ");
        stringBuilder.append(productItem.getP_pay_desc());
        an.a().e("click", "ac", "", "PRODUCT", stringBuilder.toString(), "PG", "", "");
        if (this.mPurchaselist != null) {
            u.a().a("Subscription_Payments", this.mPurchaselist.toString(), productItem.getP_payment_mode());
        }
        if (this.mFragment instanceof GaanaMiniPurchaseFragment) {
            this.mProduct = productItem;
            u.a().a(productItem, this.mProduct.getItem_id());
            ag.a(this.mContext).a(((GaanaMiniPurchaseFragment) this.mFragment).b());
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                startPurchase(productItem);
                return;
            } else {
                ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                    public void onLoginSuccess() {
                        ((BaseActivity) GaanaPlusPurchaseItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), GaanaPlusPurchaseItemView.this.mContext.getString(R.string.fetching_details_from_server));
                        ((GaanaMiniPurchaseFragment) GaanaPlusPurchaseItemView.this.mFragment).a(new b<Object>() {
                            public void onResponse(Object obj) {
                                if (obj != null && (obj instanceof GaanaMiniProduct)) {
                                    ArrayList a = ((GaanaMiniProduct) obj).a();
                                    if (!(a == null || GaanaPlusPurchaseItemView.this.mProduct == null)) {
                                        Iterator it = a.iterator();
                                        while (it.hasNext()) {
                                            if (GaanaPlusPurchaseItemView.this.mProduct.getP_id().equals(((ProductItem) it.next()).getP_id())) {
                                                GaanaPlusPurchaseItemView.this.startPurchase(productItem);
                                                return;
                                            }
                                        }
                                    }
                                }
                                aj.a().a(GaanaPlusPurchaseItemView.this.mContext, "This product purchase is not allowed for you");
                            }
                        });
                    }
                }, this.mContext.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                return;
            }
        }
        ag.a(this.mContext).a(null);
        startPurchase(productItem);
    }

    private void startPurchase(final ProductItem productItem) {
        if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
            u.a().a(productItem, this.mProduct.getDesc(), this.mProduct.getItem_id(), this.position);
            ag.a(this.mContext).a(this.mContext, productItem, new a() {
                public void onPurchaseFinished(final SubscriptionPurchaseType subscriptionPurchaseType) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Purchase Type: ");
                    stringBuilder.append(subscriptionPurchaseType.name());
                    an.a().e("click", "ac", "", "PG", stringBuilder.toString(), "SUCCESS", "", "");
                    ag.a(GaanaPlusPurchaseItemView.this.mContext).a("", "", "success");
                    ((BaseActivity) GaanaPlusPurchaseItemView.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            GaanaPlusPurchaseItemView.this.onPaymentCompleted(subscriptionPurchaseType);
                        }
                    });
                    if (Util.s() != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                        String p_payment_mode = productItem.getP_payment_mode();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Success; ");
                        stringBuilder2.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder2.toString());
                    }
                }

                public void onFailure(String str, String str2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(" | ");
                    stringBuilder.append(str2);
                    an.a().e("click", "ac", "", "PG", stringBuilder.toString(), "FAIL", "", "");
                    ag.a(GaanaPlusPurchaseItemView.this.mContext).a(str, "", str2);
                    if (GaanaPlusPurchaseItemView.this.mCallbacks != null) {
                        GaanaPlusPurchaseItemView.this.mCallbacks.onFailure(str, str2);
                    } else if (!TextUtils.isEmpty(str)) {
                        aj.a().a(GaanaPlusPurchaseItemView.this.mContext, str);
                    }
                    if (Util.s() != null && productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                        String p_payment_mode = productItem.getP_payment_mode();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Failure; ");
                        stringBuilder2.append(Util.s());
                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder2.toString());
                    }
                }
            }, this.mProduct.getItem_id(), this.mProduct.getDesc());
        } else if (!NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
            if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(productItem.getAction()) && !TextUtils.isEmpty(productItem.getWeb_url())) {
                Intent intent = new Intent(this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", productItem.getWeb_url());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                this.mContext.startActivity(intent);
            } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(productItem.getAction())) {
                u.a().a(productItem, this.mProduct.getItem_id());
                u.a().a(productItem, this.mProduct.getDesc(), this.mProduct.getItem_id(), this.position);
                ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
            } else {
                com.services.c.a(this.mContext).a(this.mContext, productItem.getAction(), GaanaApplication.getInstance());
            }
        }
    }

    private void onPaymentCompleted(SubscriptionPurchaseType subscriptionPurchaseType) {
        MoEngage.getInstance().reportUserInfoChanged(this.mAppState.getCurrentUser());
        if (this.mCallbacks == null) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            ap.a().a(this.mContext);
            Util.aa();
            aj.a().a(this.mContext, getContext().getString(R.string.enjoy_using_gaana_plus));
            if (Util.v(this.mContext)) {
                Intent intent = new Intent(this.mContext, GaanaActivity.class);
                intent.setFlags(71303168);
                this.mContext.startActivity(intent);
                return;
            }
            return;
        }
        this.mCallbacks.onPurchaseFinished(subscriptionPurchaseType);
    }
}

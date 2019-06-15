package com.gaana.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel;
import com.gaana.models.PaymentProductModel.PageHeaderConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.payu.custombrowser.util.CBConstant;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;

public class FailedPaymentCardView extends BaseItemView {
    private String eventAction = "";
    private String eventCategory = "";
    private Context mContext;
    private PageHeaderConfig mlPageHeaderConfig = null;

    public static class FailedPaymentCardViewHolder extends ViewHolder {
        public FailedPaymentCardViewHolder(View view) {
            super(view);
        }
    }

    public FailedPaymentCardView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.eventAction = "Product_Card";
    }

    public FailedPaymentCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.eventAction = "Home_Card";
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FailedPaymentCardViewHolder(this.mInflater.inflate(R.layout.failed_payment_card_view, viewGroup, false));
    }

    public View getPopulateView(LinearLayout linearLayout, PageHeaderConfig pageHeaderConfig) {
        this.mlPageHeaderConfig = pageHeaderConfig;
        callApi(linearLayout, LayoutInflater.from(this.mContext).inflate(R.layout.failed_payment_card_view, linearLayout, false));
        return null;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        callApi(null, viewHolder.itemView);
        return viewHolder.itemView;
    }

    private void callApi(final LinearLayout linearLayout, final View view) {
        if (view.getLayoutParams() != null) {
            view.getLayoutParams().width = 0;
            view.getLayoutParams().height = 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=nxtgen_getcard");
        stringBuilder.append(ag.a(this.mContext).d());
        String stringBuilder2 = stringBuilder.toString();
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(PaymentProductModel.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                PaymentProductModel paymentProductModel = (PaymentProductModel) obj;
                if (paymentProductModel != null && paymentProductModel.getBanner() != null) {
                    FailedPaymentCardView.this.initView(paymentProductModel.getBanner(), linearLayout, view);
                    if (view.getLayoutParams() != null) {
                        view.getLayoutParams().width = -1;
                        view.getLayoutParams().height = -2;
                    }
                    if (!TextUtils.isEmpty(FailedPaymentCardView.this.eventAction) && FailedPaymentCardView.this.eventAction.equals("Home_Card")) {
                        view.setPadding(0, FailedPaymentCardView.this.mContext.getResources().getDimensionPixelOffset(R.dimen.horizontal_margin), 0, FailedPaymentCardView.this.mContext.getResources().getDimensionPixelOffset(R.dimen.horizontal_margin));
                    }
                }
            }
        }, uRLManager);
    }

    private void initView(final ProductItem productItem, LinearLayout linearLayout, View view) {
        String typeOfCard = productItem.getTypeOfCard();
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.bannerCard);
        TextView textView = (TextView) view.findViewById(R.id.TopHeaderText);
        ImageView imageView = (ImageView) view.findViewById(R.id.repay_button);
        TextView textView2 = (TextView) view.findViewById(R.id.failed_description_text1);
        TextView textView3 = (TextView) view.findViewById(R.id.product_orgianl_cost);
        TextView textView4 = (TextView) view.findViewById(R.id.failed_description_text2);
        textView4 = (TextView) view.findViewById(R.id.failed_description_offer);
        TextView textView5 = (TextView) view.findViewById(R.id.offer_text);
        if (TextUtils.isEmpty(productItem.getP_pay_desc())) {
            textView2.setText(productItem.getP_desc());
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(productItem.getP_desc());
            stringBuilder.append(" | ");
            stringBuilder.append(productItem.getP_pay_desc());
            textView2.setText(stringBuilder.toString());
        }
        if (productItem == null || TextUtils.isEmpty(productItem.getCard_identifier())) {
            u.a().b("A/B Testing", "Generic");
        } else {
            u.a().b("A/B Testing", productItem.getCard_identifier());
        }
        textView3.setVisibility(0);
        textView3.setText(getDefaultActionText(productItem.getP_cost(), productItem.getP_discounted_cost(), productItem.getP_curr_code()));
        if (!TextUtils.isEmpty(productItem.getP_discounted_cost())) {
            textView5.setVisibility(8);
            textView4.setVisibility(8);
        }
        textView.setTypeface(textView.getTypeface(), 1);
        if (!TextUtils.isEmpty(typeOfCard) && typeOfCard.equalsIgnoreCase("multi_fail")) {
            textView.setText(this.mContext.getString(R.string.last_transaction_failed));
            if (TextUtils.isEmpty(productItem.getP_discounted_cost())) {
                textView4.setVisibility(8);
                textView5.setVisibility(8);
            } else {
                textView4.setVisibility(0);
                textView5.setVisibility(0);
                textView4.setText(productItem.getMsg_txt());
            }
            this.eventCategory = "Failed_Transaction";
        } else if (!TextUtils.isEmpty(typeOfCard) && typeOfCard.equalsIgnoreCase(CBConstant.FAIL)) {
            textView.setText(this.mContext.getString(R.string.last_transaction_failed_tab_to_retry));
            textView4.setVisibility(8);
            textView5.setVisibility(8);
            this.eventCategory = "Failed_Transaction";
        } else if (!TextUtils.isEmpty(typeOfCard) && typeOfCard.equalsIgnoreCase("abandoned")) {
            textView.setText(this.mContext.getString(R.string.continue_buying));
            textView4.setVisibility(8);
            textView5.setVisibility(8);
            this.eventCategory = "Abandoned_Transaction";
        }
        ag.a(this.mContext).a(this.eventAction, this.eventCategory);
        TextUtils.isEmpty(productItem.getP_discounted_cost());
        if (!(linearLayout == null || TextUtils.isEmpty(typeOfCard))) {
            linearLayout.setVisibility(0);
            linearLayout.addView(view);
        }
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (productItem == null || TextUtils.isEmpty(productItem.getSessionId())) {
                    ag.a(FailedPaymentCardView.this.mContext).b(null);
                } else {
                    ag.a(FailedPaymentCardView.this.mContext).b(productItem.getSessionId());
                }
                String p_discounted_cost = productItem.getP_discounted_cost();
                if (TextUtils.isEmpty(p_discounted_cost)) {
                    p_discounted_cost = "OFFER_NOT_SET";
                }
                String p_payment_mode = productItem.getP_payment_mode();
                if (TextUtils.isEmpty(p_payment_mode)) {
                    p_payment_mode = "PM_NOT_SET";
                }
                u a = u.a();
                String access$300 = FailedPaymentCardView.this.eventCategory;
                String access$100 = FailedPaymentCardView.this.eventAction;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(productItem.getDesc());
                stringBuilder.append("; ");
                stringBuilder.append(productItem.getP_cost());
                stringBuilder.append("; ");
                stringBuilder.append(p_payment_mode);
                stringBuilder.append("; ");
                stringBuilder.append(p_discounted_cost);
                a.a(access$300, access$100, stringBuilder.toString());
                FailedPaymentCardView.this.handleProductItemClick(productItem, -1);
            }
        });
    }

    private SpannableStringBuilder getDefaultActionText(String str, String str2, String str3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("");
        spannableStringBuilder.append(str3);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-1), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.dimen_14sp)), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(" ");
        int length = spannableStringBuilder.length();
        if (TextUtils.isEmpty(str2)) {
            spannableStringBuilder.append(str);
        } else {
            spannableStringBuilder.append(str2);
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-1), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.dimen_17sp)), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new StyleSpan(1), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(" ");
        length = spannableStringBuilder.length();
        if (!TextUtils.isEmpty(str2)) {
            spannableStringBuilder.append(str);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(-1), length, spannableStringBuilder.length(), 33);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.dimen_14sp)), length, spannableStringBuilder.length(), 33);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), length, spannableStringBuilder.length(), 33);
        }
        return spannableStringBuilder;
    }

    public void handleProductItemClick(final ProductItem productItem, int i) {
        if (productItem != null && !TextUtils.isEmpty(productItem.getAction())) {
            if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
                u.a().a(productItem, productItem.getItem_id());
                ag.a(this.mContext).a(this.mContext, productItem, new ag.a() {
                    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                        ag.a(FailedPaymentCardView.this.mContext).a("", "", "success");
                        ((BaseActivity) FailedPaymentCardView.this.mContext).updateUserStatus(new au() {
                            public void onUserStatusUpdated() {
                                ((BaseActivity) FailedPaymentCardView.this.mContext).hideProgressDialog();
                                ap.a().a(FailedPaymentCardView.this.mContext);
                                Util.aa();
                                aj.a().a(FailedPaymentCardView.this.mContext, FailedPaymentCardView.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                                if (Util.v(FailedPaymentCardView.this.mContext)) {
                                    Intent intent = new Intent(FailedPaymentCardView.this.mContext, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    FailedPaymentCardView.this.mContext.startActivity(intent);
                                }
                            }
                        });
                        if (Util.s() != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                            String p_payment_mode = productItem.getP_payment_mode();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Success; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                        }
                    }

                    public void onFailure(String str, String str2) {
                        if (!TextUtils.isEmpty(str)) {
                            aj.a().a(FailedPaymentCardView.this.mContext, str);
                        }
                        ag.a(FailedPaymentCardView.this.mContext).a(str, "", str2);
                        if (Util.s() != null && productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                            String p_payment_mode = productItem.getP_payment_mode();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Failure; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                        }
                    }
                }, productItem.getItem_id(), productItem.getDesc());
            } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
                BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
                paymentDetailFragment.a(this.mlPageHeaderConfig);
                paymentDetailFragment.a(productItem);
                ((GaanaActivity) this.mContext).displayFragment(paymentDetailFragment);
            } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(productItem.getAction()) && !TextUtils.isEmpty(productItem.getWeb_url())) {
                Intent intent = new Intent(this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", productItem.getWeb_url());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                this.mContext.startActivity(intent);
            } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(productItem.getAction())) {
                u.a().a(productItem, productItem.getItem_id());
                u.a().a(productItem, productItem.getDesc(), productItem.getItem_id(), i);
                ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
            }
        }
    }
}

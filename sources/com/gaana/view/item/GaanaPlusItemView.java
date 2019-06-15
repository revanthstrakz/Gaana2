package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.ListAdapterSectionIndexer;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.ProductsPaymentsView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.PurchaseGoogleManager.a;
import com.managers.URLManager;
import com.managers.ag;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.g;
import com.managers.u;
import com.moengage.inapp.InAppMessage;
import com.payment.subscriptionProfile.SubscriptionProfile;
import com.payment.subscriptionProfile.b;
import com.payment.subscriptionProfile.c;
import com.payment.subscriptionProfile.d;
import com.payment.subscriptionProfile.e;
import com.payment.subscriptionProfile.f;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import java.util.Date;
import org.json.JSONObject;

public class GaanaPlusItemView extends BaseItemView implements OnRefreshListener, OnClickListener, a {
    private String couponCode;
    private final int currentCard;
    private final int expiredCard;
    private final int gaanaCard;
    private boolean isGaanaMiniUser;
    private String itemId;
    private ListAdapterSectionIndexer mAdapter;
    private Button mButtonBuy;
    private TextView mFeedBackButton;
    private String monthDownloadCounts;
    private String productId;
    private final int recommendedCard;
    private String sourceType;
    private SwipeRefreshLayout swipeToRefreshScrollView;
    private String totalDownloadCounts;
    private TextView txtMemberSince;
    private TextView txtReferForFreeGaana;
    private TextView txtRenewalDate;
    private TextView txtSubscriptionStatus;
    private boolean upSellPage;

    public void onProductsQueryCompleted() {
    }

    public void showPriceDialog(String str) {
    }

    public GaanaPlusItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.currentCard = 1;
        this.gaanaCard = 2;
        this.recommendedCard = 3;
        this.expiredCard = 4;
        this.mButtonBuy = null;
        this.mFeedBackButton = null;
        this.monthDownloadCounts = "NA";
        this.totalDownloadCounts = "NA";
        this.txtSubscriptionStatus = null;
        this.txtRenewalDate = null;
        this.txtMemberSince = null;
        this.txtReferForFreeGaana = null;
        this.upSellPage = false;
        this.isGaanaMiniUser = false;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mLayoutId = R.layout.view_profile_subscription;
    }

    public void setSourceType(String str) {
        this.sourceType = str;
    }

    public void setFragment(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mFragment = baseGaanaFragment;
        this.mContext = context;
    }

    public boolean getIfUpSellPage() {
        return this.upSellPage;
    }

    public void setmAdapter(ListAdapterSectionIndexer listAdapterSectionIndexer) {
        this.mAdapter = listAdapterSectionIndexer;
    }

    public View getPoplatedView(ViewGroup viewGroup) {
        return getDataFilledView(this.mView, viewGroup);
    }

    private View getDataFilledView(View view, ViewGroup viewGroup) {
        if ((this.mFragment instanceof SettingsDetailFragment) || !this.mAppState.getCurrentUser().getLoginStatus()) {
            this.mFragment.getmToolbar().setVisibility(8);
            ProductsPaymentsView productsPaymentsView = new ProductsPaymentsView(this.mContext, this.mFragment);
            productsPaymentsView.setProductAndItemId(this.itemId, this.productId);
            productsPaymentsView.setCouponCode(this.couponCode);
            this.itemId = null;
            this.productId = null;
            this.couponCode = null;
            this.mView = productsPaymentsView.getPopulatedView(viewGroup, null);
            this.upSellPage = true;
        } else {
            this.mView = this.mInflater.inflate(R.layout.view_profile_subscription, viewGroup, false);
            populateSubscriptionView((ViewGroup) this.mView.findViewById(R.id.containerView));
            showMemeberData();
            getDownloadCounts(false);
            if (Constants.l) {
                ((ScrollView) this.mView.findViewById(R.id.profile_page)).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.payment_grey_bg));
            }
        }
        return this.mView;
    }

    private void populateSubscriptionView(ViewGroup viewGroup) {
        callSubscriptionApi(viewGroup);
    }

    private void callSubscriptionApi(final ViewGroup viewGroup) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gplus_profile.php?type=gplus_profile");
        stringBuilder.append(ag.a(this.mContext).d());
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(SubscriptionProfile.class);
        uRLManager.a(stringBuilder2);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null && (obj instanceof SubscriptionProfile)) {
                    GaanaPlusItemView.this.showView((SubscriptionProfile) obj, viewGroup);
                }
            }
        }, uRLManager);
    }

    private void showView(SubscriptionProfile subscriptionProfile, ViewGroup viewGroup) {
        e a = subscriptionProfile.a();
        if (a != null) {
            int[] cardArray = getCardArray(a, a.e());
            for (int i : cardArray) {
                switch (i) {
                    case 1:
                        loadCurrentCard(a, viewGroup);
                        break;
                    case 2:
                        loadgaanaCard(a, viewGroup);
                        break;
                    case 3:
                        loadrecommendedCard(a, viewGroup);
                        break;
                    case 4:
                        loadExpirdCard(a, viewGroup);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void loadrecommendedCard(e eVar, ViewGroup viewGroup) {
        f b = eVar.b();
        if (b != null && !TextUtils.isEmpty(b.i())) {
            if (b.i().equalsIgnoreCase("trail_card")) {
                viewGroup.addView(loadRecommendeTrialCard(b));
            } else if (b.i().equalsIgnoreCase("trial_expired_gaana_card") || b.i().equalsIgnoreCase("trial_gaana_card")) {
                viewGroup.addView(loadRecommendeTrialGaanaCard(b));
            }
        }
    }

    private void loadExpirdCard(e eVar, ViewGroup viewGroup) {
        c d = eVar.d();
        if (d != null && !TextUtils.isEmpty(d.h()) && d.h().equalsIgnoreCase("gplus_expired")) {
            viewGroup.addView(loadGaaanPlusExpired(d));
        }
    }

    private void loadgaanaCard(e eVar, ViewGroup viewGroup) {
        d c = eVar.c();
        if (c != null && !TextUtils.isEmpty(c.h()) && c.h().equalsIgnoreCase("free_gaana_card")) {
            viewGroup.addView(loadFreeGaanaCard(c));
        }
    }

    private void loadCurrentCard(e eVar, ViewGroup viewGroup) {
        if (eVar.a() != null && !TextUtils.isEmpty(eVar.a().j())) {
            b a = eVar.a();
            if (a.j().equalsIgnoreCase("gaana_plus") || a.j().equalsIgnoreCase("gplus_expired")) {
                viewGroup.addView(loadGaaanPlusExpiredCard(a));
            } else if (a.j().equalsIgnoreCase(InAppConstants.INAPP_CAMPAIGN_STATUS_EXPIRED) || a.j().equalsIgnoreCase("trail") || a.j().equalsIgnoreCase("free")) {
                viewGroup.addView(loadGaaanPlusExpiredCurrentPlan(a));
            }
        }
    }

    private int[] getCardArray(e eVar, com.payment.subscriptionProfile.a aVar) {
        int[] iArr = new int[4];
        if (!(eVar.a() == null || TextUtils.isEmpty(aVar.b()))) {
            iArr[Integer.parseInt(aVar.b()) - 1] = 1;
        }
        if (!(eVar.c() == null || TextUtils.isEmpty(aVar.d()))) {
            iArr[Integer.parseInt(aVar.d()) - 1] = 2;
        }
        if (!(eVar.d() == null || TextUtils.isEmpty(aVar.a()))) {
            iArr[Integer.parseInt(aVar.a()) - 1] = 4;
        }
        if (!(eVar.b() == null || TextUtils.isEmpty(aVar.c()))) {
            iArr[Integer.parseInt(aVar.c()) - 1] = 3;
        }
        return iArr;
    }

    private View loadFreeGaanaCard(final d dVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_gaana_plus, null);
        TextView textView = (TextView) inflate.findViewById(R.id.gaanplusText);
        TextView textView2 = (TextView) inflate.findViewById(R.id.gaanPlusdescriptionText);
        TextView textView3 = (TextView) inflate.findViewById(R.id.buyNowButton);
        TextView textView4 = (TextView) inflate.findViewById(R.id.priceText);
        TextView textView5 = (TextView) inflate.findViewById(R.id.tncApllytext);
        ((TextView) inflate.findViewById(R.id.planNameText)).setText(R.string.gaana_plus);
        textView.setText(dVar.b());
        textView2.setText(dVar.c());
        setUpTandCText(textView5, dVar.f(), dVar.e());
        if (!TextUtils.isEmpty(dVar.a())) {
            textView3.setVisibility(0);
            textView3.setText(dVar.a());
            textView3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GaanaPlusItemView.this.handleProfilePaymentButton(dVar.g());
                }
            });
        }
        if (dVar.g() != null) {
            updatePaymentTag(textView4, textView3, textView5, dVar.d(), dVar.g());
        }
        return inflate;
    }

    private void setUpTandCText(TextView textView, final String str, String str2) {
        textView.setText(str2);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((BaseActivity) GaanaPlusItemView.this.mContext).sendGAEvent("TandC", "TandCView", "TandCView");
                if (!TextUtils.isEmpty(str)) {
                    Intent intent = new Intent(GaanaPlusItemView.this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", str);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                    GaanaPlusItemView.this.mContext.startActivity(intent);
                }
            }
        });
    }

    private View loadRecommendeTrialGaanaCard(final f fVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_gaana_plus, null);
        TextView textView = (TextView) inflate.findViewById(R.id.gaanplusText);
        TextView textView2 = (TextView) inflate.findViewById(R.id.gaanPlusdescriptionText);
        TextView textView3 = (TextView) inflate.findViewById(R.id.buyNowButton);
        TextView textView4 = (TextView) inflate.findViewById(R.id.priceText);
        TextView textView5 = (TextView) inflate.findViewById(R.id.tncApllytext);
        ((TextView) inflate.findViewById(R.id.planNameText)).setText(R.string.recommended_plan);
        textView.setText(fVar.c());
        textView2.setText(fVar.d());
        textView4.setText(fVar.e());
        textView5.setText(fVar.f());
        textView5.setPaintFlags(textView5.getPaintFlags() | 8);
        textView5.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((BaseActivity) GaanaPlusItemView.this.mContext).sendGAEvent("TandC", "TandCView", "TandCView");
                if (!TextUtils.isEmpty(fVar.g())) {
                    Intent intent = new Intent(GaanaPlusItemView.this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", fVar.g());
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                    GaanaPlusItemView.this.mContext.startActivity(intent);
                }
            }
        });
        if (!TextUtils.isEmpty(fVar.b())) {
            textView3.setText(fVar.b());
            textView3.setVisibility(0);
            textView3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GaanaPlusItemView.this.handleProfilePaymentButton(fVar.h());
                }
            });
        }
        if (fVar.h() != null) {
            updatePaymentTag(textView4, textView3, textView5, fVar.e(), fVar.h());
        }
        return inflate;
    }

    private View loadRecommendeTrialCard(f fVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_recommended_trial, null);
        TextView textView = (TextView) inflate.findViewById(R.id.trialMessage);
        TextView textView2 = (TextView) inflate.findViewById(R.id.activeNowButton);
        ((TextView) inflate.findViewById(R.id.recommendedTrial)).setText(R.string.recommended_plan);
        textView.setText(fVar.a());
        if (!TextUtils.isEmpty(fVar.b())) {
            textView2.setVisibility(0);
            textView2.setText(fVar.b());
            textView2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (GaanaPlusItemView.this.mContext != null) {
                        ((GaanaActivity) GaanaPlusItemView.this.mContext).popBackStack();
                    }
                    GaanaPlusItemView.this.handleProfilePaymentButton(null);
                }
            });
        }
        return inflate;
    }

    private View loadGaaanPlusExpired(final c cVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_expired_plan, null);
        TextView textView = (TextView) inflate.findViewById(R.id.gaanaPlustext);
        TextView textView2 = (TextView) inflate.findViewById(R.id.gaanaPlusValue);
        TextView textView3 = (TextView) inflate.findViewById(R.id.paymentModeText);
        TextView textView4 = (TextView) inflate.findViewById(R.id.paymentModeValue);
        textView4 = (TextView) inflate.findViewById(R.id.renewaltext);
        TextView textView5 = (TextView) inflate.findViewById(R.id.renewalValue);
        TextView textView6 = (TextView) inflate.findViewById(R.id.renewOntext);
        TextView textView7 = (TextView) inflate.findViewById(R.id.renewOnValue);
        TextView textView8 = (TextView) inflate.findViewById(R.id.renewButton);
        ((TextView) inflate.findViewById(R.id.expired_plan_text)).setText(R.string.expired_plan_text);
        textView.setText(cVar.d());
        if (cVar.a() != null && !TextUtils.isEmpty(cVar.a()) && cVar.a().equalsIgnoreCase("coupon")) {
            textView2.setVisibility(4);
        } else if (TextUtils.isEmpty(cVar.e())) {
            textView2.setVisibility(4);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.rs));
            stringBuilder.append(" ");
            stringBuilder.append(cVar.e());
            textView2.setText(stringBuilder.toString());
        }
        textView3.setText(R.string.payment_mode_text);
        textView4.setText(R.string.renewal_text);
        if (cVar.f().equalsIgnoreCase("1")) {
            textView5.setText(R.string.yes_text);
        } else {
            textView5.setText(R.string.no_text);
        }
        textView6.setText(R.string.renewon_text);
        if (cVar.c().intValue() > 0) {
            textView7.setText(Util.a(((long) cVar.c().intValue()) * 1000));
        }
        if (!TextUtils.isEmpty(cVar.b())) {
            textView8.setVisibility(0);
            textView8.setText(cVar.b());
            textView8.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GaanaPlusItemView.this.handleProfilePaymentButton(cVar.g());
                }
            });
        }
        return inflate;
    }

    private void handleProfilePaymentButton(final ProductItem productItem) {
        ag.a(this.mContext).a("Profile Screen", "Gaana Plus");
        String str;
        if (productItem == null || TextUtils.isEmpty(productItem.getAction())) {
            ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
        } else if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
            str = this.sourceType;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Mode: ");
            stringBuilder.append(productItem.getP_payment_mode());
            stringBuilder.append(" ,Description: ");
            stringBuilder.append(productItem.getDesc());
            an.a().e("click", "ac", "", str, stringBuilder.toString(), "PG", "", "");
            u.a().a(productItem, productItem.getItem_id());
            ag.a(this.mContext).a(this.mContext, productItem, new ag.a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    an a = an.a();
                    String str = "click";
                    String str2 = "ac";
                    String str3 = "";
                    String str4 = "PG";
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Description: ");
                    stringBuilder.append(productItem);
                    a.e(str, str2, str3, str4, stringBuilder.toString() != null ? productItem.getDesc() : "Not Available !", "SUCCESS", "", "");
                    ag.a(GaanaPlusItemView.this.mContext).a("", "", "success");
                    ((BaseActivity) GaanaPlusItemView.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) GaanaPlusItemView.this.mContext).hideProgressDialog();
                            ap.a().a(GaanaPlusItemView.this.mContext);
                            Util.aa();
                            aj.a().a(GaanaPlusItemView.this.mContext, GaanaPlusItemView.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                            if (Util.v(GaanaPlusItemView.this.mContext)) {
                                Intent intent = new Intent(GaanaPlusItemView.this.mContext, GaanaActivity.class);
                                intent.setFlags(71303168);
                                GaanaPlusItemView.this.mContext.startActivity(intent);
                            }
                        }
                    });
                }

                public void onFailure(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        aj.a().a(GaanaPlusItemView.this.mContext, str);
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Mode: ");
                    stringBuilder.append(productItem.getP_payment_mode());
                    stringBuilder.append(" ,Description: ");
                    stringBuilder.append(productItem.getDesc());
                    stringBuilder.append(" ,Message: ");
                    stringBuilder.append(str);
                    an.a().e("click", "ac", "", "PG", stringBuilder.toString(), "FAIL", "", "");
                    ag.a(GaanaPlusItemView.this.mContext).a(str, "", str2);
                }
            }, productItem.getItem_id(), productItem.getDesc());
        } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
            BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
            str = this.sourceType;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Mode: ");
            stringBuilder2.append(productItem.getP_payment_mode());
            stringBuilder2.append(" ,Description: ");
            stringBuilder2.append(productItem.getDesc());
            an.a().e("click", "ac", "", str, stringBuilder2.toString(), "PYMT_PLAN", "", "");
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
            u.a().a(productItem, productItem.getDesc(), productItem.getItem_id(), 0);
            ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
        }
    }

    private View loadGaaanPlusExpiredCurrentPlan(b bVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_current_plan, null);
        TextView textView = (TextView) inflate.findViewById(R.id.gaanaStatus);
        TextView textView2 = (TextView) inflate.findViewById(R.id.descripton);
        ((TextView) inflate.findViewById(R.id.cureentPlantext)).setText(R.string.your_current_plan);
        textView.setText(bVar.h());
        textView2.setText(bVar.i());
        return inflate;
    }

    private View loadGaaanPlusExpiredCard(final b bVar) {
        View inflate = this.mInflater.inflate(R.layout.view_profile_expired_plan, null);
        TextView textView = (TextView) inflate.findViewById(R.id.expired_plan_text);
        TextView textView2 = (TextView) inflate.findViewById(R.id.gaanaPlustext);
        TextView textView3 = (TextView) inflate.findViewById(R.id.gaanaPlusValue);
        TextView textView4 = (TextView) inflate.findViewById(R.id.paymentModeText);
        TextView textView5 = (TextView) inflate.findViewById(R.id.paymentModeValue);
        TextView textView6 = (TextView) inflate.findViewById(R.id.renewaltext);
        TextView textView7 = (TextView) inflate.findViewById(R.id.renewalValue);
        TextView textView8 = (TextView) inflate.findViewById(R.id.renewOntext);
        TextView textView9 = (TextView) inflate.findViewById(R.id.renewOnValue);
        TextView textView10 = (TextView) inflate.findViewById(R.id.renewButton);
        if (bVar.j().equalsIgnoreCase("gplus_expired")) {
            textView.setText(R.string.expired_plan_text);
        } else {
            textView.setText(R.string.your_current_plan);
        }
        textView2.setText(bVar.e());
        if (bVar.b() != null && !TextUtils.isEmpty(bVar.b()) && bVar.b().equalsIgnoreCase("coupon")) {
            textView3.setVisibility(4);
        } else if (TextUtils.isEmpty(bVar.f())) {
            textView3.setVisibility(4);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.rs));
            stringBuilder.append(" ");
            stringBuilder.append(bVar.f());
            textView3.setText(stringBuilder.toString());
        }
        textView4.setText(R.string.payment_mode_text);
        textView5.setText(bVar.b());
        textView6.setText(R.string.renewal_text);
        if (bVar.g().equalsIgnoreCase("1")) {
            textView7.setText(R.string.yes_text);
        } else {
            textView7.setText(R.string.no_text);
        }
        textView8.setText(R.string.renewon_text);
        if (bVar.c() > 0) {
            textView9.setText(Util.a(bVar.c() * 1000));
        }
        if (!TextUtils.isEmpty(bVar.d())) {
            textView10.setText(bVar.d());
            textView10.setVisibility(0);
            textView10.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GaanaPlusItemView.this.handleProfilePaymentButton(bVar.a());
                }
            });
        }
        View findViewById = inflate.findViewById(R.id.profile_subscription_download_songs);
        if (!(findViewById == null || ap.a().f())) {
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    g.a(GaanaPlusItemView.this.mContext, null, null);
                }
            });
        }
        return inflate;
    }

    private View getGaanaMiniProductView() {
        return new View(this.mContext);
    }

    private void getDownloadCounts(boolean z) {
        final TextView textView = (TextView) this.mView.findViewById(R.id.CurrentMonthValue);
        final TextView textView2 = (TextView) this.mView.findViewById(R.id.lifetimeValue);
        com.services.d a = com.services.d.a();
        this.monthDownloadCounts = a.c("PREFERENCE_KEY_MONTH_DOWNLOAD_COUNT", true);
        this.totalDownloadCounts = a.c("PREFERENCE_KEY_TOTAL_DOWNLOAD_COUNT", true);
        if (z || (this.monthDownloadCounts == null && this.totalDownloadCounts == null)) {
            String str = "https://api.gaana.com/gaanaplusservice.php?type=get_download_counts";
            UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (!(currentUser == null || !currentUser.getLoginStatus() || str.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&token=");
                stringBuilder.append(currentUser.getAuthToken());
                str = stringBuilder.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a(str);
            uRLManager.a(String.class);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    try {
                        String str = (String) obj;
                        if (str == null || !str.contains("success")) {
                            aj.a().a(GaanaPlusItemView.this.mContext, GaanaPlusItemView.this.mContext.getString(R.string.error_retrieving_download_counts));
                            return;
                        }
                        JSONObject jSONObject = new JSONObject(str).getJSONObject("result");
                        GaanaPlusItemView.this.totalDownloadCounts = jSONObject.getString("total");
                        GaanaPlusItemView.this.monthDownloadCounts = jSONObject.getString("lastMonth");
                        if (GaanaPlusItemView.this.totalDownloadCounts != null && GaanaPlusItemView.this.totalDownloadCounts.equals("null")) {
                            GaanaPlusItemView.this.totalDownloadCounts = null;
                        }
                        if (GaanaPlusItemView.this.monthDownloadCounts != null && GaanaPlusItemView.this.monthDownloadCounts.equals("null")) {
                            GaanaPlusItemView.this.monthDownloadCounts = null;
                        }
                        com.services.d a = com.services.d.a();
                        a.a("PREFERENCE_KEY_TOTAL_DOWNLOAD_COUNT", GaanaPlusItemView.this.totalDownloadCounts, true);
                        a.a("PREFERENCE_KEY_MONTH_DOWNLOAD_COUNT", GaanaPlusItemView.this.monthDownloadCounts, true);
                        if (!(textView == null || GaanaPlusItemView.this.monthDownloadCounts == null)) {
                            textView.setText(GaanaPlusItemView.this.monthDownloadCounts);
                        }
                        if (textView2 != null && GaanaPlusItemView.this.totalDownloadCounts != null) {
                            textView2.setText(GaanaPlusItemView.this.totalDownloadCounts);
                        }
                    } catch (Exception unused) {
                    }
                }
            }, uRLManager);
            return;
        }
        textView.setText(this.monthDownloadCounts);
        textView2.setText(this.totalDownloadCounts);
    }

    private void showMemeberData() {
        TextView textView = (TextView) this.mView.findViewById(R.id.memberSince);
        textView = (TextView) this.mView.findViewById(R.id.memberSinceValue);
        if (this.mAppState.getCurrentUser().getUserSubscriptionData() != null) {
            this.mAppState.getCurrentUser().getUserSubscriptionData().getGaanaPlusMessage();
            String subscribedOn = this.mAppState.getCurrentUser().getUserSubscriptionData().getSubscribedOn();
            this.mAppState.getCurrentUser().getUserSubscriptionData().getValidUpTo();
            Date expiryDate = this.mAppState.getCurrentUser().getUserSubscriptionData().getExpiryDate();
            Date expiryDateWithGrace = this.mAppState.getCurrentUser().getUserSubscriptionData().getExpiryDateWithGrace();
            Date date = new Date();
            this.mContext.getString(R.string.expiry_date_text_prefix);
            if (!(expiryDate == null || expiryDateWithGrace == null || ((!expiryDate.before(date) || !date.before(expiryDateWithGrace)) && (!date.before(expiryDateWithGrace) || !expiryDate.equals(expiryDateWithGrace))))) {
                this.mAppState.getCurrentUser().getUserSubscriptionData().getGaanaPlusGraceMessage();
                this.mContext.getString(R.string.grace_expiry_date_text_prefix);
            }
            setTextToTextView(textView, subscribedOn);
        }
    }

    public void onFailure(String str) {
        try {
            aj.a().a(this.mContext, str);
        } catch (Exception unused) {
        }
        ((BaseActivity) this.mContext).hideProgressDialog();
    }

    public void onInventoryQueryCompeleted(com.iabutils.a aVar, com.iabutils.b bVar) {
        if (!aVar.c()) {
            ((BaseActivity) this.mContext).hideProgressDialog();
        }
    }

    private void setTextToTextView(TextView textView, String str) {
        if (textView != null) {
            try {
                str = Util.a(Long.parseLong(str) * 1000);
                if (str != null) {
                    textView.setText(str);
                    textView.setVisibility(0);
                    return;
                }
                hideView();
            } catch (Exception unused) {
                hideView();
            }
        }
    }

    private void hideView() {
        ((TextView) this.mView.findViewById(R.id.memberSince)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.memberSinceValue)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.songDownloadActivityText)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.CurrentMonthText)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.CurrentMonthValue)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.lifetimetext)).setVisibility(8);
        ((TextView) this.mView.findViewById(R.id.lifetimeValue)).setVisibility(8);
    }

    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
        if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_TRIAL || subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_PURCHASED) {
            Util.aa();
            aj.a().a(this.mContext, this.mContext.getString(R.string.enjoy_using_gaana_plus));
            if (this.mFragment != null && this.mFragment.getFragmentManager() != null) {
                this.mFragment.getFragmentManager().popBackStack();
            }
        } else if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_EXPIRED) {
            aj.a().a(this.mContext, getContext().getString(R.string.trial_expired_msg));
            if (this.mButtonBuy != null) {
                this.mButtonBuy.setText(R.string.subscribe_caps_title);
            }
        } else if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_ERROR) {
            aj.a().a(this.mContext, getContext().getString(R.string.server_error));
        } else if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_ACCOUNT_LINKED) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.device_linked_gaana_plus));
        } else if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_DEVICELINKING_FAILED) {
            aj.a().a(this.mContext, getContext().getString(R.string.device_linked_failed));
        } else if (subscriptionPurchaseType == SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_ALREADY) {
            aj.a().a(this.mContext, getContext().getString(R.string.alread_gaana_plus));
            if (this.mFragment != null && this.mFragment.getFragmentManager() != null) {
                try {
                    this.mFragment.getFragmentManager().popBackStack();
                } catch (Exception unused) {
                }
            }
        }
    }

    private void updatePaymentTag(TextView textView, TextView textView2, TextView textView3, String str, ProductItem productItem) {
        final TextView textView4 = textView;
        final String str2 = str;
        String str3 = "";
        final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
        if (productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode()) && productItem.getP_payment_mode().equalsIgnoreCase("android")) {
            str3 = productItem.getP_id();
        } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || productItem == null || TextUtils.isEmpty(productItem.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !productItem.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
            str3 = e.getIntro_config().getIntro_p_id();
        }
        String str4 = str3;
        StringBuilder stringBuilder;
        if (str2.contains("&&&&") && !TextUtils.isEmpty(str4)) {
            final TextView textView5 = textView2;
            final TextView textView6 = textView3;
            final ProductItem productItem2 = productItem;
            PurchaseGoogleManager.a(this.mContext, null).a(str4, new PurchaseGoogleManager.d() {
                public void onGoolgeProductPriceQueryConpleted(PurchaseGoogleManager.c cVar) {
                    if (cVar != null) {
                        CharSequence c = cVar.a() ? cVar.c() : cVar.b();
                        if (!TextUtils.isEmpty(c)) {
                            textView4.setText(str2.replace("&&&&", c));
                        }
                        if (e != null) {
                            String intro_cta_text = e.getIntro_config().getIntro_cta_text();
                            if (!TextUtils.isEmpty(intro_cta_text)) {
                                textView5.setText(intro_cta_text);
                            }
                            intro_cta_text = e.getIntro_config().getIntro_tnc_link();
                            if (!TextUtils.isEmpty(intro_cta_text)) {
                                GaanaPlusItemView.this.setUpTandCText(textView6, intro_cta_text, GaanaPlusItemView.this.mContext.getString(R.string.terms_and_conditions));
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    textView4.setText(str2.replace("&&&&", productItem2.getP_cost()));
                }
            });
        } else if (str2.contains("&&&&") && !TextUtils.isEmpty(productItem.getP_curr_code()) && productItem.getP_curr_code().equalsIgnoreCase("INR")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.rs));
            stringBuilder.append(" ");
            stringBuilder.append(str2.replace("&&&&", productItem.getP_cost()));
            textView4.setText(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.rs));
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            textView4.setText(stringBuilder.toString());
        }
    }

    private void launchSubscriptionPage() {
        if (!(this.mFragment instanceof SettingsDetailFragment)) {
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_SETTINGS", 1);
            bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
            bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
            this.mFragment = new SettingsDetailFragment();
            this.mFragment.setArguments(bundle);
            ((GaanaActivity) this.mContext).displayFragment(this.mFragment);
        }
    }

    public void displayFeatureNotAvailableOfflineDialog(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append(getResources().getString(R.string.error_msg_feature_not_available_offline_suffix));
        new com.services.f(this.mContext).a("Gaana", stringBuilder.toString(), Boolean.valueOf(true), "Go Online", "Cancel", new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                com.services.d a = com.services.d.a();
                a.b("PREFERENCE_KEY_OFFLINE_MODE", false);
                a.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                a.b("PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                a.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                a.b("PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                a.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                GaanaPlusItemView.this.mAppState.setAppInOfflineMode(false);
                DownloadManager.c().d();
            }
        });
    }

    public void onRefresh() {
        this.swipeToRefreshScrollView.setRefreshing(true);
        if (!this.mAppState.isAppInOfflineMode() && this.mAppState.getCurrentUser().getLoginStatus() && Util.j(this.mContext)) {
            ((BaseActivity) this.mContext).updateUserStatus(new au() {
                public void onUserStatusUpdated() {
                }
            });
        } else {
            Toast.makeText(this.mContext, InAppMessage.INAPP_TYPE_TEST, 1);
        }
    }

    public void setProductAndItemId(String str, String str2) {
        this.productId = str2;
        this.itemId = str;
    }

    public void setCouponCode(String str) {
        this.couponCode = str;
    }
}

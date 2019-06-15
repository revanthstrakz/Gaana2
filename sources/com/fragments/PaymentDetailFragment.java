package com.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.bumptech.glide.e;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.f;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.CouponApplyModel.ProductCouponItem;
import com.gaana.models.PaymentProductDetailModel;
import com.gaana.models.PaymentProductDetailModel.CarouselOfferConfig;
import com.gaana.models.PaymentProductModel.PageHeaderConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.item.AppUpdaterView;
import com.gaana.view.item.GaanaPlusApplyCouponView;
import com.gaana.view.item.GaanaPlusApplyCouponView.CouponApplyRemovedListener;
import com.gaana.view.item.GaanaPlusPurchaseItemView;
import com.gaana.view.item.GenericCarouselView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.ag;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PaymentDetailFragment extends BaseGaanaFragment implements a, CouponApplyRemovedListener {
    List<String> a = new ArrayList();
    private View b = null;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private ProductItem f;
    private PageHeaderConfig g;
    private String h = "choose payment option";
    private boolean i;
    private PaymentProductDetailModel j;
    private GenericCarouselView k = null;
    private boolean l = false;
    private TextView m;
    private TextView n;
    private TextView o;
    private String p = "";
    private String q = "";
    private LinearLayout r;
    private boolean s;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = setContentView(R.layout.payment_detail_fragment, viewGroup);
            this.i = true;
            b(null);
            setActionBar(this.b, new GenericBackActionBar(this.mContext, this.h), false);
        }
        setGAScreenName("Payment Detail", "PaymentDetailScreen");
        MoEngage.getInstance().reportSectionViewedEvent("Payment");
        ((GaanaActivity) this.mContext).title = this.h;
        return this.b;
    }

    public void a(ProductItem productItem) {
        this.f = productItem;
    }

    public void a(PageHeaderConfig pageHeaderConfig) {
        this.g = pageHeaderConfig;
    }

    public void a(String str) {
        this.q = str;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onPause() {
        super.onPause();
    }

    public void b(String str) {
        this.c = (LinearLayout) this.b.findViewById(R.id.offer_layout);
        this.d = (LinearLayout) this.b.findViewById(R.id.product_layout);
        this.e = (LinearLayout) this.b.findViewById(R.id.coupon_layout);
        this.m = (TextView) this.b.findViewById(R.id.offer_msg);
        this.n = (TextView) this.b.findViewById(R.id.terms_conditions_text);
        this.o = (TextView) this.b.findViewById(R.id.coupon_applied_text);
        this.o.setVisibility(8);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice.php?type=p_mode_list&item_id=<item_id>&usr_cat_code=<usr_cat_code>&is_eligible_gtrial=<is_eligible_gtrial>");
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
        if (getArguments() != null) {
            stringBuilder2 = stringBuilder2.replace("<item_id>", URLEncoder.encode(getArguments().getString("KEY_ITEM_ID")));
        } else if (this.f == null || TextUtils.isEmpty(this.f.getItem_id())) {
            ((GaanaActivity) this.mContext).popBackStack();
            return;
        } else {
            stringBuilder2 = stringBuilder2.replace("<item_id>", URLEncoder.encode(this.f.getItem_id()));
            u.a().a(this.f, this.f.getItem_id());
        }
        if (this.f != null) {
            stringBuilder2 = stringBuilder2.replace("<usr_cat_code>", URLEncoder.encode(TextUtils.isEmpty(this.f.getUser_cat_code()) ? "" : this.f.getUser_cat_code()));
        }
        if (this.g == null || TextUtils.isEmpty(this.g.getIs_Eligible_Gtrial())) {
            stringBuilder2 = stringBuilder2.replace("<is_eligible_gtrial>", "");
        } else {
            stringBuilder2 = stringBuilder2.replace("<is_eligible_gtrial>", URLEncoder.encode(this.g.getIs_Eligible_Gtrial()));
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true));
        if (str != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append("&coupon_code=");
            stringBuilder.append(str);
            stringBuilder2 = stringBuilder.toString();
        }
        str = ag.a(this.mContext).b();
        if (TextUtils.isEmpty(str)) {
            ag.a(this.mContext).b(null);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append("&p_session_id=");
            stringBuilder.append(str);
            stringBuilder2 = stringBuilder.toString();
            ag.a(this.mContext).b(null);
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(PaymentProductDetailModel.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                PaymentDetailFragment.this.j = (PaymentProductDetailModel) obj;
                PaymentDetailFragment.this.a(null);
                if (PaymentDetailFragment.this.j != null && PaymentDetailFragment.this.j.getProduct_desc() != null && !TextUtils.isEmpty(PaymentDetailFragment.this.j.getProduct_desc().getMessage())) {
                    aj.a().a(PaymentDetailFragment.this.mContext, PaymentDetailFragment.this.j.getProduct_desc().getMessage());
                }
            }
        }, uRLManager);
    }

    public void onDestroy() {
        super.onDestroy();
        if (PurchaseGoogleManager.a("OnlyForCallbackNotForGettingInstance") != null) {
            PurchaseGoogleManager.a("OnlyForCallbackNotForGettingInstance").c();
        }
    }

    public void onDestroyView() {
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }

    public void couponSuccessfullyApplied(ArrayList<ProductCouponItem> arrayList, String str) {
        this.i = false;
        this.p = str;
        a((ArrayList) arrayList);
    }

    public void couponSuccessfullyRemoved() {
        this.i = true;
        a(null);
    }

    private void a(ArrayList<ProductCouponItem> arrayList) {
        if (this.j == null || this.j.getOffer() == null) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            if (TextUtils.isEmpty(this.j.getOffer().getMsg())) {
                this.m.setVisibility(8);
            } else {
                this.m.setVisibility(0);
                this.m.setText(this.j.getOffer().getMsg());
            }
            if (this.j.getOffer() != null) {
                this.n.setVisibility(0);
                this.n.setPaintFlags(this.n.getPaintFlags() | 8);
                this.n.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ((BaseActivity) PaymentDetailFragment.this.mContext).sendGAEvent("TandC", "TandCView", "TandCView");
                        if (!TextUtils.isEmpty(PaymentDetailFragment.this.j.getOffer().getT_c_text())) {
                            new AppUpdaterView(PaymentDetailFragment.this.mContext).showDialogForTermsandConditions(PaymentDetailFragment.this.j.getOffer().getT_c_text());
                        } else if (!TextUtils.isEmpty(PaymentDetailFragment.this.j.getOffer().getUrl())) {
                            Intent intent = new Intent(PaymentDetailFragment.this.mContext, WebViewActivity.class);
                            intent.putExtra("EXTRA_WEBVIEW_URL", PaymentDetailFragment.this.j.getOffer().getUrl());
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                            PaymentDetailFragment.this.mContext.startActivity(intent);
                        }
                    }
                });
            } else {
                this.n.setVisibility(8);
            }
        }
        if (TextUtils.isEmpty(this.p)) {
            this.o.setVisibility(8);
        } else {
            this.o.setVisibility(0);
            TextView textView = this.o;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.o.getText().toString());
            stringBuilder.append(": ");
            stringBuilder.append(this.p);
            textView.setText(stringBuilder.toString());
        }
        if ((this.j == null || this.j.getMore_pg() == null || this.j.getMore_pg().size() <= 0) && (this.j == null || this.j.getPreferred_pg() == null || this.j.getPreferred_pg().size() <= 0)) {
            this.d.setVisibility(8);
        } else {
            int i;
            int i2;
            this.d.setVisibility(0);
            this.d.removeAllViews();
            View b = b();
            ArrayList more_pg = this.j.getMore_pg();
            ArrayList preferred_pg = this.j.getPreferred_pg();
            if (arrayList != null && arrayList.size() > 0) {
                i = 0;
                while (i < arrayList.size()) {
                    while (more_pg.size() > 0) {
                        if (more_pg.get(0) != null && ((ProductItem) more_pg.get(0)).shouldDisplay() && ((ProductCouponItem) arrayList.get(i)).getP_id() != null && ((ProductCouponItem) arrayList.get(i)).getP_id().equalsIgnoreCase(((ProductItem) this.j.getMore_pg().get(0)).getP_id())) {
                            ((ProductItem) more_pg.get(0)).setCouponCode(this.p);
                            if (!TextUtils.isEmpty(((ProductCouponItem) arrayList.get(i)).getP_new_cost())) {
                                ((ProductItem) more_pg.get(0)).setNewCostAfterCoupon(((ProductCouponItem) arrayList.get(i)).getP_new_cost());
                            }
                        }
                        i++;
                    }
                    i++;
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                i = 0;
                while (i < arrayList.size()) {
                    while (preferred_pg.size() > 0) {
                        if (preferred_pg.get(0) != null && ((ProductItem) preferred_pg.get(0)).shouldDisplay() && ((ProductCouponItem) arrayList.get(i)).getP_id() != null && ((ProductCouponItem) arrayList.get(i)).getP_id().equalsIgnoreCase(((ProductItem) this.j.getPreferred_pg().get(0)).getP_id())) {
                            ((ProductItem) preferred_pg.get(0)).setCouponCode(this.p);
                            if (!TextUtils.isEmpty(((ProductCouponItem) arrayList.get(i)).getP_new_cost())) {
                                ((ProductItem) preferred_pg.get(0)).setNewCostAfterCoupon(((ProductCouponItem) arrayList.get(i)).getP_new_cost());
                            }
                        }
                        i++;
                    }
                    i++;
                }
            }
            if (!(this.j == null || TextUtils.isEmpty(this.j.getpaymentSessionId()))) {
                ag.a(this.mContext).a(this.j.getpaymentSessionId());
            }
            this.r = new LinearLayout(this.mContext);
            this.r.setOrientation(1);
            if (!(preferred_pg == null || preferred_pg.size() == 0)) {
                i2 = 0;
                while (i2 < preferred_pg.size()) {
                    if (!((ProductItem) preferred_pg.get(i2)).getP_payment_mode().equalsIgnoreCase("simpl") || (Constants.bT && Constants.bN && !TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber()))) {
                        if (i2 == 0) {
                            this.d.addView(new GaanaPlusPurchaseItemView(this.mContext, this, this.i, this.f).getPopulatedViewForCoupon(this.d, (BusinessObject) preferred_pg.get(i2), i2, this.q));
                        }
                        if (preferred_pg.get(i2) != null && ((ProductItem) preferred_pg.get(i2)).shouldDisplay()) {
                            this.d.addView(new GaanaPlusPurchaseItemView(this.mContext, this, this.i, this.f).getPopulatedView(this.d, (BusinessObject) preferred_pg.get(i2), i2, this.a));
                        }
                    }
                    i2++;
                }
                if (!(b == null || this.l)) {
                    this.d.addView(b, 1);
                    this.l = true;
                }
            }
            if (!(more_pg == null || preferred_pg == null || more_pg.size() == 0 || preferred_pg.size() == 0)) {
                this.d.addView(a(this.d));
            }
            if (!(more_pg == null || more_pg.size() == 0)) {
                i2 = 0;
                while (i2 < more_pg.size()) {
                    if (i2 == 0 && preferred_pg.size() == 0) {
                        this.d.addView(new GaanaPlusPurchaseItemView(this.mContext, this, this.i, this.f).getPopulatedViewForCoupon(this.d, (BusinessObject) more_pg.get(i2), i2, this.q));
                    }
                    if (more_pg.get(i2) != null && ((ProductItem) more_pg.get(i2)).shouldDisplay()) {
                        this.r.addView(new GaanaPlusPurchaseItemView(this.mContext, this, this.i, this.f).getPopulatedView(this.d, (BusinessObject) more_pg.get(i2), i2, this.a));
                    }
                    i2++;
                }
                if (!(b == null || this.l)) {
                    this.d.addView(b, 1);
                    this.l = true;
                }
            }
        }
        if (this.j == null || this.j.getCoupon_applicable() == 0) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
            this.e.addView(new GaanaPlusApplyCouponView(this.mContext, this, this.i, this).getPopulatedView(this.e, this.f));
        }
        ((BaseActivity) this.mContext).hideProgressDialog();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return (this.j == null || this.j.getCarouselOfferDetails() == null || this.j.getCarouselOfferDetails().getArrCarouselOfferConfig() == null || this.j.getCarouselOfferDetails().getArrCarouselOfferConfig().size() <= 0) ? false : true;
    }

    /* Access modifiers changed, original: 0000 */
    public View b() {
        if (!a()) {
            return null;
        }
        if (this.j.getCarouselOfferDetails().getArrCarouselOfferConfig().size() == 1 || !this.j.getCarouselOfferDetails().isCarousel()) {
            View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.carousel_view_item_payment_offer, null);
            ImageView imageView = (CrossFadeImageView) inflate.findViewById(R.id.carouselImage);
            final CarouselOfferConfig carouselOfferConfig = (CarouselOfferConfig) this.j.getCarouselOfferDetails().getArrCarouselOfferConfig().get(0);
            String offerUrl = carouselOfferConfig.getOfferUrl();
            e.c(this.mContext.getApplicationContext()).load(offerUrl).apply(new f().placeholder(imageView.getDrawable())).listener(new com.bumptech.glide.request.e<Drawable>() {
                /* renamed from: a */
                public boolean onResourceReady(Drawable drawable, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, DataSource dataSource, boolean z) {
                    return false;
                }

                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, boolean z) {
                    return false;
                }
            }).into(imageView);
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PaymentDetailFragment.this.b(carouselOfferConfig.getOfferProduct());
                }
            });
            return inflate;
        }
        this.k = new GenericCarouselView(this.mContext, this, R.layout.carousel_view_item_payment_offers, 30, 35);
        this.k.setCarouselData(this.j.getCarouselOfferDetails().getArrCarouselOfferConfig());
        return this.k.getNewView(R.layout.payment_offers_carousel_view, null);
    }

    public void c() {
        if (this.f != null && !TextUtils.isEmpty(this.f.getItem_id())) {
            u.a().b(this.f, this.f.getItem_id());
        }
    }

    private View a(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_payment_chevron, viewGroup, false);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.chevron_layout);
        relativeLayout.setVisibility(0);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.chev_img);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_down});
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        relativeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TypedArray obtainStyledAttributes;
                if (PaymentDetailFragment.this.s) {
                    obtainStyledAttributes = PaymentDetailFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_down});
                    imageView.setImageDrawable(obtainStyledAttributes.getDrawable(0));
                    obtainStyledAttributes.recycle();
                    PaymentDetailFragment.this.s = false;
                    PaymentDetailFragment.this.d.removeView(PaymentDetailFragment.this.r);
                    return;
                }
                obtainStyledAttributes = PaymentDetailFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_up});
                imageView.setImageDrawable(obtainStyledAttributes.getDrawable(0));
                obtainStyledAttributes.recycle();
                PaymentDetailFragment.this.s = true;
                PaymentDetailFragment.this.d.addView(PaymentDetailFragment.this.r);
            }
        });
        return inflate;
    }

    public void b(ProductItem productItem) {
        if (productItem == null || TextUtils.isEmpty(productItem.getAction())) {
            ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
        } else if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
            u.a().a(productItem, productItem.getItem_id());
            ag.a(this.mContext).a(this.mContext, productItem, new a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    ag.a(PaymentDetailFragment.this.mContext).a("", "", "success");
                    ((BaseActivity) PaymentDetailFragment.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) PaymentDetailFragment.this.mContext).hideProgressDialog();
                            ap.a().a(PaymentDetailFragment.this.mContext);
                            Util.aa();
                            aj.a().a(PaymentDetailFragment.this.mContext, PaymentDetailFragment.this.mContext.getString(R.string.enjoy_using_gaana_plus));
                            if (((GaanaActivity) PaymentDetailFragment.this.mContext).getCurrentSongSelectedView() != null) {
                                ((GaanaActivity) PaymentDetailFragment.this.mContext).getCurrentSongSelectedView().callOnClick();
                            }
                        }
                    });
                }

                public void onFailure(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        aj.a().a(PaymentDetailFragment.this.mContext, str);
                    }
                    ag.a(PaymentDetailFragment.this.mContext).a(str, "", str2);
                    u.a().a("Premium pop-up", "Try Gaana Plus", "Failure");
                }
            }, productItem.getItem_id(), productItem.getDesc());
        } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
            BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
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
}

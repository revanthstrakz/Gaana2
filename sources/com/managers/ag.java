package com.managers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.constants.c;
import com.d.a.b;
import com.fragments.BaseGaanaFragment;
import com.fragments.NetBankingFragmentNew;
import com.fragments.PayUCreditDebitFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.gaana.AdyenWebViewActivity;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.login.UserSubscriptionData;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaPlusUserStatus;
import com.gaana.models.PaymentProductModel;
import com.gaana.models.PaymentProductModel.PageHeaderConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.PaymentProductModel.ProductItem.AdyenParams;
import com.gaana.models.PaymentTrialStatusModel;
import com.gaana.models.PaypalApprovalUrlModel;
import com.gaana.models.SimplEligibility;
import com.gaana.view.item.SimplPayBottomSheet;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager.BusinessObjectType;
import com.models.GaanaMiniProduct;
import com.services.d;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.an;
import com.services.l.au;
import com.simpl.android.zeroClickSdk.Simpl;
import com.simpl.android.zeroClickSdk.SimplUser;
import com.simpl.android.zeroClickSdk.SimplUserApprovalListenerV2;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenAuthorization;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;
import com.utilities.Util;
import java.util.HashMap;
import java.util.Iterator;

public class ag {
    private static ag a;
    private GaanaApplication b = GaanaApplication.getInstance();
    private ProductItem c;
    private a d = null;
    private String e = null;
    private HashMap<String, String> f = null;
    private boolean g = false;
    private GaanaMiniProduct h;
    private String i = null;
    private String j;
    private PaymentProductModel k;
    private int l = 0;
    private PageHeaderConfig m;
    private String n = null;
    private String o = "";
    private String p = "";

    public interface a {
        void onFailure(String str, String str2);

        void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType);
    }

    public void a(PaymentProductModel paymentProductModel) {
        this.k = paymentProductModel;
    }

    public void a(ProductItem productItem) {
        this.c = productItem;
    }

    public void a(PageHeaderConfig pageHeaderConfig) {
        this.m = pageHeaderConfig;
    }

    public void a(int i) {
        this.l = i;
    }

    private ag(Context context) {
    }

    public static ag a(Context context) {
        if (a == null) {
            synchronized (ag.class) {
                if (a == null) {
                    a = new ag(context);
                }
            }
        }
        return a;
    }

    public void a(GaanaMiniProduct gaanaMiniProduct) {
        this.h = gaanaMiniProduct;
    }

    public GaanaMiniProduct a() {
        return this.h;
    }

    public void a(Context context, ProductItem productItem, a aVar, String str, String str2) {
        final Context context2;
        final String str3;
        if (productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
            this.c = productItem;
            this.d = aVar;
            context2 = context;
            final ProductItem productItem2 = productItem;
            final Context context3 = context;
            str3 = str;
            final String str4 = str2;
            ((BaseActivity) context).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ((BaseActivity) context2).showProgressDialog(Boolean.valueOf(true), context2.getString(R.string.fetching_details_from_server));
                    ag.this.a(context2, new au() {
                        public void onUserStatusUpdated() {
                            if (ap.a().u() && !ag.this.g) {
                                if (ag.this.d != null) {
                                    ag.this.d.onPurchaseFinished(SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_ALREADY);
                                }
                                aj.a().a(context2, context2.getResources().getString(R.string.toast_already_gaanaplus_user));
                            } else if ((ap.a().u() || ap.a().i()) && ag.this.g && !Constants.k) {
                                if (ag.this.b(productItem2)) {
                                    ag.this.a(context3, str3, str4);
                                }
                            } else if (!ap.a().u()) {
                                ag.this.g = false;
                                ag.this.a(context3, str3, str4);
                            }
                            ((BaseActivity) context2).hideProgressDialog();
                            Constants.k = false;
                        }
                    }, productItem2.getP_id());
                }
            }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
        } else if (productItem != null && !TextUtils.isEmpty(productItem.getIs_trial())) {
            this.c = productItem;
            this.d = aVar;
            context2 = context;
            final Context context4 = context;
            final String str5 = str;
            str3 = str2;
            ((BaseActivity) context).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ((BaseActivity) context2).showProgressDialog(Boolean.valueOf(true), context2.getString(R.string.fetching_details_from_server));
                    ag.this.a(context2, new au() {
                        public void onUserStatusUpdated() {
                            if (ap.a().u()) {
                                if (ag.this.d != null) {
                                    ag.this.d.onPurchaseFinished(SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_ALREADY);
                                }
                                aj.a().a(context2, context2.getResources().getString(R.string.toast_already_gaanaplus_user));
                            } else {
                                ag.this.a(context4, str5, str3);
                            }
                            ((BaseActivity) context2).hideProgressDialog();
                        }
                    }, "");
                }
            }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FREE_TRAIL));
        }
    }

    public void b(final Context context) {
        if (this.c != null && !TextUtils.isEmpty(this.c.getAction())) {
            if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(this.c.getAction())) {
                u.a().a(this.c, this.c.getItem_id());
                a(context).a(context, this.c, new a(context) {
                    final /* synthetic */ Context a;

                    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                        ag.a(this.a).a("", "", "success");
                        ((BaseActivity) this.a).updateUserStatus(new au() {
                            public void onUserStatusUpdated() {
                                ((BaseActivity) AnonymousClass9.this.a).hideProgressDialog();
                                ap.a().a(AnonymousClass9.this.a);
                                Util.aa();
                                aj.a().a(AnonymousClass9.this.a, AnonymousClass9.this.a.getString(R.string.enjoy_using_gaana_plus));
                                if (Util.v(AnonymousClass9.this.a)) {
                                    Intent intent = new Intent(AnonymousClass9.this.a, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    AnonymousClass9.this.a.startActivity(intent);
                                }
                            }
                        });
                        if (Util.s() != null && !TextUtils.isEmpty(ag.this.c.getP_payment_mode())) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(ag.this.c.getP_payment_mode());
                            stringBuilder.append("Success; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Student’s Plan", "Submit & Pay", stringBuilder.toString());
                            AppsFlyer.getInstance().reportStudentPackPurchaseCompleted(ag.this.c, ag.this.c.getP_payment_mode());
                        }
                    }

                    public void onFailure(String str, String str2) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(ag.this.c.getP_payment_mode());
                        stringBuilder.append("Failure; ");
                        u.a().a("Student’s Plan", "Submit & Pay", stringBuilder.toString());
                        if (!TextUtils.isEmpty(str)) {
                            aj.a().a(context, str);
                        }
                        ag.a(context).a(str, "", str2);
                        if (Util.s() != null && ag.this.c != null && !TextUtils.isEmpty(ag.this.c.getP_payment_mode())) {
                            String p_payment_mode = ag.this.c.getP_payment_mode();
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Failure; ");
                            stringBuilder2.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder2.toString());
                        }
                    }
                }, this.c.getItem_id(), this.c.getDesc());
            } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(this.c.getAction())) {
                BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
                if (this.m != null) {
                    paymentDetailFragment.a(this.m);
                }
                paymentDetailFragment.a(this.c);
                ((GaanaActivity) context).displayFragment(paymentDetailFragment);
            } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(this.c.getAction()) && !TextUtils.isEmpty(this.c.getWeb_url())) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", this.c.getWeb_url());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                context.startActivity(intent);
            } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(this.c.getAction())) {
                u.a().a(this.c, this.c.getItem_id());
                u.a().a(this.c, this.c.getDesc(), this.c.getItem_id(), this.l);
                ((GaanaActivity) context).displayFragment(new ReferFriendsFragment());
            }
        }
    }

    private boolean b(ProductItem productItem) {
        if (!(!GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getSubscriptionType().equalsIgnoreCase("inapp") || this.k == null || this.k.getPageHeader() == null || this.k.getPageHeader().getPageHeaderConfig() == null)) {
            String is_downgrade_allowed = this.k.getPageHeader().getPageHeaderConfig().getIs_downgrade_allowed();
            String is_upgrade_allowed = this.k.getPageHeader().getPageHeaderConfig().getIs_upgrade_allowed();
            int previousPackDuration = this.k.getPageHeader().getPageHeaderConfig().getPreviousPackDuration();
            String string = GaanaApplication.getContext().getString(R.string.upgrade_message);
            String string2 = GaanaApplication.getContext().getString(R.string.downgrade_message);
            if (!TextUtils.isEmpty(this.k.getPageHeader().getPageHeaderConfig().getUpgrade_msg())) {
                string = this.k.getPageHeader().getPageHeaderConfig().getUpgrade_msg();
            }
            if (!TextUtils.isEmpty(this.k.getPageHeader().getPageHeaderConfig().getDowngrade_msg())) {
                string2 = this.k.getPageHeader().getPageHeaderConfig().getDowngrade_msg();
            }
            if (productItem.getDuration_days() == null || Integer.parseInt(productItem.getDuration_days()) >= previousPackDuration) {
                if (productItem.getDuration_days() == null || Integer.parseInt(productItem.getDuration_days()) <= previousPackDuration) {
                    if ((TextUtils.isEmpty(is_upgrade_allowed) && TextUtils.isEmpty(is_downgrade_allowed)) || (is_upgrade_allowed.equalsIgnoreCase("0") && is_downgrade_allowed.equalsIgnoreCase("0"))) {
                        aj.a().a(GaanaApplication.getContext(), GaanaApplication.getContext().getResources().getString(R.string.toast_already_gaanaplus_user));
                        return false;
                    }
                } else if (TextUtils.isEmpty(is_upgrade_allowed) || is_upgrade_allowed.equalsIgnoreCase("0")) {
                    aj.a().a(GaanaApplication.getContext(), string);
                    return false;
                }
            } else if (TextUtils.isEmpty(is_downgrade_allowed) || is_downgrade_allowed.equalsIgnoreCase("0")) {
                aj.a().a(GaanaApplication.getContext(), string2);
                return false;
            }
        }
        return true;
    }

    public void a(String str) {
        this.i = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public String b() {
        return this.j;
    }

    public void a(final Context context, final au auVar, String str) {
        if (!this.b.isAppInOfflineMode() && this.b.getCurrentUser().getLoginStatus() && Util.j((BaseActivity) context)) {
            this.g = false;
            this.e = "https://api.gaana.com/gaanaplusservice.php?";
            this.f = new HashMap();
            this.f.put("type", "gaana_plus_status");
            this.f.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.b.getCurrentUser().getAuthToken());
            this.f.put("prod_id", str);
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(GaanaPlusUserStatus.class);
            uRLManager.a(Priority.HIGH);
            uRLManager.a(this.e);
            uRLManager.a(this.f);
            uRLManager.i(false);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    GaanaPlusUserStatus gaanaPlusUserStatus = (GaanaPlusUserStatus) obj;
                    ((BaseActivity) context).hideProgressDialog();
                    if (gaanaPlusUserStatus != null && "1".equalsIgnoreCase(gaanaPlusUserStatus.getStatus()) && "1".equalsIgnoreCase(gaanaPlusUserStatus.getUsertokenstatus())) {
                        if ("1".equalsIgnoreCase(gaanaPlusUserStatus.getIsGaanaPlusAllow())) {
                            ag.this.g = true;
                        }
                        if ("1".equalsIgnoreCase(gaanaPlusUserStatus.getIsGaanaPlusUser())) {
                            if (ag.this.g || !ap.a().d()) {
                                if (auVar != null) {
                                    auVar.onUserStatusUpdated();
                                    return;
                                }
                                return;
                            } else if (TextUtils.isEmpty(gaanaPlusUserStatus.getMessage())) {
                                ((BaseActivity) context).hideProgressDialog();
                                return;
                            } else {
                                aj.a().a(context, gaanaPlusUserStatus.getMessage());
                                return;
                            }
                        } else if (auVar != null) {
                            auVar.onUserStatusUpdated();
                            return;
                        } else {
                            return;
                        }
                    }
                    ((BaseActivity) context).hideProgressDialog();
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) context).hideProgressDialog();
                }
            }, uRLManager);
        }
    }

    public void a(String str, String str2) {
        this.o = str;
        this.p = str2;
    }

    public void a(String str, String str2, final String str3) {
        if (!(this.c == null || TextUtils.isEmpty(this.o) || TextUtils.isEmpty(this.p))) {
            String p_discounted_cost = this.c.getP_discounted_cost();
            if (TextUtils.isEmpty(p_discounted_cost)) {
                p_discounted_cost = "OFFER_NOT_SET";
            }
            String p_payment_mode = this.c.getP_payment_mode();
            if (TextUtils.isEmpty(p_payment_mode)) {
                p_payment_mode = "PM_NOT_SET";
            }
            u a = u.a();
            String str4 = this.p;
            String str5 = this.o;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append("; ");
            stringBuilder.append(this.c.getDesc());
            stringBuilder.append("; ");
            stringBuilder.append(this.c.getP_cost());
            stringBuilder.append("; ");
            stringBuilder.append(p_payment_mode);
            stringBuilder.append("; ");
            stringBuilder.append(p_discounted_cost);
            a.a(str4, str5, stringBuilder.toString());
        }
        if (!(TextUtils.isEmpty(this.i) || this.i == null)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(c.c);
            stringBuilder2.append("&token=");
            stringBuilder2.append(this.b.getCurrentUser().getAuthToken());
            stringBuilder2.append("&payment_sess_id=");
            stringBuilder2.append(this.i);
            stringBuilder2.append("&failure_msg=");
            stringBuilder2.append(str);
            stringBuilder2.append("&failure_code=");
            stringBuilder2.append(str2);
            stringBuilder2.append("&payment_status=");
            stringBuilder2.append(str3);
            str = stringBuilder2.toString();
            if (this.c != null) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append("&product_id=");
                stringBuilder3.append(this.c.getP_id());
                stringBuilder3.append("&payment_mode=");
                stringBuilder3.append(this.c.getP_payment_mode());
                str = stringBuilder3.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(BusinessObjectType.BasicResponse);
            uRLManager.a(Priority.HIGH);
            uRLManager.a(str);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    if (str3.equalsIgnoreCase("success")) {
                        ag.this.i = null;
                    }
                }
            }, uRLManager);
        }
        if (this.c != null && !str3.equalsIgnoreCase("success")) {
            MoEngage.getInstance().reportOnPaymentFailed(this.c);
        }
    }

    private void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(this.c.getIs_trial()) && this.c.getIs_trial().equalsIgnoreCase("Y")) {
            f(context);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("android")) {
            e(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("paytm")) {
            f(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("payu_ccdc")) {
            d(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("hermes_android")) {
            g(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("operator")) {
            h(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("payu_nb")) {
            a(str, str2, context);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("paypal")) {
            c(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("adyen_ccdc")) {
            b(context, str, str2);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("fc_wallet")) {
            Toast.makeText(context, "Please try some another payment mode.", 0).show();
        } else if (this.c.isWebView() && !TextUtils.isEmpty(this.c.getBankCode()) && !TextUtils.isEmpty(this.c.getPaymentGateway())) {
            e(context);
        } else if (this.c.getP_payment_mode().equalsIgnoreCase("simpl") && GaanaApplication.getInstance().getCurrentUser().getUserProfile() != null && !TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber())) {
            d(context);
        }
    }

    private void d(Context context) {
        new SimplPayBottomSheet(context, this.c).show();
    }

    public void c() {
        if (GaanaApplication.getInstance().getCurrentUser().getUserProfile() != null && !TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber())) {
            Simpl.getInstance().isUserApproved(new SimplUser("", GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber())).execute(new SimplUserApprovalListenerV2() {
                public void onError(Throwable th) {
                }

                public void onSuccess(boolean z, String str, boolean z2) {
                    Constants.bN = z;
                }
            });
        }
    }

    public void a(int i, final an anVar) {
        Simpl.getInstance().generateZeroClickToken(new SimplUser("", GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber()), new SimplZeroClickTokenListener() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(SimplZeroClickTokenAuthorization simplZeroClickTokenAuthorization) {
                Constants.bM = simplZeroClickTokenAuthorization.getZeroClickToken();
                if (anVar != null) {
                    anVar.onSimplPaymentUpdate();
                }
            }
        });
    }

    public void a(ProductItem productItem, int i, final an anVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(UserSubscriptionData.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.c(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/simpl/index.php?type=simplpay&token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        stringBuilder.append("&coupon=");
        stringBuilder.append(productItem.getCouponCode());
        stringBuilder.append("&zero_click_token=");
        stringBuilder.append(Constants.bM);
        stringBuilder.append("&pid=");
        stringBuilder.append(productItem.getP_id());
        stringBuilder.append("&is_renewal_on=");
        stringBuilder.append(i);
        uRLManager.a(stringBuilder.toString());
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                UserSubscriptionData userSubscriptionData = (UserSubscriptionData) obj;
                if (anVar != null) {
                    anVar.onSimplPaymentUpdate(userSubscriptionData);
                }
            }
        }, uRLManager);
    }

    public void a(int i, final an anVar, ProductItem productItem) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(UserSubscriptionData.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.c(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/simpl/index.php?type=makecharge&token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        stringBuilder.append("&coupon=");
        stringBuilder.append(productItem.getCouponCode());
        stringBuilder.append("&zero_click_token=");
        stringBuilder.append(Constants.bM);
        stringBuilder.append("&is_renewal_on=");
        stringBuilder.append(i);
        stringBuilder.append("&pid=");
        stringBuilder.append(productItem.getP_id());
        stringBuilder.append("&payload=");
        stringBuilder.append(Constants.bO);
        uRLManager.a(stringBuilder.toString());
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                UserSubscriptionData userSubscriptionData = (UserSubscriptionData) obj;
                if (anVar != null) {
                    anVar.onSimplPaymentUpdate(userSubscriptionData);
                }
            }
        }, uRLManager);
    }

    public void c(final Context context) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(SimplEligibility.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.c(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/simpl/index.php?type=chkeligibility&token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        stringBuilder.append("&zero_click_token=");
        stringBuilder.append(Constants.bM);
        stringBuilder.append("&payload=");
        stringBuilder.append(Constants.bO);
        uRLManager.a(stringBuilder.toString());
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                SimplEligibility simplEligibility = (SimplEligibility) obj;
                Constants.bP = simplEligibility.getIsSimplEligible();
                Constants.bQ = simplEligibility.getAmount();
                Constants.bR = simplEligibility.getRedirectionUrl();
                Constants.bS = simplEligibility.getMessage();
                if (Constants.bP == 0) {
                    Constants.bN = false;
                } else if (Constants.bP == 1) {
                    Constants.bN = true;
                } else {
                    ag.a(context).c();
                }
            }
        }, uRLManager);
    }

    private void b(Context context, String str, String str2) {
        if (this.c.getAdyenParams() == null || TextUtils.isEmpty(((AdyenParams) this.c.getAdyenParams().get(0)).getWebview_url())) {
            TextUtils.isEmpty(((AdyenParams) this.c.getAdyenParams().get(0)).getApi_url());
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        hashMap.put("product_id", this.c.getP_id());
        if (TextUtils.isEmpty(this.c.getP_discounted_cost())) {
            hashMap.put("product_cost", this.c.getP_cost());
        } else {
            hashMap.put("product_cost", this.c.getP_discounted_cost());
        }
        hashMap.put("currency", this.c.getP_cost_curr());
        hashMap.put("coupon", this.c.getP_coupon_code());
        hashMap.put("payment_mode", this.c.getP_payment_mode());
        hashMap.put("duration_days", this.c.getDuration_days());
        hashMap.put("desc", this.c.getDesc());
        hashMap.put("is_si_msg", this.c.getIs_si_msg());
        hashMap.put("profile_pic", this.b.getCurrentUser().getUserProfile().getImg());
        hashMap.put("deviceType", Constants.bU);
        hashMap.put("appVersion", "V7");
        hashMap.put("deviceId", Util.l(GaanaApplication.getContext()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        hashMap.put("gaanaAppVersion", stringBuilder.toString());
        Intent intent = new Intent(context, AdyenWebViewActivity.class);
        intent.putExtra("headers", hashMap);
        intent.putExtra("url", ((AdyenParams) this.c.getAdyenParams().get(0)).getWebview_url());
        context.startActivity(intent);
    }

    private void c(final Context context, String str, String str2) {
        PurchasePaypalManager.a(context).a(this.d);
        PurchasePaypalManager.a(context).a(this.c, str, str2, new b() {
            public void a() {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.loading));
                }
                HashMap hashMap = new HashMap();
                hashMap.put("type", "get_approval");
                hashMap.put("cid", Util.X());
                hashMap.put("pid", ag.this.c.getP_id());
                hashMap.put("platform", "android");
                URLManager uRLManager = new URLManager();
                uRLManager.a(PaypalApprovalUrlModel.class);
                uRLManager.a(Priority.HIGH);
                uRLManager.b(Boolean.valueOf(false));
                uRLManager.c(1);
                uRLManager.a(hashMap);
                String str = "https://pay.gaana.com/paypal/index.php";
                if ("https://api.gaana.com/".contains("a1api")) {
                    str = "https://a1pay.gaana.com/paypal/index.php";
                }
                uRLManager.a(str);
                uRLManager.i(false);
                i.a().a(new af() {
                    public void onRetreivalComplete(Object obj) {
                        PaypalApprovalUrlModel paypalApprovalUrlModel = (PaypalApprovalUrlModel) obj;
                        ((BaseActivity) context).hideProgressDialog();
                        String billing_approval_url = paypalApprovalUrlModel.getBilling_approval_url();
                        String message = paypalApprovalUrlModel.getMessage();
                        PurchasePaypalManager.a(context).a(paypalApprovalUrlModel.getP_ref_id());
                        if (TextUtils.isEmpty(billing_approval_url) || TextUtils.isEmpty(paypalApprovalUrlModel.getStatus()) || !paypalApprovalUrlModel.getStatus().contains("1")) {
                            ((BaseActivity) context).hideProgressDialog();
                            aj a = aj.a();
                            Context context = context;
                            if (TextUtils.isEmpty(message)) {
                                message = context.getString(R.string.server_error);
                            }
                            a.a(context, message);
                            return;
                        }
                        PurchasePaypalManager.a(context).b(billing_approval_url);
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).hideProgressDialog();
                            aj.a().a(context, context.getString(R.string.server_error));
                        }
                    }
                }, uRLManager);
            }
        });
    }

    private void a(String str, String str2, Context context) {
        MoEngage.getInstance().reportPaymentInitiated("NETBANKING", this.c);
        BaseGaanaFragment netBankingFragmentNew = new NetBankingFragmentNew();
        netBankingFragmentNew.a(this.d);
        Bundle bundle = new Bundle();
        bundle.putString("ITEM_ID", str);
        bundle.putString("ITEM_NAME", str2);
        bundle.putSerializable("PRODUCT", this.c);
        netBankingFragmentNew.setArguments(bundle);
        ((GaanaActivity) context).displayFragment(netBankingFragmentNew);
    }

    private void e(Context context) {
        ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true));
        ac acVar = new ac(context);
        acVar.b(this.c);
        acVar.a(this.c);
    }

    private void d(Context context, String str, String str2) {
        BaseGaanaFragment payUCreditDebitFragment = new PayUCreditDebitFragment();
        payUCreditDebitFragment.a(this.d);
        Bundle bundle = new Bundle();
        bundle.putString("ITEM_ID", str);
        bundle.putString("ITEM_NAME", str2);
        bundle.putString("COUPONCODE", this.c.getCouponCode());
        bundle.putSerializable("PRODUCT", this.c);
        payUCreditDebitFragment.setArguments(bundle);
        ((GaanaActivity) context).displayFragment(payUCreditDebitFragment);
    }

    private void e(final Context context, String str, String str2) {
        MoEngage.getInstance().reportPaymentInitiated("GOOGLE_PLAY", this.c);
        PurchaseGoogleManager.a(context, new com.managers.PurchaseGoogleManager.a() {
            public void onInventoryQueryCompeleted(com.iabutils.a aVar, com.iabutils.b bVar) {
            }

            public void onProductsQueryCompleted() {
            }

            public void onFailure(String str) {
                if (ag.this.d != null) {
                    ag.this.d.onFailure(str, "failed");
                }
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).hideProgressDialog();
                }
                if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
                    PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
                }
            }

            public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                if (ag.this.d != null) {
                    ag.this.d.onPurchaseFinished(subscriptionPurchaseType);
                }
                if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
                    PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
                }
            }
        }).a(this.c, str, str2);
    }

    private void f(Context context, String str, String str2) {
        MoEngage.getInstance().reportPaymentInitiated("PAYTM", this.c);
        ah.a(context, this.d).a(this.c, str, str2);
    }

    private void g(Context context, String str, String str2) {
        MoEngage.getInstance().reportPaymentInitiated("HERMES", this.c);
        PurchaseHermesManager.a(context).a(this.c, str, str2);
    }

    private void h(Context context, String str, String str2) {
        MoEngage.getInstance().reportPaymentInitiated("OPERATOR", this.c);
        PurchaseOperatorManager.a(context, this.d).a(this.c, str, str2);
    }

    private void f(final Context context) {
        this.i = null;
        MoEngage.getInstance().reportPaymentInitiated("TRIAL", this.c);
        String str = "https://api.gaana.com/gaanaplusservice.php?type=manual_gplus_trial";
        final UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str = stringBuilder.toString();
        }
        str = str.replace(" ", "%20");
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(PaymentTrialStatusModel.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                PaymentTrialStatusModel paymentTrialStatusModel = (PaymentTrialStatusModel) obj;
                if (paymentTrialStatusModel == null) {
                    aj.a().a(context, context.getResources().getString(R.string.error_msg_trial_purchase_failed));
                } else if (paymentTrialStatusModel != null && paymentTrialStatusModel.getStatus() != 0) {
                    String str = "";
                    if (!(currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null || currentUser.getUserProfile().getUserId() == null)) {
                        str = currentUser.getUserProfile().getUserId();
                    }
                    u.a().a(ag.this.c, ag.this.c.getItem_id(), ag.this.c.getDesc(), str, "");
                    ag.this.g(context);
                } else if (paymentTrialStatusModel != null && !TextUtils.isEmpty(paymentTrialStatusModel.getMessage())) {
                    aj.a().a(context, paymentTrialStatusModel.getMessage());
                    ag.this.d.onFailure("TRIAL_NOT_APPLICABLE_RELOAD", "");
                }
            }
        }, uRLManager);
    }

    public String d() {
        StringBuilder stringBuilder;
        if (Constants.bN) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("&simpl_approved=1&simpl_amount=");
            stringBuilder.append(Constants.bQ);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("&simpl_approved=0&simpl_amount=");
        stringBuilder.append(Constants.bQ);
        return stringBuilder.toString();
    }

    public void a(final Context context, final String str, final a aVar) {
        this.i = null;
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice.php?type=duration_listing");
        stringBuilder.append(a(context).d());
        String stringBuilder2 = stringBuilder.toString();
        if (currentUser != null && currentUser.getLoginStatus()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder.toString();
        }
        ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.loading));
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(PaymentProductModel.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                boolean z = false;
                Login.isSignupFromInside = false;
                ((BaseActivity) context).hideProgressDialog();
                if (obj instanceof PaymentProductModel) {
                    PaymentProductModel paymentProductModel = (PaymentProductModel) obj;
                    if (paymentProductModel.getPurchase() != null) {
                        Iterator it = paymentProductModel.getPurchase().getProducts().iterator();
                        while (it.hasNext()) {
                            ProductItem productItem = (ProductItem) it.next();
                            if (!TextUtils.isEmpty(productItem.getIs_trial()) && productItem.getIs_trial().equalsIgnoreCase("Y")) {
                                ag.this.c = productItem;
                                z = true;
                                ag.this.a(str, aVar, context);
                                break;
                            }
                        }
                    }
                }
                if (!z && !str.equals("MOEngage")) {
                    u.a().a("Gaana+ Dialog", "Trial Dialog Activation failed- product not matched", str);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) context).hideProgressDialog();
                if (aVar != null) {
                    aVar.onFailure("Network Error", "");
                }
            }
        }, uRLManager);
    }

    private void a(final String str, a aVar, final Context context) {
        this.d = aVar;
        String str2 = "https://api.gaana.com/gaanaplusservice.php?type=manual_gplus_trial";
        final UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str2 = stringBuilder.toString();
        }
        str2 = str2.replace(" ", "%20");
        URLManager uRLManager = new URLManager();
        uRLManager.a(str2);
        uRLManager.a(PaymentTrialStatusModel.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                PaymentTrialStatusModel paymentTrialStatusModel = (PaymentTrialStatusModel) obj;
                if (paymentTrialStatusModel == null) {
                    ((BaseActivity) context).hideProgressDialog();
                    aj.a().a(context, context.getResources().getString(R.string.error_msg_trial_purchase_failed));
                    if (!str.equals("MOEngage")) {
                        u.a().a("Gaana+ Dialog", "Trial Dialog Activation failed- Network Error", str);
                    }
                } else if (paymentTrialStatusModel.getStatus() != 0) {
                    if (str.equalsIgnoreCase("Download")) {
                        u.a().a("Download", "Get Trial", "Trial Success");
                    } else if (str.equalsIgnoreCase("TopSong")) {
                        u.a().a("Top Song", "Get Trial", "Trial Success");
                    } else if (str.equalsIgnoreCase("HDQuality")) {
                        u.a().a("Mini Player", "Get Trial", "Trial Success");
                    } else if (str.equalsIgnoreCase("MOEngage")) {
                        u.a().a("MOEngage", "Get Trial", "Trial Success");
                    }
                    if (!(currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null || currentUser.getUserProfile().getUserId() == null)) {
                        currentUser.getUserProfile().getUserId();
                    }
                    ag.this.g(context);
                } else if (paymentTrialStatusModel != null && !TextUtils.isEmpty(paymentTrialStatusModel.getMessage())) {
                    ((BaseActivity) context).hideProgressDialog();
                    aj.a().a(context, paymentTrialStatusModel.getMessage());
                    if (!str.equals("MOEngage")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Trial Dialog Activation failed- ");
                        stringBuilder.append(paymentTrialStatusModel.getMessage());
                        u.a().a("Gaana+ Dialog", stringBuilder.toString(), str);
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) context).hideProgressDialog();
                if (!str.equals("MOEngage")) {
                    u.a().a("Gaana+ Dialog", "Trial Dialog Activation failed- Network Error", str);
                }
            }
        }, uRLManager);
    }

    private void g(Context context) {
        ((BaseActivity) context).hideProgressDialog();
        d.a().a("PREFERENCE_SESSION_TRIAL_FIRSTTIME", true, true);
        d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", GaanaApplication.sessionHistoryCount, true);
        if (this.d != null) {
            this.d.onPurchaseFinished(SubscriptionPurchaseType.SUBSCRIBED_TRIAL);
            MoEngage.getInstance().reportTrialTaken();
            AppsFlyer.getInstance().reportPurchaseCompleted(this.c, "TRIAL");
        }
    }
}

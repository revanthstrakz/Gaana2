package com.managers;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.DeleteHash;
import com.gaana.models.IssueBankHash;
import com.gaana.models.PayUHash;
import com.gaana.models.PayUHash.OrderDetails;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.URLManager.BusinessObjectType;
import com.models.CouponProducts.PaymentGateway;
import com.payu.custombrowser.Bank;
import com.payu.custombrowser.CustomBrowser;
import com.payu.custombrowser.PayUCustomBrowserCallback;
import com.payu.custombrowser.PayUWebChromeClient;
import com.payu.custombrowser.PayUWebViewClient;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.util.CBConstant;
import com.payu.india.Model.MerchantWebService;
import com.payu.india.Model.PaymentParams;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PostData;
import com.payu.india.b.d;
import com.payu.india.c.a;
import com.payu.india.d.b;
import com.payu.india.e.c;
import com.payu.magicretry.MagicRetryFragment;
import com.services.l.au;
import com.services.l.h;
import com.services.l.s;
import com.utilities.Util;
import java.util.HashMap;

public class ac {
    private static ac a;
    private Context b;
    private final GaanaApplication c = GaanaApplication.getInstance();
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private ProductItem o;
    private PaymentGateway p;
    private String q;
    private String r;
    private int s;
    private String t;
    private String u;
    private int v;

    public void a(int i) {
        this.s = i;
    }

    public int a() {
        return this.s;
    }

    public void b(int i) {
        this.v = i;
    }

    public int b() {
        return this.v;
    }

    public ac(Context context) {
        this.b = context;
        a.a(this.b.getApplicationContext());
    }

    public void a(final ProductItem productItem) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_order_detail&prd_id=");
        stringBuilder.append(productItem.getP_id());
        stringBuilder.append("&prd_cost=");
        stringBuilder.append(productItem.getP_cost());
        stringBuilder.append("&source=payment");
        stringBuilder.append("&p_code=");
        stringBuilder.append(productItem.getP_code());
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(1);
        uRLManager.a(PayUHash.class);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof PayUHash) {
                    OrderDetails orderDetail = ((PayUHash) businessObject).getOrderDetail();
                    if (orderDetail != null) {
                        ac.this.i = orderDetail.getTxnid();
                        ac.this.d = orderDetail.getKey();
                        ac.this.j = orderDetail.getSurl();
                        ac.this.k = orderDetail.getFurl();
                        ac.this.g = orderDetail.getFirstname();
                        ac.this.f = orderDetail.getProductInfo();
                        ac.this.h = orderDetail.getEmail();
                        ac.this.m = orderDetail.getHash();
                        ac.this.e = orderDetail.getAmount();
                        ac.this.l = orderDetail.getCurl();
                        ac.this.n = orderDetail.getUser_credentials();
                        ac.this.s = orderDetail.getStore_card();
                        ac.this.v = orderDetail.getSi();
                        ac.this.r = orderDetail.getOfferKey();
                        try {
                            ac.this.a("", "", "", "", "", 2, productItem.getBankCode());
                            return;
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                            return;
                        }
                    }
                    ((BaseActivity) ac.this.b).hideProgressDialog();
                    aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) ac.this.b).hideProgressDialog();
            }
        }, uRLManager);
    }

    public void a(String str, String str2, String str3, String str4, String str5, int i, String str6) throws Exception {
        String str7;
        PaymentParams paymentParams = new PaymentParams();
        paymentParams.a(this.d);
        paymentParams.c(this.e);
        paymentParams.d(this.f);
        paymentParams.e(this.g);
        paymentParams.f(this.h);
        paymentParams.b(this.i);
        paymentParams.g(this.j);
        paymentParams.h(this.k);
        paymentParams.i(this.m);
        if (!TextUtils.isEmpty(this.r)) {
            paymentParams.j(this.r);
        }
        paymentParams.k(this.j);
        paymentParams.l(this.k);
        paymentParams.m(this.l);
        paymentParams.n("");
        paymentParams.o("");
        if (i == 1) {
            str7 = "CC";
            paymentParams.p(this.n);
            paymentParams.q(str);
            paymentParams.v(str2);
            paymentParams.u(str2);
            paymentParams.s(str3);
            paymentParams.t(str4);
            paymentParams.r(str5);
            paymentParams.a(this.s);
        } else if (i == 2) {
            str7 = this.o.getPaymentGateway();
            paymentParams.y(this.o.getPaymentGateway());
            paymentParams.x(this.o.getBankCode());
            paymentParams.p(this.n);
        } else {
            str7 = "NB";
            paymentParams.x(str6);
        }
        PostData postData = null;
        try {
            postData = new b(paymentParams, str7).c();
        } catch (Exception unused) {
        }
        if (postData.c() == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(postData.b());
            stringBuilder.append("&isMobileView=1");
            str = stringBuilder.toString();
            PayuConfig payuConfig = new PayuConfig();
            payuConfig.a(0);
            if (str7.equals("NB")) {
                payuConfig.a(str);
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("&si=");
                stringBuilder2.append(this.v);
                payuConfig.a(stringBuilder2.toString());
            }
            a(payuConfig);
            ((BaseActivity) this.b).hideProgressDialog();
            return;
        }
        ((BaseActivity) this.b).hideProgressDialog();
        if (postData.c() == 5008) {
            aj.a().a(this.b, " Invalid card number, Failed while applying Luhn");
        } else if (postData.c() == 5005) {
            aj.a().a(this.b, "Invalid bank code please verify");
        } else if (postData.c() == 5009) {
            aj.a().a(this.b, " Invalid cvv, please verify");
        } else if (postData.c() == 5010) {
            aj.a().a(this.b, " Invalid month, it should be two digit number range from 01 to 12 MM format");
        } else if (postData.c() == 5011) {
            aj.a().a(this.b, " Invalid year, year should be 4 digit YYYY format");
        } else {
            aj.a().a(this.b, this.b.getString(R.string.some_error_occured));
        }
    }

    public void a(String str, final d dVar, final h hVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.PayUHashes);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof PayUHash) {
                    PayUHash payUHash = (PayUHash) businessObject;
                    OrderDetails orderDetail = payUHash.getOrderDetail();
                    if (orderDetail != null) {
                        ac.this.i = orderDetail.getTxnid();
                        if (TextUtils.isEmpty(ac.this.i)) {
                            aj.a().a(ac.this.b, payUHash.getMessage(), true);
                            ((GaanaActivity) ac.this.b).popBackStack();
                            hVar.a("", null);
                            ((BaseActivity) ac.this.b).hideProgressDialog();
                            return;
                        }
                        ac.this.d = orderDetail.getKey();
                        ac.this.j = orderDetail.getSurl();
                        ac.this.k = orderDetail.getFurl();
                        ac.this.g = orderDetail.getFirstname();
                        ac.this.f = orderDetail.getProductInfo();
                        ac.this.h = orderDetail.getEmail();
                        ac.this.m = orderDetail.getHash();
                        ac.this.e = orderDetail.getAmount();
                        ac.this.l = orderDetail.getCurl();
                        ac.this.n = orderDetail.getUser_credentials();
                        ac.this.s = orderDetail.getStore_card();
                        ac.this.v = orderDetail.getSi();
                        ac.this.t = orderDetail.getStore_hash();
                        ac.this.u = orderDetail.getDelete_hash();
                        ac.this.r = orderDetail.getOfferKey();
                        hVar.a(ac.this.e, payUHash.getSIEnabledBanks());
                        ac.this.a(dVar);
                        return;
                    }
                    hVar.a("", null);
                    ((BaseActivity) ac.this.b).hideProgressDialog();
                    aj.a().a(ac.this.b, payUHash.getMessage(), true);
                    ((GaanaActivity) ac.this.b).popBackStack();
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) ac.this.b).hideProgressDialog();
                aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
            }
        }, uRLManager);
    }

    public void a(ProductItem productItem, final String str) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_order_detail&prd_id=");
        stringBuilder.append(productItem.getP_id());
        stringBuilder.append("&prd_cost=");
        stringBuilder.append(productItem.getP_cost());
        stringBuilder.append("&source=payment");
        stringBuilder.append("&p_code=");
        stringBuilder.append(productItem.getP_code());
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.PayUHashes);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof PayUHash) {
                    OrderDetails orderDetail = ((PayUHash) businessObject).getOrderDetail();
                    if (orderDetail != null) {
                        ac.this.i = orderDetail.getTxnid();
                        ac.this.d = orderDetail.getKey();
                        ac.this.j = orderDetail.getSurl();
                        ac.this.k = orderDetail.getFurl();
                        ac.this.g = orderDetail.getFirstname();
                        ac.this.f = orderDetail.getProductInfo();
                        ac.this.h = orderDetail.getEmail();
                        ac.this.m = orderDetail.getHash();
                        ac.this.e = orderDetail.getAmount();
                        ac.this.l = orderDetail.getCurl();
                        ac.this.n = orderDetail.getUser_credentials();
                        ac.this.s = orderDetail.getStore_card();
                        ac.this.v = orderDetail.getSi();
                        ac.this.r = orderDetail.getOfferKey();
                        try {
                            ac.this.a("", "", "", "", "", 0, str);
                            return;
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                            return;
                        }
                    }
                    ((BaseActivity) ac.this.b).hideProgressDialog();
                    aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) ac.this.b).hideProgressDialog();
            }
        }, uRLManager);
    }

    public void a(d dVar) {
        MerchantWebService merchantWebService = new MerchantWebService();
        merchantWebService.b(this.d);
        merchantWebService.a(CBConstant.PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK);
        merchantWebService.d(this.n);
        merchantWebService.c(this.t);
        PostData c = new com.payu.india.d.a(merchantWebService).c();
        if (c.c() == 0) {
            PayuConfig payuConfig = new PayuConfig();
            payuConfig.a(c.b());
            payuConfig.a(0);
            new c(dVar).execute(new PayuConfig[]{payuConfig});
            ((BaseActivity) this.b).hideProgressDialog();
            return;
        }
        ((BaseActivity) this.b).hideProgressDialog();
    }

    public void a(String str, String str2, String str3, String str4) {
        PaymentParams paymentParams = new PaymentParams();
        paymentParams.a(this.d);
        paymentParams.c(this.e);
        paymentParams.d(this.f);
        paymentParams.e(this.g);
        paymentParams.f(this.h);
        paymentParams.b(this.i);
        paymentParams.g(this.j);
        paymentParams.h(this.k);
        paymentParams.i(this.m);
        if (!TextUtils.isEmpty(this.r)) {
            paymentParams.j(this.r);
        }
        paymentParams.k(this.j);
        paymentParams.l(this.k);
        paymentParams.m(this.l);
        paymentParams.n("");
        paymentParams.o("");
        String str5 = "CC";
        paymentParams.p(this.n);
        paymentParams.w(str);
        paymentParams.s(str3);
        paymentParams.t(str4);
        paymentParams.r(str2);
        PostData postData = null;
        try {
            postData = new b(paymentParams, str5).c();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        if (postData.c() == 0) {
            PayuConfig payuConfig = new PayuConfig();
            payuConfig.a(0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(postData.b());
            stringBuilder.append("&si=");
            stringBuilder.append(this.v);
            payuConfig.a(stringBuilder.toString());
            a(payuConfig);
            ((BaseActivity) this.b).hideProgressDialog();
            return;
        }
        ((BaseActivity) this.b).hideProgressDialog();
        aj.a().a(this.b, this.b.getString(R.string.some_error_occured));
    }

    public void a(final String str, final com.payu.india.b.a aVar) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_delete_hash&card_token=");
        stringBuilder.append(str);
        stringBuilder.append("&token=");
        stringBuilder.append(this.c.getCurrentUser().getAuthToken());
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.DeleteHash);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof DeleteHash) {
                    ac.this.u = ((DeleteHash) businessObject).getDelete_hash();
                    ac.this.b(str, aVar);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
            }
        }, uRLManager);
    }

    private void a(final PayuConfig payuConfig) {
        final String str = payuConfig.b() == 0 ? "https://secure.payu.in/_payment" : "https://test.payu.in/_payment";
        AnonymousClass5 anonymousClass5 = new PayUCustomBrowserCallback() {
            public void onPaymentFailure(String str, String str2) {
                Intent intent = new Intent((Activity) ac.this.b, GaanaActivity.class);
                intent.setFlags(268468224);
                intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPayUPurchaseResponseFailure);
                intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                ac.this.b.startActivity(intent);
            }

            public void onPaymentSuccess(String str, String str2) {
                Intent intent = new Intent((Activity) ac.this.b, GaanaActivity.class);
                intent.setFlags(268468224);
                str = str.replace("success/", "");
                intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPayUPurchaseResponseSuccess);
                intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                ac.this.b.startActivity(intent);
            }

            public void setCBProperties(WebView webView, Bank bank) {
                webView.setWebChromeClient(new PayUWebChromeClient(bank));
                webView.setWebViewClient(new PayUWebViewClient(bank, ac.this.d));
                webView.postUrl(str, payuConfig.a().getBytes());
            }

            public void onBackDismiss() {
                super.onBackDismiss();
            }

            public void onBackButton(Builder builder) {
                super.onBackButton(builder);
            }

            public void initializeMagicRetry(Bank bank, WebView webView, MagicRetryFragment magicRetryFragment) {
                webView.setWebViewClient(new PayUWebViewClient(bank, magicRetryFragment, ac.this.d));
                HashMap hashMap = new HashMap();
                hashMap.put(str, payuConfig.a());
                bank.setMagicRetry(hashMap);
            }
        };
        CustomBrowserConfig customBrowserConfig = new CustomBrowserConfig(this.d, this.i);
        customBrowserConfig.setViewPortWideEnable(false);
        customBrowserConfig.setAutoApprove(false);
        customBrowserConfig.setAutoSelectOTP(true);
        customBrowserConfig.setDisableBackButtonDialog(false);
        customBrowserConfig.setStoreOneClickHash(1);
        customBrowserConfig.setMerchantSMSPermission(false);
        customBrowserConfig.setmagicRetry(true);
        customBrowserConfig.setEnableSurePay(3);
        customBrowserConfig.setGmsProviderUpdatedStatus(-1);
        customBrowserConfig.setMerchantCheckoutActivityPath("com.payu.testapp.MerchantCheckoutActivity");
        customBrowserConfig.setPostURL(str);
        if (payuConfig != null) {
            customBrowserConfig.setPayuPostData(payuConfig.a());
        }
        new CustomBrowser().addCustomBrowser((Activity) this.b, customBrowserConfig, anonymousClass5);
    }

    public void b(String str, com.payu.india.b.a aVar) {
        MerchantWebService merchantWebService = new MerchantWebService();
        merchantWebService.b(this.d);
        merchantWebService.a("delete_user_card");
        merchantWebService.d(this.n);
        merchantWebService.e(str);
        merchantWebService.c(this.u);
        PostData c = new com.payu.india.d.a(merchantWebService).c();
        if (c.c() == 0) {
            PayuConfig payuConfig = new PayuConfig();
            payuConfig.a(0);
            payuConfig.a(c.b());
            payuConfig.a(payuConfig.b());
            new com.payu.india.e.a(aVar).execute(new PayuConfig[]{payuConfig});
            return;
        }
        aj.a().a(this.b, this.b.getString(R.string.some_error_occured));
    }

    public void a(final String str, final com.payu.india.b.b bVar) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/payu/index.php?type=get_card_hash&card_no=");
        stringBuilder.append(str);
        stringBuilder.append("&token=");
        stringBuilder.append(this.c.getCurrentUser().getAuthToken());
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.IssueBankHash);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof IssueBankHash) {
                    ac.this.q = ((IssueBankHash) businessObject).getCard_hash();
                    if (TextUtils.isEmpty(ac.this.q)) {
                        aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
                    } else {
                        ac.this.b(str, bVar);
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                aj.a().a(ac.this.b, ac.this.b.getString(R.string.some_error_occured));
            }
        }, uRLManager);
    }

    public void b(String str, com.payu.india.b.b bVar) {
        MerchantWebService merchantWebService = new MerchantWebService();
        merchantWebService.a("check_isDomestic");
        merchantWebService.d(str);
        merchantWebService.b(this.d);
        merchantWebService.c(this.q);
        PostData c = new com.payu.india.d.a(merchantWebService).c();
        if (c.c() == 0) {
            PayuConfig payuConfig = new PayuConfig();
            payuConfig.a(0);
            payuConfig.a(c.b());
            payuConfig.a(payuConfig.b());
            new com.payu.india.e.b(bVar).execute(new PayuConfig[]{payuConfig});
            return;
        }
        aj.a().a(this.b, this.b.getString(R.string.some_error_occured));
    }

    public void c() {
        if (this.o != null) {
            ((BaseActivity) this.b).updateUserStatus(new au() {
                public void onUserStatusUpdated() {
                    ((BaseActivity) ac.this.b).hideProgressDialog();
                    Util.aa();
                    Toast.makeText(ac.this.c, ac.this.c.getString(R.string.enjoy_using_gaana_plus), 1).show();
                    if (Util.v(ac.this.b)) {
                        Intent intent = new Intent(ac.this.b, GaanaActivity.class);
                        intent.setFlags(71303168);
                        ac.this.b.startActivity(intent);
                    }
                }
            });
            b(this.o, "Success");
            u.a().a(this.o, this.o.getItem_id(), this.o.getP_name(), this.c.getCurrentUser().getUserProfile().getUserId(), this.o.getCouponCode());
            MoEngage.getInstance().reportOnPaymentCompleted(this.o, ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser());
            Constants.a(this.o);
            return;
        }
        ((BaseActivity) this.b).updateUserStatus(new au() {
            public void onUserStatusUpdated() {
                ((BaseActivity) ac.this.b).hideProgressDialog();
                Util.aa();
                Toast.makeText(ac.this.c, ac.this.c.getString(R.string.enjoy_using_gaana_plus), 1).show();
                if (Util.v(ac.this.b)) {
                    Intent intent = new Intent(ac.this.b, GaanaActivity.class);
                    intent.setFlags(71303168);
                    ac.this.b.startActivity(intent);
                }
            }
        });
        ((BaseActivity) this.b).sendCouponPaymentGAEvent(this.p, "Success");
    }

    public void d() {
        ((BaseActivity) this.b).updateUserStatus(new au() {
            public void onUserStatusUpdated() {
            }
        });
        if (this.o != null) {
            Toast.makeText(this.c, this.b.getString(R.string.purchase_error), 1).show();
            b(this.o, "PayU Transaction Failure");
            return;
        }
        Toast.makeText(this.c, this.b.getString(R.string.purchase_error), 1).show();
        ((BaseActivity) this.b).sendCouponPaymentGAEvent(this.p, "PayU Coupon Transaction Failure");
    }

    private void b(ProductItem productItem, String str) {
        ((BaseActivity) this.b).sendPaymentGAEvent(productItem, str);
    }

    public void b(ProductItem productItem) {
        this.o = productItem;
    }
}

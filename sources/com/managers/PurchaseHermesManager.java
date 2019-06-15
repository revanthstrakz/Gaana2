package com.managers;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.MyProfile;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.HermesOrderIdResponse;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.i.i;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;

public class PurchaseHermesManager {
    private static PurchaseHermesManager a;
    private HermesOrderIdResponse b = null;
    private Context c = null;
    private ProductItem d = null;
    private String e;
    private String f;

    public enum PaymentResponse {
        SUCCESS,
        FAILURE
    }

    private PurchaseHermesManager(Context context) {
        this.c = context;
    }

    public static PurchaseHermesManager a(Context context) {
        if (a == null) {
            a = new PurchaseHermesManager(context);
        }
        a.c = context;
        return a;
    }

    public void a(ProductItem productItem, String str, String str2) {
        this.d = productItem;
        this.e = str;
        this.f = str2;
        a(productItem);
    }

    public void a(String str, PaymentResponse paymentResponse) {
        if (!TextUtils.isEmpty(str)) {
            if (paymentResponse == PaymentResponse.SUCCESS) {
                ((BaseActivity) this.c).updateUserStatus(new au() {
                    public void onUserStatusUpdated() {
                        ((BaseActivity) PurchaseHermesManager.this.c).hideProgressDialog();
                        Util.aa();
                        aj.a().a(PurchaseHermesManager.this.c, PurchaseHermesManager.this.c.getString(R.string.enjoy_using_gaana_plus));
                        if (Util.v(PurchaseHermesManager.this.c)) {
                            Intent intent = new Intent(PurchaseHermesManager.this.c, GaanaActivity.class);
                            intent.setFlags(71303168);
                            PurchaseHermesManager.this.c.startActivity(intent);
                        }
                    }
                });
                a(this.d, "Success");
                str = this.d.getP_cost_curr();
                if (str != null) {
                    boolean equalsIgnoreCase = str.equalsIgnoreCase("Rs.");
                }
                str = "";
                if (this.b != null && this.b.getIFrameUrl().contains("paymentId=")) {
                    str = this.b.getIFrameUrl().split("paymentId=")[1];
                }
                u.a().a(this.d, this.e, this.f, str, this.d.getCouponCode());
                MoEngage.getInstance().reportOnPaymentCompleted(this.d, ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser());
                AppsFlyer.getInstance().reportPurchaseCompleted(this.d, "HERMES");
                Constants.a(this.d);
            } else if (paymentResponse == PaymentResponse.FAILURE) {
                aj.a().a(this.c, this.c.getString(R.string.transaction_successful));
                ProductItem productItem = this.d;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Hermes Transaction Failure<response=");
                stringBuilder.append(paymentResponse);
                stringBuilder.append(">");
                a(productItem, stringBuilder.toString());
            }
            if (this.c instanceof WebViewActivity) {
                ((WebViewActivity) this.c).finish();
            }
        }
    }

    private void a(ProductItem productItem) {
        if (this.c instanceof BaseActivity) {
            ((BaseActivity) this.c).showProgressDialog(Boolean.valueOf(true), this.c.getString(R.string.purchase_initializing));
        }
        String replace = "https://api.gaana.com/gaanaplusservice.php?type=updatepayment_hermes_mob&product_id=<product_id>".replace("<product_id>", productItem.getP_id());
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || replace.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(replace);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            replace = stringBuilder.toString();
        }
        replace = replace.replace(" ", "%20");
        URLManager uRLManager = new URLManager();
        uRLManager.a(HermesOrderIdResponse.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(replace);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    PurchaseHermesManager.this.b = (HermesOrderIdResponse) obj;
                } else {
                    PurchaseHermesManager.this.b = null;
                }
                ((BaseActivity) PurchaseHermesManager.this.c).hideProgressDialog();
                if (PurchaseHermesManager.this.b == null) {
                    return;
                }
                if ("1".equalsIgnoreCase(PurchaseHermesManager.this.b.getStatus()) || "true".equalsIgnoreCase(PurchaseHermesManager.this.b.getStatus())) {
                    UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
                    if (currentUser != null) {
                        PurchaseHermesManager.this.a(PurchaseHermesManager.this.b, currentUser.getUserProfile());
                    } else {
                        aj.a().a(PurchaseHermesManager.this.c, PurchaseHermesManager.this.b.getMessage());
                    }
                }
            }
        }, uRLManager);
    }

    private void a(HermesOrderIdResponse hermesOrderIdResponse, MyProfile myProfile) {
        if (myProfile != null) {
            if (Util.j(this.c)) {
                Intent intent = new Intent(this.c, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", hermesOrderIdResponse.getIFrameUrl());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("EXTRA_TRANSACTION_HERMES_INITIATED", true);
                intent.putExtra("title", hermesOrderIdResponse.getIFrameTitle());
                this.c.startActivity(intent);
                return;
            }
            ap.a().f(this.c);
        }
    }

    private void a(ProductItem productItem, String str) {
        ((BaseActivity) this.c).sendPaymentGAEvent(productItem, str);
    }
}

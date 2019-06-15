package com.managers;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.constants.Constants;
import com.d.a.b;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.i.i;
import com.managers.ag.a;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;

public class PurchasePaypalManager {
    private static PurchasePaypalManager a;
    private Context b = null;
    private GaanaApplication c;
    private a d = null;
    private ProductItem e = null;
    private String f;
    private String g;
    private TRANSACTION_STATUS h = TRANSACTION_STATUS.NOT_INITITATED;
    private String i = "";

    public enum PaymentResponse {
        SUCCESS,
        FAILURE
    }

    public enum TRANSACTION_STATUS {
        NOT_INITITATED,
        INITIATED,
        SUCCESS,
        FAIL
    }

    private PurchasePaypalManager(Context context) {
        this.b = context;
        this.c = (GaanaApplication) context.getApplicationContext();
    }

    public static PurchasePaypalManager a(Context context) {
        if (a == null) {
            a = new PurchasePaypalManager(context);
        }
        a.b = context;
        return a;
    }

    public void a(a aVar) {
        a.d = aVar;
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(ProductItem productItem, String str, String str2, b bVar) {
        this.e = productItem;
        this.f = str;
        this.g = str2;
        if (this.c.isAppInOfflineMode()) {
            ((BaseActivity) this.b).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.b)) {
            if (this.b instanceof BaseActivity) {
                ((BaseActivity) this.b).showProgressDialog(Boolean.valueOf(true), this.b.getString(R.string.loading));
            }
            com.d.a.a().a(this.b, bVar);
        } else {
            ap.a().f(this.b);
        }
    }

    public void b(String str) {
        this.h = TRANSACTION_STATUS.INITIATED;
        com.d.a.a().a(this.b, str);
    }

    public void a(int i, int i2, Intent intent) {
        if (this.h != TRANSACTION_STATUS.SUCCESS) {
            aj.a().a(this.b, this.b.getString(R.string.transaction_cancelled));
            if (!TextUtils.isEmpty(this.i)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://pay.gaana.com/paypal/paypal_cancel_order.php?type=cancel&p_ref_id=");
                stringBuilder.append(this.i);
                String stringBuilder2 = stringBuilder.toString();
                if ("https://api.gaana.com/".contains("a1api")) {
                    stringBuilder2 = stringBuilder2.replace("pay.", "a1pay.");
                }
                URLManager uRLManager = new URLManager();
                uRLManager.a(stringBuilder2);
                uRLManager.b(Boolean.valueOf(false));
                uRLManager.a(String.class);
                i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                    }
                }, uRLManager);
            }
        }
    }

    public void a(TRANSACTION_STATUS transaction_status) {
        this.h = transaction_status;
    }

    public void a(String str, PaymentResponse paymentResponse) {
        if (!TextUtils.isEmpty(str)) {
            if (paymentResponse == PaymentResponse.SUCCESS) {
                this.h = TRANSACTION_STATUS.SUCCESS;
                ((BaseActivity) this.b).updateUserStatus(new au() {
                    public void onUserStatusUpdated() {
                        ((BaseActivity) PurchasePaypalManager.this.b).hideProgressDialog();
                        Util.aa();
                        Toast.makeText(PurchasePaypalManager.this.c, PurchasePaypalManager.this.c.getString(R.string.enjoy_using_gaana_plus), 1).show();
                        if (Util.v(PurchasePaypalManager.this.b)) {
                            Intent intent = new Intent(PurchasePaypalManager.this.b, GaanaActivity.class);
                            intent.setFlags(71303168);
                            PurchasePaypalManager.this.b.startActivity(intent);
                        }
                    }
                });
                a(this.e, "Success");
                u.a().a(this.e, this.f, this.g, this.c.getCurrentUser().getUserProfile().getUserId(), this.e.getCouponCode());
                MoEngage.getInstance().reportOnPaymentCompleted(this.e, ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser());
                AppsFlyer.getInstance().reportPurchaseCompleted(this.e, "PAYPAL");
                Constants.a(this.e);
            } else if (paymentResponse == PaymentResponse.FAILURE) {
                this.h = TRANSACTION_STATUS.FAIL;
                ((BaseActivity) this.b).updateUserStatus(new au() {
                    public void onUserStatusUpdated() {
                    }
                });
                if (this.d != null) {
                    this.d.onFailure(this.b.getString(R.string.purchase_error), "failed");
                }
                Toast.makeText(this.c, this.b.getString(R.string.purchase_error), 1).show();
                ProductItem productItem = this.e;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Paypal Transaction Failure<response=");
                stringBuilder.append(paymentResponse);
                stringBuilder.append(">");
                a(productItem, stringBuilder.toString());
            }
            if (this.b instanceof WebViewActivity) {
                ((WebViewActivity) this.b).finish();
            }
        }
    }

    private void a(ProductItem productItem, String str) {
        ((BaseActivity) this.b).sendPaymentGAEvent(productItem, str);
    }
}

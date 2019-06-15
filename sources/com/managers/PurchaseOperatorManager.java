package com.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.OperatorWebDetailModel;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.i.i;
import com.managers.ag.a;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import java.util.Arrays;
import java.util.List;

public class PurchaseOperatorManager {
    public static List<String> a = Arrays.asList(new String[]{"airtel", "idea", "vodafone", "boku", "juno"});
    private static PurchaseOperatorManager b;
    private Context c = null;
    private GaanaApplication d;
    private ProductItem e = null;
    private String f;
    private String g;
    private a h = null;
    private OperatorWebDetailModel i;
    private String j;

    public enum PaymentResponse {
        SUCCESS,
        FAILURE
    }

    private PurchaseOperatorManager(Context context) {
        this.c = context;
        this.d = (GaanaApplication) context.getApplicationContext();
    }

    public static PurchaseOperatorManager a(Context context) {
        if (b == null) {
            b = new PurchaseOperatorManager(context);
        }
        b.c = context;
        return b;
    }

    public static PurchaseOperatorManager a(Context context, a aVar) {
        if (b == null) {
            b = new PurchaseOperatorManager(context);
        }
        b.c = context;
        b.h = aVar;
        return b;
    }

    public void a(final ProductItem productItem, String str, String str2) {
        this.e = productItem;
        this.f = str;
        this.g = str2;
        if (this.d.isAppInOfflineMode()) {
            ((BaseActivity) this.c).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.c)) {
            if (this.c instanceof BaseActivity) {
                ((BaseActivity) this.c).showProgressDialog(Boolean.valueOf(true), this.c.getString(R.string.fetching_details_from_server));
            }
            URLManager uRLManager = new URLManager();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/getproductinfo.php?type=get_p_info&pr_id=");
            stringBuilder.append(productItem.getP_id());
            str2 = stringBuilder.toString();
            if (!(TextUtils.isEmpty(productItem.getP_pay_desc()) || productItem.getP_pay_desc().contains("juno"))) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/gettelecoinfo.php?type=get_product_info&pr_id=");
                stringBuilder.append(productItem.getP_id());
                str2 = stringBuilder.toString();
            }
            uRLManager.a(str2);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a(OperatorWebDetailModel.class);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    PurchaseOperatorManager.this.i = (OperatorWebDetailModel) obj;
                    if (PurchaseOperatorManager.this.i != null) {
                        PurchaseOperatorManager.this.j = PurchaseOperatorManager.this.i.getWeb_view_url();
                        ((GaanaActivity) PurchaseOperatorManager.this.c).checkSetLoginStatus(new ad() {
                            public void onLoginSuccess() {
                                if (TextUtils.isEmpty(PurchaseOperatorManager.this.j)) {
                                    Toast.makeText(PurchaseOperatorManager.this.d, PurchaseOperatorManager.this.c.getString(R.string.purchase_error), 1).show();
                                    return;
                                }
                                Intent intent = new Intent(PurchaseOperatorManager.this.c, WebViewActivity.class);
                                intent.putExtra("EXTRA_WEBVIEW_URL", PurchaseOperatorManager.this.j);
                                intent.putExtra("title", productItem.getP_pay_desc());
                                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                                intent.putExtra("EXTRA_TRANSACTION_OPERATOR_INITIATED", true);
                                ((Activity) PurchaseOperatorManager.this.c).startActivityForResult(intent, 708);
                            }
                        }, PurchaseOperatorManager.this.c.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (PurchaseOperatorManager.this.h != null) {
                        PurchaseOperatorManager.this.h.onFailure(PurchaseOperatorManager.this.c.getString(R.string.purchase_error), "failed");
                    }
                }
            }, uRLManager);
        } else {
            ap.a().f(this.c);
        }
    }

    public void a(String str, PaymentResponse paymentResponse) {
        if (paymentResponse == PaymentResponse.SUCCESS) {
            ((BaseActivity) this.c).updateUserStatus(new au() {
                public void onUserStatusUpdated() {
                    ((BaseActivity) PurchaseOperatorManager.this.c).hideProgressDialog();
                    Util.aa();
                    Toast.makeText(PurchaseOperatorManager.this.d, PurchaseOperatorManager.this.d.getString(R.string.enjoy_using_gaana_plus), 1).show();
                    if (Util.v(PurchaseOperatorManager.this.c)) {
                        Intent intent = new Intent(PurchaseOperatorManager.this.c, GaanaActivity.class);
                        intent.setFlags(71303168);
                        PurchaseOperatorManager.this.c.startActivity(intent);
                    }
                }
            });
            a(this.e, "Success");
            u.a().a(this.e, this.f, this.g, this.d.getCurrentUser().getUserProfile().getUserId(), this.e.getCouponCode());
            MoEngage.getInstance().reportOnPaymentCompleted(this.e, ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser());
            AppsFlyer.getInstance().reportPurchaseCompleted(this.e, "OPERATOR");
            Constants.a(this.e);
        } else if (paymentResponse == PaymentResponse.FAILURE) {
            ((BaseActivity) this.c).updateUserStatus(new au() {
                public void onUserStatusUpdated() {
                }
            });
            if (this.h != null) {
                this.h.onFailure(this.c.getString(R.string.purchase_error), "failed");
            }
            Toast.makeText(this.d, this.c.getString(R.string.purchase_error), 1).show();
            ProductItem productItem = this.e;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Operator Transaction Failure<response=");
            stringBuilder.append(paymentResponse);
            stringBuilder.append(">");
            a(productItem, stringBuilder.toString());
        }
        if (this.c instanceof WebViewActivity) {
            ((WebViewActivity) this.c).finish();
        }
    }

    private void a(ProductItem productItem, String str) {
        ((BaseActivity) this.c).sendPaymentGAEvent(productItem, str);
    }
}

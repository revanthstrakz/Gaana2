package com.managers;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.MyProfile;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.ag.a;
import com.models.GaanaMiniProduct;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.services.l.af;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ah {
    private static ah b;
    String a;
    private Context c = null;
    private Map<String, String> d = null;
    private PaytmPGService e = null;
    private String f;
    private String g;
    private a h;
    private JSONObject i;

    private ah(Context context) {
        this.c = context;
    }

    public static ah a(Context context, a aVar) {
        if (b == null) {
            synchronized (ah.class) {
                if (b == null) {
                    b = new ah(context);
                }
            }
        }
        b.h = aVar;
        return b;
    }

    public void a(ProductItem productItem, String str, String str2) {
        this.f = str;
        this.g = str2;
        a(productItem);
    }

    private void a(final ProductItem productItem) {
        if (this.c instanceof BaseActivity) {
            ((BaseActivity) this.c).showProgressDialog(Boolean.valueOf(true), this.c.getString(R.string.purchase_initializing));
        }
        String str = "https://pay.gaana.com/paytm/index.php?type=get_order_id&product_id=<product_id>&product_cost=<product_cost>";
        GaanaMiniProduct a = ag.a(this.c).a();
        if (a != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&entity_id=");
            stringBuilder.append(a.b().e());
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&entity_type=");
            stringBuilder.append(a.b().d());
            str = stringBuilder.toString();
        }
        str = str.replace("<product_id>", productItem.getP_id());
        if (TextUtils.isEmpty(productItem.getP_discounted_cost())) {
            str = str.replace("<product_cost>", productItem.getP_cost());
        } else {
            str = str.replace("<product_cost>", productItem.getP_discounted_cost());
        }
        str = str.replace(" ", "%20");
        StringBuilder stringBuilder2;
        if (!TextUtils.isEmpty(productItem.getCouponCode())) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("&p_code=");
            stringBuilder2.append(productItem.getCouponCode());
            str = stringBuilder2.toString();
        } else if (!TextUtils.isEmpty(productItem.getP_coupon_code())) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("&p_code=");
            stringBuilder2.append(productItem.getP_coupon_code());
            str = stringBuilder2.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(str);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            /* JADX WARNING: Removed duplicated region for block: B:15:0x0056  */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x00bd  */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0056  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0074  */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x00bd  */
            public void onRetreivalComplete(java.lang.Object r6) {
                /*
                r5 = this;
                r6 = (java.lang.String) r6;
                r0 = "";
                r1 = "";
                r2 = com.managers.ah.this;	 Catch:{ JSONException -> 0x004a }
                r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x004a }
                r3.<init>(r6);	 Catch:{ JSONException -> 0x004a }
                r2.i = r3;	 Catch:{ JSONException -> 0x004a }
                r6 = com.managers.ah.this;	 Catch:{ JSONException -> 0x004a }
                r6 = r6.i;	 Catch:{ JSONException -> 0x004a }
                r2 = "status";
                r6 = r6.getString(r2);	 Catch:{ JSONException -> 0x004a }
                r0 = com.managers.ah.this;	 Catch:{ JSONException -> 0x0045 }
                r0 = r0.i;	 Catch:{ JSONException -> 0x0045 }
                r2 = "message";
                r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0045 }
                r1 = com.managers.ah.this;	 Catch:{ JSONException -> 0x003f }
                r2 = com.managers.ah.this;	 Catch:{ JSONException -> 0x003f }
                r2 = r2.i;	 Catch:{ JSONException -> 0x003f }
                r3 = "order_detail";
                r2 = r2.getJSONObject(r3);	 Catch:{ JSONException -> 0x003f }
                r3 = "ORDER_ID";
                r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x003f }
                r1.a = r2;	 Catch:{ JSONException -> 0x003f }
                goto L_0x0050;
            L_0x003f:
                r1 = move-exception;
                r4 = r0;
                r0 = r6;
                r6 = r1;
                r1 = r4;
                goto L_0x004b;
            L_0x0045:
                r0 = move-exception;
                r4 = r0;
                r0 = r6;
                r6 = r4;
                goto L_0x004b;
            L_0x004a:
                r6 = move-exception;
            L_0x004b:
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
                r6 = r0;
                r0 = r1;
            L_0x0050:
                r1 = android.text.TextUtils.isEmpty(r0);
                if (r1 == 0) goto L_0x0063;
            L_0x0056:
                r0 = com.managers.ah.this;
                r0 = r0.c;
                r1 = 2131821249; // 0x7f1102c1 float:1.9275236E38 double:1.053259642E-314;
                r0 = r0.getString(r1);
            L_0x0063:
                r1 = com.managers.ah.this;
                r1 = r1.c;
                r1 = (com.gaana.BaseActivity) r1;
                r1.hideProgressDialog();
                r1 = android.text.TextUtils.isEmpty(r6);
                if (r1 != 0) goto L_0x00b5;
            L_0x0074:
                r1 = "1";
                r6 = r1.equalsIgnoreCase(r6);
                if (r6 == 0) goto L_0x00b5;
            L_0x007c:
                r6 = com.gaana.application.GaanaApplication.getContext();
                r6 = (com.gaana.application.GaanaApplication) r6;
                r6 = r6.getCurrentUser();
                if (r6 == 0) goto L_0x0094;
            L_0x0088:
                r0 = com.managers.ah.this;
                r1 = r5;
                r6 = r6.getUserProfile();
                r0.a(r1, r6);
                goto L_0x00d5;
            L_0x0094:
                r6 = com.managers.ah.this;
                r6 = r6.h;
                if (r6 == 0) goto L_0x00a7;
            L_0x009c:
                r6 = com.managers.ah.this;
                r6 = r6.h;
                r1 = "failed";
                r6.onFailure(r0, r1);
            L_0x00a7:
                r6 = com.managers.aj.a();
                r1 = com.managers.ah.this;
                r1 = r1.c;
                r6.a(r1, r0);
                goto L_0x00d5;
            L_0x00b5:
                r6 = com.managers.ah.this;
                r6 = r6.h;
                if (r6 == 0) goto L_0x00c8;
            L_0x00bd:
                r6 = com.managers.ah.this;
                r6 = r6.h;
                r1 = "failed";
                r6.onFailure(r0, r1);
            L_0x00c8:
                r6 = com.managers.aj.a();
                r1 = com.managers.ah.this;
                r1 = r1.c;
                r6.a(r1, r0);
            L_0x00d5:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.managers.ah$AnonymousClass1.onRetreivalComplete(java.lang.Object):void");
            }
        }, uRLManager);
    }

    private void a(MyProfile myProfile) {
        this.d = new HashMap();
        try {
            Iterator keys = this.i.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    if (str.equals("CHECKSUMHASH")) {
                        String str2 = (String) this.i.get(str);
                        if (!TextUtils.isEmpty(str2)) {
                            this.d.put("CHECKSUMHASH", str2);
                        }
                    }
                }
            }
            JSONObject jSONObject = this.i.getJSONObject("order_detail");
            Iterator keys2 = jSONObject.keys();
            if (keys2 != null) {
                while (keys2.hasNext()) {
                    String str3 = (String) keys2.next();
                    this.d.put(str3, (String) jSONObject.get(str3));
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(final ProductItem productItem, MyProfile myProfile) {
        if (myProfile != null) {
            if (this.i == null) {
                if (this.h != null) {
                    this.h.onFailure(this.c.getString(R.string.transaction_successful_english_text), "failed");
                }
                aj.a().a(this.c, this.c.getString(R.string.transaction_successful));
            } else if (TextUtils.isEmpty(this.a)) {
                if (this.h != null) {
                    this.h.onFailure(this.c.getString(R.string.transaction_successful_english_text), "failed");
                }
                aj.a().a(this.c, this.c.getString(R.string.transaction_unsuccessful));
            } else {
                try {
                    this.e = PaytmPGService.getProductionService();
                    a(myProfile);
                    this.e.initialize(new PaytmOrder(this.d), null);
                    this.e.startPaymentTransaction(this.c, true, true, new PaytmPaymentTransactionCallback() {
                        public void onTransactionResponse(Bundle bundle) {
                            if (ah.this.h != null) {
                                ah.this.h.onPurchaseFinished(SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_PURCHASED);
                            }
                            String p_cost_curr = productItem.getP_cost_curr();
                            if (p_cost_curr != null) {
                                boolean equalsIgnoreCase = p_cost_curr.equalsIgnoreCase("Rs.");
                            }
                            u.a().a(productItem, ah.this.f, ah.this.g, ah.this.a, productItem.getCouponCode());
                            MoEngage.getInstance().reportOnPaymentCompleted(productItem, ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser());
                            AppsFlyer.getInstance().reportPurchaseCompleted(productItem, "PAYTM");
                            ah.this.a(productItem, "Success");
                            if (ah.this.c instanceof BaseActivity) {
                                Constants.a(productItem);
                            }
                        }

                        public void networkNotAvailable() {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.network_unavailable_msg_english_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.hs__network_unavailable_msg));
                            ah.this.a(productItem, "Paytm Purchase Network Error");
                        }

                        public void someUIErrorOccurred(String str) {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.transaction_successful_english_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.transaction_unsuccessful));
                            ah ahVar = ah.this;
                            ProductItem productItem = productItem;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Paytm UI Error!<String=");
                            stringBuilder.append(str);
                            stringBuilder.append(">");
                            ahVar.a(productItem, stringBuilder.toString());
                        }

                        public void onErrorLoadingWebPage(int i, String str, String str2) {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.transaction_successful_english_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.transaction_unsuccessful));
                            ah ahVar = ah.this;
                            ProductItem productItem = productItem;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Paytm Web page Load Error <String s=");
                            stringBuilder.append(str);
                            stringBuilder.append(">");
                            stringBuilder.append("<String s2=");
                            stringBuilder.append(str2);
                            stringBuilder.append("><int i =");
                            stringBuilder.append(i);
                            stringBuilder.append(">");
                            ahVar.a(productItem, stringBuilder.toString());
                        }

                        public void onBackPressedCancelTransaction() {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.transaction_not_completed_english_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.transaction_not_completed));
                        }

                        public void onTransactionCancel(String str, Bundle bundle) {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.transaction_not_completed_english_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.transaction_not_completed));
                            ah ahVar = ah.this;
                            ProductItem productItem = productItem;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Paytm Transaction Fail<Error Message=");
                            stringBuilder.append(str);
                            stringBuilder.append(">");
                            ahVar.a(productItem, stringBuilder.toString());
                        }

                        public void clientAuthenticationFailed(String str) {
                            if (ah.this.h != null) {
                                ah.this.h.onFailure(ah.this.c.getString(R.string.user_authentication_failed_englisg_text), "failed");
                            }
                            aj.a().a(ah.this.c, ah.this.c.getString(R.string.user_authentication_failed));
                            ah ahVar = ah.this;
                            ProductItem productItem = productItem;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Paytm User authentication failed<Error Message=");
                            stringBuilder.append(str);
                            stringBuilder.append(">");
                            ahVar.a(productItem, stringBuilder.toString());
                        }
                    });
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Paytm payment exception ");
                    stringBuilder.append(e.getMessage());
                    Log.d("PaytmWalletNewManager", stringBuilder.toString());
                    aj.a().a(this.c, this.c.getString(R.string.transaction_not_completed));
                }
            }
        }
    }

    private void a(ProductItem productItem, String str) {
        if (this.c instanceof BaseActivity) {
            ((BaseActivity) this.c).sendPaymentGAEvent(productItem, str);
        }
    }
}

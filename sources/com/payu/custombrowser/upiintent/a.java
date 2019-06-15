package com.payu.custombrowser.upiintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.PayUCustomBrowserCallback;
import com.payu.custombrowser.util.CBUtil;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

class a {
    private WeakReference<Activity> a;
    private String b;
    private PayUCustomBrowserCallback c;
    private com.payu.custombrowser.widgets.a d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private b j;
    private Payment k;
    private Timer l;
    private long m = 1200000;

    a(Activity activity, String str, PayUCustomBrowserCallback payUCustomBrowserCallback) {
        this.a = new WeakReference(activity);
        this.b = str;
        this.j = new b();
        this.c = payUCustomBrowserCallback;
        this.e = "https://secure.payu.in/_payment";
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        new AsyncTask<String, Void, Boolean>() {
            /* Access modifiers changed, original: protected */
            public void onPreExecute() {
                super.onPreExecute();
                if (a.this.a.get() != null && !((Activity) a.this.a.get()).isFinishing()) {
                    a.this.d = new com.payu.custombrowser.widgets.a((Context) a.this.a.get());
                    a.this.d.show();
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            /* renamed from: a */
            public Boolean doInBackground(String... strArr) {
                try {
                    CBUtil cBUtil = new CBUtil();
                    a.this.b = a.this.b.concat("&txn_s2s_flow=2");
                    HttpsURLConnection httpsConn = cBUtil.getHttpsConn(a.this.e, a.this.b);
                    if (httpsConn != null && httpsConn.getResponseCode() == 200) {
                        try {
                            StringBuffer stringBufferFromInputStream = CBUtil.getStringBufferFromInputStream(httpsConn.getInputStream());
                            if (stringBufferFromInputStream != null) {
                                JSONObject jSONObject = new JSONObject(stringBufferFromInputStream.toString());
                                a.this.h = jSONObject.optString("merchantName");
                                a.this.f = jSONObject.optString("returnUrl");
                                a.this.g = jSONObject.optString("merchantVpa");
                                a.this.i = jSONObject.optString("referenceId");
                                if (!(TextUtils.isEmpty(a.this.h) || TextUtils.isEmpty(a.this.f) || TextUtils.isEmpty(a.this.g))) {
                                    if (!TextUtils.isEmpty(a.this.i)) {
                                        return Boolean.valueOf(true);
                                    }
                                }
                                return Boolean.valueOf(false);
                            }
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                return Boolean.valueOf(false);
            }

            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                if (!(a.this.d == null || !a.this.d.isShowing() || ((Activity) a.this.a.get()).isFinishing())) {
                    a.this.d.dismiss();
                }
                if (bool.booleanValue()) {
                    a.this.b();
                    return;
                }
                a.this.c.onCBErrorReceived(1002, "MERCHANT_INFO_NOT_PRESENT");
                ((Activity) a.this.a.get()).finish();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{""});
    }

    private void b() {
        this.k = this.j.a(this.b);
        Intent intent = new Intent();
        intent.setPackage(this.k.getPackageName());
        intent.setData(Uri.parse(this.j.a(this.g, this.h, this.j.a(this.b, "amount"), this.j.a(this.b, "transactionId"), this.i)));
        ((Activity) this.a.get()).startActivityForResult(intent, 101);
        c();
    }

    private void c() {
        if (this.l != null) {
            new CBUtil().cancelTimer(this.l);
        }
        this.l = new Timer();
        this.l.schedule(new TimerTask() {
            public void run() {
                if (a.this.a.get() != null && !((Activity) a.this.a.get()).isFinishing()) {
                    ((Activity) a.this.a.get()).runOnUiThread(new Runnable() {
                        public void run() {
                            a.this.a("failure", "timeout");
                        }
                    });
                }
            }
        }, this.m);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(final String str, final String str2) {
        new AsyncTask<String, Void, String>() {
            /* Access modifiers changed, original: protected */
            public void onPreExecute() {
                super.onPreExecute();
                if (a.this.a.get() != null && !((Activity) a.this.a.get()).isFinishing()) {
                    a.this.d = new com.payu.custombrowser.widgets.a((Context) a.this.a.get());
                    a.this.d.show();
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            /* renamed from: a */
            public String doInBackground(String... strArr) {
                try {
                    CBUtil cBUtil = new CBUtil();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("txnStatus=");
                    stringBuilder.append(str);
                    stringBuilder.append("&");
                    stringBuilder.append("failureReason");
                    stringBuilder.append("=");
                    stringBuilder.append(str2);
                    HttpsURLConnection httpsConn = cBUtil.getHttpsConn(a.this.f, stringBuilder.toString());
                    if (httpsConn != null && httpsConn.getResponseCode() == 200) {
                        try {
                            StringBuffer stringBufferFromInputStream = CBUtil.getStringBufferFromInputStream(httpsConn.getInputStream());
                            if (stringBufferFromInputStream != null) {
                                return CBUtil.getBase64DecodedString(stringBufferFromInputStream.toString());
                            }
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                return null;
            }

            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                if (!(a.this.d == null || !a.this.d.isShowing() || ((Activity) a.this.a.get()).isFinishing())) {
                    a.this.d.dismiss();
                }
                if (TextUtils.isEmpty(str)) {
                    a.this.c.onPaymentFailure(null, null);
                } else if (str.equalsIgnoreCase("failure") || str.equalsIgnoreCase("cancel")) {
                    a.this.c.onPaymentFailure(str, null);
                } else {
                    a.this.c.onPaymentSuccess(str, null);
                }
                if (a.this.l != null) {
                    new CBUtil().cancelTimer(a.this.l);
                }
                ((Activity) a.this.a.get()).finish();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{""});
    }
}

package com.payu.custombrowser;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.ReviewOrderData;
import com.payu.custombrowser.d.d;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.g;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.c;
import com.payu.magicretry.MagicRetryFragment;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public class a extends Fragment implements CBConstant {
    public static final boolean DEBUG = false;
    public static ArrayAdapter drawerAdapter;
    BroadcastReceiver A = null;
    String B;
    boolean C;
    String D;
    String E;
    String F;
    Boolean G;
    int H;
    Bundle I;
    boolean J;
    FrameLayout K;
    View L;
    View M;
    CBUtil N;
    View O;
    View P;
    com.payu.custombrowser.a.b Q;
    CountDownTimer R;
    boolean S;
    boolean T;
    Set<String> U;
    Set<String> V;
    Executor W;
    RelativeLayout X;
    TextView Y;
    TextView Z;
    private boolean a;
    c aa;
    protected boolean autoApprove;
    protected boolean autoSelectOtp;
    private boolean b;
    protected String backupOfOTP;
    protected boolean backwardJourneyStarted = false;
    private int c = 0;
    protected boolean catchAllJSEnabled = false;
    protected CustomBrowserConfig customBrowserConfig;
    final String e = CBConstant.PRODUCTION_URL;
    Activity f;
    protected boolean firstTouch = false;
    protected boolean forwardJourneyForChromeLoaderIsComplete = false;
    BroadcastReceiver g;
    JSONObject h;
    protected String hostName;
    JSONObject i;
    protected boolean isOTPFilled = false;
    protected boolean isSurePayValueLoaded = false;
    protected boolean isTxnNBType;
    protected boolean isWebviewReloading;
    int j;
    AlertDialog k;
    com.payu.custombrowser.a.a l;
    protected String listOfTxtFld;
    String m;
    protected Handler mHandler = new Handler();
    protected Runnable mResetCounter = new Runnable() {
        public void run() {
            a.this.c = 0;
        }
    };
    protected String merchantKey;
    boolean n = false;
    ArrayList<String> o = new ArrayList();
    protected String otp;
    protected boolean otpTriggered = false;
    MagicRetryFragment p;
    protected String pageType = "";
    protected boolean payuChromeLoaderDisabled = false;
    protected String phpSessionId;
    boolean q;
    Drawable r;
    protected ArrayList<ReviewOrderData> reviewOrderDetailList;
    WebView s;
    public int snoozeMode = 1;
    protected String surePayS2SPayUId;
    protected String surePayS2Surl;
    int t;
    protected String timeOfArrival;
    protected String timeOfDeparture;
    protected Timer timerProgress;
    protected String txnId;
    protected String txnType;
    int u;
    int v;
    protected b viewOnClickListener;
    com.payu.custombrowser.widgets.a w;
    int x;
    ProgressBar y;
    int z;

    public class a implements OnTouchListener {
        float a;
        boolean b = true;
        int c = 0;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (a.this.q) {
                return false;
            }
            a.this.f();
            if (!this.b) {
                return false;
            }
            int actionMasked = motionEvent.getActionMasked();
            TranslateAnimation translateAnimation;
            if (a.this.L.getVisibility() != 0) {
                switch (actionMasked) {
                    case 0:
                        this.a = motionEvent.getY();
                        break;
                    case 1:
                        float y = motionEvent.getY();
                        if (this.a < y && a.this.K.getVisibility() == 0 && y - this.a > 0.0f) {
                            this.c = view.getHeight();
                            translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (view.getHeight() - 30));
                            translateAnimation.setDuration(500);
                            translateAnimation.setFillBefore(false);
                            translateAnimation.setFillEnabled(true);
                            translateAnimation.setZAdjustment(1);
                            view.startAnimation(translateAnimation);
                            if (a.this.M != null) {
                                a.this.M.setVisibility(8);
                            }
                            this.b = false;
                            this.b = true;
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    if (a.this.f != null && !a.this.f.isFinishing()) {
                                        a.this.z = 1;
                                        a.this.K.setVisibility(8);
                                        a.this.L.setVisibility(0);
                                    }
                                }
                            }, 400);
                            break;
                        }
                }
            }
            a.this.L.setClickable(false);
            a.this.L.setOnTouchListener(null);
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.c, 0.0f);
            translateAnimation.setDuration(500);
            translateAnimation.setFillBefore(true);
            view.startAnimation(translateAnimation);
            a.this.K.setVisibility(0);
            this.b = false;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (a.this.f != null && !a.this.f.isFinishing()) {
                        a.this.L.setVisibility(8);
                    }
                }
            }, 20);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    a.this.b = true;
                    a.this.z = 2;
                    if (a.this.M != null && a.this.f != null && !a.this.f.isFinishing()) {
                        a.this.a(a.this.M, a.this.f);
                    }
                }
            }, 500);
            return true;
        }
    }

    public class b implements OnClickListener {
        public void onClick(View view) {
            if (view.getId() == e.bank_logo) {
                if (a.this.c == 0) {
                    a.this.mHandler.postDelayed(a.this.mResetCounter, 3000);
                }
                a.this.c = a.this.c + 1;
                if (a.this.c == 5) {
                    a.this.mHandler.removeCallbacks(a.this.mResetCounter);
                    a.this.c = 0;
                    Toast.makeText(a.this.f, "Version Name: 7.2.2", 0).show();
                }
            }
        }
    }

    public void loadUrlWebView(JSONObject jSONObject, String str) {
    }

    public void onBackApproved() {
    }

    public void onBackCancelled() {
    }

    public void onBackPressed(Builder builder) {
    }

    /* Access modifiers changed, original: protected */
    public boolean checkIfTransactionNBType(String str) {
        try {
            if (this.N.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), "pg").equalsIgnoreCase(CBConstant.NB)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void resetAutoSelectOTP() {
        boolean z = true;
        if (this.customBrowserConfig == null || this.customBrowserConfig.getAutoSelectOTP() != 1) {
            z = false;
        }
        this.autoSelectOtp = z;
    }

    public Drawable getCbDrawable(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
        return context.getResources().getDrawable(i);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(final View view, Context context) {
        if (view != null) {
            view.startAnimation(AnimationUtils.loadAnimation(context, com.payu.custombrowser.d.a.cb_fade_in));
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (a.this.f != null && !a.this.f.isFinishing()) {
                        view.setVisibility(0);
                    }
                }
            }, 500);
        }
    }

    public void checkStatusFromJS(String str) {
        checkStatusFromJS(str, 0);
    }

    public void checkStatusFromJS(final String str, final int i) {
        try {
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(VERSION.RELEASE);
                            stringBuilder.append("");
                            jSONObject.put("androidosversion", stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(Build.MANUFACTURER);
                            stringBuilder.append("");
                            jSONObject.put("androidmanufacturer", stringBuilder.toString().toLowerCase());
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(Build.MODEL);
                            stringBuilder.append("");
                            jSONObject.put("model", stringBuilder.toString().toLowerCase());
                            jSONObject.put(CBConstant.MERCHANT_KEY, Bank.keyAnalytics);
                            jSONObject.put(CBConstant.SDK_DETAILS, Bank.c);
                            jSONObject.put("cbname", "7.2.2");
                            StringBuilder stringBuilder2;
                            String stringBuilder3;
                            WebView webView;
                            if (i == 1) {
                                if (a.this.h.has("set_dynamic_snooze")) {
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(a.this.h.getString("set_dynamic_snooze"));
                                    stringBuilder2.append("(");
                                    stringBuilder2.append(jSONObject);
                                    stringBuilder2.append(")");
                                    stringBuilder3 = stringBuilder2.toString();
                                } else {
                                    stringBuilder3 = "";
                                }
                                webView = a.this.s;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("javascript:");
                                stringBuilder.append(stringBuilder3);
                                webView.loadUrl(stringBuilder.toString());
                            } else if (i == 0) {
                                jSONObject.put("bankname", str.toLowerCase());
                                webView = a.this.s;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("javascript:");
                                stringBuilder.append(a.this.h.getString("checkVisibilityCBCall"));
                                stringBuilder.append("(");
                                stringBuilder.append(jSONObject);
                                stringBuilder.append(")");
                                webView.loadUrl(stringBuilder.toString());
                            } else if (i == 2) {
                                if (a.this.h.has("checkVisibilityReviewOrderCall")) {
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(a.this.h.getString("checkVisibilityReviewOrderCall"));
                                    stringBuilder2.append("(");
                                    stringBuilder2.append(jSONObject);
                                    stringBuilder2.append(")");
                                    stringBuilder3 = stringBuilder2.toString();
                                } else {
                                    stringBuilder3 = null;
                                }
                                if (stringBuilder3 != null) {
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            if (a.this.f != null && !a.this.f.isFinishing()) {
                                                WebView webView = a.this.s;
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append("javascript:");
                                                stringBuilder.append(stringBuilder3);
                                                webView.loadUrl(stringBuilder.toString());
                                            }
                                        }
                                    }, 1000);
                                }
                            } else if (i == 3) {
                                webView = a.this.s;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("javascript:");
                                stringBuilder.append(a.this.h.getString(a.this.getString(g.cb_check_visibility_cajs)));
                                stringBuilder.append("(");
                                stringBuilder.append(jSONObject);
                                stringBuilder.append(")");
                                webView.loadUrl(stringBuilder.toString());
                            }
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                });
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) this.f.getSystemService("input_method")).showSoftInput(view, 2);
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        this.f.getWindow().setSoftInputMode(3);
    }

    /* Access modifiers changed, original: protected */
    public void initAnalytics(String str) {
        this.l = com.payu.custombrowser.a.a.a(this.f.getApplicationContext(), "local_cache_analytics");
        a(str, this.f.getApplicationContext());
    }

    private void a(String str, Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            CBUtil cBUtil = this.N;
            jSONObject.put("payu_id", CBUtil.getCookie(CBConstant.PAYUID, context));
            jSONObject.put(CBConstant.TXN_ID, Bank.a);
            jSONObject.put("merchant_key", str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append("");
            jSONObject.put("device_os_version", stringBuilder.toString());
            jSONObject.put("device_resolution", this.N.getDeviceDensity(this.f));
            jSONObject.put("device_manufacturer", Build.MANUFACTURER);
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put("network_info", this.N.getNetworkStatus(this.f.getApplicationContext()));
            jSONObject.put("sdk_version_name", Bank.c);
            jSONObject.put("cb_version_name", "7.2.2");
            jSONObject.put(InMobiNetworkValues.PACKAGE_NAME, context.getPackageName());
            jSONObject.put("network_strength", this.N.getNetworkStrength(this.f.getApplicationContext()));
            CBUtil.setVariableReflection(CBConstant.MAGIC_RETRY_PAKAGE, str, CBConstant.ANALYTICS_KEY);
            this.Q = new com.payu.custombrowser.a.b(this.f.getApplicationContext(), "cb_local_cache_device");
            this.Q.a(jSONObject.toString());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.trim().equalsIgnoreCase("")) {
                    com.payu.custombrowser.a.a aVar = this.l;
                    CBUtil cBUtil = this.N;
                    aVar.a(CBUtil.getLogMessage(this.f.getApplicationContext(), str, str2.toLowerCase(), this.D, Bank.keyAnalytics, Bank.a, this.pageType));
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c(String str) {
        if (this.r == null && str != null) {
            try {
                if (!(str.equalsIgnoreCase("sbinet") || str.equalsIgnoreCase("sbi"))) {
                    if (!str.startsWith("sbi_")) {
                        if (!(str.equalsIgnoreCase("icici") || str.equalsIgnoreCase("icicinet") || str.equalsIgnoreCase("icicicc"))) {
                            if (!str.startsWith("icici_")) {
                                if (!(str.equalsIgnoreCase("kotaknet") || str.equalsIgnoreCase("kotak"))) {
                                    if (!str.startsWith("kotak_")) {
                                        if (!str.equalsIgnoreCase("indus")) {
                                            if (!str.startsWith("indus_")) {
                                                if (!(str.equalsIgnoreCase("hdfc") || str.equalsIgnoreCase("hdfcnet"))) {
                                                    if (!str.startsWith("hdfc_")) {
                                                        if (!str.equalsIgnoreCase("yesnet")) {
                                                            if (!str.startsWith("yes_")) {
                                                                if (!str.equalsIgnoreCase("sc")) {
                                                                    if (!str.startsWith("sc_")) {
                                                                        if (!(str.equalsIgnoreCase("axisnet") || str.equalsIgnoreCase("axis"))) {
                                                                            if (!str.startsWith("axis_")) {
                                                                                if (!str.equalsIgnoreCase("amex")) {
                                                                                    if (!str.startsWith("amex_")) {
                                                                                        if (!(str.equalsIgnoreCase("hdfcnet") || str.equalsIgnoreCase("hdfc"))) {
                                                                                            if (!str.startsWith("hdfc_")) {
                                                                                                if (!str.equalsIgnoreCase("ing")) {
                                                                                                    if (!str.startsWith("ing_")) {
                                                                                                        if (!str.equalsIgnoreCase("idbi")) {
                                                                                                            if (!str.startsWith("idbi_")) {
                                                                                                                if (!str.equalsIgnoreCase("citi")) {
                                                                                                                    if (!str.startsWith("citi_")) {
                                                                                                                        if (!str.equalsIgnoreCase("unionnet")) {
                                                                                                                            if (!str.startsWith("unionnet_")) {
                                                                                                                                this.r = null;
                                                                                                                                return;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.union_bank_logo);
                                                                                                                        return;
                                                                                                                    }
                                                                                                                }
                                                                                                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.citi);
                                                                                                                return;
                                                                                                            }
                                                                                                        }
                                                                                                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.idbi);
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.ing_logo);
                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                        this.r = this.N.getDrawableCB(this.f, d.hdfc_bank);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.cb_amex_logo);
                                                                                return;
                                                                            }
                                                                        }
                                                                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.axis_logo);
                                                                        return;
                                                                    }
                                                                }
                                                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.scblogo);
                                                                return;
                                                            }
                                                        }
                                                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.yesbank_logo);
                                                        return;
                                                    }
                                                }
                                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.hdfc_bank);
                                                return;
                                            }
                                        }
                                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.induslogo);
                                        return;
                                    }
                                }
                                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.kotak);
                                return;
                            }
                        }
                        this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.icici);
                        return;
                    }
                }
                this.r = this.N.getDrawableCB(this.f.getApplicationContext(), d.sbi);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        View currentFocus = this.f.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) this.f.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(View view) {
        view.measure(-2, -2);
        this.t = view.getMeasuredHeight();
        if (this.v != 0) {
            this.u = this.v - this.t;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        try {
            if (this.v == 0 && this.D != null) {
                this.s.measure(-1, -1);
                this.s.requestLayout();
                this.v = this.s.getMeasuredHeight();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        if (this.v == 0) {
            e();
        }
        if (this.v != 0) {
            this.s.getLayoutParams().height = this.v;
            this.s.requestLayout();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        if (this.v != 0) {
            this.s.getLayoutParams().height = this.u;
            this.s.requestLayout();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, String str) {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            if (i == 8 || i == 4) {
                if (this.w != null) {
                    this.w.dismiss();
                    this.w = null;
                    showReviewOrderHorizontalBar();
                }
            } else if (i == 0 && !this.payuChromeLoaderDisabled && !this.n) {
                if (this.w == null) {
                    this.w = new com.payu.custombrowser.widgets.a(this.f);
                }
                if (this.isWebviewReloading) {
                    this.w.a(this.f.getString(g.cb_resuming_transaction));
                    this.isWebviewReloading = false;
                } else {
                    this.w.a(this.f.getString(g.cb_please_wait));
                }
                this.w.show();
                if (!this.J) {
                    hideReviewOrderHorizontalBar();
                    hideReviewOrderDetails();
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        if (this.f != null && !this.f.isFinishing() && isAdded() && !isRemoving()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    a.this.a(8, "");
                    if (a.this.y != null) {
                        a.this.y.setVisibility(8);
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i) {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            if (this.x > i) {
                this.y.setProgress(i);
            }
            if (VERSION.SDK_INT >= 11) {
                ObjectAnimator ofInt = ObjectAnimator.ofInt(this.y, NotificationCompat.CATEGORY_PROGRESS, new int[]{i});
                ofInt.setDuration(50);
                ofInt.setInterpolator(new AccelerateInterpolator());
                ofInt.start();
            } else {
                if (i <= 10) {
                    i = 10;
                }
                this.y.setProgress(i);
            }
            this.x = i;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void i() {
        f();
        this.z = 1;
        onHelpUnavailable();
    }

    public void registerBroadcast(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.f != null && !this.f.isFinishing()) {
            this.A = broadcastReceiver;
            this.f.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
        if (this.A != null) {
            this.f.unregisterReceiver(broadcastReceiver);
            this.A = null;
        }
    }

    public void onHelpUnavailable() {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.findViewById(e.parent).setVisibility(8);
        }
    }

    public void onBankError() {
        this.f.findViewById(e.parent).setVisibility(8);
    }

    public void onHelpAvailable() {
        this.a = true;
        this.f.findViewById(e.parent).setVisibility(0);
    }

    public boolean wasCBVisibleOnce() {
        return this.a;
    }

    public boolean isRetryURL(String str) {
        if (this.V.size() == 0) {
            return str.contains(CBConstant.PAYMENT_OPTION_URL_PROD);
        }
        for (String contains : this.V) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void j() {
        if (this.h != null) {
            try {
                StringTokenizer stringTokenizer;
                if (this.h.has("postPaymentPgUrlList")) {
                    stringTokenizer = new StringTokenizer(this.h.getString("postPaymentPgUrlList").replace(" ", ""), CBConstant.CB_DELIMITER);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.U.add(stringTokenizer.nextToken());
                    }
                }
                if (this.h.has("retryList")) {
                    stringTokenizer = new StringTokenizer(this.h.getString("retryUrlList").replace(" ", ""), CBConstant.CB_DELIMITER);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.V.add(stringTokenizer.nextToken());
                    }
                }
            } catch (Exception e) {
                h();
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void k() {
        this.R = new CountDownTimer(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (a.this.f != null && !a.this.f.isFinishing() && a.this.isAdded() && !a.this.isRemoving()) {
                    a.this.f.runOnUiThread(new Runnable() {
                        public void run() {
                            if (a.this.f != null && !a.this.f.isFinishing() && a.this.isAdded()) {
                                a.this.l();
                            }
                        }
                    });
                }
            }
        }.start();
    }

    /* Access modifiers changed, original: 0000 */
    public void l() {
        if (this.f != null && !this.f.isFinishing() && isAdded() && !isRemoving()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    if (a.this.f != null && !a.this.f.isFinishing() && a.this.isAdded()) {
                        if (a.this.J) {
                            Intent intent = new Intent();
                            intent.putExtra(a.this.getString(g.cb_result), a.this.F);
                            intent.putExtra(a.this.getString(g.cb_payu_response), a.this.E);
                            if (a.this.G.booleanValue()) {
                                if (a.this.H == 1) {
                                    new f().execute(new String[]{a.this.E});
                                }
                                a.this.f.setResult(-1, intent);
                            } else {
                                a.this.f.setResult(0, intent);
                            }
                        } else if (a.this.G.booleanValue()) {
                            if (a.this.customBrowserConfig.getStoreOneClickHash() == 1) {
                                new f().execute(new String[]{a.this.E});
                            }
                            if (com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                                com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback().onPaymentSuccess(a.this.E, a.this.F);
                            } else {
                                c.a("PayuError", "No PayUCustomBrowserCallback found, please assign a callback: com.payu.custombrowser.PayUCustomBrowserCallback.setPayuCustomBrowserCallback(PayUCustomBrowserCallback payuCustomBrowserCallback)");
                            }
                        } else if (com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                            com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback().onPaymentFailure(a.this.E, a.this.F);
                        } else {
                            c.a("PayuError", "No PayUCustomBrowserCallback found, please assign a callback: com.payu.custombrowser.PayUCustomBrowserCallback.setPayuCustomBrowserCallback(PayUCustomBrowserCallback payuCustomBrowserCallback)");
                        }
                        a.this.f.finish();
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: protected */
    public void cancelTransactionNotification() {
        NotificationManager notificationManager = (NotificationManager) this.f.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        notificationManager.cancel(CBConstant.TRANSACTION_STATUS_NOTIFICATION_ID);
        notificationManager.cancel(CBConstant.SNOOZE_NOTIFICATION_ID);
    }

    /* Access modifiers changed, original: protected */
    public void setTransactionStatusReceived(boolean z) {
        this.b = z;
    }

    /* Access modifiers changed, original: protected */
    public boolean getTransactionStatusReceived() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void postToPaytxn() {
        if (this.T) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        if (a.this.N.getHttpsConn("https://secure.payu.in/paytxn", null, -1, a.this.N.getCookieList(a.this.getActivity().getApplicationContext(), "https://secure.payu.in")).getResponseCode() != 200) {
                            Log.d("PayU", "BackButtonClick - UnSuccessful post to Paytxn");
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            });
            thread.setPriority(10);
            thread.start();
        }
    }

    public void showReviewOrderHorizontalBar() {
        if ((this.w == null || !this.w.isShowing()) && !this.J && this.customBrowserConfig.getEnableReviewOrder() == 0 && !this.n) {
            if (!this.o.contains("review_order_custom_browser")) {
                this.o.add("review_order_custom_browser");
            }
            this.X.setVisibility(0);
            this.X.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    a.this.a("user_input", "review_order_btn_click");
                    a.this.d();
                    a.this.showReviewOrderDetails();
                }
            });
            setReviewOrderButtonProperty(this.Y);
        }
    }

    public void setReviewOrderButtonProperty(TextView textView) {
        if (this.J) {
            textView.setVisibility(8);
        } else if (this.customBrowserConfig.getEnableReviewOrder() == 0) {
            if (this.customBrowserConfig.getReviewOrderButtonText() != null) {
                textView.setText(this.customBrowserConfig.getReviewOrderButtonText());
            }
            if (this.customBrowserConfig.getReviewOrderButtonTextColor() != -1) {
                textView.setTextColor(this.f.getResources().getColor(this.customBrowserConfig.getReviewOrderButtonTextColor()));
            }
            textView.setVisibility(0);
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    a.this.a("user_input", "review_order_btn_click");
                    a.this.d();
                    a.this.showReviewOrderDetails();
                }
            });
        } else {
            textView.setVisibility(8);
        }
    }

    public void hideReviewOrderHorizontalBar() {
        if (!this.J) {
            this.X.setVisibility(8);
        }
    }

    public void showReviewOrderDetails() {
        if ((this.aa == null || !this.aa.isAdded()) && getActivity() != null) {
            this.aa = c.a(this.reviewOrderDetailList, this.customBrowserConfig.getReviewOrderCustomView());
            FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(com.payu.custombrowser.d.a.slide_up_in, com.payu.custombrowser.d.a.slide_up_out);
            beginTransaction.add(e.payu_review_order, this.aa);
            beginTransaction.commit();
        }
    }

    public void hideReviewOrderDetails() {
        if (getActivity() != null && this.aa != null) {
            FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            beginTransaction.remove(this.aa);
            beginTransaction.setCustomAnimations(com.payu.custombrowser.d.a.slide_up_out, com.payu.custombrowser.d.a.slide_up_in);
            beginTransaction.commitAllowingStateLoss();
        }
    }
}

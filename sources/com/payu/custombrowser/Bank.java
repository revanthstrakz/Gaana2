package com.payu.custombrowser;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.f;
import com.payu.custombrowser.d.g;
import com.payu.custombrowser.d.h;
import com.payu.custombrowser.services.SnoozeService;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.c;
import com.payu.custombrowser.util.d;
import com.payu.custombrowser.widgets.SnoozeLoaderView;
import com.payu.magicretry.MagicRetryFragment;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class Bank extends b {
    public static String Version = "7.2.2";
    static String a;
    private static List<String> aw = new ArrayList();
    static String b;
    static String c;
    public static String keyAnalytics;
    private SnoozeLoaderView aA;
    private View aB;
    private boolean aC;
    private boolean aD;
    private a aE;
    private boolean aF;
    private boolean aG;
    private boolean aH;
    private boolean aI;
    private CountDownTimer aJ;
    private CountDownTimer aK;
    private boolean aL;
    private boolean aM;
    private boolean aN;
    private boolean aO;
    private boolean aP;
    private boolean aQ;
    private AlertDialog aR;
    private boolean aS;
    private boolean aT;
    private boolean aU;
    private String aV;
    private CountDownTimer ax;
    private boolean ay;
    private boolean az;
    Runnable d;
    public long snoozeClickedTime;

    public class a implements OnClickListener {
        private View b;

        public void a(View view) {
            this.b = view;
        }

        public void onClick(View view) {
            String str = "";
            boolean z = view instanceof Button;
            if (z) {
                str = ((Button) view).getText().toString();
            } else if (view instanceof TextView) {
                str = ((TextView) view).getText().toString();
            }
            WebView webView;
            StringBuilder stringBuilder;
            switch (Bank.this.b(str.toLowerCase())) {
                case 1:
                case 3:
                    Bank.this.ae = true;
                    Bank.this.aj = Boolean.valueOf(true);
                    Bank.this.f();
                    Bank.this.z = 1;
                    Bank.this.onHelpUnavailable();
                    if (Bank.this.L != null) {
                        Bank.this.L.setVisibility(8);
                    }
                    if (Bank.this.M != null) {
                        Bank.this.M.setVisibility(8);
                    }
                    try {
                        webView = Bank.this.s;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("javascript:");
                        stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_pin)));
                        webView.loadUrl(stringBuilder.toString());
                        Bank.this.m = "password_click";
                        Bank.this.a("user_input", Bank.this.m);
                        return;
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                        return;
                    }
                case 2:
                    try {
                        Bank.this.m = "regenerate_click";
                        Bank.this.a("user_input", Bank.this.m);
                        Bank.this.ah = null;
                        webView = Bank.this.s;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("javascript:");
                        stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_regen_otp)));
                        webView.loadUrl(stringBuilder.toString());
                        Bank.this.m();
                        return;
                    } catch (JSONException e2) {
                        ThrowableExtension.printStackTrace(e2);
                        return;
                    }
                case 4:
                    view = Bank.this.f.getLayoutInflater().inflate(f.wait_for_otp, null);
                    if (z) {
                        Bank.this.m = "enter_manually_click";
                    } else {
                        Bank.this.m = "enter_manually_ontimer_click";
                    }
                    Bank.this.a("user_input", Bank.this.m);
                    if (Bank.this.af == 0) {
                        view.measure(-2, -2);
                        Bank.this.af = view.getMeasuredHeight();
                    }
                    Bank.this.K.removeAllViews();
                    Bank.this.K.addView(view);
                    if (Bank.this.K.isShown()) {
                        Bank.this.z = 2;
                    } else {
                        Bank.this.f();
                    }
                    ImageView imageView = (ImageView) view.findViewById(e.bank_logo);
                    imageView.setOnClickListener(Bank.this.viewOnClickListener);
                    if (Bank.this.r != null) {
                        imageView.setImageDrawable(Bank.this.r);
                    }
                    view.findViewById(e.waiting).setVisibility(8);
                    final Button button = (Button) view.findViewById(e.approve);
                    button.setClickable(false);
                    EditText editText = (EditText) view.findViewById(e.otp_sms);
                    if (Bank.this.aS) {
                        editText.setInputType(2);
                    } else {
                        editText.setInputType(1);
                    }
                    Bank.this.a((View) editText);
                    CBUtil.setAlpha(0.3f, button);
                    button.setVisibility(0);
                    editText.setVisibility(0);
                    view.findViewById(e.regenerate_layout).setVisibility(8);
                    view.findViewById(e.progress).setVisibility(4);
                    Bank.this.a((View) editText);
                    editText.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            if (((EditText) view.findViewById(e.otp_sms)).getText().toString().length() > 5) {
                                Bank.this.aE.a(view);
                                button.setOnClickListener(Bank.this.aE);
                                button.setClickable(true);
                                CBUtil.setAlpha(1.0f, button);
                                return;
                            }
                            button.setClickable(false);
                            CBUtil.setAlpha(0.3f, button);
                            button.setOnClickListener(null);
                        }

                        public void afterTextChanged(Editable editable) {
                            Bank.this.fillOTPFromCBScreen(editable.toString());
                        }
                    });
                    Bank.this.updateHeight(view);
                    Bank.this.addReviewOrder(view);
                    return;
                case 5:
                    try {
                        Bank.this.d();
                        Bank.this.ah = null;
                        Bank.this.ak = false;
                        Bank.this.aj = Boolean.valueOf(true);
                        Bank.this.onHelpUnavailable();
                        Bank.this.f();
                        Bank.this.z = 1;
                        Bank.this.m();
                        if (((EditText) this.b.findViewById(e.otp_sms)).getText().toString().length() > 5) {
                            Bank.this.m = "approved_otp";
                            Bank.this.a("user_input", Bank.this.m);
                            Bank.this.a("Approve_btn_clicked_time", "-1");
                            webView = Bank.this.s;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:");
                            stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_process_otp)));
                            stringBuilder.append("(\"");
                            stringBuilder.append(((TextView) this.b.findViewById(e.otp_sms)).getText().toString());
                            stringBuilder.append("\")");
                            webView.loadUrl(stringBuilder.toString());
                            ((EditText) this.b.findViewById(e.otp_sms)).setText("");
                            return;
                        }
                        return;
                    } catch (JSONException e22) {
                        ThrowableExtension.printStackTrace(e22);
                        return;
                    }
                case 6:
                case 7:
                    Bank.this.aq = true;
                    Bank.this.n();
                    Bank.this.m = "otp_click";
                    Bank.this.a("user_input", Bank.this.m);
                    if (VERSION.SDK_INT < 23) {
                        Bank.this.ah = null;
                        Bank.this.m();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public Bank() {
        this.ax = null;
        this.ay = false;
        this.az = false;
        this.aC = true;
        this.aD = false;
        this.aF = true;
        this.aH = false;
        this.aL = true;
        this.aN = false;
        this.aO = false;
        this.aP = false;
        this.aQ = false;
        this.aS = true;
        this.aT = false;
        this.aE = new a();
        this.aI = false;
        this.ar = new com.payu.custombrowser.custombar.a();
        this.U = new HashSet();
        this.N = new CBUtil();
        this.W = Executors.newCachedThreadPool();
        this.V = new HashSet();
    }

    public void onPause() {
        super.onPause();
        this.aU = true;
    }

    public void onStart() {
        super.onStart();
        this.aU = false;
        if (this.aV != null) {
            Toast.makeText(this.f, this.aV, 0).show();
            this.aV = null;
        }
    }

    public static boolean isUrlWhiteListed(String str) {
        if ((str.contains("https://secure.payu.in") || str.contains(CBConstant.PAYU_DOMAIN_TEST)) && str.contains(CBConstant.RESPONSE_BACKWARD)) {
            return true;
        }
        for (String str2 : aw) {
            if (str != null && str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public SnoozeLoaderView getSnoozeLoaderView() {
        return this.aA;
    }

    public void setSnoozeLoaderView(SnoozeLoaderView snoozeLoaderView) {
        this.aA = snoozeLoaderView;
    }

    public String getPageType() {
        return this.pageType;
    }

    public void reloadWebView(String str, String str2) {
        this.forwardJourneyForChromeLoaderIsComplete = false;
        this.backwardJourneyStarted = false;
        this.isWebviewReloading = true;
        registerSMSBroadcast();
        this.backwardJourneyStarted = false;
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        if (this.n) {
            dismissSnoozeWindow();
        }
        if (this.w != null) {
            this.w.dismiss();
        }
        this.w = null;
        if (VERSION.SDK_INT == 16 || VERSION.SDK_INT == 17 || VERSION.SDK_INT == 18) {
            this.s.loadUrl("about:blank");
        }
        a(true);
        resetAutoSelectOTP();
        this.N.resetPayuID();
        this.surePayS2SPayUId = null;
        if (str != null && str2 != null) {
            this.s.postUrl(str, str2.getBytes());
        } else if (str != null) {
            this.s.loadUrl(str);
        }
    }

    public void killSnoozeService() {
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
    }

    public void reloadWebView() {
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        if (this.n) {
            dismissSnoozeWindow();
        }
        registerSMSBroadcast();
        this.isWebviewReloading = true;
        if (this.isSnoozeEnabled) {
            q();
        }
        if (this.s.getUrl() != null) {
            a(true);
            if (19 == VERSION.SDK_INT) {
                this.s.reload();
            } else {
                reloadWVNative();
            }
        }
    }

    public void reloadWebView(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        if (this.n) {
            dismissSnoozeWindow();
        }
        registerSMSBroadcast();
        this.isWebviewReloading = true;
        if (this.isSnoozeEnabled) {
            q();
        }
        if (this.s.getUrl() != null) {
            a(true);
            if (19 == VERSION.SDK_INT) {
                this.s.reload();
                return;
            } else {
                reloadWVUsingJS();
                return;
            }
        }
        reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
    }

    public String getBankName() {
        if (this.D == null) {
            return "";
        }
        return this.D;
    }

    private void n() {
        boolean z = false;
        WebView webView;
        StringBuilder stringBuilder;
        if (this.an || VERSION.SDK_INT < 23 || !this.C) {
            onHelpAvailable();
            if (ContextCompat.checkSelfPermission(this.f, "android.permission.RECEIVE_SMS") == 0) {
                z = true;
            }
            this.am = z;
            if (this.aq) {
                try {
                    webView = this.s;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("javascript:");
                    stringBuilder.append(this.i.getString(getString(g.cb_otp)));
                    webView.loadUrl(stringBuilder.toString());
                    return;
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                    return;
                }
            }
            return;
        }
        this.an = true;
        if (ContextCompat.checkSelfPermission(this.f, "android.permission.RECEIVE_SMS") != 0) {
            requestPermissions(new String[]{"android.permission.RECEIVE_SMS"}, 1);
            this.ao = true;
            return;
        }
        this.am = true;
        if (this.aq) {
            try {
                webView = this.s;
                stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(this.i.getString(getString(g.cb_otp)));
                webView.loadUrl(stringBuilder.toString());
            } catch (JSONException e3) {
                ThrowableExtension.printStackTrace(e3);
            } catch (Exception e22) {
                ThrowableExtension.printStackTrace(e22);
            }
        }
    }

    @JavascriptInterface
    public void showCustomBrowser(final boolean z) {
        this.ag = z;
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (!z) {
                        Bank.this.f();
                        Bank.this.z = 1;
                        try {
                            if (Bank.this.L != null) {
                                Bank.this.L.setVisibility(8);
                            }
                            Bank.this.onHelpUnavailable();
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setMRData(String str) {
        if (!this.aH) {
            MagicRetryFragment.b(str, getActivity().getApplicationContext());
            a(CBUtil.updateRetryData(str, getActivity().getApplicationContext()));
            this.aH = true;
        }
    }

    public void onOverrideURL(String str) {
        if (this.y != null) {
            this.y.setProgress(10);
        }
    }

    private void o() {
        setIsPageStoppedForcefully(true);
        if (this.at != null) {
            u();
            this.av = this.N.getSurePayDisableStatus(this.at, this.B);
            launchSnoozeWindow(2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Object sslError2;
        StringBuilder stringBuilder = new StringBuilder();
        if (sslError2 == null) {
            sslError2 = "";
        }
        stringBuilder.append(sslError2);
        stringBuilder.append("|");
        stringBuilder.append(webView.getUrl() == null ? "" : webView.getUrl());
        b("SSL_ERROR", stringBuilder.toString());
        h();
    }

    public void onReceivedErrorWebClient(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("|");
        stringBuilder.append(str == null ? "" : str);
        stringBuilder.append("|");
        stringBuilder.append(this.s.getUrl() == null ? "" : this.s.getUrl());
        b("ERROR_RECEIVED", stringBuilder.toString());
        h();
        if (this.y != null) {
            this.y.setVisibility(8);
        }
        this.aO = true;
        try {
            if (getActivity() != null && !getActivity().isFinishing() && b.SINGLETON != null && b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                if (!this.backwardJourneyStarted) {
                    o();
                } else if (this.backwardJourneyStarted && this.isTxnNBType && this.snoozeCountBackwardJourney < this.customBrowserConfig.getEnableSurePay()) {
                    dismissSnoozeWindow();
                    o();
                }
                onHelpUnavailable();
                this.K.removeAllViews();
                if (this.v != 0) {
                    f();
                    this.z = 1;
                }
                i();
                if (!this.J) {
                    b.SINGLETON.getPayuCustomBrowserCallback().onCBErrorReceived(i, str);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void p() {
        try {
            WebView webView = this.s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("javascript:");
            stringBuilder.append(this.h.getString("getMagicRetryUrls"));
            stringBuilder.append("('");
            stringBuilder.append(keyAnalytics);
            stringBuilder.append("')");
            webView.loadUrl(stringBuilder.toString());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void onProgressChanged(int i) {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded() && this.y != null) {
            this.y.setVisibility(0);
            if (i != 100) {
                a(i);
            } else if (this.y != null) {
                this.y.setProgress(100);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (Bank.this.f != null && !Bank.this.f.isFinishing() && !Bank.this.isRemoving() && Bank.this.isAdded()) {
                            Bank.this.y.setVisibility(8);
                            Bank.this.x = 0;
                        }
                    }
                }, 100);
            }
        }
    }

    @JavascriptInterface
    public void onMerchantHashReceived(final String str) {
        if (getActivity() != null && !getActivity().isFinishing() && !isRemoving() && isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    switch (Bank.this.H) {
                        case 2:
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                Bank.this.N.storeInSharedPreferences(Bank.this.getActivity().getApplicationContext(), jSONObject.getString("card_token"), jSONObject.getString("merchant_hash"));
                                return;
                            } catch (JSONException e) {
                                ThrowableExtension.printStackTrace(e);
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
        }
    }

    private void q() {
        if (b.SINGLETON != null && b.SINGLETON.getPayuCustomBrowserCallback() != null && this.customBrowserConfig != null && this.N.getBooleanSharedPreferenceDefaultTrue(CBConstant.SNOOZE_ENABLED, this.f.getApplicationContext()) && this.customBrowserConfig.getEnableSurePay() > this.snoozeCount) {
            if (this.ay) {
                u();
            }
            t();
        }
    }

    public void onPageStartedWebclient(String str) {
        this.aN = true;
        this.az = false;
        if ((VERSION.SDK_INT == 16 || VERSION.SDK_INT == 17 || VERSION.SDK_INT == 18) && this.aO) {
            r();
        }
        this.aO = false;
        dismissSlowUserWarning();
        if (!(this.T || str == null || (!str.equalsIgnoreCase("https://secure.payu.in/_payment") && !str.equalsIgnoreCase(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS)))) {
            this.T = true;
        }
        if (!this.aM) {
            if (this.customBrowserConfig != null && this.customBrowserConfig.getPayuPostData() == null && this.customBrowserConfig.getPostURL() == null && this.customBrowserConfig.getHtmlData() == null) {
                if (b.SINGLETON.getPayuCustomBrowserCallback().getPostData() == null || b.SINGLETON.getPayuCustomBrowserCallback().getPostURL() == null) {
                    throw new d("POST Data or POST URL Missing or wrong POST URL or HTML Data missing");
                }
                this.customBrowserConfig.setPayuPostData(b.SINGLETON.getPayuCustomBrowserCallback().getPostData());
                this.customBrowserConfig.setPostURL(b.SINGLETON.getPayuCustomBrowserCallback().getPostURL());
                b.SINGLETON.getPayuCustomBrowserCallback().setPostURL(null);
                b.SINGLETON.getPayuCustomBrowserCallback().setPostData(null);
            }
            if (!(this.customBrowserConfig == null || this.customBrowserConfig.getPayuPostData() == null || this.isS2SHtmlSupport)) {
                this.isTxnNBType = checkIfTransactionNBType(this.customBrowserConfig.getPayuPostData());
            }
            this.aM = true;
        }
        this.aL = true;
        if (!(this.pageType == null || this.pageType.equalsIgnoreCase(""))) {
            a("departure", "-1");
            this.pageType = "";
        }
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("s:");
            stringBuilder.append(str);
            this.N.setStringSharedPreferenceLastURL(this.f.getApplicationContext(), "last_url", stringBuilder.toString());
            if (this.y != null) {
                this.y.setVisibility(0);
            }
            if (this.aJ != null) {
                this.aJ.cancel();
            }
            if (this.y != null) {
                this.y.setVisibility(0);
                this.y.setProgress(10);
            }
            this.backwardJourneyStarted = isInBackWardJourney(str);
            if (!this.forwardJourneyForChromeLoaderIsComplete || this.backwardJourneyStarted) {
                a(0, str);
            }
            String url = (this.s.getUrl() == null || this.s.getUrl().equalsIgnoreCase("")) ? str : this.s.getUrl();
            this.B = url;
            if (b.SINGLETON != null && b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                if (this.backwardJourneyStarted) {
                    if (this.isTxnNBType) {
                        this.n = false;
                    } else {
                        dismissSnoozeWindow();
                    }
                }
                if (str.contains(CBConstant.PAYMENT_OPTION_URL_PROD)) {
                    this.i = null;
                    this.r = null;
                }
                try {
                    if (this.customBrowserConfig == null) {
                        return;
                    }
                    if ((this.customBrowserConfig.getPayuPostData() != null && ((!this.N.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), CBConstant.SURL).equals("") && str.contains(URLDecoder.decode(this.N.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), CBConstant.SURL), "UTF-8"))) || ((!this.N.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), CBConstant.FURL).equals("") && str.contains(URLDecoder.decode(this.N.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), CBConstant.FURL), "UTF-8"))) || isRetryURL(str)))) || (this.isS2SHtmlSupport && isRetryURL(str))) {
                        this.aL = false;
                        dismissSnoozeWindow();
                        i();
                        if (isRetryURL(str)) {
                            resetAutoSelectOTP();
                            this.backupOfOTP = null;
                            this.ah = null;
                            this.backwardJourneyStarted = false;
                        }
                        u();
                        if (this.snoozeService != null) {
                            this.snoozeService.a();
                        }
                    } else if (this.isSnoozeEnabled && this.customBrowserConfig.getSurePayMode() == 1 && !this.backwardJourneyStarted) {
                        this.as = this.at.getPercentageAndTimeout(str);
                        this.snoozeUrlLoadingPercentage = this.as[0];
                        this.snoozeUrlLoadingTimeout = this.as[1];
                        this.av = this.N.getSurePayDisableStatus(this.at, str);
                        q();
                    }
                } catch (UnsupportedEncodingException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    private void r() {
        setIsPageStoppedForcefully(true);
        if (this.at != null && !this.J) {
            u();
            this.av = this.N.getSurePayDisableStatus(this.at, this.B);
            launchSnoozeWindow(2);
        }
    }

    public boolean isInBackWardJourney(String str) {
        try {
            if (!this.backwardJourneyStarted) {
                if ((str.startsWith("https://secure.payu.in") || str.startsWith(CBConstant.PAYU_DOMAIN_TEST)) && str.contains(CBConstant.RESPONSE_BACKWARD)) {
                    return true;
                }
                if (this.U != null) {
                    for (String contains : this.U) {
                        if (str.contains(contains)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return this.backwardJourneyStarted;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return this.backwardJourneyStarted;
        }
    }

    public void onLoadResourse(WebView webView, String str) {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded() && !str.equalsIgnoreCase(CBConstant.rupeeURL) && !str.contains(CBConstant.rupeeURL1)) {
            str.contains(CBConstant.rupeeURL2);
        }
    }

    @JavascriptInterface
    public void showReviewOrder(boolean z) {
        if (!this.J) {
            int enableReviewOrder = this.customBrowserConfig.getEnableReviewOrder();
            CustomBrowserConfig customBrowserConfig = this.customBrowserConfig;
            if (enableReviewOrder == 0 && !z) {
                CustomBrowserConfig customBrowserConfig2 = this.customBrowserConfig;
                CustomBrowserConfig customBrowserConfig3 = this.customBrowserConfig;
                customBrowserConfig2.setEnableReviewOrder(-1);
                this.Z.setVisibility(8);
                dismissReviewOrder();
            }
        }
    }

    @JavascriptInterface
    public void surePayData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CBConstant.S2SPAYUID)) {
                this.surePayS2SPayUId = jSONObject.getString(CBConstant.S2SPAYUID);
            }
            if (jSONObject.has(CBConstant.S2SREPLAYURL) && jSONObject.has(CBConstant.SNOOZE_COUNT) && jSONObject.has(CBConstant.TXN_TYPE) && jSONObject.has(CBConstant.MERCHANTKEY) && jSONObject.has(CBConstant.TXNID)) {
                this.surePayS2Surl = jSONObject.getString(CBConstant.S2SREPLAYURL);
                this.merchantKey = jSONObject.getString(CBConstant.MERCHANTKEY);
                this.txnId = jSONObject.getString(CBConstant.TXNID);
                this.txnType = jSONObject.getString(CBConstant.TXN_TYPE);
                this.isTxnNBType = this.txnType.equalsIgnoreCase("NB");
                this.customBrowserConfig.setEnableSurePay(Integer.parseInt(jSONObject.getString(CBConstant.SNOOZE_COUNT)));
                this.isSurePayValueLoaded = true;
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @JavascriptInterface
    public void dismissReviewOrder() {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    if (!Bank.this.J) {
                        Bank.this.hideReviewOrderHorizontalBar();
                        Bank.this.hideReviewOrderDetails();
                    }
                }
            });
        }
    }

    public void onPageFinishWebclient(String str) {
        this.aN = false;
        if (!(this.f == null || this.f.isFinishing() || isRemoving() || !isAdded())) {
            if (this.aQ) {
                a("snooze_resume_url", str);
                a(false);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("f:");
            stringBuilder.append(str);
            this.N.setStringSharedPreferenceLastURL(this.f.getApplicationContext(), "last_url", stringBuilder.toString());
            s();
            if (!(!this.aC || getArguments() == null || getArguments().getInt(CBConstant.MAIN_LAYOUT, -1) == -1)) {
                try {
                    final View findViewById = this.f.findViewById(getArguments().getInt(CBConstant.MAIN_LAYOUT));
                    findViewById.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        private final int c = 100;
                        private final int d;
                        private final Rect e;

                        public void onGlobalLayout() {
                            if (Bank.this.f != null && !Bank.this.f.isFinishing() && !Bank.this.isRemoving() && Bank.this.isAdded()) {
                                int applyDimension = (int) TypedValue.applyDimension(1, (float) this.d, findViewById.getResources().getDisplayMetrics());
                                findViewById.getWindowVisibleDisplayFrame(this.e);
                                if ((findViewById.getRootView().getHeight() - (this.e.bottom - this.e.top) >= applyDimension ? 1 : 0) != 0 && Bank.this.j == 0) {
                                    ((InputMethodManager) Bank.this.f.getSystemService("input_method")).toggleSoftInput(3, 0);
                                    Bank.this.j = 1;
                                }
                            }
                        }
                    });
                    this.aC = false;
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
        if (!this.az) {
            u();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!Bank.this.az && !Bank.this.aN && Bank.this.n && !Bank.this.backwardJourneyStarted) {
                    if (Bank.this.n) {
                        Bank.this.a("snooze_window_automatically_disappear_time", "-1");
                    }
                    Bank.this.dismissSnoozeWindow();
                }
            }
        }, 1000);
    }

    @JavascriptInterface
    public void setSnoozeEnabled(boolean z) {
        if (!z) {
            this.customBrowserConfig.setEnableSurePay(0);
        }
        this.N.setBooleanSharedPreference(CBConstant.SNOOZE_ENABLED, z, this.f.getApplicationContext());
    }

    private void s() {
        if (this.aJ != null) {
            this.aJ.cancel();
        }
        if (this.aK != null) {
            this.aK.cancel();
        }
        this.aJ = new CountDownTimer(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                Bank.this.dismissPayULoader();
                Bank.this.showReviewOrderHorizontalBar();
            }
        }.start();
    }

    @JavascriptInterface
    public void getUserId() {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        if (Bank.this.N.getStringSharedPreference(Bank.this.f.getApplicationContext(), Bank.this.D) != null && !Bank.this.N.getStringSharedPreference(Bank.this.f.getApplicationContext(), Bank.this.D).equals("")) {
                            WebView webView = Bank.this.s;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:");
                            stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_populate_user_id)));
                            stringBuilder.append("(\"");
                            stringBuilder.append(Bank.this.N.getStringSharedPreference(Bank.this.f.getApplicationContext(), Bank.this.D));
                            stringBuilder.append("\")");
                            webView.loadUrl(stringBuilder.toString());
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setUserId(String str) {
        if (this.aF) {
            if (this.f != null && !this.f.isFinishing()) {
                this.N.storeInSharedPreferences(this.f.getApplicationContext(), this.D, str);
            }
        } else if (!this.N.getStringSharedPreference(this.f.getApplicationContext(), this.D).equals("")) {
            this.N.removeFromSharedPreferences(this.f.getApplicationContext(), this.D);
        }
    }

    @JavascriptInterface
    public void nativeHelperForNB(final String str, final String str2) {
        if (!(this.f == null || this.f.isFinishing() || isRemoving() || !isAdded())) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Bank.this.dismissReviewOrder();
                        if (Bank.this.n) {
                            Bank.this.dismissSnoozeWindow();
                            Bank.this.a("snooze_window_action", "snooze_window_dismissed_by_cb");
                            Bank.this.a("snooze_window_automatically_disappear_time", "-1");
                        }
                        Bank.this.pageType = "NBLogin Page";
                        Bank.this.a("arrival", "-1");
                        Bank.this.onHelpAvailable();
                        Bank.this.a("cb_status", com.payu.custombrowser.util.a.c);
                        if (str != null && Bank.this.f != null) {
                            Bank.this.dismissSnoozeWindow();
                            View inflate = Bank.this.f.getLayoutInflater().inflate(f.nb_layout, null);
                            final Button button = (Button) inflate.findViewById(e.b_continue);
                            final CheckBox checkBox = (CheckBox) inflate.findViewById(e.checkbox);
                            JSONObject jSONObject = new JSONObject(str2);
                            String string = Bank.this.getString(g.cb_btn_text);
                            if (!jSONObject.has(string) || jSONObject.getString(string) == null || jSONObject.getString(string).equalsIgnoreCase("")) {
                                Bank.this.onHelpUnavailable();
                                Bank.this.K.removeAllViews();
                            } else if (str.equals(Bank.this.getString(g.cb_button))) {
                                if (!jSONObject.has(Bank.this.getString(g.cb_checkbox))) {
                                    checkBox.setVisibility(8);
                                } else if (jSONObject.getBoolean(Bank.this.getString(g.cb_checkbox))) {
                                    if (Bank.this.aF) {
                                        Bank.this.a(com.payu.custombrowser.util.a.f, AvidJSONUtil.KEY_Y);
                                        checkBox.setChecked(true);
                                    } else {
                                        Bank.this.a(com.payu.custombrowser.util.a.f, "n");
                                        checkBox.setChecked(false);
                                    }
                                    checkBox.setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            Bank.this.aF = checkBox.isChecked();
                                            StringBuilder stringBuilder;
                                            if (Bank.this.aF) {
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append(com.payu.custombrowser.util.a.e);
                                                stringBuilder.append(AvidJSONUtil.KEY_Y);
                                                Bank.this.a("user_input", stringBuilder.toString());
                                                return;
                                            }
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append(com.payu.custombrowser.util.a.e);
                                            stringBuilder.append("n");
                                            Bank.this.a("user_input", stringBuilder.toString());
                                        }
                                    });
                                    checkBox.setVisibility(0);
                                } else {
                                    checkBox.setVisibility(8);
                                }
                                button.setText(jSONObject.getString(string));
                                button.setTransformationMethod(null);
                                button.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        try {
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append(com.payu.custombrowser.util.a.d);
                                            stringBuilder.append(button.getText());
                                            Bank.this.a("user_input", stringBuilder.toString());
                                            WebView webView = Bank.this.s;
                                            StringBuilder stringBuilder2 = new StringBuilder();
                                            stringBuilder2.append("javascript:");
                                            stringBuilder2.append(Bank.this.i.getString(Bank.this.getString(g.cb_btn_action)));
                                            webView.loadUrl(stringBuilder2.toString());
                                        } catch (Exception e) {
                                            ThrowableExtension.printStackTrace(e);
                                        }
                                    }
                                });
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                                Bank.this.q = true;
                            } else if (str.equals(Bank.this.getString(g.cb_pwd_btn))) {
                                button.setText(jSONObject.getString(string));
                                if (Bank.this.aI) {
                                    checkBox.setChecked(true);
                                } else {
                                    checkBox.setChecked(false);
                                }
                                if (checkBox.isChecked()) {
                                    try {
                                        WebView webView = Bank.this.s;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("javascript:");
                                        stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_toggle_field)));
                                        stringBuilder.append("(\"");
                                        stringBuilder.append(true);
                                        stringBuilder.append("\")");
                                        webView.loadUrl(stringBuilder.toString());
                                    } catch (Exception e) {
                                        ThrowableExtension.printStackTrace(e);
                                    }
                                }
                                checkBox.setText(Bank.this.getString(g.cb_show_password));
                                checkBox.setVisibility(0);
                                checkBox.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        Bank.this.aI = checkBox.isChecked();
                                        WebView webView;
                                        StringBuilder stringBuilder;
                                        if (checkBox.isChecked()) {
                                            try {
                                                webView = Bank.this.s;
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append("javascript:");
                                                stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_toggle_field)));
                                                stringBuilder.append("(\"");
                                                stringBuilder.append(true);
                                                stringBuilder.append("\")");
                                                webView.loadUrl(stringBuilder.toString());
                                                return;
                                            } catch (Exception e) {
                                                ThrowableExtension.printStackTrace(e);
                                                return;
                                            }
                                        }
                                        try {
                                            webView = Bank.this.s;
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append("javascript:");
                                            stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_toggle_field)));
                                            stringBuilder.append("(\"");
                                            stringBuilder.append(false);
                                            stringBuilder.append("\")");
                                            webView.loadUrl(stringBuilder.toString());
                                        } catch (Exception e2) {
                                            ThrowableExtension.printStackTrace(e2);
                                        }
                                    }
                                });
                                button.setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        try {
                                            WebView webView = Bank.this.s;
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("javascript:");
                                            stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_btn_action)));
                                            webView.loadUrl(stringBuilder.toString());
                                        } catch (Exception e) {
                                            ThrowableExtension.printStackTrace(e);
                                        }
                                    }
                                });
                                Bank.this.q = true;
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                            }
                        }
                    } catch (Exception e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                }
            });
        }
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.dismissPayULoader();
                }
            });
        }
    }

    @JavascriptInterface
    public void reInit() {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.onPageFinished();
                }
            });
        }
    }

    @JavascriptInterface
    public void bankFound(final String str) {
        if (!this.aG) {
            checkStatusFromJS(str);
            this.aG = true;
        }
        c(str);
        CBUtil.setVariableReflection(CBConstant.MAGIC_RETRY_PAKAGE, str, CBConstant.BANKNAME);
        if (!(this.f == null || this.f.isFinishing())) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.e();
                }
            });
        }
        this.D = str;
        if (!this.ap) {
            try {
                if (!(this.O == null || this.f == null || this.f.isFinishing())) {
                    this.f.runOnUiThread(new Runnable() {
                        public void run() {
                            Bank.this.ar.b(Bank.this.O.findViewById(e.progress));
                        }
                    });
                }
                if (!this.az) {
                    if (this.O == null) {
                        convertToNative(CBConstant.LOADING, "{}");
                    } else if (!(this.f == null || this.O == ((ViewGroup) this.f.findViewById(e.help_view)).getChildAt(0))) {
                        convertToNative(CBConstant.LOADING, "{}");
                    }
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (!this.aD && this.i == null) {
            this.W.execute(new Runnable() {
                /* JADX WARNING: Failed to extract finally block: empty outs */
                /* JADX WARNING: Failed to extract finally block: empty outs */
                public void run() {
                    /*
                    r6 = this;
                    r0 = com.payu.custombrowser.Bank.this;
                    r1 = 1;
                    r0.aD = r1;
                    r0 = 0;
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ Exception -> 0x00bf }
                    r1 = r1.f;	 Catch:{ Exception -> 0x00bf }
                    if (r1 == 0) goto L_0x0076;
                L_0x000d:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ Exception -> 0x00bf }
                    r1 = r1.h;	 Catch:{ Exception -> 0x00bf }
                    r2 = r3;	 Catch:{ Exception -> 0x00bf }
                    r1 = r1.getString(r2);	 Catch:{ Exception -> 0x00bf }
                    r2 = new java.io.File;	 Catch:{ Exception -> 0x00bf }
                    r3 = com.payu.custombrowser.Bank.this;	 Catch:{ Exception -> 0x00bf }
                    r3 = r3.f;	 Catch:{ Exception -> 0x00bf }
                    r3 = r3.getFilesDir();	 Catch:{ Exception -> 0x00bf }
                    r2.<init>(r3, r1);	 Catch:{ Exception -> 0x00bf }
                    r2 = r2.exists();	 Catch:{ Exception -> 0x00bf }
                    if (r2 != 0) goto L_0x0076;
                L_0x002a:
                    r2 = new java.net.URL;	 Catch:{ Exception -> 0x00bf }
                    r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00bf }
                    r3.<init>();	 Catch:{ Exception -> 0x00bf }
                    r4 = "https://cbjs.payu.in/js/sdk_js/v3/";
                    r3.append(r4);	 Catch:{ Exception -> 0x00bf }
                    r3.append(r1);	 Catch:{ Exception -> 0x00bf }
                    r4 = ".js";
                    r3.append(r4);	 Catch:{ Exception -> 0x00bf }
                    r3 = r3.toString();	 Catch:{ Exception -> 0x00bf }
                    r2.<init>(r3);	 Catch:{ Exception -> 0x00bf }
                    r2 = r2.openConnection();	 Catch:{ Exception -> 0x00bf }
                    r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ Exception -> 0x00bf }
                    r3 = "GET";
                    r2.setRequestMethod(r3);	 Catch:{ Exception -> 0x00bf }
                    r3 = new com.payu.custombrowser.util.h;	 Catch:{ Exception -> 0x00bf }
                    r3.<init>();	 Catch:{ Exception -> 0x00bf }
                    r2.setSSLSocketFactory(r3);	 Catch:{ Exception -> 0x00bf }
                    r3 = "Accept-Charset";
                    r4 = "UTF-8";
                    r2.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x00bf }
                    r3 = r2.getResponseCode();	 Catch:{ Exception -> 0x00bf }
                    r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                    if (r3 != r4) goto L_0x0076;
                L_0x0067:
                    r3 = com.payu.custombrowser.Bank.this;	 Catch:{ Exception -> 0x00bf }
                    r3 = r3.N;	 Catch:{ Exception -> 0x00bf }
                    r2 = r2.getInputStream();	 Catch:{ Exception -> 0x00bf }
                    r4 = com.payu.custombrowser.Bank.this;	 Catch:{ Exception -> 0x00bf }
                    r4 = r4.f;	 Catch:{ Exception -> 0x00bf }
                    r3.writeFileOutputStream(r2, r4, r1, r0);	 Catch:{ Exception -> 0x00bf }
                L_0x0076:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 == 0) goto L_0x0113;
                L_0x007c:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.h;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = r3;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.getString(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r3 = new org.json.JSONObject;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r4 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r4 = r4.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r4.openFileInput(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = com.payu.custombrowser.util.CBUtil.decodeContents(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r3.<init>(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2.i = r3;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 == 0) goto L_0x00b7;
                L_0x00a1:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.isFinishing();	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 != 0) goto L_0x00b7;
                L_0x00ab:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = new com.payu.custombrowser.Bank$34$1;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2.<init>();	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1.runOnUiThread(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                L_0x00b7:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1.aD = r0;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    goto L_0x0113;
                L_0x00bd:
                    r1 = move-exception;
                    goto L_0x0114;
                L_0x00bf:
                    r1 = move-exception;
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x00bd }
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 == 0) goto L_0x0113;
                L_0x00c9:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.h;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = r3;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.getString(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r3 = new org.json.JSONObject;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r4 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r4 = r4.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r4.openFileInput(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = com.payu.custombrowser.util.CBUtil.decodeContents(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r3.<init>(r1);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2.i = r3;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 == 0) goto L_0x0104;
                L_0x00ee:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.isFinishing();	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    if (r1 != 0) goto L_0x0104;
                L_0x00f8:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1 = r1.f;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2 = new com.payu.custombrowser.Bank$34$1;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r2.<init>();	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1.runOnUiThread(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                L_0x0104:
                    r1 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    r1.aD = r0;	 Catch:{ FileNotFoundException | JSONException -> 0x010f, FileNotFoundException | JSONException -> 0x010f, Exception -> 0x010a }
                    goto L_0x0113;
                L_0x010a:
                    r0 = move-exception;
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                    goto L_0x0113;
                L_0x010f:
                    r0 = move-exception;
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                L_0x0113:
                    return;
                L_0x0114:
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.f;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    if (r2 == 0) goto L_0x0164;
                L_0x011a:
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.h;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r3 = r3;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.getString(r3);	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r3 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r4 = new org.json.JSONObject;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r5 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r5 = r5.f;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r5.openFileInput(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = com.payu.custombrowser.util.CBUtil.decodeContents(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r4.<init>(r2);	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r3.i = r4;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.f;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    if (r2 == 0) goto L_0x0155;
                L_0x013f:
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.f;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.isFinishing();	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    if (r2 != 0) goto L_0x0155;
                L_0x0149:
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2 = r2.f;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r3 = new com.payu.custombrowser.Bank$34$1;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r3.<init>();	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2.runOnUiThread(r3);	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                L_0x0155:
                    r2 = com.payu.custombrowser.Bank.this;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    r2.aD = r0;	 Catch:{ FileNotFoundException | JSONException -> 0x0160, FileNotFoundException | JSONException -> 0x0160, Exception -> 0x015b }
                    goto L_0x0164;
                L_0x015b:
                    r0 = move-exception;
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                    goto L_0x0164;
                L_0x0160:
                    r0 = move-exception;
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                L_0x0164:
                    throw r1;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.Bank$AnonymousClass34.run():void");
                }
            });
        }
    }

    @JavascriptInterface
    public void fillOTPCallback(final boolean z) {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.isOTPFilled = z;
                    if (Bank.this.isOTPFilled) {
                        Bank.this.otp = null;
                        if (Bank.this.otpTriggered) {
                            Bank.this.otpTriggered = false;
                            try {
                                String string = Bank.this.h.getString(Bank.this.getString(g.cb_catchAll_success_msg));
                                if (Bank.this.aU) {
                                    Bank.this.aV = string;
                                } else {
                                    Toast.makeText(Bank.this.f.getApplicationContext(), string, 0).show();
                                }
                            } catch (JSONException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        }
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void enableCatchAllJS(final boolean z) {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.catchAllJSEnabled = z;
                    Bank.this.fillOTPOnBankPage(true);
                }
            });
        }
    }

    @JavascriptInterface
    public void cacheAnalytics(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.get("inputFields").toString();
            if (this.listOfTxtFld == null) {
                this.listOfTxtFld = str;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.listOfTxtFld);
                stringBuilder.append(str);
                this.listOfTxtFld = stringBuilder.toString();
            }
            this.hostName = jSONObject.get("hostName").toString();
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public void convertToNative(final String str, final String str2) {
        if (this.n) {
            dismissSnoozeWindow();
            killSnoozeService();
            cancelTransactionNotification();
            a("snooze_window_action", "snooze_window_dismissed_by_cb");
            a("snooze_window_automatically_disappear_time", "-1");
        }
        if (!(this.f == null || this.f.isFinishing())) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.dismissPayULoader();
                }
            });
        }
        if (!(this.pageType == null || this.pageType.equalsIgnoreCase(""))) {
            a("departure", "-1");
            this.pageType = "";
        }
        if (this.f != null && this.ag && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    if (Bank.this.O != null) {
                        Bank.this.ar.b(Bank.this.O.findViewById(e.progress));
                    }
                    if (Bank.this.P != null) {
                        Bank.this.ar.b(Bank.this.P.findViewById(e.progress));
                    }
                    try {
                        if (!(Bank.this.ai == null || Bank.this.d == null)) {
                            Bank.this.N.cancelTimer(Bank.this.ai);
                        }
                        if (str.equals(Bank.this.getString(g.cb_error))) {
                            Bank.this.onBankError();
                        } else if (str.equals("parse error")) {
                            Bank.this.onBankError();
                        } else if (str.contentEquals(CBConstant.LOADING) && !Bank.this.ae && Bank.this.ak) {
                            Bank.this.onHelpAvailable();
                            if (Bank.this.M != null) {
                                Bank.this.M.setVisibility(0);
                            }
                            if (Bank.this.O == null) {
                                Bank.this.O = Bank.this.f.getLayoutInflater().inflate(f.loading, null);
                            }
                            ImageView imageView = (ImageView) Bank.this.O.findViewById(e.bank_logo);
                            imageView.setOnClickListener(Bank.this.viewOnClickListener);
                            if (Bank.this.r != null) {
                                imageView.setImageDrawable(Bank.this.r);
                            }
                            LayoutParams layoutParams = new LayoutParams(-1, Bank.this.af);
                            View findViewById = Bank.this.O.findViewById(e.loading);
                            findViewById.setLayoutParams(layoutParams);
                            findViewById.requestLayout();
                            findViewById.invalidate();
                            Bank.this.ar.a(Bank.this.O.findViewById(e.progress));
                            Bank.this.K.removeAllViews();
                            Bank.this.K.addView(Bank.this.O);
                            if (Bank.this.K.isShown()) {
                                Bank.this.z = 2;
                            } else {
                                Bank.this.f();
                            }
                            Bank.this.updateHeight(Bank.this.O);
                            Bank.this.addReviewOrder(Bank.this.O);
                        } else {
                            boolean z = true;
                            View inflate;
                            ImageView imageView2;
                            final JSONObject jSONObject;
                            if (str.equals(Bank.this.getString(g.cb_choose))) {
                                Bank.this.a();
                                Bank.this.z = 2;
                                Bank.this.ak = true;
                                if (Bank.this.M != null) {
                                    Bank.this.M.setVisibility(0);
                                }
                                inflate = Bank.this.f.getLayoutInflater().inflate(f.choose_action, null);
                                Bank.this.addReviewOrder(inflate);
                                if (Bank.this.v == 0) {
                                    Bank.this.e();
                                    Bank.this.f();
                                }
                                Bank.this.K.setVisibility(0);
                                if (Bank.this.L != null) {
                                    Bank.this.L.setVisibility(8);
                                }
                                Bank.this.b(inflate);
                                Bank.this.onHelpAvailable();
                                inflate.measure(-2, -2);
                                Bank.this.af = inflate.getMeasuredHeight();
                                imageView2 = (ImageView) inflate.findViewById(e.bank_logo);
                                imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.r != null) {
                                    imageView2.setImageDrawable(Bank.this.r);
                                }
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                                if (Bank.this.K.isShown()) {
                                    Bank.this.z = 2;
                                }
                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Select an option for Faster payment");
                                spannableStringBuilder.setSpan(new StyleSpan(1), 21, 27, 33);
                                ((TextView) inflate.findViewById(e.choose_text)).setText(spannableStringBuilder);
                                try {
                                    jSONObject = new JSONObject(str2);
                                    if ((jSONObject.has(Bank.this.getString(g.cb_otp)) && jSONObject.getBoolean(Bank.this.getString(g.cb_otp))) || (jSONObject.has(Bank.this.getString(g.cb_pin)) && jSONObject.getBoolean(Bank.this.getString(g.cb_pin)))) {
                                        Bank.this.pageType = "Choose Screen";
                                    } else {
                                        Bank.this.pageType = "";
                                    }
                                    if (!jSONObject.has(Bank.this.getString(g.cb_otp)) || jSONObject.getBoolean(Bank.this.getString(g.cb_otp))) {
                                        inflate.findViewById(e.otp).setOnClickListener(Bank.this.aE);
                                        if (Bank.this.autoSelectOtp) {
                                            Bank.this.m = "auto_otp_select";
                                            Bank.this.a("user_input", Bank.this.m);
                                            inflate.findViewById(e.otp).performClick();
                                            Bank.this.autoSelectOtp = false;
                                        }
                                    } else {
                                        inflate.findViewById(e.otp).setVisibility(8);
                                        inflate.findViewById(e.view).setVisibility(8);
                                    }
                                    inflate.findViewById(e.otp).setOnClickListener(Bank.this.aE);
                                    if (!jSONObject.has(Bank.this.getString(g.cb_pin)) || jSONObject.getBoolean(Bank.this.getString(g.cb_pin))) {
                                        inflate.findViewById(e.pin).setOnClickListener(new OnClickListener() {
                                            public void onClick(View view) {
                                                Throwable e;
                                                Bank.this.ae = true;
                                                Bank.this.aj = Boolean.valueOf(true);
                                                Bank.this.f();
                                                Bank.this.z = 1;
                                                if (Bank.this.M != null) {
                                                    Bank.this.M.setVisibility(8);
                                                }
                                                try {
                                                    if (jSONObject.has(Bank.this.getString(g.cb_register)) && jSONObject.getBoolean(Bank.this.getString(g.cb_register))) {
                                                        View inflate = Bank.this.f.getLayoutInflater().inflate(f.register_pin, null);
                                                        try {
                                                            Bank.this.K.removeAllViews();
                                                            Bank.this.K.addView(inflate);
                                                            if (Bank.this.K.isShown()) {
                                                                Bank.this.z = 2;
                                                            }
                                                            inflate.findViewById(e.pin).setOnClickListener(new OnClickListener() {
                                                                public void onClick(View view) {
                                                                    try {
                                                                        Bank.this.m = "password_click";
                                                                        Bank.this.a("user_input", Bank.this.m);
                                                                        WebView webView = Bank.this.s;
                                                                        StringBuilder stringBuilder = new StringBuilder();
                                                                        stringBuilder.append("javascript:");
                                                                        stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_pin)));
                                                                        webView.loadUrl(stringBuilder.toString());
                                                                    } catch (JSONException e) {
                                                                        ThrowableExtension.printStackTrace(e);
                                                                    }
                                                                }
                                                            });
                                                            if (jSONObject.has(Bank.this.getString(g.cb_otp)) && !jSONObject.getBoolean(Bank.this.getString(g.cb_otp))) {
                                                                inflate.findViewById(e.otp).setVisibility(8);
                                                            }
                                                            inflate.findViewById(e.otp).setOnClickListener(Bank.this.aE);
                                                            view = inflate;
                                                        } catch (JSONException e2) {
                                                            View view2 = inflate;
                                                            e = e2;
                                                            view = view2;
                                                            ThrowableExtension.printStackTrace(e);
                                                            Bank.this.updateHeight(view);
                                                        }
                                                        Bank.this.updateHeight(view);
                                                    }
                                                    Bank.this.m = "password_click";
                                                    Bank.this.a("user_input", Bank.this.m);
                                                    Bank.this.onHelpUnavailable();
                                                    WebView webView = Bank.this.s;
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    stringBuilder.append("javascript:");
                                                    stringBuilder.append(Bank.this.i.getString(Bank.this.getString(g.cb_pin)));
                                                    webView.loadUrl(stringBuilder.toString());
                                                    Bank.this.updateHeight(view);
                                                } catch (JSONException e3) {
                                                    e = e3;
                                                }
                                            }
                                        });
                                    } else {
                                        inflate.findViewById(e.pin).setVisibility(8);
                                        inflate.findViewById(e.view).setVisibility(8);
                                    }
                                    if (jSONObject.has(Bank.this.getString(g.cb_error))) {
                                        inflate.findViewById(e.error_message).setVisibility(0);
                                        ((TextView) inflate.findViewById(e.error_message)).setText(jSONObject.getString("error"));
                                    }
                                } catch (JSONException e) {
                                    ThrowableExtension.printStackTrace(e);
                                }
                            } else if (str.equals(Bank.this.getString(g.cb_incorrect_OTP_2))) {
                                Bank.this.pageType = str;
                                Bank.this.a();
                                Bank.this.ak = true;
                                Bank.this.onHelpAvailable();
                                inflate = Bank.this.f.getLayoutInflater().inflate(f.retry_otp, null);
                                Bank.this.addReviewOrder(inflate);
                                imageView2 = (ImageView) inflate.findViewById(e.bank_logo);
                                imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.r != null) {
                                    imageView2.setImageDrawable(Bank.this.r);
                                }
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                                if (Bank.this.K.isShown()) {
                                    Bank.this.z = 2;
                                } else {
                                    if (Bank.this.L != null) {
                                        Bank.this.L.setVisibility(0);
                                    }
                                    Bank.this.f();
                                }
                                if (Bank.this.ah == null) {
                                    inflate.findViewById(e.regenerate_layout).setVisibility(0);
                                    inflate.findViewById(e.Regenerate_layout_gone).setVisibility(8);
                                    try {
                                        jSONObject = new JSONObject(str2);
                                        if (!jSONObject.has(Bank.this.getString(g.cb_pin)) || !jSONObject.getBoolean(Bank.this.getString(g.cb_pin))) {
                                            z = false;
                                        }
                                        inflate.findViewById(e.enter_manually).setOnClickListener(Bank.this.aE);
                                        if (z) {
                                            inflate.findViewById(e.pin_layout_gone).setVisibility(0);
                                        } else {
                                            inflate.findViewById(e.pin_layout_gone).setVisibility(8);
                                        }
                                        inflate.findViewById(e.pin).setOnClickListener(Bank.this.aE);
                                    } catch (Exception e2) {
                                        ThrowableExtension.printStackTrace(e2);
                                    }
                                }
                                Bank.this.updateHeight(inflate);
                            } else if (str.equals(Bank.this.getString(g.cb_retry_otp))) {
                                Bank.this.pageType = str;
                                Bank.this.a();
                                Bank.this.ak = true;
                                Bank.this.onHelpAvailable();
                                Bank.this.c();
                                if (Bank.this.M != null) {
                                    Bank.this.M.setVisibility(0);
                                }
                                inflate = Bank.this.f.getLayoutInflater().inflate(f.retry_otp, null);
                                Bank.this.addReviewOrder(inflate);
                                imageView2 = (ImageView) inflate.findViewById(e.bank_logo);
                                imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.r != null) {
                                    imageView2.setImageDrawable(Bank.this.r);
                                }
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                                if (Bank.this.K.isShown()) {
                                    Bank.this.z = 2;
                                } else {
                                    if (Bank.this.L != null) {
                                        Bank.this.L.setVisibility(0);
                                    }
                                    Bank.this.f();
                                }
                                try {
                                    if (Bank.this.ah == null) {
                                        jSONObject = new JSONObject(str2);
                                        boolean z2 = jSONObject.has(Bank.this.getString(g.cb_regenerate)) && jSONObject.getBoolean(Bank.this.getString(g.cb_regenerate));
                                        if (!jSONObject.has(Bank.this.getString(g.cb_pin)) || !jSONObject.getBoolean(Bank.this.getString(g.cb_pin))) {
                                            z = false;
                                        }
                                        inflate.findViewById(e.regenerate_layout).setVisibility(0);
                                        if (z2) {
                                            inflate.findViewById(e.Regenerate_layout_gone).setVisibility(0);
                                            if (z) {
                                                inflate.findViewById(e.Enter_manually_gone).setVisibility(8);
                                                inflate.findViewById(e.pin_layout_gone).setVisibility(0);
                                            } else {
                                                inflate.findViewById(e.Enter_manually_gone).setVisibility(0);
                                                inflate.findViewById(e.pin_layout_gone).setVisibility(8);
                                            }
                                        } else {
                                            if (z) {
                                                inflate.findViewById(e.pin_layout_gone).setVisibility(0);
                                            } else {
                                                inflate.findViewById(e.pin_layout_gone).setVisibility(8);
                                            }
                                            inflate.findViewById(e.Regenerate_layout_gone).setVisibility(8);
                                            inflate.findViewById(e.Enter_manually_gone).setVisibility(0);
                                        }
                                    }
                                    inflate.findViewById(e.pin).setOnClickListener(Bank.this.aE);
                                    inflate.findViewById(e.enter_manually).setOnClickListener(Bank.this.aE);
                                    inflate.findViewById(e.retry).setOnClickListener(Bank.this.aE);
                                    Bank.this.aE.a(inflate);
                                    inflate.findViewById(e.approve).setOnClickListener(Bank.this.aE);
                                } catch (Exception e22) {
                                    ThrowableExtension.printStackTrace(e22);
                                }
                                Bank.this.updateHeight(inflate);
                            } else if (str.equals(Bank.this.getString(g.cb_enter_pin))) {
                                Bank.this.pageType = "PIN Page";
                                Bank.this.a();
                                if (Bank.this.L != null) {
                                    Bank.this.L.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                                Bank.this.ae = true;
                                Bank.this.aj = Boolean.valueOf(true);
                                Bank.this.f();
                                Bank.this.z = 1;
                                if (Bank.this.M != null) {
                                    Bank.this.M.setVisibility(8);
                                }
                                Bank.this.f();
                                Bank.this.K.removeAllViews();
                            } else if (str.equals(Bank.this.getString(g.cb_enter_otp))) {
                                Bank.this.pageType = str;
                                Bank.this.aq = false;
                                Bank.this.n();
                                Bank.this.al = str2;
                                if (!Bank.this.ao) {
                                    Bank.this.a();
                                    Bank.this.a(str2);
                                }
                            } else if (str.equals(Bank.this.getString(g.cb_incorrect_pin))) {
                                Bank.this.pageType = "Choose Screen";
                                Bank.this.a();
                                try {
                                    JSONObject jSONObject2 = new JSONObject(str2);
                                    if (jSONObject2.has(Bank.this.getString(g.cb_otp)) && jSONObject2.getBoolean(Bank.this.getString(g.cb_otp))) {
                                        Bank.this.ak = true;
                                        Bank.this.onHelpAvailable();
                                        inflate = Bank.this.f.getLayoutInflater().inflate(f.choose_action, null);
                                        Bank.this.addReviewOrder(inflate);
                                        imageView2 = (ImageView) inflate.findViewById(e.bank_logo);
                                        imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                        if (Bank.this.r != null) {
                                            imageView2.setImageDrawable(Bank.this.r);
                                        }
                                        TextView textView = (TextView) inflate.findViewById(e.error_message);
                                        textView.setVisibility(0);
                                        textView.setText(Bank.this.f.getResources().getString(g.cb_incorrect_password));
                                        textView = (TextView) inflate.findViewById(e.choose_text);
                                        textView.setVisibility(0);
                                        textView.setText(Bank.this.f.getResources().getString(g.cb_retry));
                                        Bank.this.K.removeAllViews();
                                        Bank.this.K.addView(inflate);
                                        inflate.findViewById(e.otp).setOnClickListener(Bank.this.aE);
                                        inflate.findViewById(e.pin).setOnClickListener(Bank.this.aE);
                                        Bank.this.updateHeight(inflate);
                                        if (Bank.this.K.isShown()) {
                                            Bank.this.z = 2;
                                        } else {
                                            Bank.this.f();
                                        }
                                    }
                                } catch (Exception e3) {
                                    ThrowableExtension.printStackTrace(e3);
                                }
                            } else if (str.equals(Bank.this.getString(g.cb_register_option))) {
                                Bank.this.pageType = "Register Page";
                                Bank.this.a();
                                Bank.this.onHelpAvailable();
                                inflate = Bank.this.f.getLayoutInflater().inflate(f.register, null);
                                Bank.this.addReviewOrder(inflate);
                                Bank.this.K.removeAllViews();
                                Bank.this.K.addView(inflate);
                                imageView2 = (ImageView) inflate.findViewById(e.bank_logo);
                                imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.r != null) {
                                    imageView2.setImageDrawable(Bank.this.r);
                                }
                                Bank.this.updateHeight(inflate);
                                if (Bank.this.K.isShown()) {
                                    Bank.this.z = 2;
                                } else {
                                    Bank.this.f();
                                }
                            } else {
                                Bank.this.f();
                                Bank.this.z = 1;
                                if (Bank.this.L != null) {
                                    Bank.this.L.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                            }
                        }
                    } catch (Exception e32) {
                        ThrowableExtension.printStackTrace(e32);
                    }
                    if (Bank.this.pageType != null && !Bank.this.pageType.equalsIgnoreCase("")) {
                        Bank.this.a("arrival", "-1");
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void showJSRequestedToast(String str) {
        if (this.aU) {
            this.aV = str;
        } else {
            Toast.makeText(this.f.getApplicationContext(), str, 0).show();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        if (!this.o.contains("CUSTOM_BROWSER")) {
            this.m = "CUSTOM_BROWSER";
            this.o.add("CUSTOM_BROWSER");
            a("cb_status", this.m);
        }
    }

    public void onPageFinished() {
        if (isAdded() && !isRemoving() && this.f != null) {
            this.ap = true;
            if (this.aj.booleanValue()) {
                onHelpUnavailable();
                this.aj = Boolean.valueOf(false);
            }
            if (this.O != null && this.O.isShown()) {
                this.z = 1;
                f();
                onHelpUnavailable();
            }
            this.f.getWindow().setSoftInputMode(3);
            if (!(this.i == null || !this.ag || this.az)) {
                try {
                    WebView webView = this.s;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("javascript:");
                    stringBuilder.append(this.i.getString(getString(g.cb_init)));
                    webView.loadUrl(stringBuilder.toString());
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            if (this.h != null) {
                if (!this.aP) {
                    checkStatusFromJS("", 3);
                    this.aP = true;
                }
                if (this.M != null) {
                    this.M.setVisibility(8);
                }
            }
            fillOTPOnBankPage(true);
        }
    }

    public void fillOTPOnBankPage(boolean z) {
        if (!TextUtils.isEmpty(this.otp) && this.catchAllJSEnabled && !this.backwardJourneyStarted && !this.isOTPFilled) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("otp", this.otp);
                jSONObject.put("isAutoFillOTP", z);
                WebView webView = this.s;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(this.h.getString(getString(g.cb_fill_otp)));
                stringBuilder.append("(");
                stringBuilder.append(jSONObject);
                stringBuilder.append(")");
                webView.loadUrl(stringBuilder.toString());
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void fillOTPFromCBScreen(final String str) {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    if (Bank.this.isOTPFilled && Bank.this.backupOfOTP != null && !str.contentEquals(Bank.this.backupOfOTP)) {
                        Bank.this.otp = str;
                        Bank.this.backupOfOTP = Bank.this.otp;
                        Bank.this.isOTPFilled = false;
                        Bank.this.fillOTPOnBankPage(false);
                    }
                }
            });
        }
    }

    public void onPageStarted() {
        if (this.f != null && !this.f.isFinishing() && !isRemoving() && isAdded()) {
            if (this.q) {
                onHelpUnavailable();
                this.q = false;
            }
            if (isAdded() && !isRemoving()) {
                this.ap = false;
                if (this.h != null) {
                    try {
                        if (this.ag) {
                            WebView webView = this.s;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:");
                            stringBuilder.append(this.h.getString(getString(g.cb_detect_bank)));
                            webView.loadUrl(stringBuilder.toString());
                            p();
                        }
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                if (this.M != null) {
                    this.M.setVisibility(8);
                }
            }
        }
    }

    @JavascriptInterface
    public void onFailure(String str) {
        this.F = str;
    }

    @JavascriptInterface
    public void onPayuFailure(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        if (this.f != null) {
            this.m = "failure_transaction";
            a("trxn_status", this.m);
            this.G = Boolean.valueOf(false);
            this.E = str;
        }
        cancelTransactionNotification();
        k();
    }

    @JavascriptInterface
    public void onSuccess() {
        onSuccess("");
    }

    @JavascriptInterface
    public void onPayuSuccess(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        this.G = Boolean.valueOf(true);
        this.m = "success_transaction";
        a("trxn_status", this.m);
        this.E = str;
        if (this.H == 2) {
            try {
                JSONObject jSONObject = new JSONObject(this.E);
                this.N.storeInSharedPreferences(this.f.getApplicationContext(), jSONObject.getString("card_token"), jSONObject.getString("merchant_hash"));
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        cancelTransactionNotification();
        k();
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        this.F = str;
    }

    @JavascriptInterface
    public void onCancel() {
        onCancel("");
    }

    @JavascriptInterface
    public void onCancel(final String str) {
        if (this.f != null && !this.f.isFinishing()) {
            this.f.runOnUiThread(new Runnable() {
                public void run() {
                    if (Bank.this.f != null && !Bank.this.f.isFinishing() && Bank.this.isAdded()) {
                        Intent intent = new Intent();
                        intent.putExtra(Bank.this.getString(g.cb_result), str);
                        Bank.this.f.setResult(0, intent);
                        Bank.this.f.finish();
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x027a A:{Catch:{ Exception -> 0x0287 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0214 A:{SYNTHETIC, Splitter:B:66:0x0214} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01fa A:{SYNTHETIC, Splitter:B:58:0x01fa} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0214 A:{SYNTHETIC, Splitter:B:66:0x0214} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x027a A:{Catch:{ Exception -> 0x0287 }} */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x02b1 A:{Catch:{ Exception -> 0x0311 }} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01d4 A:{SYNTHETIC, Splitter:B:47:0x01d4} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01fa A:{SYNTHETIC, Splitter:B:58:0x01fa} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x027a A:{Catch:{ Exception -> 0x0287 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0214 A:{SYNTHETIC, Splitter:B:66:0x0214} */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x02b1 A:{Catch:{ Exception -> 0x0311 }} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x037e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0438  */
    public void a(java.lang.String r43) {
        /*
        r42 = this;
        r15 = r42;
        r1 = r15.aO;
        if (r1 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r42.m();
        r1 = r15.m;
        r2 = "payment_initiated";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0026;
    L_0x0014:
        r1 = "CUSTOM_BROWSER";
        r15.m = r1;
        r1 = r15.o;
        r2 = "CUSTOM_BROWSER";
        r1.add(r2);
        r1 = "cb_status";
        r2 = r15.m;
        r15.a(r1, r2);
    L_0x0026:
        r1 = r15.P;
        if (r1 == 0) goto L_0x0037;
    L_0x002a:
        r1 = r15.ar;
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.progress;
        r2 = r2.findViewById(r3);
        r1.b(r2);
    L_0x0037:
        r14 = 1;
        r15.ak = r14;
        r42.onHelpAvailable();
        r1 = r15.M;
        r13 = 0;
        if (r1 == 0) goto L_0x0047;
    L_0x0042:
        r1 = r15.M;
        r1.setVisibility(r13);
    L_0x0047:
        r1 = r15.P;
        if (r1 != 0) goto L_0x005a;
    L_0x004b:
        r1 = r15.f;
        r1 = r1.getLayoutInflater();
        r2 = com.payu.custombrowser.d.f.wait_for_otp;
        r3 = 0;
        r1 = r1.inflate(r2, r3);
        r15.P = r1;
    L_0x005a:
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.approve;
        r1 = r1.findViewById(r2);
        r12 = r1;
        r12 = (android.widget.Button) r12;
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.Regenerate_layout_gone;
        r7 = r1.findViewById(r2);
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.Enter_manually_gone;
        r9 = r1.findViewById(r2);
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.pin_layout_gone;
        r8 = r1.findViewById(r2);
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.regenerate_layout;
        r11 = r1.findViewById(r2);
        r1 = r15.P;
        r2 = com.payu.custombrowser.d.e.bank_logo;
        r1 = r1.findViewById(r2);
        r1 = (android.widget.ImageView) r1;
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.timer;
        r2 = r2.findViewById(r3);
        r4 = r2;
        r4 = (android.widget.TextView) r4;
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.otp_sms;
        r2 = r2.findViewById(r3);
        r10 = r2;
        r10 = (android.widget.EditText) r10;
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.waiting;
        r6 = r2.findViewById(r3);
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.pin;
        r5 = r2.findViewById(r3);
        r2 = r15.P;
        r3 = com.payu.custombrowser.d.e.retry;
        r3 = r2.findViewById(r3);
        r2 = r15.P;
        r14 = com.payu.custombrowser.d.e.enter_manually_button;
        r14 = r2.findViewById(r14);
        r2 = r15.P;
        r13 = com.payu.custombrowser.d.e.enter_manually;
        r13 = r2.findViewById(r13);
        r2 = r15.P;
        r21 = r1;
        r1 = com.payu.custombrowser.d.e.retry_text;
        r2 = r2.findViewById(r1);
        r1 = r15.P;
        r22 = r13;
        r13 = com.payu.custombrowser.d.e.text_or;
        r13 = r1.findViewById(r13);
        r1 = r15.P;
        r23 = r13;
        r13 = com.payu.custombrowser.d.e.progress;
        r13 = r1.findViewById(r13);
        r1 = r15.P;
        r15 = com.payu.custombrowser.d.e.otp_recieved;
        r15 = r1.findViewById(r15);
        r1 = 8;
        r12.setVisibility(r1);
        r25 = r14;
        r14 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        com.payu.custombrowser.util.CBUtil.setAlpha(r14, r12);
        r7.setVisibility(r1);
        r14 = 0;
        r9.setVisibility(r14);
        r8.setVisibility(r1);
        r11.setVisibility(r14);
        r14 = 4;
        r4.setVisibility(r14);
        r10.setVisibility(r1);
        r14 = 0;
        r6.setVisibility(r14);
        r5.setVisibility(r14);
        r3.setVisibility(r14);
        r2.setVisibility(r1);
        r13.setVisibility(r14);
        r15.setVisibility(r1);
        r27 = r15;
        r15 = r25;
        r15.setVisibility(r1);
        r1 = r22;
        r1.setVisibility(r14);
        r28 = r4;
        r4 = r23;
        r4.setVisibility(r14);
        r29 = r3;
        r14 = r42;
        r3 = r14.aE;
        r15.setOnClickListener(r3);
        r3 = r14.aE;
        r1.setOnClickListener(r3);
        r3 = r14.r;
        if (r3 == 0) goto L_0x0156;
    L_0x014c:
        r3 = r14.r;
        r30 = r1;
        r1 = r21;
        r1.setImageDrawable(r3);
        goto L_0x0158;
    L_0x0156:
        r30 = r1;
    L_0x0158:
        r1 = new com.payu.custombrowser.Bank$7;
        r1.<init>(r10, r12);
        r10.addTextChangedListener(r1);
        r1 = r14.f;
        if (r1 == 0) goto L_0x0171;
    L_0x0164:
        r1 = r14.f;
        r1 = r1.isFinishing();
        if (r1 != 0) goto L_0x0171;
    L_0x016c:
        r1 = r14.ar;
        r1.a(r13);
    L_0x0171:
        r1 = r14.K;
        r1.removeAllViews();
        r1 = r14.K;
        r3 = r14.P;
        r1.addView(r3);
        r1 = r14.K;
        r1 = r1.isShown();
        r3 = 2;
        if (r1 == 0) goto L_0x0189;
    L_0x0186:
        r14.z = r3;
        goto L_0x018c;
    L_0x0189:
        r42.f();
    L_0x018c:
        r1 = r14.z;
        r3 = 1;
        if (r1 != r3) goto L_0x019b;
    L_0x0191:
        r1 = r14.L;
        if (r1 == 0) goto L_0x019b;
    L_0x0195:
        r1 = r14.L;
        r3 = 0;
        r1.setVisibility(r3);
    L_0x019b:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0323 }
        r1 = r43;
        r3.<init>(r1);	 Catch:{ Exception -> 0x0323 }
        r1 = com.payu.custombrowser.d.g.cb_regenerate;	 Catch:{ Exception -> 0x0323 }
        r1 = r14.getString(r1);	 Catch:{ Exception -> 0x0323 }
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0323 }
        if (r1 == 0) goto L_0x01c5;
    L_0x01ae:
        r1 = com.payu.custombrowser.d.g.cb_regenerate;	 Catch:{ Exception -> 0x01be }
        r1 = r14.getString(r1);	 Catch:{ Exception -> 0x01be }
        r1 = r3.getBoolean(r1);	 Catch:{ Exception -> 0x01be }
        if (r1 == 0) goto L_0x01c5;
    L_0x01ba:
        r31 = r13;
        r1 = 1;
        goto L_0x01c8;
    L_0x01be:
        r0 = move-exception;
        r1 = r0;
        r33 = r29;
    L_0x01c2:
        r3 = 2;
        goto L_0x0328;
    L_0x01c5:
        r31 = r13;
        r1 = 0;
    L_0x01c8:
        r13 = com.payu.custombrowser.d.g.cb_skip_screen;	 Catch:{ Exception -> 0x031d }
        r13 = r14.getString(r13);	 Catch:{ Exception -> 0x031d }
        r13 = r3.has(r13);	 Catch:{ Exception -> 0x031d }
        if (r13 == 0) goto L_0x01eb;
    L_0x01d4:
        r13 = com.payu.custombrowser.d.g.cb_skip_screen;	 Catch:{ Exception -> 0x01e4 }
        r13 = r14.getString(r13);	 Catch:{ Exception -> 0x01e4 }
        r13 = r3.getBoolean(r13);	 Catch:{ Exception -> 0x01e4 }
        if (r13 == 0) goto L_0x01eb;
    L_0x01e0:
        r32 = r15;
        r13 = 1;
        goto L_0x01ee;
    L_0x01e4:
        r0 = move-exception;
    L_0x01e5:
        r1 = r0;
        r33 = r29;
    L_0x01e8:
        r13 = r31;
        goto L_0x01c2;
    L_0x01eb:
        r32 = r15;
        r13 = 0;
    L_0x01ee:
        r15 = com.payu.custombrowser.d.g.cb_pin;	 Catch:{ Exception -> 0x0315 }
        r15 = r14.getString(r15);	 Catch:{ Exception -> 0x0315 }
        r15 = r3.has(r15);	 Catch:{ Exception -> 0x0315 }
        if (r15 == 0) goto L_0x0211;
    L_0x01fa:
        r15 = com.payu.custombrowser.d.g.cb_pin;	 Catch:{ Exception -> 0x0208 }
        r15 = r14.getString(r15);	 Catch:{ Exception -> 0x0208 }
        r3 = r3.getBoolean(r15);	 Catch:{ Exception -> 0x0208 }
        if (r3 == 0) goto L_0x0211;
    L_0x0206:
        r3 = 1;
        goto L_0x0212;
    L_0x0208:
        r0 = move-exception;
        r1 = r0;
        r33 = r29;
        r13 = r31;
        r15 = r32;
        goto L_0x01c2;
    L_0x0211:
        r3 = 0;
    L_0x0212:
        if (r13 == 0) goto L_0x027a;
    L_0x0214:
        r13 = r14.f;	 Catch:{ Exception -> 0x0275 }
        if (r13 == 0) goto L_0x0270;
    L_0x0218:
        if (r10 == 0) goto L_0x0270;
    L_0x021a:
        r13 = r10.getVisibility();	 Catch:{ Exception -> 0x0275 }
        if (r13 == 0) goto L_0x0270;
    L_0x0220:
        r13 = 0;
        r2.setVisibility(r13);	 Catch:{ Exception -> 0x0275 }
        r15 = 8;
        r4.setVisibility(r15);	 Catch:{ Exception -> 0x0275 }
        if (r1 == 0) goto L_0x0237;
    L_0x022b:
        r7.setVisibility(r13);	 Catch:{ Exception -> 0x0208 }
        r8.setVisibility(r15);	 Catch:{ Exception -> 0x0208 }
        r9.setVisibility(r13);	 Catch:{ Exception -> 0x0208 }
        r1 = 8;
        goto L_0x024a;
    L_0x0237:
        if (r3 == 0) goto L_0x023f;
    L_0x0239:
        r8.setVisibility(r13);	 Catch:{ Exception -> 0x0208 }
        r1 = 8;
        goto L_0x0244;
    L_0x023f:
        r1 = 8;
        r8.setVisibility(r1);	 Catch:{ Exception -> 0x0275 }
    L_0x0244:
        r7.setVisibility(r1);	 Catch:{ Exception -> 0x0275 }
        r9.setVisibility(r13);	 Catch:{ Exception -> 0x0275 }
    L_0x024a:
        r11.setVisibility(r13);	 Catch:{ Exception -> 0x0275 }
        r12.setVisibility(r1);	 Catch:{ Exception -> 0x0275 }
        r6.setVisibility(r1);	 Catch:{ Exception -> 0x0275 }
        r1 = r14.aE;	 Catch:{ Exception -> 0x0275 }
        r5.setOnClickListener(r1);	 Catch:{ Exception -> 0x0275 }
        r1 = r14.aE;	 Catch:{ Exception -> 0x0275 }
        r13 = r29;
        r13.setOnClickListener(r1);	 Catch:{ Exception -> 0x026c }
        r1 = r14.aE;	 Catch:{ Exception -> 0x026c }
        r15 = r32;
        r15.setOnClickListener(r1);	 Catch:{ Exception -> 0x0287 }
        r1 = r14.P;	 Catch:{ Exception -> 0x0287 }
        r14.updateHeight(r1);	 Catch:{ Exception -> 0x0287 }
        goto L_0x02ab;
    L_0x026c:
        r0 = move-exception;
        r15 = r32;
        goto L_0x0288;
    L_0x0270:
        r13 = r29;
        r15 = r32;
        goto L_0x02ab;
    L_0x0275:
        r0 = move-exception;
        r15 = r32;
        goto L_0x01e5;
    L_0x027a:
        r13 = r29;
        r15 = r32;
        if (r1 != 0) goto L_0x028d;
    L_0x0280:
        if (r3 != 0) goto L_0x028d;
    L_0x0282:
        r1 = r14.am;	 Catch:{ Exception -> 0x0287 }
        if (r1 == 0) goto L_0x02ab;
    L_0x0286:
        goto L_0x028d;
    L_0x0287:
        r0 = move-exception;
    L_0x0288:
        r1 = r0;
        r33 = r13;
        goto L_0x01e8;
    L_0x028d:
        r1 = "";
        r10.setText(r1);	 Catch:{ Exception -> 0x0311 }
        r1 = new java.util.Timer;	 Catch:{ Exception -> 0x0311 }
        r1.<init>();	 Catch:{ Exception -> 0x0311 }
        r14.ai = r1;	 Catch:{ Exception -> 0x0311 }
        r1 = r14.ai;	 Catch:{ Exception -> 0x0311 }
        r3 = new com.payu.custombrowser.Bank$8;	 Catch:{ Exception -> 0x0311 }
        r3.<init>();	 Catch:{ Exception -> 0x0311 }
        r23 = 0;
        r25 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r21 = r1;
        r22 = r3;
        r21.scheduleAtFixedRate(r22, r23, r25);	 Catch:{ Exception -> 0x0311 }
    L_0x02ab:
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0311 }
        r3 = 23;
        if (r1 < r3) goto L_0x030b;
    L_0x02b1:
        r1 = r14.am;	 Catch:{ Exception -> 0x0311 }
        if (r1 != 0) goto L_0x030b;
    L_0x02b5:
        r1 = 0;
        r12.setClickable(r1);	 Catch:{ Exception -> 0x0311 }
        r1 = r14.P;	 Catch:{ Exception -> 0x0311 }
        r3 = com.payu.custombrowser.d.e.linear_layout_waiting_for_otp;	 Catch:{ Exception -> 0x0311 }
        r1 = r1.findViewById(r3);	 Catch:{ Exception -> 0x0311 }
        r1 = (android.widget.LinearLayout) r1;	 Catch:{ Exception -> 0x0311 }
        r1 = r14.aS;	 Catch:{ Exception -> 0x0311 }
        if (r1 == 0) goto L_0x02d4;
    L_0x02c7:
        r3 = 2;
        r10.setInputType(r3);	 Catch:{ Exception -> 0x02cd }
        r1 = 1;
        goto L_0x02d9;
    L_0x02cd:
        r0 = move-exception;
        r1 = r0;
        r33 = r13;
        r13 = r31;
        goto L_0x0328;
    L_0x02d4:
        r1 = 1;
        r3 = 2;
        r10.setInputType(r1);	 Catch:{ Exception -> 0x0305 }
    L_0x02d9:
        r14.a(r10);	 Catch:{ Exception -> 0x0305 }
        r33 = r13;
        r13 = 0;
        r10.setVisibility(r13);	 Catch:{ Exception -> 0x0303 }
        r12.setVisibility(r13);	 Catch:{ Exception -> 0x0303 }
        r1 = 8;
        r6.setVisibility(r1);	 Catch:{ Exception -> 0x0303 }
        r13 = r31;
        r13.setVisibility(r1);	 Catch:{ Exception -> 0x0301 }
        r1 = 0;
        r11.setVisibility(r1);	 Catch:{ Exception -> 0x0301 }
        r1 = 8;
        r9.setVisibility(r1);	 Catch:{ Exception -> 0x0301 }
        r1 = new com.payu.custombrowser.Bank$9;	 Catch:{ Exception -> 0x0301 }
        r1.<init>(r10, r12);	 Catch:{ Exception -> 0x0301 }
        r10.addTextChangedListener(r1);	 Catch:{ Exception -> 0x0301 }
        goto L_0x032b;
    L_0x0301:
        r0 = move-exception;
        goto L_0x0327;
    L_0x0303:
        r0 = move-exception;
        goto L_0x0308;
    L_0x0305:
        r0 = move-exception;
        r33 = r13;
    L_0x0308:
        r13 = r31;
        goto L_0x0327;
    L_0x030b:
        r33 = r13;
        r13 = r31;
        r3 = 2;
        goto L_0x032b;
    L_0x0311:
        r0 = move-exception;
        r33 = r13;
        goto L_0x0320;
    L_0x0315:
        r0 = move-exception;
        r33 = r29;
        r13 = r31;
        r15 = r32;
        goto L_0x0326;
    L_0x031d:
        r0 = move-exception;
        r33 = r29;
    L_0x0320:
        r13 = r31;
        goto L_0x0326;
    L_0x0323:
        r0 = move-exception;
        r33 = r29;
    L_0x0326:
        r3 = 2;
    L_0x0327:
        r1 = r0;
    L_0x0328:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);
    L_0x032b:
        r1 = android.os.Build.VERSION.SDK_INT;
        r3 = 23;
        if (r1 < r3) goto L_0x0338;
    L_0x0331:
        r1 = r14.am;
        if (r1 != 0) goto L_0x0338;
    L_0x0335:
        r1 = 45;
        goto L_0x033a;
    L_0x0338:
        r1 = 30;
    L_0x033a:
        r3 = r1;
        r1 = new com.payu.custombrowser.Bank$10;
        r34 = r1;
        r16 = r30;
        r17 = 0;
        r18 = 1;
        r35 = r2;
        r2 = r14;
        r19 = r33;
        r20 = r4;
        r4 = r28;
        r21 = r5;
        r5 = r43;
        r36 = r6;
        r6 = r20;
        r37 = r10;
        r10 = r16;
        r38 = r11;
        r11 = r15;
        r15 = r12;
        r12 = r35;
        r39 = r13;
        r13 = r38;
        r14 = r15;
        r40 = r15;
        r41 = r27;
        r15 = r36;
        r16 = r37;
        r17 = r21;
        r18 = r19;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        r2 = r34;
        r1 = r42;
        r1.d = r2;
        r2 = r1.ah;
        if (r2 == 0) goto L_0x0422;
    L_0x037e:
        r2 = r37;
        if (r2 == 0) goto L_0x0422;
    L_0x0382:
        r3 = r2.getVisibility();
        if (r3 == 0) goto L_0x0422;
    L_0x0388:
        r3 = r1.N;
        r4 = r1.ai;
        r3.cancelTimer(r4);
        r3 = r1.f;
        r4 = com.payu.custombrowser.d.e.timer;
        r3 = r3.findViewById(r4);
        r4 = 8;
        r3.setVisibility(r4);
        r3 = r1.m;
        r5 = "payment_initiated";
        r3 = r3.equals(r5);
        if (r3 != 0) goto L_0x03b0;
    L_0x03a6:
        r3 = r1.m;
        r5 = "CUSTOM_BROWSER";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x03bb;
    L_0x03b0:
        r3 = "received_otp_direct";
        r1.m = r3;
        r3 = "otp_received";
        r5 = r1.m;
        r1.a(r3, r5);
    L_0x03bb:
        r3 = r1.ah;
        r2.setText(r3);
        r3 = com.payu.custombrowser.d.g.cb_approve_otp;
        r3 = r1.getString(r3);
        r5 = r40;
        r5.setText(r3);
        r3 = 1;
        r5.setClickable(r3);
        r6 = r1.autoApprove;
        if (r6 == 0) goto L_0x03e1;
    L_0x03d3:
        r5.performClick();
        r6 = "auto_approve";
        r1.m = r6;
        r6 = "user_input";
        r7 = r1.m;
        r1.a(r6, r7);
    L_0x03e1:
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        com.payu.custombrowser.util.CBUtil.setAlpha(r6, r5);
        r6 = r41;
        r7 = 0;
        r6.setVisibility(r7);
        r6 = r1.ar;
        r8 = r39;
        r6.c(r8);
        r6 = r35;
        r6.setVisibility(r4);
        r6 = r38;
        r6.setVisibility(r4);
        r5.setVisibility(r7);
        r6 = r36;
        r6.setVisibility(r4);
        r4 = r1.aS;
        if (r4 == 0) goto L_0x040e;
    L_0x0409:
        r4 = 2;
        r2.setInputType(r4);
        goto L_0x0412;
    L_0x040e:
        r4 = 2;
        r2.setInputType(r3);
    L_0x0412:
        r2.setVisibility(r7);
        r2 = r1.aE;
        r3 = r1.P;
        r2.a(r3);
        r2 = r1.aE;
        r5.setOnClickListener(r2);
        goto L_0x0423;
    L_0x0422:
        r4 = 2;
    L_0x0423:
        r2 = r1.P;
        r1.updateHeight(r2);
        r2 = r1.P;
        r1.addReviewOrder(r2);
        r2 = r1.K;
        r2 = r2.isShown();
        if (r2 == 0) goto L_0x0438;
    L_0x0435:
        r1.z = r4;
        goto L_0x043b;
    L_0x0438:
        r42.f();
    L_0x043b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.Bank.a(java.lang.String):void");
    }

    /* Access modifiers changed, original: 0000 */
    public int b(String str) {
        if (str.equalsIgnoreCase(getString(g.cb_pin))) {
            return 3;
        }
        if (str.equalsIgnoreCase(getString(g.cb_password))) {
            return 1;
        }
        if (str.equalsIgnoreCase(getString(g.cb_enter_manually))) {
            return 4;
        }
        if (str.equalsIgnoreCase(getString(g.cb_approve_otp))) {
            return 5;
        }
        if (str.equalsIgnoreCase(getString(g.cb_otp)) || str.equalsIgnoreCase(getString(g.cb_use_sms_otp))) {
            return 6;
        }
        if (str.equalsIgnoreCase(getString(g.cb_sms_otp))) {
            return 7;
        }
        return str.equalsIgnoreCase(getString(g.cb_regenerate_otp)) ? 2 : 0;
    }

    private void t() {
        this.ax = new CountDownTimer((long) this.snoozeUrlLoadingTimeout, 500) {
            public void onTick(long j) {
                Bank.this.ay = true;
            }

            public void onFinish() {
                Bank.this.ay = false;
                if (Bank.this.s.getProgress() < Bank.this.snoozeUrlLoadingPercentage && !Bank.this.n && Bank.this.aL && !Bank.this.getTransactionStatusReceived()) {
                    Bank.this.launchSnoozeWindow();
                }
                Bank.this.u();
            }
        };
        this.ax.start();
    }

    private void u() {
        if (this.ax != null) {
            this.ay = false;
            this.ax.cancel();
            this.ax = null;
        }
    }

    public void launchSnoozeWindow() {
        launchSnoozeWindow(1);
    }

    public void launchSnoozeWindow(int i) {
        final int i2 = i;
        if (this.av != 3) {
            if (i2 != 2 || this.av != 2) {
                if (i2 != 1 || this.av != 1) {
                    showCbBlankOverlay(8);
                    if (this.backwardJourneyStarted) {
                        if (this.snoozeCountBackwardJourney >= this.customBrowserConfig.getEnableSurePay() || (this.isS2SHtmlSupport && (TextUtils.isEmpty(this.surePayS2SPayUId) || TextUtils.isEmpty(this.surePayS2Surl)))) {
                            return;
                        }
                    } else if (this.snoozeCount >= this.customBrowserConfig.getEnableSurePay() || (this.isS2SHtmlSupport && TextUtils.isEmpty(this.surePayS2Surl))) {
                        return;
                    }
                    this.snoozeMode = i2;
                    if (!(this.f == null || this.f.isFinishing())) {
                        boolean z;
                        dismissSlowUserWarning();
                        a(8, "");
                        this.n = true;
                        a("snooze_window_status", "snooze_visible");
                        a("snooze_appear_url", this.B);
                        a("snooze_window_launch_mode", i2 == 1 ? CBConstant.STR_SNOOZE_MODE_WARN : CBConstant.STR_SNOOZE_MODE_FAIL);
                        a("snooze_window_appear_time", "-1");
                        this.aB = this.f.getLayoutInflater().inflate(f.cb_layout_snooze, null);
                        TextView textView = (TextView) this.aB.findViewById(e.text_view_snooze_message);
                        TextView textView2 = (TextView) this.aB.findViewById(e.text_view_transaction_snoozed_message1);
                        TextView textView3 = (TextView) this.aB.findViewById(e.button_cancel_transaction);
                        Button button = (Button) this.aB.findViewById(e.button_snooze_transaction);
                        Button button2 = (Button) this.aB.findViewById(e.button_retry_transaction);
                        TextView textView4 = (TextView) this.aB.findViewById(e.text_view_cancel_snooze_window);
                        final TextView textView5 = (TextView) this.aB.findViewById(e.t_confirm);
                        TextView textView6 = (TextView) this.aB.findViewById(e.t_nconfirm);
                        TextView textView7 = (TextView) this.aB.findViewById(e.snooze_header_txt);
                        TextView textView8 = (TextView) this.aB.findViewById(e.text_view_retry_message_detail);
                        Button button3 = (Button) this.aB.findViewById(e.button_retry_anyway);
                        this.aA = (SnoozeLoaderView) this.aB.findViewById(e.snooze_loader_view);
                        this.aA.setVisibility(8);
                        textView4.setVisibility(0);
                        textView3.setVisibility(0);
                        button.setVisibility(0);
                        button2.setVisibility(0);
                        textView.setVisibility(0);
                        textView2.setVisibility(8);
                        textView8.setVisibility(0);
                        textView5.setVisibility(8);
                        textView6.setVisibility(8);
                        button3.setVisibility(8);
                        textView.setText(this.f.getString(g.cb_slownetwork_status));
                        textView7.setText(this.f.getString(g.cb_try_later));
                        textView8.setText(this.f.getString(g.cb_retry_restart));
                        if (this.backwardJourneyStarted && this.T) {
                            textView.setText(this.f.getResources().getString(g.cb_slow_internet_confirmation));
                            textView2.setText(this.f.getResources().getString(g.cb_receive_sms));
                            textView7.setText(this.f.getResources().getString(g.cb_confirm_transaction));
                            button.setVisibility(8);
                            textView8.setVisibility(8);
                            button2.setVisibility(8);
                            textView3.setVisibility(8);
                            textView.setVisibility(0);
                            textView2.setVisibility(0);
                            textView5.setVisibility(0);
                            textView6.setVisibility(0);
                            button3.setVisibility(8);
                            this.snoozeVisibleCountBackwdJourney++;
                            a("snooze_backward_visible", "Y");
                        } else {
                            this.snoozeVisibleCountFwdJourney++;
                        }
                        Button button4 = button3;
                        AnonymousClass13 anonymousClass13 = r0;
                        final TextView textView9 = textView7;
                        TextView textView10 = textView8;
                        textView8 = textView;
                        TextView textView11 = textView7;
                        final Button button5 = button;
                        TextView textView12 = textView6;
                        textView6 = textView2;
                        TextView textView13 = textView5;
                        TextView textView14 = textView2;
                        textView2 = textView4;
                        textView4 = textView12;
                        AnonymousClass13 anonymousClass132 = new OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.a(com.payu.custombrowser.util.a.a, "confirm_deduction_y");
                                if (Bank.this.ai != null) {
                                    Bank.this.ai.cancel();
                                    Bank.this.ai.purge();
                                }
                                Bank bank = Bank.this;
                                bank.snoozeCountBackwardJourney++;
                                Bank.this.k.setCanceledOnTouchOutside(false);
                                textView9.setText(Bank.this.f.getResources().getString(g.cb_confirm_transaction));
                                textView8.setText(Bank.this.f.getString(g.cb_transaction_status));
                                Bank.this.aA.setVisibility(0);
                                Bank.this.aA.a();
                                button5.setVisibility(8);
                                textView6.setVisibility(8);
                                textView5.setVisibility(8);
                                textView4.setVisibility(8);
                                if (Bank.this.S) {
                                    Bank.this.startSnoozeServiceVerifyPayment(Bank.this.f.getResources().getString(g.cb_verify_message_received));
                                } else {
                                    Bank.this.startSnoozeServiceVerifyPayment(Bank.this.f.getResources().getString(g.cb_user_input_confirm_transaction));
                                }
                            }
                        };
                        textView13.setOnClickListener(anonymousClass13);
                        textView12.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank bank = Bank.this;
                                bank.snoozeCountBackwardJourney++;
                                Bank.this.dismissSnoozeWindow();
                                Bank.this.a(com.payu.custombrowser.util.a.a, "confirm_deduction_n");
                            }
                        });
                        textView2.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank bank;
                                if (Bank.this.backwardJourneyStarted) {
                                    bank = Bank.this;
                                    bank.snoozeCountBackwardJourney++;
                                } else {
                                    bank = Bank.this;
                                    bank.snoozeCount++;
                                }
                                Bank.this.a("snooze_interaction_time", "-1");
                                if (!Bank.this.backwardJourneyStarted) {
                                    Bank.this.a("snooze_window_action", "snooze_cancel_window_click");
                                }
                                if (i2 == 2) {
                                    Bank.this.killSnoozeService();
                                }
                                Bank.this.dismissSnoozeWindow();
                            }
                        });
                        button2.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.i();
                                Bank.this.c(view);
                            }
                        });
                        textView9 = textView2;
                        textView8 = textView3;
                        final Button button6 = button2;
                        textView5 = textView;
                        textView4 = textView10;
                        textView = textView11;
                        textView2 = textView14;
                        button2 = button4;
                        button.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.isRetryNowPressed = true;
                                Bank bank = Bank.this;
                                bank.snoozeCount++;
                                Bank.this.a("snooze_interaction_time", "-1");
                                Bank.this.f();
                                Bank.this.z = 1;
                                if (Bank.this.L != null) {
                                    Bank.this.L.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                                Bank.this.snoozeClickedTime = System.currentTimeMillis();
                                Bank.this.isSnoozeBroadCastReceiverRegistered = true;
                                Bank.this.az = true;
                                Bank.this.s.stopLoading();
                                if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                                    b.hasToStart = true;
                                    Bank.this.bindService();
                                }
                                Bank.this.ah = null;
                                Bank.this.unregisterBroadcast(Bank.this.g);
                                textView9.setVisibility(8);
                                textView8.setVisibility(8);
                                button5.setVisibility(8);
                                button6.setVisibility(8);
                                textView5.setText("We have paused your transaction because the network was unable to process it now. We will notify you when the network improves.");
                                textView4.setVisibility(8);
                                textView.setText(Bank.this.f.getResources().getString(g.cb_transaction_paused));
                                textView2.setVisibility(0);
                                button2.setVisibility(0);
                                Bank.this.a(8, "");
                                Bank.this.a("snooze_window_action", "snooze_click");
                                Bank.this.a("snooze_load_url", Bank.this.s.getUrl() == null ? Bank.this.B : Bank.this.s.getUrl());
                                Bank.this.slowUserCountDownTimer = null;
                                Bank.this.showCbBlankOverlay(0);
                            }
                        });
                        textView3.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank bank;
                                if (Bank.this.backwardJourneyStarted) {
                                    bank = Bank.this;
                                    bank.snoozeCountBackwardJourney++;
                                } else {
                                    bank = Bank.this;
                                    bank.snoozeCount++;
                                }
                                Bank.this.a("snooze_interaction_time", "-1");
                                Bank.this.a("snooze_window_action", "snooze_cancel_transaction_click");
                                Bank.this.showBackButtonDialog();
                            }
                        });
                        button4.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.i();
                                Bank.this.c(view);
                            }
                        });
                        if (this.k == null || !this.k.isShowing()) {
                            this.k = new Builder(this.f).create();
                            this.k.setView(this.aB);
                            z = false;
                            this.k.setCanceledOnTouchOutside(false);
                            this.k.setOnDismissListener(new OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    Bank.this.showCbBlankOverlay(8);
                                }
                            });
                            this.k.setOnKeyListener(new OnKeyListener() {
                                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                    if (i == 4 && keyEvent.getAction() == 0) {
                                        Bank.this.a("user_input", "payu_back_button".toLowerCase());
                                        Bank.this.showBackButtonDialog();
                                    }
                                    return true;
                                }
                            });
                        } else {
                            z = false;
                        }
                        dismissReviewOrder();
                        this.k.show();
                        if (i2 == 2 && !this.backwardJourneyStarted) {
                            hasToStart = z;
                            bindService();
                        }
                    }
                }
            }
        }
    }

    public void bindService() {
        LocalBroadcastManager.getInstance(this.f).unregisterReceiver(this.snoozeBroadCastReceiver);
        LocalBroadcastManager.getInstance(this.f.getApplicationContext()).registerReceiver(this.snoozeBroadCastReceiver, new IntentFilter(this.SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION));
        Intent intent = new Intent(this.f, SnoozeService.class);
        intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
        intent.putExtra(CBConstant.CURRENT_URL, this.B);
        intent.putExtra(CBConstant.MERCHANT_CHECKOUT_ACTIVITY, this.customBrowserConfig.getMerchantCheckoutActivityPath());
        if (!TextUtils.isEmpty(this.surePayS2Surl)) {
            intent.putExtra(CBConstant.S2S_RETRY_URL, this.surePayS2Surl);
        }
        this.isSnoozeServiceBounded = true;
        this.f.bindService(intent, this.snoozeServiceConnection, 1);
        this.f.startService(intent);
    }

    public void startSnoozeServiceVerifyPayment(String str) {
        LocalBroadcastManager.getInstance(this.f).unregisterReceiver(this.snoozeBroadCastReceiver);
        LocalBroadcastManager.getInstance(this.f.getApplicationContext()).registerReceiver(this.snoozeBroadCastReceiver, new IntentFilter(this.SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION));
        Intent intent = new Intent(this.f, SnoozeService.class);
        intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
        intent.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
        intent.putExtra(CBConstant.MERCHANT_CHECKOUT_ACTIVITY, this.customBrowserConfig.getMerchantCheckoutActivityPath());
        intent.putExtra(CBConstant.VERIFY_ADDON_PARAMS, str);
        if (!TextUtils.isEmpty(this.surePayS2SPayUId)) {
            intent.putExtra(CBConstant.PAYUID, this.surePayS2SPayUId);
        }
        if (!TextUtils.isEmpty(this.merchantKey)) {
            intent.putExtra(CBConstant.MERCHANTKEY, this.merchantKey);
        }
        if (!TextUtils.isEmpty(this.txnId)) {
            intent.putExtra(CBConstant.TXN_ID, this.txnId);
        }
        this.isSnoozeServiceBounded = true;
        this.f.bindService(intent, this.snoozeServiceConnection, 1);
        this.isSnoozeBroadCastReceiverRegistered = true;
        this.f.startService(intent);
    }

    public void dismissSnoozeWindow() {
        this.n = false;
        showReviewOrderHorizontalBar();
        if (this.k != null) {
            this.k.dismiss();
            this.k.cancel();
            showCbBlankOverlay(8);
        }
    }

    public void setMagicRetry(Map<String, String> map) {
        if (this.p != null) {
            this.p.a((Map) map);
        }
    }

    public void initMagicRetry() {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        this.p = new MagicRetryFragment();
        Bundle bundle = new Bundle();
        if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
            bundle.putString("transaction_id", this.customBrowserConfig.getTransactionID());
        }
        this.p.setArguments(bundle);
        supportFragmentManager.beginTransaction().add(e.magic_retry_container, this.p, "magicRetry").commit();
        toggleFragmentVisibility(0);
        this.p.b(true);
        this.p.a(this.s);
        this.p.a(this.f);
        if (this.customBrowserConfig.getEnableSurePay() > 0) {
            this.s.setWebViewClient(new PayUSurePayWebViewClient(this, keyAnalytics));
        } else {
            this.s.setWebViewClient(new PayUWebViewClient(this, this.p, keyAnalytics));
        }
    }

    public void toggleFragmentVisibility(int i) {
        if (getActivity() != null && !getActivity().isFinishing() && isAdded() && !isRemoving()) {
            FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            if (this.p != null && i == 1) {
                beginTransaction.show(this.p).commitAllowingStateLoss();
            } else if (this.p != null && i == 0) {
                beginTransaction.hide(this.p).commitAllowingStateLoss();
            }
        }
    }

    public void showMagicRetry() {
        dismissSnoozeWindow();
        toggleFragmentVisibility(1);
    }

    public void hideMagicRetry() {
        toggleFragmentVisibility(0);
    }

    public void showBackButtonDialog() {
        if (this.f != null && !this.f.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.f, h.cb_dialog);
            builder.setCancelable(false);
            builder.setMessage("Do you really want to cancel the transaction ?");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bank.this.postToPaytxn();
                    if (Bank.this.k != null && Bank.this.k.isShowing()) {
                        Bank.this.k.cancel();
                    }
                    Bank.this.killSnoozeService();
                    Bank.this.cancelTransactionNotification();
                    Bank.this.a("user_input", "back_button_ok");
                    Bank.this.dismissSnoozeWindow();
                    if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                        b.SINGLETON.getPayuCustomBrowserCallback().onBackApprove();
                    }
                    Bank.this.f.finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bank.this.a("user_input", "back_button_cancel");
                    dialogInterface.dismiss();
                    if (b.SINGLETON != null && b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                        b.SINGLETON.getPayuCustomBrowserCallback().onBackDismiss();
                    }
                }
            });
            if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                b.SINGLETON.getPayuCustomBrowserCallback().onBackButton(builder);
            }
            this.aR = builder.create();
            this.aR.getWindow().getAttributes().type = 2003;
            this.aR = builder.show();
        }
    }

    public void setIsPageStoppedForcefully(boolean z) {
        this.az = z;
    }

    private void a(List<String> list) {
        aw.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MR Cleared whitelisted urls, length: ");
        stringBuilder.append(aw.size());
        c.a("#### PAYU", stringBuilder.toString());
        aw.addAll(list);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("MR Updated whitelisted urls, length: ");
        stringBuilder2.append(aw.size());
        c.a("#### PAYU", stringBuilder2.toString());
    }

    @JavascriptInterface
    public void setSnoozeConfig(String str) {
        this.at = this.N.storeSnoozeConfigInSharedPref(this.f.getApplicationContext(), str);
    }

    @JavascriptInterface
    public void dismissPayULoader() {
        if (this.f != null && !this.f.isFinishing() && this.w != null) {
            this.w.dismiss();
            this.w.cancel();
            if (!this.aO) {
                this.forwardJourneyForChromeLoaderIsComplete = true;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Setting forwardJourneyForChromeLoaderIsComplete = ");
                stringBuilder.append(this.forwardJourneyForChromeLoaderIsComplete);
                c.a("stag", stringBuilder.toString());
                startSlowUserWarningTimer();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void startSlowUserWarningTimer() {
        c.a("sTag", "Attempting to start slowUserCountDownTimer");
        if (this.slowUserCountDownTimer == null) {
            c.a("sTag", "Starting slowUserCountDownTimer");
        }
    }

    /* Access modifiers changed, original: protected */
    public void dismissSlowUserWarningTimer() {
        if (this.slowUserCountDownTimer != null) {
            c.a("sTag", "Shutting down slowUserCountDownTimer");
            this.slowUserCountDownTimer.cancel();
        }
    }

    public void reloadWVUsingJS() {
        this.s.loadUrl("javascript:window.location.reload(true)");
    }

    public void reloadWVNative() {
        this.s.reload();
    }

    public void reloadWVUsingJSFromCache() {
        this.s.loadUrl("javascript:window.location.reload()");
    }

    private void c(View view) {
        if (view.getId() == e.button_retry_transaction) {
            this.snoozeCount++;
            a("snooze_interaction_time", "-1");
            a("snooze_window_action", "snooze_retry_click");
        } else if (view.getId() == e.button_retry_anyway) {
            this.snoozeCount++;
            a("snooze_txn_paused_user_interaction_time", "-1");
            a("snooze_txn_paused_window_action", "retry_anyway_click");
        }
        setTransactionStatusReceived(false);
        if (CBUtil.isNetworkAvailable(this.f.getApplicationContext())) {
            if (this.s.getUrl() == null || this.s.getUrl().contentEquals("https://secure.payu.in/_payment") || this.s.getUrl().contentEquals(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS) || !isUrlWhiteListed(this.s.getUrl())) {
                this.N.clearCookie();
                if ((this.customBrowserConfig.getPostURL() != null && (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment"))) || !(!this.isS2SHtmlSupport || TextUtils.isEmpty(this.surePayS2Surl) || TextUtils.isEmpty(this.surePayS2SPayUId))) {
                    CBUtil cBUtil = this.N;
                    markPreviousTxnAsUserCanceled(CBUtil.getLogMessage(this.f.getApplicationContext(), "sure_pay_cancelled", this.customBrowserConfig.getTransactionID(), "", keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
                }
                if (this.customBrowserConfig.getPostURL() != null && this.customBrowserConfig.getPayuPostData() != null && this.surePayS2Surl == null) {
                    reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
                } else if (this.surePayS2Surl != null) {
                    reloadWebView(this.surePayS2Surl, null);
                }
            } else {
                reloadWebView();
            }
            dismissSnoozeWindow();
            this.slowUserCountDownTimer = null;
            if (view.getId() == e.button_retry_anyway) {
                killSnoozeService();
                ((NotificationManager) this.f.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).cancel(CBConstant.SNOOZE_NOTIFICATION_ID);
                return;
            }
            return;
        }
        Toast.makeText(this.f.getApplicationContext(), CBConstant.MSG_NO_INTERNET, 0).show();
    }

    @JavascriptInterface
    public void spResumedWindowTTL(String str) {
        try {
            this.au = Integer.parseInt(str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(boolean z) {
        this.aQ = z;
    }

    public void addReviewOrder(View view) {
        setReviewOrderButtonProperty((TextView) view.findViewById(e.t_payu_review_option));
    }

    @JavascriptInterface
    public void logPayUAnalytics(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                if (this.mAnalyticsMap.get(str2) == null || !((String) this.mAnalyticsMap.get(str2)).contentEquals(jSONObject.get(str2).toString())) {
                    this.mAnalyticsMap.put(str2, jSONObject.get(str2).toString());
                    a(str2, jSONObject.get(str2).toString());
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void b(String str, String str2) {
        int i = 0;
        while (str2.length() > 0) {
            try {
                String substring;
                i++;
                if (str2.length() > 128) {
                    String substring2 = str2.substring(0, 128);
                    substring = str2.substring(128, str2.length());
                    str2 = substring2;
                } else {
                    substring = "";
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("_");
                stringBuilder.append(i);
                a(stringBuilder.toString(), str2);
                str2 = substring;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (this.aR != null) {
            this.aR.dismiss();
            this.aR = null;
        }
    }

    @JavascriptInterface
    public void isOTPKeyboardNumeric(boolean z) {
        this.aS = z;
    }
}

package com.payu.custombrowser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog.Builder;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.d.d;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.f;
import com.payu.custombrowser.d.h;
import com.payu.custombrowser.services.SnoozeService;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.SnoozeConfigMap;
import com.payu.custombrowser.util.c;
import com.payu.custombrowser.util.g;
import com.payu.custombrowser.widgets.SnoozeLoaderView;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class b extends a implements com.payu.custombrowser.b.b {
    private static boolean a = false;
    public static boolean hasToStart = false;
    public static int snoozeImageDownloadTimeout;
    protected static List<String> whiteListedUrls = new ArrayList();
    protected String SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION = "webview_status_action";
    View ab;
    boolean ac;
    Intent ad;
    boolean ae;
    int af;
    boolean ag = true;
    String ah;
    Timer ai;
    Boolean aj = Boolean.valueOf(false);
    boolean ak;
    String al;
    boolean am = true;
    boolean an = false;
    boolean ao;
    boolean ap = false;
    boolean aq = false;
    com.payu.custombrowser.custombar.a ar;
    int[] as;
    SnoozeConfigMap at;
    int au = 0;
    int av = 0;
    private String b = "snooze_broad_cast_message";
    protected boolean isRetryNowPressed = false;
    public boolean isS2SHtmlSupport = false;
    protected boolean isSnoozeBroadCastReceiverRegistered = false;
    protected boolean isSnoozeEnabled = true;
    protected boolean isSnoozeServiceBounded = false;
    protected HashMap<String, String> mAnalyticsMap;
    protected CountDownTimer slowUserCountDownTimer;
    protected AlertDialog slowUserWarningDialog;
    protected BroadcastReceiver snoozeBroadCastReceiver;
    protected int snoozeCount = 0;
    protected int snoozeCountBackwardJourney = 0;
    protected SnoozeService snoozeService;
    protected ServiceConnection snoozeServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.payu.custombrowser.services.SnoozeService.b bVar = (com.payu.custombrowser.services.SnoozeService.b) iBinder;
            b.this.snoozeService = bVar.a();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            b.this.snoozeService = null;
        }
    };
    protected int snoozeUrlLoadingPercentage;
    protected int snoozeUrlLoadingTimeout;
    protected int snoozeVisibleCountBackwdJourney;
    protected int snoozeVisibleCountFwdJourney;

    public class a extends com.payu.custombrowser.a.a {
        public a() {
            super();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            c.a("sTag", "onTouch of PayUCBLifeCycleCalled");
            b.this.p();
            return super.onTouch(view, motionEvent);
        }
    }

    public abstract void a(String str);

    public abstract void b();

    public abstract void dismissSlowUserWarningTimer();

    public abstract void onPageStarted();

    public abstract void reloadWebView();

    public abstract void reloadWebView(String str);

    public abstract void reloadWebView(String str, String str2);

    public abstract void startSlowUserWarningTimer();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.N.resetPayuID();
        this.surePayS2SPayUId = null;
        this.isSnoozeEnabled = this.N.getBooleanSharedPreferenceDefaultTrue(CBConstant.SNOOZE_ENABLED, getActivity().getApplicationContext());
        a = false;
        this.at = this.N.convertToSnoozeConfigMap(g.a(this.f, CBConstant.SNOOZE_SHARED_PREF));
        this.as = this.at.getPercentageAndTimeout(CBConstant.DEFAULT_PAYMENT_URLS);
        this.snoozeUrlLoadingPercentage = this.as[0];
        this.snoozeUrlLoadingTimeout = this.as[1];
        this.av = this.N.getSurePayDisableStatus(this.at, CBConstant.DEFAULT_PAYMENT_URLS);
        whiteListedUrls = CBUtil.processAndAddWhiteListedUrls(g.b(this.f, CBConstant.SP_RETRY_FILE_NAME, CBConstant.SP_RETRY_WHITELISTED_URLS, ""));
        snoozeImageDownloadTimeout = g.b(this.f.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, 0);
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        if (this.f.getIntent().getStringExtra(CBConstant.SENDER) != null && this.f.getIntent().getStringExtra(CBConstant.SENDER).contentEquals(CBConstant.SNOOZE_SERVICE)) {
            a = true;
        }
        this.snoozeBroadCastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (context != null && b.this.f != null && !b.this.f.isFinishing()) {
                    if (intent.hasExtra("broadcaststatus")) {
                        Intent intent2 = new Intent(context.getApplicationContext(), CBActivity.class);
                        intent2.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
                        intent2.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
                        intent2.putExtra(CBConstant.PAYU_RESPONSE, intent.getExtras().getString(CBConstant.PAYU_RESPONSE));
                        intent2.setFlags(805306368);
                        context.startActivity(intent2);
                    }
                    if (intent.hasExtra(b.this.b) && b.this.snoozeService != null) {
                        b.this.snoozeService.a(intent.getStringExtra(b.this.b));
                    }
                    if (intent.getBooleanExtra("BROAD_CAST_FROM_SNOOZE_SERVICE", false)) {
                        b.this.a(intent.getStringExtra("event_key"), intent.getStringExtra("event_value"));
                    }
                    if (intent.hasExtra(CBConstant.SNOOZE_SERVICE_STATUS)) {
                        b.this.ac = true;
                        if (CBActivity.b != 2) {
                            int i = CBActivity.b;
                        }
                        b.this.a();
                    }
                    if (!intent.getBooleanExtra(CBConstant.BROADCAST_FROM_SERVICE_UPDATE_UI, false) || !intent.hasExtra(CBConstant.IS_FORWARD_JOURNEY)) {
                        return;
                    }
                    if (intent.getStringExtra(CBConstant.KEY).contentEquals(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED)) {
                        b.this.ac = true;
                        b.this.ad = intent;
                        return;
                    }
                    b.this.ac = false;
                    b.this.a(intent);
                }
            }
        };
        if (this.f.getClass().getSimpleName().equalsIgnoreCase("CBActivity")) {
            cbOnCreate();
        } else {
            this.J = true;
            cbOldOnCreate();
        }
        initAnalytics(Bank.keyAnalytics);
        this.ae = false;
        if (this.f != null) {
            this.N.clearCookie();
            this.surePayS2SPayUId = null;
            this.surePayS2Surl = null;
        }
        if (this.customBrowserConfig != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(this.customBrowserConfig.getEnableSurePay());
            a("snooze_enable_count", stringBuilder.toString());
            a("snooze_mode_set_merchant", this.customBrowserConfig.getSurePayMode() == 1 ? "WARN" : "FAIL");
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.f = (Activity) context;
    }

    private void a() {
        if (this.k != null && this.k.isShowing()) {
            this.k.cancel();
            this.k.dismiss();
        }
        if (this.f != null && !this.f.isFinishing()) {
            View inflate = this.f.getLayoutInflater().inflate(f.cb_layout_snooze, null);
            ((TextView) inflate.findViewById(e.snooze_header_txt)).setText(getString(d.g.cb_snooze_network_error));
            inflate.findViewById(e.text_view_cancel_snooze_window).setVisibility(8);
            ((TextView) inflate.findViewById(e.text_view_snooze_message)).setText(getString(d.g.cb_snooze_network_down_message));
            inflate.findViewById(e.snooze_loader_view).setVisibility(8);
            inflate.findViewById(e.button_snooze_transaction).setVisibility(8);
            inflate.findViewById(e.text_view_retry_message_detail).setVisibility(8);
            inflate.findViewById(e.button_retry_transaction).setVisibility(8);
            inflate.findViewById(e.button_cancel_transaction).setVisibility(8);
            inflate.findViewById(e.t_confirm).setVisibility(8);
            inflate.findViewById(e.t_nconfirm).setVisibility(8);
            Button button = (Button) inflate.findViewById(e.button_go_back_snooze);
            button.setVisibility(0);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    b.this.f.finish();
                }
            });
            this.k = new Builder(this.f, h.cb_snooze_dialog).create();
            this.k.setView(inflate);
            this.k.setCanceledOnTouchOutside(false);
            this.k.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    b.this.f.finish();
                }
            });
            this.k.show();
        }
    }

    public void cbOldOnCreate() {
        this.I = getArguments();
        this.autoApprove = this.I.getBoolean(CBConstant.AUTO_APPROVE, false);
        this.autoSelectOtp = this.I.getBoolean(CBConstant.AUTO_SELECT_OTP, false);
        this.H = this.I.getInt(CBConstant.STORE_ONE_CLICK_HASH, 0);
        this.C = this.I.getBoolean(CBConstant.MERCHANT_SMS_PERMISSION, false);
        if (Bank.c == null || Bank.c.equalsIgnoreCase("")) {
            Bank.c = getArguments().getString(CBConstant.SDK_DETAILS);
        }
        if (Bank.a == null || Bank.a.equalsIgnoreCase("")) {
            Bank.a = getArguments().getString(CBConstant.TXN_ID);
        }
        if (Bank.keyAnalytics == null || Bank.keyAnalytics.equalsIgnoreCase("")) {
            Bank.keyAnalytics = getArguments().getString(CBConstant.MERCHANT_KEY);
        }
    }

    public void cbOnCreate() {
        if (getArguments() != null && getArguments().containsKey(CBConstant.CB_CONFIG)) {
            this.customBrowserConfig = (CustomBrowserConfig) getArguments().getParcelable(CBConstant.CB_CONFIG);
            this.reviewOrderDetailList = getArguments().getParcelableArrayList(CBConstant.ORDER_DETAILS);
            int i = 0;
            boolean z = this.customBrowserConfig != null && this.customBrowserConfig.getMerchantSMSPermission() == 1;
            this.C = z;
            z = this.customBrowserConfig != null && this.customBrowserConfig.getAutoApprove() == 1;
            this.autoApprove = z;
            z = this.customBrowserConfig != null && this.customBrowserConfig.getAutoSelectOTP() == 1;
            this.autoSelectOtp = z;
            if (this.customBrowserConfig != null) {
                i = this.customBrowserConfig.getStoreOneClickHash();
            }
            this.H = i;
            if (this.customBrowserConfig != null) {
                this.customBrowserConfig.getPostURL();
            }
            if (this.customBrowserConfig != null) {
                if (Bank.keyAnalytics == null || Bank.keyAnalytics.trim().equals("")) {
                    if (this.customBrowserConfig.getMerchantKey() == null && this.customBrowserConfig.getMerchantKey().trim().equals("")) {
                        Bank.keyAnalytics = "";
                    } else {
                        Bank.keyAnalytics = this.customBrowserConfig.getMerchantKey();
                    }
                }
                if (Bank.a == null || Bank.a.trim().equals("")) {
                    if (this.customBrowserConfig.getTransactionID() == null || this.customBrowserConfig.getTransactionID().trim().equals("")) {
                        Bank.a = "123";
                    } else {
                        Bank.a = this.customBrowserConfig.getTransactionID();
                    }
                }
                if (Bank.c == null || Bank.c.trim().equals("")) {
                    if (this.customBrowserConfig.getSdkVersionName() == null || this.customBrowserConfig.getSdkVersionName().trim().equals("")) {
                        Bank.c = "";
                    } else {
                        Bank.c = this.customBrowserConfig.getSdkVersionName();
                    }
                }
                if (!TextUtils.isEmpty(this.customBrowserConfig.getSurepayS2Surl()) || !TextUtils.isEmpty(this.customBrowserConfig.getHtmlData())) {
                    this.isS2SHtmlSupport = true;
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        View view;
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.J) {
            inflate = layoutInflater.inflate(f.bankold, viewGroup, false);
            inflate.bringToFront();
            cbOldFlowOnCreateView();
            view = inflate;
        } else {
            inflate = layoutInflater.inflate(f.bank, viewGroup, false);
            this.M = inflate.findViewById(e.trans_overlay);
            this.s = (WebView) inflate.findViewById(e.webview);
            this.ab = inflate.findViewById(e.cb_blank_overlay);
            view = inflate.findViewById(e.parent);
            this.Z = (TextView) inflate.findViewById(e.t_payu_review_order_cb);
            this.Y = (TextView) inflate.findViewById(e.t_payu_review_order);
            setReviewOrderButtonProperty(this.Z);
            this.X = (RelativeLayout) inflate.findViewById(e.r_payu_review_order);
            cbOnCreateView();
            if (this.customBrowserConfig.getEnableReviewOrder() == 0) {
                if (this.customBrowserConfig.getReviewOrderCustomView() != -1) {
                    a("review_order_type", "review_order_custom");
                } else {
                    a("review_order_type", "review_order_default");
                }
            }
        }
        CBUtil.setVariableReflection(CBConstant.MAGIC_RETRY_PAKAGE, "7.2.2", CBConstant.CB_VERSION);
        this.K = (FrameLayout) inflate.findViewById(e.help_view);
        this.L = inflate.findViewById(e.view);
        this.y = (ProgressBar) inflate.findViewById(e.cb_progressbar);
        n();
        this.viewOnClickListener = new com.payu.custombrowser.a.b();
        o();
        this.mAnalyticsMap = new HashMap();
        view.setOnTouchListener(new a());
        return inflate;
    }

    private void n() {
        this.s.getSettings().setJavaScriptEnabled(true);
        this.s.addJavascriptInterface(this, "PayU");
        this.s.getSettings().setSupportMultipleWindows(true);
        this.s.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                b.this.p();
                if (b.this.M != null) {
                    b.this.M.setVisibility(8);
                }
                if (b.this.z == 2) {
                    b.this.g();
                }
                return false;
            }
        });
        this.s.getSettings().setDomStorageEnabled(true);
        this.s.getSettings().setRenderPriority(RenderPriority.HIGH);
        this.s.getSettings().setCacheMode(2);
        this.s.getSettings().setAppCacheEnabled(false);
    }

    public void cbOldFlowOnCreateView() {
        this.s = (WebView) this.f.findViewById(getArguments().getInt(CBConstant.WEBVIEW));
        if (Bank.b != null && Bank.b.equalsIgnoreCase(CBConstant.NB)) {
            this.s.getSettings().setUseWideViewPort(true);
        } else if (this.customBrowserConfig != null && this.customBrowserConfig.getViewPortWideEnable() == 1) {
            this.s.getSettings().setUseWideViewPort(true);
        }
        this.s.setFocusable(true);
        this.s.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1 && i == 4) {
                    if (b.this.getArguments().getBoolean(CBConstant.BACK_BUTTON, true)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(b.this.f);
                        builder.setCancelable(false);
                        builder.setMessage("Do you really want to cancel the transaction ?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                b.this.postToPaytxn();
                                b.this.a("user_input", "back_button_ok");
                                dialogInterface.dismiss();
                                b.this.onBackApproved();
                                b.this.f.finish();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                b.this.a("user_input", "back_button_cancel");
                                b.this.onBackCancelled();
                                dialogInterface.dismiss();
                            }
                        });
                        b.this.a("user_input", "payu_back_button");
                        b.this.onBackPressed(builder);
                        builder.show();
                        return true;
                    }
                    b.this.a("user_input", "m_back_button");
                    b.this.onBackPressed(null);
                    b.this.f.onBackPressed();
                }
                return false;
            }
        });
        if (Bank.b != null && Bank.b.equalsIgnoreCase(CBConstant.NB)) {
            this.s.getSettings().setUseWideViewPort(true);
        } else if (this.I.getBoolean(CBConstant.VIEWPORTWIDE, false)) {
            this.s.getSettings().setUseWideViewPort(true);
        }
    }

    public void cbOnCreateView() {
        if (Bank.b != null && Bank.b.equalsIgnoreCase(CBConstant.NB)) {
            this.s.getSettings().setUseWideViewPort(true);
        } else if (this.customBrowserConfig != null && this.customBrowserConfig.getViewPortWideEnable() == 1) {
            this.s.getSettings().setUseWideViewPort(true);
        }
        Bank bank = (Bank) this;
        this.s.setWebChromeClient(new PayUWebChromeClient(bank));
        if (this.customBrowserConfig.getEnableSurePay() > 0) {
            this.s.setWebViewClient(new PayUSurePayWebViewClient(bank, Bank.keyAnalytics));
        } else {
            this.s.setWebViewClient(new PayUWebViewClient(bank, Bank.keyAnalytics));
        }
        if (!TextUtils.isEmpty(this.customBrowserConfig.getHtmlData())) {
            a("cb_status", "load_html");
            this.s.loadDataWithBaseURL("https://secure.payu.in/_payment", this.customBrowserConfig.getHtmlData(), "text/html", "UTF-8", null);
        } else if (!TextUtils.isEmpty(this.customBrowserConfig.getSurepayS2Surl())) {
            this.s.loadUrl(this.customBrowserConfig.getSurepayS2Surl());
        } else if (!(this.customBrowserConfig == null || this.customBrowserConfig.getPostURL() == null || this.customBrowserConfig.getPayuPostData() == null)) {
            this.s.postUrl(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData().getBytes());
        }
        if (com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback() != null) {
            com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback().setCBProperties(this.s, bank);
        }
        if (this.customBrowserConfig != null && this.customBrowserConfig.getMagicretry() == 1) {
            if (this.customBrowserConfig.getEnableSurePay() == 0) {
                initMagicRetry();
            }
            if (com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                com.payu.custombrowser.bean.b.SINGLETON.getPayuCustomBrowserCallback().initializeMagicRetry(bank, this.s, this.p);
            }
        }
        this.mAnalyticsMap = new HashMap();
        String webViewVersionFromSP = CBUtil.getWebViewVersionFromSP(getContext());
        if (webViewVersionFromSP.length() > 0 && !webViewVersionFromSP.contentEquals(CBUtil.getWebViewVersion(new WebView(getContext())))) {
            a("web_view_updated_successfully", CBUtil.getWebViewVersion(new WebView(getContext())));
            CBUtil.setWebViewVersionInSP(getContext(), "");
        }
    }

    public void logOnTerminate() {
        try {
            a("last_url", CBUtil.updateLastUrl(this.N.getStringSharedPreference(this.f.getApplicationContext(), "last_url")));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            this.N.deleteSharedPrefKey(this.f.getApplicationContext(), "last_url");
        }
        this.N.deleteSharedPrefKey(this.f.getApplicationContext(), "last_url");
        if (!this.o.contains("CUSTOM_BROWSER")) {
            if (this.o.contains("review_order_custom_browser")) {
                this.m = "review_order_custom_browser";
            } else {
                this.m = "NON_CUSTOM_BROWSER";
            }
            a("cb_status", this.m);
        }
        this.m = "terminate_transaction";
        a("user_input", this.m);
        if (!(this.w == null || this.w.isShowing())) {
            this.w.dismiss();
        }
        if (this.g != null) {
            unregisterBroadcast(this.g);
            this.g = null;
        }
        if (this.listOfTxtFld != null && this.listOfTxtFld.length() > 1 && !this.isOTPFilled) {
            a("bank_page_otp_fields", this.listOfTxtFld);
            a("bank_page_host_name", this.hostName);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.N.cancelTimer(this.timerProgress);
        if (this.k != null && this.k.isShowing()) {
            this.k.dismiss();
        }
        if (this.w != null) {
            this.w.dismiss();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.mResetCounter);
        this.N.cancelTimer(this.timerProgress);
        this.N.cancelTimer(this.ai);
        if (this.slowUserCountDownTimer != null) {
            this.slowUserCountDownTimer.cancel();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.snoozeVisibleCountBackwdJourney + this.snoozeVisibleCountFwdJourney);
        a("snooze_count", stringBuilder.toString());
        com.payu.custombrowser.bean.b.SINGLETON.setPayuCustomBrowserCallback(null);
        com.payu.custombrowser.bean.b.SINGLETON.setSamsungPayWrapper(null);
        if (this.k != null && this.k.isShowing()) {
            this.k.dismiss();
        }
        if (!(this.snoozeBroadCastReceiver == null || !this.isSnoozeBroadCastReceiverRegistered || a)) {
            LocalBroadcastManager.getInstance(this.f.getApplicationContext()).unregisterReceiver(this.snoozeBroadCastReceiver);
        }
        if (this.snoozeServiceConnection != null && this.isSnoozeServiceBounded) {
            this.f.unbindService(this.snoozeServiceConnection);
        }
        if (this.snoozeService != null && a) {
            this.snoozeService.a();
        }
        if (this.O != null) {
            this.ar.b(this.O.findViewById(e.progress));
        }
        if (this.P != null) {
            this.ar.b(this.P.findViewById(e.progress));
        }
        if (this.Q != null) {
            this.N.cancelTimer(this.Q.a());
        }
        if (this.l != null) {
            this.N.cancelTimer(this.l.a());
        }
        this.N.cancelTimer(this.ai);
        if (this.R != null) {
            this.R.cancel();
        }
        logOnTerminate();
        Bank.c = null;
        Bank.keyAnalytics = null;
        Bank.a = null;
        Bank.b = null;
        if (this.s != null) {
            this.s.destroy();
        }
        this.N.resetPayuID();
        this.surePayS2SPayUId = null;
    }

    public void onResume() {
        super.onResume();
        if (this.ac) {
            this.ac = false;
            cancelTransactionNotification();
            if (this.ad != null) {
                if (this.backwardJourneyStarted) {
                    try {
                        if (Integer.parseInt(new JSONObject(this.ad.getStringExtra("value")).get(getString(d.g.cb_snooze_verify_api_status)).toString()) == 1) {
                            a("transaction_verified_dialog_recent_app", "-1");
                        } else {
                            a("transaction_not_verified_dialog_recent_app", "-1");
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        a("transaction_not_verified_dialog_recent_app", "-1");
                    }
                } else {
                    a("internet_restored_dialog_recent_app", "-1");
                }
                a(this.ad);
                return;
            }
            a("internet_not_restored_dialog_recent_app", "-1");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void m() {
        if (this.g == null) {
            this.g = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    try {
                        if (b.this.h != null) {
                            Bundle extras = intent.getExtras();
                            if (!(b.this.getActivity() == null || b.this.getActivity().isFinishing())) {
                                Bundle extras2 = intent.getExtras();
                                if (extras2 != null) {
                                    String str = "";
                                    String str2 = null;
                                    Object[] objArr = (Object[]) extras2.get("pdus");
                                    if (objArr != null) {
                                        SmsMessage[] smsMessageArr = new SmsMessage[objArr.length];
                                        String str3 = null;
                                        str2 = str;
                                        for (int i = 0; i < smsMessageArr.length; i++) {
                                            if (VERSION.SDK_INT >= 23) {
                                                smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i], extras.getString("format"));
                                            } else {
                                                smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
                                            }
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append(str2);
                                            stringBuilder.append(smsMessageArr[i].getMessageBody());
                                            str2 = stringBuilder.toString();
                                            str3 = smsMessageArr[i].getDisplayOriginatingAddress();
                                        }
                                        str = str2;
                                        str2 = str3;
                                    }
                                    b.this.ah = CBUtil.filterSMS(b.this.h, str, b.this.f.getApplicationContext());
                                    if (b.this.ah == null || b.this.ah.length() < 6 || b.this.ah.length() > 8) {
                                        if (b.this.T) {
                                            b.this.S = b.this.b(str2, str);
                                        }
                                        if (b.this.S) {
                                            b.this.a(com.payu.custombrowser.util.a.b, com.payu.custombrowser.util.a.g);
                                        }
                                    } else {
                                        b.this.fillOTPOnBankPage();
                                        b.this.otp = b.this.ah;
                                        b.this.backupOfOTP = b.this.otp;
                                        b.this.otpTriggered = true;
                                        try {
                                            b.this.isOTPFilled = false;
                                            if (b.this.catchAllJSEnabled && !TextUtils.isEmpty(b.this.otp)) {
                                                JSONObject jSONObject = new JSONObject();
                                                jSONObject.put("otp", b.this.otp);
                                                jSONObject.put("isAutoFillOTP", true);
                                                WebView webView = b.this.s;
                                                StringBuilder stringBuilder2 = new StringBuilder();
                                                stringBuilder2.append("javascript:");
                                                stringBuilder2.append(b.this.h.getString(b.this.getString(d.g.cb_fill_otp)));
                                                stringBuilder2.append("(");
                                                stringBuilder2.append(jSONObject);
                                                stringBuilder2.append(")");
                                                webView.loadUrl(stringBuilder2.toString());
                                            }
                                        } catch (JSONException e) {
                                            ThrowableExtension.printStackTrace(e);
                                        }
                                        b.this.fillOTP(this);
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(9999999);
            intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerBroadcast(this.g, intentFilter);
        }
    }

    public void fillOTPOnBankPage() {
        try {
            if (this.i != null && !TextUtils.isEmpty(this.ah) && this.i.has(getString(d.g.cb_fill_otp))) {
                WebView webView = this.s;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(this.i.getString(getString(d.g.cb_fill_otp)));
                stringBuilder.append("(\"");
                stringBuilder.append(this.ah);
                stringBuilder.append("\",\"url\")");
                webView.loadUrl(stringBuilder.toString());
                this.ah = null;
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void registerSMSBroadcast() {
        if (this.g == null) {
            m();
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(9999999);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerBroadcast(this.g, intentFilter);
    }

    /* Access modifiers changed, original: 0000 */
    public String d(String str) {
        for (String split : this.customBrowserConfig.getPayuPostData().split("&")) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && split2[0].equalsIgnoreCase(str)) {
                return split2[1];
            }
        }
        return null;
    }

    private boolean b(String str, String str2) {
        str2 = str2.toLowerCase();
        boolean z = false;
        int i = str.contains(this.D) ? 1 : 0;
        if (str2.toLowerCase().contains(d("amount").replace(",", ""))) {
            i++;
        }
        boolean z2 = i == 2;
        if (i == 0) {
            z2 = false;
        }
        if (i != 0) {
            if (str2.contains("made") && str2.contains(ProductAction.ACTION_PURCHASE)) {
                return true;
            }
            if (str2.contains("account") && str2.contains("debited")) {
                return true;
            }
            if (str2.contains("ac") && str2.contains("debited")) {
                return true;
            }
            if (str2.contains("tranx") && str2.contains("made")) {
                return true;
            }
            if ((str2.contains("transaction") && str2.contains("made")) || str2.contains("spent") || str2.contains("Thank you using card for")) {
                return true;
            }
            if (!str2.contains("charge") || !str2.contains("initiated")) {
                return z2;
            }
            if (str2.contains("charge") && str2.contains("initiated")) {
                z = true;
            }
        }
        return z;
    }

    public void fillOTP(BroadcastReceiver broadcastReceiver) {
        if (getActivity().findViewById(e.otp_sms) != null) {
            final TextView textView = (TextView) getActivity().findViewById(e.otp_sms);
            if (this.ag && this.ah != null) {
                this.N.cancelTimer(this.ai);
                String str = this.m;
                boolean z = true;
                int hashCode = str.hashCode();
                if (hashCode != -557081102) {
                    if (hashCode != 674270068) {
                        if (hashCode == 2084916017 && str.equals("regenerate_click")) {
                            z = true;
                        }
                    } else if (str.equals("otp_click")) {
                        z = true;
                    }
                } else if (str.equals("payment_initiated")) {
                    z = false;
                }
                switch (z) {
                    case false:
                        this.m = "received_otp_direct";
                        break;
                    case true:
                        this.m = "received_otp_selected";
                        break;
                    case true:
                        this.m = "received_otp_regenerate";
                        break;
                    default:
                        this.m = "otp_web";
                        break;
                }
                a("otp_received", this.m);
                textView.setText(this.ah);
                this.ah = null;
                this.ar.c(getActivity().findViewById(e.progress));
                Button button = (Button) getActivity().findViewById(e.approve);
                button.setClickable(true);
                CBUtil.setAlpha(1.0f, button);
                button.setVisibility(0);
                this.f.findViewById(e.timer).setVisibility(8);
                this.f.findViewById(e.retry_text).setVisibility(8);
                this.f.findViewById(e.regenerate_layout).setVisibility(8);
                this.f.findViewById(e.waiting).setVisibility(8);
                this.f.findViewById(e.otp_recieved).setVisibility(0);
                textView.setVisibility(0);
                if (this.autoApprove) {
                    button.performClick();
                    this.m = CBConstant.AUTO_APPROVE;
                    a("user_input", this.m);
                }
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            b.this.ah = null;
                            b.this.m = "approved_otp";
                            b.this.a("user_input", b.this.m);
                            b.this.a("Approve_btn_clicked_time", "-1");
                            b.this.m();
                            b.this.ak = false;
                            b.this.aj = Boolean.valueOf(true);
                            b.this.onHelpUnavailable();
                            b.this.f();
                            b.this.z = 1;
                            WebView webView = b.this.s;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:");
                            stringBuilder.append(b.this.i.getString(b.this.getString(d.g.cb_process_otp)));
                            stringBuilder.append("(\"");
                            stringBuilder.append(textView.getText().toString());
                            stringBuilder.append("\")");
                            webView.loadUrl(stringBuilder.toString());
                            textView.setText("");
                            b.this.c();
                        } catch (JSONException e) {
                            ThrowableExtension.printStackTrace(e);
                        } catch (Exception e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                    }
                });
            }
        }
    }

    private void o() {
        m();
        this.m = "payment_initiated";
        a("user_input", this.m);
        this.W.execute(new Runnable() {
            public void run() {
                StringBuilder stringBuilder;
                String stringBuilder2;
                String str = "initialize";
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(CBConstant.PRODUCTION_URL);
                stringBuilder3.append(str);
                stringBuilder3.append(".js");
                HttpsURLConnection httpsConn = CBUtil.getHttpsConn(stringBuilder3.toString());
                if (httpsConn != null) {
                    try {
                        if (httpsConn.getResponseCode() == 200) {
                            b.this.N.writeFileOutputStream(httpsConn.getInputStream(), b.this.f, str, 0);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        try {
                            if (b.this.f != null && !b.this.f.isFinishing()) {
                                b.this.h = new JSONObject(CBUtil.decodeContents(b.this.f.openFileInput(str)));
                                b.this.j();
                                if (!b.this.J) {
                                    b.this.checkStatusFromJS("", 1);
                                    b.this.checkStatusFromJS("", 2);
                                }
                                if (b.this.h.has("snooze_config")) {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append(b.this.h.get("snooze_config"));
                                    stringBuilder.append("('");
                                    stringBuilder.append(Bank.keyAnalytics);
                                    stringBuilder.append("')");
                                    str = stringBuilder.toString();
                                } else {
                                    str = "";
                                }
                                b.snoozeImageDownloadTimeout = Integer.parseInt(b.this.h.has("snooze_image_download_time") ? b.this.h.get("snooze_image_download_time").toString() : "0");
                                g.a(b.this.f.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, b.snoozeImageDownloadTimeout);
                                if (b.this.h.has(b.this.getString(d.g.sp_internet_restored_ttl))) {
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(b.this.h.get(b.this.getString(d.g.sp_internet_restored_ttl)));
                                    stringBuilder3.append("('");
                                    stringBuilder3.append(Bank.keyAnalytics);
                                    stringBuilder3.append("')");
                                    stringBuilder2 = stringBuilder3.toString();
                                } else {
                                    stringBuilder2 = "";
                                }
                                b.this.f.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = b.this.s;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("javascript:");
                                        stringBuilder.append(str);
                                        webView.loadUrl(stringBuilder.toString());
                                    }
                                });
                                b.this.f.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = b.this.s;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("javascript:");
                                        stringBuilder.append(stringBuilder2);
                                        webView.loadUrl(stringBuilder.toString());
                                    }
                                });
                                if (b.this.h.has(CBConstant.SUREPAY_S2S)) {
                                    b.this.N.setStringSharedPreference(b.this.f, CBConstant.SUREPAY_S2S, b.this.h.getString(CBConstant.SUREPAY_S2S));
                                }
                                if (b.this.ap && b.this.f != null) {
                                    b.this.f.runOnUiThread(new Runnable() {
                                        public void run() {
                                            b.this.onPageStarted();
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (FileNotFoundException | JSONException e2) {
                            b.this.h();
                            ThrowableExtension.printStackTrace(e2);
                            return;
                        } catch (Exception e3) {
                            b.this.h();
                            ThrowableExtension.printStackTrace(e3);
                            return;
                        }
                    } catch (Throwable th) {
                        try {
                            if (!(b.this.f == null || b.this.f.isFinishing())) {
                                String stringBuilder4;
                                b.this.h = new JSONObject(CBUtil.decodeContents(b.this.f.openFileInput(str)));
                                b.this.j();
                                if (!b.this.J) {
                                    b.this.checkStatusFromJS("", 1);
                                    b.this.checkStatusFromJS("", 2);
                                }
                                if (b.this.h.has("snooze_config")) {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append(b.this.h.get("snooze_config"));
                                    stringBuilder.append("('");
                                    stringBuilder.append(Bank.keyAnalytics);
                                    stringBuilder.append("')");
                                    str = stringBuilder.toString();
                                } else {
                                    str = "";
                                }
                                b.snoozeImageDownloadTimeout = Integer.parseInt(b.this.h.has("snooze_image_download_time") ? b.this.h.get("snooze_image_download_time").toString() : "0");
                                g.a(b.this.f.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, b.snoozeImageDownloadTimeout);
                                if (b.this.h.has(b.this.getString(d.g.sp_internet_restored_ttl))) {
                                    StringBuilder stringBuilder5 = new StringBuilder();
                                    stringBuilder5.append(b.this.h.get(b.this.getString(d.g.sp_internet_restored_ttl)));
                                    stringBuilder5.append("('");
                                    stringBuilder5.append(Bank.keyAnalytics);
                                    stringBuilder5.append("')");
                                    stringBuilder4 = stringBuilder5.toString();
                                } else {
                                    stringBuilder4 = "";
                                }
                                b.this.f.runOnUiThread(/* anonymous class already generated */);
                                b.this.f.runOnUiThread(/* anonymous class already generated */);
                                if (b.this.h.has(CBConstant.SUREPAY_S2S)) {
                                    b.this.N.setStringSharedPreference(b.this.f, CBConstant.SUREPAY_S2S, b.this.h.getString(CBConstant.SUREPAY_S2S));
                                }
                                if (b.this.ap && b.this.f != null) {
                                    b.this.f.runOnUiThread(/* anonymous class already generated */);
                                }
                            }
                        } catch (FileNotFoundException | JSONException e22) {
                            b.this.h();
                            ThrowableExtension.printStackTrace(e22);
                        } catch (Exception e32) {
                            b.this.h();
                            ThrowableExtension.printStackTrace(e32);
                        }
                    }
                }
                if (b.this.f != null && !b.this.f.isFinishing()) {
                    b.this.h = new JSONObject(CBUtil.decodeContents(b.this.f.openFileInput(str)));
                    b.this.j();
                    if (!b.this.J) {
                        b.this.checkStatusFromJS("", 1);
                        b.this.checkStatusFromJS("", 2);
                    }
                    if (b.this.h.has("snooze_config")) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(b.this.h.get("snooze_config"));
                        stringBuilder.append("('");
                        stringBuilder.append(Bank.keyAnalytics);
                        stringBuilder.append("')");
                        str = stringBuilder.toString();
                    } else {
                        str = "";
                    }
                    b.snoozeImageDownloadTimeout = Integer.parseInt(b.this.h.has("snooze_image_download_time") ? b.this.h.get("snooze_image_download_time").toString() : "0");
                    g.a(b.this.f.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, b.snoozeImageDownloadTimeout);
                    if (b.this.h.has(b.this.getString(d.g.sp_internet_restored_ttl))) {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(b.this.h.get(b.this.getString(d.g.sp_internet_restored_ttl)));
                        stringBuilder3.append("('");
                        stringBuilder3.append(Bank.keyAnalytics);
                        stringBuilder3.append("')");
                        stringBuilder2 = stringBuilder3.toString();
                    } else {
                        stringBuilder2 = "";
                    }
                    b.this.f.runOnUiThread(/* anonymous class already generated */);
                    b.this.f.runOnUiThread(/* anonymous class already generated */);
                    if (b.this.h.has(CBConstant.SUREPAY_S2S)) {
                        b.this.N.setStringSharedPreference(b.this.f, CBConstant.SUREPAY_S2S, b.this.h.getString(CBConstant.SUREPAY_S2S));
                    }
                    if (b.this.ap && b.this.f != null) {
                        b.this.f.runOnUiThread(/* anonymous class already generated */);
                    }
                }
            }
        });
    }

    public void updateHeight(View view) {
        if (this.v == 0) {
            e();
            f();
        }
        b(view);
    }

    public void updateLoaderHeight() {
        if (this.af == 0) {
            this.s.measure(-1, -1);
            this.af = (int) (((double) this.s.getMeasuredHeight()) * 0.35d);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            this.ao = false;
            if (this.aq) {
                try {
                    WebView webView = this.s;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("javascript:");
                    stringBuilder.append(this.i.getString(getString(d.g.cb_otp)));
                    webView.loadUrl(stringBuilder.toString());
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            if (ContextCompat.checkSelfPermission(this.f, "android.permission.RECEIVE_SMS") == 0) {
                this.am = true;
                this.ah = null;
                m();
                a(this.al);
                return;
            }
            this.am = false;
            a(this.al);
        }
    }

    /* Access modifiers changed, original: protected */
    public void showSlowUserWarning() {
        if (this.f != null && !this.f.isFinishing() && !this.n) {
            View inflate = this.f.getLayoutInflater().inflate(f.cb_layout_snooze_slow_user, null);
            ((TextView) inflate.findViewById(e.snooze_header_txt)).setText(d.g.cb_snooze_slow_user_warning_header);
            TextView textView = (TextView) inflate.findViewById(e.text_view_cancel_snooze_window);
            ImageView imageView = (ImageView) inflate.findViewById(e.snooze_status_icon);
            imageView.setVisibility(0);
            imageView.setImageDrawable(getCbDrawable(this.f.getApplicationContext(), d.hourglass));
            if (this.slowUserWarningDialog == null) {
                this.slowUserWarningDialog = new AlertDialog.Builder(this.f).create();
                this.slowUserWarningDialog.setView(inflate);
                this.slowUserWarningDialog.setCanceledOnTouchOutside(true);
                this.slowUserWarningDialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });
                this.slowUserWarningDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == 4 && keyEvent.getAction() == 0) {
                            b.this.slowUserWarningDialog.dismiss();
                        }
                        return true;
                    }
                });
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        b.this.slowUserWarningDialog.dismiss();
                    }
                });
            }
            this.slowUserWarningDialog.show();
            CBActivity cBActivity = (CBActivity) this.f;
            if (CBActivity.b == 1) {
                showSlowUserWarningNotification();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void dismissSlowUserWarning() {
        if (this.slowUserWarningDialog != null) {
            this.slowUserWarningDialog.dismiss();
        }
    }

    private void p() {
        if (this.forwardJourneyForChromeLoaderIsComplete) {
            this.firstTouch = true;
            dismissSlowUserWarningTimer();
        }
    }

    /* Access modifiers changed, original: protected */
    public void showSlowUserWarningNotification() {
        if (this.f != null && !this.f.isFinishing()) {
            Intent intent = new Intent();
        }
    }

    /* Access modifiers changed, original: protected */
    public void showCbBlankOverlay(int i) {
        if (this.ab != null) {
            this.ab.setVisibility(i);
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateSnoozeDialogWithMessage(String str, String str2) {
        if (this.k != null && this.k.isShowing()) {
            this.k.cancel();
            this.k.dismiss();
        }
        if (this.snoozeService != null) {
            this.snoozeService.a();
        }
        b();
        if (this.f != null && !this.f.isFinishing()) {
            View inflate = this.f.getLayoutInflater().inflate(f.cb_layout_snooze, null);
            ((TextView) inflate.findViewById(e.snooze_header_txt)).setText(str);
            inflate.findViewById(e.text_view_cancel_snooze_window).setVisibility(8);
            ((TextView) inflate.findViewById(e.text_view_snooze_message)).setText(str2);
            SnoozeLoaderView snoozeLoaderView = (SnoozeLoaderView) inflate.findViewById(e.snooze_loader_view);
            snoozeLoaderView.setVisibility(0);
            snoozeLoaderView.a();
            inflate.findViewById(e.button_snooze_transaction).setVisibility(8);
            inflate.findViewById(e.text_view_retry_message_detail).setVisibility(8);
            inflate.findViewById(e.button_retry_transaction).setVisibility(8);
            inflate.findViewById(e.button_cancel_transaction).setVisibility(8);
            inflate.findViewById(e.t_confirm).setVisibility(8);
            inflate.findViewById(e.t_nconfirm).setVisibility(8);
            inflate.findViewById(e.button_go_back_snooze).setVisibility(8);
            this.k = new Builder(this.f).create();
            this.k.setView(inflate);
            this.k.setCancelable(false);
            this.k.setCanceledOnTouchOutside(false);
            hideReviewOrderHorizontalBar();
            this.k.show();
        }
    }

    private void a(final Intent intent) {
        int internetRestoredWindowTTL = this.customBrowserConfig != null ? this.customBrowserConfig.getInternetRestoredWindowTTL() : 5000;
        if (this.au != 0) {
            internetRestoredWindowTTL = this.au;
        }
        if (this.backwardJourneyStarted) {
            try {
                if (this.N.getValueOfJSONKey(intent.getStringExtra("value"), getString(d.g.cb_snooze_verify_api_status)).contentEquals("1")) {
                    if (!(this.f == null || this.f.isFinishing())) {
                        updateSnoozeDialogWithMessage(this.f.getResources().getString(d.g.cb_transaction_verified), this.f.getResources().getString(d.g.redirect_back_to_merchant));
                    }
                } else if (!(this.f == null || this.f.isFinishing())) {
                    updateSnoozeDialogWithMessage(this.f.getResources().getString(d.g.cb_transaction_state_unknown), this.f.getResources().getString(d.g.status_unknown_redirect_to_merchant));
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                if (!(this.f == null || this.f.isFinishing())) {
                    updateSnoozeDialogWithMessage(this.f.getResources().getString(d.g.cb_transaction_state_unknown), this.f.getResources().getString(d.g.status_unknown_redirect_to_merchant));
                }
            }
        } else if (!(this.f == null || this.f.isFinishing())) {
            updateSnoozeDialogWithMessage(this.f.getResources().getString(d.g.internet_restored), this.f.getResources().getString(d.g.resuming_your_transaction));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (b.this.k != null) {
                    b.this.k.dismiss();
                    b.this.showReviewOrderHorizontalBar();
                }
                if (b.this.backwardJourneyStarted) {
                    if (b.this.snoozeService != null) {
                        b.this.snoozeService.a();
                    }
                    b.this.showTransactionStatusDialog(intent.getStringExtra("value"), false);
                    return;
                }
                if (b.this.isRetryNowPressed) {
                    b.this.isRetryNowPressed = false;
                } else {
                    b bVar = b.this;
                    bVar.snoozeCount++;
                }
                b.this.resumeTransaction(intent);
            }
        }, (long) internetRestoredWindowTTL);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x012b A:{Catch:{ Exception -> 0x01fb }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0099 A:{Catch:{ Exception -> 0x01fb }} */
    public void showTransactionStatusDialog(java.lang.String r10, boolean r11) {
        /*
        r9 = this;
        r0 = 1;
        r9.setTransactionStatusReceived(r0);	 Catch:{ Exception -> 0x01fb }
        r0 = r9.N;	 Catch:{ Exception -> 0x01fb }
        r1 = com.payu.custombrowser.d.g.cb_snooze_verify_api_status;	 Catch:{ Exception -> 0x01fb }
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x01fb }
        r0 = r0.getValueOfJSONKey(r10, r1);	 Catch:{ Exception -> 0x01fb }
        r1 = r9.f;	 Catch:{ Exception -> 0x01fb }
        r1 = r1.getLayoutInflater();	 Catch:{ Exception -> 0x01fb }
        r2 = com.payu.custombrowser.d.f.cb_layout_snooze;	 Catch:{ Exception -> 0x01fb }
        r3 = 0;
        r1 = r1.inflate(r2, r3);	 Catch:{ Exception -> 0x01fb }
        r2 = new android.support.v7.app.AlertDialog$Builder;	 Catch:{ Exception -> 0x01fb }
        r3 = r9.f;	 Catch:{ Exception -> 0x01fb }
        r2.<init>(r3);	 Catch:{ Exception -> 0x01fb }
        r2.setView(r1);	 Catch:{ Exception -> 0x01fb }
        r2 = r2.create();	 Catch:{ Exception -> 0x01fb }
        r9.k = r2;	 Catch:{ Exception -> 0x01fb }
        r2 = "1";
        r0 = r0.contentEquals(r2);	 Catch:{ Exception -> 0x01fb }
        r2 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r4 = 0;
        r5 = 8;
        if (r0 == 0) goto L_0x0149;
    L_0x003a:
        r0 = r9.s;	 Catch:{ Exception -> 0x01fb }
        r0 = r0.getUrl();	 Catch:{ Exception -> 0x01fb }
        if (r0 == 0) goto L_0x0049;
    L_0x0042:
        r0 = r9.s;	 Catch:{ Exception -> 0x01fb }
        r0 = r0.getUrl();	 Catch:{ Exception -> 0x01fb }
        goto L_0x004b;
    L_0x0049:
        r0 = "";
    L_0x004b:
        r0 = com.payu.custombrowser.Bank.isUrlWhiteListed(r0);	 Catch:{ Exception -> 0x01fb }
        if (r0 == 0) goto L_0x0068;
    L_0x0051:
        r0 = 19;
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01fb }
        if (r0 == r6) goto L_0x0068;
    L_0x0057:
        r10 = "snooze_transaction_status_update";
        r11 = "data_repost";
        r9.a(r10, r11);	 Catch:{ Exception -> 0x01fb }
        r10 = "";
        r9.a(r5, r10);	 Catch:{ Exception -> 0x01fb }
        r9.reloadWebView();	 Catch:{ Exception -> 0x01fb }
        goto L_0x01ff;
    L_0x0068:
        r0 = "snooze_transaction_status_update";
        r6 = "post_to_surl";
        r9.a(r0, r6);	 Catch:{ Exception -> 0x01fb }
        r0 = "";
        r6 = r9.N;	 Catch:{ JSONException -> 0x0090 }
        r7 = "response";
        r10 = r6.getValueOfJSONKey(r10, r7);	 Catch:{ JSONException -> 0x0090 }
        r0 = new com.payu.custombrowser.util.CBUtil;	 Catch:{ JSONException -> 0x008e }
        r0.<init>();	 Catch:{ JSONException -> 0x008e }
        r6 = r9.customBrowserConfig;	 Catch:{ JSONException -> 0x008e }
        r6 = r6.getPayuPostData();	 Catch:{ JSONException -> 0x008e }
        r7 = "surl";
        r0 = r0.getDataFromPostData(r6, r7);	 Catch:{ JSONException -> 0x008e }
        r9.postDataToSurl(r10, r0);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0097;
    L_0x008e:
        r0 = move-exception;
        goto L_0x0094;
    L_0x0090:
        r10 = move-exception;
        r8 = r0;
        r0 = r10;
        r10 = r8;
    L_0x0094:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ Exception -> 0x01fb }
    L_0x0097:
        if (r11 == 0) goto L_0x012b;
    L_0x0099:
        r11 = com.payu.custombrowser.d.e.snooze_status_icon;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r4);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.snooze_header_txt;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11 = (android.widget.TextView) r11;	 Catch:{ Exception -> 0x01fb }
        r0 = com.payu.custombrowser.d.g.cb_transaction_sucess;	 Catch:{ Exception -> 0x01fb }
        r11.setText(r0);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.text_view_cancel_snooze_window;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.text_view_snooze_message;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11 = (android.widget.TextView) r11;	 Catch:{ Exception -> 0x01fb }
        r0 = com.payu.custombrowser.d.g.cb_transaction_success_msg;	 Catch:{ Exception -> 0x01fb }
        r0 = r9.getString(r0);	 Catch:{ Exception -> 0x01fb }
        r11.setText(r0);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.snooze_loader_view;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.button_snooze_transaction;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.text_view_retry_message_detail;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.button_retry_transaction;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.button_cancel_transaction;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.t_confirm;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.e.t_nconfirm;	 Catch:{ Exception -> 0x01fb }
        r11 = r1.findViewById(r11);	 Catch:{ Exception -> 0x01fb }
        r11.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r11 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r0 = new com.payu.custombrowser.b$16;	 Catch:{ Exception -> 0x01fb }
        r0.<init>(r10);	 Catch:{ Exception -> 0x01fb }
        r11.setOnDismissListener(r0);	 Catch:{ Exception -> 0x01fb }
        r10 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r10.setCanceledOnTouchOutside(r4);	 Catch:{ Exception -> 0x01fb }
        r10 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r10.show();	 Catch:{ Exception -> 0x01fb }
        r10 = new android.os.Handler;	 Catch:{ Exception -> 0x01fb }
        r10.<init>();	 Catch:{ Exception -> 0x01fb }
        r11 = new com.payu.custombrowser.b$17;	 Catch:{ Exception -> 0x01fb }
        r11.<init>();	 Catch:{ Exception -> 0x01fb }
        r10.postDelayed(r11, r2);	 Catch:{ Exception -> 0x01fb }
        goto L_0x01ff;
    L_0x012b:
        r11 = com.payu.custombrowser.bean.b.SINGLETON;	 Catch:{ Exception -> 0x01fb }
        if (r11 == 0) goto L_0x0142;
    L_0x012f:
        r11 = com.payu.custombrowser.bean.b.SINGLETON;	 Catch:{ Exception -> 0x01fb }
        r11 = r11.getPayuCustomBrowserCallback();	 Catch:{ Exception -> 0x01fb }
        if (r11 == 0) goto L_0x0142;
    L_0x0137:
        r11 = com.payu.custombrowser.bean.b.SINGLETON;	 Catch:{ Exception -> 0x01fb }
        r11 = r11.getPayuCustomBrowserCallback();	 Catch:{ Exception -> 0x01fb }
        r0 = "";
        r11.onPaymentSuccess(r10, r0);	 Catch:{ Exception -> 0x01fb }
    L_0x0142:
        r10 = r9.f;	 Catch:{ Exception -> 0x01fb }
        r10.finish();	 Catch:{ Exception -> 0x01fb }
        goto L_0x01ff;
    L_0x0149:
        if (r11 == 0) goto L_0x01f5;
    L_0x014b:
        r10 = com.payu.custombrowser.d.e.button_snooze_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r4);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.snooze_status_icon;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r4);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.text_view_cancel_snooze_window;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.button_snooze_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.snooze_header_txt;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10 = (android.widget.TextView) r10;	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.g.cb_transaction_failed_title;	 Catch:{ Exception -> 0x01fb }
        r10.setText(r11);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.text_view_snooze_message;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10 = (android.widget.TextView) r10;	 Catch:{ Exception -> 0x01fb }
        r11 = com.payu.custombrowser.d.g.cb_transaction_failed;	 Catch:{ Exception -> 0x01fb }
        r10.setText(r11);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.button_retry_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.button_cancel_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r4);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.button_snooze_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.text_view_retry_message_detail;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.text_view_transaction_snoozed_message1;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.text_view_ac_debited_twice;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r10.setVisibility(r5);	 Catch:{ Exception -> 0x01fb }
        r10 = com.payu.custombrowser.d.e.button_cancel_transaction;	 Catch:{ Exception -> 0x01fb }
        r10 = r1.findViewById(r10);	 Catch:{ Exception -> 0x01fb }
        r11 = new com.payu.custombrowser.b$18;	 Catch:{ Exception -> 0x01fb }
        r11.<init>();	 Catch:{ Exception -> 0x01fb }
        r10.setOnClickListener(r11);	 Catch:{ Exception -> 0x01fb }
        r10 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r11 = new com.payu.custombrowser.b$19;	 Catch:{ Exception -> 0x01fb }
        r11.<init>();	 Catch:{ Exception -> 0x01fb }
        r10.setOnDismissListener(r11);	 Catch:{ Exception -> 0x01fb }
        r10 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r10.setCanceledOnTouchOutside(r4);	 Catch:{ Exception -> 0x01fb }
        r9.hideReviewOrderDetails();	 Catch:{ Exception -> 0x01fb }
        r9.hideReviewOrderHorizontalBar();	 Catch:{ Exception -> 0x01fb }
        r10 = r9.k;	 Catch:{ Exception -> 0x01fb }
        r10.show();	 Catch:{ Exception -> 0x01fb }
        r10 = new android.os.Handler;	 Catch:{ Exception -> 0x01fb }
        r10.<init>();	 Catch:{ Exception -> 0x01fb }
        r11 = new com.payu.custombrowser.b$2;	 Catch:{ Exception -> 0x01fb }
        r11.<init>();	 Catch:{ Exception -> 0x01fb }
        r10.postDelayed(r11, r2);	 Catch:{ Exception -> 0x01fb }
        goto L_0x01ff;
    L_0x01f5:
        r10 = r9.f;	 Catch:{ Exception -> 0x01fb }
        r10.finish();	 Catch:{ Exception -> 0x01fb }
        goto L_0x01ff;
    L_0x01fb:
        r10 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r10);
    L_0x01ff:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.b.showTransactionStatusDialog(java.lang.String, boolean):void");
    }

    public void postDataToSurl(final String str, final String str2) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(URLDecoder.decode(str2, "UTF-8")).openConnection();
                    String str = str;
                    httpsURLConnection.setRequestMethod(HttpMethods.POST);
                    httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
                    httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(str.length()));
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.getOutputStream().write(str.getBytes());
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }).start();
    }

    public void resumeTransaction(Intent intent) {
        this.customBrowserConfig = (CustomBrowserConfig) intent.getExtras().getParcelable(CBConstant.CB_CONFIG);
        CBUtil cBUtil;
        if (intent.getStringExtra(CBConstant.CURRENT_URL) == null || intent.getStringExtra(CBConstant.S2S_RETRY_URL) != null) {
            if (intent.getStringExtra(CBConstant.S2S_RETRY_URL) != null) {
                reloadWebView(intent.getStringExtra(CBConstant.S2S_RETRY_URL), null);
            } else {
                reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
            }
        } else if (intent.getStringExtra(CBConstant.CURRENT_URL).equalsIgnoreCase(this.customBrowserConfig.getPostURL())) {
            if (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment")) {
                cBUtil = this.N;
                markPreviousTxnAsUserCanceled(CBUtil.getLogMessage(this.f.getApplicationContext(), "sure_pay_cancelled", this.customBrowserConfig.getTransactionID(), "", Bank.keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
            }
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        } else if (Bank.isUrlWhiteListed(intent.getStringExtra(CBConstant.CURRENT_URL))) {
            reloadWebView(intent.getStringExtra(CBConstant.CURRENT_URL));
        } else {
            if (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment")) {
                cBUtil = this.N;
                markPreviousTxnAsUserCanceled(CBUtil.getLogMessage(this.f.getApplicationContext(), "sure_pay_cancelled", this.customBrowserConfig.getTransactionID(), "", Bank.keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
            }
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        }
    }

    public void markPreviousTxnAsUserCanceled(String str) {
        new com.payu.custombrowser.widgets.b(str).a();
    }
}

package com.gaana;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.actionbar.GenericBackActionBar;
import com.actionbar.GenericBackActionBar.a;
import com.b.b;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.ads.mediation.inmobi.InMobiNetworkKeys;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.PurchaseOperatorManager;
import com.managers.aj;
import com.services.c;
import com.utilities.Util;
import com.utilities.h;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends AppCompatActivity implements OnClickListener, a {
    private boolean ALLOW_MULTIPLE_UPLOAD_FILES = false;
    LayoutParams COVER_SCREEN_GRAVITY_CENTER = new LayoutParams(-1, -1, 17);
    private boolean isLyricsWebBarVisible = false;
    private boolean isTransactionInitiatedHermes = false;
    private boolean isTransactionInitiatedOperator = false;
    private boolean isTransactionInitiatedPaypal = false;
    private boolean isYoutubeUrl = false;
    private String lastUrl = "";
    private LinearLayout mContentView;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private ProgressBar mProgressLoader;
    private Toolbar mToolbar;
    private ValueCallback<Uri> mUploadFileUri;
    private ValueCallback<Uri[]> mUploadFileUriArray;
    private WebChromeClient mWebChromeClient;
    private WebView mWebView;
    private boolean showActionBar = false;
    private boolean showActionBar2 = false;
    private String title;
    private String url = "";

    public interface DeviceIdCallBack {
        void callDeviceId(String str, String str2);
    }

    public void onClearAllClicked() {
    }

    public void onClick(View view) {
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.url = getIntent().getStringExtra("EXTRA_WEBVIEW_URL");
        String stringExtra = getIntent().getStringExtra("title");
        this.title = getResources().getString(R.string.terms_and_conditions);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.title = stringExtra;
        }
        this.isYoutubeUrl = getIntent().getBooleanExtra("EXTRA_WEBVIEW_YOUTUBE", false);
        this.showActionBar = getIntent().getBooleanExtra("EXTRA_SHOW_ACTIONBAR", false);
        this.showActionBar2 = getIntent().getBooleanExtra("EXTRA_SHOW_ACTIONBAR2", false);
        this.isTransactionInitiatedHermes = getIntent().getBooleanExtra("EXTRA_TRANSACTION_HERMES_INITIATED", false);
        this.isTransactionInitiatedOperator = getIntent().getBooleanExtra("EXTRA_TRANSACTION_OPERATOR_INITIATED", false);
        this.isTransactionInitiatedPaypal = getIntent().getBooleanExtra("EXTRA_TRANSACTION_PAYPAL_INITIATED", false);
        this.isLyricsWebBarVisible = getIntent().getBooleanExtra("EXTRA_SHOW_WEB_BARS", false);
        setContentView((int) R.layout.youtubeplay_webview);
        this.mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        this.mToolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(this.mToolbar);
        if (!this.showActionBar) {
            getSupportActionBar().hide();
            getWindow().setFlags(1024, 1024);
        }
        if (this.showActionBar) {
            View genericBackActionBar;
            if (!this.showActionBar2) {
                getSupportActionBar().setCustomView(new GenericBackActionBar((Context) this, "manage devices", (a) this));
            } else if (this.isTransactionInitiatedHermes) {
                genericBackActionBar = new GenericBackActionBar((Context) this, this.title, (a) this);
                genericBackActionBar.a();
                getSupportActionBar().setCustomView(genericBackActionBar);
            } else if (this.isTransactionInitiatedOperator) {
                genericBackActionBar = new GenericBackActionBar((Context) this, this.title, (a) this);
                genericBackActionBar.a();
                getSupportActionBar().setCustomView(genericBackActionBar);
            } else if (this.isTransactionInitiatedPaypal) {
                genericBackActionBar = new GenericBackActionBar((Context) this, this.title, (a) this);
                genericBackActionBar.a();
                getSupportActionBar().setCustomView(genericBackActionBar);
            } else {
                getSupportActionBar().setCustomView(new GenericBackActionBar((Context) this, this.title, null));
            }
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gaana_grey)));
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        Util.b();
        this.mContentView = (LinearLayout) findViewById(R.id.linearlayout);
        this.mWebView = (WebView) findViewById(R.id.webView);
        this.mProgressLoader = (ProgressBar) findViewById(R.id.progress_loader);
        this.mCustomViewContainer = (FrameLayout) findViewById(R.id.fullscreen_custom_content);
        this.mWebChromeClient = new WebChromeClient() {
            public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
                WebViewActivity.this.mUploadFileUri = valueCallback;
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                if (WebViewActivity.this.ALLOW_MULTIPLE_UPLOAD_FILES && VERSION.SDK_INT >= 18) {
                    intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                }
                WebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 713);
            }

            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                if (h.e(WebViewActivity.this)) {
                    if (WebViewActivity.this.mUploadFileUriArray != null) {
                        WebViewActivity.this.mUploadFileUriArray.onReceiveValue(null);
                    }
                    WebViewActivity.this.mUploadFileUriArray = valueCallback;
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType("*/*");
                    if (WebViewActivity.this.ALLOW_MULTIPLE_UPLOAD_FILES) {
                        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                    }
                    Intent intent2 = new Intent("android.intent.action.CHOOSER");
                    intent2.putExtra("android.intent.extra.INTENT", intent);
                    intent2.putExtra("android.intent.extra.TITLE", "File Chooser");
                    WebViewActivity.this.startActivityForResult(intent2, 713);
                    return true;
                }
                h.b(WebViewActivity.this, WebViewActivity.this.getString(R.string.please_enable_permission), -2);
                return false;
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                if (WebViewActivity.this.mCustomView != null) {
                    customViewCallback.onCustomViewHidden();
                    return;
                }
                WebViewActivity.this.mCustomViewContainer.addView(view, WebViewActivity.this.COVER_SCREEN_GRAVITY_CENTER);
                WebViewActivity.this.mCustomView = view;
                WebViewActivity.this.mCustomViewCallback = customViewCallback;
                WebViewActivity.this.mContentView.setVisibility(8);
                WebViewActivity.this.mCustomViewContainer.setVisibility(0);
                WebViewActivity.this.mCustomViewContainer.bringToFront();
            }

            public void onHideCustomView() {
                if (WebViewActivity.this.mCustomView != null) {
                    WebViewActivity.this.mCustomView.setVisibility(8);
                    WebViewActivity.this.mCustomViewContainer.removeView(WebViewActivity.this.mCustomView);
                    WebViewActivity.this.mCustomView = null;
                    WebViewActivity.this.mCustomViewContainer.setVisibility(8);
                    WebViewActivity.this.mCustomViewCallback.onCustomViewHidden();
                    WebViewActivity.this.mContentView.setVisibility(0);
                }
            }
        };
        WebSettings settings = this.mWebView.getSettings();
        settings.setPluginState(PluginState.ON);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        this.mWebView.setWebChromeClient(this.mWebChromeClient);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!TextUtils.isEmpty(WebViewActivity.this.lastUrl) && WebViewActivity.this.lastUrl.equals(str)) {
                    return false;
                }
                WebViewActivity.this.lastUrl = str;
                String access$800 = WebViewActivity.this.getOperatorDeeplinkString(str);
                Intent intent;
                if (TextUtils.isEmpty(str) || !str.contains("view/gaanarewards")) {
                    String str2 = null;
                    String[] split;
                    if (!TextUtils.isEmpty(str) && str.contains("view/hermessuccess")) {
                        split = str.split("view/hermessuccess/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                        intent.setFlags(4194304);
                        intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                        intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuHermesPurchaseResponseSuccess);
                        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
                        WebViewActivity.this.startActivity(intent);
                        return true;
                    } else if (TextUtils.isEmpty(str) || !str.contains("view/hermesfailure")) {
                        if (!TextUtils.isEmpty(access$800)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("gaana://view/");
                            stringBuilder.append(access$800);
                            stringBuilder.append("purchase/");
                            if (str.contains(stringBuilder.toString())) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("gaana://view/");
                                stringBuilder.append(access$800);
                                stringBuilder.append("purchase/");
                                split = str.split(stringBuilder.toString());
                                if (split.length > 1) {
                                    str2 = split[1];
                                }
                                if (str2.contains("success")) {
                                    intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                                    intent.setFlags(4194304);
                                    intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuOperatorPurchaseResponseSuccess);
                                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
                                    WebViewActivity.this.startActivity(intent);
                                } else if (str2.contains("failure")) {
                                    intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                                    intent.setFlags(4194304);
                                    intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuOperatorPurchaseResponseFailure);
                                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
                                    WebViewActivity.this.startActivity(intent);
                                }
                                return true;
                            }
                        }
                        if (!TextUtils.isEmpty(str) && str.contains("gaana://view/paypalpurchase")) {
                            split = str.split("gaana://view/paypalpurchase/");
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            if (str2.contains("success")) {
                                intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                                intent.setFlags(4194304);
                                intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                                intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPaypalPurchaseResponseSuccess);
                                intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2.replace("success/", ""));
                                WebViewActivity.this.startActivity(intent);
                            } else if (str2.contains("failure")) {
                                intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                                intent.setFlags(4194304);
                                intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                                intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPaypalPurchaseResponseFailure);
                                intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
                                WebViewActivity.this.startActivity(intent);
                            }
                            return true;
                        } else if (!TextUtils.isEmpty(str) && str.contains("gaana://view/studentverification")) {
                            split = str.split("gaana://view/studentverification/");
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            intent = WebViewActivity.this.getIntent();
                            if (str2 == null || !str2.contains("success")) {
                                intent.putExtra("SubscriptionPayment", "Failure");
                            } else {
                                intent.putExtra("SubscriptionPayment", "Success");
                            }
                            WebViewActivity.this.setResult(-1, intent);
                            WebViewActivity.this.finish();
                            return true;
                        } else if (!TextUtils.isEmpty(str) && str.startsWith("gaana://view")) {
                            c.a(WebViewActivity.this).a(WebViewActivity.this, str, (GaanaApplication) GaanaApplication.getContext());
                            return true;
                        } else if (TextUtils.isEmpty(str) || !str.contains("facebook.com")) {
                            return false;
                        } else {
                            WebViewActivity.this.openExternalBrowser(str);
                            return true;
                        }
                    } else {
                        split = str.split("view/hermesfailure/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        intent = new Intent(WebViewActivity.this, GaanaActivity.class);
                        intent.setFlags(4194304);
                        intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                        intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuHermesPurchaseResponseFailure);
                        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
                        WebViewActivity.this.startActivity(intent);
                        return true;
                    }
                }
                intent = new Intent(WebViewActivity.this, Login.class);
                intent.putExtra("is_login_as_activity_result", true);
                intent.putExtra("Launched_From", WebViewActivity.this.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_REWARDS));
                WebViewActivity.this.startActivityForResult(intent, 701);
                return true;
            }

            public void onPageFinished(final WebView webView, String str) {
                WebViewActivity.this.mProgressLoader.setVisibility(8);
                webView.setVisibility(0);
                String stringExtra = WebViewActivity.this.getIntent().getStringExtra("MWEB_VIEW_LOGIN");
                boolean booleanExtra = WebViewActivity.this.getIntent().getBooleanExtra("GETTING_DEVICE_ID", false);
                if (stringExtra != null && !TextUtils.isEmpty(stringExtra) && booleanExtra) {
                    Util.a(new DeviceIdCallBack() {
                        public void callDeviceId(String str, String str2) {
                            str = WebViewActivity.this.getJSonObject(str, str2);
                            WebView webView = webView;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("javascript:setDevice(\"");
                            stringBuilder.append(str);
                            stringBuilder.append("\")");
                            webView.loadUrl(stringBuilder.toString());
                        }
                    });
                }
            }
        });
        if (this.isYoutubeUrl) {
            this.mWebView.setBackgroundColor(getResources().getColor(R.color.black_alfa_60));
        } else {
            this.mProgressLoader.setVisibility(8);
        }
        try {
            if (this.isTransactionInitiatedOperator) {
                this.mWebView.loadUrl(this.url, getJunoteleHttpHeaders());
            } else {
                this.mWebView.loadUrl(this.url, getHttpHeaders());
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            finish();
        }
    }

    private String getJSonObject(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", str);
            jSONObject.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, str2);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    private String getOperatorDeeplinkString(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        for (int i = 0; i < PurchaseOperatorManager.a.size(); i++) {
            String str3 = (String) PurchaseOperatorManager.a.get(i);
            if (str.contains(str3)) {
                return str3;
            }
        }
        return str2;
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        if (this.mCustomView != null) {
            if (this.mCustomViewCallback != null) {
                this.mCustomViewCallback.onCustomViewHidden();
            }
            this.mCustomView = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        Util.a(false);
    }

    public void onBackPressed() {
        if (!checkAndHandleTransactionCancel()) {
            if (this.mCustomView != null) {
                this.mWebChromeClient.onHideCustomView();
            }
            if (this.mWebView != null) {
                this.mContentView.removeAllViews();
                this.mWebView.removeAllViews();
                this.mWebView.destroy();
            }
            super.onBackPressed();
        }
    }

    public void onBackClicked() {
        if (!checkAndHandleTransactionCancel()) {
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
                return;
            }
            this.mContentView.removeAllViews();
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            setResult(-1, getIntent());
            finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        Util.a(true);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (checkAndHandleTransactionCancel()) {
            return true;
        }
        if (i != 4 || !this.mWebView.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(-1, getIntent());
        this.mWebView.goBack();
        finish();
        return true;
    }

    public void onSubmitClicked() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
        setResult(-1, getIntent());
        finish();
    }

    public boolean checkAndHandleTransactionCancel() {
        if (!this.isTransactionInitiatedHermes && !this.isTransactionInitiatedOperator) {
            return false;
        }
        CustomDialogView customDialogView = new CustomDialogView((Context) this, getResources().getString(R.string.transaction_cancelled_message), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                aj.a().a(WebViewActivity.this, WebViewActivity.this.getString(R.string.transaction_cancelled));
                WebViewActivity.this.finish();
            }
        });
        customDialogView.setCancelable(false);
        customDialogView.show();
        return true;
    }

    public void finish() {
        super.finish();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 701) {
            if (i2 != 0) {
                try {
                    GaanaApplication instance = GaanaApplication.getInstance();
                    if (instance.getCurrentUser().getLoginStatus()) {
                        this.url = "https://gaana.com/rewarddetails/";
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.url);
                        stringBuilder.append(instance.getCurrentUser().getAuthToken());
                        this.url = stringBuilder.toString();
                        this.mWebView.loadUrl(this.url, getHttpHeaders());
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    finish();
                }
            }
        } else if (i == 712) {
            com.d.a.a().a(i, i2, intent);
        } else if (i == 713) {
            if (VERSION.SDK_INT >= 21) {
                Object obj;
                if (i2 == -1) {
                    if (this.mUploadFileUriArray != null) {
                        if (intent != null) {
                            i2 = 0;
                            if (intent.getDataString() != null) {
                                obj = new Uri[]{Uri.parse(intent.getDataString())};
                            } else if (this.ALLOW_MULTIPLE_UPLOAD_FILES && intent.getClipData() != null) {
                                i = intent.getClipData().getItemCount();
                                Object obj2 = new Uri[i];
                                while (i2 < i) {
                                    obj2[i2] = intent.getClipData().getItemAt(i2).getUri();
                                    i2++;
                                }
                                obj = obj2;
                            }
                            this.mUploadFileUriArray.onReceiveValue(obj);
                            this.mUploadFileUriArray = null;
                        }
                    } else {
                        return;
                    }
                }
                obj = null;
                this.mUploadFileUriArray.onReceiveValue(obj);
                this.mUploadFileUriArray = null;
            } else if (i == 713 && this.mUploadFileUri != null) {
                Object data = (intent == null || i2 != -1) ? null : intent.getData();
                this.mUploadFileUri.onReceiveValue(data);
                this.mUploadFileUri = null;
            }
        }
    }

    private Map<String, String> getHttpHeaders() {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(Constants.ct)) {
            Constants.ct = "IN";
        }
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("appId", Constants.bt);
        hashMap.put(InMobiNetworkKeys.COUNTRY, Constants.ct);
        hashMap.put("gps_city", Constants.cC);
        hashMap.put("gps_state", Constants.cB);
        hashMap.put("gps_enable", Constants.cD);
        hashMap.put("deviceType", Constants.bU);
        hashMap.put("appVersion", "V7");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PHPSESSID=");
        stringBuilder.append(GaanaApplication.getCurrentSessionId());
        hashMap.put("Cookie", stringBuilder.toString());
        hashMap.put("deviceId", Util.l(GaanaApplication.getContext()));
        stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        hashMap.put("gaanaAppVersion", stringBuilder.toString());
        if (this.isLyricsWebBarVisible) {
            hashMap.put("headerFooterState", "1");
        }
        return hashMap;
    }

    private Map<String, String> getJunoteleHttpHeaders() {
        GaanaApplication instance = GaanaApplication.getInstance();
        CharSequence charSequence = "";
        if (instance.getCurrentUser().getLoginStatus()) {
            charSequence = instance.getCurrentUser().getAuthToken();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Encoding", "gzip");
        if (!TextUtils.isEmpty(charSequence)) {
            hashMap.put("user_token", charSequence);
        }
        return hashMap;
    }

    private void openExternalBrowser(String str) {
        if (!TextUtils.isEmpty(str) && URLUtil.isValidUrl(str)) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                finish();
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, getString(R.string.error_generic_unableto_process), 1).show();
            }
        }
    }
}

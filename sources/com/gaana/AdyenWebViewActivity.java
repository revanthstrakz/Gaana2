package com.gaana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.actionbar.GenericBackActionBar;
import com.actionbar.GenericBackActionBar.a;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.managers.aj;
import java.util.Map;

public class AdyenWebViewActivity extends AppCompatActivity implements a {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String lastUrl = "";
    private ProgressBar mProgressLoader;
    private Toolbar mToolbar;
    private WebView mWebView;

    public void onClearAllClicked() {
    }

    public void onSubmitClicked() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.webview_payments);
        bundle = getIntent().getExtras();
        this.mWebView = (WebView) findViewById(R.id.webView);
        this.mProgressLoader = (ProgressBar) findViewById(R.id.progress_loader);
        this.mToolbar = (Toolbar) findViewById(R.id.main_tool);
        setSupportActionBar(this.mToolbar);
        View genericBackActionBar = new GenericBackActionBar((Context) this, "Purchase GaanaPlus", (a) this);
        genericBackActionBar.a();
        getSupportActionBar().setCustomView(genericBackActionBar);
        Map map = (Map) bundle.getSerializable("headers");
        this.mWebView.loadUrl(bundle.getString("url"), map);
        this.mWebView.getSettings().setSupportMultipleWindows(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() {
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!TextUtils.isEmpty(AdyenWebViewActivity.this.lastUrl) && AdyenWebViewActivity.this.lastUrl.equals(str)) {
                    return false;
                }
                AdyenWebViewActivity.this.lastUrl = str;
                if (TextUtils.isEmpty(str) || !str.contains("gaana://view/adyen")) {
                    return false;
                }
                String[] split = str.split("gaana://view/adyen/");
                str = null;
                if (split.length > 1) {
                    str = split[1];
                }
                Intent intent;
                if (str.contains("success")) {
                    intent = new Intent(AdyenWebViewActivity.this, GaanaActivity.class);
                    intent.setFlags(268468224);
                    str = str.replace("success/", "");
                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuAdyenPurchaseResponseSuccess);
                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                    AdyenWebViewActivity.this.startActivity(intent);
                } else if (str.contains("failure")) {
                    intent = new Intent(AdyenWebViewActivity.this, GaanaActivity.class);
                    intent.setFlags(268468224);
                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuAdyenPurchaseResponseFailure);
                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                    AdyenWebViewActivity.this.startActivity(intent);
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                AdyenWebViewActivity.this.mProgressLoader.setVisibility(8);
                webView.setVisibility(0);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onBackClicked() {
        if (!checkAndHandleTransactionCancel()) {
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
                return;
            }
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            setResult(-1, getIntent());
            finish();
        }
    }

    public void onBackPressed() {
        if (!checkAndHandleTransactionCancel()) {
            if (this.mWebView != null) {
                this.mWebView.removeAllViews();
                this.mWebView.destroy();
            }
            super.onBackPressed();
        }
    }

    public boolean checkAndHandleTransactionCancel() {
        CustomDialogView customDialogView = new CustomDialogView((Context) this, getResources().getString(R.string.transaction_cancelled_message), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                aj.a().a(AdyenWebViewActivity.this, AdyenWebViewActivity.this.getString(R.string.transaction_cancelled));
                AdyenWebViewActivity.this.finish();
            }
        });
        customDialogView.setCancelable(false);
        customDialogView.show();
        return true;
    }
}

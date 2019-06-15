package com.gaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import com.payu.india.Model.PayuConfig;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentsActivity extends AppCompatActivity {
    private String lastUrl = "";
    private ProgressBar mProgressLoader;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.webview_payments);
        PayuConfig payuConfig = (PayuConfig) getIntent().getExtras().getParcelable("payuConfig");
        WebView webView = (WebView) findViewById(R.id.webView);
        this.mProgressLoader = (ProgressBar) findViewById(R.id.progress_loader);
        webView.postUrl(payuConfig.b() == 0 ? "https://secure.payu.in/_payment" : "https://test.payu.in/_payment", EncodingUtils.getBytes(payuConfig.a(), "base64"));
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!TextUtils.isEmpty(PaymentsActivity.this.lastUrl) && PaymentsActivity.this.lastUrl.equals(str)) {
                    return false;
                }
                PaymentsActivity.this.lastUrl = str;
                if (TextUtils.isEmpty(str) || !str.contains("gaana://view/payupurchase")) {
                    return false;
                }
                String[] split = str.split("gaana://view/payupurchase/");
                str = null;
                if (split.length > 1) {
                    str = split[1];
                }
                Intent intent;
                if (str.contains("success")) {
                    intent = new Intent(PaymentsActivity.this, GaanaActivity.class);
                    intent.setFlags(268468224);
                    str = str.replace("success/", "");
                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPayUPurchaseResponseSuccess);
                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                    PaymentsActivity.this.startActivity(intent);
                } else if (str.contains("failure")) {
                    intent = new Intent(PaymentsActivity.this, GaanaActivity.class);
                    intent.setFlags(268468224);
                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPayUPurchaseResponseFailure);
                    intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                    PaymentsActivity.this.startActivity(intent);
                }
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                PaymentsActivity.this.mProgressLoader.setVisibility(8);
                webView.setVisibility(0);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (intent != null) {
                try {
                    new JSONObject(intent.getStringExtra(CBConstant.PAYU_RESPONSE)).has("card_token");
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
            setResult(i2, intent);
            finish();
        }
    }
}

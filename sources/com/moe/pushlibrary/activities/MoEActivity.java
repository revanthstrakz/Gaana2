package com.moe.pushlibrary.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.net.UrlQuerySanitizer.ParameterValuePair;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.Logger;
import java.util.List;
import org.json.JSONObject;

public class MoEActivity extends MoEBaseActivity {
    final String EVENT_ACTION_WEB_ACTIVITY_CLICK = "EVENT_ACTION_WEB_ACTIVITY_CLICK";
    private WebView webview;

    /* Access modifiers changed, original: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent().getExtras();
        getWindow().requestFeature(2);
        this.webview = new WebView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        CharSequence charSequence = null;
        final ProgressBar progressBar = new ProgressBar(this, null, 16842872);
        progressBar.setLayoutParams(new LayoutParams(-1, -2));
        this.webview.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.addView(progressBar);
        linearLayout.addView(this.webview);
        setContentView(linearLayout);
        if (bundle != null && bundle.containsKey(MoEHelperConstants.GCM_EXTRA_WEB_URL)) {
            charSequence = bundle.getString(MoEHelperConstants.GCM_EXTRA_WEB_URL);
        }
        if (TextUtils.isEmpty(charSequence)) {
            finish();
            return;
        }
        this.webview.loadUrl(charSequence);
        if (VERSION.SDK_INT >= 11) {
            setUp();
        }
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setBuiltInZoomControls(true);
        this.webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i < 100 && progressBar.getVisibility() == 8) {
                    progressBar.setVisibility(0);
                }
                progressBar.setProgress(i);
                if (i == 100) {
                    progressBar.setVisibility(8);
                }
            }
        });
        this.webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", str);
                } catch (Exception e) {
                    Logger.f("shouldOverrideUrlLoading", e);
                }
                MoEActivity.this.mHelper.trackEvent("EVENT_ACTION_WEB_ACTIVITY_CLICK", jSONObject);
                Uri parse = Uri.parse(str);
                if (parse.getScheme().equals("moengage")) {
                    try {
                        Intent intent = new Intent(this, Class.forName(parse.getHost()));
                        Bundle access$000 = MoEActivity.this.getParamsInBundle(str);
                        if (access$000 != null) {
                            intent.putExtras(access$000);
                        }
                        MoEActivity.this.startActivity(intent);
                        return true;
                    } catch (ClassNotFoundException e2) {
                        Logger.f("MoEActivity : activity not found ", e2);
                        return false;
                    } catch (Exception e3) {
                        Logger.f("MoEActivity : activity not found ", e3);
                        return false;
                    }
                }
                MoEActivity.this.startActivity(new Intent("android.intent.action.VIEW", parse));
                return true;
            }
        });
    }

    private Bundle getParamsInBundle(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bundle bundle = new Bundle();
        List<ParameterValuePair> parameterList = new UrlQuerySanitizer(str).getParameterList();
        if (parameterList.isEmpty()) {
            return null;
        }
        for (ParameterValuePair parameterValuePair : parameterList) {
            if (TextUtils.isDigitsOnly(parameterValuePair.mValue)) {
                bundle.putInt(parameterValuePair.mParameter, Integer.parseInt(parameterValuePair.mValue));
            } else {
                bundle.putString(parameterValuePair.mParameter, parameterValuePair.mValue);
            }
        }
        return bundle;
    }

    @TargetApi(11)
    private void setUp() {
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            if (NavUtils.getParentActivityName(this) == null) {
                onBackPressed();
            } else {
                TaskStackBuilder.create(this).addNextIntentWithParentStack(NavUtils.getParentActivityIntent(this)).startActivities();
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.webview.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.webview.goBack();
        return true;
    }
}

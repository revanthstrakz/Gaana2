package com.timespointssdk;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.timespointssdk.c.a;
import com.timespointssdk.c.b;
import com.timespointssdk.c.c;

public class ProfileViewActivity extends AppCompatActivity {
    WebView a;
    String b = "ProfileViewActivity";

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(c.activity_web_view);
        String stringExtra = getIntent().getStringExtra("ticketId");
        this.a = (WebView) findViewById(b.webView);
        this.a.getSettings().setDomStorageEnabled(true);
        this.a.getSettings().setJavaScriptEnabled(true);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) "TIMES POINTS");
            supportActionBar.setHomeAsUpIndicator(a.cancel);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?uid=");
        stringBuilder.append(g.b("userid"));
        stringBuilder.append("&source=");
        stringBuilder.append(g.b("pcode"));
        stringBuilder.append("&platform=android&ticketId=");
        stringBuilder.append(stringExtra);
        stringExtra = stringBuilder.toString();
        String str;
        StringBuilder stringBuilder2;
        if (getIntent().hasExtra("profileLink")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getIntent().getStringExtra("profileLink"));
            stringBuilder.append(stringExtra);
            stringExtra = stringBuilder.toString();
            str = this.b;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("finalUrl --->");
            stringBuilder2.append(stringExtra);
            Log.e(str, stringBuilder2.toString());
            this.a.loadUrl(stringExtra);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(g.b("profileLink"));
            stringBuilder.append(stringExtra);
            str = stringBuilder.toString();
            String str2 = this.b;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("finalUrl1 --->");
            stringBuilder3.append(g.b("profileLink"));
            Log.e(str2, stringBuilder3.toString());
            str2 = this.b;
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("finalUrl2 --->");
            stringBuilder3.append(stringExtra);
            Log.e(str2, stringBuilder3.toString());
            stringExtra = this.b;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("finalUrl3 --->");
            stringBuilder2.append(str);
            Log.e(stringExtra, stringBuilder2.toString());
            this.a.loadUrl(str);
        }
        this.a.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (a.a.booleanValue()) {
                    String str2 = ProfileViewActivity.this.b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("URL---->");
                    stringBuilder.append(str);
                    Log.e(str2, stringBuilder.toString());
                }
                return false;
            }

            public void onLoadResource(WebView webView, String str) {
                if (a.a.booleanValue()) {
                    String str2 = ProfileViewActivity.this.b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("url---->");
                    stringBuilder.append(str);
                    Log.e(str2, stringBuilder.toString());
                }
            }
        });
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (VERSION.SDK_INT >= 12) {
            CookieManager.setAcceptFileSchemeCookies(true);
        }
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this.a, true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.a.canGoBack()) {
            this.a.goBack();
        } else {
            finish();
        }
        return true;
    }
}

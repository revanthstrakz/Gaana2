package com.payu.magicretry;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.payu.magicretry.a.a;
import com.payu.magicretry.a.b;
import com.payu.magicretry.a.c;

public class MainActivity extends AppCompatActivity {
    WebView a;
    MagicRetryFragment b;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.magicretry_main);
        this.a = (WebView) findViewById(a.wv1);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.b = new MagicRetryFragment();
        supportFragmentManager.beginTransaction().add(a.magic_retry_container, this.b, "magicRetry").commit();
        this.a.setWebChromeClient(new WebChromeClient());
        this.a.setWebViewClient(new com.payu.magicretry.b.b(this.b));
        this.b.a(this.a);
        this.a.loadUrl("http://google.com");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(c.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == a.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

package com.facebook.accountkit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AccountKitEmailRedirectActivity extends Activity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, AccountKitActivity.class);
        intent.putExtra("url", getIntent().getDataString());
        intent.addFlags(335544320);
        startActivity(intent);
    }
}

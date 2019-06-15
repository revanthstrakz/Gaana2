package com.til.colombia.dmp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;

public class PsA extends Activity {
    WebView a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            bundle = getIntent().getExtras();
            if (Utils.getBooleanPreferences(getApplicationContext(), Utils.DMP_PREF, Utils.CP_SERVER_DISABLE)) {
                Log.i("cps", "cross platform disabled");
            } else if (bundle == null || bundle.get("id") == null) {
                Log.i("cps", "received cps intent Null");
            } else {
                StringBuilder stringBuilder = new StringBuilder("received cps intent ");
                stringBuilder.append(bundle.get("id").toString());
                Log.i("cps", stringBuilder.toString());
                Intent intent = new Intent();
                intent.setClass(this, PSService.class);
                intent.putExtra("sid", bundle.get("id").toString());
                startService(intent);
                LayoutParams attributes = getWindow().getAttributes();
                attributes.type = 2002;
                attributes.gravity = 17;
                attributes.x = 0;
                attributes.height = 70;
                attributes.width = 100;
                attributes.y = 0;
                getWindow().setAttributes(attributes);
            }
            finish();
        } catch (Exception unused) {
            finish();
        } catch (Throwable th) {
            finish();
            throw th;
        }
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
    }
}

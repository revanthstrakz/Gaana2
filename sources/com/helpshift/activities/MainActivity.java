package com.helpshift.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.helpshift.k.b;

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getBooleanExtra("showInFullScreen", false)) {
            getWindow().setFlags(1024, 1024);
        }
        Integer num = b.a().a.k;
        if (num != null && num.intValue() != -1) {
            setRequestedOrientation(num.intValue());
        }
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.helpshift.util.b.e(context));
    }
}

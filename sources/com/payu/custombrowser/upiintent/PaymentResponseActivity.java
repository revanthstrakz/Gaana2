package com.payu.custombrowser.upiintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.util.CBConstant;

public class PaymentResponseActivity extends Activity {
    a a;
    String b;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getStringExtra(CBConstant.POST_DATA);
        this.a = new a(this, this.b, b.SINGLETON.getPayuCustomBrowserCallback());
        this.a.a();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 101) {
            return;
        }
        if (i2 != -1) {
            this.a.a("cancel", null);
        } else if (intent.getStringExtra("Status").equalsIgnoreCase("success")) {
            this.a.a("success", null);
        } else {
            this.a.a("failure", null);
        }
    }
}

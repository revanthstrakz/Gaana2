package com.d;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.gaana.BaseActivity;
import com.gaana.WebViewActivity;

public final class c {
    public void a(Context context, Uri uri) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("EXTRA_WEBVIEW_URL", uri.toString());
        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
        intent.putExtra("EXTRA_TRANSACTION_PAYPAL_INITIATED", true);
        intent.putExtra("title", "Pay through Paypal");
        ((BaseActivity) context).startActivityForResult(intent, 712);
    }
}

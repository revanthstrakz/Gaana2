package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.moengage.inapp.InAppManager;
import com.moengage.inapp.InAppMessage;
import com.moengage.inapp.InAppTracker;

public class RateUsDialog extends Dialog {
    public RateUsDialog(final Context context, final InAppMessage inAppMessage) {
        super(context);
        requestWindowFeature(1);
        setContentView(R.layout.popup_rate_app);
        ((Button) findViewById(R.id.popup_rate_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=com.gaana"));
                context.startActivity(intent);
                MoEngage.getInstance().reportWhetherAppRated(true);
                MoEngage.getInstance().reportRateUsAction("Rated");
                if (inAppMessage != null) {
                    InAppManager.getInstance().trackInAppPrimaryClick(context, inAppMessage);
                    InAppTracker.getInstance(context).trackInAppClicked(inAppMessage);
                }
                RateUsDialog.this.dismiss();
            }
        });
        ((Button) findViewById(R.id.popup_remind_later_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MoEngage.getInstance().reportRateUsAction("Later");
                if (inAppMessage != null) {
                    InAppManager.getInstance().trackInAppPrimaryClick(context, inAppMessage);
                    InAppTracker.getInstance(context).trackInAppClicked(inAppMessage);
                }
                RateUsDialog.this.dismiss();
            }
        });
        ((Button) findViewById(R.id.popup_no_thanks_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MoEngage.getInstance().reportRateUsAction("NoThanks");
                if (inAppMessage != null) {
                    InAppManager.getInstance().trackInAppPrimaryClick(context, inAppMessage);
                    InAppTracker.getInstance(context).trackInAppClicked(inAppMessage);
                }
                RateUsDialog.this.dismiss();
            }
        });
    }
}

package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.login.LoginManager;
import com.gaana.models.BasicResponse;
import com.gaana.models.BusinessObject;
import com.managers.ColombiaManager;
import com.managers.ap;
import com.managers.u;
import com.services.l.s;

public class SubscribeDialog extends Dialog {
    public SubscribeDialog(final Context context, String str, final OnClickListener onClickListener, final String str2, boolean z) {
        super(context);
        requestWindowFeature(1);
        setContentView(R.layout.view_subscribe_popup);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(view);
                u.a().a("Trial_Sponsership", "Trial Pop Up_Ad", "CTA - Activate tapped");
                SubscribeDialog.this.dismiss();
            }
        });
        findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().a("Trial_Sponsership", "Trial Pop Up_Ad", "Trial - Dismiss");
                SubscribeDialog.this.dismiss();
            }
        });
        ((TextView) findViewById(R.id.message)).setText(str);
        if (z) {
            LoginManager.getInstance().checkTrialAvailability(context, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (SubscribeDialog.this.isShowing()) {
                        if (((businessObject != null ? 1 : 0) & (businessObject instanceof BasicResponse)) != 0) {
                            BasicResponse basicResponse = (BasicResponse) businessObject;
                            if (basicResponse.getResult() == null || !basicResponse.getResult().equalsIgnoreCase("Yes")) {
                                u.a().a("Gaana+ Dialog", "Gaana+ dialog shown", str2);
                            } else {
                                Resources resources = context.getResources();
                                ((TextView) SubscribeDialog.this.findViewById(R.id.title)).setText(resources.getString(R.string.activate_gaanaplus_trial_title));
                                ((TextView) SubscribeDialog.this.findViewById(R.id.message)).setText(resources.getString(R.string.activate_gaanaplus_trial_message));
                                button.setText(context.getString(R.string.activate_gaanaplus_button_msg));
                                button.setTag(businessObject);
                                u.a().a("Gaana+ Dialog", "Trial dialog shown", str2);
                                if (ap.a().b(context)) {
                                    SubscribeDialog.this.initiateSponsorAd(context);
                                }
                            }
                            SubscribeDialog.this.findViewById(R.id.autoTrialMessage).setVisibility(8);
                        }
                    }
                }
            });
        }
    }

    private void initiateSponsorAd(Context context) {
        ColombiaManager.b().a(1, context, 25, -1, (LinearLayout) findViewById(R.id.llNativeAdSlot), "PlayerMaterial", null, "");
    }
}

package com.helpshift.campaigns.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.helpshift.c;
import com.helpshift.campaigns.models.AnalyticsEvent.a;
import com.helpshift.enums.ACTION_TYPE;
import com.helpshift.util.b;
import com.helpshift.util.l;

public class NotificationActivity extends Activity {

    /* renamed from: com.helpshift.campaigns.activities.NotificationActivity$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ACTION_TYPE.values().length];

        static {
            try {
                a[ACTION_TYPE.SHOW_INBOX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        l.a("Helpshift_NotifAct", "Campaign notification clicked");
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(NativeProtocol.WEB_DIALOG_ACTION);
        if (stringExtra == null) {
            stringExtra = "0";
        }
        ACTION_TYPE action_type = ACTION_TYPE.getEnum(stringExtra);
        String stringExtra2 = intent.getStringExtra("data");
        String stringExtra3 = intent.getStringExtra("campaignId");
        boolean booleanExtra = intent.getBooleanExtra("foregroundStatus", true);
        b.a((Context) this, stringExtra3, 1);
        if (action_type != ACTION_TYPE.SHOW_INBOX) {
            com.helpshift.campaigns.c.b.a().e.a(Integer.valueOf(intent.getIntExtra("type", a.a.intValue())), stringExtra3, Boolean.valueOf(false));
        }
        if (booleanExtra) {
            if (AnonymousClass1.a[action_type.ordinal()] != 1) {
                c.b().a(this, action_type, stringExtra2);
            } else {
                com.helpshift.campaigns.a aVar = com.helpshift.campaigns.c.b.a().g;
                if (aVar == null || aVar.a() == null) {
                    intent = new Intent(this, ParentActivity.class);
                    intent.putExtra("launch_source", 1);
                    intent.putExtra("campaignId", stringExtra3);
                    startActivity(intent);
                } else {
                    aVar.a().a(stringExtra3);
                }
            }
        }
        finish();
    }
}

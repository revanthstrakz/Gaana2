package com.managers;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.Referral;
import com.i.i;
import com.library.util.Serializer;
import com.services.l.s;
import com.utilities.Util;

public class d {
    private static d a;
    private static Referral b;

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public void b() {
        b = null;
        com.services.d.a().b("PREF_REFERRAL_DETAILS", true);
    }

    private void a(final Context context, final s sVar, final BusinessObject businessObject) {
        if (sVar != null && (context instanceof Activity)) {
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    sVar.onRetreivalComplete(businessObject);
                }
            });
        }
    }

    public void a(Context context, String str) {
        com.services.d.a().a("PREF_REFERRAL_ID", str, false);
    }

    public String c() {
        return com.services.d.a().c("PREF_REFERRAL_ID", false);
    }

    public void a(Context context, s sVar) {
        if (b != null) {
            sVar.onRetreivalComplete(b);
            return;
        }
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.getting_your_referral_link));
        }
        b(context, sVar);
    }

    private void b(final Context context, final s sVar) {
        String c = com.services.d.a().c("PREF_REFERRAL_DETAILS", true);
        if (TextUtils.isEmpty(c)) {
            c = "https://api.gaana.com/user.php?type=user_referral_url";
            try {
                UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
                if (!(currentUser == null || !currentUser.getLoginStatus() || c.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(c);
                    stringBuilder.append("&token=");
                    stringBuilder.append(currentUser.getAuthToken());
                    c = stringBuilder.toString();
                }
                URLManager uRLManager = new URLManager();
                uRLManager.a(c);
                uRLManager.a(Referral.class);
                uRLManager.b(Boolean.valueOf(false));
                if (Util.j(context)) {
                    i.a().a(new s() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (businessObject != null) {
                                d.b = (Referral) businessObject;
                            }
                            com.services.d.a().a("PREF_REFERRAL_DETAILS", Serializer.serialize(d.b), true);
                            d.this.a(context, sVar, d.b);
                        }
                    }, uRLManager);
                }
            } catch (Exception unused) {
                a(context, sVar, b);
            }
            return;
        }
        b = (Referral) Serializer.deserialize(c);
        a(context, sVar, b);
    }

    public void d() {
        com.services.d.a().b("PREF_REFERRAL_ID", false);
    }
}

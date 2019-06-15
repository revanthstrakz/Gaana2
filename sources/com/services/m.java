package com.services;

import android.app.Activity;
import android.content.Intent;
import com.constants.Constants;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;
import com.facebook.accountkit.ui.AccountKitConfiguration.AccountKitConfigurationBuilder;
import com.facebook.accountkit.ui.LoginType;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.sso.SsoErrorCodes;
import java.util.HashMap;
import java.util.Map;

public class m {
    private static m c;
    private int a = SsoErrorCodes.SDK_NOT_INITIALIZED;
    private final Map<Integer, a> b = new HashMap();
    private b d;

    private interface a {
        void a();
    }

    public interface b {
        void onPhoneLoginCancel(String str);

        void onPhoneLoginFailed(String str);

        void onPhoneLoginSuccess(String str);
    }

    public static m a() {
        if (c == null) {
            c = new m();
        }
        return c;
    }

    private m() {
        AccountKit.initialize(GaanaApplication.getContext().getApplicationContext());
    }

    public void a(final Activity activity, b bVar, final boolean z) {
        this.d = bVar;
        final Intent intent = new Intent(activity, AccountKitActivity.class);
        AccountKitConfigurationBuilder accountKitConfigurationBuilder = new AccountKitConfigurationBuilder(LoginType.PHONE, ResponseType.TOKEN);
        if (Constants.l) {
            accountKitConfigurationBuilder.setTheme(R.style.FbLoginThemeWhite);
        } else {
            accountKitConfigurationBuilder.setTheme(R.style.FbLoginTheme);
        }
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, accountKitConfigurationBuilder.build());
        new a() {
            public void a() {
                if (z) {
                    activity.startActivityForResult(intent, 2);
                } else {
                    activity.startActivityForResult(intent, 1);
                }
            }
        }.a();
    }

    public void a(int i, int i2, Intent intent) {
        if (intent != null) {
            if (i == 1 || i == 2) {
                AccountKitLoginResult accountKitLoginResult = (AccountKitLoginResult) intent.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                if (accountKitLoginResult != null) {
                    if (accountKitLoginResult.getError() != null) {
                        accountKitLoginResult.getError().getErrorType().getMessage();
                        this.d.onPhoneLoginFailed(accountKitLoginResult.getError().getErrorType().getMessage());
                    } else if (accountKitLoginResult.wasCancelled()) {
                        this.d.onPhoneLoginCancel("Login Cancelled");
                    } else if (accountKitLoginResult.getAccessToken() != null) {
                        this.d.onPhoneLoginSuccess(accountKitLoginResult.getAccessToken().getToken());
                    } else {
                        this.d.onPhoneLoginSuccess(accountKitLoginResult.getAuthorizationCode());
                    }
                }
            }
        }
    }

    public void b() {
        AccountKit.logOut();
    }
}

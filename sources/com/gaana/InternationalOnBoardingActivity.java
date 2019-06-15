package com.gaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.models.ReferralSignup;
import com.google.gson.Gson;
import com.library.controls.CrossFadeImageView;
import com.managers.u;
import com.services.d;
import com.utilities.g;

public class InternationalOnBoardingActivity extends AppCompatActivity implements OnClickListener {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.GaanaAppTheme);
        if (g.b() || Constants.l) {
            setTheme(R.style.GaanaAppThemeWhite);
        }
        setContentView((int) R.layout.activity_international_on_boarding);
        ((CrossFadeImageView) findViewById(R.id.bg_intl_onboarding)).setOnClickListener(this);
        ((TextView) findViewById(R.id.txt_next_screen)).setOnClickListener(this);
        u.a().a("InternationalOnBoardingScreen");
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bg_intl_onboarding || id == R.id.txt_next_screen) {
            u.a().a("InternationalOnBoarding", "Next", "InternationalOnBoarding");
            launchPaymentPage();
        }
    }

    private void launchPaymentPage() {
        Intent intent = new Intent(this, GaanaActivity.class);
        intent.putExtra("PLAY_DEEPLINKING_SONG", false);
        intent.putExtra("DEEPLINKING_SCREEN", R.id.InternationalOnBoardPurchaseScreen);
        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", null);
        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2", null);
        intent.putExtra("PLAY_DEEPLINKING_RADIO", null);
        intent.putExtra("SHOW_PROFILE_USER", null);
        intent.putExtra("LAUNCH_DETAIL_PAGE", false);
        intent.setFlags(67108864);
        GaanaApplication.getInstance().setAppLaucnhedFromDeeplinking(true);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        Constants.T = false;
        Intent intent = new Intent(this, GaanaActivity.class);
        intent.setFlags(603979776);
        startActivity(intent);
        String str = "Skip-Home";
        d a = d.a();
        if (a.c("PREF_KEY_REFERRAL_INFO", false) != null) {
            ReferralSignup referralSignup = (ReferralSignup) new Gson().fromJson(a.c("PREF_KEY_REFERRAL_INFO", false), ReferralSignup.class);
            Intent intent2 = new Intent(getApplicationContext(), ReferralSignupActivity.class);
            intent2.setFlags(603979776);
            intent2.putExtra("is_first_ap_launch", true);
            intent2.putExtra("referralInfo", referralSignup);
            startActivity(intent2);
            a.b("PREF_KEY_REFERRAL_INFO", false);
            str = "Skip-Referral";
        }
        u.a().a("InternationalOnBoarding", str, "InternationalOnBoarding");
        finish();
    }
}

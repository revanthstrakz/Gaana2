package com.payu.custombrowser;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.c.a;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.f;
import com.payu.custombrowser.d.g;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.magicretry.MagicRetryFragment;
import java.util.ArrayList;
import org.json.JSONException;

public class CBActivity extends AppCompatActivity implements a, MagicRetryFragment.a {
    protected static ArrayAdapter a;
    public static int b;
    protected static View e;
    CustomBrowserConfig c;
    CBUtil d;
    private Bank f;
    private AlertDialog g;
    private android.app.AlertDialog h;

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        b = 1;
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        b = 2;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(null);
        setContentView(f.cb_payments);
        this.f = new Bank();
        this.d = new CBUtil();
        this.d.resetPayuID();
        bundle = new Bundle();
        this.c = (CustomBrowserConfig) getIntent().getParcelableExtra(CBConstant.CB_CONFIG);
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(CBConstant.ORDER_DETAILS);
        bundle.putParcelable(CBConstant.CB_CONFIG, this.c);
        if (parcelableArrayListExtra != null) {
            bundle.putParcelableArrayList(CBConstant.ORDER_DETAILS, parcelableArrayListExtra);
        }
        this.f.setArguments(bundle);
        cbSetToolBar(e);
        d();
        getSupportFragmentManager().beginTransaction().add(e.main_frame, this.f).commit();
    }

    public void onBackPressed() {
        if (this.c == null || this.c.getDisableBackButtonDialog() == 1) {
            this.f.a("user_input", "m_back_button");
            if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                b.SINGLETON.getPayuCustomBrowserCallback().onBackButton(null);
            }
            finish();
            return;
        }
        this.f.a("user_input", "payu_back_button".toLowerCase());
        this.f.showBackButtonDialog();
    }

    public void a() {
        this.f.showMagicRetry();
    }

    public void b() {
        this.f.hideMagicRetry();
    }

    public void onDestroy() {
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
            this.h.cancel();
        }
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
            this.g.cancel();
        }
        b = 3;
        if (!(this.f == null || this.f.getSnoozeLoaderView() == null)) {
            this.f.getSnoozeLoaderView().b();
            this.f.setSnoozeLoaderView(null);
        }
        if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
            b.SINGLETON.getPayuCustomBrowserCallback().onPaymentTerminate();
            b.SINGLETON.setPayuCustomBrowserCallback(null);
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        notificationManager.cancel(CBConstant.SNOOZE_NOTIFICATION_ID);
        notificationManager.cancel(63);
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getStringExtra(CBConstant.SENDER).contentEquals(CBConstant.SNOOZE_SERVICE) && this.f != null) {
            this.f.killSnoozeService();
            this.f.dismissSnoozeWindow();
            this.f.ad = null;
            this.f.ac = false;
            if (intent.getExtras().getBoolean(CBConstant.VERIFICATION_MSG_RECEIVED)) {
                try {
                    if (this.d.getValueOfJSONKey(intent.getExtras().getString(CBConstant.PAYU_RESPONSE), getString(g.cb_snooze_verify_api_status)).equalsIgnoreCase("1")) {
                        this.f.a("transaction_verified_notification_click", "-1");
                    } else {
                        this.f.a("transaction_not_verified_notification_click", "-1");
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                this.f.showTransactionStatusDialog(intent.getExtras().getString(CBConstant.PAYU_RESPONSE), true);
                return;
            }
            this.f.a("internet_restored_notification_click", "-1");
            this.f.resumeTransaction(intent);
        }
    }

    public void c() {
        this.f.a("user_input", "review_order_close_click");
    }

    public void cbSetToolBar(View view) {
        if (view != null && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setCustomView(view, new LayoutParams(-1, -1));
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            ((Toolbar) view.getParent()).setContentInsetsAbsolute(0, 0);
        } else if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void d() {
        if (a != null && this.c.getCbDrawerCustomMenu() != 0) {
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(e.drawer_layout);
            ListView listView = (ListView) getLayoutInflater().inflate(this.c.getCbDrawerCustomMenu(), null);
            DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 8388611;
            drawerLayout.addView(listView);
            listView.setLayoutParams(layoutParams);
            listView.setAdapter(a);
            b.SINGLETON.getPayuCustomBrowserCallback().getNavigationDrawerObject(drawerLayout);
        }
    }
}

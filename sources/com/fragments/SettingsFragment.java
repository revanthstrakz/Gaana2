package com.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.b.i;
import com.constants.Constants;
import com.constants.a.a;
import com.f.b;
import com.f.c;
import com.gaana.AppLanguageSettingsScreenActivity;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.GaanaActivity.ISleepTimerListener;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.CountryData;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.ag;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.payu.custombrowser.util.CBConstant;
import com.services.l.ad;
import com.services.l.au;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends BaseGaanaFragment implements OnClickListener, a, a, PurchaseGoogleManager.a {
    TextView a = null;
    private LinearLayout b;
    private LayoutInflater c;
    private View d;
    private View e;

    public String getFragmentStackName() {
        return "settings";
    }

    public void onFragmentScroll() {
    }

    public void onProductsQueryCompleted() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.containerView = setContentView(R.layout.settings, viewGroup);
        if (getArguments() != null ? getArguments().getBoolean("TAG_SETTINGS_START_RESTORE_PURCHASE") : false) {
            f();
        } else {
            b();
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        setGAScreenName("SettingsScreen", "SettingsScreen");
        MoEngage.getInstance().reportSectionViewedEvent("Settings");
        setActionBar(this.containerView, new GenericBackActionBar(this.mContext, getString(R.string.settings)));
        return this.containerView;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private View a() {
        return this.c.inflate(R.layout.setting_horizontal_divider, this.b, false);
    }

    public void onPause() {
        super.onPause();
        ((GaanaActivity) this.mContext).removeSleepTimerCallback();
    }

    public void onResume() {
        super.onResume();
        g();
        if (this.d != null) {
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                this.d.setVisibility(0);
                this.containerView.findViewById(R.id.logout_buttonText).setVisibility(0);
                this.d.setTag(Integer.valueOf(12));
                this.d.setOnClickListener(this);
            } else {
                this.d.setVisibility(8);
                this.containerView.findViewById(R.id.logout_buttonText).setVisibility(8);
            }
        }
        ((GaanaActivity) this.mContext).title = getString(R.string.gaana_settings_frag_title);
    }

    private View a(String str, String str2, boolean z) {
        this.e = this.c.inflate(R.layout.view_settings_listitem_status, this.b, false);
        ((TextView) this.e.findViewById(R.id.headerText)).setText(str);
        if (str2 != null) {
            this.e.findViewById(R.id.subHeaderContainer).setVisibility(0);
            ((TextView) this.e.findViewById(R.id.subheaderText)).setText(str2);
            ((LayoutParams) this.e.findViewById(R.id.statusText).getLayoutParams()).height = (int) getResources().getDimension(R.dimen.grid_two_line_bar_height);
            a(this.e.findViewById(R.id.statusText), (int) getResources().getDimension(R.dimen.grid_two_line_bar_height));
            a(this.e.findViewById(R.id.llParentViews), (int) getResources().getDimension(R.dimen.grid_two_line_bar_height));
        } else {
            this.e.findViewById(R.id.subHeaderContainer).setVisibility(8);
            a(this.e.findViewById(R.id.statusText), (int) getResources().getDimension(R.dimen.grid_single_line_bar_height));
            a(this.e.findViewById(R.id.llParentViews), (int) getResources().getDimension(R.dimen.grid_single_line_bar_height));
        }
        if (z) {
            ((TextView) this.e.findViewById(R.id.statusText)).setText(R.string.sleep_timer_on);
        } else {
            ((TextView) this.e.findViewById(R.id.statusText)).setText(R.string.sleep_timer_off);
        }
        this.e.setTag(Integer.valueOf(20));
        this.e.setOnClickListener(this);
        return this.e;
    }

    private void a(View view, int i) {
        ((LayoutParams) view.getLayoutParams()).height = i;
    }

    private void b() {
        this.c = LayoutInflater.from(this.mContext);
        this.b = (LinearLayout) this.containerView.findViewById(R.id.settingsContainer);
        this.d = this.containerView.findViewById(R.id.logout_buttonText);
        this.b.removeAllViews();
        this.b.addView(a(this.mContext.getResources().getString(R.string.settings)));
        this.b.addView(b(getString(R.string.language_settings)));
        this.b.addView(a(getString(R.string.title_songs_language), 14, false));
        this.b.addView(a());
        if (Constants.p) {
            this.b.addView(a(getString(R.string.display_language_settings), 140, false));
            this.b.addView(a());
        }
        this.b.addView(b(getString(R.string.playback_settings)));
        this.b.addView(a(getString(R.string.playback), 0, true));
        this.b.addView(a(getString(R.string.gapless_playback_subtitle), 0));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.download_songs), 1, true));
        this.b.addView(a(getString(R.string.gaana_plus_subtitle), 1));
        this.b.addView(a());
        if (c.d()) {
            this.b.addView(b(getString(R.string.equalizer), 16, false));
            this.b.addView(d());
            this.b.addView(a());
        }
        int i = this.mDeviceResManager.b("pref_auto_night_mode_on", false, false) ? 2 : Constants.l ? 1 : 0;
        View a = a(getString(R.string.night_mode), R.array.auto_night_mode_array, i, new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                boolean z = false;
                switch (i) {
                    case 0:
                        SettingsFragment.this.mDeviceResManager.a("pref_auto_night_mode_on", false, false);
                        if (Constants.l) {
                            ((GaanaActivity) SettingsFragment.this.mContext).switchTheme(1 ^ Constants.l);
                            break;
                        }
                        break;
                    case 1:
                        SettingsFragment.this.mDeviceResManager.a("pref_auto_night_mode_on", false, false);
                        if (!Constants.l) {
                            ((GaanaActivity) SettingsFragment.this.mContext).switchTheme(1 ^ Constants.l);
                            break;
                        }
                        break;
                    case 2:
                        if (!SettingsFragment.this.mDeviceResManager.b("pref_auto_night_mode_on", false, false)) {
                            SettingsFragment.this.mDeviceResManager.a("pref_auto_night_mode_on", true, false);
                            if ((GaanaApplication.getInstance().isDayOrNightUsingTwilightCalculator() == 0) == Constants.l) {
                                z = true;
                            }
                            if (!z) {
                                ((GaanaActivity) SettingsFragment.this.mContext).switchTheme(Constants.l ^ 1, true);
                                break;
                            } else {
                                aj.a().a(SettingsFragment.this.mContext, SettingsFragment.this.getString(R.string.toast_auto_nigth_mode_activiated));
                                break;
                            }
                        }
                        break;
                }
                Util.o(SettingsFragment.this.mContext);
            }
        });
        ((TextView) a.findViewById(R.id.tvHeader)).setTextSize(16.0f);
        this.b.addView(a);
        this.b.addView(a());
        c();
        this.b.addView(a());
        this.b.addView(b(getString(R.string.privacy_settings)));
        this.b.addView(a(getString(R.string.push_motifications), 2, false));
        this.b.addView(a());
        CountryData countryData = this.mAppState.getCountryData();
        if (countryData != null && countryData.getEuRegion() == 1) {
            this.b.addView(a(getString(R.string.user_data), 21, false));
            this.b.addView(a());
        }
        this.b.addView(b(getString(R.string.offers_and_promotions)));
        this.b.addView(a(getString(R.string.subscribe_for_gaana_plus), 5, true));
        this.b.addView(a(getString(R.string.subscribe_gaana_plus_subtitle), 5));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.redeem_coupon), 6, false));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.gaana_rewards), 19, false));
        this.b.addView(a());
        this.b.addView(b(getString(R.string.general)));
        if (this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && this.mAppState.getCurrentUser().getUserSubscriptionData().getIsFamilyOwner()) {
            this.b.addView(a(getString(R.string.manage_family_plan), 25, true));
            this.b.addView(a(getString(R.string.manage_family_plan_subtitle), 25));
            this.b.addView(a());
        }
        this.b.addView(a(getString(R.string.rate_app), 7, false));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.share_app), 8, false));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.about_us), 9, false));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.manage_device), 15, false));
        this.b.addView(a());
        this.b.addView(a(getString(R.string.restore_purchase), 10, false));
        this.b.addView(a());
        String str = "";
        try {
            str = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
        }
        if (str.trim().length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.app_version));
            stringBuilder.append(str);
            this.b.addView(c(stringBuilder.toString()));
        }
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            this.d.setVisibility(0);
            this.containerView.findViewById(R.id.logout_buttonText).setVisibility(0);
            this.d.setTag(Integer.valueOf(12));
            this.d.setOnClickListener(this);
            return;
        }
        this.d.setVisibility(8);
        this.containerView.findViewById(R.id.logout_buttonText).setVisibility(8);
    }

    private void c() {
        int sleepTime = ((GaanaActivity) this.mContext).getSleepTime();
        if (sleepTime == 0) {
            this.b.addView(a(getString(R.string.sleep_timer), null, false));
        } else {
            this.b.addView(a(getString(R.string.sleep_timer), getString(R.string.sleep_timer_subtitle).replace(CBConstant.DEFAULT_PAYMENT_URLS, String.valueOf(sleepTime)), true));
            sleepTime = ((GaanaActivity) this.mContext).getCurrentSleepTime();
            a(sleepTime / 60, sleepTime % 60);
        }
        this.a = (TextView) this.containerView.findViewById(R.id.time);
    }

    private View a(String str) {
        View inflate = this.c.inflate(R.layout.view_page_heading, this.b, false);
        ((TextView) inflate.findViewById(R.id.txt_page_title)).setText(str);
        ((TextView) inflate.findViewById(R.id.txt_page_title)).setTypeface(null, 1);
        return inflate;
    }

    private View b(String str) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_titlestrip_orange, this.b, false);
        ((TextView) inflate.findViewById(R.id.titleText)).setText(str);
        ((TextView) inflate.findViewById(R.id.titleText)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        return inflate;
    }

    private View a(String str, int i, boolean z) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_main_title, this.b, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        if (z) {
            inflate.setBackgroundColor(0);
            inflate.getLayoutParams().height = -2;
            inflate.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), (int) getResources().getDimension(R.dimen.activity_horizontal_margin_half), (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);
        }
        inflate.setTag(Integer.valueOf(i));
        inflate.setOnClickListener(this);
        return inflate;
    }

    private View b(String str, int i, boolean z) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_main_title, this.b, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        if (z) {
            inflate.setBackgroundColor(0);
            inflate.getLayoutParams().height = -2;
            inflate.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), (int) getResources().getDimension(R.dimen.activity_horizontal_margin_half), (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);
        }
        return inflate;
    }

    private View c(String str) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_text, this.b, false);
        if (str != null) {
            inflate.findViewById(R.id.subheaderText).setVisibility(0);
            ((TextView) inflate.findViewById(R.id.subheaderText)).setText(str);
        } else {
            inflate.findViewById(R.id.subheaderText).setVisibility(8);
        }
        return inflate;
    }

    private View a(String str, int i) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_sub_title, this.b, false);
        inflate.findViewById(R.id.subheaderText).setVisibility(0);
        ((TextView) inflate.findViewById(R.id.subheaderText)).setText(str);
        inflate.setTag(Integer.valueOf(i));
        inflate.setOnClickListener(this);
        return inflate;
    }

    private View d() {
        View inflate = this.c.inflate(R.layout.view_settings_equalizer, this.b, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.equalizer_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        List arrayList = new ArrayList();
        String c = c.c();
        int a = c.a().a(arrayList, c);
        c.a().a(c);
        recyclerView.setAdapter(new b(arrayList, a, c.a(this.mContext), c.b(this.mContext), c.c(this.mContext), c.d(this.mContext), c.e(this.mContext), c.f(this.mContext)));
        return inflate;
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != 25) {
            Bundle bundle;
            BaseGaanaFragment settingsDetailFragment;
            Intent intent;
            switch (intValue) {
                case 0:
                    bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 0);
                    settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                    break;
                case 1:
                    ((BaseActivity) this.mContext).sendGAEvent("Settings", "Download Settings", null);
                    bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    bundle.putBoolean("NOT_DOWNLOAD", false);
                    settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    an.a().e("click", "ac", "", "SETTINGS", "", "PYMT_PLAN", "", "");
                    ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                    updateView();
                    break;
                case 2:
                    if (!this.mAppState.isAppInOfflineMode()) {
                        if (Util.j(this.mContext)) {
                            bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 2);
                            settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                            break;
                        }
                        ap.a().f(this.mContext);
                        return;
                    }
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                    return;
                case 3:
                    if (!this.mAppState.isAppInOfflineMode()) {
                        if (Util.j(this.mContext)) {
                            bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 3);
                            settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                            break;
                        }
                        ap.a().f(this.mContext);
                        return;
                    }
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                    return;
                case 4:
                    bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 4);
                    settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                    break;
                case 5:
                    ((BaseActivity) this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Settings");
                    this.mAppState.setSidebarActiveBtn(R.id.upgradeButtonLayout);
                    an.a().e("click", "ac", "", "SETTINGS", "", "PYMT_PLAN", "", "");
                    ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
                    break;
                case 6:
                    if (!this.mAppState.isAppInOfflineMode()) {
                        if (Util.j(this.mContext)) {
                            bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 6);
                            settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                            break;
                        }
                        ap.a().f(this.mContext);
                        return;
                    }
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                    return;
                case 7:
                    this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.gaana")));
                    break;
                case 8:
                    intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "Download Gaana ");
                    intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=com.gaana");
                    try {
                        startActivity(Intent.createChooser(intent, getString(R.string.share_app)));
                        break;
                    } catch (ActivityNotFoundException unused) {
                        aj.a().a(this.mContext, getString(R.string.no_apps_share_with));
                        break;
                    }
                case 9:
                    bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 9);
                    settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                    break;
                case 10:
                    f();
                    break;
                case 11:
                    break;
                case 12:
                    if (Util.j(this.mContext)) {
                        new CustomDialogView(this.mContext, getResources().getString(R.string.confirmation_msg_logout), new OnButtonClickListener() {
                            public void onNegativeButtonClick() {
                            }

                            public void onPositiveButtonClick() {
                                ap.a().a(SettingsFragment.this.mContext, true, null, LOGIN_STATUS.LOGGED_OUT);
                            }
                        }).show();
                        break;
                    }
                    ap.a().f(this.mContext);
                    return;
                default:
                    switch (intValue) {
                        case 14:
                            bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 14);
                            settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                            break;
                        case 15:
                            if (!this.mAppState.isAppInOfflineMode()) {
                                if (Util.j(this.mContext)) {
                                    ((GaanaActivity) this.mContext).checkSetLoginStatus(new ad() {
                                        public void onLoginSuccess() {
                                            Intent intent = new Intent(SettingsFragment.this.mContext, WebViewActivity.class);
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("https://gaana.com/gaana_plus&token=");
                                            stringBuilder.append(SettingsFragment.this.mAppState.getCurrentUser().getAuthToken());
                                            stringBuilder.append("&");
                                            stringBuilder.append("deviceId");
                                            stringBuilder.append("=");
                                            stringBuilder.append(Util.l(GaanaApplication.getContext()));
                                            intent.putExtra("EXTRA_WEBVIEW_URL", stringBuilder.toString());
                                            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                            ((Activity) SettingsFragment.this.mContext).startActivityForResult(intent, 708);
                                        }
                                    }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                                    break;
                                } else {
                                    ap.a().f(this.mContext);
                                    return;
                                }
                            }
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                            return;
                        case 16:
                            ((BaseActivity) this.mContext).sendGAEvent("Settings", "Equalizer", "Click");
                            Util.t(this.mContext);
                            break;
                        default:
                            switch (intValue) {
                                case 19:
                                    if (!this.mAppState.isAppInOfflineMode()) {
                                        if (Util.j(this.mContext)) {
                                            intent = new Intent((GaanaActivity) this.mContext, WebViewActivity.class);
                                            String str = "https://gaana.com/rewarddetails/";
                                            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append(str);
                                                stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
                                                str = stringBuilder.toString();
                                            }
                                            intent.putExtra("EXTRA_WEBVIEW_URL", str);
                                            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                                            intent.putExtra("title", this.mContext.getString(R.string.gaana_extras));
                                            startActivity(intent);
                                            break;
                                        }
                                        ap.a().f(this.mContext);
                                        return;
                                    }
                                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                                    return;
                                case 20:
                                    a(20);
                                    break;
                                case 21:
                                    if (!this.mAppState.isAppInOfflineMode()) {
                                        if (Util.j(this.mContext)) {
                                            bundle = new Bundle();
                                            bundle.putInt("KEY_SETTINGS", 21);
                                            settingsDetailFragment = new SettingsDetailFragment();
                                            settingsDetailFragment.setArguments(bundle);
                                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                                            break;
                                        }
                                        ap.a().f(this.mContext);
                                        return;
                                    }
                                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
                                    return;
                                default:
                                    switch (intValue) {
                                        case 140:
                                            if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
                                                intent = new Intent(this.mContext, AppLanguageSettingsScreenActivity.class);
                                                intent.setFlags(71303168);
                                                intent.putExtra("skipEnabled", false);
                                                startActivity(intent);
                                                break;
                                            }
                                        case 141:
                                            bundle = new Bundle();
                                            bundle.putInt("KEY_SETTINGS", 141);
                                            settingsDetailFragment = new SettingsDetailFragment();
                                            settingsDetailFragment.setArguments(bundle);
                                            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                                            break;
                                    }
                                    break;
                            }
                    }
            }
        }
        e();
    }

    private void e() {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.mContext)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://gaana.com/weblink/manage_family/");
            stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
            String stringBuilder2 = stringBuilder.toString();
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", stringBuilder2);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_MANAGE_FAMILY_PLAN", true);
            intent.putExtra("title", this.mContext.getString(R.string.manage_family_plan));
            startActivity(intent);
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void a(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_SETTINGS", i);
        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
        settingsDetailFragment.setArguments(bundle);
        ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
    }

    private void f() {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.mContext)) {
            ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ((BaseActivity) SettingsFragment.this.mContext).showProgressDialog(Boolean.valueOf(true), SettingsFragment.this.getString(R.string.fetching_details_from_server));
                    ag.a((BaseActivity) SettingsFragment.this.mContext).a(SettingsFragment.this.mContext, new au() {
                        public void onUserStatusUpdated() {
                            PurchaseGoogleManager.a(SettingsFragment.this.mContext, SettingsFragment.this, true);
                            ((BaseActivity) SettingsFragment.this.mContext).hideProgressDialog();
                        }
                    }, "");
                }
            }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void onFailure(String str) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        aj.a().a(this.mContext, str);
        if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
            PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
        }
        ((GaanaActivity) this.mContext).onBackPressed();
    }

    public void onInventoryQueryCompeleted(com.iabutils.a aVar, com.iabutils.b bVar) {
        if (aVar.c()) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            aj.a().a(this.mContext, aVar.a());
        }
    }

    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        ((BaseActivity) this.mContext).updateUserStatus(new au() {
            public void onUserStatusUpdated() {
                ((BaseActivity) SettingsFragment.this.mContext).hideProgressDialog();
                ap.a().a(SettingsFragment.this.mContext);
                Util.aa();
                aj.a().a(SettingsFragment.this.mContext, SettingsFragment.this.getString(R.string.enjoy_using_gaana_plus));
                Intent intent = new Intent(SettingsFragment.this.mContext, GaanaActivity.class);
                intent.setFlags(71303168);
                SettingsFragment.this.mContext.startActivity(intent);
            }
        });
        if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
            PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
        }
    }

    public void onDestroyView() {
        if (Util.K()) {
            Util.L();
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void g() {
        if (((GaanaActivity) this.mContext).getCurrentSleepTime() == 0) {
            i();
        } else {
            h();
        }
    }

    private void h() {
        ((GaanaActivity) this.mContext).setSleepTimerListener(new ISleepTimerListener() {
            public void SleepTimerCompleted() {
                SettingsFragment.this.i();
            }

            public void SleepTimerTick(int i, int i2) {
                SettingsFragment.this.a(i, i2);
            }
        });
    }

    private void i() {
        this.containerView.findViewById(R.id.subHeaderContainer).setVisibility(8);
        if (this.e != null) {
            ((TextView) this.e.findViewById(R.id.statusText)).setText(R.string.sleep_timer_off);
            a(this.e.findViewById(R.id.statusText), (int) getResources().getDimension(R.dimen.grid_single_line_bar_height));
            a(this.e.findViewById(R.id.llParentViews), (int) getResources().getDimension(R.dimen.grid_single_line_bar_height));
        }
    }

    private void a(int i, int i2) {
        String valueOf = String.valueOf(i);
        String valueOf2 = String.valueOf(i2);
        if (valueOf.length() == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(i);
            valueOf = stringBuilder.toString();
        }
        if (valueOf2.length() == 1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("0");
            stringBuilder2.append(i2);
            valueOf2 = stringBuilder2.toString();
        }
        if (this.a != null) {
            TextView textView = this.a;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(valueOf);
            stringBuilder3.append(":");
            stringBuilder3.append(valueOf2);
            textView.setText(stringBuilder3.toString());
        }
    }

    private View a(String str, int i, int i2, OnItemSelectedListener onItemSelectedListener) {
        View inflate = this.c.inflate(R.layout.view_settings_listitem_spinner_vertical, this.b, false);
        ((TextView) inflate.findViewById(R.id.tvHeader)).setText(str);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.view_settings_listitem_sub_title_for_spinner, this.mContext.getResources().getStringArray(i));
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_2);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(i2);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        return inflate;
    }
}

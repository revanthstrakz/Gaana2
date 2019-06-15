package com.gaana;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.constants.b;
import com.dynamicview.DynamicViewManager;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.ReferralSignup;
import com.gaana.view.HomeSettingsItemView;
import com.google.gson.Gson;
import com.i.i;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.o;
import com.managers.q;
import com.managers.u;
import com.managers.w;
import com.managers.w.a;
import com.services.d;
import com.services.l.aa;
import com.services.l.af;
import com.services.l.y;
import com.utilities.Util;
import com.utilities.f;
import java.util.List;
import org.json.JSONObject;

public class AppLanguageSettingsScreenActivity extends AppCompatActivity implements OnClickListener {
    int[] attrs = new int[]{R.attr.selector_language_choice, R.attr.first_line_color};
    final String defaultLanguage = "English";
    private boolean isDisplayLanguageSkipEnabled;
    private boolean isFromDeferredDeepLink = false;
    private boolean isSignupFromInside = false;
    private ImageView mBackButton;
    private Button mContinueButton;
    private RelativeLayout mDisplayLanguageLayout;
    private LinearLayout mDisplayLanguageLoadingLayout;
    private TextView mDisplayLanguageTitle;
    private TextView mDisplayLine1;
    private TextView mDisplayLine2;
    private TextView mDisplayLine3;
    private ProgressBar mDyanmicViewLoader;
    private CrossFadeImageView mDynamicViewLoadingDone;
    private b mGaanaResources;
    private ProgressBar mLanguageProgressBar;
    private CrossFadeImageView mLanguageSampleView;
    private LinearLayout mParentLinearLayout;
    private TextView mSkipText;
    OnClickListener radioButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            int childCount = AppLanguageSettingsScreenActivity.this.mParentLinearLayout.getChildCount();
            for (int i = 0; i != childCount; i++) {
                ((RadioGroup) AppLanguageSettingsScreenActivity.this.mParentLinearLayout.getChildAt(i)).clearCheck();
            }
            ((RadioButton) view).setChecked(true);
            Language language = (Language) view.getTag();
            AppLanguageSettingsScreenActivity.this.selectedLanguageString = language.getLanguage();
            AppLanguageSettingsScreenActivity.this.selectedLanguageSampleViewURL = language.getAppTranslationSampleArtwork();
            AppLanguageSettingsScreenActivity.this.mGaanaResources.a(f.a(language.getLanguage()));
            String a = AppLanguageSettingsScreenActivity.this.mGaanaResources.a((int) R.string.your_app_in_your_language);
            String a2 = AppLanguageSettingsScreenActivity.this.mGaanaResources.a((int) R.string.curated_download_suggestion_first_line);
            String a3 = AppLanguageSettingsScreenActivity.this.mGaanaResources.a((int) R.string.curated_download_suggestion_second_line);
            String a4 = AppLanguageSettingsScreenActivity.this.mGaanaResources.a((int) R.string.recently_played);
            AppLanguageSettingsScreenActivity.this.mDisplayLanguageTitle.setText(a);
            AppLanguageSettingsScreenActivity.this.mDisplayLine1.setText(a2);
            AppLanguageSettingsScreenActivity.this.mDisplayLine2.setText(a3);
            AppLanguageSettingsScreenActivity.this.mDisplayLine3.setText(a4);
            AppLanguageSettingsScreenActivity.this.mDisplayLanguageTitle.setAlpha(0.0f);
            AppLanguageSettingsScreenActivity.this.mDisplayLine3.setAlpha(0.0f);
            AppLanguageSettingsScreenActivity.this.mDisplayLine2.setAlpha(0.0f);
            AppLanguageSettingsScreenActivity.this.mDisplayLine1.setAlpha(0.0f);
            AppLanguageSettingsScreenActivity.this.mDisplayLanguageTitle.animate().setDuration(500).alpha(1.0f).start();
            AppLanguageSettingsScreenActivity.this.mDisplayLine1.animate().setDuration(500).alpha(1.0f).start();
            AppLanguageSettingsScreenActivity.this.mDisplayLine2.animate().setDuration(500).alpha(1.0f).start();
            AppLanguageSettingsScreenActivity.this.mDisplayLine3.animate().setDuration(500).alpha(1.0f).start();
            a = language.getTranslated();
            if (TextUtils.isEmpty(a)) {
                a = language.getLanguage();
            }
            Button access$1000 = AppLanguageSettingsScreenActivity.this.mContinueButton;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(AppLanguageSettingsScreenActivity.this.getResources().getString(R.string.continue_with_english));
            stringBuilder.append(" '");
            stringBuilder.append(a.toUpperCase());
            stringBuilder.append("'");
            access$1000.setText(stringBuilder.toString());
        }
    };
    private String selectedLanguageSampleViewURL;
    private String selectedLanguageString;
    private boolean updateDispLang = false;
    private String userServerLanguage = null;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().hide();
        setTheme(R.style.AppDisplayLanguageTheme);
        if (Constants.l) {
            setTheme(R.style.AppDisplayLanguageThemeWhite);
        }
        setContentView((int) R.layout.app_language_settings_screen);
        this.mDisplayLanguageLayout = (RelativeLayout) findViewById(R.id.language_selection_view);
        this.mDisplayLanguageLoadingLayout = (LinearLayout) findViewById(R.id.language_loading_view);
        this.mDyanmicViewLoader = (ProgressBar) findViewById(R.id.progressbar);
        this.mDynamicViewLoadingDone = (CrossFadeImageView) findViewById(R.id.done_button);
        if (getIntent() != null) {
            this.isFromDeferredDeepLink = getIntent().getBooleanExtra("IS_FROM_DEFERRED_DEEPLINK", false);
            if (this.isFromDeferredDeepLink) {
                d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_DISP_LANG", false);
            }
        }
        bundle = getIntent().getExtras();
        if (bundle != null) {
            String string = bundle.getString("language_display", null);
            if (TextUtils.isEmpty(string)) {
                showDisplayChoiceViews();
                return;
            }
            this.selectedLanguageString = string;
            applyLanguage();
            return;
        }
        showDisplayChoiceViews();
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.b.b.a(context));
    }

    private void showDisplayChoiceViews() {
        this.mDisplayLanguageTitle = (TextView) findViewById(R.id.app_language_setting_title);
        this.mDisplayLine1 = (TextView) findViewById(R.id.display_line_1);
        this.mDisplayLine2 = (TextView) findViewById(R.id.display_line_2);
        this.mDisplayLine3 = (TextView) findViewById(R.id.display_line_3);
        Configuration configuration = getResources().getConfiguration();
        this.mGaanaResources = new b(this, configuration.locale, configuration.locale);
        this.isSignupFromInside = getIntent().getBooleanExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
        this.isDisplayLanguageSkipEnabled = getIntent().getBooleanExtra("skipEnabled", true);
        initViews();
        getDynamicTextLayout(getIntent().getExtras() != null ? (List) getIntent().getExtras().getSerializable("languageList") : null);
        if (this.isDisplayLanguageSkipEnabled) {
            u.a().a("DisplayLanguageSelection");
        } else {
            u.a().a("Settings:DisplayLanguageSelection");
        }
        Constants.m = false;
    }

    private void initViews() {
        this.mSkipText = (TextView) findViewById(R.id.txt_skip);
        this.mBackButton = (ImageView) findViewById(R.id.back_button);
        if (!this.isDisplayLanguageSkipEnabled) {
            this.mSkipText.setVisibility(8);
            this.mBackButton.setVisibility(0);
            this.mBackButton.setOnClickListener(this);
        }
        this.mContinueButton = (Button) findViewById(R.id.continue_btn);
        this.mLanguageProgressBar = (ProgressBar) findViewById(R.id.lp_progress_bar);
        this.mParentLinearLayout = (LinearLayout) findViewById(R.id.layout_language_select);
    }

    private void getDynamicTextLayout(List<?> list) {
        if (list == null || list.size() == 0) {
            this.mLanguageProgressBar.setVisibility(0);
            this.mParentLinearLayout.setVisibility(8);
            w.a(GaanaApplication.getInstance()).a((Context) this, new a() {
                public void onLanguagesFetched(Languages languages) {
                    if (languages != null && languages.getArrListBusinessObj() != null) {
                        AppLanguageSettingsScreenActivity.this.mLanguageProgressBar.setVisibility(8);
                        AppLanguageSettingsScreenActivity.this.mParentLinearLayout.setVisibility(0);
                        AppLanguageSettingsScreenActivity.this.displayDynamicTextLayout(languages.getArrListBusinessObj());
                    }
                }
            }, d.a().b("PREFERENCE_APP_DISPLAY_LANGUAGE_SHOWN", false, false) ^ 1);
            return;
        }
        displayDynamicTextLayout(list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:80:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x03c3  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x03cf  */
    /* JADX WARNING: Missing block: B:60:0x01e7, code skipped:
            if (r3.getLanguage().equalsIgnoreCase(r0.updateDispLang ? r1 : r0.defaultLanguage) == false) goto L_0x01f4;
     */
    private void displayDynamicTextLayout(java.util.List<?> r27) {
        /*
        r26 = this;
        r0 = r26;
        r1 = r0.mParentLinearLayout;
        if (r1 == 0) goto L_0x000b;
    L_0x0006:
        r1 = r0.mParentLinearLayout;
        r1.removeAllViews();
    L_0x000b:
        com.gaana.application.GaanaApplication.getInstance();
        r1 = com.gaana.application.GaanaApplication.getLanguage(r26);
        r2 = com.services.d.a();
        r3 = "UPDATE_DISP_LANG";
        r4 = 0;
        r5 = 1;
        r2 = r2.b(r3, r4, r5);
        r0.userServerLanguage = r2;
        r2 = r0.userServerLanguage;
        r3 = 0;
        if (r2 == 0) goto L_0x0032;
    L_0x0025:
        r2 = r0.userServerLanguage;
        r2 = r2.equalsIgnoreCase(r1);
        if (r2 != 0) goto L_0x0032;
    L_0x002d:
        r1 = r0.userServerLanguage;
        r0.updateDispLang = r5;
        goto L_0x0044;
    L_0x0032:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0044;
    L_0x0038:
        r2 = "English";
        r2 = r1.equalsIgnoreCase(r2);
        if (r2 != 0) goto L_0x0044;
    L_0x0040:
        r0.updateDispLang = r5;
        r2 = r5;
        goto L_0x0045;
    L_0x0044:
        r2 = r3;
    L_0x0045:
        r4 = com.services.d.a();
        r6 = "UPDATE_DISP_LANG";
        r4.b(r6, r5);
        r4 = new com.gaana.AppLanguageSettingsScreenActivity$2;
        r4.<init>();
        r6 = r27;
        java.util.Collections.sort(r6, r4);
        r4 = r0.attrs;
        r4 = r0.obtainStyledAttributes(r4);
        r7 = new android.widget.RadioGroup;
        r7.<init>(r0);
        r8 = new android.widget.LinearLayout$LayoutParams;
        r9 = -2;
        r8.<init>(r9, r9);
        r10 = 17;
        r8.gravity = r10;
        r11 = 8;
        r12 = 16;
        r8.setMargins(r12, r3, r12, r11);
        r7.setLayoutParams(r8);
        r7.setOrientation(r3);
        r7.setGravity(r10);
        r11 = new android.util.DisplayMetrics;
        r11.<init>();
        r13 = r26.getWindowManager();
        r13 = r13.getDefaultDisplay();
        r13.getMetrics(r11);
        r11 = r11.widthPixels;
        r13 = r26.getResources();
        r14 = 2131165704; // 0x7f070208 float:1.7945633E38 double:1.05293576E-314;
        r13 = r13.getDimension(r14);
        r13 = (int) r13;
        r15 = r26.getResources();
        r14 = r15.getDimension(r14);
        r14 = (int) r14;
        r15 = r26.getResources();
        r12 = 2131165706; // 0x7f07020a float:1.7945637E38 double:1.052935761E-314;
        r15.getDimension(r12);
        r12 = r26.getResources();
        r15 = 2131165705; // 0x7f070209 float:1.7945635E38 double:1.0529357604E-314;
        r12.getDimension(r15);
        r11 = r11 + -32;
        r12 = r27.size();
        r6 = r27.iterator();
        r17 = r3;
        r15 = r7;
        r18 = r11;
        r7 = r17;
    L_0x00c9:
        r19 = r6.hasNext();
        if (r19 == 0) goto L_0x03bf;
    L_0x00cf:
        r19 = r6.next();
        r3 = r19;
        r3 = (com.gaana.models.Languages.Language) r3;
        r7 = r7 + r5;
        r19 = r3.isAppTranslationEnabled();
        if (r19 == 0) goto L_0x0398;
    L_0x00de:
        r10 = new android.widget.RadioButton;
        r10.<init>(r0);
        r10.setSingleLine(r5);
        r9 = android.text.TextUtils.TruncateAt.END;
        r10.setEllipsize(r9);
        r9 = r3.isPrefered();
        r20 = r6;
        if (r9 != r5) goto L_0x0108;
    L_0x00f3:
        r9 = new android.widget.RadioGroup$LayoutParams;
        r5 = 1106247680; // 0x41f00000 float:30.0 double:5.465589745E-315;
        r5 = com.cast_music.b.d.a(r0, r5);
        r6 = -2;
        r9.<init>(r6, r5);
        r5 = 1096810496; // 0x41600000 float:14.0 double:5.41896386E-315;
        r6 = 2;
        r10.setTextSize(r6, r5);
    L_0x0105:
        r5 = 17;
        goto L_0x011c;
    L_0x0108:
        r6 = 2;
        r9 = new android.widget.RadioGroup$LayoutParams;
        r5 = 1104150528; // 0x41d00000 float:26.0 double:5.455228437E-315;
        r5 = com.cast_music.b.d.a(r0, r5);
        r6 = -2;
        r9.<init>(r6, r5);
        r5 = 1094713344; // 0x41400000 float:12.0 double:5.408602553E-315;
        r6 = 2;
        r10.setTextSize(r6, r5);
        goto L_0x0105;
    L_0x011c:
        r9.gravity = r5;
        r5 = 0;
        r9.setMargins(r5, r5, r13, r5);
        r10.setLayoutParams(r9);
        r5 = -1;
        r6 = 1;
        r5 = r4.getColor(r6, r5);
        r10.setTextColor(r5);
        r5 = r3 instanceof com.gaana.models.Languages.Language;
        if (r5 == 0) goto L_0x0163;
    L_0x0132:
        r5 = r3.getTranslated();
        r6 = android.text.TextUtils.isEmpty(r5);
        if (r6 == 0) goto L_0x0156;
    L_0x013c:
        r5 = r3.getLanguage();
        r6 = android.text.TextUtils.isEmpty(r5);
        if (r6 == 0) goto L_0x0156;
    L_0x0146:
        if (r7 != r12) goto L_0x014d;
    L_0x0148:
        r3 = r0.mParentLinearLayout;
        r3.addView(r15, r8);
    L_0x014d:
        r6 = r20;
        r3 = 0;
        r5 = 1;
        r9 = -2;
        r10 = 17;
        goto L_0x00c9;
    L_0x0156:
        r6 = new android.text.SpannableStringBuilder;
        r6.<init>(r5);
        r5 = android.widget.TextView.BufferType.SPANNABLE;
        r10.setText(r6, r5);
        r10.setTag(r3);
    L_0x0163:
        r5 = 17170445; // 0x106000d float:2.461195E-38 double:8.483327E-317;
        r10.setButtonDrawable(r5);
        r5 = r3.isPrefered();
        r6 = 1;
        if (r5 == r6) goto L_0x01a0;
    L_0x0170:
        r5 = r3.getLanguage();
        r6 = r0.updateDispLang;
        if (r6 == 0) goto L_0x017a;
    L_0x0178:
        r6 = r1;
        goto L_0x017c;
    L_0x017a:
        r6 = r0.defaultLanguage;
    L_0x017c:
        r5 = r5.equalsIgnoreCase(r6);
        if (r5 == 0) goto L_0x0183;
    L_0x0182:
        goto L_0x01a0;
    L_0x0183:
        r5 = 0;
        r10.setPadding(r14, r5, r14, r5);
        r6 = 16;
        r10.setGravity(r6);
        r9 = android.os.Build.VERSION.SDK_INT;
        if (r9 < r6) goto L_0x0198;
    L_0x0190:
        r9 = r4.getDrawable(r5);
        r10.setBackground(r9);
        goto L_0x01bc;
    L_0x0198:
        r9 = r4.getDrawable(r5);
        r10.setBackgroundDrawable(r9);
        goto L_0x01bc;
    L_0x01a0:
        r5 = 0;
        r6 = 16;
        r10.setPadding(r14, r5, r14, r5);
        r10.setGravity(r6);
        r9 = android.os.Build.VERSION.SDK_INT;
        if (r9 < r6) goto L_0x01b5;
    L_0x01ad:
        r6 = r4.getDrawable(r5);
        r10.setBackground(r6);
        goto L_0x01bc;
    L_0x01b5:
        r6 = r4.getDrawable(r5);
        r10.setBackgroundDrawable(r6);
    L_0x01bc:
        r10.measure(r5, r5);
        r5 = r0.radioButtonClickListener;
        r10.setOnClickListener(r5);
        r5 = r10.getMeasuredWidth();
        r6 = r18;
        if (r6 < r5) goto L_0x01f4;
    L_0x01cc:
        if (r17 != 0) goto L_0x01ea;
    L_0x01ce:
        r9 = r3.isPrefered();
        r21 = r6;
        r6 = 1;
        if (r9 == r6) goto L_0x01ec;
    L_0x01d7:
        r6 = r3.getLanguage();
        r9 = r0.updateDispLang;
        if (r9 == 0) goto L_0x01e1;
    L_0x01df:
        r9 = r1;
        goto L_0x01e3;
    L_0x01e1:
        r9 = r0.defaultLanguage;
    L_0x01e3:
        r6 = r6.equalsIgnoreCase(r9);
        if (r6 != 0) goto L_0x01ec;
    L_0x01e9:
        goto L_0x01f4;
    L_0x01ea:
        r21 = r6;
    L_0x01ec:
        r22 = r11;
        r9 = r15;
        r6 = 17;
        r11 = 16;
        goto L_0x0251;
    L_0x01f4:
        r6 = r0.mParentLinearLayout;
        r6.addView(r15, r8);
        if (r17 != 0) goto L_0x022f;
    L_0x01fb:
        r6 = r3.isPrefered();
        r9 = 1;
        if (r6 == r9) goto L_0x022f;
    L_0x0202:
        r6 = new android.widget.LinearLayout$LayoutParams;
        r9 = -2;
        r6.<init>(r9, r9);
        r15 = 17;
        r6.gravity = r15;
        r9 = new android.widget.RadioGroup;
        r9.<init>(r0);
        r9.setLayoutParams(r6);
        r6 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r6 = com.cast_music.b.d.a(r0, r6);
        r22 = r11;
        r11 = 16;
        r15 = 0;
        r9.setPadding(r11, r6, r11, r15);
        r9.setOrientation(r15);
        r6 = 17;
        r9.setGravity(r6);
        r21 = r22;
        r17 = 1;
        goto L_0x0251;
    L_0x022f:
        r22 = r11;
        r6 = 17;
        r11 = 16;
        r15 = 0;
        r9 = new android.widget.RadioGroup;
        r9.<init>(r0);
        r9.setLayoutParams(r8);
        r6 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r6 = com.cast_music.b.d.a(r0, r6);
        r9.setPadding(r11, r6, r11, r15);
        r9.setOrientation(r15);
        r6 = 17;
        r9.setGravity(r6);
        r21 = r22;
    L_0x0251:
        r9.addView(r10);
        r15 = r3.getLanguage();
        r15 = r15.equalsIgnoreCase(r1);
        if (r15 == 0) goto L_0x037f;
    L_0x025e:
        r0.selectedLanguageString = r1;
        r15 = r3.getAppTranslationSampleArtwork();
        r0.selectedLanguageSampleViewURL = r15;
        r15 = r3.getTranslated();
        r16 = android.text.TextUtils.isEmpty(r15);
        if (r16 == 0) goto L_0x0274;
    L_0x0270:
        r15 = r3.getLanguage();
    L_0x0274:
        r3 = r0.updateDispLang;
        if (r3 == 0) goto L_0x034b;
    L_0x0278:
        r3 = r0.mGaanaResources;
        r11 = com.utilities.f.a(r1);
        r3.a(r11);
        r3 = r0.mGaanaResources;
        r11 = 2131822876; // 0x7f11091c float:1.9278536E38 double:1.053260446E-314;
        r3 = r3.a(r11);
        r11 = r0.mGaanaResources;
        r6 = 2131821054; // 0x7f1101fe float:1.927484E38 double:1.053259546E-314;
        r6 = r11.a(r6);
        r11 = r0.mGaanaResources;
        r23 = r1;
        r1 = 2131821055; // 0x7f1101ff float:1.9274842E38 double:1.0532595464E-314;
        r1 = r11.a(r1);
        r11 = r0.mGaanaResources;
        r24 = r14;
        r14 = 2131822245; // 0x7f1106a5 float:1.9277256E38 double:1.0532601343E-314;
        r11 = r11.a(r14);
        r14 = r0.mDisplayLanguageTitle;
        r14.setText(r3);
        r3 = r0.mDisplayLine1;
        r3.setText(r6);
        r3 = r0.mDisplayLine2;
        r3.setText(r1);
        r1 = r0.mDisplayLine3;
        r1.setText(r11);
        r1 = r0.mDisplayLanguageTitle;
        r3 = 0;
        r1.setAlpha(r3);
        r1 = r0.mDisplayLine3;
        r1.setAlpha(r3);
        r1 = r0.mDisplayLine2;
        r1.setAlpha(r3);
        r1 = r0.mDisplayLine1;
        r1.setAlpha(r3);
        r1 = r0.mDisplayLanguageTitle;
        r1 = r1.animate();
        r25 = r4;
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r1 = r1.setDuration(r3);
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1 = r1.alpha(r6);
        r1.start();
        r1 = r0.mDisplayLine1;
        r1 = r1.animate();
        r1 = r1.setDuration(r3);
        r1 = r1.alpha(r6);
        r1.start();
        r1 = r0.mDisplayLine2;
        r1 = r1.animate();
        r1 = r1.setDuration(r3);
        r1 = r1.alpha(r6);
        r1.start();
        r1 = r0.mDisplayLine3;
        r1 = r1.animate();
        r1 = r1.setDuration(r3);
        r1 = r1.alpha(r6);
        r1.start();
        r1 = r0.mContinueButton;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r26.getResources();
        r6 = 2131821032; // 0x7f1101e8 float:1.9274796E38 double:1.053259535E-314;
        r4 = r4.getString(r6);
        r3.append(r4);
        r4 = " '";
        r3.append(r4);
        r4 = r15.toUpperCase();
        r3.append(r4);
        r4 = "'";
        r3.append(r4);
        r3 = r3.toString();
        r1.setText(r3);
    L_0x0349:
        r1 = 1;
        goto L_0x037b;
    L_0x034b:
        r23 = r1;
        r25 = r4;
        r24 = r14;
        r1 = r0.mContinueButton;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r26.getResources();
        r6 = 2131821032; // 0x7f1101e8 float:1.9274796E38 double:1.053259535E-314;
        r4 = r4.getString(r6);
        r3.append(r4);
        r4 = " '";
        r3.append(r4);
        r3.append(r15);
        r4 = "'";
        r3.append(r4);
        r3 = r3.toString();
        r1.setText(r3);
        goto L_0x0349;
    L_0x037b:
        r10.setChecked(r1);
        goto L_0x0386;
    L_0x037f:
        r23 = r1;
        r25 = r4;
        r24 = r14;
        r1 = 1;
    L_0x0386:
        if (r7 != r12) goto L_0x038e;
    L_0x0388:
        r3 = r0.mParentLinearLayout;
        r3.addView(r9, r8);
        goto L_0x0392;
    L_0x038e:
        r21 = r21 - r5;
        r21 = r21 - r13;
    L_0x0392:
        r15 = r9;
        r18 = r21;
        r3 = 17;
        goto L_0x03af;
    L_0x0398:
        r23 = r1;
        r25 = r4;
        r1 = r5;
        r20 = r6;
        r3 = r10;
        r22 = r11;
        r24 = r14;
        r21 = r18;
        if (r7 != r12) goto L_0x03ad;
    L_0x03a8:
        r4 = r0.mParentLinearLayout;
        r4.addView(r15, r8);
    L_0x03ad:
        r18 = r21;
    L_0x03af:
        r5 = r1;
        r10 = r3;
        r6 = r20;
        r11 = r22;
        r1 = r23;
        r14 = r24;
        r4 = r25;
        r3 = 0;
        r9 = -2;
        goto L_0x00c9;
    L_0x03bf:
        r25 = r4;
        if (r2 == 0) goto L_0x03c6;
    L_0x03c3:
        r1 = 0;
        r0.updateDispLang = r1;
    L_0x03c6:
        r1 = r0.mContinueButton;
        r1.setOnClickListener(r0);
        r1 = r0.isDisplayLanguageSkipEnabled;
        if (r1 == 0) goto L_0x03d4;
    L_0x03cf:
        r1 = r0.mSkipText;
        r1.setOnClickListener(r0);
    L_0x03d4:
        r1 = r25;
        r1.recycle();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.AppLanguageSettingsScreenActivity.displayDynamicTextLayout(java.util.List):void");
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            finish();
        } else if (id == R.id.continue_btn) {
            resetDeferredDeepLinking();
            applyLanguage();
        } else if (id == R.id.txt_skip) {
            resetDeferredDeepLinking();
            if (this.isDisplayLanguageSkipEnabled) {
                u.a().a("DisplayLanguageSelection", "Skip", "DisplayLanguageSelection-Skip");
            } else {
                u.a().a("Settings:DisplayLanguageSelection", "Skip", "DisplayLanguageSelection-Skip");
            }
            if (this.updateDispLang) {
                applyLanguage();
            } else {
                applyDefaultLanguage(this.isSignupFromInside);
            }
        }
    }

    private void resetDeferredDeepLinking() {
        if (this.isFromDeferredDeepLink) {
            GaanaApplication.onBoardingSkipped = false;
            d.a().b("DEFERRED_DEEPLINK_ONBOARDING_STATE", false);
        }
    }

    private void applyDefaultLanguage(boolean z) {
        Intent intent = new Intent(getApplicationContext(), GaanaActivity.class);
        if (z) {
            intent.setFlags(71303168);
        } else {
            intent.setFlags(335544320);
        }
        if (checkDisableInternationalOnBoarding()) {
            startActivity(intent);
        }
        finish();
    }

    private void applyLanguage() {
        if (Util.j((Context) this)) {
            this.updateDispLang = false;
            this.mDisplayLanguageLayout.setVisibility(8);
            this.mDisplayLanguageLoadingLayout.setVisibility(0);
            GaanaApplication.getInstance();
            GaanaApplication.setLanguage(getApplicationContext(), this.selectedLanguageString, new aa() {
                public void onFontRetrieved(Typeface typeface) {
                    if (!AppLanguageSettingsScreenActivity.this.checkDisableInternationalOnBoarding()) {
                        AppLanguageSettingsScreenActivity.this.finish();
                    } else if (Util.j(AppLanguageSettingsScreenActivity.this)) {
                        j.a().a("https://apiv2.gaana.com/radio/metadata");
                        DynamicViewManager.a().a(new y() {
                            public void OnDynamicViewDataFetched() {
                                AppLanguageSettingsScreenActivity.this.updateDisplayLanguageOnServer();
                                if (!TextUtils.isEmpty(AppLanguageSettingsScreenActivity.this.selectedLanguageString)) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("RTlang:");
                                    stringBuilder.append(AppLanguageSettingsScreenActivity.this.selectedLanguageString);
                                    q.a().a("int", stringBuilder.toString());
                                }
                                d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                                d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                                j.a().c().d().a();
                                o.a().b();
                                if (GaanaApplication.getInstance().getCurrentUser() != null && GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                                    com.e.a.f.a().b();
                                    PlaylistSyncManager.getInstance().syncOnLogin();
                                }
                                AppLanguageSettingsScreenActivity.this.mDyanmicViewLoader.setVisibility(8);
                                AppLanguageSettingsScreenActivity.this.mDynamicViewLoadingDone.setVisibility(0);
                                if (AppLanguageSettingsScreenActivity.this.isDisplayLanguageSkipEnabled) {
                                    u.a().a("DisplayLanguageSelection", "Submit", AppLanguageSettingsScreenActivity.this.selectedLanguageString);
                                } else {
                                    u.a().a("Settings:DisplayLanguageSelection", "Submit", AppLanguageSettingsScreenActivity.this.selectedLanguageString);
                                }
                                Intent intent = new Intent(AppLanguageSettingsScreenActivity.this.getApplicationContext(), GaanaActivity.class);
                                if (AppLanguageSettingsScreenActivity.this.isSignupFromInside) {
                                    intent.setFlags(71303168);
                                } else {
                                    intent.setFlags(335544320);
                                }
                                if (AppLanguageSettingsScreenActivity.this.isDisplayLanguageSkipEnabled) {
                                    intent.putExtra("onboarding_display_language_set", true);
                                }
                                AppLanguageSettingsScreenActivity.this.startActivity(intent);
                                AppLanguageSettingsScreenActivity.this.finish();
                            }
                        }, AppLanguageSettingsScreenActivity.this);
                    } else {
                        Toast.makeText(AppLanguageSettingsScreenActivity.this, "Network not available", 1).show();
                    }
                }

                public void onError(String str) {
                    AppLanguageSettingsScreenActivity.this.selectedLanguageString = Constants.bV;
                    AppLanguageSettingsScreenActivity.this.applyDefaultLanguage(AppLanguageSettingsScreenActivity.this.isSignupFromInside);
                    Toast.makeText(AppLanguageSettingsScreenActivity.this, AppLanguageSettingsScreenActivity.this.getResources().getString(R.string.language_apply_error), 1).show();
                }
            });
            return;
        }
        Toast.makeText(this, "Network not available", 1).show();
        applyDefaultLanguage(this.isSignupFromInside);
    }

    public void onBackPressed() {
        resetDeferredDeepLinking();
        if (this.updateDispLang) {
            applyLanguage();
            return;
        }
        if (this.isDisplayLanguageSkipEnabled) {
            Intent intent = new Intent(getApplicationContext(), GaanaActivity.class);
            if (this.isSignupFromInside) {
                intent.setFlags(71303168);
            } else {
                intent.setFlags(335544320);
            }
            if (checkDisableInternationalOnBoarding()) {
                startActivity(intent);
            }
        }
        finish();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean checkDisableInternationalOnBoarding() {
        if (!Constants.T) {
            return true;
        }
        Constants.T = false;
        d a = d.a();
        if (a.c("PREF_KEY_REFERRAL_INFO", false) == null) {
            return true;
        }
        ReferralSignup referralSignup = (ReferralSignup) new Gson().fromJson(a.c("PREF_KEY_REFERRAL_INFO", false), ReferralSignup.class);
        a.b("PREF_KEY_REFERRAL_INFO", false);
        Intent intent = new Intent(getApplicationContext(), ReferralSignupActivity.class);
        intent.setFlags(603979776);
        intent.putExtra("is_first_ap_launch", true);
        intent.putExtra("referralInfo", referralSignup);
        intent.putExtra("FROM_INTERNATIONAL_ONBOARDING", true);
        startActivity(intent);
        return false;
    }

    private void updateDisplayLanguageOnServer() {
        String replace = "https://api.gaana.com/user.php?type=set_user_language_setting&display_language=<display_language>".replace("<display_language>", this.selectedLanguageString);
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(replace);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            replace = stringBuilder.toString();
            Util.b(HomeSettingsItemView.SETTINGS_TAG_DISPLAY_LANGUAGE, this.selectedLanguageString);
            Util.L();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(String.class);
        uRLManager.a(replace);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.i(false);
        if (!TextUtils.isEmpty(this.selectedLanguageString)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("DispLang:");
            stringBuilder2.append(this.selectedLanguageString);
            q.a().a("ua", stringBuilder2.toString());
        }
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                try {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    jSONObject.getString("message");
                    int i = jSONObject.getInt("status");
                } catch (Exception unused) {
                }
            }
        }, uRLManager);
    }
}

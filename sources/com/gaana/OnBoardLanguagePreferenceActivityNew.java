package com.gaana;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.ReferralSignup;
import com.gaana.view.AdjustableImageView;
import com.google.gson.Gson;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.f;
import com.managers.o;
import com.managers.u;
import com.managers.w;
import com.managers.w.a;
import com.managers.w.b;
import com.services.c;
import com.utilities.Util;
import com.utilities.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OnBoardLanguagePreferenceActivityNew extends AppCompatActivity implements OnClickListener {
    private Button btnAllDone;
    private boolean fromSavedList = false;
    private boolean isDeferredDeeplinkLaunched = false;
    private boolean isFromDeferredDeepLink = false;
    private boolean isSignupFromInside = false;
    private boolean isSkipEnabled = false;
    private boolean isTempPreferred = false;
    private boolean launchHomeScreen = true;
    private LanguageAdapter mAdapter;
    private ArrayList<Language> mLanguageList = new ArrayList();
    private String mLastPreferredLanguage = null;
    private ListView mListView;
    private ProgressBar mLoadingProgress;
    private RelativeLayout mParentLayout;
    private ProgressDialog mProgressDialog = null;
    private ArrayList<Language> mSavedList = new ArrayList();
    private ArrayList<Language> mSelectedLanguageList;
    private int preferredLanguageCount = 0;
    private int selected_languages = 0;

    private class LanguageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        private class ViewHolder {
            public View lang_grd;
            public CrossFadeImageView languageBg;
            public ImageView languageSelection;
            public TextView languageText;
            public TextView translatedText;

            private ViewHolder() {
            }

            /* synthetic */ ViewHolder(LanguageAdapter languageAdapter, AnonymousClass1 anonymousClass1) {
                this();
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        LanguageAdapter(ArrayList<Language> arrayList) {
            OnBoardLanguagePreferenceActivityNew.this.mLanguageList = arrayList;
            Collections.sort(OnBoardLanguagePreferenceActivityNew.this.mLanguageList, new Comparator<Language>(OnBoardLanguagePreferenceActivityNew.this) {
                public int compare(Language language, Language language2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(language2.isPrefered());
                    stringBuilder.append("");
                    String stringBuilder2 = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(language.isPrefered());
                    stringBuilder.append("");
                    return stringBuilder2.compareTo(stringBuilder.toString());
                }
            });
            this.mInflater = LayoutInflater.from(OnBoardLanguagePreferenceActivityNew.this);
        }

        public int getCount() {
            return OnBoardLanguagePreferenceActivityNew.this.mLanguageList.size();
        }

        public Object getItem(int i) {
            return OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                if (d.b()) {
                    view = this.mInflater.inflate(R.layout.view_item_onboard_language_preference, viewGroup, false);
                } else {
                    view = this.mInflater.inflate(R.layout.view_item_onboard_language_preference_adjustable, viewGroup, false);
                }
                viewHolder = new ViewHolder(this, null);
                viewHolder.languageText = (TextView) view.findViewById(R.id.language_name);
                viewHolder.translatedText = (TextView) view.findViewById(R.id.language_name_translted);
                viewHolder.translatedText.setTypeface(Typeface.createFromAsset(OnBoardLanguagePreferenceActivityNew.this.getBaseContext().getAssets(), "fonts/Roboto-Bold.ttf"));
                viewHolder.languageSelection = (ImageView) view.findViewById(R.id.language_selected);
                viewHolder.lang_grd = view.findViewById(R.id.lang_bg_grd);
                if (d.b()) {
                    viewHolder.languageBg = (CrossFadeImageView) view.findViewById(R.id.language_bg_img);
                } else {
                    viewHolder.languageBg = (AdjustableImageView) view.findViewById(R.id.language_bg_img);
                }
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Language language = (Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i);
            String translated = language.getTranslated();
            if (TextUtils.isEmpty(translated) || language.getLanguage().equalsIgnoreCase(translated)) {
                viewHolder.languageText.setText("");
                viewHolder.translatedText.setText(language.getLanguage());
            } else {
                viewHolder.languageText.setText(language.getLanguage());
                viewHolder.translatedText.setText(language.getTranslated());
            }
            if (((Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i)).isPrefered() == 1) {
                viewHolder.languageSelection.setVisibility(0);
                viewHolder.lang_grd.setVisibility(0);
                OnBoardLanguagePreferenceActivityNew.this.mLastPreferredLanguage = ((Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i)).getLanguage();
            } else {
                viewHolder.languageSelection.setVisibility(8);
                viewHolder.lang_grd.setVisibility(8);
            }
            if (!TextUtils.isEmpty(language.getLanguage_img_url())) {
                viewHolder.languageBg.bindImage(language.getLanguage_img_url());
            }
            return view;
        }
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().hide();
        if (Constants.l) {
            setTheme(R.style.OnboardLanguagePreferenceWhiteTheme);
        }
        setContentView((int) R.layout.activity_on_board_language_preference_new);
        initViews();
        u.a().a("LangaugeSelection");
        if (getIntent() != null) {
            this.isFromDeferredDeepLink = getIntent().getBooleanExtra("IS_FROM_DEFERRED_DEEPLINK", false);
            if (this.isFromDeferredDeepLink) {
                com.services.d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_SONG_LANG", false);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        comScore.onEnterForeground();
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ImageView imageView = (ImageView) view.findViewById(R.id.language_selected);
                view = view.findViewById(R.id.lang_bg_grd);
                if (((Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i)).isPrefered() == 1) {
                    imageView.setVisibility(8);
                    view.setVisibility(8);
                    ((Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i)).setIsPrefered(0);
                    OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount = OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount - 1;
                } else {
                    OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount = OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount + 1;
                    imageView.setVisibility(0);
                    view.setVisibility(0);
                    ((Language) OnBoardLanguagePreferenceActivityNew.this.mLanguageList.get(i)).setIsPrefered(1);
                }
                CharSequence string = OnBoardLanguagePreferenceActivityNew.this.getResources().getString(R.string.language_preference_submit);
                if (OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(string);
                    stringBuilder.append(" (");
                    stringBuilder.append(OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount);
                    stringBuilder.append(")");
                    string = stringBuilder.toString();
                }
                OnBoardLanguagePreferenceActivityNew.this.btnAllDone.setText(string);
                if (OnBoardLanguagePreferenceActivityNew.this.preferredLanguageCount == 0) {
                    OnBoardLanguagePreferenceActivityNew.this.btnAllDone.setBackgroundDrawable(OnBoardLanguagePreferenceActivityNew.this.getResources().getDrawable(R.drawable.shape_continue_disabled));
                } else {
                    OnBoardLanguagePreferenceActivityNew.this.btnAllDone.setBackgroundDrawable(OnBoardLanguagePreferenceActivityNew.this.getResources().getDrawable(R.drawable.shape_continue_btn));
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        comScore.onExitForeground();
    }

    private void initViews() {
        this.mListView = (ListView) findViewById(R.id.list_view);
        this.btnAllDone = (Button) findViewById(R.id.btn_all_done);
        this.mLoadingProgress = (ProgressBar) findViewById(R.id.lp_progress_bar);
        this.mParentLayout = (RelativeLayout) findViewById(R.id.parent_ll_layout);
        this.launchHomeScreen = getIntent().getBooleanExtra("ONBOARD_LAUNCH_HOME_SCREEN", true);
        this.isSignupFromInside = getIntent().getBooleanExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
        if (getIntent().getSerializableExtra("ONBOARD_SAVED_LANG_PREF") != null) {
            this.mSavedList = (ArrayList) getIntent().getSerializableExtra("ONBOARD_SAVED_LANG_PREF");
            if (this.mSavedList != null) {
                this.fromSavedList = true;
            }
        }
        if (Util.j((Context) this) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            w.a(GaanaApplication.getInstance()).a((Context) this, new a() {
                public void onLanguagesFetched(Languages languages) {
                    OnBoardLanguagePreferenceActivityNew.this.mLoadingProgress.setVisibility(8);
                    if (languages == null || languages.getArrListBusinessObj() == null) {
                        OnBoardLanguagePreferenceActivityNew.this.launchHome();
                        return;
                    }
                    if (languages.getSkipEnabled()) {
                        OnBoardLanguagePreferenceActivityNew.this.isSkipEnabled = true;
                        OnBoardLanguagePreferenceActivityNew.this.findViewById(R.id.txt_skip).setVisibility(0);
                        OnBoardLanguagePreferenceActivityNew.this.findViewById(R.id.txt_skip).setOnClickListener(OnBoardLanguagePreferenceActivityNew.this);
                    }
                    TextView textView = (TextView) OnBoardLanguagePreferenceActivityNew.this.findViewById(R.id.pref_header_text);
                    textView.setTypeface(Typeface.createFromAsset(OnBoardLanguagePreferenceActivityNew.this.getBaseContext().getAssets(), "fonts/Roboto-Bold.ttf"));
                    if (TextUtils.isEmpty(languages.getLanguageTitle())) {
                        textView.setText(OnBoardLanguagePreferenceActivityNew.this.getResources().getString(R.string.language_preference_header_text));
                    } else {
                        textView.setText(languages.getLanguageTitle());
                    }
                    if (languages.getAppDisplayPageNeededToDisplay()) {
                        Constants.p = true;
                    }
                    Constants.t = languages.getWait_time_switch();
                    Constants.s = languages.getWait_time();
                    Constants.q = languages.getLogin_switch();
                    Constants.r = languages.getLogin_skip();
                    Constants.z = languages.getAutologin_email();
                    Constants.A = languages.getAutologin_email_switch();
                    Constants.B = languages.getMandatory_signup();
                    com.services.d.a().a("PREFERENCE_MANDATORY_SIGNUP", Constants.B, false);
                    OnBoardLanguagePreferenceActivityNew.this.btnAllDone.setOnClickListener(OnBoardLanguagePreferenceActivityNew.this);
                    ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                    if (arrListBusinessObj.size() == OnBoardLanguagePreferenceActivityNew.this.mSavedList.size() && com.services.d.a().b("ONBOARD_NEW_USER", false, false)) {
                        OnBoardLanguagePreferenceActivityNew.this.mAdapter = new LanguageAdapter(OnBoardLanguagePreferenceActivityNew.this.mSavedList);
                        OnBoardLanguagePreferenceActivityNew.this.mListView.setAdapter(OnBoardLanguagePreferenceActivityNew.this.mAdapter);
                    } else {
                        OnBoardLanguagePreferenceActivityNew.this.mAdapter = new LanguageAdapter(arrListBusinessObj);
                        OnBoardLanguagePreferenceActivityNew.this.mListView.setAdapter(OnBoardLanguagePreferenceActivityNew.this.mAdapter);
                    }
                    OnBoardLanguagePreferenceActivityNew.this.setSelectedLanguageCount();
                }
            }, false);
        }
    }

    private void saveLanguageSettings() {
        String str;
        int i;
        this.mSelectedLanguageList = new ArrayList();
        Languages languages = new Languages();
        String str2 = "";
        try {
            this.preferredLanguageCount = 0;
            str = str2;
            int i2 = 0;
            i = i2;
            while (i2 < this.mLanguageList.size()) {
                try {
                    int i3 = ((Language) this.mLanguageList.get(i2)).isPrefered() == 1 ? 1 : 0;
                    i += i3;
                    this.mSelectedLanguageList.add(languages.getLanguage(((Language) this.mLanguageList.get(i2)).getLanguage(), i3));
                    if (i3 == 1) {
                        String stringBuilder;
                        this.mLastPreferredLanguage = ((Language) this.mLanguageList.get(i2)).getLanguage();
                        this.preferredLanguageCount++;
                        StringBuilder stringBuilder2;
                        if (str.equals("")) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str);
                            stringBuilder2.append(((Language) this.mLanguageList.get(i2)).getLanguage());
                            stringBuilder = stringBuilder2.toString();
                        } else {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str);
                            stringBuilder2.append(",");
                            stringBuilder2.append(((Language) this.mLanguageList.get(i2)).getLanguage());
                            stringBuilder = stringBuilder2.toString();
                        }
                        str = stringBuilder;
                    }
                    i2++;
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            str = str2;
            i = 0;
        }
        if (i == 0) {
            hideProgressDialog();
            Toast.makeText(this, getString(R.string.SELECT_LANGUAGE), 1).show();
            return;
        }
        if (!Util.j((Context) this)) {
            hideProgressDialog();
            aj.a().a(this, getResources().getString(R.string.error_msg_no_connection));
        } else if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            hideProgressDialog();
            askUserToGoOnline(getResources().getString(R.string.error_msg_feature_not_available_offline));
        } else {
            w.a(GaanaApplication.getInstance()).a((Context) this, this.mSelectedLanguageList, new b() {
                public void onLanguageSavedOnServer(String str, boolean z) {
                    if (z) {
                        OnBoardLanguagePreferenceActivityNew.this.hideProgressDialog();
                        MoEngage.getInstance().reportLanguageSet(OnBoardLanguagePreferenceActivityNew.this.mSelectedLanguageList);
                        u.a().a("LangaugeSelection", "Submit", str);
                        o.a().b();
                        aj.a().a(OnBoardLanguagePreferenceActivityNew.this, str);
                        if (!OnBoardLanguagePreferenceActivityNew.this.isSignupFromInside) {
                            OnBoardLanguagePreferenceActivityNew.this.launchHomeScreen = true;
                        }
                        Intent intent = new Intent(GaanaApplication.getContext(), OnBoardArtistPreferenceActivity.class);
                        intent.setFlags(805339136);
                        GaanaApplication.getContext().startActivity(intent);
                        f.v().a(GaanaApplication.getInstance().getCurrentUser());
                        return;
                    }
                    aj.a().a(OnBoardLanguagePreferenceActivityNew.this, OnBoardLanguagePreferenceActivityNew.this.getResources().getString(R.string.error_updating_languages));
                }
            });
        }
    }

    private void setSelectedLanguageCount() {
        for (int i = 0; i < this.mLanguageList.size(); i++) {
            if (((Language) this.mLanguageList.get(i)).isPrefered() == 1) {
                this.preferredLanguageCount++;
            }
            CharSequence string = getResources().getString(R.string.language_preference_submit);
            if (this.preferredLanguageCount > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(string);
                stringBuilder.append(" (");
                stringBuilder.append(this.preferredLanguageCount);
                stringBuilder.append(")");
                string = stringBuilder.toString();
            }
            this.btnAllDone.setText(string);
        }
    }

    private void askUserToGoOnline(String str) {
        new com.services.f(this).a(getString(R.string.app_name), str, Boolean.valueOf(true), getString(R.string.go_online_text), getString(R.string.cancel), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                com.services.d.a().a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                com.services.d.a().a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                com.services.d.a().a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                GaanaApplication.getInstance().setAppInOfflineMode(false);
                DownloadManager.c().d();
                OnBoardLanguagePreferenceActivityNew.this.saveLanguageSettings();
            }
        });
    }

    private void launchHome() {
        hideProgressDialog();
        Intent intent;
        if (c.a((Context) this).a((Context) this, GaanaApplication.getInstance(), false)) {
            showProgressDialog(getString(R.string.loading));
            this.isDeferredDeeplinkLaunched = true;
        } else if (!this.launchHomeScreen) {
            finish();
        } else if (Constants.q == 1 && (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus())) {
            intent = new Intent(getApplicationContext(), OnBoardArtistPreferenceActivity.class);
            intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", true);
            intent.putExtra("languageList", this.mLanguageList);
            if (this.isFromDeferredDeepLink) {
                intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                com.services.d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_LOGIN", false);
            }
            if (GaanaApplication.onBoardingSkipped && GaanaApplication.sessionHistoryCount > 0) {
                intent.addFlags(805339136);
            }
            startActivity(intent);
            finish();
        } else if (!Constants.p || (this.preferredLanguageCount <= 1 && (this.preferredLanguageCount != 1 || this.mLastPreferredLanguage.equalsIgnoreCase("English")))) {
            if (this.isFromDeferredDeepLink) {
                com.services.d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", null, false);
            }
            intent = new Intent(getApplicationContext(), GaanaActivity.class);
            if (this.isSignupFromInside) {
                intent.setFlags(71303168);
            } else {
                intent.setFlags(335544320);
            }
            if (checkDisableInternationalOnBoarding()) {
                startActivity(intent);
            }
        } else {
            intent = new Intent(getApplicationContext(), AppLanguageSettingsScreenActivity.class);
            intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", this.isSignupFromInside);
            intent.putExtra("languageList", this.mLanguageList);
            if (this.isFromDeferredDeepLink) {
                intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                com.services.d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_DISP_LANG", false);
            }
            startActivity(intent);
            finish();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean checkDisableInternationalOnBoarding() {
        if (!Constants.T) {
            return true;
        }
        Constants.T = false;
        com.services.d a = com.services.d.a();
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

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_all_done) {
            com.services.d.a().a("PREFERENCE_LANGUAGE_ONBOARD", 1, false);
            showProgressDialog(getString(R.string.saving));
            saveLanguageSettings();
        } else if (id == R.id.txt_skip) {
            hideProgressDialog();
            skipOnboardLangPrefScreen();
        }
    }

    private void showProgressDialog(String str) {
        try {
            StringBuilder stringBuilder;
            if (this.mProgressDialog == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\t");
                this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, false);
            } else if (this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\t");
                this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, false);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\t");
                this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, false);
            }
        } catch (Exception unused) {
        }
    }

    private void hideProgressDialog() {
        try {
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    private void skipOnboardLangPrefScreen() {
        if (this.isTempPreferred) {
            ((Language) this.mLanguageList.get(0)).setIsPrefered(0);
            this.isTempPreferred = false;
        }
        com.services.d.a().a("PREFERENCE_LANGUAGE_ONBOARD", -1, false);
        u.a().a("LangaugeSelection", "Skip", "LangaugeSelection-Skip");
        launchHome();
    }

    public void onBackPressed() {
        if (this.isSkipEnabled) {
            skipOnboardLangPrefScreen();
        } else {
            super.onBackPressed();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        if (this.isDeferredDeeplinkLaunched) {
            finish();
        }
    }
}

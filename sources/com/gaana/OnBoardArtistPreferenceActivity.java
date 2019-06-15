package com.gaana;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.gaana.adapter.ArtistSelectionAdapter;
import com.gaana.adapter.ArtistSelectionAdapter.PreferedArtistSelectedListener;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.PreferedArtists;
import com.gaana.models.PreferedArtists.PreferedArtist;
import com.gaana.models.ReferralSignup;
import com.google.gson.Gson;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.b.a;
import com.managers.f;
import com.managers.o;
import com.managers.u;
import com.services.c;
import com.services.d;
import com.services.l.af;
import com.utilities.Util;
import com.views.b;
import java.util.ArrayList;

public class OnBoardArtistPreferenceActivity extends AppCompatActivity implements OnClickListener {
    ArtistSelectionAdapter adapter;
    private RecyclerView artistRecyclerView = null;
    private Button btn_all_done;
    private boolean isFromDeferredDeepLink = false;
    private boolean isSignupFromInside = false;
    private boolean isTempPreferred = false;
    private boolean launchHomeScreen = true;
    private ArrayList<Artist> mArtistList = new ArrayList();
    private ProgressDialog mProgressDialog = null;
    private TextView mSkipText;
    TextView prefHeaderText;
    private int preferredArtistCount = 0;

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
        setContentView((int) R.layout.on_board_artist_preference);
        u.a().a("ArtistSelection");
        initViews();
        getOnBoardArtists();
    }

    private void initViews() {
        this.mSkipText = (TextView) findViewById(R.id.txt_skip);
        this.mSkipText.setVisibility(0);
        this.mSkipText.setOnClickListener(this);
        this.artistRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.artistRecyclerView.addItemDecoration(new b(Util.b(getResources().getInteger(R.integer.num_artists_grid_space))));
        this.btn_all_done = (Button) findViewById(R.id.btn_all_done);
        this.btn_all_done.setOnClickListener(this);
        this.prefHeaderText = (TextView) findViewById(R.id.pref_header_text);
        this.prefHeaderText.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Bold.ttf"));
        this.launchHomeScreen = getIntent().getBooleanExtra("ONBOARD_LAUNCH_HOME_SCREEN", true);
        this.isSignupFromInside = getIntent().getBooleanExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
        if (getIntent() != null) {
            this.isFromDeferredDeepLink = getIntent().getBooleanExtra("IS_FROM_DEFERRED_DEEPLINK", false);
            if (this.isFromDeferredDeepLink) {
                d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_SONG_LANG", false);
            }
        }
    }

    private void launchHome() {
        hideProgressDialog();
        Intent intent;
        if (c.a((Context) this).a((Context) this, GaanaApplication.getInstance(), false)) {
            showProgressDialog(getString(R.string.loading));
            finish();
        } else if (!this.launchHomeScreen) {
            finish();
        } else if (Constants.q == 1 && (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus())) {
            intent = new Intent(getApplicationContext(), Login.class);
            intent.putExtra("artistList", this.mArtistList);
            if (this.isFromDeferredDeepLink) {
                intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_LOGIN", false);
            }
            if (GaanaApplication.onBoardingSkipped && GaanaApplication.sessionHistoryCount > 0) {
                intent.addFlags(805339136);
            }
            startActivity(intent);
            finish();
        } else if (!Constants.p || (this.preferredArtistCount <= 1 && this.preferredArtistCount != 1)) {
            if (this.isFromDeferredDeepLink) {
                d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", null, false);
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
            intent.putExtra("artistList", this.mArtistList);
            if (this.isFromDeferredDeepLink) {
                intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_DISP_LANG", false);
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

    private void skipOnboardArtistPrefScreen() {
        if (this.isTempPreferred) {
            this.isTempPreferred = false;
        }
        d.a().a("PREFERENCE_ARTIST_ONBOARD", -1, false);
        u.a().a("ArtistSelection", "Skip", "ArtistSelection-Skip");
        launchHome();
    }

    private void hideProgressDialog() {
        try {
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (Exception unused) {
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

    private void getOnBoardArtists() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/splash/language/artist?userLanguage=<languages>".replace("<languages>", GaanaApplication.getInstance().getSongLanguagesString()));
        uRLManager.c(0);
        uRLManager.a(PreferedArtists.class);
        uRLManager.b(Boolean.valueOf(true));
        showProgressDialog(getString(R.string.loading));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                OnBoardArtistPreferenceActivity.this.hideProgressDialog();
                PreferedArtists preferedArtists = (PreferedArtists) obj;
                OnBoardArtistPreferenceActivity.this.prefHeaderText.setText(preferedArtists.getEntityDescription());
                OnBoardArtistPreferenceActivity.this.populateArtists(preferedArtists.getPreferedArtists());
            }
        }, uRLManager);
    }

    private void populateArtists(ArrayList<PreferedArtist> arrayList) {
        this.adapter = new ArtistSelectionAdapter();
        this.adapter.setAdapterParameters(arrayList, this);
        this.artistRecyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.num_artists_grid)));
        this.artistRecyclerView.setAdapter(this.adapter);
        setSelectedArtistsCount();
        this.adapter.setPreferedArtistSelectedListener(new PreferedArtistSelectedListener() {
            public void ArtistSeclected(boolean z) {
                OnBoardArtistPreferenceActivity.this.setSelectedArtistsCount();
            }
        });
    }

    private void setSelectedArtistsCount() {
        int size = this.adapter.getSelectedArtists().size();
        if (size > 0) {
            Button button = this.btn_all_done;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getResources().getString(R.string.continue_button));
            stringBuilder.append(" (");
            stringBuilder.append(size);
            stringBuilder.append(")");
            button.setText(stringBuilder.toString());
            return;
        }
        this.btn_all_done.setText(getResources().getString(R.string.continue_button));
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        comScore.onExitForeground();
    }

    private void saveArtistSettings() {
        String str;
        int i;
        String str2 = "";
        try {
            this.preferredArtistCount = 0;
            str = str2;
            int i2 = 0;
            i = i2;
            while (i2 < this.adapter.getSelectedArtists().size()) {
                try {
                    int i3 = ((PreferedArtist) this.adapter.getSelectedArtists().get(i2)).isPrefered() ? 1 : 0;
                    i += i3;
                    if (i3 == 1) {
                        String stringBuilder;
                        this.preferredArtistCount++;
                        StringBuilder stringBuilder2;
                        if (str.equals("")) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str);
                            stringBuilder2.append(((PreferedArtist) this.adapter.getSelectedArtists().get(i2)).getArtistId());
                            stringBuilder = stringBuilder2.toString();
                        } else {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str);
                            stringBuilder2.append(",");
                            stringBuilder2.append(((PreferedArtist) this.adapter.getSelectedArtists().get(i2)).getArtistId());
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
            Toast.makeText(this, getString(R.string.SELECT_ARTIST), 1).show();
            return;
        }
        if (!Util.j((Context) this)) {
            hideProgressDialog();
            aj.a().a(this, getResources().getString(R.string.error_msg_no_connection));
        } else if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            hideProgressDialog();
            askUserToGoOnline(getResources().getString(R.string.error_msg_feature_not_available_offline));
        } else {
            com.managers.b.a(GaanaApplication.getInstance()).a(this, this.adapter.getSelectedArtists(), new a() {
                public void onArtistsSavedOnServer(String str, boolean z) {
                    if (z) {
                        OnBoardArtistPreferenceActivity.this.hideProgressDialog();
                        u.a().a("ArtistSelection", "Submit", str);
                        o.a().b();
                        aj.a().a(OnBoardArtistPreferenceActivity.this, str);
                        if (!OnBoardArtistPreferenceActivity.this.isSignupFromInside) {
                            OnBoardArtistPreferenceActivity.this.launchHomeScreen = true;
                        }
                        OnBoardArtistPreferenceActivity.this.launchHome();
                        f.v().a(GaanaApplication.getInstance().getCurrentUser());
                        return;
                    }
                    aj.a().a(OnBoardArtistPreferenceActivity.this, OnBoardArtistPreferenceActivity.this.getResources().getString(R.string.error_updating_languages));
                }
            });
        }
    }

    private void askUserToGoOnline(String str) {
        new com.services.f(this).a(getString(R.string.app_name), str, Boolean.valueOf(true), getString(R.string.go_online_text), getString(R.string.cancel), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                d.a().a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                d.a().a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                d.a().a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                GaanaApplication.getInstance().setAppInOfflineMode(false);
                DownloadManager.c().d();
                OnBoardArtistPreferenceActivity.this.saveArtistSettings();
            }
        });
    }

    public void onBackPressed() {
        skipOnboardArtistPrefScreen();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_all_done) {
            d.a().a("PREFERENCE_ARTIST_ONBOARD", 1, false);
            showProgressDialog(getString(R.string.saving));
            saveArtistSettings();
        } else if (id == R.id.txt_skip) {
            hideProgressDialog();
            skipOnboardArtistPrefScreen();
        }
    }
}

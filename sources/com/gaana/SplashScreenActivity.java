package com.gaana;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.dynamicview.DynamicViewManager;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.CountryData;
import com.gaana.models.GaEventsConfig;
import com.gaana.models.SplashData;
import com.i.i;
import com.library.custom_glide.GlideFileLoader;
import com.managers.URLManager;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.l.af;
import com.services.l.e;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.d;
import com.utilities.f;
import java.util.UUID;

public class SplashScreenActivity extends BaseLaunchActivity {
    private static final String LOG_TAG = "SplashScreen";
    protected boolean isDynamicSplash = false;
    private boolean isInitInProgress = false;
    private ImageView mAdImageView;
    private int splash_hold_duration = 0;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        if (d.d()) {
            f.a((Context) this);
        }
        overridePendingTransition(0, 0);
        this.isInitInProgress = false;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_splash_screen);
        this.mAdImageView = (ImageView) findViewById(R.id.ad_image_view);
        this.shouldDisplayAd = displaySplashAd();
        this.loginAndConsentUpdate = false;
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (!this.isInitInProgress) {
            this.isInitInProgress = true;
            getSplashImage();
            initSplash();
        }
    }

    private void getSplashImage() {
        this.splash_hold_duration = com.services.d.a().b("SPLASH_IMAGE_DURATION", 1, false);
        String b = com.services.d.a().b("SPLASH_IMAGE_URL", "", false);
        if (!TextUtils.isEmpty(b)) {
            this.isDynamicSplash = true;
            bindSplashArtwork(b);
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/splash/home");
        uRLManager.a(SplashData.class);
        uRLManager.b(Boolean.valueOf(true));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                try {
                    SplashData splashData = (SplashData) obj;
                    String artwork = splashData.getArtwork();
                    splashData.getHashValue();
                    SplashScreenActivity.this.splash_hold_duration = splashData.getDuration();
                    com.services.d.a().a("SPLASH_IMAGE_DURATION", SplashScreenActivity.this.splash_hold_duration, false);
                    com.services.d.a().a("SPLASH_IMAGE_URL", artwork, false);
                } catch (Exception unused) {
                    com.services.d.a().b("SPLASH_IMAGE_URL", false);
                    com.services.d.a().b("SPLASH_IMAGE_DURATION", false);
                }
            }
        }, uRLManager);
    }

    private void bindSplashArtwork(String str) {
        i.a().a(str, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                ((ImageView) SplashScreenActivity.this.findViewById(R.id.splash_image)).setImageBitmap(bitmap);
            }
        });
    }

    private void initSplash() {
        GaanaApplication.sessionHistoryCount = this.mDeviceResourceManager.b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false);
        Constants.ed = this.mDeviceResourceManager.b("PREFERENCE_NOKIA_MODE", 0, false);
        if (!com.services.d.a().b("pref_dyn_rest_flag", false, false)) {
            com.services.d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
            DynamicViewManager.a().c();
            com.services.d.a().a("pref_dyn_rest_flag", true, false);
        }
        f.a(this.mAppState);
        this.mAppState.setCurrentSessionId(UUID.randomUUID().toString());
        Constants.dC = Util.p(getApplicationContext());
        if (com.services.d.a().b("pref_auto_night_mode_on", false, false)) {
            Constants.l = GaanaApplication.getInstance().isDayOrNightUsingTwilightCalculator() == 0;
        }
        getUJFlags();
        Util.af();
        if (this.mDeviceResourceManager.b("PREF_COUNTRY_DATA_BLOCKING_CALL", true, false)) {
            Constants.en = 1;
            Util.a((Context) this, new e() {
                public void onDataRetrieved(int i) {
                    switch (i) {
                        case 0:
                            SplashScreenActivity.this.finishSetup();
                            return;
                        case 1:
                            SplashScreenActivity.this.mDeviceResourceManager.a("PREF_COUNTRY_DATA_BLOCKING_CALL", false, false);
                            break;
                        case 2:
                            break;
                        default:
                            return;
                    }
                    SplashScreenActivity.this.handleCountryDataResponse();
                }
            });
            return;
        }
        handleCountryDataResponse();
    }

    private void handleCountryDataResponse() {
        CountryData countryData = this.mAppState.getCountryData();
        Intent intent;
        if (countryData == null || countryData.getEuRegion() != 1) {
            finishSetup();
        } else if (countryData.getUserStatus() == 0 && Constants.ek == 0) {
            intent = new Intent(this.mContext, ConsentActivity.class);
            intent.setFlags(603979776);
            startLaunchActivity(intent);
        } else if (countryData.getUserStatus() == 1) {
            this.mAppState.clearApplicationData();
            intent = new Intent(this.mContext, ConsentActivity.class);
            intent.setFlags(603979776);
            intent.putExtra("BLOCKING_SCREEN", true);
            startLaunchActivity(intent);
        } else {
            finishSetup();
        }
    }

    private void getUJFlags() {
        Constants.aL = this.mDeviceResourceManager.b("PREFERENCE_UJ_CLICK", 0, false);
        Constants.aM = this.mDeviceResourceManager.b("PREFERENCE_UJ_SCROLL", 0, false);
        Constants.aN = this.mDeviceResourceManager.b("PREFERENCE_UJ_STATE", 0, false);
        Constants.aQ = this.mDeviceResourceManager.b("PREFERENCE_UJ_PLAYOUT", 0, false);
        Constants.aP = this.mDeviceResourceManager.b("PREFERENCE_UJ_ADS", 0, false);
        Constants.aR = this.mDeviceResourceManager.b("PREFERENCE_UJ_MASTER", 0, false);
        if (Util.ad()) {
            an.a().a("state", "fg", "", "", "", "SPLASH");
        }
        Constants.I = this.mDeviceResourceManager.b("PREFERENCE_UJ_MINI_V4_ENABLED", 0, false);
        Constants.J = this.mDeviceResourceManager.b("PREFERENCE_UJ_MINI_V4_PULL_UP_TEXT", 0, false);
        Constants.P = Constants.I;
        Constants.H = com.services.d.a().b("PREFERENCE_UJ_PLAYER_CAROUSEL_ENABLED", 1, false);
        Constants.aV = com.services.d.a().b("pref_home_view_all", false, false);
        Constants.aW = com.services.d.a().b("pref_home_playlist_play_icon", false, false);
        Constants.aX = com.services.d.a().b("pref_home_tracks_playouts", true, false);
        Constants.aY = com.services.d.a().b("pref_home_lyrics_card", false, false);
        Constants.aZ = com.services.d.a().b("pref_home_prescreen", true, false);
        configureGaEvents();
    }

    private void configureGaEvents() {
        u a = u.a();
        int b = com.services.d.a().b(GaEventsConfig.IN_APP_CATEGORY_KEY, 1, false);
        int b2 = com.services.d.a().b(GaEventsConfig.IN_APP_ACTION_RESPONSE_KEY, 1, false);
        int b3 = com.services.d.a().b(GaEventsConfig.IN_APP_ACTION_APIRESPONSE_KEY, 1, false);
        int b4 = com.services.d.a().b(GaEventsConfig.ABTESTING_PREFERENCE_KEY, 1, false);
        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, "MASTER", b);
        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, GaEventsConfig.IN_APP_ACTION_APIRESPONSE_KEY, b3);
        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, GaEventsConfig.IN_APP_ACTION_RESPONSE_KEY, b2);
        a.a(GaEventsConfig.ABTESTING_CATEGORY_KEY, "MASTER", b4);
    }

    private boolean displaySplashAd() {
        if (ap.a().b((Context) this)) {
            String fullFileName = GlideFileLoader.getFullFileName("spl_ad_*#");
            if (!TextUtils.isEmpty(fullFileName)) {
                fullFileName = fullFileName.replaceAll("/", "");
                final String substring = (!fullFileName.contains("spl_ad_*#") || TextUtils.isEmpty(fullFileName.substring(fullFileName.indexOf("spl_ad_*#") + "spl_ad_*#".length()))) ? "brand" : fullFileName.substring(fullFileName.indexOf("spl_ad_*#") + "spl_ad_*#".length());
                i.a().a(fullFileName, new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(Bitmap bitmap) {
                        if (!SplashScreenActivity.this.isFinishing()) {
                            if (d.h()) {
                                SplashScreenActivity.this.mAdImageView.setImageBitmap(bitmap);
                            } else {
                                int b = com.services.d.a().b();
                                int height = (bitmap.getHeight() * b) / bitmap.getWidth();
                                LayoutParams layoutParams = (LayoutParams) SplashScreenActivity.this.mAdImageView.getLayoutParams();
                                layoutParams.width = b;
                                layoutParams.height = height;
                                layoutParams.gravity = 81;
                                SplashScreenActivity.this.mAdImageView.setLayoutParams(layoutParams);
                                SplashScreenActivity.this.mAdImageView.setImageBitmap(bitmap);
                            }
                            u.a().a("Splash", substring, "ad");
                        }
                    }
                });
                return true;
            }
        }
        return false;
    }

    public void startLaunchActivity(final Intent intent) {
        if (this.shouldDisplayAd || this.isDynamicSplash) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.finish();
                }
            }, (long) (this.splash_hold_duration * 1000));
            return;
        }
        startActivity(intent);
        finish();
    }
}

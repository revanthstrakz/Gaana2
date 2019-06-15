package com.gaana;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.android.b.a.a;
import com.android.b.a.c;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.dynamicview.DynamicViewManager;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData.CompletionHandler;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.OEM_Tracking;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.AppUpdateData;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.ReferralSignup;
import com.gaana.models.User;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.AppUpdaterView;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.i.j;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchasePaypalManager;
import com.managers.PurchasePaypalManager.TRANSACTION_STATUS;
import com.managers.SdCardManager;
import com.managers.aa;
import com.managers.aj;
import com.managers.am;
import com.managers.ap;
import com.managers.f;
import com.managers.l;
import com.managers.o;
import com.managers.q;
import com.managers.s;
import com.managers.u;
import com.managers.v;
import com.managers.w;
import com.player_framework.MediaButtonIntentReceiver;
import com.services.DownloadedTracksMetaUpdateService;
import com.services.d;
import com.services.n;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.utilities.g;
import com.utilities.h;
import io.branch.referral.Branch;
import io.branch.referral.Branch.e;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import org.json.JSONObject;

public class BaseLaunchActivity extends AppCompatActivity implements c {
    private boolean isLaunchedFromReferralLink = false;
    private boolean launchAppDisplayLanguageScreen = false;
    private long loadBranchSessionTime = -1;
    protected boolean loginAndConsentUpdate = false;
    protected GaanaApplication mAppState;
    protected Activity mContext;
    protected d mDeviceResourceManager;
    protected LayoutInflater mInflater;
    private boolean mIsFirstPermissionAsked;
    private ReferralSignup mReferralSignup;
    a mReferrerClient = null;
    protected boolean shouldDisplayAd = false;
    private boolean showUpgradeCoachMark = false;

    private static final class InitAsyncRunnable implements Runnable {
        private InitAsyncRunnable() {
        }

        /* synthetic */ InitAsyncRunnable(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void run() {
            GaanaApplication.getInstance().setAppInDataSaveMode(d.a().b("PREFERENCE_KEY_DATA_SAVE_MODE", false, false));
            Constants.cL = d.a().b("pref_explicit_content", false, false);
            GaanaLogger.a().a(GaanaApplication.getContext());
            Constants.cN = Util.j();
            Constants.aa = d.a().b(Constants.ad, false, true);
            Constants.ab = d.a().b(Constants.ac, false, true);
            if (com.services.c.b) {
                com.services.c.b = false;
                com.services.c.c = true;
            }
            q.a().b();
            q.a().f();
            q.a().a(GaanaApplication.getInstance().getCurrentUser());
            q.a().b(GaanaApplication.getInstance().getCurrentUser());
            q.a().c();
            f.v().a(GaanaApplication.getInstance().getCurrentUser());
            Constants.dg = d.a().b("PREFERENCE_MAX_QUEUE_SIZE", Constants.dg, false);
            Constants.dh = d.a().b("PREFERENCE_MAX_RECENT_SEARCH_SIZE", Constants.dh, false);
            Constants.cG = d.a().b("PREFERENCE_IS_DB_SEARCH_LOG_ENABLED", Constants.cG, false);
            Constants.dp = d.a().b("PREFERENCE_USER_ACTIVITY_REFRESH_TIME", Constants.dp, false);
            Constants.cZ = d.a().b("pref_gaana_party_hub", false, false);
            Constants.Q = d.a().b("PREFERENCE_IS_LOCAL_MEDIA", true, false);
            Constants.R = d.a().b("PREFERENCE_REFERRAL_ACTIVE", true, false);
            Constants.S = d.a().b("PREFERENCE_REFERRAL_BANNER_ACTIVE", true, false);
            Constants.aD = d.a().b("pref_hereit_layout_config", false, false);
            Constants.aC = d.a().b("pref_trending_layout_config", false, false);
            Constants.dv = d.a().b("PREFERENCE_INITIAL_SESSION_TIME", 3, false);
            Constants.dw = d.a().b("PREFERENCE_HOME_FEED_SESSION_TIME", 3, false);
            Constants.dy = d.a().b("PREFERENCE_DAYS_INTERVAL", 4, false);
            Constants.dz = d.a().b("PREFERENCE_LAST_TIME_LOGIN_BANNER_SHOWN", String.valueOf(System.currentTimeMillis()), false);
            Constants.dx = d.a().b("PREFERENCE_HOME_FEED_APPLICATION_STATUS", 0, false);
            Constants.dB = false;
            boolean z = Constants.W || d.a().b("pref_schd_count", 0, false) >= 3;
            Constants.W = z;
            Constants.Y = false;
            if (System.currentTimeMillis() - Long.parseLong(Constants.dz) > ((long) (Constants.dy * com.comscore.utils.Constants.KEEPALIVE_INTERVAL_MS))) {
                Constants.dx = Math.max(GaanaApplication.sessionHistoryCount, Constants.dv) + Constants.dw;
                Constants.dz = String.valueOf(System.currentTimeMillis());
                d.a().a("PREFERENCE_LAST_TIME_LOGIN_BANNER_SHOWN", Constants.dz, false);
                d.a().a("PREFERENCE_HOME_FEED_APPLICATION_STATUS", Constants.dx, false);
            }
            Constants.cW = Util.u(GaanaApplication.getContext());
            if (ap.a().b(GaanaApplication.getContext())) {
                String c = d.a().c(Constants.ag, false);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String format = simpleDateFormat.format(Calendar.getInstance().getTime());
                if (c != null) {
                    try {
                        if (simpleDateFormat.parse(c).compareTo(simpleDateFormat.parse(format)) < 0) {
                            d.a().a(Constants.af, 0, false);
                            d.a().a(Constants.ag, format, false);
                        }
                    } catch (ParseException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                } else if (c == null) {
                    d.a().a(Constants.ag, format, false);
                }
                d.a().a(Constants.af, d.a().b(Constants.af, 0, false) + 1, false);
            }
            v.a().c();
            Util.e();
            if (GaanaApplication.sessionHistoryCount > 0) {
                Constants.cO = d.a().b("PREF_KEY_LOW_RAM_THRESHOLD", Constants.cO, false);
                Constants.cP = d.a().b("PREF_KEY_LOW_RAM_ADS_FREE_SESSION", Constants.cP, false);
            }
            if (Util.j(GaanaApplication.getContext())) {
                Util.ac();
            }
            if (Util.K()) {
                Util.L();
            }
            Util.E();
        }
    }

    static class AppLinkCompletionHandler implements CompletionHandler {
        AppLinkCompletionHandler() {
        }

        public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
            if (appLinkData != null) {
                GaanaApplication.targetUri = appLinkData.getTargetUri().toString();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mAppState = GaanaApplication.getInstance();
        this.mDeviceResourceManager = d.a();
        this.mInflater = LayoutInflater.from(this);
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                if (!TextUtils.isEmpty(extras.getString("data"))) {
                    String string = new JSONObject(extras.getString("data")).getString("url");
                    if (string.contains("view/mymusic/downloads") || string.contains("view/mymusic/songs/1")) {
                        initUser();
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        getUser();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 501) {
            return;
        }
        if (h.a(this.mContext)) {
            getUser();
        } else {
            h.b(this.mContext, this.mContext.getString(R.string.please_enable_permission), -2);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        comScore.onExitForeground();
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public void finishSetup() {
        initUiElements();
        loadProviderInstaller();
        if (this instanceof SplashScreenActivity) {
            AppsFlyer.getInstance().sendDeepLinkData(this);
        }
        if (GaanaApplication.sessionHistoryCount <= 0) {
            com.g.a.a.a(GaanaApplication.getContext(), "GAANA001", 1);
            if (g.b()) {
                setIcon();
            }
            AppLinkData.fetchDeferredAppLinkData(this.mContext, new AppLinkCompletionHandler());
            this.mReferrerClient = a.a(this.mContext).a();
            try {
                this.mReferrerClient.a((c) this);
            } catch (SecurityException e) {
                ThrowableExtension.printStackTrace(e);
            }
            Branch.c(this.mAppState);
            initBranchIO();
        } else if (!isHardUpdate()) {
            getUser();
        }
        initAsync();
        if (VERSION.SDK_INT >= 25) {
            setShortCuts();
        }
    }

    @TargetApi(25)
    private void setShortCuts() {
        ShortcutManager shortcutManager = (ShortcutManager) this.mContext.getSystemService(ShortcutManager.class);
        Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.vector_more_option_download);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(canvas.getWidth() / 5, canvas.getHeight() / 6, canvas.getWidth(), (int) (((float) canvas.getHeight()) * 0.9f));
        drawable.draw(canvas);
        Intent intent = new Intent("android.intent.action.MAIN", Uri.EMPTY, this.mContext, GaanaActivity.class);
        intent.setFlags(67108864);
        intent.putExtra("DEEPLINKING_SCREEN", R.id.MyMusicMenuDownloads);
        ShortcutInfo build = new Builder(this.mContext, "gaana_id_1").setShortLabel(this.mContext.getString(R.string.downloads_text)).setLongLabel(this.mContext.getString(R.string.downloads_text)).setIcon(Icon.createWithBitmap(createBitmap)).setIntent(intent).setRank(0).build();
        Intent intent2 = new Intent("android.intent.action.MAIN", Uri.EMPTY, this.mContext, GaanaActivity.class);
        intent2.setFlags(67108864);
        intent2.putExtra("DEEPLINKING_SCREEN", R.id.MyMusicMenuSongs);
        Drawable drawable2 = ContextCompat.getDrawable(this.mContext, R.drawable.vector_ab_favorite);
        Bitmap createBitmap2 = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Config.ARGB_4444);
        Canvas canvas2 = new Canvas(createBitmap2);
        drawable2.setBounds(canvas2.getWidth() / 5, canvas2.getHeight() / 6, canvas2.getWidth(), (int) (((float) canvas2.getHeight()) * 0.9f));
        drawable2.draw(canvas2);
        ShortcutInfo build2 = new Builder(this.mContext, "gaana_id_2").setShortLabel(this.mContext.getString(R.string.favorites_text)).setLongLabel(this.mContext.getString(R.string.favorites_text)).setIcon(Icon.createWithBitmap(createBitmap2)).setIntent(intent2).setRank(1).build();
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_URI_PATH", "/view/recentlyplayed/seeall");
        bundle.putInt("DEEPLINKING_SCREEN", R.id.RecentlyPlayedSeeAll);
        Intent intent3 = new Intent("android.intent.action.MAIN", Uri.EMPTY, this.mContext, GaanaActivity.class);
        intent3.setFlags(67108864);
        intent3.putExtras(bundle);
        Drawable drawable3 = ContextCompat.getDrawable(this.mContext, R.drawable.vector_my_music_song);
        createBitmap2 = Bitmap.createBitmap(drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight(), Config.ARGB_4444);
        Canvas canvas3 = new Canvas(createBitmap2);
        drawable3.setBounds(canvas3.getWidth() / 5, canvas3.getHeight() / 6, canvas3.getWidth(), (int) (((float) canvas3.getHeight()) * 0.9f));
        drawable3.draw(canvas3);
        ShortcutInfo build3 = new Builder(this.mContext, "gaana_id_3").setShortLabel(this.mContext.getString(R.string.recently_played)).setLongLabel(this.mContext.getString(R.string.recently_played)).setIcon(Icon.createWithBitmap(createBitmap2)).setIntent(intent3).setRank(2).build();
        shortcutManager.setDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{build, build2, build3}));
    }

    private void initAsync() {
        com.i.d.a(new InitAsyncRunnable());
        PurchaseGoogleManager.a(this.mContext).d();
    }

    private void initUiElements() {
        String b = am.a().b();
        if (TextUtils.isEmpty(b)) {
            DynamicViewManager.a().a(false);
        } else {
            int parseInt = Integer.parseInt(b.replaceAll("[^0-9]", ""));
            if (parseInt < 751) {
                DynamicViewManager.a().b();
            } else if (parseInt < 772) {
                DynamicViewManager.a().a(true);
            } else {
                DynamicViewManager.a().a(false);
            }
        }
        s.a().a(this.mContext);
    }

    private void loadProviderInstaller() {
        com.services.h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                try {
                    ProviderInstaller.installIfNeeded(BaseLaunchActivity.this);
                } catch (GooglePlayServicesRepairableException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }, -1);
    }

    private void initBranchIO() {
        Branch a = Branch.a();
        final long timeInMillis = Calendar.getInstance().getTimeInMillis();
        a.a(new e() {
            /* JADX WARNING: Removed duplicated region for block: B:24:0x013a A:{Catch:{ Exception -> 0x0148 }} */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x00f4 A:{Catch:{ Exception -> 0x0148 }} */
            public void onInitFinished(org.json.JSONObject r7, io.branch.referral.e r8) {
                /*
                r6 = this;
                if (r8 != 0) goto L_0x0152;
            L_0x0002:
                r8 = java.util.Calendar.getInstance();	 Catch:{ Exception -> 0x0148 }
                r0 = r8.getTimeInMillis();	 Catch:{ Exception -> 0x0148 }
                r2 = r1;	 Catch:{ Exception -> 0x0148 }
                r4 = 0;
                r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r8 == 0) goto L_0x001b;
            L_0x0012:
                r8 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r2 = r1;	 Catch:{ Exception -> 0x0148 }
                r4 = r0 - r2;
                r8.loadBranchSessionTime = r4;	 Catch:{ Exception -> 0x0148 }
            L_0x001b:
                r7 = r7.toString();	 Catch:{ Exception -> 0x0148 }
                r8 = new com.google.gson.Gson;	 Catch:{ Exception -> 0x0148 }
                r8.<init>();	 Catch:{ Exception -> 0x0148 }
                r0 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r1 = com.gaana.models.ReferralSignup.class;
                r7 = r8.fromJson(r7, r1);	 Catch:{ Exception -> 0x0148 }
                r7 = (com.gaana.models.ReferralSignup) r7;	 Catch:{ Exception -> 0x0148 }
                r0.mReferralSignup = r7;	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r8 = 1;
                r0 = 0;
                if (r7 == 0) goto L_0x00ef;
            L_0x003b:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r7.getReferreeName();	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.getReferralCode();	 Catch:{ Exception -> 0x0148 }
                r1 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r1 = r1.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r1 = r1.getCampaign();	 Catch:{ Exception -> 0x0148 }
                if (r1 == 0) goto L_0x00db;
            L_0x005a:
                r2 = "referral";
                r1 = r1.equalsIgnoreCase(r2);	 Catch:{ Exception -> 0x0148 }
                if (r1 == 0) goto L_0x00db;
            L_0x0062:
                r1 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r1 = r1.mDeviceResourceManager;	 Catch:{ Exception -> 0x0148 }
                r2 = "SHARED_PREFF_REFERRAL_LINK";
                r3 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r3 = r3.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r3 = com.services.n.a(r3);	 Catch:{ Exception -> 0x0148 }
                r1.a(r2, r3, r0);	 Catch:{ Exception -> 0x0148 }
                r1 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r1.isLaunchedFromReferralLink = r8;	 Catch:{ Exception -> 0x0148 }
                r1 = "Install Success";
                r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0148 }
                r2.<init>();	 Catch:{ Exception -> 0x0148 }
                r3 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r3 = r3.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r3 = r3.getReferralCode();	 Catch:{ Exception -> 0x0148 }
                r2.append(r3);	 Catch:{ Exception -> 0x0148 }
                r3 = "-";
                r2.append(r3);	 Catch:{ Exception -> 0x0148 }
                r3 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r3 = r3.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r3 = r3.getCampaign();	 Catch:{ Exception -> 0x0148 }
                r2.append(r3);	 Catch:{ Exception -> 0x0148 }
                r2 = r2.toString();	 Catch:{ Exception -> 0x0148 }
                r3 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r4 = "Referral";
                r3.sendGAEvent(r4, r1, r2);	 Catch:{ Exception -> 0x0148 }
                r1 = new com.managers.URLManager;	 Catch:{ Exception -> 0x0148 }
                r1.<init>();	 Catch:{ Exception -> 0x0148 }
                r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0148 }
                r2.<init>();	 Catch:{ Exception -> 0x0148 }
                r3 = "https://api.gaana.com/index.php?type=referral&subtype=app_install&referral_code=";
                r2.append(r3);	 Catch:{ Exception -> 0x0148 }
                r2.append(r7);	 Catch:{ Exception -> 0x0148 }
                r7 = r2.toString();	 Catch:{ Exception -> 0x0148 }
                r1.a(r7);	 Catch:{ Exception -> 0x0148 }
                r7 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x0148 }
                r1.b(r7);	 Catch:{ Exception -> 0x0148 }
                r1.i(r0);	 Catch:{ Exception -> 0x0148 }
                r7 = com.i.i.a();	 Catch:{ Exception -> 0x0148 }
                r2 = new com.gaana.BaseLaunchActivity$2$1;	 Catch:{ Exception -> 0x0148 }
                r2.<init>();	 Catch:{ Exception -> 0x0148 }
                r7.a(r2, r1);	 Catch:{ Exception -> 0x0148 }
                goto L_0x00ef;
            L_0x00db:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mReferralSignup;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.getDeeplinkPath();	 Catch:{ Exception -> 0x0148 }
                r1 = android.text.TextUtils.isEmpty(r7);	 Catch:{ Exception -> 0x0148 }
                if (r1 != 0) goto L_0x00ef;
            L_0x00eb:
                com.gaana.application.GaanaApplication.targetUri = r7;	 Catch:{ Exception -> 0x0148 }
                r7 = r8;
                goto L_0x00f0;
            L_0x00ef:
                r7 = r0;
            L_0x00f0:
                r1 = com.gaana.application.GaanaApplication.sessionHistoryCount;	 Catch:{ Exception -> 0x0148 }
                if (r1 != 0) goto L_0x013a;
            L_0x00f4:
                if (r7 == 0) goto L_0x0134;
            L_0x00f6:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mContext;	 Catch:{ Exception -> 0x0148 }
                r7 = com.services.c.a(r7);	 Catch:{ Exception -> 0x0148 }
                r1 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r1 = r1.mContext;	 Catch:{ Exception -> 0x0148 }
                r2 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r2 = r2.mAppState;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.a(r1, r2, r8);	 Catch:{ Exception -> 0x0148 }
                if (r7 != 0) goto L_0x010d;
            L_0x010c:
                goto L_0x0134;
            L_0x010d:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mDeviceResourceManager;	 Catch:{ Exception -> 0x0148 }
                r1 = "PREFERENCE_MISSED_LANG_SCREEN_FIRSTTIME";
                r7.a(r1, r8, r0);	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mDeviceResourceManager;	 Catch:{ Exception -> 0x0148 }
                r1 = "PREFERENCE_SESSION_HISTORY_COUNT";
                r2 = com.gaana.application.GaanaApplication.sessionHistoryCount;	 Catch:{ Exception -> 0x0148 }
                r2 = r2 + r8;
                r7.a(r1, r2, r0);	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.mContext;	 Catch:{ Exception -> 0x0148 }
                r7.finish();	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7.onAppFirstLaunch();	 Catch:{ Exception -> 0x0148 }
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7.onSplashFinish();	 Catch:{ Exception -> 0x0148 }
                goto L_0x0157;
            L_0x0134:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7.getUser();	 Catch:{ Exception -> 0x0148 }
                goto L_0x0157;
            L_0x013a:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7 = r7.isHardUpdate();	 Catch:{ Exception -> 0x0148 }
                if (r7 != 0) goto L_0x0157;
            L_0x0142:
                r7 = com.gaana.BaseLaunchActivity.this;	 Catch:{ Exception -> 0x0148 }
                r7.getUser();	 Catch:{ Exception -> 0x0148 }
                goto L_0x0157;
            L_0x0148:
                r7 = move-exception;
                r8 = com.gaana.BaseLaunchActivity.this;
                r8.getUser();
                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);
                goto L_0x0157;
            L_0x0152:
                r7 = com.gaana.BaseLaunchActivity.this;
                r7.getUser();
            L_0x0157:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.BaseLaunchActivity$AnonymousClass2.onInitFinished(org.json.JSONObject, io.branch.referral.e):void");
            }
        }, this.mContext.getIntent().getData(), this.mContext);
    }

    private void checkAndUpgrade() {
        String b = am.a().b();
        if (TextUtils.isEmpty(b)) {
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                MoEngage.getInstance().reportOnLogin(LoginManager.getInstance().getUserInfo());
                MoEngage.getInstance().reportOnFirstLaunch();
                w.a(this.mAppState).a(this.mContext, null, false);
            } else if (!this.mAppState.getCurrentUser().getLoginStatus()) {
                MoEngage.getInstance().reportUserNotLoggedIn();
            }
            upgradeToNxtGenLogin();
            return;
        }
        b = b.replaceAll("[^0-9]", "");
        int parseInt = Integer.parseInt(b);
        if (parseInt >= 780) {
            int c = am.a().c();
            parseInt = Constants.cA;
            if (c < 811) {
                Util.M();
            }
            if (c < 813) {
                this.mDeviceResourceManager.b("DEVICE_HARDWARE_JSON", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                DynamicViewManager.a().c();
            }
            if (c < 818) {
                this.mDeviceResourceManager.b("DEVICE_HARDWARE_JSON", false);
            }
            if (c < 822) {
                j.a().d();
            }
            if (c < 824) {
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                DynamicViewManager.a().c();
            }
            if (c < 830) {
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                DynamicViewManager.a().c();
                j.a().d();
            }
            if (c < 843) {
                j.a().d();
            }
        } else if (!b.equalsIgnoreCase(Constants.cz)) {
            if (g.b()) {
                setIcon();
            }
            if (parseInt <= 513) {
                o.a().b();
                upgradeToNxtGenLogin();
            } else if (parseInt < 600) {
                if (this.mAppState.getCurrentUser() != null && this.mAppState.getCurrentUser().getLoginStatus()) {
                    PlaylistSyncManager.getInstance().syncOnLogin();
                }
                this.showUpgradeCoachMark = true;
            }
            if (parseInt < 623) {
                this.mDeviceResourceManager.b("PREFERENCE_KEY_REPEAT_STATUS", true);
            }
            if (parseInt < 624) {
                j.a().c().d().a();
                o.a().b();
            }
            if (parseInt < 631) {
                o.a().d();
            }
            if (parseInt < GooglePlusLogin.GOOGLE_PLUS_REQUEST_CODE) {
                this.mDeviceResourceManager.a("PREFERENCE_MAX_QUEUE_SIZE", Constants.dg, false);
            }
            if (parseInt < 710) {
                j.a().c().d().a();
                o.a().b();
            }
            if (parseInt < 720) {
                this.mDeviceResourceManager.b("PREFERENCE_LANGUAGE_SETTINGS", false);
                j.a().c().d().a();
                o.a().b();
            }
            if (parseInt < 731) {
                this.mDeviceResourceManager.b("PREFF_RECENT_SEARCHES", false);
                j.a().c().d().a();
                o.a().b();
            }
            if (parseInt < 750) {
                j.a().c().d().a();
                o.a().b();
            }
            if (parseInt < 760) {
                j.a().c().d().a();
                j.a().d();
            }
            if (parseInt < 770) {
                o.a().h();
                o.a().e("http://dummy.com/playerqueue");
                o.a().e("http://dummy.com/playerqueuebeforeshuffle");
                d.a().b("PREFERENCE_KEY_DB_INITIALIZED_WITH_PLAYLIST", true);
                d.a().b("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", true);
            }
            if (parseInt < 774) {
                aa.a().d();
            }
            if (parseInt < 775) {
                SdCardManager.a().d(".temp");
            }
            if (parseInt < 778) {
                j.a().d();
            }
            if (parseInt < 780) {
                o.a().c();
            }
            if (parseInt < 787) {
                j.a().d();
            }
            if (parseInt < 788) {
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                DynamicViewManager.a().c();
            }
            if (parseInt < 800) {
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                this.mDeviceResourceManager.b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                DynamicViewManager.a().c();
                j.a().d();
            }
        }
        this.mDeviceResourceManager.a("PREFERENCE_KEY_GAANAAPP_VERSION", Constants.cz, false);
        this.mDeviceResourceManager.a("PREFERENCE_KEY_GAANAAPP_VERSION_CODE", Constants.cA, false);
    }

    private void upgradeToNxtGenLogin() {
        if (this.mDeviceResourceManager.c("PREFERENCE_KEY_CURRENT_USER", false) != null) {
            User user = (User) n.a(this.mDeviceResourceManager.c("PREFERENCE_KEY_CURRENT_USER", false));
            if (user.getLoginStatus().booleanValue()) {
                Serializable loginInfo = new LoginInfo();
                switch (user.getLoginType()) {
                    case FB:
                        loginInfo.setLoginType(LoginType.FB);
                        loginInfo.setEmailId(user.getEmailId());
                        loginInfo.setFbId(user.getFbId());
                        loginInfo.setRealToken(user.getRealToken());
                        loginInfo.setFullname(user.getUserData().getFullname());
                        loginInfo.setDob(user.getUserData().getDob());
                        loginInfo.setSex(user.getUserData().getSex());
                        loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                        break;
                    case GAANA:
                        loginInfo.setLoginType(LoginType.GAANA);
                        loginInfo.setEmailId(user.getEmailId());
                        loginInfo.setPassword(user.getPassword());
                        loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                        break;
                    case GOOGLE:
                        loginInfo.setLoginType(LoginType.GOOGLE);
                        loginInfo.setEmailId(user.getEmailId());
                        loginInfo.setGoogleId(user.getGoogleID());
                        loginInfo.setRealToken(user.getRealToken());
                        loginInfo.setFullname(user.getUserData().getFullname());
                        loginInfo.setDob(user.getUserData().getDob());
                        loginInfo.setSex(user.getUserData().getSex());
                        loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                        break;
                }
                this.mDeviceResourceManager.a("PREFF_GAANA_LOGIN_INFO", n.a(loginInfo), false);
                LoginManager.getInstance().loginOnUpgrade(this.mContext);
            }
        }
    }

    private void sendGAEventSplash(Intent intent) {
        String deviceMemory;
        String str = Constants.dC;
        String str2 = null;
        if (GaanaApplication.sessionHistoryCount == 0 || this.mAppState.isUpgradedToNewVersion()) {
            deviceMemory = getDeviceMemory();
            if (GaanaApplication.sessionHistoryCount == 0 && Util.q(GaanaApplication.getContext())) {
                u.a().a("SplashScreen", str, deviceMemory, Build.MANUFACTURER);
            } else {
                u.a().b("SplashScreen", str, deviceMemory);
            }
            MoEngage.getInstance().sendPreburnUserAttribute();
            if (!(this.loadBranchSessionTime == -1 || this.mReferralSignup == null)) {
                Constants.a("Referral", this.loadBranchSessionTime, "Branch Response", null);
            }
        } else {
            if (intent != null) {
                str2 = intent.getDataString();
            }
            u.a().c("SplashScreen", str, str2);
        }
        deviceMemory = com.utilities.f.b(GaanaApplication.getContext());
        q.a().b();
        if (!TextUtils.isEmpty(deviceMemory)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RTlang:");
            stringBuilder.append(deviceMemory);
            q.a().a("int", stringBuilder.toString());
        }
    }

    public void sendGAEvent(String str, String str2, String str3) {
        u.a().a(str, str2, str3);
    }

    public void getUser() {
        if (!ap.a().d()) {
            Util.a(this.mContext, false, BLOCK_ACTION.NONE);
        }
        if (GaanaApplication.sessionHistoryCount > 0) {
            initUser();
            return;
        }
        ap.a().a("NO_USER");
        if (Constants.T) {
            launchInternationalOnBoarding();
        } else {
            launchHomeScreen();
        }
    }

    private void initUser() {
        this.mAppState.setAppInOfflineMode(this.mDeviceResourceManager.b("PREFERENCE_KEY_OFFLINE_MODE", false, false));
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            LoginInfo loginInfo = LoginManager.getInstance().getLoginInfo();
            if (loginInfo.getLoginType() != LoginType.FB || this.mDeviceResourceManager.b("pref_fb_legacy_token", false, false)) {
                d.a().a("pref_fb_legacy_token", true, false);
            } else {
                com.services.g.a().a(loginInfo);
            }
        } else {
            d.a().a("pref_fb_legacy_token", true, false);
        }
        if (!Util.j(this.mContext.getApplicationContext()) || this.mAppState.isAppInOfflineMode()) {
            UserInfo currentUser = this.mAppState.getCurrentUser();
            if (currentUser.getUserProfile() != null) {
                ap.a().a(currentUser.getUserProfile().getUserId());
            }
            handleOfflineMode();
            if (ap.a().o()) {
                ap.a().d(this.mContext);
            }
            GaanaLogger.a().a(this.mContext);
            launchSplashAd();
            return;
        }
        handleOnlineMode();
    }

    private void handleOfflineMode() {
        if (!this.mAppState.isAppInOfflineMode() || this.mAppState.getCurrentUser() == null) {
            this.mAppState.setAppInOfflineMode(false);
            return;
        }
        ap.a().e(this.mContext);
        if (ap.a().o()) {
            this.mAppState.setAppInOfflineMode(true);
            return;
        }
        this.mAppState.setAppInOfflineMode(false);
        this.mDeviceResourceManager.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
        this.mDeviceResourceManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
        this.mDeviceResourceManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
        Toast.makeText(this.mContext, this.mContext.getResources().getString(R.string.error_msg_splash_screen_offline_mode_gaana_plus_expired), 0).show();
    }

    private void handleOnlineMode() {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            Util.N();
            ap.a().e(this.mContext);
            if (d.a().b("favorite_sync_login", false, false)) {
                FavouriteSyncManager.getInstance().performSync();
            } else {
                FavouriteSyncManager.getInstance().performSyncOnLogin();
            }
            if (d.a().b("PREFERENCE_KEY_DB_INITIALIZED_WITH_PLAYLIST_NEW", false, true)) {
                PlaylistSyncManager.getInstance().performSync();
            } else {
                PlaylistSyncManager.getInstance().syncOnLogin();
            }
        } else {
            ap.a().a("NO_USER");
        }
        if (ap.a().o()) {
            ap.a().d(this.mContext);
            launchSplashAd();
            return;
        }
        ap.a().a(this.mContext);
        launchSplashAd();
    }

    private void launchSplashAd() {
        Intent intent;
        checkAndUpgrade();
        if (!this.mDeviceResourceManager.b("PREFERENCE_DOWNLOADED_TRACKS_META_UPDATED", false, false) && ap.a().j()) {
            intent = new Intent(GaanaApplication.getContext(), DownloadedTracksMetaUpdateService.class);
            intent.setAction("update_meta");
            GaanaApplication.getContext().startService(intent);
        }
        intent = this.mContext.getIntent();
        sendGAEventSplash(intent);
        if (!com.services.c.a(this.mContext).b(this.mContext, intent, this.mAppState) && !com.services.c.a(this.mContext).a(this.mContext, intent, this.mAppState)) {
            launchHomeScreen();
        }
    }

    private void launchInternationalOnBoarding() {
        if (Util.j(this.mAppState)) {
            this.mDeviceResourceManager.a("PREFERENCE_SESSION_HISTORY_COUNT", GaanaApplication.sessionHistoryCount + 1, false);
            Constants.X = false;
            Intent intent = new Intent(this.mContext, InternationalOnBoardingActivity.class);
            intent.setFlags(603979776);
            startLaunchActivity(intent);
            if (this.isLaunchedFromReferralLink) {
                this.mDeviceResourceManager.a("PREF_KEY_REFERRAL_INFO", new Gson().toJson(this.mReferralSignup), false);
            }
            onAppFirstLaunch();
            onSplashFinish();
            return;
        }
        launchHomeScreen();
    }

    private void launchHomeScreen() {
        boolean z;
        int i;
        if (Util.j(GaanaApplication.getContext()) || GaanaApplication.sessionHistoryCount > 0) {
            this.mDeviceResourceManager.a("PREFERENCE_SESSION_HISTORY_COUNT", GaanaApplication.sessionHistoryCount + 1, false);
            Constants.X = false;
        }
        boolean b = this.mDeviceResourceManager.b("PREFERENCE_MISSED_LANG_SCREEN_FIRSTTIME", false, false);
        String str = null;
        String b2 = this.mDeviceResourceManager.b("DEFERRED_DEEPLINK_ONBOARDING_STATE", null, false);
        if (!GaanaApplication.onBoardingSkipped || b2 == null) {
            if ((!this.mAppState.getCurrentUser().getLoginStatus() || b) && Util.j(this.mContext.getApplicationContext()) && d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 0) {
                this.mDeviceResourceManager.b("PREFERENCE_MISSED_LANG_SCREEN_FIRSTTIME", false);
                b = true;
            } else {
                b = false;
            }
            this.launchAppDisplayLanguageScreen = Constants.m;
            z = false;
            i = z;
        } else {
            if (b2.equalsIgnoreCase("ONBOARD_STATE_DISP_LANG")) {
                this.launchAppDisplayLanguageScreen = true;
            } else if (b2.equalsIgnoreCase("ONBOARD_STATE_LOGIN")) {
                z = true;
                i = z;
                b = false;
            } else if (b2.equalsIgnoreCase("ONBOARD_STATE_SONG_LANG")) {
                b = true;
                i = b;
                z = false;
            }
            i = 1;
            b = false;
            z = b;
        }
        this.mDeviceResourceManager.b("DEFERRED_DEEPLINK_ONBOARDING_STATE", false);
        Intent intent;
        if (!this.loginAndConsentUpdate || GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            if (this.loginAndConsentUpdate) {
                Util.z(this.mContext);
            }
            Intent intent2;
            if (GaanaApplication.sessionHistoryCount > 1 && !b && !z && !this.launchAppDisplayLanguageScreen) {
                if (f.v().w()) {
                    startLaunchActivity(new Intent(this.mContext.getApplicationContext(), AudioAdActivity.class));
                } else {
                    if (!this.mAppState.getCurrentUser().getLoginStatus()) {
                        MoEngage.getInstance().reportUserNotLoggedIn();
                    }
                    if (d.a().b("PREFERENCE_MANDATORY_SIGNUP", 0, false) != 1 || GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                        intent = new Intent(this.mContext, GaanaActivity.class);
                        if (!(this.mContext.getIntent() == null || this.mContext.getIntent().getData() == null)) {
                            b2 = this.mContext.getIntent().getDataString();
                            if (!TextUtils.isEmpty(b2) && b2.contains("paypalpurchase")) {
                                String[] split = b2.split("gaana://view/paypalpurchase/");
                                if (split.length > 1) {
                                    str = split[1];
                                }
                                intent.putExtra("PLAY_DEEPLINKING_SONG", false);
                                if (str.contains("success")) {
                                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPaypalPurchaseResponseSuccess);
                                    PurchasePaypalManager.a(this.mContext).a(TRANSACTION_STATUS.SUCCESS);
                                } else {
                                    intent.putExtra("DEEPLINKING_SCREEN", R.id.LeftMenuPaypalPurchaseResponseFailure);
                                }
                                intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str.replace("success/", ""));
                                intent.putExtra("LAUNCH_FROM_DEEPLINK", true);
                                intent.setFlags(4194304);
                            }
                        }
                        intent.setFlags(603979776);
                        startLaunchActivity(intent);
                    } else {
                        intent = new Intent(this.mContext, Login.class);
                        intent.putExtra("ONBOARD_SIGNUP", false);
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", true);
                        startLaunchActivity(intent);
                    }
                }
                if (ap.a().j()) {
                    l.a().c(false);
                }
            } else if (GaanaApplication.sessionHistoryCount == 0) {
                if (this.isLaunchedFromReferralLink) {
                    intent = new Intent(this.mContext, GaanaActivity.class);
                    intent.setFlags(603979776);
                    startLaunchActivity(intent);
                    intent = new Intent(this.mContext.getApplicationContext(), ReferralSignupActivity.class);
                    intent.setFlags(603979776);
                    intent.putExtra("is_first_ap_launch", 1 ^ this.showUpgradeCoachMark);
                    intent.putExtra("referralInfo", this.mReferralSignup);
                    startLaunchActivity(intent);
                } else if (Util.j(this.mAppState)) {
                    intent = new Intent(this.mContext.getApplicationContext(), OnBoardLanguagePreferenceActivityNew.class);
                    intent.setFlags(603979776);
                    startLaunchActivity(intent);
                } else {
                    intent = new Intent(this.mContext, GaanaActivity.class);
                    intent.setFlags(603979776);
                    startLaunchActivity(intent);
                }
                onAppFirstLaunch();
            } else if (b) {
                intent = new Intent(this.mContext.getApplicationContext(), OnBoardLanguagePreferenceActivityNew.class);
                intent.setFlags(603979776);
                startLaunchActivity(intent);
            } else if (z) {
                intent2 = new Intent(this.mContext, Login.class);
                if (i != 0) {
                    intent2.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                    intent2.addFlags(872448000);
                } else {
                    intent2.putExtra("ONBOARD_SIGNUP", false);
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                }
                startLaunchActivity(intent2);
            } else if (!this.launchAppDisplayLanguageScreen) {
                intent = new Intent(this.mContext, GaanaActivity.class);
                intent.setFlags(603979776);
                startLaunchActivity(intent);
            } else if (!Util.j(this.mContext) || GaanaApplication.getInstance().isAppInOfflineMode()) {
                intent = new Intent(this.mContext, GaanaActivity.class);
                intent.setFlags(603979776);
                startLaunchActivity(intent);
            } else if (d.a().b("PREFERENCE_MANDATORY_SIGNUP", 0, false) == 0) {
                w.a(GaanaApplication.getInstance()).a(this.mContext, new w.a() {
                    public void onLanguagesFetched(Languages languages) {
                        if (languages == null || languages.getArrListBusinessObj() == null) {
                            aj.a().a(BaseLaunchActivity.this.mContext, BaseLaunchActivity.this.mContext.getResources().getString(R.string.error_msg_no_connection));
                            return;
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
                        d.a().a("PREFERENCE_MANDATORY_SIGNUP", Constants.B, false);
                        Iterator it = languages.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            Language language = (Language) it.next();
                            if (language.isPrefered() == 1) {
                                language.getLanguage();
                            }
                        }
                    }
                }, false);
            } else {
                intent2 = new Intent(this.mContext, Login.class);
                if (i != 0) {
                    intent2.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                    intent2.addFlags(872448000);
                } else {
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent2.putExtra("ONBOARD_SIGNUP", false);
                    intent2.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", true);
                }
                startLaunchActivity(intent2);
            }
        } else {
            intent = new Intent(this.mContext, Login.class);
            intent.putExtra("ONBOARD_SIGNUP", false);
            intent.addFlags(603979776);
            intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", true);
            intent.putExtra("IS_LAUNCHED_FROM_CONSENT_SCREEN", true);
            startLaunchActivity(intent);
        }
        onSplashFinish();
    }

    private void onAppFirstLaunch() {
        Util.C();
        sendGAEventSplash(this.mContext.getIntent());
        MoEngage.getInstance().reportOnFirstLaunch();
        MoEngage.getInstance().setExistingUser(false);
        this.mDeviceResourceManager.a("IS_MO_EXISTING_USER", false, false);
        com.utilities.d.a(this.mContext);
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(new ComponentName(this.mContext, MediaButtonIntentReceiver.class));
        this.mContext.sendBroadcast(intent);
    }

    private void onSplashFinish() {
        if (!this.mAppState.isAppInOfflineMode() && Util.j(this.mContext.getApplicationContext())) {
            Util.F();
            Util.G();
            Util.H();
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                Util.I();
            }
        }
        Util.D();
        OEM_Tracking.onCreate(GaanaApplication.getContext());
    }

    private void setIcon() {
        PackageManager packageManager = this.mContext.getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivityMMX"), 1, 1);
        packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"));
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"), 2, 1);
        packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"));
    }

    @SuppressLint({"NewApi"})
    private String getDeviceMemory() {
        String str = "NOT SET";
        if (VERSION.SDK_INT <= 15) {
            return str;
        }
        MemoryInfo memoryInfo = new MemoryInfo();
        Activity activity = this.mContext;
        Activity activity2 = this.mContext;
        ((ActivityManager) activity.getSystemService("activity")).getMemoryInfo(memoryInfo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
        stringBuilder.append("MB");
        return stringBuilder.toString();
    }

    private boolean isHardUpdate() {
        String c = this.mDeviceResourceManager.c("PREF_APP_UPDATE_DEATILS", false);
        if (!TextUtils.isEmpty(c)) {
            final AppUpdateData appUpdateData = (AppUpdateData) n.a(c);
            String updatedFlag = appUpdateData.getUpdatedFlag();
            if (!TextUtils.isEmpty(updatedFlag) && updatedFlag.compareTo(InternalAvidAdSessionContext.AVID_API_LEVEL) == 0) {
                if (appUpdateData.getAppVer().equals(Constants.cz)) {
                    this.mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            new AppUpdaterView(BaseLaunchActivity.this.mContext).showDialogForHardUpdate(appUpdateData.getMsg());
                        }
                    });
                    return true;
                }
                this.mDeviceResourceManager.b("PREF_APP_UPDATE_DEATILS", false);
            }
        }
        return false;
    }

    public void onInstallReferrerSetupFinished(int i) {
        switch (i) {
            case 0:
                try {
                    Log.d("SetupFinished", "InstallReferrer connected");
                    handleReferrer(this.mReferrerClient.c());
                    this.mReferrerClient.b();
                    return;
                } catch (RemoteException e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                }
            case 1:
                Log.d("SetupFinished", "Unable to connect to the service");
                return;
            case 2:
                Log.d("SetupFinished", "InstallReferrer not supported");
                return;
            default:
                Log.d("SetupFinished", "responseCode not found.");
                return;
        }
    }

    public void onInstallReferrerServiceDisconnected() {
        Log.d("ServiceDisconnected", "onInstallReferrerServiceDisconnected");
    }

    private void handleReferrer(com.android.b.a.d dVar) {
        String str = "handleReferrer";
        if (dVar != null) {
            com.g.a.a.a(GaanaApplication.getContext(), dVar.a());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Referrer:");
            stringBuilder.append(dVar.a());
            Log.d(str, stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ReferrerClickTime:");
            stringBuilder.append(dVar.b());
            Log.d(str, stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("InstallTimeBegin:");
            stringBuilder.append(dVar.c());
            Log.d(str, stringBuilder.toString());
        }
    }

    public void startLaunchActivity(final Intent intent) {
        if (this.shouldDisplayAd) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    BaseLaunchActivity.this.mContext.startActivity(intent);
                    BaseLaunchActivity.this.mContext.finish();
                }
            }, 300);
            return;
        }
        this.mContext.startActivity(intent);
        this.mContext.finish();
    }
}

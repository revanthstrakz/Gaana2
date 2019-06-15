package com.gaana;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.actionbar.GenericActionBar;
import com.actionbar.SearchActionBar;
import com.b.b;
import com.b.i;
import com.cast_music.VideoCastManager;
import com.cast_music.a.d;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment;
import com.exoplayer2.ui.VideoPlayerView;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverFragment;
import com.fragments.EditProfileActivityFragment;
import com.fragments.LyricsBannerFragment;
import com.fragments.MyMusicFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.PlayerRadioFragmentV4;
import com.fragments.RadioActivityFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.ReferralSignup;
import com.gaana.models.Tracks;
import com.gaana.models.User.LoginType;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.gson.Gson;
import com.helpshift.a;
import com.helpshift.support.h;
import com.helpshift.support.l;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PurchaseGoogleManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.managers.f;
import com.managers.n;
import com.managers.o;
import com.managers.q;
import com.managers.u;
import com.models.CouponProducts.PaymentGateway;
import com.player_framework.PlayerConstants.PauseReasons;
import com.services.g;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.at;
import com.services.l.au;
import com.services.l.s;
import com.services.l.z;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseActivity extends AppCompatActivity {
    private static final String CAST_GAANA = "Cast gaana";
    private static ad onLoginSucess;
    public LinearLayout backgroundAdSlot;
    private f colombiaVideoAdManager = f.v();
    public FrameLayout companionAdSlot;
    protected View contentView;
    public String currentFavpage = "";
    public String currentItem = "";
    public String currentPagerView = "";
    public String currentScreen = "";
    protected RelativeLayout frameContainer;
    protected GaanaApplication mAppState;
    private final d mCastConsumer = new d() {
        public void onConnectionSuspended(int i) {
        }

        public void onConnectivityRecovered() {
        }

        public void onFailed(int i, int i2) {
        }

        public void onCastAvailabilityChanged(boolean z) {
            if ((BaseActivity.this.mFragment instanceof DynamicHomeFragment) || (BaseActivity.this.mFragment instanceof RadioActivityFragment) || (BaseActivity.this.mFragment instanceof DiscoverFragment) || (BaseActivity.this.mFragment instanceof MyMusicFragment)) {
                if (BaseActivity.this.miSBaseActivityVisible) {
                    Constants.cb = true;
                    Constants.cc = false;
                    if (z) {
                        BaseActivity.this.ShowCastCoachMarks();
                    }
                } else {
                    BaseActivity.this.mShowOverlay = true;
                }
            }
        }
    };
    private VideoCastManager mCastManager;
    public GoogleApiClient mClient;
    protected Context mContext;
    private boolean mCurrentLoginStatus = false;
    protected com.services.d mDeviceResManager;
    public com.services.f mDialog;
    protected BaseGaanaFragment mFragment;
    protected LayoutInflater mLayoutInflater;
    private MenuItem mMediaRouterButton;
    private at mOnUserRefreshedListener = null;
    private ProgressBar mProgressBar;
    private ProgressDialog mProgressDialog = null;
    private boolean mShowOverlay;
    private boolean miSBaseActivityVisible;
    protected ConstraintLayout noInternetLayout;
    public VideoPlayerView overlayExoview;

    public interface RadioCallBackListener {
        void playRadioNow(BusinessObject businessObject);
    }

    private void ShowCastCoachMarks() {
    }

    /* Access modifiers changed, original: protected */
    public void refreshScreen() {
    }

    public void refreshSidebar() {
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void sendGAEvent(String str, String str2, String str3) {
        u.a().a(str, str2, str3);
    }

    public void sendPaymentGAEvent(ProductItem productItem, String str) {
        if (productItem != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(productItem.getP_payment_mode());
            stringBuilder.append("-");
            stringBuilder.append(productItem.getP_id());
            String stringBuilder2 = stringBuilder.toString();
            sendGAEvent("SubscriptionPayment", stringBuilder2, str);
            if (str.equalsIgnoreCase("Failure")) {
                UninstallIO.sendPaymentFailureEvent(stringBuilder2);
            }
        }
    }

    public void sendCouponPaymentGAEvent(PaymentGateway paymentGateway, String str) {
        if (paymentGateway != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(paymentGateway.b());
            stringBuilder.append("-");
            stringBuilder.append(paymentGateway.e());
            String stringBuilder2 = stringBuilder.toString();
            sendGAEvent("SubscriptionPayment", stringBuilder2, str);
            if (str.equalsIgnoreCase("Failure")) {
                UninstallIO.sendPaymentFailureEvent(stringBuilder2);
            }
        }
    }

    public void onFragmentStart(String str) {
        MoEngage.getInstance().onFragmentStart(this, str);
    }

    public void onFragmentStop(String str) {
        MoEngage.getInstance().onFragmentStop(this, str);
    }

    public void setGoogleAnalyticsScreenName(String str) {
        u.a().a(str);
    }

    public GoogleApiClient getGoogleApiClient() {
        return this.mClient;
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        Util.a(true);
        comScore.onEnterForeground();
        this.mCastManager.a(this.mCastConsumer);
        this.mCastManager.b();
        this.miSBaseActivityVisible = true;
        if (this.mShowOverlay) {
            this.mCastConsumer.onCastAvailabilityChanged(true);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        Util.a(false);
        comScore.onExitForeground();
        this.miSBaseActivityVisible = false;
        if (this.mCastManager != null && this.mCastConsumer != null) {
            this.mCastManager.b(this.mCastConsumer);
            this.mCastManager.c();
        }
    }

    public void initializeMediaRouterButton(Menu menu, int i) {
        this.mMediaRouterButton = this.mCastManager.a(menu, i);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        q.a().b();
        DownloadManager.c().a(GaanaApplication.getContext());
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        this.mOnUserRefreshedListener = null;
        DownloadManager.c().b(GaanaApplication.getContext());
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        supportRequestWindowFeature(9);
        super.onCreate(bundle);
        this.mDeviceResManager = com.services.d.a();
        this.mAppState = GaanaApplication.getInstance();
        this.mContext = this;
        this.mCurrentLoginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        this.mLayoutInflater = LayoutInflater.from(this);
        if (this instanceof GaanaActivity) {
            this.contentView = this.mLayoutInflater.inflate(R.layout.gaana_layout, null);
            this.backgroundAdSlot = (LinearLayout) this.contentView.findViewById(R.id.audioAdSlot);
            ((TextView) this.contentView.findViewById(R.id.adprogresstext)).setTypeface(i.a(getAssets(), "fonts/Roboto-Medium.ttf"));
            this.noInternetLayout = (ConstraintLayout) ((ViewStub) this.contentView.findViewById(R.id.no_internet_stub)).inflate();
            if (this.noInternetLayout != null) {
                this.noInternetLayout.animate().translationY((float) Util.b(40));
            }
            setContentView(this.contentView);
            if (Constants.aZ) {
                ((ViewStub) this.contentView.findViewById(R.id.home_prescreen_container)).inflate();
            }
        }
        this.frameContainer = (RelativeLayout) findViewById(R.id.frame_container);
        this.mClient = new Builder(GaanaApplication.getContext()).addApi(AppIndex.APP_INDEX_API).build();
        System.setProperty("log.tag.SwrveSDK", "SUPPRESS");
        System.setProperty("log.tag.SwrveMessagingSDK", "SUPPRESS");
        if (Util.j((Context) this)) {
            com.logging.b.a().a(this);
        }
        setUpCast();
    }

    public void setCustomActionBar(ViewGroup viewGroup, View view) {
        Toolbar toolbar = new Toolbar(this.mContext);
        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.home_toolbar);
        linearLayout.removeAllViews();
        linearLayout.addView(toolbar);
        int i = 0;
        linearLayout.setVisibility(0);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.removeAllViews();
        if (view == null) {
            toolbar.setVisibility(8);
            toolbar.getMenu().clear();
            return;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeAllViews();
        }
        toolbar.invalidate();
        toolbar.getMenu().clear();
        toolbar.addView(view);
        toolbar.setVisibility(0);
        if (view instanceof DetailsMaterialActionBar) {
            toolbar.setBackgroundDrawable(null);
            toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_color));
            toolbar.inflateMenu(R.menu.cast_menu_detail);
            initializeMediaRouterButton(toolbar.getMenu(), R.id.media_route_menu_item);
        } else if (view instanceof GenericActionBar) {
            BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
            toolbar.inflateMenu(R.menu.cast_menu_home);
            ((GenericActionBar) view).setToolbar(toolbar);
            Menu menu = toolbar.getMenu();
            if (!(menu == null || menu.findItem(R.id.media_route_menu_item) == null)) {
                initializeMediaRouterButton(menu, R.id.media_route_menu_item);
            }
            if (menu != null) {
                MenuItem findItem = menu.findItem(R.id.searchview_actionbar);
                int i2 = findItem != null ? 1 : 0;
                if (findItem.getIcon() != null) {
                    i = 1;
                }
                if ((i & i2) != 0) {
                    if (Constants.l) {
                        DrawableCompat.setTint(findItem.getIcon(), ViewCompat.MEASURED_STATE_MASK);
                    } else {
                        DrawableCompat.setTint(findItem.getIcon(), -1);
                    }
                }
            }
            if (currentFragment != null) {
                currentFragment.setmToolbar(toolbar);
            }
        } else if (view instanceof SearchActionBar) {
            if (Constants.l) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.white));
            } else {
                toolbar.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.search_background_dark));
            }
            ((SearchActionBar) view).setSearchInnerActionBarVisibility(false);
        }
    }

    private void setUpCast() {
        this.mCastManager = VideoCastManager.y();
        this.mCastManager.p();
    }

    public void showHomescreenCoachmarks() {
        ShowCastCoachMarks();
    }

    public void getSetCastCoachmarkPosition() {
        Toolbar toolbar = ((GaanaActivity) this.mContext).getCurrentFragment().getmToolbar();
        if (toolbar != null) {
            Menu menu = toolbar.getMenu();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < menu.size(); i2++) {
                if (menu.getItem(i2).isVisible()) {
                    arrayList.add(menu.getItem(i2).getTitle().toString());
                }
            }
            int i3 = -1;
            while (i < arrayList.size()) {
                if (menu.getItem(i).getTitle().equals(CAST_GAANA)) {
                    i3 = i + 1;
                }
                i++;
            }
            if (i3 > -1) {
                Constants.ce = (arrayList.size() + 1) - i3;
            }
        }
    }

    private void openCastCoachmark() {
        Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
        intent.putExtra("COACHMARK_VALUE", "HOME_CAST_FIRST_TIME");
        startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
        overridePendingTransition(17432576, 17432577);
    }

    public void setUserDetailsToHelpShift() {
        UserInfo currentUser = this.mAppState.getCurrentUser();
        ArrayList arrayList = new ArrayList();
        if (!(currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null)) {
            String fullname = currentUser.getUserProfile().getFullname();
            String email = currentUser.getUserProfile().getEmail();
            a.a(fullname, email);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user-id-");
            stringBuilder.append(currentUser.getUserProfile().getUserId());
            l.a(stringBuilder.toString());
            arrayList.add(fullname);
            arrayList.add(email);
            if (currentUser.getLoginType() == LoginType.PHONENUMBER) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PHONE:");
                stringBuilder2.append(currentUser.getUserProfile().getPhoneNumber());
                arrayList.add(stringBuilder2.toString());
            }
            if (this.mAppState.getCurrentUser().getUserSubscriptionData() != null) {
                arrayList.add(this.mAppState.getCurrentUser().getUserSubscriptionData().getServerAccountType());
                if (ap.a().r()) {
                    arrayList.add("no_ads");
                }
            }
        }
        HashMap hashMap = new HashMap();
        if (arrayList.size() == 0) {
            arrayList = new ArrayList();
            arrayList.add("logged_out");
        }
        hashMap.put("hs-tags", arrayList.toArray(new String[arrayList.size()]));
        final h hVar = new h(hashMap);
        l.a(new com.helpshift.support.i() {
            public h call() {
                return hVar;
            }
        });
    }

    public void showProgressDialog() {
        showProgressDialog(Boolean.valueOf(false));
    }

    public void showProgressDialog(Boolean bool) {
        if (!isFinishing()) {
            try {
                if (!bool.booleanValue()) {
                    this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
                    if (this.mProgressBar != null) {
                        this.mProgressBar.setVisibility(0);
                    }
                } else if (this.mProgressDialog == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(getString(R.string.updating_text));
                    stringBuilder.append("\t\t\t\t\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, false);
                    this.mProgressDialog.setCancelable(true);
                } else if (!this.mProgressDialog.isShowing()) {
                    this.mProgressDialog.show();
                }
            } catch (Exception unused) {
            }
        }
    }

    public void showProgressDialog(Boolean bool, String str) {
        if (!isFinishing()) {
            try {
                StringBuilder stringBuilder;
                if (this.mProgressDialog == null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                } else if (this.mProgressDialog.isShowing()) {
                    this.mProgressDialog.dismiss();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                }
            } catch (Exception unused) {
            }
        }
    }

    public void refreshUser(at atVar, boolean z) {
        this.mOnUserRefreshedListener = atVar;
        LoginManager.getInstance().loginSilently(this, new IOnLoginCompleted() {
            public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
                    if (BaseActivity.this.mOnUserRefreshedListener != null) {
                        BaseActivity.this.mOnUserRefreshedListener.onUserRefreshed();
                    }
                } else if (login_status == LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED) {
                    ap.a().a(BaseActivity.this, false, null, LOGIN_STATUS.LOGGED_OUT);
                }
            }
        }, z);
    }

    public void refreshSocialAnsSsoTicket(at atVar) {
        this.mOnUserRefreshedListener = atVar;
        LoginManager.getInstance().updateSsoTicketAndSocialToken(this, new IOnLoginCompleted() {
            public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
                    if (BaseActivity.this.mOnUserRefreshedListener != null) {
                        BaseActivity.this.mOnUserRefreshedListener.onUserRefreshed();
                    }
                } else if (login_status == LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED) {
                    ap.a().a(BaseActivity.this, false, null, LOGIN_STATUS.LOGGED_OUT);
                }
            }
        });
    }

    public void updateUserStatus(au auVar) {
        if (!this.mAppState.isAppInOfflineMode() && this.mAppState.getCurrentUser().getLoginStatus()) {
            LoginManager.getInstance().getUserStatus(this, auVar, true);
        }
    }

    public void hideProgressDialog() {
        try {
            this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            if (this.mProgressBar != null) {
                this.mProgressBar.setVisibility(8);
            }
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    public void checkSetLoginStatus(ad adVar, String str) {
        checkSetLoginStatus(adVar, str, false);
    }

    public void checkSetLoginStatus(ad adVar, String str, boolean z) {
        checkSetLoginStatus(adVar, str, z, false);
    }

    public void checkSetLoginStatus(ad adVar, String str, boolean z, boolean z2) {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            this.mAppState.hasLoginStatusChanged = Boolean.valueOf(true);
            adVar.onLoginSuccess();
        } else if (!Constants.T || this.mDeviceResManager.c("PREF_KEY_REFERRAL_INFO", false) == null) {
            onLoginSucess = adVar;
            Intent intent = new Intent(this.mContext, Login.class);
            intent.putExtra("is_login_as_activity_result", true);
            intent.putExtra("Launched_From", str);
            intent.putExtra("SHOW_CAMPAIGN_MESSAGE", z);
            if (z2) {
                intent.putExtra("ONBOARD_SIGNUP", true);
            } else {
                intent.putExtra("ONBOARD_LOGIN", true);
            }
            startActivityForResult(intent, 701);
        } else {
            ReferralSignup referralSignup = (ReferralSignup) new Gson().fromJson(this.mDeviceResManager.c("PREF_KEY_REFERRAL_INFO", false), ReferralSignup.class);
            Intent intent2 = new Intent(getApplicationContext(), ReferralSignupActivity.class);
            intent2.putExtra("is_first_ap_launch", true);
            intent2.putExtra("referralInfo", referralSignup);
            startActivityForResult(intent2, 701);
            this.mDeviceResManager.b("PREF_KEY_REFERRAL_INFO", false);
        }
    }

    public void checkSetLoginStatusFromBottomSheet(ad adVar, String str, String str2, boolean z, boolean z2) {
        onLoginSucess = adVar;
        Intent intent = new Intent(this.mContext, Login.class);
        intent.putExtra("is_login_as_activity_result", true);
        intent.putExtra("Launched_From", str2);
        intent.putExtra("LOGIN_LAUNCHED_SOURCE", str);
        intent.putExtra("SHOW_CAMPAIGN_MESSAGE", z2);
        if (z) {
            intent.putExtra("ONBOARD_SIGNUP", true);
        } else {
            intent.putExtra("ONBOARD_LOGIN", true);
        }
        startActivityForResult(intent, 701);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        boolean z = this instanceof Login;
        if (z) {
            ((Login) this.mContext).removeGoogleSignSmartDialog();
        }
        if (i != 101) {
            if (i != 706) {
                if (i != 708) {
                    if (i != 710) {
                        if (i == 712) {
                            com.d.a.a().a(i, i2, intent);
                        } else if (i != 1001) {
                            if (i != PointerIconCompat.TYPE_ALIAS) {
                                if (i != 64206) {
                                    switch (i) {
                                        case GooglePlusLogin.RC_SIGN_IN /*209*/:
                                        case GooglePlusLogin.RC_CREDENTIALS_READ /*210*/:
                                        case GooglePlusLogin.RC_CREDENTIALS_SAVE /*211*/:
                                        case GooglePlusLogin.RC_HINT /*212*/:
                                            GooglePlusLogin.getInstance().authorizeCallBack(i, i2, intent);
                                            return;
                                        default:
                                            switch (i) {
                                                case 701:
                                                    if (i2 != 0) {
                                                        o.a().b();
                                                        if (onLoginSucess != null) {
                                                            onLoginSucess.onLoginSuccess();
                                                            this.mAppState.hasLoginStatusChanged = Boolean.valueOf(true);
                                                        }
                                                    }
                                                    Constants.k = false;
                                                    return;
                                                case 702:
                                                    break;
                                                default:
                                                    return;
                                            }
                                    }
                                }
                                g.a().a((Activity) this, i, i2, intent);
                                if (i2 == 0) {
                                    g.a = false;
                                    g.b++;
                                    if (g.b != 0 && g.b % 2 == 0) {
                                        g.a().h();
                                    }
                                }
                            } else if (i2 != 0) {
                                Constants.cb = true;
                                Constants.cc = false;
                                ShowCastCoachMarks();
                            }
                        } else if (Constants.dc) {
                            com.player_framework.o.c((Context) this, PauseReasons.MEDIA_BUTTON_TAP);
                            Constants.dc = false;
                        }
                    } else if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
                        PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").a(i, i2, intent);
                    }
                } else if (i2 != 0) {
                    updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            if (BaseActivity.this instanceof GaanaActivity) {
                                if (BaseActivity.onLoginSucess == null) {
                                    Intent intent = new Intent(BaseActivity.this.mContext, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    BaseActivity.this.mContext.startActivity(intent);
                                    return;
                                }
                                BaseActivity.onLoginSucess.onLoginSuccess();
                            } else if (BaseActivity.this instanceof Login) {
                                BaseActivity.this.setResult(-1, BaseActivity.this.getIntent());
                                BaseActivity.this.finish();
                            }
                        }
                    });
                } else if (z) {
                    finish();
                }
            } else if (this.mFragment instanceof EditProfileActivityFragment) {
                ((EditProfileActivityFragment) this.mFragment).a(i, i2, intent);
            } else if (this.mFragment instanceof LyricsBannerFragment) {
                ((LyricsBannerFragment) this.mFragment).a(i, i2, intent);
            }
        } else if (Constants.dc) {
            com.player_framework.o.c((Context) this, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = false;
        }
    }

    public void addRemoveFav(BusinessObject businessObject, Boolean bool, boolean z, ap.a aVar) {
        String stringBuilder;
        StringBuilder stringBuilder2;
        if (bool.booleanValue()) {
            n.a().c(businessObject);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(businessObject.getName());
            stringBuilder2.append(" ");
            stringBuilder2.append(getString(R.string.has_been_removed_from_favorites));
            stringBuilder = stringBuilder2.toString();
        } else {
            n.a().b(businessObject);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(businessObject.getName());
            stringBuilder2.append(" ");
            stringBuilder2.append(getString(R.string.has_been_added_to_favorites));
            stringBuilder = stringBuilder2.toString();
        }
        if (aVar != null) {
            aVar.onFavoriteCompleted(businessObject, true);
        }
        if (!z) {
            aj.a().a(this, stringBuilder);
        }
        BaseFragment baseFragment = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        if (baseFragment == null) {
            return;
        }
        if (baseFragment instanceof PlayerFragmentV2) {
            ((PlayerFragmentV2) baseFragment).h();
        } else if (baseFragment instanceof PlayerFragmentV4) {
            ((PlayerFragmentV4) baseFragment).m();
        } else if (baseFragment instanceof PlayerRadioFragmentV4) {
            ((PlayerRadioFragmentV4) baseFragment).k();
        }
    }

    public void addRemoveFav(BusinessObject businessObject, Boolean bool, boolean z) {
        addRemoveFav(businessObject, bool, z, null);
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        if (this.mFragment != null) {
            this.mFragment.refreshListView(businessObject, z);
        }
    }

    public void handleError(String str) {
        ap.a().f((Context) this);
    }

    public void displayFeatureNotAvailableDataSaveModeDialog(final int i, final int i2) {
        new com.services.f(this).a(this.mContext.getString(R.string.gaana_text), getResources().getString(R.string.error_msg_feature_not_available_data_save_mode), Boolean.valueOf(true), getString(R.string.disable_data_save_mode), getString(R.string.dlg_msg_cancel), new com.services.f.b() {
            public void onOkListner(String str) {
                int i;
                int i2 = false;
                BaseActivity.this.mDeviceResManager.a("PREFERENCE_KEY_DATA_SAVE_MODE", false, false);
                BaseActivity.this.mDeviceResManager.a("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", i2, true);
                BaseActivity.this.mAppState.setAppInDataSaveMode(false);
                if (i2 != -1) {
                    i = i2;
                } else {
                    i = BaseActivity.this.mDeviceResManager.b("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", 0, true);
                }
                if (BaseActivity.this.mAppState.getCurrentUser().getLoginStatus()) {
                    BaseActivity.this.setPlayerSyncQuality(i);
                }
                if (i == -1) {
                    switch (BaseActivity.this.mDeviceResManager.b("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", 10000, false)) {
                        case 10000:
                            i2 = 4;
                            break;
                        case 10001:
                            i2 = 3;
                            break;
                        case 10002:
                            i2 = 2;
                            break;
                        case 10003:
                            i2 = 1;
                            break;
                        case 10004:
                            break;
                        default:
                            i2 = i;
                            break;
                    }
                }
                i2 = i;
                if (BaseActivity.this.mContext instanceof GaanaActivity) {
                    ((GaanaActivity) BaseActivity.this.mContext).updateNavigationListView();
                }
                BaseActivity.this.setPlayerStreamingQuality(i2);
            }

            public void onCancelListner() {
                Util.b("data_save_mode", "1");
            }
        });
    }

    private void setPlayerStreamingQuality(int i) {
        String string;
        String str;
        PlayerManager.a I = PlayerManager.a(this.mContext).I();
        if (ap.a().s()) {
            string = this.mContext.getResources().getString(R.string.stream_quality_hd);
            str = "High Definition";
        } else {
            string = this.mContext.getResources().getString(R.string.stream_quality_hd_gaanaplus);
            str = "High Definition (Gaana+ only)";
        }
        String[] strArr = new String[]{this.mContext.getResources().getString(R.string.stream_quality_auto), string, this.mContext.getResources().getString(R.string.stream_quality_high), this.mContext.getResources().getString(R.string.stream_quality_med), this.mContext.getResources().getString(R.string.stream_quality_low)};
        String[] strArr2 = new String[]{"Auto", str, "High", "Medium", "Low"};
        int[] iArr = new int[]{10004, 10003, 10002, 10001, 10000};
        aj a;
        Context context;
        StringBuilder stringBuilder;
        if (i == 0) {
            if (this.mDeviceResManager.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) != iArr[i]) {
                this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                aj.a().a(this.mContext, getString(R.string.adjust_sound_quality_toast));
                if (I != null) {
                    I.j();
                }
                u.a().a(getString(R.string.mini_player), getString(R.string.set_streaming_quality), strArr2[i]);
            }
        } else if (i == 1) {
            if (!ap.a().s()) {
                u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                Util.a(this.mContext, this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), getString(R.string.hd_quality_english));
            } else if (this.mDeviceResManager.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) != iArr[i]) {
                this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                a = aj.a();
                context = this.mContext;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.changing_sound_quality));
                stringBuilder.append(strArr[i]);
                a.a(context, stringBuilder.toString());
                if (I != null) {
                    I.j();
                }
                u.a().a("Mini Player", "Set Streaming Quality", strArr2[i]);
            }
        } else if (this.mDeviceResManager.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) != iArr[i]) {
            this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
            a = aj.a();
            context = this.mContext;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.changing_sound_quality));
            stringBuilder.append(strArr[i]);
            a.a(context, stringBuilder.toString());
            if (I != null) {
                I.j();
            }
            u.a().a("Mini Player", "Set Streaming Quality", strArr2[i]);
        }
    }

    private void setPlayerSyncQuality(int i) {
        StringBuilder stringBuilder;
        switch (i) {
            case 0:
                if (!showDialogForGaanaPlusSubscribe()) {
                    if (this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 0) {
                        u.a().a("Settings", "Set Download Quality", "Regular");
                    }
                    saveToPreference("PREFERENCE_KEY_SYNC_QUALITY", 0);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(i);
                    Util.b("download_quality", stringBuilder.toString());
                    return;
                }
                return;
            case 1:
                if (!showDialogForGaanaPlusSubscribe()) {
                    if (this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 1) {
                        u.a().a("Settings", "Set Download Quality", "High");
                    }
                    saveToPreference("PREFERENCE_KEY_SYNC_QUALITY", 1);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(i);
                    Util.b("download_quality", stringBuilder.toString());
                    return;
                }
                return;
            case 2:
                if (!showDialogForGaanaPlusSubscribe()) {
                    if (this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 2) {
                        u.a().a("Settings", "Set Download Quality", "Extreme");
                    }
                    saveToPreference("PREFERENCE_KEY_SYNC_QUALITY", 2);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(i);
                    Util.b("download_quality", stringBuilder.toString());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void saveToPreference(String str, int i) {
        this.mDeviceResManager.a(str, i, true);
    }

    private boolean showDialogForGaanaPlusSubscribe() {
        if (ap.a().o()) {
            return false;
        }
        this.mDialog.a(this.mContext.getString(R.string.dlg_msg_gaanaplus), getString(R.string.need_to_be_gaana_plus_user), Boolean.valueOf(true), getString(R.string.tell_me_more), this.mContext.getString(R.string.dlg_msg_cancel), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_SETTINGS", 1);
                BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                settingsDetailFragment.setArguments(bundle);
                ((GaanaActivity) BaseActivity.this.mContext).displayFragment(settingsDetailFragment);
            }
        });
        return true;
    }

    public void displayFeatureNotAvailableOfflineDialog(String str) {
        new com.services.f(this).a(getString(R.string.gaana_text), getResources().getString(R.string.error_msg_feature_not_available_offline), Boolean.valueOf(true), getString(R.string.go_online_text), getString(R.string.dlg_msg_cancel), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                BaseActivity.this.mDeviceResManager.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                BaseActivity.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                BaseActivity.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                BaseActivity.this.mAppState.setAppInOfflineMode(false);
                DownloadManager.c().d();
            }
        });
    }

    public Boolean hasLoginChanged() {
        if (this.mAppState.isAppInOfflineMode()) {
            return Boolean.valueOf(false);
        }
        if (this.mCurrentLoginStatus == this.mAppState.getCurrentUser().getLoginStatus()) {
            return Boolean.valueOf(false);
        }
        u.a().d();
        resetLoginStatus();
        return Boolean.valueOf(true);
    }

    public void followUnfollow(BusinessObject businessObject, URLManager uRLManager, int i, z zVar, int i2) {
        showProgressDialog(Boolean.valueOf(true), this.mContext.getResources().getString(R.string.updating_text));
        final z zVar2 = zVar;
        final int i3 = i;
        final BusinessObject businessObject2 = businessObject;
        final int i4 = i2;
        com.i.i.a().a(new af() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x003e  */
            /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
            public void onRetreivalComplete(java.lang.Object r4) {
                /*
                r3 = this;
                r0 = com.gaana.BaseActivity.this;
                r0.hideProgressDialog();
                if (r4 == 0) goto L_0x001c;
            L_0x0007:
                r0 = com.managers.ap.a();
                r1 = r4;
                r1 = (java.lang.String) r1;
                r0 = r0.d(r1);
                if (r0 == 0) goto L_0x001c;
            L_0x0014:
                r0 = r0.length();
                if (r0 == 0) goto L_0x001c;
            L_0x001a:
                r0 = 1;
                goto L_0x001d;
            L_0x001c:
                r0 = 0;
            L_0x001d:
                r1 = com.managers.ap.a();
                r4 = (java.lang.String) r4;
                r1 = r1.e(r4);
                r2 = 3;
                if (r1 != r2) goto L_0x003e;
            L_0x002a:
                r0 = com.managers.aj.a();
                r1 = com.gaana.BaseActivity.this;
                r1 = r1.mContext;
                r2 = com.managers.ap.a();
                r4 = r2.f(r4);
                r0.a(r1, r4);
                goto L_0x0078;
            L_0x003e:
                r1 = r3;
                if (r1 == 0) goto L_0x0078;
            L_0x0042:
                if (r0 == 0) goto L_0x006b;
            L_0x0044:
                r0 = r4;
                r1 = -1;
                if (r0 == r1) goto L_0x005b;
            L_0x0049:
                r0 = r3;
                r1 = r5;
                r2 = com.managers.ap.a();
                r4 = r2.e(r4);
                r2 = r4;
                r0.a(r1, r4, r2);
                goto L_0x0078;
            L_0x005b:
                r0 = r3;
                r1 = r5;
                r2 = com.managers.ap.a();
                r4 = r2.e(r4);
                r0.a(r1, r4);
                goto L_0x0078;
            L_0x006b:
                r0 = r3;
                r1 = com.managers.ap.a();
                r4 = r1.e(r4);
                r0.a(r4);
            L_0x0078:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.BaseActivity$AnonymousClass8.onRetreivalComplete(java.lang.Object):void");
            }

            public void onErrorResponse(BusinessObject businessObject) {
                BaseActivity.this.hideProgressDialog();
                if (zVar2 != null) {
                    zVar2.a(i4);
                }
            }
        }, uRLManager);
    }

    public void followUnfollow(BusinessObject businessObject, URLManager uRLManager, z zVar, int i) {
        followUnfollow(businessObject, uRLManager, -1, zVar, i);
    }

    public void startDownloadDbRetreival(final s sVar, final URLManager uRLManager) {
        if (uRLManager.n()) {
            startMyMusicRetreival(sVar, uRLManager);
        } else {
            com.i.d.a(new Runnable() {
                private BusinessObject mDownloadBusinessObject = null;
                private s newOnbusinessObjectReterived = sVar;

                public void run() {
                    BusinessObjectType i = uRLManager.i();
                    if (i == BusinessObjectType.Tracks) {
                        if (uRLManager.z()) {
                            this.mDownloadBusinessObject = DownloadManager.c().a(uRLManager.q(), false, true, 2, 20);
                        } else {
                            this.mDownloadBusinessObject = DownloadManager.c().a(uRLManager.q(), false, false, -1, -1);
                        }
                    } else if (i == BusinessObjectType.Albums) {
                        this.mDownloadBusinessObject = DownloadManager.c().g(uRLManager.q());
                    } else if (i == BusinessObjectType.Playlists) {
                        this.mDownloadBusinessObject = DownloadManager.c().f(uRLManager.q());
                    }
                    if (this.newOnbusinessObjectReterived != null) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AnonymousClass9.this.newOnbusinessObjectReterived.onRetreivalComplete(AnonymousClass9.this.mDownloadBusinessObject);
                            }
                        });
                    }
                }
            });
        }
    }

    private void sortMyPlaylist(ArrayList<BusinessObject> arrayList) {
        if (arrayList.size() > 0) {
            int i = 0;
            if (arrayList.get(0) != null && (arrayList.get(0) instanceof Playlist)) {
                int i2 = 0;
                while (i < arrayList.size() && i != (arrayList.size() - 1) - i2) {
                    if (((Playlist) arrayList.get(i)).getAutomated() != null && ((Playlist) arrayList.get(i)).getAutomated().equalsIgnoreCase("1")) {
                        Playlist playlist = (Playlist) arrayList.get(i);
                        arrayList.remove(i);
                        arrayList.add(playlist);
                        i--;
                        i2++;
                    }
                    i++;
                }
            }
        }
    }

    public void startMyMusicRetreival(final s sVar, final URLManager uRLManager) {
        com.i.d.a(new Runnable() {
            private BusinessObject mMyMusicBusinessObject = new BusinessObject();
            private s newOnbusinessObjectReterived = sVar;

            public void run() {
                BusinessObjectType i = uRLManager.i();
                ArrayList arrayList = new ArrayList();
                final BusinessObject localMedia = LocalMediaManager.getInstance(BaseActivity.this.mContext).getLocalMedia(uRLManager);
                if (!(localMedia == null || localMedia.getArrListBusinessObj() == null)) {
                    if (localMedia instanceof NextGenSearchAutoSuggests) {
                        BaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AnonymousClass10.this.newOnbusinessObjectReterived.onRetreivalComplete(localMedia);
                            }
                        });
                        return;
                    }
                    arrayList.addAll(localMedia.getArrListBusinessObj());
                }
                if (i == BusinessObjectType.Playlists && Constants.Q) {
                    String q = uRLManager.q();
                    if (TextUtils.isEmpty(q) || "Recently Added".contains(q)) {
                        Playlist recentlyAddedPlaylist = LocalMediaManager.getInstance(BaseActivity.this.mContext).getRecentlyAddedPlaylist();
                        if (recentlyAddedPlaylist != null) {
                            arrayList.add(0, recentlyAddedPlaylist);
                        }
                    }
                }
                BaseActivity.this.sortMyPlaylist(arrayList);
                this.mMyMusicBusinessObject.setArrListBusinessObj(arrayList);
                BaseActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AnonymousClass10.this.newOnbusinessObjectReterived.onRetreivalComplete(AnonymousClass10.this.mMyMusicBusinessObject);
                    }
                });
            }
        });
    }

    public void getDownloadedBusinessObject(final s sVar, final String str, final URLManager uRLManager) {
        com.i.d.a(new Runnable() {
            private BusinessObject mDownloadBusinessObject = null;
            private s newOnbusinessObjectReterived = sVar;

            public void run() {
                if (uRLManager.n()) {
                    this.mDownloadBusinessObject = LocalMediaManager.getInstance(GaanaApplication.getContext()).getDetailPage(uRLManager, str);
                } else {
                    this.mDownloadBusinessObject = DownloadManager.c().a(str, false);
                }
                if (this.mDownloadBusinessObject != null && this.newOnbusinessObjectReterived != null) {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    AnonymousClass11.this.newOnbusinessObjectReterived.onRetreivalComplete(AnonymousClass11.this.mDownloadBusinessObject);
                                }
                            }, 0);
                        }
                    });
                }
            }
        });
    }

    public void getDownloadedBusinessObject(final com.android.volley.i.b<Object> bVar, final String str, final URLManager uRLManager) {
        com.i.d.a(new Runnable() {
            private BusinessObject mDownloadBusinessObject = null;
            private com.android.volley.i.b<Object> newOnbusinessObjectReterived = bVar;

            public void run() {
                if (uRLManager.n()) {
                    this.mDownloadBusinessObject = LocalMediaManager.getInstance(GaanaApplication.getContext()).getDetailPage(uRLManager, str);
                } else {
                    this.mDownloadBusinessObject = DownloadManager.c().a(str, false);
                }
                if (this.mDownloadBusinessObject != null && this.newOnbusinessObjectReterived != null) {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    AnonymousClass12.this.newOnbusinessObjectReterived.onResponse(AnonymousClass12.this.mDownloadBusinessObject);
                                }
                            }, 0);
                        }
                    });
                }
            }
        });
    }

    public void getMyPlaylistDetails(final com.android.volley.i.b<Object> bVar, final Playlist playlist, final URLManager uRLManager) {
        com.i.d.a(new Runnable() {
            private BusinessObject mTracks = null;
            private com.android.volley.i.b<Object> newListener = bVar;

            /* JADX WARNING: Removed duplicated region for block: B:27:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
            public void run() {
                /*
                r4 = this;
                r0 = r4;	 Catch:{ Exception -> 0x008a }
                r0 = r0.m();	 Catch:{ Exception -> 0x008a }
                r0 = r0.booleanValue();	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x007e;
            L_0x000c:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r0 = r0.mAppState;	 Catch:{ Exception -> 0x008a }
                r0 = r0.isAppInOfflineMode();	 Catch:{ Exception -> 0x008a }
                if (r0 != 0) goto L_0x007e;
            L_0x0016:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r0 = r0.mContext;	 Catch:{ Exception -> 0x008a }
                r0 = com.utilities.Util.j(r0);	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x007e;
            L_0x0020:
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r1 = r1.getBusinessObjId();	 Catch:{ Exception -> 0x008a }
                r0 = r0.getPlaylistSyncStatus(r1);	 Catch:{ Exception -> 0x008a }
                r1 = -2;
                if (r0 == 0) goto L_0x0036;
            L_0x0031:
                r2 = -1;
                if (r0 == r2) goto L_0x0036;
            L_0x0034:
                if (r0 != r1) goto L_0x003f;
            L_0x0036:
                r2 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r3 = r3;	 Catch:{ Exception -> 0x008a }
                r2.performSync(r3, r0);	 Catch:{ Exception -> 0x008a }
            L_0x003f:
                if (r0 != r1) goto L_0x0049;
            L_0x0041:
                r0 = new com.gaana.models.Tracks;	 Catch:{ Exception -> 0x008a }
                r0.<init>();	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x0049:
                r0 = r4;	 Catch:{ Exception -> 0x008a }
                r1 = 1;
                r0.i(r1);	 Catch:{ Exception -> 0x008a }
                r0 = com.managers.o.a();	 Catch:{ Exception -> 0x008a }
                r1 = r4;	 Catch:{ Exception -> 0x008a }
                r0 = r0.a(r1);	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x0071;
            L_0x005b:
                r1 = r0.getVolleyError();	 Catch:{ Exception -> 0x008a }
                if (r1 != 0) goto L_0x0071;
            L_0x0061:
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r2 = r4.mTracks;	 Catch:{ Exception -> 0x008a }
                r2 = (com.gaana.models.Tracks) r2;	 Catch:{ Exception -> 0x008a }
                r0.updatePlaylistTracks(r1, r2);	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x0071:
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r0 = r0.getPlaylistDetails(r1);	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x007e:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r1 = r4;	 Catch:{ Exception -> 0x008a }
                r2 = r3;	 Catch:{ Exception -> 0x008a }
                r0 = r0.fetchTracksForSyncingWithServerPlaylist(r1, r2);	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
            L_0x008a:
                r0 = r4.newListener;
                if (r0 == 0) goto L_0x0098;
            L_0x008e:
                r0 = com.gaana.BaseActivity.this;
                r1 = new com.gaana.BaseActivity$13$1;
                r1.<init>();
                r0.runOnUiThread(r1);
            L_0x0098:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.BaseActivity$AnonymousClass13.run():void");
            }
        });
    }

    public BusinessObject fetchTracksForSyncingWithServerPlaylist(URLManager uRLManager, Playlist playlist) {
        int playlistSyncStatus = PlaylistSyncManager.getInstance().getPlaylistSyncStatus(playlist.getBusinessObjId());
        BusinessObject tracks = new Tracks();
        if (playlistSyncStatus != -4) {
            return PlaylistSyncManager.getInstance().getPlaylistDetails(playlist);
        }
        if (!this.mAppState.isAppInOfflineMode() && Util.j(this.mContext)) {
            uRLManager.i(true);
            BusinessObject a = o.a().a(uRLManager);
            if (a == null || a.getVolleyError() != null) {
                return tracks;
            }
            PlaylistSyncManager.getInstance().updatePlaylistTracks(playlist, (Tracks) a);
            return a;
        } else if (!DownloadManager.c().b((BusinessObject) playlist).booleanValue()) {
            return tracks;
        } else {
            tracks.setArrListBusinessObj(DownloadManager.c().n(Integer.parseInt(playlist.getBusinessObjId())));
            return tracks;
        }
    }

    public void getMyPlaylistDetails(final s sVar, final Playlist playlist, final URLManager uRLManager) {
        com.i.d.a(new Runnable() {
            private BusinessObject mTracks = null;
            private s newOnbusinessObjectReterived = sVar;

            /* JADX WARNING: Removed duplicated region for block: B:27:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
            public void run() {
                /*
                r4 = this;
                r0 = r4;	 Catch:{ Exception -> 0x008a }
                r0 = r0.m();	 Catch:{ Exception -> 0x008a }
                r0 = r0.booleanValue();	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x007e;
            L_0x000c:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r0 = r0.mAppState;	 Catch:{ Exception -> 0x008a }
                r0 = r0.isAppInOfflineMode();	 Catch:{ Exception -> 0x008a }
                if (r0 != 0) goto L_0x007e;
            L_0x0016:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r0 = r0.mContext;	 Catch:{ Exception -> 0x008a }
                r0 = com.utilities.Util.j(r0);	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x007e;
            L_0x0020:
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r1 = r1.getBusinessObjId();	 Catch:{ Exception -> 0x008a }
                r0 = r0.getPlaylistSyncStatus(r1);	 Catch:{ Exception -> 0x008a }
                r1 = -2;
                if (r0 == 0) goto L_0x0036;
            L_0x0031:
                r2 = -1;
                if (r0 == r2) goto L_0x0036;
            L_0x0034:
                if (r0 != r1) goto L_0x003f;
            L_0x0036:
                r2 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r3 = r3;	 Catch:{ Exception -> 0x008a }
                r2.performSync(r3, r0);	 Catch:{ Exception -> 0x008a }
            L_0x003f:
                if (r0 != r1) goto L_0x0049;
            L_0x0041:
                r0 = new com.gaana.models.Tracks;	 Catch:{ Exception -> 0x008a }
                r0.<init>();	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x0049:
                r0 = r4;	 Catch:{ Exception -> 0x008a }
                r1 = 1;
                r0.i(r1);	 Catch:{ Exception -> 0x008a }
                r0 = com.managers.o.a();	 Catch:{ Exception -> 0x008a }
                r1 = r4;	 Catch:{ Exception -> 0x008a }
                r0 = r0.a(r1);	 Catch:{ Exception -> 0x008a }
                if (r0 == 0) goto L_0x0071;
            L_0x005b:
                r1 = r0.getVolleyError();	 Catch:{ Exception -> 0x008a }
                if (r1 != 0) goto L_0x0071;
            L_0x0061:
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r2 = r4.mTracks;	 Catch:{ Exception -> 0x008a }
                r2 = (com.gaana.models.Tracks) r2;	 Catch:{ Exception -> 0x008a }
                r0.updatePlaylistTracks(r1, r2);	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x0071:
                r0 = com.gaana.localmedia.PlaylistSyncManager.getInstance();	 Catch:{ Exception -> 0x008a }
                r1 = r3;	 Catch:{ Exception -> 0x008a }
                r0 = r0.getPlaylistDetails(r1);	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
                goto L_0x008a;
            L_0x007e:
                r0 = com.gaana.BaseActivity.this;	 Catch:{ Exception -> 0x008a }
                r1 = r4;	 Catch:{ Exception -> 0x008a }
                r2 = r3;	 Catch:{ Exception -> 0x008a }
                r0 = r0.fetchTracksForSyncingWithServerPlaylist(r1, r2);	 Catch:{ Exception -> 0x008a }
                r4.mTracks = r0;	 Catch:{ Exception -> 0x008a }
            L_0x008a:
                r0 = r4.newOnbusinessObjectReterived;
                if (r0 == 0) goto L_0x0098;
            L_0x008e:
                r0 = com.gaana.BaseActivity.this;
                r1 = new com.gaana.BaseActivity$14$1;
                r1.<init>();
                r0.runOnUiThread(r1);
            L_0x0098:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.BaseActivity$AnonymousClass14.run():void");
            }
        });
    }

    public void resetLoginStatus() {
        this.mCurrentLoginStatus = this.mAppState.getCurrentUser().getLoginStatus();
    }

    public int getSourceType(BusinessObject businessObject) {
        SOURCE_TYPE source_type = SOURCE_TYPE.OTHER;
        if (businessObject instanceof Album) {
            source_type = SOURCE_TYPE.ALBUM;
        } else if (businessObject instanceof Artist) {
            source_type = SOURCE_TYPE.ARTIST;
        } else if (businessObject instanceof Playlist) {
            source_type = SOURCE_TYPE.PLAYLIST;
        }
        return source_type.ordinal();
    }

    public void refreshListView() {
        if (Constants.aZ && (this instanceof GaanaActivity)) {
            GaanaActivity gaanaActivity = (GaanaActivity) this;
            if (gaanaActivity.mDrawerLayout.isDrawerOpen(8388611)) {
                gaanaActivity.refreshPreScreen();
            }
        }
        if (this.mFragment != null) {
            this.mFragment.refreshListView();
        }
    }

    public void startHelpShiftActivity() {
        if (this.mAppState.isAppInOfflineMode()) {
            displayFeatureNotAvailableOfflineDialog(getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j((Context) this)) {
            setUserDetailsToHelpShift();
            l.b(this);
        } else {
            ap.a().f((Context) this);
        }
    }

    public void startHelpShiftActivityFAQ(String str) {
        if (this.mAppState.isAppInOfflineMode()) {
            displayFeatureNotAvailableOfflineDialog(getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j((Context) this)) {
            setUserDetailsToHelpShift();
            l.b(this, str);
        } else {
            ap.a().f((Context) this);
        }
    }

    public void startHelpShiftActivitySection(String str) {
        if (this.mAppState.isAppInOfflineMode()) {
            displayFeatureNotAvailableOfflineDialog(getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j((Context) this)) {
            setUserDetailsToHelpShift();
            l.a((Activity) this, str);
        } else {
            ap.a().f((Context) this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.mOnUserRefreshedListener = null;
        super.onDestroy();
    }

    public void showFakePlayer(boolean z) {
        if (this.colombiaVideoAdManager.w() || this.colombiaVideoAdManager.t()) {
            if (this.contentView.findViewById(R.id.chotaPlayer) == null) {
                ((ViewStub) this.contentView.findViewById(R.id.frame_container).findViewById(R.id.chotaPlayer_stub)).inflate();
            }
            com.views.i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout != null) {
                slidingPanelLayout.a(2);
            }
            ((GaanaActivity) this.mContext).findViewById(R.id.bottom_bar).setVisibility(0);
            ((GaanaActivity) this.mContext).findViewById(R.id.llPlayerLayout).setVisibility(8);
            this.contentView.findViewById(R.id.chotaPlayer).setVisibility(0);
            TextView textView = (TextView) this.contentView.findViewById(R.id.chotaPlayer).findViewById(R.id.player_bottom_main_text_bottom1);
            TextView textView2 = (TextView) this.contentView.findViewById(R.id.chotaPlayer).findViewById(R.id.player_bottom_secondary_text_bottom1);
            textView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            textView2.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            this.contentView.findViewById(R.id.playerBtnPlayPlay).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.colombiaVideoAdManager.k(BaseActivity.this.colombiaVideoAdManager.D());
                    BaseActivity.this.colombiaVideoAdManager.A();
                    BaseActivity.this.hideFakePlayer();
                }
            });
            CrossFadeImageView crossFadeImageView = (CrossFadeImageView) this.contentView.findViewById(R.id.chotaPlayer).findViewById(R.id.player_bottom_image1);
            crossFadeImageView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            if (!TextUtils.isEmpty(this.colombiaVideoAdManager.r())) {
                textView.setText(this.colombiaVideoAdManager.r());
            }
            if (!TextUtils.isEmpty(this.colombiaVideoAdManager.r())) {
                textView2.setText(this.mContext.getString(R.string.sponsored_ad_text));
            }
            Bitmap p = this.colombiaVideoAdManager.p();
            if (p != null) {
                crossFadeImageView.setImageBitmap(p);
            }
        }
        if (z && (this.mContext instanceof GaanaActivity)) {
            ((GaanaActivity) this.mContext).changeFragment(R.id.LeftMenuPurchase, null, null);
        }
    }

    public void hideFakePlayer() {
        if (!this.colombiaVideoAdManager.w() && this.contentView.findViewById(R.id.chotaPlayer) != null && this.contentView.findViewById(R.id.chotaPlayer).getVisibility() == 0) {
            ((GaanaActivity) this.mContext).findViewById(R.id.llPlayerLayout).setVisibility(0);
            com.views.i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout != null && slidingPanelLayout.a() == 2) {
                slidingPanelLayout.a(0);
            }
            this.contentView.findViewById(R.id.chotaPlayer).setVisibility(8);
            AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
            this.colombiaVideoAdManager.h(false);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void setSendGAScreenName(String str) {
        this.currentScreen = str;
        this.mAppState.setPlayoutSectionName(str);
    }

    public void popBackStackToHome() {
        try {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        } catch (Exception unused) {
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return VideoCastManager.y().a(keyEvent, 0.1d) || super.dispatchKeyEvent(keyEvent);
    }
}

package com.gaana;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.provider.MediaStore.Audio.Media;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.Animatable2Compat.AnimationCallback;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.support.v7.widget.SwitchCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.custom_card_response.CustomCard;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.DynamicViewManager;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.i;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.exoplayer2.ui.VideoPlayerView;
import com.facebook.share.internal.ShareConstants;
import com.fragments.ActivityFeedActivityFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.CustomCardFragment;
import com.fragments.DiscoverFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.DownloadHomeFragment;
import com.fragments.EducativeHDStreamFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaEducativeFragment;
import com.fragments.GaanaMiniPurchaseFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.GridActivityFragment;
import com.fragments.ItemListingFragment;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.MiniPlayerFragment;
import com.fragments.MiniPlayerFragmentV4;
import com.fragments.MoreRadioActivityFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.MyMusicSearchResultFragment;
import com.fragments.PartyFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.PlayerRadioFragmentV4;
import com.fragments.PreScreenFragment;
import com.fragments.ProfileFragment;
import com.fragments.RadioActivityFragment;
import com.fragments.ReferFriendsFragment;
import com.fragments.ReferralScreenFragment;
import com.fragments.SearchEnchancedFragment;
import com.fragments.SearchFragment;
import com.fragments.SettingsDetailFragment;
import com.fragments.SettingsFragment;
import com.fragments.a.a;
import com.gaana.ads.base.ILoadAdBehaviour;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.ads.interstitial.InterstitialAdRequest;
import com.gaana.ads.interstitial.LoadInterstitialBehaviour;
import com.gaana.ads.interstitial.ShowConditionalInterstitialBehaviour;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.LocalMediaContentObserver;
import com.gaana.localmedia.LocalMediaContentObserver.OnContentChanged;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.CountryData;
import com.gaana.models.FreedomPlanUserData;
import com.gaana.models.FreedomPlanUserNotifyData;
import com.gaana.models.GaanaThemeModel;
import com.gaana.models.PaymentProductModel;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.ProfileUsers.ProfileUser;
import com.gaana.models.Radios.Radio;
import com.gaana.models.VideoFeedMetaData;
import com.gaana.view.CustomBottomNavigationView;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaana.view.item.AppUpdaterView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.DownloadSyncPopupItemView;
import com.gaana.view.item.GaanaMiniPurchaseDialog;
import com.gaana.view.item.RateUsDialog;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.helpshift.campaigns.activities.NotificationActivity;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.PurchaseHermesManager;
import com.managers.PurchaseHermesManager.PaymentResponse;
import com.managers.PurchaseOperatorManager;
import com.managers.PurchasePaypalManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.ab;
import com.managers.ac;
import com.managers.ag;
import com.managers.ai;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.l;
import com.managers.n;
import com.managers.q;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.moengage.inapp.InAppManager;
import com.moengage.inapp.InAppManager.InAppMessageListener;
import com.moengage.inapp.InAppMessage;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.services.FileDownloadService;
import com.services.d;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.ah;
import com.services.l.as;
import com.services.l.at;
import com.services.l.au;
import com.services.l.av;
import com.services.l.aw;
import com.services.l.b;
import com.services.l.bd;
import com.services.l.e;
import com.services.l.r;
import com.services.l.s;
import com.simpl.android.zeroClickSdk.Simpl;
import com.til.colombia.android.service.Item;
import com.utilities.Util;
import com.utilities.g;
import com.utilities.h;
import com.views.QueueSlidingUpPanelLayout;
import com.views.VoiceRecognitionDialog;
import com.views.c;
import com.youtube.YouTubePlayerActivity;
import com.youtube.YouTubePlayerActivity.Orientation;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class GaanaActivity extends BaseActivityWithVerticalYoutube implements OnClickListener, OnContentChanged, InAppMessageListener, b, Util.b, c.b {
    public static int SHOW_TAB_HOME = 0;
    public static int SHOW_TAB_MYMUSIC = 3;
    public static int SHOW_TAB_RADIO = 2;
    public static int SHOW_TAB_SEARCH = 1;
    public static int SHOW_TAB_SOCIAL = -1;
    private static final int SWIPE_THRESHOLD_DELTA = 30;
    public static ArrayList<BusinessObject> arrListDropdownTagsSaved;
    public boolean IS_COACHMARK_VISIBLE = false;
    boolean IS_STUDENT_PACK_AVAILABLE = false;
    int _currentTime = 0;
    private int _sleepTime = 0;
    Handler _sleepTimer = null;
    ISleepTimerListener _sleepTimerListener;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout adView;
    View animatedMyMusicGlowView = null;
    private CustomBottomNavigationView bottomNavigationView;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("broadcast_intent_download_service".equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("DisplayCoachmark", false);
                boolean b = d.a().b("DOWNLOADED_TRACKS_COACHMARK", true, false);
                int intExtra = intent.getIntExtra("track_id", -1);
                DownloadManager.c().a(intExtra);
                boolean c = DownloadManager.c().c(String.valueOf(intExtra));
                if (b && booleanExtra && GaanaActivity.this.hasWindowFocus() && !GaanaActivity.this.isPlayerExpanded() && !GaanaActivity.this.isCuratedDownloadsDisplaying()) {
                    GaanaActivity.this.showDownloadedTracksCoachmark();
                } else if (!(!GaanaActivity.this.hasWindowFocus() || GaanaActivity.this.isPlayerExpanded() || GaanaActivity.this.isCuratedDownloadsDisplaying())) {
                    GaanaActivity.this.showHideNewDownloadedSongCount();
                }
                if (GaanaActivity.this.mFragmentController == null || !GaanaActivity.this.mFragmentController.c()) {
                    if (GaanaActivity.this.mFragment != null && !(GaanaActivity.this.mFragment instanceof MyMusicItemFragment)) {
                        GaanaActivity.this.mFragment.refreshListView();
                    } else if (GaanaActivity.this.mFragment instanceof MyMusicItemFragment) {
                        if (intent.getIntExtra("has_downloaded", -3) != -4 || intExtra == -1) {
                            GaanaActivity.this.mFragment.refreshListView();
                        } else {
                            ((MyMusicItemFragment) GaanaActivity.this.mFragment).a(intExtra, true);
                        }
                    }
                } else if (GaanaActivity.this.mCurrentPlayerFragment instanceof PlayerFragmentV2) {
                    ((PlayerFragmentV2) GaanaActivity.this.mCurrentPlayerFragment).p();
                } else if (GaanaActivity.this.mCurrentPlayerFragment instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) GaanaActivity.this.mCurrentPlayerFragment).v();
                }
                if (GaanaActivity.this.mPlayer instanceof MiniPlayerFragment) {
                    ((MiniPlayerFragment) GaanaActivity.this.mPlayer).c();
                } else if (GaanaActivity.this.mPlayer instanceof MiniPlayerFragmentV4) {
                    ((MiniPlayerFragmentV4) GaanaActivity.this.mPlayer).e();
                }
                String string = intent.getExtras().getString("SNACKBAR_MSG");
                if (GaanaActivity.this.mAppState.isAppInOfflineMode() && string != null) {
                    aj.a().a(GaanaActivity.this, string);
                }
                if (c) {
                    DownloadManager.c().h(true);
                    GaanaActivity.this.showSmartDownloadsCompleteNotification();
                }
            }
        }
    };
    private BroadcastReceiver broadcastReceiverFreedomUserInfo = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("broadcast_intent_download_service_freedom_user_info".equals(intent.getAction())) {
                String string = intent.getExtras().getString("SNACKBAR_MSG");
                if (GaanaActivity.this != null && !GaanaActivity.this.isFinishing() && string != null) {
                    aj.a().a(GaanaActivity.this, GaanaActivity.this.getString(R.string.buy_now), string, new aj.b() {
                        public void undoSnackBar() {
                            GaanaActivity.this.changeFragment(R.id.LeftMenuPurchase, null, null);
                        }
                    });
                }
            }
        }
    };
    private BroadcastReceiver broadcastReceiverPlaylistStatusUpdate = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("broadcast_playlist_update_status".equals(intent.getAction())) {
                if (GaanaActivity.this.mFragment instanceof MyMusicItemFragment) {
                    int intExtra = intent.getIntExtra("has_downloaded", -3);
                    int intExtra2 = intent.getIntExtra("track_id", -1);
                    if (intExtra != -4 || intExtra2 == -1) {
                        GaanaActivity.this.mFragment.refreshListView();
                    } else {
                        ((MyMusicItemFragment) GaanaActivity.this.mFragment).a(intExtra2, true);
                    }
                } else if (GaanaActivity.this.mFragment != null) {
                    GaanaActivity.this.mFragment.refreshListView();
                }
                if (GaanaActivity.this.mPlayer instanceof MiniPlayerFragment) {
                    ((MiniPlayerFragment) GaanaActivity.this.mPlayer).c();
                } else if (GaanaActivity.this.mPlayer instanceof MiniPlayerFragmentV4) {
                    ((MiniPlayerFragmentV4) GaanaActivity.this.mPlayer).e();
                }
            } else if ("broadcast_playlist_update_status_for_download_progress".equals(intent.getAction()) && (GaanaActivity.this.mFragment instanceof MyMusicFragment)) {
                GaanaActivity.this.mFragment.refreshListView();
            }
        }
    };
    private Bundle bundle;
    boolean changeFragment = false;
    private boolean crossButtonVisibility = false;
    String currentTabName = "HOME";
    private Dialog dialogSmartDownload;
    private boolean doubleBackToExitPressedOnce = false;
    private BaseGaanaFragment fragment = null;
    private FragmentManager fragmentManager;
    private String fragmentTag = "";
    private FragmentTransaction fragmentTransaction;
    boolean fragmentTrasState = true;
    boolean freedomApiHitOnce = false;
    boolean fromInternationalOnBoarding = false;
    boolean fromSearch = false;
    private String getSearchString = "";
    boolean hasFreedomUserEnagagementPopupShown = false;
    AnimatedVectorDrawableCompat iconGlowAnimation;
    private IAdType interstitialAdType = null;
    private boolean isCoachmarkViewHidden = false;
    boolean isDownArrowAnimationRunning = false;
    private boolean isDownloadSyncReceiverRegistered = false;
    private boolean isLaunchedFromDeeplink = false;
    private boolean isMiniPlayerExpanded = false;
    private boolean isMyMusicDeeplink = false;
    boolean isMyMusicGlowAnimationRunning = false;
    private boolean isNavTabClicked = false;
    private boolean isPlayerFullScreen = false;
    private boolean isSmartDownloadNotificationPending = false;
    private boolean isVideoItemPlayed = false;
    private c mBottomNavigationBarHelper;
    private BroadcastReceiver mChromeCastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!GaanaActivity.this.isFinishing() && GaanaActivity.this.fragmentManager != null) {
                GaanaActivity.this.setUpdatePlayerFragment();
            }
        }
    };
    private BaseFragment mCurrentPlayerFragment;
    private BroadcastReceiver mDownloadSyncReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!GaanaActivity.this.isFinishing() && "intent_download_sync_completed".equalsIgnoreCase(intent.getAction())) {
                new DownloadSyncPopupItemView(GaanaActivity.this).showDownloadSyncSuccessDialog(intent.getIntExtra("EXTRA_KEY_ADDED_SONGS", 0), intent.getIntExtra("EXTRA_KEY_REMOVED_SONGS", 0));
            }
        }
    };
    private View mDownloadedSongsCount;
    public DrawerLayout mDrawerLayout;
    private a mFragmentController;
    private BusinessObject mFreedomPlanUserEngagementData;
    private final GestureDetector mGestureDetector = new GestureDetector(new OnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent.getY() - motionEvent2.getY() <= 30.0f) {
                return false;
            }
            ((GaanaActivity) GaanaActivity.this.mContext).launchExpandedPlayer();
            return true;
        }
    });
    private LocalMediaContentObserver mLocalMediaContentObserver;
    private NavigationHeaderMenu mNavigationHeaderMenu = null;
    private BroadcastReceiver mNetworkChangeBroadCastReceiver = new BroadcastReceiver() {
        /* JADX WARNING: Missing block: B:18:0x0052, code skipped:
            return;
     */
        public void onReceive(android.content.Context r1, android.content.Intent r2) {
            /*
            r0 = this;
            if (r2 == 0) goto L_0x0052;
        L_0x0002:
            r1 = r2.getAction();
            if (r1 != 0) goto L_0x0009;
        L_0x0008:
            goto L_0x0052;
        L_0x0009:
            r1 = r2.getAction();
            r2 = "android.net.conn.CONNECTIVITY_CHANGE";
            r1 = r1.equalsIgnoreCase(r2);
            if (r1 == 0) goto L_0x0051;
        L_0x0015:
            r1 = com.gaana.GaanaActivity.this;
            r1.handleNoInternetLayout();
            r1 = com.utilities.Util.d();
            r2 = com.utilities.Util.NETWORK_TYPE.NETWORK_NO_CONNECTION;
            if (r1 == r2) goto L_0x0036;
        L_0x0022:
            r2 = com.utilities.Util.NETWORK_TYPE.NETWORK_UNKNOWN;
            if (r1 == r2) goto L_0x0036;
        L_0x0026:
            r1 = com.utilities.Util.ad();
            if (r1 != 0) goto L_0x0036;
        L_0x002c:
            r1 = com.gaana.GaanaActivity.this;
            r2 = new com.gaana.GaanaActivity$14$1;
            r2.<init>();
            com.utilities.Util.a(r1, r2);
        L_0x0036:
            r1 = com.gaana.GaanaActivity.this;
            r1 = r1.mFragment;
            r1 = r1 instanceof com.fragments.ListingFragment;
            if (r1 == 0) goto L_0x0051;
        L_0x003e:
            r1 = com.gaana.GaanaActivity.this;
            r1 = r1.mFragment;
            r1 = (com.fragments.ListingFragment) r1;
            r1 = r1.p();
            if (r1 == 0) goto L_0x0051;
        L_0x004a:
            r1 = com.gaana.GaanaActivity.this;
            r1 = r1.mFragment;
            r1.refreshListView();
        L_0x0051:
            return;
        L_0x0052:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity$AnonymousClass14.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private Fragment mPlayer;
    private Bitmap mPlayerBitmapBlur;
    private PlayerFragmentV2 mPlayerFragmentV2;
    private PlayerFragmentV4 mPlayerFragmentV4;
    private PlayerRadioFragmentV4 mPlayerRadioFragmentV4;
    public boolean mPlayerStateChanged = false;
    private int mSelectedPosition = -1;
    private View mShowMyMusicCoachmark;
    private View mSongClickedView;
    private final OnTouchListener mTouchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return GaanaActivity.this.mGestureDetector.onTouchEvent(motionEvent);
        }
    };
    private Handler mUiHandler = new Handler(Looper.getMainLooper());
    private i mViewPool;
    private List<com.models.c.a> mVoiceHelpList;
    private View mVoiceLongPressCoachMark;
    private VoiceRecognitionDialog mVoiceRecognitionDialog;
    private View mVoiceSearchCoachMark;
    private View miniPlayerV4;
    private boolean needToLaunchExpandedPlayer = false;
    SwitchCompat nightModeSwitch;
    private boolean onCreateCalled = false;
    String previousTabName = "HOME";
    private FrameLayout progressOverlayView;
    private boolean refreshData = false;
    int repeat_song_counter = 0;
    private View sideView_Container;
    private com.views.i slidingUPLayout;
    aj.b snackBarUnDoInterface = new aj.b() {
        public void undoSnackBar() {
            Intent intent = new Intent(GaanaActivity.this.getApplicationContext(), AppLanguageSettingsScreenActivity.class);
            intent.setFlags(335544320);
            intent.putExtra("language_display", "English");
            GaanaActivity.this.startActivity(intent);
        }
    };
    private OnCheckedChangeListener switchGoWhiteModeCheckChangedListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.isPressed()) {
                compoundButton.setChecked(z ^ 1);
                GaanaActivity.this.handleAutoNightModeSettings(z);
            }
        }
    };
    public ImageView themeBackground;
    public ImageView themeForegroundGif;
    public String title;
    String track_id_sd_repeat = null;

    public interface OnDropDownListener {
        void itemSelected(int i);
    }

    public interface ISleepTimerListener {
        void SleepTimerCompleted();

        void SleepTimerTick(int i, int i2);
    }

    public Location getLocation() {
        return null;
    }

    public void onAdConfigFailed() {
    }

    public void setSlideUpPanel(boolean z) {
    }

    public void showHideVoiceCoachMark(int i, boolean z) {
    }

    public void setBottomNavigationViewTabs() {
        if (Constants.I != 1 || PlayerManager.a(this.mContext).l() <= 0) {
            SHOW_TAB_HOME = 0;
            SHOW_TAB_SEARCH = 1;
            SHOW_TAB_RADIO = 2;
            SHOW_TAB_MYMUSIC = 3;
            return;
        }
        SHOW_TAB_HOME = 0;
        SHOW_TAB_SEARCH = 1;
        SHOW_TAB_RADIO = 3;
        SHOW_TAB_MYMUSIC = 4;
    }

    public BusinessObject getmFreedomPlanUserEngagementData() {
        return this.mFreedomPlanUserEngagementData;
    }

    public void setmFreedomPlanUserEngagementData(BusinessObject businessObject) {
        this.mFreedomPlanUserEngagementData = businessObject;
    }

    public void setRepeatSongDownloadListener() {
        o.a("listener_android_sd_repeat", new m() {
            public void onAdEventUpdate(f fVar, AdEvent adEvent) {
            }

            public void onBufferingUpdate(f fVar, int i) {
            }

            public void onCompletion(f fVar) {
            }

            public void onError(f fVar, int i, int i2) {
            }

            public void onInfo(f fVar, int i, int i2) {
            }

            public void onPrepared(f fVar) {
                PlayerTrack i = PlayerManager.a(GaanaActivity.this.mContext).i();
                if (i != null && i.b() != null) {
                    String h = i.h();
                    if (h == GaanaActivity.this.track_id_sd_repeat) {
                        GaanaActivity gaanaActivity = GaanaActivity.this;
                        gaanaActivity.repeat_song_counter++;
                    } else {
                        GaanaActivity.this.repeat_song_counter = 1;
                    }
                    GaanaActivity.this.track_id_sd_repeat = h;
                    if (GaanaActivity.this.repeat_song_counter == Constants.O && !DownloadManager.c().l(Integer.parseInt(i.h())).booleanValue()) {
                        DownloadManager.c().a(true);
                        DownloadManager.c().a(true, i.b());
                    }
                }
            }
        });
    }

    public void getFreedomPlanUserEnagagement() {
        if (!this.freedomApiHitOnce) {
            URLManager uRLManager = new URLManager();
            GaanaApplication gaanaApplication = (GaanaApplication) this.mContext.getApplicationContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/userperformance.php?type=get_fpgamescard&token=");
            stringBuilder.append(gaanaApplication.getCurrentUser().getAuthToken());
            uRLManager.a(stringBuilder.toString());
            uRLManager.i(true);
            uRLManager.c(0);
            uRLManager.a(FreedomPlanUserData.class);
            uRLManager.b(Boolean.valueOf(false));
            com.i.i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject instanceof FreedomPlanUserData) {
                        GaanaActivity.this.setmFreedomPlanUserEngagementData(businessObject);
                        if (((FreedomPlanUserData) businessObject).getStatus() == 1) {
                            d.a().a("PREFERENCE_FREEDOM_USER_ENGAGEGMENT_POPUP_SHOWN", false, true);
                        }
                        GaanaActivity.this.freedomApiHitOnce = true;
                    }
                }
            }, uRLManager, Boolean.valueOf(false));
        }
    }

    public void notifyServerForFreedomPlanUserEnagagement() {
        FreedomPlanUserData freedomPlanUserData = this.mFreedomPlanUserEngagementData instanceof FreedomPlanUserData ? (FreedomPlanUserData) this.mFreedomPlanUserEngagementData : null;
        if (freedomPlanUserData != null) {
            URLManager uRLManager = new URLManager();
            GaanaApplication gaanaApplication = (GaanaApplication) this.mContext.getApplicationContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/userperformance.php?type=fpgames_notify&seen=1&token=");
            stringBuilder.append(gaanaApplication.getCurrentUser().getAuthToken());
            stringBuilder.append("&tid=");
            stringBuilder.append(freedomPlanUserData.getTid());
            uRLManager.a(stringBuilder.toString());
            uRLManager.i(true);
            uRLManager.c(0);
            uRLManager.a(FreedomPlanUserNotifyData.class);
            com.i.i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                }
            }, uRLManager, Boolean.valueOf(false));
        }
    }

    public boolean isSmartDownloadNotificationPending() {
        return this.isSmartDownloadNotificationPending;
    }

    public void showSmartDownloadsCompleteNotification() {
        if (DownloadManager.c().m(0) == 0) {
            DownloadManager.c().b(false);
            if ((getmCurrentPlayerFragment() instanceof PlayerFragmentV4) || !Util.c()) {
                this.isSmartDownloadNotificationPending = true;
            } else {
                showSmartDownloadNotification();
            }
        }
    }

    public void showSmartDownloadNotification() {
        this.isSmartDownloadNotificationPending = false;
        GaanaApplication.getInstance().showSmartDownloadsNotification(this, DownloadManager.c().a(), String.valueOf(DownloadManager.c().b()));
    }

    private void showDownloadedTracksCoachmark() {
        if (!(this.mFragment instanceof GaanaEducativeFragment)) {
            Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
            intent.putExtra("COACHMARK_VALUE", "DOWNLOADED_TRACKS_COACHMARK");
            startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
            overridePendingTransition(17432576, 17432577);
        }
    }

    private void handleNoInternetLayout() {
        if (Util.j((Context) this) || this.noInternetLayout == null) {
            this.noInternetLayout.animate().translationY((float) Util.b(40)).setDuration(500).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    GaanaActivity.this.noInternetLayout.setVisibility(8);
                    GaanaActivity.this.noInternetLayout.animate().setListener(null);
                }
            });
            return;
        }
        this.noInternetLayout.bringToFront();
        this.noInternetLayout.setVisibility(0);
        this.noInternetLayout.animate().setListener(null);
        this.noInternetLayout.animate().translationY(0.0f).setDuration(500).start();
        if (ap.a().j() || Util.v()) {
            this.noInternetLayout.findViewById(R.id.go_downloads).setVisibility(0);
            this.noInternetLayout.findViewById(R.id.go_downloads).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GaanaActivity.this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.DOWNLOADS.name());
                    ((GaanaActivity) GaanaActivity.this.mContext).displayFragment(new DownloadDetailsFragment());
                    GaanaActivity.this.noInternetLayout.setVisibility(8);
                }
            });
            return;
        }
        this.noInternetLayout.findViewById(R.id.go_downloads).setVisibility(8);
        this.noInternetLayout.findViewById(R.id.cross_dlg).setVisibility(0);
        this.noInternetLayout.findViewById(R.id.cross_dlg).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GaanaActivity.this.noInternetLayout.setVisibility(8);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        if (com.utilities.d.d()) {
            com.utilities.f.a((Context) this);
        }
        overridePendingTransition(0, 0);
        if (Constants.l) {
            setTheme(R.style.GaanaAppThemeWhite);
        }
        super.onCreate(bundle);
        GaanaApplication.onBoardingSkipped = false;
        this.mContext = this;
        this.onCreateCalled = true;
        this.mFragmentController = new a(getSupportFragmentManager(), R.id.llParentActivityLayout, bundle, null);
        d.a().a("ONBOARD_NEW_USER", false, false);
        if (GaanaApplication.sessionHistoryCount < 0) {
            GaanaApplication.sessionHistoryCount = d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false);
        }
        DynamicViewManager.a().d();
        this.mDeviceResManager = d.a();
        this.mAppState = GaanaApplication.getInstance();
        this.mDialog = new com.services.f(this);
        setStatusBarTransparentAndNavigationBarColor();
        this.sideView_Container = findViewById(R.id.sideView_Container);
        setUpNavMenulist();
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mDrawerLayout.setFocusable(false);
        this.mNavigationHeaderMenu = (NavigationHeaderMenu) findViewById(R.id.navMenuTop);
        this.adView = (LinearLayout) findViewById(R.id.llNativeAdSlot);
        initSlidingPane();
        setActionBar();
        initUser();
        initBottomNavigationBar();
        this.fragmentManager = getSupportFragmentManager();
        if (bundle == null) {
            bundle = getIntent().getExtras();
            int i = -1;
            if (bundle != null) {
                i = bundle.getInt("DEEPLINKING_SCREEN", -1);
            }
            if (i == R.id.LeftMenuMyMusic || i == R.id.MyMusicMenuAlbums || i == R.id.MyMusicMenuPlaylists || i == R.id.MyMusicMenuSongs || i == R.id.MyMusicMenuRadios || i == R.id.MyMusicMenuArtists || i == R.id.MyMusicMenuDownloads || i == R.id.MyMusicFavorites) {
                String stringExtra = getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM");
                String stringExtra2 = getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2");
                this.isMyMusicDeeplink = true;
                changeFragment(i, stringExtra, stringExtra2);
            } else if (this.mAppState.isAppInOfflineMode() || !Util.j((Context) this)) {
                displayLaunchFragment(SHOW_TAB_MYMUSIC, bundle);
            } else if (i == R.id.LeftMenuRadio || i == R.id.LeftMenuHome) {
                changeFragment(i, getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM"), getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2"));
            } else {
                displayLaunchFragment(SHOW_TAB_HOME, bundle);
            }
        }
        this.themeBackground = (ImageView) findViewById(R.id.theme_background);
        this.themeForegroundGif = (ImageView) findViewById(R.id.theme_foreground_gif);
        updateThemeResources();
        reportLocation();
        syncDownloadWithDb();
        com.fcm.a.a();
        n.a().c();
        new AppUpdaterView(this).checkAppUpdate();
        if (this.mAppState.isAppInOfflineMode()) {
            ap.a().a((Context) this, new ah() {
                public void onOfflineModeValidated(boolean z) {
                    if (!z) {
                        GaanaActivity.this.refreshUser(new at() {
                            public void onUserRefreshed() {
                                if (GaanaActivity.this.mNavigationHeaderMenu != null) {
                                    GaanaActivity.this.mNavigationHeaderMenu.updateLoginBar();
                                }
                            }
                        }, false);
                    }
                }
            });
        }
        if (d.a().b("PREFERENCE_KEY_SHOW_SCHEDULE_DOWNLOAD_TOAST", false, true)) {
            aj.a().a(this, getResources().getString(R.string.schedule_download_sucess_msg));
            d.a().a("PREFERENCE_KEY_SHOW_SCHEDULE_DOWNLOAD_TOAST", false, true);
        }
        registerContentObserver();
        if (this.mDeviceResManager.b("ONBOARD_LANG_MISMATCH_FOR_LOGGED_IN_USER", false, false)) {
            this.mDeviceResManager.b("ONBOARD_LANG_MISMATCH_FOR_LOGGED_IN_USER", false);
            aj.a().a(this.mContext, this.mContext.getString(R.string.snackbar_language_mismatch), 4700, this.mContext.getString(R.string.snackbar_gotit));
        }
        checkAndStartJukeSession();
        if (!(this.mAppState.getCurrentUser().getUserSubscriptionData() == null || TextUtils.isEmpty(this.mAppState.getCurrentUser().getUserSubscriptionData().getSubscriptionType()) || !this.mAppState.getCurrentUser().getUserSubscriptionData().getSubscriptionType().equalsIgnoreCase("Times Prime") || TextUtils.isEmpty(this.mAppState.getCurrentUser().getUserSubscriptionData().isAppNotified()) || !this.mAppState.getCurrentUser().getUserSubscriptionData().isAppNotified().equalsIgnoreCase("0") || TextUtils.isEmpty(this.mAppState.getCurrentUser().getUserSubscriptionData().getAppNotifyText()))) {
            aj.a().a(this.mContext, this.mAppState.getCurrentUser().getUserSubscriptionData().getAppNotifyText(), 4700, this.mContext.getString(R.string.snackbar_gotit));
            Util.ag();
        }
        if (!((!ap.a().d() && !ap.a().l()) || ap.a().f() || !d.a().b("PREFERENCE_EDUCATIVE_SCREEN", false, false) || this.mAppState.getCurrentUser().getUserSubscriptionData() == null || this.mAppState.getCurrentUser().getUserSubscriptionData().isEducate_notify())) {
            displayFragment(new GaanaEducativeFragment());
            Util.ah();
        }
        if (GaanaApplication.sessionHistoryCount == 0 && Constants.dn) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    if (Util.g != null) {
                        Util.g.initOnboardPlayer();
                    }
                }
            }, (long) (Constants.dm * 1000));
        }
        if (shouldStartSmartDownloads()) {
            DownloadManager.c().a(false, null);
            setRepeatSongDownloadListener();
        }
        if (GaanaApplication.sessionHistoryCount > 0 && Util.j(this.mContext)) {
            CallCustomCardApi(true, false);
        }
        if (getIntent() != null && getIntent().hasExtra("show_toast_autonightmode") && getIntent().getBooleanExtra("show_toast_autonightmode", false)) {
            aj.a().a(this, getString(R.string.toast_auto_nigth_mode_activiated));
        }
        if (GaanaApplication.sessionHistoryCount > 0 && Util.j(this.mContext)) {
            Util.i(this.mContext, "HomeScreen");
        }
        if (Constants.en == 0) {
            Util.a((Context) this, new e() {
                public void onDataRetrieved(int i) {
                    if (i == 1) {
                        GaanaActivity.this.handleCountryDataResponse();
                    }
                }
            });
        } else {
            Constants.en = 0;
            handleCountryDataResponse();
        }
        if (ap.a().b(this.mContext)) {
            LoadInterstitialBehaviour loadInterstitialBehaviour = new LoadInterstitialBehaviour();
            if (loadInterstitialBehaviour.whenToLoad() && this.interstitialAdType == null) {
                this.interstitialAdType = createInterstitialAd(loadInterstitialBehaviour);
            }
        }
        initTabNames();
        Simpl.init(this.mContext);
    }

    private void initTabNames() {
        if (this.fragmentTag == "mymusic") {
            this.currentTabName = "MY_MUSIC";
            this.previousTabName = "MY_MUSIC";
            return;
        }
        this.previousTabName = "HOME";
        this.currentTabName = "HOME";
    }

    private IAdType createInterstitialAd(ILoadAdBehaviour iLoadAdBehaviour) {
        this.interstitialAdType = new InterstitialAdRequest().buildPublisherInterstitialAd(new PublisherInterstitialAd(this.mContext)).buildInterstitialShowBehaviour(new ShowConditionalInterstitialBehaviour()).buildInterstitialLoadBehaviour(iLoadAdBehaviour).buildLocation(getLocation()).build();
        this.interstitialAdType.loadAd();
        return this.interstitialAdType;
    }

    private boolean shouldStartSmartDownloads() {
        return this.mDeviceResManager.b("PREFERENCE_KEY_AUTO_DOWNLOAD", true, false) && ap.a().j() && !ap.a().f();
    }

    public IAdType getInterstitialAdType() {
        return this.interstitialAdType;
    }

    /* JADX WARNING: Missing block: B:6:0x001d, code skipped:
            if (r7.getStatus() == 1) goto L_0x0021;
     */
    public void showFreedomUserEngagementPopup(com.gaana.models.BusinessObject r7) {
        /*
        r6 = this;
        r0 = r6.mDeviceResManager;
        r1 = "PREFERENCE_FREEDOM_USER_ENGAGEGMENT_POPUP_SHOWN";
        r2 = 0;
        r0 = r0.b(r1, r2, r2);
        r1 = r6.hasFreedomUserEnagagementPopupShown;
        if (r1 != 0) goto L_0x00dc;
    L_0x000d:
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        goto L_0x00dc;
    L_0x0011:
        r0 = r7 instanceof com.gaana.models.FreedomPlanUserData;
        r1 = 0;
        r3 = 1;
        if (r0 == 0) goto L_0x0020;
    L_0x0017:
        r7 = (com.gaana.models.FreedomPlanUserData) r7;
        r0 = r7.getStatus();
        if (r0 != r3) goto L_0x0020;
    L_0x001f:
        goto L_0x0021;
    L_0x0020:
        r7 = r1;
    L_0x0021:
        if (r7 != 0) goto L_0x0024;
    L_0x0023:
        return;
    L_0x0024:
        r0 = r6.mContext;
        r4 = "layout_inflater";
        r0 = r0.getSystemService(r4);
        r0 = (android.view.LayoutInflater) r0;
        r4 = 2131493153; // 0x7f0c0121 float:1.8609778E38 double:1.053097541E-314;
        r0 = r0.inflate(r4, r1);
        r1 = new android.support.design.widget.BottomSheetDialog;
        r4 = 2131886281; // 0x7f1200c9 float:1.9407136E38 double:1.0532917723E-314;
        r1.<init>(r6, r4);
        r4 = r1.getWindow();
        r5 = new android.graphics.drawable.ColorDrawable;
        r5.<init>(r2);
        r4.setBackgroundDrawable(r5);
        r1.setContentView(r0);
        r2 = r6.mContext;
        r2 = com.utilities.Util.i(r2);
        r4 = 2131298570; // 0x7f09090a float:1.8215117E38 double:1.0530014045E-314;
        r4 = r0.findViewById(r4);
        r4 = (android.widget.TextView) r4;
        r5 = r7.getTitle();
        r4.setText(r5);
        r4.setTypeface(r2);
        r4 = 2131298574; // 0x7f09090e float:1.8215125E38 double:1.0530014064E-314;
        r4 = r0.findViewById(r4);
        r4 = (android.widget.TextView) r4;
        r5 = r7.getMessage();
        r4.setText(r5);
        r4 = 2131298571; // 0x7f09090b float:1.8215119E38 double:1.053001405E-314;
        r4 = r0.findViewById(r4);
        r4 = (android.widget.TextView) r4;
        r5 = r7.getSongs();
        r5 = java.lang.String.valueOf(r5);
        r4.setText(r5);
        r4.setTypeface(r2);
        r5 = 2131298568; // 0x7f090908 float:1.8215113E38 double:1.0530014035E-314;
        r0 = r0.findViewById(r5);
        r0 = (android.widget.TextView) r0;
        r7 = r7.getDays();
        r7 = java.lang.String.valueOf(r7);
        r0.setText(r7);
        r0.setTypeface(r2);
        r1.show();
        r6.hasFreedomUserEnagagementPopupShown = r3;
        r7 = com.services.d.a();
        r1 = "PREFERENCE_FREEDOM_USER_ENGAGEGMENT_POPUP_SHOWN";
        r7.a(r1, r3, r3);
        r7 = com.managers.u.a();
        r1 = "Engagement_Overlay";
        r2 = "View";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r4.getText();
        r3.append(r4);
        r4 = "-";
        r3.append(r4);
        r0 = r0.getText();
        r3.append(r0);
        r0 = r3.toString();
        r7.a(r1, r2, r0);
        r6.notifyServerForFreedomPlanUserEnagagement();
        return;
    L_0x00dc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity.showFreedomUserEngagementPopup(com.gaana.models.BusinessObject):void");
    }

    private void checkAndStartJukeSession() {
        String b = this.mDeviceResManager.b("pref_juke_session_id", "", false);
        if (!Constants.cY && !TextUtils.isEmpty(b)) {
            JukePlaylist jukePlaylist = new JukePlaylist();
            jukePlaylist.setBusinessObjId(b);
            JukeSessionManager.getInstance().setJukeSessionPlaylist(jukePlaylist);
            JukeSessionManager.getInstance().startJukeSession(b, 2, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                }
            });
        }
    }

    private void setStatusBarTransparentAndNavigationBarColor() {
        if (VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0);
        }
        if (Constants.l && VERSION.SDK_INT >= 23) {
            setLightStatusBar(true);
            setLightNavigationBar(true);
            getWindow().setNavigationBarColor(-1);
        } else if (!Constants.l && VERSION.SDK_INT >= 23) {
            setLightNavigationBar(false);
            getWindow().setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    public void setLightStatusBar(boolean z) {
        setLightBar(z, 8192);
    }

    public void setLightNavigationBar(boolean z) {
        setLightBar(z, 16);
    }

    public void setFullScreenBar(boolean z) {
        setLightBar(z, 1024);
    }

    private void setLightBar(boolean z, int i) {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().getDecorView().setSystemUiVisibility(z ? systemUiVisibility | i : (i ^ -1) & systemUiVisibility);
    }

    private void handleCountryDataResponse() {
        if (!isFinishing()) {
            CountryData countryData = this.mAppState.getCountryData();
            if (countryData != null && countryData.getEuRegion() == 1) {
                Intent intent;
                if (countryData.getUserStatus() == 0 && Constants.ek == 0) {
                    intent = new Intent(getApplicationContext(), ConsentActivity.class);
                    intent.setFlags(603979776);
                    startActivity(intent);
                    finish();
                } else if (countryData.getUserStatus() == 1) {
                    this.mAppState.clearApplicationData();
                    intent = new Intent(getApplicationContext(), ConsentActivity.class);
                    intent.setFlags(603979776);
                    intent.putExtra("BLOCKING_SCREEN", true);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    public boolean isPlayerFullScreen() {
        return this.isPlayerFullScreen;
    }

    public void setPlayerFullScreen(boolean z) {
        this.isPlayerFullScreen = z;
    }

    private void updateThemeResources() {
        if (Constants.du && Constants.dt != null) {
            String backgroundImageWhiteArtwork;
            String foregroundGif;
            if (Constants.l) {
                backgroundImageWhiteArtwork = Constants.dt.getBackgroundImageWhiteArtwork();
                foregroundGif = Constants.dt.getForegroundGif();
            } else {
                backgroundImageWhiteArtwork = Constants.dt.getBackgroundImageBlackArtwork();
                foregroundGif = Constants.dt.getForegroundGif();
            }
            com.i.i.a().a(backgroundImageWhiteArtwork, new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    GaanaActivity.this.themeBackground.setImageBitmap(bitmap);
                }
            });
            com.bumptech.glide.e.c(this.mContext).asGif().load(foregroundGif).listener(new com.bumptech.glide.request.e<com.bumptech.glide.load.resource.d.c>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, com.bumptech.glide.request.a.i<com.bumptech.glide.load.resource.d.c> iVar, boolean z) {
                    return false;
                }

                public boolean onResourceReady(com.bumptech.glide.load.resource.d.c cVar, Object obj, com.bumptech.glide.request.a.i<com.bumptech.glide.load.resource.d.c> iVar, DataSource dataSource, boolean z) {
                    if (cVar instanceof com.bumptech.glide.load.resource.d.c) {
                        GaanaActivity.this.mUiHandler.postDelayed(new Runnable() {
                            public void run() {
                                GaanaActivity.this.themeForegroundGif.setVisibility(8);
                            }
                        }, 4000);
                    }
                    return false;
                }
            }).into(this.themeForegroundGif);
        }
    }

    public void showThemeBackground(boolean z) {
        if (z) {
            updateThemeResources();
            Fragment miniPlayer = getMiniPlayer();
            if (miniPlayer != null) {
                if (miniPlayer instanceof MiniPlayerFragment) {
                    ((MiniPlayerFragment) miniPlayer).a();
                } else if (miniPlayer instanceof MiniPlayerFragmentV4) {
                    ((MiniPlayerFragmentV4) miniPlayer).a();
                }
            }
        }
        this.themeBackground.setVisibility(0);
    }

    public void hideThemeBackground(boolean z) {
        this.themeBackground.setVisibility(8);
        Fragment miniPlayer = getMiniPlayer();
        if (z && miniPlayer != null) {
            if (miniPlayer instanceof MiniPlayerFragment) {
                ((MiniPlayerFragment) miniPlayer).b();
            } else if (miniPlayer instanceof MiniPlayerFragmentV4) {
                ((MiniPlayerFragmentV4) miniPlayer).b();
            }
        }
    }

    public void CallCustomCardApi(final boolean z, final boolean z2) {
        int b = d.a().b("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", -1, false);
        if (b != -1 && b >= 0 && b != GaanaApplication.sessionHistoryCount && this.mAppState.customCardSessionValue != GaanaApplication.sessionHistoryCount && !(this.mFragment instanceof SearchFragment) && !(this.mContext instanceof NotificationActivity)) {
            Util.a(new Util.a() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(CustomCard customCard) {
                    CustomCard customCard2 = customCard;
                    if (z) {
                        if (customCard2 == null || !customCard.isFromNetwork()) {
                            u.a().a("Custom in-app", "API Response", "Cache");
                        } else {
                            u.a().a("Custom in-app", "API Response", "Network");
                        }
                    }
                    if (customCard.a() != null && Util.c()) {
                        com.custom_card_response.d a = customCard.a().a();
                        int intValue = a.a().intValue();
                        int intValue2 = a.b().intValue();
                        long intValue3 = (long) a.c().intValue();
                        int intValue4 = a.d().intValue();
                        String e = customCard.b().e();
                        long intValue5 = (long) customCard.a().b().b().intValue();
                        long intValue6 = (long) customCard.a().b().c().intValue();
                        int intValue7 = customCard.a().b().a().intValue();
                        com.custom_card_response.d dVar = a;
                        long j = intValue3;
                        long b = GaanaActivity.this.mDeviceResManager.b(0, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                        int b2 = GaanaActivity.this.mDeviceResManager.b(e, 0, false);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        String str;
                        if (intValue7 != 1 || currentTimeMillis < intValue5 || currentTimeMillis > intValue6) {
                            com.custom_card_response.d dVar2 = dVar;
                            CustomCard customCard3 = customCard;
                            if (intValue7 == 0 && (((intValue2 == 1 && z2) || (intValue == 1 && z)) && intValue4 != 0 && b2 < intValue4 && (b == 0 || currentTimeMillis - b >= j))) {
                                str = (intValue2 == 1 && z2) ? "1st play" : "app launch";
                                u.a().a("Custom in-app", str, "View");
                                GaanaActivity.this.mAppState.customCardSessionValue = GaanaApplication.sessionHistoryCount;
                                GaanaActivity.this.mDeviceResManager.a(e, b2 + 1, false);
                                GaanaActivity.this.mDeviceResManager.a(currentTimeMillis, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                                GaanaActivity.this.showCustomCard(customCard3, str);
                                GaanaActivity.this.deleteCardCounterFlash(dVar2.e());
                            }
                        } else if (((intValue2 == 1 && z2) || (intValue == 1 && z)) && intValue4 != 0 && b2 < intValue4 && (b == 0 || currentTimeMillis - b >= j)) {
                            str = (intValue2 == 1 && z2) ? "1st play" : "app launch";
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(" ;");
                            stringBuilder.append(e);
                            u.a().a("Custom in-app", stringBuilder.toString(), "View");
                            GaanaActivity.this.mAppState.customCardSessionValue = GaanaApplication.sessionHistoryCount;
                            GaanaActivity.this.mDeviceResManager.a(e, b2 + 1, false);
                            GaanaActivity.this.mDeviceResManager.a(currentTimeMillis, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                            GaanaActivity.this.showCustomCard(customCard, str);
                            GaanaActivity.this.deleteCardCounterFlash(dVar.e());
                        }
                    }
                }
            });
        }
    }

    private void deleteCardCounterFlash(List<String> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                this.mDeviceResManager.a((String) list.get(i), 0, false);
            }
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0035, code skipped:
            return;
     */
    private void showCustomCard(final com.custom_card_response.CustomCard r5, final java.lang.String r6) {
        /*
        r4 = this;
        r0 = r4.mContext;
        if (r0 == 0) goto L_0x0035;
    L_0x0004:
        r0 = com.utilities.Util.c();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        goto L_0x0035;
    L_0x000b:
        if (r5 == 0) goto L_0x0034;
    L_0x000d:
        r0 = r5.b();
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        goto L_0x0034;
    L_0x0014:
        r0 = r5.b();
        r0 = r0.b();
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0023;
    L_0x0022:
        return;
    L_0x0023:
        r1 = r5.b();
        r2 = com.i.i.a();
        r3 = new com.gaana.GaanaActivity$22;
        r3.<init>(r5, r6, r1);
        r2.a(r0, r3);
        return;
    L_0x0034:
        return;
    L_0x0035:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity.showCustomCard(com.custom_card_response.CustomCard, java.lang.String):void");
    }

    private void showCustomCardFragment(CustomCardFragment customCardFragment) {
        try {
            customCardFragment.show(getSupportFragmentManager().beginTransaction(), "CustomFragment");
        } catch (Exception unused) {
        }
    }

    private SpannableStringBuilder getIdentifySongNavText() {
        Resources resources = getResources();
        int i = Constants.l ? ViewCompat.MEASURED_STATE_MASK : -1;
        int color = resources.getColor(Constants.l ? R.color.black_alfa_75 : R.color.white_alfa_75);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_16);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_text_size);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = resources.getString(R.string.identify_song);
        String string2 = resources.getString(R.string.identify_song_help_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append("\n");
        spannableStringBuilder.append(stringBuilder.toString());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), 0, spannableStringBuilder.length(), 33);
        i = spannableStringBuilder.length();
        spannableStringBuilder.append(string2);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), i, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(dimensionPixelSize2), i, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private void setUpNavMenulist() {
        View findViewById = findViewById(R.id.list_slidermenu);
        GaanaThemeModel b = com.managers.s.a().b();
        if (b == null || b.getThemeArrayList() == null || b.getThemeArrayList().size() <= 0) {
            findViewById.findViewById(R.id.LeftTheme).setVisibility(8);
        } else {
            findViewById.findViewById(R.id.LeftTheme).setVisibility(0);
        }
        ((TextView) findViewById.findViewById(R.id.LeftIdentifySong)).setText(getIdentifySongNavText());
        findViewById.findViewById(R.id.LeftMenuReferFriend).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftMenuCoupons).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftIdentifySong).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftSongLanguage).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftDispLanguage).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftTheme).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftMenuSettings).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftMenuFeedback).setOnClickListener(this);
        findViewById.findViewById(R.id.menuSwitchTheme_text).setOnClickListener(this);
        findViewById.findViewById(R.id.LeftMenuVideoFeed).setOnClickListener(this);
        View findViewById2 = findViewById.findViewById(R.id.LeftPartyHub);
        if (Constants.cZ) {
            findViewById2.setVisibility(0);
            findViewById2.setOnClickListener(this);
        } else {
            findViewById2.setVisibility(8);
        }
        if (!Constants.R || (GaanaApplication.getInstance().getCurrentUser().getLoginStatus() && ap.a().e())) {
            findViewById.findViewById(R.id.LeftMenuReferFriend).setVisibility(8);
        }
        if (g.b()) {
            findViewById.findViewById(R.id.menuSwitchTheme).setVisibility(8);
        }
        this.nightModeSwitch = (SwitchCompat) findViewById.findViewById(R.id.menuSwitchTheme_switch);
        this.nightModeSwitch.setChecked(Constants.l ^ 1);
        this.nightModeSwitch.setOnCheckedChangeListener(this.switchGoWhiteModeCheckChangedListener);
    }

    public Bitmap getmPlayerBitmapBlur() {
        return this.mPlayerBitmapBlur;
    }

    public void setmPlayerBitmapBlur(Bitmap bitmap) {
        this.mPlayerBitmapBlur = bitmap;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.menuSwitchTheme_text) {
            boolean z = true;
            if ((Constants.l ^ 1) != 0) {
                z = false;
            }
            handleAutoNightModeSettings(z);
        } else {
            this.mAppState.setSidebarActiveBtn(id);
        }
        if (view instanceof TextView) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            TextView textView = (TextView) view;
            stringBuilder.append(textView.getText());
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("LeftNav - ");
            stringBuilder3.append(textView.getText());
            sendGAEvent("LeftNav", stringBuilder2, stringBuilder3.toString());
        }
        closeDrawer();
    }

    private void airtelApiCall() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("http://esbint.airtel.in:8001/CPID?app=T_GaAnA");
        uRLManager.c(0);
        uRLManager.i(false);
        com.i.i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public void resetBottomNavigationBar() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                GaanaActivity.this.initBottomNavigationBar();
            }
        }, 300);
    }

    private void resetBottomNavigation() {
        int b = d.a().b("PREFERENCE_UJ_MINI_V4_ENABLED", 0, false);
        if (Constants.P != b) {
            ((GaanaActivity) this.mContext).initBottomNavigationBar();
            ((GaanaActivity) this.mContext).initMiniPlayer();
            Constants.P = b;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bb  */
    /* JADX WARNING: Missing block: B:24:0x00b3, code skipped:
            if (r0.equals("search") != false) goto L_0x00b7;
     */
    public void initBottomNavigationBar() {
        /*
        r6 = this;
        r0 = new com.views.c;
        r0.<init>();
        r6.mBottomNavigationBarHelper = r0;
        r0 = 2131296525; // 0x7f09010d float:1.821097E38 double:1.053000394E-314;
        r1 = r6.findViewById(r0);
        r1 = (com.gaana.view.CustomBottomNavigationView) r1;
        r6.bottomNavigationView = r1;
        r1 = com.utilities.Util.j();
        com.constants.Constants.cN = r1;
        r1 = com.constants.Constants.I;
        r2 = 8;
        r3 = 1;
        r4 = 2131296526; // 0x7f09010e float:1.8210971E38 double:1.0530003946E-314;
        r5 = 0;
        if (r1 != r3) goto L_0x0046;
    L_0x0023:
        r1 = r6.mContext;
        r1 = com.managers.PlayerManager.a(r1);
        r1 = r1.l();
        if (r1 <= 0) goto L_0x0046;
    L_0x002f:
        r1 = r6.findViewById(r4);
        r1.setVisibility(r5);
        r0 = r6.findViewById(r0);
        r0.setVisibility(r2);
        r0 = r6.findViewById(r4);
        r0 = (com.gaana.view.CustomBottomNavigationView) r0;
        r6.bottomNavigationView = r0;
        goto L_0x005c;
    L_0x0046:
        r1 = r6.findViewById(r4);
        r1.setVisibility(r2);
        r1 = r6.findViewById(r0);
        r1.setVisibility(r5);
        r0 = r6.findViewById(r0);
        r0 = (com.gaana.view.CustomBottomNavigationView) r0;
        r6.bottomNavigationView = r0;
    L_0x005c:
        r0 = r6.bottomNavigationView;
        r1 = r6.mTouchListener;
        r0.setOnTouchListener(r1);
        r0 = r6.bottomNavigationView;
        r1 = new com.gaana.GaanaActivity$25;
        r1.<init>();
        r0.setOnClickListener(r1);
        r6.setBottomNavigationViewTabs();
        r6.setMiniPlayerCarouselUI();
        r0 = r6.fragmentTag;
        r1 = -1;
        r2 = r0.hashCode();
        r4 = -906336856; // 0xffffffffc9fa65a8 float:-2051253.0 double:NaN;
        if (r2 == r4) goto L_0x00ad;
    L_0x007f:
        r3 = 3208415; // 0x30f4df float:4.495947E-39 double:1.5851676E-317;
        if (r2 == r3) goto L_0x00a3;
    L_0x0084:
        r3 = 108270587; // 0x67413fb float:4.590598E-35 double:5.34927775E-316;
        if (r2 == r3) goto L_0x0099;
    L_0x0089:
        r3 = 1522043897; // 0x5ab88bf9 float:2.59726486E16 double:7.51989601E-315;
        if (r2 == r3) goto L_0x008f;
    L_0x008e:
        goto L_0x00b6;
    L_0x008f:
        r2 = "mymusic";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00b6;
    L_0x0097:
        r3 = 3;
        goto L_0x00b7;
    L_0x0099:
        r2 = "radio";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00b6;
    L_0x00a1:
        r3 = 2;
        goto L_0x00b7;
    L_0x00a3:
        r2 = "home";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00b6;
    L_0x00ab:
        r3 = r5;
        goto L_0x00b7;
    L_0x00ad:
        r2 = "search";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00b6;
    L_0x00b5:
        goto L_0x00b7;
    L_0x00b6:
        r3 = r1;
    L_0x00b7:
        switch(r3) {
            case 0: goto L_0x00e5;
            case 1: goto L_0x00d7;
            case 2: goto L_0x00c9;
            case 3: goto L_0x00bb;
            default: goto L_0x00ba;
        };
    L_0x00ba:
        goto L_0x00f2;
    L_0x00bb:
        r0 = SHOW_TAB_MYMUSIC;
        r6.mSelectedPosition = r0;
        r0 = r6.mBottomNavigationBarHelper;
        r1 = r6.bottomNavigationView;
        r2 = SHOW_TAB_MYMUSIC;
        r0.a(r1, r2);
        goto L_0x00f2;
    L_0x00c9:
        r0 = SHOW_TAB_RADIO;
        r6.mSelectedPosition = r0;
        r0 = r6.mBottomNavigationBarHelper;
        r1 = r6.bottomNavigationView;
        r2 = SHOW_TAB_RADIO;
        r0.a(r1, r2);
        goto L_0x00f2;
    L_0x00d7:
        r0 = SHOW_TAB_SEARCH;
        r6.mSelectedPosition = r0;
        r0 = r6.mBottomNavigationBarHelper;
        r1 = r6.bottomNavigationView;
        r2 = SHOW_TAB_SEARCH;
        r0.a(r1, r2);
        goto L_0x00f2;
    L_0x00e5:
        r0 = SHOW_TAB_HOME;
        r6.mSelectedPosition = r0;
        r0 = r6.mBottomNavigationBarHelper;
        r1 = r6.bottomNavigationView;
        r2 = SHOW_TAB_HOME;
        r0.a(r1, r2);
    L_0x00f2:
        r0 = r6.mBottomNavigationBarHelper;
        r1 = r6.bottomNavigationView;
        r0.a(r1);
        r0 = r6.mBottomNavigationBarHelper;
        r0.a(r6);
        r6.isNavTabClicked = r5;
        r0 = r6.bottomNavigationView;
        r1 = r6.mTouchListener;
        r0.setOnTouchListener(r1);
        r0 = com.managers.an.a();
        r1 = r6.mSelectedPosition;
        r0.a = r1;
        r0 = r6.bottomNavigationView;
        r1 = new com.gaana.GaanaActivity$26;
        r1.<init>();
        r0.setOnNavigationItemSelectedListener(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity.initBottomNavigationBar():void");
    }

    private void sendBottomNavigationUJEvents(String str) {
        this.currentTabName = str;
        an.a().a("click", "ac", "", this.previousTabName, "", this.currentTabName, "", "");
        this.previousTabName = this.currentTabName;
    }

    private void handleScreenViewEvents(int i) {
        if (Constants.I == 0) {
            switch (i) {
                case -1:
                    sendGAEvent(this.currentScreen, "Bottom Bar Click", "Social");
                    return;
                case 0:
                    sendGAEvent(this.currentScreen, "Bottom Bar Click", "Home");
                    q.a().a("int", "cat:collection");
                    return;
                case 1:
                    sendGAEvent(this.currentScreen, "Bottom Bar Click", "Search");
                    q.a().a("int", "cat:discover");
                    return;
                case 2:
                    sendGAEvent(this.currentScreen, "Bottom Bar Click", "Radio");
                    q.a().a("int", "cat:radio");
                    MoEngage.getInstance().reportSectionViewedEvent("Radio");
                    return;
                case 3:
                    sendGAEvent(this.currentScreen, "Bottom Bar Click", LocalMediaManager.MY_MUSIC);
                    return;
                default:
                    return;
            }
        }
        switch (i) {
            case -1:
                sendGAEvent(this.currentScreen, "Bottom Bar Click", "Social");
                return;
            case 0:
                sendGAEvent(this.currentScreen, "Bottom Bar Click", "Home");
                q.a().a("int", "cat:collection");
                return;
            case 1:
                sendGAEvent(this.currentScreen, "Bottom Bar Click", "Search");
                q.a().a("int", "cat:discover");
                return;
            case 3:
                sendGAEvent(this.currentScreen, "Bottom Bar Click", "Radio");
                q.a().a("int", "cat:radio");
                MoEngage.getInstance().reportSectionViewedEvent("Radio");
                return;
            case 4:
                sendGAEvent(this.currentScreen, "Bottom Bar Click", LocalMediaManager.MY_MUSIC);
                return;
            default:
                return;
        }
    }

    public void setRepeatOne(String str, String str2) {
        PlayerManager a = PlayerManager.a(this.mContext);
        if (str == null || !str.equals(str2) || a == null || !a.z()) {
            PlayerManager.a(this.mContext).i(false);
            if (this.mPlayer instanceof MiniPlayerFragmentV4) {
                ((MiniPlayerFragmentV4) this.mPlayer).d();
                BaseFragment baseFragment = getmCurrentPlayerFragment();
                if (baseFragment instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) baseFragment).q();
                    return;
                }
                return;
            }
            return;
        }
        PlayerManager.a(this.mContext).i(true);
    }

    private void handleDisplayLanguageChange() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("onboarding_display_language_set", false)) {
            GaanaApplication gaanaApplication = this.mAppState;
            if (!GaanaApplication.getLanguage(this).equalsIgnoreCase("English")) {
                aj a = aj.a();
                String string = getString(R.string.undo_langauge);
                String string2 = getString(R.string.language_selected);
                Object[] objArr = new Object[1];
                GaanaApplication gaanaApplication2 = this.mAppState;
                objArr[0] = GaanaApplication.getLanguage(this);
                a.a((Context) this, string, String.format(string2, objArr), this.snackBarUnDoInterface);
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.onCreateCalled && z) {
            this.onCreateCalled = false;
            aa.a().a(false);
            ab.a().c();
            setUpdatePlayerFragment();
            handleDisplayLanguageChange();
        }
        if (z) {
            setStatusBarTransparentAndNavigationBarColor();
        }
        if (z && this.needToLaunchExpandedPlayer) {
            launchExpandedPlayer();
            Constants.P = -1;
            resetBottomNavigation();
            this.needToLaunchExpandedPlayer = false;
        }
        if (this.mCurrentPlayerFragment instanceof PlayerFragmentV4) {
            ((PlayerFragmentV4) this.mCurrentPlayerFragment).e();
            ((PlayerFragmentV4) this.mCurrentPlayerFragment).d();
        } else if (this.mCurrentPlayerFragment instanceof PlayerRadioFragmentV4) {
            ((PlayerRadioFragmentV4) this.mCurrentPlayerFragment).e();
            ((PlayerRadioFragmentV4) this.mCurrentPlayerFragment).d();
        }
    }

    private void startSponsorAd() {
        if (ap.a().c(this.mContext) && this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() == 2 && ColombiaManager.b().e()) {
            ColombiaManager.b().d();
            initiateColombiaSponsorAds();
        }
    }

    private void initiateColombiaSponsorAds() {
        ColombiaManager.b().a(1, this, 23, -1, this.adView, "GaanaActivity", new ColombiaManager.a() {
            public void onItemRequestFailed(Exception exception) {
            }

            public void onItemLoaded(Item item) {
                u.a().a("Trial_Sponsership", "Trial_Left_Nav", "Trial_Left_Nav_Counter");
            }
        }, "");
    }

    private void reportLocation() {
        MoEngage.getInstance().reportLocation(null);
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        GaanaApplication.sessionHistoryCount = d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false);
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("FRAGMENT_NAME", com.constants.a.a(this.mFragment));
        this.mFragmentController.a(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length > 0 && iArr[0] == 0) {
            switch (i) {
                case 101:
                    reportLocation();
                    return;
                case 102:
                    if (this.mFragment instanceof MyMusicFragment) {
                        ((MyMusicFragment) this.mFragment).b();
                        return;
                    }
                    return;
                case 106:
                    onBottomMenuLongClick();
                    return;
                case 107:
                    changeFragment(R.id.LeftIdentifySong, null, null);
                    return;
                case 108:
                    if (this.mFragment instanceof PartyFragment) {
                        ((PartyFragment) this.mFragment).a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (strArr.length <= 0 || !h.a(this, strArr[0])) {
            h.b(this, getString(R.string.enable_storage_permission), 0);
        } else {
            h.a(this, strArr[0], i);
        }
    }

    private boolean showMenuButton() {
        return (this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof DiscoverFragment) || (this.mFragment instanceof RadioActivityFragment) || (this.mFragment instanceof MyMusicFragment);
    }

    private void initUser() {
        boolean b = this.mDeviceResManager.b("PREFERENCE_KEY_OFFLINE_MODE", false, false);
        boolean b2 = this.mDeviceResManager.b("PREFERENCE_KEY_DATA_SAVE_MODE", false, false);
        boolean b3 = this.mDeviceResManager.b("PREFERENCE_KEY_ENDLESS_PLAYBACK", true, false);
        boolean b4 = this.mDeviceResManager.b("PREFERENCE_LYRICS_DISPLAY", true, false);
        boolean b5 = this.mDeviceResManager.b("PREFERENCE_VIDEO_AUTOPLAY", true, true);
        Constants.cL = this.mDeviceResManager.b("pref_explicit_content", false, false);
        this.mAppState.setAppInOfflineMode(b);
        this.mAppState.setAppInDataSaveMode(b2);
        this.mAppState.setIsEndlessPlayback(b3);
        this.mAppState.setLyricsDisplay(b4);
        this.mAppState.setIsVideoAutoplay(b5);
    }

    private void syncDownloadWithDb() {
        com.i.d.a(new Runnable() {
            public void run() {
                DownloadManager.c().f();
            }
        });
    }

    public com.views.i getSlidingPanelLayout() {
        return this.slidingUPLayout;
    }

    private com.views.i initSlidingPane() {
        this.slidingUPLayout = new com.views.i(this);
        return this.slidingUPLayout;
    }

    public Fragment getMiniPlayer() {
        return this.mPlayer;
    }

    public void addDownloadSyncReceiver() {
        this.isDownloadSyncReceiverRegistered = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("intent_download_sync_completed");
        registerReceiver(this.mDownloadSyncReceiver, intentFilter);
    }

    public void addDownloadReceiver() {
        DownloadManager.c().a(this.broadcastReceiver);
        if (ap.a().d()) {
            Constants.ab = false;
            Constants.aa = false;
            d.a().a(Constants.ac, false, true);
            d.a().a(Constants.ad, false, true);
        }
    }

    public void displayLaunchFragment(int i, Bundle bundle) {
        this.isLaunchedFromDeeplink = handleDeeplinkingRequest(bundle);
        boolean z = this.isLaunchedFromDeeplink && Util.j(this.mContext);
        Constants.ee = z;
        if (!this.isLaunchedFromDeeplink) {
            if (i == SHOW_TAB_HOME) {
                this.mFragment = new DynamicHomeFragment();
                this.fragmentTag = "home";
                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_HOME);
            } else if (i == SHOW_TAB_MYMUSIC) {
                this.mFragment = new MyMusicFragment();
                this.fragmentTag = "mymusic";
                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
            }
            this.mFragment.setArguments(bundle);
            displayFragment(this.mFragment);
            if (i == SHOW_TAB_HOME) {
                handleScreenViewEvents(SHOW_TAB_HOME);
            } else if (i == SHOW_TAB_MYMUSIC) {
                handleScreenViewEvents(SHOW_TAB_MYMUSIC);
            }
            this.mDrawerLayout.setScrimColor(getResources().getColor(R.color.black_alfa_50));
            if (bundle != null) {
                String stringExtra = getIntent().getStringExtra(SearchIntents.EXTRA_QUERY);
                if (TextUtils.isEmpty(stringExtra)) {
                    i = getIntent().getIntExtra("DEEPLINKING_SCREEN", -1);
                    String stringExtra2 = getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM");
                    String stringExtra3 = getIntent().getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2");
                    if (i != -1) {
                        changeFragment(i, stringExtra2, stringExtra3);
                        return;
                    }
                    return;
                }
                performSearch(stringExtra.trim());
            }
        }
    }

    public boolean handleDeeplinkingRequest(Bundle bundle) {
        boolean z;
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            boolean z2 = bundle2.getBoolean("PLAY_DEEPLINKING_SONG", false);
            Radio radio = (Radio) bundle2.getSerializable("PLAY_DEEPLINKING_RADIO");
            String string = bundle2.getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            String string2 = bundle2.getString("DEEPLINKING_SCREEN_EXTRA_PARAM2");
            String string3 = bundle2.getString("EXTRA_URI_PATH");
            String string4 = bundle2.getString("LAUNCH_PAGE");
            boolean z3 = bundle2.getBoolean("LAUNCH_DETAIL_PAGE", false);
            boolean z4 = bundle2.getBoolean("launch_see_all", false);
            boolean z5 = bundle2.getBoolean("launch_video_activity", false);
            boolean z6 = bundle2.getBoolean("LAUNCH_YEAR_VIDEO_PLAYER_ACTIVITY", false);
            boolean z7 = bundle2.getBoolean("LAUNCH_OCCASION_FRAGMENT", false);
            boolean z8 = bundle2.getBoolean("launch_vpl_section", false);
            boolean z9 = bundle2.getBoolean("LAUNCH_VIDEO_FEED_FRAGMENT", false);
            if (!TextUtils.isEmpty(string3) && string3.equalsIgnoreCase("/view/recentlyplayed/seeall")) {
                return com.services.c.a((Context) this).b(this, string3);
            }
            String string5;
            BaseGaanaFragment songParallexListingFragment;
            Intent intent;
            if (z3 && this.mAppState.getListingComponents() != null) {
                BusinessObject a;
                if (radio != null) {
                    a = this.mAppState.getListingComponents().a();
                    playRadio(radio);
                    if (a != null) {
                        com.managers.af.a(this.mContext, null).a((int) R.id.radioMenu, a);
                    }
                } else {
                    a = this.mAppState.getListingComponents().a();
                    com.managers.af a2 = com.managers.af.a(this.mContext, null);
                    a2.c(string);
                    if (a instanceof Album) {
                        com.managers.af.a(this.mContext, null).a((int) R.id.albumMenu, a);
                    } else if (a instanceof Playlist) {
                        com.managers.af.a(this.mContext, null).a((int) R.id.playlistMenu, a);
                    } else if (a instanceof Artist) {
                        com.managers.af.a(this.mContext, null).a((int) R.id.artistMenu, a);
                    }
                    a2.c(null);
                }
                return true;
            } else if (z2) {
                ArrayList arrayList = new ArrayList();
                PlayerTrack b = com.services.c.b();
                if (b != null) {
                    com.services.c.a(null);
                    arrayList.add(b);
                    PlayerManager.a(this.mContext).a(arrayList, b);
                    PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                    setUpdatePlayerFragment();
                    setSlideUpPanel(true);
                    if (b.b().isLocalMedia()) {
                        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SHARE.name());
                        com.managers.af.a(this.mContext, null).a((int) R.id.albumMenu, b.b());
                    }
                }
                return true;
            } else if (z4) {
                string5 = bundle2.getString("VIEW_TYPE_SEE_ALL");
                if (TextUtils.isEmpty(string5) || string5.equals(DynamicViewType.grid_rect.name()) || string5.equals(DynamicViewType.grid.name())) {
                    if (string3.contains("radio")) {
                        this.fragmentTag = "radio";
                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_RADIO);
                    } else {
                        this.fragmentTag = "home";
                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_HOME);
                    }
                    BaseGaanaFragment gridActivityFragment = new GridActivityFragment();
                    bundle2.putString("EXTRA_VIEW_TYPE_SEE_ALL", string5);
                    gridActivityFragment.setArguments(bundle2);
                    displayFragment(gridActivityFragment);
                } else {
                    songParallexListingFragment = new SongParallexListingFragment();
                    ListingParams listingParams = new ListingParams();
                    listingParams.e(false);
                    listingParams.f(true);
                    listingParams.h(false);
                    listingParams.d(true);
                    listingParams.i(false);
                    listingParams.a(true);
                    listingParams.a(bundle2.getString("EXTRA_GRID_SEE_ALL_AD_CODE"));
                    listingParams.b(bundle2.getString("EXTRA_GASECTION_NAME"));
                    ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                    listingButton.b(bundle2.getString("EXTRA_ACTIONBAR_TITLE").toLowerCase());
                    listingButton.a(bundle2.getString("EXTRA_ACTIONBAR_TITLE").toLowerCase());
                    URLManager c = listingButton.c();
                    c.g(true);
                    c.a(((URLManager) bundle2.getParcelable("EXTRA_URL_MANAGER")).k());
                    c.d(false);
                    c.a(true);
                    c.a(BusinessObjectType.GenericItems);
                    c.h(true);
                    listingParams.a(listingButton);
                    songParallexListingFragment.a(listingParams);
                    this.mAppState.setListingComponents(new ListingComponents());
                    displayFragment(songParallexListingFragment);
                }
                return true;
            } else if (z5 && Constants.cW) {
                String string6 = bundle2.getString("video_id");
                intent = new Intent(this, YouTubePlayerActivity.class);
                intent.putExtra("orientation", Orientation.AUTO_START_WITH_LANDSCAPE);
                intent.putExtra("video_id", string6);
                if (GaanaMusicService.t()) {
                    o.a(this, PauseReasons.MEDIA_BUTTON_TAP);
                    Constants.dc = true;
                }
                if (com.managers.f.v().w()) {
                    com.managers.f.v().F();
                    Constants.dc = true;
                }
                startActivityForResult(intent, 101);
                return false;
            } else {
                if (z6) {
                    if (GaanaMusicService.t()) {
                        o.a(this, PauseReasons.MEDIA_BUTTON_TAP);
                        Constants.dc = true;
                    }
                    if (com.managers.f.v().w()) {
                        com.managers.f.v().F();
                        Constants.dc = true;
                    }
                    if (com.utilities.d.g()) {
                        intent = new Intent(this, VideoPlayerActivityTwo.class);
                    } else {
                        intent = new Intent(this, FullScreenVideoPlayerActivity.class);
                    }
                    intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
                    intent.putExtra("share_url", bundle2.getString("share_url"));
                    intent.putExtra("video_url", bundle2.getString("video_url"));
                    startActivityForResult(intent, 1001);
                } else if (z7) {
                    songParallexListingFragment = new DynamicOccasionFragment();
                    songParallexListingFragment.setArguments(bundle2);
                    displayFragment(songParallexListingFragment);
                    return true;
                } else if (z8) {
                    return com.services.c.a((Context) this).c(this, string3);
                } else {
                    if (string != null && string.equalsIgnoreCase("g")) {
                        enablePushNotification();
                        return true;
                    } else if (!TextUtils.isEmpty(string) && string.contains("/juke/")) {
                        displayFragment(JukePartyFragment.newInstance(new JukePlaylist(), -1, string.split("/juke/")[1], false));
                        return true;
                    } else if (!TextUtils.isEmpty(string) && string.equals("persona")) {
                        displayFragment(new PersonaDedicationFragment());
                        return true;
                    } else if (!TextUtils.isEmpty(string) && string.equals("curateddownload")) {
                        com.managers.g.a(this.mContext, null, null);
                        return true;
                    } else if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && string2.contains("gcm_coupon")) {
                        string5 = string2.split("=")[1];
                        this.mFragment = new SettingsDetailFragment();
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("KEY_SETTINGS", 1);
                        bundle3.putBoolean("LAUNCH_GAANA_PLUS", true);
                        bundle3.putString("item_id", string);
                        bundle3.putString("purchase_coupon_code", string5);
                        this.mFragment.setArguments(bundle3);
                        displayFragment(this.mFragment);
                    } else if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        BaseGaanaFragment gaanaMiniPurchaseFragment = new GaanaMiniPurchaseFragment();
                        gaanaMiniPurchaseFragment.setArguments(GaanaMiniPurchaseFragment.a(string, string2));
                        displayFragment(gaanaMiniPurchaseFragment);
                        return true;
                    } else if (!TextUtils.isEmpty(string4) && string4.equals("Home")) {
                        songParallexListingFragment = new DynamicHomeFragment();
                        songParallexListingFragment.setArguments(bundle2);
                        displayFragment(songParallexListingFragment);
                        return true;
                    } else if (!TextUtils.isEmpty(string4) && string4.equals("Radio")) {
                        songParallexListingFragment = new RadioActivityFragment();
                        songParallexListingFragment.setArguments(bundle2);
                        displayFragment(songParallexListingFragment);
                        return true;
                    } else if (z9) {
                        Util.a(this, new YouTubeVideo(), GAANA_ENTRY_PAGE.DEEP_LINK.name());
                        return true;
                    }
                }
                ProfileUser profileUser = (ProfileUser) bundle2.getSerializable("SHOW_PROFILE_USER");
                if (profileUser != null) {
                    if (!"0".equals(profileUser.getBusinessObjId())) {
                        showProfileUser(profileUser);
                        return true;
                    } else if (!this.mAppState.getCurrentUser().getLoginStatus()) {
                        Intent intent2 = new Intent(this.mContext, Login.class);
                        intent2.putExtra("is_login_as_activity_result", true);
                        startActivity(intent2);
                        return true;
                    }
                }
                z = false;
            }
        } else {
            z = false;
        }
        return z;
    }

    private void enablePushNotification() {
        checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (GaanaActivity.this.mAppState.getCurrentUser() != null && GaanaActivity.this.mAppState.getCurrentUser().getLoginStatus()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    GaanaActivity.this.displayFragment(settingsDetailFragment);
                    if (GaanaActivity.this.mContext instanceof SplashScreenActivity) {
                        ((SplashScreenActivity) GaanaActivity.this.mContext).finish();
                    }
                }
            }
        }, null);
    }

    private void playRadio(final Radio radio) {
        try {
            if (Constants.cY) {
                JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    GaanaActivity.this.playRadio(radio);
                                }
                            }
                        });
                    }
                });
                return;
            }
            if (radio.getType().equals(com.constants.c.d.c)) {
                com.managers.ad.a(this.mContext).a((BusinessObject) radio);
            } else {
                com.managers.ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
            }
        } catch (Exception unused) {
        }
    }

    private void showProfileUser(final ProfileUser profileUser) {
        checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (GaanaActivity.this.mAppState.getCurrentUser() != null && GaanaActivity.this.mAppState.getCurrentUser().getLoginStatus()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("EXTRA_PROFILE_ORIGIN_MYPROFILE", ShareConstants.PEOPLE_IDS);
                    bundle.putSerializable("EXTRA_PROFILE_USER_BUSINESS_OBJECT", profileUser);
                    BaseGaanaFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    GaanaActivity.this.displayFragment(profileFragment);
                }
            }
        }, null, true);
    }

    public void updateSidebarActiveButton(BaseGaanaFragment baseGaanaFragment) {
        this.mFragment = baseGaanaFragment;
        if (this.mFragment == null) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuHome);
        } else if ((this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof DownloadHomeFragment)) {
            this.mAppState.setSidebarActiveBtn(R.id.MyMusicMenuDownloads);
        } else if (this.mFragment instanceof RadioActivityFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuRadio);
        } else if (this.mFragment instanceof DynamicHomeFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuHome);
        } else if (this.mFragment instanceof ReferFriendsFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuReferFriend);
        } else if (this.mFragment instanceof DiscoverFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuDiscover);
        } else if (this.mFragment instanceof ActivityFeedActivityFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuFriendsActivity);
        } else if (this.mFragment instanceof SettingsFragment) {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuSettings);
        } else if (this.mFragment instanceof SettingsDetailFragment) {
            String b = ((SettingsDetailFragment) this.mFragment).b();
            if (!TextUtils.isEmpty(b) && b.equalsIgnoreCase("REDEEM_COUPON_UI_SCREEN")) {
                this.mAppState.setSidebarActiveBtn(R.id.LeftMenuCoupons);
            }
        } else {
            this.mAppState.setSidebarActiveBtn(R.id.LeftMenuHome);
        }
        setUpNavMenulist();
    }

    public void closeDrawer() {
        this.changeFragment = true;
        this.mDrawerLayout.closeDrawer(this.sideView_Container);
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(i);
    }

    private void openFavoriteRadios(BaseGaanaFragment baseGaanaFragment) {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.a(SortOrder.Default);
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.d(true);
        listingParams.i(true);
        listingParams.j(true);
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_RADIO.name());
        ListingButton listingButton = (ListingButton) Constants.a().c().get(3);
        listingButton.b(true);
        listingButton.a(new com.managers.m());
        listingButton.b("Radio");
        listingButton.a("Radios");
        URLManager c = listingButton.c();
        c.g(true);
        c.a("https://api.gaana.com/radio.php?type=radio&subtype=favorite_radios");
        c.a(Boolean.valueOf(true));
        c.d(true);
        c.h(false);
        listingParams.a(listingButton);
        listingFragment.a(listingParams);
        listingFragment.a(baseGaanaFragment);
        listingFragment.b(true);
        this.mAppState.setListingComponents(new ListingComponents());
        displayFragment(listingFragment);
    }

    private void openFavoriteArtist(BaseGaanaFragment baseGaanaFragment) {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.a(SortOrder.Default);
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.d(true);
        listingParams.i(true);
        listingParams.j(true);
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_ARTIST.name());
        ListingButton listingButton = (ListingButton) Constants.a().c().get(4);
        listingButton.b("Artist");
        listingButton.a("Artists");
        listingButton.b(true);
        listingButton.a(new com.managers.m());
        URLManager c = listingButton.c();
        c.g(true);
        c.a("https://api.gaana.com/user.php?type=myartists&subtype=favorites&limit=0,100");
        c.a(Boolean.valueOf(true));
        c.h(false);
        listingParams.a(listingButton);
        listingFragment.a(listingParams);
        listingFragment.a(baseGaanaFragment);
        this.mAppState.setListingComponents(new ListingComponents());
        displayFragment(listingFragment);
    }

    public void changeFragment(int i, @Nullable final String str, @Nullable String str2) {
        this.mAppState.setSidebarActiveBtn(i);
        if (i != R.id.Benefits) {
            if (i == R.id.ExpandPlayerMenu) {
                this.needToLaunchExpandedPlayer = true;
            } else if (i == R.id.StudentPack_Verifyeligibilityscreen) {
                handleStudentPackVerification();
            } else if (i == R.id.rlProfileSideBar) {
                displayProfile();
            } else if (i != R.id.upgradeButtonLayout) {
                Bundle bundle;
                String b;
                BaseGaanaFragment settingsDetailFragment;
                switch (i) {
                    case R.id.DeepLinkingDownloadSync /*2131296264*/:
                        checkSetLoginStatus(new ad() {
                            public void onLoginSuccess() {
                                l.a().c(true);
                            }
                        }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_DOWNLOAD_SYNC));
                        break;
                    case R.id.DeepLinkingGaanaPlus /*2131296265*/:
                        this.mFragment = new SettingsDetailFragment();
                        bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        if (!TextUtils.isEmpty(str)) {
                            bundle.putString("SHOW_PRICE_DIALOGUE_TYPE", str);
                        }
                        bundle.putBoolean("SHOW_PRICE_DIALOGUE", true);
                        bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                        this.mFragment.setArguments(bundle);
                        displayFragment(this.mFragment);
                        break;
                    case R.id.DeepLinkingGaanaPlusSettings /*2131296266*/:
                        checkSetLoginStatus(new ad() {
                            public void onLoginSuccess() {
                                GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 1);
                                bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
                                bundle.putBoolean("LAUNCH_GAANA_PLUS", false);
                                GaanaActivity.this.mFragment.setArguments(bundle);
                                GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                            }
                        }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVOURITE_FROM_MENU));
                        break;
                    case R.id.DeepLinkingRateApp /*2131296267*/:
                        this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.gaana")));
                        break;
                    case R.id.DeepLinkingRedeemCoupon /*2131296268*/:
                        if (!(this.mFragment instanceof SettingsDetailFragment)) {
                            checkSetLoginStatus(new ad() {
                                public void onLoginSuccess() {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("KEY_SETTINGS", 6);
                                    bundle.putString("TAG_SETTINGS_REDEEM_COUPON_CODE", str);
                                    GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                    GaanaActivity.this.mFragment.setArguments(bundle);
                                    GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                }
                            }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                            break;
                        }
                        b = ((SettingsDetailFragment) this.mFragment).b();
                        if (TextUtils.isEmpty(b) || !b.equalsIgnoreCase("REDEEM_COUPON_UI_SCREEN")) {
                            checkSetLoginStatus(new ad() {
                                public void onLoginSuccess() {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("KEY_SETTINGS", 6);
                                    bundle.putString("TAG_SETTINGS_REDEEM_COUPON_CODE", str);
                                    GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                    GaanaActivity.this.mFragment.setArguments(bundle);
                                    GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                }
                            }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                            break;
                        }
                    case R.id.DeepLinkingRestorePurchase /*2131296269*/:
                        this.mFragment = new SettingsFragment();
                        this.bundle = new Bundle();
                        this.bundle.putBoolean("TAG_SETTINGS_START_RESTORE_PURCHASE", true);
                        this.mFragment.setArguments(this.bundle);
                        displayFragment(this.mFragment);
                        break;
                    case R.id.DeleteDataMenu /*2131296270*/:
                        bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 23);
                        settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                        break;
                    case R.id.EducativeHD /*2131296271*/:
                        if (!(this.mFragment instanceof EducativeHDStreamFragment)) {
                            this.mFragment = new EducativeHDStreamFragment();
                            displayFragment(this.mFragment);
                            break;
                        }
                        break;
                    default:
                        Intent intent;
                        Bundle bundle2;
                        switch (i) {
                            case R.id.GetGaanaStatus /*2131296276*/:
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        GaanaActivity.this.updateUserStatus(new au() {
                                            public void onUserStatusUpdated() {
                                            }
                                        });
                                    }
                                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                                break;
                            case R.id.InternationalOnBoardPurchaseScreen /*2131296277*/:
                                this.mFragment = new SettingsDetailFragment();
                                this.fromInternationalOnBoarding = true;
                                bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 1);
                                bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                bundle.putString("item_id", str);
                                bundle.putString("product_id", str2);
                                bundle.putBoolean("international_onboarding", true);
                                this.mFragment.setArguments(bundle);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LanguageSettingsDetail /*2131296278*/:
                                this.mFragment = new SettingsDetailFragment();
                                bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 14);
                                this.mFragment.setArguments(bundle);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LaunchGaanaVoice /*2131296279*/:
                                onBottomMenuLongClick();
                                break;
                            case R.id.LaunchLoginForFavorite /*2131296280*/:
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        BusinessObject b = PlayerManager.a(GaanaActivity.this.mContext).i().b();
                                        if (b.getBusinessObjId().equalsIgnoreCase(str)) {
                                            if (b.isFavorite().booleanValue()) {
                                                b.setFavorite(Boolean.valueOf(false));
                                                n.a().c(b);
                                            } else {
                                                b.setFavorite(Boolean.valueOf(true));
                                                n.a().b(b);
                                            }
                                            if (GaanaMusicService.u()) {
                                                o.g(GaanaActivity.this);
                                            }
                                        }
                                    }
                                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_SONG));
                                break;
                            case R.id.LeftDispLanguage /*2131296281*/:
                                if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
                                    intent = new Intent(this.mContext, AppLanguageSettingsScreenActivity.class);
                                    intent.setFlags(71303168);
                                    intent.putExtra("skipEnabled", false);
                                    startActivity(intent);
                                    break;
                                }
                            case R.id.LeftIdentifySong /*2131296282*/:
                                if (h.b((Activity) this.mContext)) {
                                    new com.voice.a(this).show();
                                    break;
                                }
                                break;
                            case R.id.LeftMenuAdyenPurchaseResponseFailure /*2131296283*/:
                                ((BaseActivity) this.mContext).updateUserStatus(new au() {
                                    public void onUserStatusUpdated() {
                                    }
                                });
                                Toast.makeText(this.mAppState, this.mContext.getString(R.string.purchase_error), 1).show();
                                break;
                            case R.id.LeftMenuAdyenPurchaseResponseSuccess /*2131296284*/:
                                ((BaseActivity) this.mContext).updateUserStatus(new au() {
                                    public void onUserStatusUpdated() {
                                        ((BaseActivity) GaanaActivity.this.mContext).hideProgressDialog();
                                        Util.aa();
                                        Toast.makeText(GaanaActivity.this.mAppState, GaanaActivity.this.mAppState.getString(R.string.enjoy_using_gaana_plus), 1).show();
                                        if (Util.v(GaanaActivity.this.mContext)) {
                                            Intent intent = new Intent(GaanaActivity.this.mContext, GaanaActivity.class);
                                            intent.setFlags(71303168);
                                            GaanaActivity.this.mContext.startActivity(intent);
                                        }
                                    }
                                });
                                break;
                            case R.id.LeftMenuCoupons /*2131296285*/:
                                changeFragment(R.id.DeepLinkingRedeemCoupon, null, null);
                                break;
                            case R.id.LeftMenuDiscover /*2131296286*/:
                                break;
                            case R.id.LeftMenuExit /*2131296287*/:
                                exitFromGaana();
                                break;
                            case R.id.LeftMenuFeedback /*2131296288*/:
                                UninstallIO.sendHelpScreenTappedEvent();
                                startHelpShiftActivity();
                                break;
                            case R.id.LeftMenuFriendsActivity /*2131296289*/:
                                friendsActivity();
                                break;
                            case R.id.LeftMenuGooglePurchase /*2131296290*/:
                            case R.id.LeftMenuHermesPurchase /*2131296291*/:
                            case R.id.LeftMenuPaytmPurchase /*2131296304*/:
                            case R.id.LeftMenuPurchase /*2131296305*/:
                                if (str != null && str2 != null) {
                                    initDirectPayment(str, str2);
                                    break;
                                }
                                this.mFragment = new SettingsDetailFragment();
                                bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 1);
                                bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                bundle.putString("item_id", str);
                                bundle.putString("product_id", str2);
                                this.mFragment.setArguments(bundle);
                                displayFragment(this.mFragment);
                                break;
                                break;
                            case R.id.LeftMenuHermesPurchaseResponseFailure /*2131296292*/:
                                PurchaseHermesManager.a((Context) this).a(str, PaymentResponse.FAILURE);
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("KEY_SETTINGS", 1);
                                        bundle.putBoolean("SHOW_PRICE_DIALOGUE", true);
                                        bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                        GaanaActivity.this.mFragment.setArguments(bundle);
                                        GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                    }
                                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                                break;
                            case R.id.LeftMenuHermesPurchaseResponseSuccess /*2131296293*/:
                                PurchaseHermesManager.a((Context) this).a(str, PaymentResponse.SUCCESS);
                                break;
                            case R.id.LeftMenuHome /*2131296294*/:
                                this.mFragment = new DynamicHomeFragment();
                                bundle2 = new Bundle();
                                bundle2.putInt("DEEPLINKING_SCREEN", i);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                this.fragmentTag = "home";
                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_HOME);
                                this.mFragment.setArguments(bundle2);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LeftMenuLogin /*2131296295*/:
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        if (GaanaActivity.this.mFragment instanceof DynamicHomeFragment) {
                                            GaanaActivity.this.mFragment.refreshDataandAds();
                                            ((DynamicHomeFragment) GaanaActivity.this.mFragment).h();
                                        }
                                        if (GaanaActivity.this.mFragment instanceof MyMusicFragment) {
                                            ((MyMusicFragment) GaanaActivity.this.mFragment).c();
                                        }
                                    }
                                }, "");
                                break;
                            case R.id.LeftMenuMyMusic /*2131296296*/:
                                this.mFragment = new MyMusicFragment();
                                bundle2 = new Bundle();
                                this.fragmentTag = "mymusic";
                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                bundle2.putInt("DEEPLINKING_SCREEN", i);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                bundle2.putString("DEEPLINKING_SCREEN_SORT_ORDER", SortOrder.Default.name());
                                this.mFragment.setArguments(bundle2);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LeftMenuNotifications /*2131296297*/:
                                showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
                                closeDrawers();
                                break;
                            case R.id.LeftMenuOperatorPurchaseResponseFailure /*2131296298*/:
                                PurchaseOperatorManager.a((Context) this).a(str, PurchaseOperatorManager.PaymentResponse.FAILURE);
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("KEY_SETTINGS", 1);
                                        bundle.putBoolean("SHOW_PRICE_DIALOGUE", true);
                                        bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                        GaanaActivity.this.mFragment.setArguments(bundle);
                                        GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                    }
                                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                                break;
                            case R.id.LeftMenuOperatorPurchaseResponseSuccess /*2131296299*/:
                                PurchaseOperatorManager.a((Context) this).a(str, PurchaseOperatorManager.PaymentResponse.SUCCESS);
                                break;
                            case R.id.LeftMenuPayUPurchaseResponseFailure /*2131296300*/:
                                new ac(this).d();
                                break;
                            case R.id.LeftMenuPayUPurchaseResponseSuccess /*2131296301*/:
                                new ac(this).c();
                                break;
                            case R.id.LeftMenuPaypalPurchaseResponseFailure /*2131296302*/:
                                PurchasePaypalManager.a((Context) this).a(str, PurchasePaypalManager.PaymentResponse.FAILURE);
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        GaanaActivity.this.mFragment = new SettingsDetailFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("KEY_SETTINGS", 1);
                                        bundle.putBoolean("SHOW_PRICE_DIALOGUE", true);
                                        bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                        GaanaActivity.this.mFragment.setArguments(bundle);
                                        GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                    }
                                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
                                break;
                            case R.id.LeftMenuPaypalPurchaseResponseSuccess /*2131296303*/:
                                PurchasePaypalManager.a((Context) this).a(str, PurchasePaypalManager.PaymentResponse.SUCCESS);
                                break;
                            case R.id.LeftMenuPurchaseCoupon /*2131296306*/:
                                if (!TextUtils.isEmpty(str) && str2.contains("gcm_coupon")) {
                                    b = str2.split("=")[1];
                                    this.mFragment = new SettingsDetailFragment();
                                    Bundle bundle3 = new Bundle();
                                    bundle3.putInt("KEY_SETTINGS", 1);
                                    bundle3.putBoolean("LAUNCH_GAANA_PLUS", true);
                                    bundle3.putString("item_id", str);
                                    bundle3.putString("purchase_coupon_code", b);
                                    this.mFragment.setArguments(bundle3);
                                    displayFragment(this.mFragment);
                                    break;
                                }
                            case R.id.LeftMenuRadio /*2131296307*/:
                                this.mFragment = new MoreRadioActivityFragment();
                                bundle2 = new Bundle();
                                bundle2.putInt("DEEPLINKING_SCREEN", i);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                this.fragmentTag = "radio";
                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_RADIO);
                                this.mFragment.setArguments(bundle2);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LeftMenuReferFriend /*2131296308*/:
                                checkSetLoginStatus(new ad() {
                                    public void onLoginSuccess() {
                                        if (!(GaanaActivity.this.mFragment instanceof ReferralScreenFragment)) {
                                            GaanaActivity.this.mFragment = new ReferralScreenFragment();
                                            GaanaActivity.this.mFragment.setArguments(new Bundle());
                                            GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                                        }
                                    }
                                }, this.mContext.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_REFERRALS));
                                break;
                            case R.id.LeftMenuRewards /*2131296309*/:
                                if (!this.mAppState.isAppInOfflineMode()) {
                                    if (Util.j(this.mContext)) {
                                        setGoogleAnalyticsScreenName("GaanaRewardsScreen");
                                        intent = new Intent(this, WebViewActivity.class);
                                        str = "https://gaana.com/rewarddetails/";
                                        if (this.mAppState.getCurrentUser().getLoginStatus()) {
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append(str);
                                            stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
                                            str = stringBuilder.toString();
                                        }
                                        intent.putExtra("EXTRA_WEBVIEW_URL", str);
                                        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                                        intent.putExtra("title", "Gaana Extras");
                                        startActivity(intent);
                                        break;
                                    }
                                    ap.a().f(this.mContext);
                                    return;
                                }
                                displayFeatureNotAvailableOfflineDialog("");
                                return;
                            case R.id.LeftMenuSettings /*2131296310*/:
                                if (!(this.mFragment instanceof SettingsFragment)) {
                                    this.mFragment = new SettingsFragment();
                                    displayFragment(this.mFragment);
                                    break;
                                }
                                break;
                            case R.id.LeftMenuStudentVerifyPurchaseResponseFailure /*2131296311*/:
                                this.bundle = new Bundle();
                                this.bundle.putInt("KEY_SETTINGS", 1);
                                this.bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
                                this.bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                this.mFragment = new SettingsDetailFragment();
                                this.mFragment.setArguments(this.bundle);
                                displayFragment(this.mFragment);
                                break;
                            case R.id.LeftMenuStudentVerifyPurchaseResponseSuccess /*2131296312*/:
                                ag.a((Context) this).b((Context) this);
                                break;
                            case R.id.LeftMenuVideoFeed /*2131296313*/:
                                aa.a().a(this.mContext);
                                ((GaanaActivity) this.mContext).changeFragment(R.id.LeftMenuNotifications, null, null);
                                break;
                            case R.id.LeftMenuWebView /*2131296314*/:
                                if (!TextUtils.isEmpty(str)) {
                                    intent = new Intent(this, WebViewActivity.class);
                                    intent.putExtra("EXTRA_WEBVIEW_URL", str);
                                    if (TextUtils.isEmpty(str2)) {
                                        intent.putExtra("title", "Gaana");
                                    } else {
                                        intent.putExtra("title", str2);
                                    }
                                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                                    startActivity(intent);
                                    break;
                                }
                                break;
                            case R.id.LeftPartyHub /*2131296315*/:
                                if (!(this.mFragment instanceof PartyFragment)) {
                                    this.mFragment = new PartyFragment();
                                    displayFragment(this.mFragment);
                                    break;
                                }
                                break;
                            case R.id.LeftSongLanguage /*2131296316*/:
                                bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 14);
                                settingsDetailFragment = new SettingsDetailFragment();
                                settingsDetailFragment.setArguments(bundle);
                                displayFragment(settingsDetailFragment);
                                break;
                            case R.id.LeftTheme /*2131296317*/:
                                bundle = new Bundle();
                                bundle.putInt("KEY_SETTINGS", 141);
                                settingsDetailFragment = new SettingsDetailFragment();
                                settingsDetailFragment.setArguments(bundle);
                                ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
                                break;
                            default:
                                switch (i) {
                                    case R.id.MyMusicFavorites /*2131296320*/:
                                        if (!GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                                            if (!Util.j(this.mContext)) {
                                                aj.a().a(this.mContext, getString(R.string.error_msg_no_connection));
                                                break;
                                            }
                                            displayLaunchFragment(SHOW_TAB_HOME, null);
                                            com.managers.af.a(this.mContext, null).a(i, null, this.mContext.getResources().getString(R.string.login_bottom_sheet_sections_my_music), new com.services.l.m() {
                                                public void onResponse(boolean z) {
                                                    GaanaActivity.this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.FAVORITES.name());
                                                    GaanaActivity.this.fragmentTag = "mymusic";
                                                    GaanaActivity.this.mBottomNavigationBarHelper.a(GaanaActivity.this.bottomNavigationView, GaanaActivity.SHOW_TAB_MYMUSIC);
                                                    BaseGaanaFragment favoritesFragment = new FavoritesFragment();
                                                    favoritesFragment.setArguments(new Bundle());
                                                    GaanaActivity.this.displayFragment(favoritesFragment);
                                                }
                                            });
                                            break;
                                        }
                                        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.FAVORITES.name());
                                        this.fragmentTag = "mymusic";
                                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                        BaseGaanaFragment favoritesFragment = new FavoritesFragment();
                                        favoritesFragment.setArguments(new Bundle());
                                        displayFragment(favoritesFragment);
                                        break;
                                    case R.id.MyMusicMenuAlbums /*2131296321*/:
                                        this.mFragment = new MyMusicItemFragment();
                                        this.fragmentTag = "mymusic";
                                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                        bundle2 = new Bundle();
                                        bundle2.putInt("DEEPLINKING_SCREEN", i);
                                        bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                        bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                        bundle2.putString("DEEPLINKING_SCREEN_SORT_ORDER", SortOrder.Default.name());
                                        bundle2.putSerializable("obj_type", BusinessObjectType.Albums);
                                        this.mFragment.setArguments(bundle2);
                                        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name());
                                        u.a().b("MyMusicScreen", "Albums Click");
                                        displayFragment(this.mFragment);
                                        break;
                                    case R.id.MyMusicMenuArtists /*2131296322*/:
                                        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_ARTIST.name());
                                        u.a().b("MyMusicScreen", "Artists Click");
                                        this.fragmentTag = "mymusic";
                                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                        openFavoriteArtist(this.mFragment);
                                        break;
                                    case R.id.MyMusicMenuDownloads /*2131296323*/:
                                        this.fragmentTag = "mymusic";
                                        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                        if (!this.mAppState.getCurrentUser().getLoginStatus()) {
                                            if (!(this.mFragment instanceof SettingsDetailFragment)) {
                                                bundle = new Bundle();
                                                bundle.putInt("KEY_SETTINGS", 1);
                                                bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
                                                bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                                                this.mFragment = new SettingsDetailFragment();
                                                this.mFragment.setArguments(bundle);
                                                displayFragment(this.mFragment);
                                                break;
                                            }
                                        }
                                        displayDownload(i, str, str2);
                                        break;
                                        break;
                                    default:
                                        switch (i) {
                                            case R.id.MyMusicMenuPhoneMusic /*2131296325*/:
                                                if (checkForWriteStoragePermission()) {
                                                    this.fragmentTag = "mymusic";
                                                    this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                                    if (LocalMediaManager.getInstance(this).getLocalTrackCounts() != 0) {
                                                        if (!(this.mFragment instanceof LocalMediaFragment)) {
                                                            this.mFragment = new LocalMediaFragment();
                                                            this.mFragment.setArguments(new Bundle());
                                                            displayFragment(this.mFragment);
                                                            break;
                                                        }
                                                    }
                                                    this.mFragment = new DownloadHomeFragment();
                                                    bundle = new Bundle();
                                                    bundle.putInt("recommended_page_type", 6);
                                                    this.mFragment.setArguments(bundle);
                                                    displayFragment(this.mFragment);
                                                    break;
                                                }
                                                return;
                                                break;
                                            case R.id.MyMusicMenuPlaylists /*2131296326*/:
                                                this.mFragment = new MyMusicItemFragment();
                                                this.fragmentTag = "mymusic";
                                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                                bundle2 = new Bundle();
                                                bundle2.putInt("DEEPLINKING_SCREEN", i);
                                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                                bundle2.putString("DEEPLINKING_SCREEN_SORT_ORDER", SortOrder.Default.name());
                                                bundle2.putSerializable("obj_type", BusinessObjectType.Playlists);
                                                this.mFragment.setArguments(bundle2);
                                                this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_PLAYLIST.name());
                                                u.a().b("MyMusicScreen", "Playlist Click");
                                                displayFragment(this.mFragment);
                                                break;
                                            case R.id.MyMusicMenuRadios /*2131296327*/:
                                                this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_RADIO.name());
                                                u.a().b("MyMusicScreen", "Radio Click");
                                                this.fragmentTag = "mymusic";
                                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                                openFavoriteRadios(this.mFragment);
                                                break;
                                            case R.id.MyMusicMenuSongs /*2131296328*/:
                                                this.mFragment = new MyMusicItemFragment();
                                                this.fragmentTag = "mymusic";
                                                this.mBottomNavigationBarHelper.a(this.bottomNavigationView, SHOW_TAB_MYMUSIC);
                                                bundle2 = new Bundle();
                                                bundle2.putInt("DEEPLINKING_SCREEN", i);
                                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                                bundle2.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
                                                bundle2.putString("DEEPLINKING_SCREEN_SORT_ORDER", SortOrder.Default.name());
                                                bundle2.putSerializable("obj_type", BusinessObjectType.Tracks);
                                                this.mFragment.setArguments(bundle2);
                                                this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name());
                                                u.a().b("MyMusicScreen", "Songs Click");
                                                displayFragment(this.mFragment);
                                                break;
                                            default:
                                                switch (i) {
                                                    case R.id.TopTabSearch /*2131296339*/:
                                                        if (!(this.mFragment instanceof SearchEnchancedFragment)) {
                                                            this.mFragment = new SearchEnchancedFragment();
                                                            bundle = new Bundle();
                                                            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("isTrending")) {
                                                                bundle.putBoolean("IS_TRENDING", true);
                                                            }
                                                            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("isFromVoiceSearch")) {
                                                                bundle.putBoolean("isFromVoiceSearch", true);
                                                            }
                                                            this.mFragment.setArguments(bundle);
                                                            clearStackForSearch();
                                                            displayFragment(this.mFragment);
                                                            break;
                                                        }
                                                        break;
                                                    case R.id.VoiceSearchResult /*2131296340*/:
                                                        if (!(this.mFragment instanceof SearchEnchancedFragment)) {
                                                            this.mFragment = new SearchEnchancedFragment();
                                                            bundle = new Bundle();
                                                            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
                                                            this.mFragment.setArguments(bundle);
                                                            clearStackForSearch();
                                                            displayFragment(this.mFragment);
                                                            break;
                                                        }
                                                        GaanaSearchManager.a().b(this.mContext, str);
                                                        break;
                                                }
                                                break;
                                        }
                                }
                        }
                        break;
                }
            } else {
                this.bundle = new Bundle();
                this.bundle.putInt("KEY_SETTINGS", 1);
                this.bundle.putBoolean("SHOW_PRICE_DIALOGUE", false);
                this.bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                this.mFragment = new SettingsDetailFragment();
                this.mFragment.setArguments(this.bundle);
                displayFragment(this.mFragment);
            }
        } else if (!(this.mFragment instanceof GaanaEducativeFragment) && ((ap.a().d() || ap.a().l()) && d.a().b("PREFERENCE_EDUCATIVE_SCREEN", false, false) && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && !this.mAppState.getCurrentUser().getUserSubscriptionData().isEducate_notify())) {
            this.mFragment = new GaanaEducativeFragment();
            displayFragment(this.mFragment);
            Util.ah();
        }
        setUpNavMenulist();
    }

    private void handleStudentPackVerification() {
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice.php?type=duration_listing");
        stringBuilder.append(ag.a(this.mContext).d());
        String stringBuilder2 = stringBuilder.toString();
        if (currentUser != null && currentUser.getLoginStatus()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder.toString();
        }
        showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(PaymentProductModel.class);
        uRLManager.b(Boolean.valueOf(false));
        com.i.i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                GaanaActivity.this.hideProgressDialog();
                if (obj instanceof PaymentProductModel) {
                    PaymentProductModel paymentProductModel = (PaymentProductModel) obj;
                    if (paymentProductModel.getPurchase() != null) {
                        Iterator it = paymentProductModel.getPurchase().getProducts().iterator();
                        int i = -1;
                        while (it.hasNext()) {
                            ProductItem productItem = (ProductItem) it.next();
                            i++;
                            if (!(productItem == null || productItem.getPlanType() == null || !productItem.getPlanType().equalsIgnoreCase("1"))) {
                                GaanaActivity.this.IS_STUDENT_PACK_AVAILABLE = true;
                                ag a = ag.a(GaanaActivity.this.mContext);
                                a.a(productItem);
                                a.a(i);
                                if (!(paymentProductModel.getPageHeader() == null || paymentProductModel.getPageHeader().getPageHeaderConfig() == null)) {
                                    a.a(paymentProductModel.getPageHeader().getPageHeaderConfig());
                                }
                                Util.j(GaanaActivity.this.mContext, productItem.getP_id());
                            }
                        }
                        if (!GaanaActivity.this.IS_STUDENT_PACK_AVAILABLE) {
                            GaanaActivity.this.changeFragment(R.id.LeftMenuPurchase, null, null);
                            aj.a().a(GaanaActivity.this, GaanaActivity.this.getString(R.string.pack_notavailable_text));
                        }
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                GaanaActivity.this.hideProgressDialog();
                GaanaActivity.this.IS_STUDENT_PACK_AVAILABLE = false;
            }
        }, uRLManager);
        aj.a().a(this, "Deeplinking Is started");
    }

    private void initDirectPayment(String str, String str2) {
        String str3 = "https://api.gaana.com/gaanaplusservice.php?type=product_detail&item_id=<item_id>&p_id=<p_id>";
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str3 = stringBuilder.toString();
        }
        str = str3.replace("<item_id>", str).replace("<p_id>", str2);
        checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                GaanaActivity.this.showProgressDialog(Boolean.valueOf(true), GaanaActivity.this.getString(R.string.fetching_gaana_plus_product));
                URLManager uRLManager = new URLManager();
                uRLManager.a(str);
                uRLManager.a(PaymentProductModel.class);
                com.i.i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                        GaanaActivity.this.hideProgressDialog();
                        final PaymentProductModel paymentProductModel = (PaymentProductModel) obj;
                        if (paymentProductModel != null && paymentProductModel.getDeepLinkingProduct() != null) {
                            ag.a(GaanaActivity.this.mContext).a(GaanaActivity.this.mContext, paymentProductModel.getDeepLinkingProduct(), new ag.a() {
                                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                                    ag.a(GaanaActivity.this.mContext).a("", "", "success");
                                    String p_payment_mode = paymentProductModel.getDeepLinkingProduct().getP_payment_mode();
                                    GaanaActivity.this.updateUserStatus(new au() {
                                        public void onUserStatusUpdated() {
                                            GaanaActivity.this.hideProgressDialog();
                                            ap.a().a(GaanaActivity.this.mContext);
                                            Util.aa();
                                            aj.a().a(GaanaActivity.this.mContext, GaanaActivity.this.getResources().getString(R.string.enjoy_using_gaana_plus));
                                            Intent intent = new Intent(GaanaActivity.this.mContext, GaanaActivity.class);
                                            intent.setFlags(71303168);
                                            GaanaActivity.this.mContext.startActivity(intent);
                                        }
                                    });
                                    if (Util.s() != null && !TextUtils.isEmpty(p_payment_mode)) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("Success; ");
                                        stringBuilder.append(Util.s());
                                        u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                                    }
                                }

                                public void onFailure(String str, String str2) {
                                    ag.a(GaanaActivity.this.mContext).a(str, "", str2);
                                    if (!TextUtils.isEmpty(str)) {
                                        aj.a().a(GaanaActivity.this.mContext, str);
                                    }
                                    if (paymentProductModel != null && paymentProductModel.getDeepLinkingProduct() != null) {
                                        str = paymentProductModel.getDeepLinkingProduct().getP_payment_mode();
                                        if (Util.s() != null && !TextUtils.isEmpty(str)) {
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("Failure; ");
                                            stringBuilder.append(Util.s());
                                            u.a().a("Payment_Mode", str, stringBuilder.toString());
                                        }
                                    }
                                }
                            }, paymentProductModel.getDeepLinkingProduct().getItem_id(), paymentProductModel.getDeepLinkingProduct().getDesc());
                        }
                    }
                }, uRLManager);
            }
        }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
    }

    public void friendsActivity() {
        if (!(this.mFragment instanceof ActivityFeedActivityFragment)) {
            checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    if (GaanaActivity.this.mAppState.getCurrentUser().getLoginStatus()) {
                        GaanaActivity.this.mFragment = new ActivityFeedActivityFragment();
                        GaanaActivity.this.mFragment.setArguments(new Bundle());
                        GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                    }
                }
            }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FRIENDS_ACTIVITY));
        }
    }

    public void displayProfile() {
        checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (!(GaanaActivity.this.mFragment instanceof ProfileFragment)) {
                    GaanaActivity.this.mFragment = new ProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("EXTRA_PROFILE_ORIGIN_MYPROFILE", "MYPROFILE");
                    GaanaActivity.this.mFragment.setArguments(bundle);
                    GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                }
            }
        }, null, true);
    }

    public void displayDownload(int i, String str, String str2) {
        displayDownload(i, str, str2, SortOrder.Default, null);
    }

    public void showSmartDownloadToastMessage(final String str, final Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.smart_download_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.action_button);
        CharSequence charSequence = Constants.bF;
        if (str.equals("settings")) {
            textView.setText(context.getResources().getString(R.string.settings));
        } else if (str.equals("gotit")) {
            if (!TextUtils.isEmpty(Constants.bK)) {
                textView.setText(Constants.bK.toUpperCase());
            }
            charSequence = Constants.bL;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            ((TextView) inflate.findViewById(R.id.description)).setText(charSequence);
        }
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GaanaActivity.this.dialogSmartDownload.dismiss();
                if (str.equals("settings")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) context).displayFragment(settingsDetailFragment);
                    return;
                }
                str.equals("gotit");
            }
        });
        this.dialogSmartDownload = new Dialog(context, R.style.DialogCustomTheme);
        this.dialogSmartDownload.setContentView(inflate);
        Window window = this.dialogSmartDownload.getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setGravity(80);
        window.getAttributes().y = 100;
        this.dialogSmartDownload.show();
    }

    public void displayDownload(int i, String str, String str2, SortOrder sortOrder, String str3) {
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final SortOrder sortOrder2 = sortOrder;
        final String str6 = str3;
        checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (DownloadManager.c().q() == 0) {
                    if (!(GaanaActivity.this.mFragment instanceof DownloadHomeFragment)) {
                        GaanaActivity.this.mFragment = new DownloadHomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("recommended_page_type", 5);
                        GaanaActivity.this.mFragment.setArguments(bundle);
                        GaanaActivity.this.displayFragment(GaanaActivity.this.mFragment);
                    }
                } else if (!(GaanaActivity.this.mFragment instanceof DownloadDetailsFragment)) {
                    GaanaActivity.this.displayDownloadFragment(i2, str4, str5, sortOrder2, str6);
                } else if (GaanaActivity.this.isPlayerExpanded()) {
                    ((GaanaActivity) GaanaActivity.this.mContext).popBackStackImmediate();
                }
            }
        }, null);
    }

    private void displayDownloadFragment(int i, String str, String str2, SortOrder sortOrder, String str3) {
        final Bundle bundle = new Bundle();
        bundle.putInt("DEEPLINKING_SCREEN", i);
        bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
        bundle.putString("DEEPLINKING_SCREEN_SORT_ORDER", sortOrder.name());
        bundle.putString("EXTRA_PARAM_FILTER", str3);
        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.DOWNLOADS.name());
        u.a().b("MyMusicScreen", "Downloads Click");
        Object obj = 1;
        if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            i = DownloadManager.c().B();
            int p = DownloadManager.c().p();
            if (i > 0 || p > 0 || ap.a().d()) {
                obj = null;
            }
        }
        if (obj != null) {
            Util.b(this.mContext, "My_Download_section", new as() {
                public void onTrialSuccess() {
                    BaseGaanaFragment downloadDetailsFragment = new DownloadDetailsFragment();
                    downloadDetailsFragment.setArguments(bundle);
                    GaanaActivity.this.displayFragment(downloadDetailsFragment);
                    GaanaActivity.this.updateSideBar();
                }
            });
            return;
        }
        BaseGaanaFragment downloadDetailsFragment = new DownloadDetailsFragment();
        downloadDetailsFragment.setArguments(bundle);
        displayFragment(downloadDetailsFragment);
    }

    public void updateNavigationListView() {
        setUpNavMenulist();
        if (this.mNavigationHeaderMenu != null) {
            this.mNavigationHeaderMenu.updateLoginBar();
        }
    }

    public void updateSidebarUserDetails() {
        UserInfo currentUser = this.mAppState.getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null)) {
            ((CrossFadeImageView) findViewById(R.id.imgUser)).bindImage(currentUser.getUserProfile().getImg());
            ((TextView) findViewById(R.id.userName)).setText(currentUser.getUserProfile().getFullname());
        }
        if (this.mNavigationHeaderMenu != null) {
            this.mNavigationHeaderMenu.updateLoginBar();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        Log.d("PlaylistUpdate", "0");
        intentFilter.addAction("broadcast_playlist_update_status");
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.broadcastReceiverPlaylistStatusUpdate);
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).registerReceiver(this.broadcastReceiverPlaylistStatusUpdate, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("broadcast_intent_download_service_freedom_user_info");
        intentFilter.addAction("broadcast_intent_download_service_mini_pack_info");
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).registerReceiver(this.broadcastReceiverFreedomUserInfo, intentFilter);
        Util.a((Util.b) this);
        InAppManager.getInstance().setInAppListener(this);
        if (ap.a().b(this.mContext) || !(this.mAppState.getCurrentUser().getUserSubscriptionData() == null || this.mAppState.getCurrentUser() == null || this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != 2)) {
            ColombiaManager.b().a(this.mContext);
        }
        refreshSocialAnsSsoTicket(new at() {
            public void onUserRefreshed() {
                if (GaanaActivity.this.mNavigationHeaderMenu != null) {
                    GaanaActivity.this.mNavigationHeaderMenu.updateLoginBar();
                }
            }
        });
        registerReceiver(this.mNetworkChangeBroadCastReceiver, new IntentFilter(com.til.colombia.android.internal.d.a));
        FileDownloadService.a(new bd() {
            public void showAnimationToMyMusic() {
                GaanaActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        GaanaActivity.this.showAnimationToMyMusic();
                    }
                });
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!GaanaMusicService.k() && GaanaMusicService.n() == null) {
            if (this.companionAdSlot != null) {
                removeCompanionAdSlot(this.companionAdSlot);
            }
            if (this.overlayExoview != null) {
                removeVideoView(this.overlayExoview);
            }
        }
        if (intent.getBooleanExtra("removePaymentScreen", false) && (getCurrentFragment() instanceof SettingsDetailFragment)) {
            popBackStack();
        }
        int intExtra = intent.getIntExtra("DEEPLINKING_SCREEN", -1);
        String stringExtra = intent.getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM");
        String stringExtra2 = intent.getStringExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2");
        if (intent.getBooleanExtra("LAUNCH_FROM_DEEPLINK", false)) {
            if (intExtra != -1) {
                changeFragment(intExtra, stringExtra, stringExtra2);
            } else {
                handleDeeplinkingRequest(intent.getExtras());
            }
        }
        if (this.mNavigationHeaderMenu != null) {
            this.mNavigationHeaderMenu.updateLoginBar();
        }
        if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
            PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
        }
        if (!com.managers.f.v().w() && !com.managers.f.v().t()) {
            hideFakePlayer();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        if (this.backgroundAdSlot != null) {
            this.backgroundAdSlot.setVisibility(8);
        }
        ai.a(null);
        DownloadManager.c().a(null);
        Util.a(null);
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.mChromeCastReceiver);
        if (this.mVoiceRecognitionDialog != null && this.mVoiceRecognitionDialog.isShowing()) {
            this.mVoiceRecognitionDialog.dismiss();
        }
        if (GaanaMusicService.k() && ((com.managers.f.v().s() || com.managers.f.v().t()) && GaanaMusicService.p() != null)) {
            skipForegroundAudioAd();
        } else if (com.managers.f.v().s()) {
            if (GaanaMusicService.s() instanceof com.player_framework.c) {
                ((com.player_framework.c) GaanaMusicService.s()).B();
            }
            com.managers.f.v().f(false);
        }
    }

    private void skipForegroundAudioAd() {
        f p = GaanaMusicService.p();
        f r = GaanaMusicService.r();
        if (r != null && (r instanceof com.player_framework.c) && GaanaMusicService.p().equals(r)) {
            if (!(r.m() || r.isPlaying())) {
                ((com.player_framework.c) r).A();
                r.q();
                r.setVolume(0.0f, 0.0f);
                r.b(false);
            }
            r.setVolume(1.0f, 1.0f);
            ((com.player_framework.c) GaanaMusicService.s()).A();
        } else if (p != null && GaanaMusicService.p().equals(GaanaMusicService.s())) {
            GaanaMusicService.s().q();
            ((com.player_framework.c) GaanaMusicService.r()).B();
        }
        GaanaMusicService.q();
        com.managers.f.v().f(false);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x028b  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x028b  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x028b  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02ec  */
    public void onResume() {
        /*
        r6 = this;
        r0 = com.managers.DownloadManager.c();
        r0 = r0.J();
        if (r0 == 0) goto L_0x000d;
    L_0x000a:
        r6.showSmartDownloadsCompleteNotification();
    L_0x000d:
        r0 = r6.isSmartDownloadNotificationPending;
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r6.showSmartDownloadNotification();
    L_0x0014:
        r0 = r6.bottomNavigationView;
        r0.getSelectedItemId();
        r6.mContext = r6;
        com.managers.ai.a(r6);
        r0 = r6.mDeviceResManager;
        r1 = "UPDATE_DISP_LANG";
        r2 = 0;
        r3 = 1;
        r0 = r0.b(r1, r2, r3);
        if (r0 == 0) goto L_0x0045;
    L_0x002a:
        r1 = com.managers.aj.a();
        r2 = r6.mContext;
        r4 = r6.mContext;
        r4 = r4.getResources();
        r5 = 2131822716; // 0x7f11087c float:1.9278211E38 double:1.053260367E-314;
        r4 = r4.getString(r5);
        r5 = new com.gaana.GaanaActivity$56;
        r5.<init>(r0);
        r1.a(r2, r0, r4, r5);
    L_0x0045:
        r6.handleNoInternetLayout();
        r0 = r6.getOverlayFrameLayout();
        if (r0 == 0) goto L_0x0065;
    L_0x004e:
        r0 = r6.getOverlayFrameLayout();
        r0 = r0.getVisibility();
        if (r0 != 0) goto L_0x0065;
    L_0x0058:
        r0 = com.player_framework.GaanaMusicService.n();
        if (r0 == 0) goto L_0x0065;
    L_0x005e:
        r0 = com.player_framework.GaanaMusicService.n();
        r0.a();
    L_0x0065:
        r0 = com.managers.f.v();
        r0 = r0.w();
        r1 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r2 = 0;
        if (r0 == 0) goto L_0x00ab;
    L_0x0072:
        r0 = com.services.d.a();
        r4 = "PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS";
        r0.a(r4, r2, r2);
        r0 = r6.getIntent();
        if (r0 == 0) goto L_0x012c;
    L_0x0081:
        r0 = com.gaana.AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER;
        if (r0 == 0) goto L_0x0097;
    L_0x0085:
        r0 = com.gaana.AudioAdActivity.LAUNCH_PURCHASE_SCREEN;
        r6.showFakePlayer(r0);
        r0 = com.managers.f.v();
        r0.h(r3);
        com.gaana.AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = r2;
        com.gaana.AudioAdActivity.LAUNCH_PURCHASE_SCREEN = r2;
        goto L_0x012c;
    L_0x0097:
        r0 = new android.content.Intent;
        r4 = r6.getApplicationContext();
        r5 = com.gaana.AudioAdActivity.class;
        r0.<init>(r4, r5);
        r0.setFlags(r1);
        r6.startActivity(r0);
    L_0x00a8:
        r0 = r3;
        goto L_0x012d;
    L_0x00ab:
        r0 = com.services.d.a();
        r4 = "PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS";
        r0 = r0.b(r4, r2, r2);
        if (r0 == 0) goto L_0x00d9;
    L_0x00b7:
        r0 = com.managers.f.v();
        r0 = r0.n();
        r4 = "1";
        r0 = r0.equalsIgnoreCase(r4);
        if (r0 == 0) goto L_0x00d9;
    L_0x00c7:
        r0 = new android.content.Intent;
        r4 = r6.getApplicationContext();
        r5 = com.gaana.BannerAdActivity.class;
        r0.<init>(r4, r5);
        r0.setFlags(r1);
        r6.startActivity(r0);
        goto L_0x012c;
    L_0x00d9:
        r0 = com.managers.f.v();
        r0 = r0.t();
        if (r0 == 0) goto L_0x0120;
    L_0x00e3:
        r0 = com.player_framework.GaanaMusicService.k();
        if (r0 == 0) goto L_0x00f3;
    L_0x00e9:
        r0 = r6.backgroundAdSlot;
        if (r0 == 0) goto L_0x00f3;
    L_0x00ed:
        r0 = r6.backgroundAdSlot;
        r0.setVisibility(r2);
        goto L_0x012c;
    L_0x00f3:
        r0 = r6.getIntent();
        if (r0 == 0) goto L_0x012c;
    L_0x00f9:
        r0 = com.gaana.AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER;
        if (r0 == 0) goto L_0x010e;
    L_0x00fd:
        r0 = com.gaana.AudioAdActivity.LAUNCH_PURCHASE_SCREEN;
        r6.showFakePlayer(r0);
        r0 = com.managers.f.v();
        r0.h(r3);
        com.gaana.AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = r2;
        com.gaana.AudioAdActivity.LAUNCH_PURCHASE_SCREEN = r2;
        goto L_0x012c;
    L_0x010e:
        r0 = new android.content.Intent;
        r4 = r6.getApplicationContext();
        r5 = com.gaana.AudioAdActivity.class;
        r0.<init>(r4, r5);
        r0.setFlags(r1);
        r6.startActivity(r0);
        goto L_0x00a8;
    L_0x0120:
        r6.hideFakePlayer();
        r0 = com.services.d.a();
        r1 = "PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS";
        r0.a(r1, r2, r2);
    L_0x012c:
        r0 = r2;
    L_0x012d:
        r1 = com.managers.f.v();
        r1 = r1.t();
        r4 = 8;
        if (r1 != 0) goto L_0x0142;
    L_0x0139:
        r1 = r6.backgroundAdSlot;
        if (r1 == 0) goto L_0x0142;
    L_0x013d:
        r1 = r6.backgroundAdSlot;
        r1.setVisibility(r4);
    L_0x0142:
        r1 = com.player_framework.GaanaMusicService.k();
        if (r1 != 0) goto L_0x015f;
    L_0x0148:
        r1 = com.player_framework.GaanaMusicService.n();
        if (r1 != 0) goto L_0x015f;
    L_0x014e:
        r1 = r6.backgroundAdSlot;
        if (r1 == 0) goto L_0x0157;
    L_0x0152:
        r1 = r6.backgroundAdSlot;
        r1.setVisibility(r4);
    L_0x0157:
        r1 = com.managers.f.v();
        r1.e(r2);
        goto L_0x0181;
    L_0x015f:
        r1 = com.player_framework.GaanaMusicService.k();
        if (r1 == 0) goto L_0x0181;
    L_0x0165:
        r1 = com.player_framework.GaanaMusicService.n();
        if (r1 != 0) goto L_0x0181;
    L_0x016b:
        r1 = com.player_framework.GaanaMusicService.o();
        if (r1 != 0) goto L_0x0181;
    L_0x0171:
        r1 = r6.backgroundAdSlot;
        if (r1 == 0) goto L_0x017a;
    L_0x0175:
        r1 = r6.backgroundAdSlot;
        r1.setVisibility(r4);
    L_0x017a:
        r1 = com.managers.f.v();
        r1.e(r2);
    L_0x0181:
        r1 = com.player_framework.GaanaMusicService.l();
        if (r1 != 0) goto L_0x01a1;
    L_0x0187:
        r1 = r6.getOverlayFrameLayout();
        if (r1 == 0) goto L_0x0194;
    L_0x018d:
        r1 = r6.getOverlayFrameLayout();
        r1.setVisibility(r4);
    L_0x0194:
        r1 = r6.getCompanionAdSlot();
        if (r1 == 0) goto L_0x01a1;
    L_0x019a:
        r1 = r6.getCompanionAdSlot();
        r1.setVisibility(r4);
    L_0x01a1:
        if (r0 != 0) goto L_0x0264;
    L_0x01a3:
        r1 = com.managers.ap.a();
        r4 = r6.mContext;
        r1 = r1.b(r4);
        if (r1 == 0) goto L_0x0264;
    L_0x01af:
        r1 = com.managers.f.v();
        r1 = r1.b();
        if (r1 == 0) goto L_0x0264;
    L_0x01b9:
        r0 = com.managers.f.v();
        r0 = r0.a();
        if (r0 == 0) goto L_0x02c3;
    L_0x01c3:
        r1 = r0.d();
        r0 = r0.e();
        r4 = com.constants.Constants.WebLaunchFLag.InAppBrowser;
        r4 = r4.ordinal();
        r4 = java.lang.String.valueOf(r4);
        r4 = r4.equalsIgnoreCase(r1);
        if (r4 == 0) goto L_0x01e0;
    L_0x01db:
        com.utilities.Util.a(r0, r2, r6);
        goto L_0x025c;
    L_0x01e0:
        r4 = com.constants.Constants.WebLaunchFLag.ExternalBrowser;
        r4 = r4.ordinal();
        r4 = java.lang.String.valueOf(r4);
        r4 = r4.equalsIgnoreCase(r1);
        r5 = 2131821246; // 0x7f1102be float:1.927523E38 double:1.0532596407E-314;
        if (r4 == 0) goto L_0x021a;
    L_0x01f3:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x025c;
    L_0x01f9:
        r1 = android.webkit.URLUtil.isValidUrl(r0);
        if (r1 == 0) goto L_0x025c;
    L_0x01ff:
        r1 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x020e }
        r4 = "android.intent.action.VIEW";
        r0 = android.net.Uri.parse(r0);	 Catch:{ ActivityNotFoundException -> 0x020e }
        r1.<init>(r4, r0);	 Catch:{ ActivityNotFoundException -> 0x020e }
        r6.startActivity(r1);	 Catch:{ ActivityNotFoundException -> 0x020e }
        goto L_0x025c;
    L_0x020e:
        r0 = r6.getString(r5);
        r0 = android.widget.Toast.makeText(r6, r0, r3);
        r0.show();
        goto L_0x025c;
    L_0x021a:
        r4 = com.constants.Constants.WebLaunchFLag.Deeplink;
        r4 = r4.ordinal();
        r4 = java.lang.String.valueOf(r4);
        r1 = r4.equalsIgnoreCase(r1);
        if (r1 == 0) goto L_0x0236;
    L_0x022a:
        r1 = com.services.c.a(r6);
        r4 = com.gaana.application.GaanaApplication.getInstance();
        r1.a(r6, r0, r4);
        goto L_0x025c;
    L_0x0236:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x025c;
    L_0x023c:
        r1 = android.webkit.URLUtil.isValidUrl(r0);
        if (r1 == 0) goto L_0x025c;
    L_0x0242:
        r1 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x0251 }
        r4 = "android.intent.action.VIEW";
        r0 = android.net.Uri.parse(r0);	 Catch:{ ActivityNotFoundException -> 0x0251 }
        r1.<init>(r4, r0);	 Catch:{ ActivityNotFoundException -> 0x0251 }
        r6.startActivity(r1);	 Catch:{ ActivityNotFoundException -> 0x0251 }
        goto L_0x025c;
    L_0x0251:
        r0 = r6.getString(r5);
        r0 = android.widget.Toast.makeText(r6, r0, r3);
        r0.show();
    L_0x025c:
        r0 = com.managers.f.v();
        r0.a(r2);
        goto L_0x02c3;
    L_0x0264:
        if (r0 != 0) goto L_0x02c3;
    L_0x0266:
        r0 = com.managers.ap.a();
        r1 = r6.mContext;
        r0 = r0.b(r1);
        if (r0 == 0) goto L_0x02c3;
    L_0x0272:
        r0 = com.managers.f.v();
        r0 = r0.f();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x02c3;
    L_0x0280:
        r0 = new com.gaana.ads.interstitial.LoadAlwaysInterstitialBehaviour;
        r0.<init>();
        r1 = r0.whenToLoad();
        if (r1 == 0) goto L_0x02c3;
    L_0x028b:
        r1 = new com.gaana.ads.interstitial.InterstitialAdRequest;
        r1.<init>();
        r2 = new com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
        r4 = r6.mContext;
        r2.<init>(r4);
        r1 = r1.buildPublisherInterstitialAd(r2);
        r2 = new com.gaana.ads.interstitial.ShowAlwaysInterstitialBehaviour;
        r2.<init>();
        r1 = r1.buildInterstitialShowBehaviour(r2);
        r0 = r1.buildInterstitialLoadBehaviour(r0);
        r1 = r6.getLocation();
        r0 = r0.buildLocation(r1);
        r1 = com.managers.f.v();
        r1 = r1.f();
        r0 = r0.buildAudioFollowUpCampaign(r1);
        r0 = r0.build();
        r0.loadAndShow();
    L_0x02c3:
        r0 = com.gaana.application.GaanaApplication.getContext();
        r0 = android.support.v4.content.LocalBroadcastManager.getInstance(r0);
        r1 = r6.mChromeCastReceiver;
        r2 = new android.content.IntentFilter;
        r4 = "UPDATE_UI_CHROMECAST_CONNECTED";
        r2.<init>(r4);
        r0.registerReceiver(r1, r2);
        r6.updateView();
        com.player_framework.o.c(r6, r3);
        r0 = r6.mAppState;
        r0.hockeyCheckForCrashes(r6);
        com.utilities.Util.a(r6);
        super.onResume();
        r0 = r6.mNavigationHeaderMenu;
        if (r0 == 0) goto L_0x02f1;
    L_0x02ec:
        r0 = r6.mNavigationHeaderMenu;
        r0.updateLoginBar();
    L_0x02f1:
        r0 = com.constants.Constants.dD;
        if (r0 != 0) goto L_0x030f;
    L_0x02f5:
        r0 = com.constants.Constants.dC;
        r1 = "2G";
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 == 0) goto L_0x030f;
    L_0x02ff:
        com.constants.Constants.dD = r3;
        r0 = com.managers.aj.a();
        r1 = 2131822442; // 0x7f11076a float:1.9277656E38 double:1.0532602316E-314;
        r1 = r6.getString(r1);
        r0.a(r6, r1, r3);
    L_0x030f:
        r6.showHideNewDownloadedSongCount();
        r6.makeVideoFeedCategoriesRequest();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity.onResume():void");
    }

    /* Access modifiers changed, original: protected */
    public void refreshScreen() {
        super.refreshScreen();
        if (this.mNavigationHeaderMenu != null) {
            this.mNavigationHeaderMenu.updateLoginBar();
        }
    }

    public void setPlayerListeners(MiniPlayerFragment miniPlayerFragment) {
        this.mPlayer = miniPlayerFragment;
    }

    public Fragment getMiniPlayerFragment() {
        return this.mPlayer;
    }

    public void setPlayerListeners(MiniPlayerFragmentV4 miniPlayerFragmentV4) {
        this.mPlayer = miniPlayerFragmentV4;
    }

    public void refreshForFavorite() {
        if (this.mPlayer instanceof MiniPlayerFragment) {
            ((MiniPlayerFragment) this.mPlayer).m();
        } else if (this.mPlayer instanceof MiniPlayerFragmentV4) {
            ((MiniPlayerFragmentV4) this.mPlayer).o();
        }
    }

    public void initMiniPlayer() {
        if (!isFinishing()) {
            Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("player_fragment");
            View findViewById = findViewById(R.id.llPlayerLayout);
            if (this.mPlayer == null || findFragmentByTag == null) {
                if (Constants.I == 1) {
                    if (this.miniPlayerV4 == null) {
                        this.miniPlayerV4 = ((ViewStub) findViewById(R.id.stub_mini_player_4)).inflate();
                    }
                    this.mPlayer = new MiniPlayerFragmentV4();
                    initBottomNavigationBar();
                } else {
                    this.mPlayer = new MiniPlayerFragment();
                    initBottomNavigationBar();
                }
                this.fragmentTrasState = false;
                this.fragmentTransaction = this.fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.llPlayerLayout, this.mPlayer, "player_fragment");
                findViewById.setVisibility(0);
                try {
                    this.fragmentTransaction.commitAllowingStateLoss();
                    this.fragmentTrasState = true;
                } catch (IllegalStateException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            } else {
                try {
                    hideFakePlayer();
                    this.mPlayer = findFragmentByTag;
                    if (this.mPlayer instanceof MiniPlayerFragment) {
                        if (Constants.I == 1) {
                            this.mPlayer = null;
                            if (Constants.I == 1) {
                                if (this.miniPlayerV4 == null) {
                                    this.miniPlayerV4 = ((ViewStub) findViewById(R.id.stub_mini_player_4)).inflate();
                                }
                                this.mPlayer = new MiniPlayerFragmentV4();
                                initBottomNavigationBar();
                            } else {
                                this.mPlayer = new MiniPlayerFragment();
                                initBottomNavigationBar();
                            }
                            this.fragmentTrasState = false;
                            this.fragmentTransaction = this.fragmentManager.beginTransaction();
                            this.fragmentTransaction.replace(R.id.llPlayerLayout, this.mPlayer, "player_fragment");
                            findViewById.setVisibility(0);
                            try {
                                this.fragmentTransaction.commitAllowingStateLoss();
                                this.fragmentTrasState = true;
                            } catch (IllegalStateException e2) {
                                ThrowableExtension.printStackTrace(e2);
                            }
                        } else {
                            ((MiniPlayerFragment) this.mPlayer).f();
                        }
                    } else if (this.mPlayer instanceof MiniPlayerFragmentV4) {
                        if (Constants.I == 0) {
                            this.mPlayer = null;
                            if (Constants.I == 1) {
                                if (this.miniPlayerV4 == null) {
                                    this.miniPlayerV4 = ((ViewStub) findViewById(R.id.stub_mini_player_4)).inflate();
                                }
                                this.mPlayer = new MiniPlayerFragmentV4();
                                initBottomNavigationBar();
                            } else {
                                this.mPlayer = new MiniPlayerFragment();
                                initBottomNavigationBar();
                            }
                            this.fragmentTrasState = false;
                            this.fragmentTransaction = this.fragmentManager.beginTransaction();
                            this.fragmentTransaction.replace(R.id.llPlayerLayout, this.mPlayer, "player_fragment");
                            findViewById.setVisibility(0);
                            try {
                                this.fragmentTransaction.commitAllowingStateLoss();
                                this.fragmentTrasState = true;
                            } catch (IllegalStateException e22) {
                                ThrowableExtension.printStackTrace(e22);
                            }
                        } else {
                            ((MiniPlayerFragmentV4) this.mPlayer).h();
                        }
                    }
                    initBottomNavigationBar();
                    setMiniPlayerCarouselUI();
                    SharedPreferences sharedPreferences = getSharedPreferences("PLAYER_CREATED_FIRST_TIME", 0);
                    if (sharedPreferences.getBoolean("PLAYER_CREATED_FIRST_TIME", true)) {
                        Editor edit = sharedPreferences.edit();
                        edit.putBoolean("PLAYER_CREATED_FIRST_TIME", false);
                        edit.apply();
                    }
                } catch (Exception e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            }
            if (!this.isVideoItemPlayed) {
                showPlayerCoachmark();
            }
            getFreedomPlanUserEnagagement();
        }
    }

    public boolean isVideoItemPlayed() {
        return this.isVideoItemPlayed;
    }

    public void setVideoItemPlayed(boolean z) {
        this.isVideoItemPlayed = z;
    }

    private void setMiniPlayerCarouselUI() {
        if (Constants.I != 1 || PlayerManager.a(this.mContext).l() <= 0) {
            this.progressOverlayView = (FrameLayout) findViewById(R.id.container_bottom_progress_view);
            if (this.progressOverlayView != null) {
                this.progressOverlayView.setVisibility(8);
            }
        } else if (this.mPlayer instanceof MiniPlayerFragmentV4) {
            this.progressOverlayView = (FrameLayout) findViewById(R.id.container_bottom_progress_view);
            this.progressOverlayView.setVisibility(0);
            ((MiniPlayerFragmentV4) this.mPlayer).c();
        }
    }

    public void hideMiniPlayerForPlayerFreeFragment() {
        this.progressOverlayView = (FrameLayout) findViewById(R.id.container_bottom_progress_view);
        if (this.progressOverlayView != null) {
            this.progressOverlayView.setVisibility(8);
        }
    }

    public void showMiniPlayerForPlayerFreeFragment() {
        this.progressOverlayView = (FrameLayout) findViewById(R.id.container_bottom_progress_view);
        if (Constants.I != 1 || PlayerManager.a(this.mContext).l() <= 0) {
            if (this.progressOverlayView != null) {
                this.progressOverlayView.setVisibility(8);
            }
        } else if (this.progressOverlayView != null) {
            this.progressOverlayView.setVisibility(0);
        }
    }

    public FrameLayout getProgressOverlayView() {
        return this.progressOverlayView;
    }

    public void setProgressOverlayView(FrameLayout frameLayout) {
        this.progressOverlayView = frameLayout;
    }

    /* JADX WARNING: Missing block: B:24:0x005f, code skipped:
            return;
     */
    public void showPlayerCoachmark() {
        /*
        r4 = this;
        r0 = r4.hasWindowFocus();
        if (r0 == 0) goto L_0x005f;
    L_0x0006:
        r0 = r4.mDrawerLayout;
        r1 = 8388611; // 0x800003 float:1.1754948E-38 double:4.1445245E-317;
        r0 = r0.isDrawerOpen(r1);
        if (r0 != 0) goto L_0x005f;
    L_0x0011:
        r0 = r4.mFragment;
        if (r0 == 0) goto L_0x001e;
    L_0x0015:
        r0 = r4.mFragment;
        r0 = r0.isBottomBarHidden();
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x005f;
    L_0x001e:
        r0 = com.services.d.a();
        r1 = "PREFERENCE_KEY_MINI_PLAYER_SWIPE_INITIATED";
        r2 = 0;
        r0 = r0.b(r1, r2, r2);
        if (r0 == 0) goto L_0x002c;
    L_0x002b:
        return;
    L_0x002c:
        r0 = 2;
        r1 = com.services.d.a();
        r3 = "SWIPE_LEFT_PLAYER_COUNT";
        r1 = r1.b(r3, r2, r2);
        if (r1 < r0) goto L_0x003a;
    L_0x0039:
        return;
    L_0x003a:
        r0 = com.services.d.a();
        r3 = "SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK";
        r0 = r0.b(r3, r2, r2);
        r2 = r0 + 5;
        r3 = 1;
        if (r0 <= 0) goto L_0x0052;
    L_0x0049:
        r0 = com.gaana.application.GaanaApplication.sessionHistoryCount;
        r0 = r0 + r3;
        if (r0 < r2) goto L_0x005e;
    L_0x004e:
        r4.playerCoachmarkAction(r1);
        goto L_0x005e;
    L_0x0052:
        if (r1 != 0) goto L_0x005e;
    L_0x0054:
        r0 = com.gaana.application.GaanaApplication.sessionHistoryCount;
        r0 = r0 + r3;
        if (r0 < r3) goto L_0x005e;
    L_0x0059:
        r4.IS_COACHMARK_VISIBLE = r3;
        r4.playerCoachmarkAction(r1);
    L_0x005e:
        return;
    L_0x005f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaActivity.showPlayerCoachmark():void");
    }

    public void showPlayerVIewPagerCoachmark() {
        if (hasWindowFocus()) {
            int b = d.a().b("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME", 0, false);
            if (b < 3) {
                int b2 = d.a().b("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", 0, false);
                int i = b2 + 2;
                if (b2 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        playerViewPagerCoachmarkAction(b);
                    }
                } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 1) {
                    this.IS_COACHMARK_VISIBLE = true;
                    playerViewPagerCoachmarkAction(b);
                }
            }
        }
    }

    private void playerCoachmarkAction(int i) {
        if (Constants.I != 0) {
            d.a().a("SWIPE_LEFT_PLAYER_COUNT", i + 1, false);
            d.a().a("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", GaanaApplication.sessionHistoryCount, false);
        } else if (this.mPlayer != null) {
            Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
            intent.putExtra("COACHMARK_VALUE", "PLAYER_SWIPE_COACHMARK_FIRSTTIME");
            startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
            overridePendingTransition(17432576, 17432577);
            d.a().a("SWIPE_LEFT_PLAYER_COUNT", i + 1, false);
            d.a().a("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", GaanaApplication.sessionHistoryCount, false);
        }
    }

    private void playerViewPagerCoachmarkAction(int i) {
        if (getmCurrentPlayerFragment() != null && (getmCurrentPlayerFragment() instanceof PlayerFragmentV4)) {
            Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
            intent.putExtra("COACHMARK_VALUE", "PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME");
            startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
            overridePendingTransition(17432576, 17432577);
            d.a().a("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME", i + 1, false);
            d.a().a("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", GaanaApplication.sessionHistoryCount, false);
        }
    }

    public void showPreScreenCoachmark() {
        if (Constants.aZ && !d.a().b("PRESCREEN_COACHMARK", false, false) && (getCurrentFragment() instanceof DynamicHomeFragment)) {
            d.a().a("PRESCREEN_COACHMARK", true, false);
            if (!this.mDrawerLayout.isDrawerOpen(8388611)) {
                Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
                intent.putExtra("COACHMARK_VALUE", "PRESCREEN_COACHMARK");
                startActivity(intent);
                overridePendingTransition(17432576, 17432577);
            }
        }
    }

    public void showMiniPlayerOverlayCoachmark() {
        boolean b = d.a().b("PREFERENCE_KEY_MINI_PLAYER_OVERLAY_SWIPE_INITIATED", false, false);
        if (hasWindowFocus() && !b) {
            int b2 = d.a().b("MINI_PLAYER_OVERLAY_COACHMARK_FIRSTTIME", 0, false);
            if (b2 < 3) {
                int b3 = d.a().b("SESSION_OCCURENCE_MINI_PLAYER_OVERLAY_COACHMARK", 0, false);
                int i = b3 + 2;
                if (b3 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        miniPlayerCoachmarkAction(b2);
                    }
                } else if (b2 == 0 && GaanaApplication.sessionHistoryCount + 1 >= 1) {
                    miniPlayerCoachmarkAction(b2);
                }
            }
        }
    }

    private void miniPlayerCoachmarkAction(final int i) {
        if (this.mPlayer != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(GaanaActivity.this.mContext, VideoCoachmarkActivity.class);
                    intent.putExtra("COACHMARK_VALUE", "MINI_PLAYER_OVERLAY_COACHMARK_FIRSTTIME");
                    GaanaActivity.this.startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
                    GaanaActivity.this.overridePendingTransition(17432576, 17432577);
                    int i = i + 1;
                    d.a().a("MINI_PLAYER_OVERLAY_COACHMARK_FIRSTTIME", i, false);
                    d.a().a("SESSION_OCCURENCE_MINI_PLAYER_OVERLAY_COACHMARK", i, false);
                }
            }, 200);
        }
    }

    public void setUpdatePlayerFragment() {
        ArrayList n = PlayerManager.a(this.mContext).n();
        if (n == null || n.size() == 0) {
            com.managers.o.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    GaanaActivity.this.mUiHandler.post(new Runnable() {
                        public void run() {
                            GaanaActivity.this.initMiniPlayer();
                        }
                    });
                }
            });
        } else {
            initMiniPlayer();
        }
    }

    public boolean isSlidingPanelExpanded() {
        return isPlayerExpanded();
    }

    /* Access modifiers changed, original: protected */
    public void updateView() {
        refreshUser(new at() {
            public void onUserRefreshed() {
                if (!GaanaApplication.getInstance().isAppInForeground()) {
                    return;
                }
                if (ap.a().j() || Util.v()) {
                    GaanaActivity.this.addDownloadReceiver();
                    DownloadManager.c().d();
                }
            }
        }, false);
    }

    public void performSearch(String str) {
        if (this.mFragment instanceof DownloadDetailsFragment) {
            ((DownloadDetailsFragment) this.mFragment).a(str);
        } else if (this.mFragment instanceof LocalMediaFragment) {
            ((LocalMediaFragment) this.mFragment).a(str);
        } else if (this.mFragment instanceof ListingFragment) {
            ((ListingFragment) this.mFragment).c(str);
        } else if (this.mFragment instanceof FavoritesFragment) {
            ((FavoritesFragment) this.mFragment).a(str);
        }
    }

    public void performSearch(String str, String str2) {
        performSearch(str, str2, true);
    }

    public void performSearch(String str, String str2, boolean z) {
        if (!(this.mFragment instanceof SearchFragment)) {
            if (!GaanaSearchManager.a().i()) {
                if (this.mAppState.isAppInOfflineMode()) {
                    displayFeatureNotAvailableOfflineDialog(getString(R.string.search_title));
                    return;
                } else if (!Util.j(this.mContext)) {
                    ap.a().f(this.mContext);
                    return;
                }
            }
            BaseGaanaFragment searchFragment = new SearchFragment();
            Bundle bundle = new Bundle();
            bundle.putString("default_tab", str2);
            bundle.putString("search_string", str);
            bundle.putBoolean("save_search_query", z);
            bundle.putBoolean("search_my_music", GaanaSearchManager.a().i());
            searchFragment.setArguments(bundle);
            displayFragment(searchFragment);
        }
    }

    public boolean isFragmentDisplayed(BaseGaanaFragment baseGaanaFragment) {
        return (baseGaanaFragment instanceof SettingsDetailFragment) && (this.mFragment instanceof SettingsDetailFragment);
    }

    public void clearStackForSearch() {
        this.isLaunchedFromDeeplink = false;
        this.isMyMusicDeeplink = false;
        this.mSelectedPosition = SHOW_TAB_SEARCH;
        this.mBottomNavigationBarHelper.a(this.bottomNavigationView, this.mSelectedPosition);
        if (this.mFragmentController != null) {
            this.mFragmentController.b("search");
        }
    }

    public void displayFragment(BaseGaanaFragment baseGaanaFragment) {
        if (baseGaanaFragment != null) {
            boolean z = false;
            if (this.isNavTabClicked) {
                this.isNavTabClicked = false;
            } else {
                z = true;
            }
            this.mFragmentController.a((Fragment) baseGaanaFragment, z);
        }
    }

    public void displayFragment(String str) {
        if (!TextUtils.isEmpty(str)) {
            boolean z = false;
            if (this.isNavTabClicked) {
                this.isNavTabClicked = false;
            } else {
                z = true;
            }
            this.mFragmentController.a(str, z);
        }
    }

    public void showHidePreScreen(boolean z) {
        if (this.mDrawerLayout != null && VERSION.SDK_INT >= 21) {
            this.mDrawerLayout.setDrawerElevation(z ? (float) Util.b(10) : 0.0f);
        }
        if (Constants.aZ && findViewById(R.id.home_prescreen) != null) {
            findViewById(R.id.home_prescreen).setVisibility(z ? 8 : 0);
        }
    }

    public void onBackPressed() {
        if (Constants.T && !this.fromInternationalOnBoarding) {
            Constants.T = false;
        }
        if (com.services.c.b && com.services.c.c) {
            finish();
            return;
        }
        QueueSlidingUpPanelLayout queueSlidingUpPanelLayout = isPlayerExpanded() ? (QueueSlidingUpPanelLayout) findViewById(R.id.sliding_layout_queue) : null;
        if (this.mDrawerLayout.isDrawerOpen(this.sideView_Container)) {
            this.mDrawerLayout.closeDrawers();
        } else if (queueSlidingUpPanelLayout != null && queueSlidingUpPanelLayout.isShown() && queueSlidingUpPanelLayout.e()) {
            queueSlidingUpPanelLayout.g();
        } else if (isPlayerExpanded()) {
            if (this.mCurrentPlayerFragment instanceof com.services.l.q) {
                ((com.services.l.q) this.mCurrentPlayerFragment).b();
            } else {
                this.mFragmentController.a();
            }
        } else if (this.mFragment instanceof com.services.l.q) {
            ((com.services.l.q) this.mFragment).b();
        } else if (this.draggablePanel == null || this.draggablePanel.getParent() == null) {
            onBackPressedHandling();
        } else if (this.isFullScreen && this.youtubePlayer != null) {
            this.youtubePlayer.setFullscreen(false);
        } else if (this.draggablePanel.f()) {
            this.draggablePanel.d();
        } else {
            this.draggablePanel.a();
        }
    }

    private void homepageBackHandlingOnTab() {
        this.fragment = new DynamicHomeFragment();
        if (this.bundle == null) {
            this.bundle = new Bundle();
        }
        try {
            if (this.fragmentManager != null) {
                this.fragmentManager.popBackStackImmediate(null, 1);
            }
        } catch (IllegalStateException unused) {
        }
        this.fragment.setArguments(this.bundle);
        an.a().b = this.mSelectedPosition;
        this.mSelectedPosition = SHOW_TAB_HOME;
        an.a().a = this.mSelectedPosition;
        displayFragment(this.fragment);
        handleScreenViewEvents(this.mSelectedPosition);
    }

    public void onBackPressedHandling() {
        if (com.services.c.b && com.services.c.c) {
            finish();
            return;
        }
        boolean z;
        if (Constants.T && this.fromInternationalOnBoarding && (this.mFragment instanceof SettingsDetailFragment)) {
            this.fromInternationalOnBoarding = false;
            z = true;
        } else {
            z = false;
        }
        if (this.isMyMusicDeeplink || this.isLaunchedFromDeeplink) {
            if (this.isLaunchedFromDeeplink) {
                this.isLaunchedFromDeeplink = false;
            }
            if (this.isMyMusicDeeplink) {
                this.isMyMusicDeeplink = false;
            }
            homepageBackHandlingOnTab();
            return;
        }
        if (this.mFragment instanceof PaymentDetailFragment) {
            ((PaymentDetailFragment) this.mFragment).c();
        }
        if (z) {
            launchOnBoardLanguageScreen();
        }
        if (!this.mFragmentController.a()) {
            performDoubleClickExit();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void launchOnBoardLanguageScreen() {
        String str;
        if (Util.j(this.mContext)) {
            str = "Back-LanguagePreference";
            Intent intent = new Intent(this.mContext, OnBoardLanguagePreferenceActivityNew.class);
            intent.setFlags(603979776);
            startActivity(intent);
        } else {
            str = "Back-Home";
            if (getCurrentFragment() instanceof SettingsDetailFragment) {
                popBackStackImmediate();
            }
            changeFragment(R.id.LeftMenuMyMusic, null, null);
        }
        u.a().a("InternationalOnBoarding", str, "SubscriptionScreen");
    }

    private void performDoubleClickExit() {
        if (this.doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        aj.a().a(this.mContext, getString(R.string.press_again_exit));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GaanaActivity.this.doubleBackToExitPressedOnce = false;
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public BaseGaanaFragment getCurrentFragment() {
        return this.mFragment;
    }

    /* Access modifiers changed, original: protected */
    public void onPostResume() {
        if (!this.fragmentTrasState) {
            try {
                this.fragmentTransaction.commitAllowingStateLoss();
            } catch (IllegalStateException unused) {
            }
        }
        if (this.mFragmentController != null) {
            this.mFragmentController.b();
        }
        super.onPostResume();
    }

    public void homeIconClick() {
        if (!showMenuButton()) {
            onBackPressedHandling();
        } else if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.closeDrawers();
        } else {
            this.mDrawerLayout.openDrawer(8388611);
        }
    }

    public void closeDrawers() {
        if (this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.closeDrawers();
        }
    }

    public void openDrawers(boolean z) {
        if (z) {
            showHidePreScreen(true);
        }
        if (this.mDrawerLayout != null && !this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.openDrawer(8388611);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            homeIconClick();
        } else if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.closeDrawers();
        } else {
            this.mDrawerLayout.openDrawer(8388611);
        }
        return true;
    }

    private void setActionBar() {
        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, R.string.app_name, Constants.l ? R.string.app_name_mmx : R.string.app_name) {
            public void onDrawerClosed(View view) {
                if (GaanaActivity.this.changeFragment) {
                    if (GaanaActivity.this.mAppState.getSidebarActiveBtn() == R.id.LeftPartyHub) {
                        u.a().a("PartyHub", "Entry", "Nav_Drawer");
                    }
                    GaanaActivity.this.changeFragment(GaanaActivity.this.mAppState.getSidebarActiveBtn(), "", null);
                    GaanaActivity.this.changeFragment = false;
                }
                if (GaanaActivity.this.fromSearch) {
                    GaanaActivity.this.fromSearch = false;
                    GaanaActivity.this.performSearch(GaanaActivity.this.getSearchString);
                    GaanaActivity.this.getSearchString = "";
                }
                GaanaActivity.this.showHidePreScreen(false);
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                GaanaActivity.this.startSponsorAd();
                GaanaActivity.this.refreshPreScreen();
                if (GaanaActivity.this.findViewById(R.id.home_prescreen) != null && GaanaActivity.this.findViewById(R.id.home_prescreen).getVisibility() == 0) {
                    u.a().b("ForYou", "Open");
                    u.a().a("ForYou");
                }
            }

            public void onDrawerStateChanged(int i) {
                super.onDrawerStateChanged(i);
            }

            public void onDrawerSlide(View view, float f) {
                super.onDrawerSlide(view, f);
                if (GaanaActivity.this.findViewById(R.id.home_prescreen) != null && GaanaActivity.this.findViewById(R.id.home_prescreen).getVisibility() == 0) {
                    GaanaActivity.this.frameContainer.setTranslationX(((float) view.getWidth()) * f);
                }
            }
        };
        this.mDrawerLayout.setDrawerListener(this.actionBarDrawerToggle);
    }

    /* Access modifiers changed, original: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.actionBarDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.actionBarDrawerToggle.onConfigurationChanged(configuration);
    }

    public boolean isDownloadSyncReceiverRegistered() {
        return this.isDownloadSyncReceiverRegistered;
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        q.a().c();
        super.onDestroy();
        if (PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance") != null) {
            PurchaseGoogleManager.a("onlyForCallbackNotForGettingInstance").c();
        }
        if (this.mDownloadSyncReceiver != null && isDownloadSyncReceiverRegistered()) {
            unregisterReceiver(this.mDownloadSyncReceiver);
            this.isDownloadSyncReceiverRegistered = false;
        }
        unRegisterContentObserver();
        DownloadSongsItemView.resetStatiView();
        aa.a().a(null);
        if (this.bottomNavigationView != null) {
            this.bottomNavigationView.setOnNavigationItemSelectedListener(null);
            this.bottomNavigationView = null;
        }
    }

    private void exitFromGaana() {
        DownloadManager.c().e();
        FileDownloadService.a(false);
        l.a().i();
        o.d((Context) this);
        if (!ap.a().b(this.mContext)) {
            moveTaskToBack(true);
        }
        ArrayList arrayList = null;
        if (PlayerManager.a(this.mContext).m() == PlayerType.GAANA) {
            arrayList = PlayerManager.a(this.mContext).n();
        }
        if (arrayList == null || arrayList.size() == 0) {
            d.a().a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            com.managers.o.a().a(new ArrayList());
        }
        com.managers.o.a().a(arrayList, PlayerManager.a((Context) this).s(), new aw() {
            public void onPlayerQueueSavingCompleted() {
                GaanaActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Process.killProcess(Process.myPid());
                    }
                });
            }
        });
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 714 && i2 == -1) {
            Bundle extras = intent.getExtras();
            if (extras != null && "Success".equalsIgnoreCase(extras.getString("SubscriptionPayment"))) {
                ag.a(this.mContext).b(this.mContext);
            }
        }
        if (i2 != -1 && i == 111) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.instagram_storage_permission));
        }
        if (i == 1) {
            com.services.m.a().a(i, i2, intent);
        }
        if (i == 2) {
            com.services.m.a().a(i, i2, intent);
        }
        if (i == 701 && i2 != 0) {
            addDownloadReceiver();
        }
        if (i == 711 && i2 != 0) {
            Util.b(this.mContext, "Free_trial", new as() {
                public void onTrialSuccess() {
                    u.a().a("Skip Count", "30 days Pop Up", "Default Plan_30 Days Trial_Success");
                    if (Constants.ab) {
                        Constants.ab = false;
                        Constants.aa = false;
                        d.a().a(Constants.ac, false, true);
                        d.a().a(Constants.ad, false, true);
                        GaanaActivity.this.recreate();
                    }
                }
            });
        }
        if (i == 100 && i2 == -1 && intent != null) {
            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            Toast.makeText(this, (CharSequence) stringArrayListExtra.get(0), 1).show();
            GaanaSearchManager a = GaanaSearchManager.a();
            Constants.at = true;
            a.a((Context) this, (String) stringArrayListExtra.get(0));
        }
        if (i == PointerIconCompat.TYPE_ALIAS && i2 == -1 && intent != null) {
            i = intent.getIntExtra("search", -1);
            i2 = intent.getIntExtra("home", -1);
            int intExtra = intent.getIntExtra("radio", -1);
            int intExtra2 = intent.getIntExtra("my_music", -1);
            if (i != -1) {
                changeFragment(R.id.TopTabSearch, null, null);
            } else if (i2 != -1) {
                changeFragment(R.id.LeftMenuHome, null, null);
            } else if (intExtra != -1) {
                changeFragment(R.id.LeftMenuRadio, null, null);
            } else if (intExtra2 != -1) {
                changeFragment(R.id.LeftMenuMyMusic, null, null);
            }
        }
    }

    public void setFragment(BaseGaanaFragment baseGaanaFragment) {
        if (baseGaanaFragment != null) {
            this.mFragment = baseGaanaFragment;
        }
    }

    public boolean getRefreshData() {
        return this.refreshData;
    }

    public void setRefreshData(boolean z) {
        this.refreshData = z;
    }

    public void contentChanged() {
        if (this.mFragment instanceof LocalMediaFragment) {
            ((LocalMediaFragment) this.mFragment).a();
        } else if (this.mFragment instanceof AlbumDetailsMaterialListing) {
            ((AlbumDetailsMaterialListing) this.mFragment).d();
        } else if (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
            ((GaanaSpecialDetailsMaterialListing) this.mFragment).e();
        } else if (this.mFragment instanceof ListingFragment) {
            ((ListingFragment) this.mFragment).i();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        Util.a(null);
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.broadcastReceiverPlaylistStatusUpdate);
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.broadcastReceiverFreedomUserInfo);
        LoginManager.getInstance().getTimesPointLogger().a();
        InAppManager.getInstance().setInAppListener(null);
        if (ap.a().b(this.mContext) || !(this.mAppState.getCurrentUser().getUserSubscriptionData() == null || this.mAppState.getCurrentUser() == null || this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != 2)) {
            ColombiaManager.b().g();
        }
        an.a().c();
        try {
            unregisterReceiver(this.mNetworkChangeBroadCastReceiver);
        } catch (IllegalStateException unused) {
        }
        super.onStop();
        if (PlayerManager.a(this.mContext).m() == PlayerType.GAANA && PlayerManager.a(this.mContext).E() == null) {
            ArrayList n = PlayerManager.a(this.mContext).n();
            if (n == null || n.size() == 0) {
                d.a().a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
                com.managers.o.a().a(new ArrayList());
            }
            com.managers.o.a().a(n, PlayerManager.a((Context) this).s(), null);
        }
        this.mUiHandler.removeCallbacks(null);
        FileDownloadService.a(null);
    }

    public void onInAppShown(InAppMessage inAppMessage) {
        if ((this.mFragment instanceof DynamicHomeFragment) && ((DynamicHomeFragment) this.mFragment).a() != null) {
            ((DynamicHomeFragment) this.mFragment).a().dismissDialog();
        }
        if (inAppMessage != null && inAppMessage.rules != null && !TextUtils.isEmpty(inAppMessage.rules.campaignId)) {
            u.a().a("InAPP", "Impression", inAppMessage.rules.campaignId);
        }
    }

    public boolean showInAppMessage(final InAppMessage inAppMessage) {
        try {
            String string = new JSONObject(inAppMessage.content).getString("template");
            Long l = (Long) GaanaApplication.getInstance().inAppShownList.get(string);
            if (l == null || System.currentTimeMillis() - l.longValue() >= 900000) {
                if (!TextUtils.isEmpty(string) && string.equalsIgnoreCase("purchase_dlg")) {
                    GaanaApplication.getInstance().inAppShownList.put(string, Long.valueOf(System.currentTimeMillis()));
                    new Handler(getMainLooper()).post(new Runnable() {
                        public void run() {
                            new GaanaMiniPurchaseDialog(GaanaActivity.this.mContext, inAppMessage).show();
                        }
                    });
                    return true;
                } else if (!TextUtils.isEmpty(string) && string.equals("rate_us")) {
                    GaanaApplication.getInstance().inAppShownList.put(string, Long.valueOf(System.currentTimeMillis()));
                    new Handler(getMainLooper()).post(new Runnable() {
                        public void run() {
                            new RateUsDialog(GaanaActivity.this.mContext, inAppMessage).show();
                        }
                    });
                    return true;
                } else if (!TextUtils.isEmpty(string) && string.equals("user_feedback")) {
                    new Handler(getMainLooper()).post(new Runnable() {
                        public void run() {
                            Util.a(GaanaActivity.this.mContext, inAppMessage);
                        }
                    });
                    return true;
                } else if (this.mFragment instanceof DynamicHomeFragment) {
                    new Handler(getMainLooper()).post(new Runnable() {
                        public void run() {
                            ((DynamicHomeFragment) GaanaActivity.this.mFragment).a(inAppMessage);
                        }
                    });
                    return true;
                } else {
                    DynamicHomeFragment.d = inAppMessage;
                    return true;
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return false;
    }

    public void onInAppClosed(InAppMessage inAppMessage) {
        if (inAppMessage != null && inAppMessage.rules != null && !TextUtils.isEmpty(inAppMessage.rules.campaignId)) {
            u.a().a("InAPP", "Close", inAppMessage.rules.campaignId);
        }
    }

    public boolean onInAppClick(@Nullable String str, @Nullable Bundle bundle, @Nullable Uri uri) {
        if (uri != null) {
            u.a().a("InAPP", "Click", uri.toString());
        }
        return false;
    }

    public void setDrawerLockMode(int i) {
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.setDrawerLockMode(1);
        }
    }

    public void drawerModeUnLocked() {
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.setDrawerLockMode(0);
        }
    }

    public void initOnboardPlayer() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                com.views.f fVar = new com.views.f(GaanaActivity.this);
                fVar.a();
                fVar.b();
            }
        });
    }

    public void onBottomMenuLongClick() {
        if (h.a((Activity) this.mContext)) {
            u.a().a("VoiceInteraction", "Permission", "pass");
            if (Util.j(this.mContext)) {
                this.mVoiceRecognitionDialog = new VoiceRecognitionDialog(this);
                this.mVoiceRecognitionDialog.show();
            } else {
                ap.a().f(this.mContext);
            }
        } else {
            u.a().a("VoiceInteraction", "Permission", "failed");
        }
        showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
        d.a().a("PREFERENCE_VOICE_SEARCH_COACHMARK", true, true);
        d.a().a("PREFERENCE_KEY_VOICE_SEARCH_HOME_INITIATED", true, false);
        u.a().b("VoiceInteraction", "SearchLongPress");
        fetchHelpText();
    }

    public void onAdConfigLoaded() {
        if (this.mFragment != null && this.mFragment.isAdded()) {
            this.mFragment.onAdConfigLoaded();
        }
        if (ap.a().b(this.mContext)) {
            LoadInterstitialBehaviour loadInterstitialBehaviour = new LoadInterstitialBehaviour();
            if (loadInterstitialBehaviour.whenToLoad() && this.interstitialAdType == null) {
                this.interstitialAdType = createInterstitialAd(loadInterstitialBehaviour);
            }
        }
    }

    public void setCurrentSongSelectedView(View view) {
        this.mSongClickedView = view;
    }

    public View getCurrentSongSelectedView() {
        return this.mSongClickedView;
    }

    public void registerContentObserver() {
        this.mLocalMediaContentObserver = new LocalMediaContentObserver(new Handler());
        getContentResolver().registerContentObserver(Media.EXTERNAL_CONTENT_URI, false, this.mLocalMediaContentObserver);
        this.mLocalMediaContentObserver.setOnSearchCompleted(this);
    }

    public void unRegisterContentObserver() {
        if (this.mLocalMediaContentObserver != null) {
            getContentResolver().unregisterContentObserver(this.mLocalMediaContentObserver);
        }
    }

    public PlayerStates getPlayerStates() {
        if (this.mPlayer instanceof MiniPlayerFragment) {
            return ((MiniPlayerFragment) this.mPlayer).h();
        }
        if (this.mPlayer instanceof MiniPlayerFragmentV4) {
            return ((MiniPlayerFragmentV4) this.mPlayer).n();
        }
        return PlayerStates.INVALID;
    }

    public void updateSideBar() {
        if (this.mNavigationHeaderMenu != null) {
            this.mNavigationHeaderMenu.updateLoginBar();
        }
    }

    public void refreshTrialCard() {
        if (this.mFragment != null) {
            this.mFragment.refreshDataandAds();
            updateSideBar();
        }
    }

    public BaseFragment getmCurrentPlayerFragment() {
        return this.mCurrentPlayerFragment;
    }

    public void setmCurrentPlayerFragment(BaseFragment baseFragment) {
        this.mCurrentPlayerFragment = baseFragment;
    }

    public boolean launchExpandedPlayer() {
        Fragment a = com.constants.a.a(PlayerManager.a(GaanaApplication.getContext()).m());
        if (this.mPlayer instanceof MiniPlayerFragment) {
            ((MiniPlayerFragment) this.mPlayer).a((av) a);
        } else if (this.mPlayer instanceof MiniPlayerFragmentV4) {
            ((MiniPlayerFragmentV4) this.mPlayer).a((av) a);
        }
        this.mFragmentController.a(a, true);
        showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
        d.a().a("PREFERENCE_KEY_MINI_PLAYER_OVERLAY_SWIPE_INITIATED", true, false);
        return false;
    }

    public void setSleepTimer(int i) {
        cancelSleepTimer();
        u.a().a("Sleep timer", "Sleep timer", "user sets a timer");
        this._currentTime = i * 60;
        this._sleepTime = i;
        this._sleepTimer = new Handler();
        this._sleepTimer.postDelayed(new Runnable() {
            public void run() {
                if (GaanaActivity.this._currentTime > 0) {
                    GaanaActivity gaanaActivity = GaanaActivity.this;
                    gaanaActivity._currentTime--;
                    int i = GaanaActivity.this._currentTime / 60;
                    int i2 = GaanaActivity.this._currentTime % 60;
                    if (GaanaActivity.this._sleepTimerListener != null) {
                        GaanaActivity.this._sleepTimerListener.SleepTimerTick(i, i2);
                    }
                    if (GaanaActivity.this._currentTime == 0) {
                        if (GaanaActivity.this._sleepTimerListener != null) {
                            GaanaActivity.this._sleepTimerListener.SleepTimerCompleted();
                        }
                        GaanaActivity.this.cancelSleepTimer();
                        o.a(GaanaActivity.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                        return;
                    }
                    GaanaActivity.this._sleepTimer.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    public int getSleepTime() {
        return this._sleepTime;
    }

    public int getCurrentSleepTime() {
        return this._currentTime;
    }

    public void cancelSleepTimer() {
        if (this._sleepTimer != null) {
            this._sleepTimer.removeCallbacksAndMessages(null);
        }
        this._currentTime = 0;
        this._sleepTime = 0;
        this._sleepTimer = null;
    }

    public void setSleepTimerListener(ISleepTimerListener iSleepTimerListener) {
        this._sleepTimerListener = iSleepTimerListener;
    }

    public void removeSleepTimerCallback() {
        this._sleepTimerListener = null;
    }

    public void setSleepTimerOnPlayerPrepared(final int i) {
        o.a("LISTENER_KEY_SLEEP_TIMER", new m() {
            public void onAdEventUpdate(f fVar, AdEvent adEvent) {
            }

            public void onBufferingUpdate(f fVar, int i) {
            }

            public void onCompletion(f fVar) {
            }

            public void onError(f fVar, int i, int i2) {
            }

            public void onInfo(f fVar, int i, int i2) {
            }

            public void onPrepared(f fVar) {
                GaanaActivity.this.setSleepTimer(i);
                o.d("LISTENER_KEY_SLEEP_TIMER");
            }
        });
    }

    public void applyLightMode(boolean z) {
        BaseGaanaFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof DynamicHomeFragment) {
            ((DynamicHomeFragment) currentFragment).g();
        }
    }

    public void switchTheme(boolean z) {
        MoEngage.getInstance().sendThemeChangeAttribute(z);
        Constants.l = z;
        u.a().a("Navigation Drawer", "Night Mode", z ? "White" : "Black");
        d.a().a("PREFERENCE_WHITE_THEME_ENABLED", Constants.l, false);
        restartApp(false);
    }

    public void restartApp(boolean z) {
        BaseGaanaFragment currentFragment = getCurrentFragment();
        if ((currentFragment instanceof ListingFragment) || (currentFragment instanceof DownloadDetailsFragment) || (currentFragment instanceof LocalMediaFragment) || (currentFragment instanceof MyMusicSearchResultFragment)) {
            popBackStackToHome();
            recreate();
            return;
        }
        Intent intent = new Intent(this, GaanaActivity.class);
        if (z) {
            intent.putExtra("show_toast_autonightmode", true);
        }
        intent.setFlags(71303168);
        startActivity(intent);
    }

    public void switchTheme(boolean z, boolean z2) {
        MoEngage.getInstance().sendThemeChangeAttribute(z);
        Constants.l = z;
        u.a().a("Navigation Drawer", "Night Mode", z ? "White" : "Black");
        d.a().a("PREFERENCE_WHITE_THEME_ENABLED", Constants.l, false);
        restartApp(z2);
    }

    private void handleAutoNightModeSettings(final boolean z) {
        if (this.mDeviceResManager.b("pref_show_auto_night_mode_dialog", false, false)) {
            this.mDeviceResManager.a("pref_auto_night_mode_on", false, false);
            switchTheme(z ^ 1);
            this.nightModeSwitch.setChecked(Constants.l ^ 1);
            return;
        }
        com.managers.af.a(this.mContext, null).a(new com.services.l.n() {
            public void onEnableAutoNow() {
                boolean z = false;
                GaanaActivity.this.mDeviceResManager.a("pref_auto_night_mode_on", true, false);
                if ((GaanaApplication.getInstance().isDayOrNightUsingTwilightCalculator() == 0) == Constants.l) {
                    z = true;
                }
                if (z) {
                    aj.a().a(GaanaActivity.this.mContext, GaanaActivity.this.getString(R.string.toast_auto_nigth_mode_activiated));
                    return;
                }
                GaanaActivity.this.switchTheme(z ^ 1, true);
                GaanaActivity.this.nightModeSwitch.setChecked(Constants.l ^ 1);
            }

            public void onChangeThemeOnly() {
                GaanaActivity.this.switchTheme(z ^ 1);
                GaanaActivity.this.nightModeSwitch.setChecked(Constants.l ^ 1);
            }
        });
    }

    private void turnDataSaveModeOn(boolean z) {
        GaanaApplication instance = GaanaApplication.getInstance();
        BaseGaanaFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof SettingsDetailFragment) {
            ((SettingsDetailFragment) currentFragment).a(z);
        }
        instance.setAppInDataSaveMode(z);
        this.mDeviceResManager.a("PREFERENCE_KEY_DATA_SAVE_MODE", z, false);
        Util.b("data_save_mode", z ? "1" : "0");
        if (z) {
            this.mDeviceResManager.a("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 0, true), true);
            this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_QUALITY", 0, true);
            Util.b("download_quality", "0");
            this.mDeviceResManager.a("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", this.mDeviceResManager.b("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false), false);
            this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false);
        } else {
            int b = this.mDeviceResManager.b("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", 0, true);
            this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_QUALITY", b, true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b);
            Util.b("download_quality", stringBuilder.toString());
            this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", this.mDeviceResManager.b("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", 10000, false), false);
        }
        PlayerManager.a I = PlayerManager.a(instance.getApplicationContext()).I();
        if (I != null) {
            I.j();
        }
    }

    public void updateMiniPlayerList() {
        if (this.mPlayer instanceof MiniPlayerFragment) {
            ((MiniPlayerFragment) this.mPlayer).a(PlayerManager.a(GaanaApplication.getContext()).n());
        } else if (this.mPlayer instanceof MiniPlayerFragmentV4) {
            ((MiniPlayerFragmentV4) this.mPlayer).a(PlayerManager.a(GaanaApplication.getContext()).n());
        }
    }

    private boolean checkForWriteStoragePermission() {
        if (h.c(this.mContext)) {
            return true;
        }
        displayLaunchFragment(SHOW_TAB_MYMUSIC, null);
        this.isMyMusicDeeplink = false;
        Toast.makeText(this.mContext, R.string.gaana_need_storage_permission_display_page, 1).show();
        return false;
    }

    public c getBottomNavigationBarHelper() {
        return this.mBottomNavigationBarHelper;
    }

    public CustomBottomNavigationView getBottomNavigationView() {
        return this.bottomNavigationView;
    }

    public boolean getCrossbuttonVisibility() {
        return this.crossButtonVisibility;
    }

    public void setCrossButtonVisibility(boolean z) {
        this.crossButtonVisibility = z;
    }

    public void setCoachmarkViewHidden(boolean z) {
        this.isCoachmarkViewHidden = z;
    }

    public void showAnimationToMyMusic() {
        if (!shouldHideAnimationToMyMusic() && !this.isDownArrowAnimationRunning) {
            if (this.mShowMyMusicCoachmark == null) {
                this.mShowMyMusicCoachmark = ((ViewStub) findViewById(R.id.stub_show_mymusic_animated)).inflate();
            }
            this.mShowMyMusicCoachmark.setVisibility(0);
            this.mShowMyMusicCoachmark.setTranslationY(0.0f);
            ((MarginLayoutParams) this.mShowMyMusicCoachmark.getLayoutParams()).rightMargin = Util.b(41);
            ((MarginLayoutParams) this.mShowMyMusicCoachmark.getLayoutParams()).bottomMargin = Util.b(110);
            this.mShowMyMusicCoachmark.animate().translationYBy((float) Util.b(80)).setDuration(1120).setListener(new AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    GaanaActivity.this.isDownArrowAnimationRunning = true;
                    if (!GaanaActivity.this.isMyMusicGlowAnimationRunning) {
                        GaanaActivity.this.showMyMusicGlowAnimation();
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    GaanaActivity.this.mShowMyMusicCoachmark.setVisibility(8);
                    GaanaActivity.this.isDownArrowAnimationRunning = false;
                }

                public void onAnimationCancel(Animator animator) {
                    GaanaActivity.this.mShowMyMusicCoachmark.setVisibility(8);
                    GaanaActivity.this.isDownArrowAnimationRunning = false;
                }
            }).start();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void showMyMusicGlowAnimation() {
        if (this.animatedMyMusicGlowView == null) {
            this.animatedMyMusicGlowView = ((ViewStub) findViewById(R.id.stub_mymusic_icon_animation)).inflate();
        }
        this.animatedMyMusicGlowView.setVisibility(0);
        ((MarginLayoutParams) this.animatedMyMusicGlowView.getLayoutParams()).rightMargin = Util.b(42);
        ((MarginLayoutParams) this.animatedMyMusicGlowView.getLayoutParams()).bottomMargin = Util.b(27);
        ImageView imageView = (ImageView) this.animatedMyMusicGlowView.findViewById(R.id.img_animated_vector);
        if (Constants.l) {
            this.iconGlowAnimation = AnimatedVectorDrawableCompat.create(this.mContext, R.drawable.animated_vector_bottom_nav_mymusic_white);
        } else {
            this.iconGlowAnimation = AnimatedVectorDrawableCompat.create(this.mContext, R.drawable.animated_vector_bottom_nav_mymusic);
        }
        this.iconGlowAnimation.registerAnimationCallback(new AnimationCallback() {
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                GaanaActivity.this.isMyMusicGlowAnimationRunning = true;
            }

            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                GaanaActivity.this.animatedMyMusicGlowView.setVisibility(8);
                GaanaActivity.this.isMyMusicGlowAnimationRunning = false;
            }
        });
        imageView.setImageDrawable(this.iconGlowAnimation);
        this.iconGlowAnimation.start();
    }

    public void hideAnimationToMyMusic() {
        if (this.mShowMyMusicCoachmark != null) {
            this.mShowMyMusicCoachmark.setVisibility(8);
            this.isDownArrowAnimationRunning = false;
            this.isMyMusicGlowAnimationRunning = false;
        }
    }

    public boolean shouldHideAnimationToMyMusic() {
        return (this.mFragment != null && ((this.fragmentTag == "mymusic" && !(this.mFragment instanceof DownloadDetailsFragment)) || ((this.mFragment instanceof ItemListingFragment) && this.title == "notifications"))) || isPlayerExpanded() || this.isCoachmarkViewHidden || isCuratedDownloadsDisplaying() || !hasWindowFocus();
    }

    public void showHideNewDownloadedSongCount() {
        if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode()) {
            if (this.mDownloadedSongsCount == null) {
                this.mDownloadedSongsCount = ((ViewStub) findViewById(R.id.stub_downloaded_songs_count)).inflate();
            }
            int i = 8;
            if (shouldHideDownloadedSongsCount()) {
                this.mDownloadedSongsCount.setVisibility(8);
                return;
            }
            int newDownloadsCount = getNewDownloadsCount();
            TextView textView = (TextView) this.mDownloadedSongsCount.findViewById(R.id.txt_downloaded_songs_count);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(newDownloadsCount);
            textView.setText(stringBuilder.toString());
            View view = this.mDownloadedSongsCount;
            if (newDownloadsCount > 0) {
                i = 0;
            }
            view.setVisibility(i);
        }
    }

    public void hideNewDownloadedSongCount() {
        if (this.mDownloadedSongsCount != null) {
            this.mDownloadedSongsCount.setVisibility(8);
        }
    }

    public boolean shouldHideDownloadedSongsCount() {
        return (this.mFragment != null && ((this.mFragment instanceof DownloadDetailsFragment) || ((this.mFragment instanceof ItemListingFragment) && this.title == "notifications"))) || isPlayerExpanded() || this.isCoachmarkViewHidden || isCuratedDownloadsDisplaying() || !hasWindowFocus();
    }

    public int getNewDownloadsCount() {
        if (!d.a().d("ORIGINAL_DOWNLOADS_COUNT", true)) {
            resetOriginalDownloadsCount();
        }
        int p = DownloadManager.c().p() - d.a().b("ORIGINAL_DOWNLOADS_COUNT", 0, true);
        if (p < 0) {
            resetOriginalDownloadsCount();
            p = -p;
            if (p > d.a().b("PREVIOUS_DELTA", 0, true)) {
                p = d.a().b("PREVIOUS_DELTA", 0, true);
            }
            return p;
        }
        d.a().a("PREVIOUS_DELTA", p, true);
        return p;
    }

    public void resetOriginalDownloadsCount() {
        d.a().a("ORIGINAL_DOWNLOADS_COUNT", DownloadManager.c().p(), true);
    }

    public boolean isMiniPlayerExpanded() {
        return this.isMiniPlayerExpanded;
    }

    public void setMiniPlayerExpanded(boolean z) {
        this.isMiniPlayerExpanded = z;
    }

    private void fetchHelpText() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/voice-search/help-text");
        uRLManager.a(com.models.c.class);
        uRLManager.b(Boolean.valueOf(true));
        com.i.i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof com.models.c) {
                    GaanaActivity.this.mVoiceHelpList = ((com.models.c) obj).a();
                }
            }
        }, uRLManager);
    }

    public List<com.models.c.a> getVoiceHelpList() {
        return this.mVoiceHelpList;
    }

    public boolean popBackStack() {
        return popBackStackImmediate();
    }

    public boolean popBackStackImmediate() {
        return this.mFragmentController.a();
    }

    public boolean popBackStackImmediate(String str) {
        return this.mFragmentController.a(str);
    }

    public boolean popBackStackImmediate(String str, int i) {
        return this.mFragmentController.a(str, i);
    }

    public boolean isPlayerExpanded() {
        return this.mFragmentController != null && this.mFragmentController.c();
    }

    public boolean isCuratedDownloadsDisplaying() {
        return (this.mFragment instanceof ListingFragment) && ((ListingFragment) this.mFragment).p();
    }

    public VideoPlayerView getOverlayFrameLayout() {
        return this.overlayExoview;
    }

    public FrameLayout getCompanionAdSlot() {
        return this.companionAdSlot;
    }

    public void addOverlayFrameLayout(VideoPlayerView videoPlayerView) {
        if (this.mDrawerLayout.isDrawerOpen(8388611) && findViewById(R.id.home_prescreen) != null && findViewById(R.id.home_prescreen).getVisibility() == 0) {
            this.frameContainer.setTag(Boolean.valueOf(Constants.aZ));
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.home_prescreen);
            if (findFragmentById != null) {
                ((PreScreenFragment) findFragmentById).a();
            }
            closeDrawer();
        }
        this.overlayExoview = videoPlayerView;
        this.frameContainer.addView(videoPlayerView);
        LayoutParams layoutParams = (LayoutParams) videoPlayerView.getLayoutParams();
        layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.status_bar_height), 0, 0);
        layoutParams.addRule(12);
    }

    public void removeVideoView(VideoPlayerView videoPlayerView) {
        this.frameContainer.removeView(videoPlayerView);
        this.overlayExoview = null;
        if (this.frameContainer.getTag() != null) {
            this.frameContainer.setTag(null);
            openDrawers(false);
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.home_prescreen);
            if (findFragmentById != null) {
                ((PreScreenFragment) findFragmentById).b();
            }
        }
    }

    public void addCompanionAdSlot(FrameLayout frameLayout) {
        if (this.mDrawerLayout.isDrawerOpen(8388611) && findViewById(R.id.home_prescreen) != null && findViewById(R.id.home_prescreen).getVisibility() == 0) {
            this.frameContainer.setTag(Boolean.valueOf(Constants.aZ));
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.home_prescreen);
            if (findFragmentById != null) {
                ((PreScreenFragment) findFragmentById).a();
            }
            closeDrawer();
        }
        this.companionAdSlot = frameLayout;
        this.frameContainer.addView(frameLayout);
        LayoutParams layoutParams = (LayoutParams) frameLayout.getLayoutParams();
        layoutParams.setMargins(0, Util.b(30), 0, Util.b(150));
        layoutParams.addRule(13);
        com.managers.f.v().c(System.currentTimeMillis());
    }

    public void removeCompanionAdSlot(FrameLayout frameLayout) {
        this.frameContainer.removeView(frameLayout);
        this.companionAdSlot = null;
        if (this.frameContainer.getTag() != null) {
            this.frameContainer.setTag(null);
            openDrawers(false);
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.home_prescreen);
            if (findFragmentById != null) {
                ((PreScreenFragment) findFragmentById).b();
            }
        }
    }

    public LinearLayout getBackgroundAdSlot() {
        return this.backgroundAdSlot;
    }

    public RecycledViewPool getViewPool() {
        if (this.mViewPool == null) {
            this.mViewPool = new i();
        }
        return this.mViewPool;
    }

    public boolean isJukeSessionFragment() {
        if (this.mFragment instanceof JukePartyFragment) {
            String sessionId = ((JukePartyFragment) this.mFragment).getSessionId();
            CharSequence businessObjId = JukeSessionManager.getInstance().getJukeSessionPlaylist() != null ? JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId() : "";
            if (!TextUtils.isEmpty(businessObjId)) {
                return businessObjId.equals(sessionId);
            }
        }
        return false;
    }

    public void refreshPreScreen() {
        if (Constants.aZ) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.home_prescreen);
            if (findFragmentById != null) {
                ((PreScreenFragment) findFragmentById).refreshListView();
            }
        }
    }

    private void makeVideoFeedCategoriesRequest() {
        URLManager uRLManager = new URLManager();
        uRLManager.c(0);
        uRLManager.a(VideoFeedMetaData.class);
        uRLManager.a("https://apiv2.gaana.com/video/metadata");
        com.i.i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }
}

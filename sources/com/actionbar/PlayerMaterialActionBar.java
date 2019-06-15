package com.actionbar;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.PlayerRadioFragmentV4;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadSyncPopupItemView;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.o;
import com.managers.u;
import com.models.PlayerTrack;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.l.as;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerMaterialActionBar extends RelativeLayout implements OnMenuItemClickListener, OnClickListener, DownloadPopupListener, a {
    BaseFragment a;
    private Context b;
    private LayoutInflater c;
    private Toolbar d;
    private GestureDetectorCompat e;
    private View f;

    public enum PlayerVersion {
        PlayerV2,
        PlayerV4,
        PlayerVideo
    }

    public PlayerMaterialActionBar(Context context) {
        super(context);
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.action_player, this);
        a();
    }

    public PlayerMaterialActionBar(Context context, PlayerVersion playerVersion) {
        super(context);
        this.b = context;
        this.c = LayoutInflater.from(context);
        if (playerVersion == PlayerVersion.PlayerV2) {
            this.c.inflate(R.layout.action_player_v2, this);
        } else if (playerVersion == PlayerVersion.PlayerV4) {
            this.c.inflate(R.layout.action_player_v4, this);
        } else if (playerVersion == PlayerVersion.PlayerVideo) {
            this.c.inflate(R.layout.action_player_video_feed, this);
        } else {
            this.c.inflate(R.layout.action_player, this);
        }
        a();
    }

    public PlayerMaterialActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = ((ContextWrapper) context).getBaseContext();
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.action_player, this);
        a();
    }

    public PlayerMaterialActionBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = ((ContextWrapper) context).getBaseContext();
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.action_player, this);
        a();
    }

    private void a() {
        this.a = ((GaanaActivity) this.b).getmCurrentPlayerFragment();
        this.e = new GestureDetectorCompat(this.b, new OnGestureListener() {
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

            public boolean onDown(MotionEvent motionEvent) {
                if (PlayerMaterialActionBar.this.a instanceof PlayerFragmentV4) {
                    if (((PlayerFragmentV4) PlayerMaterialActionBar.this.a).k() == null || !((PlayerFragmentV4) PlayerMaterialActionBar.this.a).k().e()) {
                        ((PlayerFragmentV4) PlayerMaterialActionBar.this.a).a(((PlayerFragmentV4) PlayerMaterialActionBar.this.a).g(), false);
                    } else {
                        ((PlayerFragmentV4) PlayerMaterialActionBar.this.a).k().g();
                    }
                }
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 120.0f) {
                    return false;
                }
                if (PlayerMaterialActionBar.this.a instanceof PlayerFragmentV2) {
                    if (!((PlayerFragmentV2) PlayerMaterialActionBar.this.a).f().e()) {
                        ((GaanaActivity) PlayerMaterialActionBar.this.b).popBackStackImmediate();
                        return true;
                    }
                } else if (PlayerMaterialActionBar.this.a instanceof PlayerFragmentV4) {
                    if (!((PlayerFragmentV4) PlayerMaterialActionBar.this.a).k().e()) {
                        ((GaanaActivity) PlayerMaterialActionBar.this.b).popBackStackImmediate();
                        return true;
                    }
                } else if (PlayerMaterialActionBar.this.a instanceof PlayerRadioFragmentV4) {
                    ((GaanaActivity) PlayerMaterialActionBar.this.b).popBackStackImmediate();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.menu_icon).setOnClickListener(this);
        if ((this.a instanceof PlayerFragmentV4) || (this.a instanceof PlayerRadioFragmentV4)) {
            findViewById(R.id.menu_icon_back).setOnClickListener(this);
            findViewById(R.id.report_lrc_button).setOnClickListener(this);
        }
    }

    public void setToolbar(Toolbar toolbar) {
        this.d = toolbar;
        this.d.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return PlayerMaterialActionBar.this.e.onTouchEvent(motionEvent);
            }
        });
        toolbar.setOnMenuItemClickListener(this);
        final MenuItem findItem = this.d.getMenu().findItem(R.id.menu_add_to_playlist);
        if (findItem != null) {
            ImageView imageView = (ImageView) findItem.getActionView();
            imageView.setVisibility(8);
            if (imageView != null) {
                imageView.setPadding(this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0, this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0);
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PlayerMaterialActionBar.this.f = view;
                        PlayerMaterialActionBar.this.a(findItem.getItemId());
                    }
                });
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_add_to_playlist /*2131297697*/:
                b();
                return;
            case R.id.menu_icon /*2131297709*/:
                ((GaanaActivity) this.b).onBackPressed();
                an.a().a("click", "ac", "", "player", "", "close", "", "");
                u.a().b("VideoFeed", "Close");
                return;
            case R.id.menu_icon_back /*2131297710*/:
                if (this.a instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) this.a).c();
                    return;
                } else if (this.a instanceof PlayerRadioFragmentV4) {
                    ((PlayerRadioFragmentV4) this.a).c();
                    return;
                } else {
                    return;
                }
            case R.id.menu_option /*2131297716*/:
                if (PlayerManager.a(this.b).m() == PlayerType.GAANA) {
                    PopupWindowView.getInstance(this.b, null).contextPopupWindowPlayer();
                } else {
                    Track F = PlayerManager.a(this.b).F();
                    if (F == null) {
                        F = PlayerManager.a(this.b).i().b();
                    }
                    if (!(F == null || F.getBusinessObjType() == null || ad.a(this.b).p().booleanValue())) {
                        PopupWindowView.getInstance(this.b, null).contextPopupWindow(F, true, false);
                    }
                }
                u.a().b("Player", "Context Menu tapped");
                return;
            case R.id.report_lrc_button /*2131298225*/:
                if (this.a instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) this.a).w();
                    return;
                } else if (this.a instanceof PlayerRadioFragmentV4) {
                    ((PlayerRadioFragmentV4) this.a).l();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void a(int i) {
        PlayerManager.a(this.b);
        switch (i) {
            case R.id.menu_add_to_playlist /*2131297697*/:
                BusinessObject F = PlayerManager.a(this.b).F();
                if (F == null) {
                    F = PlayerManager.a(this.b).i().b();
                }
                F.setBusinessObjType(BusinessObjectType.Tracks);
                af a = af.a(this.b, null);
                a.a("Player Screen");
                a.b(F.getBusinessObjId());
                a.a((int) R.id.favoriteMenu, F);
                if (this.d != null) {
                    Menu menu = this.d.getMenu();
                    if (menu != null) {
                        ImageView imageView = (ImageView) menu.findItem(R.id.menu_add_to_playlist).getActionView();
                        if (F == null || !F.isFavorite().booleanValue()) {
                            TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(R.styleable.VectorDrawables);
                            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                            obtainStyledAttributes.recycle();
                            return;
                        }
                        imageView.setImageResource(R.drawable.vector_more_option_favorited);
                        imageView.setPadding(this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0, this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0);
                        if (this.f != null) {
                            Animation loadAnimation = AnimationUtils.loadAnimation(this.b, R.anim.favorite_tap_animation);
                            loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                            this.f.startAnimation(loadAnimation);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case R.id.menu_icon /*2131297709*/:
                ((GaanaActivity) this.b).setSlideUpPanel(true);
                return;
            case R.id.menu_icon_back /*2131297710*/:
                if (this.a instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) this.a).c();
                    return;
                } else if (this.a instanceof PlayerRadioFragmentV4) {
                    ((PlayerRadioFragmentV4) this.a).c();
                    return;
                } else {
                    return;
                }
            case R.id.menu_option /*2131297716*/:
                Track F2 = PlayerManager.a(this.b).F();
                if (F2 == null || PlayerManager.a(this.b).m() == PlayerType.GAANA_RADIO) {
                    F2 = PlayerManager.a(this.b).i().b();
                }
                if (!(F2 == null || F2.getBusinessObjType() == null || ad.a(this.b).p().booleanValue())) {
                    PopupWindowView instance = PopupWindowView.getInstance(this.b, null);
                    instance.setDownloadPopupListener(this);
                    instance.contextPopupWindow(F2, true, this, false);
                }
                u.a().b("Player", "Context Menu tapped");
                return;
            case R.id.report_lrc_button /*2131298225*/:
                if (this.a instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) this.a).w();
                    return;
                } else if (this.a instanceof PlayerRadioFragmentV4) {
                    ((PlayerRadioFragmentV4) this.a).l();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void b() {
        u.a().a("PlayerQueue", "Save Queue", "PlayerQueue - Save Queue");
        ArrayList n = PlayerManager.a(this.b).n();
        if (n != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(((PlayerTrack) it.next()).a(true));
            }
            ap.a().a(this.b, arrayList, false);
            return;
        }
        aj.a().a(this.b, getContext().getString(R.string.no_songs_to_add));
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        a(menuItem.getItemId());
        return false;
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        if (this.d != null) {
            Menu menu = this.d.getMenu();
            if (menu != null) {
                ImageView imageView = (ImageView) menu.findItem(R.id.menu_add_to_playlist).getActionView();
                Track F = PlayerManager.a(this.b).F();
                if (F == null) {
                    F = PlayerManager.a(this.b).i().b();
                }
                if (F == null || !F.isFavorite().booleanValue()) {
                    TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                    obtainStyledAttributes.recycle();
                    return;
                }
                imageView.setImageResource(R.drawable.vector_more_option_favorited);
            }
        }
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        a(str, businessObject);
    }

    public void a(final String str, BusinessObject businessObject) {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(str));
        businessObject = (Track) businessObject;
        if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            ((BaseActivity) this.b).displayFeatureNotAvailableOfflineDialog(this.b.getString(R.string.this_feature));
        } else if (Util.j(this.b)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.b).currentScreen);
            stringBuilder.append(" - ");
            stringBuilder.append(((BaseActivity) this.b).currentFavpage);
            stringBuilder.append(" - Download");
            ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Download", stringBuilder.toString());
            if (e == DownloadStatus.DOWNLOADED) {
                new CustomDialogView(this.b, this.b.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        PlayerMaterialActionBar.this.a(businessObject);
                    }
                }).show();
            } else if (e == DownloadStatus.QUEUED) {
                new CustomDialogView(this.b, this.b.getResources().getString(R.string.toast_remove_queue_song), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(str);
                        PlayerMaterialActionBar.this.c();
                        DownloadManager.c().d();
                    }
                }).show();
            } else if (e == DownloadStatus.DOWNLOADING) {
                new CustomDialogView(this.b, this.b.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(str);
                        PlayerMaterialActionBar.this.c();
                        DownloadManager.c().d();
                    }
                }).show();
            } else {
                a(businessObject);
            }
        } else {
            ap.a().f(this.b);
        }
    }

    private void a(Track track) {
        DownloadManager.c().d(track.getBusinessObjId());
        c();
        DownloadManager.c().d();
    }

    private void c() {
        a(true);
    }

    public void a(boolean z) {
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.b).getCurrentFragment();
        if ((currentFragment instanceof DownloadDetailsFragment) && z) {
            ((DownloadDetailsFragment) currentFragment).d();
        } else {
            ((BaseActivity) this.b).refreshListView();
        }
    }

    private void a(BusinessObject businessObject) {
        a(businessObject, null);
    }

    /* Access modifiers changed, original: protected */
    public void a(final BusinessObject businessObject, final View view) {
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.b).getCurrentFragment();
        if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            ((BaseActivity) this.b).hideProgressDialog();
            ((BaseActivity) this.b).displayFeatureNotAvailableOfflineDialog(this.b.getString(R.string.this_feature));
        } else if (Util.j(this.b)) {
            if (!ap.a().j()) {
                boolean z = businessObject instanceof Track;
                if (!(z && ((Track) businessObject).isFreeDownloadEnabled())) {
                    ((BaseActivity) this.b).hideProgressDialog();
                    PopupWindowView.getInstance(this.b, currentFragment).dismiss(true);
                    String str = null;
                    if (view != null) {
                        str = this.b.getString(R.string.topsong_english);
                    }
                    String str2 = "";
                    if (ap.a().l()) {
                        str2 = z ? "tr" : "pl";
                    }
                    Util.b(this.b, str2, str, new as() {
                        public void onTrialSuccess() {
                            PlayerMaterialActionBar.this.a(view, businessObject);
                            currentFragment.refreshDataandAds();
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerMaterialActionBar.this.b).updateSideBar();
                        }
                    });
                }
            }
            a(view, businessObject);
        } else {
            ((BaseActivity) this.b).hideProgressDialog();
            ap.a().f(this.b);
        }
    }

    private void a(View view, BusinessObject businessObject) {
        Util.i(this.b, "Download");
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.b).getCurrentFragment();
        if (Util.k(GaanaApplication.getContext()) == 0) {
            ((BaseActivity) this.b).hideProgressDialog();
            d a = d.a();
            boolean b = a.b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (!a.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                ((BaseActivity) this.b).mDialog = new f(this.b);
                ((BaseActivity) this.b).mDialog.a(this.b.getString(R.string.gaana_plus_feature), this.b.getString(R.string.dlg_msg_sync_data_disablde), Boolean.valueOf(true), this.b.getString(R.string.settings_text), this.b.getString(R.string.dlg_msg_cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                            PopupWindowView.getInstance(PlayerMaterialActionBar.this.b, currentFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((BaseActivity) PlayerMaterialActionBar.this.b).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                        PopupWindowView.getInstance(PlayerMaterialActionBar.this.b, currentFragment).dismiss(true);
                        ((GaanaActivity) PlayerMaterialActionBar.this.b).displayFragment(settingsDetailFragment);
                    }
                });
                return;
            } else if (b) {
                if (!Constants.V) {
                    aj.a().a(this.b, this.b.getString(R.string.schedule_songs_queue_msg));
                    Constants.V = true;
                }
            } else if (!Constants.W) {
                Constants.W = true;
                aj.a().a(this.b, this.b.getString(R.string.schedule_cta_text), this.b.getString(R.string.schedule_download_msg), new OnClickListener() {
                    public void onClick(View view) {
                        if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                            PopupWindowView.getInstance(PlayerMaterialActionBar.this.b, currentFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        PopupWindowView.getInstance(PlayerMaterialActionBar.this.b, currentFragment).dismiss(true);
                        ((GaanaActivity) PlayerMaterialActionBar.this.b).displayFragment(settingsDetailFragment);
                    }
                });
            }
        }
        b(view, businessObject);
    }

    private void b(View view, BusinessObject businessObject) {
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.b).getCurrentFragment();
        if (!DownloadManager.c().v()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Util.w(PlayerMaterialActionBar.this.b);
                }
            });
        } else if (Constants.t() && !Constants.U) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    PopupWindowView.getInstance(PlayerMaterialActionBar.this.b, currentFragment).dismiss(true);
                    ((GaanaActivity) PlayerMaterialActionBar.this.b).setSlideUpPanel(true);
                    new DownloadSyncPopupItemView(PlayerMaterialActionBar.this.b).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (view != null) {
            view.setVisibility(0);
            if (businessObject instanceof Track) {
                String trackTitle = ((Track) businessObject).getTrackTitle();
                aj a = aj.a();
                Context context = this.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.getString(R.string.downloading_text));
                stringBuilder.append(trackTitle);
                a.a(context, stringBuilder.toString());
            }
        }
        if (businessObject instanceof Track) {
            ((BaseActivity) this.b).hideProgressDialog();
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            Track track;
            Playlist c;
            ArrayList g;
            if (e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                if (ap.a().m()) {
                    track = (Track) businessObject;
                    c = DownloadManager.c().c(track);
                    if (c != null) {
                        g = DownloadManager.c().g(Integer.parseInt(c.getBusinessObjId()));
                        if (g == null || !g.contains(Integer.valueOf(Integer.parseInt(businessObject.getBusinessObjId())))) {
                            g = new ArrayList();
                            g.add(track);
                            a(c, g);
                        } else {
                            DownloadManager.c().b(track);
                            if (!businessObject.isFavorite().booleanValue()) {
                                businessObject.setFavorite(Boolean.valueOf(true));
                                ap.a().a(this.b, businessObject, true);
                            }
                        }
                    }
                } else {
                    DownloadManager.c().b((Track) businessObject);
                }
            } else if (ap.a().m()) {
                track = (Track) businessObject;
                c = DownloadManager.c().c(track);
                if (!(c == null || c == null)) {
                    g = DownloadManager.c().g(Integer.parseInt(c.getBusinessObjId()));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(track);
                    if (g == null || !g.contains(Integer.valueOf(Integer.parseInt(businessObject.getBusinessObjId())))) {
                        a(c, arrayList);
                    } else {
                        DownloadManager.c().b(arrayList, Integer.parseInt(c.getBusinessObjId()), false);
                        if (!businessObject.isFavorite().booleanValue()) {
                            businessObject.setFavorite(Boolean.valueOf(true));
                            ap.a().a(this.b, businessObject, true);
                        }
                    }
                }
            } else {
                DownloadManager.c().a((Track) businessObject, this.b);
            }
            onFavoriteCompleted(businessObject, true);
            a(false);
        }
    }

    private void a(final Playlist playlist, final ArrayList<Track> arrayList) {
        ((BaseActivity) this.b).showProgressDialog(Boolean.valueOf(true));
        h.a().a(new TaskListner() {
            private PLAYLIST_STATUS d;

            public void onBackGroundTaskCompleted() {
                ((BaseActivity) PlayerMaterialActionBar.this.b).hideProgressDialog();
                o a = o.a();
                String[] strArr = new String[2];
                strArr[0] = "type=playlist&subtype=playlist_detail";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id=");
                stringBuilder.append(playlist.getBusinessObjId());
                strArr[1] = stringBuilder.toString();
                a.a(strArr);
                int a2 = Util.a(playlist.getBusinessObjId());
                if (a2 != 0 && DownloadManager.c().b(playlist).booleanValue() && arrayList != null && this.d == PLAYLIST_STATUS.SUCCESS) {
                    DownloadManager.c().b(arrayList, a2, true);
                    if (!((Track) arrayList.get(0)).isFavorite().booleanValue()) {
                        ((Track) arrayList.get(0)).setFavorite(Boolean.valueOf(true));
                        ap.a().a(PlayerMaterialActionBar.this.b, (BusinessObject) arrayList.get(0), true);
                        PlayerMaterialActionBar.this.onFavoriteCompleted((BusinessObject) arrayList.get(0), true);
                    }
                }
                if (this.d == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(a2);
                } else if (this.d == PLAYLIST_STATUS.MAX_LIMIT_REACHED) {
                    a2 = DownloadManager.c().j(a2);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(PlayerMaterialActionBar.this.b.getResources().getString(R.string.gaana_mini_artist_max_limit_1));
                    stringBuilder2.append(" ");
                    stringBuilder2.append(a2);
                    stringBuilder2.append(" songs.");
                    String stringBuilder3 = stringBuilder2.toString();
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(PlayerMaterialActionBar.this.b.getResources().getString(R.string.gaana_mini_artist_max_limit_2));
                    stringBuilder2.append(" ");
                    stringBuilder2.append(playlist.getName());
                    stringBuilder2.append(" playlist");
                    String stringBuilder4 = stringBuilder2.toString();
                    aj a3 = aj.a();
                    Context a4 = PlayerMaterialActionBar.this.b;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append(stringBuilder3);
                    stringBuilder5.append(stringBuilder4);
                    a3.a(a4, stringBuilder5.toString());
                } else {
                    aj.a().a(PlayerMaterialActionBar.this.b, PlayerMaterialActionBar.this.b.getResources().getString(R.string.some_error_occured));
                }
            }

            public void doBackGroundTask() {
                if (arrayList != null) {
                    String[] strArr = new String[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        strArr[i] = ((Track) arrayList.get(i)).getBusinessObjId();
                    }
                    this.d = PlaylistSyncManager.getInstance().addToPlaylistGaanaMini(playlist, arrayList);
                    return;
                }
                this.d = PLAYLIST_STATUS.FAILED;
            }
        }, -1);
    }
}

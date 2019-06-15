package com.gaanasocial.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.ProfileFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.SocialFeed.FeedData;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadSyncPopupItemView;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.n;
import com.managers.o;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.l.as;
import com.services.l.t;
import com.utilities.Util;
import java.util.ArrayList;

public class CardBottomLayout extends LinearLayout implements OnClickListener, DownloadPopupListener, com.managers.ap.a {
    private FeedData a;
    private String b;
    private LayoutInflater c;
    private Context d;
    private BaseGaanaFragment e;
    private BusinessObject f;
    private a g;
    private Drawable h;
    private t i;

    public static class a extends ViewHolder {
        private CrossFadeImageView a;
        private CrossFadeImageView b;
        private CrossFadeImageView c;

        public a(View view) {
            super(view);
            this.a = (CrossFadeImageView) view.findViewById(R.id.crown_favorite);
            this.b = (CrossFadeImageView) view.findViewById(R.id.crown_share);
            this.c = (CrossFadeImageView) view.findViewById(R.id.crown_menu);
        }
    }

    public CardBottomLayout(Context context, BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject, t tVar, String str, FeedData feedData) {
        super(context);
        this.d = context;
        this.e = baseGaanaFragment;
        this.f = businessObject;
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.card_bottom_layout, this);
        this.a = feedData;
        this.b = str;
        this.i = tVar;
    }

    public void a(ViewHolder viewHolder) {
        this.g = (a) viewHolder;
        new int[1][0] = R.attr.heart_grey;
        TypedArray obtainStyledAttributes = this.d.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.h = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(18, -1));
        if (this.f == null || !this.f.isFavorite().booleanValue()) {
            this.g.a.setImageDrawable(this.h);
            obtainStyledAttributes.recycle();
        } else {
            this.h = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
            obtainStyledAttributes.recycle();
            this.g.a.setImageDrawable(this.h);
        }
        if (this.f != null) {
            this.g.a.setOnClickListener(this);
            this.g.b.setOnClickListener(this);
            this.g.c.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        ((BaseActivity) this.d).setSendGAScreenName(this.a == null ? this.b : "SocialFeed_Activity_Play");
        switch (view.getId()) {
            case R.id.crown_favorite /*2131296816*/:
                af a = af.a(this.d, this.e);
                a.a("Social");
                a.b(this.f.getBusinessObjId());
                a.a((int) R.id.favoriteMenu, this.f, (com.managers.ap.a) this);
                return;
            case R.id.crown_menu /*2131296817*/:
                PopupWindowView instance = PopupWindowView.getInstance(this.d, this.e);
                instance.setDownloadPopupListener(this);
                instance.contextPopupWindow(this.f, false, this, false);
                return;
            case R.id.crown_share /*2131296819*/:
                ap.a().a(this.d, this.f, this.e);
                return;
            default:
                return;
        }
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        if (n.a().a(businessObject)) {
            TypedArray obtainStyledAttributes = this.d.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.h = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
            obtainStyledAttributes.recycle();
            this.g.a.setImageDrawable(this.h);
            b(true);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.d, R.anim.favorite_tap_animation);
            loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
            this.g.a.clearAnimation();
            this.g.a.startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (CardBottomLayout.this.e instanceof ProfileFragment) {
                        CardBottomLayout.this.e.refreshListView();
                    } else if (CardBottomLayout.this.i != null) {
                        CardBottomLayout.this.i.a(1001, -1);
                    }
                }
            });
            return;
        }
        this.g.a.setImageDrawable(this.h);
        b(false);
        if (this.i != null) {
            this.i.a(1001, -1);
        }
    }

    private void b(boolean z) {
        if (this.a != null) {
            ArrayList feedEntity = this.a.getFeedEntity();
            if (feedEntity != null && feedEntity.size() > 0) {
                Item item = (Item) feedEntity.get(0);
                if (z) {
                    item.setFavoriteCount(item.getFavoriteCount() + 1);
                } else if (item.getFavoriteCount() > 0) {
                    item.setFavoriteCount(item.getFavoriteCount() - 1);
                }
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
            ((BaseActivity) this.d).displayFeatureNotAvailableOfflineDialog(this.d.getString(R.string.this_feature));
        } else if (Util.j(this.d)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.d).currentScreen);
            stringBuilder.append(" - ");
            stringBuilder.append(((BaseActivity) this.d).currentFavpage);
            stringBuilder.append(" - Download");
            ((BaseActivity) this.d).sendGAEvent(((BaseActivity) this.d).currentScreen, "Download", stringBuilder.toString());
            if (e == DownloadStatus.DOWNLOADED) {
                new CustomDialogView(this.d, this.d.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        CardBottomLayout.this.a(businessObject);
                    }
                }).show();
            } else if (e == DownloadStatus.QUEUED) {
                new CustomDialogView(this.d, this.d.getResources().getString(R.string.toast_remove_queue_song), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(str);
                        CardBottomLayout.this.a();
                        DownloadManager.c().d();
                    }
                }).show();
            } else if (e == DownloadStatus.DOWNLOADING) {
                new CustomDialogView(this.d, this.d.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(str);
                        CardBottomLayout.this.a();
                        DownloadManager.c().d();
                    }
                }).show();
            } else {
                a(businessObject);
            }
        } else {
            ap.a().f(this.d);
        }
    }

    private void a(Track track) {
        DownloadManager.c().d(track.getBusinessObjId());
        a();
        DownloadManager.c().d();
    }

    private void a() {
        a(true);
    }

    public void a(boolean z) {
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.d).getCurrentFragment();
        if ((currentFragment instanceof DownloadDetailsFragment) && z) {
            ((DownloadDetailsFragment) currentFragment).d();
        } else {
            ((BaseActivity) this.d).refreshListView();
        }
    }

    private void a(BusinessObject businessObject) {
        a(businessObject, null);
    }

    /* Access modifiers changed, original: protected */
    public void a(final BusinessObject businessObject, final View view) {
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.d).getCurrentFragment();
        if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            ((BaseActivity) this.d).hideProgressDialog();
            ((BaseActivity) this.d).displayFeatureNotAvailableOfflineDialog(this.d.getString(R.string.this_feature));
        } else if (Util.j(this.d)) {
            if (!ap.a().j()) {
                boolean z = businessObject instanceof Track;
                if (!(z && ((Track) businessObject).isFreeDownloadEnabled())) {
                    ((BaseActivity) this.d).hideProgressDialog();
                    PopupWindowView.getInstance(this.d, currentFragment).dismiss(true);
                    String str = null;
                    if (view != null) {
                        str = this.d.getString(R.string.topsong_english);
                    }
                    String str2 = "";
                    if (ap.a().l()) {
                        str2 = z ? "tr" : "pl";
                    }
                    Util.b(this.d, str2, str, new as() {
                        public void onTrialSuccess() {
                            CardBottomLayout.this.a(view, businessObject);
                            currentFragment.refreshDataandAds();
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) CardBottomLayout.this.d).updateSideBar();
                        }
                    });
                }
            }
            a(view, businessObject);
        } else {
            ((BaseActivity) this.d).hideProgressDialog();
            ap.a().f(this.d);
        }
    }

    private void a(View view, BusinessObject businessObject) {
        Util.i(this.d, "Download");
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.d).getCurrentFragment();
        if (Util.k(GaanaApplication.getContext()) == 0) {
            ((BaseActivity) this.d).hideProgressDialog();
            d a = d.a();
            boolean b = a.b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (!a.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                ((BaseActivity) this.d).mDialog = new f(this.d);
                ((BaseActivity) this.d).mDialog.a(this.d.getString(R.string.gaana_plus_feature), this.d.getString(R.string.dlg_msg_sync_data_disablde), Boolean.valueOf(true), this.d.getString(R.string.settings_text), this.d.getString(R.string.dlg_msg_cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                            PopupWindowView.getInstance(CardBottomLayout.this.d, currentFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((BaseActivity) CardBottomLayout.this.d).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                        PopupWindowView.getInstance(CardBottomLayout.this.d, currentFragment).dismiss(true);
                        ((GaanaActivity) CardBottomLayout.this.d).displayFragment(settingsDetailFragment);
                    }
                });
                return;
            } else if (b) {
                if (!Constants.V) {
                    aj.a().a(this.d, this.d.getString(R.string.schedule_songs_queue_msg));
                    Constants.V = true;
                }
            } else if (!Constants.W) {
                Constants.W = true;
                aj.a().a(this.d, this.d.getString(R.string.schedule_cta_text), this.d.getString(R.string.schedule_download_msg), new OnClickListener() {
                    public void onClick(View view) {
                        if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                            PopupWindowView.getInstance(CardBottomLayout.this.d, currentFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        PopupWindowView.getInstance(CardBottomLayout.this.d, currentFragment).dismiss(true);
                        ((GaanaActivity) CardBottomLayout.this.d).displayFragment(settingsDetailFragment);
                    }
                });
            }
        }
        b(view, businessObject);
    }

    private void b(View view, BusinessObject businessObject) {
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.d).getCurrentFragment();
        if (!DownloadManager.c().v()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Util.w(CardBottomLayout.this.d);
                }
            });
        } else if (Constants.t() && !Constants.U) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    PopupWindowView.getInstance(CardBottomLayout.this.d, currentFragment).dismiss(true);
                    ((GaanaActivity) CardBottomLayout.this.d).setSlideUpPanel(true);
                    new DownloadSyncPopupItemView(CardBottomLayout.this.d).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (view != null) {
            view.setVisibility(0);
            if (businessObject instanceof Track) {
                String trackTitle = ((Track) businessObject).getTrackTitle();
                aj a = aj.a();
                Context context = this.d;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.d.getString(R.string.downloading_text));
                stringBuilder.append(trackTitle);
                a.a(context, stringBuilder.toString());
            }
        }
        if (businessObject instanceof Track) {
            ((BaseActivity) this.d).hideProgressDialog();
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
                                ap.a().a(this.d, businessObject, true);
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
                            ap.a().a(this.d, businessObject, true);
                        }
                    }
                }
            } else {
                DownloadManager.c().a((Track) businessObject, this.d);
            }
            onFavoriteCompleted(businessObject, true);
            a(false);
        }
    }

    private void a(final Playlist playlist, final ArrayList<Track> arrayList) {
        ((BaseActivity) this.d).showProgressDialog(Boolean.valueOf(true));
        h.a().a(new TaskListner() {
            private PLAYLIST_STATUS d;

            public void onBackGroundTaskCompleted() {
                ((BaseActivity) CardBottomLayout.this.d).hideProgressDialog();
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
                        ap.a().a(CardBottomLayout.this.d, (BusinessObject) arrayList.get(0), true);
                        CardBottomLayout.this.onFavoriteCompleted((BusinessObject) arrayList.get(0), true);
                    }
                }
                if (this.d == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(a2);
                } else if (this.d == PLAYLIST_STATUS.MAX_LIMIT_REACHED) {
                    a2 = DownloadManager.c().j(a2);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(CardBottomLayout.this.d.getResources().getString(R.string.gaana_mini_artist_max_limit_1));
                    stringBuilder2.append(" ");
                    stringBuilder2.append(a2);
                    stringBuilder2.append(" songs.");
                    String stringBuilder3 = stringBuilder2.toString();
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(CardBottomLayout.this.d.getResources().getString(R.string.gaana_mini_artist_max_limit_2));
                    stringBuilder2.append(" ");
                    stringBuilder2.append(playlist.getName());
                    stringBuilder2.append(" playlist");
                    String stringBuilder4 = stringBuilder2.toString();
                    aj a3 = aj.a();
                    Context d = CardBottomLayout.this.d;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append(stringBuilder3);
                    stringBuilder5.append(stringBuilder4);
                    a3.a(d, stringBuilder5.toString());
                } else {
                    aj.a().a(CardBottomLayout.this.d, CardBottomLayout.this.d.getResources().getString(R.string.some_error_occured));
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

package com.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.collapsible_header.ObservableRecyclerView;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserMessage;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.ShareableSongsView;
import com.gaana.view.item.ShareableSongsView.ShareableSongsHolder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.d;
import com.services.f;
import com.services.l.as;
import com.utilities.Util;
import com.views.i;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class PersonaDetailsFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, IAddListItemView, c {
    private static float b = 1.2f;
    private DisplayMetrics A;
    private boolean B = false;
    private String C = "";
    private String D = "";
    private LinearLayout E;
    private TextView F;
    private PublisherAdView G;
    private Bundle H = null;
    private String I = "";
    private ADSTATUS J;
    private b K = new b() {
        public void a(ADSTATUS adstatus) {
            PersonaDetailsFragment.this.J = adstatus;
        }

        public void b(ADSTATUS adstatus) {
            PersonaDetailsFragment.this.J = adstatus;
        }

        public void c(ADSTATUS adstatus) {
            PersonaDetailsFragment.this.J = adstatus;
        }

        public void d(ADSTATUS adstatus) {
            PersonaDetailsFragment.this.J = adstatus;
        }
    };
    LinearLayout a = null;
    private View c = null;
    private ListingComponents d;
    private BusinessObject e;
    private ObservableRecyclerView f;
    private CrossFadeImageView g;
    private CrossFadeImageView h;
    private CustomListAdapter i;
    private BaseItemView j;
    private SwipeRefreshLayout k;
    private boolean l = false;
    private int m = 0;
    private DetailsMaterialActionBar n;
    private Toolbar o;
    private ProgressBar p;
    private int q;
    private View r;
    private ArrayList<BusinessObject> s = new ArrayList();
    private ArrayList<BusinessObject> t = new ArrayList();
    private String u = null;
    private String v = "";
    private boolean w = false;
    private long x;
    private String y = "";
    private String z = "";

    public void l() {
    }

    public void onClick(View view) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public static Bundle a(BusinessObject businessObject, String str, String[] strArr, String str2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", businessObject);
        bundle.putStringArray("header_img", strArr);
        bundle.putString(JukeSeeAllFragment.EXTRA_ARG_TITLE, str2);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        }
        return bundle;
    }

    private boolean a(Bundle bundle, ViewGroup viewGroup) {
        if (bundle != null) {
            this.C = bundle.getString("page_title");
            this.e = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            if (this.e != null) {
                this.mAppState.setGADParameter(this.e.getBusinessObjId());
                if (this.e instanceof Playlist) {
                    this.d = Constants.g();
                    Iterator it = this.d.c().iterator();
                    while (it.hasNext()) {
                        ListingButton listingButton = (ListingButton) it.next();
                        if (this.e.isLocalMedia()) {
                            listingButton.c().d(this.e.isLocalMedia());
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append("playlist_id=");
                            stringBuilder.append(this.e.getBusinessObjId());
                            stringBuilder.append("&playlist_type=");
                            stringBuilder.append(((Playlist) this.e).getPlaylistType());
                            String stringBuilder2 = stringBuilder.toString();
                            if (((Playlist) this.e).getAutomated() != null && ((Playlist) this.e).getAutomated().equalsIgnoreCase("1")) {
                                StringBuilder stringBuilder3 = new StringBuilder();
                                stringBuilder3.append(stringBuilder2);
                                stringBuilder3.append("&automated=1");
                                stringBuilder2 = stringBuilder3.toString();
                            }
                            listingButton.c().a(stringBuilder2);
                            if (DownloadManager.c().b(this.e).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(Util.c(this.e.getBusinessObjType()));
                        stringBuilder4.append(this.e.getBusinessObjId());
                        AppsFlyer.getInstance().reportViewContent(this.e.getEnglishName(), "Playlist", stringBuilder4.toString());
                    }
                }
                this.d.b(this.e.getName());
                this.d.a(this.e);
                this.mAppState.setListingComponents(this.d);
                a((ListingButton) this.d.c().get(0));
                a(viewGroup);
                return true;
            }
        }
        ((GaanaActivity) this.mContext).popBackStack();
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.H = null;
        if (this.e != null) {
            this.e.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.e);
        }
    }

    public final void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B = false;
        this.A = new DisplayMetrics();
        this.H = bundle;
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.A);
        GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
        if (this.c == null) {
            boolean a;
            this.x = Calendar.getInstance().getTimeInMillis();
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.u = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (bundle == null) {
                a = a(getArguments(), viewGroup);
            } else {
                a = a(bundle, viewGroup);
            }
            if (a) {
                this.p.setVisibility(0);
                f();
                String[] stringArray = getArguments().getStringArray("header_img");
                this.D = getArguments().getString(JukeSeeAllFragment.EXTRA_ARG_TITLE);
                if (stringArray != null) {
                    a(stringArray[0], stringArray[1]);
                }
            } else {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } else {
            this.mAppState.setListingComponents(this.d);
            if (this.s != null) {
                this.mAppState.setCurrentBusObjInListView(this.s);
            }
            if (((GaanaActivity) this.mContext).getRefreshData()) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                b();
            } else if (!(this.f == null || this.f.getAdapter() == null)) {
                this.f.getAdapter().notifyDataSetChanged();
            }
        }
        if (this.k == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.l) {
            this.k.setRefreshing(true);
        } else {
            this.k.setRefreshing(false);
        }
        if (this.e != null) {
            this.TITLE = this.e.getEnglishName();
            if (this.e instanceof Playlist) {
                Playlist playlist = (Playlist) this.e;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.z = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.y = stringBuilder.toString();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PlaylistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.I = stringBuilder2.toString();
            }
            setGAScreenName(this.I, this.I);
        }
        return this.c;
    }

    public void onStart() {
        super.onStart();
        c();
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (ap.a().b(getActivity())) {
            ColombiaAdViewManager.a().a((c) this);
        }
        if (this.G != null) {
            this.G.resume();
        }
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            f();
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
    }

    public void onPause() {
        if (this.G != null) {
            this.G.pause();
        }
        ColombiaAdViewManager.a().a(null);
        super.onPause();
    }

    private void a(ViewGroup viewGroup) {
        this.c = setContentView(R.layout.fragment_persona_details, viewGroup);
        this.q = d.a().a(142);
        this.k = (SwipeRefreshLayout) this.c.findViewById(R.id.swipe_refresh_layout);
        this.k.setOnRefreshListener(this);
        this.f = (ObservableRecyclerView) this.c.findViewById(R.id.scroll);
        this.g = (CrossFadeImageView) this.c.findViewById(R.id.details_artwork);
        this.h = (CrossFadeImageView) this.c.findViewById(R.id.details_artwork_footer);
        this.F = (TextView) this.c.findViewById(R.id.album_title);
        this.E = (LinearLayout) this.c.findViewById(R.id.llNativeAdSlot1);
        this.f.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.f.setHasFixedSize(false);
        this.r = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null);
        this.r.setLayoutParams(new LayoutParams(-1, this.q));
        this.r.getLayoutParams().height = this.q;
        this.i = new CustomListAdapter(this.mContext, this.r);
        this.i.setParamaters(0, this);
        this.f.setAdapter(this.i);
        this.o = (Toolbar) this.c.findViewById(R.id.main_toolbar);
        this.o.setContentInsetsAbsolute(0, 0);
        this.n = new DetailsMaterialActionBar(this.mContext);
        this.o.addView(this.n);
        this.n.setParams(this, this.e);
        this.n.showContextMenu(false);
        this.n.setToolbar(this.o);
        this.p = (ProgressBar) this.c.findViewById(R.id.progressbar);
    }

    private void a(String str, String str2) {
        this.g.bindImage(str, this.g.getScaleType());
        this.h.bindImage(str2, this.h.getScaleType());
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.mContext != null) {
            i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout == null || slidingPanelLayout.a() != 1) {
                e();
            }
        }
    }

    private void e() {
        if (this.e != null && this.i != null) {
            this.i.notifyDataSetChanged();
        }
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        e();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        e();
    }

    private void f() {
        this.x = Calendar.getInstance().getTimeInMillis();
        URLManager c = ((ListingButton) this.d.c().get(0)).c();
        c.c(Boolean.valueOf(this.l));
        com.i.i.a().a(c, toString(), (com.android.volley.i.b) this, (a) this);
    }

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.j = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
            if (this.j instanceof ShareableSongsView) {
                ((ShareableSongsView) this.j).setPageTitle(this.C);
            }
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public BusinessObject a() {
        return this.e;
    }

    public void b() {
        if (this.d != null && this.d.c() != null) {
            f();
        }
    }

    public void onStop() {
        d();
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (viewHolder.getItemViewType() == 5) {
            return viewHolder.itemView;
        }
        return this.j.getPoplatedView(viewHolder, (BusinessObject) this.s.get(i), viewGroup);
    }

    public void notifyItemChanged(int i) {
        if (this.i != null) {
            this.i.notifyItemChanged(i + 1);
        }
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (i != 5) {
            return new ShareableSongsHolder(this.j.createViewHolder(viewGroup, i));
        }
        UserMessage userMessage = new UserMessage();
        userMessage.setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
        View emptyMsgView = this.j.getEmptyMsgView(userMessage, viewGroup);
        if (Constants.l) {
            emptyMsgView.findViewById(R.id.ll_view_user_msg).setBackgroundColor(-1);
        } else {
            emptyMsgView.findViewById(R.id.ll_view_user_msg).setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return new ItemEmptyMessageHolder(emptyMsgView);
    }

    public int getItemViewType(int i) {
        return (i == 0 && this.w) ? 5 : 1;
    }

    public void onRefresh() {
        if (!this.l) {
            this.k.setRefreshing(true);
            this.l = true;
            f();
        }
    }

    private LinearLayout g() {
        if (this.a == null) {
            this.a = new LinearLayout(this.mContext);
            this.a.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            this.a.setGravity(17);
            this.a.setBackgroundColor(getResources().getColor(R.color.gaana_grey));
        }
        return this.a;
    }

    public void onDestroyView() {
        if (this.c.getParent() != null) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
        if (this.G != null) {
            this.G.destroy();
        }
        this.B = true;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.l = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.p.setVisibility(8);
    }

    public void onResponse(Object obj) {
        if (!this.B) {
            this.l = false;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.x != 0) {
                long j = timeInMillis - this.x;
                String str = "";
                if (this.e.getBusinessObjType() == BusinessObjectType.Playlists) {
                    str = "Playlist detail";
                } else if (this.e.getBusinessObjType() == BusinessObjectType.Albums) {
                    str = "Album detail";
                }
                Constants.a("Load", j, str, null);
            }
            this.k.setRefreshing(false);
            this.p.setVisibility(8);
            BusinessObject businessObject = (BusinessObject) obj;
            if (businessObject == null || businessObject.getArrListBusinessObj() == null) {
                this.m = 0;
                ArrayList arrayList = new ArrayList();
                this.e.setArrListBusinessObj(arrayList);
                this.e.setCount("0");
                this.t.clear();
                this.t.addAll(arrayList);
                this.i.updateAdapterCount(1);
                this.w = true;
            } else {
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                if (arrListBusinessObj == null || arrListBusinessObj.size() == 0) {
                    this.m = 0;
                    this.e.setArrListBusinessObj(arrListBusinessObj);
                    this.e.setCount("0");
                    this.t.clear();
                    this.t.addAll(arrListBusinessObj);
                    this.i.updateAdapterCount(1);
                    this.w = true;
                } else {
                    this.s = businessObject.getArrListBusinessObj();
                    this.t.clear();
                    this.t.addAll(this.s);
                    this.m = this.s.size();
                    this.w = false;
                    ((ListingButton) this.d.c().get(0)).a(this.t);
                    this.e.setArrListBusinessObj(arrListBusinessObj);
                    this.mAppState.setCurrentBusObjInListView(arrListBusinessObj);
                    this.f.setItemAnimator(new DefaultItemAnimator());
                    String str2 = "0";
                    Object obj2 = "0";
                    if (this.e instanceof Playlist) {
                        str2 = ((Playlist) this.e).getFavoriteCount();
                        obj2 = ((Tracks) businessObject).getFavoriteCount();
                        ((Playlist) this.e).setFavoriteCount(obj2);
                    }
                    this.e.setCount(String.valueOf(businessObject.getArrListBusinessObj().size()));
                    if (!(this.e.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(this.e.getBusinessObjId())) == null)) {
                        if (this.e instanceof Playlist) {
                            ((Playlist) this.e).setLastModifiedDate(((Tracks) businessObject).getModifiedOn());
                            DownloadManager.c().d(this.e);
                        }
                        if (!(str2 == null || obj2 == null || str2.equals(obj2))) {
                            DownloadManager.c().a(this.e.getBusinessObjId(), this.e);
                        }
                    }
                    this.F.setText(this.D);
                    this.i.updateAdapterCount(this.m);
                    h();
                }
            }
        }
    }

    private void h() {
        if ((this.e instanceof Album) || (this.e instanceof Playlist)) {
            this.v = "";
            if (this.e instanceof Album) {
                this.v = ((Album) this.e).getChannelPageAdCode();
            } else if (this.e instanceof Playlist) {
                this.v = ((Playlist) this.e).getChannelPageAdCode();
            }
            if (!(this.v == null || TextUtils.isEmpty(this.v))) {
                i();
            }
        }
        if (this.H == null && !TextUtils.isEmpty(this.u)) {
            if (this.u.contains("play")) {
                String[] split = this.u.split("/");
                if (split.length > 1) {
                    BusinessObject businessObject;
                    String str = split[1];
                    ArrayList g = ((ListingButton) this.d.c().get(0)).g();
                    Iterator it = g.iterator();
                    while (it.hasNext()) {
                        BusinessObject businessObject2 = (BusinessObject) it.next();
                        if (str.equals(businessObject2.getBusinessObjId())) {
                            businessObject = (Track) businessObject2;
                            break;
                        }
                    }
                    businessObject = null;
                    if (businessObject != null) {
                        PlayerManager.a(this.mContext).a(com.logging.d.a().a((BaseGaanaFragment) this, g), com.logging.d.a().a((BaseGaanaFragment) this, businessObject));
                        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                    }
                } else {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, a());
                }
            } else if (this.u.contains("download")) {
                a(false);
            }
            this.u = null;
        }
    }

    public void a(final boolean z) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            if (ap.a().a(this.e, null)) {
                b(z);
            } else {
                String str = "";
                if (ap.a().l()) {
                    str = this.e instanceof Track ? "tr" : "pl";
                }
                Util.b(this.mContext, str, null, new as() {
                    public void onTrialSuccess() {
                        PersonaDetailsFragment.this.b(z);
                        PersonaDetailsFragment.this.refreshDataandAds();
                        PersonaDetailsFragment.this.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) PersonaDetailsFragment.this.mContext).updateSideBar();
                    }
                });
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void b(boolean z) {
        Util.i(this.mContext, "Download");
        BusinessObject businessObject = this.e;
        this.e.setArrListBusinessObj(((ListingButton) this.d.c().get(0)).g());
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        boolean b = d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
        if (h == null || h == DownloadStatus.TRIED_BUT_FAILED || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new f.b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) PersonaDetailsFragment.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) PersonaDetailsFragment.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                    return;
                } else if (b) {
                    if (!Constants.V) {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                        Constants.V = true;
                    }
                } else if (!Constants.W) {
                    Constants.W = true;
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                        public void onClick(View view) {
                            if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                                PopupWindowView.getInstance(PersonaDetailsFragment.this.mContext, currentFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(PersonaDetailsFragment.this.mContext, currentFragment).dismiss(true);
                            ((GaanaActivity) PersonaDetailsFragment.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            if (h == null) {
                DownloadManager.c().a(businessObject, this.mContext);
            } else {
                DownloadManager.c().c(businessObject);
            }
            new int[1][0] = R.attr.button_inqueue;
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(29, -1));
            obtainStyledAttributes.recycle();
        } else if (z) {
            if (h == DownloadStatus.QUEUED || h == DownloadStatus.DOWNLOADING) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_pause_this_album_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().r(Integer.parseInt(PersonaDetailsFragment.this.e.getBusinessObjId()));
                        new int[1][0] = R.attr.button_resume;
                        TypedArray obtainStyledAttributes = PersonaDetailsFragment.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        ContextCompat.getDrawable(PersonaDetailsFragment.this.getContext(), obtainStyledAttributes.getResourceId(10, -1));
                        obtainStyledAttributes.recycle();
                    }
                }, false);
            } else if (h == DownloadStatus.DOWNLOADED) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_to_remove_this_album_from_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().p(Integer.parseInt(PersonaDetailsFragment.this.e.getBusinessObjId()));
                        DownloadManager.c().d(Integer.parseInt(PersonaDetailsFragment.this.e.getBusinessObjId()));
                        TypedArray obtainStyledAttributes = PersonaDetailsFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.download_all});
                        obtainStyledAttributes.getDrawable(0);
                        obtainStyledAttributes.recycle();
                    }
                }, false);
            }
        }
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.DEDICATIONS.name();
    }

    private void i() {
        if (isAdded() && ap.a().p() && !TextUtils.isEmpty(Constants.j)) {
            String str = Constants.j;
            final PublisherAdView publisherAdView = new PublisherAdView(this.mContext);
            publisherAdView.setAdUnitId(str);
            AdSize[] adSizeArr = new AdSize[]{new AdSize(ModuleDescriptor.MODULE_VERSION, 100), new AdSize(ModuleDescriptor.MODULE_VERSION, 140), new AdSize(ModuleDescriptor.MODULE_VERSION, 150), new AdSize(340, 100), new AdSize(340, 140), new AdSize(340, 150), new AdSize(728, 100), new AdSize(728, 140), new AdSize(728, 150), new AdSize(468, 100), new AdSize(468, 140), new AdSize(468, 150)};
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(this.I);
            adsUJData.setAdUnitCode(str);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
            publisherAdView.setAdSizes(adSizeArr);
            publisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        PersonaDetailsFragment.this.a = PersonaDetailsFragment.this.g();
                        PersonaDetailsFragment.this.a.removeAllViews();
                        PersonaDetailsFragment.this.a.addView(publisherAdView);
                        if (adsUJData != null) {
                            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData != null) {
                try {
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                } catch (Exception unused) {
                }
            }
            Location location = ((GaanaActivity) this.mContext).getLocation();
            if (location != null) {
                Builder builder = new Builder();
                if (this.mAppState.getNetworkExtrasBundle() != null) {
                    builder.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                publisherAdView.loadAd(builder.setLocation(location2).build());
            } else {
                Builder builder2 = new Builder();
                if (this.mAppState.getNetworkExtrasBundle() != null) {
                    builder2.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                stringBuilder2.append("Gaana ");
                builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                publisherAdView.loadAd(builder2.build());
            }
        }
    }

    public void c() {
        if (!TextUtils.isEmpty(this.y)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.y), this.TITLE, Uri.parse(this.z), arrayList);
        }
    }

    public void d() {
        if (!TextUtils.isEmpty(this.y)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.y));
            this.mClient.disconnect();
        }
    }

    public void refreshDataandAds() {
        onRefresh();
    }

    public void loadTopBannerAds() {
        if (ap.a().b(this.mContext)) {
            if (this.G == null) {
                this.G = new PublisherAdView(this.mContext);
            }
            if (!e.J.equals("0")) {
                if (this.G.getAdUnitId() == null) {
                    this.G.setAdUnitId(e.J);
                }
                this.G.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, 50));
                this.G.setAdListener(new AdListener() {
                    public void onAdClosed() {
                        super.onAdClosed();
                        PersonaDetailsFragment.this.K.a(ADSTATUS.CLOSED);
                    }

                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        PersonaDetailsFragment.this.K.c(ADSTATUS.FAILED);
                    }

                    public void onAdLeftApplication() {
                        super.onAdLeftApplication();
                    }

                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    public void onAdLoaded() {
                        super.onAdLoaded();
                        try {
                            PersonaDetailsFragment.this.K.b(ADSTATUS.LOADED);
                            PersonaDetailsFragment.this.E.addView(PersonaDetailsFragment.this.G);
                            PersonaDetailsFragment.this.E.setVisibility(0);
                        } catch (Exception unused) {
                        }
                    }
                });
                try {
                    Location location = ((GaanaActivity) this.mContext).getLocation();
                    if (location != null) {
                        Builder builder = new Builder();
                        if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                            builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(Util.l(GaanaApplication.getContext()));
                        stringBuilder.append("Gaana ");
                        builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                        Location location2 = new Location("");
                        location2.setLatitude(location.getLatitude());
                        location2.setLongitude(location.getLongitude());
                        this.G.loadAd(builder.setLocation(location2).build());
                        return;
                    }
                    Builder builder2 = new Builder();
                    if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                        builder2.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                    stringBuilder2.append("Gaana ");
                    builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                    this.G.loadAd(builder2.build());
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            return;
        }
        this.E.setVisibility(8);
    }
}

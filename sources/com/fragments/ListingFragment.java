package com.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.actionbar.DownloadDetailsActionbar;
import com.actionbar.GenericBackActionBar;
import com.actionbar.GenericSearchActionBar;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.constants.Constants.SortOrder;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapterSectionIndexer.OnSearchCompleted;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.RecommendedPageView;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.view.CustomListView;
import com.gaana.view.CustomListView.OnDataLoadedListener;
import com.gaana.view.CustomListView.OnDataRefreshListener;
import com.gaana.view.CustomListViewOffline;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.URLManager.BusinessObjectType;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.models.ListingButton;
import com.models.ListingParams;
import com.services.l;
import java.util.ArrayList;

public class ListingFragment extends BaseGaanaFragment implements com.actionbar.DownloadDetailsActionbar.a, d, OnSearchCompleted, OnDataLoadedListener, OnDataRefreshListener, b, c {
    private d A;
    private LinearLayoutManager B;
    private boolean C = false;
    private boolean D = false;
    private int E = 0;
    private a F;
    private int G = 0;
    private int H = 0;
    private int I = -1;
    private int J = -1;
    private LinearLayout a;
    private CustomListView b = null;
    private View c = null;
    private String d = null;
    private SortOrder e = SortOrder.Default;
    private ListingParams f;
    private GenericSearchActionBar g;
    private GenericBackActionBar h;
    private DownloadDetailsActionbar i;
    private OnDataLoadedListener j;
    private BaseGaanaFragment k;
    private String l;
    private String m;
    private ViewGroup n;
    private boolean o = true;
    private PublisherAdView p;
    private ADSTATUS q = ADSTATUS.CLOSED;
    private boolean r = false;
    private BusinessObject s;
    private RecommendedPageView t;
    private boolean u;
    private int v = 0;
    private boolean w = false;
    private boolean x = false;
    private String y;
    private boolean z;

    public interface a {
        void a(boolean z, int i, SortOrder sortOrder);
    }

    public void a() {
    }

    public boolean a(int i) {
        return false;
    }

    public void b() {
    }

    public void onDownMotionEvent() {
    }

    public int c() {
        return this.f.j().g() != null ? this.f.j().g().size() : 0;
    }

    public String d() {
        return this.m;
    }

    public void a(String str) {
        this.y = str;
    }

    private boolean q() {
        return this.q != ADSTATUS.LOADING;
    }

    public void a(BaseGaanaFragment baseGaanaFragment) {
        this.k = baseGaanaFragment;
    }

    public BaseGaanaFragment e() {
        return this.k;
    }

    public void a(OnDataLoadedListener onDataLoadedListener) {
        this.j = onDataLoadedListener;
    }

    public ListingParams f() {
        return this.f;
    }

    private void a(Bundle bundle) {
        if (bundle != null && this.f == null) {
            this.f = (ListingParams) bundle.getParcelable("bgf_saved_state");
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("bgf_saved_state", this.f);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.c = setContentView(R.layout.fragment_listing, viewGroup);
            this.a = (LinearLayout) this.c.findViewById(R.id.llParentListing);
            this.p = new PublisherAdView(this.mContext);
            if (bundle == null) {
                a(getArguments());
            } else {
                a(bundle);
            }
            if (this.f != null) {
                this.e = this.f.w();
                if (this.f.f() && !this.isChildFragment) {
                    ((GaanaActivity) this.mContext).setFragment(this);
                }
            }
            if (this.f != null) {
                g();
            }
        }
        if (this.f != null) {
            if (this.f.f()) {
                if (!this.isChildFragment) {
                    ((GaanaActivity) this.mContext).setFragment(this);
                }
                this.l = this.f.j().d();
                this.c.findViewById(R.id.dummy_shadow).setVisibility(0);
                ((GaanaActivity) this.mContext).title = this.l;
                if (this.f.m()) {
                    if (this.i == null) {
                        this.i = new DownloadDetailsActionbar(this.mContext, false, this.l);
                        this.i.setDownloadActionbarClickListener(this);
                        this.i.setPagerPosition(this.f.e());
                        this.i.showContextMenu(false);
                        this.i.c(this.f.j().c().i() != BusinessObjectType.Playlists);
                    }
                    this.i.setSortOrder(this.e);
                    if (this.f.q()) {
                        this.i.setCustomMenuId(this.f.r());
                    }
                    setActionBar(this.c, this.i);
                    this.i.setParams(this, this.s);
                } else if (this.f.b()) {
                    this.h = new GenericBackActionBar(this.mContext, this.l);
                    setActionBar(this.c, this.h);
                    this.h.showContextMenu(false);
                    this.h.setParams(this, this.b.getmBusinessObject());
                } else {
                    if (this.g == null) {
                        this.g = new GenericSearchActionBar(this.mContext, this.l);
                        if (this.f.g()) {
                            this.g.a();
                        }
                    }
                    setActionBar(this.c, this.g);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MyMusic_");
                stringBuilder.append(this.f.j().e());
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("MyMusic_");
                stringBuilder3.append(this.f.j().e());
                setGAScreenName(stringBuilder2, stringBuilder3.toString());
            } else {
                this.c.findViewById(R.id.main_toolbar).setVisibility(8);
            }
            if (((GaanaActivity) this.mContext).getRefreshData() || PlaylistSyncManager.refreshFragment) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                PlaylistSyncManager.refreshFragment = false;
                if (this.b != null) {
                    i();
                } else {
                    g();
                }
            } else if (this.b != null && this.b.getListAdapter() != null) {
                this.b.getListAdapter().notifyDataSetChanged();
            } else if (this.t != null) {
                this.t.refreshListView();
            }
            if (this.f.j() != null && this.f.j().a()) {
                this.hideBottomBar = true;
            }
        } else {
            ((BaseActivity) this.mContext).popBackStackToHome();
        }
        if (f().a() == 0) {
            r();
        }
        return this.c;
    }

    private void r() {
        ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) this.c.findViewById(R.id.bottomAdSlot), e.A, this.y, new AdsUJData[0]);
    }

    public void onStop() {
        if (this.f.j() != null && this.f.j().a()) {
            this.C = false;
        }
        super.onStop();
        if (al.a) {
            j();
        }
    }

    public void onResume() {
        if (this.f.j() != null && this.f.j().a()) {
            this.C = true;
        }
        super.onResume();
        if (this.b != null) {
            this.b.updateSongQueue();
        }
        updateView();
        ColombiaAdViewManager.a().a((c) this);
        ColombiaAdViewManager.a().a(this.mContext, this.n);
        if (this.p != null) {
            this.p.resume();
        }
        if (this.b != null) {
            this.b.onResume();
        }
        if (!TextUtils.isEmpty(getSectionName()) && !getSectionName().equalsIgnoreCase(PLAYOUT_SECTION_TYPE.OTHERS.name())) {
            GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
        }
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        if (this.p != null) {
            this.p.pause();
        }
        super.onPause();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.D = z;
        if (z) {
            this.r = false;
            c(z);
        }
        if (this.b != null) {
            this.b.setUserVisibleHint(z);
        }
    }

    public void g() {
        this.a.removeAllViews();
        if (this.f.i()) {
            this.b = new CustomListViewOffline(this.mContext, this);
        } else {
            this.b = new CustomListView(this.mContext, this);
        }
        this.b.showRepetativeAds(this.f.o());
        if (this.f.x()) {
            this.b.showScrollHeader(true);
        }
        if (this.f.c()) {
            this.b.shouldShowSearchWidget(true);
            this.b.setSearchHintText(String.format(this.mContext.getString(R.string.search_by), new Object[]{this.f.s()}));
        } else {
            this.b.shouldShowSearchWidget(false);
            this.b.setSearchHintText(String.format(this.mContext.getString(R.string.search_by), new Object[]{this.f.s()}));
        }
        this.b.setShouldShowNoDownloadView(this.f.d());
        if (this.f.h() != -1) {
            this.b.setCustomHeaderId(this.f.h());
        }
        this.b.setSortOrder(this.e);
        if (this.i != null) {
            this.i.setSortOrder(this.e);
        }
        this.b.setOnDataLoadedListener(this);
        this.b.setOnDataRefreshListener(this);
        if (!this.f.j().j()) {
            this.f.j().a(null);
        }
        this.m = this.f.n();
        this.b.setUpdateListView(this.f.j());
        this.b.setEnableShuffleIcon(this.f.v());
        if (s()) {
            this.n = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
            this.a.addView(this.n);
            this.b.setOnAdRefreshListener(this);
        }
        if (!(this.f == null || this.b == null)) {
            this.b.setIsTrendingSongsOnLocalFiles(this.f.t());
        }
        this.a.addView(this.b.getListView());
        if (this.f.x()) {
            ((ObservableRecyclerView) this.b.getCustomListView()).setScrollViewCallbacks(this);
            this.B = (LinearLayoutManager) this.b.getCustomListView().getLayoutManager();
        }
    }

    public CustomListView h() {
        return this.b;
    }

    public void refreshListView() {
        if (!(this.b == null || this.b.getListAdapter() == null)) {
            this.b.getListAdapter().notifyDataSetChanged();
            if (this.C && this.b.getCuratedDownloadAllLayout() != null) {
                this.b.updateCuratedDownloadAllLayout();
            }
        }
        if (this.a != null) {
            Object tag = this.a.getTag();
            if (tag != null && (tag instanceof RecommendedPageView)) {
                ((RecommendedPageView) tag).refreshListView(true);
            }
        }
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(SortOrder sortOrder) {
        this.e = sortOrder;
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        if (businessObject == null || !z) {
            refreshListView();
        } else {
            this.b.getListAdapter().removeItem(businessObject);
        }
    }

    public void c(String str) {
        if (this.f.g()) {
            this.d = str;
            if (this.b != null) {
                this.b.startSearch(str, this);
            }
        }
    }

    public void a(boolean z) {
        this.x = z;
    }

    public void i() {
        if (this.f != null && this.f.u()) {
            if (this.b != null) {
                this.b.refreshListData();
            }
            if (this.a != null) {
                Object tag = this.a.getTag();
                if (tag != null && (tag instanceof RecommendedPageView)) {
                    ((RecommendedPageView) tag).refreshListView(true);
                }
            }
            if (this.b != null) {
                this.b.showHideGaanaPlusExpirationHeader();
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void b(boolean z) {
        this.u = z;
    }

    public void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType) {
        if (getActivity() != null) {
            if (businessObject != null) {
                this.s = businessObject;
                a(businessObject.getArrListBusinessObj());
                if (!TextUtils.isEmpty(this.d)) {
                    c(this.d);
                }
            }
            if (this.j != null) {
                this.j.onDataLoaded(businessObject, businessObjectType);
            }
        }
        if (this.f.x()) {
            this.B = (LinearLayoutManager) this.b.getCustomListView().getLayoutManager();
        }
    }

    public void onEmptyDataRefresh() {
        this.r = true;
        if (this.D) {
            c(false);
        }
    }

    private boolean s() {
        if (this.mContext == null) {
            return false;
        }
        if (this.p == null) {
            this.p = new PublisherAdView(this.mContext);
        }
        if (((((GaanaActivity) this.mContext).getCurrentFragment() instanceof FavoritesFragment) || (((GaanaActivity) this.mContext).getCurrentFragment() instanceof ListingFragment)) && ap.a().b(this.mContext)) {
            return true;
        }
        return false;
    }

    private void a(ArrayList<BusinessObject> arrayList) {
        if (this.b != null) {
            if (this.f != null) {
                this.b.showRepetativeAds(this.f.o());
            }
            this.b.clearAdSpots();
            this.b.setRepetativeAdSpots();
        }
        int size = arrayList != null ? arrayList.size() : 0;
        this.v = size;
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        if (size > 0 || !this.f.k() || !TextUtils.isEmpty(this.d)) {
            String e = this.f.j().e();
            if (this.f.l()) {
                ListingButton j = this.f.j();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(e);
                stringBuilder.append(" (");
                stringBuilder.append(size);
                stringBuilder.append(")");
                j.a(stringBuilder.toString());
            } else {
                this.f.j().a(e);
            }
            this.f.j().a((ArrayList) arrayList);
            if (s()) {
                q();
            }
            if (this.f.j().c().i() == BusinessObjectType.Tracks && getUserVisibleHint()) {
                this.mAppState.setCurrentBusObjInListView(arrayList);
            }
            if (currentFragment instanceof ArtistDetailsMaterialListing) {
                ((ArtistDetailsMaterialListing) currentFragment).a(this.f.e(), this.f.j().g().size());
            } else if (currentFragment instanceof RevampedDetailListing) {
                ((RevampedDetailListing) currentFragment).a(this.f.e(), this.f.j().g().size());
            } else if (currentFragment instanceof MyMusicSearchResultFragment) {
                ((MyMusicSearchResultFragment) currentFragment).a(this.f.e(), this.f.j().d());
            } else if (this.g != null) {
                this.g.setTitle(this.f.j().d());
            }
            if (size == 0) {
                this.r = true;
                if (this.D) {
                    c(false);
                    return;
                }
                return;
            }
            this.r = false;
            if (this.b != null && this.D) {
                c(true);
            }
        } else if (!this.mAppState.isAppInOfflineMode()) {
            this.b = null;
            this.a.removeAllViews();
            if (s() && q()) {
                if (this.n == null) {
                    this.n = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
                }
                this.a.addView(this.n, 0);
            }
            this.f.j().a(this.f.j().e());
            if (this.g != null) {
                this.g.b();
                this.g.setTitle(this.f.j().e());
            } else if (currentFragment instanceof ArtistDetailsMaterialListing) {
                ((ArtistDetailsMaterialListing) currentFragment).a(this.f.e(), this.f.j().g().size());
            } else if (currentFragment instanceof RevampedDetailListing) {
                ((RevampedDetailListing) currentFragment).a(this.f.e(), this.f.j().g().size());
            }
            this.t = new RecommendedPageView(this.mContext);
            this.t.setSwipeRefreshListener(new l.ap() {
                public void onSwipeRefresh() {
                    ListingFragment.this.g();
                }
            });
            this.t.setIsDownloadFragment(isDownLoadFragment());
            this.a.addView(this.t.getRecommendedView((BaseGaanaFragment) this, this.mContext, this.f.j().c().i()));
            this.r = true;
            if (this.D) {
                c(false);
            }
            this.a.setTag(this.t);
        } else if (this.b != null) {
            this.b.showHideEmtpyViewLayout(true);
        }
    }

    public void c(boolean z) {
        if (this.F != null) {
            boolean z2 = false;
            z = this.v > 0;
            a aVar = this.F;
            if (this.f.m() && !this.r && z) {
                z2 = true;
            }
            aVar.a(z2, this.f.r(), this.e);
        }
    }

    public void onSearch(ArrayList<BusinessObject> arrayList) {
        a((ArrayList) arrayList);
    }

    public void a(View view, int i) {
        if (this.k instanceof DownloadDetailsFragment) {
            ((DownloadDetailsFragment) this.k).a(view, i, this.b);
        } else if (this.k instanceof LocalMediaFragment) {
            ((LocalMediaFragment) this.k).a(view, i, this.b);
        } else if (this.k instanceof FavoritesFragment) {
            ((FavoritesFragment) this.k).a(view, i, this.b);
        } else if (this.k instanceof MyMusicItemFragment) {
            ((MyMusicItemFragment) this.k).a(view, i, this.b);
        } else {
            b(view, i);
        }
    }

    private void b(View view, int i) {
        c(view, i);
    }

    private void c(View view, int i) {
        this.E = i;
        if (this.h != null) {
            this.h.setParams(this, this.b.getmBusinessObject());
            this.h.showContextMenu(true);
        } else if (this.i != null) {
            this.i.setParams(this, this.s);
            this.i.showContextMenu(true);
        }
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        k();
        refreshListView();
    }

    public void a(BusinessObject businessObject, boolean z) {
        if (h() != null) {
            if (this.f != null) {
                h().showRepetativeAds(this.f.o());
            }
            h().clearAdSpots();
            h().setRepetativeAdSpots();
        }
        b(businessObject, z);
    }

    public void j() {
        this.E = 0;
        if (this.h != null) {
            this.h.showContextMenu(false);
        } else if (this.i != null) {
            this.i.showContextMenu(false);
        }
        al.a().a(false);
        al.a().c();
        refreshListView();
    }

    public void k() {
        if (this.h != null) {
            this.h.updateSelectedCountinContextMode(this.E);
        } else if (this.i != null) {
            this.i.updateSelectedCountinContextMode(this.E);
        }
    }

    public void b(int i) {
        this.E = i;
    }

    public void m() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.b.getListingButton().g());
        }
        refreshListView();
        k();
    }

    public void onDestroyView() {
        if (this.b != null) {
            this.b.onDestroyView();
        }
        if (this.p != null) {
            this.p.destroy();
        }
        if (!(this.c == null || this.c.getParent() == null)) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
    }

    public void a(a aVar) {
        this.F = aVar;
    }

    public void a(SortOrder sortOrder, int i) {
        if (h() != null) {
            this.e = sortOrder;
            h().sortList(sortOrder, this.f.j().b() ^ 1);
        }
    }

    public void a(ADSTATUS adstatus) {
        this.q = adstatus;
    }

    public void b(ADSTATUS adstatus) {
        this.q = adstatus;
    }

    public void c(ADSTATUS adstatus) {
        this.q = adstatus;
    }

    public void d(ADSTATUS adstatus) {
        this.q = adstatus;
    }

    public void l() {
        if (ap.a().b(this.mContext) && this.n == null) {
            this.n = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    public void a(ListingParams listingParams) {
        this.f = listingParams;
    }

    public void d(boolean z) {
        this.z = z;
    }

    public boolean n() {
        return this.z;
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (this.B == null || this.B.findFirstVisibleItemPosition() == 0) {
            this.A.onScrollChanged(i, z, z2);
            this.G = i;
        }
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        this.A.onUpOrCancelMotionEvent(scrollState);
        if ((scrollState == ScrollState.UP || scrollState == ScrollState.DOWN) && this.G > this.H) {
            this.I = ((LinearLayoutManager) h().getCustomListView().getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
            this.J = h().getCustomListView().getAdapter().getItemCount();
            if (this.k instanceof RevampedDetailListing) {
                an.a().c("scroll", AvidJSONUtil.KEY_Y, "", ((RevampedDetailListing) this.k).d(), ((RevampedDetailListing) this.k).s().getBusinessObjId(), "", String.valueOf(this.J), String.valueOf(this.I));
            }
            this.H = this.G;
        }
    }

    public void a(d dVar) {
        this.A = dVar;
    }

    public void onDestroy() {
        if (this.b != null) {
            this.b.clearCuratedList();
        }
        super.onDestroy();
        ColombiaAdViewManager.a().a(this.p);
    }

    public void b(BusinessObject businessObject, boolean z) {
        if (this.b != null && z) {
            this.b.updateArrayList(businessObject, true);
        }
    }

    public String getSectionName() {
        return this.f != null ? this.f.p() : "";
    }

    public void refreshDataandAds() {
        if (getParentFragment() != null) {
            ((BaseGaanaFragment) getParentFragment()).refreshDataandAds();
        } else {
            refreshListView();
        }
    }

    public void o() {
        if (h() != null) {
            View findViewById = h().getListView().findViewById(R.id.shuffle_play_button);
            if (findViewById != null) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
                loadAnimation.setInterpolator(new com.a.a(0.1d, 20.0d));
                findViewById.startAnimation(loadAnimation);
            }
        }
    }

    public void setIsDownloadFragment(boolean z) {
        super.setIsDownloadFragment(z);
    }

    public boolean p() {
        return this.C;
    }
}

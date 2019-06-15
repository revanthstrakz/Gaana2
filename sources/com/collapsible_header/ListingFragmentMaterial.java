package com.collapsible_header;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.GenericSearchActionBar;
import com.collapsible_header.c.b;
import com.constants.Constants;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapterSectionIndexer.OnSearchCompleted;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.RecommendedPageView;
import com.gaana.models.BusinessObject;
import com.gaana.view.CustomListView.OnDataLoadedListener;
import com.managers.URLManager.BusinessObjectType;
import com.models.ListingButton;
import com.models.ListingParams;
import java.util.ArrayList;

public class ListingFragmentMaterial extends FlexibleSpaceWithImageBaseFragment<ObservableRecyclerView> implements b, OnSearchCompleted {
    public c a = null;
    private LinearLayout b;
    private View c = null;
    private String d = null;
    private ListingParams e;
    private GenericSearchActionBar f;
    private OnDataLoadedListener g;

    public static Bundle b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("ARG_SCROLL_Y", i);
        return bundle;
    }

    private void a(Bundle bundle) {
        if (bundle != null && this.e == null) {
            this.e = (ListingParams) bundle.getParcelable("bgf_saved_state");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("bgf_saved_state", this.e);
    }

    public View a() {
        return this.a != null ? this.a.a() : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.c = setContentView(R.layout.item_listing, viewGroup);
            this.b = (LinearLayout) this.c.findViewById(R.id.llParentListing);
            if (bundle == null) {
                a(getArguments());
            } else {
                a(bundle);
            }
            if (this.e != null) {
                b();
            }
        }
        if (this.e == null) {
            ((GaanaActivity) this.mContext).onBackPressed();
        } else if (((GaanaActivity) this.mContext).getRefreshData() || PlaylistSyncManager.refreshFragment) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            PlaylistSyncManager.refreshFragment = false;
            if (this.a != null) {
                c();
            } else {
                b();
            }
        } else if (!(this.a == null || this.a.c() == null || this.a.c().getAdapter() == null)) {
            this.a.c().getAdapter().notifyDataSetChanged();
        }
        return this.c;
    }

    public void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.e();
        }
        updateView();
    }

    public void onPause() {
        super.onPause();
    }

    public void b() {
        this.b.removeAllViews();
        if (!this.e.i()) {
            this.a = new c(this.mContext, this);
        }
        this.a.c().setLayoutManager(new LinearLayoutManager(getActivity()));
        this.a.c().setHasFixedSize(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int i = displayMetrics.widthPixels;
        this.a.c().setTouchInterceptionViewGroup((ViewGroup) this.c.findViewById(R.id.llParentListing));
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("ARG_SCROLL_Y")) {
            a(0, this.c);
        } else {
            final int i2 = arguments.getInt("ARG_SCROLL_Y", 0);
            f.a(this.a.c(), new Runnable() {
                public void run() {
                    int i = i2 % i;
                    LayoutManager layoutManager = ListingFragmentMaterial.this.a.c().getLayoutManager();
                    if (layoutManager != null && (layoutManager instanceof LinearLayoutManager)) {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, -i);
                    }
                }
            });
            a(i2, this.c);
        }
        this.a.c().setScrollViewCallbacks(this);
        this.a.a((b) this);
        this.e.j().a(null);
        this.a.b(this.e.j());
        this.b.addView(this.a.b());
    }

    public void refreshListView() {
        if (!(this.a == null || this.a.d() == null)) {
            this.a.d().notifyDataSetChanged();
        }
        if (this.b != null) {
            Object tag = this.b.getTag();
            if (tag != null && (tag instanceof RecommendedPageView)) {
                ((RecommendedPageView) tag).refreshListView(true);
            }
        }
    }

    private void d() {
        if (this.a != null && this.a.d() != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((GaanaActivity) this.mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int itemCount = this.a.d().getItemCount();
            int dimension = (int) this.mContext.getResources().getDimension(R.dimen.item_two_line_bar_height);
            if (dimension > 0 && getParentFragment() != null) {
                dimension = (dimension * itemCount) + ((ArtistDetailsMaterialListing) getParentFragment()).e();
                if (dimension < displayMetrics.heightPixels) {
                    View view = new View(this.mContext);
                    if (Constants.l) {
                        view.setBackgroundColor(this.mContext.getResources().getColor(R.color.gaana_grey_white));
                    } else {
                        view.setBackgroundColor(this.mContext.getResources().getColor(R.color.gaana_grey));
                    }
                    view.setLayoutParams(new LayoutParams(-1, displayMetrics.heightPixels - dimension));
                    this.a.d().setFooterView(view);
                }
            }
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        refreshListView();
    }

    public void c() {
        if (this.a != null) {
            this.a.k();
        }
        if (this.b != null) {
            Object tag = this.b.getTag();
            if (tag != null && (tag instanceof RecommendedPageView)) {
                ((RecommendedPageView) tag).refreshListView(true);
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void a(BusinessObject businessObject, BusinessObjectType businessObjectType) {
        if (getActivity() != null) {
            if (businessObject != null) {
                a(businessObject.getArrListBusinessObj());
            }
            d();
            if (this.g != null) {
                this.g.onDataLoaded(businessObject, businessObjectType);
            }
        }
        this.a.c().setVisibility(0);
    }

    private void a(ArrayList<BusinessObject> arrayList) {
        int size = arrayList != null ? arrayList.size() : 0;
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        if (size > 0 || !this.e.k() || !TextUtils.isEmpty(this.d)) {
            String e = this.e.j().e();
            ListingButton j = this.e.j();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e);
            stringBuilder.append(" (");
            stringBuilder.append(size);
            stringBuilder.append(")");
            j.a(stringBuilder.toString());
            this.e.j().a((ArrayList) arrayList);
            if (this.e.j().c().i() == BusinessObjectType.Tracks) {
                this.mAppState.setCurrentBusObjInListView(arrayList);
            }
            if (currentFragment instanceof ArtistDetailsMaterialListing) {
                ((ArtistDetailsMaterialListing) currentFragment).a(this.e.e(), size);
            } else if (this.f != null) {
                this.f.setTitle(this.e.j().d());
            }
        } else if (!this.mAppState.isAppInOfflineMode()) {
            this.a = null;
            this.b.removeAllViews();
            this.e.j().a(this.e.j().e());
            if (this.f != null) {
                this.f.b();
                this.f.setTitle(this.e.j().e());
            } else if (currentFragment instanceof ArtistDetailsMaterialListing) {
                ((ArtistDetailsMaterialListing) currentFragment).a(this.e.e(), this.e.j().g().size());
            }
            RecommendedPageView recommendedPageView = new RecommendedPageView(this.mContext);
            this.b.addView(recommendedPageView.getRecommendedView((BaseGaanaFragment) this, this.mContext, this.e.j().c().i()));
            this.b.setTag(recommendedPageView);
        } else if (this.a != null) {
            this.a.a(true);
        }
    }

    public void onSearch(ArrayList<BusinessObject> arrayList) {
        a((ArrayList) arrayList);
    }

    public void a(int i, int i2) {
        View view = getView();
        if (view != null) {
            ObservableRecyclerView observableRecyclerView = (ObservableRecyclerView) view.findViewById(R.id.recycler_view);
            if (observableRecyclerView != null) {
                int i3 = 0;
                View childAt = observableRecyclerView.getChildAt(0);
                if (childAt != null) {
                    if (i2 < i) {
                        i2 = childAt.getHeight();
                        i3 = i / i2;
                        i %= i2;
                    }
                    LayoutManager layoutManager = observableRecyclerView.getLayoutManager();
                    if (layoutManager != null && (layoutManager instanceof LinearLayoutManager)) {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i3, -i);
                    }
                }
            }
        }
    }

    public void onDestroyView() {
        if (!(this.c == null || this.c.getParent() == null)) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        super.onDestroyView();
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, View view) {
        if (getParentFragment() instanceof ArtistDetailsMaterialListing) {
            ((ArtistDetailsMaterialListing) getParentFragment()).a(i, this.a.c());
        }
    }

    public void refreshDataandAds() {
        if (getParentFragment() != null) {
            ((BaseGaanaFragment) getParentFragment()).refreshDataandAds();
        } else {
            refreshListView();
        }
    }

    public void a(ListingParams listingParams) {
        this.e = listingParams;
    }
}

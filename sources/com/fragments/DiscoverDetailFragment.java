package com.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.GenericBackActionBar;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.GaanaActivity.OnDropDownListener;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.view.CustomGridView;
import com.gaana.view.CustomGridView.OnBusinessObjectRetrievedCallback;
import com.gaana.view.CustomGridView.OnGetViewCallback;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.DiscoverItemView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class DiscoverDetailFragment extends BaseGaanaFragment implements OnDropDownListener, b, c {
    boolean a = false;
    private String b;
    private String c;
    private URLManager d = null;
    private CustomGridView e;
    private LinearLayout f;
    private ArrayList<BusinessObject> g = null;
    private ArrayList<BusinessObject> h = null;
    private View i = null;
    private boolean j = true;
    private String k = "";
    private String l = "";
    private String m = "";
    private int n;
    private ViewGroup o;
    private ADSTATUS p;
    private PublisherAdView q;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.i == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.i = setContentView(R.layout.activity_main, viewGroup);
            this.c = getArguments().getString("EXTRA_ACTIONBAR_TITLE");
            this.m = getArguments().getString("EXTRA_DISCOVER_SEO_KEY");
            Bundle arguments = getArguments();
            this.n = arguments.getInt("BG_COLOR");
            this.i.setBackgroundColor(this.n);
            setActionBar(this.i, new GenericBackActionBar(this.mContext, this.c));
            a(arguments);
        } else {
            setAdShown(false);
            this.j = false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://gaana.com/discover/");
        stringBuilder.append(this.m);
        this.l = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("android-app://com.gaana/gaanagoogle/discover/");
        stringBuilder.append(this.m);
        this.k = stringBuilder.toString();
        ((GaanaActivity) this.mContext).title = this.c;
        updateView();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("DiscoverDetailsScreen:");
        stringBuilder2.append(this.c);
        setGAScreenName("Discover Details", stringBuilder2.toString());
        return this.i;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onStart() {
        a();
        super.onStart();
    }

    public void onStop() {
        b();
        super.onStop();
    }

    public void onDestroyView() {
        if (this.i.getParent() != null) {
            ((ViewGroup) this.i.getParent()).removeView(this.i);
        }
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        ColombiaAdViewManager.a().a((c) this);
        updateView();
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        super.onPause();
    }

    private void a(Bundle bundle) {
        this.b = bundle.getString("<category_id>");
        this.c = bundle.getString("EXTRA_ACTIONBAR_TITLE");
        if (this.b != null) {
            this.d = a(this.b);
            this.d.a(Boolean.valueOf(true));
            this.d.c(Boolean.valueOf(false));
            a(this.d);
        }
    }

    private URLManager a(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Discover);
        uRLManager.a("https://api.gaana.com/home/discover/category/<category_id>?limit=0,20".replace("<category_id>", str));
        return uRLManager;
    }

    private void a(URLManager uRLManager) {
        this.f = (LinearLayout) this.i.findViewById(R.id.llParentListing);
        this.f.removeAllViews();
        if (ap.a().b(this.mContext) && c()) {
            this.o = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
            this.f.addView(this.o);
        }
        this.e = new CustomGridView(getActivity(), this);
        this.e.setOnAdRefreshListener(this);
        this.e.setNumColumns(2);
        this.e.setViewClassName(DiscoverItemView.class.getName());
        this.e.setOnBusinessObjectRetrievedCallback(new OnBusinessObjectRetrievedCallback() {
            public void onBusinessObjectRetrieved(BusinessObject businessObject, boolean z) {
                DiscoverDetailFragment.this.a(businessObject, z);
                DiscoverDetailFragment.this.e.populateGrid(DiscoverDetailFragment.this.g, z);
            }
        });
        this.e.seOnGetViewCallback(new OnGetViewCallback() {
            public View onGetViewCalled(ViewHolder viewHolder, View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
                if (viewHolder instanceof ItemAdViewHolder) {
                    return viewHolder.itemView;
                }
                return new DiscoverItemView(DiscoverDetailFragment.this.getActivity(), DiscoverDetailFragment.this).getPoplatedViewForDetail(viewHolder, businessObject, viewGroup, DiscoverDetailFragment.this.g.indexOf(businessObject));
            }
        });
        this.e.updateGridView(uRLManager);
        this.f.addView(this.e.getPopulatedView());
    }

    public void a(BusinessObject businessObject, boolean z) {
        b(businessObject, z);
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.e != null && this.e.getPagerAdapter() != null) {
            this.e.getPagerAdapter().notifyDataSetChanged();
        }
    }

    public void onAdRefresh() {
        super.onAdRefresh();
        if (ap.a().b(this.mContext)) {
            c();
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        refreshListView();
    }

    private void b(BusinessObject businessObject, boolean z) {
        if (!z) {
            this.g = new ArrayList();
            this.h = new ArrayList();
        }
        if (businessObject != null && (businessObject instanceof Items) && businessObject.getArrListBusinessObj() != null) {
            for (int i = 0; i < businessObject.getArrListBusinessObj().size(); i++) {
                BusinessObject businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(i);
                if ("ST".equalsIgnoreCase(((Item) businessObject2).getEntityType())) {
                    this.h.add(businessObject2);
                } else {
                    this.g.add(businessObject2);
                }
            }
            this.e.setTagCount(this.h.size());
        }
    }

    public void itemSelected(int i) {
        if (this.a) {
            if (i > 0) {
                i--;
            }
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_item));
                return;
            } else if (Util.j(this.mContext)) {
                this.h = GaanaActivity.arrListDropdownTagsSaved;
                Bundle bundle = new Bundle();
                bundle.putString("<category_id>", ((BusinessObject) this.h.get(i)).getBusinessObjId());
                bundle.putString("EXTRA_ACTIONBAR_TITLE", ((BusinessObject) this.h.get(i)).getRawName());
                BaseGaanaFragment discoverDetailFragment = new DiscoverDetailFragment();
                discoverDetailFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(discoverDetailFragment);
            } else {
                ap.a().f(this.mContext);
                return;
            }
        }
        this.a = true;
    }

    public void a() {
        if (!this.mClient.isConnected()) {
            this.mClient.connect();
        }
        this.TITLE = this.c;
        List arrayList = new ArrayList();
        AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.k), this.TITLE, Uri.parse(this.l), arrayList);
    }

    public void b() {
        AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.k));
        this.mClient.disconnect();
    }

    public void onDestroy() {
        super.onDestroy();
        ColombiaAdViewManager.a().a(this.q);
    }

    public void a(ADSTATUS adstatus) {
        this.p = adstatus;
    }

    public void b(ADSTATUS adstatus) {
        this.p = adstatus;
    }

    public void c(ADSTATUS adstatus) {
        this.p = adstatus;
    }

    public void d(ADSTATUS adstatus) {
        this.p = adstatus;
    }

    public void l() {
        if (ap.a().b(this.mContext) && this.o == null) {
            this.o = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    private boolean c() {
        return this.p != ADSTATUS.LOADING;
    }

    public String getSectionName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PLAYOUT_SECTION_TYPE.DISCOVER.name());
        stringBuilder.append("-");
        stringBuilder.append(getTitle());
        return stringBuilder.toString();
    }
}

package com.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.view.CustomGridView;
import com.gaana.view.item.DiscoverItemView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.ap;
import com.managers.e;
import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends BaseGaanaFragment implements b, c {
    boolean a = false;
    private View b = null;
    private LinearLayout c;
    private CustomGridView d;
    private ViewGroup e;
    private boolean f = true;
    private PublisherAdView g;
    private ADSTATUS h;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = setContentView(R.layout.discover_layout, viewGroup);
            this.a = true;
            this.g = new PublisherAdView(this.mContext);
            if (ap.a().b(this.mContext) && c()) {
                this.e = (ViewGroup) layoutInflater.inflate(R.layout.top_banner_ad, null);
            }
        }
        aa.a().b(false);
        return this.b;
    }

    public void loadTopBannerAds() {
        if (e.V == 0) {
            ColombiaAdViewManager.a().b(this.mContext, this.e, e.z, this.g, this, "");
            return;
        }
        ColombiaAdViewManager.a().a(this.mContext, this.e, 27, getClass().getSimpleName(), this.g, (b) this, "", new AdsUJData[0]);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            setCurrentFragment();
            GaanaApplication.getInstance().setCurrentPageName(getPageName());
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private boolean c() {
        return this.h != ADSTATUS.LOADING;
    }

    private void d() {
        URLManager e = e();
        e.c(Boolean.valueOf(false));
        a(e);
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (this.a) {
            d();
            this.a = false;
        }
        super.onViewCreated(view, bundle);
    }

    public void onResume() {
        updateView();
        ColombiaAdViewManager.a().a(this.mContext, this.e);
        super.onResume();
        ColombiaAdViewManager.a().a((c) this);
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        super.onPause();
    }

    public void onStart() {
        a();
        super.onStart();
    }

    public void onStop() {
        b();
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        ColombiaAdViewManager.a().a(this.g);
    }

    public void onDestroyView() {
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }

    private URLManager e() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Discover);
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a("https://api.gaana.com/home/discover-listing?new_artwork=1&limit=0,50");
        return uRLManager;
    }

    private void a(URLManager uRLManager) {
        this.c = (LinearLayout) this.b.findViewById(R.id.llParentListing);
        this.c.removeAllViews();
        if (this.e != null) {
            this.c.addView(this.e);
        }
        this.d = new CustomGridView(getActivity(), this);
        this.d.setOnAdRefreshListener(this);
        this.d.setNumColumns(1);
        this.d.setViewClassName(DiscoverItemView.class.getName());
        this.c.addView(this.d.getPopulatedView());
        this.d.updateGridView(uRLManager);
    }

    public void a() {
        if (!this.mClient.isConnected()) {
            this.mClient.connect();
        }
        List arrayList = new ArrayList();
        this.TITLE = "discover";
        AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse("android-app://com.gaana/gaanagoogle/discover"), this.TITLE, Uri.parse("https://gaana.com/discover"), arrayList);
    }

    public void b() {
        AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse("android-app://com.gaana/gaanagoogle/discover"));
        this.mClient.disconnect();
    }

    public void onAdRefresh() {
        if (ap.a().b(this.mContext) && c() && this.e == null) {
            this.e = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    public void a(ADSTATUS adstatus) {
        this.h = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void b(ADSTATUS adstatus) {
        this.h = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void c(ADSTATUS adstatus) {
        this.h = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void d(ADSTATUS adstatus) {
        this.h = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.DISCOVER.name();
    }

    public void l() {
        if (this.e == null) {
            this.e = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }
}

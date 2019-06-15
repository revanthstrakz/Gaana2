package com.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.DownloadDetailsActionbar;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.RecommendedPageView;
import com.gaana.view.item.DownloadProgressBar;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.DownloadManager;
import com.managers.ap;

public class DownloadHomeFragment extends BaseGaanaFragment {
    private LinearLayout a = null;
    private View b = null;
    private RecommendedPageView c;
    private int d = 5;
    private String e = GaanaApplication.getInstance().getResources().getString(R.string.downloads);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = setContentView(R.layout.activity_download_home, viewGroup);
            this.d = getArguments().getInt("recommended_page_type", 5);
            if (this.d == 6) {
                this.e = GaanaApplication.getInstance().getResources().getString(R.string.music_on_my_phone);
            }
            this.a = (LinearLayout) this.b.findViewById(R.id.llParentListing);
            this.b.findViewById(R.id.ll_download_home_mydownload).setVisibility(8);
            this.c = new RecommendedPageView(this.mContext);
            this.a.addView(this.c.getRecommendedView((BaseGaanaFragment) this, this.mContext, this.d));
            DownloadDetailsActionbar downloadDetailsActionbar = new DownloadDetailsActionbar(this.mContext, false, this.e);
            downloadDetailsActionbar.a(false);
            downloadDetailsActionbar.c(true);
            setActionBar(this.b, downloadDetailsActionbar);
        }
        a();
        updateView();
        setGAScreenName("Downloads Home", "MyMusic-Downloads");
        MoEngage.getInstance().reportSectionViewedEvent("Downloads");
        ((GaanaActivity) this.mContext).title = this.e;
        return this.b;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onResume() {
        super.onResume();
    }

    public void refreshListView() {
        super.refreshListView();
        a();
    }

    private void a() {
        if (this.d != 5) {
            if (this.c != null) {
                this.c.refreshListView();
            }
        } else if (this.b != null) {
            LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.f32download.home.progressbar);
            this.c.refreshListView();
            if (DownloadManager.c().k() == -1 || !ap.a().j()) {
                linearLayout.removeAllViews();
            } else {
                this.c.removeMessageHeaderView();
                linearLayout.removeAllViews();
                linearLayout.addView(new DownloadProgressBar(this.mContext, this).getView(null));
                this.b.findViewById(R.id.ll_download_home_mydownload).setVisibility(0);
                this.b.findViewById(R.id.download_home_mydownload).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        BaseGaanaFragment downloadDetailsFragment = new DownloadDetailsFragment();
                        downloadDetailsFragment.setArguments(bundle);
                        ((GaanaActivity) DownloadHomeFragment.this.mContext).displayFragment(downloadDetailsFragment);
                    }
                });
            }
        }
    }

    public void onDestroyView() {
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.c = null;
        if (this.a != null) {
            this.a.removeAllViews();
            this.a = null;
        }
        this.b = null;
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.DOWNLOADS.name();
    }
}

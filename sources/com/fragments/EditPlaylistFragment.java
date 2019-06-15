package com.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gaana.R;
import com.gaana.actionbar.EditPlaylistActionbar;
import com.gaana.view.CustomListView;
import com.models.ListingButton;
import com.models.ListingComponents;

public class EditPlaylistFragment extends BaseGaanaFragment {
    public CustomListView a;
    private LinearLayout b;
    private ListingComponents c = null;
    private ListingButton d;
    private View e = null;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.e == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.e = setContentView(R.layout.activity_main, viewGroup);
            this.b = (LinearLayout) this.e.findViewById(R.id.llParentListing);
            if (bundle == null) {
                this.c = this.mAppState.getListingComponents();
            } else {
                this.c = (ListingComponents) bundle.getParcelable("listing_component");
            }
            this.d = (ListingButton) this.c.c().get(0);
            a();
        }
        updateView();
        this.mAppState.setListingComponents(this.c);
        setActionBar(this.e, new EditPlaylistActionbar(this.mContext, this), false);
        return this.e;
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(this.c);
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("listing_component", this.c);
    }

    public void onPause() {
        super.onPause();
    }

    private void a() {
        this.a = new CustomListView(this.mContext, this);
        this.a.setUpdateListView(this.d);
        this.b.addView(this.a.getListView());
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroyView() {
        if (!(this.e == null || this.e.getParent() == null)) {
            ((ViewGroup) this.e.getParent()).removeView(this.e);
        }
        super.onDestroyView();
    }
}

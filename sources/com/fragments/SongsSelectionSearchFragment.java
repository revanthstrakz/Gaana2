package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbar.CustomSearchView;
import com.gaana.R;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.gaana.models.Playlists.Playlist;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.GaanaSearchManager.b;
import com.managers.URLManager.BusinessObjectType;
import com.services.d;
import com.services.n;
import com.utilities.Util;

public class SongsSelectionSearchFragment extends BaseGaanaFragment implements b {
    private View a = null;
    private boolean b = false;
    private CustomSearchView c;
    private TypedValue d = new TypedValue();
    private SearchTabFragment e;
    private int f = 0;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().getTheme().resolveAttribute(R.attr.first_line_color, this.d, true);
        if (this.a == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            GaanaSearchManager.a().a((Activity) this.mContext, "", "0");
            this.a = setContentView(R.layout.songs_selection_search, viewGroup);
            this.c = (CustomSearchView) this.a.findViewById(R.id.custom_songs_search_view);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            this.e = new SearchTabFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("ADD_MORE_SONGS", true);
            bundle2.putBoolean("is_child_fragment", true);
            bundle2.putBoolean("should_hide_bottom_bar", true);
            if (getArguments() != null) {
                this.f = getArguments().getInt("search_type");
                bundle2.putSerializable("BUSINESS_OBJECT", getArguments().getSerializable("BUSINESS_OBJECT"));
            }
            this.e.setArguments(bundle2);
            beginTransaction.add((int) R.id.songs_selection_fragment, this.e);
            beginTransaction.commit();
        }
        Object a = n.a(d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (a != null && (a instanceof Languages)) {
            GaanaSearchManager.a().a(((Languages) a).getArrListBusinessObj());
        }
        return this.a;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onResume() {
        super.onResume();
        this.c.setSearchInterface(this);
        GaanaSearchManager.a().a((b) this);
        if (this.f == 1) {
            GaanaSearchManager.a().a(SearchType.OnlySongs);
        } else {
            GaanaSearchManager.a().a(SearchType.Playlist_Search);
        }
        GaanaApplication.getInstance().setCurrentPageName(getPageName());
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        GaanaSearchManager.a().a(null);
        super.onDestroy();
    }

    public void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter) {
        if (!(nextGenAutoSuggestAdapter == null || getArguments() == null)) {
            BusinessObject businessObject = (BusinessObject) getArguments().getSerializable("BUSINESS_OBJECT");
            if (businessObject instanceof Playlist) {
                nextGenAutoSuggestAdapter.setCurrentTracksInPlaylist(((Playlist) businessObject).getArrListBusinessObj());
            }
        }
        this.e.a(nextGenAutoSuggestAdapter);
    }

    public void a(String str, String str2) {
        this.e.a(str);
        this.e.a(false);
        GaanaSearchManager.a().a((Activity) this.mContext, str, str2);
    }

    public void a(boolean z, boolean z2) {
        this.e.b(z);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!z) {
            a(this.mContext);
        }
    }

    public void a(Context context) {
        if (!(this.a == null || this.a.getWindowToken() == null)) {
            Util.a(this.mContext, this.a);
        }
        if (this.c != null && this.c.hasFocus()) {
            this.c.clearFocus();
        }
    }

    public void e() {
        this.e.i();
    }

    public void a(View view) {
        this.e.b(view);
    }

    public void onDestroyView() {
        if (((ViewGroup) this.a.getParent()) != null) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        super.onDestroyView();
    }

    public void refreshListView() {
        this.e.h();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        refreshListView();
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        refreshListView();
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.SEARCH.name();
    }
}

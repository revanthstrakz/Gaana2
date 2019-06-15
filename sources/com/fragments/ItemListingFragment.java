package com.fragments;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.actionbar.GenericBackActionBar.a;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ViewPagerAdapter;
import com.gaana.adapter.ViewPagerAdapter.AddItemListner;
import com.gaana.models.BusinessObject;
import com.gaana.models.Notifications.Notification;
import com.gaana.view.CustomListView;
import com.managers.aa;
import com.managers.ab;
import com.managers.ab.b;
import com.managers.aj;
import com.managers.ap;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.f;
import com.views.CustomViewPager;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemListingFragment extends BaseGaanaFragment implements OnPageChangeListener, OnClickListener, a, AddItemListner, aa.a, b {
    private int a = 0;
    private boolean b = false;
    private LinearLayout c;
    private ListingComponents d = null;
    private String e;
    private CustomViewPager f;
    private ViewPagerAdapter g;
    private LinearLayout h;
    private ArrayList<CustomListView> i = null;
    private f j;
    private Drawable k;

    public void onBackClicked() {
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
    }

    public void onSubmitClicked() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new int[]{R.attr.ic_action_forward});
        this.k = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.containerView = setContentView(R.layout.activity_main, viewGroup);
        this.c = (LinearLayout) this.containerView.findViewById(R.id.llParentListing);
        this.h = (LinearLayout) this.layoutInflater.inflate(R.layout.home_notification_listing, null);
        this.c.addView(this.h);
        if (bundle == null) {
            if (this.d == null) {
                this.d = this.mAppState.getListingComponents();
            } else {
                this.mAppState.setListingComponents(this.d);
            }
            if (getArguments().containsKey("ArtistID")) {
                this.e = getArguments().getString("ArtistID");
            }
        } else {
            this.d = (ListingComponents) bundle.getParcelable("listing_component");
            this.e = bundle.getString("ArtistID");
            if (this.d != null) {
                this.mAppState.setListingComponents(this.d);
            }
        }
        if (this.d != null) {
            if (getArguments().getBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_NEXT", false)) {
                this.a = 1;
                View inflate = this.layoutInflater.inflate(R.layout.view_top_tabbar_buttons, null);
                inflate.findViewById(R.id.btnLeft).setOnClickListener(this);
                inflate.findViewById(R.id.btnRight).setOnClickListener(this);
                ((GaanaActivity) this.mContext).actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                ((ImageView) inflate.findViewById(R.id.btnRight)).setImageDrawable(this.k);
                ((TextView) inflate.findViewById(R.id.tvCurrentViewTag)).setText(R.string.select_songs);
                setActionBar(this.containerView, inflate, false);
            } else if (getArguments().getBoolean("notifications", false)) {
                ((GaanaActivity) this.mContext).title = "notifications";
                setGAScreenName("NotificationScreen", "NotificationScreen");
                setActionBar(this.containerView, new GenericBackActionBar(this.mContext, this.d.d(), this, true));
                aa.a().a((aa.a) this);
                ab.a().a((b) this);
                ((ListingButton) this.d.c().get(0)).a(null);
                if (!Constants.l) {
                    this.c.setBackgroundColor(getResources().getColor(R.color.black));
                }
            } else if (getArguments().getBoolean("Blocked Users Settings", false)) {
                ((GaanaActivity) this.mContext).title = "Blocked Users Settings";
                setGAScreenName("BlockedUsersScreen", "BlockedUsersScreen");
                setActionBar(this.containerView, new GenericBackActionBar(this.mContext, this.d.d()));
                ((ListingButton) this.d.c().get(0)).a(null);
                if (!Constants.l) {
                    this.c.setBackgroundColor(getResources().getColor(R.color.black));
                }
            } else {
                ((GaanaActivity) this.mContext).title = this.d.d();
                setActionBar(this.containerView, new GenericBackActionBar(this.mContext, this.d.d()));
            }
        }
        this.i = new ArrayList();
        if (!a(this.d).booleanValue()) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.unable_process_request));
        }
        this.j = new f(this.mContext);
        b();
        ((BaseActivity) this.mContext).hasLoginChanged().booleanValue();
        return this.containerView;
    }

    public void onResume() {
        super.onResume();
        int i = 0;
        if (getArguments().getBoolean("notifications", false)) {
            setPlayerFreeFragment();
            ((GaanaActivity) this.mContext).hideMiniPlayerForPlayerFreeFragment();
        }
        this.mAppState.setListingComponents(this.d);
        while (i < this.i.size()) {
            ((CustomListView) this.i.get(i)).updateSongQueue();
            i++;
        }
        updateView();
    }

    public String a() {
        return this.e;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("listing_component", this.d);
        bundle.putString("ArtistID", this.e);
    }

    public void onDestroyView() {
        aa.a().a(null);
        if (!(this.containerView == null || this.containerView.getParent() == null)) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.f = new CustomViewPager(getActivity());
        this.f.setOnPageChangeListener(this);
        this.g = new ViewPagerAdapter();
        this.h.setVisibility(8);
        if (this.d != null && this.d.c() != null) {
            d();
            this.f.setAdapter(this.g);
            c();
        }
    }

    private void c() {
        if (this.c.getChildCount() > 2) {
            this.c.removeAllViews();
        }
        if (this.c.getChildCount() == 1) {
            this.c.addView(this.f);
        }
    }

    private void d() {
        if (this.d.c().size() < 2) {
            this.f.a();
        }
        this.g.setAdapterParams(this.d.c().size(), this, this.d);
    }

    private Boolean a(ListingComponents listingComponents) {
        Boolean valueOf = Boolean.valueOf(true);
        if (listingComponents == null) {
            return Boolean.valueOf(false);
        }
        return listingComponents.d() == null ? Boolean.valueOf(false) : valueOf;
    }

    public Object addItem(ViewGroup viewGroup, int i) {
        CustomListView customListView = new CustomListView(getActivity(), this);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LayoutParams(-1, 30));
        if (this.d.d() == null || !this.d.d().equalsIgnoreCase("Player Queue")) {
            linearLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            linearLayout.setBackgroundColor(this.mContext.getResources().getColor(17170445));
        }
        this.i.add(customListView);
        customListView.setUpdateListView((ListingButton) this.d.c().get(i));
        viewGroup.addView(customListView.getListView(), 0);
        return customListView.getListView();
    }

    public Object addItem(ViewGroup viewGroup, ListingButton listingButton) {
        CustomListView customListView = new CustomListView(getActivity(), this);
        this.i.add(customListView);
        customListView.setUpdateListView(listingButton);
        viewGroup.addView(customListView.getListView(), 0);
        return customListView.getListView();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btnLeft) {
            if (id == R.id.btnRight && getArguments().getBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_NEXT", false)) {
                ArrayList arrListTracksForPlaylist = this.mAppState.getArrListTracksForPlaylist();
                if (arrListTracksForPlaylist != null && arrListTracksForPlaylist.size() != 0) {
                    ap.a().a(getActivity(), true);
                } else if (this.j != null) {
                    this.j.a(this.mContext.getString(R.string.select_atleas_a_track));
                }
            }
        } else if (((GaanaActivity) getActivity()) != null) {
            if (this.a == 2 && this.b) {
                ((GaanaActivity) this.mContext).popBackStack();
            }
            ((GaanaActivity) getActivity()).onBackPressedHandling();
        }
    }

    public void refreshListView() {
        if (this.i != null) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                CustomListView customListView = (CustomListView) it.next();
                if (customListView.getListAdapter() != null) {
                    customListView.getListAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        refreshListView();
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClearAllClicked() {
        if (this.i.size() > 0 && ((CustomListView) this.i.get(0)).getListAdapter() != null && ((CustomListView) this.i.get(0)).getListAdapter().getItemCount() > 0) {
            ((CustomListView) this.i.get(0)).refreshList();
        }
    }

    public void a(Notification notification) {
        if (this.mContext != null) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (ItemListingFragment.this.i.size() > 0 && ItemListingFragment.this.i.get(0) != null) {
                        ((CustomListView) ItemListingFragment.this.i.get(0)).refreshList();
                    }
                }
            });
        }
    }

    public void b(Notification notification) {
        if (this.mContext != null) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (ItemListingFragment.this.i.size() > 0 && ItemListingFragment.this.i.get(0) != null) {
                        ((CustomListView) ItemListingFragment.this.i.get(0)).refreshList();
                    }
                }
            });
        }
    }
}

package com.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbar.GenericBackActionBar;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.ArtistItemView;
import com.gaana.view.item.ArtistItemView.ArtistItemHolder;
import com.gaana.view.item.DownloadAlbumItemView;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.VerticalListingView;
import com.gaana.view.item.VerticalListingView.VerticalListingHolder;
import com.i.d;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import java.util.ArrayList;

public class ArtistFragment extends BaseGaanaFragment {
    private RecyclerView a;
    private a b;
    private VerticalListingView c;
    private ArtistItemView d;
    private Artist e;
    private ArrayList<BusinessObject> f = new ArrayList();
    private GenericBackActionBar g;
    private ArrayList<BusinessObject> h;

    class a extends Adapter<ViewHolder> {
        public int getItemViewType(int i) {
            return i == 0 ? 2 : 1;
        }

        a() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case 1:
                    return new VerticalListingHolder(LayoutInflater.from(ArtistFragment.this.mContext).inflate(R.layout.view_vertical_listing, viewGroup, false));
                case 2:
                    return new ArtistItemHolder(LayoutInflater.from(ArtistFragment.this.mContext).inflate(R.layout.view_item_artist_card, viewGroup, false));
                default:
                    return null;
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int itemViewType = viewHolder.getItemViewType();
            if (itemViewType == 1) {
                BusinessObject businessObject = (BusinessObject) ArtistFragment.this.f.get(i - 1);
                if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
                    ArtistFragment.this.c.setBaseItemView(new DownloadSongsItemView(ArtistFragment.this.mContext, ArtistFragment.this));
                } else if (businessObject.getBusinessObjType() == BusinessObjectType.Albums) {
                    ArtistFragment.this.c.setBaseItemView(new DownloadAlbumItemView(ArtistFragment.this.mContext, ArtistFragment.this));
                }
                ArtistFragment.this.c.getPoplatedView(viewHolder, businessObject, null);
            } else if (itemViewType == 2) {
                ArtistFragment.this.d.getPoplatedView(viewHolder, ArtistFragment.this.e, null);
            }
        }

        public int getItemCount() {
            return ArtistFragment.this.f.size() > 0 ? ArtistFragment.this.f.size() + 1 : 0;
        }
    }

    public void setGAScreenName(String str, String str2) {
    }

    public static BaseGaanaFragment a(Artist artist) {
        ArtistFragment artistFragment = new ArtistFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_artist_details", artist);
        artistFragment.setArguments(bundle);
        return artistFragment;
    }

    public void refreshListView() {
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.containerView == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.containerView = setContentView(R.layout.fragment_artist, viewGroup);
            ((BaseActivity) this.mContext).showProgressDialog();
            a(this.containerView, getArguments());
            this.g = new GenericBackActionBar(this.mContext, this.e.getName());
            setActionBar(this.containerView, this.g);
        } else if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
        return this.containerView;
    }

    private void a(View view, Bundle bundle) {
        this.e = (Artist) bundle.getSerializable("extra_artist_details");
        if (this.e == null) {
            ((GaanaActivity) getActivity()).popBackStackImmediate();
            return;
        }
        this.a = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.b = new a();
        this.a.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.a.setAdapter(this.b);
        this.d = new ArtistItemView(this.mContext, this);
        this.c = new VerticalListingView(this.mContext, this);
        a();
    }

    public void onResume() {
        super.onResume();
        if (this.h != null) {
            this.mAppState.setCurrentBusObjInListView(this.h);
        }
    }

    private void a() {
        d.a(new Runnable() {
            public void run() {
                if (ArtistFragment.this.isAdded()) {
                    com.managers.a aVar = new com.managers.a();
                    URLManager uRLManager = new URLManager();
                    uRLManager.b(ArtistFragment.this.e.getName());
                    uRLManager.a(BusinessObjectType.Albums);
                    BusinessObject a = aVar.a(uRLManager, "", 0, 3, null, null);
                    if (a.getArrListBusinessObj() != null && a.getArrListBusinessObj().size() > 0) {
                        a.setName(ArtistFragment.this.e.getName());
                        a.setLanguage(ArtistFragment.this.e.getLanguage());
                        a.setBusinessObjType(BusinessObjectType.Albums);
                        ArtistFragment.this.f.add(a);
                    }
                    uRLManager.a(BusinessObjectType.Tracks);
                    aVar.a();
                    BusinessObject a2 = aVar.a(uRLManager, "", 0, 3, null, null);
                    if (a2.getArrListBusinessObj() != null && a2.getArrListBusinessObj().size() > 0) {
                        a2.setName(ArtistFragment.this.e.getName());
                        a2.setLanguage(ArtistFragment.this.e.getLanguage());
                        a2.setBusinessObjType(BusinessObjectType.Tracks);
                        ArtistFragment.this.h = a2.getArrListBusinessObj();
                        ArtistFragment.this.mAppState.setCurrentBusObjInListView(a2.getArrListBusinessObj());
                        ArtistFragment.this.f.add(a2);
                    }
                    ArtistFragment.this.b();
                }
            }
        });
    }

    private void b() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (ArtistFragment.this.isAdded()) {
                    ((BaseActivity) ArtistFragment.this.mContext).hideProgressDialog();
                    if (ArtistFragment.this.f.size() > 0) {
                        ArtistFragment.this.b.notifyDataSetChanged();
                        return;
                    }
                    ArtistFragment.this.containerView = null;
                    ((GaanaActivity) ArtistFragment.this.mContext).popBackStackImmediate();
                    af.a(ArtistFragment.this.mContext, ArtistFragment.this).a((int) R.id.artistMenu, ArtistFragment.this.e);
                }
            }
        });
    }

    public void onDestroyView() {
        if (!(this.containerView == null || this.containerView.getParent() == null)) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
    }
}

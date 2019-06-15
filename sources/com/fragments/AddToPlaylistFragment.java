package com.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.b.i;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.item.AddToPlaylistItemView;
import com.gaana.view.item.AddToPlaylistItemView.AddToPlaylistItemViewHolder;
import com.gaana.view.item.AddToPlaylistSongsView;
import com.gaana.view.item.AddToPlaylistSongsView.AddToPlaylistSongsViewHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.l.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddToPlaylistFragment extends BaseGaanaFragment implements OnClickListener, a {
    int a = 0;
    int b = 0;
    private int c = 0;
    private boolean d = false;
    private ListingComponents e = null;
    private RecyclerView f;
    private ViewGroup g = null;
    private ArrayList<BusinessObject> h;
    private ArrayList<BusinessObject> i;
    private a j;
    private AddToPlaylistItemView k;
    private String l = "";

    protected class a extends Adapter<ViewHolder> {
        protected a() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new AddToPlaylistSongsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_playlist_header, viewGroup, false));
            }
            if (i == 2 || i == 4) {
                return new AddToPlaylistItemViewHolder(AddToPlaylistFragment.this.k.createViewHolder(viewGroup, i));
            }
            if (i != 8) {
                return null;
            }
            View inflate = LayoutInflater.from(AddToPlaylistFragment.this.mContext).inflate(R.layout.view_item_text_view, viewGroup, false);
            ((TextView) inflate.findViewById(R.id.txt_title)).setTypeface(i.a(AddToPlaylistFragment.this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
            return new ItemAdViewHolder(inflate);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (viewHolder instanceof AddToPlaylistItemViewHolder) {
                BusinessObject businessObject;
                Object obj = "";
                if (viewHolder.getItemViewType() == 2) {
                    businessObject = (BusinessObject) AddToPlaylistFragment.this.h.get(i - 2);
                    obj = "Recent Playlist";
                } else if (viewHolder.getItemViewType() == 4) {
                    if (AddToPlaylistFragment.this.a > 0) {
                        businessObject = (BusinessObject) AddToPlaylistFragment.this.i.get((i - 3) - AddToPlaylistFragment.this.a);
                    } else {
                        businessObject = (BusinessObject) AddToPlaylistFragment.this.i.get(i - 2);
                    }
                    obj = "All Playlist";
                } else {
                    businessObject = null;
                }
                viewHolder.itemView.setTag(R.id.ga_category, "Add to Playlist Screen");
                viewHolder.itemView.setTag(R.id.ga_action, obj);
                AddToPlaylistFragment.this.k.getPoplatedView(viewHolder, businessObject, null);
            } else if (viewHolder instanceof AddToPlaylistSongsViewHolder) {
                AddToPlaylistSongsView addToPlaylistSongsView = new AddToPlaylistSongsView(AddToPlaylistFragment.this.mContext, AddToPlaylistFragment.this);
                addToPlaylistSongsView.setFragmentTagToPop(AddToPlaylistFragment.this.l);
                addToPlaylistSongsView.getPoplatedView(viewHolder, null, null);
            } else if (!(viewHolder instanceof ItemAdViewHolder)) {
            } else {
                if (i != 1 || AddToPlaylistFragment.this.a <= 0) {
                    ((TextView) viewHolder.itemView.findViewById(R.id.txt_title)).setText(AddToPlaylistFragment.this.mContext.getString(R.string.add_playlist_caps));
                } else {
                    ((TextView) viewHolder.itemView.findViewById(R.id.txt_title)).setText(AddToPlaylistFragment.this.mContext.getString(R.string.recently_updated));
                }
            }
        }

        public int getItemCount() {
            int i = AddToPlaylistFragment.this.b > 0 ? (AddToPlaylistFragment.this.b + 1) + 1 : 1;
            return AddToPlaylistFragment.this.a > 0 ? i + (AddToPlaylistFragment.this.a + 1) : i;
        }

        public int getItemViewType(int i) {
            if (i == 0) {
                return 1;
            }
            if (AddToPlaylistFragment.this.a > 0 && i == 1) {
                return 8;
            }
            if (AddToPlaylistFragment.this.a > 0 && i < AddToPlaylistFragment.this.a + 2) {
                return 2;
            }
            if ((AddToPlaylistFragment.this.a == 0 ? AddToPlaylistFragment.this.a : AddToPlaylistFragment.this.a + 1) + 1 == i) {
                return 8;
            }
            return i < (1 + AddToPlaylistFragment.this.b) + (AddToPlaylistFragment.this.a == 0 ? AddToPlaylistFragment.this.a : AddToPlaylistFragment.this.a + 2) ? 4 : 4;
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("listing_component", this.e);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.g == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.g = (ViewGroup) setContentView(R.layout.fragment_add_to_playlist, viewGroup);
            ((TextView) this.g.findViewById(R.id.txt_header)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
            this.f = (RecyclerView) this.g.findViewById(R.id.recycler_view);
            this.f.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.j = new a();
            this.f.setAdapter(this.j);
            if (bundle == null) {
                this.e = this.mAppState.getListingComponents();
            } else {
                this.e = (ListingComponents) bundle.getParcelable("listing_component");
                if (this.e != null) {
                    this.mAppState.setListingComponents(this.e);
                }
            }
            this.d = getArguments().getBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_ISFROMHEADER", false);
            this.c = 2;
            a();
            this.k = new AddToPlaylistItemView(this.mContext, this);
            try {
                this.l = this.d ? String.valueOf(Integer.valueOf(getTag()).intValue() - 1) : getTag();
            } catch (NumberFormatException unused) {
                this.l = getTag();
            }
            this.k.setFragmentTagToPop(this.l);
        }
        this.g.findViewById(R.id.btnLeft).setOnClickListener(this);
        updateView();
        return this.g;
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(this.e);
    }

    private void a() {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading_string_text));
        PlaylistSyncManager.getInstance().getMyPlaylistAsync(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (AddToPlaylistFragment.this.e != null) {
                    ListingButton listingButton = (ListingButton) AddToPlaylistFragment.this.e.c().get(0);
                    ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                    if (arrListBusinessObj.size() > 0 && (arrListBusinessObj.get(0) instanceof Playlist)) {
                        AddToPlaylistFragment.this.b(arrListBusinessObj);
                        AddToPlaylistFragment.this.a(arrListBusinessObj);
                        if (arrListBusinessObj.size() > 5) {
                            AddToPlaylistFragment.this.h = new ArrayList(arrListBusinessObj.subList(0, 2));
                            AddToPlaylistFragment.this.a = AddToPlaylistFragment.this.h.size();
                        }
                        AddToPlaylistFragment.this.i = arrListBusinessObj;
                        AddToPlaylistFragment.this.b = AddToPlaylistFragment.this.i.size();
                        Collections.sort(arrListBusinessObj, new Comparator<Object>() {
                            public int compare(Object obj, Object obj2) {
                                Playlist playlist = (Playlist) obj;
                                if (!TextUtils.isEmpty(playlist.getName())) {
                                    Playlist playlist2 = (Playlist) obj2;
                                    if (!TextUtils.isEmpty(playlist2.getName())) {
                                        return playlist.getName().compareToIgnoreCase(playlist2.getName());
                                    }
                                }
                                if (TextUtils.isEmpty(playlist.getName()) && TextUtils.isEmpty(((Playlist) obj2).getName())) {
                                    return 0;
                                }
                                return TextUtils.isEmpty(playlist.getName()) ? 1 : -1;
                            }
                        });
                        if (AddToPlaylistFragment.this.isAdded() && arrListBusinessObj.size() > 0) {
                            AddToPlaylistFragment.this.j.notifyDataSetChanged();
                        }
                    }
                    listingButton.a(arrListBusinessObj);
                }
                ((BaseActivity) AddToPlaylistFragment.this.mContext).hideProgressDialog();
            }

            public void onErrorResponse(BusinessObject businessObject) {
                AddToPlaylistFragment.this.handleErrorResponse(businessObject);
            }
        }, false);
    }

    private void a(ArrayList<?> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((Playlist) arrayList.get(i)).getIsMiniPlaylist() != null && ((Playlist) arrayList.get(i)).getIsMiniPlaylist().equalsIgnoreCase("1")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }

    private void b(ArrayList<?> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((Playlist) arrayList.get(i)).getAutomated() != null && ((Playlist) arrayList.get(i)).getAutomated().equalsIgnoreCase("1")) {
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnLeft) {
            u.a().b("Add to Playlist Screen", "Close");
            if (((GaanaActivity) getActivity()) != null) {
                if (this.c == 2 && this.d) {
                    ((GaanaActivity) this.mContext).popBackStackImmediate();
                }
                if (isAdded()) {
                    ((GaanaActivity) getActivity()).onBackPressedHandling();
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

    public void onDestroyView() {
        if (!(this.g == null || this.g.getParent() == null)) {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        }
        super.onDestroyView();
    }
}

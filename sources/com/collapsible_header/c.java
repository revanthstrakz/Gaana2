package com.collapsible_header;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.AddToPlaylistFragment;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.ItemListingFragment;
import com.fragments.ListingFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.adapter.ListAdapterSectionIndexer;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions.FavoriteOccasion;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserActivities.UserActivity;
import com.gaana.models.UserMessage;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.FavoriteOccasionItemView;
import com.gaana.view.FavoriteOccasionItemView.FavoriteOccasionItemHolder;
import com.gaana.view.item.AlbumItemView;
import com.gaana.view.item.ArtistItemView;
import com.gaana.view.item.ArtistItemView.ArtistItemHolder;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.EmptyMessageHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.SpinnerHolder;
import com.gaana.view.item.DownloadAlbumItemView;
import com.gaana.view.item.DownloadAlbumItemView.DownloadAlbumItemHolder;
import com.gaana.view.item.DownloadPlaylistItemView;
import com.gaana.view.item.DownloadPlaylistItemView.DownloadPlayListItemHolder;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.DownloadSongListingView.DownloadSongListingHolder;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.gaana.view.item.EditPlaylistSelectSongView;
import com.gaana.view.item.NewGenericItemView;
import com.gaana.view.item.NewGenericItemView.NewGenericItemHolder;
import com.gaana.view.item.NotificationItemView;
import com.gaana.view.item.NotificationItemView.NotificationItemHolder;
import com.gaana.view.item.PlaylistItemView;
import com.gaana.view.item.RadioButtonPlaylistView;
import com.gaana.view.item.RadioButtonSongView;
import com.gaana.view.item.RadioButtonSongView.RadioButtonSongHolder;
import com.gaana.view.item.RadioItemView;
import com.gaana.view.item.RadioItemView.RadioItemHolder;
import com.gaana.view.item.SongsItemView;
import com.i.i;
import com.managers.URLManager.BusinessObjectType;
import com.managers.al;
import com.managers.aq;
import com.managers.e;
import com.models.ListingButton;
import com.models.PlayerTrack;
import com.services.l.l;
import com.services.l.p;
import com.services.l.s;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class c implements OnScrollListener, l {
    private Fragment A;
    private Spinner B;
    private boolean C;
    private boolean D;
    private View E;
    private int F;
    private int G;
    private ArrayList<BusinessObject> H;
    private ArrayList<BusinessObject> I;
    public SwipeRefreshLayout a;
    boolean b;
    protected b c;
    Context d;
    GaanaApplication e;
    ObservableRecyclerView f;
    e g;
    BaseItemView h;
    ListingButton i;
    ProgressBar j;
    Boolean k;
    Boolean l;
    BaseGaanaFragment m;
    s n;
    s o;
    s p;
    private LayoutInflater q;
    private View r;
    private View s;
    private BusinessObject t;
    private p u;
    private boolean v;
    private boolean w;
    private LinearLayout x;
    private View y;
    private long z;

    public interface b {
        void a(BusinessObject businessObject, BusinessObjectType businessObjectType);
    }

    static class a extends ViewHolder {
        public a(View view) {
            super(view);
        }
    }

    public void loadMoreData(int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void a(Fragment fragment) {
        this.A = fragment;
    }

    public View a() {
        return this.E;
    }

    public c(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.a = null;
        this.b = false;
        this.q = null;
        this.r = null;
        this.s = null;
        this.u = null;
        this.v = false;
        this.w = false;
        this.y = null;
        this.e = null;
        this.g = null;
        this.k = Boolean.valueOf(false);
        this.l = Boolean.valueOf(false);
        this.z = 0;
        this.n = new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                System.out.println("CustomListViewMaterial.onRetreivalComplete");
                if (c.this.w) {
                    c.this.w = false;
                    if (c.this.x != null) {
                        c.this.x.removeAllViews();
                    }
                    c.this.h();
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                        c.this.k = Boolean.valueOf(true);
                    } else if (c.this.a(businessObject.getArrListBusinessObj().get(0), c.this.h)) {
                        c.this.g.updateAdapterArrayList(businessObject.getArrListBusinessObj());
                        c.this.i.a(c.this.g.getAdapterArrayList());
                        c.this.e();
                    } else {
                        return;
                    }
                    if (c.this.m != null && (c.this.m instanceof SongParallexListingFragment)) {
                        ((SongParallexListingFragment) c.this.m).e();
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                c.this.w = false;
            }
        };
        this.o = new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                String str = null;
                if (c.this.z != 0) {
                    Constants.a("Load", Calendar.getInstance().getTimeInMillis() - c.this.z, "Artist detail", null);
                }
                boolean z = false;
                c.this.a.setRefreshing(false);
                c.this.j.setVisibility(8);
                if (c.this.i.i()) {
                    c.this.a.setEnabled(true);
                } else {
                    c.this.a.setEnabled(true);
                }
                if (businessObject == null) {
                    c.this.a(true);
                    c.this.k = Boolean.valueOf(true);
                    return;
                }
                if (c.this.i.k() != null) {
                    c.this.i.k().a(businessObject);
                    businessObject.setArrListBusinessObj(c.this.i.g());
                }
                if (c.this.e.getListingComponents() != null) {
                    str = c.this.e.getListingComponents().a();
                }
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                if (arrListBusinessObj == null || arrListBusinessObj.size() == 0) {
                    if (((c.this.m instanceof ListingFragment) || (c.this.m instanceof ListingFragmentMaterial) || (c.this.m instanceof SongParallexListingFragment)) && c.this.c != null) {
                        c.this.c.a(businessObject, c.this.i.c().i());
                    }
                    c.this.a(true);
                    c.this.k = Boolean.valueOf(true);
                } else {
                    try {
                        HashMap h = c.this.i.c().h();
                        if (!(h == null || h.get("type") == null || !((String) h.get("type")).equals("mysongs"))) {
                            z = true;
                        }
                        if (z || (str != null && (str instanceof Playlist))) {
                            for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                                Track track = (Track) arrListBusinessObj.get(size);
                                if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                    track = LocalMediaManager.getInstance(c.this.d).getLocalTrackFromHash(track.getBusinessObjId());
                                    arrListBusinessObj.remove(size);
                                    if (track != null) {
                                        arrListBusinessObj.add(size, track);
                                    }
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                    if (businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        c.this.a(true);
                        c.this.k = Boolean.valueOf(true);
                    } else {
                        c.this.a(businessObject.getArrListBusinessObj(), c.this.h);
                        c.this.a(businessObject);
                    }
                    if (c.this.i.c().e().booleanValue() && businessObject.getArrListBusinessObj().size() < 20) {
                        c.this.k = Boolean.valueOf(true);
                    }
                    if (c.this.c != null) {
                        c.this.c.a(businessObject, c.this.i.c().i());
                    }
                    c.this.H = businessObject.getArrListBusinessObj();
                    c.this.I.clear();
                    c.this.I.addAll(c.this.H);
                }
                if (c.this.m != null && (c.this.m instanceof SongParallexListingFragment) && arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                    ((SongParallexListingFragment) c.this.m).e();
                }
            }
        };
        this.p = new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() != 0) {
                    c.this.b(businessObject);
                }
            }
        };
        this.F = 1;
        this.G = 2;
        this.H = new ArrayList();
        this.I = new ArrayList();
        this.z = Calendar.getInstance().getTimeInMillis();
        this.d = context;
        this.m = baseGaanaFragment;
        this.q = (LayoutInflater) this.d.getSystemService("layout_inflater");
        if (this.m instanceof SongParallexListingFragment) {
            this.D = true;
            this.r = this.q.inflate(R.layout.dynamic_home_item_listing, null);
        } else {
            this.r = this.q.inflate(R.layout.view_custom_listview_material, null);
        }
        this.e = (GaanaApplication) this.d.getApplicationContext();
        this.a = (SwipeRefreshLayout) this.r.findViewById(R.id.swipe_layout);
        this.f = (ObservableRecyclerView) this.r.findViewById(R.id.recycler_view);
        this.x = (LinearLayout) this.r.findViewById(R.id.llCustomListViewTabs);
        this.y = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        l();
        this.j = (ProgressBar) this.r.findViewById(R.id.llParentLoading);
        this.s = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
    }

    public View b() {
        return this.r;
    }

    public ObservableRecyclerView c() {
        return this.f;
    }

    public e d() {
        return this.g;
    }

    public void e() {
        if (this.i != null && this.i.g() != null && (this.h instanceof SongsItemView)) {
            this.e.setCurrentBusObjInListView(this.i.g());
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.h = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.d, this.m});
        } catch (Exception unused) {
        }
    }

    public void b(ListingButton listingButton) {
        this.i = listingButton;
        this.k = Boolean.valueOf(false);
        if (this.i.i()) {
            this.a.setEnabled(true);
        } else {
            this.a.setEnabled(false);
        }
        if (!this.l.booleanValue()) {
            this.j.setVisibility(0);
        }
        a(listingButton);
        if (listingButton.g() == null || this.l.booleanValue()) {
            BusinessObject businessObject = null;
            if (!(this.i == null || this.i.c() == null || !this.i.c().e().booleanValue() || listingButton.g() == null)) {
                this.l.booleanValue();
                this.i.c().a(a(this.i.c().k(), 0, 20, null));
            }
            this.a.setEnabled(false);
            if (this.i != null && this.i.c() != null) {
                if (this.e.getListingComponents() != null) {
                    businessObject = this.e.getListingComponents().a();
                }
                if (this.i.c() == null || this.i.c().j() != UserRecentActivity.class) {
                    if (businessObject != null && (businessObject instanceof Playlist)) {
                        Playlist playlist = (Playlist) businessObject;
                        if (PlaylistSyncManager.getInstance().isMyPlaylist(playlist)) {
                            this.i.c().c(this.l);
                            ((BaseActivity) this.d).getMyPlaylistDetails(this.o, playlist, this.i.c());
                            return;
                        }
                    }
                    if (businessObject != null && (businessObject instanceof Artist) && businessObject.isLocalMedia()) {
                        ((BaseActivity) this.d).getDownloadedBusinessObject(this.o, businessObject.getBusinessObjId(), this.i.c());
                        return;
                    } else if (this.l.booleanValue() || !this.i.l() || businessObject == null) {
                        i.a().a(this.o, this.i.c(), Boolean.valueOf(false));
                        return;
                    } else {
                        ((BaseActivity) this.d).getDownloadedBusinessObject(this.o, businessObject.getBusinessObjId(), this.i.c());
                        return;
                    }
                }
                aq.a().a(this.i.c(), this.o);
            }
        } else if (listingButton.g() == null || listingButton.g().size() == 0) {
            a(true);
            this.k = Boolean.valueOf(true);
            Adapter adapter = this.f.getAdapter();
        } else {
            a(listingButton.g(), this.h);
        }
    }

    private String a(String str, int i, int i2, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            str = a(str);
        }
        StringBuilder stringBuilder;
        if (obj != null && (obj instanceof UserActivity)) {
            UserActivity userActivity = (UserActivity) obj;
            StringBuilder stringBuilder2;
            if (str.contains("last_seen_id")) {
                str = str.substring(0, str.lastIndexOf("&"));
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("&last_seen_id=");
                stringBuilder2.append(userActivity.getActivityId());
                return stringBuilder2.toString();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("&last_seen_id=");
            stringBuilder2.append(userActivity.getActivityId());
            return stringBuilder2.toString();
        } else if (str.contains("limit")) {
            if (str.contains("?limit")) {
                str = str.split("\\?limit")[0];
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("?limit=");
                stringBuilder.append(i);
                stringBuilder.append(",");
                stringBuilder.append(i2);
                return stringBuilder.toString();
            }
            str = str.split("&limit")[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else if (str.contains("?")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("?limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        }
    }

    private String a(String str) {
        String str2 = null;
        for (String str3 : str.split("&")) {
            String str4 = str3.split("=")[0];
            if (str4.compareToIgnoreCase(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE) == 0) {
                str2 = str3.split("=")[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&");
                stringBuilder.append(str4);
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = str.replace(stringBuilder.toString(), " ").trim();
            }
        }
        return str2 == null ? str : str2;
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public ListingButton f() {
        return this.i;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(ArrayList<?> arrayList, final BaseItemView baseItemView) {
        if (a(arrayList.get(0), baseItemView)) {
            if (this.j != null) {
                this.j.setVisibility(8);
            }
            if (this.l.booleanValue()) {
                this.a.setRefreshing(false);
                this.l = Boolean.valueOf(false);
            }
            this.j.setVisibility(8);
            this.i.a((ArrayList) arrayList);
            if (baseItemView instanceof SongsItemView) {
                this.e.setCurrentBusObjInListView(arrayList);
            }
            if (this.g == null) {
                this.E = LayoutInflater.from(this.d).inflate(R.layout.recycler_header, null);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i = displayMetrics.widthPixels;
                if (this.m instanceof SongParallexListingFragment) {
                    this.E.setLayoutParams(new LayoutParams(-1, i));
                } else {
                    this.E.setLayoutParams(new LayoutParams(-1, i + ((int) ((Activity) this.d).getResources().getDimension(R.dimen.artist_recyclerview_padding))));
                }
                this.g = new e(this.d, this.E, this.m);
            }
            if (this.i.c() != null && this.i.c().e().booleanValue()) {
                this.g.setLoadMoreListner(this);
            }
            if ((this.m instanceof ListingFragmentMaterial) && e.j != 0) {
                this.g.setAdEnabled(true);
            }
            if ((this.m instanceof SongParallexListingFragment) && !TextUtils.isEmpty(((SongParallexListingFragment) this.m).k())) {
                this.g.setAdEnabled(true);
            }
            if (this.m instanceof SongParallexListingFragment) {
                this.g.setSortItem(true);
            }
            if (arrayList.size() > 0) {
                a(false);
            }
            this.g.setParamaters(arrayList, new IAddListItemView() {
                public int getItemViewType(int i) {
                    return 1;
                }

                public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                    View view;
                    if (viewHolder instanceof ItemAdViewHolder) {
                        view = viewHolder.itemView;
                    } else if (viewHolder instanceof SpinnerHolder) {
                        if (!c.this.C) {
                            c.this.a(viewHolder);
                            c.this.C = true;
                        }
                        view = null;
                    } else if (obj instanceof PlayerTrack) {
                        view = baseItemView.getPoplatedView(viewHolder, ((PlayerTrack) obj).b(), viewGroup);
                    } else {
                        view = baseItemView.getPoplatedView(viewHolder, (BusinessObject) obj, viewGroup);
                    }
                    if (c.this.D && view != null && !al.a && ((baseItemView instanceof DownloadSongListingView) || (baseItemView instanceof DownloadSongsItemView))) {
                        view.setOnLongClickListener(new OnLongClickListener() {
                            public boolean onLongClick(View view) {
                                c.this.g.notifyDataSetChanged();
                                ((SongParallexListingFragment) c.this.m).a(view, c.this.g.getCount());
                                return true;
                            }
                        });
                    }
                    return view;
                }

                public void showHideEmtpyView(boolean z) {
                    c.this.a(z);
                }

                public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                    View createViewHolder = baseItemView.createViewHolder(viewGroup, i);
                    if (i == 321) {
                        return new SpinnerHolder(LayoutInflater.from(c.this.d).inflate(R.layout.view_album_details_filter, viewGroup, false));
                    }
                    if (baseItemView instanceof DownloadSongListingView) {
                        return new DownloadSongListingHolder(createViewHolder, false);
                    }
                    if (baseItemView instanceof DownloadAlbumItemView) {
                        return new DownloadAlbumItemHolder(createViewHolder);
                    }
                    if (baseItemView instanceof DownloadPlaylistItemView) {
                        return new DownloadPlayListItemHolder(createViewHolder);
                    }
                    if (baseItemView instanceof RadioItemView) {
                        return new RadioItemHolder(createViewHolder);
                    }
                    if (baseItemView instanceof ArtistItemView) {
                        return new ArtistItemHolder(createViewHolder);
                    }
                    if (baseItemView instanceof FavoriteOccasionItemView) {
                        FavoriteOccasionItemHolder favoriteOccasionItemHolder = new FavoriteOccasionItemHolder(createViewHolder);
                    } else if (baseItemView instanceof DownloadSongsItemView) {
                        return new AlbumDetailItemHolder(createViewHolder);
                    } else {
                        if (baseItemView instanceof RadioButtonSongView) {
                            return new RadioButtonSongHolder(createViewHolder);
                        }
                        if (baseItemView instanceof NotificationItemView) {
                            return new NotificationItemHolder(createViewHolder);
                        }
                        if (baseItemView instanceof NewGenericItemView) {
                            return new NewGenericItemHolder(createViewHolder);
                        }
                    }
                    return null;
                }
            });
            this.f.setLayoutManager(new LinearLayoutManager(this.d));
            this.f.setItemAnimator(new DefaultItemAnimator());
            this.f.setAdapter(this.g);
        }
    }

    private boolean a(Object obj, BaseItemView baseItemView) {
        if ((obj instanceof Track) && !(baseItemView instanceof SongsItemView)) {
            boolean z = baseItemView instanceof RadioButtonSongView;
            if (!(z || (baseItemView instanceof EditPlaylistSelectSongView) || z)) {
                return false;
            }
        }
        if ((obj instanceof Album) && !(baseItemView instanceof AlbumItemView)) {
            return false;
        }
        if ((obj instanceof Playlist) && !(baseItemView instanceof PlaylistItemView) && !(baseItemView instanceof RadioButtonPlaylistView)) {
            return false;
        }
        if ((obj instanceof Artist) && !(baseItemView instanceof ArtistItemView)) {
            return false;
        }
        if ((obj instanceof Radio) && !(baseItemView instanceof RadioItemView)) {
            return false;
        }
        if (!(obj instanceof FavoriteOccasion) || (baseItemView instanceof FavoriteOccasionItemView)) {
            return true;
        }
        return false;
    }

    public void g() {
        if (!this.v) {
            this.v = true;
            this.s.setVisibility(0);
        }
    }

    public void h() {
        if (this.v) {
            this.v = false;
            this.s.setVisibility(8);
        }
    }

    /* JADX WARNING: Missing block: B:13:0x005c, code skipped:
            return;
     */
    public void loadMoreData(int r4, java.lang.Object r5) {
        /*
        r3 = this;
        r0 = r3.e;
        r0 = r0.isAppInOfflineMode();
        if (r0 != 0) goto L_0x005c;
    L_0x0008:
        r0 = r3.d;
        r0 = com.utilities.Util.j(r0);
        if (r0 != 0) goto L_0x0011;
    L_0x0010:
        goto L_0x005c;
    L_0x0011:
        r0 = r3.w;
        if (r0 != 0) goto L_0x005b;
    L_0x0015:
        r0 = r3.k;
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x005b;
    L_0x001d:
        r0 = 1;
        r3.w = r0;
        r0 = r3.x;
        if (r0 == 0) goto L_0x0030;
    L_0x0024:
        r0 = r3.x;
        r0.removeAllViews();
        r0 = r3.x;
        r1 = r3.y;
        r0.addView(r1);
    L_0x0030:
        r0 = r3.i;
        r0 = r0.c();
        r1 = r3.i;
        r1 = r1.c();
        r1 = r1.k();
        r2 = 20;
        r4 = r3.a(r1, r4, r2, r5);
        r0.a(r4);
        r3.g();
        r4 = com.i.i.a();
        r5 = r3.n;
        r0 = r3.i;
        r0 = r0.c();
        r4.a(r5, r0);
    L_0x005b:
        return;
    L_0x005c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.collapsible_header.c.loadMoreData(int, java.lang.Object):void");
    }

    private void l() {
        if (this.d.getResources().getString(R.string.NO_DATA).trim().length() != 0) {
            this.a.setOnRefreshListener(new OnRefreshListener() {
                public void onRefresh() {
                    c.this.z = Calendar.getInstance().getTimeInMillis();
                    c.this.i();
                }
            });
        }
    }

    public void i() {
        if (this.i != null && this.i.i()) {
            if (this.u != null) {
                this.u.onAdRefresh();
            }
            BaseGaanaFragment currentFragment = ((GaanaActivity) this.d).getCurrentFragment();
            if (currentFragment instanceof ArtistDetailsMaterialListing) {
                ((ArtistDetailsMaterialListing) currentFragment).h();
            }
            if (this.x != null) {
                this.x.removeAllViews();
            }
            this.l = Boolean.valueOf(true);
            if (!this.b) {
                this.i.c().c(Boolean.valueOf(true));
            }
            b(this.i);
            if (this.g != null) {
                this.g.onRefresh(true);
            }
        }
    }

    private void b(BusinessObject businessObject) {
        new LinearLayout(this.d).setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.s = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        this.s.setVisibility(0);
        this.s.setBackgroundColor(this.d.getResources().getColor(R.color.gray));
    }

    public void a(BusinessObject businessObject) {
        this.t = businessObject;
    }

    public BusinessObject j() {
        return this.t;
    }

    public void a(boolean z) {
        if (!this.D) {
            TextView textView = (TextView) this.r.findViewById(R.id.emptyMsgView);
            if (this.j != null) {
                this.j.setVisibility(8);
            }
            if (this.m instanceof ItemListingFragment) {
                textView.setVisibility(8);
                if (z) {
                    m();
                }
            } else if (z) {
                if (this.m instanceof AddToPlaylistFragment) {
                    textView.setText(R.string.no_existing_playlist_create_playlist_first);
                }
                if (this.g == null) {
                    textView.setVisibility(8);
                    m();
                } else if (textView.getVisibility() != 0) {
                    if (!(this.f == null || this.g == null)) {
                        this.g.setAdapterArrayList(new ArrayList());
                        this.g.notifyDataSetChanged();
                    }
                    textView.setVisibility(0);
                }
            } else {
                textView.setVisibility(8);
            }
        }
    }

    private void m() {
        final BaseItemView baseItemView = new BaseItemView(this.d, this.m);
        ArrayList arrayList = new ArrayList();
        final UserMessage userMessage = new UserMessage();
        userMessage.setEmptyMsg(this.d.getResources().getString(R.string.NO_DATA));
        UserMessage userMessage2 = new UserMessage();
        userMessage2.setEmptyMsg("dummy_first");
        UserMessage userMessage3 = new UserMessage();
        userMessage3.setEmptyMsg("dummy_last");
        arrayList.add(userMessage2);
        arrayList.add(userMessage);
        arrayList.add(userMessage3);
        final View inflate = LayoutInflater.from(this.d).inflate(R.layout.recycler_header, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        if (this.m instanceof SongParallexListingFragment) {
            inflate.setLayoutParams(new LayoutParams(-1, i));
        } else {
            inflate.setLayoutParams(new LayoutParams(-1, i + ((int) ((Activity) this.d).getResources().getDimension(R.dimen.artist_recyclerview_padding))));
        }
        final View view = new View(this.d);
        if (Constants.l) {
            view.setBackgroundColor(this.d.getResources().getColor(R.color.gaana_grey_white));
        } else {
            view.setBackgroundColor(this.d.getResources().getColor(R.color.gaana_grey));
        }
        view.setLayoutParams(new RecyclerView.LayoutParams(-1, (displayMetrics.heightPixels - ((int) ((Activity) this.d).getResources().getDimension(R.dimen.artist_dummy_tab_height))) - n()));
        if (this.l.booleanValue()) {
            this.a.setRefreshing(false);
            this.l = Boolean.valueOf(false);
        }
        this.j.setVisibility(8);
        if (this.g != null) {
            this.g.clearAdapter();
            this.g = null;
        }
        ListAdapterSectionIndexer listAdapterSectionIndexer = new ListAdapterSectionIndexer(this.d, this.m);
        listAdapterSectionIndexer.setParamaters(arrayList, new IAddListItemView() {
            public void showHideEmtpyView(boolean z) {
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                UserMessage userMessage = (UserMessage) obj;
                if (userMessage.getEmptyMsg().equalsIgnoreCase("dummy_first")) {
                    return inflate;
                }
                if (userMessage.getEmptyMsg().equalsIgnoreCase("dummy_last")) {
                    return view;
                }
                return baseItemView.getEmptyMsgView(userMessage, null);
            }

            public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                if (i == c.this.F) {
                    return new a(inflate);
                }
                if (i == c.this.G) {
                    return new a(view);
                }
                return new EmptyMessageHolder(baseItemView.getEmptyMsgView(userMessage, viewGroup));
            }

            public int getItemViewType(int i) {
                if (i == 0) {
                    return c.this.F;
                }
                return i == 2 ? c.this.G : 0;
            }
        });
        this.f.setLayoutManager(new LinearLayoutManager(this.d));
        this.f.setItemAnimator(new DefaultItemAnimator());
        this.f.setAdapter(listAdapterSectionIndexer);
    }

    private int n() {
        return this.d.getResources().getDimensionPixelSize(R.dimen.player_bottom_control_height);
    }

    private void c(BusinessObject businessObject) {
        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            this.a.setRefreshing(false);
            a(true);
        } else {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            if (this.h instanceof SongsItemView) {
                this.e.setCurrentBusObjInListView(arrListBusinessObj);
            }
            if (this.i.g() == null || this.i.g().size() == 0) {
                this.i.a(arrListBusinessObj);
                a(arrListBusinessObj, this.h);
            } else {
                this.i.a(arrListBusinessObj);
                this.g.setAdapterArrayList(arrListBusinessObj);
            }
        }
        if (this.c != null) {
            this.c.a(businessObject, this.i.c().i());
        }
    }

    public void k() {
        BusinessObject a = this.e.getListingComponents() != null ? this.e.getListingComponents().a() : null;
        if (a != null && (a instanceof Artist) && a.isLocalMedia()) {
            ((BaseActivity) this.d).getDownloadedBusinessObject(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    c.this.c(businessObject);
                }
            }, a.getBusinessObjId(), this.i.c());
        } else {
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    c.this.c(businessObject);
                }
            }, this.i.c(), Boolean.valueOf(false));
        }
    }

    private void a(ViewHolder viewHolder) {
        SpinnerHolder spinnerHolder = (SpinnerHolder) viewHolder;
        if (this.I == null || this.I.size() < 2) {
            spinnerHolder.setVisibility(false);
        } else {
            spinnerHolder.setVisibility(true);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.d, R.layout.view_filter_spinner_item, this.d.getResources().getStringArray(R.array.filter_type));
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_filter);
        this.B = spinnerHolder.mSpinner;
        spinnerHolder.mSpinner.setAdapter(arrayAdapter);
        spinnerHolder.mSpinner.setSelection(0);
        spinnerHolder.mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (c.this.H != null && c.this.H.size() >= 2) {
                    switch (i) {
                        case 0:
                            c.this.H.clear();
                            c.this.H.addAll(c.this.I);
                            break;
                        case 1:
                            Collections.sort(c.this.H, new Comparator<BusinessObject>() {
                                /* renamed from: a */
                                public int compare(BusinessObject businessObject, BusinessObject businessObject2) {
                                    return ((businessObject instanceof Track) && (businessObject2 instanceof Track)) ? ((Track) businessObject).getName().toLowerCase().compareTo(((Track) businessObject2).getName().toLowerCase()) : 0;
                                }
                            });
                            break;
                        case 2:
                            Collections.sort(c.this.H, new Comparator<BusinessObject>() {
                                /* renamed from: a */
                                public int compare(BusinessObject businessObject, BusinessObject businessObject2) {
                                    if (!(businessObject instanceof Track) || !(businessObject2 instanceof Track)) {
                                        return 0;
                                    }
                                    Track track = (Track) businessObject;
                                    if (TextUtils.isEmpty(track.getArtistNames()) && TextUtils.isEmpty(((Track) businessObject2).getArtistNames())) {
                                        return 0;
                                    }
                                    if (TextUtils.isEmpty(track.getArtistNames())) {
                                        return 1;
                                    }
                                    Track track2 = (Track) businessObject2;
                                    if (TextUtils.isEmpty(track2.getArtistNames())) {
                                        return -1;
                                    }
                                    return track.getArtistNames().toLowerCase().compareTo(track2.getArtistNames().toLowerCase());
                                }
                            });
                            break;
                        case 3:
                            Collections.sort(c.this.H, new Comparator<BusinessObject>() {
                                /* renamed from: a */
                                public int compare(BusinessObject businessObject, BusinessObject businessObject2) {
                                    if (!(businessObject instanceof Track) || !(businessObject2 instanceof Track)) {
                                        return 0;
                                    }
                                    Track track = (Track) businessObject2;
                                    Track track2 = (Track) businessObject;
                                    if (track.getPopularity() > track2.getPopularity()) {
                                        return 1;
                                    }
                                    if (track.getPopularity() < track2.getPopularity()) {
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                            break;
                    }
                    c.this.g.setAdapterArrayList(c.this.H);
                }
            }
        });
    }
}

package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.b.i;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.f;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.qr_scanner.ScannerFragment;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemNormalViewHolder;
import com.gaana.view.item.JukeScrollView;
import com.gaana.view.item.JukeScrollView.HorizontalViewHolder;
import com.gaana.view.item.PlaylistItemView;
import com.gaana.view.item.PopupShareitemView;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.library.controls.CrossFadeImageView;
import com.managers.ColombiaAdViewManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.e;
import com.managers.u;
import com.services.d;
import com.services.l.s;
import com.utilities.Util;
import com.utilities.h;
import java.util.ArrayList;
import java.util.Map;

public class PartyFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener {
    View a;
    private a b;
    private Toolbar c;
    private Context d;
    private ObservableRecyclerView e;
    private int f;
    private View g;
    private float h;
    private View i;
    private SwipeRefreshLayout j;
    private JukeScrollView k;
    private JukeScrollView l;
    private JukeScrollView m;
    private boolean n;
    private ArrayList<String> o = new ArrayList();
    private CrossFadeImageView p;
    private boolean q = false;
    private boolean r = true;
    private ILifeCycleAwareCustomView s;

    private class a extends Adapter implements OnClickListener {
        public int getItemCount() {
            return 7;
        }

        public int getItemViewType(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                default:
                    return 6;
            }
        }

        private a() {
        }

        /* synthetic */ a(PartyFragment partyFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view;
            switch (i) {
                case 0:
                    view = new View(PartyFragment.this.d);
                    view.setLayoutParams(new LayoutParams(-1, (int) PartyFragment.this.d.getResources().getDimension(R.dimen.juke_party_header_height)));
                    return new ItemAdViewHolder(view);
                case 1:
                    if (PartyFragment.this.k == null) {
                        PartyFragment.this.k = new JukeScrollView(PartyFragment.this.d, PartyFragment.this, PartyFragment.this.d());
                    }
                    return (HorizontalViewHolder) PartyFragment.this.k.onCreateViewHolder(viewGroup, i);
                case 2:
                    return new ItemAdViewHolder(LayoutInflater.from(PartyFragment.this.d).inflate(R.layout.view_txt_header, viewGroup, false));
                case 3:
                    if (PartyFragment.this.l == null) {
                        PartyFragment.this.l = new JukeScrollView(PartyFragment.this.d, PartyFragment.this, PartyFragment.this.e());
                    }
                    return (HorizontalViewHolder) PartyFragment.this.l.onCreateViewHolder(viewGroup, i);
                case 4:
                    if (PartyFragment.this.m == null) {
                        PartyFragment.this.m = new JukeScrollView(PartyFragment.this.d, PartyFragment.this, PartyFragment.this.f());
                    }
                    return (HorizontalViewHolder) PartyFragment.this.m.onCreateViewHolder(viewGroup, i);
                case 5:
                    NextGenAutoSuggestAdapter.HorizontalViewHolder horizontalViewHolder = new NextGenAutoSuggestAdapter.HorizontalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_listview_party, viewGroup, false));
                    horizontalViewHolder.textLabel.setTypeface(i.a(PartyFragment.this.d.getAssets(), "fonts/Roboto-Medium.ttf"));
                    horizontalViewHolder.textLabel.setTextSize(2, 18.0f);
                    TypedValue typedValue = new TypedValue();
                    PartyFragment.this.d.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
                    horizontalViewHolder.viewAll.setTextColor(typedValue.data);
                    horizontalViewHolder.viewAll.setTypeface(i.a(PartyFragment.this.d.getAssets(), "fonts/Roboto-Regular.ttf"));
                    horizontalViewHolder.viewAll.setText(R.string.opt_see_all_camel);
                    horizontalViewHolder.textLabel.setText(PartyFragment.this.d.getResources().getString(R.string.party_myplaylist_idea));
                    horizontalViewHolder.viewAll.setOnClickListener(this);
                    return horizontalViewHolder;
                case 6:
                    view = LayoutInflater.from(PartyFragment.this.d).inflate(R.layout.view_create_juke_playlist, viewGroup, false);
                    if (!Constants.l) {
                        ((TextView) view.findViewById(R.id.text)).setTextColor(-1);
                    }
                    view.findViewById(R.id.btn_create_playlist).setOnClickListener(this);
                    return new ItemAdViewHolder(view);
                default:
                    return null;
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            switch (viewHolder.getItemViewType()) {
                case 1:
                    PartyFragment.this.k.getPopulatedView(0, viewHolder, null);
                    return;
                case 2:
                    ((TextView) viewHolder.itemView.findViewById(R.id.horizontal_list_view_header)).setText(PartyFragment.this.a(PartyFragment.this.d.getResources().getString(R.string.new_start_with_ideas), PartyFragment.this.d.getResources().getString(R.string.new_start_with_ideas_bold)));
                    return;
                case 3:
                    PartyFragment.this.l.getPopulatedView(0, viewHolder, null);
                    return;
                case 4:
                    PartyFragment.this.m.getPopulatedView(0, viewHolder, null);
                    return;
                case 5:
                    PartyFragment.this.a(viewHolder);
                    return;
                default:
                    return;
            }
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.btn_create_playlist) {
                if (id == R.id.f63similar.header.view_all) {
                    ((GaanaActivity) PartyFragment.this.d).displayFragment(JukeSeeAllFragment.newInstance("", PartyFragment.this.d.getResources().getString(R.string.party_myplaylist_idea), "", true));
                }
            } else if (Util.j(PartyFragment.this.d)) {
                JukePlaylist jukePlaylist = new JukePlaylist();
                jukePlaylist.setName(PartyFragment.this.d.getResources().getString(R.string.opt_my_party));
                u.a().a("PartyHub", "Create_Suggestion_Tap", "New");
                ((GaanaActivity) PartyFragment.this.d).displayFragment(JukePartyFragment.newInstance(jukePlaylist, 0, "", false));
            } else {
                ap.a().f(PartyFragment.this.d);
            }
        }
    }

    public class b extends ItemDecoration {
        private int b;

        public b(int i) {
            this.b = i;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            if (recyclerView.getChildLayoutPosition(view) == state.getItemCount() - 1) {
                rect.bottom = this.b;
            } else {
                rect.bottom = 0;
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.d = getActivity();
        if (this.a == null) {
            this.a = setContentView(R.layout.fragment_party, viewGroup);
            b();
            c();
            this.q = true;
        } else {
            this.q = false;
        }
        if (this.d != null && ((GaanaActivity) this.d).getRefreshData()) {
            ((GaanaActivity) this.d).setRefreshData(false);
            onRefresh();
        }
        ((GaanaActivity) this.d).hideThemeBackground(false);
        setGAScreenName("PartyHub_Home", "PartyHub_Home");
        a(false);
        g();
        return this.a;
    }

    private void b() {
        this.c = (Toolbar) this.a.findViewById(R.id.fragment_party_toolbar);
        this.c.findViewById(R.id.share_icon).setOnClickListener(this);
        this.i = this.a.findViewById(R.id.fragment_party_header_overlay);
        this.p = (CrossFadeImageView) this.a.findViewById(R.id.fragment_party_header);
        this.g = this.a.findViewById(R.id.toolbar_bg);
        this.a.findViewById(R.id.fragment_party_back).setOnClickListener(this);
        this.c.setContentInsetsAbsolute(0, 0);
        this.a.findViewById(R.id.fragment_party_qr).setOnClickListener(this);
        this.j = (SwipeRefreshLayout) this.a.findViewById(R.id.swipe_refresh_layout);
        this.j.setOnRefreshListener(this);
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void c() {
        this.r = d.a().b("create_party", false, false);
        this.f = (int) getResources().getDimension(R.dimen.juke_party_header_height);
        this.h = this.d.getResources().getDimension(R.dimen.actionbar_height);
        this.b = new a(this, null);
        this.e = (ObservableRecyclerView) this.a.findViewById(R.id.party_main_view);
        this.e.setLayoutManager(new LinearLayoutManager(this.d));
        if (!this.r) {
            this.e.addOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    if (i == 0) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) PartyFragment.this.e.getLayoutManager();
                        i = linearLayoutManager.findFirstVisibleItemPosition();
                        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                        ViewHolder findViewHolderForAdapterPosition = PartyFragment.this.e.findViewHolderForAdapterPosition(3);
                        View view = findViewHolderForAdapterPosition != null ? findViewHolderForAdapterPosition.itemView : null;
                        if (view != null && i < 3 && 3 < findLastVisibleItemPosition) {
                            float y = (view.getY() + ((float) view.getHeight())) - ((float) PartyFragment.this.getResources().getDimensionPixelSize(R.dimen.dp75));
                            d.a().a("create_party", true, false);
                            PartyFragment.this.r = true;
                            PartyFragment.this.e.removeOnScrollListener(this);
                            Intent intent = new Intent(PartyFragment.this.d, VideoCoachmarkActivity.class);
                            intent.putExtra("COACHMARK_VALUE", "create_party");
                            intent.putExtra("extra_y", y);
                            PartyFragment.this.startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
                            PartyFragment.this.getActivity().overridePendingTransition(17432576, 17432577);
                        }
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                }
            });
        }
        this.e.addScrollViewCallbacks(new com.collapsible_header.d() {
            public void onDownMotionEvent() {
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
            }

            public void onScrollChanged(int i, boolean z, boolean z2) {
                float c = ((float) i) / (((float) PartyFragment.this.f) - PartyFragment.this.h);
                com.collapsible_header.i.a(PartyFragment.this.g, f.a(c, 0.0f, 1.0f));
                com.collapsible_header.i.a(PartyFragment.this.i, f.a(c, 0.0f, 1.0f));
            }
        });
        this.e.setAdapter(this.b);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fragment_party_back) {
            ((GaanaActivity) this.d).onBackPressed();
        } else if (id != R.id.fragment_party_qr) {
            if (id == R.id.share_icon) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.d.getResources().getString(R.string.party_play_friends));
                stringBuilder.append(" ");
                stringBuilder.append("https://gaana.com/");
                stringBuilder.append("view/jukepage");
                new PopupShareitemView(this.d, stringBuilder.toString()).shareOnOther();
            }
        } else if (!this.d.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            aj.a().a(this.d, this.d.getResources().getString(R.string.error_msg_no_camera));
        } else if (!h.d((Activity) this.d)) {
        } else {
            if (Util.j(this.d)) {
                ((GaanaActivity) this.d).displayFragment(new ScannerFragment());
            } else {
                ap.a().f(this.d);
            }
        }
    }

    public void onRefresh() {
        this.n = false;
        a(true);
        if (this.k != null) {
            this.k.setIsToBeRefreshed(true);
        }
        if (this.l != null) {
            this.l.setIsToBeRefreshed(true);
        }
        if (this.m != null) {
            this.m.setIsToBeRefreshed(true);
        }
        if (this.j != null) {
            this.j.setRefreshing(false);
        }
    }

    private void a(ViewHolder viewHolder) {
        if (!this.n) {
            this.n = true;
            final PlaylistItemView playlistItemView = new PlaylistItemView(getContext(), this);
            if (!(this.d == null || this.d.getResources() == null)) {
                playlistItemView.setSourceName(this.d.getResources().getString(R.string.party_myplaylist_idea));
            }
            final int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
            final int dimensionPixelSize2 = this.d.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin);
            final ViewHolder viewHolder2 = viewHolder;
            PlaylistSyncManager.getInstance().getMyPlaylistAsync(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().isEmpty()) {
                        viewHolder2.itemView.getLayoutParams().height = 0;
                        viewHolder2.itemView.requestLayout();
                        return;
                    }
                    final ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                    NextGenAutoSuggestAdapter.HorizontalViewHolder horizontalViewHolder = (NextGenAutoSuggestAdapter.HorizontalViewHolder) viewHolder2;
                    com.views.HorizontalRecyclerView.b a = horizontalViewHolder.recyclerView.a(PartyFragment.this.d, arrListBusinessObj.size() + 1);
                    a.a(new com.views.HorizontalRecyclerView.a() {
                        public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                            if (viewHolder.getItemViewType() == 0) {
                                playlistItemView.getPopulatedView(viewHolder, (Playlist) arrListBusinessObj.get(i3));
                            } else {
                                LayoutParams layoutParams = (LayoutParams) viewHolder.itemView.getLayoutParams();
                                layoutParams.height = PartyFragment.this.d.getResources().getDimensionPixelSize(R.dimen.dp140);
                                layoutParams.width = PartyFragment.this.d.getResources().getDimensionPixelSize(R.dimen.dp140);
                                layoutParams.leftMargin = dimensionPixelSize;
                                layoutParams.rightMargin = dimensionPixelSize2;
                            }
                            if (i3 == arrListBusinessObj.size()) {
                                viewHolder.itemView.setPadding(dimensionPixelSize, 0, dimensionPixelSize2, 0);
                            } else {
                                viewHolder.itemView.setPadding(dimensionPixelSize, 0, 0, 0);
                            }
                            return viewHolder.itemView;
                        }

                        public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                            if (i2 == 0) {
                                return new ItemNormalViewHolder(LayoutInflater.from(PartyFragment.this.d).inflate(R.layout.item_playlist_grid_juke_140x140, viewGroup, false));
                            }
                            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_create_party_playlist_grid, viewGroup, false);
                            inflate.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    if (Util.j(PartyFragment.this.d)) {
                                        JukePlaylist jukePlaylist = new JukePlaylist();
                                        jukePlaylist.setName(PartyFragment.this.d.getResources().getString(R.string.opt_my_party));
                                        u.a().a("PartyHub", "Create_Suggestion_Tap", "New");
                                        ((GaanaActivity) PartyFragment.this.d).displayFragment(JukePartyFragment.newInstance(jukePlaylist, 0, "", false));
                                        return;
                                    }
                                    ap.a().f(PartyFragment.this.d);
                                }
                            });
                            return new ItemAdViewHolder(inflate);
                        }

                        public int getItemViewType(int i) {
                            return i < arrListBusinessObj.size() ? 0 : 1;
                        }
                    });
                    horizontalViewHolder.recyclerView.setAdapter(a);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    PartyFragment.this.n = false;
                    viewHolder2.itemView.getLayoutParams().height = 0;
                    viewHolder2.itemView.requestLayout();
                }
            }, true);
        }
    }

    private com.dynamicview.f.a d() {
        com.dynamicview.f.a aVar = new com.dynamicview.f.a(getString(R.string.my_party_playlist), "https://apiv2.gaana.com/collab/playlist/user?", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/collab/playlist/user?", "my party playlists", "", "", "240");
        aVar.c(true);
        aVar.h(this.d.getResources().getString(R.string.my_party_playlist));
        aVar.a(VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal());
        aVar.a(true);
        return aVar;
    }

    private com.dynamicview.f.a e() {
        com.dynamicview.f.a aVar = new com.dynamicview.f.a(getString(R.string.house_party), "https://apiv2.gaana.com/collab/playlist/mood/1", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/collab/playlist/mood/1", "house party playlists", "", "", "240");
        aVar.h(this.d.getResources().getString(R.string.house_party));
        aVar.a(VIEW_SIZE.SCROLL_GENERIC.getNumVal());
        aVar.a(true);
        return aVar;
    }

    private com.dynamicview.f.a f() {
        com.dynamicview.f.a aVar = new com.dynamicview.f.a(getString(R.string.road_trip), "https://apiv2.gaana.com/collab/playlist/mood/2", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/collab/playlist/mood/2", "road trip playlists", "", "", "240");
        aVar.a(VIEW_SIZE.SCROLL_GENERIC.getNumVal());
        aVar.h(this.d.getResources().getString(R.string.road_trip));
        aVar.a(true);
        return aVar;
    }

    public void a() {
        if (h.d(this.d)) {
            ((GaanaActivity) this.d).displayFragment(new ScannerFragment());
        }
    }

    private SpannableStringBuilder a(String str, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(str2);
        spannableStringBuilder.setSpan(new StyleSpan(1), length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private void a(boolean z) {
        com.i.b bVar = new com.i.b("https://apiv2.gaana.com/collab/playlist/design", String.class, new com.i.e.a() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, boolean z) {
                Map map = (Map) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson((String) obj, new TypeToken<Map<String, String>>() {
                }.getType());
                PartyFragment.this.o.clear();
                if (map != null) {
                    String str = (String) map.get("bg_img");
                    if (!TextUtils.isEmpty(str)) {
                        PartyFragment.this.p.bindImage(str);
                    }
                }
            }
        });
        bVar.a(true);
        bVar.a(Boolean.valueOf(z));
        bVar.d((int) PsExtractor.VIDEO_STREAM_MASK);
        com.i.i.a().a(bVar);
    }

    private void g() {
        if (ap.a().b(this.d)) {
            LinearLayout linearLayout = (LinearLayout) this.a.findViewById(R.id.bottom_ad_banner);
            ColombiaAdViewManager.a().e();
            if (e.X == 0) {
                this.s = ColombiaAdViewManager.a().a(this.d, linearLayout, e.A, "PARTY_BOTTOM_BANNER", new com.services.l.a() {
                    public void onAdBottomBannerGone() {
                    }

                    public void onAdBottomBannerLoaded() {
                        if (PartyFragment.this.q && PartyFragment.this.d != null && PartyFragment.this.d.getResources() != null && PartyFragment.this.e != null) {
                            int dimensionPixelSize = PartyFragment.this.d.getResources().getDimensionPixelSize(R.dimen.dp50);
                            if (PartyFragment.this.e.getItemDecorationCount() == 0) {
                                PartyFragment.this.e.addItemDecoration(new b(dimensionPixelSize));
                            }
                        }
                    }
                }, new AdsUJData[0]);
                if (this.s != null) {
                    getLifecycle().a(this.s);
                }
            }
        }
    }

    public void onDestroyView() {
        if (this.s != null) {
            this.s.destroy();
            getLifecycle().b(this.s);
        }
        super.onDestroyView();
    }
}

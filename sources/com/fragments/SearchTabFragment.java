package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.SearchActionBar;
import com.constants.Constants;
import com.constants.Constants.VIEW_SUBTYPE;
import com.dynamicview.DynamicHomeScrollerView;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.adapter.SearchFeedAdapter;
import com.gaana.adapter.TrendingSearchAdapter;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.RecentSearches;
import com.gaana.models.SearchTags.Tag;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.SearchItemView;
import com.gaana.view.item.SearchItemView.SearchCategory;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.ACTION_DETAILS;
import com.managers.GaanaSearchManager.ACTION_TYPE;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.e;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingParams;
import com.services.c;
import com.services.l.af;
import com.utilities.Util;
import com.utilities.d;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchTabFragment extends BaseGaanaFragment implements OnClickListener, com.actionbar.SearchActionBar.a, com.services.l.a {
    private boolean A;
    private boolean B = false;
    private View C = null;
    private ILifeCycleAwareCustomView D;
    private OnScrollListener E = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 1) {
                Util.a(SearchTabFragment.this.mContext, SearchTabFragment.this.b);
                SearchTabFragment.this.c.requestFocus();
            }
            ((GaanaActivity) SearchTabFragment.this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }
    };
    private OnScrollListener F = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (SearchTabFragment.this.x != null && recyclerView.canScrollVertically(1) && findFirstVisibleItemPosition == 0 && SearchTabFragment.this.y > -15 && SearchTabFragment.this.z && i == 0) {
                SearchTabFragment.this.d().setVisibility(0);
                SearchTabFragment.this.x.d();
                SearchTabFragment.this.z = false;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            View childAt = SearchTabFragment.this.m.getChildAt(0);
            if (childAt != null) {
                SearchTabFragment.this.y = childAt.getTop();
                if (SearchTabFragment.this.x != null && SearchTabFragment.this.y != 0) {
                    SearchTabFragment.this.x.c();
                    SearchTabFragment.this.z = true;
                }
            }
        }
    };
    public ArrayList<AutoComplete> a;
    private View b;
    private RecyclerView c;
    private TypedValue d = new TypedValue();
    private TextView e;
    private String f;
    private boolean g;
    private ProgressBar h;
    private View i;
    private float j = 0.0f;
    private boolean k = false;
    private NextGenAutoSuggestAdapter l;
    private RecyclerView m;
    private ArrayList<Tag> n = new ArrayList();
    private DynamicHomeScrollerView o;
    private SearchFeedAdapter p = null;
    private SearchActionBar q = null;
    private boolean r = true;
    private TrendingSearchAdapter s;
    private ArrayList<Item> t = null;
    private boolean u = true;
    private SwipeRefreshLayout v;
    private TextView w;
    private b x;
    private int y;
    private boolean z;

    public interface b {
        void c();

        void d();
    }

    public class a extends Adapter<ViewHolder> {
        private final LayoutInflater b;
        private BaseItemView c;

        public int getItemCount() {
            return 1;
        }

        public a() {
            this.b = LayoutInflater.from(SearchTabFragment.this.mContext);
            a();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (SearchTabFragment.this.o == null) {
                SearchTabFragment.this.o = new DynamicHomeScrollerView(SearchTabFragment.this.mContext, SearchTabFragment.this);
            }
            SearchTabFragment.this.o.setDynamicData(SearchTabFragment.this.q());
            SearchTabFragment.this.o.a(false);
            com.dynamicview.DynamicHomeScrollerView.a aVar = (com.dynamicview.DynamicHomeScrollerView.a) SearchTabFragment.this.o.onCreateViewHolder(viewGroup, i);
            aVar.c.setVisibility(8);
            return aVar;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            SearchTabFragment.this.o.getPopulatedView(0, viewHolder, null);
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            try {
                this.c = (BaseItemView) Class.forName(SearchItemView.class.getName()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{SearchTabFragment.this.mContext, null});
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void j() {
    }

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().getTheme().resolveAttribute(R.attr.search_item_text_color, this.d, true);
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = setContentView(R.layout.search_tab_list, viewGroup);
            n();
        }
        a(this.b);
        h();
        if (((GaanaActivity) this.mContext).getRefreshData()) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            i();
        }
        m();
        final Window window = ((GaanaActivity) this.mContext).getWindow();
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (SearchTabFragment.this.b != null) {
                    SearchTabFragment.this.b.getRootView().getHeight();
                    Rect rect = new Rect();
                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                    int i = rect.bottom;
                }
            }
        });
        setShouldShowKeyboard(false);
        if (getArguments() != null) {
            this.hideBottomBar = getArguments().getBoolean("should_hide_bottom_bar", false);
        }
        return this.b;
    }

    private void a(ArrayList<?> arrayList) {
        this.t = arrayList;
        this.s = new TrendingSearchAdapter(this.mContext, this, arrayList);
        this.c.setAdapter(this.s);
        this.c.setVisibility(0);
        this.m.setVisibility(8);
        this.i.setVisibility(8);
        this.u = false;
        this.v.setEnabled(false);
    }

    public boolean b() {
        return this.u;
    }

    private void l() {
        com.dynamicview.f.a q = q();
        String l = q.l();
        if (!TextUtils.isEmpty(q.y()) && q.y().equalsIgnoreCase("X5X")) {
            int i = 0;
            StringBuilder stringBuilder;
            if (l.contains("?")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(l);
                stringBuilder.append("&trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i = 1;
                }
                stringBuilder.append(i);
                l = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(l);
                stringBuilder.append("?trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i = 1;
                }
                stringBuilder.append(i);
                l = stringBuilder.toString();
            }
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.a(l);
        uRLManager.b(Boolean.valueOf(true));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    SearchTabFragment.this.a(((BusinessObject) obj).getArrListBusinessObj());
                }
            }
        }, uRLManager);
    }

    public void c() {
        l();
    }

    private void m() {
        GaanaApplication.getInstance().setGADParameter("search");
        if (e.X == 0) {
            this.D = ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) this.b.findViewById(R.id.searchAdSlot), e.A, Constants.dN, (com.services.l.a) this, new AdsUJData[0]);
            if (this.D != null) {
                getLifecycle().a(this.D);
            }
        }
    }

    public void onAdBottomBannerLoaded() {
        this.B = true;
        if (this.C != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.C.setVisibility(0);
            this.C.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("Gaana Plus", "remove_adhook", "SearchPage");
                    c.a(SearchTabFragment.this.mContext).a(SearchTabFragment.this.mContext, SearchTabFragment.this.removeAdDeeplink, SearchTabFragment.this.mAppState);
                }
            });
        }
    }

    public void onAdBottomBannerGone() {
        if (this.C != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.C.setVisibility(8);
            this.C.setOnClickListener(null);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.q != null) {
            if (this.r) {
                this.q.setSearchInnerActionBarVisibility(true);
            } else {
                this.q.setSearchInnerActionBarVisibility(false);
            }
        }
        if (this.p != null) {
            this.p.notifyDataSetChanged();
        }
        if (this.u) {
            ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        }
        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_FEED.name());
    }

    public void onPause() {
        super.onPause();
        if (this.B && this.C != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.C.setVisibility(8);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void n() {
        boolean z;
        this.v = (SwipeRefreshLayout) this.b.findViewById(R.id.grid_swipe_refresh_layout);
        this.v.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                SearchTabFragment.this.k = true;
                SearchTabFragment.this.a(0, true);
            }
        });
        this.m = (RecyclerView) this.b.findViewById(R.id.recycler_view_main);
        this.c = (RecyclerView) this.b.findViewById(R.id.recycler_view);
        this.c.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.c.setHasFixedSize(true);
        this.c.addOnScrollListener(this.E);
        this.i = this.b.findViewById(R.id.emptyTextContainer);
        this.e = (TextView) this.b.findViewById(R.id.emptyTextView);
        this.w = (TextView) this.b.findViewById(R.id.oopsText);
        this.h = (ProgressBar) this.b.findViewById(R.id.progressBar);
        if (d.j()) {
            this.h.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.new_gaana_red), Mode.SRC_IN);
        } else {
            Drawable wrap = DrawableCompat.wrap(this.h.getIndeterminateDrawable());
            DrawableCompat.setTint(wrap, ContextCompat.getColor(getContext(), R.color.new_gaana_red));
            this.h.setIndeterminateDrawable(DrawableCompat.unwrap(wrap));
        }
        if (getArguments() != null) {
            this.g = getArguments().getBoolean("ADD_MORE_SONGS", false);
            z = getArguments().getBoolean("IS_TRENDING", false);
        } else {
            z = false;
        }
        if (this.g) {
            this.v.setRefreshing(false);
            this.v.setEnabled(false);
        } else {
            RecentSearches f = GaanaSearchManager.a().f();
            if (f != null) {
                this.a = f.getRecentSearcheItems();
            }
            this.m.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.m.setHasFixedSize(true);
            this.p = new SearchFeedAdapter(this.mContext, this, r());
            if (z) {
                c();
            } else {
                o();
            }
        }
        this.C = this.b.findViewById(R.id.remove_ad_cta);
        this.C.setVisibility(8);
    }

    private void o() {
        this.A = true;
        this.m.setAdapter(this.p);
        this.m.addOnScrollListener(this.F);
        ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        a(0, false);
    }

    public TextView d() {
        return (this.p == null || this.p.getSearchTextView() == null) ? null : this.p.getSearchTextView();
    }

    public void a(final int i, final boolean z) {
        if (Util.j(this.mContext)) {
            this.v.setRefreshing(z);
        } else {
            this.v.setRefreshing(false);
        }
        URLManager uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(p());
        hashMap.put("recentSearches", stringBuilder.toString());
        hashMap.put("geoLocation", Constants.ct);
        if (!TextUtils.isEmpty(GaanaSearchManager.a().d())) {
            hashMap.put("usrLang", GaanaSearchManager.a().d());
        }
        uRLManager.a(hashMap);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://gsearch.gaana.com/gaanasearch-api/searchfeed/fetch?page=");
        stringBuilder2.append(i);
        stringBuilder2.append("&");
        uRLManager.a(stringBuilder2.toString());
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.m(false);
        if (this.k) {
            uRLManager.c(Boolean.valueOf(this.k));
        } else {
            uRLManager.c(Boolean.valueOf(z));
        }
        uRLManager.a(com.h.a.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof com.h.a) {
                    com.h.a aVar = (com.h.a) obj;
                    if (aVar.a() != null) {
                        if (z) {
                            SearchTabFragment.this.p.clearData();
                        }
                        SearchTabFragment.this.p.setDataView(aVar.a(), aVar.b(), z, i);
                        SearchTabFragment.this.v.setRefreshing(false);
                    }
                }
            }
        }, uRLManager);
    }

    private String p() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a != null && this.a.size() > 0) {
            int i = 5;
            if (this.a.size() <= 5) {
                i = this.a.size();
            }
            for (int i2 = 0; i2 < i; i2++) {
                AutoComplete autoComplete = (AutoComplete) this.a.get(i2);
                if (!TextUtils.isEmpty(autoComplete.getType())) {
                    switch (SearchCategory.valueOf(autoComplete.getType())) {
                        case Artist:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Artist");
                            stringBuilder.append(",");
                            break;
                        case Radio:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Radio");
                            stringBuilder.append(",");
                            break;
                        case Album:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Album");
                            stringBuilder.append(",");
                            break;
                        case Playlist:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Playlist");
                            stringBuilder.append(",");
                            break;
                        case Track:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Track");
                            stringBuilder.append(",");
                            break;
                        case OfflineTrack:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("OfflineTracks");
                            stringBuilder.append(",");
                            break;
                        case Occasion:
                            stringBuilder.append(autoComplete.getBusinessObjectId());
                            stringBuilder.append("-");
                            stringBuilder.append("Occasion");
                            stringBuilder.append(",");
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public void a(View view) {
        if (!(view instanceof SearchView)) {
            view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!(SearchTabFragment.this.b == null || SearchTabFragment.this.b.getWindowToken() == null)) {
                        Util.a(SearchTabFragment.this.mContext, SearchTabFragment.this.b);
                    }
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            int i = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i < viewGroup.getChildCount()) {
                    a(viewGroup.getChildAt(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void onClick(View view) {
        c(view);
    }

    public void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter) {
        if (nextGenAutoSuggestAdapter.getItemCount() <= 0) {
            if ((this.l == null && !GaanaSearchManager.a().l()) || this.f.length() < 3) {
                f();
            }
            return;
        }
        this.l = nextGenAutoSuggestAdapter;
        this.c.setAdapter(nextGenAutoSuggestAdapter);
        this.c.setVisibility(0);
        this.m.setVisibility(8);
        this.i.setVisibility(8);
        this.u = false;
        this.v.setEnabled(false);
        if (nextGenAutoSuggestAdapter.getItemCount() > 0 && this.j > 0.0f) {
            this.b.findViewById(R.id.searchAdSlot).setVisibility(8);
        }
    }

    public void a(boolean z) {
        if (this.c != null) {
            this.c.setVisibility(z ? 0 : 8);
        }
    }

    public void e() {
        this.m.setVisibility(0);
        this.m.setAdapter(new a());
        this.u = false;
        this.v.setEnabled(false);
    }

    public void f() {
        if (TextUtils.isEmpty(this.f) || this.f.length() < 3) {
            this.c.setAdapter(this.s);
            this.c.setVisibility(0);
            this.m.setVisibility(8);
            this.i.setVisibility(8);
            this.h.setVisibility(8);
            this.u = false;
            this.v.setEnabled(false);
            ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        } else {
            this.w.setTypeface(Typeface.DEFAULT_BOLD);
            this.e.setText(String.format(getResources().getString(R.string.search_null_result), new Object[]{this.f}));
            this.i.setVisibility(0);
            if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
                e();
            }
        }
        this.l = null;
    }

    public void a(String str) {
        if (this.mContext instanceof GaanaActivity) {
            ((GaanaActivity) this.mContext).setCrossButtonVisibility(true);
            ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
            this.f = str;
            if ((TextUtils.isEmpty(this.f) || this.f.length() < 3) && !GaanaSearchManager.a().j()) {
                this.m.setVisibility(0);
                this.u = true;
                this.v.setEnabled(true);
                return;
            }
            this.m.setVisibility(8);
            this.u = false;
            this.v.setEnabled(false);
        }
    }

    public String g() {
        return this.f;
    }

    public void h() {
        if (this.c != null && this.c.getAdapter() != null) {
            this.c.getAdapter().notifyDataSetChanged();
        }
    }

    public void b(boolean z) {
        if (this.h != null) {
            this.h.setVisibility(z ? 0 : 8);
        }
    }

    public void i() {
        RecentSearches f = GaanaSearchManager.a().f();
        if (f != null && f.getRecentSearcheItems() != null) {
            this.a = f.getRecentSearcheItems();
            if (this.p != null) {
                this.p.updateRecentSearchAdapter(this.a);
            }
        }
    }

    public void b(View view) {
        c(view);
    }

    private void c(View view) {
        Util.a(this.mContext, view);
        Object tag = view.getTag();
        CharSequence charSequence = "";
        String str = "";
        String str2 = "TrendingSearch";
        int ordinal = ACTION_DETAILS.TRENDING_SEARCH.ordinal();
        if (view instanceof TextView) {
            charSequence = String.valueOf(((TextView) view).getText());
        }
        if (tag instanceof AutoComplete) {
            str2 = "RecentSearch";
            AutoComplete autoComplete = (AutoComplete) tag;
            charSequence = autoComplete.getTitle();
            str = autoComplete.getEnglishTitle();
            ordinal = ACTION_DETAILS.RECENT_SEARCH.ordinal();
        } else {
            GaanaSearchManager.a().a(new AutoComplete(charSequence, null, 0, null));
        }
        int i = ordinal;
        GaanaSearchManager.a().a(true);
        EditText editText = (EditText) ((Activity) this.mContext).findViewById(16908290).findViewById(R.id.search_src_text);
        if (editText != null) {
            editText.setText(charSequence);
            editText.setSelection(editText.getText().length());
        }
        String str3 = "Online-SearchScreen";
        if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) {
            str3 = "Offline-SearchScreen";
        }
        ((BaseActivity) this.mContext).sendGAEvent(str3, str2, str);
        GaanaSearchManager.a().a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), i, 0, "", 0, "");
        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name());
    }

    public void a(SearchActionBar searchActionBar) {
        this.q = searchActionBar;
    }

    public void a() {
        if (this.q == null) {
            return;
        }
        if (this.u) {
            ((GaanaActivity) this.mContext).onBackPressed();
            return;
        }
        this.q.a();
        this.u = true;
        this.v.setEnabled(true);
        this.m.setVisibility(0);
        this.m.setAdapter(this.p);
        this.q.setSearchInnerActionBarVisibility(false);
        this.i.setVisibility(8);
        this.r = true;
        if (!this.A) {
            o();
        }
        if (this.x != null) {
            this.x.d();
        }
        ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        GaanaSearchManager.a().a(ACTION_TYPE.SEARCH_CANCEL.ordinal(), ACTION_DETAILS.SEARCH_BACK_TAP.ordinal(), 0, "", 0, "");
    }

    public void a(b bVar) {
        this.x = bVar;
    }

    public void onDestroyView() {
        if (this.D != null) {
            this.D.destroy();
            getLifecycle().b(this.D);
        }
        if (!(this.b == null || this.b.getParent() == null)) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }

    private com.dynamicview.f.a q() {
        com.dynamicview.f.a aVar = new com.dynamicview.f.a("Trending", "https://api.gaana.com/search/trending", DynamicViewType.hor_scroll.name(), null, "Trending", "", "", "240");
        aVar.c(VIEW_SUBTYPE.SEARCH_TRENDING.getNumVal());
        return aVar;
    }

    public void k() {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.d(true);
        ListingParams listingParams = new ListingParams();
        listingParams.d(true);
        listingParams.f(true);
        listingParams.h(true);
        listingParams.k(false);
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        uRLManager.d(true);
        listingButton.a(uRLManager);
        listingButton.d(true);
        listingButton.b(this.mContext.getString(R.string.recent_searches));
        listingButton.a(this.mContext.getString(R.string.recent_searches));
        listingButton.c(SearchItemView.class.getName());
        listingButton.a(this.a);
        listingButton.c(false);
        listingParams.a(listingButton);
        listingParams.g(true);
        listingParams.n(false);
        listingFragment.a(listingParams);
        listingFragment.a((BaseGaanaFragment) this);
        listingParams.m(true);
        ((GaanaActivity) this.mContext).displayFragment(listingFragment);
    }

    private int r() {
        return (int) ((((float) this.mContext.getResources().getDisplayMetrics().widthPixels) - this.mContext.getResources().getDimension(R.dimen.dimen_60dp)) / 3.0f);
    }
}

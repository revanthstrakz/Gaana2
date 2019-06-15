package com.gaana.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.b.i;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.fragments.MyMusicSearchResultFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.NextGenSearchAutoSuggests.GroupItem;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.RadioButtonGenericView;
import com.gaana.view.item.RadioButtonGenericView.RadioSearchItemHolder;
import com.gaana.view.item.SearchItemView;
import com.gaana.view.item.SearchItemView.RecentSearchItemHolder;
import com.gaana.view.item.SearchItemView.SearchItemHolder;
import com.gaana.view.item.ShareableSongsView;
import com.gaana.view.item.ShareableSongsView.ShareableSongsHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.URLManager;
import com.managers.an;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.b;
import com.views.HorizontalRecyclerView.c;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class NextGenAutoSuggestAdapter extends Adapter<ViewHolder> {
    public static final int HEADER_VIEW = 1;
    public static final int HORIZONTAL_VIEW = 2;
    public static final int SEARCH_INTERVENTION_VIEW = 0;
    private ArrayList<AutoComplete> arrListAutoComplete;
    private BaseItemView baseItemView;
    private ArrayList<Track> currentTracksInPlaylist;
    private boolean hideHeaderText;
    private boolean hideViewAll;
    private boolean isTopResult = false;
    private ArrayList<GroupItem> mArrrListItems;
    private final int mBottomRecyclerPadding;
    private final int mCardBg;
    private final int mCardTextColor;
    private Context mContext;
    private SearchType mSearchType;
    private final String mSeeAllText;
    private NextGenSearchAutoSuggests searchAutoSuggests;
    private String searchText;
    private RecycledViewPool viewPool;

    public class ItemAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
        private ArrayList<AutoComplete> arrListItem;
        private ArrayList<Track> currentTracksInPlaylist;
        private String itemType;

        public ItemAdapter(SearchType searchType, ArrayList<Track> arrayList, String str, ArrayList<AutoComplete> arrayList2) {
            this.arrListItem = arrayList2;
            this.itemType = str;
            this.currentTracksInPlaylist = arrayList;
            initItemView(searchType);
        }

        public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View createViewHolder = NextGenAutoSuggestAdapter.this.baseItemView.createViewHolder(viewGroup, i);
            if (NextGenAutoSuggestAdapter.this.baseItemView instanceof RadioButtonGenericView) {
                return new RadioSearchItemHolder(createViewHolder);
            }
            if (NextGenAutoSuggestAdapter.this.baseItemView instanceof ShareableSongsView) {
                return new ShareableSongsHolder(createViewHolder);
            }
            android.support.v7.widget.RecyclerView.ViewHolder searchItemHolder = new SearchItemHolder(createViewHolder);
            if (!NextGenAutoSuggestAdapter.this.isTopResult) {
                return searchItemHolder;
            }
            ((SearchItemHolder) searchItemHolder).setTopResult(true);
            return searchItemHolder;
        }

        public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
            ((AutoComplete) this.arrListItem.get(i)).setPosition(NextGenAutoSuggestAdapter.this.arrListAutoComplete.indexOf(this.arrListItem.get(i)));
            if (!(TextUtils.isEmpty(this.itemType) || this.itemType.equalsIgnoreCase(LocalMediaManager.MY_MUSIC) || this.itemType.equalsIgnoreCase("Local Files"))) {
                ((AutoComplete) this.arrListItem.get(i)).setType(this.itemType);
            }
            if (NextGenAutoSuggestAdapter.this.baseItemView instanceof RadioButtonGenericView) {
                NextGenAutoSuggestAdapter.this.baseItemView.getPoplatedView(viewHolder, (BusinessObject) this.arrListItem.get(i), null, this.currentTracksInPlaylist);
                return;
            }
            NextGenAutoSuggestAdapter.this.baseItemView.setSearchQuery(NextGenAutoSuggestAdapter.this.searchText);
            NextGenAutoSuggestAdapter.this.baseItemView.getPoplatedView(viewHolder, (BusinessObject) this.arrListItem.get(i), null);
        }

        public int getItemCount() {
            return this.arrListItem != null ? this.arrListItem.size() : 0;
        }

        /* Access modifiers changed, original: 0000 */
        public void initItemView(SearchType searchType) {
            try {
                Class cls;
                if (searchType == SearchType.Playlist_Search) {
                    cls = Class.forName(RadioButtonGenericView.class.getName());
                } else if (searchType == SearchType.OnlySongs) {
                    cls = Class.forName(RadioButtonGenericView.class.getName());
                } else {
                    cls = Class.forName(SearchItemView.class.getName());
                }
                Constructor constructor = cls.getConstructor(new Class[]{Context.class, BaseGaanaFragment.class, Integer.TYPE});
                NextGenAutoSuggestAdapter.this.baseItemView = (BaseItemView) constructor.newInstance(new Object[]{NextGenAutoSuggestAdapter.this.mContext, null, Integer.valueOf(Integer.MIN_VALUE)});
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public static class HeaderViewHolder extends ViewHolder {
        private ImageView clickOptionImage;
        private ItemAdapter itemAdapter;
        private RecyclerView recyclerView;
        private TextView title;
        private TextView viewAll;
        private LinearLayout viewAllContainer;

        public HeaderViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.headerText);
            this.viewAll = (TextView) view.findViewById(R.id.viewAll);
            this.viewAllContainer = (LinearLayout) view.findViewById(R.id.viewAllContainer);
            this.clickOptionImage = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.viewAll.setTypeface(i.a(view.getContext().getAssets(), "fonts/Roboto-Medium.ttf"));
            this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
    }

    public static class HorizontalViewHolder extends ViewHolder {
        public HorizontalRecyclerView recyclerView;
        public TextView textLabel;
        public TextView viewAll;

        public HorizontalViewHolder(View view) {
            super(view);
            this.textLabel = (TextView) view.findViewById(R.id.f62similar.header.text);
            this.viewAll = (TextView) view.findViewById(R.id.f63similar.header.view_all);
            this.viewAll.setTypeface(i.a(view.getContext().getAssets(), "fonts/Roboto-Medium.ttf"));
            this.recyclerView = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
            this.recyclerView.setHasFixedSize(false);
        }
    }

    public static class SearchInterventionViewHolder extends ViewHolder {
        private TextView mainText;
        private TextView subText;

        public SearchInterventionViewHolder(View view) {
            super(view);
            this.mainText = (TextView) view.findViewById(R.id.mainText);
            this.subText = (TextView) view.findViewById(R.id.subText);
        }
    }

    public void updateArrayList(ArrayList<GroupItem> arrayList) {
        this.mArrrListItems = arrayList;
    }

    public NextGenAutoSuggestAdapter(Activity activity, String str) {
        this.mContext = activity;
        this.searchText = str;
        this.arrListAutoComplete = new ArrayList();
        this.mBottomRecyclerPadding = (int) activity.getResources().getDimension(R.dimen.search_see_all_height);
        this.mSeeAllText = activity.getResources().getString(R.string.see_all_search);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.view_foreground, R.attr.first_line_color});
        this.mCardBg = obtainStyledAttributes.getColor(0, 0);
        this.mCardTextColor = obtainStyledAttributes.getColor(1, 0);
        obtainStyledAttributes.recycle();
        this.viewPool = new RecycledViewPool();
    }

    public void setSearchType(SearchType searchType) {
        this.mSearchType = searchType;
    }

    public void setCurrentTracksInPlaylist(ArrayList<Track> arrayList) {
        this.currentTracksInPlaylist = arrayList;
    }

    public void shouldHideHeaderText(boolean z) {
        this.hideHeaderText = z;
    }

    public boolean isHideViewAll() {
        return this.hideViewAll;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_header, viewGroup, false));
        }
        if (i == 2) {
            return new HorizontalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_list_view_search, viewGroup, false));
        }
        return new SearchInterventionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_intervention_view, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SearchInterventionViewHolder searchInterventionViewHolder;
        if (this.searchAutoSuggests != null && this.searchAutoSuggests.getSearchIntervention() == 1 && i == 0) {
            searchInterventionViewHolder = (SearchInterventionViewHolder) viewHolder;
            searchInterventionViewHolder.mainText.setText(this.searchAutoSuggests.getInterventionText());
            searchInterventionViewHolder.subText.setText(this.searchAutoSuggests.getSubText());
            searchInterventionViewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(NextGenAutoSuggestAdapter.this.searchText, "1");
                    }
                }
            });
        } else if (this.searchAutoSuggests == null || TextUtils.isEmpty(this.searchAutoSuggests.getTransliteratedHeader()) || i != 0) {
            if (this.searchAutoSuggests != null && (this.searchAutoSuggests.getSearchIntervention() == 1 || !TextUtils.isEmpty(this.searchAutoSuggests.getTransliteratedHeader()))) {
                i--;
            } else if (i == 0 && isTopResult()) {
                this.isTopResult = true;
            } else {
                this.isTopResult = false;
            }
            final GroupItem groupItem = (GroupItem) this.mArrrListItems.get(i);
            this.arrListAutoComplete.addAll(((GroupItem) this.mArrrListItems.get(i)).getAutocomplete());
            if (viewHolder.getItemViewType() == 2) {
                HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
                if (TextUtils.isEmpty(((GroupItem) this.mArrrListItems.get(i)).getdisplayTitle())) {
                    horizontalViewHolder.textLabel.setText(((GroupItem) this.mArrrListItems.get(i)).getType());
                } else {
                    horizontalViewHolder.textLabel.setText(((GroupItem) this.mArrrListItems.get(i)).getdisplayTitle());
                }
                if (groupItem.getArrListBusinessObj().size() >= 10) {
                    horizontalViewHolder.viewAll.setVisibility(0);
                    horizontalViewHolder.viewAll.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            NextGenAutoSuggestAdapter.this.viewAll(groupItem.getType(), groupItem.isLocalMedia());
                        }
                    });
                } else {
                    horizontalViewHolder.viewAll.setVisibility(8);
                }
                b a = horizontalViewHolder.recyclerView.a(this.mContext, groupItem.getArrListBusinessObj().size());
                a.a(new c() {
                    public View getCompatibleView(int i, int i2, int i3, android.support.v7.widget.RecyclerView.ViewHolder viewHolder) {
                        if (i3 == 0) {
                            viewHolder.itemView.setPadding((int) NextGenAutoSuggestAdapter.this.mContext.getResources().getDimension(R.dimen.search_padding), 0, 0, 0);
                        } else if (i3 == groupItem.getAutocomplete().size() - 1) {
                            viewHolder.itemView.setPadding(0, 0, (int) NextGenAutoSuggestAdapter.this.mContext.getResources().getDimension(R.dimen.search_padding), 0);
                        }
                        NextGenAutoSuggestAdapter.this.baseItemView.getPoplatedView(viewHolder, (BusinessObject) groupItem.getAutocomplete().get(i3));
                        return viewHolder.itemView;
                    }

                    public android.support.v7.widget.RecyclerView.ViewHolder createViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                        NextGenAutoSuggestAdapter.this.baseItemView = new SearchItemView(NextGenAutoSuggestAdapter.this.mContext, null);
                        return new RecentSearchItemHolder(NextGenAutoSuggestAdapter.this.baseItemView.createViewHolder(viewGroup, i, R.layout.view_recent_search_item));
                    }
                });
                horizontalViewHolder.recyclerView.setAdapter(a);
            } else {
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                LayoutParams layoutParams = (LayoutParams) headerViewHolder.itemView.getLayoutParams();
                int i2 = 4;
                if (i == 0) {
                    layoutParams.setMargins(this.mBottomRecyclerPadding / 8, this.mBottomRecyclerPadding / 4, this.mBottomRecyclerPadding / 8, this.mBottomRecyclerPadding / 4);
                } else {
                    layoutParams.setMargins(this.mBottomRecyclerPadding / 8, 0, this.mBottomRecyclerPadding / 8, this.mBottomRecyclerPadding / 4);
                }
                if (this.hideHeaderText) {
                    headerViewHolder.viewAllContainer.setVisibility(8);
                    headerViewHolder.title.setVisibility(8);
                } else {
                    if (TextUtils.isEmpty(((GroupItem) this.mArrrListItems.get(i)).getdisplayTitle())) {
                        headerViewHolder.title.setText(((GroupItem) this.mArrrListItems.get(i)).getType());
                    } else {
                        headerViewHolder.title.setText(((GroupItem) this.mArrrListItems.get(i)).getdisplayTitle());
                    }
                    LinearLayout access$700 = headerViewHolder.viewAllContainer;
                    if (((GroupItem) this.mArrrListItems.get(i)).isViewAllEnabled()) {
                        i2 = 0;
                    }
                    access$700.setVisibility(i2);
                }
                if (this.hideViewAll) {
                    headerViewHolder.viewAllContainer.setVisibility(8);
                }
                headerViewHolder.itemAdapter = new ItemAdapter(this.mSearchType, this.currentTracksInPlaylist, ((GroupItem) this.mArrrListItems.get(i)).getType(), ((GroupItem) this.mArrrListItems.get(i)).getAutocomplete());
                headerViewHolder.recyclerView.setRecycledViewPool(this.viewPool);
                headerViewHolder.recyclerView.setAdapter(headerViewHolder.itemAdapter);
                headerViewHolder.viewAllContainer.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (groupItem.isViewAllEnabled()) {
                            NextGenAutoSuggestAdapter.this.viewAll(groupItem.getType(), groupItem.isLocalMedia());
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(groupItem.getType().toUpperCase());
                        stringBuilder.append("_SEE_ALL");
                        an.a().a("click", "ac", NextGenAutoSuggestAdapter.this.searchText, "SEARCH", "", stringBuilder.toString(), "", "");
                    }
                });
                TextView access$1000 = headerViewHolder.viewAll;
                String str = this.mSeeAllText;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" ");
                stringBuilder.append(headerViewHolder.title.getText());
                access$1000.setText(str.concat(stringBuilder.toString()));
                headerViewHolder.recyclerView.setPadding(0, 0, 0, headerViewHolder.viewAllContainer.getVisibility() == 0 ? this.mBottomRecyclerPadding : 0);
                headerViewHolder.clickOptionImage.setVisibility(8);
                headerViewHolder.title.setTextColor(this.mCardTextColor);
            }
        } else {
            searchInterventionViewHolder = (SearchInterventionViewHolder) viewHolder;
            searchInterventionViewHolder.mainText.setText(String.format(this.mContext.getResources().getString(R.string.transliterated_search_header), new Object[]{this.searchAutoSuggests.getTransliteratedHeader()}));
            searchInterventionViewHolder.mainText.setPadding(0, (int) this.mContext.getResources().getDimension(R.dimen.margin_standard), 0, (int) this.mContext.getResources().getDimension(R.dimen.margin_standard));
            searchInterventionViewHolder.subText.setVisibility(8);
        }
    }

    private void viewAll(String str, boolean z) {
        String str2 = "Online-Autosuggest";
        if (GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(this.mContext)) {
            str2 = "Offline-Autosuggest";
        }
        BaseActivity baseActivity = (BaseActivity) this.mContext;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ViewAll-");
        stringBuilder.append(str);
        baseActivity.sendGAEvent(str2, stringBuilder.toString(), this.searchText);
        BaseGaanaFragment myMusicSearchResultFragment;
        if (z) {
            myMusicSearchResultFragment = new MyMusicSearchResultFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("searchInDownloads", str.equalsIgnoreCase(LocalMediaManager.MY_MUSIC));
            bundle.putString("searchText", this.searchText);
            myMusicSearchResultFragment.setArguments(bundle);
            ((GaanaActivity) this.mContext).displayFragment(myMusicSearchResultFragment);
            return;
        }
        ListingParams listingParams = new ListingParams();
        listingParams.e(false);
        listingParams.f(true);
        listingParams.h(z);
        listingParams.d(true);
        listingParams.i(false);
        listingParams.a(false);
        ListingComponents a = Constants.a(this.mSearchType, this.searchText, str);
        ListingButton listingButton = (ListingButton) a.c().get(0);
        listingButton.b(this.currentTracksInPlaylist);
        URLManager c = listingButton.c();
        c.g(false);
        c.d(z);
        c.e(z);
        c.h(z);
        c.b(this.searchText);
        c.a(NextGenSearchAutoSuggests.class);
        listingParams.a(listingButton);
        listingParams.a(this.mSearchType);
        myMusicSearchResultFragment = new ListingFragment();
        myMusicSearchResultFragment.a(listingParams);
        GaanaApplication.getInstance().setListingComponents(a);
        ((GaanaActivity) this.mContext).displayFragment(myMusicSearchResultFragment);
    }

    public int getItemCount() {
        if (this.searchAutoSuggests == null || this.mArrrListItems.size() <= 0 || (this.searchAutoSuggests.getSearchIntervention() != 1 && TextUtils.isEmpty(this.searchAutoSuggests.getTransliteratedHeader()))) {
            return this.mArrrListItems.size();
        }
        return this.mArrrListItems.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i == 0 && this.searchAutoSuggests != null && (this.searchAutoSuggests.getSearchIntervention() == 1 || !TextUtils.isEmpty(this.searchAutoSuggests.getTransliteratedHeader()))) {
            return 0;
        }
        if (this.searchAutoSuggests != null && (this.searchAutoSuggests.getSearchIntervention() == 1 || !TextUtils.isEmpty(this.searchAutoSuggests.getTransliteratedHeader()))) {
            i--;
        }
        if (this.mArrrListItems == null || i >= this.mArrrListItems.size() || !((GroupItem) this.mArrrListItems.get(i)).getType().equals(LocalMediaManager.MY_MUSIC)) {
            return 1;
        }
        return 2;
    }

    public ArrayList<?> getAdapterArrayList() {
        return this.mArrrListItems;
    }

    public void setSearchSuggestions(NextGenSearchAutoSuggests nextGenSearchAutoSuggests, boolean z) {
        if (z) {
            this.searchAutoSuggests = nextGenSearchAutoSuggests;
        }
        if (this.mArrrListItems == null || this.mArrrListItems.size() <= 0) {
            this.mArrrListItems = nextGenSearchAutoSuggests.getGroupItems();
        } else {
            this.mArrrListItems.addAll(nextGenSearchAutoSuggests.getGroupItems());
        }
        this.arrListAutoComplete.clear();
    }

    public void setHideViewAll(boolean z) {
        this.hideViewAll = z;
    }

    private boolean isTopResult() {
        return this.searchAutoSuggests != null && ((AutoComplete) ((GroupItem) this.mArrrListItems.get(0)).getAutocomplete().get(0)).isTopResult();
    }
}

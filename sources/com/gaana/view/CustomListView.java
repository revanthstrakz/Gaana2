package com.gaana.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StyleableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.fragments.AddToPlaylistFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.ItemListingFragment;
import com.fragments.ListingFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.MyMusicSearchResultFragment;
import com.fragments.ProfileFragment;
import com.fragments.RevampedDetailListing;
import com.fragments.SettingsDetailFragment;
import com.gaana.AutomatedPlaylistItemView;
import com.gaana.BaseActivity;
import com.gaana.FastScrollRecyclerView.FastScrollRecyclerView;
import com.gaana.GaanaActivity;
import com.gaana.LocalFileSongsRecyclerView;
import com.gaana.R;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.adapter.ListAdapterSectionIndexer;
import com.gaana.adapter.ListAdapterSectionIndexer.OnNotificationsCleared;
import com.gaana.adapter.ListAdapterSectionIndexer.OnSearchCompleted;
import com.gaana.adapter.ListAdapterSectionIndexer.SearchFilter;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.localmedia.LocalMediaFilter.AlbumFilter;
import com.gaana.localmedia.LocalMediaFilter.GenericFilter;
import com.gaana.localmedia.LocalMediaFilter.SongFilter;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions.FavoriteOccasion;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.NextGenSearchAutoSuggests.GroupItem;
import com.gaana.models.Notifications;
import com.gaana.models.Notifications.Notification;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserActivities.UserActivity;
import com.gaana.models.UserMessage;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.FavoriteOccasionItemView.FavoriteOccasionItemHolder;
import com.gaana.view.item.ActivityItemView;
import com.gaana.view.item.AddToPlaylistItemView;
import com.gaana.view.item.AddToPlaylistItemView.AddToPlaylistItemViewHolder;
import com.gaana.view.item.AlbumItemView;
import com.gaana.view.item.ArtistItemView;
import com.gaana.view.item.ArtistItemView.ArtistItemHolder;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ActivityListHolder;
import com.gaana.view.item.BaseItemView.EmptyMessageHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.ByMePlaylistItemView;
import com.gaana.view.item.DownloadAlbumItemView;
import com.gaana.view.item.DownloadAlbumItemView.DownloadAlbumItemHolder;
import com.gaana.view.item.DownloadPlaylistItemView;
import com.gaana.view.item.DownloadPlaylistItemView.DownloadPlayListItemHolder;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.DownloadSongListingView.DownloadSongListingHolder;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.gaana.view.item.EditPlaylistSelectSongView;
import com.gaana.view.item.EditPlaylistSelectSongView.EditPlaylistSelectSongViewHolder;
import com.gaana.view.item.MyActivityInfoItemView;
import com.gaana.view.item.MyActivityInfoItemView.MyActivityInfoListingHolder;
import com.gaana.view.item.NewGenericItemView;
import com.gaana.view.item.NewGenericItemView.NewGenericItemHolder;
import com.gaana.view.item.NotificationItemView;
import com.gaana.view.item.NotificationItemView.DownloadNotifHolder;
import com.gaana.view.item.NotificationItemView.NotificationItemHolder;
import com.gaana.view.item.OffersView.OffersViewHolder;
import com.gaana.view.item.PlaylistItemView;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.RadioButtonGenericView;
import com.gaana.view.item.RadioButtonGenericView.RadioSearchItemHolder;
import com.gaana.view.item.RadioButtonPlaylistView;
import com.gaana.view.item.RadioButtonPlaylistView.RadioButtonPlaylistViewHolder;
import com.gaana.view.item.RadioButtonSongView;
import com.gaana.view.item.RadioButtonSongView.RadioButtonSongHolder;
import com.gaana.view.item.RadioItemView;
import com.gaana.view.item.RadioItemView.RadioItemHolder;
import com.gaana.view.item.SearchItemView;
import com.gaana.view.item.SearchItemView.SearchItemHolder;
import com.gaana.view.item.ShuffleBottomView;
import com.gaana.view.item.SongsItemView;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.managers.ColombiaManager;
import com.managers.ColombiaManager.a;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.ab;
import com.managers.af;
import com.managers.aj;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.aq;
import com.managers.g;
import com.models.ListingButton;
import com.models.PlayerTrack;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.l.as;
import com.services.l.l;
import com.services.l.p;
import com.services.l.s;
import com.services.l.u;
import com.utilities.Util;
import com.utilities.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class CustomListView implements OnNotificationsCleared, l, u {
    private static final int AD_VIEW = 8;
    private static final int VIEW_TYPE_SOCIAL_HEADER = 5;
    private int REPETATIVE_AD_INTERVAL = 5;
    private int actualLastPos = -1;
    ArrayList<Integer> adSpots = new ArrayList();
    ListAdapterSectionIndexer adapter = null;
    LinearLayout bottomLayout = null;
    private boolean enableShuffleIcon = true;
    boolean fetchDataFromDb = false;
    Boolean hasDataEnded = Boolean.valueOf(false);
    private boolean hasLoadingStarted = false;
    private ImageView imgDownloadAll;
    private String initialUrl;
    boolean isFirstClickOnDownloadAllCurated = true;
    private boolean isFirstime;
    Boolean isPullToRefresh = Boolean.valueOf(false);
    private boolean is_trendinglist_onlocal_songs = false;
    private int itemCount = -1;
    private int lastScrolledPos = 0;
    private String listViewLabel;
    LinearLayout llLoadingView = null;
    private LocalFileSongsRecyclerView localFileSongsRecyclerView = null;
    GaanaApplication mAppState = null;
    private BusinessObject mBusinessObject;
    BaseItemView mBusinessView;
    Context mContext;
    private LinearLayout mCuratedLayout;
    protected OnDataLoadedListener mDataLoadedListener;
    protected OnDataRefreshListener mDataRefreshListener;
    private LinearLayout mDownloadAllLayout;
    private RelativeLayout mDownloadCuratedSongsLayout;
    private LinearLayout mDownloadNowLayout;
    BaseGaanaFragment mFragment;
    private RelativeLayout mGaanaPlusExpiredLayout;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private View mHeaderViewScroll;
    private LayoutInflater mInflater = null;
    private boolean mIsLoadMoreProgressBarVisible = false;
    private ItemTouchHelper mItemTouchHelper;
    private LinearLayout mLLHeaderLayout;
    private int mLayoutId = -1;
    private View mListFooterView = null;
    RecyclerView mListViewHome;
    ListingButton mListingButton;
    private String mListingName;
    private p mOnAdRefreshListener = null;
    private ArrayList<Object> mOrignalList = new ArrayList();
    ProgressBar mParentLoading;
    private int mPosition;
    private SearchView mSearchView;
    private LinearLayout mSearchViewContainer;
    private boolean mShowScrollHeader;
    protected SortOrder mSortOrder = SortOrder.Default;
    private e mSwipeCallback;
    private int mTotalScrolled = 0;
    private View mView = null;
    private ArrayList oldArrayList = new ArrayList();
    s onBusinessObjectRetrieved = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            CustomListView.this.mParentLoading.setVisibility(8);
            if (CustomListView.this.mListingButton.i()) {
                CustomListView.this.pullToRefreshlistView.setEnabled(true);
            } else {
                CustomListView.this.pullToRefreshlistView.setEnabled(false);
            }
            CustomListView.this.pullToRefreshlistView.setRefreshing(false);
            if (businessObject == null) {
                CustomListView.this.showHideEmtpyViewLayout(true);
                CustomListView.this.hasDataEnded = Boolean.valueOf(true);
                return;
            }
            if (CustomListView.this.mListingButton.k() != null) {
                CustomListView.this.mListingButton.k().a(businessObject);
                businessObject.setArrListBusinessObj(CustomListView.this.mListingButton.g());
            }
            if (businessObject instanceof Notifications) {
                ArrayList arrayList = new ArrayList();
                if (businessObject.getArrListBusinessObj() != null) {
                    Iterator it = businessObject.getArrListBusinessObj().iterator();
                    while (it.hasNext()) {
                        aa.a().a((Notification) it.next(), false);
                    }
                }
                if (!(aa.a().b() == null || aa.a().b().getArrListBusinessObj() == null)) {
                    if (aa.a().b().getArrListBusinessObj().size() >= 100) {
                        aa.a().b().getArrListBusinessObj().subList(100, aa.a().b().getArrListBusinessObj().size()).clear();
                    }
                    arrayList.addAll(aa.a().b().getArrListBusinessObj());
                }
                if (ab.a().d() > 0) {
                    Notification notification = new Notification("", "", "", "", "");
                    notification.setTimestamp(Long.MAX_VALUE);
                    notification.setSeen(1);
                    arrayList.add(0, notification);
                    CustomListView.this.showOffers = Boolean.valueOf(true);
                }
                businessObject.setArrListBusinessObj(arrayList);
            }
            BusinessObject a = CustomListView.this.mAppState.getListingComponents() != null ? CustomListView.this.mAppState.getListingComponents().a() : null;
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            if (arrListBusinessObj == null || arrListBusinessObj.size() <= 0) {
                if ((CustomListView.this.mFragment instanceof ListingFragment) && CustomListView.this.mDataLoadedListener != null) {
                    CustomListView.this.mDataLoadedListener.onDataLoaded(businessObject, CustomListView.this.mListingButton.c().i());
                }
                CustomListView.this.showHideEmtpyViewLayout(true);
                CustomListView.this.hasDataEnded = Boolean.valueOf(true);
            } else {
                CustomListView.this.mOrignalList.clear();
                CustomListView.this.mOrignalList.addAll(arrListBusinessObj);
                try {
                    HashMap h = CustomListView.this.mListingButton.c().h();
                    boolean z = (h == null || h.get("type") == null || !((String) h.get("type")).equals("mysongs")) ? false : true;
                    if (z || (a instanceof Playlist)) {
                        for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                            Track track = (Track) arrListBusinessObj.get(size);
                            if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                track = LocalMediaManager.getInstance(CustomListView.this.mContext).getLocalTrackFromHash(track.getBusinessObjId());
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
                    CustomListView.this.showHideEmtpyViewLayout(true);
                    CustomListView.this.hasDataEnded = Boolean.valueOf(true);
                } else {
                    if (businessObject instanceof NextGenSearchAutoSuggests) {
                        businessObject = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
                        ((AutoComplete) businessObject.getArrListBusinessObj().get(0)).setType(((GroupItem) businessObject).getType());
                    }
                    CustomListView.this.populateListView(businessObject.getArrListBusinessObj(), CustomListView.this.mBusinessView);
                    CustomListView.this.setBusinesObject(businessObject);
                    CustomListView.this.showHideEmtpyViewLayout(false);
                }
                if (CustomListView.this.mListingButton.c().e().booleanValue() && businessObject.getArrListBusinessObj().size() < 20) {
                    CustomListView.this.hasDataEnded = Boolean.valueOf(true);
                }
                if (CustomListView.this.mDataLoadedListener != null) {
                    CustomListView.this.mDataLoadedListener.onDataLoaded(businessObject, CustomListView.this.mListingButton.c().i());
                }
                if (CustomListView.this.mListingButton.a() && CustomListView.this.mDownloadAllLayout != null) {
                    CustomListView.this.mDownloadAllLayout.setVisibility(0);
                    CustomListView.this.updateCuratedListHeader(CustomListView.this.getCuratedPlaylist());
                    al.a().a(false);
                }
            }
            CustomListView.this.onScrollChanged(-1);
            CustomListView.this.scrollStateChanged(0);
        }
    };
    s onLoadMoreDataFinished = new s() {
        public void onRetreivalComplete(BusinessObject businessObject) {
            if (CustomListView.this.hasLoadingStarted) {
                CustomListView.this.onLoadMoreComplete();
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                    CustomListView.this.hasDataEnded = Boolean.valueOf(true);
                } else if (CustomListView.this.matchModelView(businessObject.getArrListBusinessObj().get(0), CustomListView.this.mBusinessView)) {
                    if (businessObject.getArrListBusinessObj().size() < 20) {
                        CustomListView.this.hasDataEnded = Boolean.valueOf(true);
                    }
                    CustomListView.this.adapter.updateAdapterArrayList(businessObject.getArrListBusinessObj());
                    CustomListView.this.mListingButton.a(CustomListView.this.adapter.getAdapterArrayList());
                    if (al.a().d() && (CustomListView.this.mFragment instanceof ListingFragment)) {
                        ((ListingFragment) CustomListView.this.mFragment).b(CustomListView.this.adapter.getAdapterArrayList().size());
                    }
                    CustomListView.this.updateSongQueue();
                    if (CustomListView.this.mFragment instanceof ProfileFragment) {
                        CustomListView.this.setBusinesObject(businessObject);
                    }
                    CustomListView.this.setRepetativeAdSpots();
                } else {
                    return;
                }
                CustomListView.this.hasLoadingStarted = false;
                if (CustomListView.this.shuffleMoreSongs) {
                    CustomListView.this.shuffleSongs();
                    CustomListView.this.shuffleMoreSongs = false;
                }
                CustomListView.this.mHandler.postDelayed(CustomListView.this.runnable, 500);
            }
        }

        public void onErrorResponse(BusinessObject businessObject) {
            CustomListView.this.hasLoadingStarted = false;
            CustomListView.this.mHandler.postDelayed(CustomListView.this.runnable, 500);
        }
    };
    private String orderColumn = "added_on";
    private String orderType = "DESC";
    public SwipeRefreshLayout pullToRefreshlistView = null;
    private Runnable runnable = new Runnable() {
        public void run() {
            CustomListView.this.shufflePlayLoadMore = false;
        }
    };
    private String searchHintText = "";
    private String searchParam = "";
    public boolean shouldShowNoDownloadView;
    private boolean shouldShowSearchWidget;
    public boolean showDownloadCuratedSongsLayout = false;
    private boolean showEmptyView = false;
    protected boolean showFabButton = false;
    private Boolean showOffers = Boolean.valueOf(false);
    private boolean showRepetativeAds = false;
    private boolean shuffleMoreSongs;
    private boolean shufflePlayLoadMore = false;
    ArrayList<?> trendingArray = null;
    private TextView txtDownloadAll;

    public class Header extends BusinessObject {
        private String name;

        private Header(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType);
    }

    public interface OnDataRefreshListener {
        void onEmptyDataRefresh();
    }

    public void loadMoreData(int i) {
    }

    public void onCheckedStateUnSelected(int i) {
    }

    public void setIsTrendingSongsOnLocalFiles(boolean z) {
        this.is_trendinglist_onlocal_songs = z;
        if (z) {
            this.localFileSongsRecyclerView = new LocalFileSongsRecyclerView(this.mContext, this.mFragment);
        }
    }

    public boolean getIsTrendingSongsOnLocalFiles() {
        return this.is_trendinglist_onlocal_songs;
    }

    public void saveOriginalMyPlaylist(ArrayList arrayList) {
        this.mOrignalList.clear();
        this.mOrignalList.addAll(arrayList);
    }

    public CustomListView(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public CustomListView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        this.mPosition = i;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    /* Access modifiers changed, original: protected */
    public void initView() {
        if (this.showFabButton) {
            if (!(this instanceof CustomListViewOffline)) {
                this.mView = this.mInflater.inflate(R.layout.view_custom_list_fab, null);
                ((RecyclerView) this.mView.findViewById(R.id.recycler_view)).addOnScrollListener(new OnScrollListener() {
                    private int actualLastPos = -1;
                    private int lastScrolledPos = 0;
                    private int mTotalScrolled = 0;

                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        CustomListView.this.onScrollChanged(i2);
                        this.mTotalScrolled += i2;
                    }

                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        CustomListView.this.scrollStateChanged(i);
                        CustomListView.this.sendScrollEvent(i, recyclerView, this.mTotalScrolled, this.lastScrolledPos, this.actualLastPos);
                    }
                });
            } else if (this.mAppState.getCurrentUser().getUserSubscriptionData() == null || this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != 2) {
                this.mView = this.mInflater.inflate(R.layout.view_custom_list_fab, null);
                ((RecyclerView) this.mView.findViewById(R.id.recycler_view)).addOnScrollListener(new OnScrollListener() {
                    private int actualLastPos = -1;
                    private int lastScrolledPos = 0;
                    private int mTotalScrolled = 0;

                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        CustomListView.this.onScrollChanged(i2);
                        this.mTotalScrolled += i2;
                    }

                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        CustomListView.this.scrollStateChanged(i);
                        CustomListView.this.sendScrollEvent(i, recyclerView, this.mTotalScrolled, this.lastScrolledPos, this.actualLastPos);
                    }
                });
            } else {
                this.mView = this.mInflater.inflate(R.layout.view_custom_list_fab_withads, null);
                ((RecyclerView) this.mView.findViewById(R.id.recycler_view)).addOnScrollListener(new OnScrollListener() {
                    private int actualLastPos = -1;
                    private int lastScrolledPos = 0;
                    private int mTotalScrolled = 0;

                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        CustomListView.this.onScrollChanged(i2);
                        this.mTotalScrolled += i2;
                    }

                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        CustomListView.this.scrollStateChanged(i);
                        CustomListView.this.sendScrollEvent(i, recyclerView, this.mTotalScrolled, this.lastScrolledPos, this.actualLastPos);
                    }
                });
            }
            scrollStateChanged(0);
        } else {
            this.mView = this.mInflater.inflate(R.layout.view_custom_listview, null);
        }
        this.llLoadingView = (LinearLayout) this.mView.findViewById(R.id.ll_loading_row);
        this.bottomLayout = (LinearLayout) this.mView.findViewById(R.id.bottom_layout);
        this.mSearchViewContainer = (LinearLayout) this.mView.findViewById(R.id.searchview_container);
        this.mSearchView = (SearchView) this.mSearchViewContainer.findViewById(R.id.srchview);
        if (this.shouldShowSearchWidget) {
            if (!TextUtils.isEmpty(this.mListingName)) {
                TextView textView = new TextView(this.mContext);
                if (Constants.l) {
                    textView.setBackgroundColor(this.mContext.getResources().getColor(R.color.generic_background_screen_color_white));
                } else {
                    textView.setBackgroundColor(this.mContext.getResources().getColor(R.color.generic_background_screen_color));
                }
                textView.setText(this.mListingName);
                textView.setVisibility(0);
                textView.setPadding((int) this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin), (int) this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_half), 0, (int) this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_half));
                this.mSearchViewContainer.addView(textView);
            }
            if (!this.shouldShowNoDownloadView) {
                this.mSearchViewContainer.setVisibility(0);
            }
            this.mSearchView.setQueryHint(this.searchHintText);
            this.mSearchView.setOnQueryTextFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        an.a().a("click", "ac", "", an.a().a(3), "", "search", "", "");
                    }
                }
            });
        } else {
            this.mSearchViewContainer.setVisibility(8);
        }
        if (this.mLayoutId != -1) {
            View inflate = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mSearchViewContainer, false);
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (CustomListView.this.mLayoutId == R.layout.item_add_playlist_card) {
                        Util.A(CustomListView.this.mContext);
                    }
                }
            });
            if (this.mLayoutId == R.layout.item_add_playlist_card) {
                if (Constants.l) {
                    inflate.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_foreground_light));
                } else {
                    inflate.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_background_dark));
                }
            }
            this.mSearchViewContainer.addView(inflate);
        }
        this.mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                ((InputMethodManager) CustomListView.this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(CustomListView.this.mSearchView.findViewById(R.id.search_src_text).getWindowToken(), 0);
                if (!(CustomListView.this.mFragment instanceof ListingFragment)) {
                    return false;
                }
                ((ListingFragment) CustomListView.this.mFragment).c(str);
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (!(CustomListView.this.mFragment instanceof ListingFragment)) {
                    return false;
                }
                ((ListingFragment) CustomListView.this.mFragment).c(str);
                return true;
            }
        });
        this.pullToRefreshlistView = (SwipeRefreshLayout) this.mView.findViewById(R.id.swipe_layout);
        this.mListViewHome = (RecyclerView) this.mView.findViewById(R.id.recycler_view);
        if (this.shouldShowSearchWidget) {
            this.mListViewHome.addOnScrollListener(new OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    CustomListView.this.mTotalScrolled = CustomListView.this.mTotalScrolled + i2;
                }

                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 1) {
                        Util.a(CustomListView.this.mContext, CustomListView.this.mView);
                        CustomListView.this.mListViewHome.requestFocus();
                    }
                    CustomListView.this.sendScrollEvent(i, recyclerView, CustomListView.this.mTotalScrolled, CustomListView.this.lastScrolledPos, CustomListView.this.actualLastPos);
                }
            });
        }
        setPullRefreshListner();
        this.mParentLoading = (ProgressBar) this.mView.findViewById(R.id.llParentLoading);
        this.mListFooterView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        this.mLLHeaderLayout = (LinearLayout) this.mView.findViewById(R.id.header_layout);
        this.mGaanaPlusExpiredLayout = (RelativeLayout) this.mInflater.inflate(R.layout.view_filtered_gaana_plus_renew, null);
        this.mCuratedLayout = (LinearLayout) this.mInflater.inflate(R.layout.curated_download_suggestion_header, null);
        this.mDownloadNowLayout = (LinearLayout) this.mInflater.inflate(R.layout.downloads_songs_header, null);
        this.mDownloadCuratedSongsLayout = (RelativeLayout) this.mInflater.inflate(R.layout.header_download_curated_songs, null);
        this.mDownloadCuratedSongsLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                g.a(CustomListView.this.mContext, CustomListView.this.mFragment, null);
                com.managers.u.a().a("Downloads", "Click", "Download more songs");
            }
        });
        if (this.mListingButton.a()) {
            this.mCuratedLayout.findViewById(R.id.cross_big_curated).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((GaanaActivity) CustomListView.this.mContext).popBackStack();
                }
            });
            this.mCuratedLayout.findViewById(R.id.tick_big_curated).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((GaanaActivity) CustomListView.this.mContext).popBackStack();
                }
            });
            this.mDownloadAllLayout = (LinearLayout) this.mCuratedLayout.findViewById(R.id.ll_download_all);
            this.mDownloadAllLayout.setVisibility(4);
            this.txtDownloadAll = (TextView) this.mCuratedLayout.findViewById(R.id.text_download_all);
            this.imgDownloadAll = (ImageView) this.mCuratedLayout.findViewById(R.id.btn_download_all);
            this.txtDownloadAll.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CustomListView.this.downloadAllCuratedSuggestions(CustomListView.this.getCuratedPlaylist());
                }
            });
            this.imgDownloadAll.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CustomListView.this.downloadAllCuratedSuggestions(CustomListView.this.getCuratedPlaylist());
                }
            });
            com.managers.u.a().a("Curated", "Curated Download Screen", "Shown");
            this.mLLHeaderLayout.removeAllViews();
            this.mLLHeaderLayout.addView(this.mCuratedLayout);
        } else if (this.mListingButton.m()) {
            this.mLLHeaderLayout.removeAllViews();
            this.mLLHeaderLayout.addView(this.mDownloadNowLayout);
            this.mDownloadNowLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    g.a(CustomListView.this.mContext, CustomListView.this.mFragment, null);
                    com.managers.u.a().b("CuratedDownloadsPersonalized", "PopUpView");
                }
            });
        } else {
            this.mLLHeaderLayout.removeAllViews();
            this.mLLHeaderLayout.addView(this.mGaanaPlusExpiredLayout);
        }
        this.mGaanaPlusExpiredLayout.findViewById(R.id.oops_renew_now).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((BaseActivity) CustomListView.this.mContext).sendGAEvent("Download", "Expired notification clicked", "Gaana+ subscription Expired");
                CustomListView.this.mAppState.setSidebarActiveBtn(R.id.upgradeButtonLayout);
                ((GaanaActivity) CustomListView.this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
            }
        });
        ImageView imageView = (ImageView) this.mGaanaPlusExpiredLayout.findViewById(R.id.menu_icon);
        if (Constants.l) {
            imageView.setImageResource(R.drawable.vector_ab_cancel);
        } else {
            imageView.setImageResource(R.drawable.vector_ab_cancel_white);
        }
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomListView.this.mLLHeaderLayout.removeView(CustomListView.this.mGaanaPlusExpiredLayout);
            }
        });
        showHideGaanaPlusExpirationHeader();
        if (this.mListingButton.a()) {
            this.mLLHeaderLayout.setVisibility(0);
        }
        setupUI(this.mView);
    }

    public Playlist getCuratedPlaylist() {
        if (this.mOrignalList == null || (this.mOrignalList != null && this.mOrignalList.size() == 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mOrignalList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Item) {
                arrayList.add(Util.g((Item) next));
            }
        }
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(String.valueOf(-100));
        playlist.setArrListBusinessObj(arrayList);
        return playlist;
    }

    public LinearLayout getCuratedDownloadAllLayout() {
        return this.mDownloadAllLayout;
    }

    public void updateCuratedDownloadAllLayout() {
        if (this.mOrignalList != null && (this.mOrignalList == null || this.mOrignalList.size() != 0)) {
            updateCuratedListHeader(new Playlist());
        }
    }

    public void updateCuratedListHeader(Playlist playlist) {
        if (this.txtDownloadAll != null && this.imgDownloadAll != null && playlist != null) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
            } else if (!Util.j(this.mContext)) {
                ap.a().f(this.mContext);
            }
            this.txtDownloadAll.setText(this.mContext.getString(R.string.download_all_songs));
            updateDownloadBtnImage(this.imgDownloadAll, 44);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateDownloadBtnImage(ImageView imageView, @StyleableRes int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        Drawable drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(i, -1));
        obtainStyledAttributes.recycle();
        imageView.setImageDrawable(drawable);
    }

    public void downloadAllCuratedSuggestions(final Playlist playlist) {
        if (this.txtDownloadAll != null && this.imgDownloadAll != null && playlist != null) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
            } else if (Util.j(this.mContext)) {
                if (ap.a().a((BusinessObject) playlist, null)) {
                    startCuratedListDownload(playlist);
                } else {
                    Util.b(this.mContext, "pl", null, new as() {
                        public void onTrialSuccess() {
                            CustomListView.this.startCuratedListDownload(playlist);
                            ((GaanaActivity) CustomListView.this.mContext).updateSideBar();
                        }
                    });
                }
            } else {
                ap.a().f(this.mContext);
            }
        }
    }

    public void startCuratedListDownload(final Playlist playlist) {
        Util.i(this.mContext, "Download");
        int i = 0;
        boolean b = d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        if (Util.k(GaanaApplication.getContext()) == 0) {
            if (!d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((BaseActivity) CustomListView.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                        ((GaanaActivity) CustomListView.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
                return;
            } else if (b) {
                if (!Constants.V) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                    Constants.V = true;
                }
            } else if (!Constants.W) {
                Constants.W = true;
                aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                    public void onClick(View view) {
                        if ((CustomListView.this.mFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) CustomListView.this.mFragment).a() == 1) {
                            PopupWindowView.getInstance(CustomListView.this.mContext, CustomListView.this.mFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        PopupWindowView.getInstance(CustomListView.this.mContext, CustomListView.this.mFragment).dismiss(true);
                        ((GaanaActivity) CustomListView.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
            }
        }
        if (!ap.a().f() || !ap.a().o()) {
            DownloadManager.c().a((BusinessObject) playlist, getTracksIdsToBeDownloaded(playlist.getArrListBusinessObj()));
        } else if (ap.a().g()) {
            String str = "pl";
            if (playlist.getArrListBusinessObj() != null) {
                Iterator it = playlist.getArrListBusinessObj().iterator();
                while (it.hasNext()) {
                    if (DownloadManager.c().e(Integer.valueOf(((Track) it.next()).getBusinessObjId()).intValue()) == null) {
                        i++;
                    }
                }
            }
            if ((((i + DownloadManager.c().K()) + DownloadManager.c().B()) + DownloadManager.c().r()) + DownloadManager.c().C() > Integer.valueOf(this.mAppState.getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                Util.b(this.mContext, str, "", "", new as() {
                    public void onTrialSuccess() {
                        DownloadManager.c().a(playlist, CustomListView.this.getTracksIdsToBeDownloaded(playlist.getArrListBusinessObj()));
                        CustomListView.this.mFragment.refreshDataandAds();
                        CustomListView.this.mFragment.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) CustomListView.this.mContext).updateSideBar();
                    }
                });
            } else {
                DownloadManager.c().a((BusinessObject) playlist, getTracksIdsToBeDownloaded(playlist.getArrListBusinessObj()));
            }
        } else {
            Util.x(this.mContext);
        }
        updateCuratedListHeader(playlist);
    }

    private ArrayList<String> getTracksIdsToBeDownloaded(ArrayList<BusinessObject> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList e = com.managers.l.a().e();
        Iterator it;
        if (e != null) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                BusinessObject businessObject = (BusinessObject) it.next();
                if (e.contains(businessObject.getBusinessObjId())) {
                    arrayList2.add(businessObject.getBusinessObjId());
                }
            }
        } else {
            it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((BusinessObject) it.next()).getBusinessObjId());
            }
        }
        return arrayList2;
    }

    public void shouldShowSearchWidget(boolean z) {
        this.shouldShowSearchWidget = z;
    }

    public void setShouldShowNoDownloadView(boolean z) {
        this.shouldShowNoDownloadView = z;
    }

    public void setCustomHeaderId(int i) {
        this.mLayoutId = i;
    }

    public void setupUI(View view) {
        if (!(view instanceof SearchView)) {
            view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!(CustomListView.this.mView == null || CustomListView.this.mView.getWindowToken() == null)) {
                        Util.a(CustomListView.this.mContext, CustomListView.this.mView);
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
                    setupUI(viewGroup.getChildAt(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void setSearchHintText(String str) {
        this.searchHintText = str;
    }

    public void setEnableShuffleIcon(boolean z) {
        this.enableShuffleIcon = z;
    }

    private void onScrollChanged(int i) {
        if (!this.shufflePlayLoadMore) {
        }
    }

    private void scrollStateChanged(int i) {
        Button button = (Button) this.mView.findViewById(R.id.shuffle_play_button);
        if (button == null) {
            return;
        }
        if (i == 0 && this.enableShuffleIcon && isShufflePlayButtonEnabled() && getCustomListView() != null) {
            if (!Constants.ab && this.showEmptyView && button.isShown()) {
                button.setVisibility(8);
            } else if (!button.isShown() && !this.showEmptyView) {
                button.setVisibility(0);
            } else if (Constants.ab && !button.isShown() && !this.showEmptyView) {
                button.setVisibility(0);
            }
        } else if (button.getVisibility() == 0) {
            button.setVisibility(8);
        }
    }

    public void setOnAdRefreshListener(p pVar) {
        this.mOnAdRefreshListener = pVar;
    }

    public String getListViewLabel() {
        return this.listViewLabel;
    }

    public void setListViewLabel(String str) {
        this.listViewLabel = str;
    }

    public View getListView() {
        return this.mView;
    }

    public RecyclerView getCustomListView() {
        return this.mListViewHome;
    }

    public boolean isShufflePlayButtonEnabled() {
        boolean z = false;
        if (this.mView == null) {
            return false;
        }
        if (this.mView.findViewById(R.id.shuffle_play_button) != null) {
            z = true;
        }
        return z;
    }

    public ListAdapterSectionIndexer getListAdapter() {
        return this.adapter;
    }

    public void updateSongQueue() {
        if (this.mListingButton != null && this.mListingButton.g() != null && this.mFragment.getUserVisibleHint() && (this.mBusinessView instanceof SongsItemView)) {
            this.mAppState.setCurrentBusObjInListView(this.mListingButton.g());
        }
    }

    /* Access modifiers changed, original: protected */
    public void initItemView(ListingButton listingButton) {
        try {
            this.mBusinessView = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this.mFragment});
            if (this.mBusinessView instanceof SongsItemView) {
                this.showFabButton = true;
            }
        } catch (Exception unused) {
        }
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.mSortOrder = sortOrder;
    }

    public void setSortParams() {
        if (this.mListingButton != null && this.mListingButton.b()) {
            if (this.mSortOrder == SortOrder.TrackName) {
                this.orderColumn = "name";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Popularity) {
                this.orderColumn = "popularity";
                this.orderType = "DESC";
            } else if (this.mSortOrder == SortOrder.ArtistName) {
                this.orderColumn = "artist_names";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Name) {
                this.orderColumn = "name";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Default) {
                this.orderColumn = "added_on";
                this.orderType = "DESC";
            }
        }
    }

    public void setUpdateListView(ListingButton listingButton) {
        this.mListingButton = listingButton;
        setSortParams();
        this.hasDataEnded = Boolean.valueOf(false);
        initItemView(listingButton);
        if (!this.isPullToRefresh.booleanValue()) {
            initView();
            this.mParentLoading.setVisibility(0);
        }
        if (this.isPullToRefresh.booleanValue()) {
            this.isFirstime = false;
            this.oldArrayList.clear();
        }
        if (this.mListingButton.i()) {
            this.pullToRefreshlistView.setEnabled(true);
        } else {
            this.pullToRefreshlistView.setEnabled(false);
        }
        if (listingButton.g() == null || this.isPullToRefresh.booleanValue()) {
            if (!(this.mListingButton == null || this.mListingButton.c() == null || !this.mListingButton.c().e().booleanValue() || listingButton.g() == null)) {
                this.initialUrl = this.mListingButton.c().k();
                this.mListingButton.c().a(this.initialUrl);
            }
            this.pullToRefreshlistView.setEnabled(false);
            if (this.mAppState != null && this.mAppState.getListingComponents() != null && this.mListingButton != null && this.mListingButton.c() != null) {
                BusinessObject a = this.mAppState.getListingComponents().a();
                if (a instanceof Playlist) {
                    Playlist playlist = (Playlist) a;
                    if (PlaylistSyncManager.getInstance().isMyPlaylist(playlist)) {
                        this.mListingButton.c().c(this.isPullToRefresh);
                        ((BaseActivity) this.mContext).getMyPlaylistDetails(this.onBusinessObjectRetrieved, playlist, this.mListingButton.c());
                        return;
                    }
                }
                if (this.mListingButton.c() != null && this.mListingButton.c().j() == UserRecentActivity.class) {
                    aq.a().a(this.mListingButton.c(), this.onBusinessObjectRetrieved);
                } else if (!this.isPullToRefresh.booleanValue() && this.mListingButton.l() && a != null) {
                    ((BaseActivity) this.mContext).getDownloadedBusinessObject(this.onBusinessObjectRetrieved, a.getBusinessObjId(), this.mListingButton.c());
                } else if (isFavouriteContainsFragment() || this.mListingButton.b()) {
                    this.mListingButton.c().c(this.isPullToRefresh);
                    if (this.isPullToRefresh.booleanValue()) {
                        FavouriteSyncManager.getInstance().onPullToRefresh(this.mListingButton.c().i(), new com.services.l.g() {
                            public void favouriteSyncCompleted() {
                                ((BaseActivity) CustomListView.this.mContext).runOnUiThread(new Runnable() {
                                    public void run() {
                                        CustomListView.this.initialUrl = CustomListView.this.mListingButton.c().k();
                                        if (CustomListView.this.mListingButton.n() != null) {
                                            CustomListView.this.mListingButton.n().a();
                                            CustomListView.this.mListingButton.n().a(CustomListView.this.mListingButton.c(), CustomListView.this.searchParam, 0, 20, CustomListView.this.orderColumn, CustomListView.this.orderType, CustomListView.this.onBusinessObjectRetrieved);
                                            return;
                                        }
                                        i.a().b(CustomListView.this.onBusinessObjectRetrieved, CustomListView.this.mListingButton.c(), CustomListView.this.searchParam, 0, 20, CustomListView.this.orderColumn, CustomListView.this.orderType);
                                    }
                                });
                            }
                        });
                    } else {
                        FavouriteSyncManager.getInstance().performSync(new com.services.l.g() {
                            public void favouriteSyncCompleted() {
                                ((BaseActivity) CustomListView.this.mContext).runOnUiThread(new Runnable() {
                                    public void run() {
                                        CustomListView.this.initialUrl = CustomListView.this.mListingButton.c().k();
                                        if (CustomListView.this.mListingButton.n() != null) {
                                            CustomListView.this.mListingButton.n().a();
                                            CustomListView.this.mListingButton.n().a(CustomListView.this.mListingButton.c(), CustomListView.this.searchParam, 0, 20, CustomListView.this.orderColumn, CustomListView.this.orderType, CustomListView.this.onBusinessObjectRetrieved);
                                            return;
                                        }
                                        i.a().b(CustomListView.this.onBusinessObjectRetrieved, CustomListView.this.mListingButton.c(), CustomListView.this.searchParam, 0, 20, CustomListView.this.orderColumn, CustomListView.this.orderType);
                                    }
                                });
                            }
                        });
                    }
                } else {
                    i.a().a(this.onBusinessObjectRetrieved, this.mListingButton.c(), Boolean.valueOf(false));
                }
            }
        } else if (listingButton.g() == null || listingButton.g().size() <= 0) {
            showHideEmtpyViewLayout(true);
            this.hasDataEnded = Boolean.valueOf(true);
        } else {
            populateListView(listingButton.g(), this.mBusinessView);
            if ((this.mFragment instanceof ListingFragment) && ((ListingFragment) this.mFragment).e() != null && (((ListingFragment) this.mFragment).e() instanceof DownloadDetailsFragment) && listingButton.g().size() > 0) {
                showSearchView();
                showDownloadedSongsEmptyView(false);
                if (DownloadManager.c().p() <= 10) {
                    showDownloadCuratedSongsLayout();
                } else {
                    hideDownloadCuratedSongsLayout();
                }
            }
        }
    }

    public void sortList(SortOrder sortOrder, boolean z) {
        SortOrder sortOrder2 = sortOrder;
        boolean z2 = false;
        if (z && this.adapter != null && this.mListingButton != null) {
            this.mSortOrder = sortOrder2;
            sortIfRequired(this.mListingButton.g(), this.mSortOrder);
            FastScrollRecyclerView fastScrollRecyclerView = (FastScrollRecyclerView) this.mListViewHome;
            if (this.mSortOrder != null && this.mSortOrder == SortOrder.TrackName) {
                z2 = true;
            }
            fastScrollRecyclerView.showHidePopup(z2);
            this.adapter.setAdapterArrayList(this.mListingButton.g());
        } else if (!z && sortOrder2 != this.mSortOrder) {
            this.mSortOrder = sortOrder2;
            if (this.mSortOrder == SortOrder.TrackName) {
                this.orderColumn = "name";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Popularity) {
                this.orderColumn = "popularity";
                this.orderType = "DESC";
            } else if (this.mSortOrder == SortOrder.ArtistName) {
                this.orderColumn = "artist_names";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Name) {
                this.orderColumn = "name";
                this.orderType = "ASC";
            } else if (this.mSortOrder == SortOrder.Default) {
                this.orderColumn = "added_on";
                this.orderType = "DESC";
            }
            this.hasDataEnded = Boolean.valueOf(false);
            if (this.adapter != null) {
                this.adapter.clearAdapter();
            }
            this.mParentLoading.setVisibility(0);
            if (this.mListingButton.b()) {
                this.initialUrl = this.mListingButton.c().k();
                if (this.mListingButton.n() != null) {
                    this.mListingButton.n().a();
                    this.mListingButton.n().a(this.mListingButton.c(), this.searchParam, 0, 20, this.orderColumn, this.orderType, this.onBusinessObjectRetrieved);
                    return;
                }
                i.a().b(this.onBusinessObjectRetrieved, this.mListingButton.c(), this.searchParam, 0, 20, this.orderColumn, this.orderType);
                return;
            }
            i.a().a(this.onBusinessObjectRetrieved, this.mListingButton.c(), Boolean.valueOf(false));
        }
    }

    private void sendScrollEvent(int i, RecyclerView recyclerView, int i2, int i3, int i4) {
        if (i != 0) {
            return;
        }
        if (((this.mFragment.getParentFragment() instanceof FavoritesFragment) || (this.mFragment.getParentFragment() instanceof DownloadDetailsFragment)) && i2 > i3) {
            i = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
            this.itemCount = recyclerView.getAdapter().getItemCount();
            an.a().c("scroll", AvidJSONUtil.KEY_Y, "", "", "", "", String.valueOf(this.itemCount), String.valueOf(i));
        }
    }

    private String removeLimitFromUrl(String str) {
        String str2 = null;
        for (String str3 : str.split("&")) {
            String str4 = str3.split("=")[0];
            if (str4.compareToIgnoreCase("limit") == 0) {
                str2 = str3.split("=")[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&");
                stringBuilder.append(str4);
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = str.replace(stringBuilder.toString(), "").trim();
            }
        }
        return str2 == null ? str : str2;
    }

    private String removeOrderingFromUrl(String str) {
        String str2 = null;
        for (String str3 : str.split("&")) {
            String str4 = str3.split("=")[0];
            if (str4.compareToIgnoreCase("order") == 0) {
                str2 = str3.split("=")[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&");
                stringBuilder.append(str4);
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = str.replace(stringBuilder.toString(), "").trim();
            }
        }
        return str2 == null ? str : str2;
    }

    private String getNewUrl(String str, int i, int i2, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            str = removeTokenFromUrl(str);
        }
        StringBuilder stringBuilder;
        if (obj instanceof UserActivity) {
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

    private String removeTokenFromUrl(String str) {
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

    public void setOnDataLoadedListener(OnDataLoadedListener onDataLoadedListener) {
        this.mDataLoadedListener = onDataLoadedListener;
    }

    public void setOnDataRefreshListener(OnDataRefreshListener onDataRefreshListener) {
        this.mDataRefreshListener = onDataRefreshListener;
    }

    public ListingButton getListingButton() {
        return this.mListingButton;
    }

    /* Access modifiers changed, original: protected */
    public void sortIfRequired(ArrayList<Object> arrayList, SortOrder sortOrder) {
        if (!((((GaanaActivity) this.mContext).getCurrentFragment() instanceof DownloadDetailsFragment) || isFavouriteContainsFragment())) {
            if (!(this.mFragment instanceof ListingFragment)) {
                return;
            }
            if (!((((ListingFragment) this.mFragment).e() instanceof MyMusicFragment) || (((ListingFragment) this.mFragment).e() instanceof MyMusicItemFragment))) {
                return;
            }
        }
        if (sortOrder != null && arrayList != null && arrayList.size() > 1) {
            if (sortOrder == SortOrder.TrackName) {
                Collections.sort(arrayList, new Comparator<Object>() {
                    public int compare(Object obj, Object obj2) {
                        if ((obj instanceof OfflineTrack) && (obj2 instanceof OfflineTrack)) {
                            return ((OfflineTrack) obj).getName().toLowerCase().compareTo(((OfflineTrack) obj2).getName().toLowerCase());
                        }
                        if ((obj instanceof Track) && (obj2 instanceof Track)) {
                            return ((Track) obj).getName().toLowerCase().compareTo(((Track) obj2).getName().toLowerCase());
                        }
                        if ((obj instanceof Album) && (obj2 instanceof Album)) {
                            return ((Album) obj).getName().toLowerCase().compareTo(((Album) obj2).getName().toLowerCase());
                        }
                        if ((obj instanceof Playlist) && (obj2 instanceof Playlist)) {
                            return ((Playlist) obj).getName().toLowerCase().compareTo(((Playlist) obj2).getName().toLowerCase());
                        }
                        if ((obj instanceof Radio) && (obj2 instanceof Radio)) {
                            return ((Radio) obj).getName().toLowerCase().compareTo(((Radio) obj2).getName().toLowerCase());
                        }
                        return ((obj instanceof Artist) && (obj2 instanceof Artist)) ? ((Artist) obj).getName().toLowerCase().compareTo(((Artist) obj2).getName().toLowerCase()) : 0;
                    }
                });
            } else if (sortOrder == SortOrder.ArtistName) {
                Collections.sort(arrayList, new Comparator<Object>() {
                    public int compare(Object obj, Object obj2) {
                        if ((obj instanceof OfflineTrack) && (obj2 instanceof OfflineTrack)) {
                            OfflineTrack offlineTrack = (OfflineTrack) obj;
                            if (TextUtils.isEmpty(offlineTrack.getArtistName()) && TextUtils.isEmpty(((OfflineTrack) obj2).getArtistName())) {
                                return 0;
                            }
                            if (TextUtils.isEmpty(offlineTrack.getArtistName())) {
                                return 1;
                            }
                            OfflineTrack offlineTrack2 = (OfflineTrack) obj2;
                            if (TextUtils.isEmpty(offlineTrack2.getArtistName())) {
                                return -1;
                            }
                            return offlineTrack.getArtistName().toLowerCase().compareTo(offlineTrack2.getArtistName().toLowerCase());
                        } else if (!(obj instanceof Track) || !(obj2 instanceof Track)) {
                            return 0;
                        } else {
                            Track track = (Track) obj;
                            if (TextUtils.isEmpty(track.getArtistNames()) && TextUtils.isEmpty(((Track) obj2).getArtistNames())) {
                                return 0;
                            }
                            if (TextUtils.isEmpty(track.getArtistNames())) {
                                return 1;
                            }
                            Track track2 = (Track) obj2;
                            if (TextUtils.isEmpty(track2.getArtistNames())) {
                                return -1;
                            }
                            return track.getArtistNames().compareTo(track2.getArtistNames());
                        }
                    }
                });
            } else if (sortOrder == SortOrder.DownloadTime) {
                Collections.sort(arrayList, new Comparator<Object>() {
                    public int compare(Object obj, Object obj2) {
                        if ((obj instanceof OfflineTrack) && (obj2 instanceof OfflineTrack)) {
                            OfflineTrack offlineTrack = (OfflineTrack) obj2;
                            OfflineTrack offlineTrack2 = (OfflineTrack) obj;
                            if (offlineTrack.getDownloadTime() > offlineTrack2.getDownloadTime()) {
                                return 1;
                            }
                            return offlineTrack.getDownloadTime() < offlineTrack2.getDownloadTime() ? -1 : 0;
                        } else if ((obj instanceof Playlist) && (obj2 instanceof Playlist)) {
                            Playlist playlist = (Playlist) obj2;
                            Playlist playlist2 = (Playlist) obj;
                            if (playlist.getDownloadTime() > playlist2.getDownloadTime()) {
                                return 1;
                            }
                            return playlist.getDownloadTime() < playlist2.getDownloadTime() ? -1 : 0;
                        } else if (!(obj instanceof Album) || !(obj2 instanceof Album)) {
                            return 0;
                        } else {
                            Album album = (Album) obj2;
                            Album album2 = (Album) obj;
                            if (album.getDownloadTime() > album2.getDownloadTime()) {
                                return 1;
                            }
                            return album.getDownloadTime() < album2.getDownloadTime() ? -1 : 0;
                        }
                    }
                });
            } else if (sortOrder == SortOrder.Popularity) {
                Collections.sort(arrayList, new Comparator<Object>() {
                    public int compare(Object obj, Object obj2) {
                        return ((obj instanceof Track) && (obj2 instanceof Track)) ? (int) (((Track) obj).getPopularity() - ((Track) obj2).getPopularity()) : 0;
                    }
                });
            } else if (sortOrder == SortOrder.Default) {
                arrayList.clear();
                arrayList.addAll(this.mOrignalList);
            }
        }
    }

    private void startSponsorAd() {
        if (ap.a().c(this.mContext) && this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserSubscriptionData() != null && this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() == 2 && ColombiaManager.b().e()) {
            ColombiaManager.b().d();
            initiateColombiaSponsorAds();
        }
    }

    public void setUserVisibleHint(boolean z) {
        if (z && this.showFabButton && this.mListingButton != null && this.mListingButton.g() != null && this.mListingButton.g().size() > 0) {
            this.mAppState.setCurrentBusObjInListView(this.mListingButton.g());
        }
    }

    private void initiateColombiaSponsorAds() {
        final LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.llNativeAdSlot);
        ColombiaManager.b().a(1, this.mContext, 26, -1, linearLayout, "Download_details_fragment", new a() {
            public void onItemRequestFailed(Exception exception) {
            }

            public void onItemLoaded(com.til.colombia.android.service.Item item) {
                com.managers.u.a().a("Trial_Sponsership", "Trial_Download", "Trial_Download_Counter");
                linearLayout.setVisibility(0);
            }
        }, "");
    }

    private void resetAdSpots() {
        if (this.trendingArray != null) {
            showRepetativeAds(this.showRepetativeAds);
            clearAdSpots();
            setRepetativeAdSpots();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void populateListView(final ArrayList<?> arrayList, final BaseItemView baseItemView) {
        if (arrayList.size() > 0 && matchModelView(arrayList.get(0), baseItemView)) {
            sortIfRequired(arrayList, this.mSortOrder);
            if (this.mParentLoading != null) {
                this.mParentLoading.setVisibility(8);
            }
            if (this.isPullToRefresh.booleanValue()) {
                this.pullToRefreshlistView.setRefreshing(false);
                this.isPullToRefresh = Boolean.valueOf(false);
            }
            this.mListingButton.a((ArrayList) arrayList);
            if (this.adapter == null) {
                this.adapter = new ListAdapterSectionIndexer(this.mContext, this.mFragment);
                this.adapter.setPullToRefresh(true);
            }
            if (this.showFabButton) {
                if (this.mFragment.getUserVisibleHint()) {
                    this.mAppState.setCurrentBusObjInListView(arrayList);
                }
                if (!(this.mListingButton.c() == null || this.mListingButton.c().n() || this.mView.findViewById(R.id.llNativeAdSlot) == null)) {
                    startSponsorAd();
                }
                if (!this.enableShuffleIcon) {
                    this.mView.findViewById(R.id.shuffle_play_button).setVisibility(8);
                }
                this.mView.findViewById(R.id.shuffle_play_button).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        an.a().a("click", "ac", "", "Shuffle Play", "", "fav", "", "");
                        if ((CustomListView.this.mFragment instanceof ListingFragment) && (((ListingFragment) CustomListView.this.mFragment).getParentFragment() instanceof RevampedDetailListing)) {
                            ((RevampedDetailListing) ((ListingFragment) CustomListView.this.mFragment).getParentFragment()).a("Shuffle Play", false);
                        }
                        if (CustomListView.this.mListingButton.g().size() >= 200 || (CustomListView.this.mFragment.getParentFragment() instanceof DownloadDetailsFragment) || CustomListView.this.hasDataEnded.booleanValue() || !((CustomListView.this.mFragment.getParentFragment() instanceof RevampedDetailListing) || (CustomListView.this.mFragment.getParentFragment() instanceof FavoritesFragment) || ((CustomListView.this.mFragment.getParentFragment() instanceof MyMusicItemFragment) && ((MyMusicItemFragment) CustomListView.this.mFragment.getParentFragment()).c().getCurrentItem() != 2))) {
                            CustomListView.this.shuffleSongs();
                            return;
                        }
                        if (d.a().b("PREFERENCE_SHUFFLE_DIALOG", true, false)) {
                            new ShuffleBottomView(CustomListView.this.mContext, CustomListView.this.mListingButton).show();
                        }
                        CustomListView.this.shuffleMoreSongs = true;
                        CustomListView.this.loadMoreData(CustomListView.this.mListingButton.g().size(), null);
                    }
                });
            }
            if (this.mListingButton.c() != null && this.mListingButton.c().e().booleanValue()) {
                this.adapter.setLoadMoreListner(this);
            }
            if (this.mFragment instanceof ListingFragment) {
                CharSequence d = ((ListingFragment) this.mFragment).d();
                if ((this.mFragment.getParentFragment() instanceof RevampedDetailListing) && TextUtils.isEmpty(d) && com.managers.e.j != 0) {
                    d = String.valueOf(com.managers.e.j);
                }
                if (!TextUtils.isEmpty(d)) {
                    this.adapter.setAdEnabled(true);
                }
            }
            if (arrayList.size() > 0) {
                showHideEmtpyViewLayout(false);
            }
            if (baseItemView instanceof NotificationItemView) {
                ((NotificationItemView) baseItemView).setNotificationListener(new com.services.l.i() {
                    public void onRCVItemChanged(int i) {
                        if (CustomListView.this.getListAdapter() != null) {
                            CustomListView.this.getListAdapter().notifyItemChanged(i);
                        }
                    }
                });
            }
            if (!(baseItemView instanceof ByMePlaylistItemView) || this.mListingButton.g().size() <= 0) {
                this.adapter.setAutomatedPlayList(null, false);
            } else {
                this.adapter.setAutomatedPlayList(new AutomatedPlaylistItemView(this.mContext, this.mFragment), true);
            }
            if ((baseItemView instanceof DownloadSongListingView) && this.is_trendinglist_onlocal_songs) {
                this.adapter.setTrendingView(this.trendingArray, this.localFileSongsRecyclerView, this.is_trendinglist_onlocal_songs);
            } else {
                this.adapter.setTrendingView(null, this.localFileSongsRecyclerView, this.is_trendinglist_onlocal_songs);
            }
            this.adapter.setParamaters(arrayList, new IAddListItemView() {
                public View addListItemView(Object obj, final ViewHolder viewHolder, ViewGroup viewGroup) {
                    View view;
                    baseItemView.setSearchQuery(CustomListView.this.searchParam);
                    if (viewHolder instanceof ItemAdViewHolder) {
                        if (obj instanceof Header) {
                            ((TextView) viewHolder.itemView.findViewById(R.id.headerview)).setText(((Header) obj).getName());
                            return viewHolder.itemView;
                        }
                        view = viewHolder.itemView;
                    } else if (obj instanceof PlayerTrack) {
                        view = baseItemView.getPoplatedView(viewHolder, ((PlayerTrack) obj).b(), viewGroup);
                    } else if (obj instanceof AutoComplete) {
                        BusinessObject businessObject = (AutoComplete) obj;
                        businessObject.setPosition(arrayList.indexOf(obj));
                        if (TextUtils.isEmpty(businessObject.getType())) {
                            businessObject.setType(((AutoComplete) arrayList.get(0)).getType());
                        }
                        view = baseItemView instanceof RadioButtonGenericView ? baseItemView.getPoplatedView(viewHolder, businessObject, viewGroup, CustomListView.this.mListingButton.h()) : baseItemView.getPoplatedView(viewHolder, businessObject, viewGroup);
                    } else if (baseItemView instanceof RadioButtonGenericView) {
                        view = baseItemView.getPoplatedView(viewHolder, (BusinessObject) obj, viewGroup, CustomListView.this.mListingButton.h());
                    } else if (!(obj instanceof BusinessObject)) {
                        return new View(CustomListView.this.mContext);
                    } else {
                        view = baseItemView.getPoplatedView(viewHolder, (BusinessObject) obj, viewGroup);
                        if (baseItemView instanceof EditPlaylistSelectSongView) {
                            view.findViewById(R.id.img_edit_holder).setOnTouchListener(new OnTouchListener() {
                                public boolean onTouch(View view, MotionEvent motionEvent) {
                                    if (CustomListView.this.mItemTouchHelper != null) {
                                        CustomListView.this.mItemTouchHelper.startDrag(viewHolder);
                                    }
                                    return false;
                                }
                            });
                        }
                    }
                    if (!al.a && (((baseItemView instanceof DownloadSongListingView) || (baseItemView instanceof DownloadSongsItemView)) && !(CustomListView.this.mFragment.getParentFragment() instanceof RevampedDetailListing))) {
                        view.setOnLongClickListener(new OnLongClickListener() {
                            public boolean onLongClick(View view) {
                                CustomListView.this.adapter.notifyDataSetChanged();
                                ((ListingFragment) CustomListView.this.mFragment).a(view, CustomListView.this.adapter.getCount());
                                return true;
                            }
                        });
                    }
                    return view;
                }

                public void showHideEmtpyView(boolean z) {
                    CustomListView.this.showHideEmtpyViewLayout(z);
                }

                public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                    View createViewHolder = baseItemView.createViewHolder(viewGroup, i);
                    if (baseItemView instanceof DownloadSongListingView) {
                        return new DownloadSongListingHolder(createViewHolder, true);
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
                        return new FavoriteOccasionItemHolder(createViewHolder);
                    }
                    if (baseItemView instanceof DownloadSongsItemView) {
                        AlbumDetailItemHolder albumDetailItemHolder = new AlbumDetailItemHolder(createViewHolder);
                        if (CustomListView.this.mListingButton.a()) {
                            albumDetailItemHolder.isFromCuratedDialog = true;
                        }
                        return albumDetailItemHolder;
                    } else if (baseItemView instanceof RadioButtonSongView) {
                        return new RadioButtonSongHolder(createViewHolder);
                    } else {
                        if (baseItemView instanceof NotificationItemView) {
                            ViewHolder notificationItemHolder;
                            if (i == 1) {
                                notificationItemHolder = new NotificationItemHolder(createViewHolder);
                            } else if (i == 6) {
                                notificationItemHolder = new DownloadNotifHolder(createViewHolder);
                            } else {
                                notificationItemHolder = new OffersViewHolder(createViewHolder);
                            }
                            return notificationItemHolder;
                        } else if (baseItemView instanceof EditPlaylistSelectSongView) {
                            return new EditPlaylistSelectSongViewHolder(createViewHolder);
                        } else {
                            if (baseItemView instanceof MyActivityInfoItemView) {
                                return new MyActivityInfoListingHolder(createViewHolder);
                            }
                            if (baseItemView instanceof ActivityItemView) {
                                return new ActivityListHolder(createViewHolder);
                            }
                            if (baseItemView instanceof SearchItemView) {
                                return new SearchItemHolder(createViewHolder);
                            }
                            if (baseItemView instanceof RadioButtonGenericView) {
                                return new RadioSearchItemHolder(createViewHolder);
                            }
                            if (baseItemView instanceof RadioButtonPlaylistView) {
                                return new RadioButtonPlaylistViewHolder(createViewHolder);
                            }
                            if (baseItemView instanceof AddToPlaylistItemView) {
                                return new AddToPlaylistItemViewHolder(createViewHolder);
                            }
                            return baseItemView instanceof NewGenericItemView ? new NewGenericItemHolder(createViewHolder) : null;
                        }
                    }
                }

                public int getItemViewType(int i) {
                    if ((baseItemView instanceof NotificationItemView) && i == 0 && CustomListView.this.showOffers.booleanValue()) {
                        return 2;
                    }
                    return ((baseItemView instanceof NotificationItemView) && arrayList.get(i) != null && (arrayList.get(i) instanceof Notification) && ((Notification) arrayList.get(i)).getType() != null && ((Notification) arrayList.get(i)).getType().equalsIgnoreCase("offline_play_notif")) ? 6 : 1;
                }
            });
            resetAdSpots();
            this.mListViewHome.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.mListViewHome.setItemAnimator(new DefaultItemAnimator());
            if (this.mShowScrollHeader) {
                this.adapter.setHeaderView(this.mHeaderViewScroll);
            }
            this.mListViewHome.setAdapter(this.adapter);
            if (baseItemView instanceof EditPlaylistSelectSongView) {
                if (this.mSwipeCallback == null) {
                    this.mSwipeCallback = new e(this.adapter);
                }
                if (this.mItemTouchHelper == null) {
                    this.mItemTouchHelper = new ItemTouchHelper(this.mSwipeCallback);
                }
                this.adapter.setItemType(4);
                this.mSwipeCallback.b(false);
                this.mItemTouchHelper.attachToRecyclerView(this.mListViewHome);
                this.adapter.setOnNotificationsCleared(this);
            }
        }
    }

    public void setRepetativeAdSpots() {
        if (this.showRepetativeAds && getListAdapter() != null) {
            insertAdSpot();
            getListAdapter().setRepetativeAdSpots(this.showRepetativeAds);
            getListAdapter().setAdSpots(this.adSpots);
        }
    }

    private void shuffleSongs() {
        ArrayList arrayList;
        if (this.mBusinessObject == null || this.mBusinessObject.isLocalMedia()) {
            if (this.mBusinessObject != null) {
                af.a(this.mContext, this.mFragment).a((int) R.id.shuffleMenu, this.mBusinessObject);
            } else if (this.mListingButton != null && this.mListingButton.g() != null && this.mListingButton.g().size() > 0) {
                arrayList = new ArrayList(this.mListingButton.g());
                Collections.shuffle(arrayList);
                PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList, 0), this.mContext);
            }
        } else if (this.mListingButton != null && this.mListingButton.g() != null && this.mListingButton.g().size() > 0) {
            arrayList = new ArrayList(this.mListingButton.g());
            Collections.shuffle(arrayList);
            PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList, 0), this.mContext);
        }
    }

    public void clearAdSpots() {
        this.adSpots.clear();
        if (getListAdapter() != null) {
            getListAdapter().setAdSpots(this.adSpots);
        }
    }

    public void showRepetativeAds(boolean z) {
        if (ap.a().b(this.mContext) && GaanaApplication.getInstance().getColombiaSdkInit()) {
            this.showRepetativeAds = z;
        }
    }

    public void insertAdSpot() {
        if (this.adapter != null && this.adapter.getAdapterArrayList().size() >= this.REPETATIVE_AD_INTERVAL + 1) {
            boolean isAutoPlaylist = this.adapter.isAutoPlaylist();
            if (this.adSpots.size() == 0 && this.adapter.getAdapterArrayList().size() > 0) {
                if (!this.adSpots.contains(Integer.valueOf(this.REPETATIVE_AD_INTERVAL))) {
                    this.adSpots.add(Integer.valueOf(this.REPETATIVE_AD_INTERVAL));
                }
                insertAdSpot();
            } else if (((Integer) this.adSpots.get(this.adSpots.size() - 1)).intValue() + (this.REPETATIVE_AD_INTERVAL + 1) < (this.adapter.getAdapterArrayList().size() + this.adSpots.size()) + isAutoPlaylist) {
                int intValue = ((Integer) this.adSpots.get(this.adSpots.size() - 1)).intValue() + (this.REPETATIVE_AD_INTERVAL + 1);
                if (!this.adSpots.contains(Integer.valueOf(intValue))) {
                    this.adSpots.add(Integer.valueOf(intValue));
                }
                insertAdSpot();
            }
        }
    }

    public void showScrollHeader(boolean z) {
        this.mShowScrollHeader = z;
        int dimensionPixelSize = (int) (((float) this.mContext.getResources().getDimensionPixelSize(R.dimen.header_height)) - this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin));
        this.mHeaderViewScroll = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null, false);
        this.mHeaderViewScroll.setLayoutParams(new LayoutParams(-1, dimensionPixelSize - getActionBarSize()));
    }

    private boolean matchModelView(Object obj, BaseItemView baseItemView) {
        if ((obj instanceof Track) && !(baseItemView instanceof SongsItemView) && !(baseItemView instanceof RadioButtonSongView) && !(baseItemView instanceof EditPlaylistSelectSongView) && !(baseItemView instanceof RadioButtonGenericView)) {
            return false;
        }
        if ((obj instanceof Album) && !(baseItemView instanceof AlbumItemView)) {
            return false;
        }
        if ((obj instanceof Playlist) && !(baseItemView instanceof PlaylistItemView) && !(baseItemView instanceof RadioButtonPlaylistView) && !(baseItemView instanceof AddToPlaylistItemView)) {
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

    public void onLoadMoreStarted() {
        this.shufflePlayLoadMore = true;
        if (!this.mIsLoadMoreProgressBarVisible) {
            this.mIsLoadMoreProgressBarVisible = true;
            this.llLoadingView.addView(this.mListFooterView);
            this.mListFooterView.setVisibility(0);
        }
    }

    public void onLoadMoreComplete() {
        if (this.mIsLoadMoreProgressBarVisible) {
            this.mIsLoadMoreProgressBarVisible = false;
            this.llLoadingView.removeAllViews();
            this.mListFooterView.setVisibility(8);
        }
    }

    public void loadMoreData(int i, Object obj) {
        if (!(((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !this.mListingButton.b()) || this.hasLoadingStarted || this.hasDataEnded.booleanValue())) {
            this.hasLoadingStarted = true;
            if (!this.shuffleMoreSongs) {
                onLoadMoreStarted();
            }
            if (!this.mListingButton.b()) {
                if (this.shuffleMoreSongs) {
                    this.mListingButton.c().a(getNewUrl(this.mListingButton.c().k(), i, 200, obj));
                } else {
                    this.mListingButton.c().a(getNewUrl(this.mListingButton.c().k(), i, 20, obj));
                }
                i.a().a(this.onLoadMoreDataFinished, this.mListingButton.c());
            } else if (this.mListingButton.n() == null) {
                i.a().b(this.onLoadMoreDataFinished, this.mListingButton.c(), this.searchParam, i, 20, this.orderColumn, this.orderType);
            } else if (this.shuffleMoreSongs) {
                this.mListingButton.n().a(this.mListingButton.c(), this.searchParam, i, 200, this.orderColumn, this.orderType, this.onLoadMoreDataFinished);
            } else {
                this.mListingButton.n().a(this.mListingButton.c(), this.searchParam, i, 20, this.orderColumn, this.orderType, this.onLoadMoreDataFinished);
            }
        }
    }

    private void setPullRefreshListner() {
        this.pullToRefreshlistView.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                CustomListView.this.refreshList();
            }
        });
    }

    public void refreshList() {
        if (this.mListingButton != null && this.mListingButton.i()) {
            if (this.mOnAdRefreshListener != null) {
                this.mOnAdRefreshListener.onAdRefresh();
            }
            this.isPullToRefresh = Boolean.valueOf(true);
            if (!this.fetchDataFromDb) {
                this.mListingButton.c().c(Boolean.valueOf(true));
            }
            setUpdateListView(this.mListingButton);
            if (this.adapter != null) {
                this.adapter.setPullToRefresh(this.isPullToRefresh.booleanValue());
                this.adapter.onRefresh(true);
            }
        }
    }

    private void addFooter(BusinessObject businessObject) {
        new LinearLayout(this.mContext).setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.mListFooterView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        this.mListFooterView.setVisibility(0);
        this.mListFooterView.setBackgroundColor(this.mContext.getResources().getColor(R.color.gray));
    }

    public void setBusinesObject(BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
    }

    public BusinessObject getmBusinessObject() {
        return this.mBusinessObject;
    }

    public void showNoContentScreen(boolean z) {
        TextView textView;
        if (z) {
            textView = (TextView) this.mView.findViewById(R.id.emptyMsgView);
            if (this.mFragment != null && this.mFragment.getParentFragment() != null && (this.mFragment.getParentFragment() instanceof DownloadDetailsFragment) && textView != null) {
                textView.setVisibility(0);
                return;
            }
            return;
        }
        textView = (TextView) this.mView.findViewById(R.id.emptyMsgView);
        if (this.mFragment != null && this.mFragment.getParentFragment() != null && (this.mFragment.getParentFragment() instanceof DownloadDetailsFragment) && textView != null) {
            textView.setVisibility(8);
        }
    }

    public void showHideEmtpyViewLayout(boolean z) {
        this.showEmptyView = z;
        TextView textView = (TextView) this.mView.findViewById(R.id.emptyMsgView);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.followMsgView);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.followMsgImageView);
        if (this.mParentLoading != null) {
            this.mParentLoading.setVisibility(8);
        }
        if ((this.mFragment instanceof ItemListingFragment) || (this.mFragment instanceof ProfileFragment) || (this.mFragment.getParentFragment() != null && ((this.mFragment.getParentFragment() instanceof DownloadDetailsFragment) || (this.mFragment.getParentFragment() instanceof MyMusicSearchResultFragment)))) {
            textView.setVisibility(8);
            if (z && (this.mFragment.getParentFragment() == null || !((this.mFragment.getParentFragment() instanceof DownloadDetailsFragment) || (this.mFragment.getParentFragment() instanceof MyMusicSearchResultFragment)))) {
                populateEmptyMsgInListView();
            }
            if (z && this.mFragment.getParentFragment() != null && (this.mFragment.getParentFragment() instanceof DownloadDetailsFragment)) {
                if (this.shouldShowNoDownloadView) {
                    if (this.mView.findViewById(R.id.no_downloads_here) != null) {
                        this.mView.findViewById(R.id.no_downloads_here).setVisibility(8);
                    }
                    if (TextUtils.isEmpty(this.searchParam)) {
                        hideSearchView();
                    } else {
                        textView.setVisibility(0);
                    }
                } else if (this.mView.findViewById(R.id.no_downloads_here) != null) {
                    this.mView.findViewById(R.id.no_downloads_here).setVisibility(0);
                    setNoDownloadsText();
                }
                if (TextUtils.isEmpty(this.searchParam)) {
                    refreshList();
                }
            } else if (!z || this.mFragment.getParentFragment() == null || !(this.mFragment.getParentFragment() instanceof MyMusicSearchResultFragment)) {
                if (!(imageView == null || textView2 == null)) {
                    imageView.setVisibility(8);
                    textView2.setVisibility(8);
                }
                if (this.mView.findViewById(R.id.no_downloads_here) != null) {
                    this.mView.findViewById(R.id.no_downloads_here).setVisibility(8);
                }
            } else if (this.mView.findViewById(R.id.no_downloads_here) != null) {
                this.mView.findViewById(R.id.no_downloads_here).setVisibility(0);
                setNoSearchUI();
            }
        } else if (z) {
            if (this.mFragment instanceof AddToPlaylistFragment) {
                textView.setText(R.string.no_existing_playlist_create_playlist_first);
            }
            if (this.adapter == null && !(this.mFragment.getParentFragment() instanceof RevampedDetailListing)) {
                textView.setVisibility(8);
                populateEmptyMsgInListView();
            } else if (textView.getVisibility() != 0) {
                if (!(this.mListViewHome == null || this.adapter == null)) {
                    this.adapter.setAdapterArrayList(new ArrayList());
                    this.adapter.notifyDataSetChanged();
                }
                textView.setVisibility(0);
            }
        } else {
            textView.setVisibility(8);
        }
    }

    public void showDownloadedSongsEmptyView(boolean z) {
        if (this.mView != null) {
            LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.empty_downloaded_view);
            if (linearLayout == null) {
                return;
            }
            if (z && DownloadManager.c().p() == 0) {
                linearLayout.setVisibility(0);
                Util.a((TextView) this.mView.findViewById(R.id.tap_to_download_songs), this.mContext);
                TextView textView = (TextView) this.mView.findViewById(R.id.no_downloaded_songs);
                textView.setTypeface(textView.getTypeface(), 1);
                if (TextUtils.isEmpty(this.searchParam)) {
                    hideSearchView();
                    return;
                }
                return;
            }
            linearLayout.setVisibility(8);
        }
    }

    private void populateEmptyMsgInListView() {
        final BaseItemView baseItemView = new BaseItemView(this.mContext, this.mFragment);
        ArrayList arrayList = new ArrayList();
        final UserMessage userMessage = new UserMessage();
        userMessage.setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
        arrayList.add(userMessage);
        if (this.isPullToRefresh.booleanValue()) {
            this.pullToRefreshlistView.setRefreshing(false);
            this.isPullToRefresh = Boolean.valueOf(false);
        }
        this.mParentLoading.setVisibility(8);
        if (this.adapter != null) {
            this.adapter.clearAdapter();
            this.adapter = null;
        }
        this.adapter = new ListAdapterSectionIndexer(this.mContext, this.mFragment);
        this.adapter.setPullToRefresh(true);
        this.adapter.setParamaters(arrayList, new IAddListItemView() {
            public int getItemViewType(int i) {
                return 0;
            }

            public void showHideEmtpyView(boolean z) {
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                CustomListView.this.SocialEmptyView(viewHolder.itemView);
                return viewHolder.itemView;
            }

            public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                return new EmptyMessageHolder(baseItemView.getEmptyMsgView(userMessage, viewGroup));
            }
        });
        this.mListViewHome.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mListViewHome.setItemAnimator(new DefaultItemAnimator());
        this.mListViewHome.setAdapter(this.adapter);
    }

    public void notificationsCleared() {
        this.mView.findViewById(R.id.emptyMsgView).setVisibility(0);
    }

    public void onCheckedStateSelected(int i) {
        if (this.mListingButton.a()) {
            int i2 = 0;
            if (!(this.bottomLayout == null || this.bottomLayout.getChildAt(0) == null || !(this.bottomLayout.getChildAt(0) instanceof Button))) {
                Button button = (Button) this.bottomLayout.getChildAt(0);
                if (i == 0) {
                    button.setText(this.mContext.getString(R.string.select_song_txt));
                } else {
                    Context context = this.mContext;
                    Object[] objArr = new Object[1];
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(i);
                    objArr[0] = stringBuilder.toString();
                    button.setText(context.getString(R.string.download_count_songs, objArr));
                }
                button.setTag(Integer.valueOf(i));
            }
            ArrayList g = this.mListingButton.g();
            while (i2 < g.size()) {
                BusinessObject businessObject = (BusinessObject) g.get(i2);
                if (businessObject instanceof Track) {
                    DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                    DownloadStatus downloadStatus = DownloadStatus.DOWNLOADED;
                }
                i2++;
            }
        }
    }

    public void startSearch(String str, OnSearchCompleted onSearchCompleted) {
        this.searchParam = str;
        this.hasDataEnded = Boolean.valueOf(false);
        if (getListAdapter() != null) {
            SearchFilter searchFilter = (SearchFilter) getListAdapter().getFilter();
            searchFilter.setOnSearchCompleted(onSearchCompleted);
            if (this.mListingButton.c().i() == BusinessObjectType.Tracks) {
                searchFilter.setOnFilterStarted(new SongFilter());
            } else if (this.mListingButton.c().i() == BusinessObjectType.Albums) {
                searchFilter.setOnFilterStarted(new AlbumFilter());
            } else {
                searchFilter.setOnFilterStarted(new GenericFilter());
            }
            if (this.mListingButton.n() != null) {
                searchFilter.shouldFetchFromDb(true);
                searchFilter.setLoadStrategy(this.mListingButton.n());
            } else if (this.mListingButton.b()) {
                searchFilter.shouldFetchFromDb(true);
            }
            searchFilter.filter(str);
            if (DownloadManager.c().p() <= 0 || DownloadManager.c().p() > 10) {
                if (this.mLLHeaderLayout != null && this.mLLHeaderLayout.getVisibility() == 0) {
                    this.mLLHeaderLayout.setVisibility(8);
                }
            } else if ((this.mFragment instanceof ListingFragment) && ((ListingFragment) this.mFragment).e() != null && (((ListingFragment) this.mFragment).e() instanceof DownloadDetailsFragment)) {
                this.mLLHeaderLayout.setVisibility(0);
            } else if (this.mLLHeaderLayout != null && this.mLLHeaderLayout.getVisibility() == 0) {
                this.mLLHeaderLayout.setVisibility(8);
            }
        }
    }

    private void onDataRefreshed(BusinessObject businessObject) {
        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
            if (this.mDataRefreshListener != null) {
                this.mDataRefreshListener.onEmptyDataRefresh();
            }
            showHideEmtpyViewLayout(true);
        } else {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            this.mOrignalList.clear();
            this.mOrignalList.addAll(arrListBusinessObj);
            if ((this.mBusinessView instanceof SongsItemView) && this.mFragment.getUserVisibleHint()) {
                this.mAppState.setCurrentBusObjInListView(arrListBusinessObj);
            }
            if (this.mListingButton.g() == null || this.mListingButton.g().size() == 0 || this.adapter == null) {
                this.mListingButton.a(arrListBusinessObj);
                populateListView(arrListBusinessObj, this.mBusinessView);
            } else {
                sortIfRequired(arrListBusinessObj, this.mSortOrder);
                this.mListingButton.a(arrListBusinessObj);
                this.adapter.setAdapterArrayList(arrListBusinessObj);
            }
        }
        if (this.mDataLoadedListener != null) {
            this.mDataLoadedListener.onDataLoaded(businessObject, this.mListingButton.c().i());
        }
    }

    public void updateArrayList(BusinessObject businessObject, boolean z) {
        ArrayList g = this.mListingButton.g();
        if (z && g != null && g.size() > 0) {
            int size = g.size();
            String businessObjId = businessObject.getBusinessObjId();
            int i = 0;
            while (i < size) {
                if (businessObjId.equals(((BusinessObject) g.get(i)).getBusinessObjId())) {
                    break;
                }
                i++;
            }
            i = -1;
            if (i != -1) {
                businessObject = (BusinessObject) g.get(i);
                g.remove(i);
                this.mOrignalList.remove(businessObject);
                this.adapter.setAdapterArrayList(g);
            }
        }
    }

    public boolean isFavouriteContainsFragment() {
        return ((GaanaActivity) this.mContext).getCurrentFragment() instanceof MyMusicItemFragment;
    }

    public void refreshListData() {
        if ((this.mListingButton.n() == null || !isFavouriteContainsFragment()) && !this.mListingButton.b()) {
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if ((businessObject instanceof NextGenSearchAutoSuggests) && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                        businessObject = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
                        ((AutoComplete) businessObject.getArrListBusinessObj().get(0)).setType(((GroupItem) businessObject).getType());
                    }
                    CustomListView.this.onDataRefreshed(businessObject);
                }
            }, this.mListingButton.c(), Boolean.valueOf(false));
            return;
        }
        this.hasDataEnded = Boolean.valueOf(false);
        if (!TextUtils.isEmpty(this.initialUrl)) {
            this.mListingButton.c().a(this.initialUrl);
        }
        this.mListingButton.c().c(Boolean.valueOf(true));
        if (this.mListingButton.n() != null) {
            this.mListingButton.n().a();
            this.mListingButton.n().a(this.mListingButton.c(), this.searchParam, 0, 20, this.orderColumn, this.orderType, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    CustomListView.this.onDataRefreshed(businessObject);
                }
            });
            return;
        }
        i.a().b(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                CustomListView.this.onDataRefreshed(businessObject);
            }
        }, this.mListingButton.c(), this.searchParam, 0, 20, this.orderColumn, this.orderType);
    }

    public void onDestroyView() {
        if (this.initialUrl != null) {
            URLManager c = this.mListingButton.c();
            c.a(this.initialUrl);
            this.mListingButton.a(c);
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(null);
        }
    }

    public void showHideGaanaPlusExpirationHeader() {
        if (this.mFragment != null && this.mFragment.isDownLoadFragment() && this.mAppState.getCurrentUser().getLoginStatus() && !ap.a().j()) {
            this.mLLHeaderLayout.setVisibility(0);
        } else if (this.mListingButton.a() || this.showDownloadCuratedSongsLayout || (this.mListingButton.m() && this.mListingButton != null && this.mListingButton.g() != null && this.mListingButton.g().size() > 0 && this.mListingButton.g().size() < 10)) {
            this.mLLHeaderLayout.setVisibility(0);
        } else {
            this.mLLHeaderLayout.setVisibility(8);
        }
    }

    public void onResume() {
        showHideGaanaPlusExpirationHeader();
    }

    public void setDownloadSongCount() {
        if (this.bottomLayout != null && this.bottomLayout.getChildAt(0) != null && (this.bottomLayout.getChildAt(0) instanceof Button)) {
            Button button = (Button) this.bottomLayout.getChildAt(0);
            ArrayList g = this.mListingButton.g();
            int i = 0;
            int i2 = i;
            while (i < g.size()) {
                BusinessObject businessObject = (BusinessObject) g.get(i);
                if ((businessObject instanceof Track) && DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != DownloadStatus.DOWNLOADED) {
                    i2++;
                }
                i++;
            }
            if (i2 == 0) {
                button.setText(this.mContext.getString(R.string.select_song_txt));
            } else {
                Context context = this.mContext;
                Object[] objArr = new Object[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(i2);
                objArr[0] = stringBuilder.toString();
                button.setText(context.getString(R.string.download_count_songs, objArr));
            }
            button.setTag(Integer.valueOf(i2));
        }
    }

    public void clearCuratedList() {
        if (this.mListingButton != null && this.mListingButton.a()) {
            if (this.mAppState.getArrListTracksForPlaylist() != null && this.mAppState.getArrListTracksForPlaylist().size() > 0) {
                this.mAppState.getArrListTracksForPlaylist().clear();
            }
            if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() > 0) {
                this.mAppState.getArrListForTrackIds().clear();
            }
        }
    }

    public void SocialEmptyView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.followMsgView);
        ImageView imageView = (ImageView) view.findViewById(R.id.followMsgImageView);
        if (this.mFragment instanceof ProfileFragment) {
            textView.setVisibility(0);
            textView.setText(R.string.no_activity);
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.no_activity});
            imageView.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
            imageView.setVisibility(0);
            obtainStyledAttributes.recycle();
        } else if (this.mFragment.getParentFragment() == null || !(this.mFragment.getParentFragment() instanceof DownloadDetailsFragment)) {
            ((TextView) view.findViewById(R.id.tvUserMsg)).setVisibility(0);
        } else {
            if (this.shouldShowNoDownloadView) {
                if (view.findViewById(R.id.no_downloads_here) != null) {
                    view.findViewById(R.id.no_downloads_here).setVisibility(8);
                }
            } else if (view.findViewById(R.id.no_downloads_here) != null) {
                view.findViewById(R.id.no_downloads_here).setVisibility(0);
                setNoDownloadsText();
            }
            refreshList();
        }
    }

    private void setNoDownloadsText() {
        TextView textView = (TextView) this.mView.findViewById(R.id.no_downloads_here_text_first);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.no_downloads_here_text_second);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.no_downloads_here_image);
        TextView textView3 = (TextView) this.mView.findViewById(R.id.no_downloads_here_download_now_button);
        if (TextUtils.isEmpty(this.searchParam)) {
            textView.setTextSize(2, 18.0f);
            textView2.setVisibility(0);
            imageView.setVisibility(0);
            textView3.setVisibility(0);
            if (this.mListingButton.c().i() == BusinessObjectType.Tracks) {
                textView2.setText(this.mContext.getResources().getString(R.string.help_download_first_song));
                textView.setText(this.mContext.getResources().getString(R.string.no_downloaded_song));
                this.mView.findViewById(R.id.no_downloads_here_download_now_button).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isFromDownloads", true);
                        g.a(CustomListView.this.mContext, CustomListView.this.mFragment, bundle);
                        com.managers.u.a().b("CuratedDownloadsPersonalized", "PopUpView");
                    }
                });
            } else if (this.mListingButton.c().i() == BusinessObjectType.Albums) {
                textView2.setText(this.mContext.getResources().getString(R.string.help_download_first_album));
                textView.setText(this.mContext.getResources().getString(R.string.no_downloaded_album));
                this.mView.findViewById(R.id.no_downloads_here_download_now_button).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        an.a().a("click", "ac", "", "", "Album", "download", "", "");
                        ((GaanaActivity) CustomListView.this.mContext).changeFragment(R.id.MyMusicMenuAlbums, InternalAvidAdSessionContext.AVID_API_LEVEL, null);
                    }
                });
            } else {
                textView2.setText(this.mContext.getResources().getString(R.string.help_download_first_playlists));
                textView.setText(this.mContext.getResources().getString(R.string.no_downloaded_playlist));
                this.mView.findViewById(R.id.no_downloads_here_download_now_button).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        an.a().a("click", "ac", "", "", "Playlist", "download", "", "");
                        ((GaanaActivity) CustomListView.this.mContext).changeFragment(R.id.MyMusicMenuPlaylists, "3", null);
                    }
                });
            }
            return;
        }
        textView2.setVisibility(8);
        imageView.setVisibility(8);
        textView3.setVisibility(8);
        textView.setText(this.mContext.getResources().getString(R.string.NO_DATA));
        textView.setTextSize(2, 14.0f);
        if (Constants.l) {
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            textView.setTextColor(-1);
        }
    }

    private void setNoSearchUI() {
        TextView textView = (TextView) this.mView.findViewById(R.id.no_downloads_here_text_first);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.no_downloads_here_text_second);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.no_downloads_here_image);
        TextView textView3 = (TextView) this.mView.findViewById(R.id.no_downloads_here_download_now_button);
        ((MyMusicSearchResultFragment) this.mFragment.getParentFragment()).b();
        textView3.setVisibility(8);
        if (Constants.l) {
            imageView.setImageResource(R.drawable.no_search_result_white);
        } else {
            imageView.setImageResource(R.drawable.no_search_result);
        }
        textView2.setVisibility(8);
        textView.setText(this.mContext.getResources().getString(R.string.no_search_result));
    }

    public void setListingName(String str) {
        this.mListingName = str;
    }

    public LinearLayout getmLLHeaderLayout() {
        return this.mLLHeaderLayout;
    }

    public void showDownloadCuratedSongsLayout() {
        if (this.mLLHeaderLayout != null && this.shouldShowNoDownloadView && !Util.v()) {
            this.mLLHeaderLayout.removeAllViews();
            this.mLLHeaderLayout.addView(this.mDownloadCuratedSongsLayout);
            this.mLLHeaderLayout.setVisibility(0);
            this.showDownloadCuratedSongsLayout = true;
        }
    }

    public void hideDownloadCuratedSongsLayout() {
        if (this.mLLHeaderLayout != null) {
            this.mLLHeaderLayout.removeAllViews();
            this.mLLHeaderLayout.setVisibility(8);
            this.showDownloadCuratedSongsLayout = false;
        }
    }

    public void hideSearchView() {
        shouldShowSearchWidget(false);
        if (this.mSearchViewContainer != null && this.mSearchView != null) {
            this.mSearchViewContainer.setVisibility(8);
        }
    }

    public void showSearchView() {
        shouldShowSearchWidget(true);
        if (this.mSearchView != null && this.mSearchViewContainer != null && this.mListViewHome != null) {
            this.mSearchViewContainer.setVisibility(0);
        }
    }

    /* Access modifiers changed, original: protected */
    public int getActionBarSize() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }
}

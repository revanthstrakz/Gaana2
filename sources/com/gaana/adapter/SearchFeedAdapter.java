package com.gaana.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.DynamicOccasionFragment;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.fragments.BaseGaanaFragment;
import com.fragments.SearchTabFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.GaanaYourYearView.GAANA_VIDEO_SOURCE;
import com.h.b;
import com.library.controls.RoundedCustomImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.c;
import com.services.l.ag;
import com.services.l.ay;
import com.utilities.Util;
import com.utilities.d;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SearchFeedAdapter extends Adapter<ParentItemViewholder> implements OnClickListener {
    private static int VIEW_TYPE_FOUR = 5;
    private static int VIEW_TYPE_ONE = 2;
    private static int VIEW_TYPE_RECENT = 1;
    private static int VIEW_TYPE_THREE = 4;
    private static int VIEW_TYPE_TWO = 3;
    private String VIEW_FOUR = "V4";
    private String VIEW_ONE = "V1";
    private String VIEW_THREE = "V3";
    private String VIEW_TWO = "V2";
    OnClickListener iconClick = new OnClickListener() {
        public void onClick(View view) {
            AutoComplete autoComplete = (AutoComplete) view.getTag();
            BusinessObject a = Util.a(autoComplete, GaanaApplication.getInstance());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a.getBusinessObjType());
            stringBuilder.append("");
            stringBuilder.append(a.getBusinessObjId());
            u.a().a("search feed", "click", stringBuilder.toString());
            an.a().a("click", "en", "", "SEARCH FEED", a.getBusinessObjId(), a.getBusinessObjType().name(), "", "");
            if (autoComplete.getVurl() != null && !TextUtils.isEmpty(autoComplete.getVurl()) && (a instanceof YouTubeVideo)) {
                ((YouTubeVideo) a).a(SearchFeedAdapter.this.getVideoType(autoComplete.getVideoType()));
                Util.a(SearchFeedAdapter.this.mContext, autoComplete.getVurl(), autoComplete.getVurl(), a, SearchFeedAdapter.this.getVideoType(autoComplete.getVideoType()));
            } else if (a.getBusinessObjType() == BusinessObjectType.Tracks) {
                ((GaanaActivity) SearchFeedAdapter.this.mContext).setCurrentSongSelectedView(view);
                c.a(SearchFeedAdapter.this.mContext).a(SearchFeedAdapter.this.mContext, a, SOURCE_TYPE.SEARCH.ordinal(), false);
            } else {
                af.a(SearchFeedAdapter.this.mContext, SearchFeedAdapter.this.mFragment).a((int) R.id.playMenu, a);
            }
        }
    };
    boolean isLoading = true;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private List<b> mView = new ArrayList();
    LinkedHashMap<Integer, List<b>> map = new LinkedHashMap();
    private int mpaginationEndLimit;
    int offSet = 2;
    int page = 0;
    private SearchRecentAdapter searchRecentAdapter;
    private TextView searchTextView;
    private NestedSearchItemViewHolder viewHolder;
    private int width = 0;

    public class ParentItemViewholder extends ViewHolder {
        public ParentItemViewholder(View view) {
            super(view);
        }
    }

    public class NestedSearchItemViewHolder extends ParentItemViewholder {
        private final View divider;
        private final ImageView more;
        private final TextView recentSearches_text;
        private final TextView recent_searches_title;
        private final RecyclerView recycler_search_recent_view;
        private final View searchBarLayout;
        private final TextView searchText;

        public NestedSearchItemViewHolder(View view) {
            super(view);
            this.recycler_search_recent_view = (RecyclerView) view.findViewById(R.id.recycler_search_recent_view);
            this.searchBarLayout = view.findViewById(R.id.searchBarLayout);
            this.searchText = (TextView) view.findViewById(R.id.searchText);
            this.recentSearches_text = (TextView) view.findViewById(R.id.recentSearches_text);
            this.recent_searches_title = (TextView) view.findViewById(R.id.recent_searches_title);
            this.divider = view.findViewById(R.id.divider);
            this.more = (ImageView) view.findViewById(R.id.more);
        }
    }

    public class SearchFeedItemViewHolder extends ParentItemViewholder {
        private VideoPlayerAutoPlayView autoPlayerView;
        private ViewGroup finalVideoView;
        private boolean paused = false;
        private RoundedCustomImageView setPausedvideoImageView;
        private String streamUrl;
        private final ViewGroup videoView;
        private final RoundedCustomImageView view1;
        private final RoundedCustomImageView view2;
        private final RoundedCustomImageView view3;
        private final RoundedCustomImageView view4;
        private final RoundedCustomImageView view5;
        private final RoundedCustomImageView view6;
        private final RoundedCustomImageView view7;
        private final RoundedCustomImageView view8;

        public SearchFeedItemViewHolder(View view) {
            super(view);
            this.view1 = (RoundedCustomImageView) view.findViewById(R.id.view1);
            this.view2 = (RoundedCustomImageView) view.findViewById(R.id.view2);
            this.view3 = (RoundedCustomImageView) view.findViewById(R.id.view3);
            this.view4 = (RoundedCustomImageView) view.findViewById(R.id.view4);
            this.view5 = (RoundedCustomImageView) view.findViewById(R.id.view5);
            this.view6 = (RoundedCustomImageView) view.findViewById(R.id.view6);
            this.view7 = (RoundedCustomImageView) view.findViewById(R.id.view7);
            this.view8 = (RoundedCustomImageView) view.findViewById(R.id.view8);
            this.videoView = (ViewGroup) view.findViewById(R.id.videoView);
        }

        public void setUrl(String str) {
            this.streamUrl = str;
        }

        public String getStreamUrl(String str) {
            if (str == null || TextUtils.isEmpty(str)) {
                return "";
            }
            return (str.contains("http") || str.contains("https")) ? str : Util.k(str);
        }

        public String getVideoUrl() {
            return this.streamUrl;
        }

        public VideoPlayerAutoPlayView getAah_vi() {
            return this.autoPlayerView;
        }

        public boolean isPaused() {
            return this.paused;
        }

        public void setPaused(boolean z) {
            this.paused = z;
        }

        public void playVideo() {
            this.paused = true;
            this.autoPlayerView.k();
            this.autoPlayerView.setVideoFetchFirstTry(true);
            this.autoPlayerView.g();
        }

        public void pauseVideo() {
            this.paused = false;
            this.autoPlayerView.e();
        }

        public void destroyVideo() {
            this.autoPlayerView.i();
            this.autoPlayerView.j();
        }

        public void initilisedVideoData(VideoPlayerAutoPlayView videoPlayerAutoPlayView, RoundedCustomImageView roundedCustomImageView, ViewGroup viewGroup, String str) {
            this.autoPlayerView = videoPlayerAutoPlayView;
            this.setPausedvideoImageView = roundedCustomImageView;
            this.finalVideoView = viewGroup;
            this.autoPlayerView.setVideoStateChangeListener(new ay() {
                public void videoClicked(int i, long j) {
                }

                public void videoStateTransitioned(Bitmap bitmap) {
                }

                public void volumeStateChanged(boolean z) {
                }

                public void videoStateChanged(int i) {
                    if (i == 1) {
                        SearchFeedItemViewHolder.this.setPausedvideoImageView.setVisibility(4);
                        SearchFeedItemViewHolder.this.finalVideoView.setVisibility(0);
                        SearchFeedItemViewHolder.this.autoPlayerView.d();
                    }
                }
            });
        }
    }

    public class FeedItemViewHolderTypeFour extends SearchFeedItemViewHolder {
        private final RoundedCustomImageView view9;

        public FeedItemViewHolderTypeFour(View view) {
            super(view);
            this.view9 = (RoundedCustomImageView) view.findViewById(R.id.view9);
        }
    }

    public class FeedItemViewHolderTypeOne extends SearchFeedItemViewHolder {
        private final ViewGroup videoViewVertical;

        public FeedItemViewHolderTypeOne(View view) {
            super(view);
            this.videoViewVertical = (ViewGroup) view.findViewById(R.id.videoView2);
        }
    }

    public class FeedItemViewHolderTypeThree extends SearchFeedItemViewHolder {
        private final RoundedCustomImageView view9;

        public FeedItemViewHolderTypeThree(View view) {
            super(view);
            this.view9 = (RoundedCustomImageView) view.findViewById(R.id.view9);
        }
    }

    public class FeedItemViewHolderTypeTwo extends SearchFeedItemViewHolder {
        private final ViewGroup videoViewVertical;

        public FeedItemViewHolderTypeTwo(View view) {
            super(view);
            this.videoViewVertical = (ViewGroup) view.findViewById(R.id.videoView2);
        }
    }

    public SearchFeedAdapter(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.width = i;
    }

    private void setValue(int i, List<b> list) {
        this.map.put(Integer.valueOf(i), list);
        if (this.mView != null && this.mView.size() > 0) {
            this.mView.clear();
        }
        for (Integer intValue : this.map.keySet()) {
            List list2 = (List) this.map.get(Integer.valueOf(intValue.intValue()));
            if (list2 != null) {
                this.mView.addAll(list2);
            }
        }
        notifyDataSetChanged();
    }

    public void setDataView(List<b> list, int i, boolean z, int i2) {
        this.mpaginationEndLimit = i;
        setValue(i2, list);
        this.page = i2;
        this.isLoading = false;
    }

    public ParentItemViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ParentItemViewholder parentItemViewholder = null;
        if (i == VIEW_TYPE_RECENT) {
            return new NestedSearchItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nested_search_adapter, null));
        }
        if (i == VIEW_TYPE_ONE) {
            parentItemViewholder = new FeedItemViewHolderTypeOne(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_feed_view_type_one, null));
        } else if (i == VIEW_TYPE_TWO) {
            parentItemViewholder = new FeedItemViewHolderTypeTwo(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_feed_view_type_two, null));
        } else if (i == VIEW_TYPE_THREE) {
            parentItemViewholder = new FeedItemViewHolderTypeThree(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_feed_view_type_three, null));
        } else if (i == VIEW_TYPE_FOUR) {
            parentItemViewholder = new FeedItemViewHolderTypeFour(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_feed_view_type_four, null));
        }
        return parentItemViewholder;
    }

    public void onBindViewHolder(ParentItemViewholder parentItemViewholder, int i) {
        if (parentItemViewholder.getItemViewType() == VIEW_TYPE_RECENT) {
            this.viewHolder = (NestedSearchItemViewHolder) parentItemViewholder;
            this.searchRecentAdapter = new SearchRecentAdapter(this.mContext, (SearchTabFragment) this.mFragment, ((SearchTabFragment) this.mFragment).a);
            this.viewHolder.recycler_search_recent_view.setHasFixedSize(true);
            this.viewHolder.recycler_search_recent_view.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
            this.viewHolder.recycler_search_recent_view.setAdapter(this.searchRecentAdapter);
            this.viewHolder.searchText.setTypeface(Typeface.DEFAULT_BOLD);
            setSearchTextView(this.viewHolder.searchText);
            this.viewHolder.searchBarLayout.setVisibility(8);
            this.viewHolder.divider.setVisibility(0);
            if (((SearchTabFragment) this.mFragment).a != null && ((SearchTabFragment) this.mFragment).a.size() > 0) {
                this.viewHolder.recentSearches_text.setVisibility(0);
                this.viewHolder.recentSearches_text.setTypeface(Typeface.DEFAULT_BOLD);
                this.viewHolder.recent_searches_title.setVisibility(0);
                this.viewHolder.more.setVisibility(0);
            }
            this.viewHolder.more.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("Online-SearchScreen", "RecentSearch_ViewAll", "link");
                    ((SearchTabFragment) SearchFeedAdapter.this.mFragment).k();
                }
            });
        } else {
            SearchFeedItemViewHolder searchFeedItemViewHolder = (SearchFeedItemViewHolder) parentItemViewholder;
            int i2 = i - 1;
            searchFeedItemViewHolder.view1.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(0)).getArtwork());
            searchFeedItemViewHolder.view2.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(1)).getArtwork());
            searchFeedItemViewHolder.view3.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(2)).getArtwork());
            searchFeedItemViewHolder.view4.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(3)).getArtwork());
            searchFeedItemViewHolder.view5.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(4)).getArtwork());
            searchFeedItemViewHolder.view6.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(5)).getArtwork());
            searchFeedItemViewHolder.view7.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(6)).getArtwork());
            searchFeedItemViewHolder.view8.bindImage(((AutoComplete) ((b) this.mView.get(i2)).b().get(7)).getArtwork());
            searchFeedItemViewHolder.view1.setOnClickListener(this);
            searchFeedItemViewHolder.view1.setTag(((b) this.mView.get(i2)).b().get(0));
            searchFeedItemViewHolder.view2.setOnClickListener(this);
            searchFeedItemViewHolder.view2.setTag(((b) this.mView.get(i2)).b().get(1));
            searchFeedItemViewHolder.view3.setOnClickListener(this);
            searchFeedItemViewHolder.view3.setTag(((b) this.mView.get(i2)).b().get(2));
            searchFeedItemViewHolder.view4.setOnClickListener(this);
            searchFeedItemViewHolder.view4.setTag(((b) this.mView.get(i2)).b().get(3));
            searchFeedItemViewHolder.view5.setOnClickListener(this);
            searchFeedItemViewHolder.view5.setTag(((b) this.mView.get(i2)).b().get(4));
            searchFeedItemViewHolder.view6.setOnClickListener(this);
            searchFeedItemViewHolder.view6.setTag(((b) this.mView.get(i2)).b().get(5));
            searchFeedItemViewHolder.view7.setOnClickListener(this);
            searchFeedItemViewHolder.view7.setTag(((b) this.mView.get(i2)).b().get(6));
            searchFeedItemViewHolder.view8.setOnClickListener(this);
            searchFeedItemViewHolder.view8.setTag(((b) this.mView.get(i2)).b().get(7));
            setIndicator(searchFeedItemViewHolder, ((b) this.mView.get(i2)).b());
            setIconclick(searchFeedItemViewHolder, ((b) this.mView.get(i2)).b());
            setPlayCount(searchFeedItemViewHolder, ((b) this.mView.get(i2)).b());
            setDynamicWidthHeight(searchFeedItemViewHolder);
            if (searchFeedItemViewHolder.getItemViewType() == VIEW_TYPE_ONE) {
                populateViewtypeOne(searchFeedItemViewHolder, i2);
            } else if (searchFeedItemViewHolder.getItemViewType() == VIEW_TYPE_TWO) {
                populateViewtypeTwo(searchFeedItemViewHolder, i2);
            } else if (searchFeedItemViewHolder.getItemViewType() == VIEW_TYPE_THREE) {
                populateViewtypeThree(searchFeedItemViewHolder, i2);
            } else if (searchFeedItemViewHolder.getItemViewType() == VIEW_TYPE_FOUR) {
                populateViewtypeFour(searchFeedItemViewHolder, i2);
            }
        }
        if (this.mView != null && this.mView.size() > 0 && i == this.mView.size() - this.offSet && !this.isLoading && this.mpaginationEndLimit == 0) {
            this.isLoading = true;
            SearchTabFragment searchTabFragment = (SearchTabFragment) this.mFragment;
            i = this.page + 1;
            this.page = i;
            searchTabFragment.a(i, false);
        } else if (this.mpaginationEndLimit == 1 && this.mView != null && this.mView.size() - 1 == i) {
            u.a().a("search feed", "scrolled till end", "null");
        }
    }

    private void setPlayCount(SearchFeedItemViewHolder searchFeedItemViewHolder, List<AutoComplete> list) {
        searchFeedItemViewHolder.view1.setplayCount(((AutoComplete) list.get(0)).getPlayCount());
        searchFeedItemViewHolder.view2.setplayCount(((AutoComplete) list.get(1)).getPlayCount());
        searchFeedItemViewHolder.view3.setplayCount(((AutoComplete) list.get(2)).getPlayCount());
        searchFeedItemViewHolder.view4.setplayCount(((AutoComplete) list.get(3)).getPlayCount());
        searchFeedItemViewHolder.view5.setplayCount(((AutoComplete) list.get(4)).getPlayCount());
        searchFeedItemViewHolder.view6.setplayCount(((AutoComplete) list.get(5)).getPlayCount());
        searchFeedItemViewHolder.view7.setplayCount(((AutoComplete) list.get(6)).getPlayCount());
        searchFeedItemViewHolder.view8.setplayCount(((AutoComplete) list.get(7)).getPlayCount());
    }

    private void setIndicator(SearchFeedItemViewHolder searchFeedItemViewHolder, List<AutoComplete> list) {
        searchFeedItemViewHolder.view1.setLeftIndicationIcon(((AutoComplete) list.get(0)).getType());
        searchFeedItemViewHolder.view2.setLeftIndicationIcon(((AutoComplete) list.get(1)).getType());
        searchFeedItemViewHolder.view3.setLeftIndicationIcon(((AutoComplete) list.get(2)).getType());
        searchFeedItemViewHolder.view4.setLeftIndicationIcon(((AutoComplete) list.get(3)).getType());
        searchFeedItemViewHolder.view5.setLeftIndicationIcon(((AutoComplete) list.get(4)).getType());
        searchFeedItemViewHolder.view6.setLeftIndicationIcon(((AutoComplete) list.get(5)).getType());
        searchFeedItemViewHolder.view7.setLeftIndicationIcon(((AutoComplete) list.get(6)).getType());
        searchFeedItemViewHolder.view8.setLeftIndicationIcon(((AutoComplete) list.get(7)).getType());
    }

    private boolean isVideoAllowed() {
        return !Constants.cN && d.g();
    }

    private void populateViewtypeOne(SearchFeedItemViewHolder searchFeedItemViewHolder, int i) {
        if (searchFeedItemViewHolder instanceof FeedItemViewHolderTypeOne) {
            searchFeedItemViewHolder.view2.bindRectImage(((AutoComplete) ((b) this.mView.get(i)).b().get(1)).getArtwork());
            String autoType = ((AutoComplete) ((b) this.mView.get(i)).b().get(0)).getAutoType();
            if (autoType != null && autoType.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view1, searchFeedItemViewHolder.videoView, (AutoComplete) ((b) this.mView.get(i)).b().get(0));
            } else {
                ViewGroup access$1600 = searchFeedItemViewHolder.videoView;
                removeAndClearAutoplayView(access$1600);
                access$1600.setVisibility(4);
                searchFeedItemViewHolder.view1.setVisibility(0);
            }
            autoType = ((AutoComplete) ((b) this.mView.get(i)).b().get(1)).getAutoType();
            if (autoType != null && autoType.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view2, ((FeedItemViewHolderTypeOne) searchFeedItemViewHolder).videoViewVertical, (AutoComplete) ((b) this.mView.get(i)).b().get(1));
                return;
            }
            ViewGroup access$1700 = ((FeedItemViewHolderTypeOne) searchFeedItemViewHolder).videoViewVertical;
            removeAndClearAutoplayView(access$1700);
            access$1700.setVisibility(4);
            searchFeedItemViewHolder.view2.setVisibility(0);
        }
    }

    private void populateViewtypeTwo(SearchFeedItemViewHolder searchFeedItemViewHolder, int i) {
        if (searchFeedItemViewHolder instanceof FeedItemViewHolderTypeTwo) {
            FeedItemViewHolderTypeTwo feedItemViewHolderTypeTwo = (FeedItemViewHolderTypeTwo) searchFeedItemViewHolder;
            searchFeedItemViewHolder.view1.bindRectImage(((AutoComplete) ((b) this.mView.get(i)).b().get(0)).getArtwork());
            String autoType = ((AutoComplete) ((b) this.mView.get(i)).b().get(0)).getAutoType();
            if (autoType != null && autoType.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view1, feedItemViewHolderTypeTwo.videoViewVertical, (AutoComplete) ((b) this.mView.get(i)).b().get(0));
            } else {
                ViewGroup access$1800 = feedItemViewHolderTypeTwo.videoViewVertical;
                removeAndClearAutoplayView(access$1800);
                access$1800.setVisibility(4);
                searchFeedItemViewHolder.view1.setVisibility(0);
            }
            String autoType2 = ((AutoComplete) ((b) this.mView.get(i)).b().get(1)).getAutoType();
            if (autoType2 != null && autoType2.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view2, searchFeedItemViewHolder.videoView, (AutoComplete) ((b) this.mView.get(i)).b().get(1));
                return;
            }
            ViewGroup access$1600 = searchFeedItemViewHolder.videoView;
            removeAndClearAutoplayView(access$1600);
            access$1600.setVisibility(4);
            searchFeedItemViewHolder.view2.setVisibility(0);
        }
    }

    private void populateViewtypeThree(SearchFeedItemViewHolder searchFeedItemViewHolder, int i) {
        if (searchFeedItemViewHolder instanceof FeedItemViewHolderTypeThree) {
            FeedItemViewHolderTypeThree feedItemViewHolderTypeThree = (FeedItemViewHolderTypeThree) searchFeedItemViewHolder;
            feedItemViewHolderTypeThree.view9.bindImage(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getArtwork());
            feedItemViewHolderTypeThree.view9.setOnClickListener(this);
            feedItemViewHolderTypeThree.view9.setTag(((b) this.mView.get(i)).b().get(8));
            feedItemViewHolderTypeThree.view9.setLeftIndicationIcon(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getType());
            playIconClick(feedItemViewHolderTypeThree.view9.getIconImage(), (AutoComplete) ((b) this.mView.get(i)).b().get(8));
            feedItemViewHolderTypeThree.view9.setplayCount(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getPlayCount());
            feedItemViewHolderTypeThree.view9.setHeightWidth(this.width, feedItemViewHolderTypeThree.view9, this.mContext);
            String autoType = ((AutoComplete) ((b) this.mView.get(i)).b().get(0)).getAutoType();
            if (autoType != null && autoType.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view1, searchFeedItemViewHolder.videoView, (AutoComplete) ((b) this.mView.get(i)).b().get(0));
                return;
            }
            ViewGroup access$1600 = searchFeedItemViewHolder.videoView;
            removeAndClearAutoplayView(access$1600);
            access$1600.setVisibility(4);
            searchFeedItemViewHolder.view1.setVisibility(0);
        }
    }

    private void populateViewtypeFour(SearchFeedItemViewHolder searchFeedItemViewHolder, int i) {
        if (searchFeedItemViewHolder instanceof FeedItemViewHolderTypeFour) {
            FeedItemViewHolderTypeFour feedItemViewHolderTypeFour = (FeedItemViewHolderTypeFour) searchFeedItemViewHolder;
            feedItemViewHolderTypeFour.view9.bindImage(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getArtwork());
            feedItemViewHolderTypeFour.view9.setOnClickListener(this);
            feedItemViewHolderTypeFour.view9.setTag(((b) this.mView.get(i)).b().get(8));
            feedItemViewHolderTypeFour.view9.setLeftIndicationIcon(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getType());
            playIconClick(feedItemViewHolderTypeFour.view9.getIconImage(), (AutoComplete) ((b) this.mView.get(i)).b().get(8));
            feedItemViewHolderTypeFour.view9.setplayCount(((AutoComplete) ((b) this.mView.get(i)).b().get(8)).getPlayCount());
            feedItemViewHolderTypeFour.view9.setHeightWidth(this.width, feedItemViewHolderTypeFour.view9, this.mContext);
            String autoType = ((AutoComplete) ((b) this.mView.get(i)).b().get(1)).getAutoType();
            if (autoType != null && autoType.equalsIgnoreCase("video") && isVideoAllowed()) {
                setUpAutoPlayVideo(searchFeedItemViewHolder, searchFeedItemViewHolder.view2, searchFeedItemViewHolder.videoView, (AutoComplete) ((b) this.mView.get(i)).b().get(1));
                return;
            }
            ViewGroup access$1600 = searchFeedItemViewHolder.videoView;
            removeAndClearAutoplayView(access$1600);
            access$1600.setVisibility(4);
            searchFeedItemViewHolder.view2.setVisibility(0);
        }
    }

    public void setHeightWidth(RoundedCustomImageView roundedCustomImageView, ViewGroup viewGroup) {
        String imageSize = roundedCustomImageView.getImageSize();
        if (imageSize != null && imageSize.equalsIgnoreCase("large")) {
            viewGroup.getLayoutParams().width = (int) (((float) (this.width * 2)) + this.mContext.getResources().getDimension(R.dimen.dp10));
            viewGroup.getLayoutParams().height = (int) (((float) (this.width * 2)) + this.mContext.getResources().getDimension(R.dimen.dp10));
        } else if (imageSize != null && imageSize.equalsIgnoreCase("vertical")) {
            viewGroup.getLayoutParams().width = this.width;
            viewGroup.getLayoutParams().height = (int) (((float) (this.width * 2)) + this.mContext.getResources().getDimension(R.dimen.dp10));
        } else if (imageSize != null && imageSize.equalsIgnoreCase("small")) {
            viewGroup.getLayoutParams().width = this.width;
            viewGroup.getLayoutParams().height = this.width;
        }
    }

    private void removeAndClearAutoplayView(ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() > 0 && (viewGroup.getChildAt(0) instanceof VideoPlayerAutoPlayView)) {
            ((VideoPlayerAutoPlayView) viewGroup.getChildAt(0)).i();
            viewGroup.removeAllViews();
        }
    }

    /* JADX WARNING: Missing block: B:18:0x00dc, code skipped:
            return;
     */
    private void setUpAutoPlayVideo(final com.gaana.adapter.SearchFeedAdapter.SearchFeedItemViewHolder r8, com.library.controls.RoundedCustomImageView r9, android.view.ViewGroup r10, com.gaana.models.NextGenSearchAutoSuggests.AutoComplete r11) {
        /*
        r7 = this;
        r0 = com.utilities.d.g();
        if (r0 == 0) goto L_0x00dc;
    L_0x0006:
        r0 = com.gaana.application.GaanaApplication.getInstance();
        r0 = r0.isVideoAutoplay();
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        goto L_0x00dc;
    L_0x0012:
        r0 = r11.getVideoType();
        r0 = r7.getVideoType(r0);
        if (r0 != 0) goto L_0x001d;
    L_0x001c:
        return;
    L_0x001d:
        r7.removeAndClearAutoplayView(r10);
        r0 = new com.exoplayer2.ui.VideoPlayerAutoPlayView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r1 = r7.mContext;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.<init>(r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r1 = new com.gaanavideo.LifecycleAwareVideoView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r1.<init>();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r1.wrap(r0);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = r7.mFragment;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        if (r2 == 0) goto L_0x0044;
    L_0x0033:
        r2 = r7.mFragment;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = r2.isAdded();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        if (r2 == 0) goto L_0x0044;
    L_0x003b:
        r2 = r7.mFragment;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = r2.getLifecycle();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2.a(r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
    L_0x0044:
        r1 = r11.getVurl();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r1 = r8.getStreamUrl(r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r8.setUrl(r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.setSource(r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = r11.getBusinessObjectId();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r3 = r11.getVideoType();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r3 = r7.getVideoType(r3);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.setVideoParams(r2, r3);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = r7.mContext;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = (com.gaana.GaanaActivity) r2;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.set_act(r2);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r2 = 1;
        r0.setVideoScalingMode(r2);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r3 = r7.width;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r4 = r8.videoView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r5 = r7.mContext;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9.setAutoPlayVideoWidthHeight(r3, r4, r5);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r7.setHeightWidth(r9, r10);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r3 = 0;
        r10.addView(r0, r3);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r4 = new android.widget.LinearLayout$LayoutParams;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r5 = r8.videoView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r5 = r5.getLayoutParams();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r5 = r5.width;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r6 = r8.videoView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r6 = r6.getLayoutParams();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r6 = r6.height;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r4.<init>(r5, r6);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r5 = 16;
        r4.gravity = r5;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.setLayoutParams(r4);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r8.initilisedVideoData(r0, r9, r10, r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r10.setTag(r11);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.requestFocus();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.g();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.setVideoScalingMode(r2);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.setLooping(r2);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.k();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r0.d();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r8.setPaused(r3);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9 = r8.getAah_vi();	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9.setTag(r11);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9 = r8.videoView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r10 = new com.gaana.adapter.SearchFeedAdapter$2;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r10.<init>(r8, r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9.setOnClickListener(r10);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9 = r8.itemView;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r10 = new com.gaana.adapter.SearchFeedAdapter$3;	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r10.<init>(r8, r1);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        r9.setOnClickListener(r10);	 Catch:{ ExceptionInInitializerError -> 0x00d7 }
        goto L_0x00db;
    L_0x00d7:
        r8 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
    L_0x00db:
        return;
    L_0x00dc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.adapter.SearchFeedAdapter.setUpAutoPlayVideo(com.gaana.adapter.SearchFeedAdapter$SearchFeedItemViewHolder, com.library.controls.RoundedCustomImageView, android.view.ViewGroup, com.gaana.models.NextGenSearchAutoSuggests$AutoComplete):void");
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return VIEW_TYPE_RECENT;
        }
        i--;
        if (((b) this.mView.get(i)).a().equalsIgnoreCase(this.VIEW_ONE)) {
            return VIEW_TYPE_ONE;
        }
        if (((b) this.mView.get(i)).a().equalsIgnoreCase(this.VIEW_TWO)) {
            return VIEW_TYPE_TWO;
        }
        if (((b) this.mView.get(i)).a().equalsIgnoreCase(this.VIEW_THREE)) {
            return VIEW_TYPE_THREE;
        }
        return VIEW_TYPE_FOUR;
    }

    public int getItemCount() {
        if (this.mView == null || this.mView.size() <= 0) {
            return 1;
        }
        return this.mView.size() + 1;
    }

    public void onClick(View view) {
        handleClick(view);
    }

    private void handleClick(View view) {
        GaanaApplication instance = GaanaApplication.getInstance();
        AutoComplete autoComplete = (AutoComplete) view.getTag();
        if (autoComplete.getType() == null && autoComplete.isRecentSearch()) {
            GaanaSearchManager.a().a(view);
            return;
        }
        BusinessObject a = Util.a(autoComplete, instance);
        an.a().a("click", "en", "", "SEARCH FEED", a.getBusinessObjId(), a.getBusinessObjType().name(), "", "");
        StringBuilder stringBuilder;
        if (autoComplete.getVurl() == null || TextUtils.isEmpty(autoComplete.getVurl())) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(a.getBusinessObjType());
            stringBuilder.append("");
            stringBuilder.append(a.getBusinessObjId());
            u.a().a("search feed", "click", stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Video-");
            stringBuilder.append(a.getBusinessObjId());
            u.a().a("search feed", "click", stringBuilder.toString());
        }
        if (autoComplete.getVurl() != null && !TextUtils.isEmpty(autoComplete.getVurl())) {
            ((YouTubeVideo) a).a(getVideoType(autoComplete.getVideoType()));
            Util.a(this.mContext, autoComplete.getVurl(), autoComplete.getVurl(), a, getVideoType(autoComplete.getVideoType()));
        } else if (a.getBusinessObjType() == BusinessObjectType.Radios) {
            af.a(this.mContext, this.mFragment).a((int) R.id.radioMenu, a);
        } else if (a.getBusinessObjType() == BusinessObjectType.Occasion) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://api.gaana.com/home/occasion/meta/v2/");
            stringBuilder2.append(autoComplete.getOccasionUrl());
            loadOccasionPage(stringBuilder2.toString());
        } else if (a.getBusinessObjType() == BusinessObjectType.Playlists) {
            af.a(this.mContext, this.mFragment).a((int) R.id.playlistMenu, a);
        } else if (a.getBusinessObjType() == BusinessObjectType.Albums) {
            af.a(this.mContext, this.mFragment).a((int) R.id.albumMenu, a);
        } else if (a.getBusinessObjType() == BusinessObjectType.Artists) {
            af.a(this.mContext, this.mFragment).a((int) R.id.artistMenu, a);
        } else if (a.getBusinessObjType() == BusinessObjectType.Tracks) {
            ((GaanaActivity) this.mContext).setCurrentSongSelectedView(view);
            c.a(this.mContext).a(this.mContext, a, SOURCE_TYPE.SEARCH.ordinal(), false);
        }
    }

    private void loadOccasionPage(final String str) {
        com.dynamicview.c.a().a(new ag() {
            public void onOccasionResponse() {
                BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("OCCASION_URL", str);
                bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                dynamicOccasionFragment.setArguments(bundle);
                ((GaanaActivity) SearchFeedAdapter.this.mContext).displayFragment(dynamicOccasionFragment);
            }

            public void onOccasionError() {
                aj.a().a(SearchFeedAdapter.this.mContext, SearchFeedAdapter.this.mContext.getResources().getString(R.string.error_download_no_internet));
            }
        }, str, null, false);
    }

    private boolean isMyPlaylist(BusinessObject businessObject) {
        return businessObject.getBusinessObjType() == BusinessObjectType.Playlists && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject);
    }

    private int getVideoType(String str) {
        if (str.equalsIgnoreCase("Y")) {
            return 0;
        }
        return str.equalsIgnoreCase("H") ? 2 : 1;
    }

    public void setIconclick(SearchFeedItemViewHolder searchFeedItemViewHolder, List<AutoComplete> list) {
        playIconClick(searchFeedItemViewHolder.view1.getIconImage(), (AutoComplete) list.get(0));
        playIconClick(searchFeedItemViewHolder.view2.getIconImage(), (AutoComplete) list.get(1));
        playIconClick(searchFeedItemViewHolder.view3.getIconImage(), (AutoComplete) list.get(2));
        playIconClick(searchFeedItemViewHolder.view4.getIconImage(), (AutoComplete) list.get(3));
        playIconClick(searchFeedItemViewHolder.view5.getIconImage(), (AutoComplete) list.get(4));
        playIconClick(searchFeedItemViewHolder.view6.getIconImage(), (AutoComplete) list.get(5));
        playIconClick(searchFeedItemViewHolder.view7.getIconImage(), (AutoComplete) list.get(6));
        playIconClick(searchFeedItemViewHolder.view8.getIconImage(), (AutoComplete) list.get(7));
    }

    private void playIconClick(ImageView imageView, AutoComplete autoComplete) {
        imageView.setOnClickListener(this.iconClick);
        imageView.setTag(autoComplete);
    }

    private void launchviewPlayerActivity(VideoPlayerAutoPlayView videoPlayerAutoPlayView, String str) {
        if (!Util.j(this.mFragment.getActivity()) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return;
        }
        int ordinal;
        AutoComplete autoComplete = (AutoComplete) videoPlayerAutoPlayView.getTag();
        Context context = this.mContext;
        String videoId = videoPlayerAutoPlayView.getVideoId();
        int videoType = videoPlayerAutoPlayView.getVideoType();
        long expiryTime = autoComplete.getExpiryTime();
        if (this.mFragment instanceof DynamicOccasionFragment) {
            ordinal = GAANA_VIDEO_SOURCE.OCCASION_PAGE.ordinal();
        } else {
            ordinal = GAANA_VIDEO_SOURCE.HOME_PAGE.ordinal();
        }
        Util.a(context, str, str, videoId, videoType, expiryTime, ordinal, videoPlayerAutoPlayView != null ? videoPlayerAutoPlayView.getResumeWindow() : -1, videoPlayerAutoPlayView != null ? videoPlayerAutoPlayView.getResumePosition() : -1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Video-");
        stringBuilder.append(videoPlayerAutoPlayView.getVideoId());
        u.a().a("search feed", "click", stringBuilder.toString());
    }

    public void setDynamicWidthHeight(SearchFeedItemViewHolder searchFeedItemViewHolder) {
        searchFeedItemViewHolder.view1.setHeightWidth(this.width, searchFeedItemViewHolder.view1, this.mContext);
        searchFeedItemViewHolder.view2.setHeightWidth(this.width, searchFeedItemViewHolder.view2, this.mContext);
        searchFeedItemViewHolder.view3.setHeightWidth(this.width, searchFeedItemViewHolder.view3, this.mContext);
        searchFeedItemViewHolder.view4.setHeightWidth(this.width, searchFeedItemViewHolder.view4, this.mContext);
        searchFeedItemViewHolder.view5.setHeightWidth(this.width, searchFeedItemViewHolder.view5, this.mContext);
        searchFeedItemViewHolder.view6.setHeightWidth(this.width, searchFeedItemViewHolder.view6, this.mContext);
        searchFeedItemViewHolder.view7.setHeightWidth(this.width, searchFeedItemViewHolder.view7, this.mContext);
        searchFeedItemViewHolder.view8.setHeightWidth(this.width, searchFeedItemViewHolder.view8, this.mContext);
    }

    public void setSearchTextView(TextView textView) {
        this.searchTextView = textView;
    }

    public TextView getSearchTextView() {
        return this.searchTextView;
    }

    public void clearData() {
        if (this.map != null) {
            this.map.clear();
        }
    }

    public void updateRecentSearchAdapter(ArrayList<AutoComplete> arrayList) {
        if (this.searchRecentAdapter != null) {
            this.searchRecentAdapter.updateAdapter(arrayList);
            this.searchRecentAdapter.notifyDataSetChanged();
            if (arrayList.size() == 0 && this.viewHolder != null) {
                this.viewHolder.recentSearches_text.setVisibility(8);
                this.viewHolder.recent_searches_title.setVisibility(8);
                this.viewHolder.more.setVisibility(8);
            }
        }
    }
}

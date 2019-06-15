package com.gaana.revampeddetail.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.StyleableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.URLUtil;
import android.widget.AbsListView.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request.Priority;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.b;
import com.collapsible_header.d;
import com.constants.Constants;
import com.constants.Constants.REVAMPED_DETAIL_SECTION_TYPE;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.constants.Constants.REVAMPED_DETAIL_VIEW_TYPE;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.CustomListDiffUtil;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.adapter.TagsAdapter;
import com.gaana.adapter.TagsAdapter.OnTagClickListener;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.revampeddetail.manager.LockableViewPager;
import com.gaana.revampeddetail.manager.RevampDetailAdManager;
import com.gaana.revampeddetail.manager.RevampedDetailObjectManager;
import com.gaana.revampeddetail.manager.RevampedDetailSectionDataManger;
import com.gaana.revampeddetail.manager.RevampedDetailSectionDataManger.SECTION_RESPONSE_STATE;
import com.gaana.revampeddetail.manager.RevampedDetailSectionDataManger.SectionDataResponseListener;
import com.gaana.revampeddetail.manager.RevampedDetailSectionDataManger.SectionResponse;
import com.gaana.revampeddetail.model.RevampedDetailObject.DetailArtistSection;
import com.gaana.revampeddetail.model.RevampedDetailObject.RevampedSectionData;
import com.gaana.revampeddetail.model.RevampedEntityFeedData;
import com.gaana.revampeddetail.model.RevampedEntityFeedData.EntityFeedData;
import com.gaana.revampeddetail.model.RevampedListAdasCard;
import com.gaana.revampeddetail.model.RevampedListAdasCard.AdCard;
import com.gaana.revampeddetail.model.RevampedSimilarAlbumEntityInfo;
import com.gaana.view.CustomListView;
import com.gaana.view.UpgradeHomeView;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.DetailListingHeaderHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.TwoGridItemHolder;
import com.gaana.view.item.DownloadAlbumItemView;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.gaana.view.item.DownloadSongsItemView.VideoItemViewHolder;
import com.gaana.view.item.NewGenericItemView;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;
import com.utilities.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RevampedDetailListAdapter extends Adapter<ViewHolder> {
    private static final int ITEM_TYPE_2x2GRID_SQUARE = 7;
    private static final int ITEM_TYPE_ADS = 10;
    private static final int ITEM_TYPE_CAROUSEL = 0;
    private static final int ITEM_TYPE_HEADER = 99;
    private static final int ITEM_TYPE_HOR_SCROLL_RECT_WITH_BOTTOM_TILE = 3;
    private static final int ITEM_TYPE_HOR_SCROLL_RECT_WITH_CENTER_TILE = 1;
    private static final int ITEM_TYPE_HOR_SCROLL_SQUARE_WITH_BOTTOM_TILE = 2;
    private static final int ITEM_TYPE_LIST_ARTIST_PAGER = 5;
    private static final int ITEM_TYPE_LIST_SPECIALS = 6;
    private static final int ITEM_TYPE_LIST_TRACK_TAG = 4;
    private static final int ITEM_TYPE_PROMOTION = 9;
    private static final int ITEM_TYPE_TEXT = 8;
    private static final int LIST_TOP_BANNER = 1001;
    private int BRAND_AD_POSITION = -1;
    private RevampDetailAdManager adManager;
    Callback callback;
    ImageView coachmarkImageView = null;
    int[] coords = null;
    private LayoutInflater inflater;
    boolean isCoachmarkDisplayed = false;
    LinearLayout mAdLinearLayout = null;
    private GaanaApplication mAppState;
    private Context mContext;
    private LinearLayout mContextSelectAllLayout;
    private int mCount;
    private CustomListAdapter mCustomListAdapter;
    private BaseGaanaFragment mFragment;
    private final View mHeaderView;
    private ListingComponents mListingComponents;
    private ArrayList<ListingFragment> mListingFragments;
    private RevampedDetailObjectManager mRevampDetailObjManager;
    private final d mScrollViewCallBacks;
    private RevampedDetailSectionDataManger mSectionDataManager;
    private int mTotalCount = 0;
    private SamplePagerAdapter pagerAdapter;
    private BusinessObject parentBusinessObject = null;
    RecycledViewPool recycledViewPool;
    ArrayList<RevampedSectionData> sectionDataArrayList;
    private ArrayList<Track> trackArrayList;
    private UpgradeHomeView upgradeHomeView;
    private LockableViewPager viewPager;

    public static class RevampedArtistPagerHolder extends ViewHolder {
        TabLayout mSlidingTabLayout;
        ViewPager viewPager;

        public RevampedArtistPagerHolder(View view) {
            super(view);
            this.viewPager = (ViewPager) view.findViewById(R.id.pager);
            this.mSlidingTabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        }
    }

    public static class RevampedList2x2GridItemHolder extends ViewHolder {
        RecyclerView radioGridView;

        public RevampedList2x2GridItemHolder(View view) {
            super(view);
            this.radioGridView = (RecyclerView) view.findViewById(R.id.radios_gridview);
        }
    }

    public static class RevampedListHorScrollItemHolder extends ViewHolder {
        TextView header;
        RecyclerView horScroll;

        public RevampedListHorScrollItemHolder(View view) {
            super(view);
            this.header = (TextView) view.findViewById(R.id.header);
            this.horScroll = (RecyclerView) view.findViewById(R.id.hor_scroll);
        }
    }

    public static class RevampedListPromotionItemHolder extends ViewHolder {
        TextView desc;
        CrossFadeImageView imageView;
        TextView title;

        public RevampedListPromotionItemHolder(View view) {
            super(view);
            this.imageView = (CrossFadeImageView) view.findViewById(R.id.text_image);
            this.title = (TextView) view.findViewById(R.id.text_title);
            this.desc = (TextView) view.findViewById(R.id.text_desc);
        }
    }

    public static class RevampedListTaggListItemHolder extends ViewHolder {
        LinearLayout select_all_layout;
        RecyclerView songList;
        RecyclerView tagList;

        public RevampedListTaggListItemHolder(View view) {
            super(view);
            this.tagList = (RecyclerView) view.findViewById(R.id.tags_recyclerview);
            this.songList = (RecyclerView) view.findViewById(R.id.songs_recyclerview);
            this.select_all_layout = (LinearLayout) view.findViewById(R.id.select_all_layout);
        }
    }

    public static class RevampedListTextItemHolder extends ViewHolder {
        TextView desc;
        TextView title;

        public RevampedListTextItemHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.text_title);
            this.desc = (TextView) view.findViewById(R.id.text_desc);
        }
    }

    class SamplePagerAdapter extends b {
        private FragmentManager mFragmentManager;
        private BusinessObject mParentBusinessObject;
        private int mScrollY;

        public SamplePagerAdapter(FragmentManager fragmentManager, BusinessObject businessObject) {
            super(fragmentManager);
            this.mFragmentManager = fragmentManager;
            this.mParentBusinessObject = businessObject;
        }

        public int getCount() {
            return RevampedDetailListAdapter.this.mListingComponents.c() != null ? RevampedDetailListAdapter.this.mListingComponents.c().size() : 0;
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) RevampedDetailListAdapter.this.mListingComponents.c().get(i)).d();
        }

        public void setScrollY(int i) {
            this.mScrollY = i;
        }

        /* Access modifiers changed, original: protected */
        public Fragment createItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            listingFragment.a(RevampedDetailListAdapter.this.mScrollViewCallBacks);
            ListingParams listingParams = new ListingParams();
            listingParams.a(8);
            if (i == 0 && this.mParentBusinessObject.getBusinessObjType() == BusinessObjectType.Artists) {
                listingParams.o(false);
            }
            listingParams.p(true);
            listingParams.e(false);
            listingParams.b(i);
            listingParams.f(false);
            listingParams.h(false);
            ((ListingButton) RevampedDetailListAdapter.this.mListingComponents.c().get(i)).c().a(Boolean.valueOf(1 ^ this.mParentBusinessObject.isLocalMedia()));
            listingParams.a((ListingButton) RevampedDetailListAdapter.this.mListingComponents.c().get(i));
            listingFragment.a(listingParams);
            listingFragment.a(RevampedDetailListAdapter.this.mFragment);
            if (RevampedDetailListAdapter.this.mListingFragments.size() > i) {
                RevampedDetailListAdapter.this.mListingFragments.set(i, listingFragment);
            } else {
                RevampedDetailListAdapter.this.mListingFragments.add(listingFragment);
            }
            return listingFragment;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            super.restoreState(parcelable, classLoader);
            if (parcelable != null) {
                Bundle bundle = (Bundle) ((Bundle) parcelable).getParcelable("superState");
                if (bundle != null) {
                    for (String str : bundle.keySet()) {
                        if (str.startsWith("f")) {
                            int parseInt = Integer.parseInt(str.substring(1));
                            Fragment fragment = this.mFragmentManager.getFragment(bundle, str);
                            if (fragment != null) {
                                while (RevampedDetailListAdapter.this.mListingFragments.size() <= parseInt) {
                                    RevampedDetailListAdapter.this.mListingFragments.add(null);
                                }
                                fragment.setMenuVisibility(false);
                                ListingFragment listingFragment = (ListingFragment) fragment;
                                listingFragment.a(RevampedDetailListAdapter.this.mScrollViewCallBacks);
                                RevampedDetailListAdapter.this.mListingFragments.set(parseInt, listingFragment);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setParentBusinessObject(BusinessObject businessObject) {
        this.parentBusinessObject = businessObject;
    }

    public RevampedDetailListAdapter(Context context, BaseGaanaFragment baseGaanaFragment, View view, d dVar) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mFragment = baseGaanaFragment;
        this.mAppState = GaanaApplication.getInstance();
        this.mSectionDataManager = new RevampedDetailSectionDataManger();
        this.mRevampDetailObjManager = ((RevampedDetailListing) this.mFragment).h();
        this.mHeaderView = view;
        this.mScrollViewCallBacks = dVar;
        this.recycledViewPool = new RecycledViewPool();
        showDownloadTrackCoachmark();
    }

    public void setData(ArrayList<RevampedSectionData> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            if (this.sectionDataArrayList == null) {
                this.sectionDataArrayList = new ArrayList();
            } else {
                this.sectionDataArrayList.clear();
            }
            this.sectionDataArrayList.addAll(arrayList);
            final ObservableRecyclerView a = ((RevampedDetailListing) this.mFragment).a();
            a.addOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        i = ((LinearLayoutManager) a.getLayoutManager()).findLastVisibleItemPosition();
                        for (int findFirstVisibleItemPosition = ((LinearLayoutManager) a.getLayoutManager()).findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= i; findFirstVisibleItemPosition++) {
                            if (a.findViewHolderForAdapterPosition(findFirstVisibleItemPosition) instanceof RevampedListTaggListItemHolder) {
                                RevampedDetailListAdapter.this.playAvailableVideos();
                            }
                        }
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                }
            });
            this.mCount = arrayList.size();
            notifyDataSetChanged();
        }
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 99;
        }
        i--;
        int section_type = ((RevampedSectionData) this.sectionDataArrayList.get(i)).getSection_type();
        i = ((RevampedSectionData) this.sectionDataArrayList.get(i)).getView_type();
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.PROMOTION.getNumVal()) {
            return 9;
        }
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.TEXT.getNumVal()) {
            return 8;
        }
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.HOR_SCROLL.getNumVal() && i == REVAMPED_DETAIL_VIEW_TYPE.SQUARE_TILE.getNumVal()) {
            return 2;
        }
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.LIST.getNumVal() && i == REVAMPED_DETAIL_VIEW_TYPE.TRACK_TAGGED_LIST.getNumVal()) {
            return 4;
        }
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.ADS.getNumVal()) {
            return 10;
        }
        if (section_type == REVAMPED_DETAIL_SECTION_TYPE.GRID2x2.getNumVal() && i == REVAMPED_DETAIL_VIEW_TYPE.SQUARE_TILE.getNumVal()) {
            return 7;
        }
        return (section_type == REVAMPED_DETAIL_SECTION_TYPE.LIST.getNumVal() && i == REVAMPED_DETAIL_VIEW_TYPE.ARTIST_PAGE_LIST.getNumVal()) ? 5 : section_type;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder detailListingHeaderHolder;
        if (i == 99) {
            detailListingHeaderHolder = new DetailListingHeaderHolder(this.mHeaderView);
        } else if (i == 9) {
            return new RevampedListPromotionItemHolder(this.inflater.inflate(R.layout.revamped_detail_list_item_promotion, viewGroup, false));
        } else {
            if (i == 8) {
                return new RevampedListTextItemHolder(this.inflater.inflate(R.layout.revamped_detail_list_item_text, viewGroup, false));
            }
            if (i == 2) {
                return new RevampedListHorScrollItemHolder(this.inflater.inflate(R.layout.revamped_detail_list_item_albumhorscroll, viewGroup, false));
            }
            ViewHolder revampedListTaggListItemHolder;
            if (i == 4) {
                revampedListTaggListItemHolder = new RevampedListTaggListItemHolder(this.inflater.inflate(R.layout.revamped_detail_list_item_list_tagtrack, viewGroup, false));
                ((RevampedListTaggListItemHolder) revampedListTaggListItemHolder).songList.setRecycledViewPool(this.recycledViewPool);
                return revampedListTaggListItemHolder;
            } else if (i == 10) {
                return new ItemAdViewHolder(this.inflater.inflate(R.layout.revamped_ad_layout, viewGroup, false));
            } else {
                if (i == 7) {
                    revampedListTaggListItemHolder = new RevampedList2x2GridItemHolder(this.inflater.inflate(R.layout.revamp_detail_list_item_2x2grid, viewGroup, false));
                    ((RevampedList2x2GridItemHolder) revampedListTaggListItemHolder).radioGridView.setRecycledViewPool(this.recycledViewPool);
                    return revampedListTaggListItemHolder;
                } else if (i == 5) {
                    revampedListTaggListItemHolder = new ItemAdViewHolder(this.inflater.inflate(R.layout.ad_layout, viewGroup, false));
                    revampedListTaggListItemHolder.itemView.getLayoutParams().height = 0;
                    return revampedListTaggListItemHolder;
                } else {
                    detailListingHeaderHolder = null;
                }
            }
        }
        return detailListingHeaderHolder;
    }

    public void updateTrackUI() {
        DiffUtil.calculateDiff(new CustomListDiffUtil(this.trackArrayList, this.trackArrayList, this.adManager)).dispatchUpdatesTo(this.mCustomListAdapter);
    }

    /* Access modifiers changed, original: 0000 */
    public void updateDownloadBtnImage(ImageView imageView, @StyleableRes int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        Drawable drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(i, -1));
        obtainStyledAttributes.recycle();
        imageView.setImageDrawable(drawable);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if (viewHolder == null || i == 0) {
            if (this.parentBusinessObject != null) {
                Context context;
                int i2;
                LinearLayout linearLayout = ((DetailListingHeaderHolder) viewHolder).headerContainer;
                final TextView textView = (TextView) linearLayout.findViewById(R.id.text_download_all);
                final ImageView imageView = (ImageView) linearLayout.findViewById(R.id.btn_action_download);
                if (this.parentBusinessObject instanceof Album) {
                    context = this.mContext;
                    i2 = R.string.download_album;
                } else {
                    context = this.mContext;
                    i2 = R.string.download_playlist;
                }
                CharSequence string = context.getString(i2);
                updateDownloadBtnImage(imageView, 44);
                final DownloadStatus h = DownloadManager.c().h(Integer.parseInt(this.parentBusinessObject.getBusinessObjId()));
                if (!this.mAppState.isAppInOfflineMode() && Util.j(this.mContext) && ((this.mAppState.getCurrentUser() == null || this.mAppState.getCurrentUser().getLoginStatus()) && h != null && ap.a().a(this.parentBusinessObject, null))) {
                    if (h != null) {
                        if (h == DownloadStatus.DOWNLOADED) {
                            string = this.mContext.getString(R.string.downloaded);
                            updateDownloadBtnImage(imageView, 7);
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            if (DownloadManager.c().w()) {
                                string = this.mContext.getString(this.parentBusinessObject instanceof Album ? R.string.downloading_album : R.string.downloading_playlist);
                                updateDownloadBtnImage(imageView, 8);
                            } else {
                                string = this.mContext.getString(R.string.queued);
                                updateDownloadBtnImage(imageView, 9);
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            string = this.mContext.getString(R.string.queued);
                            updateDownloadBtnImage(imageView, 9);
                        } else if (h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED) {
                            string = this.mContext.getString(R.string.resume_download);
                            updateDownloadBtnImage(imageView, 10);
                        } else if (h == DownloadStatus.TRIED_BUT_FAILED) {
                            string = this.mContext.getString(R.string.retry_download);
                            updateDownloadBtnImage(imageView, 10);
                        }
                    }
                } else if (ap.a().k() && h != null && h == DownloadStatus.DOWNLOADED) {
                    string = this.mContext.getString(R.string.download_expired);
                    updateDownloadBtnImage(imageView, 90);
                }
                if (textView != null) {
                    textView.setText(string);
                    textView.setIncludeFontPadding(false);
                    linearLayout.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (ap.a().k() && h != null && h == DownloadStatus.DOWNLOADED) {
                                Util.a(RevampedDetailListAdapter.this.mContext, null);
                                if (RevampedDetailListAdapter.this.parentBusinessObject instanceof Playlist) {
                                    u.a().a("Expired Download", "Click", "Playlist");
                                    return;
                                } else if (RevampedDetailListAdapter.this.parentBusinessObject instanceof Album) {
                                    u.a().a("Expired Download", "Click", "Album");
                                    return;
                                } else {
                                    return;
                                }
                            }
                            String str = "Download";
                            DownloadStatus h = DownloadManager.c().h(Integer.parseInt(RevampedDetailListAdapter.this.parentBusinessObject.getBusinessObjId()));
                            if (h != null && h == DownloadStatus.DOWNLOADED) {
                                str = "Delete Download";
                            }
                            ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).a(true, textView, imageView);
                            ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).a(str, true);
                            af.a(RevampedDetailListAdapter.this.mContext, RevampedDetailListAdapter.this.mFragment).a((int) R.id.downloadMenu, RevampedDetailListAdapter.this.parentBusinessObject);
                            an.a().a("click", "ac", RevampedDetailListAdapter.this.parentBusinessObject.getBusinessObjId(), "", "", "downloadall", "", "");
                        }
                    });
                }
            }
            return;
        }
        int i3 = 1;
        i--;
        RevampedSectionData revampedSectionData = (RevampedSectionData) this.sectionDataArrayList.get(i);
        if (revampedSectionData.getTracks() == null && (revampedSectionData.getDetail_artist_sections() == null || revampedSectionData.getDetail_artist_sections().size() <= 0)) {
            i3 = 0;
        }
        if (i3 == 0) {
            if (this.mSectionDataManager.addToDataRequestQueue(revampedSectionData, i, new SectionDataResponseListener() {
                public void onResponse(SectionResponse sectionResponse) {
                    if (sectionResponse.getState() == SECTION_RESPONSE_STATE.SUCCESS) {
                        RevampedDetailListAdapter.this.mSectionDataManager.setResponseStateDone(i);
                        RevampedDetailListAdapter.this.bindData(viewHolder, i);
                    }
                }
            }, this.mRevampDetailObjManager.isRefreshing())) {
                bindData(viewHolder, i);
            }
        } else if (viewHolder instanceof RevampedListTaggListItemHolder) {
            RevampedListTaggListItemHolder revampedListTaggListItemHolder = (RevampedListTaggListItemHolder) viewHolder;
            this.mContextSelectAllLayout = revampedListTaggListItemHolder.select_all_layout;
            final ArrayList tracks = revampedSectionData.getTracks();
            this.trackArrayList = tracks;
            final HashMap videoListMap = (this.mAppState.isVideoAutoplay() && Constants.cH && com.utilities.d.g() && this.mRevampDetailObjManager.getDetailObjectModel().getPlaylist() != null) ? this.mRevampDetailObjManager.getDetailObjectModel().getPlaylist().getVideoListMap() : null;
            final BaseItemView p = ((RevampedDetailListing) this.mFragment).p();
            this.mCustomListAdapter = new CustomListAdapter(this.mContext, null);
            if (p instanceof DownloadSongsItemView) {
                ((DownloadSongsItemView) p).setAutoPlayHashMap(((RevampedDetailListing) this.mFragment).n());
            }
            this.adManager = new RevampDetailAdManager();
            this.adManager.initAds(this.mContext, ((RevampedDetailListing) this.mFragment).s(), this.mFragment, tracks, 2);
            this.mCustomListAdapter.setParamaters(tracks.size() + this.adManager.getAdsListSize(), new IAddListItemView() {
                public void showHideEmtpyView(boolean z) {
                }

                public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
                    if (RevampedDetailListAdapter.this.adManager.isAdonThisPosition(i)) {
                        return RevampedDetailListAdapter.this.adManager.onBindViewHolder(i, viewHolder, viewGroup);
                    }
                    if (viewHolder instanceof VideoItemViewHolder) {
                        return p.getPoplatedView(viewHolder, (BusinessObject) tracks.get(RevampedDetailListAdapter.this.adManager.getPositionwrtAd(i)), viewGroup);
                    }
                    if (!al.a) {
                        viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
                            public boolean onLongClick(View view) {
                                RevampedDetailListAdapter.this.goToActionMode(view, tracks.size(), tracks);
                                RevampedDetailListAdapter.this.mCustomListAdapter.notifyDataSetChanged();
                                com.services.d.a().a("PREFERENCE_KEY_LONG_PRESS_INITIATED", true, false);
                                return true;
                            }
                        });
                    }
                    if (i == 0 && RevampedDetailListAdapter.this.coachmarkImageView == null) {
                        RevampedDetailListAdapter.this.coachmarkImageView = ((AlbumDetailItemHolder) viewHolder).downloadImage;
                        RevampedDetailListAdapter.this.setCoachmarkDisplayCoords(RevampedDetailListAdapter.this.coachmarkImageView);
                    }
                    return p.getPoplatedView(viewHolder, (BusinessObject) tracks.get(RevampedDetailListAdapter.this.adManager.getPositionwrtAd(i)), viewGroup);
                }

                public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                    if (i == 20) {
                        return new VideoItemViewHolder(p.createViewHolder(viewGroup, i, R.layout.listing_autoplay_video_view));
                    }
                    if (i < 2 || i >= RevampedDetailListAdapter.this.adManager.getListOfAdPosition().size() + 2) {
                        return new AlbumDetailItemHolder(p.createViewHolder(viewGroup, i));
                    }
                    return RevampedDetailListAdapter.this.adManager.createViewHolder(viewGroup, i);
                }

                public int getItemViewType(int i) {
                    if (RevampedDetailListAdapter.this.adManager.isAdonThisPosition(i)) {
                        return RevampedDetailListAdapter.this.adManager.getItemViewType(i);
                    }
                    return (videoListMap == null || videoListMap.get(((Track) tracks.get(RevampedDetailListAdapter.this.adManager.getPositionwrtAd(i))).getBusinessObjId()) == null || !((Boolean) videoListMap.get(((Track) tracks.get(RevampedDetailListAdapter.this.adManager.getPositionwrtAd(i))).getBusinessObjId())).booleanValue()) ? 1 : 20;
                }
            });
            revampedListTaggListItemHolder.songList.setNestedScrollingEnabled(false);
            revampedListTaggListItemHolder.songList.setAdapter(this.mCustomListAdapter);
            this.callback = new e(this.mCustomListAdapter);
            new ItemTouchHelper(this.callback).attachToRecyclerView(revampedListTaggListItemHolder.songList);
            this.mCustomListAdapter.notifyDataSetChanged();
            ArrayList arrayList = new ArrayList();
            tracks = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (this.mRevampDetailObjManager.getDetailType() == REVAMPED_DETAIL_TYPE.ALBUM.getNumVal()) {
                tracks = this.mRevampDetailObjManager.getDetailObjectModel().getAlbum().getTags();
                arrayList2 = this.mRevampDetailObjManager.getDetailObjectModel().getAlbum().getTopArtists();
                arrayList3 = this.mRevampDetailObjManager.getDetailObjectModel().getAlbum().getTopLanguages();
            } else if (this.mRevampDetailObjManager.getDetailType() == REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal()) {
                tracks = this.mRevampDetailObjManager.getDetailObjectModel().getPlaylist().getTags();
                arrayList2 = this.mRevampDetailObjManager.getDetailObjectModel().getPlaylist().getTopArtists();
                arrayList3 = this.mRevampDetailObjManager.getDetailObjectModel().getPlaylist().getTopLanguages();
            }
            if (tracks != null && tracks.size() > 0) {
                arrayList.addAll(tracks);
            }
            if (arrayList2 != null && arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
            if (arrayList3 != null && arrayList3.size() > 0) {
                arrayList.addAll(arrayList3);
            }
            if (!(Constants.aF == 0 || arrayList == null || arrayList.size() <= 0)) {
                TagsAdapter tagsAdapter = new TagsAdapter(arrayList, "English", R.layout.recyclerview_tags, this.mContext, new OnTagClickListener() {
                    public void onTagClick(ArrayList<BusinessObject> arrayList) {
                        if (RevampedDetailListAdapter.this.mFragment != null && RevampedDetailListAdapter.this.mFragment.isAdded()) {
                            ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).a((ArrayList) arrayList);
                            RevampedDetailListAdapter.this.adManager.initAdPositions();
                        }
                    }
                }, this.mFragment);
                revampedListTaggListItemHolder.tagList.setNestedScrollingEnabled(false);
                revampedListTaggListItemHolder.tagList.setAdapter(tagsAdapter);
            }
        } else {
            boolean z = viewHolder instanceof RevampedArtistPagerHolder;
        }
    }

    private void playAvailableVideos() {
        if (!((RevampedDetailListing) this.mFragment).isDetached()) {
            HashMap n = ((RevampedDetailListing) this.mFragment).n();
            if (n != null) {
                for (Integer intValue : n.keySet()) {
                    VideoPlayerAutoPlayView videoPlayerAutoPlayView = (VideoPlayerAutoPlayView) n.get(Integer.valueOf(intValue.intValue()));
                    Rect rect = new Rect();
                    videoPlayerAutoPlayView.getGlobalVisibleRect(rect);
                    if (rect.intersect(new Rect(0, 0, com.services.d.a().b(), com.services.d.a().c()))) {
                        videoPlayerAutoPlayView.g();
                    } else {
                        videoPlayerAutoPlayView.i();
                    }
                }
            }
        }
    }

    private void setCoachmarkDisplayCoords(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                RevampedDetailListAdapter.this.coords = new int[2];
                view.getLocationInWindow(RevampedDetailListAdapter.this.coords);
            }
        });
    }

    private void showDownloadTrackCoachmark() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (RevampedDetailListAdapter.this.coords != null) {
                    boolean b = com.services.d.a().b("DOWNLOAD_TRACK_COACHMARK", true, false);
                    com.services.d.a().a("DOWNLOAD_TRACK_COACHMARK", false, false);
                    if (ap.a().j() && b) {
                        Intent intent = new Intent(RevampedDetailListAdapter.this.mContext, VideoCoachmarkActivity.class);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(RevampedDetailListAdapter.this.coords[0]);
                        stringBuilder.append(",");
                        stringBuilder.append(RevampedDetailListAdapter.this.coords[1] - ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).m());
                        intent.putExtra("TargetCoords", stringBuilder.toString());
                        intent.putExtra("COACHMARK_VALUE", "DOWNLOAD_TRACK_COACHMARK");
                        ((GaanaActivity) RevampedDetailListAdapter.this.mContext).startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
                        ((GaanaActivity) RevampedDetailListAdapter.this.mContext).overridePendingTransition(17432576, 17432577);
                    }
                }
            }
        }, 800);
    }

    private void bindData(ViewHolder viewHolder, final int i) {
        SectionResponse sectionResponseForPosition = this.mSectionDataManager.getSectionResponseForPosition(i);
        if (sectionResponseForPosition != null && sectionResponseForPosition.getState() == SECTION_RESPONSE_STATE.DONE) {
            final EntityFeedData entityFeedData;
            if (viewHolder instanceof RevampedListPromotionItemHolder) {
                entityFeedData = (EntityFeedData) ((RevampedEntityFeedData) sectionResponseForPosition.getResponse()).getEntity_feed_data().get(0);
                RevampedListPromotionItemHolder revampedListPromotionItemHolder = (RevampedListPromotionItemHolder) viewHolder;
                revampedListPromotionItemHolder.imageView.bindImage(entityFeedData.getAtw());
                revampedListPromotionItemHolder.title.setText(entityFeedData.getFeed_card_heading());
                revampedListPromotionItemHolder.title.setTypeface(revampedListPromotionItemHolder.title.getTypeface(), 1);
                revampedListPromotionItemHolder.desc.setText(entityFeedData.getFeed_card_subheading());
                viewHolder.itemView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        RevampedDetailListAdapter.this.openExternalBrowser(entityFeedData.getFeed_card_url());
                    }
                });
            } else if (viewHolder instanceof RevampedListTextItemHolder) {
                entityFeedData = (EntityFeedData) ((RevampedEntityFeedData) sectionResponseForPosition.getResponse()).getEntity_feed_data().get(0);
                RevampedListTextItemHolder revampedListTextItemHolder = (RevampedListTextItemHolder) viewHolder;
                revampedListTextItemHolder.title.setText(entityFeedData.getFeed_card_heading());
                revampedListTextItemHolder.desc.setText(entityFeedData.getFeed_card_subheading());
            } else if (viewHolder instanceof RevampedListHorScrollItemHolder) {
                RevampedListHorScrollItemHolder revampedListHorScrollItemHolder = (RevampedListHorScrollItemHolder) viewHolder;
                revampedListHorScrollItemHolder.itemView.setVisibility(0);
                CharSequence string = this.mContext.getString(R.string.recommended_album);
                if (!(this.sectionDataArrayList.get(i) == null || TextUtils.isEmpty(((RevampedSectionData) this.sectionDataArrayList.get(i)).getSection_title()))) {
                    string = ((RevampedSectionData) this.sectionDataArrayList.get(i)).getSection_title();
                }
                revampedListHorScrollItemHolder.header.setText(string);
                revampedListHorScrollItemHolder.header.setTypeface(revampedListHorScrollItemHolder.header.getTypeface(), 1);
                revampedListHorScrollItemHolder.header.setAllCaps(true);
                RevampedSimilarAlbumEntityInfo revampedSimilarAlbumEntityInfo = (RevampedSimilarAlbumEntityInfo) sectionResponseForPosition.getResponse();
                if (revampedSimilarAlbumEntityInfo == null || revampedSimilarAlbumEntityInfo.getGeneric_entities() == null || revampedSimilarAlbumEntityInfo.getGeneric_entities().size() <= 0) {
                    revampedListHorScrollItemHolder.itemView.setVisibility(8);
                    return;
                }
                RevampedSimilarAlbumEntityAdapter revampedSimilarAlbumEntityAdapter = new RevampedSimilarAlbumEntityAdapter(this.mContext, this.mFragment);
                revampedSimilarAlbumEntityAdapter.setData(revampedSimilarAlbumEntityInfo.getGeneric_entities());
                revampedListHorScrollItemHolder.horScroll.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
                revampedListHorScrollItemHolder.horScroll.setNestedScrollingEnabled(false);
                revampedListHorScrollItemHolder.horScroll.setAdapter(revampedSimilarAlbumEntityAdapter);
                viewHolder.itemView.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).a().smoothScrollToPosition(i + 1);
                        } catch (Exception unused) {
                        }
                    }
                }, 200);
            } else if (viewHolder instanceof ItemAdViewHolder) {
                RevampedListAdasCard revampedListAdasCard = (RevampedListAdasCard) sectionResponseForPosition.getResponse();
                if (revampedListAdasCard != null && revampedListAdasCard.getAd_cards() != null && revampedListAdasCard.getAd_cards().size() > 0) {
                    Constants.j = ((AdCard) revampedListAdasCard.getAd_cards().get(0)).getAd_code();
                    this.BRAND_AD_POSITION = i + 1;
                    if (getAdLayout() == null || getAdLayout().getChildCount() <= 0) {
                        addChannelPageAd();
                        return;
                    }
                    ItemAdViewHolder itemAdViewHolder = (ItemAdViewHolder) viewHolder;
                    ((LinearLayout) itemAdViewHolder.itemView.findViewById(R.id.llNativeAdSlot)).removeAllViews();
                    if (getAdLayout().getParent() != null) {
                        ((ViewGroup) getAdLayout().getParent()).removeView(getAdLayout());
                    }
                    ((LinearLayout) itemAdViewHolder.itemView.findViewById(R.id.llNativeAdSlot)).addView(getAdLayout());
                }
            } else if (viewHolder instanceof RevampedList2x2GridItemHolder) {
                final Items items = (Items) sectionResponseForPosition.getResponse();
                BusinessObject s = ((RevampedDetailListing) this.mFragment).s();
                if (items != null && items.getArrListBusinessObj() != null && items.getArrListBusinessObj().size() > 0) {
                    final BaseItemView p = ((RevampedDetailListing) this.mFragment).p();
                    this.mCustomListAdapter = new CustomListAdapter(this.mContext, null, this.mFragment);
                    boolean z = s instanceof Radio;
                    if (z && ((Radio) s).getGaana_ad() == 1) {
                        this.mCustomListAdapter.setDFPBannerAdCode(com.managers.e.L);
                    } else {
                        this.mCustomListAdapter.setDFPBannerAdCode(com.managers.e.K);
                    }
                    if (z && ((Radio) s).getType().equalsIgnoreCase("RM")) {
                        this.mCustomListAdapter.setAdSectionName(Constants.dW);
                    } else if (((Radio) s).getType().equalsIgnoreCase("RL")) {
                        this.mCustomListAdapter.setAdSectionName(Constants.dX);
                    }
                    this.mCustomListAdapter.setShowRepetativeBannerAd(true);
                    this.mCustomListAdapter.setParamaters(items.getArrListBusinessObj().size() % 2 == 0 ? items.getArrListBusinessObj().size() / 2 : (items.getArrListBusinessObj().size() / 2) + 1, new IAddListItemView() {
                        public void showHideEmtpyView(boolean z) {
                        }

                        public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
                            if (viewHolder.getItemViewType() == 1001) {
                                return RevampedDetailListAdapter.this.upgradeHomeView.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).s());
                            }
                            return p.getPoplatedView(viewHolder, RevampedDetailListAdapter.this.getBusinessObj(i, items.getArrListBusinessObj()), viewGroup, false, Boolean.valueOf(false));
                        }

                        public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                            if (i != 1001) {
                                return new TwoGridItemHolder(p.createViewHolder(viewGroup, i, R.layout.grid_twoitem_view));
                            }
                            String str = "";
                            BusinessObject s = ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).s();
                            if (s != null && ((Radio) s).getType().equalsIgnoreCase("RM")) {
                                str = Constants.dV;
                            }
                            if (s != null && ((Radio) s).getType().equalsIgnoreCase("RL")) {
                                str = Constants.dU;
                            }
                            RevampedDetailListAdapter.this.upgradeHomeView = new UpgradeHomeView(RevampedDetailListAdapter.this.mContext, RevampedDetailListAdapter.this.mFragment, str);
                            return RevampedDetailListAdapter.this.upgradeHomeView.onCreateViewHolder(viewGroup, i);
                        }

                        public int getItemViewType(int i) {
                            return (i == 0 && ap.a().b(RevampedDetailListAdapter.this.mContext)) ? 1001 : 1;
                        }
                    });
                    RevampedList2x2GridItemHolder revampedList2x2GridItemHolder = (RevampedList2x2GridItemHolder) viewHolder;
                    revampedList2x2GridItemHolder.radioGridView.setLayoutManager(new LinearLayoutManager(this.mContext));
                    revampedList2x2GridItemHolder.radioGridView.setHasFixedSize(false);
                    revampedList2x2GridItemHolder.radioGridView.setAdapter(this.mCustomListAdapter);
                }
            }
        }
    }

    public RecyclerView getCurrentRecyclerView() {
        return (this.viewPager == null || this.mListingFragments.size() <= this.viewPager.getCurrentItem()) ? null : ((ListingFragment) this.mListingFragments.get(this.viewPager.getCurrentItem())).h().getCustomListView();
    }

    public View initViewPagerUI(ViewGroup viewGroup, TabLayout tabLayout) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.revamped_detail_list_item_artistpager, viewGroup, false);
        this.viewPager = (LockableViewPager) inflate.findViewById(R.id.pager);
        BusinessObject s = ((RevampedDetailListing) this.mFragment).s();
        this.mListingFragments = new ArrayList();
        this.mListingComponents = new ListingComponents();
        this.pagerAdapter = new SamplePagerAdapter(this.mFragment.getChildFragmentManager(), s);
        this.viewPager.setSwipeLocked(true);
        tabLayout.setupWithViewPager(this.viewPager);
        this.viewPager.setAdapter(this.pagerAdapter);
        this.viewPager.setOffscreenPageLimit(3);
        return inflate;
    }

    public void addArtistSectionPageronDetailPage(ViewGroup viewGroup, RevampedSectionData revampedSectionData) {
        final ArrayList detail_artist_sections = revampedSectionData.getDetail_artist_sections();
        BusinessObject s = ((RevampedDetailListing) this.mFragment).s();
        ListingComponents artistDetailsListingComp = getArtistDetailsListingComp(detail_artist_sections, false);
        artistDetailsListingComp.b(s.getName());
        artistDetailsListingComp.a(s);
        this.mAppState.setListingComponents(artistDetailsListingComp);
        this.mListingComponents = artistDetailsListingComp;
        this.pagerAdapter.notifyDataSetChanged();
        this.viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (RevampedDetailListAdapter.this.mListingFragments.size() > i) {
                    CustomListView h = ((ListingFragment) RevampedDetailListAdapter.this.mListingFragments.get(i)).h();
                    if (!(h == null || h.getCustomListView() == null)) {
                        h.getCustomListView().scrollToPosition(0);
                    }
                }
                ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).u();
                if (i < detail_artist_sections.size()) {
                    String str = "";
                    if (((DetailArtistSection) detail_artist_sections.get(i)).getSection_title().equalsIgnoreCase("Album")) {
                        str = "Go to Album";
                    } else if (((DetailArtistSection) detail_artist_sections.get(i)).getSection_title().equalsIgnoreCase("Playlist")) {
                        str = "Go to Playlist";
                    } else if (((DetailArtistSection) detail_artist_sections.get(i)).getSection_title().equalsIgnoreCase("Similar Artist")) {
                        str = "Similar Artist";
                    }
                    if (!(TextUtils.isEmpty(str) || RevampedDetailListAdapter.this.mFragment == null || !(RevampedDetailListAdapter.this.mFragment instanceof RevampedDetailListing))) {
                        ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).a(str, false);
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    ((RevampedDetailListing) RevampedDetailListAdapter.this.mFragment).u();
                }
            }
        });
    }

    public void refreshArtistPager() {
        if (this.mListingFragments != null) {
            Iterator it = this.mListingFragments.iterator();
            while (it.hasNext()) {
                ListingFragment listingFragment = (ListingFragment) it.next();
                if (listingFragment != null) {
                    listingFragment.refreshListView();
                }
            }
        }
    }

    public int getItemCount() {
        return this.mHeaderView != null ? this.mCount + 1 : this.mCount;
    }

    private LinearLayout getAdLayout() {
        if (this.mAdLinearLayout == null) {
            this.mAdLinearLayout = new LinearLayout(this.mContext);
            this.mAdLinearLayout.setLayoutParams(new LayoutParams(-1, -2));
            this.mAdLinearLayout.setGravity(17);
            this.mAdLinearLayout.setBackgroundColor(this.mContext.getResources().getColor(R.color.gaana_grey));
        }
        return this.mAdLinearLayout;
    }

    private void addChannelPageAd() {
        if (ap.a().p() && !TextUtils.isEmpty(Constants.j)) {
            String str = Constants.j;
            final PublisherAdView publisherAdView = new PublisherAdView(this.mContext);
            publisherAdView.setAdUnitId(str);
            AdSize[] adSizeArr = new AdSize[]{new AdSize(ModuleDescriptor.MODULE_VERSION, 100), new AdSize(ModuleDescriptor.MODULE_VERSION, 140), new AdSize(ModuleDescriptor.MODULE_VERSION, 150), new AdSize(340, 100), new AdSize(340, 140), new AdSize(340, 150), new AdSize(728, 100), new AdSize(728, 140), new AdSize(728, 150), new AdSize(468, 100), new AdSize(468, 140), new AdSize(468, 150)};
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(((RevampedDetailListing) this.mFragment).c);
            adsUJData.setAdUnitCode(str);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
            publisherAdView.setAdSizes(adSizeArr);
            publisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        if (RevampedDetailListAdapter.this.mAdLinearLayout == null) {
                            RevampedDetailListAdapter.this.mAdLinearLayout = RevampedDetailListAdapter.this.getAdLayout();
                        }
                        RevampedDetailListAdapter.this.mAdLinearLayout.removeAllViews();
                        RevampedDetailListAdapter.this.mAdLinearLayout.addView(publisherAdView);
                        if (RevampedDetailListAdapter.this.BRAND_AD_POSITION != -1) {
                            RevampedDetailListAdapter.this.notifyItemChanged(RevampedDetailListAdapter.this.BRAND_AD_POSITION);
                        }
                        if (adsUJData != null) {
                            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData != null) {
                try {
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                } catch (Exception unused) {
                    return;
                }
            }
            Location location = ((GaanaActivity) this.mContext).getLocation();
            if (location != null) {
                Builder builder = new Builder();
                if (this.mAppState.getNetworkExtrasBundle() != null) {
                    builder.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                publisherAdView.loadAd(builder.setLocation(location2).build());
                return;
            }
            Builder builder2 = new Builder();
            if (this.mAppState.getNetworkExtrasBundle() != null) {
                builder2.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.l(GaanaApplication.getContext()));
            stringBuilder2.append("Gaana ");
            builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
            publisherAdView.loadAd(builder2.build());
        }
    }

    public CustomListAdapter getCustomListAdapter() {
        return this.mCustomListAdapter;
    }

    public BusinessObject getBusinessObj(int i, ArrayList<Item> arrayList) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList2 = new ArrayList();
        i *= 2;
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 < arrayList.size()) {
                int i3 = i + i2;
                if (i3 < arrayList.size()) {
                    arrayList2.add(i2, arrayList.get(i3));
                }
            }
        }
        businessObject.setArrListBusinessObj(arrayList2);
        return businessObject;
    }

    public static ListingComponents getArtistDetailsListingComp(ArrayList<DetailArtistSection> arrayList, boolean z) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.artists_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                DetailArtistSection detailArtistSection = (DetailArtistSection) it.next();
                ListingButton listingButton = new ListingButton();
                listingButton.c(Constants.n());
                listingButton.c(true);
                URLManager uRLManager = new URLManager();
                uRLManager.a(Boolean.valueOf(true));
                uRLManager.a(Priority.HIGH);
                uRLManager.a(60);
                if (!(arrayList == null || TextUtils.isEmpty(detailArtistSection.getSection_data_url()))) {
                    if ("Track".equals(detailArtistSection.getSection_title())) {
                        uRLManager.a(BusinessObjectType.GenericItems);
                        uRLManager.a(true);
                        uRLManager.b(BusinessObjectType.Artists);
                        listingButton.a(resources.getString(R.string.songs));
                        uRLManager.a(detailArtistSection.getSection_data_url());
                        listingButton.a(uRLManager);
                        arrayList2.add(listingButton);
                    } else if ("Album".equals(detailArtistSection.getSection_title())) {
                        if (z) {
                            listingButton.c(DownloadAlbumItemView.class.getName());
                        } else {
                            listingButton.c(NewGenericItemView.class.getName());
                        }
                        listingButton.c(true);
                        if (z) {
                            uRLManager.a(BusinessObjectType.Albums);
                            uRLManager.b(BusinessObjectType.Artists);
                        } else {
                            uRLManager.a(BusinessObjectType.GenericItems);
                            uRLManager.b(BusinessObjectType.GenericItems);
                        }
                        listingButton.a(resources.getString(R.string.albums));
                        uRLManager.a(detailArtistSection.getSection_data_url());
                        listingButton.a(uRLManager);
                        arrayList2.add(listingButton);
                    } else if ("Playlist".equals(detailArtistSection.getSection_title())) {
                        if (z) {
                            listingButton.c(DownloadAlbumItemView.class.getName());
                        } else {
                            listingButton.c(NewGenericItemView.class.getName());
                        }
                        listingButton.c(true);
                        if (z) {
                            uRLManager.a(BusinessObjectType.Playlists);
                            uRLManager.b(BusinessObjectType.Artists);
                        } else {
                            uRLManager.a(BusinessObjectType.GenericItems);
                            uRLManager.b(BusinessObjectType.GenericItems);
                        }
                        listingButton.a(resources.getString(R.string.playlists));
                        uRLManager.a(detailArtistSection.getSection_data_url());
                        listingButton.a(uRLManager);
                        arrayList2.add(listingButton);
                    } else if ("Similar Artist".equals(detailArtistSection.getSection_title())) {
                        if (z) {
                            listingButton.c(DownloadAlbumItemView.class.getName());
                        } else {
                            listingButton.c(NewGenericItemView.class.getName());
                        }
                        listingButton.c(true);
                        if (z) {
                            uRLManager.a(BusinessObjectType.Artists);
                            uRLManager.b(BusinessObjectType.Artists);
                        } else {
                            uRLManager.a(BusinessObjectType.GenericItems);
                            uRLManager.b(BusinessObjectType.GenericItems);
                        }
                        listingButton.a(resources.getString(R.string.similar_artists));
                        uRLManager.a(detailArtistSection.getSection_data_url());
                        listingButton.a(uRLManager);
                        arrayList2.add(listingButton);
                    }
                }
            }
        }
        listingComponents.a(arrayList2);
        return listingComponents;
    }

    private void openExternalBrowser(String str) {
        if (!TextUtils.isEmpty(str) && URLUtil.isValidUrl(str)) {
            try {
                this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this.mContext, this.mContext.getString(R.string.error_generic_unableto_process), 1).show();
            }
        }
    }

    private void openLinkinWebView(String str, String str2) {
        Intent intent = new Intent(this.mContext, WebViewActivity.class);
        intent.putExtra("EXTRA_WEBVIEW_URL", str2);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
        intent.putExtra("title", str);
        this.mContext.startActivity(intent);
    }

    private void goToActionMode(View view, int i, final ArrayList<Track> arrayList) {
        if (this.mContextSelectAllLayout != null) {
            this.mContextSelectAllLayout.setVisibility(0);
            ((CheckBox) this.mContextSelectAllLayout.findViewById(R.id.select_all_checkbox)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (compoundButton.isPressed()) {
                        if (compoundButton.isChecked()) {
                            al.a().b(arrayList);
                        } else {
                            al.a().c();
                        }
                        RevampedDetailListAdapter.this.mCustomListAdapter.notifyDataSetChanged();
                        RevampedDetailListAdapter.this.updateSelectedCount();
                    }
                }
            });
        }
        createActionMode(view, i);
    }

    public void createActionMode(View view, int i) {
        this.mTotalCount = i;
        ((RevampedDetailListing) this.mFragment).i().showContextMenu(true);
        al.a().a(true);
        if (this.callback != null) {
            ((e) this.callback).b(false);
        }
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        updateSelectedCount();
        ((RevampedDetailListing) this.mFragment).j();
    }

    public void destroyActionMode() {
        this.mTotalCount = 0;
        ((RevampedDetailListing) this.mFragment).i().showContextMenu(false);
        al.a().a(false);
        al.a().c();
        this.mCustomListAdapter.notifyDataSetChanged();
        if (this.callback != null) {
            ((e) this.callback).b(true);
        }
        if (this.mContextSelectAllLayout != null) {
            this.mContextSelectAllLayout.setVisibility(8);
        }
    }

    public void updateSelectedCount() {
        ((RevampedDetailListing) this.mFragment).i().updateSelectedCountinContextMode(this.mTotalCount);
        if (this.mContextSelectAllLayout != null) {
            CheckBox checkBox = (CheckBox) this.mContextSelectAllLayout.findViewById(R.id.select_all_checkbox);
            if (al.a().e()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            if (al.a().f() == this.mTotalCount) {
                checkBox.setChecked(true);
            }
        }
    }
}

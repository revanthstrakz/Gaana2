package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f;
import com.fragments.BaseGaanaFragment;
import com.fragments.CuratedDownloadSuggestionFragment;
import com.fragments.GridActivityFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.Tracks;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.BaseItemView.CuratedDownloadSongSelectionHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.TrackSelectionForDownload;
import com.managers.TrackSelectionForDownload.DownloadSelectionType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.aq;
import com.managers.aq.a;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.l.am;
import com.services.l.o;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.c;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class CuratedDownloadScrollView extends BaseItemView implements a, o, s {
    private ArrayList<?> arrListBusinessObj = null;
    private GenericItemView genericItemView;
    private boolean hasDataLoaded = false;
    private boolean hasDataRetrieved = false;
    private HorizontalRecyclerView horizontalScroller;
    private long initialTime = 0;
    private boolean isAdtoBeRefreshed = false;
    private boolean isFirstCall = true;
    private boolean isRrefreshing = false;
    private BusinessObject mBusinessObject;
    private String mDisplayTitle;
    private DownloadSelectionType mDownloadSelectionType;
    private f.a mDynamicView;
    private int mEntityParentId = -1;
    private String mGATitle;
    private boolean mHideContent;
    private int mLayoutResourceId = R.layout.view_curated_scroll_container;
    private URLManager mURLManager = null;
    private View mView = null;
    private ViewHolder mViewHolder = null;
    private OnDataLoadedListener onDataLoadedListener = null;
    private am onSelectAllStatusChangeListener = new am() {
        public void onSelectAllStausChanged(boolean z) {
            int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
            TypedArray obtainStyledAttributes = CuratedDownloadScrollView.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            if (z) {
                ((ImageView) CuratedDownloadScrollView.this.mView.findViewById(R.id.select_icon)).setImageDrawable(ContextCompat.getDrawable(CuratedDownloadScrollView.this.getContext(), obtainStyledAttributes.getResourceId(85, -1)));
            } else {
                ((ImageView) CuratedDownloadScrollView.this.mView.findViewById(R.id.select_icon)).setImageDrawable(ContextCompat.getDrawable(CuratedDownloadScrollView.this.getContext(), obtainStyledAttributes.getResourceId(88, -1)));
            }
            obtainStyledAttributes.recycle();
        }
    };
    private boolean shouldReturnEmptyView = false;
    private int viewType = 6;

    public static class HorizontalViewHolder extends ViewHolder {
        public TextView headerSubtitleText;
        public TextView headerText;
        public HorizontalRecyclerView horizontalRecyclerView;
        public RelativeLayout parentLayout;
        public LinearLayout selectAllContainer;
        public ImageView selectIcon;

        public HorizontalViewHolder(View view) {
            super(view);
            this.parentLayout = (RelativeLayout) view.findViewById(R.id.layout_horzontal_scroll_container);
            this.selectIcon = (ImageView) view.findViewById(R.id.select_icon);
            this.selectAllContainer = (LinearLayout) view.findViewById(R.id.select_all_container);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.headerSubtitleText = (TextView) view.findViewById(R.id.f56header.text.subtitle);
            this.horizontalRecyclerView = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
            this.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        }

        public void setOrientation(int i) {
            if (this.horizontalRecyclerView != null) {
                this.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), i, false));
            }
        }
    }

    public CuratedDownloadScrollView(Context context, BaseGaanaFragment baseGaanaFragment, f.a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
        this.mGATitle = this.mDynamicView.k();
        this.mDisplayTitle = this.mDynamicView.u();
        this.mDownloadSelectionType = getDownloadSelectionType(aVar);
        createUrlManager();
    }

    private DownloadSelectionType getDownloadSelectionType(f.a aVar) {
        DownloadSelectionType downloadSelectionType = DownloadSelectionType.OTHERS;
        String p = aVar.p();
        if (p.equalsIgnoreCase(DownloadSelectionType.RECENTLY_PLAYED.name())) {
            return DownloadSelectionType.RECENTLY_PLAYED;
        }
        if (p.equalsIgnoreCase(DownloadSelectionType.DAILY_MIX.name())) {
            return DownloadSelectionType.DAILY_MIX;
        }
        if (p.equalsIgnoreCase(DownloadSelectionType.WEEKLY_MIX.name())) {
            return DownloadSelectionType.WEEKLY_MIX;
        }
        if (p.equalsIgnoreCase(DownloadSelectionType.LISTEN_AGAIN.name())) {
            return DownloadSelectionType.LISTEN_AGAIN;
        }
        if (p.equalsIgnoreCase(DownloadSelectionType.FAVORITE_SONGS.name())) {
            return DownloadSelectionType.FAVORITE_SONGS;
        }
        return p.equalsIgnoreCase(DownloadSelectionType.TRENDING_SONGS.name()) ? DownloadSelectionType.TRENDING_SONGS : downloadSelectionType;
    }

    private void createUrlManager() {
        this.mURLManager = new URLManager();
        this.mURLManager.a(this.mDynamicView.l());
        this.mURLManager.l(this.mDynamicView.z());
        if (this.mDynamicView.p().equalsIgnoreCase(DownloadSelectionType.FAVORITE_SONGS.name())) {
            this.mURLManager.a(BusinessObjectType.Tracks);
        } else {
            this.mURLManager.a(BusinessObjectType.GenericItems);
        }
    }

    private URLManager getSeeAllUrlManager() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.mDynamicView.o());
        if (this.mEntityParentId != -1 && uRLManager.k().contains("<entity_Parent_Id>")) {
            uRLManager.a(uRLManager.k().replace("<entity_Parent_Id>", String.valueOf(this.mEntityParentId)));
        }
        uRLManager.a(BusinessObjectType.GenericItems);
        return uRLManager;
    }

    public void setOnDataLoadedListener(OnDataLoadedListener onDataLoadedListener) {
        this.onDataLoadedListener = onDataLoadedListener;
    }

    public void setFirstCall(boolean z) {
        this.isFirstCall = z;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.mView = super.getNewView(this.mLayoutResourceId, viewGroup);
        return this.mView;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mViewHolder = new HorizontalViewHolder(getNewView(-1, viewGroup));
        if (!(this.mDynamicView == null || this.mDynamicView.c() == null)) {
            i.a().a(this.mDynamicView.c(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    ((HorizontalViewHolder) CuratedDownloadScrollView.this.mViewHolder).horizontalRecyclerView.setBackgroundDrawable(new BitmapDrawable(CuratedDownloadScrollView.this.getResources(), bitmap));
                }
            }, true);
        }
        ((HorizontalViewHolder) this.mViewHolder).horizontalRecyclerView.setAdapter(((HorizontalViewHolder) this.mViewHolder).horizontalRecyclerView.a(((HorizontalViewHolder) this.mViewHolder).itemView.getContext(), 0));
        inflateViewTypeforRecyclerGridItems(this.mViewHolder);
        return this.mViewHolder;
    }

    public void inflateViewTypeforRecyclerGridItems(ViewHolder viewHolder) {
        if (this.mDynamicView.s()) {
            inflateEmptyView(viewHolder);
            return;
        }
        hideHolderVisibility(viewHolder);
        this.mHideContent = true;
    }

    public boolean inflateEmptyView(ViewHolder viewHolder) {
        if (this.horizontalScroller == null) {
            this.horizontalScroller = ((HorizontalViewHolder) viewHolder).horizontalRecyclerView;
        }
        HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
        if (horizontalViewHolder.selectAllContainer != null) {
            horizontalViewHolder.selectAllContainer.setVisibility(8);
        }
        setHeader(this.mDisplayTitle, null);
        if (this.genericItemView == null) {
            this.genericItemView = new GenericItemView(this.mContext, this.mFragment);
        }
        this.horizontalScroller.setViewRecycleListner(this.viewType, 4, false, new c() {
            public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                viewHolder.itemView.setPadding((int) CuratedDownloadScrollView.this.getResources().getDimension(R.dimen.list_padding), 0, 0, 0);
                return CuratedDownloadScrollView.this.genericItemView.getEmptyView(viewHolder, (ViewGroup) viewHolder.itemView.getParent(), BusinessObjectType.GenericItems);
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                float dimension;
                float dimension2;
                if (viewHolder instanceof PlaylistGridHolder) {
                    PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
                    dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                    dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                    if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                    } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                    }
                    i2 = (int) dimension2;
                    LayoutParams layoutParams = new LayoutParams(i2, (int) dimension);
                    playlistGridHolder.crossFadeImageView.setLayoutParams(layoutParams);
                    playlistGridHolder.shadowLayer.setLayoutParams(layoutParams);
                    ((LayoutParams) playlistGridHolder.tvTopHeading.getLayoutParams()).width = i2;
                    return playlistGridHolder;
                } else if (!(viewHolder instanceof CuratedDownloadSongSelectionHolder)) {
                    return viewHolder;
                } else {
                    CuratedDownloadSongSelectionHolder curatedDownloadSongSelectionHolder = (CuratedDownloadSongSelectionHolder) viewHolder;
                    dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                    dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                    if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                    } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                    }
                    curatedDownloadSongSelectionHolder.crossFadeImageView.setLayoutParams(new LayoutParams((int) dimension2, (int) dimension));
                    return curatedDownloadSongSelectionHolder;
                }
            }
        });
        return false;
    }

    private void setTrackSelectionForDownload(ViewHolder viewHolder) {
        HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
        Object obj = (this.mDownloadSelectionType == DownloadSelectionType.RECENTLY_PLAYED || this.mDownloadSelectionType == DownloadSelectionType.LISTEN_AGAIN) ? 1 : null;
        int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        BaseGaanaFragment currentFragment;
        if (obj == null) {
            TrackSelectionForDownload.a().c(this.mDownloadSelectionType);
            currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
            if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                ((CuratedDownloadSuggestionFragment) currentFragment).a();
            }
            horizontalViewHolder.selectIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
        } else if (TrackSelectionForDownload.a().a(this.arrListBusinessObj, this.mDownloadSelectionType)) {
            currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
            if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                ((CuratedDownloadSuggestionFragment) currentFragment).a();
            }
            horizontalViewHolder.selectIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
        } else {
            horizontalViewHolder.selectIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
        }
        obtainStyledAttributes.recycle();
    }

    public f.a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
        this.mView = horizontalViewHolder.itemView;
        this.viewType = 6;
        horizontalViewHolder.selectAllContainer.setOnClickListener(this);
        this.horizontalScroller = horizontalViewHolder.horizontalRecyclerView;
        int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        if (TrackSelectionForDownload.a().e(this.mDownloadSelectionType)) {
            horizontalViewHolder.selectIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
        } else {
            horizontalViewHolder.selectIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
        }
        obtainStyledAttributes.recycle();
        this.mViewHolder = horizontalViewHolder;
        if (this.isFirstCall) {
            this.isFirstCall = false;
            retrieveFeedItem(this.mURLManager);
        }
        if (this.mURLManager == null) {
            this.shouldReturnEmptyView = true;
        } else if (this.mBusinessObject != null) {
            this.arrListBusinessObj = this.mBusinessObject.getArrListBusinessObj();
            this.shouldReturnEmptyView = onFeedRetrievalcompelete(this.mBusinessObject, viewHolder.itemView);
        } else if (this.hasDataRetrieved && this.mBusinessObject == null) {
            this.shouldReturnEmptyView = true;
        }
        if (this.shouldReturnEmptyView) {
            this.mView = new View(this.mContext);
        } else {
            this.mView.findViewById(R.id.f55header.text).setOnClickListener(this);
        }
        return this.mView;
    }

    public void retrieveFeedItem(URLManager uRLManager) {
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        this.isRrefreshing = uRLManager.m().booleanValue();
        if (this.mDynamicView.g() != null) {
            uRLManager.a(Integer.parseInt(this.mDynamicView.g()));
        }
        if (this.mDownloadSelectionType == DownloadSelectionType.RECENTLY_PLAYED) {
            aq.a().a(uRLManager, (a) this);
        } else if (this.mDownloadSelectionType == DownloadSelectionType.FAVORITE_SONGS) {
            URLManager uRLManager2 = uRLManager;
            i.a().b(this, uRLManager2, "", 0, 40, "added_on", "DESC");
        } else {
            i.a().a((s) this, uRLManager);
        }
    }

    private void setHeader(String str, String str2) {
        TextView textView = ((HorizontalViewHolder) this.mViewHolder).headerText;
        TextView textView2 = ((HorizontalViewHolder) this.mViewHolder).headerSubtitleText;
        textView.setGravity(16);
        if (str != null) {
            CharSequence str3;
            String str4 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str3.split("\\s");
                String str5 = str4;
                for (int i = 0; i < split.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str5);
                    stringBuilder.append(Character.toUpperCase(split[i].charAt(0)));
                    stringBuilder.append(split[i].substring(1));
                    str5 = stringBuilder.toString();
                    if (i < split.length - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str5);
                        stringBuilder.append(" ");
                        str5 = stringBuilder.toString();
                    }
                }
                str3 = str5;
            }
            textView.setText(str3);
            if (TextUtils.isEmpty(str2)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(str2);
            }
            if (this.mDynamicView.s() || !this.shouldReturnEmptyView) {
                textView.setVisibility(0);
                return;
            } else {
                textView.setVisibility(8);
                return;
            }
        }
        textView.setVisibility(8);
    }

    private void showHolderVisibility(ViewHolder viewHolder) {
        if (viewHolder != null) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
            if (!(horizontalViewHolder.selectAllContainer == null || horizontalViewHolder.selectAllContainer.getVisibility() == 0)) {
                horizontalViewHolder.selectAllContainer.setVisibility(0);
            }
            if (!(horizontalViewHolder.horizontalRecyclerView == null || horizontalViewHolder.horizontalRecyclerView.getVisibility() == 0)) {
                horizontalViewHolder.horizontalRecyclerView.setVisibility(0);
            }
            if (horizontalViewHolder.headerText != null && horizontalViewHolder.headerText.getVisibility() != 0) {
                horizontalViewHolder.headerText.setVisibility(0);
            }
        }
    }

    private void hideHolderVisibility(ViewHolder viewHolder) {
        if (viewHolder != null) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
            if (horizontalViewHolder.selectAllContainer != null) {
                horizontalViewHolder.selectAllContainer.setVisibility(8);
            }
            if (horizontalViewHolder.headerText != null) {
                horizontalViewHolder.headerText.setVisibility(8);
            }
            if (horizontalViewHolder.horizontalRecyclerView != null) {
                horizontalViewHolder.horizontalRecyclerView.setVisibility(8);
            }
        }
    }

    private boolean resetHolderData(BusinessObject businessObject) {
        if (this.horizontalScroller == null || businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            hideHolderVisibility(this.mViewHolder);
            return false;
        }
        this.mHideContent = false;
        String tagDescription;
        StringBuilder stringBuilder;
        String stringBuilder2;
        if (businessObject instanceof Items) {
            Items items = (Items) businessObject;
            this.mEntityParentId = items.getEntityParentId();
            this.mDynamicView.s();
            this.mDynamicView.h(items.getRawTagDescription());
            tagDescription = items.getTagDescription();
            stringBuilder = new StringBuilder();
            stringBuilder.append(businessObject.getArrListBusinessObj().size());
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getResources().getString(R.string.songs_available));
            stringBuilder2 = stringBuilder.toString();
            if (!TextUtils.isEmpty(tagDescription)) {
                this.mDisplayTitle = tagDescription;
                setHeader(tagDescription, stringBuilder2);
            }
        } else {
            tagDescription = this.mDisplayTitle;
            stringBuilder = new StringBuilder();
            stringBuilder.append(businessObject.getArrListBusinessObj().size());
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getResources().getString(R.string.songs_available));
            stringBuilder2 = stringBuilder.toString();
            if (!TextUtils.isEmpty(tagDescription)) {
                this.mDisplayTitle = tagDescription;
                setHeader(tagDescription, stringBuilder2);
            }
        }
        setTrackSelectionForDownload(this.mViewHolder);
        showHolderVisibility(this.mViewHolder);
        return true;
    }

    public void filterDownloadedTracks(BusinessObject businessObject) {
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        ArrayList arrayList = new ArrayList();
        Iterator it = arrListBusinessObj.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject2 = (BusinessObject) it.next();
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject2.getBusinessObjId()));
            if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED) {
                arrayList.add(businessObject2);
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
    }

    public boolean onFeedRetrievalcompelete(BusinessObject businessObject, View view) {
        if (resetHolderData(businessObject)) {
            return populateGenericViewForGaanaPlus(businessObject, view);
        }
        return false;
    }

    private boolean populateGenericViewForGaanaPlus(final BusinessObject businessObject, View view) {
        if (this.horizontalScroller == null) {
            return false;
        }
        if (this.arrListBusinessObj != null && this.arrListBusinessObj.size() > 0) {
            this.horizontalScroller.setViewRecycleListner(this.viewType, this.arrListBusinessObj.size(), false, new c() {
                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    DownloadSongsItemView downloadSongsItemView;
                    StringBuilder stringBuilder;
                    viewHolder.itemView.setPadding((int) CuratedDownloadScrollView.this.getResources().getDimension(R.dimen.list_padding), 0, 0, 0);
                    BusinessObject businessObject = (BusinessObject) CuratedDownloadScrollView.this.arrListBusinessObj.get(i3);
                    View view = null;
                    if (businessObject instanceof Item) {
                        Item item = (Item) businessObject;
                        if (item.getEntityType() != null) {
                            if (item.getEntityType().equals(com.constants.c.c.c)) {
                                downloadSongsItemView = new DownloadSongsItemView(CuratedDownloadScrollView.this.mContext, CuratedDownloadScrollView.this.mFragment);
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Curated Downloads");
                                stringBuilder.append(CuratedDownloadScrollView.this.mDynamicView.p());
                                downloadSongsItemView.setGAData(stringBuilder.toString(), CuratedDownloadScrollView.this.mGATitle, i3 + 1);
                                if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                                    downloadSongsItemView.setItemWithoutText(true);
                                } else {
                                    downloadSongsItemView.setItemWithoutText(false);
                                }
                                downloadSongsItemView.setSongsListBusinessObject(CuratedDownloadScrollView.this.arrListBusinessObj);
                                downloadSongsItemView.setIsSongSection();
                                downloadSongsItemView.setDownloadSelectionType(CuratedDownloadScrollView.this.mDownloadSelectionType);
                                view = downloadSongsItemView.getGridItemView(viewHolder, businessObject, CuratedDownloadScrollView.this.onSelectAllStatusChangeListener);
                            }
                            return view;
                        }
                    }
                    if (businessObject instanceof Tracks) {
                        downloadSongsItemView = new DownloadSongsItemView(CuratedDownloadScrollView.this.mContext, CuratedDownloadScrollView.this.mFragment);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Curated Downloads");
                        stringBuilder.append(CuratedDownloadScrollView.this.mDynamicView.p());
                        downloadSongsItemView.setGAData(stringBuilder.toString(), CuratedDownloadScrollView.this.mGATitle, i3 + 1);
                        if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                            downloadSongsItemView.setItemWithoutText(true);
                        } else {
                            downloadSongsItemView.setItemWithoutText(false);
                        }
                        downloadSongsItemView.setSongsListBusinessObject(CuratedDownloadScrollView.this.arrListBusinessObj);
                        downloadSongsItemView.setIsSongSection();
                        downloadSongsItemView.setDownloadSelectionType(CuratedDownloadScrollView.this.mDownloadSelectionType);
                        view = downloadSongsItemView.getGridItemView(viewHolder, businessObject, CuratedDownloadScrollView.this.onSelectAllStatusChangeListener);
                    }
                    return view;
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    float dimension;
                    float dimension2;
                    if (viewHolder instanceof PlaylistGridHolder) {
                        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                        } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        }
                        i2 = (int) dimension2;
                        LayoutParams layoutParams = new LayoutParams(i2, (int) dimension);
                        playlistGridHolder.crossFadeImageView.setLayoutParams(layoutParams);
                        playlistGridHolder.shadowLayer.setLayoutParams(layoutParams);
                        ((LayoutParams) playlistGridHolder.tvTopHeading.getLayoutParams()).width = i2;
                        return playlistGridHolder;
                    } else if (!(viewHolder instanceof CuratedDownloadSongSelectionHolder)) {
                        return viewHolder;
                    } else {
                        CuratedDownloadSongSelectionHolder curatedDownloadSongSelectionHolder = (CuratedDownloadSongSelectionHolder) viewHolder;
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                        } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        }
                        curatedDownloadSongSelectionHolder.crossFadeImageView.setLayoutParams(new LayoutParams((int) dimension2, (int) dimension));
                        return curatedDownloadSongSelectionHolder;
                    }
                }
            });
        }
        if (!this.hasDataLoaded) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.initialTime != 0) {
                long j = timeInMillis - this.initialTime;
                StringBuilder stringBuilder;
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(this.mGATitle);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                } else if (this.mFragment instanceof RadioActivityFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Radio ");
                    stringBuilder.append(this.mGATitle);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                }
                this.hasDataLoaded = true;
            }
        }
        setIsToBeRefreshed(false);
        return false;
    }

    private boolean populateGenericViewWithAds(BusinessObject businessObject) {
        if (this.horizontalScroller == null) {
            return false;
        }
        final long parseLong = (this.mDynamicView.q() == null || this.mDynamicView.q().isEmpty()) ? -1 : Long.parseLong(this.mDynamicView.q());
        if (this.arrListBusinessObj != null && this.arrListBusinessObj.size() > 0) {
            int size;
            boolean z;
            if (parseLong == -1 || this.arrListBusinessObj.size() < 3) {
                size = this.arrListBusinessObj.size();
                z = false;
            } else {
                size = this.arrListBusinessObj.size() + 1;
                z = true;
            }
            this.horizontalScroller.setViewRecycleListner(this.viewType, size, z, new c() {
                /* JADX WARNING: Removed duplicated region for block: B:22:0x00e0  */
                /* JADX WARNING: Removed duplicated region for block: B:21:0x00b3  */
                public android.view.View getCompatibleView(int r14, int r15, int r16, android.support.v7.widget.RecyclerView.ViewHolder r17) {
                    /*
                    r13 = this;
                    r0 = r13;
                    r1 = r15;
                    r6 = r16;
                    r3 = r17;
                    r2 = r9;
                    r4 = 2131165709; // 0x7f07020d float:1.7945643E38 double:1.0529357624E-314;
                    r5 = 0;
                    r7 = 1;
                    r10 = 0;
                    if (r2 == 0) goto L_0x018a;
                L_0x0010:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.genericItemView;
                    if (r2 != 0) goto L_0x002e;
                L_0x0018:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r8 = new com.gaana.view.item.GenericItemView;
                    r9 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r9 = r9.mContext;
                    r11 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r11 = r11.mFragment;
                    r8.<init>(r9, r11);
                    r2.genericItemView = r8;
                L_0x002e:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.genericItemView;
                    r8 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r8 = r8.mDynamicView;
                    r8 = r8.p();
                    r2.setSourceName(r8);
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r8 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r8 = r8.getNumVal();
                    if (r2 == r8) goto L_0x0074;
                L_0x0053:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r8 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r8 = r8.getNumVal();
                    if (r2 != r8) goto L_0x0066;
                L_0x0065:
                    goto L_0x0074;
                L_0x0066:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.genericItemView;
                    r8 = java.lang.Boolean.valueOf(r10);
                    r2.setItemWithoutText(r8);
                    goto L_0x0081;
                L_0x0074:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.genericItemView;
                    r8 = java.lang.Boolean.valueOf(r7);
                    r2.setItemWithoutText(r8);
                L_0x0081:
                    if (r6 <= 0) goto L_0x0095;
                L_0x0083:
                    if (r6 <= r1) goto L_0x0095;
                L_0x0085:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r8 = r6 + -1;
                    r2 = r2.get(r8);
                    r2 = (com.gaana.models.BusinessObject) r2;
                L_0x0093:
                    r8 = r2;
                    goto L_0x00b1;
                L_0x0095:
                    if (r6 >= r1) goto L_0x00b0;
                L_0x0097:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r2 = r2.size();
                    if (r6 >= r2) goto L_0x00b0;
                L_0x00a3:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r2 = r2.get(r6);
                    r2 = (com.gaana.models.BusinessObject) r2;
                    goto L_0x0093;
                L_0x00b0:
                    r8 = r5;
                L_0x00b1:
                    if (r6 != r1) goto L_0x00e0;
                L_0x00b3:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r4 = r3;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r7 = r2.isAdtoBeRefreshed;
                    r8 = 0;
                    r9 = r3.itemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r11 = r2;
                    r11 = (android.view.ViewGroup) r11;
                    r12 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r4;
                    r4 = r7;
                    r5 = r8;
                    r7 = r9;
                    r8 = r11;
                    r9 = r12;
                    r5 = r1.getPoplatedAdView(r2, r4, r5, r6, r7, r8, r9);
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1.isAdtoBeRefreshed = r10;
                    goto L_0x0189;
                L_0x00e0:
                    r1 = r8 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x0189;
                L_0x00e4:
                    r1 = r8;
                    r1 = (com.gaana.models.Item) r1;
                    r2 = r1.getEntityType();
                    if (r2 == 0) goto L_0x0189;
                L_0x00ed:
                    r2 = r3.itemView;
                    r5 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r5 = r5.getResources();
                    r4 = r5.getDimension(r4);
                    r4 = (int) r4;
                    r2.setPadding(r4, r10, r10, r10);
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x016c;
                L_0x0109:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mContext;
                    r4 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r4 = r4.mFragment;
                    r1.<init>(r2, r4);
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r4 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r4 = r4.mGATitle;
                    r5 = r6 + 1;
                    r1.setGAData(r2, r4, r5);
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 == r4) goto L_0x0158;
                L_0x0141:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 != r4) goto L_0x0154;
                L_0x0153:
                    goto L_0x0158;
                L_0x0154:
                    r1.setItemWithoutText(r10);
                    goto L_0x015b;
                L_0x0158:
                    r1.setItemWithoutText(r7);
                L_0x015b:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r1 = r1.getGridItemView(r3, r8);
                    goto L_0x0188;
                L_0x016c:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r5 = r2;
                    r5 = (android.view.ViewGroup) r5;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r7 = r2.mGATitle;
                    r2 = r6;
                    r4 = r8;
                    r6 = r7;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6);
                L_0x0188:
                    return r1;
                L_0x0189:
                    return r5;
                L_0x018a:
                    r1 = r3.itemView;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimension(r4);
                    r2 = (int) r2;
                    r1.setPadding(r2, r10, r10, r10);
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.arrListBusinessObj;
                    r1 = r1.get(r6);
                    r4 = r1;
                    r4 = (com.gaana.models.BusinessObject) r4;
                    r1 = r4 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x02b0;
                L_0x01ab:
                    r1 = r4;
                    r1 = (com.gaana.models.Item) r1;
                    r2 = r1.getEntityType();
                    if (r2 == 0) goto L_0x02b0;
                L_0x01b4:
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x0223;
                L_0x01c0:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mContext;
                    r5 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r5 = r5.mFragment;
                    r1.<init>(r2, r5);
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r5 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r5 = r5.mGATitle;
                    r6 = r6 + r7;
                    r1.setGAData(r2, r5, r6);
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r5 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r5 = r5.getNumVal();
                    if (r2 == r5) goto L_0x020e;
                L_0x01f7:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r5 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r5 = r5.getNumVal();
                    if (r2 != r5) goto L_0x020a;
                L_0x0209:
                    goto L_0x020e;
                L_0x020a:
                    r1.setItemWithoutText(r10);
                    goto L_0x0211;
                L_0x020e:
                    r1.setItemWithoutText(r7);
                L_0x0211:
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r1 = r1.getGridItemView(r3, r4);
                    goto L_0x02af;
                L_0x0223:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    if (r1 != 0) goto L_0x0241;
                L_0x022b:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = new com.gaana.view.item.GenericItemView;
                    r5 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r5 = r5.mContext;
                    r8 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r8 = r8.mFragment;
                    r2.<init>(r5, r8);
                    r1.genericItemView = r2;
                L_0x0241:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.mDynamicView;
                    r1 = r1.e();
                    r2 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r2 = r2.getNumVal();
                    if (r1 == r2) goto L_0x0274;
                L_0x0253:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.mDynamicView;
                    r1 = r1.e();
                    r2 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r2 = r2.getNumVal();
                    if (r1 != r2) goto L_0x0266;
                L_0x0265:
                    goto L_0x0274;
                L_0x0266:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = java.lang.Boolean.valueOf(r10);
                    r1.setItemWithoutText(r2);
                    goto L_0x0281;
                L_0x0274:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = java.lang.Boolean.valueOf(r7);
                    r1.setItemWithoutText(r2);
                L_0x0281:
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r1.setSourceName(r2);
                    r1 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r5 = r2;
                    r5 = (android.view.ViewGroup) r5;
                    r2 = com.gaana.view.item.CuratedDownloadScrollView.this;
                    r7 = r2.mGATitle;
                    r2 = r6;
                    r6 = r7;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6);
                L_0x02af:
                    return r1;
                L_0x02b0:
                    return r5;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.CuratedDownloadScrollView$AnonymousClass5.getCompatibleView(int, int, int, android.support.v7.widget.RecyclerView$ViewHolder):android.view.View");
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    float dimension;
                    float dimension2;
                    if (viewHolder instanceof PlaylistGridHolder) {
                        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                        } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        }
                        i2 = (int) dimension2;
                        LayoutParams layoutParams = new LayoutParams(i2, (int) dimension);
                        playlistGridHolder.crossFadeImageView.setLayoutParams(layoutParams);
                        playlistGridHolder.shadowLayer.setLayoutParams(layoutParams);
                        ((LayoutParams) playlistGridHolder.tvTopHeading.getLayoutParams()).width = i2;
                        return playlistGridHolder;
                    } else if (!(viewHolder instanceof CuratedDownloadSongSelectionHolder)) {
                        return viewHolder;
                    } else {
                        CuratedDownloadSongSelectionHolder curatedDownloadSongSelectionHolder = (CuratedDownloadSongSelectionHolder) viewHolder;
                        dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.home_playlist_width_height);
                        if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_rect_width);
                        } else if (CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || CuratedDownloadScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                            dimension = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                            dimension2 = CuratedDownloadScrollView.this.mContext.getResources().getDimension(R.dimen.img_occasion_horizontal_item_bigsquare_width_height);
                        }
                        curatedDownloadSongSelectionHolder.crossFadeImageView.setLayoutParams(new LayoutParams((int) dimension2, (int) dimension));
                        int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
                        TypedArray obtainStyledAttributes = CuratedDownloadScrollView.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        if (TrackSelectionForDownload.a().e(CuratedDownloadScrollView.this.mDownloadSelectionType)) {
                            curatedDownloadSongSelectionHolder.select_icon.setImageDrawable(ContextCompat.getDrawable(CuratedDownloadScrollView.this.getContext(), obtainStyledAttributes.getResourceId(88, -1)));
                        } else {
                            curatedDownloadSongSelectionHolder.select_icon.setImageDrawable(ContextCompat.getDrawable(CuratedDownloadScrollView.this.getContext(), obtainStyledAttributes.getResourceId(85, -1)));
                        }
                        obtainStyledAttributes.recycle();
                        return curatedDownloadSongSelectionHolder;
                    }
                }
            });
        }
        if (!this.hasDataLoaded) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.initialTime != 0) {
                long j = timeInMillis - this.initialTime;
                StringBuilder stringBuilder;
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(this.mGATitle);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                } else if (this.mFragment instanceof RadioActivityFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Radio ");
                    stringBuilder.append(this.mGATitle);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                }
                this.hasDataLoaded = true;
            }
        }
        setIsToBeRefreshed(false);
        return false;
    }

    public void setIsToBeRefreshed(boolean z) {
        if (this.mURLManager != null) {
            this.mURLManager.c(Boolean.valueOf(z));
            if (z) {
                this.isAdtoBeRefreshed = z;
                if (!(this.mHideContent || this.mView == null)) {
                    this.mView.findViewById(R.id.seeall).setVisibility(8);
                }
                retrieveFeedItem(this.mURLManager);
            }
        }
    }

    public void onRetreivalComplete(BusinessObject businessObject) {
        this.hasDataRetrieved = true;
        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            onFeedRetrievalcompelete(businessObject, this.mView);
        } else {
            if (this.onDataLoadedListener != null) {
                this.onDataLoadedListener.onDataLoaded(businessObject, BusinessObjectType.GenericItems);
            }
            filterDownloadedTracks(businessObject);
            this.mBusinessObject = businessObject;
            this.arrListBusinessObj = this.mBusinessObject.getArrListBusinessObj();
            if (this.arrListBusinessObj != null) {
                TrackSelectionForDownload.a().a(this.mDownloadSelectionType, this.arrListBusinessObj);
            }
            if (!this.isRrefreshing) {
                onFeedRetrievalcompelete(businessObject, this.mView);
            } else if (this.horizontalScroller != null) {
                this.horizontalScroller.setCount(this.arrListBusinessObj.size());
                if (resetHolderData(businessObject)) {
                    this.horizontalScroller.a();
                }
            }
        }
        setIsToBeRefreshed(false);
        this.isRrefreshing = false;
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.f55header.text || id == R.id.select_all_container || id == R.id.view1) {
            int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            BaseGaanaFragment currentFragment;
            if (TrackSelectionForDownload.a().e(this.mDownloadSelectionType)) {
                TrackSelectionForDownload.a().c(this.mDownloadSelectionType);
                currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                    ((CuratedDownloadSuggestionFragment) currentFragment).a();
                }
                ((ImageView) this.mView.findViewById(R.id.select_icon)).setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
            } else if (TrackSelectionForDownload.a().a(this.arrListBusinessObj, this.mDownloadSelectionType)) {
                currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                    ((CuratedDownloadSuggestionFragment) currentFragment).a();
                }
                ((ImageView) this.mView.findViewById(R.id.select_icon)).setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
            }
            obtainStyledAttributes.recycle();
            this.horizontalScroller.getAdapter().notifyDataSetChanged();
        }
    }

    private void seeAllDetails(URLManager uRLManager, String str) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (uRLManager != null) {
            if (this.mDynamicView.g() != null) {
                uRLManager.a(Integer.parseInt(this.mDynamicView.g()));
            }
            str = this.mDynamicView.r();
            BaseGaanaFragment gridActivityFragment;
            if (TextUtils.isEmpty(str) || str.equals(DynamicViewType.grid_rect.name()) || str.equals(DynamicViewType.grid.name())) {
                gridActivityFragment = new GridActivityFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("EXTRA_URL_MANAGER", uRLManager);
                bundle.putBoolean("EXTRA_SHOW_LOADMORE", this.mDynamicView.t());
                bundle.putString("EXTRA_GASECTION_NAME", this.mDynamicView.p());
                bundle.putString("EXTRA_ACTIONBAR_TITLE", this.mDynamicView.w());
                bundle.putString("EXTRA_GA_TITLE", this.mGATitle);
                bundle.putString("EXTRA_GRID_SEE_ALL_AD_CODE", this.mDynamicView.i());
                gridActivityFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(gridActivityFragment);
            } else {
                gridActivityFragment = new SongParallexListingFragment();
                ListingParams listingParams = new ListingParams();
                listingParams.e(false);
                listingParams.f(true);
                listingParams.h(false);
                listingParams.d(true);
                listingParams.i(false);
                listingParams.a(true);
                listingParams.a(this.mDynamicView.i());
                listingParams.b(this.mDynamicView.p());
                ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                listingButton.b(this.mDynamicView.w());
                listingButton.a(this.mDynamicView.w());
                URLManager c = listingButton.c();
                c.g(true);
                c.a(uRLManager.k());
                c.d(false);
                c.a(true);
                c.a(BusinessObjectType.GenericItems);
                uRLManager.h(true);
                listingParams.a(listingButton);
                gridActivityFragment.a(listingParams);
                ListingComponents listingComponents = new ListingComponents();
                new ArrayList().add(listingButton);
                this.mAppState.setListingComponents(listingComponents);
                ((GaanaActivity) this.mContext).displayFragment(gridActivityFragment);
            }
        }
    }

    public void onErrorResponse(BusinessObject businessObject) {
        ThrowableExtension.printStackTrace(businessObject.getVolleyError());
        onFeedRetrievalcompelete(businessObject, this.mView);
        this.isFirstCall = true;
    }

    public void notifyDataSetChanged() {
        if (this.horizontalScroller != null) {
            this.horizontalScroller.a();
        }
    }

    public void notifyItemChanged(int i) {
        if (this.horizontalScroller != null) {
            this.horizontalScroller.a(i);
        }
    }

    public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
        ArrayList b = aq.a().b();
        BusinessObject businessObject = new BusinessObject();
        businessObject.setBusinessObjId(userRecentActivity.getBusinessObjId());
        businessObject.setBusinessObjType(userRecentActivity.getBusinessObjType());
        businessObject.setArrList(b);
        onRetreivalComplete(businessObject);
    }

    public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
        BusinessObject businessObject = new BusinessObject();
        businessObject.setVolleyError(volleyError);
        onErrorResponse(businessObject);
    }
}

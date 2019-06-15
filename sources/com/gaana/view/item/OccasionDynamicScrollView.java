package com.gaana.view.item;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.dynamicview.h;
import com.fragments.BaseGaanaFragment;
import com.fragments.GridActivityFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.l.o;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.b;
import com.views.HorizontalRecyclerView.c;
import java.util.ArrayList;
import java.util.Calendar;

public class OccasionDynamicScrollView extends BaseItemView implements o, s {
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
    private a mDynamicView;
    private int mEntityParentId = -1;
    private String mGATitle;
    private boolean mHideContent;
    private int mLayoutResourceId = R.layout.view_horizontal_scroll_container;
    private boolean mShowViewAll = true;
    private URLManager mURLManager = null;
    private View mView = null;
    private HorizontalViewHolder mViewHolder = null;
    private OnDataLoadedListener onDataLoadedListener = null;
    private boolean shouldReturnEmptyView = false;
    private int viewType = 0;

    public static class HorizontalViewHolder extends ViewHolder {
        public TextView headerText;
        public HorizontalRecyclerView horizontalRecyclerView;
        public ImageView mImgMoreIcon;
        public ConstraintLayout parentLayout;
        public TextView seeAllText;

        public HorizontalViewHolder(View view) {
            super(view);
            this.parentLayout = (ConstraintLayout) view.findViewById(R.id.layout_horzontal_scroll_container);
            this.seeAllText = (TextView) view.findViewById(R.id.seeall);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.horizontalRecyclerView = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
            this.mImgMoreIcon = (ImageView) view.findViewById(R.id.seeallImg);
            this.horizontalRecyclerView.setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        double findLastCompletelyVisibleItemPosition = (double) (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1);
                        double itemCount = (double) recyclerView.getAdapter().getItemCount();
                        String a = an.a().a(an.a().a);
                        if (findLastCompletelyVisibleItemPosition > ((double) HorizontalViewHolder.this.horizontalRecyclerView.getCurrentScrollX())) {
                            String valueOf = String.valueOf((int) itemCount);
                            int i2 = (int) findLastCompletelyVisibleItemPosition;
                            an.a().c("scroll", AvidJSONUtil.KEY_X, "", a, "", "", valueOf, String.valueOf(i2));
                            HorizontalViewHolder.this.horizontalRecyclerView.setCurrentScrollX(i2);
                        }
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                }
            });
        }

        public void setOrientation(int i) {
            if (this.horizontalRecyclerView != null) {
                this.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), i, false));
            }
        }
    }

    public OccasionDynamicScrollView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
        this.mGATitle = this.mDynamicView.k();
        this.mDisplayTitle = this.mDynamicView.u();
        this.mShowViewAll = TextUtils.isEmpty(this.mDynamicView.o()) ^ 1;
        createUrlManager();
    }

    private void createUrlManager() {
        this.mURLManager = new URLManager();
        this.mURLManager.a(this.mDynamicView.l());
        this.mURLManager.l(this.mDynamicView.z());
        this.mURLManager.a(BusinessObjectType.GenericItems);
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

    public ArrayList<?> getArrListBusinessObj() {
        return this.arrListBusinessObj;
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
        boolean z = true;
        if (!(this.mDynamicView == null || TextUtils.isEmpty(this.mDynamicView.c()))) {
            i.a().a(this.mDynamicView.c(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    OccasionDynamicScrollView.this.mViewHolder.horizontalRecyclerView.setBackgroundDrawable(new BitmapDrawable(OccasionDynamicScrollView.this.getResources(), bitmap));
                }
            }, true);
        }
        if (this.mDynamicView == null || !this.mDynamicView.m().equals(DynamicViewType.double_scroll.name())) {
            z = false;
        }
        b a = this.mViewHolder.horizontalRecyclerView.a(this.mViewHolder.itemView.getContext(), 0);
        if (z) {
            this.mViewHolder.horizontalRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2, 0, false));
        } else {
            this.mViewHolder.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        }
        this.mViewHolder.horizontalRecyclerView.setAdapter(a);
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
        if (horizontalViewHolder.seeAllText != null) {
            horizontalViewHolder.seeAllText.setVisibility(8);
        }
        if (horizontalViewHolder.mImgMoreIcon != null) {
            horizontalViewHolder.mImgMoreIcon.setVisibility(8);
        }
        setHeader(this.mDisplayTitle, this.mDynamicView.A());
        if (this.genericItemView == null) {
            this.genericItemView = new GenericItemView(this.mContext, this.mFragment);
        }
        this.horizontalScroller.setViewRecycleListner(this.viewType, 4, false, new c() {
            public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                Resources resources;
                if (i3 == 0) {
                    resources = OccasionDynamicScrollView.this.mContext.getResources();
                    i2 = R.dimen.page_left_right_margin;
                } else {
                    resources = OccasionDynamicScrollView.this.mContext.getResources();
                    i2 = R.dimen.home_item_paddding;
                }
                viewHolder.itemView.setPadding(resources.getDimensionPixelSize(i2), 0, 0, 0);
                return OccasionDynamicScrollView.this.genericItemView.getEmptyView(viewHolder, (ViewGroup) viewHolder.itemView.getParent(), BusinessObjectType.GenericItems);
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                return i == 910 ? new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.a(OccasionDynamicScrollView.this.mDynamicView), viewGroup, false)) : viewHolder;
            }
        });
        return false;
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
        this.mView = horizontalViewHolder.itemView;
        this.horizontalScroller = horizontalViewHolder.horizontalRecyclerView;
        this.mViewHolder = horizontalViewHolder;
        if (this.isFirstCall) {
            this.isFirstCall = false;
            retrieveFeedItem(this.mURLManager);
        }
        this.mShowViewAll = TextUtils.isEmpty(this.mDynamicView.o()) ^ 1;
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
            this.mView.findViewById(R.id.seeall).setOnClickListener(this);
            this.mView.findViewById(R.id.seeallImg).setOnClickListener(this);
        }
        return this.mView;
    }

    public void retrieveFeedItem(URLManager uRLManager) {
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        this.isRrefreshing = uRLManager.m().booleanValue();
        if (this.mDynamicView.g() != null) {
            uRLManager.a(Integer.parseInt(this.mDynamicView.g()));
        }
        i.a().a((s) this, uRLManager);
    }

    private void setHeader(String str) {
        TextView textView = this.mViewHolder.headerText;
        textView.setGravity(16);
        if (str != null) {
            CharSequence str2;
            String str3 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str2.split("\\s");
                String str4 = str3;
                for (int i = 0; i < split.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str4);
                    stringBuilder.append(Character.toUpperCase(split[i].charAt(0)));
                    stringBuilder.append(split[i].substring(1));
                    str4 = stringBuilder.toString();
                    if (i < split.length - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str4);
                        stringBuilder.append(" ");
                        str4 = stringBuilder.toString();
                    }
                }
                str2 = str4;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, str2.length(), 17);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str2.length(), 17);
            textView.setText(spannableStringBuilder);
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

    private void setHeader(String str, String str2) {
        TextView textView = this.mViewHolder.headerText;
        if (TextUtils.isEmpty(str2)) {
            setHeader(str);
            return;
        }
        textView.setMaxLines(2);
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
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
            spannableStringBuilder.append("\n").append(str2);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_secondline);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str3.length(), 17);
            spannableStringBuilder.setSpan(styleSpan, 0, str3.length(), 17);
            spannableStringBuilder.setSpan(textAppearanceSpan2, str3.length(), (str3.length() + str2.length()) + 1, 18);
            textView.setText(spannableStringBuilder);
            return;
        }
        textView.setVisibility(8);
    }

    private void showHolderVisibility(ViewHolder viewHolder) {
        if (viewHolder != null) {
            HorizontalViewHolder horizontalViewHolder;
            if (viewHolder.itemView.getLayoutParams().height == 0) {
                viewHolder.itemView.getLayoutParams().height = -2;
                if (viewHolder.itemView.getLayoutParams() instanceof LayoutParams) {
                    ((LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half);
                }
                viewHolder.itemView.requestLayout();
            }
            int i = 8;
            if (!Constants.aV) {
                horizontalViewHolder = (HorizontalViewHolder) viewHolder;
                if (!(horizontalViewHolder.seeAllText == null || horizontalViewHolder.seeAllText.getVisibility() == 0)) {
                    horizontalViewHolder.seeAllText.setVisibility(this.mShowViewAll ? 0 : 8);
                }
            }
            if (Constants.aV) {
                horizontalViewHolder = (HorizontalViewHolder) viewHolder;
                if (!(horizontalViewHolder.mImgMoreIcon == null || horizontalViewHolder.mImgMoreIcon.getVisibility() == 0)) {
                    ImageView imageView = horizontalViewHolder.mImgMoreIcon;
                    if (this.mShowViewAll) {
                        i = 0;
                    }
                    imageView.setVisibility(i);
                }
            }
            HorizontalViewHolder horizontalViewHolder2 = (HorizontalViewHolder) viewHolder;
            if (!(horizontalViewHolder2.horizontalRecyclerView == null || horizontalViewHolder2.horizontalRecyclerView.getVisibility() == 0)) {
                horizontalViewHolder2.horizontalRecyclerView.setVisibility(0);
            }
            if (horizontalViewHolder2.headerText != null && horizontalViewHolder2.headerText.getVisibility() != 0) {
                horizontalViewHolder2.headerText.setVisibility(0);
            }
        }
    }

    private void hideHolderVisibility(ViewHolder viewHolder) {
        if (viewHolder != null) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
            if (horizontalViewHolder.seeAllText != null) {
                horizontalViewHolder.seeAllText.setVisibility(8);
            }
            if (horizontalViewHolder.mImgMoreIcon != null) {
                horizontalViewHolder.mImgMoreIcon.setVisibility(8);
            }
            if (horizontalViewHolder.headerText != null) {
                horizontalViewHolder.headerText.setVisibility(8);
            }
            if (horizontalViewHolder.horizontalRecyclerView != null) {
                horizontalViewHolder.horizontalRecyclerView.setVisibility(8);
            }
            if (viewHolder.itemView.getLayoutParams().height != 0) {
                viewHolder.itemView.getLayoutParams().height = 0;
                if (viewHolder.itemView.getLayoutParams() instanceof LayoutParams) {
                    ((LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = 0;
                }
                viewHolder.itemView.requestLayout();
            }
        }
    }

    private boolean resetHolderData(BusinessObject businessObject) {
        if (this.horizontalScroller == null || businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            hideHolderVisibility(this.mViewHolder);
            return false;
        }
        this.mHideContent = false;
        if (businessObject instanceof Items) {
            Items items = (Items) businessObject;
            this.mEntityParentId = items.getEntityParentId();
            this.mDynamicView.s();
            this.mDynamicView.h(items.getRawTagDescription());
            String tagDescription = items.getTagDescription();
            String pageTitle = !TextUtils.isEmpty(items.getPageTitle()) ? items.getPageTitle() : this.mDynamicView.A();
            if (!TextUtils.isEmpty(tagDescription)) {
                this.mDisplayTitle = tagDescription;
                setHeader(tagDescription, pageTitle);
            }
        }
        showHolderVisibility(this.mViewHolder);
        return true;
    }

    public boolean onFeedRetrievalcompelete(BusinessObject businessObject, View view) {
        if (resetHolderData(businessObject)) {
            return populateGenericViewForGaanaPlus(businessObject, view);
        }
        return false;
    }

    private boolean populateGenericViewForGaanaPlus(BusinessObject businessObject, View view) {
        if (this.horizontalScroller == null) {
            return false;
        }
        if (this.arrListBusinessObj != null && this.arrListBusinessObj.size() > 0) {
            int i;
            boolean z;
            int size = this.arrListBusinessObj.size();
            if (this.mDynamicView == null || TextUtils.isEmpty(this.mDynamicView.c())) {
                i = size;
                z = false;
            } else {
                i = size + 1;
                z = true;
            }
            this.horizontalScroller.setViewRecycleListner(this.viewType, i, false, new HorizontalRecyclerView.a() {
                public int getItemViewType(int i) {
                    Item item = (i < 0 || i >= OccasionDynamicScrollView.this.arrListBusinessObj.size() || !(OccasionDynamicScrollView.this.arrListBusinessObj.get(i) instanceof Item)) ? null : (Item) OccasionDynamicScrollView.this.arrListBusinessObj.get(i);
                    if (item == null || !item.getEntityType().equals(com.constants.c.c.h)) {
                        return h.a(OccasionDynamicScrollView.this.mDynamicView);
                    }
                    return h.c(OccasionDynamicScrollView.this.mDynamicView);
                }

                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    boolean equals = OccasionDynamicScrollView.this.mDynamicView.m().equals(DynamicViewType.double_scroll.name());
                    int i4 = (equals ? i % 2 != 0 ? i3 != i - 1 : !(i3 == i - 1 || i3 == i - 2) : i3 != i - 1) ? 0 : 1;
                    i = !equals ? i3 == 0 ? OccasionDynamicScrollView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : OccasionDynamicScrollView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding) : i3 / 2 == 0 ? OccasionDynamicScrollView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : OccasionDynamicScrollView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
                    viewHolder.itemView.setPadding(i, 0, i4 != 0 ? OccasionDynamicScrollView.this.mContext.getResources().getDimensionPixelSize(R.dimen.home_item_paddding) : 0, 0);
                    if (z && i3 == 0) {
                        if (OccasionDynamicScrollView.this.genericItemView == null) {
                            OccasionDynamicScrollView.this.genericItemView = new GenericItemView(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment);
                        }
                        return OccasionDynamicScrollView.this.genericItemView.getPopulatedBlankView(viewHolder);
                    }
                    BusinessObject businessObject;
                    if (!z || i3 <= 0) {
                        businessObject = (BusinessObject) OccasionDynamicScrollView.this.arrListBusinessObj.get(i3);
                    } else {
                        businessObject = (BusinessObject) OccasionDynamicScrollView.this.arrListBusinessObj.get(i3 - 1);
                    }
                    BusinessObject businessObject2 = businessObject;
                    String h = com.dynamicview.c.a().h();
                    if (businessObject2 instanceof Item) {
                        Item item = (Item) businessObject2;
                        if (item.getEntityType() != null) {
                            View gridItemView;
                            if (item.getEntityType().equals(com.constants.c.c.h) || item.getEntityType().equals(com.constants.c.c.e)) {
                                e.a().a(viewHolder.itemView, OccasionDynamicScrollView.this.mContext, item);
                            }
                            ImageView imageView;
                            if (item.getEntityType().equals(com.constants.c.c.c)) {
                                DownloadSongsItemView downloadSongsItemView = new DownloadSongsItemView(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment);
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(h);
                                stringBuilder.append("_");
                                stringBuilder.append(OccasionDynamicScrollView.this.mDynamicView.p());
                                downloadSongsItemView.setGAData(stringBuilder.toString(), OccasionDynamicScrollView.this.mGATitle, i3 + 1);
                                if (OccasionDynamicScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || OccasionDynamicScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                                    downloadSongsItemView.setItemWithoutText(true);
                                } else {
                                    downloadSongsItemView.setItemWithoutText(false);
                                }
                                downloadSongsItemView.setSongsListBusinessObject(OccasionDynamicScrollView.this.arrListBusinessObj);
                                downloadSongsItemView.setIsSongSection();
                                if ((viewHolder instanceof PlaylistGridHolder) && OccasionDynamicScrollView.this.mDynamicView.C()) {
                                    imageView = ((PlaylistGridHolder) viewHolder).shareIcon;
                                    imageView.setVisibility(0);
                                    imageView.setTag(businessObject2);
                                    imageView.setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            af.a(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment).a((int) R.id.shareMenu, Util.g((Item) ((BusinessObject) view.getTag())));
                                        }
                                    });
                                }
                                gridItemView = downloadSongsItemView.getGridItemView(viewHolder, businessObject2);
                            } else if (item.getEntityType().equals(com.constants.c.c.h)) {
                                if (OccasionDynamicScrollView.this.genericItemView == null) {
                                    OccasionDynamicScrollView.this.genericItemView = new GenericItemView(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment);
                                }
                                gridItemView = OccasionDynamicScrollView.this.genericItemView.getAdView(i3, viewHolder, businessObject2, (ViewGroup) viewHolder.itemView.getParent(), OccasionDynamicScrollView.this.mDynamicView.k());
                            } else {
                                if (OccasionDynamicScrollView.this.genericItemView == null) {
                                    OccasionDynamicScrollView.this.genericItemView = new GenericItemView(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment);
                                }
                                if (OccasionDynamicScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || OccasionDynamicScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                                    OccasionDynamicScrollView.this.genericItemView.setItemWithoutText(Boolean.valueOf(true));
                                } else {
                                    OccasionDynamicScrollView.this.genericItemView.setItemWithoutText(Boolean.valueOf(false));
                                }
                                GenericItemView access$300 = OccasionDynamicScrollView.this.genericItemView;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(h);
                                stringBuilder2.append("_");
                                stringBuilder2.append(OccasionDynamicScrollView.this.mDynamicView.p());
                                access$300.setSourceName(stringBuilder2.toString());
                                if ((viewHolder instanceof PlaylistGridHolder) && OccasionDynamicScrollView.this.mDynamicView.C()) {
                                    imageView = ((PlaylistGridHolder) viewHolder).shareIcon;
                                    imageView.setVisibility(0);
                                    imageView.setTag(businessObject2);
                                    imageView.setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            BusinessObject businessObject = (BusinessObject) view.getTag();
                                            Item item = (Item) businessObject;
                                            if (item.getEntityType().equals(com.constants.c.c.b)) {
                                                businessObject = Util.c(item);
                                            } else if (item.getEntityType().equals(com.constants.c.c.a)) {
                                                businessObject = Util.b(item);
                                            }
                                            af.a(OccasionDynamicScrollView.this.mContext, OccasionDynamicScrollView.this.mFragment).a((int) R.id.shareMenu, businessObject);
                                        }
                                    });
                                }
                                gridItemView = OccasionDynamicScrollView.this.genericItemView.getPoplatedGenericView(i3, viewHolder, businessObject2, (ViewGroup) viewHolder.itemView.getParent(), OccasionDynamicScrollView.this.mGATitle);
                            }
                            return gridItemView;
                        }
                    }
                    return null;
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    if (i == 910) {
                        return new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.a(OccasionDynamicScrollView.this.mDynamicView), viewGroup, false));
                    }
                    return i == R.layout.item_playlist_grid_ad_140x140 ? new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false)) : viewHolder;
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
            boolean z2;
            if (parseLong == -1 || this.arrListBusinessObj.size() < 3) {
                size = this.arrListBusinessObj.size();
                z = false;
            } else {
                size = this.arrListBusinessObj.size() + 1;
                z = true;
            }
            if (this.mDynamicView == null || TextUtils.isEmpty(this.mDynamicView.c())) {
                z2 = false;
            } else {
                size++;
                z2 = true;
            }
            final boolean z3 = z;
            final int i = size;
            this.horizontalScroller.setViewRecycleListner(this.viewType, size, z, new c() {
                /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
                /* JADX WARNING: Removed duplicated region for block: B:52:0x0150  */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x0183  */
                /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
                /* JADX WARNING: Removed duplicated region for block: B:62:0x0192  */
                /* JADX WARNING: Removed duplicated region for block: B:52:0x0150  */
                /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x0183  */
                /* JADX WARNING: Removed duplicated region for block: B:62:0x0192  */
                /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
                /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
                /* JADX WARNING: Removed duplicated region for block: B:52:0x0150  */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x0183  */
                /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
                /* JADX WARNING: Removed duplicated region for block: B:62:0x0192  */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x0136  */
                /* JADX WARNING: Removed duplicated region for block: B:39:0x012d  */
                /* JADX WARNING: Removed duplicated region for block: B:52:0x0150  */
                /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x0183  */
                /* JADX WARNING: Removed duplicated region for block: B:62:0x0192  */
                /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
                public android.view.View getCompatibleView(int r14, int r15, int r16, android.support.v7.widget.RecyclerView.ViewHolder r17) {
                    /*
                    r13 = this;
                    r0 = r13;
                    r1 = r15;
                    r6 = r16;
                    r3 = r17;
                    r2 = r7;
                    if (r2 == 0) goto L_0x0035;
                L_0x000a:
                    if (r6 != 0) goto L_0x0035;
                L_0x000c:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    if (r1 != 0) goto L_0x002a;
                L_0x0014:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = new com.gaana.view.item.GenericItemView;
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mContext;
                    r5 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r5 = r5.mFragment;
                    r2.<init>(r4, r5);
                    r1.genericItemView = r2;
                L_0x002a:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r1 = r1.getPopulatedBlankView(r3);
                    return r1;
                L_0x0035:
                    r2 = r8;
                    r4 = 0;
                    r5 = 2131165779; // 0x7f070253 float:1.7945785E38 double:1.052935797E-314;
                    r7 = 2131165644; // 0x7f0701cc float:1.794551E38 double:1.0529357303E-314;
                    r8 = 1;
                    r10 = 0;
                    if (r2 == 0) goto L_0x027b;
                L_0x0042:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.genericItemView;
                    if (r2 != 0) goto L_0x0060;
                L_0x004a:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r9 = new com.gaana.view.item.GenericItemView;
                    r11 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r11 = r11.mContext;
                    r12 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r12 = r12.mFragment;
                    r9.<init>(r11, r12);
                    r2.genericItemView = r9;
                L_0x0060:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.genericItemView;
                    r9 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r9 = r9.mDynamicView;
                    r9 = r9.p();
                    r2.setSourceName(r9);
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r9 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r9 = r9.getNumVal();
                    if (r2 == r9) goto L_0x00a6;
                L_0x0085:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r9 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r9 = r9.getNumVal();
                    if (r2 != r9) goto L_0x0098;
                L_0x0097:
                    goto L_0x00a6;
                L_0x0098:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.genericItemView;
                    r9 = java.lang.Boolean.valueOf(r10);
                    r2.setItemWithoutText(r9);
                    goto L_0x00b3;
                L_0x00a6:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.genericItemView;
                    r9 = java.lang.Boolean.valueOf(r8);
                    r2.setItemWithoutText(r9);
                L_0x00b3:
                    r2 = r7;
                    if (r2 == 0) goto L_0x00e8;
                L_0x00b7:
                    if (r6 <= 0) goto L_0x00cb;
                L_0x00b9:
                    if (r6 <= r1) goto L_0x00cb;
                L_0x00bb:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r9 = r6 + -2;
                    r2 = r2.get(r9);
                    r2 = (com.gaana.models.BusinessObject) r2;
                L_0x00c9:
                    r9 = r2;
                    goto L_0x0117;
                L_0x00cb:
                    if (r6 >= r1) goto L_0x0116;
                L_0x00cd:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r2 = r2.size();
                    if (r6 >= r2) goto L_0x0116;
                L_0x00d9:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r9 = r6 + -1;
                    r2 = r2.get(r9);
                    r2 = (com.gaana.models.BusinessObject) r2;
                    goto L_0x00c9;
                L_0x00e8:
                    if (r6 <= 0) goto L_0x00fb;
                L_0x00ea:
                    if (r6 <= r1) goto L_0x00fb;
                L_0x00ec:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r9 = r6 + -1;
                    r2 = r2.get(r9);
                    r2 = (com.gaana.models.BusinessObject) r2;
                    goto L_0x00c9;
                L_0x00fb:
                    if (r6 >= r1) goto L_0x0116;
                L_0x00fd:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r2 = r2.size();
                    if (r6 >= r2) goto L_0x0116;
                L_0x0109:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r2 = r2.get(r6);
                    r2 = (com.gaana.models.BusinessObject) r2;
                    goto L_0x00c9;
                L_0x0116:
                    r9 = r4;
                L_0x0117:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.m();
                    r11 = com.dynamicview.DynamicViewManager.DynamicViewType.double_scroll;
                    r11 = r11.name();
                    r2 = r2.equals(r11);
                    if (r2 != 0) goto L_0x0136;
                L_0x012d:
                    r11 = r9;
                    r11 = r11 - r8;
                    if (r6 != r11) goto L_0x0134;
                L_0x0132:
                    r11 = r8;
                    goto L_0x014e;
                L_0x0134:
                    r11 = r10;
                    goto L_0x014e;
                L_0x0136:
                    r11 = r9;
                    r11 = r11 % 2;
                    if (r11 != 0) goto L_0x0148;
                L_0x013c:
                    r11 = r9;
                    r11 = r11 - r8;
                    if (r6 == r11) goto L_0x0132;
                L_0x0141:
                    r11 = r9;
                    r11 = r11 + -2;
                    if (r6 != r11) goto L_0x0134;
                L_0x0147:
                    goto L_0x0132;
                L_0x0148:
                    r11 = r9;
                    r11 = r11 - r8;
                    if (r6 != r11) goto L_0x0134;
                L_0x014d:
                    goto L_0x0132;
                L_0x014e:
                    if (r2 != 0) goto L_0x0168;
                L_0x0150:
                    if (r6 != 0) goto L_0x015d;
                L_0x0152:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r5);
                    goto L_0x0181;
                L_0x015d:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r7);
                    goto L_0x0181;
                L_0x0168:
                    r2 = r6 / 2;
                    if (r2 != 0) goto L_0x0177;
                L_0x016c:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r5);
                    goto L_0x0181;
                L_0x0177:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r7);
                L_0x0181:
                    if (r11 == 0) goto L_0x0190;
                L_0x0183:
                    r5 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r5 = r5.mContext;
                    r5 = r5.getResources();
                    r5.getDimensionPixelSize(r7);
                L_0x0190:
                    if (r6 != r1) goto L_0x01dc;
                L_0x0192:
                    r1 = r7;
                    if (r1 == 0) goto L_0x01aa;
                L_0x0196:
                    r1 = r3.itemView;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mContext;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r7);
                    r1.setPadding(r10, r2, r10, r10);
                    goto L_0x01af;
                L_0x01aa:
                    r1 = r3.itemView;
                    r1.setPadding(r10, r10, r10, r10);
                L_0x01af:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r4 = r10;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r7 = r2.isAdtoBeRefreshed;
                    r8 = 0;
                    r9 = r3.itemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r11 = r2;
                    r11 = (android.view.ViewGroup) r11;
                    r12 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r4;
                    r4 = r7;
                    r5 = r8;
                    r7 = r9;
                    r8 = r11;
                    r9 = r12;
                    r4 = r1.getPoplatedAdView(r2, r4, r5, r6, r7, r8, r9);
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1.isAdtoBeRefreshed = r10;
                    goto L_0x027a;
                L_0x01dc:
                    r1 = r9 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x027a;
                L_0x01e0:
                    r1 = r9;
                    r1 = (com.gaana.models.Item) r1;
                    r5 = r1.getEntityType();
                    if (r5 == 0) goto L_0x027a;
                L_0x01e9:
                    r4 = r3.itemView;
                    r4.setPadding(r2, r10, r10, r10);
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x025d;
                L_0x01fa:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mContext;
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mFragment;
                    r1.<init>(r2, r4);
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mGATitle;
                    r5 = r6 + 1;
                    r1.setGAData(r2, r4, r5);
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 == r4) goto L_0x0249;
                L_0x0232:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 != r4) goto L_0x0245;
                L_0x0244:
                    goto L_0x0249;
                L_0x0245:
                    r1.setItemWithoutText(r10);
                    goto L_0x024c;
                L_0x0249:
                    r1.setItemWithoutText(r8);
                L_0x024c:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r1 = r1.getGridItemView(r3, r9);
                    goto L_0x0279;
                L_0x025d:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r5 = r2;
                    r5 = (android.view.ViewGroup) r5;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r7 = r2.mGATitle;
                    r2 = r6;
                    r4 = r9;
                    r6 = r7;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6);
                L_0x0279:
                    return r1;
                L_0x027a:
                    return r4;
                L_0x027b:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.mDynamicView;
                    r1 = r1.m();
                    r2 = com.dynamicview.DynamicViewManager.DynamicViewType.double_scroll;
                    r2 = r2.name();
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x029a;
                L_0x0291:
                    r2 = r9;
                    r2 = r2 - r8;
                    if (r6 != r2) goto L_0x0298;
                L_0x0296:
                    r2 = r8;
                    goto L_0x02b2;
                L_0x0298:
                    r2 = r10;
                    goto L_0x02b2;
                L_0x029a:
                    r2 = r9;
                    r2 = r2 % 2;
                    if (r2 != 0) goto L_0x02ac;
                L_0x02a0:
                    r2 = r9;
                    r2 = r2 - r8;
                    if (r6 == r2) goto L_0x0296;
                L_0x02a5:
                    r2 = r9;
                    r2 = r2 + -2;
                    if (r6 != r2) goto L_0x0298;
                L_0x02ab:
                    goto L_0x0296;
                L_0x02ac:
                    r2 = r9;
                    r2 = r2 - r8;
                    if (r6 != r2) goto L_0x0298;
                L_0x02b1:
                    goto L_0x0296;
                L_0x02b2:
                    if (r1 != 0) goto L_0x02cc;
                L_0x02b4:
                    if (r6 != 0) goto L_0x02c1;
                L_0x02b6:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r5);
                    goto L_0x02e5;
                L_0x02c1:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r7);
                    goto L_0x02e5;
                L_0x02cc:
                    r1 = r6 / 2;
                    if (r1 != 0) goto L_0x02db;
                L_0x02d0:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r5);
                    goto L_0x02e5;
                L_0x02db:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r7);
                L_0x02e5:
                    if (r2 == 0) goto L_0x02f4;
                L_0x02e7:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mContext;
                    r2 = r2.getResources();
                    r2.getDimensionPixelSize(r7);
                L_0x02f4:
                    r2 = r3.itemView;
                    r2.setPadding(r1, r10, r10, r10);
                    r1 = r7;
                    if (r1 == 0) goto L_0x030e;
                L_0x02fd:
                    if (r6 <= 0) goto L_0x030e;
                L_0x02ff:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.arrListBusinessObj;
                    r2 = r6 + -1;
                    r1 = r1.get(r2);
                    r1 = (com.gaana.models.BusinessObject) r1;
                    goto L_0x031a;
                L_0x030e:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.arrListBusinessObj;
                    r1 = r1.get(r6);
                    r1 = (com.gaana.models.BusinessObject) r1;
                L_0x031a:
                    r5 = r1;
                    r1 = r5 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x0426;
                L_0x031f:
                    r1 = r5;
                    r1 = (com.gaana.models.Item) r1;
                    r2 = r1.getEntityType();
                    if (r2 == 0) goto L_0x0426;
                L_0x0328:
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x0397;
                L_0x0334:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mContext;
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mFragment;
                    r1.<init>(r2, r4);
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mGATitle;
                    r6 = r6 + r8;
                    r1.setGAData(r2, r4, r6);
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 == r4) goto L_0x0382;
                L_0x036b:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.e();
                    r4 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r4 = r4.getNumVal();
                    if (r2 != r4) goto L_0x037e;
                L_0x037d:
                    goto L_0x0382;
                L_0x037e:
                    r1.setItemWithoutText(r10);
                    goto L_0x0385;
                L_0x0382:
                    r1.setItemWithoutText(r8);
                L_0x0385:
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.arrListBusinessObj;
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r1 = r1.getGridItemView(r3, r5);
                    goto L_0x0425;
                L_0x0397:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    if (r1 != 0) goto L_0x03b5;
                L_0x039f:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = new com.gaana.view.item.GenericItemView;
                    r4 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r4 = r4.mContext;
                    r7 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r7 = r7.mFragment;
                    r2.<init>(r4, r7);
                    r1.genericItemView = r2;
                L_0x03b5:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.mDynamicView;
                    r1 = r1.e();
                    r2 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
                    r2 = r2.getNumVal();
                    if (r1 == r2) goto L_0x03e8;
                L_0x03c7:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.mDynamicView;
                    r1 = r1.e();
                    r2 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
                    r2 = r2.getNumVal();
                    if (r1 != r2) goto L_0x03da;
                L_0x03d9:
                    goto L_0x03e8;
                L_0x03da:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = java.lang.Boolean.valueOf(r10);
                    r1.setItemWithoutText(r2);
                    goto L_0x03f5;
                L_0x03e8:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = java.lang.Boolean.valueOf(r8);
                    r1.setItemWithoutText(r2);
                L_0x03f5:
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r2 = r2.mDynamicView;
                    r2 = r2.p();
                    r1.setSourceName(r2);
                    r1 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r1 = r1.genericItemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r7 = r2;
                    r7 = (android.view.ViewGroup) r7;
                    r2 = com.gaana.view.item.OccasionDynamicScrollView.this;
                    r8 = r2.mGATitle;
                    r2 = r6;
                    r4 = r5;
                    r5 = r7;
                    r6 = r8;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6);
                L_0x0425:
                    return r1;
                L_0x0426:
                    return r4;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.OccasionDynamicScrollView$AnonymousClass4.getCompatibleView(int, int, int, android.support.v7.widget.RecyclerView$ViewHolder):android.view.View");
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    return i == 910 ? new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.a(OccasionDynamicScrollView.this.mDynamicView), viewGroup, false)) : viewHolder;
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
                if (!(this.mHideContent || this.mViewHolder == null || this.mViewHolder.seeAllText == null)) {
                    this.mViewHolder.seeAllText.setVisibility(8);
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
            this.mBusinessObject = businessObject;
            this.arrListBusinessObj = this.mBusinessObject.getArrListBusinessObj();
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
        BaseActivity baseActivity;
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3;
        switch (view.getId()) {
            case R.id.f55header.text /*2131297292*/:
            case R.id.view1 /*2131298846*/:
                if ((this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof RadioActivityFragment)) {
                    baseActivity = (BaseActivity) this.mContext;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("OP_");
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(this.mGATitle);
                    stringBuilder3.append(" click ");
                    baseActivity.sendGAEvent(stringBuilder2, stringBuilder3.toString(), "See More");
                    seeAllDetails(getSeeAllUrlManager(), this.mDisplayTitle);
                    return;
                }
                return;
            case R.id.seeall /*2131298341*/:
            case R.id.seeallImg /*2131298342*/:
                baseActivity = (BaseActivity) this.mContext;
                stringBuilder = new StringBuilder();
                stringBuilder.append(com.dynamicview.c.a().l());
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder2 = stringBuilder.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(this.mGATitle);
                stringBuilder3.append(" click ");
                baseActivity.sendGAEvent(stringBuilder2, stringBuilder3.toString(), "See More");
                seeAllDetails(getSeeAllUrlManager(), this.mDisplayTitle);
                return;
            default:
                return;
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
}

package com.gaana.view.item;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
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
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.dynamicview.h;
import com.fragments.BaseGaanaFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukePlaylists;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.ap;
import com.managers.e;
import com.services.l.o;
import com.services.l.s;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.b;
import com.views.HorizontalRecyclerView.c;
import java.util.ArrayList;
import java.util.Calendar;

public class JukeScrollView extends BaseItemView implements o, s {
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
        }

        public void setOrientation(int i) {
            if (this.horizontalRecyclerView != null) {
                this.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), i, false));
            }
        }
    }

    public JukeScrollView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
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
        if (this.mDynamicView.v()) {
            this.mURLManager.a(BusinessObjectType.JukePlayLists);
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
        int i2 = (this.mDynamicView == null || !this.mDynamicView.m().equals(DynamicViewType.double_scroll.name())) ? 0 : 1;
        b a = this.mViewHolder.horizontalRecyclerView.a(this.mViewHolder.itemView.getContext(), 0);
        if (i2 == 0) {
            this.mViewHolder.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        } else {
            this.mViewHolder.horizontalRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2, 0, false));
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
                    resources = JukeScrollView.this.mContext.getResources();
                    i2 = R.dimen.page_left_right_margin;
                } else {
                    resources = JukeScrollView.this.mContext.getResources();
                    i2 = R.dimen.home_item_paddding;
                }
                viewHolder.itemView.setPadding(resources.getDimensionPixelSize(i2), 0, 0, 0);
                return JukeScrollView.this.genericItemView.getEmptyView(viewHolder, (ViewGroup) viewHolder.itemView.getParent(), BusinessObjectType.GenericItems);
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                return i == 910 ? new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.b(JukeScrollView.this.mDynamicView), viewGroup, false)) : viewHolder;
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
            if (!TextUtils.isEmpty(items.getRawTagDescription())) {
                this.mDynamicView.h(items.getRawTagDescription());
            }
            String tagDescription = items.getTagDescription();
            String pageTitle = !TextUtils.isEmpty(items.getPageTitle()) ? items.getPageTitle() : this.mDynamicView.A();
            if (!TextUtils.isEmpty(tagDescription)) {
                this.mDisplayTitle = tagDescription;
                setHeader(tagDescription, pageTitle);
            }
        } else if (businessObject instanceof JukePlaylists) {
            CharSequence name = businessObject.getName();
            if (TextUtils.isEmpty(name)) {
                name = this.mDynamicView.u();
            }
            if (!TextUtils.isEmpty(name)) {
                this.mDisplayTitle = name;
                setHeader(name, "");
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
            final int size = this.arrListBusinessObj.size() + 1;
            this.horizontalScroller.setViewRecycleListner(this.viewType, size, false, new HorizontalRecyclerView.a() {
                public int getItemViewType(int i) {
                    Item item = (i < 0 || i >= JukeScrollView.this.arrListBusinessObj.size() || !(JukeScrollView.this.arrListBusinessObj.get(i) instanceof Item)) ? null : (Item) JukeScrollView.this.arrListBusinessObj.get(i);
                    if (item != null && item.getEntityType().equals(com.constants.c.c.h)) {
                        return h.c(JukeScrollView.this.mDynamicView);
                    }
                    if (i == JukeScrollView.this.arrListBusinessObj.size()) {
                        return R.layout.view_create_party_playlist_grid;
                    }
                    return h.b(JukeScrollView.this.mDynamicView);
                }

                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    boolean equals = JukeScrollView.this.mDynamicView.m().equals(DynamicViewType.double_scroll.name());
                    int i4 = (equals ? size % 2 != 0 ? i3 != size - 1 : !(i3 == size - 1 || i3 == size - 2) : i3 != size - 1) ? 0 : 1;
                    i = !equals ? i3 == 0 ? JukeScrollView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : JukeScrollView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding) : i3 / 2 == 0 ? JukeScrollView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : JukeScrollView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
                    i4 = i4 != 0 ? JukeScrollView.this.mContext.getResources().getDimensionPixelSize(R.dimen.home_item_paddding) : 0;
                    viewHolder.itemView.setPadding(i, 0, i4, 0);
                    if (i3 == JukeScrollView.this.arrListBusinessObj.size()) {
                        LayoutParams layoutParams = (LayoutParams) viewHolder.itemView.getLayoutParams();
                        i3 = JukeScrollView.this.mContext.getResources().getDimensionPixelSize(R.dimen.img_dynamic_horizontal_item_medium_square_width_height);
                        if (JukeScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal()) {
                            i3 = JukeScrollView.this.mContext.getResources().getDimensionPixelSize(R.dimen.home_playlist_width_height);
                        }
                        layoutParams.height = i3;
                        layoutParams.width = i3;
                        layoutParams.leftMargin = i;
                        layoutParams.rightMargin = i4;
                        viewHolder.itemView.requestLayout();
                        viewHolder.itemView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (Util.j(JukeScrollView.this.mContext)) {
                                    JukePlaylist jukePlaylist = new JukePlaylist();
                                    jukePlaylist.setName(JukeScrollView.this.mContext.getResources().getString(R.string.opt_my_party));
                                    ((GaanaActivity) JukeScrollView.this.mContext).displayFragment(JukePartyFragment.newInstance(jukePlaylist, 0, "", false));
                                    return;
                                }
                                ap.a().f(JukeScrollView.this.mContext);
                            }
                        });
                        return viewHolder.itemView;
                    }
                    View view = null;
                    Object obj = (i3 <= -1 || i3 >= JukeScrollView.this.arrListBusinessObj.size()) ? null : (BusinessObject) JukeScrollView.this.arrListBusinessObj.get(i3);
                    if (obj instanceof JukePlaylist) {
                        JukePlaylist jukePlaylist = (JukePlaylist) obj;
                        view = new JukePlaylistItemView(JukeScrollView.this.mContext, JukeScrollView.this.mFragment, JukeScrollView.this.mGATitle).getPopulatedView(viewHolder, jukePlaylist);
                        if (!TextUtils.isEmpty(jukePlaylist.getNickName())) {
                            JukeSessionManager.getInstance().setUserNick(jukePlaylist.getNickName());
                        }
                    } else if (obj instanceof Item) {
                        Item item = (Item) obj;
                        if (item.getEntityType() != null) {
                            View gridItemView;
                            if (item.getEntityType().equals(com.constants.c.c.h) || item.getEntityType().equals(com.constants.c.c.e)) {
                                e.a().a(viewHolder.itemView, JukeScrollView.this.mContext, item);
                            }
                            if (item.getEntityType().equals(com.constants.c.c.c)) {
                                DownloadSongsItemView downloadSongsItemView = new DownloadSongsItemView(JukeScrollView.this.mContext, JukeScrollView.this.mFragment);
                                if (JukeScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || JukeScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                                    downloadSongsItemView.setItemWithoutText(true);
                                } else {
                                    downloadSongsItemView.setItemWithoutText(false);
                                }
                                downloadSongsItemView.setSongsListBusinessObject(JukeScrollView.this.arrListBusinessObj);
                                downloadSongsItemView.setIsSongSection();
                                if ((viewHolder instanceof PlaylistGridHolder) && JukeScrollView.this.mDynamicView.C()) {
                                    ImageView imageView = ((PlaylistGridHolder) viewHolder).shareIcon;
                                    imageView.setVisibility(0);
                                    imageView.setTag(obj);
                                    imageView.setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            af.a(JukeScrollView.this.mContext, JukeScrollView.this.mFragment).a((int) R.id.shareMenu, Util.g((Item) ((BusinessObject) view.getTag())));
                                        }
                                    });
                                }
                                gridItemView = downloadSongsItemView.getGridItemView(viewHolder, obj);
                            } else if (item.getEntityType().equals(com.constants.c.c.h)) {
                                if (JukeScrollView.this.genericItemView == null) {
                                    JukeScrollView.this.genericItemView = new GenericItemView(JukeScrollView.this.mContext, JukeScrollView.this.mFragment);
                                }
                                gridItemView = JukeScrollView.this.genericItemView.getAdView(i3, viewHolder, obj, (ViewGroup) viewHolder.itemView.getParent(), JukeScrollView.this.mDynamicView.k());
                            } else {
                                if (JukeScrollView.this.genericItemView == null) {
                                    JukeScrollView.this.genericItemView = new GenericItemView(JukeScrollView.this.mContext, JukeScrollView.this.mFragment);
                                }
                                if (JukeScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || JukeScrollView.this.mDynamicView.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
                                    JukeScrollView.this.genericItemView.setItemWithoutText(Boolean.valueOf(true));
                                } else {
                                    JukeScrollView.this.genericItemView.setItemWithoutText(Boolean.valueOf(false));
                                }
                                if ((viewHolder instanceof PlaylistGridHolder) && JukeScrollView.this.mDynamicView.C()) {
                                    ImageView imageView2 = ((PlaylistGridHolder) viewHolder).shareIcon;
                                    imageView2.setVisibility(0);
                                    imageView2.setTag(obj);
                                    imageView2.setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            BusinessObject businessObject = (BusinessObject) view.getTag();
                                            Item item = (Item) businessObject;
                                            if (item.getEntityType().equals(com.constants.c.c.b)) {
                                                businessObject = Util.c(item);
                                            } else if (item.getEntityType().equals(com.constants.c.c.a)) {
                                                businessObject = Util.b(item);
                                            }
                                            af.a(JukeScrollView.this.mContext, JukeScrollView.this.mFragment).a((int) R.id.shareMenu, businessObject);
                                        }
                                    });
                                }
                                gridItemView = JukeScrollView.this.genericItemView.getPoplatedGenericView(i3, viewHolder, obj, (ViewGroup) viewHolder.itemView.getParent(), JukeScrollView.this.mGATitle);
                            }
                            return gridItemView;
                        }
                    }
                    return view;
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    if (i2 == R.layout.view_create_party_playlist_grid) {
                        return new ItemAdViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
                    }
                    return i == 910 ? new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.b(JukeScrollView.this.mDynamicView), viewGroup, false)) : viewHolder;
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
            onFeedRetrievalcompelete(businessObject, this.mView);
        }
        setIsToBeRefreshed(false);
        this.isRrefreshing = false;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.f55header.text /*2131297292*/:
            case R.id.view1 /*2131298846*/:
                if ((this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof RadioActivityFragment)) {
                    seeAllDetails(getSeeAllUrlManager(), this.mDisplayTitle);
                    return;
                }
                return;
            case R.id.seeall /*2131298341*/:
            case R.id.seeallImg /*2131298342*/:
                seeAllDetails(getSeeAllUrlManager(), this.mDisplayTitle);
                return;
            default:
                return;
        }
    }

    private void seeAllDetails(URLManager uRLManager, String str) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            if (!TextUtils.isEmpty(this.mDynamicView.l())) {
                ((GaanaActivity) this.mContext).displayFragment(JukeSeeAllFragment.newInstance(this.mDynamicView.l(), this.mDynamicView.u(), (this.mDynamicView.v() ? BusinessObjectType.JukePlayLists : BusinessObjectType.GenericItems).name(), false));
            }
        } else {
            ap.a().f(this.mContext);
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

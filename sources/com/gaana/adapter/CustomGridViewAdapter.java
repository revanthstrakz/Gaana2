package com.gaana.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.GridActivityFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.R;
import com.gaana.models.AdsUJData;
import com.gaana.view.item.BaseItemView.GridItemHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.DiscoverItemView.DiscoverGridHolder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.ap;
import com.managers.e;
import com.services.l.c;
import com.services.l.l;
import com.utilities.d;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class CustomGridViewAdapter extends Adapter<ViewHolder> implements c {
    private static final int VIEW_TYPE_AD = 2;
    private static final int VIEW_TYPE_AD_GRID = 1002;
    private static final int VIEW_TYPE_BANNER_AD = 4;
    private static final int VIEW_TYPE_SINGLE_GRID = 1;
    private static final int VIEW_TYPE_SINGLE_GRID_HEADER_IMAGE = 8;
    private static final int VIEW_TYPE_TWO_GRID = 0;
    public int REPETATIVE_AD_INTERVAL = 4;
    private boolean adEnabled = false;
    private ArrayList<ItemAdViewHolder> adItemHolderList = new ArrayList();
    private String adSectionName;
    private boolean firstAdLoaded = false;
    private boolean isVideoAdLoaded = false;
    private int lastPosition = -1;
    private ArrayList<Integer> listofBannerAdPositions = new ArrayList();
    private HashMap<Integer, ItemAdViewHolder> loadedAdMap = new HashMap();
    private ColumbiaAdItemview mColumbiaAdItemView = null;
    private ColumbiaAdItemview mColumbiaBannerAdItemView = null;
    private int mColumnCount;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private RecyclerView mGridView;
    private int mItemCount;
    public ArrayList<Integer> mListofAdposition = new ArrayList();
    private OnGetViewCalledListner mOnGetViewCalled;
    private l mOnLoadMoreCalled;
    private String sectionType;

    public class AdViewHolder extends ViewHolder {
        public View view;

        public AdViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public interface OnGetViewCalledListner {
        void onBindVideoAd(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup);

        View onGetViewCalled(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup);
    }

    public CustomGridViewAdapter(int i, int i2, OnGetViewCalledListner onGetViewCalledListner, Context context, BaseGaanaFragment baseGaanaFragment, boolean z, RecyclerView recyclerView) {
        this.mItemCount = i;
        this.mOnGetViewCalled = onGetViewCalledListner;
        this.mColumnCount = i2;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.adEnabled = z;
        this.mGridView = recyclerView;
        this.mListofAdposition.clear();
        this.adItemHolderList.clear();
        this.loadedAdMap.clear();
    }

    public void setAdSectionName(String str) {
        this.adSectionName = str;
    }

    public void setSectionType(String str) {
        this.sectionType = str;
    }

    private void insertBannerAdSpots() {
        if (!(this.mFragment instanceof DiscoverDetailFragment)) {
            boolean z = this.mFragment instanceof RadioActivityFragment;
        }
    }

    public void setIsVideoAdLoaded(boolean z) {
        this.isVideoAdLoaded = z;
    }

    public void setOnLoadMoreListener(l lVar) {
        this.mOnLoadMoreCalled = lVar;
    }

    public int getActualItemIndex(int i) {
        Iterator it = this.listofBannerAdPositions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() < i) {
                i2++;
            }
        }
        return i - i2;
    }

    public void setCount(int i) {
        if (i > this.mItemCount) {
            insertAdSpots();
            initializeAdPositions(this.mItemCount, i);
        }
        this.mItemCount = i;
        notifyDataSetChanged();
    }

    private void removeDuplicateAdSlots() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < this.listofBannerAdPositions.size() && i2 < this.mListofAdposition.size()) {
            int intValue = ((Integer) this.listofBannerAdPositions.get(i)).intValue();
            int intValue2 = ((Integer) this.mListofAdposition.get(i2)).intValue();
            if (intValue == intValue2) {
                arrayList.add(Integer.valueOf(intValue2));
                i++;
                i2++;
            } else if (intValue < intValue2) {
                i++;
            } else {
                i2++;
            }
        }
        if (arrayList.size() > 0) {
            this.mListofAdposition.removeAll(arrayList);
        }
    }

    public void setAdEnabled(boolean z) {
        this.adEnabled = z;
    }

    public int getItemViewType(int i) {
        if (this.listofBannerAdPositions.contains(Integer.valueOf(i))) {
            return 4;
        }
        if (i == 0 && (this.mFragment instanceof GridActivityFragment) && !TextUtils.isEmpty(((GridActivityFragment) this.mFragment).e())) {
            return 8;
        }
        if (i == 0 && this.isVideoAdLoaded) {
            return 2;
        }
        if (this.mColumnCount == 1) {
            return 1;
        }
        return this.loadedAdMap.get(Integer.valueOf(i)) != null ? 1002 + this.mListofAdposition.indexOf(Integer.valueOf(i)) : 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4) {
            return new ItemAdViewHolder(getADView(viewGroup));
        }
        if (i == 8) {
            return new DiscoverGridHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_item_header_ad_image, viewGroup, false));
        }
        if (i == 2) {
            return new AdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false));
        }
        if (i >= 1002 && i < this.mListofAdposition.size() + 1002) {
            ItemAdViewHolder itemAdViewHolder = (ItemAdViewHolder) this.loadedAdMap.get(this.mListofAdposition.get(i - 1002));
            if (itemAdViewHolder != null) {
                if (itemAdViewHolder.itemView.getParent() != null) {
                    ((ViewGroup) itemAdViewHolder.itemView.getParent()).removeView(itemAdViewHolder.itemView);
                }
                return itemAdViewHolder;
            }
        }
        boolean z = true;
        if (this.mColumnCount != 1) {
            z = false;
        }
        switch (z) {
            case false:
                return new GridItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_grid_item, viewGroup, false));
            case true:
                View inflate;
                LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
                if (d.b()) {
                    inflate = from.inflate(R.layout.view_item_discover, viewGroup, false);
                } else {
                    inflate = from.inflate(R.layout.view_item_discover_adjustable, viewGroup, false);
                }
                return new DiscoverGridHolder(inflate);
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mOnLoadMoreCalled != null) {
            int i2 = 1;
            if (i != this.mItemCount - 1) {
                i2 = 0;
            }
            if (i2 != 0) {
                this.mOnLoadMoreCalled.loadMoreData(this.mItemCount);
            }
        }
        if (this.isVideoAdLoaded && i == 0) {
            this.mOnGetViewCalled.onBindVideoAd(viewHolder, viewHolder.itemView, i, null);
            return;
        }
        if (this.listofBannerAdPositions.contains(Integer.valueOf(i))) {
            ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) viewHolder.itemView, new PublisherAdView(this.mContext), getUnitAdCode(), null, 100, this.adSectionName, new AdsUJData[0]);
        } else if (!(viewHolder instanceof ItemAdViewHolder)) {
            int positionwrtAd;
            if (this.loadedAdMap.get(Integer.valueOf(i)) != null && this.loadedAdMap.get(Integer.valueOf(i)) == viewHolder) {
                viewHolder = (ViewHolder) this.loadedAdMap.get(Integer.valueOf(i));
            }
            if ((this.mFragment instanceof GridActivityFragment) && !TextUtils.isEmpty(((GridActivityFragment) this.mFragment).e()) && i == 0) {
                viewHolder.itemView.setPadding((int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp));
            } else {
                viewHolder.itemView.setPadding((int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp));
            }
            OnGetViewCalledListner onGetViewCalledListner = this.mOnGetViewCalled;
            View view = viewHolder.itemView;
            if (this.firstAdLoaded) {
                positionwrtAd = getPositionwrtAd(this.isVideoAdLoaded ? i - 1 : i);
            } else {
                positionwrtAd = this.isVideoAdLoaded ? i - 1 : i;
            }
            onGetViewCalledListner.onGetViewCalled(viewHolder, view, positionwrtAd, null);
            if (!Constants.cN) {
                setAnimation(viewHolder.itemView, i);
            }
        } else if ((this.mFragment instanceof GridActivityFragment) && !TextUtils.isEmpty(((GridActivityFragment) this.mFragment).e()) && i == 0) {
            viewHolder.itemView.setPadding((int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_0dp));
        } else {
            viewHolder.itemView.setPadding((int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp), (int) this.mContext.getResources().getDimension(R.dimen.dimen_12dp));
        }
    }

    private String getUnitAdCode() {
        if (!(this.mFragment instanceof GridActivityFragment)) {
            return "0";
        }
        if (this.sectionType.equalsIgnoreCase("radio mirchi")) {
            return e.E;
        }
        return ((GridActivityFragment) this.mFragment).b();
    }

    public View getADView(ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
    }

    public ArrayList<Integer> getBannerAdPositions() {
        return this.listofBannerAdPositions;
    }

    private void setAnimation(View view, int i) {
        if (i > this.lastPosition && view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.slide_list_up);
            loadAnimation.setDuration(700);
            loadAnimation.setInterpolator(new DecelerateInterpolator(3.0f));
            view.startAnimation(loadAnimation);
            this.lastPosition = i;
        }
    }

    public void onViewDetachedFromWindow(@NonNull ViewHolder viewHolder) {
        viewHolder.itemView.clearAnimation();
        super.onViewDetachedFromWindow(viewHolder);
    }

    public int getItemCount() {
        int size = this.loadedAdMap.size();
        int i = this.mItemCount + size;
        if (i >= this.REPETATIVE_AD_INTERVAL) {
            i = (this.mItemCount + size) + this.listofBannerAdPositions.size();
        }
        return this.isVideoAdLoaded ? i + 1 : i;
    }

    public void insertAdSpots() {
        String unitAdCode = getUnitAdCode();
        if (!TextUtils.isEmpty(unitAdCode) && !"0".equals(unitAdCode)) {
            if (this.sectionType != null && this.sectionType.equalsIgnoreCase("discover")) {
                this.listofBannerAdPositions.add(Integer.valueOf(this.REPETATIVE_AD_INTERVAL));
            } else if (this.listofBannerAdPositions.size() == 0 && this.mItemCount / 2 > 0) {
                this.listofBannerAdPositions.add(Integer.valueOf(this.REPETATIVE_AD_INTERVAL));
                insertAdSpots();
            } else if (this.listofBannerAdPositions.size() > 0 && ((Integer) this.listofBannerAdPositions.get(this.listofBannerAdPositions.size() - 1)).intValue() < (this.mItemCount / 2) + this.listofBannerAdPositions.size()) {
                int intValue = ((Integer) this.listofBannerAdPositions.get(this.listofBannerAdPositions.size() - 1)).intValue() + (this.REPETATIVE_AD_INTERVAL + 1);
                if (!this.listofBannerAdPositions.contains(Integer.valueOf(intValue))) {
                    this.listofBannerAdPositions.add(Integer.valueOf(intValue));
                }
                insertAdSpots();
            }
        }
    }

    public void clearAdapter() {
        this.mItemCount = 0;
        notifyDataSetChanged();
    }

    public void onAdLoadedatPosition(boolean z, int i) {
        if (z) {
            this.firstAdLoaded = true;
            this.loadedAdMap.put(Integer.valueOf(i), this.adItemHolderList.get(this.mListofAdposition.indexOf(Integer.valueOf(i))));
            notifyItemInserted(i);
        }
    }

    private int getAdPostion(int i, int i2) {
        int i3 = -1;
        if (i2 == 0) {
            return -1;
        }
        if (i2 == 4) {
            if (i > 11) {
                i = i2 + 4;
            }
            i3 = new Random().nextInt((i - i2) + 1) + i2;
            this.mListofAdposition.add(Integer.valueOf(i3));
        } else if ((i2 - 4) % 10 == 0) {
            i3 = i2 + 4;
            if (i > i3) {
                i = i3;
            }
            i3 = new Random().nextInt((i - i2) + 1) + i2;
            this.mListofAdposition.add(Integer.valueOf(i3));
        }
        return i3;
    }

    private int getPositionwrtAd(int i) {
        int i2;
        ItemAdViewHolder itemAdViewHolder = (ItemAdViewHolder) this.loadedAdMap.get(Integer.valueOf(i));
        int i3 = 0;
        if (this.listofBannerAdPositions.size() > 0) {
            int i4 = 0;
            i2 = i4;
            while (i4 < this.listofBannerAdPositions.size() && ((Integer) this.listofBannerAdPositions.get(i4)).intValue() < i) {
                i2++;
                i4++;
            }
        } else {
            i2 = 0;
        }
        if (this.loadedAdMap.size() > 0 && i == this.mItemCount && i == ((Integer) this.mListofAdposition.get(this.loadedAdMap.size() - 1)).intValue()) {
            i--;
        } else {
            for (Integer intValue : this.loadedAdMap.keySet()) {
                if (intValue.intValue() < i) {
                    i3++;
                }
            }
            i -= i3;
        }
        return i - i2;
    }

    public void onRefresh(boolean z) {
        if (z && ap.a().b(this.mContext)) {
            ColombiaManager.b().c();
            if (this.mColumbiaAdItemView != null) {
                this.mColumbiaAdItemView.a();
            }
        }
    }

    /* JADX WARNING: Missing block: B:24:0x0075, code skipped:
            return;
     */
    public void initializeAdPositions(int r4, int r5) {
        /*
        r3 = this;
        r0 = r3.mColumnCount;
        r1 = 1;
        if (r0 == r1) goto L_0x0075;
    L_0x0005:
        r0 = r3.adEnabled;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0075;
    L_0x000a:
        r0 = com.managers.ap.a();
        r1 = r3.mContext;
        r0 = r0.b(r1);
        if (r0 == 0) goto L_0x0074;
    L_0x0016:
        r0 = com.gaana.application.GaanaApplication.getInstance();
        r0 = r0.getColombiaSdkInit();
        if (r0 == 0) goto L_0x0074;
    L_0x0020:
        if (r4 >= r5) goto L_0x0043;
    L_0x0022:
        r0 = 4;
        if (r4 != r0) goto L_0x002f;
    L_0x0025:
        r0 = r3.mListofAdposition;
        r1 = java.lang.Integer.valueOf(r4);
        r0.add(r1);
        goto L_0x0040;
    L_0x002f:
        if (r4 == 0) goto L_0x0040;
    L_0x0031:
        r0 = r4 + -4;
        r0 = r0 % 7;
        if (r0 != 0) goto L_0x0040;
    L_0x0037:
        r0 = r3.mListofAdposition;
        r1 = java.lang.Integer.valueOf(r4);
        r0.add(r1);
    L_0x0040:
        r4 = r4 + 1;
        goto L_0x0020;
    L_0x0043:
        r3.removeDuplicateAdSlots();
        r4 = r3.mListofAdposition;
        r4 = r4.iterator();
    L_0x004c:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0074;
    L_0x0052:
        r5 = r4.next();
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r0 = r3.adItemHolderList;
        r0 = r0.size();
        r1 = r3.mListofAdposition;
        r2 = java.lang.Integer.valueOf(r5);
        r1 = r1.indexOf(r2);
        if (r0 != r1) goto L_0x004c;
    L_0x006e:
        r0 = r3.mGridView;
        r3.startAdRequestatPosition(r0, r5);
        goto L_0x004c;
    L_0x0074:
        return;
    L_0x0075:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.adapter.CustomGridViewAdapter.initializeAdPositions(int, int):void");
    }

    private void startAdRequestatPosition(ViewGroup viewGroup, int i) {
        if (this.mColumbiaAdItemView == null) {
            this.mColumbiaAdItemView = new ColumbiaAdItemview(this.mContext, this.mFragment);
            this.mColumbiaAdItemView.setGridItem(true);
            this.mColumbiaAdItemView.setCustomGridAdListener(this);
        }
        ItemAdViewHolder itemAdViewHolder = new ItemAdViewHolder(this.mColumbiaAdItemView.getNewView(0, viewGroup));
        this.mColumbiaAdItemView.getPopulatedView(i, itemAdViewHolder.itemView, (ViewGroup) itemAdViewHolder.itemView.getParent(), null);
        if (this.adItemHolderList.indexOf(Integer.valueOf(this.mListofAdposition.indexOf(Integer.valueOf(i)))) == -1) {
            this.adItemHolderList.add(itemAdViewHolder);
        }
    }
}

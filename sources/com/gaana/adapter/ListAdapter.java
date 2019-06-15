package com.gaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import com.collapsible_header.ListingFragmentMaterial;
import com.collapsible_header.SongParallexListingFragment;
import com.collapsible_header.e.a;
import com.collapsible_header.e.b;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.gaana.AutomatedPlaylistItemView;
import com.gaana.AutomatedPlaylistItemView.AutomatedPlaylistItemRowHolder;
import com.gaana.GaanaActivity;
import com.gaana.LocalFileSongsRecyclerView;
import com.gaana.LocalFileSongsRecyclerView.ItemRowHolder;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.SpinnerHolder;
import com.managers.ColombiaManager;
import com.managers.ap;
import com.managers.e;
import com.services.d;
import com.services.l.l;
import com.utilities.Util;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class ListAdapter extends Adapter<ViewHolder> {
    public static final int VIEW_TYPE_AD_BASE = 1002;
    public static final int VIEW_TYPE_AUTOPLAYLIST = 899;
    private static final int VIEW_TYPE_FOOTER = 101;
    private static final int VIEW_TYPE_HEADER = 0;
    private boolean IS_AUTOMATEDPLAYLIST = false;
    private boolean adEnabled = false;
    private int adOffset = 0;
    IAddListItemView iAddListItemView;
    private boolean is_trendinglist_onlocal_songs = false;
    private LocalFileSongsRecyclerView localFileSongsRecyclerView = null;
    ArrayList<Object> mArrrListItems;
    private AutomatedPlaylistItemView mAutoplaylistView = null;
    private ColumbiaAdItemview mColumbiaAdItemView = null;
    private Context mContext;
    private View mFooterView;
    private BaseGaanaFragment mFragment;
    private View mHeaderView;
    private boolean mIsSortPresent;
    private boolean mIsToAnimate;
    private int mLastPosition = -1;
    public ArrayList<Integer> mListofAdposition = new ArrayList();
    private l mLoadMoreListner;
    private int mPosition;
    private float mRowHeight;
    private int mScreenHeight;
    private boolean pullToRefresh = false;
    private boolean showRepetativeAdSpots = false;
    ArrayList<?> trendingArray = null;

    public interface IAddListItemView {
        View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup);

        ViewHolder createViewHolder(ViewGroup viewGroup, int i);

        int getItemViewType(int i);

        void showHideEmtpyView(boolean z);
    }

    public void setFooterView(View view) {
        this.mFooterView = view;
    }

    public void setHeaderView(View view) {
        this.mHeaderView = view;
    }

    public boolean isAutoPlaylist() {
        return this.IS_AUTOMATEDPLAYLIST;
    }

    public ListAdapter(Context context) {
        this.mContext = context;
        checkForAnimation();
    }

    public void setRepetativeAdSpots(boolean z) {
        this.showRepetativeAdSpots = z;
    }

    private void checkForAnimation() {
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        if (currentFragment != null && currentFragment.isToAnimateElements()) {
            this.mScreenHeight = (int) (((float) d.a().c()) - this.mContext.getResources().getDimension(R.dimen.bottom_bar_height));
            this.mRowHeight = this.mContext.getResources().getDimension(R.dimen.item_two_line_bar_height);
            this.mIsToAnimate = true;
        }
    }

    public ListAdapter(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        if (this.mFragment instanceof ListingFragmentMaterial) {
            this.adOffset = 1;
        } else if (this.mFragment instanceof SongParallexListingFragment) {
            this.adOffset = 2;
        }
        checkForAnimation();
    }

    public void setLoadMoreListner(l lVar) {
        this.mLoadMoreListner = lVar;
    }

    public void setAutomatedPlayList(AutomatedPlaylistItemView automatedPlaylistItemView, boolean z) {
        this.mAutoplaylistView = automatedPlaylistItemView;
        this.IS_AUTOMATEDPLAYLIST = z;
    }

    public void setAdEnabled(boolean z) {
        this.adEnabled = z;
    }

    public void setParamaters(ArrayList<?> arrayList, IAddListItemView iAddListItemView) {
        this.mArrrListItems = new ArrayList();
        this.mArrrListItems.addAll(arrayList);
        this.iAddListItemView = iAddListItemView;
        if (!this.showRepetativeAdSpots) {
            this.mListofAdposition.clear();
            initializeAdPositions(0, this.mArrrListItems.size());
        }
    }

    public void setAdSpots(ArrayList<Integer> arrayList) {
        if (this.showRepetativeAdSpots) {
            this.mListofAdposition.clear();
            if (this.mColumbiaAdItemView != null) {
                this.mColumbiaAdItemView.a();
            }
            this.mListofAdposition.addAll(arrayList);
        }
    }

    public ArrayList<Object> getAdapterArrayList() {
        return this.mArrrListItems;
    }

    public void setAdapterArrayList(ArrayList<?> arrayList) {
        if (arrayList != null) {
            this.mArrrListItems = arrayList;
            if (!this.showRepetativeAdSpots && arrayList.size() >= 0) {
                this.mListofAdposition.clear();
                initializeAdPositions(0, this.mArrrListItems.size());
            }
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        this.mArrrListItems.remove(obj);
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        this.mArrrListItems.clear();
        this.mListofAdposition.clear();
        notifyDataSetChanged();
    }

    public void updateAdapterArrayList(ArrayList<?> arrayList) {
        if (!(arrayList == null || arrayList.size() == 0)) {
            if (!this.showRepetativeAdSpots) {
                initializeAdPositions(this.mArrrListItems.size(), this.mArrrListItems.size() + arrayList.size());
            }
            this.mArrrListItems.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mArrrListItems.size();
    }

    public Object getItem(int i) {
        return this.mArrrListItems.size() > 0 ? this.mArrrListItems.get(getPositionwrtAd(i)) : null;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i >= 1002 && i < 1002 + this.mListofAdposition.size()) {
            if (this.mColumbiaAdItemView == null) {
                this.mColumbiaAdItemView = new ColumbiaAdItemview(this.mContext, this.mFragment);
            }
            return new ItemAdViewHolder(this.mColumbiaAdItemView.getNewView(0, viewGroup));
        } else if (i == VIEW_TYPE_AUTOPLAYLIST && this.IS_AUTOMATEDPLAYLIST) {
            if (this.pullToRefresh) {
                return new AutomatedPlaylistItemRowHolder(this.mAutoplaylistView.createViewHolder(viewGroup, i));
            }
            View view = new View(this.mContext);
            view.setLayoutParams(new LayoutParams(-2, 1));
            return new AutomatedPlaylistItemRowHolder(view);
        } else if (this.is_trendinglist_onlocal_songs && i == LocalFileSongsRecyclerView.TRENDING_LOCALFILE_ITEM_VIEW) {
            if (this.trendingArray == null || (this.trendingArray.size() <= 0 && !Util.j(this.mContext))) {
                return new ItemRowHolder(new View(this.mContext));
            }
            return new ItemRowHolder(this.localFileSongsRecyclerView.createViewHolder(viewGroup, i));
        } else if (i == 0 && this.mHeaderView != null) {
            return new b(this.mHeaderView);
        } else {
            if (this.mFooterView == null || i != 101) {
                return this.iAddListItemView.createViewHolder(viewGroup, i);
            }
            return new a(this.mFooterView);
        }
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        if (this.mAutoplaylistView != null) {
            this.mAutoplaylistView.hideLayout();
        }
    }

    public int getItemPosition() {
        return this.mPosition;
    }

    public void setPullToRefresh(boolean z) {
        this.pullToRefresh = z;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        View view = viewHolder.itemView;
        if (this.mLoadMoreListner != null) {
            if ((i == getCount() - 1 ? 1 : false) != 0) {
                this.mLoadMoreListner.loadMoreData(getCount(), getItem(i));
            }
        }
        this.mPosition = i;
        if (viewHolder instanceof ItemAdViewHolder) {
            if (this.mColumbiaAdItemView == null) {
                this.mColumbiaAdItemView = new ColumbiaAdItemview(this.mContext, this.mFragment);
            }
            this.mColumbiaAdItemView.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), null);
        } else if ((viewHolder instanceof AutomatedPlaylistItemRowHolder) && i == 0 && this.mAutoplaylistView != null) {
            this.mAutoplaylistView.getPoplatedView(viewHolder, this.pullToRefresh);
            this.pullToRefresh = false;
        } else if ((viewHolder instanceof ItemRowHolder) && i == 0 && this.is_trendinglist_onlocal_songs) {
            this.localFileSongsRecyclerView.getPoplatedView(viewHolder, this.pullToRefresh, this.trendingArray);
            this.pullToRefresh = false;
        } else if (this.mHeaderView == null && this.mFooterView == null && !this.mIsSortPresent) {
            view = this.iAddListItemView.addListItemView(getItem(i), viewHolder, null);
        } else if (!(viewHolder instanceof b) && !(viewHolder instanceof a)) {
            view = (this.mIsSortPresent && i == 1 && (viewHolder instanceof SpinnerHolder)) ? this.iAddListItemView.addListItemView(null, viewHolder, null) : this.mHeaderView != null ? this.iAddListItemView.addListItemView(getItem(i - 1), viewHolder, null) : (this.mFooterView == null || i >= getItemCount() - 1) ? this.iAddListItemView.addListItemView(getItem(i), viewHolder, null) : this.iAddListItemView.addListItemView(getItem(i), viewHolder, null);
        } else {
            return;
        }
        setAnimation(view, i);
    }

    public void setSortItem(boolean z) {
        this.mIsSortPresent = z;
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        viewHolder.itemView.clearAnimation();
        super.onViewDetachedFromWindow(viewHolder);
    }

    private void setAnimation(View view, int i) {
        if (this.mIsToAnimate && view != null && i > this.mLastPosition && ((float) i) < (((float) this.mScreenHeight) / this.mRowHeight) - 2.0f) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.slide_list_up);
            loadAnimation.setDuration(700);
            loadAnimation.setInterpolator(new DecelerateInterpolator(3.0f));
            view.startAnimation(loadAnimation);
            this.mLastPosition = i;
        }
    }

    public int getItemCount() {
        int size = (this.mListofAdposition == null || this.mListofAdposition.size() <= 0) ? 0 : this.mListofAdposition.size();
        boolean z = this.IS_AUTOMATEDPLAYLIST;
        boolean z2 = this.is_trendinglist_onlocal_songs;
        if (this.mHeaderView == null && this.mFooterView == null) {
            return ((this.mArrrListItems.size() + size) + z) + z2;
        }
        if (this.mFooterView != null && this.mHeaderView == null) {
            return (((this.mArrrListItems.size() + 1) + size) + z) + z2;
        }
        if (this.mFooterView != null || this.mHeaderView == null) {
            return (((this.mArrrListItems.size() + 2) + size) + z) + z2;
        }
        return (((this.mArrrListItems.size() + 1) + size) + z) + z2;
    }

    public int getItemViewType(int i) {
        if (this.mListofAdposition.contains(Integer.valueOf(i))) {
            return 1002 + this.mListofAdposition.indexOf(Integer.valueOf(i));
        }
        if (i == 0 && this.IS_AUTOMATEDPLAYLIST) {
            return VIEW_TYPE_AUTOPLAYLIST;
        }
        if (this.is_trendinglist_onlocal_songs && i == 0) {
            return LocalFileSongsRecyclerView.TRENDING_LOCALFILE_ITEM_VIEW;
        }
        if (this.mFooterView == null && this.mHeaderView == null) {
            return this.iAddListItemView.getItemViewType(i);
        }
        if (i == 0 && this.mHeaderView != null) {
            return 0;
        }
        if (i != getItemCount() - 1 || this.mFooterView == null) {
            return this.iAddListItemView.getItemViewType(i);
        }
        return 101;
    }

    public void setTrendingView(ArrayList<?> arrayList, LocalFileSongsRecyclerView localFileSongsRecyclerView, boolean z) {
        this.trendingArray = arrayList;
        this.localFileSongsRecyclerView = localFileSongsRecyclerView;
        this.is_trendinglist_onlocal_songs = z;
    }

    public ArrayList<Integer> getListofAdPositions() {
        return this.mListofAdposition;
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

    public boolean onItemMove(int i, int i2) {
        Collections.swap(this.mArrrListItems, i, i2);
        notifyItemMoved(i, i2);
        return true;
    }

    private int getPositionwrtAd(int i) {
        int i2 = 0;
        int i3 = (i <= 0 || !this.IS_AUTOMATEDPLAYLIST) ? 0 : 1;
        int i4 = (i <= 0 || !this.is_trendinglist_onlocal_songs) ? 0 : 1;
        if (this.mListofAdposition.size() > 0 && i == this.mArrrListItems.size() && i == ((Integer) this.mListofAdposition.get(this.mListofAdposition.size() - 1)).intValue()) {
            return ((i - 1) - i3) - i4;
        }
        Iterator it = this.mListofAdposition.iterator();
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() <= this.adOffset + i) {
                i2++;
            }
        }
        return ((i - i2) - i3) - i4;
    }

    public void onRefresh(boolean z) {
        if (z && ap.a().b(this.mContext)) {
            ColombiaManager.b().c();
            if (this.mColumbiaAdItemView != null) {
                this.mColumbiaAdItemView.a();
            }
        }
    }

    private void initializeAdPositions(int i, int i2) {
        if (!ap.a().b(this.mContext) || !GaanaApplication.getInstance().getColombiaSdkInit() || !this.adEnabled) {
            return;
        }
        int i3;
        if (!(this.mContext instanceof GaanaActivity) || !(((GaanaActivity) this.mContext).getCurrentFragment() instanceof ArtistDetailsMaterialListing)) {
            i3 = i2;
            while (i < i3) {
                if (i == 4) {
                    this.mListofAdposition.add(Integer.valueOf(i));
                    i3++;
                } else if (i != 0 && (i - 4) % 7 == 0) {
                    this.mListofAdposition.add(Integer.valueOf(i));
                    i3++;
                }
                i++;
            }
            if (this.mListofAdposition.isEmpty()) {
                this.mListofAdposition.add(Integer.valueOf(i2));
            }
        } else if (TextUtils.isEmpty(getChannelPageAdCode()) && e.ad == 0) {
            i3 = i2;
            while (i < i3) {
                if (i == 4) {
                    this.mListofAdposition.add(Integer.valueOf(i));
                    i3++;
                } else if (i != 0 && (i - 4) % 7 == 0) {
                    this.mListofAdposition.add(Integer.valueOf(i));
                    i3++;
                }
                i++;
            }
            if (this.mListofAdposition.isEmpty()) {
                this.mListofAdposition.add(Integer.valueOf(i2));
            }
        }
    }

    private String getChannelPageAdCode() {
        String str = "";
        BusinessObject a = GaanaApplication.getInstance().getListingComponents().a();
        if (a == null || !(a instanceof Album)) {
            return (a == null || !(a instanceof Playlist)) ? str : ((Playlist) a).getChannelPageAdCode();
        } else {
            return ((Album) a).getChannelPageAdCode();
        }
    }
}

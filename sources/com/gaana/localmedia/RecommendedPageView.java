package com.gaana.localmedia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.adapter.GaanaViewAdapterNew.OnGetViewCalledListener;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.view.GaanaListView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.TwoRecommendedItemHolder;
import com.gaana.view.item.RecommendedItemView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.i.i;
import com.managers.ColombiaAdViewManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.e;
import com.services.l.ap;
import com.services.l.s;
import com.utilities.d;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RecommendedPageView implements ap {
    private static final int VIEW_TYPE_AD = 2;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_LIST_ITEM = 1;
    private int REPETATIVE_AD_INTERVAL = 4;
    private Drawable download;
    private boolean headerRemoved = false;
    private boolean isRefreshing = false;
    private LayoutInflater layoutInflater;
    private ArrayList<Integer> listofAdPositions = new ArrayList();
    private LinearLayout llContentLayout;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private GaanaListView mGaanaListView;
    private View mHeaderView;
    private boolean mIsDownLoadFragment;
    private ap mSwipeRefreshListener;
    private URLManager mUrlManager;
    private Drawable mymusic;
    private Drawable occasion;
    private int pageType = 0;
    private ProgressBar progressBar;
    private Drawable search_albums;
    private Drawable search_artist;
    private Drawable search_playlist;
    private Drawable search_radio;
    private Drawable search_song;

    public interface PageType {
        public static final int ALBUM = 0;
        public static final int ARTIST = 3;
        public static final int DOWNLOADS = 5;
        public static final int FAVORITE_OCCASION = 7;
        public static final int MY_MUSIC = 6;
        public static final int PLAYLIST = 2;
        public static final int RADIO = 4;
        public static final int TRACK = 1;
    }

    public static class RecommendedHeaderHolder extends ViewHolder {
        private ImageView headerImage;
        private TextView headerText;

        public RecommendedHeaderHolder(View view) {
            super(view);
            this.headerText = (TextView) view.findViewById(R.id.tvReceommendedType);
            this.headerImage = (ImageView) view.findViewById(R.id.imgReceommendedType);
        }
    }

    private Context getContext() {
        return this.mContext;
    }

    public RecommendedPageView(Context context) {
        this.mContext = context;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
        if (obtainStyledAttributes.getResourceId(31, -1) != -1) {
            this.search_artist = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(101, -1));
            this.search_radio = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(103, -1));
            this.search_albums = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(100, -1));
            this.search_playlist = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(102, -1));
            this.search_song = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(104, -1));
            this.download = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(5, -1));
            this.mymusic = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1));
            this.occasion = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(26, -1));
            obtainStyledAttributes.recycle();
        }
    }

    public void setSwipeRefreshListener(ap apVar) {
        this.mSwipeRefreshListener = apVar;
    }

    private void insertAdSpots() {
        int size = this.mGaanaListView.getBusinessObject().getArrListBusinessObj().size() / 2;
        if (this.listofAdPositions.size() == 0 && size > 0) {
            if (!this.listofAdPositions.contains(Integer.valueOf(this.REPETATIVE_AD_INTERVAL - 1))) {
                this.listofAdPositions.add(Integer.valueOf(this.REPETATIVE_AD_INTERVAL - 1));
            }
            insertAdSpots();
        } else if (((Integer) this.listofAdPositions.get(this.listofAdPositions.size() - 1)).intValue() < size) {
            size = ((Integer) this.listofAdPositions.get(this.listofAdPositions.size() - 1)).intValue() + (this.REPETATIVE_AD_INTERVAL - 1);
            if (!this.listofAdPositions.contains(Integer.valueOf(size))) {
                this.listofAdPositions.add(Integer.valueOf(size));
            }
            insertAdSpots();
        }
    }

    public void onSwipeRefresh() {
        this.isRefreshing = true;
        if (this.mSwipeRefreshListener == null) {
            retrieveRecommendedPageData();
        } else {
            this.mSwipeRefreshListener.onSwipeRefresh();
        }
    }

    private int getType(BusinessObjectType businessObjectType) {
        if (businessObjectType == BusinessObjectType.Albums) {
            return 0;
        }
        if (businessObjectType == BusinessObjectType.Playlists) {
            return 2;
        }
        if (businessObjectType == BusinessObjectType.Artists) {
            return 3;
        }
        if (businessObjectType == BusinessObjectType.Tracks) {
            return 1;
        }
        if (businessObjectType == BusinessObjectType.Radios) {
            return 4;
        }
        if (businessObjectType == BusinessObjectType.FavoriteOccasions) {
            return 7;
        }
        return 0;
    }

    private String getAdSectionName() {
        switch (this.pageType) {
            case 0:
                return "FAV_AL_320x100";
            case 1:
                return "FAV_TR_320x100";
            case 2:
                return "FAV_PL_320x100";
            case 3:
                return "FAV_AR_320x100";
            case 4:
                return "FAV_RD_320x100";
            default:
                return "";
        }
    }

    public void refreshListView(boolean z) {
        if (this.mGaanaListView != null && this.mGaanaListView.getListAdapter() != null) {
            this.mGaanaListView.getListAdapter().notifyDataSetChanged();
            if (z && !this.headerRemoved && this.mHeaderView != null) {
                animateHeader();
            }
        }
    }

    public void refreshListView() {
        refreshListView(false);
    }

    private void animateHeader() {
        this.headerRemoved = true;
        if (d.g()) {
            int measuredHeight = this.mHeaderView.getMeasuredHeight();
            final LayoutParams layoutParams = (LayoutParams) this.mHeaderView.getLayoutParams();
            ObjectAnimator duration = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f}).setDuration(300);
            ValueAnimator.ofInt(new int[]{measuredHeight, 0}).setDuration(400).addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (layoutParams != null) {
                        layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        if (RecommendedPageView.this.mHeaderView != null) {
                            RecommendedPageView.this.mHeaderView.setLayoutParams(layoutParams);
                        }
                    }
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{duration, r1});
            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (RecommendedPageView.this.mHeaderView != null) {
                        RecommendedPageView.this.mHeaderView = null;
                    }
                }
            });
            animatorSet.start();
        } else if (this.mHeaderView != null) {
            this.mHeaderView = null;
        }
    }

    public void removeMessageHeaderView() {
        if (this.mHeaderView != null && this.mGaanaListView != null) {
            this.mHeaderView = null;
        }
    }

    public View getRecommendedView(BaseGaanaFragment baseGaanaFragment, Context context, BusinessObjectType businessObjectType) {
        return getRecommendedView(baseGaanaFragment, context, getType(businessObjectType));
    }

    public View getRecommendedView(BaseGaanaFragment baseGaanaFragment, Context context, int i) {
        this.layoutInflater = LayoutInflater.from(context);
        View inflate = this.layoutInflater.inflate(R.layout.recommended_page_view, null);
        this.llContentLayout = (LinearLayout) inflate.findViewById(R.id.llContentLayout);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.pageType = i;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mGaanaListView = new GaanaListView(this.mContext, this.mFragment);
        this.mGaanaListView.setSwipeRefreshListener(this);
        this.mUrlManager = getUrlManager(i);
        if (this.mUrlManager != null) {
            retrieveRecommendedPageData();
        } else {
            this.mHeaderView = addMessageLayout();
            this.llContentLayout.addView(this.mHeaderView);
        }
        return inflate;
    }

    private void retrieveRecommendedPageData() {
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                int i;
                if (RecommendedPageView.this.isRefreshing && RecommendedPageView.this.mGaanaListView != null) {
                    RecommendedPageView.this.mGaanaListView.onRefreshCompleted();
                }
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                    i = 0;
                } else {
                    i = businessObject.getArrListBusinessObj().size();
                    RecommendedPageView.this.mGaanaListView.setBusinessObject(businessObject);
                    RecommendedPageView.this.mGaanaListView.addGridListContent(businessObject, 0, 2, RecommendedItemView.class.getName());
                }
                RecommendedPageView.this.insertAdSpots();
                int size = ((i / 2) + (i % 2)) + RecommendedPageView.this.listofAdPositions.size();
                if (RecommendedPageView.this.isRefreshing) {
                    RecommendedPageView.this.refreshListView();
                    RecommendedPageView.this.isRefreshing = false;
                    return;
                }
                RecommendedPageView.this.progressBar.setVisibility(0);
                RecommendedPageView.this.setGaanaHomeAdapter(size, businessObject, RecommendedPageView.this.mUrlManager.e().booleanValue());
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (RecommendedPageView.this.isRefreshing && RecommendedPageView.this.mGaanaListView != null) {
                    RecommendedPageView.this.mGaanaListView.onRefreshCompleted();
                }
                RecommendedPageView.this.isRefreshing = false;
            }
        }, this.mUrlManager, Boolean.valueOf(true));
    }

    private View addMessageLayout() {
        this.mHeaderView = this.layoutInflater.inflate(R.layout.recommended_page_header, null);
        setImageAndTitle(this.mContext, this.pageType, (ImageView) this.mHeaderView.findViewById(R.id.imgReceommendedType), (TextView) this.mHeaderView.findViewById(R.id.tvReceommendedType));
        return this.mHeaderView;
    }

    private View addMessageLayout(ViewHolder viewHolder) {
        RecommendedHeaderHolder recommendedHeaderHolder = (RecommendedHeaderHolder) viewHolder;
        setImageAndTitle(this.mContext, this.pageType, recommendedHeaderHolder.headerImage, recommendedHeaderHolder.headerText);
        return this.mHeaderView;
    }

    public void setGaanaHomeAdapter(int i, BusinessObject businessObject, boolean z) {
        this.progressBar.setVisibility(8);
        this.llContentLayout.removeAllViews();
        this.llContentLayout.addView(this.mGaanaListView.getPopulatedView(), 0);
        this.mGaanaListView.setAdapterParams(this.mContext, i, new OnGetViewCalledListener() {
            ColumbiaAdItemview mColumbiaAdItemView;

            public int getItemViewType(int i) {
                if (RecommendedPageView.this.listofAdPositions.contains(Integer.valueOf(i))) {
                    return 2;
                }
                return i == 0 ? 0 : 1;
            }

            public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                if (i == 1) {
                    return new TwoRecommendedItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_two_item_recommended, viewGroup, false));
                }
                if (i == 2) {
                    return new ItemAdViewHolder(RecommendedPageView.this.getADView(viewGroup));
                }
                return new RecommendedHeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommended_page_header, viewGroup, false));
            }

            public void onBindViewHolder(ViewHolder viewHolder, int i) {
                if (RecommendedPageView.this.listofAdPositions.contains(Integer.valueOf(i))) {
                    if (viewHolder != null && (viewHolder instanceof ItemAdViewHolder)) {
                        ColombiaAdViewManager.a().a(RecommendedPageView.this.mContext, (LinearLayout) viewHolder.itemView, new PublisherAdView(RecommendedPageView.this.mContext), e.N, null, 100, RecommendedPageView.this.getAdSectionName(), new AdsUJData[0]);
                    }
                } else if (viewHolder.getItemViewType() == 1) {
                    RecommendedPageView.this.mGaanaListView.populateGridItem(RecommendedPageView.this.getActualItemIndex(i), viewHolder.itemView, viewHolder);
                } else {
                    RecommendedPageView.this.addMessageLayout(viewHolder);
                }
            }
        });
    }

    public View getADView(ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
    }

    private int getActualItemIndex(int i) {
        Iterator it = this.listofAdPositions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() < i) {
                i2++;
            }
        }
        return i - i2;
    }

    private void setImageAndTitle(Context context, int i, ImageView imageView, TextView textView) {
        Drawable drawable = this.search_song;
        if (i == 0) {
            i = isDownLoadFragment() ? R.string.recom_album_download_message : R.string.recom_album_message;
            drawable = this.search_albums;
        } else if (i == 2) {
            i = isDownLoadFragment() ? R.string.recom_playlist_download_message : R.string.recom_playlist_message;
            drawable = this.search_playlist;
        } else if (i == 3) {
            i = R.string.recom_artist_message;
            drawable = this.search_artist;
        } else if (i == 4) {
            i = R.string.recom_radio_message;
            drawable = this.search_radio;
        } else if (i == 1) {
            i = isDownLoadFragment() ? R.string.recom_track_download_message : R.string.recom_track_message;
            drawable = this.search_song;
        } else if (i == 5) {
            i = R.string.recom_download_message;
            drawable = this.download;
        } else if (i == 6) {
            i = R.string.recom_mymusic_message;
            drawable = this.mymusic;
        } else if (i == 7) {
            i = R.string.NO_DATA;
            drawable = this.occasion;
        } else {
            i = -1;
        }
        if (i != -1) {
            textView.setText(context.getResources().getString(i));
        }
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public URLManager getUrlManager(int i) {
        URLManager uRLManager = new URLManager();
        if (i == 3) {
            uRLManager.a(BusinessObjectType.Artists);
            uRLManager.a("https://api.gaana.com/index.php?type=artist&subtype=most_popular");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 4) {
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a("https://api.gaana.com/home/gaana-radio-listing?limit=0,20");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 1) {
            uRLManager.a(BusinessObjectType.Tracks);
            uRLManager.a("https://api.gaana.com/index.php?type=song&subtype=most_popular");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 2) {
            uRLManager.a(BusinessObjectType.GenericItems);
            HashMap hashMap = new HashMap();
            uRLManager.a("https://api.gaana.com/playlist/my-music/featured-playlist");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 0) {
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a("https://apiv2.gaana.com/home/album/featured");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 5) {
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a("https://apiv2.gaana.com/home/album/featured");
            uRLManager.a(Boolean.valueOf(false));
        } else if (i == 6 || i == 7) {
            uRLManager = null;
        }
        if (uRLManager != null) {
            uRLManager.c(Boolean.valueOf(this.isRefreshing));
        }
        return uRLManager;
    }

    public void setIsDownloadFragment(boolean z) {
        this.mIsDownLoadFragment = z;
    }

    public boolean isDownLoadFragment() {
        return this.mIsDownLoadFragment;
    }
}

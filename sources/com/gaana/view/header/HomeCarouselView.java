package com.gaana.view.header;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.f;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.CarouselItemView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.i.i;
import com.managers.ColombiaAdViewManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.an;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class HomeCarouselView extends BaseItemView implements OnClickListener, a, b<Object> {
    public boolean _isADLoaded = false;
    private String adCode;
    private int adPosition = 0;
    View carouselParentView;
    private long initialTime = 0;
    private int itemBottomPadding = 0;
    private View mCarousalView = null;
    CarouselItemView mCarouselItemView = null;
    private Context mContext = null;
    private f.a mDynamicView;
    private BaseGaanaFragment mFragment;
    private boolean mRefreshCarousel = true;
    private URLManager mUrlManager = null;
    private int viewSizeHeight = 0;

    public HomeCarouselView(Context context, BaseGaanaFragment baseGaanaFragment, f.a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
        this.mUrlManager = getCarouselUrlmanager();
        if (aVar.e() == VIEW_SIZE.CAROUSEL_VIEW_XL_SQUARE.getNumVal()) {
            this.viewSizeHeight = (int) getResources().getDimension(R.dimen.carousel_view_height_xl_square);
        } else {
            this.viewSizeHeight = (int) getResources().getDimension(R.dimen.carousel_view_height);
        }
        this.adCode = aVar.q();
        this.adPosition = getAdPosition(aVar);
    }

    public int getAdPosition(f.a aVar) {
        Map j = aVar.j();
        if (j == null) {
            return 0;
        }
        return Integer.parseInt((String) j.get("ad_post"));
    }

    public int getViewHeight() {
        return this.viewSizeHeight;
    }

    public void mTimerStart() {
        if (this.mCarouselItemView != null) {
            this.mCarouselItemView.startCarouselTimer();
        }
    }

    public void mTimerCancel() {
        if (this.mCarouselItemView != null) {
            this.mCarouselItemView.cancelCarouselTimer();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.carouselParentView = getNewView(R.layout.home_carousel_view, viewGroup);
        this.carouselParentView.getLayoutParams().height = this.viewSizeHeight;
        return new ItemAdViewHolder(this.carouselParentView);
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.mCarouselItemView = new CarouselItemView(this.mContext, this.mFragment, this.mDynamicView.p(), this.mDynamicView.k(), this.mDynamicView.e(), this.mDynamicView.y());
        return this.mCarouselItemView.getNewView(i, viewGroup);
    }

    public void setFirstCall(boolean z) {
        this.mRefreshCarousel = z;
    }

    public void setIsToBeRefreshed(boolean z) {
        if (this.mUrlManager != null) {
            this.mUrlManager.c(Boolean.valueOf(z));
            if (z && this.mUrlManager != null) {
                this.initialTime = Calendar.getInstance().getTimeInMillis();
                i.a().a(this.mUrlManager, this.mFragment.toString(), (b) this, (a) this);
            }
        }
    }

    private URLManager getCarouselUrlmanager() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.GenericItems);
        if (this.mDynamicView != null) {
            uRLManager.l(this.mDynamicView.z());
            uRLManager.a(this.mDynamicView.l());
            if (this.mDynamicView.g() != null) {
                uRLManager.a(Integer.parseInt(this.mDynamicView.g()));
            }
        } else {
            uRLManager.a("https://apiv2.gaana.com/home/showcase");
        }
        uRLManager.c(Boolean.valueOf(false));
        uRLManager.b(Boolean.valueOf(true));
        return uRLManager;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup, boolean z) {
        this.mCarousalView = viewHolder.itemView;
        if (this.mRefreshCarousel) {
            this.mRefreshCarousel = false;
            if (this.mUrlManager != null) {
                this.initialTime = Calendar.getInstance().getTimeInMillis();
                i.a().a(this.mUrlManager, this.mFragment.toString(), (b) this, (a) this);
            }
        }
        if (z && this.mCarouselItemView != null) {
            this.mCarouselItemView.setLightModeON(isLightsOnForDynamicView(this.mDynamicView));
            this.mCarouselItemView.refreshAdapter();
        }
        return this.mCarousalView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        this.mCarousalView = viewHolder.itemView;
        if (this.mRefreshCarousel) {
            this.mRefreshCarousel = false;
            if (this.mUrlManager != null) {
                this.initialTime = Calendar.getInstance().getTimeInMillis();
                i.a().a(this.mUrlManager, this.mFragment.toString(), (b) this, (a) this);
            }
        }
        return this.mCarousalView;
    }

    public f.a getDynamicView() {
        return this.mDynamicView;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.mRefreshCarousel = true;
        if (this.mUrlManager != null) {
            this.mUrlManager.c(Boolean.valueOf(false));
        }
        if (this.mFragment != null && this.mFragment.isAdded() && (this.mFragment instanceof DynamicHomeFragment) && !((DynamicHomeFragment) this.mFragment).f() && this.carouselParentView != null && this.carouselParentView.getLayoutParams() != null) {
            this.carouselParentView.getLayoutParams().height = 0;
        }
    }

    public void onResponse(Object obj) {
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject != null) {
            Items items = (Items) businessObject;
            if (items.getArrListBusinessObj() != null && items.getArrListBusinessObj().size() > 0) {
                ArrayList arrListBusinessObj = items.getArrListBusinessObj();
                insertCarouselAd(arrListBusinessObj);
                if (this.mUrlManager != null) {
                    this.mUrlManager.c(Boolean.valueOf(false));
                }
                if (this.mCarouselItemView != null) {
                    this.mCarouselItemView.setLightModeON(isLightsOnForDynamicView(this.mDynamicView));
                    if (arrListBusinessObj != null && arrListBusinessObj.size() != 0) {
                        this.mCarouselItemView.getPopulatedView(arrListBusinessObj);
                    } else if (!(this.carouselParentView == null || this.carouselParentView.getLayoutParams() == null)) {
                        this.carouselParentView.getLayoutParams().height = 0;
                    }
                } else if (!(this.carouselParentView == null || this.carouselParentView.getLayoutParams() == null)) {
                    this.carouselParentView.getLayoutParams().height = 0;
                }
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                if (this.initialTime != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(this.mDynamicView.k());
                    Constants.a("Load", timeInMillis - this.initialTime, "Page", stringBuilder.toString());
                    return;
                }
                return;
            }
        }
        if (this.carouselParentView != null && this.carouselParentView.getLayoutParams() != null) {
            this.carouselParentView.getLayoutParams().height = 0;
        }
    }

    private void insertCarouselAd(final ArrayList<Item> arrayList) {
        if (!TextUtils.isEmpty(this.adCode)) {
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName("carousal");
            adsUJData.setAdUnitCode(this.adCode);
            adsUJData.setSectionId("");
            adsUJData.setAdType("ctn");
            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
            ColombiaAdViewManager.a().a(this.mContext, this.adCode, new ColombiaAdViewManager.a() {
                public void DFPAdFailed() {
                }

                public void DFPAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    if (!unifiedNativeAd.getVideoController().hasVideoContent()) {
                        Item item = new Item();
                        EntityInfo entityInfo = new EntityInfo();
                        entityInfo.setKey("atw_alt");
                        if (unifiedNativeAd.getImages().size() > 0) {
                            entityInfo.setValue(((Image) unifiedNativeAd.getImages().get(0)).getUri().toString());
                        }
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(entityInfo);
                        item.setEntityInfo(arrayList);
                        item.setEntityType("CTNAD");
                        if (HomeCarouselView.this.adPosition <= arrayList.size() - 1) {
                            arrayList.add(HomeCarouselView.this.adPosition, item);
                        }
                        if (HomeCarouselView.this.mCarouselItemView != null) {
                            HomeCarouselView.this.mCarouselItemView.setADItem(unifiedNativeAd);
                            HomeCarouselView.this.mCarouselItemView.updateAdapterCount(arrayList.size());
                            HomeCarouselView.this.mCarouselItemView.refreshAdapter();
                        }
                        if (adsUJData != null) {
                            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                        }
                    }
                }
            });
        }
    }

    private boolean isLightsOnForDynamicView(f.a aVar) {
        Map j = aVar.j();
        boolean z = j != null && j.containsKey("theme") && ((String) j.get("theme")).equalsIgnoreCase("1");
        if (z && Constants.du) {
            return true;
        }
        return false;
    }
}

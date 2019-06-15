package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.h;
import com.fragments.BaseGaanaFragment;
import com.fragments.BaseGaanaFragment.a;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.juke.JukeSessionManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.carousel.RVPagerSnapDecorator;
import com.gaana.view.carousel.RVPagerSnapHelper;
import com.gaana.view.header.CarouselPagerAdapter;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.i.i;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.l.ag;
import com.services.l.s;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CarouselItemView extends BaseItemView {
    public static final int SCROLL_WHAT = 0;
    private CarouselItemViewHandler autoScrollHandler;
    private ArrayList<Item> carouselData = null;
    OnPageChangeListener carouselPageChangeListener = new OnPageChangeListener() {
        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (i < CarouselItemView.this.carouselData.size()) {
                CarouselItemView.this.mCurrentItem = i;
                Item item = (Item) CarouselItemView.this.carouselData.get(i);
                if (item.getEntityType().equalsIgnoreCase(c.h)) {
                    CarouselItemView.this.impressionHandler(item, i);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 1) {
                CarouselItemView.this.stopAutoScroll();
            }
        }
    };
    private RecyclerView carouselPager = null;
    private CarouselPagerAdapter carouselPagerAdapter = null;
    private int currentState;
    private boolean isAutoScroll = false;
    private boolean isLightModeON = false;
    private boolean isTimerStart = false;
    private Context mContext = null;
    private int mCurrentItem = 0;
    private BaseGaanaFragment mFragment;
    private String mHeader;
    private int mItemClickedPosition = -1;
    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 1) {
                CarouselItemView.this.stopAutoScroll();
            }
            super.onScrollStateChanged(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }
    };
    private String mSourceName;
    private String mUniqueId;
    private int previousState;
    private int viewSize = 0;

    private static class CarouselItemViewHandler extends Handler {
        private final WeakReference<CarouselItemView> carouselItemViewWeakReference;

        public CarouselItemViewHandler(CarouselItemView carouselItemView) {
            this.carouselItemViewWeakReference = new WeakReference(carouselItemView);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                CarouselItemView carouselItemView = (CarouselItemView) this.carouselItemViewWeakReference.get();
                if (carouselItemView != null) {
                    carouselItemView.scrollOnce();
                    carouselItemView.handleAutoScrollRunnableCall();
                }
            }
        }
    }

    public void emptyViewPagerInflated() {
    }

    public void setADItem(UnifiedNativeAd unifiedNativeAd) {
        this.carouselPagerAdapter.setADItem(unifiedNativeAd);
        unifiedNativeAd.getVideoController().setVideoLifecycleCallbacks(new VideoLifecycleCallbacks() {
            public void onVideoStart() {
                super.onVideoStart();
            }

            public void onVideoPlay() {
                super.onVideoPlay();
                CarouselItemView.this.stopAutoScroll();
            }

            public void onVideoPause() {
                super.onVideoPause();
                CarouselItemView.this.startAutoScroll();
            }

            public void onVideoEnd() {
                super.onVideoEnd();
                CarouselItemView.this.startAutoScroll();
            }
        });
    }

    public CarouselItemView(Context context, BaseGaanaFragment baseGaanaFragment, String str, String str2, int i, String str3) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mSourceName = str;
        this.mHeader = str2;
        this.viewSize = i;
        this.mUniqueId = str3;
    }

    public String getSourceType() {
        return this.mSourceName;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        View newView = super.getNewView(i, viewGroup);
        this.carouselData = new ArrayList();
        this.carouselPager = (RecyclerView) newView.findViewById(R.id.carouselPager);
        this.carouselPagerAdapter = new CarouselPagerAdapter(this.mContext, this.carouselData);
        this.carouselPagerAdapter.setLayoutId(h.a(this.viewSize));
        this.carouselPagerAdapter.setVideoLayoutId(h.b(this.viewSize));
        this.carouselPager.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.carouselPager.setAdapter(this.carouselPagerAdapter);
        this.carouselPager.addItemDecoration(new RVPagerSnapDecorator(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp15), this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin)));
        this.carouselPager.addOnScrollListener(this.mOnScrollListener);
        new RVPagerSnapHelper(this.carouselPager, this.carouselPageChangeListener).attachToRecyclerView(this.carouselPager);
        this.carouselPagerAdapter.setCarouselData(this.carouselData, 1, false);
        this.carouselPagerAdapter.setCarouselListener(this);
        this.mFragment.addFragmentListener(Constants.ai, new a() {
            public void onDestroy() {
            }
        });
        dipToPixels(this.mContext, getScreenWidthinDp(this.mContext));
        if (this.autoScrollHandler == null) {
            this.autoScrollHandler = new CarouselItemViewHandler(this);
        }
        startAutoScroll();
        return newView;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.carouselPager.removeOnScrollListener(this.mOnScrollListener);
        stopAutoScroll();
    }

    private void sendScrollMessage(long j) {
        if (this.autoScrollHandler != null) {
            this.autoScrollHandler.removeMessages(0);
            this.autoScrollHandler.sendEmptyMessageDelayed(0, j);
        }
    }

    public void startAutoScroll(int i) {
        this.isAutoScroll = true;
        sendScrollMessage((long) i);
    }

    public void stopAutoScroll() {
        if (this.autoScrollHandler != null) {
            this.isAutoScroll = false;
            this.autoScrollHandler.removeMessages(0);
        }
    }

    private void scrollOnce() {
        int i;
        int i2 = this.mCurrentItem;
        if (i2 == (this.carouselPagerAdapter != null ? this.carouselPagerAdapter.getCount() : this.carouselData.size()) - 1) {
            i2 = -1;
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            i2++;
            this.carouselPager.smoothScrollToPosition(i2);
            this.mCurrentItem = i2;
            return;
        }
        i2++;
        this.mCurrentItem = i2;
        this.carouselPager.scrollToPosition(i2);
    }

    public void updateAdapterCount(int i) {
        this.carouselPagerAdapter.setCount(i);
    }

    private void handleAutoScrollRunnableCall() {
        int i = this.mCurrentItem;
        if (this.carouselData != null && this.carouselData.size() > 0 && i <= this.carouselData.size() - 1 && i >= 0) {
            ArrayList arrayList = (ArrayList) ((Item) this.carouselData.get(i)).getEntityInfo();
            int i2 = 0;
            i = arrayList != null ? ((Item) this.carouselData.get(i)).getEntityInfo().size() : 0;
            if (i > 0) {
                while (i2 < i) {
                    String key = ((EntityInfo) arrayList.get(i2)).getKey();
                    if (key.equals("auto_play_time")) {
                        startAutoScroll(Integer.parseInt((String) ((EntityInfo) arrayList.get(i2)).getValue()) * 1000);
                        break;
                    }
                    if (key.equals("atw_alt")) {
                        startAutoScroll(SsoErrorCodes.SDK_NOT_INITIALIZED);
                    } else if (key.equals("artwork_gif")) {
                        startAutoScroll(8000);
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    private void startAutoScroll() {
        handleAutoScrollRunnableCall();
    }

    public void startCarouselTimer() {
        startAutoScroll();
    }

    public void cancelCarouselTimer() {
        stopAutoScroll();
    }

    public void setItemPosition(int i) {
        this.mItemClickedPosition = i;
    }

    public void refreshAdapter() {
        if (this.carouselPagerAdapter != null) {
            this.carouselPagerAdapter.notifyDataSetChanged();
        }
    }

    public void setLightModeON(boolean z) {
        this.isLightModeON = z;
    }

    public void getPopulatedView(ArrayList<Item> arrayList) {
        this.carouselPagerAdapter.setCarouselData(arrayList, arrayList.size(), this.isLightModeON);
        this.carouselData = arrayList;
        startAutoScroll();
    }

    private void notifyAdClick(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
            }
        }, uRLManager);
    }

    public void onClickPerformed(View view, BusinessObject businessObject) {
        final BusinessObject businessObject2 = businessObject;
        super.onClick(view);
        boolean z = view.getId() == R.id.play_icon;
        if (businessObject2 instanceof Item) {
            Item item = (Item) businessObject2;
            String entityType = item.getEntityType();
            an.a().a("click", "en", getmUniqueId(), an.a().a(an.a().a), businessObject.getBusinessObjId(), entityType, String.valueOf(this.mItemClickedPosition), "");
            StringBuilder stringBuilder;
            String stringBuilder2;
            StringBuilder stringBuilder3;
            u a;
            String str;
            String a2;
            StringBuilder stringBuilder4;
            if (entityType.equals(c.a)) {
                businessObject2 = (Playlist) populatePlaylistClicked(item);
                if (z) {
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.a);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        u.a().a("Browse", stringBuilder2, stringBuilder3.toString());
                    } else {
                        a = u.a();
                        str = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.a);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        a.a(str, stringBuilder2, stringBuilder3.toString());
                    }
                    af.a(this.mContext, ((GaanaActivity) this.mContext).getCurrentFragment()).a((int) R.id.playMenu, businessObject2);
                    return;
                }
                if (TextUtils.isEmpty(businessObject2.getChannelPageAdCode())) {
                    Constants.i = false;
                    Constants.j = "";
                } else {
                    Constants.i = true;
                    Constants.j = businessObject2.getChannelPageAdCode();
                }
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mHeader);
                    stringBuilder.append(" click ");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Position ");
                    stringBuilder3.append(this.mItemClickedPosition);
                    stringBuilder3.append(" - Playlist - ");
                    stringBuilder3.append(businessObject2.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                }
                Constants.e().a(businessObject2);
                if (businessObject2.isGaanaSpecial()) {
                    populateSpecialGaanaListing(businessObject2);
                } else {
                    populatePlaylistListing(businessObject2);
                }
            } else if (entityType.equals(c.h)) {
                str = "";
                String str2 = "";
                CharSequence charSequence = "";
                for (EntityInfo entityInfo : item.getEntityInfo()) {
                    if (entityInfo.getKey().equalsIgnoreCase("ad_url")) {
                        str2 = entityInfo.getValue().toString();
                    } else if (entityInfo.getKey().equalsIgnoreCase("click_track")) {
                        str = entityInfo.getValue().toString();
                    } else if (entityInfo.getKey().equalsIgnoreCase("dl_url")) {
                        charSequence = entityInfo.getValue().toString();
                    }
                }
                notifyAdClick(str);
                a2 = Util.a(item.getEntityInfo());
                if (!TextUtils.isEmpty(a2)) {
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("Position (");
                    stringBuilder5.append(this.mItemClickedPosition);
                    stringBuilder5.append(") -");
                    stringBuilder5.append(entityType);
                    stringBuilder5.append("- (");
                    stringBuilder5.append(businessObject.getName());
                    stringBuilder5.append(")");
                    u.a().a(a2, "Click", stringBuilder5.toString());
                }
                if (TextUtils.isEmpty(charSequence) || charSequence.contains(".html")) {
                    Intent intent = new Intent(this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", str2);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", businessObject.getName());
                    this.mContext.startActivity(intent);
                } else {
                    com.services.c.a(this.mContext).a(this.mContext, (String) charSequence, this.mAppState);
                }
            } else if (entityType.equals(c.b)) {
                businessObject2 = (Album) populateAlbumClicked(item);
                if (z) {
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.b);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        u.a().a("Browse", stringBuilder2, stringBuilder3.toString());
                    } else {
                        a = u.a();
                        str = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.b);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        a.a(str, stringBuilder2, stringBuilder3.toString());
                    }
                    af.a(this.mContext, ((GaanaActivity) this.mContext).getCurrentFragment()).a((int) R.id.playMenu, businessObject2);
                    return;
                }
                if (!businessObject2.isLocalMedia()) {
                    if ("1".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                        return;
                    } else if ("0".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                        return;
                    } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject2).booleanValue()) {
                        return;
                    } else {
                        if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ap.a().f(this.mContext);
                            return;
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject2, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                            return;
                        }
                    }
                }
                if (TextUtils.isEmpty(businessObject2.getChannelPageAdCode())) {
                    Constants.i = false;
                    Constants.j = "";
                } else {
                    Constants.i = true;
                    Constants.j = businessObject2.getChannelPageAdCode();
                }
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mHeader);
                    stringBuilder.append(" click ");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Position ");
                    stringBuilder3.append(this.mItemClickedPosition);
                    stringBuilder3.append(" - Album - ");
                    stringBuilder3.append(businessObject2.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                }
                populateAlbumListing(businessObject2);
            } else if (entityType.equals(d.d) || entityType.equals(d.c)) {
                if (Constants.cY) {
                    final View view2 = view;
                    JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                        public void onNegativeButtonClick() {
                        }

                        public void onPositiveButtonClick() {
                            JukeSessionManager.getInstance().stopJukeSession(new s() {
                                public void onErrorResponse(BusinessObject businessObject) {
                                }

                                public void onRetreivalComplete(BusinessObject businessObject) {
                                    if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                        CarouselItemView.this.onClickPerformed(view2, businessObject2);
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
                businessObject2 = (Radio) populateRadioClicked(item);
                if (this.mAppState.isAppInOfflineMode()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_radio));
                } else if (Util.j(this.mContext)) {
                    if (businessObject2.getType().equals(d.c)) {
                        if (this.mFragment instanceof DynamicHomeFragment) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(this.mHeader);
                            stringBuilder.append(" click ");
                            stringBuilder2 = stringBuilder.toString();
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Position ");
                            stringBuilder3.append(this.mItemClickedPosition);
                            stringBuilder3.append(" - RadioMirchi - ");
                            stringBuilder3.append(businessObject2.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                        } else {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder3.append(" - RadioMirchi - ");
                            stringBuilder3.append(businessObject2.getName());
                            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder3.toString());
                        }
                        ad.a(this.mContext).a(businessObject2);
                    } else {
                        if (this.mFragment instanceof DynamicHomeFragment) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(this.mHeader);
                            stringBuilder.append(" click ");
                            stringBuilder2 = stringBuilder.toString();
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Position");
                            stringBuilder3.append(this.mItemClickedPosition);
                            stringBuilder3.append(" - GaanaRadio - ");
                            stringBuilder3.append(businessObject2.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                        } else {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder3.append(" - GaanaRadio - ");
                            stringBuilder3.append(businessObject2.getName());
                            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder3.toString());
                        }
                        ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject2.getBusinessObjId()).replace("<radio_type>", businessObject2.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject2);
                    }
                    this.mListingComponents = Constants.a((Radio) businessObject2);
                    this.mListingComponents.a(businessObject2);
                    populateRadioListing(businessObject2);
                } else {
                    ap.a().f(this.mContext);
                }
            } else if (entityType.equals(c.c)) {
                businessObject2 = (Track) populateTrackClicked(item);
                if ("1".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                } else if ("0".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject2.getBusinessObjId())).booleanValue()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_song));
                } else if (Util.j(this.mContext) || DownloadManager.c().l(Integer.parseInt(businessObject2.getBusinessObjId())).booleanValue()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(businessObject2);
                    PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList), com.logging.d.a().a(this.mFragment, businessObject2));
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append(" click ");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Position ");
                        stringBuilder3.append(this.mItemClickedPosition);
                        stringBuilder3.append(" - Track - ");
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                    } else {
                        BaseActivity baseActivity = (BaseActivity) this.mContext;
                        str = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Track Detail  : ");
                        stringBuilder.append(businessObject2.getName());
                        baseActivity.sendGAEvent(str, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
                    }
                    PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                    ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                } else {
                    if (this.isPlayerQueue) {
                        aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.error_msg_no_connection));
                    } else {
                        ap.a().f(this.mContext);
                    }
                }
            } else if (entityType.equals(c.d)) {
                businessObject2 = (Artist) populateArtistClicked(item);
                if (z) {
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.d);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        u.a().a("Browse", stringBuilder2, stringBuilder3.toString());
                    } else {
                        a = u.a();
                        str = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(c.d);
                        stringBuilder3.append(businessObject2.getBusinessObjId());
                        a.a(str, stringBuilder2, stringBuilder3.toString());
                    }
                    af.a(this.mContext, ((GaanaActivity) this.mContext).getCurrentFragment()).a((int) R.id.playMenu, businessObject2);
                    return;
                }
                this.mListingComponents = Constants.a("", businessObject2.isLocalMedia());
                this.mAppState.setListingComponents(this.mListingComponents);
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mHeader);
                    stringBuilder.append(" click ");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Position ");
                    stringBuilder3.append(this.mItemClickedPosition);
                    stringBuilder3.append(" - Artist - ");
                    stringBuilder3.append(businessObject2.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                } else {
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
                }
                populateArtistListing(businessObject2);
            } else if (entityType.equals(c.k)) {
                CharSequence charSequence2 = "";
                List entityInfo2 = item.getEntityInfo();
                if (entityInfo2 != null) {
                    for (int i = 0; i < entityInfo2.size(); i++) {
                        if (((EntityInfo) entityInfo2.get(i)).getKey().equals("url")) {
                            charSequence2 = (String) ((EntityInfo) entityInfo2.get(i)).getValue();
                            break;
                        }
                    }
                }
                if (!TextUtils.isEmpty(charSequence2)) {
                    ((GaanaActivity) this.mContext).displayFragment(JukeSeeAllFragment.newInstance(charSequence2, item.getName(), BusinessObjectType.GenericItems.name(), false));
                }
            } else if (entityType.equals(c.e)) {
                a2 = Util.a(this.mContext, (ArrayList) item.getEntityInfo());
                str = Util.a(item.getEntityInfo());
                if (!TextUtils.isEmpty(str)) {
                    StringBuilder stringBuilder6 = new StringBuilder();
                    stringBuilder6.append("Position (");
                    stringBuilder6.append(this.mItemClickedPosition);
                    stringBuilder6.append(") -");
                    stringBuilder6.append(entityType);
                    stringBuilder6.append("- (");
                    stringBuilder6.append(businessObject.getName());
                    stringBuilder6.append(")");
                    u.a().a(str, "Click", stringBuilder6.toString());
                }
                Util.a(a2, getMandatoryLogin((ArrayList) item.getEntityInfo()), getInAppWeb((ArrayList) item.getEntityInfo()), this.mContext);
                if (a2 != null) {
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(this.mHeader);
                        stringBuilder4.append(" click ");
                        str = stringBuilder4.toString();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Position ");
                        stringBuilder.append(this.mItemClickedPosition);
                        stringBuilder.append(" - DeepLink - ");
                        stringBuilder.append(item.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", str, stringBuilder.toString());
                    } else {
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "DeepLink Detail", ((BaseActivity) this.mContext).currentScreen);
                    }
                }
            } else if (entityType.equals(c.f)) {
                YouTubeVideo youTubeVideo = (YouTubeVideo) populateVideoClicked(item);
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.mHeader);
                stringBuilder.append(" click ");
                stringBuilder2 = stringBuilder.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Position ");
                stringBuilder3.append(this.mItemClickedPosition);
                stringBuilder3.append(" - Video - ");
                stringBuilder3.append(youTubeVideo.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), youTubeVideo, youTubeVideo.e());
            } else if (entityType.equals(c.i)) {
                handleOccasionEntity(item);
            } else if (entityType.equalsIgnoreCase(c.j)) {
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(this.mHeader);
                    stringBuilder4.append(" click ");
                    str = stringBuilder4.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Position ");
                    stringBuilder.append(this.mItemClickedPosition);
                    stringBuilder.append(" - VPL - ");
                    stringBuilder.append(item.getName());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", str, stringBuilder.toString());
                } else {
                    BaseActivity baseActivity2 = (BaseActivity) this.mContext;
                    a2 = ((BaseActivity) this.mContext).currentScreen;
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("VPL Detail  : ");
                    stringBuilder4.append(item.getName());
                    baseActivity2.sendGAEvent(a2, stringBuilder4.toString(), ((BaseActivity) this.mContext).currentScreen);
                }
                Util.a(item, this.mContext, PLAYOUT_SECTION_TYPE.HOME_CAROUSEL_VIEW.name());
            }
        }
    }

    private String getMandatoryLogin(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("login_flag")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
    }

    private String getInAppWeb(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("app_url_view")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
    }

    private void handleOccasionEntity(Item item) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
        } else if (Util.j(this.mContext)) {
            ArrayList arrayList = (ArrayList) item.getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("url")) {
                        final String str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                        if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode() && !TextUtils.isEmpty(str) && str.contains("occasion")) {
                            String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
                            u a = u.a();
                            String str2 = ((BaseActivity) this.mContext).currentScreen;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(substring);
                            stringBuilder.append("_Click");
                            a.b(str2, stringBuilder.toString());
                            com.dynamicview.c.a().a(new ag() {
                                public void onOccasionResponse() {
                                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("OCCASION_URL", str);
                                    bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                                    dynamicOccasionFragment.setArguments(bundle);
                                    ((GaanaActivity) CarouselItemView.this.mContext).displayFragment(dynamicOccasionFragment);
                                }

                                public void onOccasionError() {
                                    aj.a().a(CarouselItemView.this.mContext, CarouselItemView.this.getResources().getString(R.string.error_download_no_internet));
                                }
                            }, str, null, false);
                        }
                    } else {
                        i++;
                    }
                }
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void impressionHandler(Item item, int i) {
        if ((!(this.mFragment instanceof DynamicHomeFragment) || ((DynamicHomeFragment) this.mFragment).b()) && (!(this.mFragment instanceof DynamicOccasionFragment) || ((DynamicOccasionFragment) this.mFragment).a())) {
            ArrayList arrayList = (ArrayList) ((Item) this.carouselData.get(i)).getEntityInfo();
            if (arrayList.size() > 0) {
                String str = "";
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    EntityInfo entityInfo = (EntityInfo) it.next();
                    if (entityInfo.getKey().equalsIgnoreCase("impression_tracker")) {
                        str = entityInfo.getValue().toString();
                    }
                }
                String replace = str.replace("RANDOM", UUID.randomUUID().toString());
                if (!TextUtils.isEmpty(replace)) {
                    updateImpression(replace);
                }
            }
        }
    }

    public String getSectionName() {
        return getSourceType();
    }

    public void updateImpression(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
            }
        }, uRLManager);
    }

    private int dipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    private int getScreenWidthinDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }

    public String getmUniqueId() {
        return this.mUniqueId;
    }
}

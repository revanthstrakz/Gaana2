package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.DownloadDetailsActionbar;
import com.actionbar.GenericActionBar;
import com.actionbar.GenericBackActionBar;
import com.actionbar.GenericSearchActionBar;
import com.actionbar.MyMusicSearchResultActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.collapsible_header.ListingFragmentMaterial;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.google.android.gms.common.api.GoogleApiClient;
import com.i.j;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.services.c;
import com.services.d;
import com.services.l.f;
import com.services.l.p;
import com.utilities.Util;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseGaanaFragment extends Fragment implements com.android.volley.i.a, b<Object>, p {
    public static DisplayMetrics metrics;
    protected final String SAVED_STATE = "bgf_saved_state";
    public String TITLE = "";
    private ConcurrentHashMap<String, a> _fragmentListener = null;
    protected View containerView;
    public String currentUJPage = "";
    a fragmentListener;
    protected boolean hideBottomBar = false;
    protected boolean isAdShown = false;
    protected boolean isChildFragment = false;
    protected LayoutInflater layoutInflater;
    protected boolean loginStatus = false;
    protected GaanaApplication mAppState;
    public GoogleApiClient mClient;
    protected Context mContext = null;
    protected d mDeviceResManager;
    private boolean mIsDownLoadFragment;
    private boolean mIsToAnimateFragmentElements;
    protected Toolbar mToolbar;
    boolean openFromGoogleSearch = false;
    private String pageNameforReturn = null;
    protected String removeAdDeeplink = null;
    private View rootView = null;
    private String sectionNameforReturn = null;
    private boolean shouldShowKeyboard;
    private String sourceNameForVPL = null;

    public interface a {
        void onDestroy();
    }

    public void handleErrorResponse(BusinessObject businessObject) {
    }

    public void notifyItemChanged(int i) {
    }

    public void notifyItemRemoved(int i) {
    }

    public void onAdConfigLoaded() {
    }

    public void onAdRefresh() {
    }

    public void onErrorResponse(VolleyError volleyError) {
    }

    public void onResponse(Object obj) {
    }

    public void refreshFavoriteCount(boolean z) {
    }

    public void refreshListView() {
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
    }

    public abstract void setGAScreenName(String str, String str2);

    public boolean shouldHandleLoginChange() {
        return true;
    }

    public boolean isChildFragment() {
        return this.isChildFragment;
    }

    public void setChildFragment(boolean z) {
        this.isChildFragment = z;
    }

    public void setSourceNameForVPL(String str) {
        this.sourceNameForVPL = str;
    }

    public String getSourceNameForVPL() {
        return this.sourceNameForVPL;
    }

    public String getSectionNameForReturn() {
        return this.sectionNameforReturn;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContext = getActivity();
        this.mDeviceResManager = d.a();
        this.mAppState = GaanaApplication.getInstance();
        this.layoutInflater = LayoutInflater.from(getActivity());
        metrics = new DisplayMetrics();
        if (getArguments() != null) {
            this.isChildFragment = getArguments().getBoolean("is_child_fragment", false);
            this.hideBottomBar = getArguments().getBoolean("should_hide_bottom_bar", false);
        }
        this.mClient = ((BaseActivity) this.mContext).getGoogleApiClient();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.mAppState.setNetworkExtrasBundle();
        if (bundle == null) {
            if (!TextUtils.isEmpty(this.mAppState.getPlayoutSectionName())) {
                this.sectionNameforReturn = this.mAppState.getPlayoutSectionName();
            }
            if (!(TextUtils.isEmpty(this.mAppState.getCurrentPageName()) || this.mAppState.getCurrentPageName().equals(PAGE_SORCE_NAME.OTHER.name()))) {
                this.pageNameforReturn = this.mAppState.getCurrentPageName();
            }
        } else {
            this.pageNameforReturn = bundle.getString("page_name");
            this.sectionNameforReturn = bundle.getString("page_section");
        }
        this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        if (!isDraggablePanelMaximized()) {
            hideHomeActionBar();
        }
        this.removeAdDeeplink = ColombiaAdViewManager.a().b();
        return null;
    }

    private boolean isDraggablePanelMaximized() {
        return (this.mContext instanceof GaanaActivity) && ((GaanaActivity) this.mContext).getDraggablePanel() != null && ((GaanaActivity) this.mContext).getDraggablePanel().getParent() != null && ((GaanaActivity) this.mContext).getDraggablePanel().f();
    }

    public void onStart() {
        super.onStart();
        ((BaseActivity) this.mContext).onFragmentStart(getClass().getSimpleName());
        if (c.b) {
            if (c.c) {
                if (getFragmentManager().getBackStackEntryCount() == 1) {
                    this.openFromGoogleSearch = true;
                } else if (getFragmentManager().getBackStackEntryCount() > 1) {
                    c.c = false;
                }
            }
            if (this.openFromGoogleSearch) {
                c.c = true;
            }
        }
        removeCustomAudienceParameter();
    }

    private void removeCustomAudienceParameter() {
        if ((this instanceof DynamicHomeFragment) || (this instanceof RadioActivityFragment) || (this instanceof MyMusicFragment) || (this instanceof SearchEnchancedFragment)) {
            GaanaApplication.getInstance().setCurrentSponsoredOccassion(null);
            GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
        }
    }

    public void loadTopBannerAds() {
        if (!ap.a().b(this.mContext)) {
        }
    }

    public void onStop() {
        super.onStop();
        ((BaseActivity) this.mContext).onFragmentStop(getClass().getSimpleName());
    }

    public void onResume() {
        super.onResume();
        this.rootView = ((Activity) this.mContext).findViewById(16908290);
        if (!(this.shouldShowKeyboard || this.rootView == null || this.rootView.getWindowToken() == null)) {
            Util.a(this.mContext, this.rootView);
        }
        if (((GaanaActivity) this.mContext).isSmartDownloadNotificationPending()) {
            ((GaanaActivity) this.mContext).showSmartDownloadNotification();
        }
        if (!(TextUtils.isEmpty(this.sectionNameforReturn) || TextUtils.isEmpty(this.mAppState.getPlayoutSectionName()))) {
            this.mAppState.setPlayoutSectionName(this.sectionNameforReturn);
        }
        if (!(TextUtils.isEmpty(this.pageNameforReturn) || TextUtils.isEmpty(this.mAppState.getCurrentPageName()))) {
            this.mAppState.setCurrentPageName(this.pageNameforReturn);
        }
        if (!(!getUserVisibleHint() || (this instanceof ListingFragment) || (this instanceof ListingFragmentMaterial) || (this instanceof SearchTabFragment) || (this instanceof SongsSelectionSearchFragment) || (this instanceof GridRecommendationFragment))) {
            ((GaanaActivity) this.mContext).updateSidebarActiveButton(this);
        }
        if (shouldHandleLoginChange() && ((GaanaActivity) this.mContext).hasLoginChanged().booleanValue()) {
            ((GaanaActivity) this.mContext).updateSidebarUserDetails();
        }
        if (!this.isChildFragment) {
            if ((this instanceof a) || this.hideBottomBar) {
                if (((GaanaActivity) this.mContext).getSlidingPanelLayout().a() != 2) {
                    ((GaanaActivity) this.mContext).getSlidingPanelLayout().a(2);
                    ((GaanaActivity) this.mContext).mPlayerStateChanged = true;
                    ((GaanaActivity) this.mContext).setDrawerLockMode(1);
                    ((GaanaActivity) this.mContext).hideMiniPlayerForPlayerFreeFragment();
                }
                ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
            } else if (((GaanaActivity) this.mContext).mPlayerStateChanged && !(this instanceof f)) {
                ((GaanaActivity) this.mContext).getSlidingPanelLayout().a(0);
                ((GaanaActivity) this.mContext).setDrawerLockMode(0);
                ((GaanaActivity) this.mContext).mPlayerStateChanged = false;
                ((GaanaActivity) this.mContext).showMiniPlayerForPlayerFreeFragment();
            }
        }
        boolean z = this instanceof DynamicHomeFragment;
        if (z || (this instanceof RadioActivityFragment) || (this instanceof MyMusicFragment) || (this instanceof LyricsBannerFragment) || (this instanceof GaanaVideoPlayerFragment)) {
            ((GaanaActivity) this.mContext).findViewById(R.id.dummy_status_bar).setVisibility(8);
            if (z) {
                ((GaanaActivity) this.mContext).drawerModeUnLocked();
            } else {
                ((GaanaActivity) this.mContext).setDrawerLockMode(1);
            }
        } else {
            ((GaanaActivity) this.mContext).setDrawerLockMode(1);
            ((GaanaActivity) this.mContext).findViewById(R.id.dummy_status_bar).setVisibility(0);
        }
        if ((this instanceof a) || this.hideBottomBar) {
            ((GaanaActivity) this.mContext).setCoachmarkViewHidden(true);
            ((GaanaActivity) this.mContext).hideAnimationToMyMusic();
            ((GaanaActivity) this.mContext).hideNewDownloadedSongCount();
        } else {
            ((GaanaActivity) this.mContext).setCoachmarkViewHidden(false);
            ((GaanaActivity) this.mContext).showHideNewDownloadedSongCount();
        }
        this.removeAdDeeplink = ColombiaAdViewManager.a().b();
    }

    /* Access modifiers changed, original: protected */
    public void setPlayerFreeFragment() {
        if (((GaanaActivity) this.mContext).getSlidingPanelLayout().a() != 2) {
            ((GaanaActivity) this.mContext).getSlidingPanelLayout().a(2);
            ((GaanaActivity) this.mContext).mPlayerStateChanged = true;
            ((GaanaActivity) this.mContext).setDrawerLockMode(1);
        }
    }

    public void onPause() {
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public View setContentView(int i, View view) {
        this.layoutInflater = LayoutInflater.from(getActivity());
        return this.layoutInflater.inflate(i, (ViewGroup) view, false);
    }

    /* Access modifiers changed, original: protected */
    public void updateView() {
        if (getUserVisibleHint() && !(this instanceof ListingFragment) && !(this instanceof ListingFragmentMaterial)) {
            ((GaanaActivity) this.mContext).setFragment(this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setCurrentFragment() {
        if (this.mContext instanceof GaanaActivity) {
            ((GaanaActivity) this.mContext).setFragment(this);
        }
    }

    public void setAdShown(boolean z) {
        this.isAdShown = z;
    }

    public void sendGAScreenName(String str, String str2) {
        ((BaseActivity) this.mContext).currentScreen = str;
        ((BaseActivity) this.mContext).setGoogleAnalyticsScreenName(str2);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this._fragmentListener != null) {
            for (a onDestroy : this._fragmentListener.values()) {
                onDestroy.onDestroy();
            }
            this._fragmentListener.clear();
            this._fragmentListener = null;
        }
    }

    public String getTitle() {
        return ((GaanaActivity) this.mContext).title;
    }

    /* Access modifiers changed, original: protected */
    public void showNetworkErrorView(View view) {
        if (view != null) {
            view.setVisibility(0);
        }
        aj.a().a(this.mContext, this.mContext.getString(R.string.some_error_occured));
    }

    public Toolbar getmToolbar() {
        return this.mToolbar;
    }

    public void setmToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }

    public void setActionBar(View view, View view2) {
        setActionBar(view, view2, true);
    }

    public void hideHomeActionBar() {
        LinearLayout linearLayout = (LinearLayout) ((BaseActivity) this.mContext).findViewById(R.id.home_toolbar);
        linearLayout.removeAllViews();
        linearLayout.setVisibility(8);
    }

    public void setActionBar(View view, View view2, boolean z) {
        this.mToolbar = (Toolbar) view.findViewById(R.id.main_toolbar);
        this.mToolbar.setContentInsetsAbsolute(0, 0);
        this.mToolbar.removeAllViews();
        if (view2.getParent() != null) {
            ((ViewGroup) view2.getParent()).removeAllViews();
        }
        this.mToolbar.addView(view2);
        this.mToolbar.setVisibility(0);
        this.mToolbar.getMenu().clear();
        hideHomeActionBar();
        if (z) {
            if (view2 instanceof GenericActionBar) {
                this.mToolbar.inflateMenu(R.menu.cast_menu_home);
                ((GenericActionBar) view2).setToolbar(this.mToolbar);
            } else if (view2 instanceof GenericBackActionBar) {
                this.mToolbar.inflateMenu(R.menu.cast_menu_generic_back);
                if (Constants.l) {
                    this.mToolbar.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_foreground_light));
                } else {
                    this.mToolbar.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_background_dark));
                }
                ((GenericBackActionBar) view2).setToolbar(this.mToolbar);
            } else {
                boolean z2 = view2 instanceof DownloadDetailsActionbar;
                if (z2 || (view2 instanceof GenericSearchActionBar) || (view2 instanceof MyMusicSearchResultActionBar)) {
                    if (z2) {
                        ((DownloadDetailsActionbar) view2).setToolBar(this.mToolbar);
                    }
                    this.mToolbar.inflateMenu(R.menu.cast_menu_search);
                } else {
                    this.mToolbar.inflateMenu(R.menu.cast_menu_generic_back);
                }
            }
            Menu menu = this.mToolbar.getMenu();
            if (!(menu == null || menu.findItem(R.id.media_route_menu_item) == null)) {
                ((BaseActivity) this.mContext).initializeMediaRouterButton(menu, R.id.media_route_menu_item);
            }
        }
        setmToolbar(this.mToolbar);
    }

    public void onDestroyView() {
        super.onDestroyView();
        j.a().a(toString());
    }

    public String getSectionName() {
        String playoutSectionName = GaanaApplication.getInstance().getPlayoutSectionName();
        if (TextUtils.isEmpty(playoutSectionName) || playoutSectionName.equals(PLAYOUT_SECTION_TYPE.OTHERS.name())) {
            return PLAYOUT_SECTION_TYPE.OTHERS.name();
        }
        return playoutSectionName;
    }

    public String getPageName() {
        String currentPageName = GaanaApplication.getInstance().getCurrentPageName();
        if (TextUtils.isEmpty(currentPageName) || currentPageName.equals(PAGE_SORCE_NAME.OTHER.name())) {
            return PAGE_SORCE_NAME.OTHER.name();
        }
        return currentPageName;
    }

    public void refreshDataandAds() {
        refreshListView();
    }

    public void showSnackbartoOpenMyMusic() {
        aj.a().a(this.mContext, GaanaApplication.getContext().getResources().getString(R.string.action_view), GaanaApplication.getContext().getResources().getString(R.string.msg_view_downloads_in_mymusic), new aj.b() {
            public void undoSnackBar() {
                ((GaanaActivity) BaseGaanaFragment.this.mContext).displayDownload(-1, null, null);
            }
        });
    }

    public void setIsDownloadFragment(boolean z) {
        this.mIsDownLoadFragment = z;
    }

    public boolean isDownLoadFragment() {
        return this.mIsDownLoadFragment;
    }

    public void setShouldShowKeyboard(boolean z) {
        this.shouldShowKeyboard = z;
    }

    public boolean isToAnimateElements() {
        return !Constants.cN && this.mIsToAnimateFragmentElements;
    }

    public void setAnimateFragmentElements(boolean z) {
        this.mIsToAnimateFragmentElements = z;
    }

    public void removeFragmentListener() {
        this.fragmentListener = null;
    }

    public void setFragmentListener(a aVar) {
        this.fragmentListener = aVar;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("page_name", this.pageNameforReturn);
        bundle.putString("page_section", this.sectionNameforReturn);
    }

    public void addFragmentListener(String str, a aVar) {
        if (this._fragmentListener == null) {
            this._fragmentListener = new ConcurrentHashMap();
        }
        if (this._fragmentListener.containsKey(str)) {
            this._fragmentListener.remove(str);
        }
        this._fragmentListener.put(str, aVar);
    }

    public void removeFragmentListener(String str) {
        this._fragmentListener.remove(str);
    }

    public boolean isBottomBarHidden() {
        return this.hideBottomBar;
    }
}

package com.services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.VolleyError;
import com.gaana.login.UserSubscriptionData;
import com.gaana.models.BusinessObject;
import com.gaana.models.LinkDeviceResponse;
import com.gaana.models.LiveCricketData.Data;
import com.gaana.models.PayUHash.SiEnabledBankName;
import com.gaana.models.Tracks.Track;
import com.logging.GaanaLogger.PLAYOUT_SOURCE;
import com.managers.PlayerManager.PlayerType;
import java.util.ArrayList;

public class l {

    public interface p {
        void onAdRefresh();
    }

    public interface ak {
        void a(Boolean bool);
    }

    public interface aa {
        void onError(String str);

        void onFontRetrieved(Typeface typeface);
    }

    public interface b {
        void onAdConfigFailed();

        void onAdConfigLoaded();
    }

    public interface af {
        void onErrorResponse(BusinessObject businessObject);

        void onRetreivalComplete(Object obj);
    }

    public interface au {
        void onUserStatusUpdated();
    }

    public interface ad {
        void onLoginSuccess();
    }

    public interface s {
        void onErrorResponse(BusinessObject businessObject);

        void onRetreivalComplete(BusinessObject businessObject);
    }

    public interface r {
        void onErrorResponse(VolleyError volleyError);

        void onSuccessfulResponse(Bitmap bitmap);
    }

    public interface as {
        void onTrialSuccess();
    }

    public interface y {
        void OnDynamicViewDataFetched();
    }

    public interface g {
        void favouriteSyncCompleted();
    }

    public interface bc {
        void a(PLAYOUT_SOURCE playout_source, boolean z);
    }

    public interface ac {
        void a(String str);
    }

    public interface ae {
        void OnNetworkChangeListener(boolean z);
    }

    public interface ag {
        void onOccasionError();

        void onOccasionResponse();
    }

    public interface ba {
        void onPlaySong(View view, Track track);
    }

    public interface a {
        void onAdBottomBannerGone();

        void onAdBottomBannerLoaded();
    }

    public interface ab {
        void a(String str);
    }

    public interface ah {
        void onOfflineModeValidated(boolean z);
    }

    public interface ai {
        void a(Track track, int i);
    }

    public interface aj {
        void a(PlayerType playerType);
    }

    public interface al {
        void a(ViewHolder viewHolder, int i);

        void a(boolean z);
    }

    public interface am {
        void onSelectAllStausChanged(boolean z);
    }

    public interface an {
        void onSimplPaymentUpdate();

        void onSimplPaymentUpdate(UserSubscriptionData userSubscriptionData);
    }

    public interface ao {
        void onStartDrag(ViewHolder viewHolder);
    }

    public interface ap {
        void onSwipeRefresh();
    }

    public interface aq {
        void a();
    }

    public interface ar {
        void onTrackSelectionChanged(int i);
    }

    public interface at {
        void onUserRefreshed();
    }

    public interface av {
        void onLiveRadioUpdate();

        void onPlayerStateChanged();

        void onRadioTracksFetched(boolean z);

        void on_deque();

        void on_enque();

        void refreshForFavorite();

        void refreshList();

        void refreshPlayerStatus();

        void updateCardAdapter(boolean z);
    }

    public interface aw {
        void onPlayerQueueSavingCompleted();
    }

    public interface ax {
    }

    public interface ay {
        void videoStateChanged(int i);
    }

    public interface az {
        void onItemClear(int i);

        void onItemSelected();
    }

    public interface bb {
    }

    public interface bd {
        void showAnimationToMyMusic();
    }

    public interface be {
        void showCricketCarouselScore(ViewGroup viewGroup, Data data);
    }

    public interface c {
        void onAdLoadedatPosition(boolean z, int i);
    }

    public interface d {
        void onConsentProvided(boolean z);
    }

    public interface e {
        void onDataRetrieved(int i);
    }

    public interface f {
    }

    public interface h {
        void a(String str, ArrayList<SiEnabledBankName> arrayList);
    }

    public interface i {
    }

    public interface j {
        void onComplete(int i);

        void onItemDelete(int i, int i2);

        boolean onItemMove(int i, int i2);
    }

    public interface k extends j {
        float getSwipeThreshold(ViewHolder viewHolder);

        float getSwipeVelocityThreshold(float f);

        void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z);
    }

    public interface l {
        void loadMoreData(int i);

        void loadMoreData(int i, Object obj);
    }

    public interface m {
        void onResponse(boolean z);
    }

    public interface n {
        void onChangeThemeOnly();

        void onEnableAutoNow();
    }

    public interface o {
        void notifyItemChanged(int i);
    }

    public interface q {
        void b();
    }

    public interface t {
        void a(int i, int i2);
    }

    public interface u {
        void onCheckedStateSelected(int i);

        void onCheckedStateUnSelected(int i);
    }

    public interface v {
        void a(BusinessObject businessObject, boolean z);
    }

    public interface w {
        void a(BusinessObject businessObject, com.dynamicview.f.a aVar, int i);

        void b(BusinessObject businessObject, com.dynamicview.f.a aVar, int i);
    }

    public interface x {
        void onDeviceLinkingFailed(boolean z);

        void onDeviceLinkingSuccessful(LinkDeviceResponse linkDeviceResponse);
    }

    public interface z {
        void a(int i);

        void a(BusinessObject businessObject, int i);

        void a(BusinessObject businessObject, int i, int i2);
    }
}

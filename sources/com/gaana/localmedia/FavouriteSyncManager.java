package com.gaana.localmedia;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.e.a.c;
import com.gaana.application.GaanaApplication;
import com.managers.URLManager.BusinessObjectType;
import com.services.FavoriteResultReceiver;
import com.services.FavoriteSyncService;
import com.services.l.g;
import com.utilities.Util;
import com.utilities.d;

public class FavouriteSyncManager {
    public static final long SECONDS_IN_24_HRS = 86400;
    private static final String TAG = "FavouriteSyncManager";
    private static FavouriteSyncManager mFavouriteSyncManager;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private FavouriteSyncManager() {
    }

    public static FavouriteSyncManager getInstance() {
        if (mFavouriteSyncManager == null) {
            mFavouriteSyncManager = new FavouriteSyncManager();
        }
        return mFavouriteSyncManager;
    }

    public void performSync(g gVar) {
        if (Util.j(GaanaApplication.getContext()) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            FavoriteResultReceiver favoriteResultReceiver = new FavoriteResultReceiver(new Handler());
            favoriteResultReceiver.a(gVar);
            Intent intent = new Intent(GaanaApplication.getContext(), FavoriteSyncService.class);
            intent.putExtra("extra_result_receiver", favoriteResultReceiver);
            intent.setAction("extra_sync_local");
            startFavoriteService(intent);
        } else if (gVar != null) {
            gVar.favouriteSyncCompleted();
        }
    }

    public void onPullToRefresh(BusinessObjectType businessObjectType, g gVar) {
        FavoriteResultReceiver favoriteResultReceiver = new FavoriteResultReceiver(new Handler());
        favoriteResultReceiver.a(gVar);
        Intent intent = new Intent(GaanaApplication.getContext(), FavoriteSyncService.class);
        intent.putExtra("extra_result_receiver", favoriteResultReceiver);
        intent.setAction(getFavoriteAction(businessObjectType));
        intent.putExtra("extra_refresh", true);
        startFavoriteService(intent);
    }

    public void performSyncOnLogin() {
        if (GaanaApplication.getInstance().isAppInForeground()) {
            Intent intent = new Intent(GaanaApplication.getContext(), FavoriteSyncService.class);
            intent.setAction("sync_login");
            startFavoriteService(intent);
        }
    }

    public void performSync() {
        Intent intent = new Intent(GaanaApplication.getContext(), FavoriteSyncService.class);
        intent.setAction("sync_favorite_delta");
        startFavoriteService(intent);
    }

    private void startFavoriteService(final Intent intent) {
        if (!GaanaApplication.getInstance().isAppInForeground()) {
            return;
        }
        if (d.e()) {
            GaanaApplication.getContext().startService(intent);
        } else {
            this.mMainHandler.postDelayed(new Runnable() {
                public void run() {
                    if (GaanaApplication.getInstance().isAppInForeground()) {
                        GaanaApplication.getContext().startService(intent);
                    }
                }
            }, 400);
        }
    }

    private String getFavoriteAction(BusinessObjectType businessObjectType) {
        String str = "sync_track";
        switch (businessObjectType) {
            case Albums:
                return "sync_album";
            case Playlists:
                return "sync_playlist";
            case Radios:
                return "sync_radio";
            case Artists:
                return "sync_artist";
            case FavoriteOccasions:
                return "sync_occasion";
            default:
                return str;
        }
    }

    public void clear() {
        c.a().b();
        com.services.d.a().b("favorite_sync_login", false);
        com.services.d.a().b("favorite_sync_flag", false);
        com.services.d.a().b("favorite_sync_tracks", false);
        com.services.d.a().b("favorite_sync_playlist", false);
        com.services.d.a().b("favorite_sync_album", false);
        com.services.d.a().b("favorite_sync_radios", false);
        com.services.d.a().b("favorite_sync_occasions", false);
        com.services.d.a().b("favorite_sync_artist", false);
    }
}

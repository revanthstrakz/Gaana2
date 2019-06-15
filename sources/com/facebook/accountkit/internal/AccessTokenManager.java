package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccessTokenTracker;

final class AccessTokenManager {
    static final String SHARED_PREFERENCES_NAME = "com.facebook.accountkit.AccessTokenManager.SharedPreferences";
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private final LocalBroadcastManager localBroadcastManager;

    AccessTokenManager(Context context, LocalBroadcastManager localBroadcastManager) {
        this(new AccessTokenCache(context), localBroadcastManager);
    }

    AccessTokenManager(@NonNull AccessTokenCache accessTokenCache, @NonNull LocalBroadcastManager localBroadcastManager) {
        this.accessTokenCache = accessTokenCache;
        this.localBroadcastManager = localBroadcastManager;
    }

    /* Access modifiers changed, original: 0000 */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void refreshCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(new AccessToken(accessToken.getToken(), accessToken.getAccountId(), accessToken.getApplicationId(), accessToken.getTokenRefreshIntervalSeconds(), null));
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcast(accessToken2, accessToken);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(AccessTokenTracker.ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(AccessTokenTracker.EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(AccessTokenTracker.EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
}

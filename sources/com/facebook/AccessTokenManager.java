package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.comscore.measurement.MeasurementDispatcher;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.GraphRequest.Callback;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

final class AccessTokenManager {
    static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    private AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    private static class RefreshResult {
        public String accessToken;
        public int expiresAt;

        private RefreshResult() {
        }

        /* synthetic */ RefreshResult(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager, AccessTokenCache accessTokenCache) {
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(accessTokenCache, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager;
        this.accessTokenCache = accessTokenCache;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
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

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcast(accessToken2, accessToken);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    /* Access modifiers changed, original: 0000 */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    private boolean shouldExtendAccessToken() {
        boolean z = false;
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (this.currentAccessToken.getSource().canExtendToken() && valueOf.longValue() - this.lastAttemptedTokenExtendDate.getTime() > 3600000 && valueOf.longValue() - this.currentAccessToken.getLastRefresh().getTime() > MeasurementDispatcher.MILLIS_PER_DAY) {
            z = true;
        }
        return z;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, Callback callback) {
        return new GraphRequest(accessToken, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, bundle, HttpMethod.GET, callback);
    }

    /* Access modifiers changed, original: 0000 */
    public void refreshCurrentAccessToken(final AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    private void refreshCurrentAccessTokenImpl(AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!(Utility.isNullOrEmpty(optString) || Utility.isNullOrEmpty(optString2))) {
                                        optString2 = optString2.toLowerCase(Locale.US);
                                        if (optString2.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (optString2.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            optString = AccessTokenManager.TAG;
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("Unexpected status: ");
                                            stringBuilder.append(optString2);
                                            Log.w(optString, stringBuilder.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), createExtendAccessTokenRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt(AccountKitGraphConstants.SEAMLESS_LOGIN_EXPIRES_AT_KEY);
                    }
                }
            }));
            final AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
            graphRequestBatch.addCallback(new GraphRequestBatch.Callback() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0052=Splitter:B:18:0x0052, B:47:0x00e3=Splitter:B:47:0x00e3} */
                public void onBatchCompleted(com.facebook.GraphRequestBatch r15) {
                    /*
                    r14 = this;
                    r15 = 0;
                    r0 = 0;
                    r1 = com.facebook.AccessTokenManager.getInstance();	 Catch:{ all -> 0x00ff }
                    r1 = r1.getCurrentAccessToken();	 Catch:{ all -> 0x00ff }
                    if (r1 == 0) goto L_0x00e3;
                L_0x000c:
                    r1 = com.facebook.AccessTokenManager.getInstance();	 Catch:{ all -> 0x00ff }
                    r1 = r1.getCurrentAccessToken();	 Catch:{ all -> 0x00ff }
                    r1 = r1.getUserId();	 Catch:{ all -> 0x00ff }
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r2 = r2.getUserId();	 Catch:{ all -> 0x00ff }
                    if (r1 == r2) goto L_0x0022;
                L_0x0020:
                    goto L_0x00e3;
                L_0x0022:
                    r1 = r4;	 Catch:{ all -> 0x00ff }
                    r1 = r1.get();	 Catch:{ all -> 0x00ff }
                    if (r1 != 0) goto L_0x0052;
                L_0x002a:
                    r1 = r5;	 Catch:{ all -> 0x00ff }
                    r1 = r1.accessToken;	 Catch:{ all -> 0x00ff }
                    if (r1 != 0) goto L_0x0052;
                L_0x0030:
                    r1 = r5;	 Catch:{ all -> 0x00ff }
                    r1 = r1.expiresAt;	 Catch:{ all -> 0x00ff }
                    if (r1 != 0) goto L_0x0052;
                L_0x0036:
                    r1 = r3;	 Catch:{ all -> 0x00ff }
                    if (r1 == 0) goto L_0x0046;
                L_0x003a:
                    r1 = r3;	 Catch:{ all -> 0x00ff }
                    r2 = new com.facebook.FacebookException;	 Catch:{ all -> 0x00ff }
                    r3 = "Failed to refresh access token";
                    r2.<init>(r3);	 Catch:{ all -> 0x00ff }
                    r1.OnTokenRefreshFailed(r2);	 Catch:{ all -> 0x00ff }
                L_0x0046:
                    r0 = com.facebook.AccessTokenManager.this;
                    r0 = r0.tokenRefreshInProgress;
                    r0.set(r15);
                    r15 = r3;
                    return;
                L_0x0052:
                    r1 = new com.facebook.AccessToken;	 Catch:{ all -> 0x00ff }
                    r2 = r5;	 Catch:{ all -> 0x00ff }
                    r2 = r2.accessToken;	 Catch:{ all -> 0x00ff }
                    if (r2 == 0) goto L_0x005f;
                L_0x005a:
                    r2 = r5;	 Catch:{ all -> 0x00ff }
                    r2 = r2.accessToken;	 Catch:{ all -> 0x00ff }
                    goto L_0x0065;
                L_0x005f:
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r2 = r2.getToken();	 Catch:{ all -> 0x00ff }
                L_0x0065:
                    r3 = r2;
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r4 = r2.getApplicationId();	 Catch:{ all -> 0x00ff }
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r5 = r2.getUserId();	 Catch:{ all -> 0x00ff }
                    r2 = r4;	 Catch:{ all -> 0x00ff }
                    r2 = r2.get();	 Catch:{ all -> 0x00ff }
                    if (r2 == 0) goto L_0x007e;
                L_0x007a:
                    r2 = r6;	 Catch:{ all -> 0x00ff }
                L_0x007c:
                    r6 = r2;
                    goto L_0x0085;
                L_0x007e:
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r2 = r2.getPermissions();	 Catch:{ all -> 0x00ff }
                    goto L_0x007c;
                L_0x0085:
                    r2 = r4;	 Catch:{ all -> 0x00ff }
                    r2 = r2.get();	 Catch:{ all -> 0x00ff }
                    if (r2 == 0) goto L_0x0091;
                L_0x008d:
                    r2 = r7;	 Catch:{ all -> 0x00ff }
                L_0x008f:
                    r7 = r2;
                    goto L_0x0098;
                L_0x0091:
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r2 = r2.getDeclinedPermissions();	 Catch:{ all -> 0x00ff }
                    goto L_0x008f;
                L_0x0098:
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r8 = r2.getSource();	 Catch:{ all -> 0x00ff }
                    r2 = r5;	 Catch:{ all -> 0x00ff }
                    r2 = r2.expiresAt;	 Catch:{ all -> 0x00ff }
                    if (r2 == 0) goto L_0x00b2;
                L_0x00a4:
                    r2 = new java.util.Date;	 Catch:{ all -> 0x00ff }
                    r9 = r5;	 Catch:{ all -> 0x00ff }
                    r9 = r9.expiresAt;	 Catch:{ all -> 0x00ff }
                    r9 = (long) r9;	 Catch:{ all -> 0x00ff }
                    r11 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                    r9 = r9 * r11;
                    r2.<init>(r9);	 Catch:{ all -> 0x00ff }
                    goto L_0x00b8;
                L_0x00b2:
                    r2 = r2;	 Catch:{ all -> 0x00ff }
                    r2 = r2.getExpires();	 Catch:{ all -> 0x00ff }
                L_0x00b8:
                    r9 = r2;
                    r10 = new java.util.Date;	 Catch:{ all -> 0x00ff }
                    r10.<init>();	 Catch:{ all -> 0x00ff }
                    r2 = r1;
                    r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x00ff }
                    r0 = com.facebook.AccessTokenManager.getInstance();	 Catch:{ all -> 0x00de }
                    r0.setCurrentAccessToken(r1);	 Catch:{ all -> 0x00de }
                    r0 = com.facebook.AccessTokenManager.this;
                    r0 = r0.tokenRefreshInProgress;
                    r0.set(r15);
                    r15 = r3;
                    if (r15 == 0) goto L_0x00dd;
                L_0x00d6:
                    if (r1 == 0) goto L_0x00dd;
                L_0x00d8:
                    r15 = r3;
                    r15.OnTokenRefreshed(r1);
                L_0x00dd:
                    return;
                L_0x00de:
                    r0 = move-exception;
                    r13 = r1;
                    r1 = r0;
                    r0 = r13;
                    goto L_0x0100;
                L_0x00e3:
                    r1 = r3;	 Catch:{ all -> 0x00ff }
                    if (r1 == 0) goto L_0x00f3;
                L_0x00e7:
                    r1 = r3;	 Catch:{ all -> 0x00ff }
                    r2 = new com.facebook.FacebookException;	 Catch:{ all -> 0x00ff }
                    r3 = "No current access token to refresh";
                    r2.<init>(r3);	 Catch:{ all -> 0x00ff }
                    r1.OnTokenRefreshFailed(r2);	 Catch:{ all -> 0x00ff }
                L_0x00f3:
                    r0 = com.facebook.AccessTokenManager.this;
                    r0 = r0.tokenRefreshInProgress;
                    r0.set(r15);
                    r15 = r3;
                    return;
                L_0x00ff:
                    r1 = move-exception;
                L_0x0100:
                    r2 = com.facebook.AccessTokenManager.this;
                    r2 = r2.tokenRefreshInProgress;
                    r2.set(r15);
                    r15 = r3;
                    if (r15 == 0) goto L_0x0114;
                L_0x010d:
                    if (r0 == 0) goto L_0x0114;
                L_0x010f:
                    r15 = r3;
                    r15.OnTokenRefreshed(r0);
                L_0x0114:
                    throw r1;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessTokenManager$AnonymousClass4.onBatchCompleted(com.facebook.GraphRequestBatch):void");
                }
            });
            graphRequestBatch.executeAsync();
        } else {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
            }
        }
    }
}

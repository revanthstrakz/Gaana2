package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.accountkit.AccountKit.InitializeCallback;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import java.util.ArrayList;
import java.util.Iterator;

public final class Initializer {
    private final ArrayList<InitializeCallback> callbacks = new ArrayList();
    private volatile Data data = null;
    private volatile State state = State.UNINITIALIZED;

    private static final class Data {
        final AccessTokenManager accessTokenManager;
        final Context applicationContext;
        final String applicationId;
        final String applicationName;
        final String clientToken;
        final LocalBroadcastManager localBroadcastManager;
        final LoginManager loginManager;

        Data(Context context, String str, String str2, String str3, AccessTokenManager accessTokenManager, LocalBroadcastManager localBroadcastManager, LoginManager loginManager) {
            this.applicationContext = context;
            this.applicationId = str;
            this.applicationName = str2;
            this.clientToken = str3;
            this.accessTokenManager = accessTokenManager;
            this.localBroadcastManager = localBroadcastManager;
            this.loginManager = loginManager;
        }
    }

    private enum State {
        UNINITIALIZED,
        INITIALIZED,
        FAILED
    }

    /* JADX WARNING: Missing block: B:7:0x000d, code skipped:
            return;
     */
    public synchronized void initialize(@android.support.annotation.NonNull android.content.Context r11, com.facebook.accountkit.AccountKit.InitializeCallback r12) throws com.facebook.accountkit.AccountKitException {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = r10.isInitialized();	 Catch:{ all -> 0x00a4 }
        if (r0 == 0) goto L_0x000e;
    L_0x0007:
        if (r12 == 0) goto L_0x000c;
    L_0x0009:
        r12.onInitialized();	 Catch:{ all -> 0x00a4 }
    L_0x000c:
        monitor-exit(r10);
        return;
    L_0x000e:
        if (r12 == 0) goto L_0x0015;
    L_0x0010:
        r0 = r10.callbacks;	 Catch:{ all -> 0x00a4 }
        r0.add(r12);	 Catch:{ all -> 0x00a4 }
    L_0x0015:
        com.facebook.accountkit.internal.Validate.checkInternetPermissionAndThrow(r11);	 Catch:{ all -> 0x00a4 }
        r1 = r11.getApplicationContext();	 Catch:{ all -> 0x00a4 }
        r12 = 0;
        r0 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x002c }
        r2 = r1.getPackageName();	 Catch:{ NameNotFoundException -> 0x002c }
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = r0.getApplicationInfo(r2, r3);	 Catch:{ NameNotFoundException -> 0x002c }
        goto L_0x002d;
    L_0x002c:
        r0 = r12;
    L_0x002d:
        if (r0 == 0) goto L_0x009e;
    L_0x002f:
        r2 = r0.metaData;	 Catch:{ all -> 0x00a4 }
        if (r2 != 0) goto L_0x0034;
    L_0x0033:
        goto L_0x009e;
    L_0x0034:
        r0 = r0.metaData;	 Catch:{ all -> 0x00a4 }
        r2 = "com.facebook.sdk.ApplicationId";
        r3 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_APP_ID;	 Catch:{ all -> 0x00a4 }
        r2 = getRequiredString(r0, r2, r3);	 Catch:{ all -> 0x00a4 }
        r3 = "com.facebook.accountkit.ClientToken";
        r4 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_CLIENT_TOKEN;	 Catch:{ all -> 0x00a4 }
        r4 = getRequiredString(r0, r3, r4);	 Catch:{ all -> 0x00a4 }
        r3 = "com.facebook.accountkit.ApplicationName";
        r5 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_APP_NAME;	 Catch:{ all -> 0x00a4 }
        r3 = getRequiredString(r0, r3, r5);	 Catch:{ all -> 0x00a4 }
        r5 = "com.facebook.accountkit.AccountKitFacebookAppEventsEnabled";
        r6 = 1;
        r0 = r0.getBoolean(r5, r6);	 Catch:{ all -> 0x00a4 }
        r6 = android.support.v4.content.LocalBroadcastManager.getInstance(r1);	 Catch:{ all -> 0x00a4 }
        r5 = new com.facebook.accountkit.internal.InternalLogger;	 Catch:{ all -> 0x00a4 }
        r7 = r11.getApplicationContext();	 Catch:{ all -> 0x00a4 }
        r5.<init>(r7, r2, r0);	 Catch:{ all -> 0x00a4 }
        r7 = new com.facebook.accountkit.internal.AccessTokenManager;	 Catch:{ all -> 0x00a4 }
        r7.<init>(r1, r6);	 Catch:{ all -> 0x00a4 }
        r8 = new com.facebook.accountkit.internal.LoginManager;	 Catch:{ all -> 0x00a4 }
        r8.<init>(r5, r7, r6);	 Catch:{ all -> 0x00a4 }
        r9 = new com.facebook.accountkit.internal.Initializer$Data;	 Catch:{ all -> 0x00a4 }
        r0 = r9;
        r5 = r7;
        r7 = r8;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x00a4 }
        r10.data = r9;	 Catch:{ all -> 0x00a4 }
        r0 = java.net.CookieManager.getDefault();	 Catch:{ all -> 0x00a4 }
        if (r0 != 0) goto L_0x0089;
    L_0x007c:
        r0 = new java.net.CookieManager;	 Catch:{ all -> 0x00a4 }
        r1 = new com.facebook.accountkit.internal.AccountKitCookieStore;	 Catch:{ all -> 0x00a4 }
        r1.<init>(r11);	 Catch:{ all -> 0x00a4 }
        r0.<init>(r1, r12);	 Catch:{ all -> 0x00a4 }
        java.net.CookieManager.setDefault(r0);	 Catch:{ all -> 0x00a4 }
    L_0x0089:
        r10.loadAccessToken();	 Catch:{ all -> 0x00a4 }
        r11 = com.facebook.accountkit.internal.Initializer.State.INITIALIZED;	 Catch:{ all -> 0x00a4 }
        r10.state = r11;	 Catch:{ all -> 0x00a4 }
        r11 = r8.getLogger();	 Catch:{ all -> 0x00a4 }
        r12 = "ak_sdk_init";
        r11.logEvent(r12);	 Catch:{ all -> 0x00a4 }
        com.facebook.accountkit.internal.NativeProtocol.updateAllAvailableProtocolVersionsAsync();	 Catch:{ all -> 0x00a4 }
        monitor-exit(r10);
        return;
    L_0x009e:
        r11 = com.facebook.accountkit.internal.Initializer.State.FAILED;	 Catch:{ all -> 0x00a4 }
        r10.state = r11;	 Catch:{ all -> 0x00a4 }
        monitor-exit(r10);
        return;
    L_0x00a4:
        r11 = move-exception;
        monitor-exit(r10);
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.Initializer.initialize(android.content.Context, com.facebook.accountkit.AccountKit$InitializeCallback):void");
    }

    /* Access modifiers changed, original: 0000 */
    public AccessTokenManager getAccessTokenManager() {
        Validate.sdkInitialized();
        return this.data.accessTokenManager;
    }

    public Context getApplicationContext() {
        Validate.sdkInitialized();
        return this.data.applicationContext;
    }

    public String getApplicationId() {
        Validate.sdkInitialized();
        return this.data.applicationId;
    }

    /* Access modifiers changed, original: 0000 */
    public String getApplicationName() {
        Validate.sdkInitialized();
        return this.data.applicationName;
    }

    /* Access modifiers changed, original: 0000 */
    public String getClientToken() {
        Validate.sdkInitialized();
        return this.data.clientToken;
    }

    public InternalLogger getLogger() {
        Validate.sdkInitialized();
        return this.data.loginManager.getLogger();
    }

    /* Access modifiers changed, original: 0000 */
    public LoginManager getLoginManager() {
        Validate.sdkInitialized();
        return this.data.loginManager;
    }

    public boolean isInitialized() {
        return this.state == State.INITIALIZED;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean getAccountKitFacebookAppEventsEnabled() {
        return getLogger().getFacebookAppEventsEnabled();
    }

    private synchronized void loadAccessToken() {
        if (!isInitialized()) {
            this.data.accessTokenManager.loadCurrentAccessToken();
            Iterator it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((InitializeCallback) it.next()).onInitialized();
            }
            this.callbacks.clear();
        }
    }

    private static String getRequiredString(Bundle bundle, String str, InternalAccountKitError internalAccountKitError) throws AccountKitException {
        String string = bundle.getString(str);
        if (string != null) {
            return string;
        }
        throw new AccountKitException(Type.INITIALIZATION_ERROR, internalAccountKitError);
    }
}

package com.facebook.accountkit.internal;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.internal.ServerProtocol;

final class EmailLoginController extends LoginController<EmailLoginModelImpl> {
    private static final String PARAMETER_EMAIL = "email";
    private static final int SECONDS_TO_MILLIS = 1000;
    private static final String TAG = "com.facebook.accountkit.internal.EmailLoginController";

    private class LoginModelCodeCallback implements Callback {
        final EmailLoginModelImpl loginModel;

        LoginModelCodeCallback(EmailLoginModelImpl emailLoginModelImpl) {
            this.loginModel = emailLoginModelImpl;
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x01b2 */
        /* JADX WARNING: Can't wrap try/catch for region: R(2:58|59) */
        /* JADX WARNING: Missing block: B:59:?, code skipped:
            r9.this$0.onError(com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED, com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
     */
        public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r10) {
            /*
            r9 = this;
            com.facebook.accountkit.internal.Utility.assertUIThread();
            r0 = com.facebook.accountkit.internal.EmailLoginController.this;
            r0 = r0.getLoginManager();
            if (r0 != 0) goto L_0x000c;
        L_0x000b:
            return;
        L_0x000c:
            r1 = r0.isActivityAvailable();
            if (r1 == 0) goto L_0x0204;
        L_0x0012:
            r1 = r0.isLoginInProgress();
            if (r1 != 0) goto L_0x001a;
        L_0x0018:
            goto L_0x0204;
        L_0x001a:
            r1 = r10.getError();	 Catch:{ all -> 0x01df }
            if (r1 == 0) goto L_0x0055;
        L_0x0020:
            r10 = r10.getError();	 Catch:{ all -> 0x01df }
            r10 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r10);	 Catch:{ all -> 0x01df }
            r1 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x01df }
            r10 = r10.first;	 Catch:{ all -> 0x01df }
            r10 = (com.facebook.accountkit.AccountKitError) r10;	 Catch:{ all -> 0x01df }
            r1.onError(r10);	 Catch:{ all -> 0x01df }
            r10 = r9.loginModel;
            if (r10 == 0) goto L_0x0054;
        L_0x0035:
            r10 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r1 = r9.loginModel;
            r1 = r1.getStatus();
            r1 = r1.ordinal();
            r10 = r10[r1];
            switch(r10) {
                case 1: goto L_0x0047;
                case 2: goto L_0x0047;
                default: goto L_0x0046;
            };
        L_0x0046:
            goto L_0x0054;
        L_0x0047:
            r10 = r9.loginModel;
            r0.onLoginComplete(r10);
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;
            r10.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x0054:
            return;
        L_0x0055:
            r10 = r10.getResponseObject();	 Catch:{ all -> 0x01df }
            if (r10 != 0) goto L_0x0088;
        L_0x005b:
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x01df }
            r1 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x01df }
            r2 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND;	 Catch:{ all -> 0x01df }
            r10.onError(r1, r2);	 Catch:{ all -> 0x01df }
            r10 = r9.loginModel;
            if (r10 == 0) goto L_0x0087;
        L_0x0068:
            r10 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r1 = r9.loginModel;
            r1 = r1.getStatus();
            r1 = r1.ordinal();
            r10 = r10[r1];
            switch(r10) {
                case 1: goto L_0x007a;
                case 2: goto L_0x007a;
                default: goto L_0x0079;
            };
        L_0x0079:
            goto L_0x0087;
        L_0x007a:
            r10 = r9.loginModel;
            r0.onLoginComplete(r10);
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;
            r10.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x0087:
            return;
        L_0x0088:
            r1 = "status";
            r1 = r10.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = "pending";
            r1 = r1.equals(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            if (r1 == 0) goto L_0x0142;
        L_0x0096:
            r1 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r3 = new com.facebook.accountkit.internal.EmailLoginController$LoginModelCodeCallback;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r4 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r5 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r3.<init>(r5);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = r1.createPolling(r2, r3);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            if (r1 != 0) goto L_0x00cd;
        L_0x00a9:
            r10 = r9.loginModel;
            if (r10 == 0) goto L_0x00cc;
        L_0x00ad:
            r10 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r1 = r9.loginModel;
            r1 = r1.getStatus();
            r1 = r1.ordinal();
            r10 = r10[r1];
            switch(r10) {
                case 1: goto L_0x00bf;
                case 2: goto L_0x00bf;
                default: goto L_0x00be;
            };
        L_0x00be:
            goto L_0x00cc;
        L_0x00bf:
            r10 = r9.loginModel;
            r0.onLoginComplete(r10);
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;
            r10.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x00cc:
            return;
        L_0x00cd:
            r2 = "interval_sec";
            r2 = r10.getString(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r3 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r3.setInterval(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = "expires_in_sec";
            r10 = r10.getString(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = java.lang.Long.parseLong(r10);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.setExpiresInSeconds(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r10.getInterval();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r4 = (long) r10;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r10 >= 0) goto L_0x0123;
        L_0x00f6:
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = com.facebook.accountkit.internal.InternalAccountKitError.EXPIRED_EMAIL_REQUEST;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.onError(r1, r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;
            if (r10 == 0) goto L_0x0122;
        L_0x0103:
            r10 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r1 = r9.loginModel;
            r1 = r1.getStatus();
            r1 = r1.ordinal();
            r10 = r10[r1];
            switch(r10) {
                case 1: goto L_0x0115;
                case 2: goto L_0x0115;
                default: goto L_0x0114;
            };
        L_0x0114:
            goto L_0x0122;
        L_0x0115:
            r10 = r9.loginModel;
            r0.onLoginComplete(r10);
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;
            r10.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x0122:
            return;
        L_0x0123:
            r10 = r0.isActivityAvailable();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            if (r10 != 0) goto L_0x012f;
        L_0x0129:
            r10 = r0.isLoginInProgress();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            if (r10 == 0) goto L_0x01bb;
        L_0x012f:
            r10 = new android.os.Handler;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.<init>();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r2.getInterval();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r2 * 1000;
            r2 = (long) r2;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.postDelayed(r1, r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            goto L_0x01bb;
        L_0x0142:
            r1 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = r1.getResponseType();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = "token";
            r1 = com.facebook.accountkit.internal.Utility.areObjectsEqual(r1, r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            if (r1 == 0) goto L_0x0194;
        L_0x0150:
            r1 = "access_token";
            r3 = r10.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = "id";
            r4 = r10.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = "token_refresh_interval_sec";
            r1 = r10.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r6 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = new com.facebook.accountkit.AccessToken;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r5 = com.facebook.accountkit.AccountKit.getApplicationId();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r8 = new java.util.Date;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r8.<init>();	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r1;
            r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r2.accessTokenManager;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2.setCurrentAccessToken(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = "state";
            r10 = r10.optString(r2);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2.setFinalAuthState(r10);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.setAccessToken(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.setStatus(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            goto L_0x01bb;
        L_0x0194:
            r1 = "code";
            r1 = r10.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r2.setCode(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = "state";
            r10 = r10.optString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1.setFinalAuthState(r10);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10 = r9.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r1 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            r10.setStatus(r1);	 Catch:{ NumberFormatException | JSONException -> 0x01b2, NumberFormatException | JSONException -> 0x01b2 }
            goto L_0x01bb;
        L_0x01b2:
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x01df }
            r1 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x01df }
            r2 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT;	 Catch:{ all -> 0x01df }
            r10.onError(r1, r2);	 Catch:{ all -> 0x01df }
        L_0x01bb:
            r10 = r9.loginModel;
            if (r10 == 0) goto L_0x01de;
        L_0x01bf:
            r10 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r1 = r9.loginModel;
            r1 = r1.getStatus();
            r1 = r1.ordinal();
            r10 = r10[r1];
            switch(r10) {
                case 1: goto L_0x01d1;
                case 2: goto L_0x01d1;
                default: goto L_0x01d0;
            };
        L_0x01d0:
            goto L_0x01de;
        L_0x01d1:
            r10 = r9.loginModel;
            r0.onLoginComplete(r10);
            r10 = com.facebook.accountkit.internal.EmailLoginController.this;
            r10.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x01de:
            return;
        L_0x01df:
            r10 = move-exception;
            r1 = r9.loginModel;
            if (r1 == 0) goto L_0x0203;
        L_0x01e4:
            r1 = com.facebook.accountkit.internal.EmailLoginController.AnonymousClass3.$SwitchMap$com$facebook$accountkit$internal$LoginStatus;
            r2 = r9.loginModel;
            r2 = r2.getStatus();
            r2 = r2.ordinal();
            r1 = r1[r2];
            switch(r1) {
                case 1: goto L_0x01f6;
                case 2: goto L_0x01f6;
                default: goto L_0x01f5;
            };
        L_0x01f5:
            goto L_0x0203;
        L_0x01f6:
            r1 = r9.loginModel;
            r0.onLoginComplete(r1);
            r1 = com.facebook.accountkit.internal.EmailLoginController.this;
            r1.broadcastLoginStateChange();
            r0.clearLogIn();
        L_0x0203:
            throw r10;
        L_0x0204:
            r10 = com.facebook.accountkit.internal.EmailLoginController.TAG;
            r0 = "Warning: Callback issues while activity not available.";
            android.util.Log.w(r10, r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.EmailLoginController$LoginModelCodeCallback.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
        }
    }

    /* Access modifiers changed, original: protected */
    public String getCredentialsType() {
        return "email";
    }

    /* Access modifiers changed, original: protected */
    public String getLoginStateChangedIntentName() {
        return EmailLoginTracker.ACTION_EMAIL_LOGIN_STATE_CHANGED;
    }

    public void onAccountVerified() {
    }

    EmailLoginController(AccessTokenManager accessTokenManager, LoginManager loginManager, EmailLoginModelImpl emailLoginModelImpl) {
        super(accessTokenManager, loginManager, emailLoginModelImpl);
    }

    public void logIn(@Nullable String str) {
        AnonymousClass1 anonymousClass1 = new Callback() {
            /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0083 */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:14|15|16|17|18|19) */
            public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r5) {
                /*
                r4 = this;
                r0 = com.facebook.accountkit.internal.EmailLoginController.this;
                r0 = r0.getLoginManager();
                if (r0 != 0) goto L_0x0009;
            L_0x0008:
                return;
            L_0x0009:
                r1 = r5.getError();	 Catch:{ all -> 0x0092 }
                if (r1 == 0) goto L_0x0026;
            L_0x000f:
                r5 = r5.getError();	 Catch:{ all -> 0x0092 }
                r5 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r5);	 Catch:{ all -> 0x0092 }
                r0 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x0092 }
                r5 = r5.first;	 Catch:{ all -> 0x0092 }
                r5 = (com.facebook.accountkit.AccountKitError) r5;	 Catch:{ all -> 0x0092 }
                r0.onError(r5);	 Catch:{ all -> 0x0092 }
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;
                r5.broadcastLoginStateChange();
                return;
            L_0x0026:
                r5 = r5.getResponseObject();	 Catch:{ all -> 0x0092 }
                if (r5 != 0) goto L_0x003b;
            L_0x002c:
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x0092 }
                r0 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x0092 }
                r1 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND;	 Catch:{ all -> 0x0092 }
                r5.onError(r0, r1);	 Catch:{ all -> 0x0092 }
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;
                r5.broadcastLoginStateChange();
                return;
            L_0x003b:
                r1 = "login_request_code";
                r1 = r5.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r2 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r2 = r2.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r2 = (com.facebook.accountkit.internal.EmailLoginModelImpl) r2;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r2.setLoginCode(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = "expires_in_sec";
                r1 = r5.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r3 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r3 = r3.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r3 = (com.facebook.accountkit.internal.EmailLoginModelImpl) r3;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r3.setExpiresInSeconds(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = "interval_sec";
                r5 = r5.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = java.lang.Integer.parseInt(r5);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = r1.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = (com.facebook.accountkit.internal.EmailLoginModelImpl) r1;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1.setInterval(r5);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = r5.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = (com.facebook.accountkit.internal.EmailLoginModelImpl) r5;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r1 = com.facebook.accountkit.internal.LoginStatus.PENDING;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5.setStatus(r1);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r5 = r5.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                r0.handle(r5);	 Catch:{ NumberFormatException | JSONException -> 0x0083, NumberFormatException | JSONException -> 0x0083 }
                goto L_0x008c;
            L_0x0083:
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;	 Catch:{ all -> 0x0092 }
                r0 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x0092 }
                r1 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT;	 Catch:{ all -> 0x0092 }
                r5.onError(r0, r1);	 Catch:{ all -> 0x0092 }
            L_0x008c:
                r5 = com.facebook.accountkit.internal.EmailLoginController.this;
                r5.broadcastLoginStateChange();
                return;
            L_0x0092:
                r5 = move-exception;
                r0 = com.facebook.accountkit.internal.EmailLoginController.this;
                r0.broadcastLoginStateChange();
                throw r5;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.EmailLoginController$AnonymousClass1.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
            }
        };
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, "email", ((EmailLoginModelImpl) this.loginModel).getEmail());
        Utility.putNonNullString(bundle, ServerProtocol.DIALOG_PARAM_REDIRECT_URI, Utility.getRedirectURL());
        Utility.putNonNullString(bundle, "state", str);
        Utility.putNonNullString(bundle, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((EmailLoginModelImpl) this.loginModel).getResponseType());
        AccountKitGraphRequest buildGraphRequest = buildGraphRequest("start_login", bundle);
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(buildGraphRequest, anonymousClass1));
    }

    public void onCancel() {
        ((EmailLoginModelImpl) this.loginModel).setStatus(LoginStatus.CANCELLED);
        broadcastLoginStateChange();
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
    }

    public void onPending() {
        LoginManager loginManager = getLoginManager();
        if (loginManager != null && loginManager.isActivityAvailable()) {
            Runnable createPolling = createPolling((EmailLoginModelImpl) this.loginModel, new LoginModelCodeCallback((EmailLoginModelImpl) this.loginModel));
            if (createPolling != null) {
                new Handler().postDelayed(createPolling, (long) (((EmailLoginModelImpl) this.loginModel).getInterval() * 1000));
            }
        }
    }

    @Nullable
    private Runnable createPolling(final EmailLoginModelImpl emailLoginModelImpl, final Callback callback) {
        LoginManager loginManager = getLoginManager();
        if (loginManager == null) {
            return null;
        }
        final String requestInstanceToken = loginManager.getRequestInstanceToken();
        return new Runnable() {
            public void run() {
                Utility.assertUIThread();
                if (checkLoginManager()) {
                    Bundle bundle = new Bundle();
                    Utility.putNonNullString(bundle, "email", emailLoginModelImpl.getEmail());
                    AccountKitGraphRequest buildGraphRequest = EmailLoginController.this.buildGraphRequest("poll_login", bundle);
                    AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                    AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(buildGraphRequest, new Callback() {
                        public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                            callback.onCompleted(accountKitGraphResponse);
                        }
                    }));
                }
            }

            private boolean checkLoginManager() {
                LoginManager loginManager = EmailLoginController.this.getLoginManager();
                return loginManager != null && requestInstanceToken.equals(loginManager.getRequestInstanceToken()) && loginManager.isLoginInProgress();
            }
        };
    }
}

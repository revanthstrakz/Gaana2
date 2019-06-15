package com.facebook.accountkit.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.GraphRequest;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.internal.ServerProtocol;
import com.gaana.login.LoginManager;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

final class PhoneLoginController extends LoginController<PhoneLoginModelImpl> {
    private static final String PARAMETER_CONFIRMATION_CODE = "confirmation_code";
    private static final String PARAMETER_PHONE = "phone_number";
    private static final String PARAMETER_USER_TOKEN = "fb_user_token";
    private static final String TAG = "com.facebook.accountkit.internal.PhoneLoginController";

    /* Access modifiers changed, original: protected */
    public String getCredentialsType() {
        return PARAMETER_PHONE;
    }

    /* Access modifiers changed, original: protected */
    public String getLoginStateChangedIntentName() {
        return PhoneLoginTracker.ACTION_PHONE_LOGIN_STATE_CHANGED;
    }

    PhoneLoginController(AccessTokenManager accessTokenManager, LoginManager loginManager, PhoneLoginModelImpl phoneLoginModelImpl) {
        super(accessTokenManager, loginManager, phoneLoginModelImpl);
    }

    public void logIn(@Nullable String str) {
        AnonymousClass1 anonymousClass1 = new Callback() {
            /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00fb */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x00d9 A:{Catch:{ NumberFormatException | JSONException -> 0x00fb }} */
            /* JADX WARNING: Removed duplicated region for block: B:32:0x00bf A:{Catch:{ NumberFormatException | JSONException -> 0x00fb }} */
            /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x009a */
            /* JADX WARNING: Can't wrap try/catch for region: R(14:14|15|(1:17)|18|(1:20)|21|22|(3:26|27|28)|29|30|(1:32)(1:33)|34|37|38) */
            /* JADX WARNING: Can't wrap try/catch for region: R(2:35|36) */
            /* JADX WARNING: Missing block: B:36:?, code skipped:
            r7.this$0.onError(com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED, com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
     */
            public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r8) {
                /*
                r7 = this;
                r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r0 = r0.getLoginManager();
                if (r0 == 0) goto L_0x0111;
            L_0x0008:
                if (r8 != 0) goto L_0x000c;
            L_0x000a:
                goto L_0x0111;
            L_0x000c:
                r0 = r8.getError();	 Catch:{ all -> 0x010a }
                if (r0 == 0) goto L_0x0029;
            L_0x0012:
                r8 = r8.getError();	 Catch:{ all -> 0x010a }
                r8 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r8);	 Catch:{ all -> 0x010a }
                r0 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x010a }
                r8 = r8.first;	 Catch:{ all -> 0x010a }
                r8 = (com.facebook.accountkit.AccountKitError) r8;	 Catch:{ all -> 0x010a }
                r0.onError(r8);	 Catch:{ all -> 0x010a }
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r8.broadcastLoginStateChange();
                return;
            L_0x0029:
                r8 = r8.getResponseObject();	 Catch:{ all -> 0x010a }
                if (r8 != 0) goto L_0x003e;
            L_0x002f:
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x010a }
                r0 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x010a }
                r1 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND;	 Catch:{ all -> 0x010a }
                r8.onError(r0, r1);	 Catch:{ all -> 0x010a }
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r8.broadcastLoginStateChange();
                return;
            L_0x003e:
                r0 = "privacy_policy";
                r0 = r8.optString(r0);	 Catch:{ all -> 0x010a }
                r1 = com.facebook.accountkit.internal.Utility.isNullOrEmpty(r0);	 Catch:{ all -> 0x010a }
                if (r1 != 0) goto L_0x0055;
            L_0x004a:
                r1 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x010a }
                r1 = r1.loginModel;	 Catch:{ all -> 0x010a }
                r1 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r1;	 Catch:{ all -> 0x010a }
                r2 = "privacy_policy";
                r1.putField(r2, r0);	 Catch:{ all -> 0x010a }
            L_0x0055:
                r0 = "terms_of_service";
                r0 = r8.optString(r0);	 Catch:{ all -> 0x010a }
                r1 = com.facebook.accountkit.internal.Utility.isNullOrEmpty(r0);	 Catch:{ all -> 0x010a }
                if (r1 != 0) goto L_0x006c;
            L_0x0061:
                r1 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x010a }
                r1 = r1.loginModel;	 Catch:{ all -> 0x010a }
                r1 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r1;	 Catch:{ all -> 0x010a }
                r2 = "terms_of_service";
                r1.putField(r2, r0);	 Catch:{ all -> 0x010a }
            L_0x006c:
                r0 = "can_attempt_seamless_login";
                r0 = r8.getBoolean(r0);	 Catch:{ JSONException -> 0x009a }
                r1 = "expires_at";
                r1 = r8.getString(r1);	 Catch:{ JSONException -> 0x009a }
                r1 = java.lang.Long.parseLong(r1);	 Catch:{ JSONException -> 0x009a }
                r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                r1 = r1 * r3;
                if (r0 == 0) goto L_0x009a;
            L_0x0081:
                r3 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x009a }
                r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
                if (r0 <= 0) goto L_0x009a;
            L_0x0089:
                r0 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ JSONException -> 0x009a }
                r0 = r0.loginModel;	 Catch:{ JSONException -> 0x009a }
                r0 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r0;	 Catch:{ JSONException -> 0x009a }
                r1 = com.facebook.accountkit.internal.LoginStatus.ACCOUNT_VERIFIED;	 Catch:{ JSONException -> 0x009a }
                r0.setStatus(r1);	 Catch:{ JSONException -> 0x009a }
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r8.broadcastLoginStateChange();
                return;
            L_0x009a:
                r0 = "login_request_code";
                r0 = r8.getString(r0);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = "expires_in_sec";
                r1 = r8.getString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r3 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r3 = r3.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r3.setExpiresInSeconds(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = "min_resend_interval_sec";
                r8 = r8.optString(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = com.facebook.accountkit.internal.Utility.isNullOrEmpty(r8);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                if (r1 != 0) goto L_0x00d9;
            L_0x00bf:
                r1 = java.lang.Long.parseLong(r8);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = r8.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r8;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r3 = java.lang.System.currentTimeMillis();	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r5 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = r5.toMillis(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r5 = r3 + r1;
                r8.setResendTime(r5);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                goto L_0x00e6;
            L_0x00d9:
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = r8.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r8;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = java.lang.System.currentTimeMillis();	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8.setResendTime(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
            L_0x00e6:
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = r8.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r8;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r1 = com.facebook.accountkit.internal.LoginStatus.PENDING;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8.setStatus(r1);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = r8.loginModel;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r8;	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                r8.setLoginCode(r0);	 Catch:{ NumberFormatException | JSONException -> 0x00fb, NumberFormatException | JSONException -> 0x00fb }
                goto L_0x0104;
            L_0x00fb:
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x010a }
                r0 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x010a }
                r1 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT;	 Catch:{ all -> 0x010a }
                r8.onError(r0, r1);	 Catch:{ all -> 0x010a }
            L_0x0104:
                r8 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r8.broadcastLoginStateChange();
                return;
            L_0x010a:
                r8 = move-exception;
                r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                r0.broadcastLoginStateChange();
                throw r8;
            L_0x0111:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.PhoneLoginController$AnonymousClass1.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
            }
        };
        String phoneNumber = ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString();
        Bundle bundle = new Bundle();
        Utility.putNonNullString(bundle, PARAMETER_PHONE, phoneNumber);
        Utility.putNonNullString(bundle, "state", str);
        Utility.putNonNullString(bundle, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((PhoneLoginModelImpl) this.loginModel).getResponseType());
        Utility.putNonNullString(bundle, GraphRequest.FIELDS_PARAM, AccountKitGraphConstants.TERMS_OF_SERVICE_AND_PRIVACY_POLICY);
        switch (((PhoneLoginModelImpl) this.loginModel).getNotificationChannel()) {
            case FACEBOOK:
                Utility.putNonNullString(bundle, AccountKitGraphConstants.PARAMETER_NOTIFICATION_MEDIUM, LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
                break;
            case VOICE_CALLBACK:
                Utility.putNonNullString(bundle, AccountKitGraphConstants.PARAMETER_NOTIFICATION_MEDIUM, "voice");
                break;
        }
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            if (loginManager.isSeamlessLoginRunning()) {
                loginManager.getLogger().logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_NOT_COMPLETED);
            } else {
                Utility.putNonNullString(bundle, PARAMETER_USER_TOKEN, loginManager.getSeamlessLoginToken());
            }
        }
        ((PhoneLoginModelImpl) this.loginModel).setInitialAuthState(str);
        AccountKitGraphRequest buildGraphRequest = buildGraphRequest("start_login", bundle);
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(buildGraphRequest, anonymousClass1));
    }

    public void onCancel() {
        ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.CANCELLED);
        broadcastLoginStateChange();
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
    }

    public void onPending() {
        if (!Utility.isNullOrEmpty(((PhoneLoginModelImpl) this.loginModel).getConfirmationCode())) {
            Validate.loginModelInProgress(this.loginModel);
            final LoginManager loginManager = getLoginManager();
            if (loginManager != null) {
                loginManager.onLoginVerify(this.loginModel);
                AnonymousClass2 anonymousClass2 = new Callback() {
                    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00e9 */
                    /* JADX WARNING: Can't wrap try/catch for region: R(7:38|39|40|41|42|(1:46)|47) */
                    public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r5) {
                        /*
                        r4 = this;
                        r0 = r0;
                        r0 = r0.isActivityAvailable();
                        if (r0 != 0) goto L_0x0012;
                    L_0x0008:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.TAG;
                        r0 = "Warning: Callback issues while activity not available.";
                        android.util.Log.w(r5, r0);
                        return;
                    L_0x0012:
                        if (r5 != 0) goto L_0x0015;
                    L_0x0014:
                        return;
                    L_0x0015:
                        r0 = 0;
                        r1 = r5.getError();	 Catch:{ all -> 0x012e }
                        if (r1 == 0) goto L_0x0098;
                    L_0x001c:
                        r5 = r5.getError();	 Catch:{ all -> 0x012e }
                        r5 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r5);	 Catch:{ all -> 0x012e }
                        r1 = r5.second;	 Catch:{ all -> 0x0095 }
                        r1 = (com.facebook.accountkit.internal.InternalAccountKitError) r1;	 Catch:{ all -> 0x0095 }
                        r1 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r1);	 Catch:{ all -> 0x0095 }
                        if (r1 != 0) goto L_0x0037;
                    L_0x002e:
                        r1 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x0095 }
                        r2 = r5.first;	 Catch:{ all -> 0x0095 }
                        r2 = (com.facebook.accountkit.AccountKitError) r2;	 Catch:{ all -> 0x0095 }
                        r1.onError(r2);	 Catch:{ all -> 0x0095 }
                    L_0x0037:
                        r1 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r1 = r1.loginModel;
                        r1 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r1;
                        r1 = r1.getStatus();
                        r2 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r1 != r2) goto L_0x0065;
                    L_0x0045:
                        if (r5 == 0) goto L_0x0065;
                    L_0x0047:
                        r5 = r5.second;
                        r5 = (com.facebook.accountkit.internal.InternalAccountKitError) r5;
                        r5 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r5);
                        if (r5 == 0) goto L_0x0065;
                    L_0x0051:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r1 = com.facebook.accountkit.internal.LoginStatus.PENDING;
                        r5.setStatus(r1);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5.setError(r0);
                    L_0x0065:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5.broadcastLoginStateChange();
                        r5 = r0;
                        r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r0 = r0.loginModel;
                        r5.onLoginComplete(r0);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                        if (r5 == r0) goto L_0x008f;
                    L_0x0081:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r5 != r0) goto L_0x0094;
                    L_0x008f:
                        r5 = r0;
                        r5.clearLogIn();
                    L_0x0094:
                        return;
                    L_0x0095:
                        r1 = move-exception;
                        goto L_0x0130;
                    L_0x0098:
                        r5 = r5.getResponseObject();	 Catch:{ all -> 0x012e }
                        if (r5 != 0) goto L_0x00e3;
                    L_0x009e:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x012e }
                        r1 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x012e }
                        r2 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND;	 Catch:{ all -> 0x012e }
                        r5.onError(r1, r2);	 Catch:{ all -> 0x012e }
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5.broadcastLoginStateChange();
                        r5 = r0;
                        r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r0 = r0.loginModel;
                        r5.onLoginComplete(r0);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                        if (r5 == r0) goto L_0x00dd;
                    L_0x00cf:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r5 != r0) goto L_0x00e2;
                    L_0x00dd:
                        r5 = r0;
                        r5.clearLogIn();
                    L_0x00e2:
                        return;
                    L_0x00e3:
                        r1 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ NumberFormatException | JSONException -> 0x00e9, NumberFormatException | JSONException -> 0x00e9 }
                        r1.extractAccessTokenOrCodeIntoModel(r5);	 Catch:{ NumberFormatException | JSONException -> 0x00e9, NumberFormatException | JSONException -> 0x00e9 }
                        goto L_0x00f2;
                    L_0x00e9:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x012e }
                        r1 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x012e }
                        r2 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT;	 Catch:{ all -> 0x012e }
                        r5.onError(r1, r2);	 Catch:{ all -> 0x012e }
                    L_0x00f2:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5.broadcastLoginStateChange();
                        r5 = r0;
                        r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r0 = r0.loginModel;
                        r5.onLoginComplete(r0);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                        if (r5 == r0) goto L_0x0128;
                    L_0x011a:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r5 != r0) goto L_0x012d;
                    L_0x0128:
                        r5 = r0;
                        r5.clearLogIn();
                    L_0x012d:
                        return;
                    L_0x012e:
                        r1 = move-exception;
                        r5 = r0;
                    L_0x0130:
                        r2 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r2 = r2.loginModel;
                        r2 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r2;
                        r2 = r2.getStatus();
                        r3 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r2 != r3) goto L_0x015e;
                    L_0x013e:
                        if (r5 == 0) goto L_0x015e;
                    L_0x0140:
                        r5 = r5.second;
                        r5 = (com.facebook.accountkit.internal.InternalAccountKitError) r5;
                        r5 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r5);
                        if (r5 == 0) goto L_0x015e;
                    L_0x014a:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r2 = com.facebook.accountkit.internal.LoginStatus.PENDING;
                        r5.setStatus(r2);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5.setError(r0);
                    L_0x015e:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5.broadcastLoginStateChange();
                        r5 = r0;
                        r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r0 = r0.loginModel;
                        r5.onLoginComplete(r0);
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                        if (r5 == r0) goto L_0x0188;
                    L_0x017a:
                        r5 = com.facebook.accountkit.internal.PhoneLoginController.this;
                        r5 = r5.loginModel;
                        r5 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r5;
                        r5 = r5.getStatus();
                        r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                        if (r5 != r0) goto L_0x018d;
                    L_0x0188:
                        r5 = r0;
                        r5.clearLogIn();
                    L_0x018d:
                        throw r1;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.PhoneLoginController$AnonymousClass2.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
                    }
                };
                Bundle bundle = new Bundle();
                Utility.putNonNullString(bundle, "confirmation_code", ((PhoneLoginModelImpl) this.loginModel).getConfirmationCode());
                Utility.putNonNullString(bundle, PARAMETER_PHONE, ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString());
                AccountKitGraphRequest buildGraphRequest = buildGraphRequest("confirm_login", bundle);
                AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(buildGraphRequest, anonymousClass2));
            }
        }
    }

    private void extractAccessTokenOrCodeIntoModel(JSONObject jSONObject) throws JSONException {
        if (Utility.areObjectsEqual(((PhoneLoginModelImpl) this.loginModel).getResponseType(), AccountKitGraphConstants.TOKEN_RESPONSE_TYPE)) {
            AccessToken accessToken = new AccessToken(jSONObject.getString("access_token"), jSONObject.getString("id"), AccountKit.getApplicationId(), Long.parseLong(jSONObject.getString(AccountKitGraphConstants.TOKEN_REFRESH_INTERVAL_SEC)), new Date());
            this.accessTokenManager.setCurrentAccessToken(accessToken);
            ((PhoneLoginModelImpl) this.loginModel).setFinalAuthState(jSONObject.optString("state"));
            ((PhoneLoginModelImpl) this.loginModel).setAccessToken(accessToken);
            ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.SUCCESS);
            return;
        }
        ((PhoneLoginModelImpl) this.loginModel).setCode(jSONObject.getString("code"));
        ((PhoneLoginModelImpl) this.loginModel).setFinalAuthState(jSONObject.optString("state"));
        ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.SUCCESS);
    }

    public void onAccountVerified() {
        Validate.loginModelInProgress(this.loginModel);
        final LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.onSeamlessLoginPending(this.loginModel);
            AnonymousClass3 anonymousClass3 = new Callback() {
                /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0063 */
                /* JADX WARNING: Can't wrap try/catch for region: R(9:9|10|11|12|13|14|15|(1:19)|20) */
                public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r3) {
                    /*
                    r2 = this;
                    r0 = r0;
                    r0 = r0.isActivityAvailable();
                    if (r0 != 0) goto L_0x0012;
                L_0x0008:
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.TAG;
                    r0 = "Warning: Callback issues while activity not available.";
                    android.util.Log.w(r3, r0);
                    return;
                L_0x0012:
                    r0 = r3.getError();	 Catch:{ all -> 0x009c }
                    if (r0 == 0) goto L_0x0059;
                L_0x0018:
                    r3 = r3.getError();	 Catch:{ all -> 0x009c }
                    r3 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r3);	 Catch:{ all -> 0x009c }
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x009c }
                    r3 = r3.first;	 Catch:{ all -> 0x009c }
                    r3 = (com.facebook.accountkit.AccountKitError) r3;	 Catch:{ all -> 0x009c }
                    r0.onError(r3);	 Catch:{ all -> 0x009c }
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3.broadcastLoginStateChange();
                    r3 = r0;
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r0 = r0.loginModel;
                    r3.onLoginComplete(r0);
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3 = r3.loginModel;
                    r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3;
                    r3 = r3.getStatus();
                    r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                    if (r3 == r0) goto L_0x0053;
                L_0x0045:
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3 = r3.loginModel;
                    r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3;
                    r3 = r3.getStatus();
                    r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                    if (r3 != r0) goto L_0x0058;
                L_0x0053:
                    r3 = r0;
                    r3.clearLogIn();
                L_0x0058:
                    return;
                L_0x0059:
                    r3 = r3.getResponseObject();	 Catch:{ all -> 0x009c }
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ JSONException -> 0x0063 }
                    r0.extractAccessTokenOrCodeIntoModel(r3);	 Catch:{ JSONException -> 0x0063 }
                    goto L_0x006c;
                L_0x0063:
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;	 Catch:{ all -> 0x009c }
                    r0 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED;	 Catch:{ all -> 0x009c }
                    r1 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT;	 Catch:{ all -> 0x009c }
                    r3.onError(r0, r1);	 Catch:{ all -> 0x009c }
                L_0x006c:
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3.broadcastLoginStateChange();
                    r3 = r0;
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r0 = r0.loginModel;
                    r3.onLoginComplete(r0);
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3 = r3.loginModel;
                    r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3;
                    r3 = r3.getStatus();
                    r0 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                    if (r3 == r0) goto L_0x0096;
                L_0x0088:
                    r3 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r3 = r3.loginModel;
                    r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3;
                    r3 = r3.getStatus();
                    r0 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                    if (r3 != r0) goto L_0x009b;
                L_0x0096:
                    r3 = r0;
                    r3.clearLogIn();
                L_0x009b:
                    return;
                L_0x009c:
                    r3 = move-exception;
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r0.broadcastLoginStateChange();
                    r0 = r0;
                    r1 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r1 = r1.loginModel;
                    r0.onLoginComplete(r1);
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r0 = r0.loginModel;
                    r0 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r0;
                    r0 = r0.getStatus();
                    r1 = com.facebook.accountkit.internal.LoginStatus.SUCCESS;
                    if (r0 == r1) goto L_0x00c7;
                L_0x00b9:
                    r0 = com.facebook.accountkit.internal.PhoneLoginController.this;
                    r0 = r0.loginModel;
                    r0 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r0;
                    r0 = r0.getStatus();
                    r1 = com.facebook.accountkit.internal.LoginStatus.ERROR;
                    if (r0 != r1) goto L_0x00cc;
                L_0x00c7:
                    r0 = r0;
                    r0.clearLogIn();
                L_0x00cc:
                    throw r3;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.PhoneLoginController$AnonymousClass3.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
                }
            };
            Bundle bundle = new Bundle();
            Utility.putNonNullString(bundle, PARAMETER_USER_TOKEN, loginManager.getSeamlessLoginToken());
            Utility.putNonNullString(bundle, PARAMETER_PHONE, ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString());
            Utility.putNonNullString(bundle, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((PhoneLoginModelImpl) this.loginModel).getResponseType());
            Utility.putNonNullString(bundle, "state", ((PhoneLoginModelImpl) this.loginModel).getInitialAuthState());
            AccountKitGraphRequest buildGraphRequest = buildGraphRequest("instant_verification_login", bundle);
            AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(buildGraphRequest, anonymousClass3));
        }
    }
}

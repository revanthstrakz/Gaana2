package com.facebook.accountkit.internal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Pair;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoginModel;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.accountkit.internal.SeamlessLoginClient.CompletedListener;
import com.facebook.accountkit.ui.NotificationChannel;
import com.facebook.internal.NativeProtocol;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

final class LoginManager {
    private static final String LOGOUT_PATH = "logout/";
    private static final String SAVED_LOGIN_MODEL = "accountkitLoginModel";
    private static final String TAG = "com.facebook.accountkit.internal.LoginManager";
    private final AccessTokenManager accessTokenManager;
    private volatile Activity currentActivity;
    private volatile LoginController currentLoginController;
    private volatile boolean isActivityAvailable = false;
    private final LocalBroadcastManager localBroadcastManager;
    private final InternalLogger logger;
    private String requestInstanceToken;
    private SeamlessLoginClient seamlessLoginClient;
    private long seamlessLoginExpirationMillis;
    private String seamlessLoginToken;

    LoginManager(InternalLogger internalLogger, AccessTokenManager accessTokenManager, @NonNull LocalBroadcastManager localBroadcastManager) {
        this.accessTokenManager = accessTokenManager;
        this.localBroadcastManager = localBroadcastManager;
        this.logger = internalLogger;
        resetRequestInstanceToken();
    }

    /* Access modifiers changed, original: 0000 */
    public void continueWithCode(String str) {
        PhoneLoginModelImpl currentPhoneNumberLogInModel = getCurrentPhoneNumberLogInModel();
        if (currentPhoneNumberLogInModel != null) {
            try {
                currentPhoneNumberLogInModel.setConfirmationCode(str);
                handle(currentPhoneNumberLogInModel);
            } catch (AccountKitException e) {
                if (Utility.isDebuggable(AccountKitController.getApplicationContext())) {
                    throw e;
                }
                this.logger.logLoginModel(InternalLogger.EVENT_NAME_SET_CONFIRMATION_CODE, currentPhoneNumberLogInModel);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void continueSeamlessLogin() {
        PhoneLoginModelImpl currentPhoneNumberLogInModel = getCurrentPhoneNumberLogInModel();
        if (currentPhoneNumberLogInModel != null) {
            try {
                handle(currentPhoneNumberLogInModel);
            } catch (AccountKitException e) {
                if (Utility.isDebuggable(AccountKitController.getApplicationContext())) {
                    throw e;
                }
                this.logger.logLoginModel(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, currentPhoneNumberLogInModel);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onActivityCreate(Activity activity, Bundle bundle) {
        this.isActivityAvailable = true;
        this.currentActivity = activity;
        this.logger.onActivityCreate(bundle);
        if (bundle != null) {
            LoginModelImpl loginModelImpl = (LoginModelImpl) bundle.getParcelable(SAVED_LOGIN_MODEL);
            if (loginModelImpl != null) {
                startWith(loginModelImpl);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onActivityDestroy(Activity activity) {
        if (this.currentActivity == activity) {
            this.isActivityAvailable = false;
            this.currentLoginController = null;
            this.currentActivity = null;
            AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (this.currentActivity == activity) {
            this.logger.saveInstanceState(bundle);
            if (this.currentLoginController != null) {
                bundle.putParcelable(SAVED_LOGIN_MODEL, this.currentLoginController.getLoginModel());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public InternalLogger getLogger() {
        return this.logger;
    }

    /* Access modifiers changed, original: 0000 */
    public void handle(LoginModelImpl loginModelImpl) {
        Validate.loginModelsEqual(loginModelImpl, this.currentLoginController.getLoginModel());
        Utility.assertUIThread();
        switch (loginModelImpl.getStatus()) {
            case PENDING:
                this.currentLoginController.onPending();
                return;
            case ACCOUNT_VERIFIED:
                this.currentLoginController.onAccountVerified();
                return;
            case ERROR:
                this.currentLoginController.onError(loginModelImpl.getError());
                return;
            case CANCELLED:
                this.currentLoginController.onCancel();
                return;
            default:
                return;
        }
    }

    private void onLoginStart(LoginModelImpl loginModelImpl) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_START, loginModelImpl);
    }

    /* Access modifiers changed, original: 0000 */
    public void onLoginVerify(LoginModelImpl loginModelImpl) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_VERIFY, loginModelImpl);
    }

    /* Access modifiers changed, original: 0000 */
    public void onSeamlessLoginPending(LoginModelImpl loginModelImpl) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, loginModelImpl);
    }

    /* Access modifiers changed, original: 0000 */
    public void onLoginComplete(LoginModelImpl loginModelImpl) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_COMPLETE, loginModelImpl);
    }

    /* Access modifiers changed, original: 0000 */
    public EmailLoginModelImpl getCurrentEmailLogInModel() {
        if (this.currentLoginController == null) {
            return null;
        }
        LoginModelImpl loginModel = this.currentLoginController.getLoginModel();
        if (loginModel instanceof EmailLoginModelImpl) {
            return (EmailLoginModelImpl) loginModel;
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public PhoneLoginModelImpl getCurrentPhoneNumberLogInModel() {
        if (this.currentLoginController == null) {
            return null;
        }
        LoginModelImpl loginModel = this.currentLoginController.getLoginModel();
        if (loginModel instanceof PhoneLoginModelImpl) {
            return (PhoneLoginModelImpl) loginModel;
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public void cancelLogin() {
        Utility.assertUIThread();
        resetRequestInstanceToken();
        if (this.currentLoginController != null) {
            this.currentLoginController.onCancel();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
            this.currentLoginController = null;
        }
        AccountKitGraphRequestAsyncTask currentAsyncTask = AccountKitGraphRequestAsyncTask.getCurrentAsyncTask();
        if (currentAsyncTask != null) {
            currentAsyncTask.cancel(true);
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getRequestInstanceToken() {
        return this.requestInstanceToken;
    }

    /* Access modifiers changed, original: 0000 */
    public void initializeLogin() {
        this.seamlessLoginToken = null;
        this.logger.logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_STARTED);
        this.seamlessLoginClient = new SeamlessLoginClient(AccountKitController.getApplicationContext(), AccountKit.getApplicationId(), this.logger);
        if (this.seamlessLoginClient.start()) {
            this.seamlessLoginClient.setCompletedListener(new CompletedListener() {
                public void completed(Bundle bundle) {
                    LoginManager.this.seamlessLoginCompleted(bundle);
                }
            });
        }
    }

    private void seamlessLoginCompleted(Bundle bundle) {
        if (bundle != null) {
            this.seamlessLoginExpirationMillis = bundle.getLong(NativeProtocol.EXTRA_EXPIRES_SECONDS_SINCE_EPOCH) * 1000;
            this.seamlessLoginToken = bundle.getString("com.facebook.platform.extra.SEAMLESS_LOGIN_TOKEN");
            this.logger.logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_COMPLETED);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getSeamlessLoginToken() {
        if (this.seamlessLoginExpirationMillis < System.currentTimeMillis()) {
            this.seamlessLoginToken = null;
        }
        return this.seamlessLoginToken;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isSeamlessLoginRunning() {
        return this.seamlessLoginToken == null && this.seamlessLoginClient != null && this.seamlessLoginClient.isRunning();
    }

    /* Access modifiers changed, original: 0000 */
    public EmailLoginModelImpl logInWithEmail(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        Utility.assertUIThread();
        cancelExisting();
        EmailLoginModelImpl emailLoginModelImpl = new EmailLoginModelImpl(str, str2);
        EmailLoginController emailLoginController = new EmailLoginController(this.accessTokenManager, this, emailLoginModelImpl);
        emailLoginController.logIn(str3);
        onLoginStart(emailLoginModelImpl);
        this.currentLoginController = emailLoginController;
        return emailLoginModelImpl;
    }

    /* Access modifiers changed, original: 0000 */
    public PhoneLoginModelImpl logInWithPhoneNumber(@NonNull PhoneNumber phoneNumber, @NonNull NotificationChannel notificationChannel, @NonNull String str, @Nullable String str2) {
        Utility.assertUIThread();
        if (notificationChannel == NotificationChannel.SMS) {
            cancelExisting();
        }
        PhoneLoginModelImpl phoneLoginModelImpl = new PhoneLoginModelImpl(phoneNumber, notificationChannel, str);
        PhoneLoginController phoneLoginController = new PhoneLoginController(this.accessTokenManager, this, phoneLoginModelImpl);
        phoneLoginController.logIn(str2);
        onLoginStart(phoneLoginModelImpl);
        this.currentLoginController = phoneLoginController;
        return phoneLoginModelImpl;
    }

    /* Access modifiers changed, original: 0000 */
    public void logOut() {
        logOut(null);
        this.accessTokenManager.setCurrentAccessToken(null);
    }

    /* Access modifiers changed, original: 0000 */
    public void logOut(@Nullable final AccountKitCallback<Void> accountKitCallback) {
        AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
        if (currentAccessToken == null) {
            Log.w(TAG, "No access token: cannot log out");
            if (accountKitCallback != null) {
                accountKitCallback.onSuccess(null);
            }
            return;
        }
        AccountKitGraphRequest.executeAsync(new AccountKitGraphRequest(currentAccessToken, LOGOUT_PATH, null, false, HttpMethod.POST), new Callback() {
            public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                if (accountKitGraphResponse.getError() != null) {
                    Pair createErrorFromServerError = Utility.createErrorFromServerError(accountKitGraphResponse.getError());
                    LoginManager.this.logger.logEvent(InternalLogger.EVENT_NAME_LOG_OUT_ERROR);
                    if (accountKitCallback != null) {
                        accountKitCallback.onError((AccountKitError) createErrorFromServerError.first);
                        return;
                    }
                    return;
                }
                LoginManager.this.accessTokenManager.setCurrentAccessToken(null);
                LoginManager.this.logger.logEvent(InternalLogger.EVENT_NAME_LOG_OUT);
                if (accountKitCallback != null) {
                    accountKitCallback.onSuccess(null);
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void clearLogIn() {
        this.currentLoginController = null;
    }

    private void cancelExisting() {
        if (this.currentLoginController != null) {
            this.currentLoginController.getLoginModel().setStatus(LoginStatus.CANCELLED);
            this.currentLoginController.onCancel();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isActivityAvailable() {
        return this.isActivityAvailable;
    }

    /* Access modifiers changed, original: 0000 */
    public LocalBroadcastManager getLocalBroadcastManager() {
        return this.localBroadcastManager;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isLoginInProgress() {
        return this.currentLoginController != null;
    }

    private void cancelCurrentRequest() {
        this.currentLoginController = null;
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
    }

    /* Access modifiers changed, original: 0000 */
    public void cancel(LoginModel loginModel) {
        this.seamlessLoginToken = null;
        if (this.currentLoginController != null && Utility.areObjectsEqual(loginModel, this.currentLoginController.getLoginModel())) {
            cancelCurrentRequest();
        }
    }

    private void startWith(@NonNull LoginModelImpl loginModelImpl) {
        Utility.assertUIThread();
        if (loginModelImpl instanceof EmailLoginModelImpl) {
            this.currentLoginController = new EmailLoginController(this.accessTokenManager, this, (EmailLoginModelImpl) loginModelImpl);
        } else if (loginModelImpl instanceof PhoneLoginModelImpl) {
            this.currentLoginController = new PhoneLoginController(this.accessTokenManager, this, (PhoneLoginModelImpl) loginModelImpl);
        } else {
            throw new AccountKitException(Type.ARGUMENT_ERROR, InternalAccountKitError.INVALID_LOGIN_TYPE, loginModelImpl.getClass().getName());
        }
        handle(loginModelImpl);
    }

    /* Access modifiers changed, original: 0000 */
    public void getCurrentAccount(final AccountKitCallback<Account> accountKitCallback) {
        final AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
        if (currentAccessToken == null) {
            Log.w(TAG, "No access token: cannot retrieve account");
            accountKitCallback.onError(new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_RETRIEVE_ACCESS_TOKEN_NO_ACCOUNT));
            return;
        }
        AccountKitGraphRequest.executeAsync(new AccountKitGraphRequest(currentAccessToken, currentAccessToken.getAccountId(), null, false, HttpMethod.GET), new Callback() {
            public void onCompleted(AccountKitGraphResponse accountKitGraphResponse) {
                if (accountKitGraphResponse.getError() != null) {
                    accountKitCallback.onError((AccountKitError) Utility.createErrorFromServerError(accountKitGraphResponse.getError()).first);
                    return;
                }
                JSONObject responseObject = accountKitGraphResponse.getResponseObject();
                if (responseObject == null) {
                    accountKitCallback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_RESULT_FOUND));
                    return;
                }
                try {
                    String string;
                    String string2;
                    String string3 = responseObject.getString("id");
                    JSONObject optJSONObject = responseObject.optJSONObject("email");
                    PhoneNumber phoneNumber = null;
                    String string4 = optJSONObject != null ? optJSONObject.getString(AccountKitGraphConstants.EMAIL_ADDRESS_KEY) : null;
                    responseObject = responseObject.optJSONObject("phone");
                    if (responseObject != null) {
                        string = responseObject.getString(AccountKitGraphConstants.PHONE_NATIONAL_NUMBER);
                        string2 = responseObject.getString(AccountKitGraphConstants.PHONE_COUNTRY_PREFIX);
                    } else {
                        string2 = null;
                        string = string2;
                    }
                    if (string2 == null && string == null && string4 == null) {
                        accountKitCallback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_ACCOUNT_FOUND));
                    } else if ((string2 != null || string == null) && (string2 == null || string != null)) {
                        if (string2 != null) {
                            phoneNumber = new PhoneNumber(string2, string, null);
                        }
                        AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
                        if (currentAccessToken != null && currentAccessToken.equals(currentAccessToken)) {
                            LoginManager.this.accessTokenManager.refreshCurrentAccessToken(currentAccessToken);
                        }
                        accountKitCallback.onSuccess(new Account(string3, phoneNumber, string4));
                    } else {
                        accountKitCallback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_ACCOUNT_FOUND));
                    }
                } catch (JSONException unused) {
                    accountKitCallback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                }
            }
        });
    }

    private void resetRequestInstanceToken() {
        this.requestInstanceToken = UUID.randomUUID().toString();
    }
}

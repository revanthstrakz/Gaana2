package com.facebook.accountkit.internal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.Tracker;
import java.lang.ref.WeakReference;

abstract class LoginController<E extends LoginModelImpl> {
    static final String GRAPH_PATH_LOGIN_REQUEST_CONFIRM = "confirm_login";
    static final String GRAPH_PATH_LOGIN_REQUEST_STATUS = "poll_login";
    static final String GRAPH_PATH_LOGIN_START = "start_login";
    static final String GRAPH_PATH_SEAMLESS_REQUEST_CONFIRM = "instant_verification_login";
    static final String PARAMETER_ARGUMENT_FACEBOOK = "facebook";
    static final String PARAMETER_ARGUMENT_VOICE = "voice";
    private static final String PARAMETER_CREDENTIALS_TYPE = "credentials_type";
    static final String PARAMETER_FB_USER_TOKEN = "fb_user_token";
    static final String PARAMETER_FIELDS_TYPE = "fields";
    private static final String PARAMETER_LOGGING_REF = "logging_ref";
    private static final String PARAMETER_LOGIN_REQUEST_CODE = "login_request_code";
    static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    static final String PARAMETER_RESPONSE_TYPE = "response_type";
    static final String PARAMETER_STATE = "state";
    private static final String TAG = "com.facebook.accountkit.internal.LoginController";
    final AccessTokenManager accessTokenManager;
    private final WeakReference<LoginManager> loginManagerRef;
    protected final E loginModel;

    public abstract String getCredentialsType();

    public abstract String getLoginStateChangedIntentName();

    public abstract void logIn(String str);

    public abstract void onAccountVerified();

    public abstract void onCancel();

    public abstract void onPending();

    LoginController(@NonNull AccessTokenManager accessTokenManager, @NonNull LoginManager loginManager, @NonNull E e) {
        this.accessTokenManager = accessTokenManager;
        this.loginManagerRef = new WeakReference(loginManager);
        this.loginModel = e;
    }

    public E getLoginModel() {
        return this.loginModel;
    }

    public void onError(AccountKitError accountKitError) {
        this.loginModel.setError(accountKitError);
        this.loginModel.setStatus(LoginStatus.ERROR);
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.cancel(this.loginModel);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public AccountKitGraphRequest buildGraphRequest(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Utility.putNonNullString(bundle2, PARAMETER_CREDENTIALS_TYPE, getCredentialsType());
        Utility.putNonNullString(bundle2, "login_request_code", this.loginModel.getLoginRequestCode());
        Utility.putNonNullString(bundle2, PARAMETER_LOGGING_REF, getLoginManager() != null ? getLoginManager().getLogger().getLoggingRef() : null);
        bundle2.putAll(bundle);
        return new AccountKitGraphRequest(null, str, bundle2, isLoginRequestPath(str), HttpMethod.POST);
    }

    /* Access modifiers changed, original: 0000 */
    public LoginManager getLoginManager() {
        LoginManager loginManager = (LoginManager) this.loginManagerRef.get();
        if (loginManager == null) {
            return null;
        }
        if (loginManager.isActivityAvailable()) {
            return loginManager;
        }
        Log.w(TAG, "Warning: Callback issues while activity not available.");
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void onError(Type type, InternalAccountKitError internalAccountKitError) {
        onError(new AccountKitError(type, internalAccountKitError));
    }

    /* Access modifiers changed, original: 0000 */
    public void broadcastLoginStateChange() {
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.getLocalBroadcastManager().sendBroadcast(new Intent(getLoginStateChangedIntentName()).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, this.loginModel.getStatus()).putExtra(Tracker.EXTRA_LOGIN_ERROR, this.loginModel.getError()));
        }
    }

    private boolean isLoginRequestPath(String str) {
        return Utility.areObjectsEqual(str, GRAPH_PATH_LOGIN_START) || Utility.areObjectsEqual(str, GRAPH_PATH_LOGIN_REQUEST_STATUS) || Utility.areObjectsEqual(str, GRAPH_PATH_LOGIN_REQUEST_CONFIRM);
    }
}

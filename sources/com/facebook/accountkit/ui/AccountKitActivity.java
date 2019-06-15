package com.facebook.accountkit.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.LoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.R;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.KeyboardObserver.OnVisibleFrameChangedListener;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;

public final class AccountKitActivity extends AppCompatActivity {
    public static final String ACCOUNT_KIT_ACTIVITY_CONFIGURATION = AccountKitConfiguration.TAG;
    private static final IntentFilter LOGIN_FLOW_BROADCAST_RECEIVER_FILTER = LoginFlowBroadcastReceiver.getIntentFilter();
    private static final String LOGIN_FLOW_MANAGER_KEY;
    private static final String PENDING_LOGIN_FLOW_STATE_KEY;
    private static final String TAG = "AccountKitActivity";
    private static final String TRACKING_SMS_KEY;
    private static final String VIEW_STATE_KEY;
    private AccessToken accessToken;
    private String authorizationCode;
    private AccountKitConfiguration configuration;
    private AccountKitError error;
    private String finalAuthState;
    private boolean isActive;
    private KeyboardObserver keyboardObserver;
    private final BroadcastReceiver loginFlowBroadcastReceiver = new LoginFlowBroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (LoginFlowBroadcastReceiver.ACTION_UPDATE.contentEquals(intent.getAction())) {
                Event event = (Event) intent.getSerializableExtra(EXTRA_EVENT);
                ContentController contentController = AccountKitActivity.this.stateStackManager.getContentController();
                String stringExtra;
                PhoneLoginFlowManager phoneLoginFlowManager;
                PhoneLoginFlowManager phoneLoginFlowManager2;
                switch (event) {
                    case SENT_CODE_COMPLETE:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onSentCodeComplete(AccountKitActivity.this);
                        break;
                    case ACCOUNT_VERIFIED_COMPLETE:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onAccountVerifiedComplete(AccountKitActivity.this);
                        break;
                    case CONFIRM_SEAMLESS_LOGIN:
                        ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmSeamlessLogin(AccountKitActivity.this, (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager);
                        break;
                    case EMAIL_LOGIN_COMPLETE:
                        if (contentController instanceof EmailLoginContentController) {
                            stringExtra = intent.getStringExtra(EXTRA_EMAIL);
                            EmailLoginFlowManager emailLoginFlowManager = (EmailLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityEmailHandler) emailLoginFlowManager.getActivityHandler()).onEmailLoginComplete(AccountKitActivity.this, emailLoginFlowManager, stringExtra);
                            break;
                        }
                        break;
                    case EMAIL_VERIFY_RETRY:
                        if (contentController instanceof EmailVerifyContentController) {
                            ((ActivityEmailHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onEmailVerifyRetry(AccountKitActivity.this);
                            break;
                        }
                        break;
                    case ERROR_RESTART:
                        if (contentController instanceof ErrorContentController) {
                            ActivityErrorHandler.onErrorRestart(AccountKitActivity.this, LoginFlowState.values()[intent.getIntExtra(EXTRA_RETURN_LOGIN_FLOW_STATE, 0)]);
                            break;
                        }
                        break;
                    case PHONE_LOGIN_COMPLETE:
                        if (contentController instanceof PhoneLoginContentController) {
                            PhoneNumber phoneNumber = (PhoneNumber) intent.getParcelableExtra(EXTRA_PHONE_NUMBER);
                            phoneLoginFlowManager = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager.getActivityHandler()).onPhoneLoginComplete(AccountKitActivity.this, phoneLoginFlowManager, phoneNumber);
                            break;
                        }
                        break;
                    case PHONE_CONFIRMATION_CODE_COMPLETE:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            stringExtra = intent.getStringExtra(EXTRA_CONFIRMATION_CODE);
                            phoneLoginFlowManager = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager.getActivityHandler()).onConfirmationCodeComplete(AccountKitActivity.this, phoneLoginFlowManager, stringExtra);
                            break;
                        }
                        break;
                    case PHONE_CONFIRMATION_CODE_RETRY:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmationCodeRetry(AccountKitActivity.this);
                            break;
                        }
                        break;
                    case PHONE_RESEND:
                        if ((contentController instanceof ResendContentController) || (contentController instanceof ConfirmationCodeContentController)) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onResend(AccountKitActivity.this);
                            break;
                        }
                    case PHONE_RESEND_FACEBOOK_NOTIFICATION:
                        if (contentController instanceof ResendContentController) {
                            phoneLoginFlowManager2 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager2.getActivityHandler()).onResendFacebookNotification(AccountKitActivity.this, phoneLoginFlowManager2);
                            break;
                        }
                        break;
                    case PHONE_RESEND_VOICE_CALL_NOTIFICATION:
                        if (contentController instanceof ResendContentController) {
                            phoneLoginFlowManager2 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager2.getActivityHandler()).onResendVoiceCallNotification(AccountKitActivity.this, phoneLoginFlowManager2);
                            break;
                        }
                        break;
                }
            }
        }
    };
    private LoginFlowManager loginFlowManager;
    private Tracker loginTracker;
    private LoginResult result = LoginResult.CANCELLED;
    private StateStackManager stateStackManager;
    private long tokenRefreshIntervalInSeconds;
    private final Bundle viewState = new Bundle();

    public enum ResponseType {
        CODE("code"),
        TOKEN(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE);
        
        private final String value;

        private ResponseType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Deprecated
    public enum TitleType {
        APP_NAME,
        LOGIN
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".loginFlowManager");
        LOGIN_FLOW_MANAGER_KEY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".pendingLoginFlowState");
        PENDING_LOGIN_FLOW_STATE_KEY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".trackingSms");
        TRACKING_SMS_KEY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".viewState");
        VIEW_STATE_KEY = stringBuilder.toString();
    }

    private static boolean urlIsRedirectUrl(@NonNull String str) {
        return str.startsWith(Utility.getRedirectURL());
    }

    /* Access modifiers changed, original: 0000 */
    public ContentController getContentController() {
        return this.stateStackManager.getContentController();
    }

    public void onBackPressed() {
        if (this.stateStackManager.getContentController() == null) {
            super.onBackPressed();
        } else {
            backPressed();
        }
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    public void onCancelPressed(View view) {
        sendCancelResult();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.configuration = (AccountKitConfiguration) intent.getParcelableExtra(ACCOUNT_KIT_ACTIVITY_CONFIGURATION);
        if (this.configuration == null) {
            this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_CONFIGURATION);
            sendResult();
        } else if (ViewUtility.doesTextColorContrast(this, this.configuration.getUIManager())) {
            int themeId = this.configuration.getUIManager().getThemeId();
            if (themeId != -1) {
                setTheme(themeId);
            }
            boolean z = true;
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
            if (!ViewUtility.isTablet(this)) {
                setRequestedOrientation(1);
            }
            String dataString = intent.getDataString();
            if (dataString != null && !urlIsRedirectUrl(dataString)) {
                sendResult();
            } else if (this.configuration.getLoginType() == null) {
                this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_LOGIN_TYPE);
                sendResult();
            } else if (this.configuration.getResponseType() == null) {
                this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_RESPONSE_TYPE);
                sendResult();
            } else {
                this.stateStackManager = new StateStackManager(this, this.configuration);
                setContentView(R.layout.com_accountkit_activity_layout);
                final ConstrainedLinearLayout constrainedLinearLayout = (ConstrainedLinearLayout) findViewById(R.id.com_accountkit_content_view);
                View findViewById = findViewById(R.id.com_accountkit_scroll_view);
                if (!(constrainedLinearLayout == null || findViewById == null || constrainedLinearLayout.getMinHeight() >= 0 || constrainedLinearLayout.getRootView() == null)) {
                    this.keyboardObserver = new KeyboardObserver(findViewById);
                    this.keyboardObserver.setOnVisibleFrameChangedListener(new OnVisibleFrameChangedListener() {
                        public void onVisibleFrameChanged(Rect rect) {
                            int height = rect.height();
                            if (height >= 0) {
                                constrainedLinearLayout.setMinHeight(height);
                            }
                        }
                    });
                }
                AccountKit.onActivityCreate(this, bundle);
                if (bundle != null) {
                    this.viewState.putAll(bundle.getBundle(VIEW_STATE_KEY));
                }
                Bundle bundle2 = this.viewState;
                if (bundle == null) {
                    z = false;
                }
                onViewReadyWithState(bundle2, z);
                LocalBroadcastManager.getInstance(this).registerReceiver(this.loginFlowBroadcastReceiver, LOGIN_FLOW_BROADCAST_RECEIVER_FILTER);
            }
        } else {
            Logger.logInvalidUIManager();
            this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_BACKGROUND_CONTRACT);
            sendResult();
        }
    }

    public void onPause() {
        super.onPause();
        ContentController contentController = getContentController();
        if (contentController != null) {
            contentController.onPause(this);
        }
        this.isActive = false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.loginTracker != null) {
            this.loginTracker.pauseTracking();
        }
        AccountKit.onActivitySaveInstanceState(this, bundle);
        if (this.loginFlowManager.getLoginType() == LoginType.PHONE) {
            ActivityPhoneHandler activityPhoneHandler = (ActivityPhoneHandler) this.loginFlowManager.getActivityHandler();
            activityPhoneHandler.pauseSmsTracker();
            this.viewState.putBoolean(TRACKING_SMS_KEY, activityPhoneHandler.isSmsTracking());
            this.viewState.putParcelable(LOGIN_FLOW_MANAGER_KEY, this.loginFlowManager);
        }
        bundle.putBundle(VIEW_STATE_KEY, this.viewState);
        super.onSaveInstanceState(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String dataString = intent.getDataString();
        if (dataString != null) {
            if (urlIsRedirectUrl(dataString)) {
                if (getContentController() instanceof EmailVerifyContentController) {
                    pushState(LoginFlowState.VERIFYING_CODE, null);
                }
                return;
            }
            sendResult();
        }
    }

    private void onViewReadyWithState(Bundle bundle, boolean z) {
        ViewUtility.applyThemeBackground(this, this.configuration.getUIManager(), findViewById(R.id.com_accountkit_background));
        setNewLoginFlowManagerAndHandler((LoginFlowManager) bundle.getParcelable(LOGIN_FLOW_MANAGER_KEY));
        if (!z) {
            if (this.configuration != null) {
                switch (this.configuration.getLoginType()) {
                    case PHONE:
                        pushState(LoginFlowState.PHONE_NUMBER_INPUT, null);
                        break;
                    case EMAIL:
                        pushState(LoginFlowState.EMAIL_INPUT, null);
                        break;
                    default:
                        this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_LOGIN_TYPE);
                        sendResult();
                        break;
                }
            }
            return;
        }
        this.stateStackManager.updateContentController(this);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        this.isActive = true;
        if (this.configuration != null) {
            switch (this.configuration.getLoginType()) {
                case PHONE:
                case EMAIL:
                    this.loginTracker = this.loginFlowManager.getActivityHandler().getLoginTracker(this);
                    this.loginTracker.startTracking();
                    break;
            }
            if (this.viewState.getBoolean(TRACKING_SMS_KEY, false) && this.loginFlowManager.getLoginType() == LoginType.PHONE) {
                ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).startSmsTrackerIfPossible(this);
            }
            String string = this.viewState.getString(PENDING_LOGIN_FLOW_STATE_KEY);
            if (!Utility.isNullOrEmpty(string)) {
                this.viewState.putString(PENDING_LOGIN_FLOW_STATE_KEY, null);
                pushState(LoginFlowState.valueOf(string), null);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.loginFlowBroadcastReceiver);
        super.onDestroy();
        if (this.keyboardObserver != null) {
            this.keyboardObserver.setOnVisibleFrameChangedListener(null);
            this.keyboardObserver = null;
        }
        if (this.loginTracker != null) {
            this.loginTracker.stopTracking();
            this.loginTracker = null;
        }
        if (this.loginFlowManager != null && this.loginFlowManager.getLoginType() == LoginType.PHONE) {
            ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).stopSmsTracker();
        }
        AccountKit.onActivityDestroy(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        backPressed();
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void sendCancelResult() {
        sendResult(0, new AccountKitLoginResultImpl(null, null, null, 0, null, true));
    }

    /* Access modifiers changed, original: 0000 */
    public void sendResult() {
        sendResult(this.result == LoginResult.SUCCESS ? -1 : 0, new AccountKitLoginResultImpl(this.accessToken, this.authorizationCode, this.finalAuthState, this.tokenRefreshIntervalInSeconds, this.error, false));
    }

    private void sendResult(int i, AccountKitLoginResult accountKitLoginResult) {
        if (getCallingActivity() == null) {
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(AccountKitLoginResult.RESULT_KEY, accountKitLoginResult);
        setResult(i, intent);
        finish();
    }

    /* Access modifiers changed, original: 0000 */
    public void setAuthorizationCode(String str) {
        this.authorizationCode = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setFinalAuthState(String str) {
        this.finalAuthState = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    /* Access modifiers changed, original: 0000 */
    public void setTokenRefreshIntervalInSeconds(long j) {
        this.tokenRefreshIntervalInSeconds = j;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLoginResult(LoginResult loginResult) {
        this.result = loginResult;
    }

    private void backPressed() {
        ContentController contentController = this.stateStackManager.getContentController();
        if (contentController != null) {
            if (contentController instanceof ConfirmationCodeContentController) {
                ((ConfirmationCodeContentController) contentController).setRetry(false);
            }
            onContentControllerDismissed(contentController);
            switch (contentController.getLoginFlowState()) {
                case NONE:
                case PHONE_NUMBER_INPUT:
                case EMAIL_INPUT:
                    sendCancelResult();
                    break;
                case SENDING_CODE:
                case SENT_CODE:
                case CODE_INPUT:
                case ACCOUNT_VERIFIED:
                case CONFIRM_ACCOUNT_VERIFIED:
                case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                case EMAIL_VERIFY:
                case VERIFYING_CODE:
                case RESEND:
                    resetFlowTo(LoginFlowState.getBackState(this.loginFlowManager.getFlowState()));
                    break;
                case ERROR:
                    resetFlowTo(((ErrorContentController) contentController).getReturnState());
                    break;
                case VERIFIED:
                    sendResult();
                    break;
                default:
                    resetFlowTo(LoginFlowState.NONE);
                    break;
            }
        }
    }

    private void resetFlowTo(LoginFlowState loginFlowState) {
        this.loginFlowManager.setFlowState(loginFlowState);
        AnonymousClass3 anonymousClass3 = new OnPopListener() {
            public void onContentPopped() {
                AccountKitActivity.this.getContentController().onResume(AccountKitActivity.this);
            }
        };
        setNewLoginFlowManagerAndHandler(null);
        popBackStack(loginFlowState, anonymousClass3);
    }

    /* Access modifiers changed, original: 0000 */
    public void setNewLoginFlowManagerAndHandler(LoginFlowManager loginFlowManager) {
        LoginFlowState loginFlowState;
        if (this.loginFlowManager == null) {
            loginFlowState = LoginFlowState.NONE;
        } else {
            loginFlowState = this.loginFlowManager.getFlowState();
        }
        if (loginFlowManager == null && this.loginFlowManager != null) {
            this.loginFlowManager.cancel();
        }
        switch (this.configuration.getLoginType()) {
            case PHONE:
                this.loginFlowManager = new PhoneLoginFlowManager(this.configuration);
                this.loginFlowManager.setFlowState(loginFlowState);
                return;
            case EMAIL:
                this.loginFlowManager = new EmailLoginFlowManager(this.configuration);
                this.loginFlowManager.setFlowState(loginFlowState);
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void popBackStack(@NonNull LoginFlowState loginFlowState, @Nullable OnPopListener onPopListener) {
        this.stateStackManager.popBackStack(loginFlowState, onPopListener);
    }

    /* Access modifiers changed, original: 0000 */
    public void multiPopBackStack(@Nullable OnPopListener onPopListener) {
        this.stateStackManager.multiPopBackStack(onPopListener);
    }

    /* Access modifiers changed, original: 0000 */
    public void pushError(@Nullable AccountKitError accountKitError) {
        String userFacingMessage = accountKitError == null ? null : accountKitError.getUserFacingMessage();
        this.error = accountKitError;
        LoginFlowState backState = LoginFlowState.getBackState(this.loginFlowManager.getFlowState());
        this.loginFlowManager.setFlowState(LoginFlowState.ERROR);
        this.stateStackManager.pushError(this, this.loginFlowManager, backState, accountKitError, this.stateStackManager.getErrorOnPushListener(userFacingMessage));
    }

    /* Access modifiers changed, original: 0000 */
    public void pushState(LoginFlowState loginFlowState, @Nullable OnPushListener onPushListener) {
        if (this.isActive) {
            this.loginFlowManager.setFlowState(loginFlowState);
            if (onPushListener == null) {
                int i = AnonymousClass4.$SwitchMap$com$facebook$accountkit$ui$LoginFlowState[loginFlowState.ordinal()];
                if (i == 6) {
                    onPushListener = ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).getConfirmationCodePushListener(this);
                } else if (i == 13) {
                    pushError(null);
                    return;
                }
            }
            this.stateStackManager.pushState(this, this.loginFlowManager, onPushListener);
        } else {
            this.viewState.putString(PENDING_LOGIN_FLOW_STATE_KEY, loginFlowState.name());
        }
        if (!loginFlowState.equals(LoginFlowState.ERROR)) {
            this.error = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onContentControllerDismissed(ContentController contentController) {
        if (contentController != null) {
            contentController.onPause(this);
            logContentControllerDismissed(contentController);
        }
    }

    private void logContentControllerDismissed(ContentController contentController) {
        if (this.configuration != null) {
            if (contentController instanceof PhoneLoginContentController) {
                Logger.logUIPhoneLogin();
            } else if (contentController instanceof SendingCodeContentController) {
                Logger.logUISendingCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof SentCodeContentController) {
                Logger.logUISentCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof ConfirmationCodeContentController) {
                Logger.logUIConfirmationCode();
            } else if (contentController instanceof VerifyingCodeContentController) {
                Logger.logUIVerifyingCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof VerifiedCodeContentController) {
                Logger.logUIVerifiedCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof ErrorContentController) {
                Logger.logUIError(false, this.configuration.getLoginType());
            } else if (contentController instanceof EmailLoginContentController) {
                Logger.logUIEmailLogin();
            } else if (contentController instanceof EmailVerifyContentController) {
                Logger.logUIEmailVerify(false);
            } else if (contentController instanceof ResendContentController) {
                Logger.logUIResend(false);
            } else if (contentController instanceof ConfirmAccountVerifiedContentController) {
                Logger.logUIConfirmAccountVerified(false);
            } else if (contentController instanceof AccountVerifiedContentController) {
                Logger.logUIAccountVerified(false);
            } else {
                throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_FRAGMENT, contentController.getClass().getName());
            }
        }
    }
}

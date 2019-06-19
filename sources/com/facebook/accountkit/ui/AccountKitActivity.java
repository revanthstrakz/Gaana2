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
                switch (AnonymousClass4.$SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event[event.ordinal()]) {
                    case 1:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onSentCodeComplete(AccountKitActivity.this);
                        break;
                    case 2:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onAccountVerifiedComplete(AccountKitActivity.this);
                        break;
                    case 3:
                        ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmSeamlessLogin(AccountKitActivity.this, (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager);
                        break;
                    case 4:
                        if (contentController instanceof EmailLoginContentController) {
                            stringExtra = intent.getStringExtra(EXTRA_EMAIL);
                            EmailLoginFlowManager emailLoginFlowManager = (EmailLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityEmailHandler) emailLoginFlowManager.getActivityHandler()).onEmailLoginComplete(AccountKitActivity.this, emailLoginFlowManager, stringExtra);
                            break;
                        }
                        break;
                    case 5:
                        if (contentController instanceof EmailVerifyContentController) {
                            ((ActivityEmailHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onEmailVerifyRetry(AccountKitActivity.this);
                            break;
                        }
                        break;
                    case 6:
                        if (contentController instanceof ErrorContentController) {
                            ActivityErrorHandler.onErrorRestart(AccountKitActivity.this, LoginFlowState.values()[intent.getIntExtra(EXTRA_RETURN_LOGIN_FLOW_STATE, 0)]);
                            break;
                        }
                        break;
                    case 7:
                        if (contentController instanceof PhoneLoginContentController) {
                            PhoneNumber phoneNumber = (PhoneNumber) intent.getParcelableExtra(EXTRA_PHONE_NUMBER);
                            phoneLoginFlowManager = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager.getActivityHandler()).onPhoneLoginComplete(AccountKitActivity.this, phoneLoginFlowManager, phoneNumber);
                            break;
                        }
                        break;
                    case 8:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            stringExtra = intent.getStringExtra(EXTRA_CONFIRMATION_CODE);
                            phoneLoginFlowManager = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager.getActivityHandler()).onConfirmationCodeComplete(AccountKitActivity.this, phoneLoginFlowManager, stringExtra);
                            break;
                        }
                        break;
                    case 9:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmationCodeRetry(AccountKitActivity.this);
                            break;
                        }
                        break;
                    case 10:
                        if ((contentController instanceof ResendContentController) || (contentController instanceof ConfirmationCodeContentController)) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onResend(AccountKitActivity.this);
                            break;
                        }
                    case 11:
                        if (contentController instanceof ResendContentController) {
                            phoneLoginFlowManager2 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) phoneLoginFlowManager2.getActivityHandler()).onResendFacebookNotification(AccountKitActivity.this, phoneLoginFlowManager2);
                            break;
                        }
                        break;
                    case 12:
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

    /* renamed from: com.facebook.accountkit.ui.AccountKitActivity$4 */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event = new int[Event.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00ee */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x012a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x00f8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0134 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x013e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00bd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0102 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x010c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0120 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00e4 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(70:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(69:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(68:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(67:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(66:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(66:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(65:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(64:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(64:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(63:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(62:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        /* JADX WARNING: Can't wrap try/catch for region: R(61:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|41|42|43|45|46|47|48|49|50|51|53|54|55|56|57|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|84) */
        static {
            /*
            r0 = com.facebook.accountkit.ui.LoginFlowState.values();
            r0 = r0.length;
            r0 = new int[r0];
            $SwitchMap$com$facebook$accountkit$ui$LoginFlowState = r0;
            r0 = 1;
            r1 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.facebook.accountkit.ui.LoginFlowState.NONE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.facebook.accountkit.ui.LoginFlowState.PHONE_NUMBER_INPUT;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = 3;
            r3 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = com.facebook.accountkit.ui.LoginFlowState.EMAIL_INPUT;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r3 = 4;
            r4 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = com.facebook.accountkit.ui.LoginFlowState.SENDING_CODE;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r4 = 5;
            r5 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r6 = com.facebook.accountkit.ui.LoginFlowState.SENT_CODE;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r6 = r6.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r5[r6] = r4;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            r5 = 6;
            r6 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x004b }
            r7 = com.facebook.accountkit.ui.LoginFlowState.CODE_INPUT;	 Catch:{ NoSuchFieldError -> 0x004b }
            r7 = r7.ordinal();	 Catch:{ NoSuchFieldError -> 0x004b }
            r6[r7] = r5;	 Catch:{ NoSuchFieldError -> 0x004b }
        L_0x004b:
            r6 = 7;
            r7 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r8 = com.facebook.accountkit.ui.LoginFlowState.ACCOUNT_VERIFIED;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r8 = r8.ordinal();	 Catch:{ NoSuchFieldError -> 0x0056 }
            r7[r8] = r6;	 Catch:{ NoSuchFieldError -> 0x0056 }
        L_0x0056:
            r7 = 8;
            r8 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0062 }
            r9 = com.facebook.accountkit.ui.LoginFlowState.CONFIRM_ACCOUNT_VERIFIED;	 Catch:{ NoSuchFieldError -> 0x0062 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0062 }
            r8[r9] = r7;	 Catch:{ NoSuchFieldError -> 0x0062 }
        L_0x0062:
            r8 = 9;
            r9 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x006e }
            r10 = com.facebook.accountkit.ui.LoginFlowState.CONFIRM_INSTANT_VERIFICATION_LOGIN;	 Catch:{ NoSuchFieldError -> 0x006e }
            r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x006e }
            r9[r10] = r8;	 Catch:{ NoSuchFieldError -> 0x006e }
        L_0x006e:
            r9 = 10;
            r10 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x007a }
            r11 = com.facebook.accountkit.ui.LoginFlowState.EMAIL_VERIFY;	 Catch:{ NoSuchFieldError -> 0x007a }
            r11 = r11.ordinal();	 Catch:{ NoSuchFieldError -> 0x007a }
            r10[r11] = r9;	 Catch:{ NoSuchFieldError -> 0x007a }
        L_0x007a:
            r10 = 11;
            r11 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0086 }
            r12 = com.facebook.accountkit.ui.LoginFlowState.VERIFYING_CODE;	 Catch:{ NoSuchFieldError -> 0x0086 }
            r12 = r12.ordinal();	 Catch:{ NoSuchFieldError -> 0x0086 }
            r11[r12] = r10;	 Catch:{ NoSuchFieldError -> 0x0086 }
        L_0x0086:
            r11 = 12;
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x0092 }
            r13 = com.facebook.accountkit.ui.LoginFlowState.RESEND;	 Catch:{ NoSuchFieldError -> 0x0092 }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x0092 }
            r12[r13] = r11;	 Catch:{ NoSuchFieldError -> 0x0092 }
        L_0x0092:
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x009e }
            r13 = com.facebook.accountkit.ui.LoginFlowState.ERROR;	 Catch:{ NoSuchFieldError -> 0x009e }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x009e }
            r14 = 13;
            r12[r13] = r14;	 Catch:{ NoSuchFieldError -> 0x009e }
        L_0x009e:
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowState;	 Catch:{ NoSuchFieldError -> 0x00aa }
            r13 = com.facebook.accountkit.ui.LoginFlowState.VERIFIED;	 Catch:{ NoSuchFieldError -> 0x00aa }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x00aa }
            r14 = 14;
            r12[r13] = r14;	 Catch:{ NoSuchFieldError -> 0x00aa }
        L_0x00aa:
            r12 = com.facebook.accountkit.ui.LoginType.values();
            r12 = r12.length;
            r12 = new int[r12];
            $SwitchMap$com$facebook$accountkit$ui$LoginType = r12;
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginType;	 Catch:{ NoSuchFieldError -> 0x00bd }
            r13 = com.facebook.accountkit.ui.LoginType.PHONE;	 Catch:{ NoSuchFieldError -> 0x00bd }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x00bd }
            r12[r13] = r0;	 Catch:{ NoSuchFieldError -> 0x00bd }
        L_0x00bd:
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginType;	 Catch:{ NoSuchFieldError -> 0x00c7 }
            r13 = com.facebook.accountkit.ui.LoginType.EMAIL;	 Catch:{ NoSuchFieldError -> 0x00c7 }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x00c7 }
            r12[r13] = r1;	 Catch:{ NoSuchFieldError -> 0x00c7 }
        L_0x00c7:
            r12 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.values();
            r12 = r12.length;
            r12 = new int[r12];
            $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event = r12;
            r12 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x00da }
            r13 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.SENT_CODE_COMPLETE;	 Catch:{ NoSuchFieldError -> 0x00da }
            r13 = r13.ordinal();	 Catch:{ NoSuchFieldError -> 0x00da }
            r12[r13] = r0;	 Catch:{ NoSuchFieldError -> 0x00da }
        L_0x00da:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r12 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.ACCOUNT_VERIFIED_COMPLETE;	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r12 = r12.ordinal();	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r0[r12] = r1;	 Catch:{ NoSuchFieldError -> 0x00e4 }
        L_0x00e4:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x00ee }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.CONFIRM_SEAMLESS_LOGIN;	 Catch:{ NoSuchFieldError -> 0x00ee }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ee }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x00ee }
        L_0x00ee:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x00f8 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.EMAIL_LOGIN_COMPLETE;	 Catch:{ NoSuchFieldError -> 0x00f8 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x00f8 }
            r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x00f8 }
        L_0x00f8:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x0102 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.EMAIL_VERIFY_RETRY;	 Catch:{ NoSuchFieldError -> 0x0102 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0102 }
            r0[r1] = r4;	 Catch:{ NoSuchFieldError -> 0x0102 }
        L_0x0102:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x010c }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.ERROR_RESTART;	 Catch:{ NoSuchFieldError -> 0x010c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x010c }
            r0[r1] = r5;	 Catch:{ NoSuchFieldError -> 0x010c }
        L_0x010c:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x0116 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_LOGIN_COMPLETE;	 Catch:{ NoSuchFieldError -> 0x0116 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0116 }
            r0[r1] = r6;	 Catch:{ NoSuchFieldError -> 0x0116 }
        L_0x0116:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x0120 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_CONFIRMATION_CODE_COMPLETE;	 Catch:{ NoSuchFieldError -> 0x0120 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0120 }
            r0[r1] = r7;	 Catch:{ NoSuchFieldError -> 0x0120 }
        L_0x0120:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x012a }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_CONFIRMATION_CODE_RETRY;	 Catch:{ NoSuchFieldError -> 0x012a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x012a }
            r0[r1] = r8;	 Catch:{ NoSuchFieldError -> 0x012a }
        L_0x012a:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x0134 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_RESEND;	 Catch:{ NoSuchFieldError -> 0x0134 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0134 }
            r0[r1] = r9;	 Catch:{ NoSuchFieldError -> 0x0134 }
        L_0x0134:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x013e }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_RESEND_FACEBOOK_NOTIFICATION;	 Catch:{ NoSuchFieldError -> 0x013e }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x013e }
            r0[r1] = r10;	 Catch:{ NoSuchFieldError -> 0x013e }
        L_0x013e:
            r0 = $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event;	 Catch:{ NoSuchFieldError -> 0x0148 }
            r1 = com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event.PHONE_RESEND_VOICE_CALL_NOTIFICATION;	 Catch:{ NoSuchFieldError -> 0x0148 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0148 }
            r0[r1] = r11;	 Catch:{ NoSuchFieldError -> 0x0148 }
        L_0x0148:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.AccountKitActivity$AnonymousClass4.<clinit>():void");
        }
    }

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

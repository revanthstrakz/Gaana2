package com.facebook.accountkit.ui;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

abstract class LoginFlowBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_UPDATE;
    public static final String EXTRA_CONFIRMATION_CODE;
    public static final String EXTRA_EMAIL;
    public static final String EXTRA_EVENT;
    public static final String EXTRA_PHONE_NUMBER;
    public static final String EXTRA_RETURN_LOGIN_FLOW_STATE;
    private static final String TAG = "LoginFlowBroadcastReceiver";

    public enum Event {
        SENT_CODE_COMPLETE,
        ACCOUNT_VERIFIED_COMPLETE,
        CONFIRM_SEAMLESS_LOGIN,
        EMAIL_LOGIN_COMPLETE,
        EMAIL_VERIFY_RETRY,
        ERROR_RESTART,
        PHONE_LOGIN_COMPLETE,
        PHONE_CONFIRMATION_CODE_COMPLETE,
        PHONE_CONFIRMATION_CODE_RETRY,
        PHONE_RESEND,
        PHONE_RESEND_FACEBOOK_NOTIFICATION,
        PHONE_RESEND_VOICE_CALL_NOTIFICATION
    }

    LoginFlowBroadcastReceiver() {
    }

    public static IntentFilter getIntentFilter() {
        return new IntentFilter(ACTION_UPDATE);
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".action_update");
        ACTION_UPDATE = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".extra_event");
        EXTRA_EVENT = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".extra_email");
        EXTRA_EMAIL = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".extra_confirmationCode");
        EXTRA_CONFIRMATION_CODE = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".extra_phoneNumber");
        EXTRA_PHONE_NUMBER = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".EXTRA_RETURN_LOGIN_FLOW_STATE");
        EXTRA_RETURN_LOGIN_FLOW_STATE = stringBuilder.toString();
    }
}

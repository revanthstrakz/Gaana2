package com.facebook.accountkit.ui;

import android.content.Intent;
import android.telephony.SmsMessage;
import com.facebook.accountkit.Tracker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class SmsTracker extends Tracker {
    private static final Pattern ACCOUNT_KIT_PATTERN = Pattern.compile("(\\d{6})(?=.*\\bAccount Kit\\b)(?=.*\\bFacebook\\b)");
    static final String SMS_INTENT = "android.provider.Telephony.SMS_RECEIVED";

    public abstract void confirmationCodeReceived(String str);

    /* Access modifiers changed, original: protected */
    public String getActionStateChanged() {
        return SMS_INTENT;
    }

    /* Access modifiers changed, original: protected */
    public boolean isLocal() {
        return false;
    }

    public SmsTracker() {
        startTracking();
    }

    /* Access modifiers changed, original: protected */
    public void onReceive(Intent intent) {
        String str = "";
        for (Object obj : (Object[]) intent.getSerializableExtra("pdus")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SmsMessage.createFromPdu((byte[]) obj).getDisplayMessageBody());
            str = stringBuilder.toString();
        }
        String codeFromString = getCodeFromString(str);
        if (codeFromString != null) {
            confirmationCodeReceived(codeFromString);
        }
    }

    static String getCodeFromString(String str) {
        Matcher matcher = ACCOUNT_KIT_PATTERN.matcher(str);
        return matcher.find() ? matcher.group(1) : null;
    }
}

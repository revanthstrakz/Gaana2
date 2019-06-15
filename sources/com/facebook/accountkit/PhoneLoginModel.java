package com.facebook.accountkit;

import android.support.annotation.Nullable;
import com.facebook.accountkit.ui.NotificationChannel;

public interface PhoneLoginModel extends LoginModel {
    String getConfirmationCode();

    NotificationChannel getNotificationChannel();

    PhoneNumber getPhoneNumber();

    @Nullable
    String getPrivacyPolicy();

    long getResendTime();

    @Nullable
    String getTermsOfService();
}

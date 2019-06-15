package com.facebook.accountkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.ui.NotificationChannel;
import java.util.concurrent.Executor;

public final class AccountKit {
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.accountkit.ApplicationName";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.accountkit.ClientToken";
    public static final String FACEBOOK_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.accountkit.AccountKitFacebookAppEventsEnabled";
    private static final Object LOCK = new Object();
    private static volatile Executor executor;
    private static final LoggingBehaviorCollection loggingBehaviors = new LoggingBehaviorCollection();

    public interface InitializeCallback {
        void onInitialized();
    }

    public static LoggingBehaviorCollection getLoggingBehaviors() {
        return loggingBehaviors;
    }

    public static boolean isInitialized() {
        return AccountKitController.isInitialized();
    }

    @Deprecated
    public static synchronized void initialize(Context context) {
        synchronized (AccountKit.class) {
            initialize(context, null);
        }
    }

    public static void initialize(Context context, InitializeCallback initializeCallback) {
        AccountKitController.initialize(context, initializeCallback);
    }

    @Deprecated
    public static EmailLoginModel logInWithEmail(String str, String str2, @Nullable String str3) {
        return AccountKitController.logInWithEmail(str, str2, str3);
    }

    @Deprecated
    public static PhoneLoginModel logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel notificationChannel, String str, @Nullable String str2) {
        if (getCurrentAccessToken() != null) {
            logOut();
        }
        return AccountKitController.logInWithPhoneNumber(phoneNumber, notificationChannel, str, str2);
    }

    public static void logOut() {
        AccountKitController.logOut();
    }

    private static void logOut(AccountKitCallback<Void> accountKitCallback) {
        AccountKitController.logOut(accountKitCallback);
    }

    public static void cancelLogin() {
        AccountKitController.cancelLogin();
    }

    @Nullable
    public static AccessToken getCurrentAccessToken() {
        return AccountKitController.getCurrentAccessToken();
    }

    @Nullable
    public static AccountPreferences getAccountPreferences() {
        return AccountKitController.getAccountPreferences();
    }

    public static void getCurrentAccount(AccountKitCallback<Account> accountKitCallback) {
        AccountKitController.getCurrentAccount(accountKitCallback);
    }

    public static EmailLoginModel getCurrentEmailLogInModel() {
        return AccountKitController.getCurrentEmailLogInModel();
    }

    public static PhoneLoginModel getCurrentPhoneNumberLogInModel() {
        return AccountKitController.getCurrentPhoneNumberLogInModel();
    }

    public static void onActivityCreate(Activity activity, Bundle bundle) {
        AccountKitController.onActivityCreate(activity, bundle);
    }

    public static void onActivityDestroy(Activity activity) {
        AccountKitController.onActivityDestroy(activity);
    }

    public static void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        AccountKitController.onActivitySaveInstanceState(activity, bundle);
    }

    public static String getApplicationId() {
        return AccountKitController.getApplicationId();
    }

    public static String getApplicationName() {
        return AccountKitController.getApplicationName();
    }

    public static String getClientToken() {
        return AccountKitController.getClientToken();
    }

    public static boolean getAccountKitFacebookAppEventsEnabled() {
        return AccountKitController.getAccountKitFacebookAppEventsEnabled();
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static void setExecutor(@NonNull Executor executor) {
        synchronized (LOCK) {
            executor = executor;
        }
    }

    @Nullable
    public static AccountKitLoginResult loginResultWithIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
        if (parcelableExtra instanceof AccountKitLoginResult) {
            return (AccountKitLoginResult) parcelableExtra;
        }
        return null;
    }
}

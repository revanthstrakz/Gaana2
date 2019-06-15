package com.facebook.accountkit.ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;
import com.facebook.accountkit.ui.AccountKitActivity.TitleType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public final class AccountKitConfiguration implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public AccountKitConfiguration createFromParcel(Parcel parcel) {
            return new AccountKitConfiguration(parcel, null);
        }

        public AccountKitConfiguration[] newArray(int i) {
            return new AccountKitConfiguration[i];
        }
    };
    static final String TAG = "AccountKitConfiguration";
    private final String defaultCountryCode;
    private final String initialAuthState;
    private final String initialEmail;
    private final PhoneNumber initialPhoneNumber;
    private final LoginType loginType;
    private final LinkedHashSet<NotificationChannel> notificationChannels;
    private final boolean readPhoneStateEnabled;
    private final boolean receiveSMSEnabled;
    private final ResponseType responseType;
    private final String[] smsBlacklist;
    private final String[] smsWhitelist;
    @NonNull
    private final UIManager uiManager;

    public static class AccountKitConfigurationBuilder {
        private String defaultCountryCode;
        private String initialAuthState;
        private String initialEmail;
        private PhoneNumber initialPhoneNumber;
        private LoginType loginType;
        private final LinkedHashSet<NotificationChannel> notificationChannels = new LinkedHashSet(NotificationChannel.values().length);
        private boolean readPhoneStateEnabled = true;
        private boolean receiveSMSEnabled = true;
        private ResponseType responseType;
        private String[] smsBlacklist;
        private String[] smsWhitelist;
        @Deprecated
        private int theme = -1;
        private UIManagerStub uiManager;

        public AccountKitConfigurationBuilder setTitleType(@Nullable TitleType titleType) {
            return this;
        }

        public AccountKitConfigurationBuilder(LoginType loginType, ResponseType responseType) {
            this.notificationChannels.add(NotificationChannel.FACEBOOK);
            this.notificationChannels.add(NotificationChannel.VOICE_CALLBACK);
            this.loginType = loginType;
            this.responseType = responseType;
        }

        public AccountKitConfigurationBuilder setAdvancedUIManager(@Nullable AdvancedUIManager advancedUIManager) {
            this.uiManager = advancedUIManager;
            this.theme = -1;
            return this;
        }

        public AccountKitConfigurationBuilder setUIManager(@Nullable UIManager uIManager) {
            this.uiManager = uIManager;
            this.theme = -1;
            return this;
        }

        public AccountKitConfigurationBuilder setDefaultCountryCode(@Nullable String str) {
            this.defaultCountryCode = str;
            return this;
        }

        public AccountKitConfigurationBuilder setFacebookNotificationsEnabled(boolean z) {
            if (!z) {
                this.notificationChannels.remove(NotificationChannel.FACEBOOK);
            } else if (!this.notificationChannels.contains(NotificationChannel.FACEBOOK)) {
                this.notificationChannels.add(NotificationChannel.FACEBOOK);
            }
            return this;
        }

        public AccountKitConfigurationBuilder setVoiceCallbackNotificationsEnabled(boolean z) {
            if (!z) {
                this.notificationChannels.remove(NotificationChannel.VOICE_CALLBACK);
            } else if (!this.notificationChannels.contains(NotificationChannel.VOICE_CALLBACK)) {
                this.notificationChannels.add(NotificationChannel.VOICE_CALLBACK);
            }
            return this;
        }

        public AccountKitConfigurationBuilder setInitialAuthState(@Nullable String str) {
            this.initialAuthState = str;
            return this;
        }

        public AccountKitConfigurationBuilder setInitialEmail(@Nullable String str) {
            this.initialEmail = str;
            return this;
        }

        public AccountKitConfigurationBuilder setInitialPhoneNumber(@Nullable PhoneNumber phoneNumber) {
            this.initialPhoneNumber = phoneNumber;
            return this;
        }

        public AccountKitConfigurationBuilder setReadPhoneStateEnabled(boolean z) {
            this.readPhoneStateEnabled = z;
            return this;
        }

        public AccountKitConfigurationBuilder setReceiveSMS(boolean z) {
            this.receiveSMSEnabled = z;
            return this;
        }

        public AccountKitConfigurationBuilder setSMSBlacklist(@Nullable String[] strArr) {
            this.smsBlacklist = strArr;
            return this;
        }

        public AccountKitConfigurationBuilder setSMSWhitelist(@Nullable String[] strArr) {
            this.smsWhitelist = strArr;
            return this;
        }

        public AccountKitConfigurationBuilder setTheme(int i) {
            this.theme = i;
            return this;
        }

        public AccountKitConfiguration build() {
            if (this.uiManager == null) {
                this.uiManager = new ThemeUIManager(this.theme);
            } else if (this.theme != -1 && (this.uiManager instanceof SkinManager)) {
                ((UIManager) this.uiManager).setThemeId(this.theme);
            }
            if (this.uiManager instanceof AdvancedUIManager) {
                this.uiManager = new AdvancedUIManagerWrapper((AdvancedUIManager) this.uiManager, this.theme);
            }
            return new AccountKitConfiguration((UIManager) this.uiManager, this.defaultCountryCode, this.notificationChannels, this.initialAuthState, this.initialEmail, this.initialPhoneNumber, this.loginType, this.readPhoneStateEnabled, this.receiveSMSEnabled, this.responseType, this.smsBlacklist, this.smsWhitelist, null);
        }
    }

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public TitleType getTitleType() {
        return null;
    }

    /* synthetic */ AccountKitConfiguration(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    /* synthetic */ AccountKitConfiguration(UIManager uIManager, String str, LinkedHashSet linkedHashSet, String str2, String str3, PhoneNumber phoneNumber, LoginType loginType, boolean z, boolean z2, ResponseType responseType, String[] strArr, String[] strArr2, AnonymousClass1 anonymousClass1) {
        this(uIManager, str, linkedHashSet, str2, str3, phoneNumber, loginType, z, z2, responseType, strArr, strArr2);
    }

    private AccountKitConfiguration(@NonNull UIManager uIManager, String str, LinkedHashSet<NotificationChannel> linkedHashSet, String str2, String str3, PhoneNumber phoneNumber, LoginType loginType, boolean z, boolean z2, ResponseType responseType, String[] strArr, String[] strArr2) {
        this.notificationChannels = new LinkedHashSet(NotificationChannel.values().length);
        this.initialAuthState = str2;
        this.defaultCountryCode = str;
        this.initialEmail = str3;
        this.notificationChannels.addAll(linkedHashSet);
        this.uiManager = uIManager;
        this.loginType = loginType;
        this.initialPhoneNumber = phoneNumber;
        this.readPhoneStateEnabled = z;
        this.receiveSMSEnabled = z2;
        this.responseType = responseType;
        this.smsBlacklist = strArr;
        this.smsWhitelist = strArr2;
    }

    @Nullable
    @Deprecated
    public AdvancedUIManager getAdvancedUIManager() {
        return this.uiManager instanceof AdvancedUIManagerWrapper ? ((AdvancedUIManagerWrapper) this.uiManager).getAdvancedUIManager() : null;
    }

    @NonNull
    public UIManager getUIManager() {
        return this.uiManager;
    }

    public String getDefaultCountryCode() {
        return this.defaultCountryCode;
    }

    public List<NotificationChannel> getNotificationChannels() {
        return Collections.unmodifiableList(new ArrayList(this.notificationChannels));
    }

    public boolean areFacebookNotificationsEnabled() {
        return getNotificationChannels().contains(NotificationChannel.FACEBOOK);
    }

    public String getInitialAuthState() {
        return this.initialAuthState;
    }

    public String getInitialEmail() {
        return this.initialEmail;
    }

    public PhoneNumber getInitialPhoneNumber() {
        return this.initialPhoneNumber;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public boolean isReadPhoneStateEnabled() {
        return this.readPhoneStateEnabled;
    }

    public boolean isReceiveSMSEnabled() {
        return this.receiveSMSEnabled;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }

    public String[] getSmsBlacklist() {
        return this.smsBlacklist;
    }

    public String[] getSmsWhitelist() {
        return this.smsWhitelist;
    }

    @Deprecated
    public int getTheme() {
        return this.uiManager.getThemeId();
    }

    private AccountKitConfiguration(Parcel parcel) {
        this.notificationChannels = new LinkedHashSet(NotificationChannel.values().length);
        this.uiManager = (UIManager) parcel.readParcelable(UIManager.class.getClassLoader());
        this.defaultCountryCode = parcel.readString();
        this.notificationChannels.clear();
        for (int i : parcel.createIntArray()) {
            this.notificationChannels.add(NotificationChannel.values()[i]);
        }
        this.initialAuthState = parcel.readString();
        this.initialEmail = parcel.readString();
        this.initialPhoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        this.loginType = LoginType.valueOf(parcel.readString());
        boolean z = true;
        this.readPhoneStateEnabled = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.receiveSMSEnabled = z;
        this.responseType = ResponseType.valueOf(parcel.readString());
        this.smsBlacklist = parcel.createStringArray();
        this.smsWhitelist = parcel.createStringArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.uiManager, i);
        parcel.writeString(this.defaultCountryCode);
        NotificationChannel[] notificationChannelArr = new NotificationChannel[this.notificationChannels.size()];
        this.notificationChannels.toArray(notificationChannelArr);
        int[] iArr = new int[notificationChannelArr.length];
        for (int i2 = 0; i2 < notificationChannelArr.length; i2++) {
            iArr[i2] = notificationChannelArr[i2].ordinal();
        }
        parcel.writeIntArray(iArr);
        parcel.writeString(this.initialAuthState);
        parcel.writeString(this.initialEmail);
        parcel.writeParcelable(this.initialPhoneNumber, i);
        parcel.writeString(this.loginType.name());
        parcel.writeByte((byte) this.readPhoneStateEnabled);
        parcel.writeByte((byte) this.receiveSMSEnabled);
        parcel.writeString(this.responseType.name());
        parcel.writeStringArray(this.smsBlacklist);
        parcel.writeStringArray(this.smsWhitelist);
    }
}

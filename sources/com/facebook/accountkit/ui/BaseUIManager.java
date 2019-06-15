package com.facebook.accountkit.ui;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.ui.UIManager.UIManagerListener;

public class BaseUIManager implements Parcelable, UIManager {
    public static final Creator<BaseUIManager> CREATOR = new Creator<BaseUIManager>() {
        public BaseUIManager createFromParcel(Parcel parcel) {
            return new BaseUIManager(parcel);
        }

        public BaseUIManager[] newArray(int i) {
            return new BaseUIManager[i];
        }
    };
    public static final int THEME_ID_NOT_SET = -1;
    private Fragment bodyFragment;
    private LoginFlowState flowState;
    private Fragment footerFragment;
    private Fragment headerFragment;
    protected UIManagerListener listener;
    @StyleRes
    private int themeId;

    public int describeContents() {
        return 0;
    }

    public void onError(AccountKitError accountKitError) {
    }

    public BaseUIManager(@StyleRes int i) {
        this.themeId = i;
        this.flowState = LoginFlowState.NONE;
    }

    protected BaseUIManager(Parcel parcel) {
        this.themeId = parcel.readInt();
        this.flowState = LoginFlowState.values()[parcel.readInt()];
    }

    public int getThemeId() {
        return this.themeId;
    }

    public void setThemeId(@StyleRes int i) {
        this.themeId = i;
    }

    /* Access modifiers changed, original: protected */
    public void updateFlowState(LoginFlowState loginFlowState) {
        if (this.flowState != loginFlowState) {
            this.flowState = loginFlowState;
            this.headerFragment = null;
            this.bodyFragment = null;
            this.footerFragment = null;
        }
    }

    @Nullable
    public Fragment getBodyFragment(LoginFlowState loginFlowState) {
        updateFlowState(loginFlowState);
        if (this.bodyFragment != null) {
            return this.bodyFragment;
        }
        this.bodyFragment = getDefaultBodyFragment(this, this.flowState);
        return this.bodyFragment;
    }

    @NonNull
    static Fragment getDefaultBodyFragment(UIManager uIManager, LoginFlowState loginFlowState) {
        switch (loginFlowState) {
            case ACCOUNT_VERIFIED:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_sent_code_center);
            case CONFIRM_ACCOUNT_VERIFIED:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState);
            case CODE_INPUT:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_confirmation_code_center);
            case EMAIL_INPUT:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_email_login_center);
            case EMAIL_VERIFY:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_email_verify_center);
            case ERROR:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_error_center);
            case PHONE_NUMBER_INPUT:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_phone_login_center);
            case SENDING_CODE:
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_sending_code_center);
            case SENT_CODE:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_sent_code_center);
            case VERIFIED:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_verified_code_center);
            case VERIFYING_CODE:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState, R.layout.com_accountkit_fragment_verifying_code_center);
            default:
                return StaticContentFragmentFactory.create(uIManager, loginFlowState);
        }
    }

    @Nullable
    public ButtonType getButtonType(LoginFlowState loginFlowState) {
        updateFlowState(loginFlowState);
        return null;
    }

    @Nullable
    public Fragment getFooterFragment(LoginFlowState loginFlowState) {
        updateFlowState(loginFlowState);
        if (this.footerFragment != null) {
            return this.footerFragment;
        }
        this.footerFragment = getDefaultFooterFragment(this);
        return this.footerFragment;
    }

    @NonNull
    static Fragment getDefaultFooterFragment(UIManager uIManager) {
        return TitleFragmentFactory.create(uIManager);
    }

    @Nullable
    public Fragment getHeaderFragment(LoginFlowState loginFlowState) {
        updateFlowState(loginFlowState);
        return this.headerFragment;
    }

    @NonNull
    static Fragment getDefaultHeaderFragment(UIManager uIManager, LoginFlowState loginFlowState, LoginType loginType) {
        int i;
        switch (loginFlowState) {
            case ACCOUNT_VERIFIED:
                i = R.string.com_accountkit_account_verified;
                break;
            case CONFIRM_ACCOUNT_VERIFIED:
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                i = R.string.com_accountkit_logging_in;
                break;
            case CODE_INPUT:
                i = R.string.com_accountkit_confirmation_code_title;
                break;
            case EMAIL_INPUT:
                i = R.string.com_accountkit_email_login_title;
                break;
            case EMAIL_VERIFY:
                i = R.string.com_accountkit_email_verify_title;
                break;
            case ERROR:
                if (AnonymousClass2.$SwitchMap$com$facebook$accountkit$ui$LoginType[loginType.ordinal()] == 1) {
                    i = R.string.com_accountkit_phone_error_title;
                    break;
                }
                i = R.string.com_accountkit_error_title;
                break;
            case PHONE_NUMBER_INPUT:
                i = R.string.com_accountkit_phone_login_title;
                break;
            case SENDING_CODE:
                switch (loginType) {
                    case PHONE:
                        i = R.string.com_accountkit_phone_loading_title;
                        break;
                    case EMAIL:
                        i = R.string.com_accountkit_email_loading_title;
                        break;
                    default:
                        throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_STATE);
                }
            case SENT_CODE:
                i = R.string.com_accountkit_sent_title;
                break;
            case VERIFIED:
                i = R.string.com_accountkit_success_title;
                break;
            case VERIFYING_CODE:
                i = R.string.com_accountkit_verify_title;
                break;
            case RESEND:
                i = R.string.com_accountkit_resend_title;
                break;
            default:
                i = -1;
                break;
        }
        if (i > -1) {
            return TitleFragmentFactory.create(uIManager, i, new String[0]);
        }
        return TitleFragmentFactory.create(uIManager);
    }

    @Nullable
    public TextPosition getTextPosition(LoginFlowState loginFlowState) {
        updateFlowState(loginFlowState);
        return TextPosition.BELOW_BODY;
    }

    public void setUIManagerListener(UIManagerListener uIManagerListener) {
        this.listener = uIManagerListener;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.themeId);
        parcel.writeInt(this.flowState.ordinal());
    }
}

package com.facebook.accountkit.ui;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.ui.UIManager.UIManagerListener;

@Deprecated
public class AdvancedUIManagerWrapper extends BaseUIManager {
    public static final Creator<AdvancedUIManagerWrapper> CREATOR = new Creator<AdvancedUIManagerWrapper>() {
        public AdvancedUIManagerWrapper createFromParcel(Parcel parcel) {
            return new AdvancedUIManagerWrapper(parcel);
        }

        public AdvancedUIManagerWrapper[] newArray(int i) {
            return new AdvancedUIManagerWrapper[i];
        }
    };
    private final AdvancedUIManager advancedUIManager;

    public int describeContents() {
        return 0;
    }

    public AdvancedUIManagerWrapper(AdvancedUIManager advancedUIManager, @StyleRes int i) {
        super(i);
        this.advancedUIManager = advancedUIManager;
    }

    public AdvancedUIManagerWrapper(Parcel parcel) {
        super(parcel);
        this.advancedUIManager = (AdvancedUIManager) parcel.readParcelable(getClass().getClassLoader());
    }

    @Deprecated
    public AdvancedUIManager getAdvancedUIManager() {
        return this.advancedUIManager;
    }

    @Nullable
    public Fragment getBodyFragment(LoginFlowState loginFlowState) {
        Fragment bodyFragment = this.advancedUIManager.getBodyFragment(loginFlowState);
        return bodyFragment == null ? super.getBodyFragment(loginFlowState) : bodyFragment;
    }

    @Nullable
    public ButtonType getButtonType(LoginFlowState loginFlowState) {
        return this.advancedUIManager.getButtonType(loginFlowState);
    }

    @Nullable
    public Fragment getFooterFragment(LoginFlowState loginFlowState) {
        Fragment footerFragment = this.advancedUIManager.getFooterFragment(loginFlowState);
        return footerFragment == null ? super.getFooterFragment(loginFlowState) : footerFragment;
    }

    @Nullable
    public Fragment getHeaderFragment(LoginFlowState loginFlowState) {
        Fragment headerFragment = this.advancedUIManager.getHeaderFragment(loginFlowState);
        return headerFragment == null ? super.getHeaderFragment(loginFlowState) : headerFragment;
    }

    @Nullable
    public TextPosition getTextPosition(LoginFlowState loginFlowState) {
        return this.advancedUIManager.getTextPosition(loginFlowState);
    }

    public void setUIManagerListener(UIManagerListener uIManagerListener) {
        throw new RuntimeException("Use setAdvancedUIManagerListener");
    }

    public void onError(AccountKitError accountKitError) {
        this.advancedUIManager.onError(accountKitError);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.advancedUIManager, i);
    }
}

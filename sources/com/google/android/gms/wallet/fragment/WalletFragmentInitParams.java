package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@Class(creator = "WalletFragmentInitParamsCreator")
@Reserved({1})
public final class WalletFragmentInitParams extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<WalletFragmentInitParams> CREATOR = new zzd();
    @Field(getter = "getAccountName", id = 2)
    private String zzcj;
    @Field(getter = "getMaskedWalletRequest", id = 3)
    private MaskedWalletRequest zzfk;
    @Field(getter = "getMaskedWallet", id = 5)
    private MaskedWallet zzfl;
    @Field(defaultValue = "-1", getter = "getMaskedWalletRequestCode", id = 4)
    private int zzfz;

    public final class Builder {
        private Builder() {
        }

        public final Builder setAccountName(String str) {
            WalletFragmentInitParams.this.zzcj = str;
            return this;
        }

        public final Builder setMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            WalletFragmentInitParams.this.zzfk = maskedWalletRequest;
            return this;
        }

        public final Builder setMaskedWalletRequestCode(int i) {
            WalletFragmentInitParams.this.zzfz = i;
            return this;
        }

        public final Builder setMaskedWallet(MaskedWallet maskedWallet) {
            WalletFragmentInitParams.this.zzfl = maskedWallet;
            return this;
        }

        public final WalletFragmentInitParams build() {
            boolean z = false;
            boolean z2 = (WalletFragmentInitParams.this.zzfl != null && WalletFragmentInitParams.this.zzfk == null) || (WalletFragmentInitParams.this.zzfl == null && WalletFragmentInitParams.this.zzfk != null);
            Preconditions.checkState(z2, "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if (WalletFragmentInitParams.this.zzfz >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }

        /* synthetic */ Builder(WalletFragmentInitParams walletFragmentInitParams, zzc zzc) {
            this();
        }
    }

    public static Builder newBuilder() {
        return new Builder(new WalletFragmentInitParams(), null);
    }

    private WalletFragmentInitParams() {
        this.zzfz = -1;
    }

    @Constructor
    WalletFragmentInitParams(@Param(id = 2) String str, @Param(id = 3) MaskedWalletRequest maskedWalletRequest, @Param(id = 4) int i, @Param(id = 5) MaskedWallet maskedWallet) {
        this.zzcj = str;
        this.zzfk = maskedWalletRequest;
        this.zzfz = i;
        this.zzfl = maskedWallet;
    }

    public final String getAccountName() {
        return this.zzcj;
    }

    public final MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzfk;
    }

    public final int getMaskedWalletRequestCode() {
        return this.zzfz;
    }

    public final MaskedWallet getMaskedWallet() {
        return this.zzfl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAccountName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getMaskedWalletRequest(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, getMaskedWalletRequestCode());
        SafeParcelWriter.writeParcelable(parcel, 5, getMaskedWallet(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

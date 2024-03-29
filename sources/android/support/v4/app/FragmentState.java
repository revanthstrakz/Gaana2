package android.support.v4.app;

import android.arch.lifecycle.r;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new Creator<FragmentState>() {
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] newArray(int i) {
            return new FragmentState[i];
        }
    };
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    public int describeContents() {
        return 0;
    }

    FragmentState(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mIndex = fragment.mIndex;
        this.mFromLayout = fragment.mFromLayout;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mDetached = fragment.mDetached;
        this.mArguments = fragment.mArguments;
        this.mHidden = fragment.mHidden;
    }

    FragmentState(Parcel parcel) {
        this.mClassName = parcel.readString();
        this.mIndex = parcel.readInt();
        boolean z = false;
        this.mFromLayout = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        this.mRetainInstance = parcel.readInt() != 0;
        this.mDetached = parcel.readInt() != 0;
        this.mArguments = parcel.readBundle();
        if (parcel.readInt() != 0) {
            z = true;
        }
        this.mHidden = z;
        this.mSavedFragmentState = parcel.readBundle();
    }

    public Fragment instantiate(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment, FragmentManagerNonConfig fragmentManagerNonConfig, r rVar) {
        if (this.mInstance == null) {
            Context context = fragmentHostCallback.getContext();
            if (this.mArguments != null) {
                this.mArguments.setClassLoader(context.getClassLoader());
            }
            if (fragmentContainer != null) {
                this.mInstance = fragmentContainer.instantiate(context, this.mClassName, this.mArguments);
            } else {
                this.mInstance = Fragment.instantiate(context, this.mClassName, this.mArguments);
            }
            if (this.mSavedFragmentState != null) {
                this.mSavedFragmentState.setClassLoader(context.getClassLoader());
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            }
            this.mInstance.setIndex(this.mIndex, fragment);
            this.mInstance.mFromLayout = this.mFromLayout;
            this.mInstance.mRestored = true;
            this.mInstance.mFragmentId = this.mFragmentId;
            this.mInstance.mContainerId = this.mContainerId;
            this.mInstance.mTag = this.mTag;
            this.mInstance.mRetainInstance = this.mRetainInstance;
            this.mInstance.mDetached = this.mDetached;
            this.mInstance.mHidden = this.mHidden;
            this.mInstance.mFragmentManager = fragmentHostCallback.mFragmentManager;
            if (FragmentManagerImpl.DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Instantiated fragment ");
                stringBuilder.append(this.mInstance);
                Log.v("FragmentManager", stringBuilder.toString());
            }
        }
        this.mInstance.mChildNonConfig = fragmentManagerNonConfig;
        this.mInstance.mViewModelStore = rVar;
        return this.mInstance;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClassName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mFromLayout);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.mRetainInstance);
        parcel.writeInt(this.mDetached);
        parcel.writeBundle(this.mArguments);
        parcel.writeInt(this.mHidden);
        parcel.writeBundle(this.mSavedFragmentState);
    }
}

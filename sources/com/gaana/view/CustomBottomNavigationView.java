package com.gaana.view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.util.AttributeSet;
import com.gaana.GaanaActivity;

public class CustomBottomNavigationView extends BottomNavigationView {
    private Context mContext;
    private int selectedTab = GaanaActivity.SHOW_TAB_HOME;

    protected static class BottomNavSavedState extends AbsSavedState {
        public static final Creator<BottomNavSavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<BottomNavSavedState>() {
            public BottomNavSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new BottomNavSavedState(parcel, classLoader);
            }

            public BottomNavSavedState[] newArray(int i) {
                return new BottomNavSavedState[i];
            }
        });
        int selectedPosition = GaanaActivity.SHOW_TAB_HOME;

        public BottomNavSavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.selectedPosition = parcel.readInt();
        }

        public BottomNavSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.selectedPosition);
        }
    }

    public CustomBottomNavigationView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomBottomNavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public CustomBottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void setSelectedPosition(int i) {
        this.selectedTab = i;
    }

    /* Access modifiers changed, original: protected */
    public Parcelable onSaveInstanceState() {
        BottomNavSavedState bottomNavSavedState = new BottomNavSavedState(super.onSaveInstanceState());
        bottomNavSavedState.selectedPosition = this.selectedTab;
        return bottomNavSavedState;
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof BottomNavSavedState) {
            BottomNavSavedState bottomNavSavedState = (BottomNavSavedState) parcelable;
            super.onRestoreInstanceState(bottomNavSavedState.getSuperState());
            ((GaanaActivity) this.mContext).getBottomNavigationBarHelper().a(this, bottomNavSavedState.selectedPosition);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}

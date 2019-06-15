package com.collapsible_header;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ObservableRecyclerView extends RecyclerView implements g {
    private static int recyclerViewLibraryVersion = 22;
    private List<d> mCallbackCollection;
    private d mCallbacks;
    private SparseIntArray mChildrenHeights;
    private boolean mDragging;
    private boolean mFirstScroll;
    private boolean mIntercepted;
    private int mPrevFirstVisibleChildHeight = -1;
    private int mPrevFirstVisiblePosition;
    private MotionEvent mPrevMoveEvent;
    private int mPrevScrollY;
    private int mPrevScrolledChildrenHeight;
    private ScrollState mScrollState;
    private int mScrollY;
    private ViewGroup mTouchInterceptionViewGroup;

    static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public static final SavedState a = new SavedState() {
        };
        int b;
        int c;
        int d;
        int e;
        int f;
        SparseIntArray g;
        Parcelable h;

        public int describeContents() {
            return 0;
        }

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        private SavedState() {
            this.c = -1;
            this.h = null;
        }

        SavedState(Parcelable parcelable) {
            this.c = -1;
            if (parcelable == a) {
                parcelable = null;
            }
            this.h = parcelable;
        }

        private SavedState(Parcel parcel) {
            this.c = -1;
            Parcelable readParcelable = parcel.readParcelable(RecyclerView.class.getClassLoader());
            if (readParcelable == null) {
                readParcelable = a;
            }
            this.h = readParcelable;
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = new SparseIntArray();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (int i = 0; i < readInt; i++) {
                    this.g.put(parcel.readInt(), parcel.readInt());
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.h, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            int i2 = 0;
            i = this.g == null ? 0 : this.g.size();
            parcel.writeInt(i);
            if (i > 0) {
                while (i2 < i) {
                    parcel.writeInt(this.g.keyAt(i2));
                    parcel.writeInt(this.g.valueAt(i2));
                    i2++;
                }
            }
        }

        public Parcelable a() {
            return this.h;
        }
    }

    public ObservableRecyclerView(Context context) {
        super(context);
        init();
    }

    public ObservableRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ObservableRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.mPrevFirstVisiblePosition = savedState.b;
        this.mPrevFirstVisibleChildHeight = savedState.c;
        this.mPrevScrolledChildrenHeight = savedState.d;
        this.mPrevScrollY = savedState.e;
        this.mScrollY = savedState.f;
        this.mChildrenHeights = savedState.g;
        super.onRestoreInstanceState(savedState.a());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = this.mPrevFirstVisiblePosition;
        savedState.c = this.mPrevFirstVisibleChildHeight;
        savedState.d = this.mPrevScrolledChildrenHeight;
        savedState.e = this.mPrevScrollY;
        savedState.f = this.mScrollY;
        savedState.g = this.mChildrenHeights;
        return savedState;
    }

    /* Access modifiers changed, original: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (!hasNoCallbacks() && getChildCount() > 0) {
            i2 = getChildAdapterPosition(getChildAt(0));
            i3 = getChildAdapterPosition(getChildAt(getChildCount() - 1));
            int i5 = 0;
            int i6 = i2;
            while (i6 <= i3) {
                View childAt = getChildAt(i5);
                int height = (childAt == null || (this.mChildrenHeights.indexOfKey(i6) >= 0 && childAt.getHeight() == this.mChildrenHeights.get(i6))) ? 0 : childAt.getHeight();
                this.mChildrenHeights.put(i6, height);
                i6++;
                i5++;
            }
            View childAt2 = getChildAt(0);
            if (childAt2 != null) {
                if (this.mPrevFirstVisiblePosition < i2) {
                    if (i2 - this.mPrevFirstVisiblePosition != 1) {
                        i6 = 0;
                        for (i4 = i2 - 1; i4 > this.mPrevFirstVisiblePosition; i4--) {
                            if (this.mChildrenHeights.indexOfKey(i4) > 0) {
                                i5 = this.mChildrenHeights.get(i4);
                            } else {
                                i5 = childAt2.getHeight();
                            }
                            i6 += i5;
                        }
                    } else {
                        i6 = 0;
                    }
                    this.mPrevScrolledChildrenHeight += this.mPrevFirstVisibleChildHeight + i6;
                    this.mPrevFirstVisibleChildHeight = childAt2.getHeight();
                } else if (i2 < this.mPrevFirstVisiblePosition) {
                    if (this.mPrevFirstVisiblePosition - i2 != 1) {
                        i4 = 0;
                        for (i6 = this.mPrevFirstVisiblePosition - 1; i6 > i2; i6--) {
                            if (this.mChildrenHeights.indexOfKey(i6) > 0) {
                                i5 = this.mChildrenHeights.get(i6);
                            } else {
                                i5 = childAt2.getHeight();
                            }
                            i4 += i5;
                        }
                    } else {
                        i4 = 0;
                    }
                    this.mPrevScrolledChildrenHeight -= childAt2.getHeight() + i4;
                    this.mPrevFirstVisibleChildHeight = childAt2.getHeight();
                } else if (i2 == 0) {
                    this.mPrevFirstVisibleChildHeight = childAt2.getHeight();
                    this.mPrevScrolledChildrenHeight = 0;
                }
                if (this.mPrevFirstVisibleChildHeight < 0) {
                    this.mPrevFirstVisibleChildHeight = 0;
                }
                this.mScrollY = (this.mPrevScrolledChildrenHeight - childAt2.getTop()) + getPaddingTop();
                this.mPrevFirstVisiblePosition = i2;
                dispatchOnScrollChanged(this.mScrollY, this.mFirstScroll, this.mDragging);
                if (this.mFirstScroll) {
                    this.mFirstScroll = false;
                }
                if (this.mPrevScrollY < this.mScrollY) {
                    this.mScrollState = ScrollState.UP;
                } else if (this.mScrollY < this.mPrevScrollY) {
                    this.mScrollState = ScrollState.DOWN;
                } else {
                    this.mScrollState = ScrollState.STOP;
                }
                this.mPrevScrollY = this.mScrollY;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (hasNoCallbacks()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 0) {
            this.mDragging = true;
            this.mFirstScroll = true;
            dispatchOnDownMotionEvent();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (hasNoCallbacks()) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getActionMasked()) {
            case 1:
            case 3:
                this.mIntercepted = false;
                this.mDragging = false;
                dispatchOnUpOrCancelMotionEvent(this.mScrollState);
                break;
            case 2:
                if (this.mPrevMoveEvent == null) {
                    this.mPrevMoveEvent = motionEvent;
                }
                float y = motionEvent.getY() - this.mPrevMoveEvent.getY();
                this.mPrevMoveEvent = MotionEvent.obtainNoHistory(motionEvent);
                if (((float) getCurrentScrollY()) - y <= 0.0f) {
                    if (this.mIntercepted) {
                        return false;
                    }
                    View view;
                    if (this.mTouchInterceptionViewGroup == null) {
                        view = (ViewGroup) getParent();
                    } else {
                        view = this.mTouchInterceptionViewGroup;
                    }
                    float f = 0.0f;
                    float f2 = f;
                    View view2 = this;
                    while (view2 != null && view2 != view) {
                        f += (float) (view2.getLeft() - view2.getScrollX());
                        f2 += (float) (view2.getTop() - view2.getScrollY());
                        view2 = (View) view2.getParent();
                    }
                    final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    obtainNoHistory.offsetLocation(f, f2);
                    if (!view.onInterceptTouchEvent(obtainNoHistory)) {
                        return super.onTouchEvent(motionEvent);
                    }
                    this.mIntercepted = true;
                    obtainNoHistory.setAction(0);
                    post(new Runnable() {
                        public void run() {
                            view.dispatchTouchEvent(obtainNoHistory);
                        }
                    });
                    return false;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setScrollViewCallbacks(d dVar) {
        this.mCallbacks = dVar;
    }

    public void addScrollViewCallbacks(d dVar) {
        if (this.mCallbackCollection == null) {
            this.mCallbackCollection = new ArrayList();
        }
        this.mCallbackCollection.add(dVar);
    }

    public void removeScrollViewCallbacks(d dVar) {
        if (this.mCallbackCollection != null) {
            this.mCallbackCollection.remove(dVar);
        }
    }

    public void clearScrollViewCallbacks() {
        if (this.mCallbackCollection != null) {
            this.mCallbackCollection.clear();
        }
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.mTouchInterceptionViewGroup = viewGroup;
    }

    public void scrollVerticallyTo(int i) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            scrollVerticallyToPosition(i / childAt.getHeight());
        }
    }

    public void scrollVerticallyToPosition(int i) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            scrollToPosition(i);
        } else {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
        }
    }

    public int getCurrentScrollY() {
        return this.mScrollY;
    }

    public int getChildAdapterPosition(View view) {
        if (22 <= recyclerViewLibraryVersion) {
            return super.getChildAdapterPosition(view);
        }
        return getChildPosition(view);
    }

    private void init() {
        this.mChildrenHeights = new SparseIntArray();
        checkLibraryVersion();
    }

    private void checkLibraryVersion() {
        try {
            super.getChildAdapterPosition(null);
        } catch (NoSuchMethodError unused) {
            recyclerViewLibraryVersion = 21;
        }
    }

    private void dispatchOnDownMotionEvent() {
        if (this.mCallbacks != null) {
            this.mCallbacks.onDownMotionEvent();
        }
        if (this.mCallbackCollection != null) {
            for (int i = 0; i < this.mCallbackCollection.size(); i++) {
                ((d) this.mCallbackCollection.get(i)).onDownMotionEvent();
            }
        }
    }

    private void dispatchOnScrollChanged(int i, boolean z, boolean z2) {
        if (this.mCallbacks != null) {
            this.mCallbacks.onScrollChanged(i, z, z2);
        }
        if (this.mCallbackCollection != null) {
            for (int i2 = 0; i2 < this.mCallbackCollection.size(); i2++) {
                ((d) this.mCallbackCollection.get(i2)).onScrollChanged(i, z, z2);
            }
        }
    }

    private void dispatchOnUpOrCancelMotionEvent(ScrollState scrollState) {
        if (this.mCallbacks != null) {
            this.mCallbacks.onUpOrCancelMotionEvent(scrollState);
        }
        if (this.mCallbackCollection != null) {
            for (int i = 0; i < this.mCallbackCollection.size(); i++) {
                ((d) this.mCallbackCollection.get(i)).onUpOrCancelMotionEvent(scrollState);
            }
        }
    }

    private boolean hasNoCallbacks() {
        return this.mCallbacks == null && this.mCallbackCollection == null;
    }
}

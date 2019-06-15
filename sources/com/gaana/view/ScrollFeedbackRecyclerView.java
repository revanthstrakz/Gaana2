package com.gaana.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;

public class ScrollFeedbackRecyclerView extends RecyclerView {
    private WeakReference<Callbacks> mCallbacks;

    public interface Callbacks {
        boolean isAppBarCollapsed();

        void setExpanded(boolean z);
    }

    public ScrollFeedbackRecyclerView(Context context) {
        super(context);
        attachCallbacks(context);
    }

    public ScrollFeedbackRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        attachCallbacks(context);
    }

    public ScrollFeedbackRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onScrolled(int i, int i2) {
        if (((LinearLayoutManager) getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0 && ((Callbacks) this.mCallbacks.get()).isAppBarCollapsed()) {
            ((Callbacks) this.mCallbacks.get()).setExpanded(true);
        }
        super.onScrolled(i, i2);
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            super.setLayoutManager(layoutManager);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(layoutManager.toString());
        stringBuilder.append(" must be of type LinearLayoutManager");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private void attachCallbacks(Context context) {
        try {
            this.mCallbacks = new WeakReference((Callbacks) context);
        } catch (ClassCastException unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.toString());
            stringBuilder.append(" must implement ");
            stringBuilder.append("ScrollFeedbackRecyclerView.Callbacks");
            throw new ClassCastException(stringBuilder.toString());
        }
    }
}

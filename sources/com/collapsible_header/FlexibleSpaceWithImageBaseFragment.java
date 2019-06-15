package com.collapsible_header;

import android.view.View;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;

public abstract class FlexibleSpaceWithImageBaseFragment<S extends g> extends BaseGaanaFragment implements d {
    public abstract void a(int i, View view);

    public final void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public void a(int i, int i2) {
        View view = getView();
        if (view != null) {
            g gVar = (g) view.findViewById(R.id.scroll);
            if (gVar != null) {
                gVar.scrollVerticallyTo(i);
            }
        }
    }

    public void a(int i) {
        a(i, getView());
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (getView() != null) {
            a(i, getView());
        }
    }
}

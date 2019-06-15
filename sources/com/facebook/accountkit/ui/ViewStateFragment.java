package com.facebook.accountkit.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

abstract class ViewStateFragment extends Fragment {
    public static final String TAG = "ViewStateFragment";
    protected static final String UI_MANAGER_KEY;
    private static final String VIEW_STATE_KEY;
    private final Bundle viewState = new Bundle();

    /* Access modifiers changed, original: protected */
    public void onViewReadyWithState(View view, Bundle bundle) {
    }

    ViewStateFragment() {
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".VIEW_STATE_KEY");
        VIEW_STATE_KEY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(TAG);
        stringBuilder.append(".UI_MANAGER_KEY");
        UI_MANAGER_KEY = stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public UIManager getUIManager() {
        return (UIManager) this.viewState.get(UI_MANAGER_KEY);
    }

    /* Access modifiers changed, original: protected */
    public Bundle getViewState() {
        return this.viewState;
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.viewState.putAll(bundle.getBundle(VIEW_STATE_KEY));
        }
        if (this.viewState.containsKey(UI_MANAGER_KEY)) {
            super.onCreate(bundle);
            setRetainInstance(true);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You must supply a UIManager to ");
        stringBuilder.append(TAG);
        throw new RuntimeException(stringBuilder.toString());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        if (view != null) {
            onViewReadyWithState(view, this.viewState);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBundle(VIEW_STATE_KEY, this.viewState);
        super.onSaveInstanceState(bundle);
    }
}

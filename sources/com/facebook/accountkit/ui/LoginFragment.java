package com.facebook.accountkit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract class LoginFragment extends ViewStateFragment {
    public abstract View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    LoginFragment() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (onCreateView == null) {
            onCreateView = createView(layoutInflater, viewGroup, bundle);
        }
        ViewUtility.applyThemeAttributes(getActivity(), getUIManager(), onCreateView);
        return onCreateView;
    }
}

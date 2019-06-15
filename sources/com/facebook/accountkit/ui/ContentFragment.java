package com.facebook.accountkit.ui;

abstract class ContentFragment extends LoginFragment {
    public abstract LoginFlowState getLoginFlowState();

    public abstract boolean isKeyboardFragment();

    ContentFragment() {
    }
}

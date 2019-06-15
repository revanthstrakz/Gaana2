package com.facebook.accountkit.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.accountkit.R;

final class StaticContentFragmentFactory {
    private static final String LAYOUT_RESOURCE_ID_KEY = "layoutResourceId";
    private static final String LOGIN_FLOW_STATE_KEY = "loginFlowState";

    public static final class StaticContentFragment extends ContentFragment {
        /* Access modifiers changed, original: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
        }

        public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }

        public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(getViewState().getInt(StaticContentFragmentFactory.LAYOUT_RESOURCE_ID_KEY, R.layout.com_accountkit_fragment_static_content), viewGroup, false);
        }

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return LoginFlowState.valueOf(getViewState().getString(StaticContentFragmentFactory.LOGIN_FLOW_STATE_KEY, LoginFlowState.NONE.name()));
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            view = view.findViewById(R.id.com_accountkit_icon_view);
            if (view != null) {
                int color;
                if (ViewUtility.useLegacy(getUIManager())) {
                    color = ViewUtility.getColor(getActivity(), R.attr.com_accountkit_icon_color, -1);
                } else {
                    color = ViewUtility.getPrimaryColor(getActivity(), getUIManager());
                }
                if (view instanceof ImageView) {
                    ViewUtility.applyThemeColor(getActivity(), (ImageView) view, color);
                } else {
                    ViewUtility.applyThemeColor(getActivity(), view.getBackground(), color);
                }
            }
        }
    }

    StaticContentFragmentFactory() {
    }

    static StaticContentFragment create(@NonNull UIManager uIManager, LoginFlowState loginFlowState, int i) {
        StaticContentFragment create = create(uIManager, loginFlowState);
        create.getViewState().putInt(LAYOUT_RESOURCE_ID_KEY, i);
        return create;
    }

    static StaticContentFragment create(@NonNull UIManager uIManager, LoginFlowState loginFlowState) {
        StaticContentFragment staticContentFragment = new StaticContentFragment();
        Bundle viewState = staticContentFragment.getViewState();
        viewState.putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
        viewState.putString(LOGIN_FLOW_STATE_KEY, loginFlowState.name());
        return staticContentFragment;
    }
}

package com.facebook.accountkit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class ErrorContentController extends ContentControllerBase {
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.ERROR;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private final LoginFlowState returnState;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    public static final class BottomFragment extends ContentFragment {
        private static final String RETURN_LOGIN_FLOW_STATE;

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

        static {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(TAG);
            stringBuilder.append(".RETURN_LOGIN_FLOW_STATE");
            RETURN_LOGIN_FLOW_STATE = stringBuilder.toString();
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_error_bottom, viewGroup, false);
        }

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return ErrorContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, final Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            view = view.findViewById(R.id.com_accountkit_start_over_button);
            if (view != null) {
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Logger.logUIErrorInteraction(Buttons.TRY_AGAIN.name());
                        LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.ERROR_RESTART).putExtra(LoginFlowBroadcastReceiver.EXTRA_RETURN_LOGIN_FLOW_STATE, (Integer) bundle.get(BottomFragment.RETURN_LOGIN_FLOW_STATE)));
                    }
                });
            }
        }
    }

    @Nullable
    public View getFocusView() {
        return null;
    }

    ErrorContentController(LoginFlowState loginFlowState, AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
        this.returnState = loginFlowState;
    }

    /* Access modifiers changed, original: 0000 */
    public LoginFlowState getReturnState() {
        return this.returnState;
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(new BottomFragment());
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof BottomFragment) {
            this.bottomFragment = (BottomFragment) contentFragment;
            this.bottomFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.bottomFragment.getViewState().putInt(BottomFragment.RETURN_LOGIN_FLOW_STATE, this.returnState.ordinal());
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_error_center));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.centerFragment = (StaticContentFragment) contentFragment;
        }
    }

    public TitleFragment getFooterFragment() {
        if (this.footerFragment == null) {
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(@Nullable TitleFragment titleFragment) {
        this.footerFragment = titleFragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_error_title, new String[0]);
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(@Nullable TitleFragment titleFragment) {
        this.headerFragment = titleFragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LOGIN_FLOW_STATE;
    }

    public ContentFragment getTextFragment() {
        if (this.textFragment == null) {
            this.textFragment = StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState());
        }
        return this.textFragment;
    }

    public void setTextFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.textFragment = (StaticContentFragment) contentFragment;
        }
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.topFragment = (StaticContentFragment) contentFragment;
        }
    }

    public void setErrorMessage(@Nullable String str) {
        if (this.headerFragment != null) {
            this.headerFragment.setTitle(str);
        }
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        Logger.logUIError(true, this.configuration.getLoginType());
    }
}

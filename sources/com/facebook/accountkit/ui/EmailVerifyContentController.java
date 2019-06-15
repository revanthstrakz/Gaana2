package com.facebook.accountkit.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class EmailVerifyContentController extends ContentControllerBase {
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.EMAIL_VERIFY;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    public static final class BottomFragment extends ContentFragment {
        private OnCompleteListener onCompleteListener;

        interface OnCompleteListener {
            void onRetry(Context context);
        }

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
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_email_verify_bottom, viewGroup, false);
        }

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailVerifyContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            View findViewById = view.findViewById(R.id.com_accountkit_retry_email_button);
            if (findViewById != null) {
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Logger.logUIEmailVerifyInteraction(Buttons.SEND_NEW_EMAIL.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onRetry(view.getContext());
                        }
                    }
                });
            }
            Button button = (Button) view.findViewById(R.id.com_accountkit_check_email_button);
            if (button != null) {
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.APP_EMAIL");
                        intent.addFlags(1073741824);
                        Logger.logUIEmailVerifyInteraction(Buttons.OPEN_EMAIL.name());
                        try {
                            BottomFragment.this.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                        }
                    }
                });
            }
        }

        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }
    }

    @Nullable
    public View getFocusView() {
        return null;
    }

    EmailVerifyContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
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
            this.bottomFragment.setOnCompleteListener(new OnCompleteListener() {
                public void onRetry(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.EMAIL_VERIFY_RETRY));
                }
            });
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_email_verify_center));
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
            this.footerFragment = new TitleFragment();
        }
        return this.footerFragment;
    }

    public void setFooterFragment(@Nullable TitleFragment titleFragment) {
        this.footerFragment = titleFragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_email_verify_title, new String[0]);
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

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        Logger.logUIEmailVerify(true);
    }
}

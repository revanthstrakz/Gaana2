package com.facebook.accountkit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

public class AccountVerifiedContentController extends ContentControllerBase {
    private static final int COMPLETION_UI_DURATION_MS = 2000;
    private StaticContentFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private Handler delayedTransitionHandler;
    private Runnable delayedTransitionRunnable;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    @Nullable
    public View getFocusView() {
        return null;
    }

    AccountVerifiedContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public void onResume(final Activity activity) {
        super.onResume(activity);
        cancelTransition();
        this.delayedTransitionHandler = new Handler();
        this.delayedTransitionRunnable = new Runnable() {
            public void run() {
                LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.ACCOUNT_VERIFIED_COMPLETE));
                AccountVerifiedContentController.this.delayedTransitionHandler = null;
                AccountVerifiedContentController.this.delayedTransitionRunnable = null;
            }
        };
        this.delayedTransitionHandler.postDelayed(this.delayedTransitionRunnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        Logger.logUIAccountVerified(true);
    }

    public void onPause(Activity activity) {
        cancelTransition();
        super.onPause(activity);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.bottomFragment = (StaticContentFragment) contentFragment;
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_sent_code_center));
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_account_verified, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(@Nullable TitleFragment titleFragment) {
        this.headerFragment = titleFragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.ACCOUNT_VERIFIED;
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

    private void cancelTransition() {
        if (this.delayedTransitionHandler != null && this.delayedTransitionRunnable != null) {
            this.delayedTransitionHandler.removeCallbacks(this.delayedTransitionRunnable);
            this.delayedTransitionRunnable = null;
            this.delayedTransitionHandler = null;
        }
    }
}

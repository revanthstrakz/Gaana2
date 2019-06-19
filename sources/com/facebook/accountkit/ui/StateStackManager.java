package com.facebook.accountkit.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.ui.AdvancedUIManager.AdvancedUIManagerListener;
import com.facebook.accountkit.ui.SkinManager.Skin;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;
import com.facebook.accountkit.ui.UIManager.UIManagerListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class StateStackManager implements OnBackStackChangedListener, AdvancedUIManagerListener, UIManagerListener {
    private final WeakReference<AccountKitActivity> activityRef;
    private final AccountKitConfiguration configuration;
    private ContentController contentController;
    private final Map<LoginFlowState, ContentController> contentControllerMap = new HashMap();
    private final List<OnPopListener> onPopListeners = new ArrayList();
    private final List<OnPushListener> onPushListeners = new ArrayList();
    private final UIManager uiManager;

    interface OnPopListener {
        void onContentPopped();
    }

    interface OnPushListener {
        void onContentControllerReady(ContentController contentController);

        void onContentPushed();
    }

    private enum FragmentType {
        BODY,
        FOOTER,
        HEADER
    }

    StateStackManager(AccountKitActivity accountKitActivity, AccountKitConfiguration accountKitConfiguration) {
        UIManager uIManager;
        this.activityRef = new WeakReference(accountKitActivity);
        accountKitActivity.getFragmentManager().addOnBackStackChangedListener(this);
        this.configuration = accountKitConfiguration;
        if (accountKitConfiguration == null) {
            uIManager = null;
        } else {
            uIManager = accountKitConfiguration.getUIManager();
        }
        this.uiManager = uIManager;
        if (this.uiManager instanceof AdvancedUIManagerWrapper) {
            ((AdvancedUIManagerWrapper) this.uiManager).getAdvancedUIManager().setAdvancedUIManagerListener(this);
        } else if (this.uiManager != null) {
            this.uiManager.setUIManagerListener(this);
        }
    }

    public void onBack() {
        AccountKitActivity accountKitActivity = (AccountKitActivity) this.activityRef.get();
        if (accountKitActivity != null) {
            accountKitActivity.onBackPressed();
        }
    }

    public void onBackStackChanged() {
        AccountKitActivity accountKitActivity = (AccountKitActivity) this.activityRef.get();
        if (accountKitActivity != null) {
            updateContentController(accountKitActivity);
        }
    }

    public void onCancel() {
        AccountKitActivity accountKitActivity = (AccountKitActivity) this.activityRef.get();
        if (accountKitActivity != null) {
            accountKitActivity.sendCancelResult();
        }
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public ContentController getContentController() {
        return this.contentController;
    }

    /* Access modifiers changed, original: 0000 */
    public void popBackStack(LoginFlowState loginFlowState, @Nullable OnPopListener onPopListener) {
        AccountKitActivity accountKitActivity = (AccountKitActivity) this.activityRef.get();
        if (accountKitActivity != null) {
            if (onPopListener != null) {
                this.onPopListeners.add(onPopListener);
            }
            ContentController ensureContentController = ensureContentController(accountKitActivity, loginFlowState, LoginFlowState.NONE, false);
            accountKitActivity.getFragmentManager().popBackStack();
            ensureNextButton(accountKitActivity, ensureContentController);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void multiPopBackStack(OnPopListener onPopListener) {
        AccountKitActivity accountKitActivity = (AccountKitActivity) this.activityRef.get();
        if (accountKitActivity != null) {
            if (onPopListener != null) {
                this.onPopListeners.add(onPopListener);
            }
            accountKitActivity.getFragmentManager().popBackStack();
            ensureNextButton(accountKitActivity, null);
        }
    }

    private void ensureNextButton(AccountKitActivity accountKitActivity, ContentController contentController) {
        if (ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
            FragmentManager fragmentManager = accountKitActivity.getFragmentManager();
            if (contentController == null) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                if (remove(fragmentManager, beginTransaction, R.id.com_accountkit_content_bottom_fragment) == null) {
                    remove(fragmentManager, beginTransaction, R.id.com_accountkit_content_bottom_keyboard_fragment);
                }
                beginTransaction.commit();
                return;
            }
            ContentFragment bottomFragment = contentController.getBottomFragment();
            FragmentTransaction beginTransaction2 = fragmentManager.beginTransaction();
            if (bottomFragment.isKeyboardFragment()) {
                remove(fragmentManager, beginTransaction2, R.id.com_accountkit_content_bottom_fragment);
                replace(fragmentManager, beginTransaction2, R.id.com_accountkit_content_bottom_keyboard_fragment, bottomFragment);
            } else {
                remove(fragmentManager, beginTransaction2, R.id.com_accountkit_content_bottom_keyboard_fragment);
                replace(fragmentManager, beginTransaction2, R.id.com_accountkit_content_bottom_fragment, bottomFragment);
            }
            beginTransaction2.commit();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void pushError(AccountKitActivity accountKitActivity, LoginFlowManager loginFlowManager, LoginFlowState loginFlowState, AccountKitError accountKitError, @Nullable OnPushListener onPushListener) {
        this.uiManager.onError(accountKitError);
        pushState(accountKitActivity, loginFlowManager, loginFlowState, onPushListener);
    }

    /* Access modifiers changed, original: 0000 */
    public OnPushListener getErrorOnPushListener(@Nullable final String str) {
        return new OnPushListener() {
            public void onContentPushed() {
            }

            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ErrorContentController) {
                    ((ErrorContentController) contentController).setErrorMessage(str);
                }
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public void pushState(AccountKitActivity accountKitActivity, LoginFlowManager loginFlowManager, @Nullable OnPushListener onPushListener) {
        pushState(accountKitActivity, loginFlowManager, LoginFlowState.NONE, onPushListener);
    }

    private void pushState(AccountKitActivity accountKitActivity, LoginFlowManager loginFlowManager, LoginFlowState loginFlowState, @Nullable OnPushListener onPushListener) {
        LoginFlowState flowState = loginFlowManager.getFlowState();
        ContentController contentController = getContentController();
        int i = 0;
        ContentController ensureContentController = ensureContentController(accountKitActivity, flowState, loginFlowState, false);
        if (ensureContentController != null && contentController != ensureContentController) {
            Fragment headerFragment;
            Logger.logUIManager(this.uiManager);
            boolean z = true;
            if ((flowState == LoginFlowState.RESEND && (ensureContentController instanceof ResendContentController)) || ((flowState == LoginFlowState.CODE_INPUT && (ensureContentController instanceof ConfirmationCodeContentController)) || (ensureContentController instanceof ErrorContentController))) {
                headerFragment = ensureContentController.getHeaderFragment();
            } else {
                headerFragment = this.uiManager.getHeaderFragment(flowState);
                Logger.logUICustomFragment(this.configuration.getLoginType(), FragmentType.HEADER.name(), headerFragment != null);
            }
            Fragment bodyFragment = this.uiManager.getBodyFragment(flowState);
            Logger.logUICustomFragment(this.configuration.getLoginType(), FragmentType.BODY.name(), bodyFragment != null);
            Fragment footerFragment = this.uiManager.getFooterFragment(flowState);
            LoginType loginType = this.configuration.getLoginType();
            String name = FragmentType.FOOTER.name();
            if (footerFragment == null) {
                z = false;
            }
            Logger.logUICustomFragment(loginType, name, z);
            if (headerFragment == null) {
                headerFragment = BaseUIManager.getDefaultHeaderFragment(this.uiManager, flowState, loginFlowManager.getLoginType());
            }
            if (bodyFragment == null) {
                bodyFragment = BaseUIManager.getDefaultBodyFragment(this.uiManager, flowState);
            }
            if (footerFragment == null) {
                footerFragment = BaseUIManager.getDefaultFooterFragment(this.uiManager);
            }
            TextPosition textPosition = this.uiManager.getTextPosition(flowState);
            if (ensureContentController instanceof ButtonContentController) {
                ButtonType buttonType = this.uiManager.getButtonType(flowState);
                if (buttonType != null) {
                    ((ButtonContentController) ensureContentController).setButtonType(buttonType);
                }
            }
            ContentFragment topFragment = ensureContentController.getTopFragment();
            Fragment textFragment = ensureContentController.getTextFragment();
            ContentFragment bottomFragment = ensureContentController.getBottomFragment();
            if (onPushListener != null) {
                this.onPushListeners.add(onPushListener);
                onPushListener.onContentControllerReady(ensureContentController);
            }
            if (textPosition == null) {
                textPosition = TextPosition.BELOW_BODY;
            }
            if (textFragment != null) {
                int i2;
                int i3;
                switch (textPosition) {
                    case ABOVE_BODY:
                        i2 = R.dimen.com_accountkit_vertical_spacer_small_height;
                        i3 = 0;
                        break;
                    case BELOW_BODY:
                        i3 = R.dimen.com_accountkit_vertical_spacer_small_height;
                        i2 = 0;
                        break;
                    default:
                        i2 = 0;
                        i3 = i2;
                        break;
                }
                i2 = i2 == 0 ? 0 : accountKitActivity.getResources().getDimensionPixelSize(i2);
                if (i3 != 0) {
                    i = accountKitActivity.getResources().getDimensionPixelSize(i3);
                }
                if (textFragment instanceof TextContentFragment) {
                    TextContentFragment textContentFragment = (TextContentFragment) textFragment;
                    textContentFragment.setContentPaddingTop(i2);
                    textContentFragment.setContentPaddingBottom(i);
                }
            }
            FragmentManager fragmentManager = accountKitActivity.getFragmentManager();
            if (contentController != null) {
                accountKitActivity.onContentControllerDismissed(contentController);
                if (contentController.isTransient()) {
                    fragmentManager.popBackStack();
                }
            }
            if (ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
                ensureNextButton(accountKitActivity, ensureContentController);
            }
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            replace(fragmentManager, beginTransaction, R.id.com_accountkit_header_fragment, headerFragment);
            replace(fragmentManager, beginTransaction, R.id.com_accountkit_content_top_fragment, topFragment);
            replace(fragmentManager, beginTransaction, R.id.com_accountkit_content_top_text_fragment, textPosition == TextPosition.ABOVE_BODY ? textFragment : null);
            replace(fragmentManager, beginTransaction, R.id.com_accountkit_content_center_fragment, bodyFragment);
            int i4 = R.id.com_accountkit_content_bottom_text_fragment;
            if (textPosition != TextPosition.BELOW_BODY) {
                textFragment = null;
            }
            replace(fragmentManager, beginTransaction, i4, textFragment);
            if (!ViewUtility.isSkin(this.uiManager, Skin.CONTEMPORARY)) {
                replace(fragmentManager, beginTransaction, R.id.com_accountkit_content_bottom_fragment, bottomFragment);
                replace(fragmentManager, beginTransaction, R.id.com_accountkit_footer_fragment, footerFragment);
            }
            beginTransaction.addToBackStack(null);
            ViewUtility.hideKeyboard(accountKitActivity);
            beginTransaction.commit();
            ensureContentController.onResume(accountKitActivity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateContentController(AccountKitActivity accountKitActivity) {
        ContentFragment contentFragment = getContentFragment(accountKitActivity, R.id.com_accountkit_content_top_fragment);
        if (contentFragment != null) {
            ContentController ensureContentController = ensureContentController(accountKitActivity, contentFragment.getLoginFlowState(), LoginFlowState.NONE, true);
            if (ensureContentController != null) {
                this.contentController = ensureContentController;
                ArrayList<OnPopListener> arrayList = new ArrayList(this.onPopListeners);
                this.onPopListeners.clear();
                for (OnPopListener onContentPopped : arrayList) {
                    onContentPopped.onContentPopped();
                }
                ArrayList<OnPushListener> arrayList2 = new ArrayList(this.onPushListeners);
                this.onPushListeners.clear();
                for (OnPushListener onContentPushed : arrayList2) {
                    onContentPushed.onContentPushed();
                }
            }
        }
    }

    private static void replace(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, int i, Fragment fragment) {
        if (fragmentManager.findFragmentById(i) != fragment) {
            fragmentTransaction.replace(i, fragment);
        }
    }

    private static Fragment remove(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, int i) {
        Fragment findFragmentById = fragmentManager.findFragmentById(i);
        if (findFragmentById != null) {
            fragmentTransaction.remove(findFragmentById);
        }
        return findFragmentById;
    }

    @Nullable
    private ContentFragment getContentFragment(AccountKitActivity accountKitActivity, int i) {
        Fragment findFragmentById = accountKitActivity.getFragmentManager().findFragmentById(i);
        if (findFragmentById instanceof ContentFragment) {
            return (ContentFragment) findFragmentById;
        }
        return null;
    }

    @Nullable
    private ContentController ensureContentController(AccountKitActivity accountKitActivity, LoginFlowState loginFlowState, LoginFlowState loginFlowState2, boolean z) {
        ContentController contentController = (ContentController) this.contentControllerMap.get(loginFlowState);
        if (contentController != null) {
            return contentController;
        }
        ContentController phoneLoginContentController;
        switch (loginFlowState) {
            case NONE:
                return null;
            case PHONE_NUMBER_INPUT:
                phoneLoginContentController = new PhoneLoginContentController(this.configuration);
                break;
            case SENDING_CODE:
                phoneLoginContentController = new SendingCodeContentController(this.configuration);
                break;
            case SENT_CODE:
                switch (this.configuration.getLoginType()) {
                    case PHONE:
                        phoneLoginContentController = new PhoneSentCodeContentController(this.configuration);
                        break;
                    case EMAIL:
                        phoneLoginContentController = new EmailSentCodeContentController(this.configuration);
                        break;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unexpected login type: ");
                        stringBuilder.append(this.configuration.getLoginType().toString());
                        throw new RuntimeException(stringBuilder.toString());
                }
            case ACCOUNT_VERIFIED:
                phoneLoginContentController = new AccountVerifiedContentController(this.configuration);
                break;
            case CONFIRM_ACCOUNT_VERIFIED:
                phoneLoginContentController = new ConfirmAccountVerifiedContentController(this.configuration);
                break;
            case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                phoneLoginContentController = new VerifyingCodeContentController(this.configuration);
                break;
            case CODE_INPUT:
                phoneLoginContentController = new ConfirmationCodeContentController(this.configuration);
                break;
            case VERIFYING_CODE:
                phoneLoginContentController = new VerifyingCodeContentController(this.configuration);
                break;
            case VERIFIED:
                phoneLoginContentController = new VerifiedCodeContentController(this.configuration);
                break;
            case ERROR:
                phoneLoginContentController = new ErrorContentController(loginFlowState2, this.configuration);
                break;
            case EMAIL_INPUT:
                phoneLoginContentController = new EmailLoginContentController(this.configuration);
                break;
            case EMAIL_VERIFY:
                phoneLoginContentController = new EmailVerifyContentController(this.configuration);
                break;
            case RESEND:
                phoneLoginContentController = new ResendContentController(this.configuration);
                break;
            default:
                return null;
        }
        if (z) {
            Fragment findFragmentById = accountKitActivity.getFragmentManager().findFragmentById(R.id.com_accountkit_header_fragment);
            if (findFragmentById instanceof TitleFragment) {
                phoneLoginContentController.setHeaderFragment((TitleFragment) findFragmentById);
            }
            phoneLoginContentController.setTopFragment(getContentFragment(accountKitActivity, R.id.com_accountkit_content_top_fragment));
            phoneLoginContentController.setCenterFragment(getContentFragment(accountKitActivity, R.id.com_accountkit_content_center_fragment));
            phoneLoginContentController.setBottomFragment(getContentFragment(accountKitActivity, R.id.com_accountkit_content_bottom_fragment));
            findFragmentById = accountKitActivity.getFragmentManager().findFragmentById(R.id.com_accountkit_footer_fragment);
            if (findFragmentById instanceof TitleFragment) {
                phoneLoginContentController.setFooterFragment((TitleFragment) findFragmentById);
            }
            phoneLoginContentController.onResume(accountKitActivity);
        }
        this.contentControllerMap.put(loginFlowState, phoneLoginContentController);
        return phoneLoginContentController;
    }
}

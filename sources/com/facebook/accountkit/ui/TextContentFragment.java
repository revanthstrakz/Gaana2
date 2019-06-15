package com.facebook.accountkit.ui;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;

abstract class TextContentFragment extends ContentFragment {
    private static final String CONTENT_PADDING_BOTTOM_KEY = "contentPaddingBottom";
    private static final String CONTENT_PADDING_TOP_KEY = "contentPaddingTop";
    private NextButtonTextProvider nextButtonTextProvider;
    private TextView textView;

    public interface NextButtonTextProvider {
        String getNextButtonText();
    }

    public abstract Spanned getText(String str);

    TextContentFragment() {
    }

    public int getContentPaddingBottom() {
        return getViewState().getInt(CONTENT_PADDING_BOTTOM_KEY, 0);
    }

    public void setContentPaddingBottom(int i) {
        getViewState().putInt(CONTENT_PADDING_BOTTOM_KEY, i);
        updateContentPadding();
    }

    public int getContentPaddingTop() {
        return getViewState().getInt(CONTENT_PADDING_TOP_KEY, 0);
    }

    public void setContentPaddingTop(int i) {
        getViewState().putInt(CONTENT_PADDING_TOP_KEY, i);
        updateContentPadding();
    }

    /* Access modifiers changed, original: protected */
    public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.com_accountkit_fragment_phone_login_text, viewGroup, false);
    }

    public void setNextButtonTextProvider(NextButtonTextProvider nextButtonTextProvider) {
        this.nextButtonTextProvider = nextButtonTextProvider;
    }

    /* Access modifiers changed, original: protected */
    public void onViewReadyWithState(View view, Bundle bundle) {
        super.onViewReadyWithState(view, bundle);
        this.textView = (TextView) view.findViewById(R.id.com_accountkit_text);
        if (this.textView != null) {
            this.textView.setMovementMethod(new CustomLinkMovement(new OnURLClickedListener() {
                public void onURLClicked(String str) {
                    Logger.logUIPhoneLoginInteraction(Buttons.POLICY_LINKS.name(), str);
                }
            }));
        }
        updateContentPadding();
        updateText();
    }

    public void onStart() {
        super.onStart();
        updateText();
    }

    private void updateContentPadding() {
        if (this.textView != null) {
            this.textView.setPadding(this.textView.getPaddingLeft(), getContentPaddingTop(), this.textView.getPaddingRight(), getContentPaddingBottom());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing block: B:9:0x0020, code skipped:
            return;
     */
    public void updateText() {
        /*
        r2 = this;
        r0 = r2.textView;
        if (r0 == 0) goto L_0x0020;
    L_0x0004:
        r0 = r2.nextButtonTextProvider;
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x0020;
    L_0x0009:
        r0 = r2.getActivity();
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return;
    L_0x0010:
        r0 = r2.textView;
        r1 = r2.nextButtonTextProvider;
        r1 = r1.getNextButtonText();
        r1 = r2.getText(r1);
        r0.setText(r1);
        return;
    L_0x0020:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.TextContentFragment.updateText():void");
    }
}

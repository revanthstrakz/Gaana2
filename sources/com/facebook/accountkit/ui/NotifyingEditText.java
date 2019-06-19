package com.facebook.accountkit.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

public final class NotifyingEditText extends AppCompatEditText {
    private OnKeyListener onSoftKeyListener;
    private PasteListener pasteListener;

    public interface PasteListener {
        void onTextPaste();
    }

    private class NotifyingInputConnection extends InputConnectionWrapper {
        public NotifyingInputConnection(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        public boolean deleteSurroundingText(int i, int i2) {
            if (NotifyingEditText.this.onSoftKeyListener != null) {
                int i3 = 0;
                boolean onKey = NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, 67, new KeyEvent(0, 67));
                if (NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, 67, new KeyEvent(1, 67)) || onKey) {
                    i3 = 1;
                }
                if (i3 != 0) {
                    return true;
                }
            }
            return super.deleteSurroundingText(i, i2);
        }

        public boolean sendKeyEvent(KeyEvent keyEvent) {
            return (NotifyingEditText.this.onSoftKeyListener != null && NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, keyEvent.getKeyCode(), keyEvent)) || super.sendKeyEvent(keyEvent);
        }
    }

    public NotifyingEditText(Context context) {
        super(context);
    }

    public NotifyingEditText(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NotifyingEditText(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSoftKeyListener(OnKeyListener onKeyListener) {
        this.onSoftKeyListener = onKeyListener;
    }

    public boolean onTextContextMenuItem(int i) {
        boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
        if (i == 16908322 && this.pasteListener != null) {
            this.pasteListener.onTextPaste();
        }
        return onTextContextMenuItem;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new NotifyingInputConnection(super.onCreateInputConnection(editorInfo), true);
    }

    public void setPasteListener(PasteListener pasteListener) {
        this.pasteListener = pasteListener;
    }
}

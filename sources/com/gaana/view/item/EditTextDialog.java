package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.gaana.R;
import com.managers.aj;
import com.utilities.Util;

public class EditTextDialog extends Dialog {
    private static final int layoutResID = 2131493072;
    private EditText editText;
    private Context mContext;
    private OnButtonClickListener mOnButtonClickListener;
    private View positiveButton;
    private String title;

    public interface OnButtonClickListener {
        void onNegativeButtonClick();

        void onPositiveButtonClick(String str);
    }

    public EditTextDialog(Context context, String str, String str2, String str3, boolean z, String str4, OnButtonClickListener onButtonClickListener) {
        this(context, R.layout.dialog_edit_text, str, str2, str3, z, str4, onButtonClickListener);
    }

    public EditTextDialog(Context context, @LayoutRes int i, String str, String str2, String str3, final boolean z, final String str4, OnButtonClickListener onButtonClickListener) {
        super(context, R.style.voice_recog_dialog_theme);
        this.mContext = null;
        this.positiveButton = null;
        this.title = null;
        this.editText = null;
        this.mContext = context;
        this.title = str;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(i);
        this.positiveButton = findViewById(R.id.f22dialog.button.ok);
        this.editText = (EditText) findViewById(R.id.f25dialog.edit.text);
        if (!TextUtils.isEmpty(str3)) {
            this.editText.setText(str3);
        }
        ((Button) this.positiveButton).setText(str2);
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str));
        }
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = EditTextDialog.this.editText.getText().toString();
                if (!TextUtils.isEmpty(obj.trim())) {
                    if (EditTextDialog.this.mOnButtonClickListener != null) {
                        EditTextDialog.this.mOnButtonClickListener.onPositiveButtonClick(obj);
                    }
                    EditTextDialog.this.dismiss();
                } else if (z) {
                    if (EditTextDialog.this.mOnButtonClickListener != null) {
                        EditTextDialog.this.mOnButtonClickListener.onPositiveButtonClick("");
                    }
                    EditTextDialog.this.dismiss();
                } else {
                    aj.a().a(EditTextDialog.this.mContext, str4);
                }
            }
        });
    }

    public void show() {
        getWindow().clearFlags(131080);
        getWindow().setSoftInputMode(4);
        super.show();
        this.editText.requestFocus();
    }

    public void dismiss() {
        Util.a(this.mContext, this.editText);
        super.dismiss();
    }
}

package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.gaana.R;
import com.library.controls.CircularImageView;

public class CustomDialogView extends Dialog {
    private static final int layoutResID = 2131493080;
    private Context mContext = null;
    private OnButtonClickListener mOnButtonClickListener;
    private OnCheckBoxDialogButtonClickListener mOnCheckBoxDialogButtonClickListener;
    private View negativeButton = null;
    private View positiveButton = null;
    private String title = null;

    public interface OnButtonClickListener {
        void onNegativeButtonClick();

        void onPositiveButtonClick();
    }

    public interface OnCheckBoxDialogButtonClickListener {
        void onNegativeButtonClick();

        void onPositiveButtonClick(boolean z);
    }

    public CustomDialogView(Context context, String str, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = str;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(R.layout.dialog_stop_download);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        init();
    }

    public CustomDialogView(Context context, String str, String str2, String str3, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = str;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(R.layout.dialog_stop_download);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        ((Button) this.negativeButton).setText(str3);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        ((Button) this.positiveButton).setText(str2);
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str));
        }
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CustomDialogView.this.mOnButtonClickListener != null) {
                    CustomDialogView.this.mOnButtonClickListener.onNegativeButtonClick();
                }
                CustomDialogView.this.dismiss();
            }
        });
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CustomDialogView.this.mOnButtonClickListener != null) {
                    CustomDialogView.this.mOnButtonClickListener.onPositiveButtonClick();
                }
                CustomDialogView.this.dismiss();
            }
        });
    }

    public CustomDialogView(Context context, @LayoutRes int i, String str, String str2, String str3, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = str;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(i);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        ((Button) this.negativeButton).setText(str3);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        ((Button) this.positiveButton).setText(str2);
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str));
        }
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CustomDialogView.this.mOnButtonClickListener != null) {
                    CustomDialogView.this.mOnButtonClickListener.onNegativeButtonClick();
                }
                CustomDialogView.this.dismiss();
            }
        });
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CustomDialogView.this.mOnButtonClickListener != null) {
                    CustomDialogView.this.mOnButtonClickListener.onPositiveButtonClick();
                }
                CustomDialogView.this.dismiss();
            }
        });
    }

    public CustomDialogView(Context context, String str, OnCheckBoxDialogButtonClickListener onCheckBoxDialogButtonClickListener, boolean z) {
        super(context);
        this.mContext = context;
        this.title = str;
        this.mOnCheckBoxDialogButtonClickListener = onCheckBoxDialogButtonClickListener;
        requestWindowFeature(1);
        setContentView(R.layout.dialog_dontshow_message);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.chkLanguageName);
        if (!z) {
            this.negativeButton.setVisibility(8);
            findViewById(R.id.middleLine).setVisibility(8);
            findViewById(R.id.dividerView).setVisibility(8);
        }
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str));
        }
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnCheckBoxDialogButtonClickListener.onPositiveButtonClick(checkBox.isChecked());
                CustomDialogView.this.dismiss();
            }
        });
    }

    public CustomDialogView(Context context, int i, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = (String) this.mContext.getResources().getText(i);
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(R.layout.dialog_stop_download);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        init();
    }

    public CustomDialogView(Context context, String str, int i, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.mOnButtonClickListener = onButtonClickListener;
        this.title = str;
        setCancelable(false);
        requestWindowFeature(1);
        setContentView(i);
        this.negativeButton = findViewById(R.id.f20dialog.button.cancel);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        init();
    }

    public CustomDialogView(Context context, View view) {
        super(context);
        this.mContext = context;
        this.title = this.title;
        setCancelable(false);
        requestWindowFeature(1);
        setContentView(view);
    }

    public CustomDialogView(Context context, String str, String str2, int i, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = str;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(i);
        this.positiveButton = findViewById(R.id.f23dialog.button.stop);
        ((Button) this.positiveButton).setText(str2);
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str));
        }
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnButtonClickListener.onPositiveButtonClick();
                CustomDialogView.this.dismiss();
            }
        });
    }

    public CustomDialogView(Context context, int i, String str, String str2, String str3, String str4, OnButtonClickListener onButtonClickListener) {
        super(context);
        this.mContext = context;
        this.title = str2;
        this.mOnButtonClickListener = onButtonClickListener;
        requestWindowFeature(1);
        setContentView(i);
        if (!TextUtils.isEmpty(str2)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(Html.fromHtml(str2));
        }
        ((CircularImageView) findViewById(R.id.f27dialog.img)).bindImage(str);
        this.negativeButton = findViewById(R.id.f21dialog.button.no);
        ((TextView) this.negativeButton).setText(str4);
        this.positiveButton = findViewById(R.id.f24dialog.button.yes);
        ((TextView) this.positiveButton).setText(str3);
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnButtonClickListener.onNegativeButtonClick();
                CustomDialogView.this.dismiss();
            }
        });
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnButtonClickListener.onPositiveButtonClick();
                CustomDialogView.this.dismiss();
            }
        });
    }

    private void init() {
        if (!TextUtils.isEmpty(this.title)) {
            ((TextView) findViewById(R.id.f26dialog.header.text)).setText(this.title);
        }
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnButtonClickListener.onNegativeButtonClick();
                CustomDialogView.this.dismiss();
            }
        });
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomDialogView.this.mOnButtonClickListener.onPositiveButtonClick();
                CustomDialogView.this.dismiss();
            }
        });
    }

    public Button getPositiveButton() {
        return (Button) this.positiveButton;
    }
}

package com.gaana.view.item;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.widget.TextView;
import com.b.i;
import com.gaana.R;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;

public class HeadingDialogView extends CustomDialogView {
    private static final int layoutResID = 2131493075;

    public HeadingDialogView(Context context, String str, String str2, String str3, String str4, OnButtonClickListener onButtonClickListener) {
        super(context, R.layout.dialog_heading_view, str2, str3, str4, onButtonClickListener);
        TextView textView = (TextView) findViewById(R.id.f28dialog.title.text);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setTypeface(i.a(context.getAssets(), "fonts/Roboto-Medium.ttf"));
    }

    public HeadingDialogView(Context context, @LayoutRes int i, String str, String str2, String str3, String str4, OnButtonClickListener onButtonClickListener) {
        super(context, i, str2, str3, str4, onButtonClickListener);
        TextView textView = (TextView) findViewById(R.id.f28dialog.title.text);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setTypeface(i.a(context.getAssets(), "fonts/Roboto-Medium.ttf"));
    }
}

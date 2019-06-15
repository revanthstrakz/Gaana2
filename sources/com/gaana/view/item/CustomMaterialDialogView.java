package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.gaana.R;

public class CustomMaterialDialogView extends Dialog {
    private static final int layoutResID = 2131493076;
    private Context mContext = null;

    public CustomMaterialDialogView(Context context, String str, String str2, String str3) {
        TextView textView;
        StringBuilder stringBuilder;
        super(context);
        this.mContext = context;
        requestWindowFeature(1);
        setContentView(R.layout.dialog_material_stop_download);
        if (!TextUtils.isEmpty(str)) {
            textView = (TextView) findViewById(R.id.dialog_top_text);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(str);
            textView.setText(stringBuilder2.toString());
        }
        if (!TextUtils.isEmpty(str2)) {
            textView = (TextView) findViewById(R.id.f29dialog_middle.text);
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str2);
            textView.setText(stringBuilder.toString());
        }
        if (!TextUtils.isEmpty(str3)) {
            textView = (TextView) findViewById(R.id.dialog_bottom_text);
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str3);
            textView.setText(stringBuilder.toString());
        }
        findViewById(R.id.dialog_gotit_text).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomMaterialDialogView.this.dismiss();
            }
        });
    }
}

package com.actionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.b.i;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;

public class CardsDetailsActionbar extends RelativeLayout implements OnClickListener {
    private Context a;
    private LayoutInflater b;
    private CustomDialogView c = null;
    private boolean d = false;

    public CardsDetailsActionbar(Context context, String str, String str2, boolean z) {
        super(context);
        a(context, str, str2, z);
    }

    private void a(Context context, String str, String str2, boolean z) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        setLayoutParams(new LayoutParams(-1, -2));
        this.b.inflate(R.layout.card_detail_actionbar, this);
        this.d = z;
        findViewById(R.id.menu_icon).setOnClickListener(this);
        a(str, str2);
    }

    private void a(String str, String str2) {
        ((TextView) findViewById(R.id.actionbar_title)).setText(str);
        ((TextView) findViewById(R.id.actionbar_title)).setTypeface(i.a(this.a.getAssets(), "fonts/Roboto-Medium.ttf"));
        ((TextView) findViewById(R.id.actionbar_amount)).setText(str2);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.menu_icon) {
            if (this.d) {
                this.c = new CustomDialogView(this.a, this.a.getString(R.string.exit_confirmation_msg), this.a.getString(R.string.dialog_yes), this.a.getString(R.string.dialog_no), new OnButtonClickListener() {
                    public void onPositiveButtonClick() {
                        CardsDetailsActionbar.this.c.dismiss();
                        ((GaanaActivity) CardsDetailsActionbar.this.a).homeIconClick();
                    }

                    public void onNegativeButtonClick() {
                        CardsDetailsActionbar.this.c.dismiss();
                    }
                });
                this.c.show();
                return;
            }
            ((GaanaActivity) this.a).homeIconClick();
        }
    }
}

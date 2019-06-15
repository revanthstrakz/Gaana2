package com.gaana.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.view.item.BaseItemView;

public class ErrorMessageView extends BaseItemView {
    private String mMessage;

    public ErrorMessageView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_error_message;
    }

    public View getPoplatedView(String str) {
        this.mView = super.getNewView(R.layout.view_error_message, null, null);
        this.mMessage = str;
        return getDataFilledView(this.mView);
    }

    private View getDataFilledView(View view) {
        ((TextView) view.findViewById(16908299)).setText(this.mMessage);
        return view;
    }
}

package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaMiniSetupFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.view.BaseItemView;
import com.managers.u;

public class GaanaMiniPackSetup extends BaseItemView implements OnClickListener {
    private Activity activityContext;
    private boolean linkDeviceCountExceeded = false;
    private TextView mExpiredRenewMessageFirst = null;
    private TextView mExpiredRenewMessageSecond = null;
    private int mLayoutResourceId = R.layout.view_gaana_mini_header;
    private Button mSetupButton = null;
    private View mView = null;

    public GaanaMiniPackSetup(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getView(Activity activity, boolean z) {
        this.mView = super.getNewView(this.mLayoutResourceId, null);
        this.linkDeviceCountExceeded = z;
        this.mExpiredRenewMessageFirst = (TextView) this.mView.findViewById(R.id.expired_renew_message_first_line);
        this.mExpiredRenewMessageSecond = (TextView) this.mView.findViewById(R.id.expired_renew_message_second_line);
        this.mSetupButton = (Button) this.mView.findViewById(R.id.renew_button);
        this.activityContext = activity;
        setHeaderText();
        this.mSetupButton.setOnClickListener(this);
        return this.mView;
    }

    private void setHeaderText() {
        int i = Constants.l ? R.color.first_line_color_white : R.color.first_line_color;
        SpannableString spannableString = new SpannableString("Complete GaanaPlus Mini Setup and ");
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageFirst.setText(spannableString);
        spannableString = new SpannableString("play songs without internet.");
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageSecond.append(spannableString);
        this.mSetupButton.setText("Setup Now");
    }

    public void onClick(View view) {
        if (view == this.mSetupButton) {
            u.a().a("Gaana Plus Mini", "setup", "downloadspage");
            ((GaanaActivity) this.mContext).displayFragment(new GaanaMiniSetupFragment());
        }
    }
}

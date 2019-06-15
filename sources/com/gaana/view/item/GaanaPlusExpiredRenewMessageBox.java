package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.view.BaseItemView;
import com.managers.ap;
import com.utilities.Util;

public class GaanaPlusExpiredRenewMessageBox extends BaseItemView implements OnClickListener {
    private Activity activityContext;
    private boolean linkDeviceCountExceeded = false;
    private TextView mExpiredRenewMessageFirst = null;
    private TextView mExpiredRenewMessageSecond = null;
    private int mLayoutResourceId = R.layout.view_gaanaplus_expired_header;
    private Button mRenewButton = null;
    private View mView = null;

    public GaanaPlusExpiredRenewMessageBox(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getView(Activity activity, boolean z) {
        this.mView = super.getNewView(this.mLayoutResourceId, null);
        this.linkDeviceCountExceeded = z;
        this.mExpiredRenewMessageFirst = (TextView) this.mView.findViewById(R.id.expired_renew_message_first_line);
        this.mExpiredRenewMessageSecond = (TextView) this.mView.findViewById(R.id.expired_renew_message_second_line);
        this.mRenewButton = (Button) this.mView.findViewById(R.id.renew_button);
        this.activityContext = activity;
        setHeaderText(z);
        this.mRenewButton.setOnClickListener(this);
        return this.mView;
    }

    private void setHeaderText(boolean z) {
        int i = Constants.l ? R.color.first_line_color_white : R.color.first_line_color;
        if (z) {
            this.mExpiredRenewMessageFirst.setText(String.format(this.mContext.getString(R.string.device_linking_limit_reached), new Object[]{Integer.valueOf(this.mAppState.getCurrentUser().getUserSubscriptionData().getProductProperties().getDeviceLimit())}));
            this.mExpiredRenewMessageSecond.setVisibility(8);
            this.mRenewButton.setText(R.string.manage_devices);
            return;
        }
        SpannableString spannableString = new SpannableString(this.mContext.getString(R.string.your));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageFirst.setText(spannableString);
        spannableString = new SpannableString(this.mContext.getString(R.string.gaana_subscription));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.f17gaana.red)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageFirst.append(spannableString);
        spannableString = new SpannableString(this.mContext.getString(R.string.has_expired));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageFirst.append(spannableString);
        spannableString = new SpannableString(this.mContext.getString(R.string.downloaded_songs_stream_online));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i)), 0, spannableString.length(), 33);
        this.mExpiredRenewMessageSecond.append(spannableString);
        this.mRenewButton.setText(R.string.click_to_renew);
        if (ap.a().l() && this.mView != null) {
            this.mView.setVisibility(8);
        }
    }

    public void onClick(View view) {
        if (view != this.mRenewButton) {
            return;
        }
        if (this.linkDeviceCountExceeded) {
            ((BaseActivity) this.mContext).sendGAEvent("Download", "Expired notification clicked", "Device link limit reached");
            UserInfo currentUser = this.mAppState.getCurrentUser();
            currentUser.getUserSubscriptionData().setAccountType(1);
            LoginManager.getInstance().saveUserInfoInSharedPreff();
            Intent intent = new Intent(this.activityContext, WebViewActivity.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://gaana.com/gaana_plus&token=");
            stringBuilder.append(currentUser.getAuthToken());
            stringBuilder.append("&");
            stringBuilder.append("deviceId");
            stringBuilder.append("=");
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            intent.putExtra("EXTRA_WEBVIEW_URL", stringBuilder.toString());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            this.activityContext.startActivityForResult(intent, 708);
            return;
        }
        ((BaseActivity) this.mContext).sendGAEvent("Download", "Expired notification clicked", "Gaana+ subscription Expired");
        this.mAppState.setSidebarActiveBtn(R.id.upgradeButtonLayout);
        ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
    }
}

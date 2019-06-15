package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.TrialProductFeature;
import com.managers.u;
import com.services.d;
import com.services.l.as;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;

public class FreeUserTrialPopUpView extends BottomSheetDialog implements OnClickListener {
    private TextView additionalText;
    private BLOCK_ACTION mBlockAction;
    private Context mContext;
    private TextView topHeaderTitle;

    public FreeUserTrialPopUpView(Context context, TrialProductFeature trialProductFeature) {
        super(context);
        this.mContext = context;
        init(context, trialProductFeature);
    }

    public FreeUserTrialPopUpView(Context context, TrialProductFeature trialProductFeature, BLOCK_ACTION block_action) {
        super(context);
        this.mContext = context;
        this.mBlockAction = block_action;
        init(context, trialProductFeature);
    }

    private void init(Context context, final TrialProductFeature trialProductFeature) {
        setContentView((int) R.layout.popup_google_trial_view_layout);
        BottomSheetBehavior.from((RelativeLayout) findViewById(R.id.layout)).setState(3);
        this.topHeaderTitle = (TextView) findViewById(R.id.topHeaderTitle);
        this.topHeaderTitle.setText(!TextUtils.isEmpty(trialProductFeature.getTrial_header()) ? trialProductFeature.getTrial_header() : this.mContext.getResources().getString(R.string.discoverd_gaana_feature));
        this.topHeaderTitle.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        ((TextView) findViewById(R.id.subTitleText)).setText(!TextUtils.isEmpty(trialProductFeature.getTrial_desc()) ? trialProductFeature.getTrial_desc() : this.mContext.getResources().getString(R.string.gaana_plus_description));
        Button button = (Button) findViewById(R.id.payNowButton);
        button.setVisibility(0);
        button.setOnClickListener(this);
        button.setText(this.mContext.getResources().getString(R.string.start_free_trial));
        button.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (!TextUtils.isEmpty(trialProductFeature.getCta_text())) {
            button.setText(trialProductFeature.getCta_text());
        }
        this.additionalText = (TextView) findViewById(R.id.additionalText);
        this.additionalText.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.terms_conditions_text);
        SpannableString spannableString = new SpannableString("T&C");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        textView.setText(spannableString);
        textView.setVisibility(0);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FreeUserTrialPopUpView.this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", trialProductFeature.getTermAndCondition());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                FreeUserTrialPopUpView.this.mContext.startActivity(intent);
            }
        });
        textView = (TextView) findViewById(R.id.moreOptionText);
        textView.setVisibility(0);
        textView.setText(this.mContext.getResources().getString(R.string.later));
        textView.setTextColor(ContextCompat.getColor(this.mContext, Constants.l ? R.color.black_alfa_70 : R.color.white_alfa_70));
        textView.setOnClickListener(this);
        textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (this.mBlockAction != null && this.mBlockAction == BLOCK_ACTION.SKIP) {
            u.a().a("Skip Count", "30 days Pop Up", "Default");
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.payNowButton) {
            if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                Util.b(this.mContext, "Free_trial", new as() {
                    public void onTrialSuccess() {
                        if (Constants.ab || Constants.aa) {
                            Constants.ab = false;
                            Constants.aa = false;
                            d.a().a(Constants.ac, false, true);
                            d.a().a(Constants.ad, false, true);
                            ((GaanaActivity) FreeUserTrialPopUpView.this.mContext).recreate();
                        }
                    }
                });
            } else {
                Intent intent = new Intent(this.mContext, Login.class);
                intent.putExtra("is_shuffle_result", true);
                ((GaanaActivity) this.mContext).startActivityForResult(intent, 711);
            }
        }
        dismiss();
    }

    private void openProductListingScreen() {
        GaanaApplication.getInstance().setSidebarActiveBtn(R.id.upgradeButtonLayout);
        ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.mBlockAction != null && this.mBlockAction == BLOCK_ACTION.SKIP) {
            u.a().a("Skip Count", "30 days Pop Up", "Default Plan_30 Days Trial_Abort");
        }
    }
}

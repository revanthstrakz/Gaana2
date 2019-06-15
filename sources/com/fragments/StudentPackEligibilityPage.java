package com.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.PaymentProductModel.PageHeaderConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.k;
import com.services.k.a;
import com.services.l.ad;
import com.services.l.au;
import com.utilities.Util;
import java.io.IOException;

public class StudentPackEligibilityPage extends BaseGaanaFragment implements TextWatcher, OnClickListener, a, a {
    private static StudentPackEligibilityPage l;
    private Bitmap a;
    private ProductItem b;
    private PageHeaderConfig c;
    private int d;
    private EditText e;
    private EditText f;
    private TextView g;
    private EditText h;
    private TextView i;
    private TextView j;
    private boolean k = false;
    private boolean m = false;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.containerView == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.containerView = setContentView(R.layout.student_pack_verify_eligibility_page, viewGroup);
            hideHomeActionBar();
        }
        a();
        return this.containerView;
    }

    private void a() {
        ((ImageView) this.containerView.findViewById(R.id.crossButton)).setOnClickListener(this);
        ((Button) this.containerView.findViewById(R.id.verifyEligibilityButton)).setOnClickListener(this);
        this.j = (TextView) this.containerView.findViewById(R.id.submitAndPay);
        this.j.setOnClickListener(this);
        ((ImageView) this.containerView.findViewById(R.id.crossButton2)).setOnClickListener(this);
        ((ImageView) this.containerView.findViewById(R.id.camera)).setOnClickListener(this);
        this.e = (EditText) this.containerView.findViewById(R.id.studentNameText);
        this.f = (EditText) this.containerView.findViewById(R.id.studentEmailAddress);
        this.g = (TextView) this.containerView.findViewById(R.id.studentDateOfBirth);
        this.h = (EditText) this.containerView.findViewById(R.id.enrollmentId);
        this.i = (TextView) this.containerView.findViewById(R.id.uploadId);
        this.i.setOnClickListener(this);
        this.e.addTextChangedListener(this);
        this.f.addTextChangedListener(this);
        this.g.addTextChangedListener(this);
        this.h.addTextChangedListener(this);
        this.i.addTextChangedListener(this);
        TextView textView = (TextView) this.containerView.findViewById(R.id.terms_conditions_text2);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView = (TextView) this.containerView.findViewById(R.id.terms_conditions_text);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        Util.a(getActivity(), this.g);
        ((TextView) this.containerView.findViewById(R.id.terms_conditions_text)).setOnClickListener(this);
        ((TextView) this.containerView.findViewById(R.id.terms_conditions_text2)).setOnClickListener(this);
        if (!(this.b == null || this.b.getP_cost_curr() == null || this.b.getP_cost() == null)) {
            textView = (TextView) this.containerView.findViewById(R.id.packDetails);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("@ ");
            stringBuilder.append(this.b.getP_cost_curr());
            stringBuilder.append(" ");
            stringBuilder.append(this.b.getP_cost());
            stringBuilder.append("/- ");
            stringBuilder.append(getString(R.string.month_only_text));
            textView.setText(stringBuilder.toString());
        }
        if (Constants.l) {
            this.j.setBackgroundResource(R.drawable.rounded_stuent_pay_button_white);
        } else {
            this.j.setBackgroundResource(R.drawable.rounded_student_pay_button);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera /*2131296603*/:
            case R.id.uploadId /*2131298798*/:
                d();
                return;
            case R.id.crossButton /*2131296807*/:
            case R.id.crossButton2 /*2131296808*/:
                try {
                    ((GaanaActivity) getActivity()).onBackPressedHandling();
                    return;
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                }
            case R.id.submitAndPay /*2131298523*/:
                c();
                return;
            case R.id.terms_conditions_text /*2131298560*/:
            case R.id.terms_conditions_text2 /*2131298561*/:
                Intent intent = new Intent(this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", "https://gaana.com/terms-conditions-studentpack.html");
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                this.mContext.startActivity(intent);
                return;
            case R.id.verifyEligibilityButton /*2131298821*/:
                ((GaanaActivity) getActivity()).checkSetLoginStatus(new ad() {
                    public void onLoginSuccess() {
                        if (GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() != 3) {
                            ((RelativeLayout) StudentPackEligibilityPage.this.containerView.findViewById(R.id.studentDetailsLayout)).setVisibility(0);
                            ((RelativeLayout) StudentPackEligibilityPage.this.containerView.findViewById(R.id.eligibiliyLayout)).setVisibility(8);
                            StudentPackEligibilityPage.this.b();
                        } else if (GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getSubscriptionType().equalsIgnoreCase("inapp") || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getSubscriptionType().equalsIgnoreCase("paytm")) {
                            aj.a().a(StudentPackEligibilityPage.this.mContext, StudentPackEligibilityPage.this.getString(R.string.not_allow_text));
                            ((GaanaActivity) StudentPackEligibilityPage.this.mContext).onBackPressedHandling();
                            u.a().a("Student’s Plan", "Verify eligibility", "Already Gaana+ User");
                        } else {
                            ((RelativeLayout) StudentPackEligibilityPage.this.containerView.findViewById(R.id.studentDetailsLayout)).setVisibility(0);
                            ((RelativeLayout) StudentPackEligibilityPage.this.containerView.findViewById(R.id.eligibiliyLayout)).setVisibility(8);
                            StudentPackEligibilityPage.this.b();
                        }
                    }
                }, "Log in using for purchase the student pack");
                u.a().a("Student’s Plan", "Verify eligibility", "Tapped");
                return;
            default:
                return;
        }
    }

    private void b() {
        CharSequence charSequence = "";
        CharSequence charSequence2 = "";
        u.a().a("Student’s Plan", "Eligibility detail screen", "Eligibility detail screen");
        if (!(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null)) {
            if (GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail() != null) {
                charSequence = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail();
            }
            if (GaanaApplication.getInstance().getCurrentUser().getUserProfile().getFullname() != null) {
                charSequence2 = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getFullname();
            }
        }
        if (!(TextUtils.isEmpty(charSequence2) || charSequence2.equalsIgnoreCase("gaana user"))) {
            this.e.setText(charSequence2);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.f.setText(charSequence);
            this.f.setClickable(false);
            this.f.setEnabled(false);
            if (Constants.l) {
                this.f.setBackgroundResource(R.color.grey);
            } else {
                this.f.setBackgroundResource(R.color.student_editext_color);
            }
        }
    }

    private void c() {
        if (!Util.j(getActivity())) {
            ap.a().f(getActivity());
        } else if (this.m) {
            new k(this.mContext, "https://api.gaana.com/studentpack.php", this.a, this.e.getText().toString(), this.f.getText().toString(), this.h.getText().toString(), this.g.getText().toString().replace("/", "-"), true, this).execute(new Void[0]);
        } else {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.allrequired_infoermation_text));
        }
    }

    private void d() {
        startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 9067);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            Uri data = intent.getData();
            try {
                if (String.valueOf((Bitmap) intent.getExtras().get("data")).equals("null")) {
                    this.a = Media.getBitmap(getActivity().getContentResolver(), data);
                } else {
                    this.a = (Bitmap) intent.getExtras().get("data");
                }
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
            if (this.a != null) {
                this.i.setText(getString(R.string.upload_successfully));
                aj.a().a(this.mContext, getString(R.string.image_captured_text));
            }
        }
        if (this.a == null) {
            aj.a().a(this.mContext, getString(R.string.not_captured_text));
        }
        f();
    }

    public void a(String str, String str2) {
        aj.a().a(this.mContext, str2);
        if (str.equalsIgnoreCase("1")) {
            ag.a(this.mContext).a("Student Pack", "Gaana Plus");
            e();
        }
    }

    private void e() {
        if (this.b != null && !TextUtils.isEmpty(this.b.getAction())) {
            if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(this.b.getAction())) {
                u.a().a(this.b, this.b.getItem_id());
                ag.a(this.mContext).a(this.mContext, this.b, new ag.a() {
                    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                        ag.a(StudentPackEligibilityPage.this.mContext).a("", "", "success");
                        ((BaseActivity) StudentPackEligibilityPage.this.mContext).updateUserStatus(new au() {
                            public void onUserStatusUpdated() {
                                ((BaseActivity) StudentPackEligibilityPage.this.mContext).hideProgressDialog();
                                ap.a().a(StudentPackEligibilityPage.this.mContext);
                                Util.aa();
                                aj.a().a(StudentPackEligibilityPage.this.mContext, StudentPackEligibilityPage.this.getString(R.string.enjoy_using_gaana_plus));
                                if (Util.v(StudentPackEligibilityPage.this.mContext)) {
                                    Intent intent = new Intent(StudentPackEligibilityPage.this.mContext, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    StudentPackEligibilityPage.this.mContext.startActivity(intent);
                                }
                            }
                        });
                        if (Util.s() != null && !TextUtils.isEmpty(StudentPackEligibilityPage.this.b.getP_payment_mode())) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(StudentPackEligibilityPage.this.b.getP_payment_mode());
                            stringBuilder.append("Success; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Student’s Plan", "Submit & Pay", stringBuilder.toString());
                            AppsFlyer.getInstance().reportStudentPackPurchaseCompleted(StudentPackEligibilityPage.this.b, StudentPackEligibilityPage.this.b.getP_payment_mode());
                        }
                    }

                    public void onFailure(String str, String str2) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(StudentPackEligibilityPage.this.b.getP_payment_mode());
                        stringBuilder.append("Failure; ");
                        u.a().a("Student’s Plan", "Submit & Pay", stringBuilder.toString());
                        if (!TextUtils.isEmpty(str)) {
                            aj.a().a(StudentPackEligibilityPage.this.mContext, str);
                        }
                        ag.a(StudentPackEligibilityPage.this.mContext).a(str, "", str2);
                        if (Util.s() != null && StudentPackEligibilityPage.this.b != null && !TextUtils.isEmpty(StudentPackEligibilityPage.this.b.getP_payment_mode())) {
                            String p_payment_mode = StudentPackEligibilityPage.this.b.getP_payment_mode();
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Failure; ");
                            stringBuilder2.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder2.toString());
                        }
                    }
                }, this.b.getItem_id(), this.b.getDesc());
            } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(this.b.getAction())) {
                g();
                BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
                paymentDetailFragment.a(this.c);
                paymentDetailFragment.a(this.b);
                ((GaanaActivity) this.mContext).displayFragment(paymentDetailFragment);
            } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(this.b.getAction()) && !TextUtils.isEmpty(this.b.getWeb_url())) {
                Intent intent = new Intent(this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", this.b.getWeb_url());
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                this.mContext.startActivity(intent);
            } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(this.b.getAction())) {
                u.a().a(this.b, this.b.getItem_id());
                u.a().a(this.b, this.b.getDesc(), this.b.getItem_id(), this.d);
                ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        f();
    }

    private void f() {
        if (TextUtils.isEmpty(this.e.getText()) || TextUtils.isEmpty(this.f.getText()) || TextUtils.isEmpty(this.g.getText()) || TextUtils.isEmpty(this.h.getText()) || TextUtils.isEmpty(this.i.getText()) || this.a == null) {
            this.m = false;
            if (Constants.l) {
                this.j.setBackgroundResource(R.drawable.rounded_stuent_pay_button_white);
                return;
            } else {
                this.j.setBackgroundResource(R.drawable.rounded_student_pay_button);
                return;
            }
        }
        this.m = true;
        this.j.setBackgroundResource(R.drawable.rounded_paynow_button);
    }

    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(32);
        }
    }

    public void onPause() {
        super.onPause();
        getActivity().getWindow().setSoftInputMode(48);
    }

    public void onDestroyView() {
        if (!(this.containerView == null || this.containerView.getParent() == null)) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
        getActivity().getWindow().setSoftInputMode(48);
    }

    private void g() {
        try {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        } catch (Exception unused) {
        }
    }
}

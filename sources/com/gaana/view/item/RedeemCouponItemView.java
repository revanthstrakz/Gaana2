package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.CouponProductFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.ap;
import com.services.d;
import com.services.f.b;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class RedeemCouponItemView extends BaseItemView {
    private LinearLayout couponAppliedContainer;
    private String extra_message;
    private boolean isUserLoggedIn;
    private RelativeLayout mRelativeLayout;
    private String message;
    private BaseGaanaFragment parentFragment;
    private String status;
    private EditText txCouponCode;
    private TextInputLayout txCouponCodeLayout;
    private TextView txtResponse;

    public RedeemCouponItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.isUserLoggedIn = false;
        this.txCouponCode = null;
        this.couponAppliedContainer = null;
        this.txCouponCodeLayout = null;
        this.txtResponse = null;
        this.mRelativeLayout = null;
        this.message = "";
        this.status = "0";
        this.extra_message = "";
        this.mLayoutId = R.layout.view_item_redeemcoupon;
        this.parentFragment = baseGaanaFragment;
    }

    public View getPoplatedView(ViewGroup viewGroup) {
        this.isUserLoggedIn = this.mAppState.getCurrentUser().getLoginStatus();
        this.mView = super.createNewBaseView(this.mLayoutId, null, viewGroup);
        return getDataFilledView();
    }

    private View getDataFilledView() {
        this.mRelativeLayout = (RelativeLayout) this.mView.findViewById(R.id.rlParent);
        TextView textView = (TextView) this.mView.findViewById(R.id.loginButton);
        this.txCouponCode = (EditText) this.mView.findViewById(R.id.edtTextCouponCode);
        this.couponAppliedContainer = (LinearLayout) this.mView.findViewById(R.id.couponAppliedContainer);
        this.txCouponCodeLayout = (TextInputLayout) this.mView.findViewById(R.id.coupon_code_title);
        Button button = (Button) this.mView.findViewById(R.id.btnSubmit);
        this.txtResponse = (TextView) this.mView.findViewById(R.id.txResult);
        if (this.isUserLoggedIn) {
            this.mRelativeLayout.setVisibility(0);
            textView.setVisibility(8);
            this.mView.findViewById(R.id.txLoginNeeded).setVisibility(8);
            button.setOnClickListener(this);
            this.txtResponse.setVisibility(0);
        } else {
            this.mRelativeLayout.setVisibility(8);
            textView.setVisibility(0);
            this.mView.findViewById(R.id.txLoginNeeded).setVisibility(0);
            this.txtResponse.setVisibility(4);
            textView.setOnClickListener(this);
        }
        return this.mView;
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id != R.id.btnSubmit) {
            if (id == R.id.loginButton) {
                ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                    public void onLoginSuccess() {
                        RedeemCouponItemView.this.isUserLoggedIn = true;
                        RedeemCouponItemView.this.getDataFilledView();
                    }
                }, getResources().getString(R.string.LOGIN_LAUNCHED_FOR_REDEEM_COUPON));
            }
        } else if (this.txCouponCode.getText() == null || this.txCouponCode.getText().toString().trim().length() == 0) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.please_enter_coupon_code));
        } else {
            this.txtResponse.setVisibility(4);
            String trim = this.txCouponCode.getText().toString().trim();
            if (trim.contains(" ")) {
                aj.a().a(this.mContext, this.mContext.getString(R.string.please_enter_correct_coupon_code));
                return;
            }
            sendRedeemCouponRequest(trim);
        }
    }

    public void sendRedeemCouponRequest(String str) {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true));
        if (this.txCouponCode != null) {
            this.txCouponCode.setText(str);
        }
        str = "https://api.gaana.com/gaanaplusservice.php?type=coupon_redeem&coupon_code=<coupon_code>".replace("<coupon_code>", Uri.encode(str));
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || str.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str = stringBuilder.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(str);
        uRLManager.a(String.class);
        uRLManager.a(Priority.HIGH);
        if (Util.j(this.mContext)) {
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    String str = (String) obj;
                    if (str != null) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            RedeemCouponItemView.this.message = jSONObject.getString("message");
                            RedeemCouponItemView.this.status = jSONObject.getString("status");
                            RedeemCouponItemView.this.extra_message = jSONObject.getString("extra_msg");
                        } catch (JSONException e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                    if (RedeemCouponItemView.this.status.equals("1")) {
                        if (RedeemCouponItemView.this.parentFragment instanceof SettingsDetailFragment) {
                            ((SettingsDetailFragment) RedeemCouponItemView.this.parentFragment).c().a("Coupons", RedeemCouponItemView.this.message, Boolean.valueOf(false), new b() {
                                public void onCancelListner() {
                                }

                                public void onOkListner(String str) {
                                    ((BaseActivity) RedeemCouponItemView.this.mContext).updateUserStatus(new au() {
                                        public void onUserStatusUpdated() {
                                            ((BaseActivity) RedeemCouponItemView.this.mContext).hideProgressDialog();
                                            ap.a().a(RedeemCouponItemView.this.mContext);
                                            aj.a().a(RedeemCouponItemView.this.mContext, RedeemCouponItemView.this.mContext.getResources().getString(R.string.success_msg_purchase_trial));
                                            Util.a(RedeemCouponItemView.this.mContext, RedeemCouponItemView.this.txCouponCode);
                                            d.a().a("PREFERENCE_SESSION_TRIAL_FIRSTTIME", true, true);
                                            d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", GaanaApplication.sessionHistoryCount, true);
                                            Intent intent = new Intent(RedeemCouponItemView.this.mContext, GaanaActivity.class);
                                            intent.setFlags(71303168);
                                            RedeemCouponItemView.this.mContext.startActivity(intent);
                                        }
                                    });
                                }
                            }, false);
                        }
                    } else if (!RedeemCouponItemView.this.status.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                        ((BaseActivity) RedeemCouponItemView.this.mContext).hideProgressDialog();
                    } else if (RedeemCouponItemView.this.couponAppliedContainer != null) {
                        ((TextView) RedeemCouponItemView.this.couponAppliedContainer.findViewById(R.id.sucessMessage)).setText(RedeemCouponItemView.this.message);
                        ((TextView) RedeemCouponItemView.this.couponAppliedContainer.findViewById(R.id.couponMessage)).setText(RedeemCouponItemView.this.extra_message);
                        RedeemCouponItemView.this.couponAppliedContainer.setVisibility(0);
                        RedeemCouponItemView.this.txCouponCodeLayout.setVisibility(8);
                        Util.a(RedeemCouponItemView.this.mContext, RedeemCouponItemView.this.txCouponCode);
                        ((BaseActivity) RedeemCouponItemView.this.mContext).hideProgressDialog();
                        RedeemCouponItemView.this.mView.findViewById(R.id.btnSubmit).setVisibility(8);
                        RedeemCouponItemView.this.mView.findViewById(R.id.btnProceed).setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (!TextUtils.isEmpty(RedeemCouponItemView.this.txCouponCode.getText().toString())) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("COUPONCODE", RedeemCouponItemView.this.txCouponCode.getText().toString());
                                    BaseGaanaFragment couponProductFragment = new CouponProductFragment();
                                    couponProductFragment.setArguments(bundle);
                                    ((GaanaActivity) RedeemCouponItemView.this.mContext).displayFragment(couponProductFragment);
                                }
                            }
                        });
                    }
                    if (RedeemCouponItemView.this.txCouponCodeLayout != null) {
                        RedeemCouponItemView.this.txCouponCodeLayout.setError(Constants.b(RedeemCouponItemView.this.message));
                    }
                }
            }, uRLManager);
        }
    }

    private void showGaanaPlusActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_SETTINGS", 1);
        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
        settingsDetailFragment.setArguments(bundle);
        ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
    }
}

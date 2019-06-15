package com.gaana.login.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.LoginManager.SsoSdkInitialized;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.User.LoginType;
import com.login.nativesso.a.ah;
import com.login.nativesso.a.r;
import com.login.nativesso.e.c;
import com.managers.u;
import com.services.f;
import com.services.f.b;
import com.utilities.Util;
import in.til.core.a;
import in.til.core.integrations.TILSDKExceptionDto;

public class VerifyOtpFragment extends Fragment implements OnClickListener {
    private static final int ERROR_MSG_EXPIRED_OTP = 0;
    private static final int ERROR_MSG_LIMIT_EXCEEDED = 2;
    private static final int ERROR_MSG_UNVERIFIED_EMAIL = 3;
    private static final int ERROR_MSG_WRONG_OTP = 1;
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_REQUEST_OTP = "extra_request_otp";
    private String email;
    private boolean isVerifying;
    private Button mBtnVerifyOtp;
    private Context mContext;
    private f mDialog;
    private EditText mEditTxtOtp;
    private IOnLoginCompleted mLoginCompletedListener;
    private TextView mTxtShowOtp;
    private PasswordTransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
    private b resendOtpListener = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            VerifyOtpFragment.this.resendOtp();
        }
    };
    private boolean shouldRequestOtp;
    private boolean showOtp = false;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public static VerifyOtpFragment newInstance(String str, boolean z) {
        VerifyOtpFragment verifyOtpFragment = new VerifyOtpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_email", str);
        bundle.putBoolean(EXTRA_REQUEST_OTP, z);
        verifyOtpFragment.setArguments(bundle);
        return verifyOtpFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_verify_otp, viewGroup, false);
        this.email = getArguments().getString("extra_email");
        this.shouldRequestOtp = getArguments().getBoolean(EXTRA_REQUEST_OTP, false);
        this.mBtnVerifyOtp = (Button) inflate.findViewById(R.id.btn_verify_otp);
        this.mEditTxtOtp = (EditText) inflate.findViewById(R.id.edit_txt_otp);
        this.mTxtShowOtp = (TextView) inflate.findViewById(R.id.txt_show_otp);
        this.mTxtShowOtp.setOnClickListener(this);
        if (this.showOtp) {
            this.mEditTxtOtp.setTransformationMethod(null);
            this.mTxtShowOtp.setText(this.mContext.getString(R.string.txt_hide));
        } else {
            this.mEditTxtOtp.setTransformationMethod(this.passwordTransformationMethod);
            this.mTxtShowOtp.setText(this.mContext.getString(R.string.txt_show));
        }
        this.mBtnVerifyOtp.setOnClickListener(this);
        inflate.findViewById(R.id.txt_resend_otp).setOnClickListener(this);
        inflate.findViewById(R.id.back).setOnClickListener(this);
        inflate.findViewById(R.id.rl_fb_btn).setOnClickListener(this);
        inflate.findViewById(R.id.onboard_btn_fb).setOnClickListener(this);
        enableVerifyOtpButton(false);
        this.mEditTxtOtp.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable == null || editable.length() != 6) {
                    VerifyOtpFragment.this.enableVerifyOtpButton(false);
                } else {
                    VerifyOtpFragment.this.enableVerifyOtpButton(true);
                }
            }
        });
        return inflate;
    }

    private void enableVerifyOtpButton(boolean z) {
        this.mBtnVerifyOtp.setEnabled(z);
        if (z) {
            this.mBtnVerifyOtp.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard_enabled));
            this.mBtnVerifyOtp.setTextColor(getResources().getColor(R.color.white));
            return;
        }
        this.mBtnVerifyOtp.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard));
        this.mBtnVerifyOtp.setTextColor(getResources().getColor(R.color.white_alfa_55));
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        switch (view.getId()) {
            case R.id.back /*2131296487*/:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_verify_otp /*2131296563*/:
                if (!this.isVerifying) {
                    verifyOtp(this.mEditTxtOtp.getText().toString());
                    break;
                }
                return;
            case R.id.onboard_btn_fb /*2131297859*/:
            case R.id.rl_fb_btn /*2131298257*/:
                LoginManager.getInstance().login(getActivity(), LoginType.FB, (Login) this.mContext);
                break;
            case R.id.txt_resend_otp /*2131298773*/:
                resendOtp();
                break;
            case R.id.txt_show_otp /*2131298776*/:
                this.showOtp ^= 1;
                if (!this.showOtp) {
                    this.mEditTxtOtp.setTransformationMethod(this.passwordTransformationMethod);
                    this.mTxtShowOtp.setText(this.mContext.getString(R.string.txt_show));
                    break;
                }
                this.mEditTxtOtp.setTransformationMethod(null);
                this.mTxtShowOtp.setText(this.mContext.getString(R.string.txt_hide));
                break;
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.shouldRequestOtp) {
            showDialog(3);
        }
    }

    private void resendOtp() {
        if (this.shouldRequestOtp) {
            this.shouldRequestOtp = false;
        }
        ((Login) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.resending_otp));
        LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onSuccess() {
                a.b().a(VerifyOtpFragment.this.email, "", new r() {
                    public void onSuccess() {
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                    }

                    public void onFailure(c cVar) {
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                    }

                    public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                    }
                });
            }

            public void onError() {
                ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
            }
        });
    }

    private void verifyOtp(final String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(getContext(), R.string.error_msg_correct_otp, 0).show();
            return;
        }
        this.isVerifying = true;
        ((Login) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.verifying_otp));
        LoginManager.getInstance().setmOnLoginCompleted(this.mLoginCompletedListener);
        LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onSuccess() {
                a.b().a(VerifyOtpFragment.this.email, "", str, new ah() {
                    public void onSuccess() {
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                        LoginManager.getInstance().setLoginInProcess(true);
                        u.a().a("Registration", "Registration Success", "Registration - Email");
                        AppsFlyer.getInstance().reportUserRegistration("email.register");
                        LoginManager.getInstance().getLoginClient(LoginType.GAANA).retrieveTicketAndLogin(LoginType.GAANA, LoginManager.getInstance().getLoginInfo());
                        VerifyOtpFragment.this.isVerifying = false;
                    }

                    public void onFailure(c cVar) {
                        VerifyOtpFragment.this.isVerifying = false;
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                        int i = cVar.a;
                        if (i != SsoErrorCodes.INVALID_OTP) {
                            switch (i) {
                                case SsoErrorCodes.WRONG_OTP /*414*/:
                                    break;
                                case SsoErrorCodes.EXPIRED_OTP /*415*/:
                                    VerifyOtpFragment.this.showDialog(0);
                                    break;
                                case SsoErrorCodes.LIMIT_EXCEEDED /*416*/:
                                    VerifyOtpFragment.this.showDialog(2);
                                    ((Login) VerifyOtpFragment.this.mContext).finish();
                                    break;
                            }
                        }
                        VerifyOtpFragment.this.showDialog(1);
                        if (VerifyOtpFragment.this.mLoginCompletedListener != null) {
                            VerifyOtpFragment.this.mLoginCompletedListener.onLoginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                        }
                    }

                    public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                        VerifyOtpFragment.this.isVerifying = false;
                        ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                        VerifyOtpFragment.this.mLoginCompletedListener.onLoginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                    }
                });
            }

            public void onError() {
                ((Login) VerifyOtpFragment.this.mContext).hideProgressDialog();
                VerifyOtpFragment.this.mLoginCompletedListener.onLoginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
            }
        });
    }

    public void setLoginCompletedListener(IOnLoginCompleted iOnLoginCompleted) {
        this.mLoginCompletedListener = iOnLoginCompleted;
    }

    private void showDialog(int i) {
        if (this.mDialog == null) {
            this.mDialog = new f(this.mContext);
        }
        switch (i) {
            case 0:
                this.mDialog.a("", this.mContext.getString(R.string.error_msg_expired_otp), Boolean.valueOf(true), this.mContext.getString(R.string.resend_otp), this.mContext.getString(R.string.cancel), this.resendOtpListener);
                return;
            case 1:
                this.mDialog.a("", this.mContext.getString(R.string.error_msg_wrong_otp), Boolean.valueOf(true), this.mContext.getString(R.string.resend_otp), this.mContext.getString(R.string.cancel), this.resendOtpListener);
                return;
            case 2:
                this.mDialog.a("", this.mContext.getString(R.string.error_msg_limit_exceeded), Boolean.valueOf(true), this.mContext.getString(R.string.resend_otp), this.mContext.getString(R.string.cancel), this.resendOtpListener);
                return;
            case 3:
                this.mDialog.a("", this.mContext.getString(R.string.error_msg_unverified_email), Boolean.valueOf(true), this.mContext.getString(R.string.resend_otp), this.mContext.getString(R.string.cancel), this.resendOtpListener);
                return;
            default:
                return;
        }
    }
}

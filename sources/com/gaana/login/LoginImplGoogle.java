package com.gaana.login;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.LoginManager.SsoSdkInitialized;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import com.login.nativesso.a.x;
import com.login.nativesso.e.c;
import com.managers.ai;
import com.managers.ap;
import com.utilities.Util;
import in.til.core.a;
import in.til.core.integrations.TILSDKExceptionDto;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginImplGoogle extends LoginClient {
    public void loginOnUpgrade() {
    }

    public void login(final LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().a(loginInfo.getRealToken(), loginInfo.getGoogleID(), LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE, false, new x() {
                        public void onLoginSuccess() {
                            LoginImplGoogle.this.retrieveTicketAndLogin(LoginType.GOOGLE, loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            LoginManager.getInstance().hideProgressDialog();
                            if (cVar.a == SsoErrorCodes.EMAIL_ID_MISSING) {
                                UserInfo userInfo = new UserInfo();
                                userInfo.setError(GaanaApplication.getContext().getString(R.string.complete_your_profile));
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_LAUNCH_TRAP_PAGE, userInfo, null);
                            } else {
                                LoginManager.getInstance().logoutFromGoogle(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                            }
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, cVar.a, cVar.b);
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.getInstance().hideProgressDialog();
                            LoginManager.getInstance().logoutFromGoogle(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, tILSDKExceptionDto.a, tILSDKExceptionDto.b);
                        }
                    });
                }

                public void onError() {
                    LoginManager.getInstance().hideProgressDialog();
                    LoginManager.getInstance().logoutFromGoogle(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                    LoginManager.getInstance().handleSSOLoginFailure(loginInfo, SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED");
                }
            });
        } else {
            LoginManager.getInstance().loginToGaana(LoginType.GOOGLE, "", loginInfo);
        }
    }

    public void loginSilently(final LoginInfo loginInfo, final IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().a(loginInfo.getRealToken(), loginInfo.getGoogleID(), LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE, false, new x() {
                        public void onLoginSuccess() {
                            LoginImplGoogle.this.retrieveTicketAndLogin(LoginType.GOOGLE, loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            if (LoginImplGoogle.this.shouldLogoutUser(loginInfo, cVar)) {
                                ap.a().a(ai.a(), false, iOnLoginCompleted, LOGIN_STATUS.LOGGED_OUT);
                            } else {
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, GaanaApplication.getInstance().getCurrentUser(), null);
                            }
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SDK_NOT_INITIALIZED, GaanaApplication.getInstance().getCurrentUser(), null);
                        }
                    });
                }

                public void onError() {
                    LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SDK_NOT_INITIALIZED, GaanaApplication.getInstance().getCurrentUser(), null);
                }
            });
        } else {
            LoginManager.getInstance().loginToGaana(LoginType.GOOGLE, "", loginInfo);
        }
    }

    public void register(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        login(loginInfo, iOnLoginCompleted);
    }

    public void logout(LoginInfo loginInfo) {
        if (isSsoEnabled(loginInfo)) {
            signOutFromSso();
        }
        GooglePlusLogin.getInstance().disconnect();
    }

    public HashMap<String, String> getLoginParams(String str, LoginInfo loginInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", LoginManager.TAG_TYPE_VALUE);
        if (isSsoEnabled(loginInfo)) {
            hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_SSO);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("login_mode", LoginManager.TAG_SUBTYPE_GOOGLE);
                if (!TextUtils.isEmpty(loginInfo.getDob())) {
                    jSONObject.put(LoginManager.TAG_DOB, loginInfo.getDob());
                }
                if (!TextUtils.isEmpty(loginInfo.getImgUrl())) {
                    jSONObject.put("profile_img", URLEncoder.encode(loginInfo.getImgUrl()));
                }
            } catch (JSONException unused) {
            }
            hashMap.put(LoginManager.TAG_SSO_USER_INFO, jSONObject.toString());
            hashMap.put(LoginManager.TAG_SSO_TICKET_ID, str);
        } else {
            hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_GOOGLE);
            hashMap.put("googleid", loginInfo.getGoogleID());
            hashMap.put("googletoken", Util.a(Util.b(loginInfo.getGoogleID()), Constants.bc));
            hashMap.put("googlerealtoken", loginInfo.getRealToken());
            hashMap.put("gp_manual_data", String.valueOf(loginInfo.getIsManualData()));
            hashMap.put("email", loginInfo.getEmailId());
        }
        return hashMap;
    }

    public boolean isSsoEnabled(LoginInfo loginInfo) {
        return loginInfo.getLoginMode() == LoginMode.SSO;
    }

    public LoginType getLoginType() {
        return LoginType.GOOGLE;
    }
}

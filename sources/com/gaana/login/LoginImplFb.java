package com.gaana.login;

import com.constants.Constants;
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
import com.services.g;
import com.utilities.Util;
import in.til.core.a;
import in.til.core.integrations.TILSDKExceptionDto;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginImplFb extends LoginClient {
    public void loginOnUpgrade() {
    }

    public void login(final LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().a(loginInfo.getRealToken(), loginInfo.getFbId(), LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK, false, new x() {
                        public void onLoginSuccess() {
                            LoginImplFb.this.retrieveTicketAndLogin(LoginType.FB, loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            LoginManager.getInstance().hideProgressDialog();
                            if (cVar.a == SsoErrorCodes.EMAIL_ID_MISSING) {
                                UserInfo userInfo = new UserInfo();
                                LoginManager.getInstance().setLoginInfo(loginInfo);
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_EMAIL_MISSING_FB, userInfo, null);
                            } else {
                                LoginManager.getInstance().logoutFromFacebook(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                            }
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, cVar.a, cVar.b);
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.getInstance().hideProgressDialog();
                            LoginManager.getInstance().logoutFromFacebook(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, tILSDKExceptionDto.a, tILSDKExceptionDto.b);
                        }
                    });
                }

                public void onError() {
                    LoginManager.getInstance().hideProgressDialog();
                    LoginManager.getInstance().logoutFromFacebook(LOGIN_STATUS.LOGIN_FAILURE_SSO);
                    LoginManager.getInstance().handleSSOLoginFailure(loginInfo, SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED");
                }
            });
        } else {
            LoginManager.getInstance().loginToGaana(LoginType.FB, "", loginInfo);
        }
    }

    public void loginSilently(final LoginInfo loginInfo, final IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().a(loginInfo.getRealToken(), loginInfo.getFbId(), LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK, false, new x() {
                        public void onLoginSuccess() {
                            LoginImplFb.this.retrieveTicketAndLogin(LoginType.FB, loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            if (LoginImplFb.this.shouldLogoutUser(loginInfo, cVar)) {
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
            LoginManager.getInstance().loginToGaana(LoginType.FB, "", loginInfo);
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
        g.a().h();
    }

    public HashMap<String, String> getLoginParams(String str, LoginInfo loginInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", LoginManager.TAG_TYPE_VALUE);
        if (isSsoEnabled(loginInfo)) {
            hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_SSO);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("login_mode", LoginManager.TAG_SUBTYPE_FB);
            } catch (JSONException unused) {
            }
            hashMap.put(LoginManager.TAG_SSO_USER_INFO, jSONObject.toString());
            hashMap.put(LoginManager.TAG_SSO_TICKET_ID, str);
        } else {
            hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_FB);
            hashMap.put(LoginManager.TAG_FBID, loginInfo.getFbId());
            hashMap.put(LoginManager.TAG_FB_TOKEN, Util.a(Util.b(loginInfo.getFbId()), Constants.bc));
            hashMap.put(LoginManager.TAG_FB_REAL_TOKEN, loginInfo.getRealToken());
            hashMap.put(LoginManager.TAG_FB_MANUAL_DATA, String.valueOf(loginInfo.getIsManualData()));
            hashMap.put("email", loginInfo.getEmailId());
            hashMap.put(LoginManager.TAG_FULL_NAME, loginInfo.getFullname());
        }
        return hashMap;
    }

    public boolean isSsoEnabled(LoginInfo loginInfo) {
        return loginInfo.getLoginMode() == LoginMode.SSO;
    }

    public LoginType getLoginType() {
        return LoginType.FB;
    }
}

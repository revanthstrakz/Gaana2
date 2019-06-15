package com.gaana.login;

import android.app.Activity;
import android.text.TextUtils;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.LoginManager.SsoSdkInitialized;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginType;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.b;
import com.i.e;
import com.i.i;
import com.login.nativesso.a.f;
import com.login.nativesso.a.h;
import com.login.nativesso.a.l;
import com.login.nativesso.a.u;
import com.login.nativesso.e.c;
import com.managers.ai;
import com.managers.an;
import com.managers.ap;
import com.services.d;
import com.services.l.af;
import com.utilities.Util;
import in.til.core.a;
import in.til.core.integrations.TILSDKExceptionDto;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LoginClient {
    public abstract HashMap<String, String> getLoginParams(String str, LoginInfo loginInfo);

    public abstract LoginType getLoginType();

    public abstract boolean isSsoEnabled(LoginInfo loginInfo);

    public abstract void login(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted);

    public abstract void loginOnUpgrade();

    public abstract void loginSilently(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted);

    public abstract void logout(LoginInfo loginInfo);

    public abstract void register(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted);

    /* Access modifiers changed, original: 0000 */
    public void signOutFromSso() {
        LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onError() {
            }

            public void onSuccess() {
                a.b().a(GaanaApplication.getContext(), true, new u() {
                    public void onFailure(c cVar) {
                    }

                    public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                    }

                    public void onSuccess() {
                    }
                });
            }
        });
    }

    public void retrieveTicketAndLogin(final LoginType loginType, final LoginInfo loginInfo) {
        LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onSuccess() {
                a.b().c(new h() {
                    public void onSuccess(com.login.nativesso.e.a aVar) {
                        an.a().d("s2s", "ac", LoginClient.this.getRequestParameters(loginInfo), "SSO", aVar.a(), "SUCCESS", "", "");
                        LoginManager.getInstance().loginToGaana(loginType, aVar.a(), loginInfo);
                    }

                    public void onFailure(c cVar) {
                        String access$000 = LoginClient.this.getRequestParameters(loginInfo);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(cVar.a);
                        stringBuilder.append("##");
                        stringBuilder.append(cVar.b);
                        an.a().d("s2s", "ac", access$000, "SSO", stringBuilder.toString(), "FAIL", "", "");
                        LoginManager.getInstance().hideProgressDialog();
                        LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SSO, GaanaApplication.getInstance().getCurrentUser(), null);
                    }

                    public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                        LoginManager.getInstance().hideProgressDialog();
                        LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SSO, GaanaApplication.getInstance().getCurrentUser(), null);
                        String access$000 = LoginClient.this.getRequestParameters(loginInfo);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(tILSDKExceptionDto.a);
                        stringBuilder.append("##");
                        stringBuilder.append(tILSDKExceptionDto.b);
                        an.a().d("s2s", "ac", access$000, "SSO", "FAIL", stringBuilder.toString(), "", "");
                    }
                });
            }

            public void onError() {
                LoginManager.getInstance().hideProgressDialog();
                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SDK_NOT_INITIALIZED, GaanaApplication.getInstance().getCurrentUser(), null);
            }
        });
    }

    private String getRequestParameters(LoginInfo loginInfo) {
        StringBuilder stringBuilder;
        if (loginInfo.getLoginType() == LoginType.FB) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("##");
            stringBuilder.append(loginInfo.getFbId());
            return stringBuilder.toString();
        } else if (loginInfo.getLoginType() == LoginType.GOOGLE) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("##");
            stringBuilder.append(loginInfo.getGoogleID());
            return stringBuilder.toString();
        } else if (loginInfo.getLoginType() == LoginType.PHONENUMBER) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("##");
            stringBuilder.append(loginInfo.getFbPhoneLoginAuthCode());
            return stringBuilder.toString();
        } else if (loginInfo.getLoginType() != LoginType.GAANA) {
            return "";
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("##");
            stringBuilder.append(loginInfo.getEmailId());
            return stringBuilder.toString();
        }
    }

    public void checkSSOValidity(final Activity activity, LoginInfo loginInfo) {
        if (isSsoEnabled(loginInfo) || loginInfo.isUnverifiedSSOUser()) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onError() {
                }

                public void onSuccess() {
                    a.b().c(new h() {
                        public void onFailure(c cVar) {
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                        }

                        public void onSuccess(com.login.nativesso.e.a aVar) {
                            if (aVar != null && !TextUtils.isEmpty(aVar.a())) {
                                b bVar = new b("https://api.gaana.com/user.php?type=nxtgen_validate_sso_tkt&sso_ticket_id=<what>".replace("<what>", aVar.a()), String.class, new e.a() {
                                    public void onErrorResponse(BusinessObject businessObject) {
                                    }

                                    public void onDataRetrieved(Object obj, boolean z) {
                                        if (LoginClient.this.isValidTicketJson((String) obj)) {
                                            d.a().a(System.currentTimeMillis(), "pref_sso_last_refresh_time", false);
                                            return;
                                        }
                                        LoginManager instance = LoginManager.getInstance();
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("INVALID TICKET ID - ");
                                        stringBuilder.append(Util.X());
                                        instance.sendUserLogOutEvent(-1, stringBuilder.toString());
                                        ap.a().a(activity, false, null, LOGIN_STATUS.LOGGED_OUT);
                                    }
                                });
                                bVar.a(false);
                                i.a().a(bVar);
                            }
                        }
                    });
                }
            });
        }
    }

    private boolean isValidTicketJson(String str) {
        try {
            return TextUtils.isEmpty(new JSONObject(str).optString("error_code"));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public void createUnverifiedSSOSession(final String str, final LoginInfo loginInfo) {
        LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onError() {
            }

            public void onSuccess() {
                a.b().b(str, new f() {
                    public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                    }

                    public void onSuccess() {
                        loginInfo.setUnverifiedSSOUser(true);
                        LoginManager.getInstance().setLoginInfo(loginInfo);
                    }

                    public void onFailure(c cVar) {
                        if (cVar.a == 404) {
                            ap.a().a(ai.a(), false, null, LOGIN_STATUS.LOGGED_OUT);
                        }
                    }
                });
            }
        });
    }

    public void getUserId(LoginInfo loginInfo, final af afVar) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().b(new l() {
                        public void onSuccess(com.login.nativesso.e.e eVar) {
                            String b = eVar.b();
                            if (afVar != null) {
                                afVar.onRetreivalComplete(b);
                            }
                        }

                        public void onFailure(c cVar) {
                            if (afVar != null) {
                                afVar.onErrorResponse(null);
                            }
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            if (afVar != null) {
                                afVar.onErrorResponse(null);
                            }
                        }
                    });
                }

                public void onError() {
                    if (afVar != null) {
                        afVar.onErrorResponse(null);
                    }
                }
            });
        } else if (afVar != null) {
            afVar.onErrorResponse(null);
        }
    }

    public void getTicketId(LoginInfo loginInfo, final af afVar) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().c(new h() {
                        public void onSuccess(com.login.nativesso.e.a aVar) {
                            String a = aVar.a();
                            if (afVar != null) {
                                afVar.onRetreivalComplete(a);
                            }
                        }

                        public void onFailure(c cVar) {
                            if (afVar != null) {
                                afVar.onErrorResponse(null);
                            }
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            if (afVar != null) {
                                afVar.onErrorResponse(null);
                            }
                        }
                    });
                }

                public void onError() {
                    if (afVar != null) {
                        afVar.onErrorResponse(null);
                    }
                }
            });
        } else if (afVar != null) {
            afVar.onErrorResponse(null);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldLogoutUser(LoginInfo loginInfo, c cVar) {
        if (cVar.a == 404 || cVar.a == SsoErrorCodes.WRONG_PASSWORD || cVar.a == SsoErrorCodes.WRONG_OTP_PASSWORD || cVar.a == SsoErrorCodes.WRONG_OTP) {
            LoginManager instance = LoginManager.getInstance();
            int i = cVar.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(cVar.b);
            stringBuilder.append(" ");
            stringBuilder.append(Util.X());
            instance.sendUserLogOutEvent(i, stringBuilder.toString());
            LoginManager.getInstance().handleSSOLoginFailureOnReAuth(loginInfo, cVar.a, cVar.b, true);
            return true;
        }
        LoginManager.getInstance().handleSSOLoginFailureOnReAuth(loginInfo, cVar.a, cVar.b, false);
        return false;
    }
}

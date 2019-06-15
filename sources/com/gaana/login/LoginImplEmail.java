package com.gaana.login;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.LoginManager.SsoSdkInitialized;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import com.i.i;
import com.login.nativesso.a.m;
import com.login.nativesso.a.v;
import com.login.nativesso.e.c;
import com.managers.URLManager;
import com.managers.ai;
import com.managers.ap;
import com.managers.d;
import com.payu.custombrowser.util.CBConstant;
import com.services.l.af;
import com.utilities.Util;
import in.til.core.a;
import in.til.core.integrations.TILSDKExceptionDto;
import java.util.Calendar;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginImplEmail extends LoginClient {
    private long initialTime;

    public void loginOnUpgrade() {
    }

    public void login(final LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().b(loginInfo.getEmailId(), loginInfo.getPassword(), new m() {
                        public void onLoginSuccess() {
                            LoginImplEmail.this.retrieveTicketAndLogin(LoginType.GAANA, loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            LoginManager.getInstance().hideProgressDialog();
                            int i = cVar.a;
                            if (i == 405) {
                                Bundle bundle = new Bundle();
                                bundle.putString("extra_email", loginInfo.getEmailId());
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_VERIFY_USER, new UserInfo(), bundle);
                            } else {
                                UserInfo userInfo = new UserInfo();
                                if (i == SsoErrorCodes.PROXY_OR_DEFUNC_MAIL) {
                                    userInfo.setError(GaanaApplication.getContext().getString(R.string.invalid_email_id));
                                }
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SSO, userInfo, null);
                            }
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, cVar.a, cVar.b);
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.getInstance().hideProgressDialog();
                            LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SSO, new UserInfo(), null);
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, tILSDKExceptionDto.a, tILSDKExceptionDto.b);
                        }
                    });
                }

                public void onError() {
                    LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_FAILURE_SSO, new UserInfo(), null);
                    LoginManager.getInstance().handleSSOLoginFailure(loginInfo, SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED");
                }
            });
        } else {
            LoginManager.getInstance().loginToGaana(LoginType.GAANA, "", loginInfo);
        }
    }

    public void loginSilently(final LoginInfo loginInfo, final IOnLoginCompleted iOnLoginCompleted) {
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().b(loginInfo.getEmailId(), loginInfo.getPassword(), new m() {
                        public void onLoginSuccess() {
                            LoginImplEmail.this.retrieveTicketAndLogin(loginInfo.getLoginType(), loginInfo);
                        }

                        public void onLoginFailure(c cVar) {
                            if (LoginImplEmail.this.shouldLogoutUser(loginInfo, cVar)) {
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
            LoginManager.getInstance().loginToGaana(LoginType.GAANA, "", loginInfo);
        }
    }

    public void register(final LoginInfo loginInfo, final IOnLoginCompleted iOnLoginCompleted) {
        this.initialTime = System.currentTimeMillis();
        if (isSsoEnabled(loginInfo)) {
            LoginManager.getInstance().isSsoSdkInitialized(new SsoSdkInitialized() {
                public void onSuccess() {
                    a.b().a(loginInfo.getEmailId(), loginInfo.getSex().substring(0, 1).toLowerCase(), "", loginInfo.getPassword(), loginInfo.getFullname(), false, null, null, null, new v() {
                        public void onSuccess() {
                            LoginManager.getInstance().setLoginInfo(loginInfo);
                            LoginManager.getInstance().hideProgressDialog();
                            Bundle bundle = new Bundle();
                            bundle.putString("extra_email", loginInfo.getEmailId());
                            LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_VERIFY, new UserInfo(), bundle);
                        }

                        public void onFailure(c cVar) {
                            LoginManager.getInstance().hideProgressDialog();
                            UserInfo userInfo = new UserInfo();
                            if (cVar.a == SsoErrorCodes.PROXY_OR_DEFUNC_MAIL) {
                                userInfo.setError(GaanaApplication.getContext().getString(R.string.invalid_email_id));
                            }
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, cVar.a, cVar.b);
                            LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, userInfo, null);
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.getInstance().hideProgressDialog();
                            LoginManager.getInstance().handleSSOLoginFailure(loginInfo, tILSDKExceptionDto.a, tILSDKExceptionDto.b);
                            LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                        }
                    });
                }

                public void onError() {
                    LoginManager.getInstance().hideProgressDialog();
                    LoginManager.getInstance().handleSSOLoginFailure(loginInfo, SsoErrorCodes.SDK_NOT_INITIALIZED, "SDK_NOT_INITIALIZED");
                    LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                }
            });
            return;
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("type", "registrationtoken");
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/user.php?");
        uRLManager.c(1);
        uRLManager.a(String.class);
        uRLManager.a(hashMap);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                String str = (String) obj;
                if (str != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        hashMap.put(CBConstant.KEY, jSONObject.getString(CBConstant.KEY));
                        hashMap.put("regtoken", Util.a(Util.b(jSONObject.getString("regtoken")), Constants.bc));
                        hashMap.put("type", "registration");
                        hashMap.put("email", loginInfo.getEmailId());
                        hashMap.put(LoginManager.TAG_FULL_NAME, loginInfo.getFullname());
                        hashMap.put(LoginManager.TAG_PASSWORD, loginInfo.getPassword());
                        hashMap.put("gender", "NA");
                        obj = d.a().c();
                        if (obj == null) {
                            obj = "";
                        }
                        hashMap.put("referrer_user_id", obj);
                        URLManager uRLManager = new URLManager();
                        uRLManager.a(String.class);
                        uRLManager.c(1);
                        uRLManager.a(Priority.IMMEDIATE);
                        uRLManager.b(Boolean.valueOf(false));
                        uRLManager.a("https://api.gaana.com/user.php?");
                        uRLManager.a(hashMap);
                        uRLManager.i(false);
                        i.a().a(new af() {
                            /* JADX WARNING: Removed duplicated region for block: B:27:0x0090  */
                            /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
                            /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
                            /* JADX WARNING: Removed duplicated region for block: B:27:0x0090  */
                            public void onRetreivalComplete(java.lang.Object r5) {
                                /*
                                r4 = this;
                                r5 = (java.lang.String) r5;
                                r0 = android.text.TextUtils.isEmpty(r5);
                                r1 = 0;
                                if (r0 != 0) goto L_0x003a;
                            L_0x0009:
                                r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0032 }
                                r0.<init>(r5);	 Catch:{ Exception -> 0x0032 }
                                r5 = "Status";
                                r5 = r0.has(r5);	 Catch:{ Exception -> 0x0032 }
                                if (r5 == 0) goto L_0x001d;
                            L_0x0016:
                                r5 = "Status";
                                r5 = r0.getString(r5);	 Catch:{ Exception -> 0x0032 }
                                goto L_0x001e;
                            L_0x001d:
                                r5 = r1;
                            L_0x001e:
                                r2 = "token";
                                r2 = r0.has(r2);	 Catch:{ Exception -> 0x002d }
                                if (r2 == 0) goto L_0x0038;
                            L_0x0026:
                                r2 = "token";
                                r0 = r0.getString(r2);	 Catch:{ Exception -> 0x002d }
                                goto L_0x003c;
                            L_0x002d:
                                r0 = move-exception;
                                r3 = r0;
                                r0 = r5;
                                r5 = r3;
                                goto L_0x0034;
                            L_0x0032:
                                r5 = move-exception;
                                r0 = r1;
                            L_0x0034:
                                com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
                                r5 = r0;
                            L_0x0038:
                                r0 = r1;
                                goto L_0x003c;
                            L_0x003a:
                                r5 = r1;
                                r0 = r5;
                            L_0x003c:
                                com.utilities.Util.b();
                                r2 = com.gaana.login.LoginImplEmail.AnonymousClass4.this;
                                r2 = com.gaana.login.LoginImplEmail.this;
                                r2.sendTrackingEventsOnRegistration(r5, r0);
                                if (r5 == 0) goto L_0x0090;
                            L_0x0048:
                                r2 = "1";
                                r5 = r5.equals(r2);
                                if (r5 == 0) goto L_0x0086;
                            L_0x0050:
                                r5 = android.text.TextUtils.isEmpty(r0);
                                if (r5 != 0) goto L_0x007c;
                            L_0x0056:
                                r5 = com.managers.u.a();
                                r0 = "Registration";
                                r1 = "Registration Success";
                                r2 = "Registration - Email";
                                r5.a(r0, r1, r2);
                                r5 = com.gaana.analytics.AppsFlyer.getInstance();
                                r0 = "email.register";
                                r5.reportUserRegistration(r0);
                                r5 = com.gaana.login.LoginImplEmail.AnonymousClass4.this;
                                r5 = com.gaana.login.LoginImplEmail.this;
                                r0 = com.gaana.login.LoginImplEmail.AnonymousClass4.this;
                                r0 = r5;
                                r1 = com.gaana.login.LoginImplEmail.AnonymousClass4.this;
                                r1 = r6;
                                r5.login(r0, r1);
                                goto L_0x0099;
                            L_0x007c:
                                r5 = com.gaana.login.LoginManager.getInstance();
                                r0 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_REGISTRATION_FAILED;
                                r5.loginCompleted(r0, r1, r1);
                                goto L_0x0099;
                            L_0x0086:
                                r5 = com.gaana.login.LoginManager.getInstance();
                                r0 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_REGISTRATION_FAILED;
                                r5.loginCompleted(r0, r1, r1);
                                goto L_0x0099;
                            L_0x0090:
                                r5 = com.gaana.login.LoginManager.getInstance();
                                r0 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_REGISTRATION_FAILED;
                                r5.loginCompleted(r0, r1, r1);
                            L_0x0099:
                                return;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.LoginImplEmail$4$AnonymousClass1.onRetreivalComplete(java.lang.Object):void");
                            }

                            public void onErrorResponse(BusinessObject businessObject) {
                                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                            }
                        }, uRLManager);
                    } catch (Exception unused) {
                        LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_REGISTRATION_FAILED, null, null);
            }
        }, uRLManager);
    }

    public void logout(LoginInfo loginInfo) {
        if (isSsoEnabled(loginInfo) || loginInfo.isUnverifiedSSOUser()) {
            signOutFromSso();
        }
        GooglePlusLogin.getInstance().disconnect();
    }

    public HashMap<String, String> getLoginParams(String str, LoginInfo loginInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", LoginManager.TAG_TYPE_VALUE);
        if (isSsoEnabled(loginInfo)) {
            hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_SSO);
            hashMap.put(LoginManager.TAG_SSO_TICKET_ID, str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("login_mode", "email");
            } catch (JSONException unused) {
            }
            hashMap.put(LoginManager.TAG_SSO_USER_INFO, jSONObject.toString());
        } else {
            hashMap.put(LoginManager.TAG_USER_NAME, loginInfo.getEmailId());
            hashMap.put(LoginManager.TAG_PASSWORD, loginInfo.getPassword());
        }
        return hashMap;
    }

    public boolean isSsoEnabled(LoginInfo loginInfo) {
        return loginInfo.getLoginMode() == LoginMode.SSO;
    }

    public LoginType getLoginType() {
        return LoginType.GAANA;
    }

    private void sendTrackingEventsOnRegistration(String str, String str2) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (this.initialTime != 0) {
            long j = timeInMillis - this.initialTime;
            if (str == null) {
                str = "Email";
                str2 = "Failure";
            } else if (!str.equals("1")) {
                str = "Email";
                str2 = "Failure";
            } else if (TextUtils.isEmpty(str2)) {
                str = "Email";
                str2 = "Failure";
            } else {
                str = "Email";
                str2 = "Success";
            }
            Constants.a("Signup", j, str, str2);
        }
    }
}

package com.gaana.login;

import android.text.TextUtils;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.models.User.LoginType;
import com.services.m;
import java.util.HashMap;

public class LoginImplPhoneNumber extends LoginClient {
    public boolean isSsoEnabled(LoginInfo loginInfo) {
        return false;
    }

    public void loginOnUpgrade() {
    }

    public void login(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        LoginManager.getInstance().loginToGaana(loginInfo.getLoginType(), loginInfo.getFbPhoneloginAccessToken(), loginInfo);
    }

    public void loginSilently(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        LoginManager.getInstance().loginToGaana(loginInfo.getLoginType(), loginInfo.getFbPhoneloginAccessToken(), loginInfo);
    }

    public void register(LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        login(loginInfo, iOnLoginCompleted);
    }

    public void logout(LoginInfo loginInfo) {
        m.a().b();
    }

    public LoginType getLoginType() {
        return LoginType.PHONENUMBER;
    }

    public HashMap<String, String> getLoginParams(String str, LoginInfo loginInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", LoginManager.TAG_TYPE_VALUE);
        hashMap.put(LoginManager.TAG_SUBTYPE, LoginManager.TAG_SUBTYPE_PHONE_LOGIN);
        hashMap.put(LoginManager.TAG_FB_ACCESS_TOKEN, str);
        if (!TextUtils.isEmpty(loginInfo.getFullname())) {
            hashMap.put(LoginManager.TAG_FULL_NAME, loginInfo.getFullname());
        }
        if (!TextUtils.isEmpty(loginInfo.getSex())) {
            hashMap.put("gender", "NA");
        }
        if (!TextUtils.isEmpty(loginInfo.getDob())) {
            hashMap.put(LoginManager.TAG_DOB, "00/00/0000");
        }
        return hashMap;
    }
}

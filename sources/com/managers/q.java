package com.managers;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.User.LoginType;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.services.d;
import com.services.n;
import com.til.colombia.dmp.android.DmpManager;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class q {
    private static q a;

    public static q a() {
        if (a == null) {
            a = new q();
        }
        return a;
    }

    public void b() {
        if (DmpManager.getInstance() == null) {
            DmpManager.initialize(GaanaApplication.getContext());
        }
        if (DmpManager.getInstance() != null && Constants.el) {
            DmpManager.disablePersona(GaanaApplication.getContext());
        }
    }

    public void a(String str, String str2) {
        if (DmpManager.getInstance() != null && !Constants.el) {
            DmpManager.getInstance().addEvents(str, str2);
        }
    }

    public void c() {
        if (DmpManager.getInstance() != null) {
            DmpManager.getInstance().completeSession();
        }
    }

    public String[] d() {
        if (DmpManager.getInstance() != null) {
            return DmpManager.getInstance().getAudsArray();
        }
        return new String[0];
    }

    public String e() {
        String str = "";
        if (DmpManager.getInstance() == null) {
            return str;
        }
        String[] audsArray = DmpManager.getInstance().getAudsArray();
        if (audsArray != null) {
            for (String str2 : audsArray) {
                if (TextUtils.isEmpty(str)) {
                    str = str2;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    stringBuilder.append(str2);
                    str = stringBuilder.toString();
                }
            }
        }
        return str;
    }

    public void a(UserInfo userInfo) {
        if (userInfo != null) {
            try {
                if (!(!userInfo.getLoginStatus() || userInfo.getUserProfile() == null || DmpManager.getInstance() == null)) {
                    if (!Constants.el) {
                        CharSequence dob = userInfo.getUserProfile().getDob();
                        if (dob.contains("-")) {
                            dob = dob.replaceAll("-", "/");
                        }
                        if (dob != null) {
                            if (dob.contains("0000")) {
                                dob = "00/00/0000";
                            }
                            if (!TextUtils.isEmpty(dob)) {
                                DmpManager.getInstance().addEvents("DOB", dob);
                            }
                        }
                        String sex = userInfo.getUserProfile().getSex();
                        if (!TextUtils.isEmpty(sex)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(sex.substring(0, 1).toUpperCase());
                            stringBuilder.append(sex.substring(1));
                            sex = stringBuilder.toString();
                            if (!TextUtils.isEmpty(sex)) {
                                DmpManager.getInstance().addEvents("gender", sex);
                            }
                        }
                        LoginType loginType = GaanaApplication.getInstance().getCurrentUser().getLoginType();
                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
                        Calendar instance = Calendar.getInstance();
                        long b = d.a().b(0, "PREFERENCE_DMP_LAGIN_DATE", false);
                        instance.setTimeInMillis(b);
                        if (loginType != null && (b == 0 || gregorianCalendar.get(1) > instance.get(1) || gregorianCalendar.get(2) > instance.get(2) || gregorianCalendar.get(5) > instance.get(5))) {
                            d.a().a(gregorianCalendar.getTimeInMillis(), "PREFERENCE_DMP_LAGIN_DATE", false);
                            if (a(loginType).equalsIgnoreCase("Gaana")) {
                                DmpManager.getInstance().addEvents("Login", "email");
                            } else {
                                DmpManager.getInstance().addEvents("Login", a(loginType));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private String a(LoginType loginType) {
        String str = "";
        if (loginType == LoginType.FB) {
            return "Facebook";
        }
        if (loginType == LoginType.GAANA) {
            return "Gaana";
        }
        if (loginType == LoginType.GOOGLE) {
            return "Google";
        }
        return loginType == LoginType.PHONENUMBER ? "Phone" : str;
    }

    public void b(UserInfo userInfo) {
        if (DmpManager.getInstance() != null && !Constants.el) {
            String str = "";
            if (!(userInfo == null || userInfo.getUserSubscriptionData() == null)) {
                int accountType = userInfo.getUserSubscriptionData().getAccountType();
                StringBuilder stringBuilder;
                if (accountType == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("freeuser");
                    str = stringBuilder.toString();
                } else if (accountType == 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("freeuser");
                    str = stringBuilder.toString();
                } else if (accountType == 2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("trialuser");
                    str = stringBuilder.toString();
                } else if (accountType == 3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("paiduser");
                    str = stringBuilder.toString();
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = "freeuser";
            }
            DmpManager.getInstance().addEvents("ua", str);
        }
    }

    public void f() {
        if (DmpManager.getInstance() != null && !Constants.el) {
            if (Util.ai()) {
                com.i.d.a(new Runnable() {
                    public void run() {
                        Object a = n.a(d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
                        if (a instanceof Languages) {
                            Languages languages = (Languages) a;
                            if (languages != null) {
                                ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                                if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                                    Iterator it = arrListBusinessObj.iterator();
                                    while (it.hasNext()) {
                                        Language language = (Language) it.next();
                                        if (language.isPrefered() == 1) {
                                            if (!TextUtils.isEmpty(language.getLanguage())) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append("PrefLang:");
                                                stringBuilder.append(language.getLanguage());
                                                DmpManager.getInstance().addEvents("ua", stringBuilder.toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            } else {
                Object a = n.a(d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
                if (a instanceof Languages) {
                    Languages languages = (Languages) a;
                    if (languages != null) {
                        ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                        if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                            Iterator it = arrListBusinessObj.iterator();
                            while (it.hasNext()) {
                                Language language = (Language) it.next();
                                if (language.isPrefered() == 1) {
                                    if (!TextUtils.isEmpty(language.getLanguage())) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("PrefLang:");
                                        stringBuilder.append(language.getLanguage());
                                        DmpManager.getInstance().addEvents("ua", stringBuilder.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

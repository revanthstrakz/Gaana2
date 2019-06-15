package com.managers;

import android.content.Context;
import android.provider.Settings.Secure;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginClient;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.services.l.af;
import com.timespointssdk.f.a;
import in.til.core.integrations.TILSDKExceptionDto;
import java.security.MessageDigest;

public class ak {
    private boolean a = false;

    public void a(final a aVar) {
        final Context context = GaanaApplication.getContext();
        if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            final LoginClient loginClient = LoginManager.getInstance().getLoginClient(LoginManager.getInstance().getLoginInfo().getLoginType());
            loginClient.getUserId(LoginManager.getInstance().getLoginInfo(), new af() {
                public void onRetreivalComplete(Object obj) {
                    final String str = (String) obj;
                    loginClient.getTicketId(LoginManager.getInstance().getLoginInfo(), new af() {
                        public void onRetreivalComplete(Object obj) {
                            in.til.core.a.b().a(context, "Gaana", "Gaana", str, ak.this.a(context), (String) obj, aVar);
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            in.til.core.a.b().a(context, "Gaana", "Gaana", str, ak.this.a(context), null, aVar);
                        }
                    });
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    in.til.core.a.b().a(context, "Gaana", "Gaana", null, ak.this.a(context), null, aVar);
                }
            });
            return;
        }
        in.til.core.a.b().a(context, "Gaana", "Gaana", null, a(context), null, aVar);
    }

    public void a(final String str, final String str2, String str3, a aVar) {
        str3 = a(str3, str);
        if (this.a) {
            in.til.core.a.b().b(str, str2, str3, null);
        } else {
            a(new a() {
                public void a() {
                    ak.this.a = true;
                    in.til.core.a.b().b(str, str2, str3, null);
                }

                public void a(Exception exception) {
                    ak.this.a = false;
                }

                public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                    ak.this.a = false;
                }
            });
        }
    }

    public void a() {
        if (this.a) {
            in.til.core.a.b().d(null);
        }
    }

    public void b() {
        if (this.a) {
            in.til.core.a.b().e(null);
            this.a = false;
        }
    }

    private String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Gaana");
        stringBuilder.append("|");
        stringBuilder.append(str2);
        stringBuilder.append("|");
        stringBuilder.append(str);
        stringBuilder.append("|");
        stringBuilder.append("d185d85999f7eac1edfce7cfc91b2a8d4f17bf6fd61e9bde805b3941b3151318");
        stringBuilder.append("|");
        stringBuilder.append("timespoint");
        return a(stringBuilder.toString());
    }

    private String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(255 & b);
                if (toHexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(toHexString);
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private String a(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}

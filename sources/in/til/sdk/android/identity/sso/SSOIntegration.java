package in.til.sdk.android.identity.sso;

import android.content.Context;
import com.login.nativesso.a.aa;
import com.login.nativesso.a.ab;
import com.login.nativesso.a.ac;
import com.login.nativesso.a.ad;
import com.login.nativesso.a.ae;
import com.login.nativesso.a.af;
import com.login.nativesso.a.ag;
import com.login.nativesso.a.ah;
import com.login.nativesso.a.d;
import com.login.nativesso.a.e;
import com.login.nativesso.a.f;
import com.login.nativesso.a.h;
import com.login.nativesso.a.i;
import com.login.nativesso.a.j;
import com.login.nativesso.a.k;
import com.login.nativesso.a.l;
import com.login.nativesso.a.m;
import com.login.nativesso.a.n;
import com.login.nativesso.a.o;
import com.login.nativesso.a.p;
import com.login.nativesso.a.q;
import com.login.nativesso.a.r;
import com.login.nativesso.a.s;
import com.login.nativesso.a.t;
import com.login.nativesso.a.u;
import com.login.nativesso.a.v;
import com.login.nativesso.a.w;
import com.login.nativesso.a.x;
import com.login.nativesso.a.y;
import com.login.nativesso.a.z;
import com.login.nativesso.d.c;
import com.login.nativesso.e.g;
import in.til.core.integrations.b;
import in.til.core.integrations.b.a;
import java.util.HashMap;

public class SSOIntegration extends b<Void> {
    public static final a FACTORY = new a() {
        public String key() {
            return SSOIntegration.NSSO;
        }

        public b<?> create(HashMap hashMap, in.til.core.a aVar) {
            return new SSOIntegration(hashMap, aVar);
        }
    };
    private static final String NSSO = "nsso";

    public SSOIntegration(HashMap hashMap, in.til.core.a aVar) {
    }

    public boolean nSSOcheckIfSdkInitialized() {
        return c.a().c();
    }

    public void nSSOpassiveLogin(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (x) cVar);
    }

    public void nSSOresendSignUpOtp(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (r) cVar);
    }

    public void nSSOinitializeSDK(Context context, String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(context, str, str2, str3, (s) cVar);
    }

    public void nSSOgooglePlusLogin(String str, in.til.core.integrations.c cVar) {
        c.a().b(str, (x) cVar);
    }

    public void nSSOfbLogin(in.til.core.integrations.c cVar) {
        c.a().a((x) cVar);
    }

    public Context nSSOgetAppContext() {
        return c.a().d();
    }

    public void nSSOgetLoginOtp(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (k) cVar);
    }

    public void nSSOloginWithEmail(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (m) cVar);
    }

    public void nSSOloginWithEmail(String str, String str2, String str3, String str4, String str5, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, str4, str5, (m) cVar);
    }

    public void updateUserPermissions(String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, (ac) cVar);
    }

    public void blockUserChannel(in.til.core.integrations.c cVar) {
        c.a().a((com.login.nativesso.a.a) cVar);
    }

    public void nSSOloginWithMobile(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().b(str, str2, (m) cVar);
    }

    public void nSSOloginWithMobile(String str, String str2, String str3, String str4, String str5, in.til.core.integrations.c cVar) {
        c.a().b(str, str2, str3, str4, str5, (m) cVar);
    }

    public void nSSOresendFPOtp(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (q) cVar);
    }

    public void nSSOsignOutUser(Context context, boolean z, in.til.core.integrations.c cVar) {
        c.a().a(context, z, (u) cVar);
    }

    public void nSSOgetUserDetails(in.til.core.integrations.c cVar) {
        c.a().a((l) cVar);
    }

    public void nSSOsetUserPassword(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (t) cVar);
    }

    public void nSSOchangeUserPassword(String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, (com.login.nativesso.a.b) cVar);
    }

    public void nSSOsendFPOtpEmail(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (i) cVar);
    }

    public void nSSOsendFPOtpMobile(String str, in.til.core.integrations.c cVar) {
        c.a().b(str, (i) cVar);
    }

    public void nSSOverifyFPOtpEmail(String str, String str2, String str3, String str4, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, str4, (ag) cVar);
    }

    public void nSSOverifyFPOtpMobile(String str, String str2, String str3, String str4, in.til.core.integrations.c cVar) {
        c.a().b(str, str2, str3, str4, (ag) cVar);
    }

    public void nSSOsignUpUser(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8, in.til.core.integrations.c cVar) {
        g gVar = new g();
        gVar.a(str);
        gVar.b(str2);
        gVar.c(str3);
        gVar.d(str4);
        gVar.e(str5);
        gVar.a(z);
        gVar.f(str6);
        gVar.g(str7);
        gVar.h(str8);
        gVar.a((v) cVar);
        c.a().a(gVar);
    }

    public void nSSOverifySignUpUser(String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, (ah) cVar);
    }

    public void nSSOgetGlobalSession(boolean z, in.til.core.integrations.c cVar) {
        c.a().a(z, (j) cVar);
    }

    public void nSSOgetAppSession(in.til.core.integrations.c cVar) {
        c.a().a((h) cVar);
    }

    public void nSSOcopyGlobalSessionToApp(in.til.core.integrations.c cVar) {
        c.a().a((e) cVar);
    }

    public void nSSOmigrateCurrentSession(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (n) cVar);
    }

    public void nSSOcreateUnverifiedSession(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (f) cVar);
    }

    public void nSSOupdateMobile(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (aa) cVar);
    }

    public void nSSOverifyMobile(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, (af) cVar);
    }

    public void nSSOaddEmail(String str, in.til.core.integrations.c cVar) {
        c.a().b(str, (aa) cVar);
    }

    public void nSSOverifyEmail(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().b(str, str2, (af) cVar);
    }

    public void nSSOupdateUserDetails(String str, String str2, String str3, String str4, String str5, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, str4, str5, (ab) cVar);
    }

    public void nSSOupdateSocialAccessToken(String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, (w) cVar);
    }

    public void nSSOupdateProfilePic(in.til.core.integrations.c cVar) {
        c.a().a((ad) cVar);
    }

    public void nSSOupdateProfilePic(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (ad) cVar);
    }

    public void nSSOloginWithSocial(String str, String str2, String str3, boolean z, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, z, (x) cVar);
    }

    public void nSSOlinkGooglePlus(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (w) cVar);
    }

    public void nSSOlinkFacebook(in.til.core.integrations.c cVar) {
        c.a().a((w) cVar);
    }

    public void nSSOgetPicByGooglePlus(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (y) cVar);
    }

    public void nSSOgetPicByFacebook(in.til.core.integrations.c cVar) {
        c.a().a((y) cVar);
    }

    public void nSSOdelinkSocial(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (com.login.nativesso.a.g) cVar);
    }

    public void nSSOrenewTicket(in.til.core.integrations.c cVar) {
        c.a().a((p) cVar);
    }

    public void nSSOcheckUserExist(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (d) cVar);
    }

    public void nSSOsignUpUsingMobile(String str, String str2, String str3, String str4, String str5, String str6, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, str4, str5, str6, (o) cVar);
    }

    public void nSSOfbLoginWithRequiredPermission(String[] strArr, in.til.core.integrations.c cVar) {
        c.a().a(strArr, (x) cVar);
    }

    public void nSSOvalidatePassword(String str, String str2, String str3, in.til.core.integrations.c cVar) {
        c.a().a(str, str2, str3, (ae) cVar);
    }

    public void nSSOloginViaGsmaMobile(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().c(str, str2, (m) cVar);
    }

    public void nSSOgetUserDetailsLocal(in.til.core.integrations.c cVar) {
        c.a().b((l) cVar);
    }

    public void nSSOsetBaseURL(String str, String str2, in.til.core.integrations.c cVar) {
        c.a().a(str, str2);
    }

    public void nSSOloginViaTrueCaller(String str, in.til.core.integrations.c cVar) {
        c.a().a(str, (z) cVar);
    }
}

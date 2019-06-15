package in.til.core.integrations;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.util.HashMap;

public abstract class b<T> {

    public interface a {
        b<?> create(HashMap<String, String> hashMap, in.til.core.a aVar);

        String key();
    }

    public void blockUserChannel(c cVar) {
    }

    public String checkLeapStatus(Application application) {
        return null;
    }

    public int checkLeapStatusId(Application application) {
        return -1;
    }

    public void dmpAddMultipleEvents(String str, String str2) {
    }

    public String dmpAudience() {
        return null;
    }

    public String[] dmpAudienceArray() {
        return null;
    }

    public void dmpDisableDMP(Context context) {
    }

    public void dmpDisablePersona(Context context) {
    }

    public void dmpDisableTPPixel(Context context) {
    }

    public void dmpEnableDMP(Context context) {
    }

    public void dmpEnablePersona(Context context) {
    }

    public void dmpEnableTPPixel(Context context) {
    }

    public void dmpEvent(String str, String str2) {
    }

    public void dmpInitialize(Context context) {
    }

    public void dmpUpdateAudience() {
    }

    public void dmpaddReferer(String str) {
    }

    public void dmpcompleteSession() {
    }

    public void dmpsyncSSO(String str) {
    }

    public T getInstance() {
        return null;
    }

    public String getLeapExistingEmail(Application application) {
        return null;
    }

    public void iBeatInitialize(Context context, long j, int i, c cVar) {
    }

    public void iBeatSendDataOnStopOrPause() {
    }

    public void iBeatValidateSavePageTrendLogDTO(a aVar, c cVar) {
    }

    public void initializeLeap(Application application, String str) {
    }

    public void initializeLeap(Application application, String str, String str2) {
    }

    public boolean isInStable(Application application) {
        return false;
    }

    public boolean isInitialized(Application application) {
        return false;
    }

    public boolean isOptedIn(Application application) {
        return false;
    }

    public boolean isOptedOut(Application application) {
        return false;
    }

    public boolean isSaverCardReady(Application application) {
        return false;
    }

    public boolean isSdkReady(Application application) {
        return false;
    }

    public void nSSOaddEmail(String str, c cVar) {
    }

    public void nSSOchangeUserPassword(String str, String str2, String str3, c cVar) {
    }

    public boolean nSSOcheckIfSdkInitialized() {
        return false;
    }

    public void nSSOcheckUserExist(String str, c cVar) {
    }

    public void nSSOcopyGlobalSessionToApp(c cVar) {
    }

    public void nSSOcreateUnverifiedSession(String str, c cVar) {
    }

    public void nSSOdelinkSocial(String str, c cVar) {
    }

    public void nSSOfbLogin(c cVar) {
    }

    public void nSSOfbLoginWithRequiredPermission(String[] strArr, c cVar) {
    }

    public Context nSSOgetAppContext() {
        return null;
    }

    public void nSSOgetAppSession(c cVar) {
    }

    public void nSSOgetGlobalSession(boolean z, c cVar) {
    }

    public void nSSOgetLoginOtp(String str, String str2, c cVar) {
    }

    public void nSSOgetPicByFacebook(c cVar) {
    }

    public void nSSOgetPicByGooglePlus(String str, c cVar) {
    }

    public void nSSOgetUserDetails(c cVar) {
    }

    public void nSSOgetUserDetailsLocal(c cVar) {
    }

    public void nSSOgooglePlusLogin(String str, c cVar) {
    }

    public void nSSOinitializeSDK(Context context, String str, String str2, String str3, c cVar) {
    }

    public void nSSOlinkFacebook(c cVar) {
    }

    public void nSSOlinkGooglePlus(String str, c cVar) {
    }

    public void nSSOloginViaGsmaMobile(String str, String str2, c cVar) {
    }

    public void nSSOloginViaTrueCaller(String str, c cVar) {
    }

    public void nSSOloginWithEmail(String str, String str2, c cVar) {
    }

    public void nSSOloginWithEmail(String str, String str2, String str3, String str4, String str5, c cVar) {
    }

    public void nSSOloginWithMobile(String str, String str2, c cVar) {
    }

    public void nSSOloginWithMobile(String str, String str2, String str3, String str4, String str5, c cVar) {
    }

    public void nSSOloginWithSocial(String str, String str2, String str3, boolean z, c cVar) {
    }

    public void nSSOmigrateCurrentSession(String str, c cVar) {
    }

    public void nSSOpassiveLogin(String str, c cVar) {
    }

    public void nSSOrenewTicket(c cVar) {
    }

    public void nSSOresendFPOtp(String str, String str2, c cVar) {
    }

    public void nSSOresendSignUpOtp(String str, String str2, c cVar) {
    }

    public void nSSOsendFPOtpEmail(String str, c cVar) {
    }

    public void nSSOsendFPOtpMobile(String str, c cVar) {
    }

    public void nSSOsetBaseURL(String str, String str2, c cVar) {
    }

    public void nSSOsetUserPassword(String str, String str2, c cVar) {
    }

    public void nSSOsignOutUser(Context context, boolean z, c cVar) {
    }

    public void nSSOsignUpUser(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8, c cVar) {
    }

    public void nSSOsignUpUsingMobile(String str, String str2, String str3, String str4, String str5, String str6, c cVar) {
    }

    public void nSSOupdateMobile(String str, c cVar) {
    }

    public void nSSOupdateProfilePic(c cVar) {
    }

    public void nSSOupdateProfilePic(String str, c cVar) {
    }

    public void nSSOupdateSocialAccessToken(String str, String str2, String str3, c cVar) {
    }

    public void nSSOupdateUserDetails(String str, String str2, String str3, String str4, String str5, c cVar) {
    }

    public void nSSOvalidatePassword(String str, String str2, String str3, c cVar) {
    }

    public void nSSOverifyEmail(String str, String str2, c cVar) {
    }

    public void nSSOverifyFPOtpEmail(String str, String str2, String str3, String str4, c cVar) {
    }

    public void nSSOverifyFPOtpMobile(String str, String str2, String str3, String str4, c cVar) {
    }

    public void nSSOverifyMobile(String str, String str2, c cVar) {
    }

    public void nSSOverifySignUpUser(String str, String str2, String str3, c cVar) {
    }

    public int optIn(Application application) {
        return -1;
    }

    public int optOut(Application application) {
        return -1;
    }

    public void tpApplicationPaused(c cVar) {
    }

    public void tpForeground(Context context, c cVar) {
    }

    public void tpInit(Context context, String str, String str2, String str3, String str4, String str5, c cVar) {
    }

    public void tpLogActivityWithCode(String str, String str2, String str3, c cVar) {
    }

    public void tpLogout(c cVar) {
    }

    public void tpShowProfile(Context context, String str, c cVar) {
    }

    public void tprBuySubscription(Activity activity, a aVar, a aVar2, HashMap<String, String> hashMap) {
    }

    public void tprProceedToPay(Activity activity, a aVar, a aVar2, HashMap<String, String> hashMap) {
    }

    public void tprSetParams(Activity activity, String str, String str2) {
    }

    public void tprSubscribeLoggedInUser(Activity activity, a aVar, a aVar2) {
    }

    public void updateUserPermissions(String str, String str2, String str3, c cVar) {
    }
}

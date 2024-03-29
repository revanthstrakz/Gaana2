package com.facebook.login;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.CustomTabMainActivity;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.login.LoginClient.Request;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomTabLoginMethodHandler extends WebLoginMethodHandler {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int CHALLENGE_LENGTH = 20;
    private static final String[] CHROME_PACKAGES = new String[]{"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    public static final Creator<CustomTabLoginMethodHandler> CREATOR = new Creator() {
        public CustomTabLoginMethodHandler createFromParcel(Parcel parcel) {
            return new CustomTabLoginMethodHandler(parcel);
        }

        public CustomTabLoginMethodHandler[] newArray(int i) {
            return new CustomTabLoginMethodHandler[i];
        }
    };
    private static final String CUSTOM_TABS_SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final int CUSTOM_TAB_REQUEST_CODE = 1;
    private String currentPackage;
    private String expectedChallenge;

    public int describeContents() {
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public String getNameForLogging() {
        return "custom_tab";
    }

    /* Access modifiers changed, original: protected */
    public String getSSODevice() {
        return "chrome_custom_tab";
    }

    CustomTabLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        this.expectedChallenge = Utility.generateRandomString(20);
    }

    /* Access modifiers changed, original: 0000 */
    public AccessTokenSource getTokenSource() {
        return AccessTokenSource.CHROME_CUSTOM_TAB;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean tryAuthorize(Request request) {
        if (!isCustomTabsAllowed()) {
            return false;
        }
        Bundle addExtraParameters = addExtraParameters(getParameters(request), request);
        Intent intent = new Intent(this.loginClient.getActivity(), CustomTabMainActivity.class);
        intent.putExtra(CustomTabMainActivity.EXTRA_PARAMS, addExtraParameters);
        intent.putExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE, getChromePackage());
        this.loginClient.getFragment().startActivityForResult(intent, 1);
        return true;
    }

    private boolean isCustomTabsAllowed() {
        return isCustomTabsEnabled() && getChromePackage() != null && Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext());
    }

    private boolean isCustomTabsEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(Utility.getMetadataApplicationId(this.loginClient.getActivity()));
        return appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCustomTabsEnabled();
    }

    private String getChromePackage() {
        if (this.currentPackage != null) {
            return this.currentPackage;
        }
        FragmentActivity activity = this.loginClient.getActivity();
        List<ResolveInfo> queryIntentServices = activity.getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
        if (queryIntentServices != null) {
            HashSet hashSet = new HashSet(Arrays.asList(CHROME_PACKAGES));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && hashSet.contains(serviceInfo.packageName)) {
                    this.currentPackage = serviceInfo.packageName;
                    return this.currentPackage;
                }
            }
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return super.onActivityResult(i, i2, intent);
        }
        Request pendingRequest = this.loginClient.getPendingRequest();
        if (i2 == -1) {
            onCustomTabComplete(intent.getStringExtra(CustomTabMainActivity.EXTRA_URL), pendingRequest);
            return true;
        }
        super.onComplete(pendingRequest, null, new FacebookOperationCanceledException());
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    private void onCustomTabComplete(java.lang.String r7, com.facebook.login.LoginClient.Request r8) {
        /*
        r6 = this;
        if (r7 == 0) goto L_0x00b3;
    L_0x0002:
        r0 = com.facebook.CustomTabMainActivity.getRedirectUrl();
        r0 = r7.startsWith(r0);
        if (r0 == 0) goto L_0x00b3;
    L_0x000c:
        r7 = android.net.Uri.parse(r7);
        r0 = r7.getQuery();
        r0 = com.facebook.internal.Utility.parseUrlQueryString(r0);
        r7 = r7.getFragment();
        r7 = com.facebook.internal.Utility.parseUrlQueryString(r7);
        r0.putAll(r7);
        r7 = r6.validateChallengeParam(r0);
        r1 = 0;
        if (r7 != 0) goto L_0x0035;
    L_0x002a:
        r7 = new com.facebook.FacebookException;
        r0 = "Invalid state parameter";
        r7.<init>(r0);
        super.onComplete(r8, r1, r7);
        return;
    L_0x0035:
        r7 = "error";
        r7 = r0.getString(r7);
        if (r7 != 0) goto L_0x0043;
    L_0x003d:
        r7 = "error_type";
        r7 = r0.getString(r7);
    L_0x0043:
        r2 = "error_msg";
        r2 = r0.getString(r2);
        if (r2 != 0) goto L_0x0051;
    L_0x004b:
        r2 = "error_message";
        r2 = r0.getString(r2);
    L_0x0051:
        if (r2 != 0) goto L_0x0059;
    L_0x0053:
        r2 = "error_description";
        r2 = r0.getString(r2);
    L_0x0059:
        r3 = "error_code";
        r3 = r0.getString(r3);
        r4 = com.facebook.internal.Utility.isNullOrEmpty(r3);
        r5 = -1;
        if (r4 != 0) goto L_0x006b;
    L_0x0066:
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x006b }
        goto L_0x006c;
    L_0x006b:
        r3 = r5;
    L_0x006c:
        r4 = com.facebook.internal.Utility.isNullOrEmpty(r7);
        if (r4 == 0) goto L_0x007e;
    L_0x0072:
        r4 = com.facebook.internal.Utility.isNullOrEmpty(r2);
        if (r4 == 0) goto L_0x007e;
    L_0x0078:
        if (r3 != r5) goto L_0x007e;
    L_0x007a:
        super.onComplete(r8, r0, r1);
        goto L_0x00b3;
    L_0x007e:
        if (r7 == 0) goto L_0x0099;
    L_0x0080:
        r0 = "access_denied";
        r0 = r7.equals(r0);
        if (r0 != 0) goto L_0x0090;
    L_0x0088:
        r0 = "OAuthAccessDeniedException";
        r0 = r7.equals(r0);
        if (r0 == 0) goto L_0x0099;
    L_0x0090:
        r7 = new com.facebook.FacebookOperationCanceledException;
        r7.<init>();
        super.onComplete(r8, r1, r7);
        goto L_0x00b3;
    L_0x0099:
        r0 = 4201; // 0x1069 float:5.887E-42 double:2.0756E-320;
        if (r3 != r0) goto L_0x00a6;
    L_0x009d:
        r7 = new com.facebook.FacebookOperationCanceledException;
        r7.<init>();
        super.onComplete(r8, r1, r7);
        goto L_0x00b3;
    L_0x00a6:
        r0 = new com.facebook.FacebookRequestError;
        r0.<init>(r3, r7, r2);
        r7 = new com.facebook.FacebookServiceException;
        r7.<init>(r0, r2);
        super.onComplete(r8, r1, r7);
    L_0x00b3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.CustomTabLoginMethodHandler.onCustomTabComplete(java.lang.String, com.facebook.login.LoginClient$Request):void");
    }

    /* Access modifiers changed, original: protected */
    public void putChallengeParam(JSONObject jSONObject) throws JSONException {
        jSONObject.put("7_challenge", this.expectedChallenge);
    }

    private boolean validateChallengeParam(Bundle bundle) {
        try {
            String string = bundle.getString("state");
            if (string == null) {
                return false;
            }
            return new JSONObject(string).getString("7_challenge").equals(this.expectedChallenge);
        } catch (JSONException unused) {
            return false;
        }
    }

    CustomTabLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.expectedChallenge = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.expectedChallenge);
    }
}

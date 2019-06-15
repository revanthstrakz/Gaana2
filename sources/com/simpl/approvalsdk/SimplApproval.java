package com.simpl.approvalsdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.simpl.approvalsdk.model.UserApproval;

public class SimplApproval {
    private static final String TAG = "SimplApproval";
    private static SimplApproval instance;
    private Context applicationContext;
    private String approvalApi;
    private SimplUserApprovalListenerV2 globalSimplUserApprovalListener;
    private boolean isInDebug;
    private boolean isInSandboxMode;
    private boolean isInStaging;
    private String merchantId;
    private SimplUser simplUser;
    private UserApproval userApproval = new UserApproval();

    private SimplApproval(Context context, String str, boolean z) {
        this.merchantId = str;
        this.applicationContext = context.getApplicationContext();
        this.isInSandboxMode = z;
    }

    public static SimplApproval getInstance() {
        if (instance != null) {
            return instance;
        }
        Log.e(TAG, "Error: Simpl sdk is not initialized properly.");
        return null;
    }

    public static void init(@NonNull Context context, @NonNull String str) {
        if (instance != null) {
            Log.w(TAG, "SimplApproval is already initialized");
        } else {
            instance = new SimplApproval(context, str, false);
        }
    }

    public void addFlags(String... strArr) {
        a.a.b = strArr;
    }

    /* Access modifiers changed, original: protected */
    public String getApprovalsApiBaseUrl() {
        return this.isInStaging ? "https://staging-approvals-api.getsimpl.com/api/v2/" : this.isInSandboxMode ? "https://sandbox-approvals-api.getsimpl.com/api/v2/" : this.isInDebug ? this.approvalApi : "https://approvals-api.getsimpl.com/api/v2/";
    }

    /* Access modifiers changed, original: 0000 */
    public Context getCurrentApplicationContext() {
        return this.applicationContext;
    }

    @NonNull
    public SimplUserApprovalListenerV2 getGlobalSimplUserApprovalListener() {
        return this.globalSimplUserApprovalListener;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public SimplUser getSimplUser() {
        return this.simplUser;
    }

    public boolean isSimplApproved() {
        return this.userApproval != null && this.userApproval.isApproved();
    }

    public SimplUserApprovalRequest isUserApproved(@NonNull SimplUser simplUser) {
        return new SimplUserApprovalRequest(simplUser, this.merchantId);
    }

    public boolean isUserFirstTransaction() {
        return this.userApproval != null && this.userApproval.isFirstTransaction();
    }

    public void runInDebugMode() {
        this.isInDebug = true;
    }

    public void runInSandboxMode() {
        this.isInSandboxMode = true;
    }

    public void runInStagingMode() {
        this.isInStaging = true;
    }

    public void setApprovalApi(String str) {
        this.approvalApi = str;
    }

    public void setGlobalSimplUserApprovalListener(@NonNull SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        this.globalSimplUserApprovalListener = simplUserApprovalListenerV2;
    }

    public void setMerchantId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.merchantId = str;
        }
    }

    public void setSimplUser(SimplUser simplUser) {
        this.simplUser = simplUser;
    }

    /* Access modifiers changed, original: 0000 */
    public void setUserApproval(UserApproval userApproval) {
        this.userApproval = userApproval;
    }
}

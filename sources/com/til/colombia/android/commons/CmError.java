package com.til.colombia.android.commons;

import com.facebook.internal.AnalyticsEvents;

public class CmError {
    public static final CmError NETWORK_ERROR = new CmError(100, "Network Error");
    public static final int NETWORK_ERROR_CODE = 100;
    public static final CmError NO_FILL_ERROR = new CmError(102, "No more feed available");
    public static final int NO_FILL_ERROR_CODE = 102;
    public static final CmError SERVER_ERROR = new CmError(101, "Server Error");
    public static final int SERVER_ERROR_CODE = 101;
    public static final CmError UNKNOWN = new CmError(-1, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    public static final int UNKNOWN_CODE = -1;
    public static final CmError WRONG_FORMAT_ERROR = new CmError(103, "Response format is not correct");
    public static final int WRONG_FORMAT_ERROR_CODE = 103;
    private final int errorCode;
    private final String errorMsg;

    CmError(int i, String str) {
        this.errorCode = i;
        this.errorMsg = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}

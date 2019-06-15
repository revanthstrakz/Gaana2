package com.inmobi.commons.core.network;

import com.google.api.client.http.HttpStatusCodes;

public final class NetworkError {
    public ErrorCode a;
    public String b;

    public enum ErrorCode {
        NETWORK_UNAVAILABLE_ERROR(0),
        UNKNOWN_ERROR(-1),
        NETWORK_IO_ERROR(-2),
        OUT_OF_MEMORY_ERROR(-3),
        INVALID_ENCRYPTED_RESPONSE_RECEIVED(-4),
        RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT(-5),
        GZIP_DECOMPRESSION_FAILED(-6),
        BAD_REQUEST(-7),
        GDPR_COMPLIANCE_ENFORCED(-8),
        HTTP_NO_CONTENT(HttpStatusCodes.STATUS_CODE_NO_CONTENT),
        HTTP_NOT_MODIFIED(HttpStatusCodes.STATUS_CODE_NOT_MODIFIED),
        HTTP_SEE_OTHER(HttpStatusCodes.STATUS_CODE_SEE_OTHER),
        HTTP_SERVER_NOT_FOUND(404),
        HTTP_MOVED_TEMP(HttpStatusCodes.STATUS_CODE_FOUND),
        HTTP_INTERNAL_SERVER_ERROR(500),
        HTTP_NOT_IMPLEMENTED(501),
        HTTP_BAD_GATEWAY(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY),
        HTTP_SERVER_NOT_AVAILABLE(HttpStatusCodes.STATUS_CODE_SERVICE_UNAVAILABLE),
        HTTP_GATEWAY_TIMEOUT(504),
        HTTP_VERSION_NOT_SUPPORTED(505);
        
        private int a;

        private ErrorCode(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }

        public static ErrorCode fromValue(int i) {
            if (400 <= i && 500 > i) {
                return BAD_REQUEST;
            }
            for (ErrorCode errorCode : values()) {
                if (errorCode.a == i) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    public NetworkError(ErrorCode errorCode, String str) {
        this.a = errorCode;
        this.b = str;
    }
}

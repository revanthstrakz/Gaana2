package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;

final class LoginStatusClient extends PlatformServiceClient {
    private final String loggerRef;

    LoginStatusClient(Context context, String str, String str2) {
        super(context, NativeProtocol.MESSAGE_GET_LOGIN_STATUS_REQUEST, NativeProtocol.MESSAGE_GET_LOGIN_STATUS_REPLY, NativeProtocol.PROTOCOL_VERSION_20170411, str);
        this.loggerRef = str2;
    }

    /* Access modifiers changed, original: protected */
    public void populateRequestBundle(Bundle bundle) {
        bundle.putString(NativeProtocol.EXTRA_LOGGER_REF, this.loggerRef);
    }
}

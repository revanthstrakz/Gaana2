package com.helpshift.common.domain.network;

import com.google.api.client.http.HttpStatusCodes;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.g;
import java.util.Map;

public class f implements h {
    private final h a;

    public f(h hVar) {
        this.a = hVar;
    }

    public g c(Map<String, String> map) {
        g c = this.a.c(map);
        int i = c.a;
        if (i >= 200 && i < HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
            return c;
        }
        NetworkException networkException = NetworkException.UNHANDLED_STATUS_CODE;
        networkException.serverStatusCode = c.a;
        throw RootAPIException.a(null, networkException);
    }
}

package com.helpshift.common.domain.network;

import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.g;
import java.util.HashMap;
import java.util.Map;

public class c implements h {
    private final h a;

    public c(h hVar) {
        this.a = hVar;
    }

    public g c(Map<String, String> map) {
        g c = this.a.c(new HashMap(map));
        int i = c.a;
        if (!a(i)) {
            return c;
        }
        NetworkException networkException = NetworkException.NON_RETRIABLE;
        networkException.serverStatusCode = i;
        throw RootAPIException.a(null, networkException);
    }

    private boolean a(int i) {
        return j.w.contains(Integer.valueOf(i));
    }
}

package com.helpshift.common.domain.network;

import com.google.android.gms.wallet.WalletConstants;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.g;
import java.util.Map;

public class e implements h {
    private final h a;

    public e(h hVar) {
        this.a = hVar;
    }

    public g c(Map<String, String> map) {
        g c = this.a.c(map);
        if (c.a != WalletConstants.ERROR_CODE_INVALID_TRANSACTION) {
            return c;
        }
        throw RootAPIException.a(null, NetworkException.CONVERSATION_ARCHIVED);
    }
}

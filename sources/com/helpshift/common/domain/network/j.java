package com.helpshift.common.domain.network;

import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.gms.wallet.WalletConstants;
import com.google.api.client.http.HttpStatusCodes;
import java.util.Set;

public interface j {
    public static final Integer a = Integer.valueOf(0);
    public static final Integer b = Integer.valueOf(1);
    public static final Integer c = Integer.valueOf(2);
    public static final Integer d = Integer.valueOf(3);
    public static final Integer e = Integer.valueOf(4);
    public static final Integer f = Integer.valueOf(5);
    public static final Integer g = Integer.valueOf(6);
    public static final Integer h = Integer.valueOf(200);
    public static final Integer i = Integer.valueOf(HttpStatusCodes.STATUS_CODE_NOT_MODIFIED);
    public static final Integer j = Integer.valueOf(400);
    public static final Integer k = Integer.valueOf(401);
    public static final Integer l = Integer.valueOf(403);
    public static final Integer m = Integer.valueOf(404);
    public static final Integer n = Integer.valueOf(405);
    public static final Integer o = Integer.valueOf(WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED);
    public static final Integer p = Integer.valueOf(408);
    public static final Integer q = Integer.valueOf(WalletConstants.ERROR_CODE_INVALID_TRANSACTION);
    public static final Integer r = Integer.valueOf(WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE);
    public static final Integer s = Integer.valueOf(413);
    public static final Integer t = Integer.valueOf(SsoErrorCodes.WRONG_OTP);
    public static final Integer u = Integer.valueOf(HttpStatusCodes.STATUS_CODE_UNPROCESSABLE_ENTITY);
    public static final Integer v = Integer.valueOf(500);
    public static final Set<Integer> w = new NetworkErrorCodes$1();
}

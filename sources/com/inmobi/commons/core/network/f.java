package com.inmobi.commons.core.network;

import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public final class f extends b {
    public final /* bridge */ /* synthetic */ d a() {
        return super.a();
    }

    public f(c cVar) {
        super(cVar);
    }

    /* Access modifiers changed, original: protected|final */
    public final d b() {
        d dVar = new d();
        StringBuilder stringBuilder;
        try {
            int responseCode = this.c.getResponseCode();
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b.q);
            stringBuilder.append("Response code: ");
            stringBuilder.append(responseCode);
            dVar.c = this.c.getContentLength();
            this.c.disconnect();
        } catch (SocketTimeoutException unused) {
            dVar.b = new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString());
        } catch (IOException unused2) {
            dVar.b = new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString());
        } catch (OutOfMemoryError unused3) {
            dVar.b = new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString());
        } catch (Exception e) {
            dVar.b = new NetworkError(ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR.toString());
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "GenericException");
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(e.getMessage());
                hashMap.put("message", stringBuilder2.toString());
                b.a();
                b.a("root", "ExceptionCaught", hashMap);
            } catch (Exception unused4) {
                stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
            }
        } catch (Throwable th) {
            this.c.disconnect();
        }
        return dVar;
    }
}

package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zal;

final class zaco extends zal {
    private final /* synthetic */ zacm zakv;

    public zaco(zacm zacm, Looper looper) {
        this.zakv = zacm;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                PendingResult pendingResult = (PendingResult) message.obj;
                synchronized (this.zakv.zadn) {
                    if (pendingResult == null) {
                        try {
                            this.zakv.zako.zad(new Status(13, "Transform returned null"));
                        } catch (Throwable th) {
                        }
                    } else if (pendingResult instanceof zacd) {
                        this.zakv.zako.zad(((zacd) pendingResult).getStatus());
                    } else {
                        this.zakv.zako.zaa(pendingResult);
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String str = "TransformedResultImpl";
                String str2 = "Runtime exception on the transformation worker thread: ";
                String valueOf = String.valueOf(runtimeException.getMessage());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                throw runtimeException;
            default:
                int i = message.what;
                StringBuilder stringBuilder = new StringBuilder(70);
                stringBuilder.append("TransformationResultHandler received unknown message type: ");
                stringBuilder.append(i);
                Log.e("TransformedResultImpl", stringBuilder.toString());
                return;
        }
    }
}

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.comscore.utils.Constants;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;

final class zze extends GmsClientSupervisor implements Callback {
    private final Handler mHandler;
    private final HashMap<zza, zzf> zzdu = new HashMap();
    private final Context zzdv;
    private final ConnectionTracker zzdw;
    private final long zzdx;
    private final long zzdy;

    zze(Context context) {
        this.zzdv = context.getApplicationContext();
        this.mHandler = new com.google.android.gms.internal.common.zze(context.getMainLooper(), this);
        this.zzdw = ConnectionTracker.getInstance();
        this.zzdx = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.zzdy = Constants.USER_SESSION_INACTIVE_PERIOD;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zza zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzdu) {
            String valueOf;
            zzf zzf = (zzf) this.zzdu.get(zza);
            if (zzf != null) {
                this.mHandler.removeMessages(0, zza);
                if (!zzf.zza(serviceConnection)) {
                    zzf.zza(serviceConnection, str);
                    switch (zzf.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzf.getComponentName(), zzf.getBinder());
                            break;
                        case 2:
                            zzf.zze(str);
                            break;
                        default:
                            break;
                    }
                }
                valueOf = String.valueOf(zza);
                StringBuilder stringBuilder = new StringBuilder(81 + String.valueOf(valueOf).length());
                stringBuilder.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                stringBuilder.append(valueOf);
                throw new IllegalStateException(stringBuilder.toString());
            }
            zzf = new zzf(this, zza);
            zzf.zza(serviceConnection, str);
            zzf.zze(str);
            this.zzdu.put(zza, zzf);
            if (this.zzdu.size() > 200) {
                str = String.valueOf(zza);
                int size = this.zzdu.size();
                StringBuilder stringBuilder2 = new StringBuilder(51 + String.valueOf(str).length());
                stringBuilder2.append("Adding ");
                stringBuilder2.append(str);
                stringBuilder2.append(", and there are now ");
                stringBuilder2.append(size);
                stringBuilder2.append(" connections.");
                Log.wtf("GmsClientSupervisor", stringBuilder2.toString(), new Exception());
            }
            if (zzf.zzs() > 100) {
                int zzs = zzf.zzs();
                valueOf = String.valueOf(zza);
                StringBuilder stringBuilder3 = new StringBuilder(41 + String.valueOf(valueOf).length());
                stringBuilder3.append("There are");
                stringBuilder3.append(zzs);
                stringBuilder3.append(" clients for service ");
                stringBuilder3.append(valueOf);
                Log.wtf("GmsClientSupervisor", stringBuilder3.toString(), new Exception());
            }
            isBound = zzf.isBound();
        }
        return isBound;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zza zza, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzdu) {
            zzf zzf = (zzf) this.zzdu.get(zza);
            String valueOf;
            StringBuilder stringBuilder;
            if (zzf == null) {
                valueOf = String.valueOf(zza);
                stringBuilder = new StringBuilder(50 + String.valueOf(valueOf).length());
                stringBuilder.append("Nonexistent connection status for service config: ");
                stringBuilder.append(valueOf);
                throw new IllegalStateException(stringBuilder.toString());
            } else if (zzf.zza(serviceConnection)) {
                zzf.zzb(serviceConnection, str);
                if (zzf.zzr()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza), this.zzdx);
                }
            } else {
                valueOf = String.valueOf(zza);
                stringBuilder = new StringBuilder(76 + String.valueOf(valueOf).length());
                stringBuilder.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                stringBuilder.append(valueOf);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        zza zza;
        zzf zzf;
        switch (message.what) {
            case 0:
                synchronized (this.zzdu) {
                    zza = (zza) message.obj;
                    zzf = (zzf) this.zzdu.get(zza);
                    if (zzf != null && zzf.zzr()) {
                        if (zzf.isBound()) {
                            zzf.zzf("GmsClientSupervisor");
                        }
                        this.zzdu.remove(zza);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzdu) {
                    zza = (zza) message.obj;
                    zzf = (zzf) this.zzdu.get(zza);
                    if (zzf != null && zzf.getState() == 3) {
                        String valueOf = String.valueOf(zza);
                        StringBuilder stringBuilder = new StringBuilder(47 + String.valueOf(valueOf).length());
                        stringBuilder.append("Timeout waiting for ServiceConnection callback ");
                        stringBuilder.append(valueOf);
                        Log.e("GmsClientSupervisor", stringBuilder.toString(), new Exception());
                        ComponentName componentName = zzf.getComponentName();
                        if (componentName == null) {
                            componentName = zza.getComponentName();
                        }
                        if (componentName == null) {
                            componentName = new ComponentName(zza.getPackage(), "unknown");
                        }
                        zzf.onServiceDisconnected(componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}

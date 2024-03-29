package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public abstract class RemoteCreator<T> {
    private final String zzib;
    private T zzic;

    @KeepForSdk
    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String str) {
            super(str);
        }

        public RemoteCreatorException(String str, Throwable th) {
            super(str, th);
        }
    }

    @KeepForSdk
    protected RemoteCreator(String str) {
        this.zzib = str;
    }

    @KeepForSdk
    public abstract T getRemoteCreator(IBinder iBinder);

    /* Access modifiers changed, original: protected|final */
    @KeepForSdk
    public final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        if (this.zzic == null) {
            Preconditions.checkNotNull(context);
            context = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (context == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            try {
                this.zzic = getRemoteCreator((IBinder) context.getClassLoader().loadClass(this.zzib).newInstance());
            } catch (ClassNotFoundException e) {
                throw new RemoteCreatorException("Could not load creator class.", e);
            } catch (InstantiationException e2) {
                throw new RemoteCreatorException("Could not instantiate creator.", e2);
            } catch (IllegalAccessException e3) {
                throw new RemoteCreatorException("Could not access creator.", e3);
            }
        }
        return this.zzic;
    }
}

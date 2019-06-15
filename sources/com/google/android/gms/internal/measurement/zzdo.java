package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzdo extends zzr implements zzdn {
    public zzdo() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzdn asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        if (queryLocalInterface instanceof zzdn) {
            return (zzdn) queryLocalInterface;
        }
        return new zzdp(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzdq zzdq = null;
        String readString;
        String readString2;
        IInterface queryLocalInterface;
        IBinder readStrongBinder;
        IBinder readStrongBinder2;
        zzdt zzdt;
        switch (i) {
            case 1:
                initialize(Stub.asInterface(parcel.readStrongBinder()), (zzdy) zzs.zza(parcel, zzdy.CREATOR), parcel.readLong());
                break;
            case 2:
                logEvent(parcel.readString(), parcel.readString(), (Bundle) zzs.zza(parcel, Bundle.CREATOR), zzs.zza(parcel), zzs.zza(parcel), parcel.readLong());
                break;
            case 3:
                readString = parcel.readString();
                readString2 = parcel.readString();
                Bundle bundle = (Bundle) zzs.zza(parcel, Bundle.CREATOR);
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    queryLocalInterface = readStrongBinder3.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder3);
                    }
                }
                logEventAndBundle(readString, readString2, bundle, zzdq, parcel.readLong());
                break;
            case 4:
                setUserProperty(parcel.readString(), parcel.readString(), Stub.asInterface(parcel.readStrongBinder()), zzs.zza(parcel), parcel.readLong());
                break;
            case 5:
                readString = parcel.readString();
                readString2 = parcel.readString();
                boolean zza = zzs.zza(parcel);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getUserProperties(readString, readString2, zza, zzdq);
                break;
            case 6:
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getMaxUserProperties(readString, zzdq);
                break;
            case 7:
                setUserId(parcel.readString(), parcel.readLong());
                break;
            case 8:
                setConditionalUserProperty((Bundle) zzs.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 9:
                clearConditionalUserProperty(parcel.readString(), parcel.readString(), (Bundle) zzs.zza(parcel, Bundle.CREATOR));
                break;
            case 10:
                readString = parcel.readString();
                readString2 = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getConditionalUserProperties(readString, readString2, zzdq);
                break;
            case 11:
                setMeasurementEnabled(zzs.zza(parcel), parcel.readLong());
                break;
            case 12:
                resetAnalyticsData(parcel.readLong());
                break;
            case 13:
                setMinimumSessionDuration(parcel.readLong());
                break;
            case 14:
                setSessionTimeoutDuration(parcel.readLong());
                break;
            case 15:
                setCurrentScreen(Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readLong());
                break;
            case 16:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getCurrentScreenName(zzdq);
                break;
            case 17:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getCurrentScreenClass(zzdq);
                break;
            case 18:
                zzdw zzdw;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
                    if (queryLocalInterface instanceof zzdw) {
                        zzdw = (zzdw) queryLocalInterface;
                    } else {
                        zzdw = new zzdx(readStrongBinder);
                    }
                }
                setInstanceIdProvider(zzdw);
                break;
            case 19:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getCachedAppInstanceId(zzdq);
                break;
            case 20:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getAppInstanceId(zzdq);
                break;
            case 21:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                getGmpAppId(zzdq);
                break;
            case 22:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder);
                    }
                }
                generateEventId(zzdq);
                break;
            case 23:
                beginAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 24:
                endAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 25:
                onActivityStarted(Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 26:
                onActivityStopped(Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 27:
                onActivityCreated(Stub.asInterface(parcel.readStrongBinder()), (Bundle) zzs.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 28:
                onActivityDestroyed(Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 29:
                onActivityPaused(Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 30:
                onActivityResumed(Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 31:
                IObjectWrapper asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface = readStrongBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder2);
                    }
                }
                onActivitySaveInstanceState(asInterface, zzdq, parcel.readLong());
                break;
            case 32:
                Bundle bundle2 = (Bundle) zzs.zza(parcel, Bundle.CREATOR);
                readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface = readStrongBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder2);
                    }
                }
                performAction(bundle2, zzdq, parcel.readLong());
                break;
            case 33:
                logHealthData(parcel.readInt(), parcel.readString(), Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 34:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (queryLocalInterface instanceof zzdt) {
                        zzdt = (zzdt) queryLocalInterface;
                    } else {
                        zzdt = new zzdv(readStrongBinder);
                    }
                }
                setEventInterceptor(zzdt);
                break;
            case 35:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (queryLocalInterface instanceof zzdt) {
                        zzdt = (zzdt) queryLocalInterface;
                    } else {
                        zzdt = new zzdv(readStrongBinder);
                    }
                }
                registerOnMeasurementEventListener(zzdt);
                break;
            case 36:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (queryLocalInterface instanceof zzdt) {
                        zzdt = (zzdt) queryLocalInterface;
                    } else {
                        zzdt = new zzdv(readStrongBinder);
                    }
                }
                unregisterOnMeasurementEventListener(zzdt);
                break;
            case 37:
                initForTests(zzs.zzb(parcel));
                break;
            case 38:
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    queryLocalInterface = readStrongBinder4.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (queryLocalInterface instanceof zzdq) {
                        zzdq = (zzdq) queryLocalInterface;
                    } else {
                        zzdq = new zzds(readStrongBinder4);
                    }
                }
                getTestFlag(zzdq, parcel.readInt());
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}

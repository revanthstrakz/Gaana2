package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;
import java.util.Map;

public abstract class zzbil extends zzex implements zzbik {
    public zzbil() {
        super("com.google.android.gms.ads.measurement.IAppMeasurementProxy");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String appInstanceId;
        switch (i) {
            case 1:
                performAction((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                Bundle performActionWithResponse = performActionWithResponse((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzey.zzb(parcel2, performActionWithResponse);
                break;
            case 3:
                logEvent(parcel.readString(), parcel.readString(), (Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza(parcel.readString(), parcel.readString(), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 5:
                Map userProperties = getUserProperties(parcel.readString(), parcel.readString(), zzey.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeMap(userProperties);
                break;
            case 6:
                i = getMaxUserProperties(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(i);
                break;
            case 7:
                setConditionalUserProperty((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 8:
                clearConditionalUserProperty(parcel.readString(), parcel.readString(), (Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 9:
                List conditionalUserProperties = getConditionalUserProperties(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeList(conditionalUserProperties);
                break;
            case 10:
                appInstanceId = getAppInstanceId();
                parcel2.writeNoException();
                parcel2.writeString(appInstanceId);
                break;
            case 11:
                appInstanceId = getGmpAppId();
                parcel2.writeNoException();
                parcel2.writeString(appInstanceId);
                break;
            case 12:
                long generateEventId = generateEventId();
                parcel2.writeNoException();
                parcel2.writeLong(generateEventId);
                break;
            case 13:
                beginAdUnitExposure(parcel.readString());
                parcel2.writeNoException();
                break;
            case 14:
                endAdUnitExposure(parcel.readString());
                parcel2.writeNoException();
                break;
            case 15:
                zzb(Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 16:
                appInstanceId = getCurrentScreenName();
                parcel2.writeNoException();
                parcel2.writeString(appInstanceId);
                break;
            case 17:
                appInstanceId = getCurrentScreenClass();
                parcel2.writeNoException();
                parcel2.writeString(appInstanceId);
                break;
            case 18:
                appInstanceId = getAppIdOrigin();
                parcel2.writeNoException();
                parcel2.writeString(appInstanceId);
                break;
            default:
                return false;
        }
        return true;
    }
}

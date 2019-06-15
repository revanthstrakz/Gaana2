package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public final class zzc extends zzm<zzg> {
    private final zze zzda;

    public zzc(Context context, zze zze) {
        super(context, "FaceNativeHandle", "face");
        this.zzda = zze;
        zzq();
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzn zzn) {
        int i = 0;
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zzg) zzq()).zzc(ObjectWrapper.wrap(byteBuffer), zzn);
            Face[] faceArr = new Face[zzc.length];
            int i2 = 0;
            while (i2 < zzc.length) {
                FaceParcel[] faceParcelArr;
                Face[] faceArr2;
                int i3;
                Landmark[] landmarkArr;
                Contour[] contourArr;
                FaceParcel faceParcel = zzc[i2];
                int i4 = faceParcel.id;
                PointF pointF = new PointF(faceParcel.centerX, faceParcel.centerY);
                float f = faceParcel.width;
                float f2 = faceParcel.height;
                float f3 = faceParcel.zzdb;
                float f4 = faceParcel.zzdc;
                float f5 = faceParcel.zzdd;
                LandmarkParcel[] landmarkParcelArr = faceParcel.zzde;
                if (landmarkParcelArr == null) {
                    faceParcelArr = zzc;
                    faceArr2 = faceArr;
                    i3 = i2;
                    landmarkArr = new Landmark[i];
                } else {
                    landmarkArr = new Landmark[landmarkParcelArr.length];
                    int i5 = i;
                    while (i5 < landmarkParcelArr.length) {
                        LandmarkParcel landmarkParcel = landmarkParcelArr[i5];
                        faceParcelArr = zzc;
                        LandmarkParcel[] landmarkParcelArr2 = landmarkParcelArr;
                        faceArr2 = faceArr;
                        i3 = i2;
                        landmarkArr[i5] = new Landmark(new PointF(landmarkParcel.x, landmarkParcel.y), landmarkParcel.type);
                        i5++;
                        zzc = faceParcelArr;
                        landmarkParcelArr = landmarkParcelArr2;
                        faceArr = faceArr2;
                        i2 = i3;
                    }
                    faceParcelArr = zzc;
                    faceArr2 = faceArr;
                    i3 = i2;
                }
                zza[] zzaArr = faceParcel.zzdf;
                if (zzaArr == null) {
                    contourArr = new Contour[0];
                } else {
                    Contour[] contourArr2 = new Contour[zzaArr.length];
                    for (int i6 = 0; i6 < zzaArr.length; i6++) {
                        zza zza = zzaArr[i6];
                        contourArr2[i6] = new Contour(zza.zzcz, zza.type);
                    }
                    contourArr = contourArr2;
                }
                faceArr2[i3] = new Face(i4, pointF, f, f2, f3, f4, f5, landmarkArr, contourArr, faceParcel.zzcg, faceParcel.zzch, faceParcel.zzci);
                i2 = i3 + 1;
                zzc = faceParcelArr;
                faceArr = faceArr2;
                i = 0;
            }
            return faceArr;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    public final boolean zzd(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zzg) zzq()).zzd(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzm() throws RemoteException {
        ((zzg) zzq()).zzn();
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException {
        zzj asInterface;
        if (zzp.zza(context, "com.google.android.gms.vision.dynamite.face")) {
            asInterface = zzk.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            asInterface = zzk.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        if (asInterface == null) {
            return null;
        }
        return asInterface.newFaceDetector(ObjectWrapper.wrap(context), this.zzda);
    }
}

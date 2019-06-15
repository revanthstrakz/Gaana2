package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.PlatformVersion;
import java.io.InputStream;

final class zzaqc implements zzazy<zzabr> {
    private final /* synthetic */ String zzdpv;
    private final /* synthetic */ zzapw zzdtp;
    private final /* synthetic */ boolean zzdtz;
    private final /* synthetic */ double zzdua;
    private final /* synthetic */ boolean zzdub;

    zzaqc(zzapw zzapw, boolean z, double d, boolean z2, String str) {
        this.zzdtp = zzapw;
        this.zzdtz = z;
        this.zzdua = d;
        this.zzdub = z2;
        this.zzdpv = str;
    }

    @TargetApi(19)
    private final zzabr zzd(InputStream inputStream) {
        Bitmap decodeStream;
        Options options = new Options();
        options.inDensity = (int) (160.0d * this.zzdua);
        if (!this.zzdub) {
            options.inPreferredConfig = Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e) {
            zzbbd.zzb("Error grabbing image.", e);
            decodeStream = null;
        }
        if (decodeStream == null) {
            this.zzdtp.zzh(2, this.zzdtz);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (PlatformVersion.isAtLeastKitKat() && zzaxz.zzza()) {
            int width = decodeStream.getWidth();
            int height = decodeStream.getHeight();
            int allocationByteCount = decodeStream.getAllocationByteCount();
            long j = uptimeMillis2 - uptimeMillis;
            boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
            StringBuilder stringBuilder = new StringBuilder(108);
            stringBuilder.append("Decoded image w: ");
            stringBuilder.append(width);
            stringBuilder.append(" h:");
            stringBuilder.append(height);
            stringBuilder.append(" bytes: ");
            stringBuilder.append(allocationByteCount);
            stringBuilder.append(" time: ");
            stringBuilder.append(j);
            stringBuilder.append(" on ui thread: ");
            stringBuilder.append(z);
            zzaxz.v(stringBuilder.toString());
        }
        return new zzabr(new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(this.zzdpv), this.zzdua);
    }

    public final /* synthetic */ Object zzwf() {
        this.zzdtp.zzh(2, this.zzdtz);
        return null;
    }

    @TargetApi(19)
    public final /* synthetic */ Object zze(InputStream inputStream) {
        return zzd(inputStream);
    }
}

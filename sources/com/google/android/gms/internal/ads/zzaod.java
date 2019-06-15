package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzark
public final class zzaod extends zzaok {
    private final Context mContext;
    private final Map<String, String> zzczl;

    public zzaod(zzbgg zzbgg, Map<String, String> map) {
        super(zzbgg, "storePicture");
        this.zzczl = map;
        this.mContext = zzbgg.zzabw();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzda("Activity context is not available");
            return;
        }
        zzbv.zzlf();
        if (zzayh.zzam(this.mContext).zzqt()) {
            String str = (String) this.zzczl.get("iurl");
            String lastPathSegment;
            if (TextUtils.isEmpty(str)) {
                zzda("Image url cannot be empty.");
                return;
            } else if (URLUtil.isValidUrl(str)) {
                lastPathSegment = Uri.parse(str).getLastPathSegment();
                zzbv.zzlf();
                if (zzayh.zzdz(lastPathSegment)) {
                    Resources resources = zzbv.zzlj().getResources();
                    zzbv.zzlf();
                    Builder zzal = zzayh.zzal(this.mContext);
                    zzal.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
                    zzal.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
                    zzal.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzaoe(this, str, lastPathSegment));
                    zzal.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaof(this));
                    zzal.create().show();
                    return;
                }
                str = "Image type not recognized: ";
                lastPathSegment = String.valueOf(lastPathSegment);
                zzda(lastPathSegment.length() != 0 ? str.concat(lastPathSegment) : new String(str));
                return;
            } else {
                lastPathSegment = "Invalid image url: ";
                str = String.valueOf(str);
                zzda(str.length() != 0 ? lastPathSegment.concat(str) : new String(lastPathSegment));
                return;
            }
        }
        zzda("Feature is not supported by the device.");
    }
}

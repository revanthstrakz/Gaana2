package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzark
public final class zzanx extends zzaok {
    private final Context mContext;
    private final Map<String, String> zzczl;
    private String zzdoz = zzcy("description");
    private long zzdpa = zzcz("start_ticks");
    private long zzdpb = zzcz("end_ticks");
    private String zzdpc = zzcy("summary");
    private String zzdpd = zzcy(PlaceFields.LOCATION);

    public zzanx(zzbgg zzbgg, Map<String, String> map) {
        super(zzbgg, "createCalendarEvent");
        this.zzczl = map;
        this.mContext = zzbgg.zzabw();
    }

    private final String zzcy(String str) {
        return TextUtils.isEmpty((CharSequence) this.zzczl.get(str)) ? "" : (String) this.zzczl.get(str);
    }

    private final long zzcz(String str) {
        str = (String) this.zzczl.get(str);
        if (str == null) {
            return -1;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final void execute() {
        if (this.mContext == null) {
            zzda("Activity context is not available.");
            return;
        }
        zzbv.zzlf();
        if (zzayh.zzam(this.mContext).zzqu()) {
            zzbv.zzlf();
            Builder zzal = zzayh.zzal(this.mContext);
            Resources resources = zzbv.zzlj().getResources();
            zzal.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
            zzal.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
            zzal.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzany(this));
            zzal.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzanz(this));
            zzal.create().show();
            return;
        }
        zzda("This feature is not available on the device.");
    }

    /* Access modifiers changed, original: final */
    @TargetApi(14)
    public final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.zzdoz);
        data.putExtra("eventLocation", this.zzdpd);
        data.putExtra("description", this.zzdpc);
        if (this.zzdpa > -1) {
            data.putExtra("beginTime", this.zzdpa);
        }
        if (this.zzdpb > -1) {
            data.putExtra("endTime", this.zzdpb);
        }
        data.setFlags(C.ENCODING_PCM_MU_LAW);
        return data;
    }
}

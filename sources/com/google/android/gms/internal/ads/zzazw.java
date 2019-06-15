package com.google.android.gms.internal.ads;

final class zzazw implements zzy {
    private final /* synthetic */ String zzdvo;
    private final /* synthetic */ zzazz zzena;

    zzazw(zzazs zzazs, String str, zzazz zzazz) {
        this.zzdvo = str;
        this.zzena = zzazz;
    }

    public final void zzd(zzae zzae) {
        String str = this.zzdvo;
        String zzae2 = zzae.toString();
        StringBuilder stringBuilder = new StringBuilder((21 + String.valueOf(str).length()) + String.valueOf(zzae2).length());
        stringBuilder.append("Failed to load URL: ");
        stringBuilder.append(str);
        stringBuilder.append("\n");
        stringBuilder.append(zzae2);
        zzbbd.zzeo(stringBuilder.toString());
        this.zzena.zzb(null);
    }
}

package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.CastApi;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerClient.GameManagerInstanceResult;
import com.google.android.gms.cast.games.GameManagerClient.GameManagerResult;
import com.google.android.gms.cast.games.GameManagerClient.Listener;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcb extends zzcw {
    private static final String NAMESPACE = zzdk.zzq("com.google.cast.games");
    private static final zzdw zzbf = new zzdw("GameManagerChannel");
    private final CastApi zzhz;
    private final GoogleApiClient zzpb;
    private final Map<String, String> zzvv = new ConcurrentHashMap();
    private final SharedPreferences zzvw;
    private final String zzvx;
    private zzco zzvy;
    private boolean zzvz = false;
    private GameManagerState zzwa;
    private GameManagerState zzwb;
    private String zzwc;
    private JSONObject zzwd;
    private long zzwe = 0;
    private Listener zzwf;
    private final Clock zzwg;
    private String zzwh;

    public zzcb(GoogleApiClient googleApiClient, String str, CastApi castApi) throws IllegalArgumentException, IllegalStateException {
        super(NAMESPACE, "CastGameManagerChannel", null);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("castSessionId cannot be null.");
        } else if (googleApiClient != null && googleApiClient.isConnected() && googleApiClient.hasConnectedApi(Cast.API)) {
            this.zzwg = DefaultClock.getInstance();
            this.zzvx = str;
            this.zzhz = castApi;
            this.zzpb = googleApiClient;
            this.zzvw = r12.getApplicationContext().getSharedPreferences(String.format(Locale.ROOT, "%s.%s", new Object[]{googleApiClient.getContext().getApplicationContext().getPackageName(), "game_manager_channel_data"}), 0);
            this.zzwb = null;
            this.zzwa = new zzcq(0, 0, "", null, new ArrayList(), "", -1);
        } else {
            throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
        }
    }

    public final synchronized PendingResult<GameManagerInstanceResult> zza(GameManagerClient gameManagerClient) throws IllegalArgumentException {
        return this.zzpb.execute(new zzcc(this, gameManagerClient));
    }

    public final synchronized void dispose() throws IllegalStateException {
        if (!this.zzvz) {
            this.zzwa = null;
            this.zzwb = null;
            this.zzwc = null;
            this.zzwd = null;
            this.zzvz = true;
            try {
                this.zzhz.removeMessageReceivedCallbacks(this.zzpb, getNamespace());
            } catch (IOException e) {
                zzbf.w("Exception while detaching game manager channel.", e);
            }
        }
    }

    public final synchronized PendingResult<GameManagerResult> zza(String str, int i, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        return this.zzpb.execute(new zzce(this, i, str, jSONObject));
    }

    public final synchronized void sendGameMessage(String str, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        long j = this.zzwe + 1;
        this.zzwe = j;
        JSONObject zza = zza(j, str, 7, jSONObject);
        if (zza != null) {
            this.zzhz.sendMessage(this.zzpb, getNamespace(), zza.toString());
        }
    }

    public final synchronized PendingResult<GameManagerResult> sendGameRequest(String str, JSONObject jSONObject) throws IllegalStateException {
        zzek();
        return this.zzpb.execute(new zzcf(this, str, jSONObject));
    }

    public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
        zzek();
        return this.zzwa;
    }

    public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
        zzek();
        return this.zzwh;
    }

    private final synchronized String zzn(String str) throws IllegalStateException {
        if (str == null) {
            return null;
        }
        return (String) this.zzvv.get(str);
    }

    public final synchronized void setListener(Listener listener) {
        this.zzwf = listener;
    }

    public final void zzo(String str) {
        Object[] objArr = new Object[1];
        int i = 0;
        objArr[0] = str;
        zzbf.d("message received: %s", objArr);
        try {
            zzcp zzj = zzcp.zzj(new JSONObject(str));
            if (zzj == null) {
                zzbf.w("Could not parse game manager message from string: %s", str);
            } else if ((isInitialized() || zzj.zzxk != null) && !isDisposed()) {
                int i2 = zzj.zzwy == 1 ? 1 : 0;
                if (!(i2 == 0 || TextUtils.isEmpty(zzj.zzxj))) {
                    this.zzvv.put(zzj.zzxh, zzj.zzxj);
                    zzel();
                }
                if (zzj.zzwz == 0) {
                    zza(zzj);
                } else {
                    zzbf.w("Not updating from game message because the message contains error code: %d", Integer.valueOf(zzj.zzwz));
                }
                int i3 = zzj.zzwz;
                switch (i3) {
                    case 0:
                        break;
                    case 1:
                        i = 2001;
                        break;
                    case 2:
                        i = 2003;
                        break;
                    case 3:
                        i = GameManagerClient.STATUS_INCORRECT_VERSION;
                        break;
                    case 4:
                        i = GameManagerClient.STATUS_TOO_MANY_PLAYERS;
                        break;
                    default:
                        zzdw zzdw = zzbf;
                        StringBuilder stringBuilder = new StringBuilder(53);
                        stringBuilder.append("Unknown GameManager protocol status code: ");
                        stringBuilder.append(i3);
                        zzdw.w(stringBuilder.toString(), new Object[0]);
                        i = 13;
                        break;
                }
                if (i2 != 0) {
                    zzb(zzj.zzxi, i, zzj);
                }
                if (isInitialized() && i == 0) {
                    if (this.zzwf != null) {
                        if (!(this.zzwb == null || this.zzwa.equals(this.zzwb))) {
                            this.zzwf.onStateChanged(this.zzwa, this.zzwb);
                        }
                        if (!(this.zzwd == null || this.zzwc == null)) {
                            this.zzwf.onGameMessageReceived(this.zzwc, this.zzwd);
                        }
                    }
                    this.zzwb = null;
                    this.zzwc = null;
                    this.zzwd = null;
                }
            }
        } catch (JSONException e) {
            zzbf.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public final synchronized boolean isDisposed() {
        return this.zzvz;
    }

    private final synchronized boolean isInitialized() {
        return this.zzvy != null;
    }

    public final void zza(long j, int i) {
        zzb(j, i, null);
    }

    private final synchronized void zzek() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
        } else if (isDisposed()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
        }
    }

    private final void zza(String str, int i, JSONObject jSONObject, zzec zzec) {
        long j = this.zzwe + 1;
        this.zzwe = j;
        JSONObject zza = zza(j, str, i, jSONObject);
        if (zza == null) {
            zzec.zza(-1, 2001, null);
            zzbf.w("Not sending request because it was invalid.", new Object[0]);
            return;
        }
        zzed zzed = new zzed(30000);
        zzed.zza(j, zzec);
        zza(zzed);
        this.zzhz.sendMessage(this.zzpb, getNamespace(), zza.toString()).setResultCallback(new zzcg(this, j));
    }

    private final JSONObject zza(long j, String str, int i, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("requestId", j);
            jSONObject2.put("type", i);
            jSONObject2.put("extraMessageData", jSONObject);
            jSONObject2.put("playerId", str);
            jSONObject2.put("playerToken", zzn(str));
            return jSONObject2;
        } catch (JSONException e) {
            zzbf.w("JSONException when trying to create a message: %s", e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:28:0x008a, code skipped:
            return;
     */
    private final synchronized void zza(com.google.android.gms.internal.cast.zzcp r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r10.zzwy;	 Catch:{ all -> 0x008b }
        r1 = 1;
        if (r0 != r1) goto L_0x0007;
    L_0x0006:
        goto L_0x0008;
    L_0x0007:
        r1 = 0;
    L_0x0008:
        r0 = r9.zzwa;	 Catch:{ all -> 0x008b }
        r9.zzwb = r0;	 Catch:{ all -> 0x008b }
        if (r1 == 0) goto L_0x0016;
    L_0x000e:
        r0 = r10.zzxk;	 Catch:{ all -> 0x008b }
        if (r0 == 0) goto L_0x0016;
    L_0x0012:
        r0 = r10.zzxk;	 Catch:{ all -> 0x008b }
        r9.zzvy = r0;	 Catch:{ all -> 0x008b }
    L_0x0016:
        r0 = r9.isInitialized();	 Catch:{ all -> 0x008b }
        if (r0 != 0) goto L_0x001e;
    L_0x001c:
        monitor-exit(r9);
        return;
    L_0x001e:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x008b }
        r6.<init>();	 Catch:{ all -> 0x008b }
        r0 = r10.zzxe;	 Catch:{ all -> 0x008b }
        r0 = r0.iterator();	 Catch:{ all -> 0x008b }
    L_0x0029:
        r1 = r0.hasNext();	 Catch:{ all -> 0x008b }
        if (r1 == 0) goto L_0x0050;
    L_0x002f:
        r1 = r0.next();	 Catch:{ all -> 0x008b }
        r1 = (com.google.android.gms.internal.cast.zzcs) r1;	 Catch:{ all -> 0x008b }
        r2 = r1.getPlayerId();	 Catch:{ all -> 0x008b }
        r3 = new com.google.android.gms.internal.cast.zzcr;	 Catch:{ all -> 0x008b }
        r4 = r1.getPlayerState();	 Catch:{ all -> 0x008b }
        r1 = r1.getPlayerData();	 Catch:{ all -> 0x008b }
        r5 = r9.zzvv;	 Catch:{ all -> 0x008b }
        r5 = r5.containsKey(r2);	 Catch:{ all -> 0x008b }
        r3.<init>(r2, r4, r1, r5);	 Catch:{ all -> 0x008b }
        r6.add(r3);	 Catch:{ all -> 0x008b }
        goto L_0x0029;
    L_0x0050:
        r0 = new com.google.android.gms.internal.cast.zzcq;	 Catch:{ all -> 0x008b }
        r2 = r10.zzxd;	 Catch:{ all -> 0x008b }
        r3 = r10.zzxc;	 Catch:{ all -> 0x008b }
        r4 = r10.zzxg;	 Catch:{ all -> 0x008b }
        r5 = r10.zzxf;	 Catch:{ all -> 0x008b }
        r1 = r9.zzvy;	 Catch:{ all -> 0x008b }
        r7 = r1.zzeo();	 Catch:{ all -> 0x008b }
        r1 = r9.zzvy;	 Catch:{ all -> 0x008b }
        r8 = r1.getMaxPlayers();	 Catch:{ all -> 0x008b }
        r1 = r0;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x008b }
        r9.zzwa = r0;	 Catch:{ all -> 0x008b }
        r0 = r9.zzwa;	 Catch:{ all -> 0x008b }
        r1 = r10.zzxh;	 Catch:{ all -> 0x008b }
        r0 = r0.getPlayer(r1);	 Catch:{ all -> 0x008b }
        if (r0 == 0) goto L_0x0089;
    L_0x0076:
        r0 = r0.isControllable();	 Catch:{ all -> 0x008b }
        if (r0 == 0) goto L_0x0089;
    L_0x007c:
        r0 = r10.zzwy;	 Catch:{ all -> 0x008b }
        r1 = 2;
        if (r0 != r1) goto L_0x0089;
    L_0x0081:
        r0 = r10.zzxh;	 Catch:{ all -> 0x008b }
        r9.zzwc = r0;	 Catch:{ all -> 0x008b }
        r10 = r10.zzxb;	 Catch:{ all -> 0x008b }
        r9.zzwd = r10;	 Catch:{ all -> 0x008b }
    L_0x0089:
        monitor-exit(r9);
        return;
    L_0x008b:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcb.zza(com.google.android.gms.internal.cast.zzcp):void");
    }

    private final void zzb(long j, int i, Object obj) {
        List zzer = zzer();
        synchronized (zzer) {
            Iterator it = zzer.iterator();
            while (it.hasNext()) {
                if (((zzed) it.next()).zzc(j, i, obj)) {
                    it.remove();
                }
            }
        }
    }

    private final synchronized void zzel() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("castSessionId", this.zzvx);
            jSONObject.put("playerTokenMap", new JSONObject(this.zzvv));
            this.zzvw.edit().putString("save_data", jSONObject.toString()).commit();
        } catch (JSONException e) {
            zzbf.w("Error while saving data: %s", e.getMessage());
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0046, code skipped:
            return;
     */
    private final synchronized void zzem() {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.zzvw;	 Catch:{ all -> 0x005b }
        r1 = "save_data";
        r2 = 0;
        r0 = r0.getString(r1, r2);	 Catch:{ all -> 0x005b }
        if (r0 != 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r5);
        return;
    L_0x000e:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0047 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0047 }
        r0 = "castSessionId";
        r0 = r1.getString(r0);	 Catch:{ JSONException -> 0x0047 }
        r2 = r5.zzvx;	 Catch:{ JSONException -> 0x0047 }
        r0 = r2.equals(r0);	 Catch:{ JSONException -> 0x0047 }
        if (r0 == 0) goto L_0x0045;
    L_0x0021:
        r0 = "playerTokenMap";
        r0 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x0047 }
        r1 = r0.keys();	 Catch:{ JSONException -> 0x0047 }
    L_0x002b:
        r2 = r1.hasNext();	 Catch:{ JSONException -> 0x0047 }
        if (r2 == 0) goto L_0x0041;
    L_0x0031:
        r2 = r1.next();	 Catch:{ JSONException -> 0x0047 }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x0047 }
        r3 = r5.zzvv;	 Catch:{ JSONException -> 0x0047 }
        r4 = r0.getString(r2);	 Catch:{ JSONException -> 0x0047 }
        r3.put(r2, r4);	 Catch:{ JSONException -> 0x0047 }
        goto L_0x002b;
    L_0x0041:
        r0 = 0;
        r5.zzwe = r0;	 Catch:{ JSONException -> 0x0047 }
    L_0x0045:
        monitor-exit(r5);
        return;
    L_0x0047:
        r0 = move-exception;
        r1 = zzbf;	 Catch:{ all -> 0x005b }
        r2 = "Error while loading data: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x005b }
        r4 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x005b }
        r3[r4] = r0;	 Catch:{ all -> 0x005b }
        r1.w(r2, r3);	 Catch:{ all -> 0x005b }
        monitor-exit(r5);
        return;
    L_0x005b:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcb.zzem():void");
    }
}

package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzcq implements GameManagerState {
    private final String zzww;
    private final int zzwx;
    private final int zzxl;
    private final int zzxm;
    private final String zzxn;
    private final JSONObject zzxo;
    private final Map<String, PlayerInfo> zzxp;

    public zzcq(int i, int i2, String str, JSONObject jSONObject, Collection<PlayerInfo> collection, String str2, int i3) {
        this.zzxl = i;
        this.zzxm = i2;
        this.zzxn = str;
        this.zzxo = jSONObject;
        this.zzww = str2;
        this.zzwx = i3;
        this.zzxp = new HashMap(collection.size());
        for (PlayerInfo playerInfo : collection) {
            this.zzxp.put(playerInfo.getPlayerId(), playerInfo);
        }
    }

    public final int getLobbyState() {
        return this.zzxl;
    }

    public final int getGameplayState() {
        return this.zzxm;
    }

    public final JSONObject getGameData() {
        return this.zzxo;
    }

    public final CharSequence getGameStatusText() {
        return this.zzxn;
    }

    public final CharSequence getApplicationName() {
        return this.zzww;
    }

    public final int getMaxPlayers() {
        return this.zzwx;
    }

    public final List<PlayerInfo> getPlayersInState(int i) {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.getPlayerState() == i) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    public final PlayerInfo getPlayer(String str) {
        return str == null ? null : (PlayerInfo) this.zzxp.get(str);
    }

    public final Collection<PlayerInfo> getPlayers() {
        return Collections.unmodifiableCollection(this.zzxp.values());
    }

    public final List<PlayerInfo> getControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isControllable()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedPlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isConnected()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo playerInfo : getPlayers()) {
            if (playerInfo.isConnected() && playerInfo.isControllable()) {
                arrayList.add(playerInfo);
            }
        }
        return arrayList;
    }

    public final boolean hasLobbyStateChanged(GameManagerState gameManagerState) {
        return this.zzxl != gameManagerState.getLobbyState();
    }

    public final boolean hasGameplayStateChanged(GameManagerState gameManagerState) {
        return this.zzxm != gameManagerState.getGameplayState();
    }

    public final boolean hasGameDataChanged(GameManagerState gameManagerState) {
        return !JsonUtils.areJsonValuesEquivalent(this.zzxo, gameManagerState.getGameData());
    }

    public final boolean hasGameStatusTextChanged(GameManagerState gameManagerState) {
        return !zzdk.zza(this.zzxn, gameManagerState.getGameStatusText());
    }

    public final boolean hasPlayerChanged(String str, GameManagerState gameManagerState) {
        return !zzdk.zza(getPlayer(str), gameManagerState.getPlayer(str));
    }

    /* JADX WARNING: Missing block: B:11:0x001f, code skipped:
            return true;
     */
    public final boolean hasPlayerStateChanged(java.lang.String r3, com.google.android.gms.cast.games.GameManagerState r4) {
        /*
        r2 = this;
        r0 = r2.getPlayer(r3);
        r3 = r4.getPlayer(r3);
        r4 = 0;
        if (r0 != 0) goto L_0x000e;
    L_0x000b:
        if (r3 != 0) goto L_0x000e;
    L_0x000d:
        return r4;
    L_0x000e:
        r1 = 1;
        if (r0 == 0) goto L_0x001f;
    L_0x0011:
        if (r3 == 0) goto L_0x001f;
    L_0x0013:
        r0 = r0.getPlayerState();
        r3 = r3.getPlayerState();
        if (r0 == r3) goto L_0x001e;
    L_0x001d:
        return r1;
    L_0x001e:
        return r4;
    L_0x001f:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcq.hasPlayerStateChanged(java.lang.String, com.google.android.gms.cast.games.GameManagerState):boolean");
    }

    /* JADX WARNING: Missing block: B:11:0x0023, code skipped:
            return true;
     */
    public final boolean hasPlayerDataChanged(java.lang.String r3, com.google.android.gms.cast.games.GameManagerState r4) {
        /*
        r2 = this;
        r0 = r2.getPlayer(r3);
        r3 = r4.getPlayer(r3);
        r4 = 0;
        if (r0 != 0) goto L_0x000e;
    L_0x000b:
        if (r3 != 0) goto L_0x000e;
    L_0x000d:
        return r4;
    L_0x000e:
        r1 = 1;
        if (r0 == 0) goto L_0x0023;
    L_0x0011:
        if (r3 == 0) goto L_0x0023;
    L_0x0013:
        r0 = r0.getPlayerData();
        r3 = r3.getPlayerData();
        r3 = com.google.android.gms.common.util.JsonUtils.areJsonValuesEquivalent(r0, r3);
        if (r3 != 0) goto L_0x0022;
    L_0x0021:
        return r1;
    L_0x0022:
        return r4;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcq.hasPlayerDataChanged(java.lang.String, com.google.android.gms.cast.games.GameManagerState):boolean");
    }

    public final Collection<String> getListOfChangedPlayers(GameManagerState gameManagerState) {
        HashSet hashSet = new HashSet();
        for (PlayerInfo playerInfo : getPlayers()) {
            PlayerInfo player = gameManagerState.getPlayer(playerInfo.getPlayerId());
            if (player == null || !playerInfo.equals(player)) {
                hashSet.add(playerInfo.getPlayerId());
            }
        }
        for (PlayerInfo playerInfo2 : gameManagerState.getPlayers()) {
            if (getPlayer(playerInfo2.getPlayerId()) == null) {
                hashSet.add(playerInfo2.getPlayerId());
            }
        }
        return hashSet;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GameManagerState)) {
            return false;
        }
        GameManagerState gameManagerState = (GameManagerState) obj;
        if (getPlayers().size() != gameManagerState.getPlayers().size()) {
            return false;
        }
        for (PlayerInfo playerInfo : getPlayers()) {
            boolean z = false;
            for (PlayerInfo playerInfo2 : gameManagerState.getPlayers()) {
                if (zzdk.zza(playerInfo.getPlayerId(), playerInfo2.getPlayerId())) {
                    if (!zzdk.zza(playerInfo, playerInfo2)) {
                        return false;
                    }
                    z = true;
                }
            }
            if (!z) {
                return false;
            }
        }
        if (this.zzxl == gameManagerState.getLobbyState() && this.zzxm == gameManagerState.getGameplayState() && this.zzwx == gameManagerState.getMaxPlayers() && zzdk.zza(this.zzww, gameManagerState.getApplicationName()) && zzdk.zza(this.zzxn, gameManagerState.getGameStatusText()) && JsonUtils.areJsonValuesEquivalent(this.zzxo, gameManagerState.getGameData())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzxl), Integer.valueOf(this.zzxm), this.zzxp, this.zzxn, this.zzxo, this.zzww, Integer.valueOf(this.zzwx));
    }
}

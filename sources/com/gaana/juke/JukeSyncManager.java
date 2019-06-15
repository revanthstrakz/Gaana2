package com.gaana.juke;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.juke.JukeSessionManager.JukeSyncListener;
import com.gaana.models.BusinessObject;
import com.i.i;
import com.i.j;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.services.d;
import com.services.l.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JukeSyncManager {
    private static final Boolean BOOLEAN_ADDED = Boolean.TRUE;
    private static final Boolean BOOLEAN_DELETED = Boolean.FALSE;
    private static final Boolean BOOLEAN_DOWN_VOTED = Boolean.FALSE;
    private static final Boolean BOOLEAN_UP_VOTED = Boolean.TRUE;
    private final Handler handlerWithID = new Handler(Looper.getMainLooper());
    private boolean isReOrder = false;
    private boolean isRunnableAdded = false;
    private JukePlaylist mJukePlaylist;
    private List<String> mPlayNextList = new ArrayList();
    private List<String> mReOrderedList = new ArrayList();
    private Map<String, Boolean> mTracksAddDeleteMap = new LinkedHashMap();
    private Map<String, Boolean> mTracksVoteStatusMap = new LinkedHashMap();
    private Runnable runnable = new Runnable() {
        public void run() {
            JukeSyncManager.this.handlerWithID.removeCallbacksAndMessages(null);
            if (0 != JukeSyncManager.this.mJukePlaylist.getPlStatus()) {
                JukeSyncManager.this.editPlaylist(JukeSyncManager.this.mJukePlaylist, null, false, false);
            }
        }
    };
    private JukeSyncListener syncListener;

    public JukeSyncManager(JukePlaylist jukePlaylist) {
        this.mJukePlaylist = jukePlaylist;
    }

    public void setSyncListener(JukeSyncListener jukeSyncListener) {
        scheduleTimer();
        this.syncListener = jukeSyncListener;
    }

    public JukeSyncListener getSyncListener() {
        return this.syncListener;
    }

    public void setJukePlaylist(JukePlaylist jukePlaylist) {
        this.mJukePlaylist = jukePlaylist;
    }

    public void removeSyncListener() {
        this.syncListener = null;
    }

    public void editPlaylist(JukePlaylist jukePlaylist, s sVar, boolean z, boolean z2) {
        editPlaylist(jukePlaylist, sVar, z, z2, false);
    }

    public void editPlaylist(JukePlaylist jukePlaylist, s sVar, boolean z, boolean z2, boolean z3) {
        String str;
        String str2;
        if (this.syncListener != null) {
            this.syncListener.onSyncStarted();
        }
        j a = j.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("juke_edit_playlist_");
        stringBuilder.append(this.mJukePlaylist.getBusinessObjId());
        a.a(stringBuilder.toString());
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com//collab/playlist/edit");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.JukePlaylist);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("juke_edit_playlist_");
        stringBuilder2.append(this.mJukePlaylist.getBusinessObjId());
        uRLManager.c(stringBuilder2.toString());
        HashMap hashMap = new HashMap();
        stringBuilder = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        for (Entry entry : this.mTracksAddDeleteMap.entrySet()) {
            str = (String) entry.getKey();
            if (entry.getValue() == BOOLEAN_ADDED) {
                stringBuilder.append(",");
                stringBuilder.append(str);
            } else {
                stringBuilder3.append(",");
                stringBuilder3.append(str);
            }
        }
        String replaceFirst = stringBuilder.toString().replaceFirst(",", "");
        String replaceFirst2 = stringBuilder3.toString().replaceFirst(",", "");
        StringBuilder stringBuilder4 = new StringBuilder();
        StringBuilder stringBuilder5 = new StringBuilder();
        for (Entry entry2 : this.mTracksVoteStatusMap.entrySet()) {
            str2 = (String) entry2.getKey();
            if (entry2.getValue() == BOOLEAN_UP_VOTED) {
                stringBuilder4.append(",");
                stringBuilder4.append(str2);
            } else {
                stringBuilder5.append(",");
                stringBuilder5.append(str2);
            }
        }
        String replaceFirst3 = stringBuilder4.toString().replaceFirst(",", "");
        String replaceFirst4 = stringBuilder5.toString().replaceFirst(",", "");
        StringBuilder stringBuilder6 = new StringBuilder();
        for (String str22 : this.mPlayNextList) {
            stringBuilder6.append(",");
            stringBuilder6.append(str22);
        }
        Object obj = "";
        boolean z4 = this.isReOrder;
        if (z4) {
            StringBuilder stringBuilder7 = new StringBuilder();
            if (!(jukePlaylist == null || jukePlaylist.getArrListBusinessObj() == null)) {
                for (int i = 0; i < jukePlaylist.getArrListBusinessObj().size(); i++) {
                    stringBuilder7.append(",");
                    stringBuilder7.append(((BusinessObject) jukePlaylist.getArrListBusinessObj().get(i)).getBusinessObjId());
                }
            }
            obj = stringBuilder7.toString().replaceFirst(",", "");
        }
        Set keySet = this.mTracksAddDeleteMap.keySet();
        Set keySet2 = this.mTracksVoteStatusMap.keySet();
        ArrayList arrayList = new ArrayList(this.mPlayNextList);
        ArrayList arrayList2 = new ArrayList(this.mReOrderedList);
        str = stringBuilder6.toString().replaceFirst(",", "");
        hashMap.put("addIds", replaceFirst);
        hashMap.put("deleteIds", replaceFirst2);
        hashMap.put("upvotedIds", replaceFirst3);
        hashMap.put("downvotedIds", replaceFirst4);
        hashMap.put("nextTrackId", str);
        hashMap.put("pid", this.mJukePlaylist.getBusinessObjId());
        replaceFirst = d.a().b("pref_juke_nick", "", false);
        if (!TextUtils.isEmpty(replaceFirst)) {
            hashMap.put("nick_name", replaceFirst);
        }
        if (z2) {
            hashMap.put("verbose", "1");
        } else {
            hashMap.put("verbose", "0");
        }
        if (z4) {
            hashMap.put("reorderedId", obj);
        }
        uRLManager.a(hashMap);
        i a2 = i.a();
        final ArrayList arrayList3 = arrayList;
        final ArrayList arrayList4 = arrayList2;
        final Set set = keySet;
        final Set set2 = keySet2;
        final JukePlaylist jukePlaylist2 = jukePlaylist;
        z4 = z3;
        s sVar2 = r0;
        final s sVar3 = sVar;
        AnonymousClass2 anonymousClass2 = new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                String businessObjId = JukeSessionManager.getInstance().getJukeSessionPlaylist() != null ? JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId() : "";
                JukeSyncManager.this.mPlayNextList.removeAll(arrayList3);
                JukeSyncManager.this.mReOrderedList.removeAll(arrayList4);
                JukeSyncManager.this.mTracksAddDeleteMap.keySet().removeAll(set);
                JukeSyncManager.this.mTracksVoteStatusMap.keySet().removeAll(set2);
                JukeSyncManager.this.isReOrder = false;
                if (Constants.cY && businessObjId.equals(jukePlaylist2.getBusinessObjId())) {
                    JukeSessionManager.getInstance().getJukeQueueManager().setJukePlaylist((JukePlaylist) businessObject);
                }
                if (z4 && sVar3 != null) {
                    sVar3.onRetreivalComplete(businessObject);
                } else if (JukeSyncManager.this.syncListener != null) {
                    JukeSyncManager.this.syncListener.onSyncEnd(businessObject);
                } else if (sVar3 != null) {
                    sVar3.onRetreivalComplete(businessObject);
                }
                if (JukeSyncManager.this.syncListener == null) {
                    JukeSyncManager.this.cancelTimer();
                } else {
                    JukeSyncManager.this.handlerWithID.postDelayed(JukeSyncManager.this.runnable, JukeSessionManager.JUKE_SYNC_INTERVAL);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (z4 && sVar3 != null) {
                    sVar3.onErrorResponse(businessObject);
                } else if (JukeSyncManager.this.syncListener != null) {
                    JukeSyncManager.this.syncListener.onSyncError();
                } else if (sVar3 != null) {
                    sVar3.onErrorResponse(businessObject);
                }
            }
        };
        a2.a(sVar2, uRLManager);
    }

    private void scheduleTimer() {
        if (!this.isRunnableAdded) {
            this.isRunnableAdded = true;
            this.handlerWithID.postDelayed(this.runnable, JukeSessionManager.JUKE_SYNC_INTERVAL);
        }
    }

    private void cancelTimer() {
        this.handlerWithID.removeCallbacksAndMessages(null);
        this.isRunnableAdded = false;
    }

    public void setReOrderFlag(boolean z) {
        scheduleTimer();
        this.isReOrder = z;
    }

    public void addVote(String str, boolean z) {
        scheduleTimer();
        if (this.mTracksVoteStatusMap.get(str) == null) {
            this.mTracksVoteStatusMap.put(str, z ? BOOLEAN_UP_VOTED : BOOLEAN_DOWN_VOTED);
        } else if (((Boolean) this.mTracksVoteStatusMap.get(str)).booleanValue() != z) {
            this.mTracksVoteStatusMap.remove(str);
        }
    }

    public void addDeleteTracks(String str, boolean z) {
        scheduleTimer();
        if (this.mTracksAddDeleteMap.get(str) == null) {
            this.mTracksAddDeleteMap.put(str, z ? BOOLEAN_ADDED : BOOLEAN_DELETED);
        } else if (((Boolean) this.mTracksAddDeleteMap.get(str)).booleanValue() != z) {
            this.mTracksAddDeleteMap.remove(str);
        }
    }

    public void forceSync(boolean z, s sVar) {
        editPlaylist(this.mJukePlaylist, sVar, z, true, true);
    }

    public void addPlayNext(String str) {
        scheduleTimer();
        this.mTracksAddDeleteMap.put(str, BOOLEAN_ADDED);
        this.mPlayNextList.add(str);
    }

    public void reOrder(List<String> list) {
        scheduleTimer();
        this.mReOrderedList = list;
    }

    public void clearData() {
        this.mTracksAddDeleteMap.clear();
        this.mTracksVoteStatusMap.clear();
        this.mPlayNextList.clear();
        this.mReOrderedList.clear();
        this.isReOrder = false;
    }
}

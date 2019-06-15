package com.gaana.juke;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.EditTextDialog;
import com.gaana.view.item.HeadingDialogView;
import com.i.i;
import com.i.j;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ai;
import com.managers.aj;
import com.managers.o;
import com.services.d;
import com.services.l.af;
import com.services.l.aw;
import com.services.l.s;
import com.utilities.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum JukeSessionManager {
    INSTANCE;
    
    public static final int ACTION_ADD = 1;
    public static final int ACTION_DELETE = 2;
    public static final int ACTION_DOWN_VOTE = 8;
    public static final int ACTION_PLAY_NEXT = 32;
    public static final int ACTION_REORDER = 16;
    public static final int ACTION_UP_VOTE = 4;
    public static final int DEFAULT_JUKE_SYNC_INTERVAL = 30000;
    public static final int DIALOG_JUKE_DELETE_JUKE = 5;
    public static final int DIALOG_JUKE_NICK_NAME = 4;
    public static final int DIALOG_JUKE_ONGOING_ERROR = 1;
    public static final int DIALOG_JUKE_RADIO_PLAYBACK_ERROR = 0;
    public static final int DIALOG_JUKE_START_NOTIFICATION = 2;
    public static final int DIALOG_JUKE_SWITCH_DEVICE = 3;
    public static final int JUKE_SESSION_STATUS_DEACTIVE = 0;
    public static final int JUKE_SESSION_STATUS_OFF = 1;
    public static final int JUKE_SESSION_STATUS_ON = 2;
    public static final int JUKE_SESSION_STATUS_UNSET = -1;
    public static long JUKE_SYNC_INTERVAL = 30000;
    public static final int JUKE_TRACK_NOT_PLAYED = 0;
    public static final int JUKE_TRACK_PLAYED = 2;
    public static final int JUKE_TRACK_PLAYING = 1;
    public static final int PAGE_TYPE_ADMIN_CREATE = 0;
    public static final int PAGE_TYPE_ADMIN_SESSION = 1;
    public static final int PAGE_TYPE_ADMIN_SESSION_REORDER = 3;
    public static final int PAGE_TYPE_GUEST_SESSION = 2;
    public static final int PAGE_TYPE_NO_SESSION = -1;
    private JukeQueueManager jukeQueueManager;
    private Map<String, JukeSyncManager> jukeSyncManagerMap;
    private Context mContext;
    private BusinessObject mCurrentBusinessObject;
    private int mCurrentSessionType;
    private JukePlaylist mJukeSessionPlaylist;
    private String mUserNick;

    @Retention(RetentionPolicy.SOURCE)
    public @interface JukeAction {
    }

    public interface JukePlayerEnqueueListener {
        void enqueue(String str);

        void enqueue(List<String> list);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JukeSessionDialogType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JukeSessionType {
    }

    public interface JukeSyncListener {
        void onSyncEnd(BusinessObject businessObject);

        void onSyncError();

        void onSyncStarted();
    }

    public void setJukeSessionPlaylist(JukePlaylist jukePlaylist) {
        this.mJukeSessionPlaylist = jukePlaylist;
    }

    public static JukeSessionManager getInstance() {
        return INSTANCE;
    }

    public void stopJukeSession(s sVar) {
        setJukeSessionStatus(this.mJukeSessionPlaylist.getBusinessObjId(), 1, sVar);
    }

    public void startJukeSession() {
        this.jukeQueueManager.init();
    }

    public String getCurrentPlayingId() {
        return PlayerManager.a(this.mContext).i().h();
    }

    public void setCurrentBusinessObject(BusinessObject businessObject) {
        this.mCurrentBusinessObject = businessObject;
    }

    public int getCurrentSessionType() {
        return this.mCurrentSessionType;
    }

    public void setCurrentSessionType(int i) {
        this.mCurrentSessionType = i;
    }

    public BusinessObject getCurrentBusinessObject() {
        return this.mCurrentBusinessObject;
    }

    public void setSyncListener(JukePlaylist jukePlaylist, JukeSyncListener jukeSyncListener) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).setSyncListener(jukeSyncListener);
    }

    public JukeSyncListener getSyncListener(JukePlaylist jukePlaylist) {
        return this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) != null ? ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).getSyncListener() : null;
    }

    public void forceSync(JukePlaylist jukePlaylist, boolean z, s sVar) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).forceSync(z, sVar);
    }

    public void removeSyncListener(JukePlaylist jukePlaylist) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).removeSyncListener();
    }

    public void setReOrderFlag(JukePlaylist jukePlaylist, boolean z) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).setJukePlaylist(jukePlaylist);
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).setReOrderFlag(z);
    }

    public void editPlaylist(JukePlaylist jukePlaylist, s sVar, boolean z, boolean z2) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).editPlaylist(jukePlaylist, sVar, z, z2);
    }

    public void addVote(JukePlaylist jukePlaylist, String str, boolean z) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).addVote(str, z);
    }

    public void addDeleteTracks(JukePlaylist jukePlaylist, String str, boolean z) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).addDeleteTracks(str, z);
    }

    public void addPlayNext(JukePlaylist jukePlaylist, ArrayList<BusinessObject> arrayList) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                addPlayNext(jukePlaylist, ((BusinessObject) arrayList.get(size)).getBusinessObjId());
            }
        }
    }

    public void addPlayNext(JukePlaylist jukePlaylist, String str) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).addPlayNext(str);
    }

    public void reOrder(JukePlaylist jukePlaylist, List<String> list) {
        if (this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId()) == null) {
            this.jukeSyncManagerMap.put(jukePlaylist.getBusinessObjId(), new JukeSyncManager(jukePlaylist));
        }
        ((JukeSyncManager) this.jukeSyncManagerMap.get(jukePlaylist.getBusinessObjId())).reOrder(list);
    }

    public static void createJukePlaylist(List<BusinessObject> list, String str, String str2, String str3, String str4, s sVar) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (BusinessObject businessObjId : list) {
                stringBuilder.append(businessObjId.getBusinessObjId());
                stringBuilder.append(",");
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/create");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.JukePlaylist);
        HashMap hashMap = new HashMap();
        hashMap.put("addIds", stringBuilder2);
        hashMap.put("pl_name", str3);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("party_source", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("source_playlist_id", str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("nick_name", str4);
        }
        hashMap.put("verbose", "1");
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        uRLManager.a(hashMap);
        i.a().a(sVar, uRLManager, Boolean.valueOf(true));
    }

    public static void getErrorDialog(Context context, int i, @NonNull OnButtonClickListener onButtonClickListener) {
        String str = "";
        String string = context.getResources().getString(R.string.dlg_msg_stop);
        String string2 = context.getResources().getString(R.string.dlg_msg_cancel_cap);
        if (i == 0) {
            str = context.getResources().getString(R.string.error_juke_radio_playback);
        } else if (i == 1) {
            str = context.getResources().getString(R.string.error_juke_ongoing_party);
        }
        new CustomDialogView(context, str, string, string2, onButtonClickListener).show();
    }

    public static void getDialog(Context context, int i, @NonNull OnButtonClickListener onButtonClickListener) {
        String str;
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        if (i == 3) {
            String string = context.getResources().getString(R.string.switch_device);
            str3 = context.getResources().getString(R.string.control_juke_session);
            str4 = context.getResources().getString(R.string.switch_device);
            str5 = context.getResources().getString(R.string.no_thanks);
            str = string;
        } else {
            if (i == 2) {
                str2 = context.getResources().getString(R.string.tell_friends);
                str3 = context.getResources().getString(R.string.juke_start_session_notif);
                str4 = context.getResources().getString(R.string.opt_send);
                str5 = context.getResources().getString(R.string.no);
            } else if (i == 5) {
                str2 = context.getResources().getString(R.string.opt_delete_party);
                str3 = context.getResources().getString(R.string.warning_delete_juke);
                str4 = context.getResources().getString(R.string.opt_delete);
                str5 = context.getResources().getString(R.string.cancel);
            }
            str = str2;
        }
        new HeadingDialogView(context, R.layout.dialog_heading_view, str, str3, str4, str5, onButtonClickListener).show();
    }

    public static void getNickDialog(Context context, String str, @NonNull EditTextDialog.OnButtonClickListener onButtonClickListener) {
        new EditTextDialog(context, context.getResources().getString(R.string.msg_juke_nick_user), context.getResources().getString(R.string.continue_button), str, false, context.getResources().getString(R.string.juke_enter_nick), onButtonClickListener).show();
    }

    public static void getRenamePlaylistDialog(Context context, String str, @NonNull EditTextDialog.OnButtonClickListener onButtonClickListener) {
        new EditTextDialog(context, context.getResources().getString(R.string.opt_rename_playlist), context.getResources().getString(R.string.continue_button), str, false, context.getResources().getString(R.string.enter_playlist_name), onButtonClickListener).show();
    }

    public static ArrayList<JukeTrack> getJukeTrackList(ArrayList<Track> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList == null) {
            return arrayList2;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(getJukeTrack((Track) arrayList.get(i)));
        }
        return arrayList2;
    }

    public static JukeTrack getJukeTrack(Track track) {
        JukeTrack jukeTrack = new JukeTrack();
        jukeTrack.setBusinessObjId(track.getBusinessObjId());
        jukeTrack.setAtw(track.getArtwork());
        jukeTrack.setName(track.getRawName());
        jukeTrack.setVoteCount(1);
        jukeTrack.setVotedBy(true);
        String albumTitle = track.getAlbumTitle();
        String artistNames = track.getArtistNames();
        if (!TextUtils.isEmpty(albumTitle) && !TextUtils.isEmpty(artistNames)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(albumTitle);
            stringBuilder.append(" - ");
            stringBuilder.append(artistNames);
            albumTitle = stringBuilder.toString();
        } else if (TextUtils.isEmpty(albumTitle) && TextUtils.isEmpty(artistNames)) {
            albumTitle = "";
        } else if (TextUtils.isEmpty(albumTitle)) {
            albumTitle = artistNames;
        }
        jukeTrack.setSubtitleText(albumTitle);
        return jukeTrack;
    }

    public static JukePlaylist getJukePlaylist(Playlist playlist) {
        JukePlaylist jukePlaylist = new JukePlaylist();
        jukePlaylist.setBusinessObjId(playlist.getBusinessObjId());
        jukePlaylist.setName(playlist.getName());
        jukePlaylist.setAtw(playlist.getArtwork());
        jukePlaylist.setPartySource(playlist.getPartySource());
        jukePlaylist.setSourcePlayListId(playlist.getBusinessObjId());
        jukePlaylist.setPartyTracks(getJukeTrackList(playlist.getArrListBusinessObj()));
        return jukePlaylist;
    }

    public void reportCurrentPlaying(String str, String str2) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/track/current");
        uRLManager.c(1);
        HashMap hashMap = new HashMap();
        hashMap.put("trackId", str);
        hashMap.put("pid", str2);
        uRLManager.a(hashMap);
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
            }
        }, uRLManager);
    }

    public void renameNickName(String str, s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com//collab/playlist/rename/nickname");
        uRLManager.c(1);
        HashMap hashMap = new HashMap();
        hashMap.put("nick_name", str);
        hashMap.put("deviceId", Util.l(GaanaApplication.getContext()));
        uRLManager.a(hashMap);
        i.a().a(sVar, uRLManager);
    }

    public void getNextTracks(String str, int i, s sVar) {
        j.a().a((Object) "juke_queue");
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/queue");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.c("juke_queue");
        HashMap hashMap = new HashMap();
        hashMap.put("pid", str);
        hashMap.put("offset", String.valueOf(i));
        uRLManager.a(hashMap);
        i.a().a(sVar, uRLManager);
    }

    public void startJukeSession(String str, int i, s sVar) {
        setJukeSessionStatus(str, i, sVar);
    }

    private void setJukeSessionStatus(String str, final int i, final s sVar) {
        final Handler handler = new Handler(Looper.getMainLooper());
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/status");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.JukePlaylist);
        HashMap hashMap = new HashMap();
        hashMap.put("pid", str);
        hashMap.put("status", String.valueOf(i));
        hashMap.put("verbose", "1");
        uRLManager.a(hashMap);
        Context a = ai.a();
        if (a instanceof BaseActivity) {
            ((BaseActivity) a).showProgressDialog(Boolean.valueOf(false), this.mContext.getResources().getString(R.string.loading));
        }
        i.a().a(new s() {
            public void onRetreivalComplete(final BusinessObject businessObject) {
                Context a = ai.a();
                if (a instanceof BaseActivity) {
                    ((BaseActivity) a).hideProgressDialog();
                }
                if (i == 2) {
                    if (!(businessObject instanceof JukePlaylist)) {
                        handler.post(new Runnable() {
                            public void run() {
                                sVar.onRetreivalComplete(businessObject);
                                aj.a().a(JukeSessionManager.this.mContext, JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occured));
                            }
                        });
                    } else if (((JukePlaylist) businessObject).getPlStatus() == 2) {
                        Util.a(new aw() {
                            public void onPlayerQueueSavingCompleted() {
                                handler.post(new Runnable() {
                                    public void run() {
                                        Constants.cY = true;
                                        d.a().a("pref_juke_session_id", businessObject.getBusinessObjId(), false);
                                        JukeSessionManager.this.mJukeSessionPlaylist = (JukePlaylist) businessObject;
                                        if (((JukePlaylist) businessObject).getCurrentPlayingIndex() < 0) {
                                            ((JukePlaylist) businessObject).setmCurrentPlayingIndex(0);
                                        }
                                        BusinessObject businessObject = (businessObject.getArrListBusinessObj() == null || ((JukePlaylist) businessObject).getCurrentPlayingIndex() <= -1 || ((JukePlaylist) businessObject).getCurrentPlayingIndex() >= businessObject.getArrListBusinessObj().size()) ? null : (BusinessObject) businessObject.getArrListBusinessObj().get(((JukePlaylist) businessObject).getCurrentPlayingIndex());
                                        if (businessObject != null) {
                                            JukeSessionManager.this.jukeQueueManager.init();
                                            JukeSessionManager.this.jukeQueueManager.setJukePlaylist((JukePlaylist) businessObject);
                                            JukeSessionManager.this.jukeQueueManager.fetchFromDummyTrack(businessObject.getBusinessObjId(), true);
                                        }
                                        sVar.onRetreivalComplete(businessObject);
                                    }
                                });
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            public void run() {
                                sVar.onRetreivalComplete(businessObject);
                                aj.a().a(JukeSessionManager.this.mContext, JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occured));
                            }
                        });
                    }
                } else if (i != 1) {
                } else {
                    if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                        PlayerManager.a(GaanaApplication.getContext()).A();
                        o.a().a(new af() {
                            public void onRetreivalComplete(Object obj) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        Constants.cY = false;
                                        JukeSessionManager.getInstance().setJukeSessionPlaylist(null);
                                        JukeSessionManager.this.jukeQueueManager.shutDown();
                                        d.a().a("pref_juke_session_id", "", false);
                                        if (ai.a() != null) {
                                            ((GaanaActivity) ai.a()).setUpdatePlayerFragment();
                                        }
                                        sVar.onRetreivalComplete(businessObject);
                                    }
                                });
                            }

                            public void onErrorResponse(BusinessObject businessObject) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        Constants.cY = false;
                                        JukeSessionManager.getInstance().setJukeSessionPlaylist(null);
                                        JukeSessionManager.this.jukeQueueManager.shutDown();
                                        d.a().a("pref_juke_session_id", "", false);
                                        if (ai.a() != null) {
                                            ((GaanaActivity) ai.a()).setUpdatePlayerFragment();
                                            ((GaanaActivity) ai.a()).getSlidingPanelLayout().a(3);
                                        }
                                        sVar.onRetreivalComplete(businessObject);
                                    }
                                });
                            }
                        });
                        return;
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            sVar.onRetreivalComplete(businessObject);
                            aj.a().a(JukeSessionManager.this.mContext, JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occured));
                        }
                    });
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Context a = ai.a();
                if (a instanceof BaseActivity) {
                    ((BaseActivity) a).hideProgressDialog();
                }
                aj.a().a(JukeSessionManager.this.mContext, JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occured));
                sVar.onErrorResponse(businessObject);
            }
        }, uRLManager);
    }

    public void toggleVoting(JukePlaylist jukePlaylist, int i, boolean z, final s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/voting-status");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.JukePlaylist);
        HashMap hashMap = new HashMap();
        hashMap.put("pid", jukePlaylist.getBusinessObjId());
        hashMap.put("status", String.valueOf(i));
        hashMap.put("verbose", z ? "1" : "0");
        uRLManager.a(hashMap);
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                sVar.onRetreivalComplete(businessObject);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                sVar.onErrorResponse(businessObject);
            }
        }, uRLManager);
    }

    public void addRemoveJoinee(JukePlaylist jukePlaylist, int i, boolean z, final s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/add/joinee");
        uRLManager.c(1);
        HashMap hashMap = new HashMap();
        hashMap.put("pid", jukePlaylist.getBusinessObjId());
        hashMap.put("status", String.valueOf(i));
        hashMap.put("verbose", String.valueOf(z));
        uRLManager.a(hashMap);
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                sVar.onRetreivalComplete(businessObject);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                sVar.onErrorResponse(businessObject);
            }
        }, uRLManager);
    }

    public void renameParty(@NonNull JukePlaylist jukePlaylist, @NonNull String str, @NonNull s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/rename/party");
        uRLManager.c(1);
        HashMap hashMap = new HashMap();
        hashMap.put("pl_name", str);
        hashMap.put("pid", jukePlaylist.getBusinessObjId());
        uRLManager.a(hashMap);
        i.a().a(sVar, uRLManager);
    }

    public void getTrackDetails(String str, final s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.a(str);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                sVar.onRetreivalComplete(businessObject);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                sVar.onErrorResponse(businessObject);
            }
        }, uRLManager);
    }

    public JukeQueueManager getJukeQueueManager() {
        return this.jukeQueueManager;
    }

    public JukePlaylist getJukeSessionPlaylist() {
        return this.mJukeSessionPlaylist;
    }

    public boolean isCurrentJukeSession(BusinessObject businessObject) {
        return Constants.cY && (getInstance().getJukeSessionPlaylist() != null ? getInstance().getJukeSessionPlaylist().getBusinessObjId() : "").equals(businessObject.getBusinessObjId());
    }

    public void deleteJukePlaylist(final JukePlaylist jukePlaylist, final s sVar) {
        if (!(this.mJukeSessionPlaylist != null ? this.mJukeSessionPlaylist.getBusinessObjId() : "").equals(jukePlaylist.getBusinessObjId())) {
            deleteJukePlaylist(jukePlaylist.getBusinessObjId(), sVar);
        } else if (this.mJukeSessionPlaylist.getPlStatus() == 2) {
            setJukeSessionStatus(jukePlaylist.getBusinessObjId(), 1, new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (!(businessObject instanceof JukePlaylist)) {
                        return;
                    }
                    if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                        JukeSessionManager.this.deleteJukePlaylist(jukePlaylist.getBusinessObjId(), sVar);
                        return;
                    }
                    sVar.onErrorResponse(null);
                    aj.a().a(ai.a(), JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occurred));
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    sVar.onErrorResponse(null);
                    aj.a().a(ai.a(), JukeSessionManager.this.mContext.getResources().getString(R.string.some_error_occurred));
                }
            });
        } else {
            deleteJukePlaylist(jukePlaylist.getBusinessObjId(), sVar);
        }
    }

    private void deleteJukePlaylist(String str, s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/status");
        uRLManager.c(1);
        uRLManager.a(BusinessObjectType.JukePlaylist);
        HashMap hashMap = new HashMap();
        hashMap.put("pid", str);
        hashMap.put("status", String.valueOf(0));
        hashMap.put("verbose", "1");
        uRLManager.a(hashMap);
        i.a().a(sVar, uRLManager);
    }

    public String getUserNick() {
        if (this.mUserNick == null) {
            return "";
        }
        return this.mUserNick;
    }

    public void setUserNick(String str) {
        this.mUserNick = str;
    }
}

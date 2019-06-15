package com.gaana.juke;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.constants.Constants.ErrorType;
import com.gaana.GaanaActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSessionManager.JukeSyncListener;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.i.d;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ai;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.n;
import com.player_framework.n.a;
import com.player_framework.n.b;
import com.player_framework.o;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class JukeQueueManager implements b {
    private static final int MAX_RETRY_COUNT = 3;
    private static final String PLAYER_COMMAND_LISTENER_KEY = "jukeQM";
    private static final int e = 5;
    private JukePlaylist jukePlaylist;
    private PlayerTrack mCurrentPlaying;
    private PlayerTrack mFetchedTrack;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int mRetryCount = 0;
    private int mTrackFetchBefore = 7000;
    private int mTrackFetchOffset = 1;
    private int mTrackRefresh = 2000;
    a playerNextPreviousDelegate = new a() {
        public void onPlayPrevious(boolean z, boolean z2) {
            JukeQueueManager.this.playPrevious(z, z2);
        }

        public void onPlayNext(boolean z, boolean z2) {
            JukeQueueManager.this.playNext(z, z2);
        }
    };
    private Runnable runnable = new Runnable() {
        public void run() {
            long parseLong = (((Long.parseLong(PlayerManager.a(GaanaApplication.getContext()).i().b().getDuration().trim()) * 1000) - ((long) GaanaMusicService.s().v())) - ((long) JukeQueueManager.this.mTrackFetchBefore)) / 3;
            JukeQueueManager.this.fetchNextTracks(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId(), false, 1, false);
        }
    };

    public void displayErrorDialog(String str, ErrorType errorType) {
    }

    public void displayErrorToast(String str, int i) {
    }

    public void onPlayNext(boolean z, boolean z2) {
    }

    public void onPlayPrevious(boolean z, boolean z2) {
    }

    public void onPlayerPause() {
    }

    public void onPlayerRepeatReset(boolean z) {
    }

    public void onPlayerResume() {
    }

    public void onStreamingQualityChanged(int i) {
    }

    public void onTrackModeChanged(int i) {
    }

    public void init() {
        o.a(PLAYER_COMMAND_LISTENER_KEY, (n) this);
        o.a(this.playerNextPreviousDelegate);
    }

    public void shutDown() {
        this.jukePlaylist = null;
        this.mHandler.removeCallbacksAndMessages(null);
        o.b(PLAYER_COMMAND_LISTENER_KEY);
        o.a(null);
    }

    public void setJukePlaylist(JukePlaylist jukePlaylist) {
        this.jukePlaylist = jukePlaylist;
    }

    private void setupTimer() {
        long v = (long) GaanaMusicService.s().v();
        long parseLong = Long.parseLong(PlayerManager.a(GaanaApplication.getContext()).i().b().getDuration().trim()) * 1000;
        long j = 0;
        if (((long) GaanaMusicService.s().u()) - v <= 5) {
            v = 0;
        }
        parseLong = (parseLong - v) - ((long) this.mTrackFetchBefore);
        if (parseLong >= 0) {
            j = parseLong;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(this.runnable, j);
    }

    private void updatePlaylistLocally() {
        final JukeSyncListener syncListener = JukeSessionManager.getInstance().getSyncListener(this.jukePlaylist);
        if (syncListener != null) {
            syncListener.onSyncStarted();
            d.a(new Runnable() {
                public void run() {
                    if (JukeQueueManager.this.jukePlaylist == null || JukeQueueManager.this.jukePlaylist.getArrListBusinessObj() == null || JukeQueueManager.this.jukePlaylist.getArrListBusinessObj().size() <= 0) {
                        syncListener.onSyncError();
                        return;
                    }
                    for (int i = 0; i < JukeQueueManager.this.jukePlaylist.getArrListBusinessObj().size(); i++) {
                        JukeTrack jukeTrack = (JukeTrack) JukeQueueManager.this.jukePlaylist.getArrListBusinessObj().get(i);
                        if (JukeQueueManager.this.mCurrentPlaying.h().equals(jukeTrack.getBusinessObjId())) {
                            JukeQueueManager.this.jukePlaylist.setmCurrentPlayingIndex(i);
                            jukeTrack.setPlayStatus(1);
                        } else {
                            jukeTrack.setPlayStatus((long) 2);
                        }
                    }
                    JukeQueueManager.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (JukeQueueManager.this.jukePlaylist != null) {
                                syncListener.onSyncEnd(JukeQueueManager.this.jukePlaylist);
                            } else {
                                syncListener.onSyncError();
                            }
                        }
                    });
                }
            });
        }
    }

    private void setupQueue() {
        if (this.mFetchedTrack == null || this.mFetchedTrack == this.mCurrentPlaying) {
            fetchNextTracks(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId(), true, 1, true);
            return;
        }
        this.mFetchedTrack.f("PARTY");
        if (this.jukePlaylist != null) {
            if (TextUtils.isEmpty(this.jukePlaylist.getSourcePlayListId())) {
                this.mFetchedTrack.c("PARTY");
            } else {
                this.mFetchedTrack.c(this.jukePlaylist.getSourcePlayListId());
            }
            if (TextUtils.isEmpty(this.jukePlaylist.getPartySource())) {
                this.mFetchedTrack.d("PARTY");
            } else {
                this.mFetchedTrack.d(this.jukePlaylist.getPartySource());
            }
        } else {
            this.mFetchedTrack.d("PARTY");
            this.mFetchedTrack.c("PARTY");
        }
        this.mFetchedTrack.b(SOURCE_TYPE.OTHER.ordinal());
        setupPlayer(this.mFetchedTrack);
    }

    public void setupPlayer(PlayerTrack playerTrack) {
        this.mCurrentPlaying = playerTrack;
        ArrayList arrayList = new ArrayList();
        arrayList.add(playerTrack);
        PlayerManager.a(GaanaApplication.getContext()).g(true);
        PlayerManager.a(GaanaApplication.getContext()).b(arrayList, playerTrack, 999999);
        PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA, GaanaApplication.getContext());
        PlayerManager.a(GaanaApplication.getContext()).j();
        if (PlayerManager.a) {
            o.a(GaanaApplication.getContext());
            PlayerManager.a = false;
        }
        if (!(ai.a() == null || ((GaanaActivity) ai.a()).isFinishing())) {
            ((GaanaActivity) ai.a()).setUpdatePlayerFragment();
        }
        PlayerManager.a(GaanaApplication.getContext()).g(false);
    }

    private void fetchNextTracks(String str, final boolean z, final int i, final boolean z2) {
        if (Util.j(GaanaApplication.getContext())) {
            JukeSessionManager.getInstance().getNextTracks(str, i, new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                        JukeQueueManager.this.mFetchedTrack = com.logging.d.a().a(null, businessObject.getArrListBusinessObj() != null ? (Track) businessObject.getArrListBusinessObj().get(0) : null, false);
                        JukeQueueManager.this.mFetchedTrack.d(z2);
                        JukeQueueManager.this.mRetryCount = 0;
                        if (z) {
                            JukeQueueManager.this.setupQueue();
                        }
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (JukeQueueManager.this.jukePlaylist != null) {
                        int i = 0;
                        if (JukeQueueManager.this.jukePlaylist.getCurrentPlayingIndex() < 0) {
                            JukeQueueManager.this.jukePlaylist.setmCurrentPlayingIndex(0);
                        }
                        if (JukeQueueManager.this.jukePlaylist.getArrListBusinessObj() != null) {
                            i = JukeQueueManager.this.jukePlaylist.getArrListBusinessObj().size();
                        }
                        if (i > 0 && JukeQueueManager.this.jukePlaylist.getCurrentPlayingIndex() + i < i) {
                            JukeQueueManager.this.fetchTrackDetails((BusinessObject) JukeQueueManager.this.jukePlaylist.getArrListBusinessObj().get(JukeQueueManager.this.jukePlaylist.getCurrentPlayingIndex() + i), z);
                        }
                    }
                }
            });
            return;
        }
        if (this.jukePlaylist.getCurrentPlayingIndex() < 0) {
            this.jukePlaylist.setmCurrentPlayingIndex(0);
        }
        int currentPlayingIndex = this.jukePlaylist.getCurrentPlayingIndex() + i;
        BusinessObject businessObject = (this.jukePlaylist.getArrListBusinessObj() == null || currentPlayingIndex >= this.jukePlaylist.getArrListBusinessObj().size()) ? null : (BusinessObject) this.jukePlaylist.getArrListBusinessObj().get(currentPlayingIndex);
        if (businessObject != null) {
            businessObject = Util.d(businessObject);
            if (!businessObject.isLocalMedia() && Util.c(businessObject)) {
                this.mFetchedTrack = com.logging.d.a().a(null, DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId()), false);
                if (this.mFetchedTrack != null) {
                    this.mFetchedTrack.d(z2);
                    if (z) {
                        setupQueue();
                    }
                }
            }
        }
    }

    public void playNext(boolean z, boolean z2) {
        if (z) {
            fetchNextTracks(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId(), true, 1, z);
        } else if (this.mFetchedTrack == null || this.mFetchedTrack == this.mCurrentPlaying) {
            fetchNextTracks(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId(), true, 1, z);
        } else {
            setupQueue();
        }
    }

    public void playPrevious(boolean z, boolean z2) {
        fetchNextTracks(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId(), true, -1, z);
    }

    public void fetchFromDummyTrack(String str, final boolean z) {
        if (Util.j(GaanaApplication.getContext())) {
            BusinessObject track = new Track();
            track.setBusinessObjId(str);
            track.setBusinessObjType(BusinessObjectType.Tracks);
            if (Util.c(track)) {
                this.mFetchedTrack = com.logging.d.a().a(null, DownloadManager.c().a(track.getBusinessObjType(), track.getBusinessObjId()), false);
                if (this.mFetchedTrack != null) {
                    this.mFetchedTrack.d(true);
                    if (z) {
                        setupQueue();
                        return;
                    }
                    return;
                }
                return;
            }
            Util.a(GaanaApplication.getContext(), null, track, false, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    JukeQueueManager.this.mFetchedTrack = com.logging.d.a().a(null, businessObject.getArrListBusinessObj() != null ? (Track) businessObject.getArrListBusinessObj().get(0) : null, false);
                    if (JukeQueueManager.this.mFetchedTrack != null) {
                        JukeQueueManager.this.mFetchedTrack.d(true);
                        if (z) {
                            JukeQueueManager.this.setupQueue();
                        }
                    }
                }
            });
            return;
        }
        if (this.jukePlaylist.getCurrentPlayingIndex() < 0) {
            this.jukePlaylist.setmCurrentPlayingIndex(0);
        }
        BusinessObject businessObject = (this.jukePlaylist.getArrListBusinessObj() == null || this.jukePlaylist.getCurrentPlayingIndex() >= this.jukePlaylist.getArrListBusinessObj().size()) ? null : (BusinessObject) this.jukePlaylist.getArrListBusinessObj().get(this.jukePlaylist.getCurrentPlayingIndex());
        if (businessObject != null) {
            businessObject = Util.d(businessObject);
            if (!businessObject.isLocalMedia() && Util.c(businessObject)) {
                this.mFetchedTrack = com.logging.d.a().a(null, DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId()), false);
                if (this.mFetchedTrack != null) {
                    this.mFetchedTrack.d(true);
                    if (z) {
                        setupQueue();
                    }
                }
            }
        }
    }

    private void fetchTrackDetails(BusinessObject businessObject, final boolean z) {
        if (Util.c(businessObject)) {
            this.mFetchedTrack = com.logging.d.a().a(null, DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId()), false);
            if (this.mFetchedTrack != null) {
                this.mFetchedTrack.d(z);
                if (z) {
                    setupQueue();
                    return;
                }
                return;
            }
            return;
        }
        Util.a(GaanaApplication.getContext(), null, businessObject, false, new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                JukeQueueManager.this.mFetchedTrack = com.logging.d.a().a(null, businessObject.getArrListBusinessObj() != null ? (Track) businessObject.getArrListBusinessObj().get(0) : null, false);
                if (JukeQueueManager.this.mFetchedTrack != null) {
                    JukeQueueManager.this.mFetchedTrack.d(z);
                    if (z) {
                        JukeQueueManager.this.setupQueue();
                    }
                }
            }
        });
    }

    public void onPlayerPlay() {
        JukeSessionManager.getInstance().reportCurrentPlaying(PlayerManager.a(GaanaApplication.getContext()).i().h(), JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
        setupTimer();
        updatePlaylistLocally();
    }

    public void onPlayerStop() {
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void seekTo(int i) {
        setupTimer();
    }
}

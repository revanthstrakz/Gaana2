package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;

class zzgu {
    protected AudioTrack zzacf;
    private boolean zzady;
    private long zzadz;
    private long zzaea;
    private long zzaeb;
    private long zzaec;
    private long zzaed;
    private long zzaee;
    private int zzzu;

    private zzgu() {
    }

    public boolean zzdf() {
        return false;
    }

    public void zza(AudioTrack audioTrack, boolean z) {
        this.zzacf = audioTrack;
        this.zzady = z;
        this.zzaec = C.TIME_UNSET;
        this.zzadz = 0;
        this.zzaea = 0;
        this.zzaeb = 0;
        if (audioTrack != null) {
            this.zzzu = audioTrack.getSampleRate();
        }
    }

    public final void zzp(long j) {
        this.zzaed = zzdd();
        this.zzaec = SystemClock.elapsedRealtime() * 1000;
        this.zzaee = j;
        this.zzacf.stop();
    }

    public final void pause() {
        if (this.zzaec == C.TIME_UNSET) {
            this.zzacf.pause();
        }
    }

    public final long zzdd() {
        if (this.zzaec != C.TIME_UNSET) {
            return Math.min(this.zzaee, this.zzaed + ((((SystemClock.elapsedRealtime() * 1000) - this.zzaec) * ((long) this.zzzu)) / 1000000));
        }
        int playState = this.zzacf.getPlayState();
        if (playState == 1) {
            return 0;
        }
        long j;
        long playbackHeadPosition = 4294967295L & ((long) this.zzacf.getPlaybackHeadPosition());
        if (this.zzady) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.zzaeb = this.zzadz;
            }
            j = playbackHeadPosition + this.zzaeb;
        } else {
            j = playbackHeadPosition;
        }
        if (this.zzadz > j) {
            this.zzaea++;
        }
        this.zzadz = j;
        return j + (this.zzaea << 32);
    }

    public final long zzde() {
        return (zzdd() * 1000000) / ((long) this.zzzu);
    }

    public long zzdg() {
        throw new UnsupportedOperationException();
    }

    public long zzdh() {
        throw new UnsupportedOperationException();
    }

    /* synthetic */ zzgu(zzgt zzgt) {
        this();
    }
}

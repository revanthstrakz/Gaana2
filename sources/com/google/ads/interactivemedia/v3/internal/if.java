package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VolumeProvider;
import com.google.ads.interactivemedia.v3.impl.data.s;
import com.google.ads.interactivemedia.v3.internal.jc.a;
import com.google.ads.interactivemedia.v3.internal.ji.b;

public class if implements VideoAdPlayerCallback, b {
    private final jd a;
    private final String b;
    private final ih c;
    private final VolumeProvider d;
    private boolean e = false;

    public if(jd jdVar, String str, ih ihVar, VolumeProvider volumeProvider) {
        this.a = jdVar;
        this.b = str;
        this.c = ihVar;
        this.d = volumeProvider;
    }

    public void onPlay() {
        this.e = false;
    }

    public void onPause() {
        this.c.c();
        a(jc.b.pause);
    }

    public void onLoaded() {
        a(jc.b.loaded);
    }

    public void onResume() {
        this.c.b();
        a(jc.b.play);
    }

    public void onEnded() {
        a(jc.b.end);
    }

    public void onError() {
        a(jc.b.error);
    }

    public void onVolumeChanged(int i) {
        a(jc.b.volumeChange, s.builder().volumePercentage(i).build());
    }

    public void a(VideoProgressUpdate videoProgressUpdate) {
        if (videoProgressUpdate != null && videoProgressUpdate.getDuration() > 0.0f) {
            b(videoProgressUpdate);
            a(jc.b.timeupdate, videoProgressUpdate);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(VideoProgressUpdate videoProgressUpdate) {
        if (!this.e && videoProgressUpdate.getCurrentTime() > 0.0f) {
            a(jc.b.start, s.builder().volumePercentage(this.d.getVolume()).build());
            this.e = true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(jc.b bVar) {
        a(bVar, null);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(jc.b bVar, Object obj) {
        this.a.b(new jc(a.videoDisplay, bVar, this.b, obj));
    }
}

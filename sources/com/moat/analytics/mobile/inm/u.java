package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class u extends h implements NativeVideoTracker {
    private WeakReference<MediaPlayer> m;

    u(String str) {
        StringBuilder stringBuilder;
        super(str);
        p.a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            stringBuilder = new StringBuilder("PartnerCode is ");
            stringBuilder.append(str == null ? "null" : "empty");
            str = stringBuilder.toString();
            stringBuilder = new StringBuilder("NativeDisplayTracker creation problem, ");
            stringBuilder.append(str);
            p.a("[ERROR] ", 3, "NativeVideoTracker", this, stringBuilder.toString());
            this.a = new m(str);
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append(" created");
        p.a("[SUCCESS] ", stringBuilder.toString());
    }

    private void a(MediaPlayer mediaPlayer) {
        if (mediaPlayer == null) {
            throw new m("Null player instance");
        }
        try {
            mediaPlayer.getCurrentPosition();
        } catch (Exception unused) {
            throw new m("Playback has already completed");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String a() {
        return "NativeVideoTracker";
    }

    /* Access modifiers changed, original: 0000 */
    public void a(List<String> list) {
        if (!n()) {
            list.add("Player is null");
        }
        super.a((List) list);
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, Object> i() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.m.get();
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean n() {
        return (this.m == null || this.m.get() == null) ? false : true;
    }

    /* Access modifiers changed, original: 0000 */
    public Integer o() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getCurrentPosition());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean q() {
        return ((MediaPlayer) this.m.get()).isPlaying();
    }

    /* Access modifiers changed, original: 0000 */
    public Integer r() {
        return Integer.valueOf(((MediaPlayer) this.m.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            c();
            d();
            a(mediaPlayer);
            this.m = new WeakReference(mediaPlayer);
            return super.a(map, view);
        } catch (Exception e) {
            m.a(e);
            String a = m.a("trackVideoAd", e);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(a);
            }
            p.a(3, "NativeVideoTracker", (Object) this, a);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(a);
            p.a("[ERROR] ", stringBuilder.toString());
            return false;
        }
    }
}

package com.facebook.ads.internal.adapters.a;

import com.facebook.ads.internal.adapters.a.c.a;
import com.google.ads.mediation.facebook.FacebookAdapter;
import java.io.Serializable;
import org.json.JSONObject;

public class h implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    private final c a;
    private final e b;
    private final b c;

    private h(c cVar, e eVar, b bVar) {
        this.a = cVar;
        this.b = eVar;
        this.c = bVar;
    }

    static h a(JSONObject jSONObject) {
        c a = new a().a(jSONObject.optString("title")).b(jSONObject.optString(FacebookAdapter.KEY_SUBTITLE_ASSET)).c(jSONObject.optString("body")).a();
        e eVar = new e(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        a b = new a().a(jSONObject.optString("video_url")).a(optBoolean).b(jSONObject.optBoolean("video_autoplay_with_sound"));
        int i = 0;
        if (optBoolean) {
            i = jSONObject.optInt("unskippable_seconds", 0);
        }
        a a2 = b.a(i);
        jSONObject = jSONObject.optJSONObject(TtmlNode.TAG_IMAGE);
        if (jSONObject != null) {
            a2.b(jSONObject.optString("url")).c(jSONObject.optInt("width")).d(jSONObject.optInt("height"));
        }
        return new h(a, eVar, a2.a());
    }

    public c a() {
        return this.a;
    }

    public e b() {
        return this.b;
    }

    public b c() {
        return this.c;
    }
}

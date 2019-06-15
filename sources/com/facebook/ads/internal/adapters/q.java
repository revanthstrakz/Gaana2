package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.comscore.android.id.IdHelperAndroid;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p.k;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.til.colombia.android.internal.h;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q extends b {
    private final j c;
    private k d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private View j;
    private List<View> k;
    private a l = a.ALL;

    public enum a {
        ALL(h.l),
        NONE(IdHelperAndroid.NO_ID_AVAILABLE),
        MANUAL("manual");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    public q(Context context, c cVar, com.facebook.ads.internal.t.a aVar, j jVar) {
        super(context, cVar, aVar);
        this.c = jVar;
    }

    private String b(View view) {
        try {
            return c(view).toString();
        } catch (JSONException unused) {
            return "Json exception";
        }
    }

    private JSONObject c(View view) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(view.getId()));
        jSONObject.putOpt("class", view.getClass());
        r5 = new Object[2];
        int i = 0;
        r5[0] = Integer.valueOf(view.getTop());
        boolean z = true;
        r5[1] = Integer.valueOf(view.getLeft());
        jSONObject.putOpt("origin", String.format(Locale.US, "{x:%d, y:%d}", r5));
        jSONObject.putOpt("size", String.format(Locale.US, "{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        if (this.k == null || !this.k.contains(view)) {
            z = false;
        }
        jSONObject.putOpt("clickable", Boolean.valueOf(z));
        Object obj = "unknown";
        if (view instanceof Button) {
            obj = "button";
        } else if (view instanceof TextView) {
            obj = MimeTypes.BASE_TYPE_TEXT;
        } else if (view instanceof ImageView) {
            obj = TtmlNode.TAG_IMAGE;
        } else if (view instanceof MediaView) {
            obj = "mediaview";
        } else if (view instanceof ViewGroup) {
            obj = "viewgroup";
        }
        jSONObject.putOpt("type", obj);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            while (i < viewGroup.getChildCount()) {
                jSONArray.put(c(viewGroup.getChildAt(i)));
                i++;
            }
            jSONObject.putOpt("list", jSONArray);
        }
        return jSONObject;
    }

    private String d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, this.c.j(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(View view) {
        this.j = view;
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void a(k kVar) {
        this.d = kVar;
    }

    public void a(List<View> list) {
        this.k = list;
    }

    /* Access modifiers changed, original: protected */
    public void a(Map<String, String> map) {
        if (this.c != null) {
            if (this.d != null) {
                map.put("nti", String.valueOf(this.d.c()));
            }
            if (this.e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.j != null && this.c.e()) {
                map.put(Promotion.ACTION_VIEW, b(this.j));
            }
            if (this.j != null && this.c.i()) {
                map.put("snapshot", d(this.j));
            }
            if (this.h) {
                map.put("niv", Boolean.TRUE.toString());
            }
            if (this.l != null) {
                map.put("precache_media", this.l.toString());
            }
            if (this.i) {
                map.put("ucvr", Boolean.TRUE.toString());
            }
            this.c.a((Map) map);
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public void d(boolean z) {
        this.h = z;
    }

    public void e(boolean z) {
        this.i = z;
    }
}

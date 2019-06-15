package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class jb extends ImageView implements OnClickListener {
    private final CompanionData a;
    private final jd b;
    private final String c;
    private final List<ClickListener> d;

    public jb(Context context, jd jdVar, CompanionData companionData, String str, List<ClickListener> list) {
        super(context);
        this.b = jdVar;
        this.a = companionData;
        this.c = str;
        this.d = list;
        setOnClickListener(this);
    }

    /* Access modifiers changed, original: 0000 */
    public Bitmap a(String str) throws IOException {
        return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
    }

    public void a() {
        new AsyncTask<Void, Void, Bitmap>() {
            Exception a = null;

            /* Access modifiers changed, original: protected|varargs */
            /* renamed from: a */
            public Bitmap doInBackground(Void... voidArr) {
                try {
                    return jb.this.a(jb.this.a.src());
                } catch (IOException e) {
                    this.a = e;
                    return null;
                }
            }

            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public void onPostExecute(Bitmap bitmap) {
                if (bitmap == null) {
                    String src = jb.this.a.src();
                    String valueOf = String.valueOf(this.a);
                    StringBuilder stringBuilder = new StringBuilder((33 + String.valueOf(src).length()) + String.valueOf(valueOf).length());
                    stringBuilder.append("Loading image companion ");
                    stringBuilder.append(src);
                    stringBuilder.append(" failed: ");
                    stringBuilder.append(valueOf);
                    Log.e("IMASDK", stringBuilder.toString());
                    return;
                }
                jb.this.b();
                jb.this.setImageBitmap(bitmap);
            }
        }.execute(new Void[0]);
    }

    private void b() {
        this.b.a(this.a.companionId(), this.c);
    }

    public void onClick(View view) {
        for (ClickListener onCompanionAdClick : this.d) {
            onCompanionAdClick.onCompanionAdClick();
        }
        this.b.c(this.a.clickThroughUrl());
    }
}

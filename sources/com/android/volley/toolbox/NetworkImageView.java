package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.g.c;
import com.android.volley.toolbox.g.d;
import com.gaana.application.GaanaApplication;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.library.helpers.Enums.ConnectionType;
import com.library.util.ConnectionUtil;

public class NetworkImageView extends ImageView {
    protected String a;
    protected Drawable b;
    protected ImageLoadingCompeletedListener c;
    private ConnectionType[] d;
    private ConnectionType[] e;
    private int f;
    private int g;
    private g h;
    private c i;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new ConnectionType[]{ConnectionType.H_SPEED, ConnectionType.WIFI, ConnectionType.L_SPEED};
        this.b = null;
        this.c = null;
    }

    public void setImageUrl(String str, g gVar) {
        this.a = str;
        this.h = gVar;
        a(false);
    }

    public String getImageURL() {
        return this.a;
    }

    public void setDefaultImageResId(int i) {
        this.f = i;
    }

    public void setErrorImageResId(int i) {
        this.g = i;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(final boolean z) {
        Object obj;
        Object obj2;
        int width = getWidth();
        int height = getHeight();
        ScaleType scaleType = getScaleType();
        Object obj3 = 1;
        if (getLayoutParams() != null) {
            obj = getLayoutParams().width == -2 ? 1 : null;
            obj2 = getLayoutParams().height == -2 ? 1 : null;
        } else {
            obj = null;
            obj2 = obj;
        }
        if (obj == null || obj2 == null) {
            obj3 = null;
        }
        if (width != 0 || height != 0 || obj3 != null) {
            if (TextUtils.isEmpty(this.a)) {
                if (this.i != null) {
                    this.i.a();
                    this.i = null;
                }
                b();
                return;
            }
            if (!(this.i == null || this.i.c() == null)) {
                if (!this.i.c().equals(this.a)) {
                    b();
                } else {
                    return;
                }
            }
            if (obj != null) {
                width = 0;
            }
            this.i = this.h.a(this.a, new d() {
                public void onErrorResponse(VolleyError volleyError) {
                    if (NetworkImageView.this.b != null) {
                        NetworkImageView.this.setImageDrawable(NetworkImageView.this.b);
                    }
                    if (NetworkImageView.this.c != null) {
                        NetworkImageView.this.c.onError();
                    }
                }

                public void a(final c cVar, boolean z) {
                    if (z && z) {
                        NetworkImageView.this.post(new Runnable() {
                            public void run() {
                                AnonymousClass1.this.a(cVar, false);
                            }
                        });
                        return;
                    }
                    if (cVar.b() != null) {
                        NetworkImageView.this.setImageBitmap(cVar.b());
                    } else if (NetworkImageView.this.f != 0) {
                        NetworkImageView.this.setImageResource(NetworkImageView.this.f);
                    }
                }
            }, width, obj2 != null ? 0 : height, scaleType, a());
        }
    }

    private void b() {
        if (this.f != 0) {
            setImageResource(this.f);
        } else {
            setImageBitmap(null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(true);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        if (this.i != null) {
            this.i.a();
            setImageBitmap(null);
            this.i = null;
        }
        super.onDetachedFromWindow();
    }

    /* Access modifiers changed, original: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public boolean a() {
        return c().booleanValue() ^ 1;
    }

    public ConnectionType[] getBandwidthModes() {
        if (this.e == null) {
            this.e = this.d;
        }
        return this.e;
    }

    public void setBandwithMode(ConnectionType... connectionTypeArr) {
        this.e = connectionTypeArr;
    }

    private Boolean c() {
        if (GaanaApplication.getInstance().isAppInDataSaveMode()) {
            return Boolean.valueOf(false);
        }
        ConnectionType[] bandwidthModes = getBandwidthModes();
        ConnectionType connectionType = ConnectionUtil.getConnectionType(getContext());
        for (ConnectionType connectionType2 : bandwidthModes) {
            if (connectionType2 == connectionType) {
                return Boolean.valueOf(true);
            }
        }
        return Boolean.valueOf(false);
    }
}

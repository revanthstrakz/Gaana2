package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import net.hockeyapp.android.a;
import net.hockeyapp.android.d.f;
import net.hockeyapp.android.i;
import net.hockeyapp.android.i.d;
import net.hockeyapp.android.objects.FeedbackAttachment;

@SuppressLint({"ViewConstructor"})
public class AttachmentView extends FrameLayout {
    private final Context a;
    private final ViewGroup b;
    private final FeedbackAttachment c;
    private final Uri d;
    private final String e;
    private ImageView f;
    private TextView g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;

    public AttachmentView(Context context, ViewGroup viewGroup, Uri uri, boolean z) {
        super(context);
        this.a = context;
        this.b = viewGroup;
        this.c = null;
        this.d = uri;
        this.e = uri.getLastPathSegment();
        a(20);
        a(context, z);
        this.g.setText(this.e);
        new AsyncTask<Void, Void, Bitmap>() {
            /* Access modifiers changed, original: protected|varargs */
            /* renamed from: a */
            public Bitmap doInBackground(Void... voidArr) {
                return AttachmentView.this.c();
            }

            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    AttachmentView.this.a(bitmap, false);
                } else {
                    AttachmentView.this.a(false);
                }
            }
        }.execute(new Void[0]);
    }

    public AttachmentView(Context context, ViewGroup viewGroup, FeedbackAttachment feedbackAttachment, boolean z) {
        super(context);
        this.a = context;
        this.b = viewGroup;
        this.c = feedbackAttachment;
        this.d = Uri.fromFile(new File(a.a(), feedbackAttachment.c()));
        this.e = feedbackAttachment.a();
        a(30);
        a(context, z);
        this.m = 0;
        this.g.setText(d.hockeyapp_feedback_attachment_loading);
        a(false);
    }

    public FeedbackAttachment getAttachment() {
        return this.c;
    }

    public Uri getAttachmentUri() {
        return this.d;
    }

    public int getWidthPortrait() {
        return this.h;
    }

    public int getMaxHeightPortrait() {
        return this.i;
    }

    public int getWidthLandscape() {
        return this.j;
    }

    public int getMaxHeightLandscape() {
        return this.k;
    }

    public int getGap() {
        return this.l;
    }

    public int getEffectiveMaxHeight() {
        return this.m == 1 ? this.k : this.i;
    }

    public void a() {
        this.b.removeView(this);
    }

    public void setImage(Bitmap bitmap, int i) {
        this.g.setText(this.e);
        this.m = i;
        if (bitmap == null) {
            a(true);
        } else {
            a(bitmap, true);
        }
    }

    public void b() {
        this.g.setText(d.hockeyapp_feedback_attachment_error);
    }

    private void a(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.l = Math.round(TypedValue.applyDimension(1, 10.0f, displayMetrics));
        int round = displayMetrics.widthPixels - (Math.round(TypedValue.applyDimension(1, (float) i, displayMetrics)) * 2);
        i = round - (this.l * 2);
        round -= this.l;
        this.h = i / 3;
        this.j = round / 2;
        this.i = this.h * 2;
        this.k = this.j;
    }

    private void a(Context context, boolean z) {
        setLayoutParams(new LayoutParams(-2, -2, 80));
        setPadding(0, this.l, 0, 0);
        this.f = new ImageView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2, 80));
        linearLayout.setGravity(8388611);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(Color.parseColor("#80262626"));
        this.g = new TextView(context);
        this.g.setLayoutParams(new LayoutParams(-1, -2, 17));
        this.g.setGravity(17);
        this.g.setTextColor(context.getResources().getColor(i.a.hockeyapp_text_white));
        this.g.setSingleLine();
        this.g.setEllipsize(TruncateAt.MIDDLE);
        if (z) {
            ImageButton imageButton = new ImageButton(context);
            imageButton.setLayoutParams(new LayoutParams(-1, -2, 80));
            imageButton.setAdjustViewBounds(true);
            imageButton.setImageDrawable(a("ic_menu_delete"));
            imageButton.setBackgroundResource(0);
            imageButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AttachmentView.this.a();
                }
            });
            linearLayout.addView(imageButton);
        }
        linearLayout.addView(this.g);
        addView(this.f);
        addView(linearLayout);
    }

    private void a(Bitmap bitmap, final boolean z) {
        int i = this.m == 1 ? this.j : this.h;
        int i2 = this.m == 1 ? this.k : this.i;
        this.g.setMaxWidth(i);
        this.g.setMinWidth(i);
        this.f.setLayoutParams(new LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(true);
        this.f.setMinimumWidth(i);
        this.f.setMaxWidth(i);
        this.f.setMaxHeight(i2);
        this.f.setScaleType(ScaleType.CENTER_INSIDE);
        this.f.setImageBitmap(bitmap);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (z) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(AttachmentView.this.d, "image/*");
                    AttachmentView.this.a.startActivity(intent);
                }
            }
        });
    }

    private void a(final boolean z) {
        this.g.setMaxWidth(this.h);
        this.g.setMinWidth(this.h);
        this.f.setLayoutParams(new LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(false);
        this.f.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.f.setMinimumHeight((int) (((float) this.h) * 1.2f));
        this.f.setMinimumWidth(this.h);
        this.f.setScaleType(ScaleType.FIT_CENTER);
        this.f.setImageDrawable(a("ic_menu_attachment"));
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (z) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(AttachmentView.this.d, "*/*");
                    AttachmentView.this.a.startActivity(intent);
                }
            }
        });
    }

    private Bitmap c() {
        try {
            this.m = f.a(this.a, this.d);
            return f.a(this.a, this.d, this.m == 1 ? this.j : this.h, this.m == 1 ? this.k : this.i);
        } catch (Throwable unused) {
            return null;
        }
    }

    private Drawable a(String str) {
        if (VERSION.SDK_INT >= 21) {
            return getResources().getDrawable(getResources().getIdentifier(str, "drawable", "android"), this.a.getTheme());
        }
        return getResources().getDrawable(getResources().getIdentifier(str, "drawable", "android"));
    }
}

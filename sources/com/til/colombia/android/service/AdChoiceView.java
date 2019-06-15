package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.utils.b;

public class AdChoiceView extends FrameLayout {
    private OnClickListener clickListener = new a(this);
    private ImageView img;
    private Context mcontext = null;

    public AdChoiceView(Context context) {
        super(context);
        this.img = new ImageView(context);
        this.mcontext = context;
        setOnClickListener(this.clickListener);
        loadImage(a.q(), this.img);
        addView(this.img);
    }

    public AdChoiceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.img = new ImageView(context, attributeSet);
        this.mcontext = context;
        setOnClickListener(this.clickListener);
        loadImage(a.q(), this.img);
        addView(this.img);
    }

    public AdChoiceView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.img = new ImageView(context, attributeSet, i);
        this.mcontext = context;
        setOnClickListener(this.clickListener);
        loadImage(a.q(), this.img);
        addView(this.img);
    }

    @TargetApi(21)
    public AdChoiceView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.img = new ImageView(context, attributeSet, i, i2);
        this.mcontext = context;
        setOnClickListener(this.clickListener);
        loadImage(a.q(), this.img);
        addView(this.img);
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        super.setOnClickListener(this.clickListener);
    }

    private void loadImage(String str, ImageView imageView) {
        if (!h.a(str)) {
            imageView.setVisibility(0);
            Bitmap a = com.til.colombia.android.commons.a.h.a(str);
            if (a != null) {
                imageView.setImageBitmap(a);
                return;
            }
            new b().a(imageView, str);
        }
    }
}

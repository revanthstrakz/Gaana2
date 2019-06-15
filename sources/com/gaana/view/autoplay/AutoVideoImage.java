package com.gaana.view.autoplay;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.gaana.R;

public class AutoVideoImage extends FrameLayout {
    private VideoPlayerAutoPlayView cvv;
    private ImageView iv;
    private Context mContext;

    public AutoVideoImage(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public AutoVideoImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    public AutoVideoImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init();
    }

    @TargetApi(21)
    public AutoVideoImage(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
        init();
    }

    public VideoPlayerAutoPlayView getCustomVideoView() {
        return this.cvv;
    }

    public ImageView getImageView() {
        return this.iv;
    }

    private void init() {
        setTag("aah_vi");
        this.cvv = new VideoPlayerAutoPlayView(this.mContext);
        this.iv = new ImageView(this.mContext);
        this.iv.setScaleType(ScaleType.FIT_XY);
        this.iv.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.placeholder_album_artwork_large));
        addView(this.iv);
        LayoutParams layoutParams = (LayoutParams) this.iv.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        addView(this.cvv);
        layoutParams = (LayoutParams) this.cvv.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.cvv.requestFocus();
    }
}

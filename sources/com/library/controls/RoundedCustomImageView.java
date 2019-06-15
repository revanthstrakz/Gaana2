package com.library.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaana.R;

public class RoundedCustomImageView extends FrameLayout {
    private RoundedCornerImageView backgroundImage;
    private ImageView iconImage;
    private String imageSize;
    private ImageView indicatorIconLeft;
    private TextView playCountText;

    public RoundedCustomImageView(Context context) {
        super(context);
    }

    public RoundedCustomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_rounded_image, this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Options, 0, 0);
        float dimension = obtainStyledAttributes.getDimension(0, 24.0f);
        float dimension2 = obtainStyledAttributes.getDimension(2, 24.0f);
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        this.imageSize = obtainStyledAttributes.getString(4);
        obtainStyledAttributes.recycle();
        this.backgroundImage = (RoundedCornerImageView) inflate.findViewById(R.id.backgroundImage);
        this.iconImage = (ImageView) inflate.findViewById(R.id.iconImage);
        this.indicatorIconLeft = (ImageView) inflate.findViewById(R.id.indicatorIconLeft);
        this.iconImage.setImageDrawable(ContextCompat.getDrawable(getContext(), resourceId));
        this.iconImage.getLayoutParams().width = (int) dimension2;
        this.iconImage.getLayoutParams().height = (int) dimension;
        this.playCountText = (TextView) inflate.findViewById(R.id.playCount);
    }

    public void bindImage(String str) {
        this.backgroundImage.bindImage(str);
    }

    public void bindRectImage(String str) {
        this.backgroundImage.bindRectImage(str);
    }

    public void setRightIconVisibility(boolean z) {
        if (z) {
            this.iconImage.setVisibility(0);
        } else {
            this.iconImage.setVisibility(8);
        }
    }

    public ImageView getIconImage() {
        return this.iconImage;
    }

    public void setLeftIndicationIcon(String str) {
        if (str != null) {
            if (str.equalsIgnoreCase("Video")) {
                this.iconImage.setImageResource(R.drawable.vector_ic_video_indicator);
                this.indicatorIconLeft.setVisibility(8);
            } else if (str.equalsIgnoreCase("Playlist")) {
                this.indicatorIconLeft.setImageResource(R.drawable.vector_ic_playlist_indicator);
                this.indicatorIconLeft.setVisibility(0);
                this.iconImage.setImageResource(R.drawable.ic_artwork_play);
            } else if (str.equalsIgnoreCase("Album")) {
                this.indicatorIconLeft.setImageResource(R.drawable.vector_ic_playlist_indicator);
                this.indicatorIconLeft.setVisibility(0);
                this.iconImage.setImageResource(R.drawable.ic_artwork_play);
            } else if (str.equalsIgnoreCase("Track")) {
                this.indicatorIconLeft.setImageResource(R.drawable.vector_ic_tracks_indicator);
                this.indicatorIconLeft.setVisibility(0);
                this.iconImage.setImageResource(R.drawable.ic_artwork_play);
            } else {
                this.indicatorIconLeft.setVisibility(8);
            }
        }
    }

    public void setplayCount(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            this.playCountText.setVisibility(8);
            return;
        }
        this.playCountText.setText(str);
        this.playCountText.setVisibility(0);
    }

    public void setHeightWidth(int i, RoundedCustomImageView roundedCustomImageView, Context context) {
        if (this.imageSize != null && this.imageSize.equalsIgnoreCase("large")) {
            float f = (float) (i * 2);
            roundedCustomImageView.getLayoutParams().width = (int) (context.getResources().getDimension(R.dimen.dp10) + f);
            roundedCustomImageView.getLayoutParams().height = (int) (f + context.getResources().getDimension(R.dimen.dp10));
        } else if (this.imageSize != null && this.imageSize.equalsIgnoreCase("vertical")) {
            roundedCustomImageView.getLayoutParams().width = i;
            roundedCustomImageView.getLayoutParams().height = (int) (((float) (i * 2)) + context.getResources().getDimension(R.dimen.dp10));
        } else if (this.imageSize != null && this.imageSize.equalsIgnoreCase("small")) {
            roundedCustomImageView.getLayoutParams().width = i;
            roundedCustomImageView.getLayoutParams().height = i;
        }
    }

    public String getImageSize() {
        return this.imageSize;
    }

    public void setAutoPlayVideoWidthHeight(int i, ViewGroup viewGroup, Context context) {
        if (this.imageSize != null && this.imageSize.equalsIgnoreCase("large")) {
            float f = (float) (i * 2);
            viewGroup.getLayoutParams().width = (int) (context.getResources().getDimension(R.dimen.dp10) + f);
            viewGroup.getLayoutParams().height = (int) (f + context.getResources().getDimension(R.dimen.dp10));
        } else if (this.imageSize != null && this.imageSize.equalsIgnoreCase("vertical")) {
            viewGroup.getLayoutParams().width = i;
            viewGroup.getLayoutParams().height = (int) (((float) (i * 2)) + context.getResources().getDimension(R.dimen.dp10));
        }
    }
}

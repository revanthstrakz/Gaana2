package com.til.colombia.android.vast;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.til.colombia.android.commons.a.a;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class VastCompanionResource implements Serializable {
    public static final List<String> VALID_APPLICATION_TYPES = Arrays.asList(new String[]{"application/x-javascript"});
    public static final List<String> VALID_IMAGE_TYPES = Arrays.asList(new String[]{"image/jpeg", "image/png", "image/bmp", "image/gif"});
    private static final long serialVersionUID = 0;
    private CreativeType mCreativeType;
    private int mHeight;
    private String mResource;
    private Type mType;
    private int mWidth;

    public enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT,
        GIF
    }

    public enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    public VastCompanionResource(String str, Type type, CreativeType creativeType, int i, int i2) {
        this.mResource = str;
        this.mType = type;
        this.mCreativeType = creativeType;
        this.mWidth = i;
        this.mHeight = i2;
    }

    public String getResource() {
        return this.mResource;
    }

    public Type getType() {
        return this.mType;
    }

    public CreativeType getCreativeType() {
        return this.mCreativeType;
    }

    public void initializeVastResourceView(View view) {
        if (view != null) {
            i iVar;
            StringBuilder stringBuilder;
            if (this.mType == Type.IFRAME_RESOURCE) {
                iVar = (i) view;
                stringBuilder = new StringBuilder("<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\" width=\"");
                stringBuilder.append(this.mWidth);
                stringBuilder.append("\" height=\"");
                stringBuilder.append(this.mHeight);
                stringBuilder.append("\" src=\"");
                stringBuilder.append(this.mResource);
                stringBuilder.append("\"></iframe>");
                iVar.a(stringBuilder.toString());
            } else if (this.mType == Type.HTML_RESOURCE) {
                ((i) view).a(this.mResource);
            } else {
                if (this.mType == Type.STATIC_RESOURCE) {
                    if (this.mCreativeType == CreativeType.IMAGE) {
                        if (view instanceof ImageView) {
                            ((ImageView) view).setAdjustViewBounds(true);
                            a.a(this.mResource, new b(this, view));
                        }
                    } else if (this.mCreativeType == CreativeType.JAVASCRIPT) {
                        stringBuilder = new StringBuilder("<script src=\"");
                        stringBuilder.append(this.mResource);
                        stringBuilder.append("\"></script>");
                        ((i) view).a(stringBuilder.toString());
                    } else if (this.mCreativeType == CreativeType.GIF) {
                        iVar = (i) view;
                        String str = this.mResource;
                        iVar.getSettings().setLoadWithOverviewMode(true);
                        iVar.getSettings().setUseWideViewPort(true);
                        iVar.loadUrl(str);
                    }
                }
            }
        }
    }

    private void fillImageView(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public String getCorrectClickThroughUrl(String str, String str2) {
        switch (f.a[this.mType.ordinal()]) {
            case 1:
                if (CreativeType.IMAGE == this.mCreativeType || CreativeType.GIF == this.mCreativeType) {
                    return str;
                }
                if (CreativeType.JAVASCRIPT == this.mCreativeType) {
                    return str2;
                }
                return null;
            case 2:
            case 3:
                return str2;
            default:
                return null;
        }
    }
}

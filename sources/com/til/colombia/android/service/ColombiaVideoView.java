package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.CommonUtil.MediaSource;

@TargetApi(14)
public class ColombiaVideoView extends RelativeLayout {
    private static final String TAG = "ColombiaNativeVideoAdView";
    private CmItem item;
    private Context mContext;
    private View mRoot;
    private v mVideoView;
    private MediaSource srcType;

    public ColombiaVideoView(Context context) {
        super(context);
        this.mContext = context;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public ColombiaVideoView(Context context, int i, int i2) {
        super(context);
        this.mContext = context;
        setLayoutParams(new LayoutParams(CommonUtil.b((float) i, this.mContext), CommonUtil.b((float) i2, this.mContext)));
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public ColombiaVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    /* Access modifiers changed, original: protected */
    public void setNativeAd(CmItem cmItem, View view) {
        this.item = cmItem;
        this.mRoot = view;
        NativeItem nativeItem = (NativeItem) cmItem;
        this.srcType = nativeItem.getMediaSrcMode();
        initializeHelper(nativeItem.getMediaSrc());
    }

    public String getItemUid() {
        return ((NativeItem) this.item).getUID();
    }

    private void initializeHelper(String str) {
        if (str == null || str.isEmpty()) {
            setVisibility(8);
            return;
        }
        if (this.srcType == MediaSource.VAST_URL || this.srcType == MediaSource.VPAID_URL || this.srcType == MediaSource.VAST_XML) {
            initView();
        }
    }

    private void initView() {
        this.mVideoView = new v(this.mContext, this, this.mRoot);
        this.mVideoView.a(this.item);
        this.mVideoView.a();
        this.mRoot.setVisibility(0);
        if (this.mRoot.getLayoutParams() != null) {
            this.mRoot.getLayoutParams().height = -2;
        }
        if (((NativeItem) this.item).getAdManager() != null) {
            ((NativeItem) this.item).getAdManager().addVideoView(getItemUid(), (ColombiaNativeVideoAdView) this.mRoot);
        }
    }

    public void refresh() {
        if (this.mVideoView != null) {
            this.mVideoView.l();
        }
    }

    public void pause() {
        if (this.mVideoView != null) {
            this.mVideoView.j();
        }
    }

    public void autoPause() {
        if (this.mVideoView != null) {
            this.mVideoView.k();
        }
    }

    public boolean isPlaying() {
        return this.mVideoView == null || this.mVideoView.i();
    }

    public void clear() {
        if (this.mVideoView != null) {
            v vVar = this.mVideoView;
            if (vVar.b != null) {
                vVar.b.b(vVar.a);
            }
            if (vVar.c != null) {
                vVar.c.release();
                vVar.c = null;
            }
        }
    }

    private void hideColombiaNativeVideoAdView() {
        if (this.mRoot != null) {
            this.mRoot.setVisibility(8);
            if (this.mRoot.getLayoutParams() != null) {
                this.mRoot.getLayoutParams().height = 1;
            }
        }
    }
}

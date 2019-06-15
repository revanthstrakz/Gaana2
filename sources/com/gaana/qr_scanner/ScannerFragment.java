package com.gaana.qr_scanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.qr_scanner.QREader.Builder;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.services.c;
import com.services.f;
import com.services.f.b;
import com.utilities.Util;
import com.utilities.h;

public class ScannerFragment extends BaseGaanaFragment {
    private static final String cameraPerm = "android.permission.CAMERA";
    boolean hasCameraPermission = false;
    private Context mContext;
    private b mDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            if (ScannerFragment.this.isAdded()) {
                ScannerFragment.this.getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        }
    };
    private f mDialogs;
    private b mOkDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            if (ScannerFragment.this.isAdded()) {
                ScannerFragment.this.getActivity().getSupportFragmentManager().popBackStack();
            }
        }
    };
    private SurfaceView mySurfaceView;
    private QREader qrEader;

    /* Access modifiers changed, original: 0000 */
    public void restartActivity() {
    }

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mContext = getActivity();
        this.mDialogs = new f(this.mContext);
        View inflate = layoutInflater.inflate(R.layout.fragment_scanner, viewGroup, false);
        this.hasCameraPermission = h.d(this.mContext);
        this.mySurfaceView = (SurfaceView) inflate.findViewById(R.id.camera_view);
        if (this.hasCameraPermission) {
            setupQREader();
        } else {
            requestPermissions(new String[]{cameraPerm}, 100);
        }
        inflate.findViewById(R.id.fragment_scanner_cross).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) ScannerFragment.this.mContext).onBackPressed();
            }
        });
        View findViewById = inflate.findViewById(R.id.fragment_scanner_scan);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-Util.b(35)), (float) Util.b((int) MoEHelperUtils.BASELINE_SCREEN_DPI));
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setRepeatMode(1);
        translateAnimation.setRepeatCount(-1);
        findViewById.startAnimation(translateAnimation);
        return inflate;
    }

    /* Access modifiers changed, original: 0000 */
    public void setupQREader() {
        this.qrEader = new Builder(this.mContext, this.mySurfaceView, new QRDataListener() {
            public void onDetected(final String str) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Value : ");
                stringBuilder.append(str);
                Log.d("QREader", stringBuilder.toString());
                ((GaanaActivity) ScannerFragment.this.mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        if (TextUtils.isEmpty(str) || !str.contains("/juke/")) {
                            f access$200 = ScannerFragment.this.mDialogs;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Invalid Party Code : ");
                            stringBuilder.append(str);
                            access$200.a(stringBuilder.toString());
                            return;
                        }
                        if (ScannerFragment.this.qrEader.isCameraRunning()) {
                            ScannerFragment.this.qrEader.stop();
                        }
                        ((GaanaActivity) ScannerFragment.this.mContext).popBackStackImmediate();
                        c.a(ScannerFragment.this.mContext).a(ScannerFragment.this.mContext, str, GaanaApplication.getInstance());
                    }
                });
            }
        }).facing(0).enableAutofocus(true).height(this.mySurfaceView.getHeight()).width(this.mySurfaceView.getWidth()).build();
    }

    public void onPause() {
        super.onPause();
        if (this.hasCameraPermission) {
            this.qrEader.releaseAndCleanup();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.hasCameraPermission) {
            this.qrEader.initAndStart(this.mySurfaceView);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != 100) {
            return;
        }
        if (h.d(this.mContext)) {
            setupQREader();
            this.qrEader.initAndStart(this.mySurfaceView);
            return;
        }
        ((GaanaActivity) this.mContext).onBackPressed();
    }
}

package com.gaana.qr_scanner;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;

public class QREader {
    public static final int BACK_CAM = 0;
    public static final int FRONT_CAM = 1;
    private final String LOGTAG;
    private boolean autoFocusEnabled;
    private BarcodeDetector barcodeDetector;
    private boolean cameraRunning;
    private CameraSource cameraSource;
    private final Context context;
    private final int facing;
    private final int height;
    private final QRDataListener qrDataListener;
    private boolean surfaceCreated;
    private final Callback surfaceHolderCallback;
    private final SurfaceView surfaceView;
    private final int width;

    public static class Builder {
        private boolean autofocusEnabled = true;
        private BarcodeDetector barcodeDetector;
        private final Context context;
        private int facing = 0;
        private int height = 800;
        private final QRDataListener qrDataListener;
        private final SurfaceView surfaceView;
        private int width = 800;

        public Builder(Context context, SurfaceView surfaceView, QRDataListener qRDataListener) {
            this.qrDataListener = qRDataListener;
            this.context = context;
            this.surfaceView = surfaceView;
        }

        public Builder enableAutofocus(boolean z) {
            this.autofocusEnabled = z;
            return this;
        }

        public Builder width(int i) {
            if (i != 0) {
                this.width = i;
            }
            return this;
        }

        public Builder height(int i) {
            if (i != 0) {
                this.height = i;
            }
            return this;
        }

        public Builder facing(int i) {
            this.facing = i;
            return this;
        }

        public QREader build() {
            return new QREader(this, null);
        }

        public void barcodeDetector(BarcodeDetector barcodeDetector) {
            this.barcodeDetector = barcodeDetector;
        }
    }

    /* synthetic */ QREader(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private QREader(Builder builder) {
        this.LOGTAG = getClass().getSimpleName();
        this.cameraSource = null;
        this.barcodeDetector = null;
        this.cameraRunning = false;
        this.surfaceCreated = false;
        this.surfaceHolderCallback = new Callback() {
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                QREader.this.surfaceCreated = true;
                QREader.this.startCameraView(QREader.this.context, QREader.this.cameraSource, QREader.this.surfaceView);
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                QREader.this.surfaceCreated = false;
                QREader.this.stop();
                surfaceHolder.removeCallback(this);
            }
        };
        this.autoFocusEnabled = builder.autofocusEnabled;
        this.width = builder.width;
        this.height = builder.height;
        this.facing = builder.facing;
        this.qrDataListener = builder.qrDataListener;
        this.context = builder.context;
        this.surfaceView = builder.surfaceView;
        if (builder.barcodeDetector == null) {
            this.barcodeDetector = BarcodeDetectorHolder.getBarcodeDetector(this.context);
        } else {
            this.barcodeDetector = builder.barcodeDetector;
        }
    }

    public void initAndStart(final SurfaceView surfaceView) {
        surfaceView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                QREader.this.init();
                QREader.this.start();
                QREader.removeOnGlobalLayoutListener(surfaceView, this);
            }
        });
    }

    private void init() {
        if (!hasAutofocus(this.context)) {
            Log.e(this.LOGTAG, "Do not have autofocus feature, disabling autofocus feature in the library!");
            this.autoFocusEnabled = false;
        }
        if (!hasCameraHardware(this.context)) {
            Log.e(this.LOGTAG, "Does not have camera hardware!");
        } else if (checkCameraPermission(this.context)) {
            if (this.barcodeDetector.isOperational()) {
                this.barcodeDetector.setProcessor(new Processor<Barcode>() {
                    public void release() {
                    }

                    public void receiveDetections(Detections<Barcode> detections) {
                        SparseArray detectedItems = detections.getDetectedItems();
                        if (detectedItems.size() != 0 && QREader.this.qrDataListener != null) {
                            QREader.this.qrDataListener.onDetected(((Barcode) detectedItems.valueAt(0)).displayValue);
                        }
                    }
                });
                this.cameraSource = new com.google.android.gms.vision.CameraSource.Builder(this.context, this.barcodeDetector).setAutoFocusEnabled(this.autoFocusEnabled).setFacing(this.facing).setRequestedPreviewSize(this.width, this.height).build();
            } else {
                Log.e(this.LOGTAG, "Barcode recognition libs are not downloaded and are not operational");
            }
        } else {
            Log.e(this.LOGTAG, "Do not have camera permission!");
        }
    }

    public void start() {
        if (this.surfaceView != null && this.surfaceHolderCallback != null) {
            if (this.surfaceCreated) {
                startCameraView(this.context, this.cameraSource, this.surfaceView);
            } else {
                this.surfaceView.getHolder().addCallback(this.surfaceHolderCallback);
            }
        }
    }

    @TargetApi(16)
    private static void removeOnGlobalLayoutListener(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    private boolean hasAutofocus(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.autofocus");
    }

    private boolean hasCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    private boolean checkCameraPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.CAMERA") == 0;
    }

    private void startCameraView(Context context, CameraSource cameraSource, SurfaceView surfaceView) {
        if (this.cameraRunning) {
            throw new IllegalStateException("Camera already started!");
        }
        try {
            if (ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") != 0) {
                Log.e(this.LOGTAG, "Permission not granted!");
            } else if (!this.cameraRunning && cameraSource != null && surfaceView != null) {
                cameraSource.start(surfaceView.getHolder());
                this.cameraRunning = true;
            }
        } catch (IOException e) {
            Log.e(this.LOGTAG, e.getMessage());
            ThrowableExtension.printStackTrace(e);
        }
    }

    public boolean isCameraRunning() {
        return this.cameraRunning;
    }

    public void releaseAndCleanup() {
        stop();
        if (this.cameraSource != null) {
            this.cameraSource.release();
            this.cameraSource = null;
        }
    }

    public void stop() {
        try {
            if (this.cameraRunning && this.cameraSource != null) {
                this.cameraSource.stop();
                this.cameraRunning = false;
            }
        } catch (Exception e) {
            Log.e(this.LOGTAG, e.getMessage());
            ThrowableExtension.printStackTrace(e);
        }
    }
}

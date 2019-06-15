package com.facebook.places.internal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@TargetApi(21)
public class BleScannerImpl implements BleScanner {
    private static final String TAG = "BleScannerImpl";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private Context context;
    private int errorCode;
    private boolean isScanInProgress;
    private LocationPackageRequestParams params;
    private ScanCallBackImpl scanCallBack;
    private final List<BluetoothScanResult> scanResults = new ArrayList();

    private class ScanCallBackImpl extends ScanCallback {
        private ScanCallBackImpl() {
        }

        /* synthetic */ ScanCallBackImpl(BleScannerImpl bleScannerImpl, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onScanFailed(int i) {
            super.onScanFailed(i);
            BleScannerImpl.this.errorCode = i;
        }

        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    for (ScanResult access$400 : list) {
                        BluetoothScanResult access$4002 = BleScannerImpl.newBluetoothScanResult(access$400);
                        if (access$4002 != null) {
                            BleScannerImpl.this.scanResults.add(access$4002);
                        }
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }

        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    BluetoothScanResult access$400 = BleScannerImpl.newBluetoothScanResult(scanResult);
                    if (access$400 != null) {
                        BleScannerImpl.this.scanResults.add(access$400);
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }
    }

    BleScannerImpl(Context context, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context;
        this.params = locationPackageRequestParams;
    }

    public synchronized void initAndCheckEligibility() throws ScannerException {
        if (VERSION.SDK_INT < 21) {
            throw new ScannerException(Type.NOT_SUPPORTED);
        } else if (!Validate.hasBluetoothPermission(this.context)) {
            throw new ScannerException(Type.PERMISSION_DENIED);
        } else if (Validate.hasLocationPermission(this.context)) {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (this.bluetoothAdapter != null) {
                if (this.bluetoothAdapter.isEnabled()) {
                    this.bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
                    if (this.bluetoothLeScanner == null) {
                        throw new ScannerException(Type.UNKNOWN_ERROR);
                    }
                }
            }
            throw new ScannerException(Type.DISABLED);
        } else {
            throw new ScannerException(Type.PERMISSION_DENIED);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004d */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:20|21|22) */
    /* JADX WARNING: Missing block: B:22:0x0054, code skipped:
            throw new com.facebook.places.internal.ScannerException(com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR);
     */
    public synchronized void startScanning() throws com.facebook.places.internal.ScannerException {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.isScanInProgress;	 Catch:{ all -> 0x0058 }
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        r0 = new com.facebook.places.internal.ScannerException;	 Catch:{ all -> 0x0058 }
        r1 = com.facebook.places.internal.ScannerException.Type.SCAN_ALREADY_IN_PROGRESS;	 Catch:{ all -> 0x0058 }
        r0.<init>(r1);	 Catch:{ all -> 0x0058 }
        throw r0;	 Catch:{ all -> 0x0058 }
    L_0x000d:
        r0 = new com.facebook.places.internal.BleScannerImpl$ScanCallBackImpl;	 Catch:{ all -> 0x0058 }
        r1 = 0;
        r0.<init>(r5, r1);	 Catch:{ all -> 0x0058 }
        r5.scanCallBack = r0;	 Catch:{ all -> 0x0058 }
        r0 = 1;
        r5.isScanInProgress = r0;	 Catch:{ all -> 0x0058 }
        r2 = 0;
        r5.errorCode = r2;	 Catch:{ all -> 0x0058 }
        r2 = r5.scanResults;	 Catch:{ all -> 0x0058 }
        monitor-enter(r2);	 Catch:{ all -> 0x0058 }
        r3 = r5.scanResults;	 Catch:{ all -> 0x0055 }
        r3.clear();	 Catch:{ all -> 0x0055 }
        monitor-exit(r2);	 Catch:{ all -> 0x0055 }
        r2 = r5.bluetoothLeScanner;	 Catch:{ all -> 0x0058 }
        if (r2 != 0) goto L_0x0030;
    L_0x0028:
        r0 = new com.facebook.places.internal.ScannerException;	 Catch:{ all -> 0x0058 }
        r1 = com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR;	 Catch:{ all -> 0x0058 }
        r0.<init>(r1);	 Catch:{ all -> 0x0058 }
        throw r0;	 Catch:{ all -> 0x0058 }
    L_0x0030:
        r2 = new android.bluetooth.le.ScanSettings$Builder;	 Catch:{ Exception -> 0x004d }
        r2.<init>();	 Catch:{ Exception -> 0x004d }
        r3 = 2;
        r2.setScanMode(r3);	 Catch:{ Exception -> 0x004d }
        r3 = 0;
        r2.setReportDelay(r3);	 Catch:{ Exception -> 0x004d }
        r3 = r5.bluetoothLeScanner;	 Catch:{ Exception -> 0x004d }
        r2 = r2.build();	 Catch:{ Exception -> 0x004d }
        r4 = r5.scanCallBack;	 Catch:{ Exception -> 0x004d }
        r3.startScan(r1, r2, r4);	 Catch:{ Exception -> 0x004d }
        r5.isScanInProgress = r0;	 Catch:{ Exception -> 0x004d }
        monitor-exit(r5);
        return;
    L_0x004d:
        r0 = new com.facebook.places.internal.ScannerException;	 Catch:{ all -> 0x0058 }
        r1 = com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR;	 Catch:{ all -> 0x0058 }
        r0.<init>(r1);	 Catch:{ all -> 0x0058 }
        throw r0;	 Catch:{ all -> 0x0058 }
    L_0x0055:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0055 }
        throw r0;	 Catch:{ all -> 0x0058 }
    L_0x0058:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.BleScannerImpl.startScanning():void");
    }

    public synchronized void stopScanning() {
        this.bluetoothLeScanner.flushPendingScanResults(this.scanCallBack);
        this.bluetoothLeScanner.stopScan(this.scanCallBack);
        waitForMainLooper(this.params.getBluetoothFlushResultsTimeoutMs());
        this.isScanInProgress = false;
    }

    private void waitForMainLooper(long j) {
        try {
            final Object obj = new Object();
            synchronized (obj) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            synchronized (obj) {
                                obj.notify();
                            }
                        } catch (Exception e) {
                            BleScannerImpl.logException("Exception waiting for main looper", e);
                        }
                    }
                });
                obj.wait(j);
            }
        } catch (Exception e) {
            logException("Exception waiting for main looper", e);
        }
    }

    public synchronized int getErrorCode() {
        return this.errorCode;
    }

    public synchronized List<BluetoothScanResult> getScanResults() {
        List<BluetoothScanResult> arrayList;
        synchronized (this.scanResults) {
            int bluetoothMaxScanResults = this.params.getBluetoothMaxScanResults();
            if (this.scanResults.size() > bluetoothMaxScanResults) {
                arrayList = new ArrayList(bluetoothMaxScanResults);
                Collections.sort(this.scanResults, new Comparator<BluetoothScanResult>() {
                    public int compare(BluetoothScanResult bluetoothScanResult, BluetoothScanResult bluetoothScanResult2) {
                        return bluetoothScanResult2.rssi - bluetoothScanResult.rssi;
                    }
                });
                arrayList.addAll(this.scanResults.subList(0, bluetoothMaxScanResults));
            } else {
                arrayList = new ArrayList(this.scanResults.size());
                arrayList.addAll(this.scanResults);
            }
        }
        return arrayList;
    }

    private static BluetoothScanResult newBluetoothScanResult(ScanResult scanResult) {
        return new BluetoothScanResult(formatPayload(scanResult.getScanRecord().getBytes()), scanResult.getRssi());
    }

    private static String formatPayload(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? null : toHexString(bArr, getPayloadLength(bArr));
    }

    private static int getPayloadLength(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == (byte) 0) {
                return i;
            }
            if (b < (byte) 0) {
                return bArr.length;
            }
            i += 1 + b;
        }
        return bArr.length;
    }

    private static String toHexString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i < 0 || i > bArr.length) {
            i = bArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i2])}));
        }
        return stringBuffer.toString();
    }

    private static void logException(String str, Exception exception) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, exception);
        }
    }
}

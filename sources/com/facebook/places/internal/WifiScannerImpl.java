package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WifiScannerImpl implements WifiScanner {
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    private final Object scanLock = new Object();
    private WifiManager wifiManager;

    private class ScanResultBroadcastReceiver extends BroadcastReceiver {
        private ScanResultBroadcastReceiver() {
        }

        /* synthetic */ ScanResultBroadcastReceiver(WifiScannerImpl wifiScannerImpl, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                synchronized (WifiScannerImpl.this.scanLock) {
                    WifiScannerImpl.this.scanLock.notify();
                }
                WifiScannerImpl.this.unregisterBroadcastReceiver();
            }
        }
    }

    WifiScannerImpl(Context context, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context;
        this.params = locationPackageRequestParams;
    }

    public void initAndCheckEligibility() throws ScannerException {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(Type.NOT_SUPPORTED);
        } else if (Validate.hasWiFiPermission(this.context)) {
            if (this.wifiManager == null) {
                this.wifiManager = (WifiManager) this.context.getSystemService(e.ad);
            }
            if (!isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
                throw new ScannerException(Type.DISABLED);
            }
        } else {
            throw new ScannerException(Type.PERMISSION_DENIED);
        }
    }

    public WifiScanResult getConnectedWifi() throws ScannerException {
        try {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (!(connectionInfo == null || TextUtils.isEmpty(connectionInfo.getBSSID()))) {
                if (connectionInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                    WifiScanResult wifiScanResult = new WifiScanResult();
                    wifiScanResult.bssid = connectionInfo.getBSSID();
                    wifiScanResult.ssid = connectionInfo.getSSID();
                    wifiScanResult.rssi = connectionInfo.getRssi();
                    if (VERSION.SDK_INT >= 21) {
                        wifiScanResult.frequency = connectionInfo.getFrequency();
                    }
                    return wifiScanResult;
                }
            }
            return null;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    public boolean isWifiScanningEnabled() {
        try {
            initAndCheckEligibility();
            if (Validate.hasLocationPermission(this.context)) {
                return true;
            }
        } catch (ScannerException unused) {
        }
        return false;
    }

    private boolean isWifiScanningAlwaysOn() {
        return VERSION.SDK_INT >= 18 ? this.wifiManager.isScanAlwaysAvailable() : false;
    }

    private List<WifiScanResult> getCachedScanResults() throws ScannerException {
        try {
            List<ScanResult> filterWifiScanResultsByMaxAge = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
            filterResults(filterWifiScanResultsByMaxAge, this.params.getWifiMaxScanResults());
            ArrayList arrayList = new ArrayList(filterWifiScanResultsByMaxAge.size());
            for (ScanResult scanResult : filterWifiScanResultsByMaxAge) {
                WifiScanResult wifiScanResult = new WifiScanResult();
                wifiScanResult.bssid = scanResult.BSSID;
                wifiScanResult.ssid = scanResult.SSID;
                wifiScanResult.rssi = scanResult.level;
                wifiScanResult.frequency = scanResult.frequency;
                arrayList.add(wifiScanResult);
            }
            return arrayList;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    private static void filterResults(List<ScanResult> list, int i) {
        if (list.size() > i) {
            Collections.sort(list, new Comparator<ScanResult>() {
                public int compare(ScanResult scanResult, ScanResult scanResult2) {
                    return scanResult2.level - scanResult.level;
                }
            });
            list.subList(i, list.size()).clear();
        }
    }

    private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> list, long j) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            if (VERSION.SDK_INT < 17) {
                arrayList.addAll(list);
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                for (ScanResult scanResult : list) {
                    long j2 = elapsedRealtime - (scanResult.timestamp / 1000);
                    if (j2 < 0) {
                        j2 = System.currentTimeMillis() - scanResult.timestamp;
                    }
                    if (j2 < j) {
                        arrayList.add(scanResult);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized List<WifiScanResult> getWifiScans() throws ScannerException {
        List<WifiScanResult> list;
        Object obj;
        list = null;
        if (!this.params.isWifiActiveScanForced()) {
            list = getCachedScanResults();
        }
        if (list != null) {
            if (!list.isEmpty()) {
                obj = null;
                if (this.params.isWifiActiveScanForced() || (this.params.isWifiActiveScanAllowed() && obj != null)) {
                    list = getActiveScanResults();
                }
            }
        }
        obj = 1;
        list = getActiveScanResults();
        return list;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0027 */
    private java.util.List<com.facebook.places.internal.WifiScanResult> getActiveScanResults() throws com.facebook.places.internal.ScannerException {
        /*
        r5 = this;
        r0 = 0;
        r1 = r5.context;	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r1 = com.facebook.internal.Validate.hasChangeWifiStatePermission(r1);	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        if (r1 == 0) goto L_0x0032;
    L_0x0009:
        r5.registerBroadcastReceiver();	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r1 = r5.wifiManager;	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r1 = r1.startScan();	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        if (r1 == 0) goto L_0x0032;
    L_0x0014:
        r1 = r5.scanLock;	 Catch:{ InterruptedException -> 0x0027 }
        monitor-enter(r1);	 Catch:{ InterruptedException -> 0x0027 }
        r2 = r5.scanLock;	 Catch:{ all -> 0x0024 }
        r3 = r5.params;	 Catch:{ all -> 0x0024 }
        r3 = r3.getWifiScanTimeoutMs();	 Catch:{ all -> 0x0024 }
        r2.wait(r3);	 Catch:{ all -> 0x0024 }
        monitor-exit(r1);	 Catch:{ all -> 0x0024 }
        goto L_0x0027;
    L_0x0024:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0024 }
        throw r2;	 Catch:{ InterruptedException -> 0x0027 }
    L_0x0027:
        r1 = r5.getCachedScanResults();	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r0 = r1;
        goto L_0x0032;
    L_0x002d:
        r0 = move-exception;
        r5.unregisterBroadcastReceiver();
        throw r0;
    L_0x0032:
        r5.unregisterBroadcastReceiver();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.WifiScannerImpl.getActiveScanResults():java.util.List");
    }

    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver(this, null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private void unregisterBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            try {
                this.context.unregisterReceiver(this.broadcastReceiver);
            } catch (Exception unused) {
            }
            this.broadcastReceiver = null;
        }
    }
}

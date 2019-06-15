package com.facebook.places.internal;

public class BluetoothScanResult {
    public String payload;
    public int rssi;

    public BluetoothScanResult(String str, int i) {
        this.payload = str;
        this.rssi = i;
    }
}

package com.gaana.qr_scanner;

interface RPResultListener {
    void onPermissionDenied();

    void onPermissionGranted();
}

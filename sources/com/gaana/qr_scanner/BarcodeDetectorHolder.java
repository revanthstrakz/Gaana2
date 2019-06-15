package com.gaana.qr_scanner;

import android.content.Context;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;

class BarcodeDetectorHolder {
    private static BarcodeDetector detector;

    BarcodeDetectorHolder() {
    }

    static BarcodeDetector getBarcodeDetector(Context context) {
        if (detector == null) {
            detector = new Builder(context.getApplicationContext()).setBarcodeFormats(256).build();
        }
        return detector;
    }
}

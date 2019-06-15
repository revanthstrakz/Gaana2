package io.branch.referral;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.payu.custombrowser.util.CBConstant;

class b {
    public static int a = 1048833;
    public static int b = 1048834;
    public static int c = 1048835;

    b() {
    }

    public String a(byte[] bArr) {
        int i;
        int b = (b(bArr, 16) * 4) + 36;
        int b2 = b(bArr, 12);
        for (i = b2; i < bArr.length - 4; i += 4) {
            if (b(bArr, i) == b) {
                b2 = i;
                break;
            }
        }
        while (b2 < bArr.length) {
            i = b(bArr, b2);
            if (i != b) {
                if (i != c) {
                    int i2 = a;
                    break;
                }
                b2 += 24;
            } else {
                i = b(bArr, b2 + 28);
                b2 += 36;
                for (int i3 = 0; i3 < i; i3++) {
                    int b3 = b(bArr, b2 + 4);
                    int b4 = b(bArr, b2 + 8);
                    int b5 = b(bArr, b2 + 16);
                    b2 += 20;
                    if (a(bArr, 36, b, b3).equals("scheme")) {
                        String a;
                        if (b4 != -1) {
                            a = a(bArr, 36, b, b4);
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("resourceID 0x");
                            stringBuilder.append(Integer.toHexString(b5));
                            a = stringBuilder.toString();
                        }
                        if (a(a)) {
                            return a;
                        }
                    }
                }
                continue;
            }
        }
        return "bnc_no_value";
    }

    private boolean a(String str) {
        return (str == null || str.equals("http") || str.equals("https") || str.equals("geo") || str.equals(CBConstant.DEFAULT_PAYMENT_URLS) || str.equals("package") || str.equals("sms") || str.equals("smsto") || str.equals("mms") || str.equals("mmsto") || str.equals("tel") || str.equals("voicemail") || str.equals("file") || str.equals("content") || str.equals("mailto")) ? false : true;
    }

    public String a(byte[] bArr, int i, int i2, int i3) {
        if (i3 < 0) {
            return null;
        }
        return a(bArr, i2 + b(bArr, i + (i3 * 4)));
    }

    public String a(byte[] bArr, int i) {
        int i2 = ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[i] & 255);
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[(i + 2) + (i3 * 2)];
        }
        return new String(bArr2);
    }

    public int b(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((((bArr[i + 3] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i + 2] << 16) & 16711680)) | ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
    }
}

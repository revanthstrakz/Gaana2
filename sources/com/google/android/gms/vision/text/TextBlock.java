package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzae[] zzev;
    private List<Line> zzew;
    private String zzex;
    private Rect zzey;

    TextBlock(SparseArray<zzae> sparseArray) {
        this.zzev = new zzae[sparseArray.size()];
        for (int i = 0; i < this.zzev.length; i++) {
            this.zzev[i] = (zzae) sparseArray.valueAt(i);
        }
    }

    public String getLanguage() {
        if (this.zzex != null) {
            return this.zzex;
        }
        HashMap hashMap = new HashMap();
        for (zzae zzae : this.zzev) {
            hashMap.put(zzae.zzex, Integer.valueOf((hashMap.containsKey(zzae.zzex) ? ((Integer) hashMap.get(zzae.zzex)).intValue() : 0) + 1));
        }
        this.zzex = (String) ((Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        if (this.zzex == null || this.zzex.isEmpty()) {
            this.zzex = C.LANGUAGE_UNDETERMINED;
        }
        return this.zzex;
    }

    public String getValue() {
        if (this.zzev.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(this.zzev[0].zzfg);
        for (int i = 1; i < this.zzev.length; i++) {
            stringBuilder.append("\n");
            stringBuilder.append(this.zzev[i].zzfg);
        }
        return stringBuilder.toString();
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            int i = 0;
            if (this.zzev.length == 0) {
                this.cornerPoints = new Point[0];
            } else {
                double sin;
                int i2;
                int i3;
                int i4 = Integer.MAX_VALUE;
                int i5 = Integer.MIN_VALUE;
                int i6 = i5;
                int i7 = 0;
                int i8 = Integer.MAX_VALUE;
                while (i7 < this.zzev.length) {
                    zzy zzy = this.zzev[i7].zzfd;
                    zzy zzy2 = this.zzev[i].zzfd;
                    int i9 = -zzy2.left;
                    int i10 = -zzy2.top;
                    sin = Math.sin(Math.toRadians((double) zzy2.zzfb));
                    double cos = Math.cos(Math.toRadians((double) zzy2.zzfb));
                    Point[] pointArr = new Point[4];
                    i2 = i6;
                    pointArr[0] = new Point(zzy.left, zzy.top);
                    pointArr[0].offset(i9, i10);
                    i3 = i4;
                    i = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
                    i6 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i;
                    pointArr[0].y = i6;
                    pointArr[1] = new Point(zzy.width + i, i6);
                    pointArr[2] = new Point(zzy.width + i, zzy.height + i6);
                    pointArr[3] = new Point(i, i6 + zzy.height);
                    i6 = i2;
                    i4 = i3;
                    for (i = 0; i < 4; i++) {
                        Point point = pointArr[i];
                        i4 = Math.min(i4, point.x);
                        i5 = Math.max(i5, point.x);
                        i8 = Math.min(i8, point.y);
                        i6 = Math.max(i6, point.y);
                    }
                    i7++;
                    i = 0;
                }
                i3 = i4;
                i2 = i6;
                zzy zzy3 = this.zzev[0].zzfd;
                i = zzy3.left;
                i4 = zzy3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzy3.zzfb));
                sin = Math.cos(Math.toRadians((double) zzy3.zzfb));
                r10 = new Point[4];
                int i11 = i3;
                int i12 = 0;
                r10[0] = new Point(i11, i8);
                r10[1] = new Point(i5, i8);
                i8 = i2;
                r10[2] = new Point(i5, i8);
                r10[3] = new Point(i11, i8);
                while (i12 < 4) {
                    int i13 = i;
                    i7 = (int) ((((double) r10[i12].x) * sin2) + (((double) r10[i12].y) * sin));
                    r10[i12].x = (int) ((((double) r10[i12].x) * sin) - (((double) r10[i12].y) * sin2));
                    r10[i12].y = i7;
                    i = i13;
                    r10[i12].offset(i, i4);
                    i12++;
                }
                this.cornerPoints = r10;
            }
        }
        return this.cornerPoints;
    }

    public List<? extends Text> getComponents() {
        int i = 0;
        if (this.zzev.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzew == null) {
            this.zzew = new ArrayList(this.zzev.length);
            zzae[] zzaeArr = this.zzev;
            int length = zzaeArr.length;
            while (i < length) {
                this.zzew.add(new Line(zzaeArr[i]));
                i++;
            }
        }
        return this.zzew;
    }

    public Rect getBoundingBox() {
        if (this.zzey == null) {
            this.zzey = zzc.zza((Text) this);
        }
        return this.zzey;
    }
}

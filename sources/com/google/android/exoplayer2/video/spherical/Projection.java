package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    public static Projection createEquirectangular(float f, int i, int i2, float f2, float f3, int i3) {
        float f4 = f;
        int i4 = i;
        int i5 = i2;
        float f5 = f2;
        float f6 = f3;
        Assertions.checkArgument(f4 > 0.0f);
        Assertions.checkArgument(i4 >= 1);
        Assertions.checkArgument(i5 >= 1);
        boolean z = f5 > 0.0f && f5 <= 180.0f;
        Assertions.checkArgument(z);
        boolean z2 = f6 > 0.0f && f6 <= 360.0f;
        Assertions.checkArgument(z2);
        f5 = (float) Math.toRadians((double) f5);
        f6 = (float) Math.toRadians((double) f6);
        float f7 = f5 / ((float) i4);
        float f8 = f6 / ((float) i5);
        int i6 = i5 + 1;
        int i7 = ((2 * i6) + 2) * i4;
        float[] fArr = new float[(i7 * 3)];
        float[] fArr2 = new float[(i7 * 2)];
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < i4) {
            float f9 = f5 / 2.0f;
            float f10 = (((float) i8) * f7) - f9;
            int i11 = i8 + 1;
            float f11 = (((float) i11) * f7) - f9;
            int i12 = i10;
            i10 = i9;
            i9 = 0;
            while (i9 < i6) {
                float f12;
                float f13;
                int i13;
                int i14;
                float f14;
                float f15;
                int i15;
                int i16;
                int i17 = i12;
                i4 = 2;
                i12 = i10;
                i10 = 0;
                while (i10 < i4) {
                    float f16;
                    if (i10 == 0) {
                        f16 = f10;
                        f12 = f16;
                    } else {
                        f12 = f10;
                        f16 = f11;
                    }
                    f10 = ((float) i9) * f8;
                    f13 = f8;
                    int i18 = i12 + 1;
                    i13 = i11;
                    i14 = i6;
                    double d = (double) f4;
                    f14 = f5;
                    double d2 = (double) ((f10 + 3.1415927f) - (f6 / 2.0f));
                    double d3 = (double) f16;
                    f15 = f7;
                    fArr[i12] = -((float) ((Math.sin(d2) * d) * Math.cos(d3)));
                    i15 = i18 + 1;
                    i16 = i8;
                    int i19 = i9;
                    fArr[i18] = (float) (d * Math.sin(d3));
                    int i20 = i15 + 1;
                    fArr[i15] = (float) ((d * Math.cos(d2)) * Math.cos(d3));
                    int i21 = i17 + 1;
                    fArr2[i17] = f10 / f6;
                    i4 = i21 + 1;
                    fArr2[i21] = (((float) (i16 + i10)) * f15) / f14;
                    if (i19 == 0 && i10 == 0) {
                        i5 = i19;
                        i21 = i2;
                    } else {
                        i5 = i19;
                        i21 = i2;
                        if (!(i5 == i21 && i10 == 1)) {
                            i15 = 2;
                            i17 = i4;
                            i12 = i20;
                            i10++;
                            i9 = i5;
                            i4 = i15;
                            f10 = f12;
                            f8 = f13;
                            i6 = i14;
                            i11 = i13;
                            f5 = f14;
                            f7 = f15;
                            i8 = i16;
                            i5 = i21;
                            f4 = f;
                        }
                    }
                    System.arraycopy(fArr, i20 - 3, fArr, i20, 3);
                    i20 += 3;
                    i15 = 2;
                    System.arraycopy(fArr2, i4 - 2, fArr2, i4, 2);
                    i4 += 2;
                    i17 = i4;
                    i12 = i20;
                    i10++;
                    i9 = i5;
                    i4 = i15;
                    f10 = f12;
                    f8 = f13;
                    i6 = i14;
                    i11 = i13;
                    f5 = f14;
                    f7 = f15;
                    i8 = i16;
                    i5 = i21;
                    f4 = f;
                }
                f14 = f5;
                f15 = f7;
                f13 = f8;
                f12 = f10;
                i13 = i11;
                i14 = i6;
                i16 = i8;
                i15 = i4;
                i9++;
                i5 = i5;
                i10 = i12;
                i12 = i17;
                f7 = f15;
                f4 = f;
                i4 = i;
            }
            i9 = i10;
            i10 = i12;
            i8 = i11;
            f4 = f;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr, fArr2, 1)), i3);
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }
}

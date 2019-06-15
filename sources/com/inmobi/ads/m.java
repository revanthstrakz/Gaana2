package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

final class m {

    static class a extends Animation {
        private final float a = 0.0f;
        private final float b = 90.0f;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public a(float f, float f2) {
            this.c = f;
            this.d = f2;
            this.e = 0.0f;
            this.f = true;
        }

        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        /* Access modifiers changed, original: protected|final */
        public final void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            f2 += (this.b - f2) * f;
            float f3 = this.c;
            float f4 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(0.0f, 0.0f, this.e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
            }
            camera.rotateX(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    static class b extends Animation {
        private final float a = 0.0f;
        private final float b = 90.0f;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public b(float f, float f2) {
            this.c = f;
            this.d = f2;
            this.e = 0.0f;
            this.f = true;
        }

        public final void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        /* Access modifiers changed, original: protected|final */
        public final void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            f2 += (this.b - f2) * f;
            float f3 = this.c;
            float f4 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(0.0f, 0.0f, this.e * f);
            } else {
                camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
            }
            camera.rotateY(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }
}

package com.gaana.view.transform;

import android.support.annotation.FloatRange;
import android.view.View;
import com.gaana.application.GaanaApplication;
import com.gaana.view.transform.Pivot.X;
import com.gaana.view.transform.Pivot.Y;
import com.managers.PlayerManager;

public class ScaleTransformerV2 implements DiscreteScrollItemTransformer {
    private float maxMinDiff = 0.25f;
    private float minScale = 0.75f;
    private Pivot pivotX = X.CENTER.create();
    private Pivot pivotY = Y.CENTER.create();

    public static class Builder {
        private float maxScale = 1.0f;
        private ScaleTransformerV2 transformer = new ScaleTransformerV2();

        public Builder setMinScale(@FloatRange(from = 0.01d) float f) {
            this.transformer.minScale = f;
            return this;
        }

        public Builder setMaxScale(@FloatRange(from = 0.01d) float f) {
            this.maxScale = f;
            return this;
        }

        public Builder setPivotX(X x) {
            return setPivotX(x.create());
        }

        public Builder setPivotX(Pivot pivot) {
            assertAxis(pivot, 0);
            this.transformer.pivotX = pivot;
            return this;
        }

        public Builder setPivotY(Y y) {
            return setPivotY(y.create());
        }

        public Builder setPivotY(Pivot pivot) {
            assertAxis(pivot, 1);
            this.transformer.pivotY = pivot;
            return this;
        }

        public ScaleTransformerV2 build() {
            this.transformer.maxMinDiff = this.maxScale - this.transformer.minScale;
            return this.transformer;
        }

        private void assertAxis(Pivot pivot, int i) {
            if (pivot.getAxis() != i) {
                throw new IllegalArgumentException("You passed a Pivot for wrong axis.");
            }
        }
    }

    public void transformItem(View view, float f) {
        this.pivotX.setOn(view);
        this.pivotY.setOn(view);
        f = this.minScale + (this.maxMinDiff * (1.0f - Math.abs(f)));
        if (view.getTag() == null || ((String) view.getTag()).equals(PlayerManager.a(GaanaApplication.getContext()).i().h())) {
            view.setScaleX(f);
            view.setScaleY(f);
            return;
        }
        view.setScaleX(this.minScale);
        view.setScaleY(this.minScale);
    }
}

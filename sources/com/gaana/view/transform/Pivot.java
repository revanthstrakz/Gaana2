package com.gaana.view.transform;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Pivot {
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    private static final int PIVOT_CENTER = -1;
    private static final int PIVOT_MAX = -2;
    private int axis;
    private int pivotPoint;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    public enum X {
        LEFT {
            public Pivot create() {
                return new Pivot(0, 0);
            }
        },
        CENTER {
            public Pivot create() {
                return new Pivot(0, -1);
            }
        },
        RIGHT {
            public Pivot create() {
                return new Pivot(0, -2);
            }
        };

        public abstract Pivot create();
    }

    public enum Y {
        TOP {
            public Pivot create() {
                return new Pivot(1, 0);
            }
        },
        CENTER {
            public Pivot create() {
                return new Pivot(1, -1);
            }
        },
        BOTTOM {
            public Pivot create() {
                return new Pivot(1, -2);
            }
        };

        public abstract Pivot create();
    }

    public Pivot(int i, int i2) {
        this.axis = i;
        this.pivotPoint = i2;
    }

    public void setOn(View view) {
        if (this.axis == 0) {
            switch (this.pivotPoint) {
                case -2:
                    view.setPivotX((float) view.getWidth());
                    break;
                case -1:
                    view.setPivotX(((float) view.getWidth()) * 0.5f);
                    break;
                default:
                    view.setPivotX((float) this.pivotPoint);
                    break;
            }
            return;
        }
        if (this.axis == 1) {
            switch (this.pivotPoint) {
                case -2:
                    view.setPivotY((float) view.getHeight());
                    break;
                case -1:
                    view.setPivotY(((float) view.getHeight()) * 0.5f);
                    break;
                default:
                    view.setPivotY((float) this.pivotPoint);
                    break;
            }
        }
    }

    public int getAxis() {
        return this.axis;
    }
}

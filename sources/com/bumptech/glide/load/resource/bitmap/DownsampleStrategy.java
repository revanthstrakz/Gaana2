package com.bumptech.glide.load.resource.bitmap;

public abstract class DownsampleStrategy {
    public static final DownsampleStrategy a = new e();
    public static final DownsampleStrategy b = new d();
    public static final DownsampleStrategy c = new a();
    public static final DownsampleStrategy d = new b();
    public static final DownsampleStrategy e = new c();
    public static final DownsampleStrategy f = new f();
    public static final DownsampleStrategy g = b;

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    private static class a extends DownsampleStrategy {
        a() {
        }

        public float a(int i, int i2, int i3, int i4) {
            i = Math.min(i2 / i4, i / i3);
            if (i == 0) {
                return 1.0f;
            }
            return 1.0f / ((float) Integer.highestOneBit(i));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class b extends DownsampleStrategy {
        b() {
        }

        public float a(int i, int i2, int i3, int i4) {
            i = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            i3 = 1;
            i2 = Math.max(1, Integer.highestOneBit(i));
            if (i2 >= i) {
                i3 = 0;
            }
            return 1.0f / ((float) (i2 << i3));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    private static class c extends DownsampleStrategy {
        c() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, a.a(i, i2, i3, i4));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class d extends DownsampleStrategy {
        d() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.max(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class e extends DownsampleStrategy {
        e() {
        }

        public float a(int i, int i2, int i3, int i4) {
            return Math.min(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class f extends DownsampleStrategy {
        public float a(int i, int i2, int i3, int i4) {
            return 1.0f;
        }

        f() {
        }

        public SampleSizeRounding b(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    public abstract float a(int i, int i2, int i3, int i4);

    public abstract SampleSizeRounding b(int i, int i2, int i3, int i4);
}

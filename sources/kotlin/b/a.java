package kotlin.b;

public final class a {
    private static final int a(int i, int i2) {
        i %= i2;
        return i >= 0 ? i : i + i2;
    }

    private static final int b(int i, int i2, int i3) {
        return a(a(i, i3) - a(i2, i3), i3);
    }

    public static final int a(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i >= i2) {
                return i2;
            }
            return i2 - b(i2, i, i3);
        } else if (i3 < 0) {
            return i <= i2 ? i2 : i2 + b(i, i2, -i3);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}

package kotlin.c;

import java.util.NoSuchElementException;
import kotlin.a.r;

public final class b extends r {
    private final int a;
    private boolean b;
    private int c;
    private final int d;

    public b(int i, int i2, int i3) {
        this.d = i3;
        this.a = i2;
        boolean z = false;
        if (this.d <= 0 ? i < i2 : i > i2) {
            z = true;
        }
        this.b = z;
        if (!this.b) {
            i = this.a;
        }
        this.c = i;
    }

    public boolean hasNext() {
        return this.b;
    }

    public int b() {
        int i = this.c;
        if (i != this.a) {
            this.c += this.d;
        } else if (this.b) {
            this.b = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }
}

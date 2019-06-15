package kotlin.jvm.internal;

import java.io.Serializable;

public abstract class Lambda<R> implements Serializable, b<R> {
    private final int a;

    public Lambda(int i) {
        this.a = i;
    }

    public String toString() {
        Object a = d.a(this);
        c.a(a, "Reflection.renderLambdaToString(this)");
        return a;
    }
}

package com.helpshift.common.domain;

import java.util.concurrent.atomic.AtomicBoolean;

public class h extends f {
    private final f a;
    private final AtomicBoolean b = new AtomicBoolean(false);

    public h(f fVar) {
        this.a = fVar;
    }

    public void a() {
        if (this.b.compareAndSet(false, true)) {
            try {
                this.a.a();
            } finally {
                this.b.set(false);
            }
        }
    }
}

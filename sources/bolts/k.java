package bolts;

import bolts.i.b;

class k {
    private i<?> a;

    public k(i<?> iVar) {
        this.a = iVar;
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        try {
            i iVar = this.a;
            if (iVar != null) {
                b a = i.a();
                if (a != null) {
                    a.a(iVar, new UnobservedTaskException(iVar.g()));
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public void a() {
        this.a = null;
    }
}

package com.google.ads.interactivemedia.v3.internal;

class lj<E> extends la<E> {
    static final la<Object> a = new lj(new Object[0], 0);
    final transient Object[] b;
    private final transient int c;

    lj(Object[] objArr, int i) {
        this.b = objArr;
        this.c = i;
    }

    /* Access modifiers changed, original: 0000 */
    public int c() {
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean f() {
        return false;
    }

    public int size() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public Object[] b() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public int d() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public int a(Object[] objArr, int i) {
        System.arraycopy(this.b, 0, objArr, i, this.c);
        return i + this.c;
    }

    public E get(int i) {
        kr.a(i, this.c);
        return this.b[i];
    }
}

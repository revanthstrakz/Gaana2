package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    private static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    private int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    private final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private android.arch.a.b.b<l<T>, b> mObservers = new android.arch.a.b.b();
    private volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable = new Runnable() {
        public void run() {
            Object access$100;
            synchronized (LiveData.this.mDataLock) {
                access$100 = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(access$100);
        }
    };
    private int mVersion = -1;

    private abstract class b {
        final l<T> c;
        boolean d;
        int e = -1;

        public abstract boolean a();

        /* Access modifiers changed, original: 0000 */
        public boolean a(e eVar) {
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
        }

        b(l<T> lVar) {
            this.c = lVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(boolean z) {
            if (z != this.d) {
                this.d = z;
                int i = 1;
                int i2 = LiveData.this.mActiveCount == 0 ? 1 : 0;
                LiveData liveData = LiveData.this;
                int access$300 = liveData.mActiveCount;
                if (!this.d) {
                    i = -1;
                }
                liveData.mActiveCount = access$300 + i;
                if (i2 != 0 && this.d) {
                    LiveData.this.onActive();
                }
                if (LiveData.this.mActiveCount == 0 && !this.d) {
                    LiveData.this.onInactive();
                }
                if (this.d) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }
    }

    private class a extends b {
        /* Access modifiers changed, original: 0000 */
        public boolean a() {
            return true;
        }

        a(l<T> lVar) {
            super(lVar);
        }
    }

    class LifecycleBoundObserver extends b implements GenericLifecycleObserver {
        @NonNull
        final e a;

        LifecycleBoundObserver(e eVar, @NonNull l<T> lVar) {
            super(lVar);
            this.a = eVar;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a() {
            return this.a.getLifecycle().a().isAtLeast(State.STARTED);
        }

        public void a(e eVar, Event event) {
            if (this.a.getLifecycle().a() == State.DESTROYED) {
                LiveData.this.removeObserver(this.c);
            } else {
                a(a());
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(e eVar) {
            return this.a == eVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            this.a.getLifecycle().b(this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActive() {
    }

    /* Access modifiers changed, original: protected */
    public void onInactive() {
    }

    private void considerNotify(b bVar) {
        if (!bVar.d) {
            return;
        }
        if (!bVar.a()) {
            bVar.a(false);
        } else if (bVar.e < this.mVersion) {
            bVar.e = this.mVersion;
            bVar.c.onChanged(this.mData);
        }
    }

    private void dispatchingValue(@Nullable b bVar) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            b bVar2;
            if (bVar2 == null) {
                d c = this.mObservers.c();
                while (c.hasNext()) {
                    considerNotify((b) ((Entry) c.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
            considerNotify(bVar2);
            bVar2 = null;
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @MainThread
    public void observe(@NonNull e eVar, @NonNull l<T> lVar) {
        if (eVar.getLifecycle().a() != State.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(eVar, lVar);
            b bVar = (b) this.mObservers.a(lVar, lifecycleBoundObserver);
            if (bVar != null && !bVar.a(eVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (bVar == null) {
                eVar.getLifecycle().a(lifecycleBoundObserver);
            }
        }
    }

    @MainThread
    public void observeForever(@NonNull l<T> lVar) {
        a aVar = new a(lVar);
        b bVar = (b) this.mObservers.a(lVar, aVar);
        if (bVar != null && (bVar instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        } else if (bVar == null) {
            aVar.a(true);
        }
    }

    @MainThread
    public void removeObserver(@NonNull l<T> lVar) {
        assertMainThread("removeObserver");
        b bVar = (b) this.mObservers.b(lVar);
        if (bVar != null) {
            bVar.b();
            bVar.a(false);
        }
    }

    @MainThread
    public void removeObservers(@NonNull e eVar) {
        assertMainThread("removeObservers");
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (((b) entry.getValue()).a(eVar)) {
                removeObserver((l) entry.getKey());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void postValue(T t) {
        Object obj;
        synchronized (this.mDataLock) {
            obj = this.mPendingData == NOT_SET ? 1 : null;
            this.mPendingData = t;
        }
        if (obj != null) {
            android.arch.a.a.a.a().b(this.mPostValueRunnable);
        }
    }

    /* Access modifiers changed, original: protected */
    @MainThread
    public void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }

    @Nullable
    public T getValue() {
        Object obj = this.mData;
        return obj != NOT_SET ? obj : null;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersion() {
        return this.mVersion;
    }

    public boolean hasObservers() {
        return this.mObservers.a() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    private static void assertMainThread(String str) {
        if (!android.arch.a.a.a.a().b()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot invoke ");
            stringBuilder.append(str);
            stringBuilder.append(" on a background");
            stringBuilder.append(" thread");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }
}

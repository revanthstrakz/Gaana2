package com.bumptech.glide.load.engine.b;

import com.bumptech.glide.f.h;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class c {
    private final Map<String, a> a = new HashMap();
    private final b b = new b();

    private static class a {
        final Lock a = new ReentrantLock();
        int b;

        a() {
        }
    }

    private static class b {
        private final Queue<a> a = new ArrayDeque();

        b() {
        }

        /* Access modifiers changed, original: 0000 */
        public a a() {
            a aVar;
            synchronized (this.a) {
                aVar = (a) this.a.poll();
            }
            return aVar == null ? new a() : aVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(a aVar) {
            synchronized (this.a) {
                if (this.a.size() < 10) {
                    this.a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(str);
            if (aVar == null) {
                aVar = this.b.a();
                this.a.put(str, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) h.a(this.a.get(str));
            if (aVar.b < 1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cannot release a lock that is not held, safeKey: ");
                stringBuilder.append(str);
                stringBuilder.append(", interestedThreads: ");
                stringBuilder.append(aVar.b);
                throw new IllegalStateException(stringBuilder.toString());
            }
            aVar.b--;
            if (aVar.b == 0) {
                a aVar2 = (a) this.a.remove(str);
                if (aVar2.equals(aVar)) {
                    this.b.a(aVar2);
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Removed the wrong lock, expected to remove: ");
                    stringBuilder2.append(aVar);
                    stringBuilder2.append(", but actually removed: ");
                    stringBuilder2.append(aVar2);
                    stringBuilder2.append(", safeKey: ");
                    stringBuilder2.append(str);
                    throw new IllegalStateException(stringBuilder2.toString());
                }
            }
        }
        aVar.a.unlock();
    }
}

package io.branch.referral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.branch.referral.Branch.e;
import io.branch.referral.Defines.RequestPath;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONException;

class y {
    private static y a;
    private SharedPreferences b;
    private Editor c = this.b.edit();
    private final List<ServerRequest> d;

    public static y a(Context context) {
        if (a == null) {
            synchronized (y.class) {
                if (a == null) {
                    a = new y(context);
                }
            }
        }
        return a;
    }

    @SuppressLint({"CommitPrefEdits"})
    private y(Context context) {
        this.b = context.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.d = b(context);
    }

    private void g() {
        new Thread(new Runnable() {
            /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0085 */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:11|19|20|26|21|22) */
            public void run() {
                /*
                r6 = this;
                r0 = io.branch.referral.y.this;
                r0 = r0.d;
                monitor-enter(r0);
                r1 = new org.json.JSONArray;	 Catch:{ all -> 0x0086 }
                r1.<init>();	 Catch:{ all -> 0x0086 }
                r2 = io.branch.referral.y.this;	 Catch:{ all -> 0x0086 }
                r2 = r2.d;	 Catch:{ all -> 0x0086 }
                r2 = r2.iterator();	 Catch:{ all -> 0x0086 }
            L_0x0016:
                r3 = r2.hasNext();	 Catch:{ all -> 0x0086 }
                if (r3 == 0) goto L_0x002c;
            L_0x001c:
                r3 = r2.next();	 Catch:{ all -> 0x0086 }
                r3 = (io.branch.referral.ServerRequest) r3;	 Catch:{ all -> 0x0086 }
                r3 = r3.i();	 Catch:{ all -> 0x0086 }
                if (r3 == 0) goto L_0x0016;
            L_0x0028:
                r1.put(r3);	 Catch:{ all -> 0x0086 }
                goto L_0x0016;
            L_0x002c:
                r2 = io.branch.referral.y.this;	 Catch:{ ConcurrentModificationException -> 0x0042 }
                r2 = r2.c;	 Catch:{ ConcurrentModificationException -> 0x0042 }
                r3 = "BNCServerRequestQueue";
                r4 = r1.toString();	 Catch:{ ConcurrentModificationException -> 0x0042 }
                r2 = r2.putString(r3, r4);	 Catch:{ ConcurrentModificationException -> 0x0042 }
                r2.commit();	 Catch:{ ConcurrentModificationException -> 0x0042 }
                goto L_0x0070;
            L_0x0040:
                r2 = move-exception;
                goto L_0x0072;
            L_0x0042:
                r2 = move-exception;
                r3 = "Persisting Queue: ";
                r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0040 }
                r4.<init>();	 Catch:{ all -> 0x0040 }
                r5 = "Failed to persit queue ";
                r4.append(r5);	 Catch:{ all -> 0x0040 }
                r2 = r2.getMessage();	 Catch:{ all -> 0x0040 }
                r4.append(r2);	 Catch:{ all -> 0x0040 }
                r2 = r4.toString();	 Catch:{ all -> 0x0040 }
                io.branch.referral.m.c(r3, r2);	 Catch:{ all -> 0x0040 }
                r2 = io.branch.referral.y.this;	 Catch:{ ConcurrentModificationException -> 0x0070 }
                r2 = r2.c;	 Catch:{ ConcurrentModificationException -> 0x0070 }
                r3 = "BNCServerRequestQueue";
                r1 = r1.toString();	 Catch:{ ConcurrentModificationException -> 0x0070 }
                r1 = r2.putString(r3, r1);	 Catch:{ ConcurrentModificationException -> 0x0070 }
                r1.commit();	 Catch:{ ConcurrentModificationException -> 0x0070 }
            L_0x0070:
                monitor-exit(r0);	 Catch:{ all -> 0x0086 }
                return;
            L_0x0072:
                r3 = io.branch.referral.y.this;	 Catch:{ ConcurrentModificationException -> 0x0085 }
                r3 = r3.c;	 Catch:{ ConcurrentModificationException -> 0x0085 }
                r4 = "BNCServerRequestQueue";
                r1 = r1.toString();	 Catch:{ ConcurrentModificationException -> 0x0085 }
                r1 = r3.putString(r4, r1);	 Catch:{ ConcurrentModificationException -> 0x0085 }
                r1.commit();	 Catch:{ ConcurrentModificationException -> 0x0085 }
            L_0x0085:
                throw r2;	 Catch:{ all -> 0x0086 }
            L_0x0086:
                r1 = move-exception;
                monitor-exit(r0);	 Catch:{ all -> 0x0086 }
                throw r1;
                */
                throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.y$AnonymousClass1.run():void");
            }
        }).start();
    }

    private List<ServerRequest> b(Context context) {
        List synchronizedList = Collections.synchronizedList(new LinkedList());
        String string = this.b.getString("BNCServerRequestQueue", null);
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < Math.min(jSONArray.length(), 25); i++) {
                    ServerRequest a = ServerRequest.a(jSONArray.getJSONObject(i), context);
                    if (!(a == null || (a instanceof aa) || (a instanceof x))) {
                        synchronizedList.add(a);
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return synchronizedList;
    }

    public int a() {
        return this.d.size();
    }

    public void a(ServerRequest serverRequest) {
        if (serverRequest != null) {
            this.d.add(serverRequest);
            if (a() >= 25) {
                this.d.remove(1);
            }
            g();
        }
    }

    public ServerRequest b() {
        try {
            ServerRequest serverRequest = (ServerRequest) this.d.remove(0);
            try {
                g();
                return serverRequest;
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                return serverRequest;
            }
        } catch (IndexOutOfBoundsException | NoSuchElementException unused2) {
            return null;
        }
    }

    public ServerRequest c() {
        try {
            return (ServerRequest) this.d.get(0);
        } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
            return null;
        }
    }

    public ServerRequest a(int i) {
        try {
            return (ServerRequest) this.d.get(i);
        } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
            return null;
        }
    }

    public void a(ServerRequest serverRequest, int i) {
        try {
            if (this.d.size() < i) {
                i = this.d.size();
            }
            this.d.add(i, serverRequest);
            g();
        } catch (IndexOutOfBoundsException unused) {
        }
    }

    public boolean b(ServerRequest serverRequest) {
        try {
            boolean remove = this.d.remove(serverRequest);
            try {
                g();
                return remove;
            } catch (UnsupportedOperationException unused) {
                return remove;
            }
        } catch (UnsupportedOperationException unused2) {
            return false;
        }
    }

    public void d() {
        try {
            this.d.clear();
            g();
        } catch (UnsupportedOperationException unused) {
        }
    }

    public boolean e() {
        synchronized (this.d) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null && serverRequest.d().equals(RequestPath.RegisterClose.getPath())) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean f() {
        synchronized (this.d) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null && ((serverRequest instanceof ab) || (serverRequest instanceof ac))) {
                    return true;
                }
            }
            return false;
        }
    }

    public void a(ServerRequest serverRequest, int i, e eVar) {
        synchronized (this.d) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ServerRequest serverRequest2 = (ServerRequest) it.next();
                if (serverRequest2 != null && ((serverRequest2 instanceof ab) || (serverRequest2 instanceof ac))) {
                    it.remove();
                    break;
                }
            }
        }
        if (i == 0) {
            a(serverRequest, 0);
        } else {
            a(serverRequest, 1);
        }
    }

    public void a(e eVar) {
        synchronized (this.d) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null) {
                    if (serverRequest instanceof ab) {
                        ((ab) serverRequest).a(eVar);
                    } else if (serverRequest instanceof ac) {
                        ((ac) serverRequest).a(eVar);
                    }
                }
            }
        }
    }

    public void a(PROCESS_WAIT_LOCK process_wait_lock) {
        synchronized (this.d) {
            for (ServerRequest serverRequest : this.d) {
                if (serverRequest != null) {
                    serverRequest.b(process_wait_lock);
                }
            }
        }
    }
}

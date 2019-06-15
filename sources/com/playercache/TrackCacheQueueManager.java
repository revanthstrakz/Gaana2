package com.playercache;

import android.content.Context;
import com.constants.c.c;
import com.gaana.application.GaanaApplication;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.StreamUrls;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.library.managers.TaskManager.TaskListner;
import com.managers.PlayerManager;
import com.models.PlayerTrack;
import com.services.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TrackCacheQueueManager {
    private static TrackCacheQueueManager a;
    private Context b;
    private LinkedList<PlayerTrack> c;
    private HashMap<String, PlayerTrack> d;

    public enum CACHING_BEHAVIOUR {
        PARTIAL_CACHE,
        FULL_CACHE,
        OTHER
    }

    public enum INSERTION_ORDER {
        FIRST,
        LAST,
        OTHER
    }

    private TrackCacheQueueManager() {
        this.b = GaanaApplication.getInstance().getApplicationContext();
        this.c = new LinkedList();
        this.d = new HashMap();
        this.d = d.a().b();
    }

    public static TrackCacheQueueManager a() {
        if (a == null) {
            a = new TrackCacheQueueManager();
        }
        return a;
    }

    public void a(final ArrayList<?> arrayList, final int i, final int i2) {
        if (e.a().c()) {
            h.a().a(new TaskListner() {
                public void doBackGroundTask() {
                    if (arrayList != null && arrayList.size() > 0) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Item item = (Item) arrayList.get(i);
                            if (item.getEntityType().equals(c.c)) {
                                Track track = new Track();
                                track.setBusinessObjId(item.getBusinessObjId());
                                track.setCachingBehaviour(i);
                                ArrayList arrayList = (ArrayList) item.getEntityInfo();
                                if (arrayList != null) {
                                    int size = arrayList.size();
                                    int i2 = 0;
                                    while (i2 < size) {
                                        if (((EntityInfo) arrayList.get(i2)).getKey().equals("stream_url")) {
                                            try {
                                                track.setStreamUrls((StreamUrls) new GsonBuilder().create().fromJson((String) ((EntityInfo) arrayList.get(i2)).getValue(), StreamUrls.class));
                                                break;
                                            } catch (Exception e) {
                                                ThrowableExtension.printStackTrace(e);
                                            }
                                        } else {
                                            i2++;
                                        }
                                    }
                                    TrackCacheQueueManager.this.a(track, i2);
                                }
                            }
                        }
                    }
                }

                public void onBackGroundTaskCompleted() {
                    e.a().b();
                }
            }, -1);
        }
    }

    public void a(final int i, final int i2, final int i3) {
        if (e.a().c()) {
            h.a().a(new TaskListner() {
                public void doBackGroundTask() {
                    int s = PlayerManager.a(TrackCacheQueueManager.this.b).s();
                    ArrayList n = PlayerManager.a(TrackCacheQueueManager.this.b).n();
                    int size = n.size();
                    int i = s + 1;
                    while (i < size && i < i + s) {
                        Track b = ((PlayerTrack) n.get(i)).b();
                        if (b != null) {
                            b.setCachingBehaviour(i2);
                            TrackCacheQueueManager.this.a(b, i3);
                        }
                        i++;
                    }
                }

                public void onBackGroundTaskCompleted() {
                    e.a().b();
                }
            }, -1);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARNING: Missing block: B:26:0x007d, code skipped:
            return;
     */
    private synchronized void a(com.gaana.models.Tracks.Track r5, int r6) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r5.getBusinessObjId();	 Catch:{ all -> 0x007e }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r4);
        return;
    L_0x000d:
        r0 = com.managers.DownloadManager.c();	 Catch:{ all -> 0x007e }
        r1 = r5.getBusinessObjId();	 Catch:{ all -> 0x007e }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ all -> 0x007e }
        r0 = r0.l(r1);	 Catch:{ all -> 0x007e }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x0025;
    L_0x0023:
        monitor-exit(r4);
        return;
    L_0x0025:
        r0 = r4.d;	 Catch:{ all -> 0x007e }
        r1 = r5.getBusinessObjId();	 Catch:{ all -> 0x007e }
        r0 = r0.containsKey(r1);	 Catch:{ all -> 0x007e }
        r1 = 1;
        if (r0 != 0) goto L_0x0034;
    L_0x0032:
        r0 = r1;
        goto L_0x0050;
    L_0x0034:
        r0 = r4.d;	 Catch:{ all -> 0x007e }
        r2 = r5.getBusinessObjId();	 Catch:{ all -> 0x007e }
        r0 = r0.get(r2);	 Catch:{ all -> 0x007e }
        r0 = (com.models.PlayerTrack) r0;	 Catch:{ all -> 0x007e }
        r0 = r0.b();	 Catch:{ all -> 0x007e }
        r0 = r0.getCachingBehaviour();	 Catch:{ all -> 0x007e }
        r2 = r5.getCachingBehaviour();	 Catch:{ all -> 0x007e }
        if (r0 == r2) goto L_0x004f;
    L_0x004e:
        goto L_0x0032;
    L_0x004f:
        r0 = 0;
    L_0x0050:
        if (r0 == 0) goto L_0x007c;
    L_0x0052:
        r0 = new com.models.PlayerTrack;	 Catch:{ all -> 0x007e }
        r2 = -1;
        r3 = 0;
        r0.<init>(r5, r3, r2, r3);	 Catch:{ all -> 0x007e }
        r2 = com.playercache.TrackCacheQueueManager.INSERTION_ORDER.FIRST;	 Catch:{ all -> 0x007e }
        r2 = r2.ordinal();	 Catch:{ all -> 0x007e }
        if (r6 != r2) goto L_0x0067;
    L_0x0061:
        r6 = r4.c;	 Catch:{ all -> 0x007e }
        r6.addFirst(r0);	 Catch:{ all -> 0x007e }
        goto L_0x006c;
    L_0x0067:
        r6 = r4.c;	 Catch:{ all -> 0x007e }
        r6.addLast(r0);	 Catch:{ all -> 0x007e }
    L_0x006c:
        r6 = r4.d;	 Catch:{ all -> 0x007e }
        r2 = r5.getBusinessObjId();	 Catch:{ all -> 0x007e }
        r6.put(r2, r0);	 Catch:{ all -> 0x007e }
        r6 = com.playercache.d.a();	 Catch:{ all -> 0x007e }
        r6.a(r5, r1);	 Catch:{ all -> 0x007e }
    L_0x007c:
        monitor-exit(r4);
        return;
    L_0x007e:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.playercache.TrackCacheQueueManager.a(com.gaana.models.Tracks$Track, int):void");
    }

    public synchronized PlayerTrack b() {
        if (this.c.size() <= 0) {
            return null;
        }
        return (PlayerTrack) this.c.remove(0);
    }

    public void a(String str) {
        this.d.remove(str);
        d.a().a(str);
    }
}

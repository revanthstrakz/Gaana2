package com.helpshift.common.platform;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.helpshift.g.b.a;
import com.helpshift.support.HSSearch.HS_SEARCH_OPTIONS;
import com.helpshift.support.d;
import java.util.ArrayList;

public class e implements a {
    d a;

    public e(d dVar) {
        this.a = dVar;
    }

    public ArrayList a(String str) {
        return this.a.a(str, HS_SEARCH_OPTIONS.KEYWORD_SEARCH);
    }

    public void a() {
        HandlerThread handlerThread = new HandlerThread("HS-faqdm-index");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            public void run() {
                e.this.a.a(new Handler() {
                    public void handleMessage(Message message) {
                        if (message.what != com.helpshift.support.b.a.c) {
                            e.this.a.i();
                            com.helpshift.support.util.d.b();
                        }
                    }
                }, new Handler(), null);
            }
        });
    }
}

package com.player_framework;

import android.content.Context;
import android.content.Intent;
import com.constants.Constants.ErrorType;
import com.f.a;
import com.models.PlayerTrack;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.services.l.bc;
import com.utilities.d;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class o {
    private static ConcurrentHashMap<String, n> a = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, m> b = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, m> c = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, a> d = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, m> e = new ConcurrentHashMap();
    private static bc f;
    private static n.a g;
    private static HashMap<String, Object> h = new HashMap();

    public static void a(n.a aVar) {
        g = aVar;
    }

    public static n.a a() {
        return g;
    }

    public static void a(Context context) {
        a(context, PlayerCommands.PLAY);
        for (n nVar : a.values()) {
            if (nVar != null) {
                nVar.onPlayerPlay();
            }
        }
    }

    public static void a(Context context, PlayerTrack playerTrack) {
        a(context, PlayerCommands.PLAY_TRACK, playerTrack);
        for (n nVar : a.values()) {
            if (nVar != null) {
                nVar.onPlayerPlay();
            }
        }
    }

    public static void a(Context context, int i) {
        a(context, PlayerCommands.SEEK_TO, i);
    }

    public static void a(Context context, PauseReasons pauseReasons) {
        a(context, PlayerCommands.PAUSE, pauseReasons.toInt());
    }

    public static void b(Context context, PauseReasons pauseReasons) {
        a(context, PlayerCommands.PLAY_PAUSE, pauseReasons.toInt());
    }

    public static void c(Context context, PauseReasons pauseReasons) {
        a(context, PlayerCommands.RESUME, pauseReasons.toInt());
    }

    public static void b(Context context) {
        a(context, PlayerCommands.FETCH_CF_TRACKS);
    }

    public static void c(Context context) {
        a(context, PlayerCommands.CANCEL_CF_SCHEDULER);
    }

    public static void d(Context context) {
        a(context, PlayerCommands.STOP);
    }

    public static void e(Context context) {
        a(context, PlayerCommands.PLAY_PREVIOUS);
    }

    public static void f(Context context) {
        a(context, PlayerCommands.PLAY_NEXT);
    }

    public static void a(Context context, String str, ErrorType errorType) {
        a(context, PlayerCommands.HANDLE_ERROR, str, errorType);
    }

    public static void g(Context context) {
        a(context, PlayerCommands.UPDATE_NOTIFICATION);
    }

    public static void h(Context context) {
        a(context, PlayerCommands.REMOVE_NOTIFICATION);
    }

    public static void b(Context context, int i) {
        a(context, PlayerCommands.CHANGE_STREAMING_QUALITY, i);
    }

    public static void c(Context context, int i) {
        a(context, PlayerCommands.CHANGE_SONG_MODE, i);
    }

    private static void a(Context context, PlayerCommands playerCommands) {
        Intent intent = new Intent(context, GaanaMusicService.class);
        intent.putExtra("EXTRA_PLAYER_COMMAND", playerCommands.toInt());
        if (d.d()) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static void a(Context context, PlayerCommands playerCommands, int i) {
        Intent intent = new Intent(context, GaanaMusicService.class);
        intent.putExtra("EXTRA_PLAYER_COMMAND", playerCommands.toInt());
        intent.putExtra("EXTRA_PLAYER_COMMAND_ARG", i);
        if (d.d()) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static void a(Context context, PlayerCommands playerCommands, String str, ErrorType errorType) {
        Intent intent = new Intent(context, GaanaMusicService.class);
        intent.putExtra("EXTRA_PLAYER_COMMAND", playerCommands.toInt());
        intent.putExtra("EXTRA_ERROR_MSG", str);
        intent.putExtra("EXTRA_ERROR_TYPE", errorType);
        if (d.d()) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static void a(Context context, PlayerCommands playerCommands, PlayerTrack playerTrack) {
        Intent intent = new Intent(context, GaanaMusicService.class);
        intent.putExtra("EXTRA_PLAYER_COMMAND", playerCommands.toInt());
        intent.putExtra("EXTRA_TRACK_OBJ", playerTrack);
        if (d.d()) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public static n a(String str) {
        return (n) a.get(str);
    }

    public static void a(String str, n nVar) {
        a.remove(str);
        a.put(str, nVar);
    }

    public static void b(String str) {
        a.remove(str);
    }

    public static ConcurrentHashMap<String, m> b() {
        return b;
    }

    public static m c(String str) {
        return (m) b.get(str);
    }

    public static void a(String str, m mVar) {
        b.remove(str);
        b.put(str, mVar);
    }

    public static void d(String str) {
        b.remove(str);
    }

    public static m e(String str) {
        return (m) c.get(str);
    }

    public static void b(String str, m mVar) {
        c.remove(str);
        c.put(str, mVar);
    }

    public static m f(String str) {
        return (m) e.get(str);
    }

    public static void c(String str, m mVar) {
        e.remove(str);
        e.put(str, mVar);
    }

    public static void a(bc bcVar) {
        f = bcVar;
    }

    public static bc c() {
        return f;
    }

    public static ConcurrentHashMap<String, n> d() {
        return a;
    }

    public static a g(String str) {
        return (a) d.get(str);
    }

    public static void a(String str, a aVar) {
        d.remove(str);
        d.put(str, aVar);
    }
}

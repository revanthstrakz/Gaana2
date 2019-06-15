package com.auto.b;

import com.gaana.localmedia.LocalMediaManager;
import java.util.ArrayList;

public class b {
    public static ArrayList<String> a = new ArrayList();
    public static ArrayList<String> b = new ArrayList();
    public static ArrayList<String> c = new ArrayList();
    public static ArrayList<String> d = new ArrayList();

    static {
        a.add("Fav Songs");
        a.add("Fav Albums");
        a.add("Fav PLaylists");
        a.add("Fav Radios");
        a.add("Fav Artists");
        a.add("Fav Local");
        b.add("Home");
        b.add("Radio");
        b.add(LocalMediaManager.MY_MUSIC);
        b.add("Queue");
        c.add("Top Charts");
        c.add("Trending Songs");
        c.add("New Releases");
        d.add("Radio Mirchi");
        d.add("Gaana Radio");
    }
}

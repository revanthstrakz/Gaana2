package com.comscore.streaming;

import java.util.Map;
import java.util.Set;

public class StreamSenseUtils {
    public static void filterMap(Map<String, String> map, Set<String> set) {
        for (String str : map.keySet()) {
            if (!set.contains(str)) {
                map.remove(str);
            }
        }
    }
}

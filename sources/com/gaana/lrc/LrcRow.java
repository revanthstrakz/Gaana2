package com.gaana.lrc;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class LrcRow implements Comparable<LrcRow> {
    public static final String TAG = "LrcRow";
    public String content;
    public String strTime;
    public long time;

    public LrcRow(String str, long j, String str2) {
        this.strTime = str;
        this.time = j;
        this.content = str2;
    }

    public static List<LrcRow> createRows(String str) {
        try {
            str = getCorrectedLyricsString(str);
            int lastIndexOf = str.lastIndexOf("]") + 1;
            String substring = str.substring(lastIndexOf, str.length());
            int i = 0;
            String[] split = str.substring(0, lastIndexOf).replace("[", "-").replace("]", "-").split("-");
            ArrayList arrayList = new ArrayList();
            int length = split.length;
            while (i < length) {
                String str2 = split[i];
                if (str2.trim().length() != 0) {
                    arrayList.add(new LrcRow(str2, timeConvert(str2), substring));
                }
                i++;
            }
            return arrayList;
        } catch (Exception e) {
            String str3 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("createRows exception:");
            stringBuilder.append(e.getMessage());
            Log.e(str3, stringBuilder.toString());
            return null;
        }
    }

    private static String getCorrectedLyricsString(String str) {
        str = str.replaceAll("[﻿-￿]", "");
        if (str.indexOf(91) == 0 && str.indexOf(93) == 9) {
            return str;
        }
        String substring = str.substring(0, str.indexOf("]") + 1);
        String[] split;
        StringBuilder stringBuilder;
        if (!substring.contains(".") && substring.split(":").length < 3) {
            split = substring.split("]");
            stringBuilder = new StringBuilder();
            stringBuilder.append(split[0]);
            stringBuilder.append(".00");
            stringBuilder.append("]");
            stringBuilder.append(str.substring(str.indexOf("]") + 1));
            str = stringBuilder.toString();
        } else if ((substring.contains(".") || substring.split(":").length == 3) && substring.length() == 9) {
            split = substring.split("]");
            stringBuilder = new StringBuilder();
            stringBuilder.append(split[0]);
            stringBuilder.append("0");
            stringBuilder.append("]");
            stringBuilder.append(str.substring(str.indexOf("]") + 1));
            str = stringBuilder.toString();
        }
        return str;
    }

    private static long timeConvert(String str) {
        String[] split = str.replace('.', ':').split(":");
        return (long) ((((Integer.valueOf(split[0]).intValue() * 60) * 1000) + (Integer.valueOf(split[1]).intValue() * 1000)) + Integer.valueOf(split[2]).intValue());
    }

    public int compareTo(LrcRow lrcRow) {
        return (int) (this.time - lrcRow.time);
    }
}

package com.gaana.lrc;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultLrcBuilder implements ILrcBuilder {
    static final String TAG = "DefaultLrcBuilder";

    public List<LrcRow> getLrcRows(String str) {
        Log.d(TAG, "getLrcRows by rawString");
        List<LrcRow> list = null;
        if (str == null || str.length() == 0) {
            Log.e(TAG, "getLrcRows rawLrc null or empty");
            return list;
        }
        StringReader stringReader = new StringReader(str);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        List<LrcRow> arrayList = new ArrayList();
        while (true) {
            String readLine;
            try {
                readLine = bufferedReader.readLine();
                if (readLine != null && readLine.length() > 0) {
                    List createRows = LrcRow.createRows(readLine);
                    if (createRows != null && createRows.size() > 0) {
                        arrayList.addAll(createRows);
                        continue;
                    }
                }
                if (readLine == null) {
                    break;
                }
            } catch (Exception e) {
                arrayList = e;
                readLine = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("parse exception:");
                stringBuilder.append(arrayList.getMessage());
                arrayList = stringBuilder.toString();
                Log.e(readLine, arrayList);
                return list;
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                stringReader.close();
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }
}

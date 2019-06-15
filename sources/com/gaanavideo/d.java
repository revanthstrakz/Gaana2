package com.gaanavideo;

import android.support.v4.content.ContextCompat;
import com.gaana.application.GaanaApplication;
import java.io.File;
import java.util.HashMap;

public class d {
    HashMap<String, a> a = new HashMap();
    a b;
    a c;
    int d = 10;

    public d(String str) {
        int i = 0;
        File[] listFiles = new File(ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0].getAbsolutePath(), str).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            int length = listFiles.length;
            while (i < length) {
                File file = listFiles[i];
                String[] split = file.getAbsolutePath().split("/");
                if (split[split.length - 1] != null) {
                    a(split[split.length - 1], file.getAbsolutePath());
                }
                i++;
            }
        }
    }

    public boolean a(String str) {
        if (!this.a.containsKey(str)) {
            return false;
        }
        a aVar = (a) this.a.get(str);
        b(aVar);
        this.a.remove(str);
        File file = new File(aVar.a);
        if (file.exists()) {
            a(file);
        }
        return true;
    }

    public void a(String str, String str2) {
        if (this.a.containsKey(str)) {
            a aVar = (a) this.a.get(str);
            aVar.a = str2;
            b(aVar);
            a(aVar);
            return;
        }
        a aVar2 = new a();
        aVar2.c = null;
        aVar2.d = null;
        aVar2.a = str2;
        aVar2.b = str;
        if (this.a.size() > this.d) {
            this.a.remove(this.c.b);
            b(this.c);
            File file = new File(this.c.a);
            if (file.exists()) {
                a(file);
            }
            a(aVar2);
        } else {
            a(aVar2);
        }
        this.a.put(str, aVar2);
    }

    public void a(a aVar) {
        aVar.d = this.b;
        aVar.c = null;
        if (this.b != null) {
            this.b.c = aVar;
        }
        this.b = aVar;
        if (this.c == null) {
            this.c = this.b;
        }
    }

    public void b(a aVar) {
        if (aVar.c != null) {
            aVar.c.d = aVar.d;
        } else {
            this.b = aVar.d;
        }
        if (aVar.d != null) {
            aVar.d.c = aVar.c;
            return;
        }
        this.c = aVar.c;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File a : listFiles) {
                a(a);
            }
        }
        if (!file.getName().contains("track_cached_content_index.exi")) {
            file.delete();
        }
    }
}

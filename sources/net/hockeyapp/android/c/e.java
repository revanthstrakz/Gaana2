package net.hockeyapp.android.c;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.net.URL;
import net.hockeyapp.android.b.a;

public class e extends d {
    private long g;

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
    }

    public e(Context context, String str, a aVar) {
        super(context, str, aVar);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public Long doInBackground(Void... voidArr) {
        try {
            return Long.valueOf((long) a(new URL(b()), 6).getContentLength());
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return Long.valueOf(0);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(Long l) {
        this.g = l.longValue();
        if (this.g > 0) {
            this.b.a(this);
        } else {
            this.b.a(this, Boolean.valueOf(false));
        }
    }

    public long c() {
        return this.g;
    }
}

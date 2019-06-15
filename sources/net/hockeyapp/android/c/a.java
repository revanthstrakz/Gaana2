package net.hockeyapp.android.c;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.til.colombia.android.internal.e;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;
import net.hockeyapp.android.d.d;
import net.hockeyapp.android.d.f;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.views.AttachmentView;

public class a {
    private Queue<b> a;
    private boolean b;

    private static class a {
        public static final a a = new a();
    }

    private static class b {
        private final FeedbackAttachment a;
        private final AttachmentView b;
        private boolean c;
        private int d;

        /* synthetic */ b(FeedbackAttachment feedbackAttachment, AttachmentView attachmentView, AnonymousClass1 anonymousClass1) {
            this(feedbackAttachment, attachmentView);
        }

        private b(FeedbackAttachment feedbackAttachment, AttachmentView attachmentView) {
            this.a = feedbackAttachment;
            this.b = attachmentView;
            this.c = false;
            this.d = 2;
        }

        public FeedbackAttachment a() {
            return this.a;
        }

        public AttachmentView b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }

        public boolean d() {
            return this.d > 0;
        }

        public boolean e() {
            int i = this.d - 1;
            this.d = i;
            return i >= 0;
        }
    }

    private static class c extends AsyncTask<Void, Integer, Boolean> {
        private final b a;
        private final Handler b;
        private File c = net.hockeyapp.android.a.a();
        private Bitmap d = null;
        private int e = 0;

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public void onProgressUpdate(Integer... numArr) {
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
        }

        public c(b bVar, Handler handler) {
            this.a = bVar;
            this.b = handler;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            FeedbackAttachment a = this.a.a();
            if (a.d()) {
                d.c("Cached...");
                a();
                return Boolean.valueOf(true);
            }
            d.c("Downloading...");
            boolean a2 = a(a.b(), a.c());
            if (a2) {
                a();
            }
            return Boolean.valueOf(a2);
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            AttachmentView b = this.a.b();
            this.a.a(bool.booleanValue());
            if (bool.booleanValue()) {
                b.setImage(this.d, this.e);
            } else if (!this.a.d()) {
                b.b();
            }
            this.b.sendEmptyMessage(0);
        }

        private void a() {
            try {
                String c = this.a.a().c();
                AttachmentView b = this.a.b();
                this.e = f.a(new File(this.c, c));
                this.d = f.a(new File(this.c, c), this.e == 1 ? b.getWidthLandscape() : b.getWidthPortrait(), this.e == 1 ? b.getMaxHeightLandscape() : b.getMaxHeightPortrait());
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                this.d = null;
            }
        }

        private boolean a(String str, String str2) {
            try {
                URLConnection a = a(new URL(str));
                a.connect();
                int contentLength = a.getContentLength();
                String headerField = a.getHeaderField("Status");
                if (headerField != null && !headerField.startsWith("200")) {
                    return false;
                }
                boolean z;
                File file = new File(this.c, str2);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(a.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    z = true;
                    if (read == -1) {
                        break;
                    }
                    publishProgress(new Integer[]{Integer.valueOf((int) ((100 * (j + ((long) read))) / ((long) contentLength)))});
                    fileOutputStream.write(bArr, 0, read);
                    j = r16;
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                if (j <= 0) {
                    z = false;
                }
                return z;
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                return false;
            }
        }

        private URLConnection a(URL url) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty(e.c, "HockeySDK/Android 4.1.1");
            httpURLConnection.setInstanceFollowRedirects(true);
            if (VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("connection", "close");
            }
            return httpURLConnection;
        }
    }

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.a = new LinkedList();
        this.b = false;
    }

    public void a(FeedbackAttachment feedbackAttachment, AttachmentView attachmentView) {
        this.a.add(new b(feedbackAttachment, attachmentView, null));
        b();
    }

    private void b() {
        if (!this.b) {
            b bVar = (b) this.a.peek();
            if (bVar != null) {
                c cVar = new c(bVar, new Handler() {
                    public void handleMessage(Message message) {
                        final b bVar = (b) a.this.a.poll();
                        if (!bVar.c() && bVar.e()) {
                            postDelayed(new Runnable() {
                                public void run() {
                                    a.this.a.add(bVar);
                                    a.this.b();
                                }
                            }, 3000);
                        }
                        a.this.b = false;
                        a.this.b();
                    }
                });
                this.b = true;
                net.hockeyapp.android.d.a.a(cVar);
            }
        }
    }
}

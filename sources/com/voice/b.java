package com.voice;

import android.content.Context;
import android.media.MediaRecorder;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import com.gaana.models.BusinessObject;
import com.gaana.models.SongResultModel;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.i.j;
import com.managers.URLManager;
import com.services.l.s;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class b {
    private static b a;
    private Context b;
    private String c = null;
    private MediaRecorder d = null;
    private a e;

    public interface a {
        void a(Track track);

        void a(String str);
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Context context, a aVar) {
        this.b = context;
        this.e = aVar;
    }

    public boolean b() {
        c();
        this.d = new MediaRecorder();
        this.d.setAudioSource(1);
        this.d.setOutputFormat(2);
        this.d.setOutputFile(this.c);
        this.d.setAudioEncoder(3);
        this.d.setAudioChannels(1);
        this.d.setAudioSamplingRate(44100);
        this.d.setAudioEncodingBitRate(320000);
        try {
            this.d.prepare();
            this.d.start();
        } catch (IOException unused) {
            Log.e("SongIdentifyManager", "prepare() failed");
        }
        return true;
    }

    public void c() {
        File file = new File(ContextCompat.getExternalFilesDirs(this.b, null)[0].getAbsolutePath(), "song_clip_temp");
        file.mkdir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getAbsolutePath());
        stringBuilder.append("/");
        stringBuilder.append("clip_001.mp4");
        this.c = stringBuilder.toString();
        File file2 = new File(this.c);
        if (file2.exists()) {
            file2.delete();
        }
    }

    public void d() {
        if (this.d != null) {
            this.d.stop();
            this.d.release();
            this.d = null;
        }
    }

    public void e() {
        try {
            String a = a(this.c);
            URLManager uRLManager = new URLManager();
            HashMap hashMap = new HashMap();
            hashMap.put("audiofile", a);
            uRLManager.a(hashMap);
            uRLManager.a("https://mis.gaana.com/identify");
            uRLManager.i(false);
            uRLManager.a(SongResultModel.class);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.c(1);
            uRLManager.c("song_identify");
            j.a().a((Object) "song_identify");
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject instanceof SongResultModel) {
                        SongResultModel songResultModel = (SongResultModel) businessObject;
                        if (songResultModel.getTrackArrayList() == null || songResultModel.getTrackArrayList().size() <= 0) {
                            b.this.e.a(businessObject.toString());
                            return;
                        } else {
                            b.this.e.a((Track) songResultModel.getTrackArrayList().get(0));
                            return;
                        }
                    }
                    b.this.e.a(businessObject != null ? businessObject.toString() : null);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    b.this.e.a(businessObject.toString());
                }
            }, uRLManager);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.e.a("Exception in converting audio into string");
        }
    }

    public String a(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                fileInputStream.close();
                byteArrayOutputStream.close();
                return Base64.encodeToString(bArr, 0);
            }
        }
    }
}

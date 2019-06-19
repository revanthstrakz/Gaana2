package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.exoplayer2.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    protected static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private final long allowedVideoJoiningTimeMs;
    private final Context context;
    @Nullable
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final int extensionRendererMode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    /* Access modifiers changed, original: protected */
    public void buildMiscellaneousRenderers(Context context, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context) {
        this(context, 0);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        this(context, (DrmSessionManager) drmSessionManager, 0);
    }

    public DefaultRenderersFactory(Context context, int i) {
        this(context, i, (long) DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i) {
        this(context, drmSessionManager, i, DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    public DefaultRenderersFactory(Context context, int i, long j) {
        this.context = context;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
        this.drmSessionManager = null;
    }

    @Deprecated
    public DefaultRenderersFactory(Context context, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i, long j) {
        this.context = context;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
        this.drmSessionManager = drmSessionManager;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        DrmSessionManager drmSessionManager2 = drmSessionManager == null ? this.drmSessionManager : drmSessionManager;
        ArrayList arrayList = new ArrayList();
        DrmSessionManager drmSessionManager3 = drmSessionManager2;
        buildVideoRenderers(this.context, drmSessionManager3, this.allowedVideoJoiningTimeMs, handler, videoRendererEventListener, this.extensionRendererMode, arrayList);
        buildAudioRenderers(this.context, drmSessionManager3, buildAudioProcessors(), handler, audioRendererEventListener, this.extensionRendererMode, arrayList);
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, arrayList);
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    /* Access modifiers changed, original: protected */
    public void buildVideoRenderers(Context context, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, ArrayList<Renderer> arrayList) {
        int i2 = i;
        ArrayList<Renderer> arrayList2 = arrayList;
        arrayList2.add(new MediaCodecVideoRenderer(context, MediaCodecSelector.DEFAULT, j, drmSessionManager, false, handler, videoRendererEventListener, 50));
        if (i2 != 0) {
            int size = arrayList.size();
            if (i2 == 2) {
                size--;
            }
            try {
                arrayList2.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{Boolean.TYPE, Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE}).newInstance(new Object[]{Boolean.valueOf(true), Long.valueOf(j), handler, videoRendererEventListener, Integer.valueOf(50)}));
                Log.i(TAG, "Loaded LibvpxVideoRenderer.");
            } catch (ClassNotFoundException unused) {
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating VP9 extension", e);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0069 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005e A:{ExcHandler: Exception (r0_0 'e' java.lang.Throwable), Splitter:B:7:0x002b} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x00a7 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009c A:{ExcHandler: Exception (r0_1 'e' java.lang.Throwable), Splitter:B:17:0x0069} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:12:0x005e, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:14:0x0067, code skipped:
            throw new java.lang.RuntimeException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Missing block: B:15:0x0068, code skipped:
            r7 = r1;
     */
    /* JADX WARNING: Missing block: B:22:0x009c, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:24:0x00a5, code skipped:
            throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Missing block: B:25:0x00a6, code skipped:
            r6 = r7;
     */
    public void buildAudioRenderers(android.content.Context r14, @android.support.annotation.Nullable com.google.android.exoplayer2.drm.DrmSessionManager<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r15, com.google.android.exoplayer2.audio.AudioProcessor[] r16, android.os.Handler r17, com.google.android.exoplayer2.audio.AudioRendererEventListener r18, int r19, java.util.ArrayList<com.google.android.exoplayer2.Renderer> r20) {
        /*
        r13 = this;
        r10 = r19;
        r11 = r20;
        r12 = new com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
        r3 = com.google.android.exoplayer2.mediacodec.MediaCodecSelector.DEFAULT;
        r8 = com.google.android.exoplayer2.audio.AudioCapabilities.getCapabilities(r14);
        r5 = 0;
        r1 = r12;
        r2 = r14;
        r4 = r15;
        r6 = r17;
        r7 = r18;
        r9 = r16;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r11.add(r12);
        if (r10 != 0) goto L_0x001f;
    L_0x001e:
        return;
    L_0x001f:
        r1 = r20.size();
        r2 = 2;
        if (r10 != r2) goto L_0x0028;
    L_0x0026:
        r1 = r1 + -1;
    L_0x0028:
        r3 = 0;
        r4 = 3;
        r5 = 1;
        r6 = "com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer";
        r6 = java.lang.Class.forName(r6);	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r8 = android.os.Handler.class;
        r7[r3] = r8;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class;
        r7[r5] = r8;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class;
        r7[r2] = r8;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r6 = r6.getConstructor(r7);	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7 = new java.lang.Object[r4];	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7[r3] = r17;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7[r5] = r18;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7[r2] = r16;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r6 = r6.newInstance(r7);	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r6 = (com.google.android.exoplayer2.Renderer) r6;	 Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
        r7 = r1 + 1;
        r11.add(r1, r6);	 Catch:{ ClassNotFoundException -> 0x0069, Exception -> 0x005e }
        r1 = "DefaultRenderersFactory";
        r6 = "Loaded LibopusAudioRenderer.";
        com.google.android.exoplayer2.util.Log.i(r1, r6);	 Catch:{ ClassNotFoundException -> 0x0069, Exception -> 0x005e }
        goto L_0x0069;
    L_0x005e:
        r0 = move-exception;
        r1 = r0;
        r2 = new java.lang.RuntimeException;
        r3 = "Error instantiating Opus extension";
        r2.<init>(r3, r1);
        throw r2;
    L_0x0068:
        r7 = r1;
    L_0x0069:
        r1 = "com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r8 = android.os.Handler.class;
        r6[r3] = r8;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class;
        r6[r5] = r8;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class;
        r6[r2] = r8;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r1 = r1.getConstructor(r6);	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6 = new java.lang.Object[r4];	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6[r3] = r17;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6[r5] = r18;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6[r2] = r16;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r1 = r1.newInstance(r6);	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r1 = (com.google.android.exoplayer2.Renderer) r1;	 Catch:{ ClassNotFoundException -> 0x00a6, Exception -> 0x009c }
        r6 = r7 + 1;
        r11.add(r7, r1);	 Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009c }
        r1 = "DefaultRenderersFactory";
        r7 = "Loaded LibflacAudioRenderer.";
        com.google.android.exoplayer2.util.Log.i(r1, r7);	 Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009c }
        goto L_0x00a7;
    L_0x009c:
        r0 = move-exception;
        r1 = r0;
        r2 = new java.lang.RuntimeException;
        r3 = "Error instantiating FLAC extension";
        r2.<init>(r3, r1);
        throw r2;
    L_0x00a6:
        r6 = r7;
    L_0x00a7:
        r1 = "com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r7 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r8 = android.os.Handler.class;
        r7[r3] = r8;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r8 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class;
        r7[r5] = r8;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r8 = com.google.android.exoplayer2.audio.AudioProcessor[].class;
        r7[r2] = r8;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r1 = r1.getConstructor(r7);	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r4 = new java.lang.Object[r4];	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r4[r3] = r17;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r4[r5] = r18;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r4[r2] = r16;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r1 = r1.newInstance(r4);	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r1 = (com.google.android.exoplayer2.Renderer) r1;	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r11.add(r6, r1);	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        r1 = "DefaultRenderersFactory";
        r2 = "Loaded FfmpegAudioRenderer.";
        com.google.android.exoplayer2.util.Log.i(r1, r2);	 Catch:{ ClassNotFoundException -> 0x00e2, Exception -> 0x00d8 }
        goto L_0x00e2;
    L_0x00d8:
        r0 = move-exception;
        r1 = r0;
        r2 = new java.lang.RuntimeException;
        r3 = "Error instantiating FFmpeg extension";
        r2.<init>(r3, r1);
        throw r2;
    L_0x00e2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, com.google.android.exoplayer2.drm.DrmSessionManager, com.google.android.exoplayer2.audio.AudioProcessor[], android.os.Handler, com.google.android.exoplayer2.audio.AudioRendererEventListener, int, java.util.ArrayList):void");
    }

    /* Access modifiers changed, original: protected */
    public void buildTextRenderers(Context context, TextOutput textOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* Access modifiers changed, original: protected */
    public void buildMetadataRenderers(Context context, MetadataOutput metadataOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    /* Access modifiers changed, original: protected */
    public void buildCameraMotionRenderers(Context context, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new CameraMotionRenderer());
    }

    /* Access modifiers changed, original: protected */
    public AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }
}

package com.player_framework;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.cast_music.VideoCastManager;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.constants.c;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.StreamUrls;
import com.gaana.models.StreamUrls.StreamUrl;
import com.gaana.models.TrackUrlResponseModel;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.e.a;
import com.i.e.b;
import com.i.i;
import com.i.j;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.PlayerManager;
import com.managers.u;
import com.models.PlayerTrack;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import com.utilities.k;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.HashMap;
import java.util.Map;

public class d {
    Context a;
    String b = null;

    public d(Context context) {
        this.a = context;
        this.b = c.v;
    }

    public void a(PlayerTrack playerTrack, final b bVar) {
        String stringBuilder;
        int e = playerTrack.e();
        String str = this.b;
        StringBuilder stringBuilder2;
        if (e == SOURCE_TYPE.ONE_TOUCH_RADIO.ordinal()) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.b);
            stringBuilder2.append("source_id=");
            stringBuilder2.append(playerTrack.c());
            stringBuilder2.append("&source_type=");
            stringBuilder2.append(playerTrack.e());
            stringBuilder2.append("&tg=");
            stringBuilder2.append(Constants.ed);
            stringBuilder2.append("&");
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.b);
            stringBuilder2.append("source_id=");
            stringBuilder2.append(playerTrack.c());
            stringBuilder2.append("&source_type=");
            stringBuilder2.append(playerTrack.e());
            stringBuilder2.append("&");
            stringBuilder = stringBuilder2.toString();
        }
        final Track a = playerTrack.a(true);
        final Map a2 = a(this.a, a);
        com.i.b bVar2 = new com.i.b(stringBuilder, TrackUrlResponseModel.class, new a() {
            /* JADX WARNING: Removed duplicated region for block: B:36:0x00f1  */
            public void onDataRetrieved(java.lang.Object r7, boolean r8) {
                /*
                r6 = this;
                r0 = "";
                r1 = new java.lang.StringBuilder;
                r1.<init>();
                r2 = r7;
                r2 = r2.getBusinessObjId();
                r1.append(r2);
                r2 = "-";
                r1.append(r2);
                r2 = r2;
                r3 = "quality";
                r2 = r2.get(r3);
                r2 = (java.lang.String) r2;
                r1.append(r2);
                r1 = r1.toString();
                r2 = 0;
                if (r7 == 0) goto L_0x0056;
            L_0x0029:
                r7 = (com.gaana.models.TrackUrlResponseModel) r7;	 Catch:{ Exception -> 0x0053 }
                r3 = r7.getContentSource();	 Catch:{ Exception -> 0x0053 }
                r3 = com.utilities.Util.n(r3);	 Catch:{ Exception -> 0x0053 }
                com.utilities.Util.m(r3);	 Catch:{ Exception -> 0x004f }
                r0 = r7.getStatus();	 Catch:{ Exception -> 0x004f }
                r4 = r7.getData();	 Catch:{ Exception -> 0x004f }
                r5 = 1;
                if (r0 != r5) goto L_0x004d;
            L_0x0041:
                r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x004f }
                if (r0 != 0) goto L_0x004d;
            L_0x0047:
                if (r4 == 0) goto L_0x004d;
            L_0x0049:
                r2 = com.utilities.Util.l(r4);	 Catch:{ Exception -> 0x004f }
            L_0x004d:
                r0 = r3;
                goto L_0x0057;
            L_0x004f:
                r7 = move-exception;
                r0 = r3;
                goto L_0x00eb;
            L_0x0053:
                r7 = move-exception;
                goto L_0x00eb;
            L_0x0056:
                r7 = r2;
            L_0x0057:
                r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0053 }
                if (r3 != 0) goto L_0x0068;
            L_0x005d:
                r3 = r8;	 Catch:{ Exception -> 0x0053 }
                r7 = r7.getAvAdFlag();	 Catch:{ Exception -> 0x0053 }
                r3.onDataRetrieved(r2, r7, r8);	 Catch:{ Exception -> 0x0053 }
                goto L_0x0130;
            L_0x0068:
                r8 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ Exception -> 0x0053 }
                r8 = r8.getResources();	 Catch:{ Exception -> 0x0053 }
                r2 = 2131821427; // 0x7f110373 float:1.9275597E38 double:1.05325973E-314;
                r8 = r8.getString(r2);	 Catch:{ Exception -> 0x0053 }
                r2 = "";
                if (r7 == 0) goto L_0x00a0;
            L_0x007b:
                r3 = r7.getErrorCode();	 Catch:{ Exception -> 0x0053 }
                r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x0053 }
                if (r3 != 0) goto L_0x00a0;
            L_0x0085:
                r2 = r7.getErrorCode();	 Catch:{ Exception -> 0x0053 }
                r7 = "4001";
                r7 = r2.equalsIgnoreCase(r7);	 Catch:{ Exception -> 0x0053 }
                if (r7 == 0) goto L_0x00a0;
            L_0x0091:
                r7 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ Exception -> 0x0053 }
                r7 = r7.getResources();	 Catch:{ Exception -> 0x0053 }
                r8 = 2131822521; // 0x7f1107b9 float:1.9277816E38 double:1.0532602707E-314;
                r8 = r7.getString(r8);	 Catch:{ Exception -> 0x0053 }
            L_0x00a0:
                r7 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0053 }
                if (r7 != 0) goto L_0x00bb;
            L_0x00a6:
                r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053 }
                r7.<init>();	 Catch:{ Exception -> 0x0053 }
                r7.append(r1);	 Catch:{ Exception -> 0x0053 }
                r3 = "-";
                r7.append(r3);	 Catch:{ Exception -> 0x0053 }
                r7.append(r2);	 Catch:{ Exception -> 0x0053 }
                r7 = r7.toString();	 Catch:{ Exception -> 0x0053 }
                r1 = r7;
            L_0x00bb:
                r7 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0053 }
                if (r7 != 0) goto L_0x00d6;
            L_0x00c1:
                r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053 }
                r7.<init>();	 Catch:{ Exception -> 0x0053 }
                r7.append(r0);	 Catch:{ Exception -> 0x0053 }
                r2 = "-";
                r7.append(r2);	 Catch:{ Exception -> 0x0053 }
                r7.append(r1);	 Catch:{ Exception -> 0x0053 }
                r7 = r7.toString();	 Catch:{ Exception -> 0x0053 }
                r1 = r7;
            L_0x00d6:
                r7 = com.managers.u.a();	 Catch:{ Exception -> 0x0053 }
                r2 = "StreamingFailure";
                r3 = "URL not fetched - URL blank";
                r7.a(r2, r3, r1);	 Catch:{ Exception -> 0x0053 }
                r7 = com.player_framework.d.this;	 Catch:{ Exception -> 0x0053 }
                r2 = r7;	 Catch:{ Exception -> 0x0053 }
                r3 = com.constants.Constants.ErrorType.TEMPORARY_NETWORK_ERROR;	 Catch:{ Exception -> 0x0053 }
                r7.a(r2, r8, r3);	 Catch:{ Exception -> 0x0053 }
                goto L_0x0130;
            L_0x00eb:
                r8 = android.text.TextUtils.isEmpty(r0);
                if (r8 != 0) goto L_0x0105;
            L_0x00f1:
                r8 = new java.lang.StringBuilder;
                r8.<init>();
                r8.append(r0);
                r0 = "-";
                r8.append(r0);
                r8.append(r1);
                r1 = r8.toString();
            L_0x0105:
                r8 = com.managers.u.a();
                r0 = "StreamingFailure";
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "URL not fetched - ";
                r2.append(r3);
                r3 = r7.getMessage();
                r2.append(r3);
                r2 = r2.toString();
                r8.a(r0, r2, r1);
                r8 = com.player_framework.d.this;
                r0 = r7;
                r7 = r7.getMessage();
                r1 = com.constants.Constants.ErrorType.NETWORK_ERROR;
                r8.a(r0, r7, r1);
            L_0x0130:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.player_framework.d$AnonymousClass1.onDataRetrieved(java.lang.Object, boolean):void");
            }

            public void onErrorResponse(BusinessObject businessObject) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("URL not fetched - Network Failure - ");
                stringBuilder.append(businessObject.getVolleyError().getMessage());
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(a.getBusinessObjId());
                stringBuilder3.append("-");
                stringBuilder3.append((String) a2.get("quality"));
                u.a().a("StreamingFailure", stringBuilder2, stringBuilder3.toString());
                d.this.a(a, businessObject.getVolleyError().getMessage(), ErrorType.NETWORK_ERROR);
            }
        });
        bVar2.a(a2);
        bVar2.a(1);
        bVar2.a(Priority.IMMEDIATE);
        bVar2.a("streaming_url");
        bVar2.c(false);
        j.a().a((Object) "streaming_url");
        i.a().a(bVar2);
    }

    public void a(String str, String str2, final b bVar) {
        String str3 = "https://apiv2.gaana.com/video/data?";
        try {
            byte[] b = new k(Constants.bu).b(str);
            str = Util.a(Util.b(str), Constants.bd);
            Map hashMap = new HashMap();
            hashMap.put("vi", k.a(b));
            hashMap.put("type", str2);
            hashMap.put("hashcode", str);
            com.i.b bVar2 = new com.i.b(str3, Object.class, new a() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onDataRetrieved(Object obj, boolean z) {
                    bVar.onDataRetrieved(obj, 0, true);
                }
            });
            bVar2.a(hashMap);
            bVar2.a(1);
            bVar2.a(Priority.IMMEDIATE);
            bVar2.a("streaming_video_url");
            bVar2.c(false);
            bVar2.a(false);
            i.a().a(bVar2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public boolean a(long j) {
        return j - 1800 >= System.currentTimeMillis() / 1000;
    }

    private void a(Track track, String str, ErrorType errorType) {
        PlayerTrack i = PlayerManager.a(this.a).i();
        if (i != null) {
            Track b = i.b();
            if (b != null && b.getBusinessObjId().equalsIgnoreCase(track.getBusinessObjId())) {
                o.a(this.a, str, errorType);
            }
        }
    }

    private HashMap<String, String> a(Context context, Track track) {
        String businessObjId = track.getBusinessObjId();
        String a = Util.a(Util.b(businessObjId), Constants.bd);
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", businessObjId);
        hashMap.put("album_id", track.getAlbumId());
        hashMap.put("type", track.getStreamType());
        hashMap.put("isrc", track.getIsrc());
        hashMap.put("hashcode", a);
        hashMap.put("delivery_type", "stream");
        if (!VideoCastManager.y().f()) {
            hashMap.put("is_cast", "0");
        } else if (VideoCastManager.y().w()) {
            hashMap.put("is_cast", InternalAvidAdSessionContext.AVID_API_LEVEL);
        } else {
            hashMap.put("is_cast", "1");
        }
        String r = Util.r(context);
        if (!r.equalsIgnoreCase("-1")) {
            hashMap.put("quality", r);
        }
        hashMap.put(e.z, Constants.dC);
        GaanaApplication gaanaApplication = (GaanaApplication) context.getApplicationContext();
        if (gaanaApplication.getCurrentUser().getLoginStatus() && gaanaApplication.getCurrentUser().getAuthToken() != null) {
            hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, gaanaApplication.getCurrentUser().getAuthToken());
        }
        return hashMap;
    }

    private boolean a(StreamUrl streamUrl) {
        if (TextUtils.isEmpty(streamUrl.getExpiry())) {
            return true;
        }
        long longValue;
        if (streamUrl.getExpiry().contains(".")) {
            longValue = new Double(Double.parseDouble(streamUrl.getExpiry())).longValue() - 900;
        } else {
            longValue = Long.parseLong(streamUrl.getExpiry()) - 900;
        }
        if (longValue >= System.currentTimeMillis() / 1000) {
            return true;
        }
        return false;
    }

    public String a(PlayerTrack playerTrack) {
        StreamUrls streamUrls = playerTrack.b().getStreamUrls();
        if (streamUrls != null) {
            StreamUrl auto;
            String r = Util.r(GaanaApplication.getContext());
            if (r.equalsIgnoreCase("auto")) {
                auto = streamUrls.getAuto();
                if (auto == null) {
                    r = Constants.dC;
                    r = r.equalsIgnoreCase("3G") ? "medium" : r.equalsIgnoreCase("2G") ? "low" : "high";
                }
            } else {
                auto = null;
            }
            if (r.equalsIgnoreCase("extreme") || r2 == null) {
                auto = streamUrls.getExtreme();
            }
            if (r.equalsIgnoreCase("high") || r2 == null) {
                auto = streamUrls.getHigh();
            }
            if (r.equalsIgnoreCase("medium") || r2 == null) {
                auto = streamUrls.getMedium();
            }
            if (r.equalsIgnoreCase("normal") || r2 == null) {
                auto = streamUrls.getNormal();
            }
            if (!(auto == null || TextUtils.isEmpty(auto.getUrl()) || !a(auto))) {
                return auto.getUrl();
            }
        }
        return null;
    }

    public void a(Track track, b bVar) {
        if (track == null) {
            bVar.onDataRetrieved(null, 0, true);
        }
        String clipVideoUrl = track.getClipVideoUrl();
        if (TextUtils.isEmpty(clipVideoUrl)) {
            bVar.onDataRetrieved(null, 0, true);
        }
        if (a(track.getVideoExpiryTime())) {
            bVar.onDataRetrieved(clipVideoUrl, 0, true);
        } else {
            a(track.getVideoId(), "clip", bVar);
        }
    }

    public void b(Track track, b bVar) {
        a(track.getVideoId(), "vert", bVar);
    }

    public String a(BusinessObject businessObject) {
        if (businessObject == null) {
            return null;
        }
        CharSequence a;
        long f;
        if (businessObject instanceof YouTubeVideo) {
            YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
            a = youTubeVideo.a();
            f = youTubeVideo.f();
        } else if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            a = track.getVerticalUrl();
            f = track.getVideoExpiryTime();
        } else {
            f = 0;
            a = null;
        }
        if (!TextUtils.isEmpty(a) && a(f)) {
            return a;
        }
        return null;
    }

    public void a(BusinessObject businessObject, b bVar) {
        if (TextUtils.isEmpty(a(businessObject))) {
            String str = null;
            if (businessObject instanceof YouTubeVideo) {
                str = businessObject.getBusinessObjId();
            } else if (businessObject instanceof Track) {
                str = ((Track) businessObject).getVideoId();
            }
            a(str, "vert", bVar);
            return;
        }
        bVar.onDataRetrieved(a(businessObject), 0, true);
    }

    public void a(BusinessObject businessObject, String str, b bVar) {
        if (TextUtils.isEmpty(a(businessObject))) {
            String str2 = null;
            if (businessObject instanceof YouTubeVideo) {
                str2 = businessObject.getBusinessObjId();
            } else if (businessObject instanceof Track) {
                str2 = ((Track) businessObject).getVideoId();
            }
            a(str2, str, bVar);
            return;
        }
        bVar.onDataRetrieved(a(businessObject), 0, true);
    }
}

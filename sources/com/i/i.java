package com.i;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.android.volley.i.c;
import com.bumptech.glide.e;
import com.bumptech.glide.request.a.g;
import com.bumptech.glide.request.b.d;
import com.bumptech.glide.request.f;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.controls.CrossFadeImageView;
import com.library.custom_glide.GlideApp;
import com.library.custom_glide.GlideFileLoader;
import com.library.helpers.Enums.ConnectionType;
import com.library.managers.TaskManager.TaskListner;
import com.library.util.ConnectionUtil;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ae;
import com.managers.m;
import com.managers.o;
import com.managers.z;
import com.services.h;
import com.services.l.ab;
import com.services.l.af;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class i {
    private static i a;
    private Handler b = new Handler(Looper.getMainLooper());

    class a {
        private Bitmap b;

        a() {
        }

        private Bitmap a() {
            return this.b;
        }

        private void a(Bitmap bitmap) {
            this.b = bitmap;
        }
    }

    public static i a() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i();
                }
            }
        }
        return a;
    }

    public void a(final b bVar) {
        if (bVar.g()) {
            b(bVar);
            return;
        }
        String d = d(bVar);
        String replace = !bVar.t() ? bVar.e().replace(" ", "%20") : d;
        Request cVar = new c(bVar.c(), d, bVar.d(), null, new com.android.volley.i.a() {
            public void onErrorResponse(VolleyError volleyError) {
                BusinessObject businessObject = new BusinessObject();
                businessObject.setVolleyError(volleyError);
                bVar.i().onErrorResponse(businessObject);
            }
        }, new c() {
            public void a(Object obj, boolean z) {
                bVar.i().onDataRetrieved(obj, z);
            }
        });
        cVar.setShouldCache(bVar.j());
        cVar.setTag(bVar.f());
        cVar.a(bVar.k());
        cVar.b(bVar.l());
        if (bVar.n() != -1) {
            cVar.a(bVar.n());
        }
        if (bVar.b() != -1) {
            cVar.setRetryPolicy(new a(bVar.b(), bVar.a()));
        } else {
            cVar.setRetryPolicy(new a(bVar.a()));
        }
        cVar.a(replace);
        cVar.a(bVar.o());
        cVar.b(bVar.p());
        cVar.setDataToBeRefreshedAfterCacheResponse(bVar.r());
        cVar.d(bVar.s());
        cVar.setSecureCall(bVar.u());
        cVar.c(bVar.q());
        cVar.setIsToBeRefreshed(bVar.m().booleanValue());
        j.a().a(cVar);
    }

    private String d(b bVar) {
        String e = bVar.e();
        if (bVar.c() == 0) {
            Map h = bVar.h();
            if (h != null && h.size() > 0) {
                Object[] toArray = h.keySet().toArray();
                for (int i = 0; i < h.size(); i++) {
                    String str = (String) h.get(toArray[i].toString());
                    if (str != null) {
                        StringBuilder stringBuilder;
                        if (i == h.size() - 1) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(e);
                            stringBuilder.append(toArray[i]);
                            stringBuilder.append("=");
                            stringBuilder.append(URLEncoder.encode(str));
                            e = stringBuilder.toString();
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(e);
                            stringBuilder.append(toArray[i]);
                            stringBuilder.append("=");
                            stringBuilder.append(URLEncoder.encode(str));
                            stringBuilder.append("&");
                            e = stringBuilder.toString();
                        }
                    }
                }
            }
        }
        return e.replace(" ", "%20");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0053 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:11|12|13) */
    /* JADX WARNING: Missing block: B:12:?, code skipped:
            r6.i().onDataRetrieved(new com.gaana.models.BusinessObject(), false);
     */
    /* JADX WARNING: Missing block: B:13:0x005f, code skipped:
            return;
     */
    public void b(com.i.b r6) {
        /*
        r5 = this;
        r0 = com.i.j.a();
        r0 = r0.c();
        r0 = r0.d();
        r1 = r6.e();
        r0 = r0.a(r1);
        r1 = 0;
        if (r0 == 0) goto L_0x0064;
    L_0x0017:
        r2 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r0 = r0.a;	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r3 = "UTF-8";
        r2.<init>(r0, r3);	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r0 = r6.d();	 Catch:{ Exception -> 0x0053 }
        if (r0 == 0) goto L_0x0064;
    L_0x0026:
        r0 = r6.d();	 Catch:{ Exception -> 0x0053 }
        r3 = java.lang.String.class;
        if (r0 == r3) goto L_0x0064;
    L_0x002e:
        r0 = new com.google.gson.GsonBuilder;	 Catch:{ Exception -> 0x0053 }
        r0.<init>();	 Catch:{ Exception -> 0x0053 }
        r3 = 2;
        r3 = new int[r3];	 Catch:{ Exception -> 0x0053 }
        r3 = {8, 4};	 Catch:{ Exception -> 0x0053 }
        r0 = r0.excludeFieldsWithModifiers(r3);	 Catch:{ Exception -> 0x0053 }
        r0 = r0.create();	 Catch:{ Exception -> 0x0053 }
        r3 = r6.i();	 Catch:{ Exception -> 0x0053 }
        r4 = r6.d();	 Catch:{ Exception -> 0x0053 }
        r0 = r0.fromJson(r2, r4);	 Catch:{ Exception -> 0x0053 }
        r0 = (com.gaana.models.BusinessObject) r0;	 Catch:{ Exception -> 0x0053 }
        r3.onDataRetrieved(r0, r1);	 Catch:{ Exception -> 0x0053 }
        return;
    L_0x0053:
        r0 = new com.gaana.models.BusinessObject;	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r0.<init>();	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r2 = r6.i();	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        r2.onDataRetrieved(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x0060 }
        return;
    L_0x0060:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x0064:
        r6 = r6.i();
        r0 = 0;
        r6.onDataRetrieved(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.i.i.b(com.i.b):void");
    }

    public void a(ImageView imageView, String str) {
        e.c(GaanaApplication.getContext()).load(str).into(imageView);
    }

    public void a(String str, r rVar) {
        a(str, rVar, true, false);
    }

    public void a(String str, r rVar, boolean z) {
        a(str, rVar, z, false);
    }

    public void b(String str, r rVar) {
        ConnectionType[] n = Util.n(GaanaApplication.getContext());
        ConnectionType connectionType = ConnectionUtil.getConnectionType(GaanaApplication.getContext());
        boolean z = false;
        for (ConnectionType connectionType2 : n) {
            if (connectionType2 == connectionType) {
                break;
            }
        }
        z = true;
        a(str, rVar, true, z);
    }

    public void a(String str, final String str2, final ab abVar) {
        GlideApp.with(GaanaApplication.getContext()).asBitmap().load(str2).into((com.bumptech.glide.request.a.i) new g<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(Bitmap bitmap, d dVar) {
                if (bitmap != null) {
                    String replaceAll = str2.replaceAll("/", "");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(GlideFileLoader.getFile(), replaceAll));
                        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        GlideFileLoader.add(replaceAll);
                        if (abVar != null) {
                            abVar.a(replaceAll);
                        }
                    } catch (FileNotFoundException e) {
                        ThrowableExtension.printStackTrace(e);
                    } catch (IOException e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
            }
        });
    }

    public void a(String str, final r rVar, boolean z, boolean z2) {
        f fVar = new f();
        z2 = z2 || !Util.W().booleanValue();
        fVar = fVar.onlyRetrieveFromCache(z2).disallowHardwareConfig();
        com.bumptech.glide.request.a.i anonymousClass7 = new g<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(Bitmap bitmap, d dVar) {
                if (bitmap != null && rVar != null) {
                    rVar.onSuccessfulResponse(bitmap);
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                if (rVar != null) {
                    rVar.onErrorResponse(new VolleyError());
                }
            }
        };
        if (TextUtils.isEmpty(str)) {
            if (rVar != null) {
                rVar.onErrorResponse(new VolleyError());
            }
            return;
        }
        if (GlideFileLoader.contains(str)) {
            a(str.replaceAll("/", ""), fVar, rVar);
        } else {
            e.c(GaanaApplication.getContext().getApplicationContext()).asBitmap().load(str).apply(fVar).into(anonymousClass7);
        }
    }

    private void a(String str, f fVar, final r rVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GlideFileLoader.getPath());
        stringBuilder.append("/");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        GlideApp.with(GaanaApplication.getContext().getApplicationContext()).asBitmap().load(str).apply(fVar).into((com.bumptech.glide.request.a.i) new g<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(Bitmap bitmap, d dVar) {
                if (bitmap != null && rVar != null) {
                    rVar.onSuccessfulResponse(bitmap);
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
            }
        });
    }

    public void c(final b bVar) {
        String replace = bVar.e().replace(" ", "%20");
        if (bVar.g()) {
            b(bVar);
            return;
        }
        final b bVar2 = bVar;
        Request anonymousClass11 = new f(bVar.c(), replace, bVar.d(), null, new com.android.volley.i.a() {
            public void onErrorResponse(VolleyError volleyError) {
                BusinessObject businessObject = new BusinessObject();
                businessObject.setVolleyError(volleyError);
                bVar.i().onErrorResponse(businessObject);
            }
        }, new c() {
            public void a(Object obj, boolean z) {
                bVar.i().onDataRetrieved(obj, z);
            }
        }) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                return bVar2.h();
            }
        };
        anonymousClass11.setShouldCache(false);
        anonymousClass11.setTag(bVar.f());
        anonymousClass11.a(bVar.k());
        anonymousClass11.b(bVar.l());
        anonymousClass11.b(bVar.p());
        anonymousClass11.setDataToBeRefreshedAfterCacheResponse(bVar.r());
        anonymousClass11.d(bVar.s());
        anonymousClass11.setSecureCall(bVar.u());
        anonymousClass11.c(bVar.q());
        if (bVar.n() != -1) {
            anonymousClass11.a(bVar.n());
        }
        anonymousClass11.setRetryPolicy(new a());
        j.a().a(anonymousClass11);
    }

    public void a(s sVar, URLManager uRLManager) {
        a(sVar, uRLManager, Boolean.valueOf(true));
    }

    public void a(final s sVar, final URLManager uRLManager, Boolean bool) {
        if (uRLManager != null) {
            String str = "https://api.gaana.com/index.php?";
            if (!TextUtils.isEmpty(uRLManager.k())) {
                str = uRLManager.k();
            } else if (uRLManager.g() == 1) {
                str = "https://api.gaana.com/user.php?";
            }
            Class a = o.a().a(uRLManager.i());
            if (uRLManager.j() != null) {
                a = uRLManager.j();
            }
            b bVar = new b(str, a, new com.i.e.a() {
                public void onDataRetrieved(Object obj, boolean z) {
                    BusinessObject businessObject;
                    if (obj instanceof BusinessObject) {
                        businessObject = (BusinessObject) obj;
                        if (businessObject.getVolleyError() == null) {
                            businessObject.setFromNetwork(z);
                            if (uRLManager.a()) {
                                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                            } else {
                                businessObject.setBusinessObjType(uRLManager.i());
                            }
                            GaanaApplication gaanaApplication = (GaanaApplication) GaanaApplication.getContext();
                            if (businessObject.getArrListBusinessObj() != null) {
                                Iterator it = businessObject.getArrListBusinessObj().iterator();
                                while (it.hasNext()) {
                                    Object next = it.next();
                                    if (uRLManager.a()) {
                                        ((BusinessObject) next).setBusinessObjType(BusinessObjectType.Tracks);
                                    } else {
                                        ((BusinessObject) next).setBusinessObjType(uRLManager.i());
                                    }
                                    if (uRLManager.l() != null) {
                                        ((BusinessObject) next).setParentBusinessObjType(uRLManager.l());
                                    }
                                }
                            }
                            uRLManager.c(Boolean.valueOf(false));
                            businessObject.setUrlManager(uRLManager);
                            sVar.onRetreivalComplete(businessObject);
                        }
                    }
                    businessObject = null;
                    sVar.onRetreivalComplete(businessObject);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    sVar.onErrorResponse(businessObject);
                }
            });
            bVar.a(uRLManager.A());
            bVar.a(uRLManager.h());
            bVar.c(uRLManager.t());
            bVar.a(uRLManager.f().booleanValue());
            boolean z = uRLManager.m().booleanValue() || Constants.ee;
            bVar.a(Boolean.valueOf(z));
            bVar.f(uRLManager.B());
            bVar.h(uRLManager.b());
            bVar.g(uRLManager.D());
            Constants.ee = false;
            bVar.d(uRLManager.d());
            bVar.a(uRLManager.v());
            bVar.b(uRLManager.a());
            bVar.c(uRLManager.w());
            bVar.e(uRLManager.y());
            bVar.d(uRLManager.x());
            bVar.b(uRLManager.C());
            if (uRLManager.t() == 1) {
                c(bVar);
            } else {
                a(bVar);
            }
        }
    }

    public void a(final af afVar, URLManager uRLManager) {
        if (uRLManager != null) {
            String str = "https://api.gaana.com/index.php?";
            if (!TextUtils.isEmpty(uRLManager.k())) {
                str = uRLManager.k();
            } else if (uRLManager.g() == 1) {
                str = "https://api.gaana.com/user.php?";
            }
            Class a = o.a().a(uRLManager.i());
            if (uRLManager.j() != null) {
                a = uRLManager.j();
            }
            b bVar = new b(str, a, new com.i.e.a() {
                public void onDataRetrieved(Object obj, boolean z) {
                    afVar.onRetreivalComplete(obj);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    afVar.onErrorResponse(businessObject);
                }
            });
            bVar.a(uRLManager.h());
            bVar.c(uRLManager.t());
            bVar.a(uRLManager.f().booleanValue());
            bVar.a(uRLManager.u());
            boolean z = uRLManager.m().booleanValue() || Constants.ee;
            bVar.a(Boolean.valueOf(z));
            Constants.ee = false;
            bVar.c(uRLManager.w());
            bVar.f(uRLManager.B());
            bVar.h(uRLManager.b());
            bVar.g(uRLManager.D());
            bVar.e(uRLManager.y());
            bVar.d(uRLManager.x());
            bVar.b(uRLManager.C());
            if (uRLManager.t() == 1) {
                c(bVar);
            } else {
                a(bVar);
            }
        }
    }

    public String a(String str, HashMap<String, String> hashMap) {
        Object[] toArray = hashMap.keySet().toArray();
        for (int i = 0; i < hashMap.size(); i++) {
            String str2 = (String) hashMap.get(toArray[i].toString());
            if (str2 != null) {
                StringBuilder stringBuilder;
                if (i == hashMap.size() - 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(toArray[i]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str2));
                    str = stringBuilder.toString();
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(toArray[i]);
                    stringBuilder.append("=");
                    stringBuilder.append(URLEncoder.encode(str2));
                    stringBuilder.append("&");
                    str = stringBuilder.toString();
                }
            }
        }
        return str;
    }

    public void a(s sVar, URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        if (uRLManager.i() == BusinessObjectType.Playlists) {
            new ae().a(uRLManager, str, i, i2, str2, str3, sVar);
        } else if (uRLManager.i() == BusinessObjectType.Artists || uRLManager.i() == BusinessObjectType.Radios) {
            new m().a(uRLManager, str, i, i2, str2, str3, sVar);
        } else {
            new z().a(uRLManager, str, i, i2, str2, str3, sVar);
        }
    }

    public void b(s sVar, URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        final s sVar2 = sVar;
        final URLManager uRLManager2 = uRLManager;
        final String str4 = str;
        final int i3 = i;
        final int i4 = i2;
        final String str5 = str2;
        final String str6 = str3;
        h.a().a(new TaskListner() {
            s a = sVar2;
            BusinessObject b = null;
            boolean c = false;

            public void onBackGroundTaskCompleted() {
                try {
                    if (this.a != null && !this.c) {
                        this.a.onRetreivalComplete(this.b);
                    }
                } catch (Exception unused) {
                }
            }

            public void doBackGroundTask() {
                try {
                    this.b = com.e.a.c.a().a(uRLManager2.i(), str4, i3, i4, str5, str6);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }, -1);
    }

    public void a(String str, CrossFadeImageView crossFadeImageView, LocalMediaImageLoader localMediaImageLoader) {
        final a aVar = new a();
        final LocalMediaImageLoader localMediaImageLoader2 = localMediaImageLoader;
        final String str2 = str;
        final CrossFadeImageView crossFadeImageView2 = crossFadeImageView;
        h.a().a(new TaskListner() {
            public void doBackGroundTask() {
                Bitmap bitmapFromDisk = localMediaImageLoader2 != null ? localMediaImageLoader2.getBitmapFromDisk(str2, crossFadeImageView2) : (str2.startsWith("http://") || str2.startsWith("https://")) ? null : i.a(i.a(str2, crossFadeImageView2.getContext()));
                aVar.a(bitmapFromDisk);
            }

            public void onBackGroundTaskCompleted() {
                if (aVar.a() != null) {
                    crossFadeImageView2.setBitmapToImageView(aVar.a(), Boolean.valueOf(true));
                }
            }
        }, -1, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARNING: Missing block: B:10:0x0035, code skipped:
            if (r7 != null) goto L_0x0045;
     */
    /* JADX WARNING: Missing block: B:19:0x0043, code skipped:
            if (r7 != null) goto L_0x0045;
     */
    /* JADX WARNING: Missing block: B:21:0x0045, code skipped:
            r7.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0048, code skipped:
            return null;
     */
    public static java.lang.String a(java.lang.String r7, android.content.Context r8) {
        /*
        r0 = 0;
        r1 = r8.getContentResolver();	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r2 = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8 = 1;
        r3 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r4 = "album_art";
        r5 = 0;
        r3[r5] = r4;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r4 = "_id=?";
        r8 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8[r5] = r7;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r6 = 0;
        r5 = r8;
        r7 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8 = r7.moveToFirst();	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        if (r8 == 0) goto L_0x0035;
    L_0x0025:
        r8 = "album_art";
        r8 = r7.getColumnIndex(r8);	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        r8 = r7.getString(r8);	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        if (r7 == 0) goto L_0x0034;
    L_0x0031:
        r7.close();
    L_0x0034:
        return r8;
    L_0x0035:
        if (r7 == 0) goto L_0x0048;
    L_0x0037:
        goto L_0x0045;
    L_0x0038:
        r8 = move-exception;
        r0 = r7;
        goto L_0x003c;
    L_0x003b:
        r8 = move-exception;
    L_0x003c:
        if (r0 == 0) goto L_0x0041;
    L_0x003e:
        r0.close();
    L_0x0041:
        throw r8;
    L_0x0042:
        r7 = r0;
    L_0x0043:
        if (r7 == 0) goto L_0x0048;
    L_0x0045:
        r7.close();
    L_0x0048:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.i.i.a(java.lang.String, android.content.Context):java.lang.String");
    }

    public static Bitmap a(String str) {
        try {
            if (!new File(str).exists()) {
                return null;
            }
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Fetching failed from Disc.Url is ");
                stringBuilder.append(str);
                Log.w("FeedManager", stringBuilder.toString());
            }
            return decodeFile;
        } catch (OutOfMemoryError unused) {
            return null;
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("EXCEPTION:Error : ");
            stringBuilder2.append(e.getMessage());
            Log.w("FeedManager", stringBuilder2.toString());
            return null;
        }
    }

    public void a(URLManager uRLManager, String str, b<Object> bVar, com.android.volley.i.a aVar) {
        a(uRLManager, str, bVar, aVar, null);
    }

    public void a(URLManager uRLManager, String str, b<Object> bVar, com.android.volley.i.a aVar, c cVar) {
        if (uRLManager != null) {
            Class a = o.a().a(uRLManager.i());
            if (uRLManager.j() != null) {
                a = uRLManager.j();
            }
            Class cls = a;
            String a2 = a(uRLManager);
            Request cVar2 = new c(uRLManager.t(), a2, cls, bVar, aVar, cVar);
            cVar2.a(uRLManager);
            cVar2.setShouldCache(uRLManager.f().booleanValue());
            cVar2.b(uRLManager.w());
            cVar2.c(uRLManager.x());
            cVar2.setDataToBeRefreshedAfterCacheResponse(uRLManager.y());
            cVar2.setTag(str);
            cVar2.a(uRLManager.u());
            cVar2.b(str);
            cVar2.d(uRLManager.B());
            cVar2.setSecureCall(uRLManager.b());
            cVar2.a(uRLManager.a());
            if (uRLManager.d() != -1) {
                cVar2.a(uRLManager.d());
            }
            cVar2.setRetryPolicy(new a(uRLManager.v()));
            cVar2.a(a2);
            cVar2.setIsToBeRefreshed(uRLManager.m().booleanValue());
            j.a().a(cVar2);
        }
    }

    private String a(URLManager uRLManager) {
        String str = "https://api.gaana.com/index.php?";
        if (!TextUtils.isEmpty(uRLManager.k())) {
            str = uRLManager.k();
        } else if (uRLManager.g() == 1) {
            str = "https://api.gaana.com/user.php?";
        }
        if (uRLManager.t() == 0) {
            HashMap h = uRLManager.h();
            if (h != null && h.size() > 0) {
                Object[] toArray = h.keySet().toArray();
                for (int i = 0; i < h.size(); i++) {
                    String str2 = (String) h.get(toArray[i].toString());
                    if (str2 != null) {
                        StringBuilder stringBuilder;
                        if (i == h.size() - 1) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(toArray[i]);
                            stringBuilder.append("=");
                            stringBuilder.append(URLEncoder.encode(str2));
                            str = stringBuilder.toString();
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(toArray[i]);
                            stringBuilder.append("=");
                            stringBuilder.append(URLEncoder.encode(str2));
                            stringBuilder.append("&");
                            str = stringBuilder.toString();
                        }
                    }
                }
            }
        }
        return str.replace(" ", "%20");
    }
}

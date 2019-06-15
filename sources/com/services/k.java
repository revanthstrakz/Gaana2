package com.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.fragments.ProfileFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.User.UserData;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.managers.aj;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.protocol.BasicHttpContext;
import org.json.JSONException;
import org.json.JSONObject;

public class k extends AsyncTask<Void, Void, String> {
    private String a;
    private Bitmap b;
    private Context c;
    private String d;
    private String e;
    private String f;
    private String g;
    private boolean h = false;
    private a i;

    public interface a {
        void a(String str, String str2);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: b */
    public void onProgressUpdate(Void... voidArr) {
    }

    public k(Context context, String str, Bitmap bitmap) {
        this.a = str;
        this.b = bitmap;
        this.c = context;
    }

    public k(Context context, String str, Bitmap bitmap, String str2, String str3, String str4, String str5, boolean z, a aVar) {
        this.a = str;
        this.b = bitmap;
        this.c = context;
        this.f = str2;
        this.g = str3;
        this.d = str4;
        this.e = str5;
        this.h = z;
        this.i = aVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public String doInBackground(Void... voidArr) {
        try {
            Boolean valueOf;
            StringBuilder stringBuilder;
            if (this.h) {
                GaanaActivity gaanaActivity = (GaanaActivity) this.c;
                valueOf = Boolean.valueOf(false);
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.c.getString(R.string.uploading_pic));
                stringBuilder.append("\t\t\t\t\t");
                gaanaActivity.showProgressDialog(valueOf, stringBuilder.toString());
            } else {
                BaseActivity baseActivity = (BaseActivity) this.c;
                valueOf = Boolean.valueOf(false);
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.c.getString(R.string.uploading_pic));
                stringBuilder.append("\t\t\t\t\t");
                baseActivity.showProgressDialog(valueOf, stringBuilder.toString());
            }
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            BasicHttpContext basicHttpContext = new BasicHttpContext();
            String authToken = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser().getAuthToken();
            AbstractHttpMessage httpPost = new HttpPost(this.a);
            new j().a(httpPost);
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.b.compress(CompressFormat.JPEG, 75, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            int length = toByteArray.length;
            if (this.h) {
                multipartEntity.addPart("type", new StringBody("verify_eligibility"));
                multipartEntity.addPart("enrollment_id", new StringBody(this.d));
                multipartEntity.addPart(LoginManager.TAG_DOB, new StringBody(this.e));
                multipartEntity.addPart(LoginManager.TAG_FULL_NAME, new StringBody(this.f));
                multipartEntity.addPart("email", new StringBody(this.g));
                multipartEntity.addPart("card_img", new ByteArrayBody(toByteArray, "student_image.jpg"));
            } else {
                multipartEntity.addPart("file_upload", new ByteArrayBody(toByteArray, "profile_image.jpg"));
            }
            multipartEntity.addPart(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, new StringBody(authToken));
            httpPost.setEntity(multipartEntity);
            return a(defaultHttpClient.execute(httpPost, basicHttpContext));
        } catch (Exception unused) {
            if (this.c != null) {
                if (this.h) {
                    ((GaanaActivity) this.c).hideProgressDialog();
                } else {
                    ((BaseActivity) this.c).hideProgressDialog();
                }
            }
            return null;
        }
    }

    public String a(HttpResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            StringBuilder stringBuilder2;
            InputStream content = httpResponse.getEntity().getContent();
            Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
            if (firstHeader != null && firstHeader.getValue().equalsIgnoreCase("gzip")) {
                InputStream gZIPInputStream = new GZIPInputStream(content);
                if (Constants.b) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" GZip Response Length: ");
                    stringBuilder2.append(httpResponse.getEntity().getContentLength());
                    stringBuilder2.append("");
                    Log.i("HttpManager", stringBuilder2.toString());
                }
                content = gZIPInputStream;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(readLine);
                    stringBuilder2.append("\n");
                    stringBuilder.append(stringBuilder2.toString());
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                        return null;
                    }
                }
            }
            content.close();
            if (TextUtils.isEmpty(stringBuilder)) {
                Log.v("Test", " ======= Response is null");
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(" ======= ");
                stringBuilder3.append(stringBuilder.toString());
                Log.v("Test", stringBuilder3.toString());
            }
            return stringBuilder.toString();
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (this.h) {
            ((GaanaActivity) this.c).hideProgressDialog();
            b(str);
            return;
        }
        ((BaseActivity) this.c).hideProgressDialog();
        if (!c(str).booleanValue()) {
            aj.a().a(this.c, this.c.getString(R.string.error_uploading));
        } else if (this.c instanceof GaanaActivity) {
            ((GaanaActivity) this.c).updateSidebarUserDetails();
            if (((GaanaActivity) this.c).getCurrentFragment() instanceof ProfileFragment) {
                ((GaanaActivity) this.c).getCurrentFragment().refreshListView();
            }
        }
    }

    private void b(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("status") && jSONObject.has("message")) {
                    this.i.a(jSONObject.getString("status"), jSONObject.getString("message"));
                }
                if (jSONObject.has(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE)) {
                    aj.a().a(this.c, jSONObject.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE));
                }
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private Boolean c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("Status") && jSONObject.has("data")) {
                    str = jSONObject.getString("Status");
                    UserData userData = (UserData) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(jSONObject.getString("data"), UserData.class);
                    if (userData == null || !"1".equalsIgnoreCase(str)) {
                        aj.a().a((BaseActivity) this.c, this.c.getString(R.string.error_updating));
                    } else {
                        str = userData.getArtwork();
                        if (!TextUtils.isEmpty(str)) {
                            ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser().getUserProfile().setImg(str);
                            LoginManager.getInstance().saveUserInfoInSharedPreff();
                        }
                        return Boolean.valueOf(true);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return Boolean.valueOf(false);
    }
}

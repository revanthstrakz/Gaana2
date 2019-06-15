package com.login.nativesso.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.ad;
import com.login.nativesso.e.e;
import com.login.nativesso.i.b;
import com.login.nativesso.i.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

public class UploadProfilePicActivity extends Activity {
    boolean a;
    private ProgressDialog b;
    private final int c = 104;
    private final int d = 1;
    private final int e = 2;
    private File f;
    private int g;
    private String h;
    private String i;

    class a extends AsyncTask<Void, Void, String> {
        File a;

        a(File file) {
            this.a = file;
            UploadProfilePicActivity.this.a((Context) UploadProfilePicActivity.this);
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public String doInBackground(Void... voidArr) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("===");
                stringBuilder.append(System.currentTimeMillis());
                stringBuilder.append("===");
                String stringBuilder2 = stringBuilder.toString();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(b.f).openConnection();
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                String str = MIME.CONTENT_TYPE;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("multipart/form-data; boundary=");
                stringBuilder3.append(stringBuilder2);
                httpURLConnection.setRequestProperty(str, stringBuilder3.toString());
                com.login.nativesso.g.b a = com.login.nativesso.g.b.a();
                httpURLConnection.setRequestProperty("channel", a.a("channel", UploadProfilePicActivity.this));
                httpURLConnection.setRequestProperty("ssec", a.b(UploadProfilePicActivity.this));
                httpURLConnection.setRequestProperty("ticketId", a.a("TICKETID", UploadProfilePicActivity.this));
                httpURLConnection.setRequestProperty("tgid", a.a("TGID", UploadProfilePicActivity.this));
                OutputStream outputStream = httpURLConnection.getOutputStream();
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
                a(printWriter, "datafile", this.a, stringBuilder2, outputStream);
                return a(printWriter, stringBuilder2, httpURLConnection);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return "";
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            if (c.a(str)) {
                UploadProfilePicActivity.this.e();
            } else {
                ad adVar = (ad) com.login.nativesso.b.a.a("UpdateUserProfilePicCb");
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getString("code").equals("200")) {
                        if (adVar != null) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                            if (jSONObject2 != null) {
                                str = jSONObject2.getString("picUrl");
                                adVar.a(str);
                                Context d = com.login.nativesso.d.c.a().d();
                                e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                                eVar.a(str);
                                com.login.nativesso.g.b.a();
                                com.login.nativesso.g.b.a(d, eVar);
                                com.login.nativesso.b.a.b("UpdateUserProfilePicCb");
                                a();
                                UploadProfilePicActivity.this.g = -1;
                            }
                        }
                    } else if (adVar != null) {
                        adVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                        com.login.nativesso.b.a.b("UpdateUserProfilePicCb");
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    if (adVar != null) {
                        adVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                        com.login.nativesso.b.a.b("UpdateUserProfilePicCb");
                    }
                }
            }
            UploadProfilePicActivity.this.e();
            UploadProfilePicActivity.this.b(UploadProfilePicActivity.this);
            UploadProfilePicActivity.this.finish();
        }

        public void a(PrintWriter printWriter, String str, File file, String str2, OutputStream outputStream) throws IOException {
            String name = file.getName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");
            stringBuilder.append(str2);
            printWriter.append(stringBuilder.toString()).append("\r\n");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Content-Disposition: form-data; name=\"");
            stringBuilder2.append(str);
            stringBuilder2.append("\"; filename=\"");
            stringBuilder2.append(name);
            stringBuilder2.append("\"");
            printWriter.append(stringBuilder2.toString()).append("\r\n");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Content-Type: ");
            stringBuilder3.append(URLConnection.guessContentTypeFromName(name));
            printWriter.append(stringBuilder3.toString()).append("\r\n");
            printWriter.append("Content-Transfer-Encoding: binary").append("\r\n");
            printWriter.append("\r\n");
            printWriter.flush();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    fileInputStream.close();
                    printWriter.append("\r\n");
                    printWriter.flush();
                    return;
                }
            }
        }

        public String a(PrintWriter printWriter, String str, HttpURLConnection httpURLConnection) throws IOException {
            StringBuffer stringBuffer = new StringBuffer("");
            printWriter.append("\r\n").flush();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");
            stringBuilder.append(str);
            stringBuilder.append("--");
            printWriter.append(stringBuilder.toString()).append("\r\n");
            printWriter.close();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
                try {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(stringBuffer);
                    stringBuilder2.append("");
                    JSONObject jSONObject = new JSONObject(stringBuilder2.toString());
                    if (jSONObject.has("code") && !jSONObject.getString("code").equalsIgnoreCase("200")) {
                        Toast.makeText(UploadProfilePicActivity.this.getBaseContext(), "Image size is very big", 1).show();
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    UploadProfilePicActivity.this.e();
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuffer);
                stringBuilder3.append("");
                return stringBuilder3.toString();
            }
            Toast.makeText(UploadProfilePicActivity.this.getBaseContext(), "Image size is very big", 1).show();
            UploadProfilePicActivity.this.e();
            return stringBuffer.toString();
        }

        private void a() {
            if (UploadProfilePicActivity.this.f != null && UploadProfilePicActivity.this.f.canWrite()) {
                UploadProfilePicActivity.this.f.delete();
                UploadProfilePicActivity.this.b(UploadProfilePicActivity.this.f);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.login.nativesso.a.b.activity_upload_profile_pic);
        this.a = getIntent().getExtras().getBoolean("BY_CUSTOM_DIALOG");
        if (this.a) {
            this.i = getIntent().getExtras().getString("GALLERY_CAMERA");
            if (VERSION.SDK_INT <= 22) {
                a(this.i);
            } else if (f()) {
                a(this.i);
            } else {
                a();
            }
        } else if (VERSION.SDK_INT <= 22) {
            a((Activity) this);
        } else if (f()) {
            a((Activity) this);
        } else {
            a();
        }
    }

    public void a(String str) {
        if (str.equalsIgnoreCase("camera")) {
            a(null);
        } else {
            a(null, (Activity) this);
        }
    }

    public void a(final Activity activity) {
        final AlertDialog create = new Builder(activity).create();
        LayoutInflater from = LayoutInflater.from(activity);
        View inflate = from.inflate(com.login.nativesso.a.b.alert_name, null);
        create.setView(inflate);
        TextView textView = (TextView) inflate.findViewById(com.login.nativesso.a.a.tvTitle);
        textView.setText("UPLOAD PROFILE PICTURE");
        textView.setGravity(17);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        create.setCustomTitle(textView);
        View inflate2 = from.inflate(com.login.nativesso.a.b.custom_list, null);
        create.setView(inflate2);
        ListView listView = (ListView) inflate2.findViewById(com.login.nativesso.a.a.lvEmail);
        listView.setAdapter(new ArrayAdapter(activity, 17367043, new String[]{"Photo Gallery", "Camera"}));
        create.show();
        create.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                activity.finish();
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != 1) {
                    UploadProfilePicActivity.this.a(create, activity);
                } else if (UploadProfilePicActivity.this.getPackageManager().hasSystemFeature("android.hardware.camera")) {
                    UploadProfilePicActivity.this.a(create);
                } else {
                    UploadProfilePicActivity.this.e();
                }
            }
        });
    }

    private void a(AlertDialog alertDialog, Activity activity) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        this.g = 2;
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                intent.putExtra("output", b());
                activity.startActivityForResult(intent, 1);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                e();
                return;
            }
        }
        e();
    }

    private Uri b() throws IOException {
        return Uri.fromFile(c());
    }

    private File c() throws IOException {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "temporary_holder.jpg");
        file.createNewFile();
        return file;
    }

    private void a(AlertDialog alertDialog) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                this.f = d();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                e();
            }
            Context d = com.login.nativesso.d.c.a().d();
            if (this.f != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(d.getPackageName());
                stringBuilder.append(".provider");
                Uri uriForFile = FileProvider.getUriForFile(this, stringBuilder.toString(), this.f);
                intent.putExtra("output", uriForFile);
                List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 65536);
                if (queryIntentActivities != null) {
                    for (ResolveInfo resolveInfo : queryIntentActivities) {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo != null) {
                            grantUriPermission(activityInfo.packageName, uriForFile, 3);
                        }
                    }
                }
                startActivityForResult(intent, 202);
                this.g = 1;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                return;
            }
        }
        e();
    }

    private File d() throws IOException {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JPEG_");
        stringBuilder.append(format);
        stringBuilder.append("_");
        File createTempFile = File.createTempFile(stringBuilder.toString(), ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        this.h = createTempFile.getAbsolutePath();
        return createTempFile;
    }

    private void e() {
        ad adVar = (ad) com.login.nativesso.b.a.a("UpdateUserProfilePicCb");
        if (adVar != null) {
            adVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            com.login.nativesso.b.a.b("UpdateUserProfilePicCb");
        }
    }

    private boolean f() {
        return ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public void a() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 104);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 104) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            ad adVar = (ad) com.login.nativesso.b.a.a("UpdateUserProfilePicCb");
            if (adVar != null) {
                adVar.a(c.a(4010, "MEDIA_PERMISSION_DENIED"));
                com.login.nativesso.b.a.b("UpdateUserProfilePicCb");
            }
            finish();
        } else if (this.a) {
            a(this.i);
        } else {
            a((Activity) this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 202) {
            if (i2 != -1 || this.f == null) {
                g();
            } else {
                h();
            }
        } else if (i == 1) {
            if (i2 == -1) {
                String a = a(intent.getData());
                if (a == null) {
                    g();
                    return;
                }
                this.f = a(new File(a));
                if (this.f != null) {
                    new a(this.f).execute(new Void[0]);
                } else {
                    g();
                }
            } else {
                g();
            }
        }
    }

    private void g() {
        e();
        finish();
    }

    private String a(Uri uri) {
        Cursor query = getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            return uri.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }

    private void h() {
        this.f = a(this.f);
        if (this.f != null) {
            new a(this.f).execute(new Void[0]);
        } else {
            g();
        }
    }

    public File a(File file) {
        try {
            if (this.g == 2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory());
                stringBuilder.append("/tmp01245");
                File file2 = new File(stringBuilder.toString());
                a(file, file2);
                file = file2;
            }
            Options options = new Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            options.inSampleSize = 6;
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            while ((options.outWidth / i) / 2 >= 75 && (options.outHeight / i) / 2 >= 150) {
                i *= 2;
            }
            options = new Options();
            options.inSampleSize = i;
            FileInputStream fileInputStream2 = new FileInputStream(file);
            Bitmap a = a(file, BitmapFactory.decodeStream(fileInputStream2, null, options));
            fileInputStream2.close();
            file.createNewFile();
            a.compress(CompressFormat.JPEG, 80, new FileOutputStream(file));
            return file;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private Bitmap a(File file, Bitmap bitmap) throws IOException {
        int attributeInt = new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 0);
        if (attributeInt == 3) {
            return a(bitmap, 180.0f);
        }
        if (attributeInt == 6) {
            return a(bitmap, 90.0f);
        }
        if (attributeInt != 8) {
            return bitmap;
        }
        return a(bitmap, 270.0f);
    }

    public Bitmap a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void a(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = new ProgressDialog(context);
            this.b.setCancelable(true);
            this.b.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    UploadProfilePicActivity.this.g();
                }
            });
        }
        this.b.show();
    }

    public void b(Context context) {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
    }

    public void b(File file) {
        if (VERSION.SDK_INT >= 19) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("file://");
        stringBuilder.append(Environment.getExternalStorageDirectory());
        sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse(stringBuilder.toString())));
    }
}

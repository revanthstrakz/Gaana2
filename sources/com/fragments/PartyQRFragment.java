package com.fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.b.i;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.e;
import com.facebook.messenger.MessengerUtils;
import com.facebook.share.internal.ShareConstants;
import com.gaana.BaseActivity;
import com.gaana.BuildConfig;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.SaveToGalleryActivity;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.custom_glide.GlideApp;
import com.managers.aj;
import com.managers.u;
import com.services.a;
import com.utilities.d;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PartyQRFragment extends BaseGaanaFragment implements OnClickListener {
    private View a;
    private List<ResolveInfo> b;
    private String c;
    private a d;
    private String e;
    private ImageView f;
    private boolean g;
    private TextView h;
    private String i = "";
    private ProgressDialog j;

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        boolean z = false;
        this.a = layoutInflater.inflate(R.layout.fragment_party_qr, viewGroup, false);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        this.b = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
        if (getArguments() != null) {
            this.c = getArguments().getString("QR_URL");
            if (getArguments().getString(ShareConstants.ACTION) != null && getArguments().getString(ShareConstants.ACTION).equals("Share")) {
                z = true;
            }
            this.g = z;
            this.i = getArguments().getString("txt_name", this.mContext.getResources().getString(R.string.your_playlist));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.share_party_text));
            stringBuilder.append(getArguments().getString("DL_URL"));
            this.e = stringBuilder.toString();
        }
        this.d = new a(this.mContext);
        b();
        return this.a;
    }

    private void b() {
        View findViewById;
        this.a.findViewById(R.id.fragment_qr_more_share).setOnClickListener(this);
        this.a.findViewById(R.id.party_sms).setOnClickListener(this);
        this.a.findViewById(R.id.party_clipboard).setOnClickListener(this);
        this.a.findViewById(R.id.fragment_party_qr_back).setOnClickListener(this);
        this.h = (TextView) this.a.findViewById(R.id.txt_page_subheading);
        ((TextView) this.a.findViewById(R.id.txt_page_heading)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        this.h.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        this.h.setText(String.format(this.mContext.getString(R.string.to_your_pl), new Object[]{this.i}));
        if (this.g) {
            this.a.findViewById(R.id.party_header_strip).setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.c)) {
            this.j = new ProgressDialog(this.mContext);
            this.j.setTitle("Processing...");
            this.j.setCancelable(false);
            this.j.show();
            this.f = (ImageView) this.a.findViewById(R.id.fragment_party_qr_code);
            GlideApp.with(this.mContext.getApplicationContext()).load(this.c).disallowHardwareConfig().listener(new e<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, boolean z) {
                    if (PartyQRFragment.this.j != null) {
                        PartyQRFragment.this.j.dismiss();
                    }
                    ((GaanaActivity) PartyQRFragment.this.mContext).onBackPressed();
                    return false;
                }

                /* renamed from: a */
                public boolean onResourceReady(Drawable drawable, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, DataSource dataSource, boolean z) {
                    if (PartyQRFragment.this.j != null) {
                        PartyQRFragment.this.j.dismiss();
                    }
                    return false;
                }
            }).into(this.f);
        }
        if (a("com.whatsapp") != null) {
            findViewById = this.a.findViewById(R.id.party_whatsapp);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(this);
        }
        if (a(MessengerUtils.PACKAGE_NAME) != null) {
            findViewById = this.a.findViewById(R.id.party_messenger);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(this);
        }
    }

    private void c() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", this.e);
        intent.setType("text/plain");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            ResolveInfo resolveInfo = (ResolveInfo) this.b.get(i);
            String str = resolveInfo.activityInfo.packageName;
            arrayList.add(new LabeledIntent(a(str, resolveInfo.activityInfo.name, null), str, resolveInfo.loadLabel(this.mContext.getPackageManager()), resolveInfo.getIconResource()));
        }
        Intent createChooser = Intent.createChooser((Intent) arrayList.remove(0), "Share");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
        ((GaanaActivity) this.mContext).startActivity(createChooser);
    }

    private Object[] d() {
        Uri uriForFile;
        Bitmap e = e();
        File a = a(e);
        e.recycle();
        if (d.f()) {
            Context context = this.mContext;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getApplicationContext().getPackageName());
            stringBuilder.append(".com.gaana.provider");
            uriForFile = FileProvider.getUriForFile(context, stringBuilder.toString(), a);
        } else {
            uriForFile = Uri.fromFile(a);
        }
        return new Object[]{uriForFile, a.getPath()};
    }

    private Intent a(String str, String str2, Uri uri) {
        String string = this.mContext.getResources().getString(R.string.share_sub_party);
        String str3 = this.e;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setPackage(str);
        intent.putExtra("android.intent.extra.SUBJECT", string);
        intent.setAction("android.intent.action.SEND");
        if (uri != null) {
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setType("image/jpeg");
        } else {
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.setType("text/plain");
        }
        return intent;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fragment_party_qr_back) {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        } else if (id == R.id.fragment_qr_more_share) {
            u.a().a("PartyHub", "Share", "Other");
            c();
        } else if (id != R.id.party_clipboard) {
            Uri uri = null;
            switch (id) {
                case R.id.party_messenger /*2131297933*/:
                    u.a().a("PartyHub", "Share", "Messenger");
                    this.mContext.startActivity(a(MessengerUtils.PACKAGE_NAME, a(MessengerUtils.PACKAGE_NAME).activityInfo.name, null));
                    return;
                case R.id.party_sms /*2131297934*/:
                    u.a().a("PartyHub", "Share", "Messages");
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.putExtra("sms_body", this.e);
                        intent.setType("vnd.android-dir/mms-sms");
                        startActivity(intent);
                        return;
                    } catch (Exception unused) {
                        aj.a().a(this.mContext, getString(R.string.not_able_to_share_via_sms));
                        return;
                    }
                case R.id.party_whatsapp /*2131297935*/:
                    u.a().a("PartyHub", "Share", "Whatsapp");
                    Context context = this.mContext;
                    String str = "com.whatsapp";
                    String str2 = a("com.whatsapp").activityInfo.name;
                    if (!TextUtils.isEmpty(this.c)) {
                        uri = (Uri) d()[0];
                    }
                    context.startActivity(a(str, str2, uri));
                    return;
                default:
                    return;
            }
        } else {
            u.a().a("PartyHub", "Share", "Other");
            ((ClipboardManager) this.mContext.getSystemService("clipboard")).setText(this.e);
            aj.a().a(this.mContext, getString(R.string.copied_to_clipboard));
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Invite", "Copy");
        }
    }

    private ResolveInfo a(String str) {
        if (this.b != null) {
            for (ResolveInfo resolveInfo : this.b) {
                if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(str)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    public static String a() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null && file.isDirectory() && file.canRead()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("/qr");
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder2.append("/qr");
        return stringBuilder2.toString();
    }

    private File a(Bitmap bitmap) {
        File file = new File(a());
        file.mkdirs();
        File file2 = new File(file, "party.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private LabeledIntent a(Context context, String str) {
        Intent intent = new Intent(context, SaveToGalleryActivity.class);
        intent.putExtra(SaveToGalleryActivity.EXTRA_KEY_CONTENT, str);
        return new LabeledIntent(intent, BuildConfig.APPLICATION_ID, this.mContext.getResources().getString(R.string.save_gallery), R.drawable.vector_icon_download);
    }

    private Bitmap e() {
        Bitmap createBitmap = Bitmap.createBitmap(this.f.getWidth(), this.f.getHeight(), Config.ARGB_8888);
        this.f.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}

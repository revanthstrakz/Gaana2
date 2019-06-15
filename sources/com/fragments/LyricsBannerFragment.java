package com.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.BuildConfig;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.SaveToGalleryActivity;
import com.gaana.adapter.ViewPagerAdapter;
import com.gaana.adapter.ViewPagerAdapter.AddItemListner;
import com.gaana.application.GaanaApplication;
import com.gaana.lrc.LrcRow;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.ScrollableViewPagerNew;
import com.gaana.view.item.SquareImageByHeight;
import com.google.android.exoplayer2.C;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.annotations.SerializedName;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.u;
import com.models.ListingButton;
import com.services.l.af;
import com.utilities.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class LyricsBannerFragment extends BaseGaanaFragment implements OnClickListener, a {
    static final Typeface[] c = new Typeface[]{Typeface.create("casual", 0), Typeface.create("cursive", 0), Typeface.create("monospace", 0), Typeface.create(C.SANS_SERIF_NAME, 0), Typeface.create("sans-serif-condensed", 0), Typeface.create("sans-serif-smallcaps", 0), Typeface.create(C.SERIF_NAME, 0), Typeface.create("serif-monospace", 0)};
    String[] a = new String[0];
    protected PlayerManager b;
    AddItemListner d = new AddItemListner() {
        public Object addItem(ViewGroup viewGroup, ListingButton listingButton) {
            return null;
        }

        public Object addItem(ViewGroup viewGroup, int i) {
            View a;
            switch (i) {
                case 0:
                    a = LyricsBannerFragment.this.b();
                    viewGroup.addView(a);
                    return a;
                case 1:
                    a = LyricsBannerFragment.this.c();
                    viewGroup.addView(a);
                    return a;
                case 2:
                    a = LyricsBannerFragment.this.d();
                    viewGroup.addView(a);
                    return a;
                default:
                    return new View(LyricsBannerFragment.this.mContext);
            }
        }
    };
    String e = "";
    Bitmap f = null;
    private View g = null;
    private TypedValue h = new TypedValue();
    private LayoutInflater i;
    private List<LrcRow> j;
    private TreeSet<LrcRow> k;
    private RelativeLayout l;
    private CrossFadeImageView m;
    private TextView n;
    private int[] o = new int[]{5, 2, 1};
    private ScrollView p;
    private int q = 6;
    private int r = 2;
    private int s = 10;
    private int t = 0;
    private TextView u;
    private Typeface v;

    class d {
        @SerializedName("cards")
        String[] a;

        public String[] a() {
            return this.a;
        }
    }

    class g implements Comparator<LrcRow> {
        g() {
        }

        /* renamed from: a */
        public int compare(LrcRow lrcRow, LrcRow lrcRow2) {
            if (lrcRow.time == lrcRow2.time) {
                return 0;
            }
            return lrcRow.time < lrcRow2.time ? -1 : 1;
        }
    }

    private class a extends Adapter {
        private a() {
        }

        /* synthetic */ a(LyricsBannerFragment lyricsBannerFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new c(LyricsBannerFragment.this.i.inflate(R.layout.lrc_line_text, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            c cVar = (c) viewHolder;
            cVar.b.setVisibility(8);
            cVar.a.setText("AABBCCDDEEFFGG");
            cVar.a.setTypeface(LyricsBannerFragment.c[i]);
            cVar.a.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LyricsBannerFragment.this.v = LyricsBannerFragment.c[i];
                    LyricsBannerFragment.this.e();
                }
            });
        }

        public int getItemCount() {
            return LyricsBannerFragment.c.length;
        }
    }

    private class b extends Adapter {
        private b() {
        }

        /* synthetic */ b(LyricsBannerFragment lyricsBannerFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new f(LyricsBannerFragment.this.i.inflate(R.layout.selector_image_view, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            if (viewHolder instanceof f) {
                f fVar = (f) viewHolder;
                LayoutParams layoutParams = fVar.b.getLayoutParams();
                int h = LyricsBannerFragment.this.t;
                layoutParams.height = h;
                layoutParams.width = h;
                if (i == 0) {
                    fVar.b.setBackgroundResource(R.drawable.rectangular_border);
                    fVar.c.setVisibility(0);
                    fVar.a.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent("android.intent.action.GET_CONTENT");
                            intent.setType("image/*");
                            new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI).setType("image/*");
                            intent = Intent.createChooser(intent, "Select Image");
                            intent.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{r0});
                            ((GaanaActivity) LyricsBannerFragment.this.mContext).startActivityForResult(intent, 706);
                        }
                    });
                    return;
                }
                fVar.b.bindImage(LyricsBannerFragment.this.a[i - 1], ScaleType.CENTER_CROP);
                fVar.c.setVisibility(8);
                fVar.a.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        LyricsBannerFragment.this.e = LyricsBannerFragment.this.a[i - 1];
                        LyricsBannerFragment.this.e();
                    }
                });
            }
        }

        public int getItemCount() {
            return LyricsBannerFragment.this.a.length + 1;
        }
    }

    private static class c extends ViewHolder {
        private TextView a;
        private CheckBox b;

        public c(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.lrcText);
            this.b = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }

    private class e extends Adapter {
        private e() {
        }

        /* synthetic */ e(LyricsBannerFragment lyricsBannerFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new c(LyricsBannerFragment.this.i.inflate(R.layout.lrc_line_text, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            if (viewHolder instanceof c) {
                final c cVar = (c) viewHolder;
                cVar.a.setText(((LrcRow) LyricsBannerFragment.this.j.get(i)).content);
                cVar.a.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (cVar.b.isChecked()) {
                            cVar.b.setChecked(false);
                        } else {
                            cVar.b.setChecked(true);
                        }
                    }
                });
                cVar.b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        if (!z) {
                            LyricsBannerFragment.this.k.remove(LyricsBannerFragment.this.j.get(i));
                            cVar.a.setTypeface(null, 0);
                        } else if (LyricsBannerFragment.this.k.size() <= 4) {
                            LyricsBannerFragment.this.k.add(LyricsBannerFragment.this.j.get(i));
                            cVar.a.setTypeface(null, 1);
                        } else {
                            cVar.b.setChecked(false);
                            cVar.a.setTypeface(null, 0);
                            aj.a().a(LyricsBannerFragment.this.mContext, LyricsBannerFragment.this.mContext.getResources().getString(R.string.more_rows_not_allowed));
                        }
                        LyricsBannerFragment.this.e();
                    }
                });
                if (LyricsBannerFragment.this.k.remove(LyricsBannerFragment.this.j.get(i))) {
                    cVar.b.setChecked(true);
                }
            }
        }

        public int getItemCount() {
            if (LyricsBannerFragment.this.j == null) {
                return 0;
            }
            return LyricsBannerFragment.this.j.size();
        }
    }

    private static class f extends ViewHolder {
        private ConstraintLayout a;
        private SquareImageByHeight b;
        private ImageView c;

        public f(View view) {
            super(view);
            this.a = (ConstraintLayout) view.findViewById(R.id.container);
            this.b = (SquareImageByHeight) view.findViewById(R.id.background_img);
            this.c = (ImageView) view.findViewById(R.id.plus_icon);
        }
    }

    public void setGAScreenName(String str, String str2) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button /*2131296488*/:
                ((GaanaActivity) this.mContext).onBackPressed();
                return;
            case R.id.center_align_btn /*2131296656*/:
                this.n.setGravity(17);
                return;
            case R.id.font_minus /*2131297175*/:
                if (this.q > this.r) {
                    this.q--;
                    this.u.setText(String.format("%2d", new Object[]{Integer.valueOf(this.q)}));
                    this.n.setTextSize((float) Util.b(this.q));
                    return;
                }
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.font_min_alert));
                return;
            case R.id.font_plus /*2131297176*/:
                if (this.q < this.s) {
                    this.q++;
                    this.u.setText(String.format("%2d", new Object[]{Integer.valueOf(this.q)}));
                    this.n.setTextSize((float) Util.b(this.q));
                    return;
                }
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.font_max_alert));
                return;
            case R.id.left_align_btn /*2131297547*/:
                this.n.setGravity(19);
                return;
            case R.id.right_align_btn /*2131298249*/:
                this.n.setGravity(21);
                return;
            case R.id.share_lrc_card /*2131298393*/:
                u.a().a("Lyrics", "Lyrics Banner", "Share");
                a(this.mContext, f());
                return;
            default:
                return;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.g == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.i = layoutInflater;
            this.g = setContentView(R.layout.lyrics_banner_fragment, viewGroup);
            Bundle arguments = getArguments();
            this.k = new TreeSet(new g());
            this.b = PlayerManager.a(this.mContext);
            a(arguments);
        }
        return this.g;
    }

    private void a(Bundle bundle) {
        this.j = this.b.o();
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, this.h, true);
        this.p = (ScrollView) this.g.findViewById(R.id.parentscrollview);
        this.l = (RelativeLayout) this.g.findViewById(R.id.top_layout);
        this.n = (TextView) this.g.findViewById(R.id.display_text);
        TextView textView = (TextView) this.g.findViewById(R.id.track_details);
        Track b = this.b.i().b();
        if (b != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b.getTrackTitle());
            stringBuilder.append(" - ");
            stringBuilder.append(b.getAlbumTitle());
            textView.setText(stringBuilder.toString());
        }
        this.m = (CrossFadeImageView) this.g.findViewById(R.id.display_img);
        this.g.findViewById(R.id.back_button).setOnClickListener(this);
        this.g.findViewById(R.id.share_lrc_card).setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) this.g.findViewById(R.id.viewpager_container);
        TabLayout tabLayout = (TabLayout) this.g.findViewById(R.id.sliding_tabs);
        ScrollableViewPagerNew scrollableViewPagerNew = (ScrollableViewPagerNew) this.g.findViewById(R.id.viewpager);
        scrollableViewPagerNew.setHorizontalScrollEnabled(false);
        tabLayout.setTabTextColors(this.h.data, this.h.data);
        tabLayout.setTabGravity(0);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        this.p.setFocusableInTouchMode(true);
        this.p.setDescendantFocusability(131072);
        viewPagerAdapter.setAdapterParams(3, this.d, null);
        scrollableViewPagerNew.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(scrollableViewPagerNew);
        a(tabLayout);
        linearLayout.setVisibility(0);
    }

    private void a(TabLayout tabLayout) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.lrc_vector_icons);
        for (int i = 0; i < 3; i++) {
            tabLayout.getTabAt(i).setIcon(obtainStyledAttributes.getResourceId(this.o[i], -1));
        }
        obtainStyledAttributes.recycle();
    }

    private View b() {
        RecyclerView recyclerView = new RecyclerView(this.mContext);
        e eVar = new e(this, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(eVar);
        return recyclerView;
    }

    private View c() {
        RecyclerView recyclerView = new RecyclerView(this.mContext);
        recyclerView.setLayoutParams(new LayoutParams(-1, -2));
        this.t = (com.services.d.a().b() - Util.b(60)) / 3;
        final b bVar = new b(this, null);
        recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/lyrics/cards");
        uRLManager.a(d.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof d) {
                    LyricsBannerFragment.this.a = ((d) obj).a();
                    bVar.notifyDataSetChanged();
                }
            }
        }, uRLManager);
        recyclerView.setAdapter(bVar);
        return recyclerView;
    }

    public void a(int i, int i2, Intent intent) {
        if (i2 == -1) {
            try {
                this.f = Constants.a(this.mContext, intent.getData());
                this.e = "MEDIA_ARTWORK";
                e();
                return;
            } catch (FileNotFoundException e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        this.f = null;
    }

    private View d() {
        View inflate = this.i.inflate(R.layout.lyrics_font_settings_view, null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.font_styles);
        a aVar = new a(this, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(aVar);
        inflate.findViewById(R.id.left_align_btn).setOnClickListener(this);
        inflate.findViewById(R.id.center_align_btn).setOnClickListener(this);
        inflate.findViewById(R.id.right_align_btn).setOnClickListener(this);
        inflate.findViewById(R.id.font_minus).setOnClickListener(this);
        inflate.findViewById(R.id.font_plus).setOnClickListener(this);
        this.u = (TextView) inflate.findViewById(R.id.font_size_text);
        this.u.setText(String.format("%2d", new Object[]{Integer.valueOf(this.q)}));
        return inflate;
    }

    private void e() {
        if (this.e.equals("MEDIA_ARTWORK")) {
            this.m.setBitmapToImageView(this.f);
        } else {
            this.m.bindImage(this.e, ScaleType.CENTER_CROP);
        }
        String str = "";
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            LrcRow lrcRow = (LrcRow) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(lrcRow.content);
            stringBuilder.append("\n");
            str = stringBuilder.toString();
        }
        this.n.setText(str);
        this.n.setTypeface(this.v);
        this.n.setTextSize((float) Util.b(this.q));
    }

    private Bitmap f() {
        Bitmap createBitmap = Bitmap.createBitmap(this.l.getWidth(), this.l.getHeight(), Config.ARGB_8888);
        this.l.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public void a(Context context, Bitmap bitmap) {
        if (this.k == null || this.k.size() < 1) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.no_lrc_error));
        } else {
            context.startActivity(a(context, a(bitmap)));
        }
    }

    private File a(Bitmap bitmap) {
        File file = new File(a());
        file.mkdirs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append(".jpg");
        File file2 = new File(file, stringBuilder.toString());
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

    public static String a() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null && file.isDirectory() && file.canRead()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("/banner");
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder2.append("/banner");
        return stringBuilder2.toString();
    }

    public Intent a(Context context, File file) {
        Parcelable uriForFile;
        if (com.utilities.d.f()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getApplicationContext().getPackageName());
            stringBuilder.append(".com.gaana.provider");
            uriForFile = FileProvider.getUriForFile(context, stringBuilder.toString(), file);
        } else {
            uriForFile = Uri.fromFile(file);
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        intent.setType("image/jpeg");
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
            String str = resolveInfo.activityInfo.packageName;
            String string = this.mContext.getResources().getString(R.string.share_sub);
            String string2 = this.mContext.getResources().getString(R.string.share_body);
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(str, resolveInfo.activityInfo.name));
            intent2.setPackage(str);
            intent2.putExtra("android.intent.extra.SUBJECT", string);
            intent2.putExtra("android.intent.extra.TEXT", string2);
            intent2.setAction("android.intent.action.SEND");
            intent2.putExtra("android.intent.extra.STREAM", uriForFile);
            intent2.setType("image/jpeg");
            arrayList.add(new LabeledIntent(intent2, str, resolveInfo.loadLabel(packageManager), resolveInfo.getIconResource()));
        }
        arrayList.add(a(this.mContext, file.getPath()));
        Intent createChooser = Intent.createChooser((Intent) arrayList.remove(0), "Share");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
        return createChooser;
    }

    private LabeledIntent a(Context context, String str) {
        Intent intent = new Intent(context, SaveToGalleryActivity.class);
        intent.putExtra(SaveToGalleryActivity.EXTRA_KEY_CONTENT, str);
        return new LabeledIntent(intent, BuildConfig.APPLICATION_ID, this.mContext.getResources().getString(R.string.save_gallery), R.drawable.vector_icon_download);
    }
}

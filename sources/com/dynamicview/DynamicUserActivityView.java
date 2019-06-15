package com.dynamicview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.bumptech.glide.e;
import com.bumptech.glide.request.a.g;
import com.bumptech.glide.request.b.d;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.fragments.BaseGaanaFragment;
import com.fragments.GridActivityFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.GenericItemView;
import com.i.i;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.an;
import com.managers.ap;
import com.managers.aq;
import com.managers.q;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.c;
import com.services.l.o;
import com.services.l.s;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import java.util.ArrayList;
import java.util.Calendar;

public class DynamicUserActivityView extends BaseItemView implements com.managers.aq.a, o {
    String a = null;
    String b = null;
    private int c = R.layout.view_horizontal_scroll_container;
    private com.dynamicview.f.a d;
    private HorizontalRecyclerView e;
    private View f = null;
    private ViewHolder g = null;
    private OnDataLoadedListener h = null;
    private ArrayList<?> i = null;
    private String j;
    private String k;
    private BusinessObject l;
    private URLManager m = null;
    private boolean n = false;
    private boolean o = true;
    private boolean p = false;
    private boolean q;
    private boolean r = false;
    private boolean s = false;
    private long t = 0;
    private int u = 0;
    private int v = -1;
    private GenericItemView w;
    private long x = 0;
    private String y = "";
    private int z = 0;

    public static class a extends ViewHolder {
        public ConstraintLayout a;
        public TextView b;
        public TextView c;
        public HorizontalRecyclerView d;
        public ImageView e;
        public ImageView f;

        public a(View view) {
            super(view);
            this.a = (ConstraintLayout) view.findViewById(R.id.layout_horzontal_scroll_container);
            this.b = (TextView) view.findViewById(R.id.seeall);
            this.c = (TextView) view.findViewById(R.id.f55header.text);
            this.d = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
            this.d.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
            this.e = (ImageView) view.findViewById(R.id.logoImage);
            this.f = (ImageView) view.findViewById(R.id.seeallImg);
        }
    }

    public DynamicUserActivityView(Context context, BaseGaanaFragment baseGaanaFragment, com.dynamicview.f.a aVar) {
        super(context, baseGaanaFragment);
        this.d = aVar;
        this.j = this.d.k();
        this.k = this.d.u();
        this.z = (int) this.mContext.getResources().getDimension(R.dimen.home_item_paddding);
        a();
    }

    private void a() {
        this.m = new URLManager();
        this.m.a(this.d.l());
        this.m.l(this.d.z());
        this.m.a(BusinessObjectType.GenericItems);
        this.m.a(UserRecentActivity.class);
    }

    private URLManager getSeeAllUrlManager() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.d.o());
        if (this.v != -1 && uRLManager.k().contains("<entity_Parent_Id>")) {
            uRLManager.a(uRLManager.k().replace("<entity_Parent_Id>", String.valueOf(this.v)));
        }
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.a(UserRecentActivity.class);
        return uRLManager;
    }

    public void setOnDataLoadedListener(OnDataLoadedListener onDataLoadedListener) {
        this.h = onDataLoadedListener;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.f = super.getNewView(this.c, viewGroup);
        return this.f;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.g = new a(getNewView(-1, viewGroup));
        ((a) this.g).d.setAdapter(((a) this.g).d.a(((a) this.g).itemView.getContext(), 0, this.d.e()));
        a(this.g);
        return this.g;
    }

    public void a(ViewHolder viewHolder) {
        if (this.d.s()) {
            b(viewHolder);
            return;
        }
        d(viewHolder);
        this.q = true;
    }

    public boolean b(ViewHolder viewHolder) {
        if (this.e == null) {
            this.e = ((a) viewHolder).d;
        }
        a aVar = (a) viewHolder;
        if (aVar.b != null) {
            aVar.b.setVisibility(8);
        }
        if (aVar.f != null) {
            aVar.f.setVisibility(8);
        }
        a(this.k, this.d.A());
        if (this.w == null) {
            this.w = new GenericItemView(this.mContext, this.mFragment);
        }
        this.e.setViewRecycleListner(this.u, 4, false, new com.views.HorizontalRecyclerView.a() {
            public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                Resources resources;
                if (i3 == 0) {
                    resources = DynamicUserActivityView.this.getResources();
                    i2 = R.dimen.page_left_right_margin;
                } else {
                    resources = DynamicUserActivityView.this.getResources();
                    i2 = R.dimen.home_item_paddding;
                }
                viewHolder.itemView.setPadding(resources.getDimensionPixelSize(i2), 0, 0, 0);
                return DynamicUserActivityView.this.w.getEmptyView(viewHolder, (ViewGroup) viewHolder.itemView.getParent(), BusinessObjectType.GenericItems);
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                return new PlaylistGridHolder(LayoutInflater.from(DynamicUserActivityView.this.mContext).inflate(h.a(DynamicUserActivityView.this.d), viewGroup, false));
            }

            public int getItemViewType(int i) {
                return h.a(DynamicUserActivityView.this.d);
            }
        });
        return false;
    }

    public com.dynamicview.f.a getDynamicView() {
        return this.d;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        a aVar = (a) viewHolder;
        this.f = aVar.itemView;
        this.e = aVar.d;
        this.g = aVar;
        a(this.m);
        if (this.m == null) {
            this.n = true;
        } else if (this.l != null) {
            this.i = this.l.getArrListBusinessObj();
            this.n = a(this.l, viewHolder.itemView);
            if (this.d.j() != null) {
                this.a = (String) this.d.j().get("url_logo");
                this.b = (String) this.d.j().get("track_url");
            }
            if (this.a != null) {
                final ImageView imageView = aVar.e;
                imageView.setVisibility(0);
                e.c(this.mContext).asBitmap().load(this.a).into(new g() {
                    public void onResourceReady(Object obj, d dVar) {
                        imageView.setImageBitmap((Bitmap) obj);
                        imageView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                c.a(DynamicUserActivityView.this.mContext).a(DynamicUserActivityView.this.mContext, com.managers.e.U, GaanaApplication.getInstance());
                            }
                        });
                        if (DynamicUserActivityView.this.x == 0 || System.currentTimeMillis() / 1000 > DynamicUserActivityView.this.x + 30) {
                            DynamicUserActivityView.this.x = System.currentTimeMillis() / 1000;
                            String str = DynamicUserActivityView.this.b;
                            if (str != null) {
                                str = str.replace("[timestamp]", String.valueOf(System.currentTimeMillis()));
                            }
                            DynamicUserActivityView.this.y = str;
                            DynamicUserActivityView.this.b();
                        }
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                    }
                });
            }
        } else if (this.p && this.l == null) {
            this.n = true;
        }
        if (this.n) {
            this.f = new View(this.mContext);
        } else {
            this.f.findViewById(R.id.f55header.text).setOnClickListener(this);
            this.f.findViewById(R.id.seeall).setOnClickListener(this);
            this.f.findViewById(R.id.seeallImg).setOnClickListener(this);
        }
        return this.f;
    }

    private void b() {
        Log.d("LogoImpression", "Notified");
        u.a().b(this.d.p(), "Branded Logo Visible");
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.y);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
            }
        }, uRLManager);
    }

    public void a(URLManager uRLManager) {
        this.t = Calendar.getInstance().getTimeInMillis();
        this.o = uRLManager.m().booleanValue();
        if (this.d.g() != null) {
            uRLManager.a(Integer.parseInt(this.d.g()));
            Constants.dp = Integer.parseInt(this.d.g());
            com.services.d.a().a("PREFERENCE_USER_ACTIVITY_REFRESH_TIME", Constants.dp, false);
        }
        if (this.d.m().equals(DynamicViewType.user_radio_activity.name())) {
            aq.a().b(uRLManager, this);
        } else {
            aq.a().a(uRLManager, (com.managers.aq.a) this);
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            setHeader(str);
            return;
        }
        TextView textView = ((a) this.g).c;
        textView.setMaxLines(2);
        textView.setGravity(16);
        if (str != null) {
            CharSequence str3;
            String str4 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str3.split("\\s");
                String str5 = str4;
                for (int i = 0; i < split.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str5);
                    stringBuilder.append(Character.toUpperCase(split[i].charAt(0)));
                    stringBuilder.append(split[i].substring(1));
                    str5 = stringBuilder.toString();
                    if (i < split.length - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str5);
                        stringBuilder.append(" ");
                        str5 = stringBuilder.toString();
                    }
                }
                str3 = str5;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            spannableStringBuilder.append("\n").append(str2);
            TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_secondline);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str3.length(), 17);
            spannableStringBuilder.setSpan(styleSpan, 0, str3.length(), 17);
            spannableStringBuilder.setSpan(textAppearanceSpan2, str3.length(), (str3.length() + str2.length()) + 1, 18);
            textView.setText(spannableStringBuilder);
            return;
        }
        textView.setVisibility(8);
    }

    private void setHeader(String str) {
        TextView textView = ((a) this.g).c;
        textView.setGravity(16);
        if (str != null) {
            CharSequence str2;
            String str3 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str2.split("\\s");
                String str4 = str3;
                for (int i = 0; i < split.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str4);
                    stringBuilder.append(Character.toUpperCase(split[i].charAt(0)));
                    stringBuilder.append(split[i].substring(1));
                    str4 = stringBuilder.toString();
                    if (i < split.length - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str4);
                        stringBuilder.append(" ");
                        str4 = stringBuilder.toString();
                    }
                }
                str2 = str4;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str2.length(), 17);
            spannableStringBuilder.setSpan(styleSpan, 0, str2.length(), 17);
            textView.setText(spannableStringBuilder);
            if (this.d.s() || !this.n) {
                textView.setVisibility(0);
                return;
            } else {
                textView.setVisibility(8);
                return;
            }
        }
        textView.setVisibility(8);
    }

    private void c(ViewHolder viewHolder) {
        if (viewHolder != null) {
            a aVar;
            if (viewHolder.itemView.getLayoutParams().height == 0) {
                viewHolder.itemView.getLayoutParams().height = -2;
                if (viewHolder.itemView.getLayoutParams() instanceof LayoutParams) {
                    ((LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half);
                }
                viewHolder.itemView.requestLayout();
            }
            if (!Constants.aV) {
                aVar = (a) viewHolder;
                if (!(aVar.b == null || aVar.b.getVisibility() == 0)) {
                    aVar.b.setVisibility(0);
                }
            }
            if (Constants.aV) {
                aVar = (a) viewHolder;
                if (!(aVar.f == null || aVar.f.getVisibility() == 0)) {
                    aVar.f.setVisibility(0);
                }
            }
            a aVar2 = (a) viewHolder;
            if (!(aVar2.d == null || aVar2.d.getVisibility() == 0)) {
                aVar2.d.setVisibility(0);
            }
            if (aVar2.c != null && aVar2.c.getVisibility() != 0) {
                aVar2.c.setVisibility(0);
            }
        }
    }

    private void d(ViewHolder viewHolder) {
        if (viewHolder != null) {
            a aVar = (a) viewHolder;
            if (aVar.b != null) {
                aVar.b.setVisibility(8);
            }
            if (aVar.f != null) {
                aVar.f.setVisibility(8);
            }
            if (aVar.c != null) {
                aVar.c.setVisibility(8);
            }
            if (aVar.d != null) {
                aVar.d.setVisibility(8);
            }
            if (viewHolder.itemView.getLayoutParams().height != 0) {
                viewHolder.itemView.getLayoutParams().height = 0;
                if (viewHolder.itemView.getLayoutParams() instanceof LayoutParams) {
                    ((LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = 0;
                }
                viewHolder.itemView.requestLayout();
            }
        }
    }

    private boolean a(BusinessObject businessObject) {
        if (this.e == null || businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            d(this.g);
            return false;
        }
        this.q = false;
        a(this.k, this.d.A());
        c(this.g);
        return true;
    }

    public boolean a(BusinessObject businessObject, View view) {
        if (!a(businessObject)) {
            return false;
        }
        if (ap.a().b(this.mContext)) {
            return b(businessObject);
        }
        return b(businessObject, view);
    }

    private boolean b(BusinessObject businessObject, View view) {
        if (this.e == null) {
            return false;
        }
        if (this.i != null && this.i.size() > 0) {
            final int size = this.i.size();
            this.e.setViewRecycleListner(this.u, size, false, new com.views.HorizontalRecyclerView.a() {
                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    Resources resources;
                    if (i3 == 0) {
                        resources = DynamicUserActivityView.this.getResources();
                        i2 = R.dimen.page_left_right_margin;
                    } else {
                        resources = DynamicUserActivityView.this.getResources();
                        i2 = R.dimen.home_item_paddding;
                    }
                    i = resources.getDimensionPixelSize(i2);
                    if (i3 == size - 1) {
                        viewHolder.itemView.setPadding(i, 0, DynamicUserActivityView.this.z, 0);
                    } else {
                        viewHolder.itemView.setPadding(i, 0, 0, 0);
                    }
                    BusinessObject businessObject = (BusinessObject) DynamicUserActivityView.this.i.get(i3);
                    if (businessObject instanceof Item) {
                        Item item = (Item) businessObject;
                        if (item.getEntityType() != null) {
                            View gridItemViewforDynamicView;
                            if (item.getEntityType().equals(com.constants.c.c.c)) {
                                DownloadSongsItemView downloadSongsItemView = new DownloadSongsItemView(DynamicUserActivityView.this.mContext, DynamicUserActivityView.this.mFragment);
                                downloadSongsItemView.setUniqueID(DynamicUserActivityView.this.d.y());
                                downloadSongsItemView.setGAData(DynamicUserActivityView.this.d.p(), DynamicUserActivityView.this.j, i3 + 1);
                                downloadSongsItemView.setSongsListBusinessObject(aq.a().b());
                                downloadSongsItemView.setIsSongSection();
                                gridItemViewforDynamicView = downloadSongsItemView.getGridItemViewforDynamicView(viewHolder, businessObject, DynamicUserActivityView.this.d);
                            } else {
                                if (DynamicUserActivityView.this.w == null) {
                                    DynamicUserActivityView.this.w = new GenericItemView(DynamicUserActivityView.this.mContext, DynamicUserActivityView.this.mFragment);
                                }
                                DynamicUserActivityView.this.w.setUniqueID(DynamicUserActivityView.this.d.y());
                                DynamicUserActivityView.this.w.setSourceName(DynamicUserActivityView.this.d.p());
                                gridItemViewforDynamicView = DynamicUserActivityView.this.w.getPoplatedGenericView(i3, viewHolder, businessObject, (ViewGroup) viewHolder.itemView.getParent(), DynamicUserActivityView.this.j, DynamicUserActivityView.this.d);
                            }
                            return gridItemViewforDynamicView;
                        }
                    }
                    return null;
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    return new PlaylistGridHolder(LayoutInflater.from(DynamicUserActivityView.this.mContext).inflate(h.a(DynamicUserActivityView.this.d), viewGroup, false));
                }

                public int getItemViewType(int i) {
                    return h.a(DynamicUserActivityView.this.d);
                }
            });
        }
        if (!this.r) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.t != 0) {
                long j = timeInMillis - this.t;
                StringBuilder stringBuilder;
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(this.j);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                } else if (this.mFragment instanceof RadioActivityFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Radio ");
                    stringBuilder.append(this.j);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                }
                this.r = true;
            }
        }
        setIsToBeRefreshed(false);
        return false;
    }

    private boolean b(BusinessObject businessObject) {
        if (this.e == null) {
            return false;
        }
        final long parseLong = (this.d.q() == null || this.d.q().isEmpty()) ? -1 : Long.parseLong(this.d.q());
        if (this.i != null && this.i.size() > 0) {
            int size;
            boolean z;
            if (parseLong == -1 || this.i.size() < 3) {
                size = this.i.size();
                z = false;
            } else {
                size = this.i.size() + 1;
                z = true;
            }
            final boolean z2 = z;
            final int i = size;
            this.e.setViewRecycleListner(this.u, size, z, new com.views.HorizontalRecyclerView.a() {
                /* JADX WARNING: Removed duplicated region for block: B:17:0x00a2  */
                /* JADX WARNING: Removed duplicated region for block: B:16:0x0075  */
                public android.view.View getCompatibleView(int r14, int r15, int r16, android.support.v7.widget.RecyclerView.ViewHolder r17) {
                    /*
                    r13 = this;
                    r0 = r13;
                    r1 = r15;
                    r6 = r16;
                    r3 = r17;
                    r2 = r7;
                    r4 = 2131165644; // 0x7f0701cc float:1.794551E38 double:1.0529357303E-314;
                    r5 = 2131165779; // 0x7f070253 float:1.7945785E38 double:1.052935797E-314;
                    r7 = 0;
                    r10 = 0;
                    if (r2 == 0) goto L_0x015a;
                L_0x0012:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.w;
                    if (r2 != 0) goto L_0x0030;
                L_0x001a:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r8 = new com.gaana.view.item.GenericItemView;
                    r9 = com.dynamicview.DynamicUserActivityView.this;
                    r9 = r9.mContext;
                    r11 = com.dynamicview.DynamicUserActivityView.this;
                    r11 = r11.mFragment;
                    r8.<init>(r9, r11);
                    r2.w = r8;
                L_0x0030:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.w;
                    r8 = com.dynamicview.DynamicUserActivityView.this;
                    r8 = r8.d;
                    r8 = r8.p();
                    r2.setSourceName(r8);
                    if (r6 <= 0) goto L_0x0057;
                L_0x0045:
                    if (r6 <= r1) goto L_0x0057;
                L_0x0047:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.i;
                    r8 = r6 + -1;
                    r2 = r2.get(r8);
                    r2 = (com.gaana.models.BusinessObject) r2;
                L_0x0055:
                    r8 = r2;
                    goto L_0x0073;
                L_0x0057:
                    if (r6 >= r1) goto L_0x0072;
                L_0x0059:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.i;
                    r2 = r2.size();
                    if (r6 >= r2) goto L_0x0072;
                L_0x0065:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.i;
                    r2 = r2.get(r6);
                    r2 = (com.gaana.models.BusinessObject) r2;
                    goto L_0x0055;
                L_0x0072:
                    r8 = r7;
                L_0x0073:
                    if (r6 != r1) goto L_0x00a2;
                L_0x0075:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    r4 = r8;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r7 = r2.s;
                    r8 = 0;
                    r9 = r3.itemView;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r11 = r2;
                    r11 = (android.view.ViewGroup) r11;
                    r12 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r4;
                    r4 = r7;
                    r5 = r8;
                    r7 = r9;
                    r8 = r11;
                    r9 = r12;
                    r7 = r1.getPoplatedAdView(r2, r4, r5, r6, r7, r8, r9);
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1.s = r10;
                    goto L_0x0159;
                L_0x00a2:
                    r1 = r8 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x0159;
                L_0x00a6:
                    r1 = r8;
                    r1 = (com.gaana.models.Item) r1;
                    r2 = r1.getEntityType();
                    if (r2 == 0) goto L_0x0159;
                L_0x00af:
                    if (r6 != 0) goto L_0x00bc;
                L_0x00b1:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r5);
                    goto L_0x00c6;
                L_0x00bc:
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.getResources();
                    r2 = r2.getDimensionPixelSize(r4);
                L_0x00c6:
                    r4 = r10;
                    r4 = r4 + -1;
                    if (r6 != r4) goto L_0x00d8;
                L_0x00cc:
                    r4 = r3.itemView;
                    r5 = com.dynamicview.DynamicUserActivityView.this;
                    r5 = r5.z;
                    r4.setPadding(r2, r10, r5, r10);
                    goto L_0x00dd;
                L_0x00d8:
                    r4 = r3.itemView;
                    r4.setPadding(r2, r10, r10, r10);
                L_0x00dd:
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x0135;
                L_0x00e9:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.mContext;
                    r4 = com.dynamicview.DynamicUserActivityView.this;
                    r4 = r4.mFragment;
                    r1.<init>(r2, r4);
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.y();
                    r1.setUniqueID(r2);
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.p();
                    r4 = com.dynamicview.DynamicUserActivityView.this;
                    r4 = r4.j;
                    r5 = r6 + 1;
                    r1.setGAData(r2, r4, r5);
                    r2 = com.managers.aq.a();
                    r2 = r2.b();
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r1 = r1.getGridItemViewforDynamicView(r3, r8, r2);
                    goto L_0x0158;
                L_0x0135:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r5 = r2;
                    r5 = (android.view.ViewGroup) r5;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r7 = r2.j;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r9 = r2.d;
                    r2 = r6;
                    r4 = r8;
                    r6 = r7;
                    r7 = r9;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6, r7);
                L_0x0158:
                    return r1;
                L_0x0159:
                    return r7;
                L_0x015a:
                    if (r6 != 0) goto L_0x0167;
                L_0x015c:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r5);
                    goto L_0x0171;
                L_0x0167:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.getResources();
                    r1 = r1.getDimensionPixelSize(r4);
                L_0x0171:
                    r2 = r10;
                    r2 = r2 + -1;
                    if (r6 != r2) goto L_0x0183;
                L_0x0177:
                    r2 = r3.itemView;
                    r4 = com.dynamicview.DynamicUserActivityView.this;
                    r4 = r4.z;
                    r2.setPadding(r1, r10, r4, r10);
                    goto L_0x0188;
                L_0x0183:
                    r2 = r3.itemView;
                    r2.setPadding(r1, r10, r10, r10);
                L_0x0188:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.i;
                    r1 = r1.get(r6);
                    r4 = r1;
                    r4 = (com.gaana.models.BusinessObject) r4;
                    r1 = r4 instanceof com.gaana.models.Item;
                    if (r1 == 0) goto L_0x0261;
                L_0x0199:
                    r1 = r4;
                    r1 = (com.gaana.models.Item) r1;
                    r2 = r1.getEntityType();
                    if (r2 == 0) goto L_0x0261;
                L_0x01a2:
                    r1 = r1.getEntityType();
                    r2 = com.constants.c.c.c;
                    r1 = r1.equals(r2);
                    if (r1 == 0) goto L_0x01fa;
                L_0x01ae:
                    r1 = new com.gaana.view.item.DownloadSongsItemView;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.mContext;
                    r5 = com.dynamicview.DynamicUserActivityView.this;
                    r5 = r5.mFragment;
                    r1.<init>(r2, r5);
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.y();
                    r1.setUniqueID(r2);
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.p();
                    r5 = com.dynamicview.DynamicUserActivityView.this;
                    r5 = r5.j;
                    r6 = r6 + 1;
                    r1.setGAData(r2, r5, r6);
                    r2 = com.managers.aq.a();
                    r2 = r2.b();
                    r1.setSongsListBusinessObject(r2);
                    r1.setIsSongSection();
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r1 = r1.getGridItemViewforDynamicView(r3, r4, r2);
                    goto L_0x0260;
                L_0x01fa:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    if (r1 != 0) goto L_0x0218;
                L_0x0202:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = new com.gaana.view.item.GenericItemView;
                    r5 = com.dynamicview.DynamicUserActivityView.this;
                    r5 = r5.mContext;
                    r7 = com.dynamicview.DynamicUserActivityView.this;
                    r7 = r7.mFragment;
                    r2.<init>(r5, r7);
                    r1.w = r2;
                L_0x0218:
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.y();
                    r1.setUniqueID(r2);
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r2 = r2.d;
                    r2 = r2.p();
                    r1.setSourceName(r2);
                    r1 = com.dynamicview.DynamicUserActivityView.this;
                    r1 = r1.w;
                    r2 = r3.itemView;
                    r2 = r2.getParent();
                    r5 = r2;
                    r5 = (android.view.ViewGroup) r5;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r7 = r2.j;
                    r2 = com.dynamicview.DynamicUserActivityView.this;
                    r8 = r2.d;
                    r2 = r6;
                    r6 = r7;
                    r7 = r8;
                    r1 = r1.getPoplatedGenericView(r2, r3, r4, r5, r6, r7);
                L_0x0260:
                    return r1;
                L_0x0261:
                    return r7;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.dynamicview.DynamicUserActivityView$AnonymousClass5.getCompatibleView(int, int, int, android.support.v7.widget.RecyclerView$ViewHolder):android.view.View");
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    return new PlaylistGridHolder(LayoutInflater.from(DynamicUserActivityView.this.mContext).inflate(h.a(DynamicUserActivityView.this.d), viewGroup, false));
                }

                public int getItemViewType(int i) {
                    return h.a(DynamicUserActivityView.this.d);
                }
            });
        }
        if (!this.r) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.t != 0) {
                long j = timeInMillis - this.t;
                if (this.mFragment instanceof DynamicHomeFragment) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(this.j);
                    Constants.a("Load", j, "Page", stringBuilder.toString());
                }
                this.r = true;
            }
        }
        setIsToBeRefreshed(false);
        return false;
    }

    public void setIsToBeRefreshed(boolean z) {
        if (this.m != null) {
            this.m.c(Boolean.valueOf(z));
            if (z) {
                this.s = z;
                if (!(this.q || this.f == null || this.f.findViewById(R.id.seeall) == null)) {
                    this.f.findViewById(R.id.seeall).setVisibility(8);
                }
                a(this.m);
            }
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.f55header.text /*2131297292*/:
            case R.id.seeall /*2131298341*/:
            case R.id.seeallImg /*2131298342*/:
            case R.id.view1 /*2131298846*/:
                this.mAppState.setPlayoutSectionName(this.d.p());
                BaseActivity baseActivity = (BaseActivity) this.mContext;
                String str = ((BaseActivity) this.mContext).currentScreen;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.j);
                stringBuilder.append(" click ");
                baseActivity.sendGAEvent(str, stringBuilder.toString(), "See More");
                a(getSeeAllUrlManager(), this.k);
                an.a().a("click", "en", this.d.y(), an.a().a(an.a().a), "SEEALL", "", "", "");
                return;
            default:
                return;
        }
    }

    private void a(URLManager uRLManager, String str) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (uRLManager != null) {
            if (this.d.g() != null) {
                uRLManager.a(Integer.parseInt(this.d.g()));
            }
            if (!TextUtils.isEmpty(this.j)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("collection:");
                stringBuilder.append(this.j);
                q.a().a("int", stringBuilder.toString());
            }
            str = this.d.r();
            BaseGaanaFragment songParallexListingFragment;
            if ((!TextUtils.isEmpty(str) && !str.equals(DynamicViewType.grid_rect.name()) && !str.equals(DynamicViewType.grid.name())) || TextUtils.isEmpty(this.d.m()) || this.d.m().equals(DynamicViewType.user_activity.name())) {
                songParallexListingFragment = new SongParallexListingFragment();
                ListingParams listingParams = new ListingParams();
                listingParams.e(false);
                listingParams.f(true);
                listingParams.h(false);
                listingParams.d(true);
                listingParams.i(false);
                listingParams.a(true);
                listingParams.a(this.d.i());
                listingParams.b(this.d.p());
                ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                listingButton.b(this.d.w());
                listingButton.a(this.d.w());
                URLManager c = listingButton.c();
                c.g(true);
                c.a(uRLManager.k());
                c.d(false);
                c.a(true);
                c.a(BusinessObjectType.GenericItems);
                c.a(UserRecentActivity.class);
                uRLManager.h(true);
                listingParams.a(listingButton);
                songParallexListingFragment.a(listingParams);
                ListingComponents listingComponents = new ListingComponents();
                new ArrayList().add(listingButton);
                this.mAppState.setListingComponents(listingComponents);
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_DYNAMIC_SECTION_UID", this.d.y());
                songParallexListingFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(songParallexListingFragment);
            } else {
                songParallexListingFragment = new GridActivityFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("EXTRA_URL_MANAGER", uRLManager);
                bundle2.putBoolean("EXTRA_SHOW_LOADMORE", this.d.t());
                bundle2.putString("EXTRA_GASECTION_NAME", this.d.p());
                bundle2.putString("EXTRA_ACTIONBAR_TITLE", this.k);
                bundle2.putString("EXTRA_GA_TITLE", this.j);
                bundle2.putString("EXTRA_GRID_SEE_ALL_AD_CODE", this.d.i());
                if (!TextUtils.isEmpty(this.d.m())) {
                    bundle2.putString("extra_dynamic_view_type_see_all", this.d.m());
                }
                if (!TextUtils.isEmpty(this.d.y())) {
                    bundle2.putString("EXTRA_DYNAMIC_SECTION_UID", this.d.y());
                }
                String str2 = null;
                if (this.d.j() != null && this.d.j().containsKey("video_ad_seeall")) {
                    str2 = (String) this.d.j().get("video_ad_seeall");
                }
                if (str2 != null) {
                    bundle2.putString("SEE_ALL_VIDEO_AD_CODE", str2);
                }
                songParallexListingFragment.setArguments(bundle2);
                ((GaanaActivity) this.mContext).displayFragment(songParallexListingFragment);
            }
        }
    }

    public void notifyItemChanged(int i) {
        if (this.e != null) {
            this.e.a(i);
        }
    }

    public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
        this.p = true;
        if (userRecentActivity == null || userRecentActivity.getArrListBusinessObj() == null || userRecentActivity.getArrListBusinessObj().size() == 0) {
            a((BusinessObject) userRecentActivity, this.f);
        } else {
            if (this.h != null) {
                this.h.onDataLoaded(userRecentActivity, BusinessObjectType.GenericItems);
            }
            this.l = userRecentActivity;
            this.i = this.l.getArrListBusinessObj();
            a((BusinessObject) userRecentActivity, this.f);
        }
        setIsToBeRefreshed(false);
        this.o = false;
    }

    public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
        a(null, this.f);
    }
}

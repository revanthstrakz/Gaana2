package com.dynamicview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.bumptech.glide.e;
import com.bumptech.glide.request.a.g;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.constants.Constants.VIEW_SUBTYPE;
import com.constants.c.c;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.GridActivityFragment;
import com.fragments.MoreRadioActivityFragment;
import com.fragments.MyMusicFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.OfflineTrack;
import com.gaana.view.BaseItemView;
import com.gaana.view.ImageCardView;
import com.gaana.view.ImageCardView.ImageCardViewHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.BaseItemView.SocialHomeGridHolder;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.GenericItemView;
import com.gaana.view.item.JukePlaylistItemView;
import com.gaana.view.item.SocialSongsItemView;
import com.gaana.view.item.StaggeredGridItemView;
import com.gaana.view.item.StaggeredGridItemView.StaggeredGridViewHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.an;
import com.managers.ap;
import com.managers.q;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.playercache.TrackCacheQueueManager;
import com.playercache.TrackCacheQueueManager.CACHING_BEHAVIOUR;
import com.playercache.TrackCacheQueueManager.INSERTION_ORDER;
import com.services.d;
import com.services.l.r;
import com.services.l.s;
import com.services.l.w;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class DynamicHomeScrollerView extends BaseItemView implements w {
    private URLManager a = null;
    private int b = 0;
    private GenericItemView c;
    private int d = ((int) getResources().getDimension(R.dimen.home_item_paddding));
    private boolean e = true;
    private long f = 0;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private a m;
    private String n = "";
    private Drawable o;
    private b p;
    private int q = -1;

    public static class a extends ViewHolder {
        public ConstraintLayout a;
        public FrameLayout b;
        public TextView c;
        public TextView d;
        public HorizontalRecyclerView e;
        public RelativeLayout f;
        public ImageView g;
        public ImageView h;
        public ImageView i;

        public a(View view) {
            super(view);
            this.a = (ConstraintLayout) view.findViewById(R.id.layout_horzontal_scroll_container);
            this.b = (FrameLayout) view.findViewById(R.id.view_all_container);
            this.c = (TextView) view.findViewById(R.id.seeall);
            this.d = (TextView) view.findViewById(R.id.f55header.text);
            this.f = (RelativeLayout) view.findViewById(R.id.section_header);
            this.e = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
            this.g = (ImageView) view.findViewById(R.id.logoImage);
            this.h = (ImageView) view.findViewById(R.id.img_indicator);
            this.i = (ImageView) view.findViewById(R.id.seeallImg);
        }
    }

    public static class b {
        com.dynamicview.f.a a;
        int b;

        public b(com.dynamicview.f.a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        public com.dynamicview.f.a a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public DynamicHomeScrollerView(Context context, BaseGaanaFragment baseGaanaFragment, com.dynamicview.f.a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
        this.mDynamicView.a(0);
    }

    public com.dynamicview.f.a getDynamicView() {
        return this.mDynamicView;
    }

    public void setDynamicData(com.dynamicview.f.a aVar) {
        this.mDynamicView = aVar;
    }

    public DynamicHomeScrollerView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    private URLManager a(com.dynamicview.f.a aVar, int i) {
        URLManager uRLManager = new URLManager();
        String o = aVar.o();
        if (TextUtils.isEmpty(o)) {
            return null;
        }
        if (!TextUtils.isEmpty(aVar.y()) && aVar.y().equalsIgnoreCase("X5X")) {
            int i2 = 0;
            StringBuilder stringBuilder;
            if (o.contains("?")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(o);
                stringBuilder.append("&trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i2 = 1;
                }
                stringBuilder.append(i2);
                o = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(o);
                stringBuilder.append("?trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i2 = 1;
                }
                stringBuilder.append(i2);
                o = stringBuilder.toString();
            }
        }
        uRLManager.a(o);
        if (i != -1 && o.contains("<entity_Parent_Id>")) {
            uRLManager.a(o.replace("<entity_Parent_Id>", String.valueOf(i)));
        }
        uRLManager.a(BusinessObjectType.GenericItems);
        return uRLManager;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        return super.getNewView(i, viewGroup);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        boolean z = this.mDynamicView != null && this.mDynamicView.m().equals(DynamicViewType.double_scroll.name());
        return a(viewGroup, i, z);
    }

    public ViewHolder a(ViewGroup viewGroup, int i, boolean z) {
        View newView;
        if (isBrandView(this.mDynamicView.p())) {
            newView = getNewView(R.layout.view_horizontal_scroll_container_brand, viewGroup);
        } else if (i == 9) {
            newView = getNewView(R.layout.view_horizontal_scroll_container_staggered_grid, viewGroup);
        } else {
            newView = getNewView(R.layout.view_horizontal_scroll_container, viewGroup);
        }
        ViewHolder aVar = new a(newView);
        if (z) {
            aVar.e.setLayoutManager(new GridLayoutManager(this.mContext, 2, 0, false));
        }
        aVar.e.setRecycledViewPool(((GaanaActivity) this.mContext).getViewPool());
        aVar.e.setAdapter(aVar.e.a(aVar.itemView.getContext(), 0));
        a(aVar, i);
        return aVar;
    }

    public boolean a(ViewHolder viewHolder) {
        a aVar = (a) viewHolder;
        HorizontalRecyclerView horizontalRecyclerView = aVar.e;
        if (aVar.c != null && this.e) {
            aVar.c.setVisibility(8);
        }
        if (aVar.i != null && this.e) {
            aVar.i.setVisibility(8);
        }
        if (aVar.d != null) {
            aVar.d.setVisibility(8);
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) horizontalRecyclerView.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, Util.b((int) this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_half)));
        horizontalRecyclerView.setLayoutParams(marginLayoutParams);
        horizontalRecyclerView.setViewSubType(VIEW_SUBTYPE.CHAMELEON.getNumVal());
        horizontalRecyclerView.setViewRecycleListner(this.b, 1, false, true, new com.views.HorizontalRecyclerView.a() {
            public int getItemViewType(int i) {
                return 0;
            }

            public View getCompatibleView(int i, int i2, int i3, final ViewHolder viewHolder) {
                String str = "";
                if (DynamicHomeScrollerView.this.mDynamicView.j() != null && DynamicHomeScrollerView.this.mDynamicView.j().size() > 0) {
                    str = (String) DynamicHomeScrollerView.this.mDynamicView.j().get("fallback_image_url");
                }
                if (!TextUtils.isEmpty(str)) {
                    i.a().a(str, new r() {
                        public void onErrorResponse(VolleyError volleyError) {
                        }

                        public void onSuccessfulResponse(Bitmap bitmap) {
                            ((ImageCardViewHolder) viewHolder).itemView.setVisibility(0);
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setVisibility(0);
                            CrossFadeImageView crossFadeImageView = new CrossFadeImageView(DynamicHomeScrollerView.this.mContext);
                            crossFadeImageView.setAdjustViewBounds(true);
                            crossFadeImageView.setShowLoadingState(true);
                            crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
                            int numVal = VIEW_SIZE.CARD_SOCIAL.getNumVal();
                            if (DynamicHomeScrollerView.this.mDynamicView.j() != null && DynamicHomeScrollerView.this.mDynamicView.j().size() > 0 && DynamicHomeScrollerView.this.mDynamicView.j().containsKey("fallback_view_size")) {
                                try {
                                    numVal = Integer.valueOf((String) DynamicHomeScrollerView.this.mDynamicView.j().get("fallback_view_size")).intValue();
                                } catch (NumberFormatException unused) {
                                    numVal = VIEW_SIZE.CARD_SOCIAL.getNumVal();
                                }
                            }
                            numVal = ImageCardView.getCardHeight(DynamicHomeScrollerView.this.mContext, numVal);
                            int b = d.a().b() - DynamicHomeScrollerView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin);
                            LayoutParams layoutParams = new LayoutParams(b, numVal);
                            layoutParams.leftMargin = (int) DynamicHomeScrollerView.this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
                            layoutParams.rightMargin = (int) DynamicHomeScrollerView.this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
                            layoutParams.bottomMargin = (int) DynamicHomeScrollerView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                            layoutParams.topMargin = (int) DynamicHomeScrollerView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                            crossFadeImageView.setLayoutParams(layoutParams);
                            crossFadeImageView.setImageBitmap(bitmap);
                            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) ((ImageCardViewHolder) viewHolder).itemView.getLayoutParams();
                            layoutParams2.height = numVal;
                            layoutParams2.width = b;
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setLayoutParams(layoutParams2);
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.removeAllViews();
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setBackgroundColor(0);
                            ((ImageCardViewHolder) viewHolder).llImgParentLayout.addView(crossFadeImageView);
                        }
                    });
                }
                return viewHolder.itemView;
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                return i == 912 ? new ImageCardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_card_view, viewGroup, false), true) : viewHolder;
            }
        });
        return false;
    }

    public boolean a(ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        HorizontalRecyclerView horizontalRecyclerView = aVar.e;
        int i2 = 8;
        if (aVar.c != null && this.e) {
            aVar.c.setVisibility(8);
        }
        if (aVar.i != null && this.e) {
            aVar.i.setVisibility(8);
        }
        if (this.c == null) {
            this.c = new GenericItemView(this.mContext, this.mFragment);
        }
        this.c.setItemWithoutText(Boolean.valueOf(true));
        horizontalRecyclerView.setViewSubType(VIEW_SUBTYPE.DEFAULT.getNumVal());
        if (this.mDynamicView == null || !this.mDynamicView.m().equals(DynamicViewType.double_scroll.name())) {
            i2 = 4;
        }
        horizontalRecyclerView.setViewRecycleListner(this.b, i2, false, new com.views.HorizontalRecyclerView.a() {
            public int getItemViewType(int i) {
                return h.a(DynamicHomeScrollerView.this.mDynamicView);
            }

            public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                int dimensionPixelSize = i3 == 0 ? DynamicHomeScrollerView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : DynamicHomeScrollerView.this.mContext.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
                int i4 = (DynamicHomeScrollerView.this.mDynamicView == null || !DynamicHomeScrollerView.this.mDynamicView.m().equals(DynamicViewType.double_scroll.name())) ? 0 : 1;
                if (i4 != 0) {
                    dimensionPixelSize = i3 / 2 == 0 ? DynamicHomeScrollerView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : DynamicHomeScrollerView.this.mContext.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
                }
                viewHolder.itemView.setPadding(dimensionPixelSize, 0, 0, 0);
                if (i3 == i2 || i == 9) {
                    return null;
                }
                return DynamicHomeScrollerView.this.c.getEmptyView(i3, viewHolder, null, (ViewGroup) viewHolder.itemView.getParent(), null);
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                if (i == 910) {
                    return new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.a(DynamicHomeScrollerView.this.mDynamicView), viewGroup, false));
                }
                return i == 9 ? new StaggeredGridViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_made_for_you, viewGroup, false)) : viewHolder;
            }
        });
        return false;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        this.f = System.currentTimeMillis();
        this.m = (a) viewHolder;
        this.q = viewHolder.getAdapterPosition();
        this.e = TextUtils.isEmpty(this.mDynamicView.o()) ^ 1;
        this.a = new URLManager();
        StringBuilder stringBuilder;
        if (this.mDynamicView.m().equals(DynamicViewType.download.name())) {
            this.a.a(BusinessObjectType.Tracks);
            this.a.j(true);
            com.dynamicview.f.a aVar = this.mDynamicView;
            stringBuilder = new StringBuilder();
            stringBuilder.append("dummy offline");
            stringBuilder.append(i);
            aVar.c(stringBuilder.toString());
        } else if (this.mDynamicView.l() == null) {
            return this.m.itemView;
        } else {
            String l = this.mDynamicView.l();
            if (!TextUtils.isEmpty(this.mDynamicView.y()) && this.mDynamicView.y().equalsIgnoreCase("X5X")) {
                if (l.contains("?")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(l);
                    stringBuilder.append("&trend=");
                    stringBuilder.append(GaanaApplication.sessionHistoryCount > 3 ? 1 : 0);
                    l = stringBuilder.toString();
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(l);
                    stringBuilder.append("?trend=");
                    stringBuilder.append(GaanaApplication.sessionHistoryCount > 3 ? 1 : 0);
                    l = stringBuilder.toString();
                }
            }
            this.a.a(l);
            this.a.l(this.mDynamicView.z());
            this.a.a(BusinessObjectType.GenericItems);
            if (this.mDynamicView.v()) {
                this.a.b(Boolean.valueOf(false));
                this.a.a(BusinessObjectType.JukePlayLists);
            }
        }
        if (this.p == null) {
            this.p = new b();
        }
        this.p.a((w) this);
        this.p.a(this.mDynamicView);
        this.p.a(i);
        if ("1a0".equals(this.mDynamicView.y()) && this.mAppState.getCurrentUser().getLoginStatus() && this.mDynamicView.j() != null && this.mDynamicView.j().containsKey("showDownloads") && ((String) this.mDynamicView.j().get("showDownloads")).equals("1") && Constants.ca) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(":");
            stringBuilder2.append(this.mDynamicView.p());
            u.a().a("Downloads", "View", stringBuilder2.toString());
        }
        this.m.d.setVisibility(0);
        this.n = this.mDynamicView.k();
        if (this.mDynamicView.e() != VIEW_SIZE.TAGS.getNumVal()) {
            a(this.n, this.mDynamicView.A(), this.m);
        } else {
            TextView textView = this.m.d;
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
        if (ap.a().b(this.mContext)) {
            a(this.m);
        }
        if (c()) {
            a(this.m, (String) this.mDynamicView.j().get("free_download_img"));
        }
        if (this.mDynamicView.l() != null) {
            this.a.c(Boolean.valueOf(this.p.g()));
            if (this.p.g()) {
                this.p.a(true);
            }
            if (this.mDynamicView.m().equals(DynamicViewType.download.name())) {
                if (this.p.a()) {
                    ((BaseActivity) this.mContext).startDownloadDbRetreival(this.p, this.a);
                } else {
                    this.p.i();
                }
            } else if (this.p.a()) {
                if (!this.p.g()) {
                    a(this.m, this.m.getItemViewType());
                }
                a(this.p);
            } else {
                this.p.i();
            }
            this.p.c(false);
        }
        return this.m.itemView;
    }

    private boolean a() {
        return (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus() || ap.a().d()) ? false : true;
    }

    private void a(String str, Drawable drawable) {
        str = str.trim();
        if (!TextUtils.isEmpty(str)) {
            drawable.setBounds(this.mContext.getResources().getDimensionPixelSize(R.dimen.dimen_4dp), 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp90), this.mContext.getResources().getDimensionPixelSize(R.dimen.dp16));
            ImageSpan imageSpan = new ImageSpan(drawable, 1);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str.length(), 17);
            spannableStringBuilder.setSpan(styleSpan, 0, str.length(), 17);
            spannableStringBuilder.setSpan(imageSpan, str.length() - 1, str.length(), 17);
            this.m.d.setText(spannableStringBuilder);
        }
    }

    private void a(final a aVar, String str) {
        ImageView imageView = aVar.g;
        if (TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
            return;
        }
        e.c(this.mContext).asBitmap().load(Util.c(this.mContext, str)).into(new g() {
            public void onResourceReady(Object obj, com.bumptech.glide.request.b.d dVar) {
                Drawable bitmapDrawable = new BitmapDrawable((Bitmap) obj);
                DynamicHomeScrollerView.this.o = bitmapDrawable;
                DynamicHomeScrollerView.this.a(aVar.d.getText().toString(), bitmapDrawable);
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
            }
        });
    }

    private void a(a aVar) {
        ImageView imageView = aVar.g;
        String p = this.mDynamicView.p();
        if (((TextUtils.isEmpty(p) || !(p.equalsIgnoreCase("TRENDING_SONG") || p.equalsIgnoreCase("MADE_FOR_YOU"))) && imageView != null && imageView.getVisibility() == 0) || !(this.mFragment instanceof DynamicHomeFragment)) {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
            aVar.c.setTextColor(typedValue.data);
            if (aVar.b != null) {
                aVar.b.setBackgroundColor(0);
            }
            return;
        }
        Map j = this.mDynamicView.j();
        if (j != null) {
            this.g = null;
            this.h = null;
            this.j = null;
            this.i = null;
            this.k = null;
            this.g = (String) j.get("url_logo");
            this.h = (String) j.get("tracker_adcode_dfp");
            this.j = (String) j.get("tracker_adcode_ctn");
            this.i = (String) j.get("tracker_adcode_dfp_viewall");
            this.k = (String) j.get("impression_url");
            this.l = (String) j.get("logo_color_code");
        }
        if (this.j != null && !TextUtils.isEmpty(this.j)) {
            com.managers.e.a().a(aVar.itemView, this.mContext, Long.parseLong(this.j));
        } else if (!(this.h == null || TextUtils.isEmpty(this.h))) {
            ColombiaAdViewManager.a().a(aVar.itemView, this.mContext, this.h);
        }
        b(aVar);
    }

    private void b(final a aVar) {
        final ImageView imageView = aVar.g;
        if (imageView != null && !TextUtils.isEmpty(this.g)) {
            e.c(this.mContext).asBitmap().load(Util.c(this.mContext, this.g)).into(new g() {
                public void onResourceReady(Object obj, com.bumptech.glide.request.b.d dVar) {
                    imageView.setImageBitmap((Bitmap) obj);
                    imageView.setVisibility(0);
                    aVar.c.setTextColor(DynamicHomeScrollerView.this.getResources().getColor(R.color.white));
                    if (aVar.b != null) {
                        aVar.b.setBackgroundColor(Color.parseColor(DynamicHomeScrollerView.this.l));
                    }
                    if (Constants.aV) {
                        aVar.i.setImageDrawable(DynamicHomeScrollerView.this.mContext.getResources().getDrawable(R.drawable.vector_ic_more));
                    }
                    if (DynamicHomeScrollerView.this.mDynamicView.p().equalsIgnoreCase("TRENDING_SONG")) {
                        u.a().b(DynamicHomeScrollerView.this.mDynamicView.p(), "Branded Logo Visible");
                    } else if (DynamicHomeScrollerView.this.mDynamicView.a() == 0 || System.currentTimeMillis() / 1000 > DynamicHomeScrollerView.this.mDynamicView.a() + 30) {
                        DynamicHomeScrollerView.this.mDynamicView.a(System.currentTimeMillis() / 1000);
                        DynamicHomeScrollerView.this.a(DynamicHomeScrollerView.this.k);
                    }
                }

                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                }
            });
        } else if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    private void a(String str) {
        if (str != null) {
            str = str.replace("[timestamp]", String.valueOf(System.currentTimeMillis()));
            u.a().b(this.mDynamicView.p(), "Branded Logo Visible");
            URLManager uRLManager = new URLManager();
            uRLManager.a(str);
            uRLManager.a(String.class);
            uRLManager.b(Boolean.valueOf(false));
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                }
            }, uRLManager);
        }
    }

    public void a(b bVar) {
        bVar.a(Calendar.getInstance().getTimeInMillis());
        if (this.mDynamicView.g() != null) {
            this.a.a(Integer.parseInt(this.mDynamicView.g()));
        }
        if (TextUtils.isEmpty(this.mDynamicView.q()) && !ap.a().d()) {
            AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(this.mDynamicView.p());
            adsUJData.setSectionId(this.mDynamicView.y());
            an.a().e("ad", "", "", "ad_section_load", adsUJData.getSectionId(), "start", adsUJData.getSectionIndex(), "");
        }
        i.a().a((s) bVar, this.a);
    }

    private void a(String str, ViewHolder viewHolder) {
        TextView textView = ((a) viewHolder).d;
        textView.setGravity(16);
        textView.setMaxLines(1);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            String str2 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str.split("\\s");
                String str3 = str2;
                for (int i = 0; i < split.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str3);
                    stringBuilder.append(Character.toUpperCase(split[i].charAt(0)));
                    stringBuilder.append(split[i].substring(1));
                    str3 = stringBuilder.toString();
                    if (i < split.length - 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str3);
                        stringBuilder.append(" ");
                        str3 = stringBuilder.toString();
                    }
                }
                str = str3;
            }
            this.n = str;
            if (this.o == null || !c()) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
                StyleSpan styleSpan = new StyleSpan(1);
                spannableStringBuilder.setSpan(textAppearanceSpan, 0, str.length(), 17);
                spannableStringBuilder.setSpan(styleSpan, 0, str.length(), 17);
                textView.setText(spannableStringBuilder);
            } else {
                a(this.n, this.o);
            }
        }
    }

    private void a(String str, String str2, ViewHolder viewHolder) {
        if (TextUtils.isEmpty(str2)) {
            a(str, viewHolder);
            return;
        }
        TextView textView = ((a) viewHolder).d;
        textView.setMaxLines(2);
        textView.setGravity(16);
        if (str != null) {
            String str3 = "";
            GaanaApplication gaanaApplication = this.mAppState;
            if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                String[] split = str.split("\\s");
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
                str = str4;
            }
            this.n = str;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_firstline);
            spannableStringBuilder.append("\n").append(str2);
            TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.home_gaana_item_secondline);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str.length(), 17);
            spannableStringBuilder.setSpan(styleSpan, 0, str.length(), 17);
            spannableStringBuilder.setSpan(textAppearanceSpan2, str.length(), (str.length() + str2.length()) + 1, 18);
            textView.setText(spannableStringBuilder);
            return;
        }
        textView.setVisibility(8);
    }

    private void b(ViewHolder viewHolder) {
        if (viewHolder != null) {
            a aVar;
            if (viewHolder.itemView.getLayoutParams().height == 0) {
                viewHolder.itemView.getLayoutParams().height = -2;
                if (viewHolder.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    ((RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half);
                }
                viewHolder.itemView.requestLayout();
            }
            int i = 8;
            if (!Constants.aV) {
                aVar = (a) viewHolder;
                if (aVar.c != null) {
                    aVar.c.setVisibility(this.e ? 0 : 8);
                }
            }
            if (Constants.aV) {
                aVar = (a) viewHolder;
                if (aVar.i != null) {
                    ImageView imageView = aVar.i;
                    if (this.e) {
                        i = 0;
                    }
                    imageView.setVisibility(i);
                }
            }
            a aVar2 = (a) viewHolder;
            if (!(aVar2.f == null || aVar2.f.getVisibility() == 0)) {
                aVar2.f.setVisibility(0);
            }
            if (!(aVar2.e == null || aVar2.e.getVisibility() == 0)) {
                aVar2.e.setVisibility(0);
            }
            if (aVar2.d != null && aVar2.d.getVisibility() != 0) {
                aVar2.d.setVisibility(0);
            }
        }
    }

    private void c(ViewHolder viewHolder) {
        if (viewHolder != null) {
            a aVar = (a) viewHolder;
            if (aVar.c != null) {
                aVar.c.setVisibility(8);
            }
            if (aVar.i != null) {
                aVar.i.setVisibility(8);
            }
            if (aVar.f != null) {
                aVar.f.setVisibility(8);
            }
            if (aVar.d != null) {
                aVar.d.setVisibility(8);
            }
            if (aVar.e != null) {
                aVar.e.setVisibility(8);
            }
            if (viewHolder.itemView.getLayoutParams().height != 0) {
                viewHolder.itemView.getLayoutParams().height = 0;
                if (viewHolder.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    ((RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams()).topMargin = 0;
                }
                viewHolder.itemView.requestLayout();
            }
        }
    }

    private boolean a(BusinessObject businessObject, a aVar, com.dynamicview.f.a aVar2) {
        if (aVar != null && this.q != -1 && this.q != aVar.getAdapterPosition()) {
            return false;
        }
        if (aVar == null || aVar.e == null || businessObject == null || ((businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) && aVar2.n() != VIEW_SUBTYPE.CHAMELEON.getNumVal())) {
            c((ViewHolder) aVar);
            return false;
        }
        if (aVar2.e() != VIEW_SIZE.TAGS.getNumVal()) {
            b((ViewHolder) aVar);
        }
        if (businessObject instanceof Items) {
            Items items = (Items) businessObject;
            aVar2.h(items.getRawTagDescription());
            String tagDescription = items.getTagDescription();
            if (TextUtils.isEmpty(tagDescription)) {
                tagDescription = aVar2.u();
            }
            String pageTitle = !TextUtils.isEmpty(items.getPageTitle()) ? items.getPageTitle() : aVar2.A();
            if (this.mDynamicView.e() != VIEW_SIZE.TAGS.getNumVal()) {
                this.n = tagDescription;
                a(this.n, pageTitle, (ViewHolder) aVar);
            } else {
                RelativeLayout relativeLayout = aVar.f;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(8);
                }
            }
        } else if (aVar2.l().contains("dummy") && !TextUtils.isEmpty(aVar2.u())) {
            this.n = aVar2.u();
            a(this.n, aVar2.A(), (ViewHolder) aVar);
        }
        if (aVar2.e() != VIEW_SIZE.TAGS.getNumVal()) {
            b((ViewHolder) aVar);
        }
        return true;
    }

    public boolean a(BusinessObject businessObject, ArrayList<?> arrayList, a aVar, com.dynamicview.f.a aVar2, ArrayList arrayList2, int i) {
        if (!a(businessObject, aVar, aVar2)) {
            return false;
        }
        if (this.mDynamicView != null && arrayList != null && arrayList.size() > 0 && (arrayList.get(0) instanceof Item) && ((Item) arrayList.get(0)).getEntityType().equals(c.c)) {
            TrackCacheQueueManager.a().a((ArrayList) arrayList, CACHING_BEHAVIOUR.PARTIAL_CACHE.ordinal(), INSERTION_ORDER.LAST.ordinal());
        }
        if (ap.a().b(this.mContext)) {
            return c(businessObject, arrayList, aVar, aVar2, arrayList2, i);
        }
        return b(businessObject, arrayList, aVar, aVar2, arrayList2, i);
    }

    private boolean a(com.dynamicview.f.a aVar) {
        Map j = aVar.j();
        boolean z = (j == null || j.get("theme") == null || !((String) j.get("theme")).equalsIgnoreCase("1")) ? false : true;
        if (z && Constants.du) {
            return true;
        }
        return false;
    }

    private boolean b(BusinessObject businessObject, ArrayList<?> arrayList, a aVar, final com.dynamicview.f.a aVar2, ArrayList arrayList2, int i) {
        final HorizontalRecyclerView horizontalRecyclerView = aVar.e;
        if (horizontalRecyclerView == null) {
            return false;
        }
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            horizontalRecyclerView.setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        double findLastCompletelyVisibleItemPosition = (double) (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1);
                        double itemCount = (double) recyclerView.getAdapter().getItemCount();
                        String a = an.a().a(an.a().a);
                        if (findLastCompletelyVisibleItemPosition > ((double) horizontalRecyclerView.getCurrentScrollX())) {
                            String valueOf = String.valueOf((int) itemCount);
                            int i2 = (int) findLastCompletelyVisibleItemPosition;
                            an.a().c("scroll", AvidJSONUtil.KEY_X, aVar2.y(), a, "", "", valueOf, String.valueOf(i2));
                            horizontalRecyclerView.setCurrentScrollX(i2);
                        }
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                }
            });
            if (aVar2.m().equals(DynamicViewType.staggered_grid.name())) {
                horizontalRecyclerView.setViewSubType(915);
            } else {
                horizontalRecyclerView.setViewSubType(aVar2.n());
            }
            final ArrayList<?> arrayList3 = arrayList;
            final com.dynamicview.f.a aVar3 = aVar2;
            final int i2 = size;
            final ArrayList arrayList4 = arrayList2;
            horizontalRecyclerView.setViewRecycleListner(this.b, size, false, new com.views.HorizontalRecyclerView.a() {
                public int getItemViewType(int i) {
                    Item item = (i < 0 || i >= arrayList3.size() || !(arrayList3.get(i) instanceof Item)) ? null : (Item) arrayList3.get(i);
                    if (aVar3.m().equals(DynamicViewType.staggered_grid.name())) {
                        return item.getEntityType().equals(c.j) ? 915 : 910;
                    } else {
                        if (item == null || !item.getEntityType().equals(c.h)) {
                            return h.a(aVar3);
                        }
                        return h.c(aVar3);
                    }
                }

                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    DownloadSongsItemView socialSongsItemView;
                    boolean equals = aVar3.m().equals(DynamicViewType.double_scroll.name());
                    int i4 = (equals ? i2 % 2 != 0 ? i3 != i2 - 1 : !(i3 == i2 - 1 || i3 == i2 - 2) : i3 != i2 - 1) ? 0 : 1;
                    i = !equals ? i3 == 0 ? DynamicHomeScrollerView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : DynamicHomeScrollerView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding) : i3 / 2 == 0 ? DynamicHomeScrollerView.this.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin) : DynamicHomeScrollerView.this.getResources().getDimensionPixelSize(R.dimen.home_item_paddding);
                    if (i4 != 0) {
                        viewHolder.itemView.setPadding(i, 0, DynamicHomeScrollerView.this.d, 0);
                    } else {
                        viewHolder.itemView.setPadding(i, 0, 0, 0);
                    }
                    BusinessObject businessObject = (BusinessObject) arrayList3.get(i3);
                    View view = null;
                    if (businessObject instanceof Item) {
                        Item item = (Item) businessObject;
                        if (item.getEntityType() != null) {
                            if (item.getEntityType().equals(c.e) || item.getEntityType().equals(c.h)) {
                                com.managers.e.a().a(viewHolder.itemView, DynamicHomeScrollerView.this.mContext, item);
                            }
                            if (item.getEntityType().equals(c.c)) {
                                if (viewHolder instanceof SocialHomeGridHolder) {
                                    socialSongsItemView = new SocialSongsItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment);
                                } else {
                                    socialSongsItemView = new DownloadSongsItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment);
                                    socialSongsItemView.setUniqueID(aVar3.y());
                                }
                                socialSongsItemView.setSourceName(aVar3.p());
                                socialSongsItemView.setGAData(aVar3.p(), aVar3.k(), i3 + 1);
                                if (aVar3.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || aVar3.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal() || aVar3.e() == VIEW_SIZE.SCROLL_RECTANGLE_DISCOVER.getNumVal() || aVar3.e() == VIEW_SIZE.TAGS.getNumVal()) {
                                    socialSongsItemView.setItemWithoutText(true);
                                } else {
                                    socialSongsItemView.setItemWithoutText(false);
                                }
                                socialSongsItemView.setLightsOn(DynamicHomeScrollerView.this.a(aVar3));
                                socialSongsItemView.setSongsListBusinessObject(arrayList3);
                                socialSongsItemView.setIsSongSection();
                                view = socialSongsItemView.getGridItemViewforDynamicView(viewHolder, businessObject, aVar3);
                            } else if (item.getEntityType().equals(c.h)) {
                                view = DynamicHomeScrollerView.this.c.getAdView(i3, viewHolder, businessObject, (ViewGroup) viewHolder.itemView.getParent(), aVar3.k());
                            } else if (viewHolder.getItemViewType() == 915) {
                                new StaggeredGridItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment).getPoplatedGenericView(i3, viewHolder, businessObject, (ViewGroup) viewHolder.itemView.getParent(), aVar3.k());
                            } else {
                                GenericItemView genericItemView = new GenericItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment);
                                if (aVar3.e() == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal() || aVar3.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal() || aVar3.e() == VIEW_SIZE.SCROLL_RECTANGLE_DISCOVER.getNumVal() || aVar3.e() == VIEW_SIZE.TAGS.getNumVal()) {
                                    genericItemView.setItemWithoutText(Boolean.valueOf(true));
                                } else {
                                    genericItemView.setItemWithoutText(Boolean.valueOf(false));
                                }
                                genericItemView.setLightsOn(DynamicHomeScrollerView.this.a(aVar3));
                                genericItemView.setSourceName(aVar3.p());
                                genericItemView.setUniqueID(aVar3.y());
                                view = genericItemView.getPoplatedGenericView(i3, viewHolder, businessObject, (ViewGroup) viewHolder.itemView.getParent(), aVar3.k(), aVar3);
                            }
                            return view;
                        }
                    }
                    if (businessObject instanceof OfflineTrack) {
                        if (businessObject.getBusinessObjId().equals("909")) {
                            view = viewHolder.itemView;
                            if (arrayList3 != null && arrayList3.size() == 1) {
                                viewHolder.itemView.setPadding(0, 0, 0, 0);
                            }
                        } else {
                            socialSongsItemView = new DownloadSongsItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment);
                            socialSongsItemView.setGAData(aVar3.p(), aVar3.k(), i3 + 1);
                            socialSongsItemView.setSourceName(aVar3.p());
                            socialSongsItemView.setUniqueID(aVar3.y());
                            if (arrayList4 == null || arrayList4.size() <= 0) {
                                socialSongsItemView.setSongsListBusinessObject(arrayList3);
                            } else {
                                socialSongsItemView.setSongsListBusinessObject(arrayList4);
                            }
                            socialSongsItemView.setIsSongSection();
                            view = socialSongsItemView.getPopulatedOfflineTrackView(viewHolder, businessObject, aVar3.k());
                        }
                    } else if (businessObject instanceof JukePlaylist) {
                        JukePlaylist jukePlaylist = (JukePlaylist) businessObject;
                        view = new JukePlaylistItemView(DynamicHomeScrollerView.this.mContext, DynamicHomeScrollerView.this.mFragment, aVar3.k()).getPopulatedView(viewHolder, jukePlaylist);
                        if (!TextUtils.isEmpty(jukePlaylist.getNickName())) {
                            JukeSessionManager.getInstance().setUserNick(jukePlaylist.getNickName());
                        }
                    }
                    return view;
                }

                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    if (i == 910) {
                        return new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(h.a(aVar3), viewGroup, false));
                    }
                    if (i == 915) {
                        return new StaggeredGridViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_made_for_you, viewGroup, false));
                    }
                    if (i == R.layout.item_playlist_grid_ad_140x140 || i == R.layout.item_playlist_grid_ad_120x120 || i == R.layout.item_playlist_grid_ad_200x200 || i == R.layout.item_playlist_grid_ad_221x221) {
                        return new PlaylistGridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
                    }
                    return viewHolder;
                }
            });
        } else if (aVar2.n() == VIEW_SUBTYPE.CHAMELEON.getNumVal()) {
            a((ViewHolder) aVar);
        }
        if (this.p.a()) {
            this.p.a(false);
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            long b = this.p.b();
            if (b != 0) {
                long j = timeInMillis - b;
                String str = "Load";
                if (businessObject != null && businessObject.isFromNetwork()) {
                    str = "Load - Network";
                }
                StringBuilder stringBuilder;
                if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Home ");
                    stringBuilder.append(aVar2.k());
                    Constants.a(str, j, "Page", stringBuilder.toString());
                } else if (this.mFragment instanceof RadioActivityFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Radio ");
                    stringBuilder.append(aVar2.k());
                    Constants.a(str, j, "Page", stringBuilder.toString());
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ec  */
    private boolean c(com.gaana.models.BusinessObject r24, java.util.ArrayList<?> r25, com.dynamicview.DynamicHomeScrollerView.a r26, com.dynamicview.f.a r27, java.util.ArrayList r28, int r29) {
        /*
        r23 = this;
        r10 = r23;
        r0 = r26;
        r11 = r0.e;
        r12 = 0;
        if (r11 != 0) goto L_0x000a;
    L_0x0009:
        return r12;
    L_0x000a:
        r1 = 0;
        r2 = r27.q();
        r2 = android.text.TextUtils.isEmpty(r2);
        r3 = -1;
        if (r2 != 0) goto L_0x0021;
    L_0x0017:
        r2 = r27.q();
        r5 = java.lang.Long.parseLong(r2);
        r7 = r5;
        goto L_0x0022;
    L_0x0021:
        r7 = r3;
    L_0x0022:
        r2 = r27.B();
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x0030;
    L_0x002c:
        r1 = r27.B();
    L_0x0030:
        r5 = r1;
        r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));
        if (r1 != 0) goto L_0x0037;
    L_0x0035:
        if (r5 == 0) goto L_0x006f;
    L_0x0037:
        r1 = com.managers.ap.a();
        r1 = r1.d();
        if (r1 != 0) goto L_0x006f;
    L_0x0041:
        r1 = new com.gaana.models.AdsUJData;
        r1.<init>();
        r2 = r27.p();
        r1.setSectionName(r2);
        r2 = r27.y();
        r1.setSectionId(r2);
        r13 = com.managers.an.a();
        r14 = "ad";
        r15 = "";
        r16 = "";
        r17 = "ad_section_load";
        r18 = r1.getSectionId();
        r19 = "end";
        r20 = r1.getSectionIndex();
        r21 = "";
        r13.e(r14, r15, r16, r17, r18, r19, r20, r21);
    L_0x006f:
        if (r25 == 0) goto L_0x00ce;
    L_0x0071:
        r1 = r25.size();
        if (r1 <= 0) goto L_0x00ce;
    L_0x0077:
        r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));
        r1 = 1;
        if (r0 != 0) goto L_0x0085;
    L_0x007c:
        if (r5 != 0) goto L_0x0085;
    L_0x007e:
        r0 = r25.size();
        r14 = r0;
        r13 = r12;
        goto L_0x008c;
    L_0x0085:
        r0 = r25.size();
        r0 = r0 + r1;
        r14 = r0;
        r13 = r1;
    L_0x008c:
        r0 = r10.p;
        r6 = r0.d();
        r0 = new com.dynamicview.DynamicHomeScrollerView$8;
        r15 = r27;
        r0.<init>(r11, r15);
        r11.addOnScrollListener(r0);
        r0 = r27.m();
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.staggered_grid;
        r1 = r1.name();
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x00b4;
    L_0x00ac:
        r0 = r27.n();
        r11.setViewSubType(r0);
        goto L_0x00b9;
    L_0x00b4:
        r0 = 915; // 0x393 float:1.282E-42 double:4.52E-321;
        r11.setViewSubType(r0);
    L_0x00b9:
        r9 = r10.b;
        r4 = new com.dynamicview.DynamicHomeScrollerView$9;
        r0 = r4;
        r1 = r10;
        r2 = r25;
        r3 = r15;
        r12 = r4;
        r4 = r13;
        r10 = r9;
        r9 = r28;
        r0.<init>(r2, r3, r4, r5, r6, r7, r9);
        r11.setViewRecycleListner(r10, r14, r13, r12);
        goto L_0x00e2;
    L_0x00ce:
        r15 = r27;
        r1 = r27.n();
        r2 = com.constants.Constants.VIEW_SUBTYPE.CHAMELEON;
        r2 = r2.getNumVal();
        if (r1 != r2) goto L_0x00e2;
    L_0x00dc:
        r1 = r23;
        r1.a(r0);
        goto L_0x00e4;
    L_0x00e2:
        r1 = r23;
    L_0x00e4:
        r0 = r1.p;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0155;
    L_0x00ec:
        r0 = r1.p;
        r2 = 0;
        r0.a(r2);
        r0 = java.util.Calendar.getInstance();
        r2 = r0.getTimeInMillis();
        r0 = r1.p;
        r4 = r0.b();
        r6 = 0;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0155;
    L_0x0106:
        r6 = r2 - r4;
        r0 = "Load";
        if (r24 == 0) goto L_0x0114;
    L_0x010c:
        r2 = r24.isFromNetwork();
        if (r2 == 0) goto L_0x0114;
    L_0x0112:
        r0 = "Load - Network";
    L_0x0114:
        r2 = r1.mFragment;
        r2 = r2 instanceof com.dynamicview.DynamicHomeFragment;
        if (r2 == 0) goto L_0x0135;
    L_0x011a:
        r2 = "Page";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Home ";
        r3.append(r4);
        r4 = r27.k();
        r3.append(r4);
        r3 = r3.toString();
        com.constants.Constants.a(r0, r6, r2, r3);
        goto L_0x0155;
    L_0x0135:
        r2 = r1.mFragment;
        r2 = r2 instanceof com.fragments.RadioActivityFragment;
        if (r2 == 0) goto L_0x0155;
    L_0x013b:
        r2 = "Page";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Radio ";
        r3.append(r4);
        r4 = r27.k();
        r3.append(r4);
        r3 = r3.toString();
        com.constants.Constants.a(r0, r6, r2, r3);
    L_0x0155:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dynamicview.DynamicHomeScrollerView.c(com.gaana.models.BusinessObject, java.util.ArrayList, com.dynamicview.DynamicHomeScrollerView$a, com.dynamicview.f$a, java.util.ArrayList, int):boolean");
    }

    public void setIsToBeRefreshed(boolean z) {
        if (this.p != null) {
            this.p.c(true);
            this.p.b(true);
        }
    }

    public void setPositionToBeRefreshed(int i, boolean z) {
        if (this.p != null && z) {
            this.p.c(true);
            this.p.b(true);
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.f55header.text /*2131297292*/:
            case R.id.seeall /*2131298341*/:
            case R.id.seeallImg /*2131298342*/:
            case R.id.view1 /*2131298846*/:
            case R.id.view_all_container /*2131298863*/:
                Object tag = view.getTag();
                this.mAppState.setPlayoutSectionName(this.mDynamicView.p());
                if (tag instanceof b) {
                    b bVar = (b) tag;
                    com.dynamicview.f.a a = bVar.a();
                    int b = bVar.b();
                    if (a.m().equals(DynamicViewType.download.name())) {
                        b();
                        an.a().a("click", "en", this.mDynamicView.y(), an.a().a(an.a().a), "SEEALL", "", "", "");
                        return;
                    } else if ((this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof RadioActivityFragment) || (this.mFragment instanceof MyMusicFragment) || (this.mFragment instanceof MoreRadioActivityFragment)) {
                        BaseActivity baseActivity = (BaseActivity) this.mContext;
                        String str = ((BaseActivity) this.mContext).currentScreen;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(a.u());
                        stringBuilder.append(" click ");
                        baseActivity.sendGAEvent(str, stringBuilder.toString(), "See More");
                        if (a.n() != VIEW_SUBTYPE.SOCIAL.getNumVal() || TextUtils.isEmpty(a.o())) {
                            a(a(a, b), a);
                        } else {
                            com.services.c.a(this.mContext).a(this.mContext, a.o(), GaanaApplication.getInstance());
                        }
                        q.a().a("Home", this.mDynamicView.u());
                        an.a().a("click", "en", this.mDynamicView.y(), an.a().a(an.a().a), "SEEALL", "", "", "");
                        return;
                    } else {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private void b() {
        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.DOWNLOADS.name());
        ((GaanaActivity) this.mContext).displayFragment(new DownloadDetailsFragment());
    }

    private void a(URLManager uRLManager, com.dynamicview.f.a aVar) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (uRLManager != null && !TextUtils.isEmpty(aVar.o())) {
            if (aVar.g() != null) {
                uRLManager.a(Integer.parseInt(aVar.g()));
            }
            String r = aVar.r();
            if (!TextUtils.isEmpty(aVar.k())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("collection:");
                stringBuilder.append(aVar.k());
                q.a().a("int", stringBuilder.toString());
            }
            if (TextUtils.isEmpty(r) || r.equals(DynamicViewType.grid_rect.name()) || r.equals(DynamicViewType.grid.name())) {
                BaseGaanaFragment gridActivityFragment = new GridActivityFragment();
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_VIEW_TYPE_SEE_ALL", r);
                bundle.putParcelable("EXTRA_URL_MANAGER", uRLManager);
                bundle.putBoolean("EXTRA_SHOW_LOADMORE", aVar.t());
                bundle.putString("EXTRA_GASECTION_NAME", aVar.p());
                bundle.putString("EXTRA_ACTIONBAR_TITLE", this.n);
                bundle.putString("EXTRA_GA_TITLE", aVar.k());
                bundle.putString("EXTRA_GRID_SEE_ALL_AD_CODE", aVar.i());
                bundle.putString("SEE_ALL_BANNER_AD_CODE", aVar.h());
                bundle.putString("EXTRA_DYNAMIC_SECTION_UID", aVar.y());
                bundle.putString("EXTRA_SOURCE_NAME", aVar.p());
                if (aVar.j() != null && ap.a().b(this.mContext)) {
                    if (aVar.j().get("url_logo_banner") != null) {
                        bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", (String) aVar.j().get("url_logo_banner"));
                    }
                    if (aVar.j().get("bottom_banner_off") != null) {
                        bundle.putBoolean("SEE_ALL_BOTTOM_BANNER_OFF", ((String) aVar.j().get("bottom_banner_off")).equals("1"));
                    }
                }
                if (this.j != null) {
                    bundle.putString("EXTRA_BRAND_CTN_TRACKER", this.j);
                }
                if (this.h != null) {
                    bundle.putString("EXTRA_BRAND_DFP_TRACKER", this.i);
                }
                String str = null;
                if (aVar.j() != null) {
                    str = (String) aVar.j().get("video_ad_seeall");
                }
                if (str != null) {
                    bundle.putString("SEE_ALL_VIDEO_AD_CODE", str);
                }
                gridActivityFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(gridActivityFragment);
            } else {
                BaseGaanaFragment songParallexListingFragment = new SongParallexListingFragment();
                ListingParams listingParams = new ListingParams();
                listingParams.e(false);
                listingParams.f(true);
                listingParams.h(false);
                listingParams.d(true);
                listingParams.i(false);
                listingParams.a(true);
                listingParams.a(aVar.i());
                listingParams.b(aVar.p());
                ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                listingButton.b(aVar.w());
                listingButton.a(aVar.w());
                URLManager c = listingButton.c();
                c.g(true);
                c.a(uRLManager.k());
                c.d(false);
                c.a(true);
                c.a(BusinessObjectType.GenericItems);
                uRLManager.h(true);
                listingParams.a(listingButton);
                songParallexListingFragment.a(listingParams);
                ListingComponents listingComponents = new ListingComponents();
                new ArrayList().add(listingButton);
                this.mAppState.setListingComponents(listingComponents);
                Bundle bundle2 = new Bundle();
                if (aVar.j() != null && ap.a().b(this.mContext)) {
                    if (aVar.j().get("url_logo_banner") != null) {
                        bundle2.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", (String) aVar.j().get("url_logo_banner"));
                    }
                    if (aVar.j().get("bottom_banner_off") != null) {
                        bundle2.putBoolean("SEE_ALL_BOTTOM_BANNER_OFF", ((String) aVar.j().get("bottom_banner_off")).equals("1"));
                    }
                }
                if (this.j != null) {
                    bundle2.putString("EXTRA_BRAND_CTN_TRACKER", this.j);
                }
                if (this.h != null) {
                    bundle2.putString("EXTRA_BRAND_DFP_TRACKER", this.i);
                }
                bundle2.putString("EXTRA_DYNAMIC_SECTION_UID", aVar.y());
                bundle2.putString("EXTRA_SOURCE_NAME", aVar.p());
                if (!(aVar.j() == null || aVar.j().get("vpl_type") == null)) {
                    bundle2.putString("EXTRA_VPL_TYPE", (String) aVar.j().get("vpl_type"));
                }
                songParallexListingFragment.setArguments(bundle2);
                ((GaanaActivity) this.mContext).displayFragment(songParallexListingFragment);
            }
        }
    }

    public void a(BusinessObject businessObject, com.dynamicview.f.a aVar, int i) {
        ArrayList arrayList;
        if (aVar.l().contains("dummy")) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(businessObject.getArrListBusinessObj());
            if (this.p.a()) {
                businessObject.getArrListBusinessObj().add(0, new OfflineTrack("909", null, null));
            }
            if (this.m.e != null) {
                this.m.e.a(true);
            }
            arrayList = arrayList2;
        } else {
            if (this.m.e != null) {
                this.m.e.a(false);
            }
            arrayList = null;
        }
        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
            a(businessObject, null, this.m, aVar, null, i);
            return;
        }
        int i2 = -1;
        if (businessObject instanceof Items) {
            i2 = ((Items) businessObject).getEntityParentId();
        }
        b bVar = new b(aVar, i2);
        this.m.d.setTag(bVar);
        this.m.c.setTag(bVar);
        this.m.i.setTag(bVar);
        this.m.d.setOnClickListener(this);
        this.m.c.setOnClickListener(this);
        this.m.i.setOnClickListener(this);
        a(businessObject, businessObject.getArrListBusinessObj(), this.m, aVar, arrayList, i);
    }

    public void b(BusinessObject businessObject, com.dynamicview.f.a aVar, int i) {
        ThrowableExtension.printStackTrace(businessObject.getVolleyError());
        a aVar2 = this.m;
        if (this.p.h() != null) {
            a(this.p.h(), aVar, i);
        } else if (!a(businessObject, aVar2, aVar)) {
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public String toString() {
        if (this.mDynamicView == null) {
            return super.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mDynamicView.m());
        stringBuilder.append(this.mDynamicView.k());
        return stringBuilder.toString();
    }

    private boolean c() {
        return (this.mDynamicView.j() == null || TextUtils.isEmpty((CharSequence) this.mDynamicView.j().get("free_download_img")) || !a()) ? false : true;
    }
}

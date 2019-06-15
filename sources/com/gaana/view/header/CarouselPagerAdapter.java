package com.gaana.view.header;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.SuperscriptSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.android.volley.VolleyError;
import com.bumptech.glide.e;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.a.i;
import com.bumptech.glide.request.f;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.GaanaThemeModel.GaanaTheme;
import com.gaana.models.Item;
import com.gaana.models.LiveCricketData.Data;
import com.gaana.models.Radios.Radio;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.CarouselItemView;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.i.e.b;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.af;
import com.managers.an;
import com.managers.s;
import com.managers.u;
import com.payu.custombrowser.util.CBConstant;
import com.services.l.be;
import com.services.l.r;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.List;

public class CarouselPagerAdapter extends Adapter<ViewHolder> implements OnClickListener, be {
    private static final int VIEW_TYPE_CRICKET_ITEM = 4;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_VIDEO_AD = 2;
    private static final int VIEW_TYPE_VIDEO_ITEM = 5;
    List<String> adImpressionsToNotify = new ArrayList();
    private UnifiedNativeAd adItem;
    private ArrayList<Item> carouselData;
    private String commOneId;
    private String commTwoId;
    private final int dp15;
    private final int dp20;
    private CarouselItemView homeCarouselListener;
    private LayoutInflater inflater;
    private boolean isCarouselLightEnabled;
    private boolean isFirstAdItemNotified = false;
    private Context mContext;
    private int mCount;
    private int mLayoutId = -1;
    private int mVideoLayoutId = -1;
    private String url;

    public class TagObject {
        Item item;
        int position;

        public TagObject(Item item, int i) {
            this.item = item;
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public int getItemPosition(Object obj) {
        return 0;
    }

    public void setADItem(UnifiedNativeAd unifiedNativeAd) {
        this.adItem = unifiedNativeAd;
    }

    public List<String> getAdImpressionsToNotify() {
        return this.adImpressionsToNotify;
    }

    public CarouselPagerAdapter(Context context, ArrayList<Item> arrayList) {
        this.carouselData = arrayList;
        this.mContext = context;
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.dp15 = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp7);
        this.dp20 = this.mContext.getResources().getDimensionPixelOffset(R.dimen.page_left_right_margin);
    }

    public void setCarouselData(ArrayList<Item> arrayList, int i, boolean z) {
        this.carouselData = arrayList;
        this.isCarouselLightEnabled = z;
        this.mCount = i;
        notifyDataSetChanged();
        this.adImpressionsToNotify.clear();
    }

    public void setCarouselLightMode(boolean z) {
        this.isCarouselLightEnabled = z;
    }

    public void setCarouselListener(CarouselItemView carouselItemView) {
        this.homeCarouselListener = carouselItemView;
    }

    private int dipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public int getCount() {
        return this.mCount;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i, int i2) {
        Object obj = viewGroup;
        int i3 = i;
        final int i4 = i2;
        UnifiedNativeAdView unifiedNativeAdView;
        if (i3 != 2) {
            int i5 = 1;
            int i6 = 0;
            ImageView imageView;
            ArrayList arrayList;
            Item item;
            f placeholder;
            String str;
            int i7;
            String key;
            TagObject tagObject;
            if (i3 == 4) {
                imageView = (CrossFadeImageView) obj.findViewById(R.id.carouselImage);
                handleThemeLightMode((ImageView) obj.findViewById(R.id.carouselImageLightOverlay));
                if (!(this.carouselData == null || this.carouselData.size() - 1 < i4 || ((Item) this.carouselData.get(i4)).getEntityInfo() == null)) {
                    arrayList = (ArrayList) ((Item) this.carouselData.get(i4)).getEntityInfo();
                    item = (Item) this.carouselData.get(i4);
                    int size = arrayList != null ? ((Item) this.carouselData.get(i4)).getEntityInfo().size() : 0;
                    placeholder = new f().placeholder(imageView.getDrawable());
                    if (size > 0) {
                        str = null;
                        for (i7 = 0; i7 < size; i7++) {
                            key = ((EntityInfo) arrayList.get(i7)).getKey();
                            if (key.equals("artwork_alt")) {
                                str = (String) ((EntityInfo) arrayList.get(i7)).getValue();
                            }
                            if (key.equals("atw_bg")) {
                                Object g = Util.g(this.mContext, (String) ((EntityInfo) arrayList.get(i7)).getValue());
                                if (g == null) {
                                    g = str;
                                }
                                e.c(this.mContext.getApplicationContext()).load(g).apply(placeholder).listener(new com.bumptech.glide.request.e<Drawable>() {
                                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, i<Drawable> iVar, boolean z) {
                                        return false;
                                    }

                                    public boolean onResourceReady(Drawable drawable, Object obj, i<Drawable> iVar, DataSource dataSource, boolean z) {
                                        if (i4 <= CarouselPagerAdapter.this.carouselData.size() - 1) {
                                            Item item = (Item) CarouselPagerAdapter.this.carouselData.get(i4);
                                            if (item.getEntityType().equalsIgnoreCase(c.h) && i4 == 0 && !CarouselPagerAdapter.this.isFirstAdItemNotified) {
                                                CarouselPagerAdapter.this.homeCarouselListener.impressionHandler(item, i4);
                                                CarouselPagerAdapter.this.isFirstAdItemNotified = true;
                                            }
                                        }
                                        return false;
                                    }
                                }).into(imageView);
                            } else if (key.equals("poll_api")) {
                                this.url = (String) ((EntityInfo) arrayList.get(i7)).getValue();
                                ad.a(this.mContext).a((String) ((EntityInfo) arrayList.get(i7)).getValue(), this, (ViewGroup) obj);
                            } else if (key.equals("comm_desc_1")) {
                                ((TextView) obj.findViewById(R.id.text_comm)).setVisibility(0);
                                ((TextView) obj.findViewById(R.id.english_comm)).setVisibility(0);
                                ((TextView) obj.findViewById(R.id.english_comm)).setText(((EntityInfo) arrayList.get(i7)).getValue().toString());
                            } else if (key.equals("comm_desc_2")) {
                                ((TextView) obj.findViewById(R.id.text_comm)).setVisibility(0);
                                ((TextView) obj.findViewById(R.id.hindi_comm)).setVisibility(0);
                                ((TextView) obj.findViewById(R.id.hindi_comm)).setText(((EntityInfo) arrayList.get(i7)).getValue().toString());
                            } else if (key.equals("comm_id_1")) {
                                this.commOneId = ((EntityInfo) arrayList.get(i7)).getValue().toString();
                            } else if (key.equals("comm_id_2")) {
                                this.commTwoId = ((EntityInfo) arrayList.get(i7)).getValue().toString();
                            } else if (key.equals("atw_logo")) {
                                e.c(this.mContext.getApplicationContext()).load(((EntityInfo) arrayList.get(i7)).getValue().toString()).apply(placeholder).into((CrossFadeImageView) obj.findViewById(R.id.ads));
                            }
                        }
                    }
                }
            } else if (i3 == 5) {
                imageView = (CrossFadeImageView) obj.findViewById(R.id.carouselImage);
                handleThemeLightMode((ImageView) obj.findViewById(R.id.carouselImageLightOverlay));
                if (!(this.carouselData == null || this.carouselData.size() - 1 < i4 || ((Item) this.carouselData.get(i4)).getEntityInfo() == null)) {
                    Item item2 = (Item) this.carouselData.get(i4);
                    if (((ArrayList) ((Item) this.carouselData.get(i4)).getEntityInfo()) != null) {
                        ((Item) this.carouselData.get(i4)).getEntityInfo().size();
                    }
                    ImageView imageView2 = (ImageView) obj.findViewById(R.id.play_icon);
                    if (item2.getEntityType().equals(c.f)) {
                        imageView2.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_video_indicator));
                    } else {
                        imageView2.setVisibility(4);
                    }
                    YouTubeVideo populateVideoObject = populateVideoObject(this.carouselData, i4);
                    e.c(this.mContext.getApplicationContext()).load(populateVideoObject.d()).apply(new f().placeholder(imageView.getDrawable())).listener(new com.bumptech.glide.request.e<Drawable>() {
                        public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, i<Drawable> iVar, boolean z) {
                            return false;
                        }

                        public boolean onResourceReady(Drawable drawable, Object obj, i<Drawable> iVar, DataSource dataSource, boolean z) {
                            if (i4 <= CarouselPagerAdapter.this.carouselData.size() - 1) {
                                Item item = (Item) CarouselPagerAdapter.this.carouselData.get(i4);
                                if (item.getEntityType().equalsIgnoreCase(c.h) && i4 == 0 && !CarouselPagerAdapter.this.isFirstAdItemNotified) {
                                    CarouselPagerAdapter.this.homeCarouselListener.impressionHandler(item, i4);
                                    CarouselPagerAdapter.this.isFirstAdItemNotified = true;
                                }
                            }
                            return false;
                        }
                    }).into(imageView);
                    if (populateVideoObject.h() > 0) {
                        bindAutoPlayVideoItem(populateVideoObject, obj);
                    }
                    tagObject = new TagObject((Item) this.carouselData.get(i4), i4);
                    obj.setTag(tagObject);
                    obj.setOnClickListener(this);
                    if (Constants.aW && (item2.getEntityType().equals(c.b) || item2.getEntityType().equals(c.a))) {
                        imageView2.setOnClickListener(this);
                        imageView2.setTag(tagObject);
                    }
                }
            } else {
                imageView = (CrossFadeImageView) obj.findViewById(R.id.carouselImage);
                handleThemeLightMode((ImageView) obj.findViewById(R.id.carouselImageLightOverlay));
                if (!(this.carouselData == null || this.carouselData.size() - 1 < i4 || ((Item) this.carouselData.get(i4)).getEntityInfo() == null)) {
                    arrayList = (ArrayList) ((Item) this.carouselData.get(i4)).getEntityInfo();
                    item = (Item) this.carouselData.get(i4);
                    if (!(item == null || item.getEntityType() == null || (!item.getEntityType().equalsIgnoreCase(c.e) && !item.getEntityType().equalsIgnoreCase(c.h)))) {
                        key = Util.a(item.getEntityInfo());
                        if (!TextUtils.isEmpty(key)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Position (");
                            stringBuilder.append(i4);
                            stringBuilder.append(") -");
                            stringBuilder.append(item.getEntityType());
                            stringBuilder.append("- (");
                            stringBuilder.append(item.getName());
                            stringBuilder.append(")");
                            u.a().a(key, "View", stringBuilder.toString());
                        }
                        com.managers.e.a().a((View) obj, this.mContext, item);
                    }
                    i7 = arrayList != null ? ((Item) this.carouselData.get(i4)).getEntityInfo().size() : 0;
                    if (!(item.getEntityType().equals(c.f) || item.getEntityType().equals(c.c) || ((item.getEntityType().equals(c.b) || item.getEntityType().equals(c.a)) && Constants.aW))) {
                        i5 = 0;
                    }
                    ImageView imageView3 = (ImageView) obj.findViewById(R.id.play_icon);
                    if (i5 != 0) {
                        imageView3.setVisibility(0);
                        if (item.getEntityType().equals(c.f)) {
                            imageView3.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_video_indicator));
                        }
                    } else {
                        imageView3.setVisibility(4);
                    }
                    placeholder = new f().placeholder(imageView.getDrawable());
                    if (i7 > 0) {
                        String str2 = null;
                        while (i6 < i7) {
                            str = ((EntityInfo) arrayList.get(i6)).getKey();
                            if (str.equals("artwork_alt")) {
                                str2 = (String) ((EntityInfo) arrayList.get(i6)).getValue();
                            }
                            if (str.equals("atw_alt")) {
                                Object g2 = Util.g(this.mContext, (String) ((EntityInfo) arrayList.get(i6)).getValue());
                                if (g2 == null) {
                                    g2 = str2;
                                }
                                e.c(this.mContext.getApplicationContext()).load(g2).apply(placeholder).listener(new com.bumptech.glide.request.e<Drawable>() {
                                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, i<Drawable> iVar, boolean z) {
                                        return false;
                                    }

                                    public boolean onResourceReady(Drawable drawable, Object obj, i<Drawable> iVar, DataSource dataSource, boolean z) {
                                        if (i4 <= CarouselPagerAdapter.this.carouselData.size() - 1) {
                                            Item item = (Item) CarouselPagerAdapter.this.carouselData.get(i4);
                                            if (item.getEntityType().equalsIgnoreCase(c.h) && i4 == 0 && !CarouselPagerAdapter.this.isFirstAdItemNotified) {
                                                CarouselPagerAdapter.this.homeCarouselListener.impressionHandler(item, i4);
                                                CarouselPagerAdapter.this.isFirstAdItemNotified = true;
                                            }
                                        }
                                        return false;
                                    }
                                }).into(imageView);
                                tagObject = new TagObject((Item) this.carouselData.get(i4), i4);
                                obj.setTag(tagObject);
                                obj.setOnClickListener(this);
                                if (Constants.aW && (item.getEntityType().equals(c.b) || item.getEntityType().equals(c.a))) {
                                    imageView3.setOnClickListener(this);
                                    imageView3.setTag(tagObject);
                                }
                            } else if (str.equals("artwork_gif")) {
                                e.c(this.mContext.getApplicationContext()).asGif().load(((EntityInfo) arrayList.get(i6)).getValue().toString()).apply(placeholder).into(imageView);
                                tagObject = new TagObject((Item) this.carouselData.get(i4), i4);
                                obj.setTag(tagObject);
                                obj.setOnClickListener(this);
                                if (Constants.aW) {
                                    imageView3.setOnClickListener(this);
                                    imageView3.setTag(tagObject);
                                }
                            } else {
                                i6++;
                            }
                        }
                    }
                }
            }
        } else if (this.adItem.getVideoController().hasVideoContent()) {
            unifiedNativeAdView = (UnifiedNativeAdView) obj.findViewById(R.id.media_unified_view);
            unifiedNativeAdView.setMediaView((MediaView) obj.findViewById(R.id.media_view));
            unifiedNativeAdView.setNativeAd(this.adItem);
            return obj;
        } else {
            unifiedNativeAdView = new UnifiedNativeAdView(this.mContext);
            unifiedNativeAdView.addView(obj);
            unifiedNativeAdView.setCallToActionView(obj);
            unifiedNativeAdView.setNativeAd(this.adItem);
            obj = unifiedNativeAdView;
        }
        return obj;
    }

    public void setLayoutId(int i) {
        this.mLayoutId = i;
    }

    public void setVideoLayoutId(int i) {
        this.mVideoLayoutId = i;
    }

    public void showCricketCarouselScore(ViewGroup viewGroup, Data data) {
        final ViewGroup viewGroup2 = viewGroup;
        TextView textView = (TextView) viewGroup2.findViewById(R.id.match_title);
        textView.setText(data.getMatch_name());
        textView.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        TextView textView2 = (TextView) viewGroup2.findViewById(R.id.match_type);
        textView2.setText(data.getMatch_detail());
        textView2.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        TextView textView3 = (TextView) viewGroup2.findViewById(R.id.team_one);
        textView3.setTypeface(Util.i(this.mContext));
        TextView textView4 = (TextView) viewGroup2.findViewById(R.id.team_two);
        textView4.setTypeface(Util.i(this.mContext));
        Button button = (Button) viewGroup2.findViewById(R.id.english_comm);
        ((ProgressBar) viewGroup2.findViewById(R.id.ProgressBar)).setVisibility(8);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BusinessObject radio = new Radio();
                radio.setBusinessObjId(CarouselPagerAdapter.this.commOneId);
                ad.a(CarouselPagerAdapter.this.mContext).b("");
                ((Radio) radio).setType(d.c);
                PlayerManager.a(CarouselPagerAdapter.this.mContext).a(PlayerType.GAANA_RADIO, CarouselPagerAdapter.this.mContext);
                ((GaanaActivity) CarouselPagerAdapter.this.mContext).setUpdatePlayerFragment();
                com.services.c.a(CarouselPagerAdapter.this.mContext).a(CarouselPagerAdapter.this.mContext, radio, SOURCE_TYPE.PUSH_NOTIFICATION.ordinal());
            }
        });
        ((Button) viewGroup2.findViewById(R.id.hindi_comm)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BusinessObject radio = new Radio();
                radio.setBusinessObjId(CarouselPagerAdapter.this.commTwoId);
                ad.a(CarouselPagerAdapter.this.mContext).b("");
                ((Radio) radio).setType(d.c);
                PlayerManager.a(CarouselPagerAdapter.this.mContext).a(PlayerType.GAANA_RADIO, CarouselPagerAdapter.this.mContext);
                ((GaanaActivity) CarouselPagerAdapter.this.mContext).setUpdatePlayerFragment();
                com.services.c.a(CarouselPagerAdapter.this.mContext).a(CarouselPagerAdapter.this.mContext, radio, SOURCE_TYPE.PUSH_NOTIFICATION.ordinal());
            }
        });
        final ImageView imageView = (ImageView) viewGroup2.findViewById(R.id.retry);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                imageView.setVisibility(8);
                ((ProgressBar) viewGroup2.findViewById(R.id.ProgressBar)).setVisibility(0);
                ad.a(CarouselPagerAdapter.this.mContext).a(CarouselPagerAdapter.this.url, CarouselPagerAdapter.this, viewGroup2);
            }
        });
        textView3.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
        if (data.getBatting_team_number().equals("1")) {
            textView4.setText(data.getTeam1_info());
            textView3.setText(data.getTeam2_info());
        } else {
            textView4.setText(data.getTeam2_info());
            textView3.setText(data.getTeam1_info());
        }
        TextView textView5 = (TextView) viewGroup2.findViewById(R.id.batsmen1);
        TextView textView6 = (TextView) viewGroup2.findViewById(R.id.batsmen2);
        TextView textView7 = (TextView) viewGroup2.findViewById(R.id.score1);
        TextView textView8 = (TextView) viewGroup2.findViewById(R.id.score2);
        if (data.getOnstrike_batsmen_number() == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(data.getBatsmen1_name());
            stringBuilder.append(CBConstant.DEFAULT_PAYMENT_URLS);
            SpannableString spannableString = new SpannableString(stringBuilder.toString());
            spannableString.setSpan(new SuperscriptSpan(), spannableString.length() - 1, spannableString.length(), 33);
            textView5.setText(spannableString, BufferType.SPANNABLE);
            textView6.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
            textView6.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Regular.ttf"));
            textView5.setTypeface(Util.i(this.mContext));
            textView7.setText(data.getBatsmen1_info());
            textView8.setText(data.getBatsmen2_info());
            textView8.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
            textView8.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Regular.ttf"));
            textView7.setTypeface(Util.i(this.mContext));
            textView6.setText(data.getBatsmen2_name());
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(data.getBatsmen2_name());
            stringBuilder2.append(CBConstant.DEFAULT_PAYMENT_URLS);
            SpannableString spannableString2 = new SpannableString(stringBuilder2.toString());
            spannableString2.setSpan(new SuperscriptSpan(), spannableString2.length() - 1, spannableString2.length(), 0);
            textView6.setText(spannableString2, BufferType.SPANNABLE);
            textView5.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
            textView5.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Regular.ttf"));
            textView6.setTypeface(Util.i(this.mContext));
            textView7.setText(data.getBatsmen1_info());
            textView8.setText(data.getBatsmen2_info());
            textView7.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
            textView8.setTypeface(Util.i(this.mContext));
            textView7.setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Regular.ttf"));
            textView5.setText(data.getBatsmen1_name());
        }
        TextView textView9 = (TextView) viewGroup2.findViewById(R.id.overs);
        textView9.setText(data.getBatting_team_overs());
        textView9.setTypeface(Util.i(this.mContext));
        textView9.setTextColor(ContextCompat.getColor(this.mContext, R.color.white_alfa_60));
        if (!com.utilities.f.b(this.mContext).equalsIgnoreCase("English")) {
            Util.a(this.mContext, "English", 0, textView3);
            Util.a(this.mContext, "English", 0, textView4);
            Util.a(this.mContext, "English", 0, textView);
            Util.a(this.mContext, "English", 0, textView2);
            Util.a(this.mContext, "English", 0, textView5);
            Util.a(this.mContext, "English", 0, textView6);
            Util.a(this.mContext, "English", 0, textView8);
            Util.a(this.mContext, "English", 0, textView7);
            Util.a(this.mContext, "English", 0, textView9);
        }
    }

    private void handleThemeLightMode(final ImageView imageView) {
        if (!Constants.du || !this.isCarouselLightEnabled) {
            imageView.setVisibility(8);
        } else if (s.a().b() == null || Constants.dt == null) {
            imageView.setVisibility(8);
        } else {
            GaanaTheme gaanaTheme = Constants.dt;
            if (!TextUtils.isEmpty(gaanaTheme.getOverlayShowcaseArtwork())) {
                com.i.i.a().a(gaanaTheme.getOverlayShowcaseArtwork(), new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(Bitmap bitmap) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ScaleType.CENTER_CROP);
                            imageView.setVisibility(0);
                        }
                    }
                });
            }
        }
    }

    private void bindAutoPlayVideoItem(final YouTubeVideo youTubeVideo, ViewGroup viewGroup) {
        if (com.utilities.d.g() && GaanaApplication.getInstance().isVideoAutoplay()) {
            final CrossFadeImageView crossFadeImageView = (CrossFadeImageView) viewGroup.findViewById(R.id.carouselImage);
            final VideoPlayerAutoPlayView videoPlayerAutoPlayView = (VideoPlayerAutoPlayView) viewGroup.findViewById(R.id.carouselVideo);
            new com.player_framework.d(this.mContext).a((BusinessObject) youTubeVideo, youTubeVideo.e() == 2 ? "horz" : "vert", new b() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                /* JADX WARNING: Removed duplicated region for block: B:19:0x0056  */
                public void onDataRetrieved(java.lang.Object r2, int r3, boolean r4) {
                    /*
                    r1 = this;
                    r3 = r2 instanceof java.lang.String;
                    r4 = 1;
                    if (r3 == 0) goto L_0x0023;
                L_0x0005:
                    r3 = r2;
                    r3 = (java.lang.String) r3;
                    r0 = android.text.TextUtils.isEmpty(r3);
                    if (r0 != 0) goto L_0x0023;
                L_0x000e:
                    r2 = com.utilities.Util.k(r3);
                    r2 = android.text.TextUtils.isEmpty(r2);
                    if (r2 != 0) goto L_0x0022;
                L_0x0018:
                    r2 = r6;
                    r3 = com.utilities.Util.k(r3);
                    r2.setSource(r3);
                    goto L_0x0050;
                L_0x0022:
                    return;
                L_0x0023:
                    r3 = r2 instanceof com.google.gson.internal.LinkedTreeMap;
                    if (r3 == 0) goto L_0x008b;
                L_0x0027:
                    r2 = (com.google.gson.internal.LinkedTreeMap) r2;
                    if (r2 == 0) goto L_0x008a;
                L_0x002b:
                    r3 = "status";
                    r3 = r2.get(r3);
                    r3 = (java.lang.String) r3;
                    r3 = java.lang.Integer.parseInt(r3);
                    if (r3 != r4) goto L_0x008a;
                L_0x0039:
                    r3 = "data";
                    r2 = r2.get(r3);
                    r2 = (java.lang.String) r2;
                    r2 = com.utilities.Util.k(r2);
                    r3 = android.text.TextUtils.isEmpty(r2);
                    if (r3 != 0) goto L_0x0089;
                L_0x004b:
                    r3 = r6;
                    r3.setSource(r2);
                L_0x0050:
                    r2 = r5;
                    r2 = r2 instanceof com.youtube.YouTubeVideos.YouTubeVideo;
                    if (r2 == 0) goto L_0x0067;
                L_0x0056:
                    r2 = r6;
                    r3 = r5;
                    r3 = r3.getBusinessObjId();
                    r0 = r5;
                    r0 = r0.e();
                    r2.setVideoParams(r3, r0);
                L_0x0067:
                    r2 = r6;
                    r3 = com.gaana.view.header.CarouselPagerAdapter.this;
                    r3 = r3.mContext;
                    r3 = (com.gaana.GaanaActivity) r3;
                    r2.set_act(r3);
                    r2 = r6;
                    r3 = new com.gaana.view.header.CarouselPagerAdapter$8$1;
                    r3.<init>();
                    r2.setVideoStateChangeListener(r3);
                    r2 = r6;
                    r2.setVideoScalingMode(r4);
                    r2 = r6;
                    r2.g();
                    return;
                L_0x0089:
                    return;
                L_0x008a:
                    return;
                L_0x008b:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.header.CarouselPagerAdapter$AnonymousClass8.onDataRetrieved(java.lang.Object, int, boolean):void");
                }
            });
        }
    }

    private YouTubeVideo populateVideoObject(ArrayList<Item> arrayList, int i) {
        YouTubeVideo youTubeVideo = null;
        if (arrayList == null || arrayList.size() - 1 < i || ((Item) arrayList.get(i)).getEntityInfo() == null) {
            return null;
        }
        ArrayList arrayList2 = (ArrayList) ((Item) arrayList.get(i)).getEntityInfo();
        Item item = (Item) arrayList.get(i);
        int size = arrayList2 != null ? ((Item) arrayList.get(i)).getEntityInfo().size() : 0;
        YouTubeVideo youTubeVideo2 = new YouTubeVideo();
        youTubeVideo2.setBusinessObjId(((Item) arrayList.get(i)).getBusinessObjId());
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                String key = ((EntityInfo) arrayList2.get(i2)).getKey();
                if (key.equals("artwork_alt")) {
                    youTubeVideo = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                }
                if (key.equals("atw_alt")) {
                    key = Util.g(this.mContext, (String) ((EntityInfo) arrayList2.get(i2)).getValue());
                    if (key == null) {
                        key = youTubeVideo;
                    }
                    youTubeVideo2.d(key);
                } else if (key.equals("auto_play_time")) {
                    youTubeVideo2.a(Long.parseLong((String) ((EntityInfo) arrayList2.get(i2)).getValue()));
                }
                key = "";
                String str = "";
                String str2 = "";
                CharSequence charSequence = "";
                Object value;
                if (((EntityInfo) arrayList2.get(i2)).getKey().equals("horz_vd")) {
                    str = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("vert_vd")) {
                    key = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("url")) {
                    str2 = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("vid_id")) {
                    charSequence = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                    youTubeVideo2.c(charSequence);
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("video_id")) {
                    charSequence = (String) ((EntityInfo) arrayList2.get(i2)).getValue();
                    youTubeVideo2.setBusinessObjId(charSequence);
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("vd_expiry")) {
                    youTubeVideo2.e((String) ((EntityInfo) arrayList2.get(i2)).getValue());
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("v_cnt_src")) {
                    value = ((EntityInfo) arrayList2.get(i2)).getValue();
                    if (value instanceof String) {
                        youTubeVideo2.a(Double.parseDouble((String) value));
                    } else {
                        youTubeVideo2.a(((Double) value).doubleValue());
                    }
                } else if (((EntityInfo) arrayList2.get(i2)).getKey().equals("h_cnt_src")) {
                    value = ((EntityInfo) arrayList2.get(i2)).getValue();
                    if (value instanceof String) {
                        youTubeVideo2.b(Double.parseDouble((String) value));
                    } else {
                        youTubeVideo2.b(((Double) value).doubleValue());
                    }
                }
                if (!TextUtils.isEmpty(key)) {
                    youTubeVideo2.a(key);
                    youTubeVideo2.a(1);
                } else if (!TextUtils.isEmpty(str)) {
                    youTubeVideo2.a(str);
                    youTubeVideo2.a(2);
                } else if (!TextUtils.isEmpty(str2)) {
                    youTubeVideo2.a(str2);
                    youTubeVideo2.a(0);
                } else if (!TextUtils.isEmpty(charSequence)) {
                    youTubeVideo2.c(charSequence);
                    youTubeVideo2.a(0);
                }
            }
        }
        return youTubeVideo2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 2) {
            return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.carousel_media_layout, viewGroup, false));
        }
        switch (i) {
            case 4:
                return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.carousel_view_item_cricket, viewGroup, false));
            case 5:
                return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(this.mVideoLayoutId, viewGroup, false));
            default:
                return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(this.mLayoutId, viewGroup, false));
        }
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        instantiateItem((ViewGroup) viewHolder.itemView, viewHolder.getItemViewType(), i);
    }

    public int getItemViewType(int i) {
        if (this.carouselData.size() <= 0 || this.carouselData.size() - 1 < i || !((Item) this.carouselData.get(i)).getEntityType().equalsIgnoreCase("CTNAD")) {
            if (this.carouselData.size() > 0 && this.carouselData.size() - 1 >= i && ((Item) this.carouselData.get(i)).getEntityType().equalsIgnoreCase(c.n)) {
                return 4;
            }
            if (this.carouselData.size() > 0 && this.carouselData.size() - 1 >= i && ((Item) this.carouselData.get(i)).getEntityType().equalsIgnoreCase(c.f) && com.utilities.d.g() && GaanaApplication.getInstance().isVideoAutoplay()) {
                return 5;
            }
        } else if (this.adItem.getVideoController().hasVideoContent()) {
            return 2;
        }
        return 1;
    }

    public int getItemCount() {
        return this.mCount;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((View) obj);
    }

    public void onClick(View view) {
        if (this.homeCarouselListener != null) {
            if (view.getTag() instanceof TagObject) {
                TagObject tagObject = (TagObject) view.getTag();
                this.homeCarouselListener.setItemPosition(tagObject.getPosition());
                this.homeCarouselListener.onClickPerformed(view, tagObject.getItem());
                an.a().a("click", "ac", tagObject.getItem().getBusinessObjId(), "carousel", "", "", String.valueOf(this.carouselData.size()), String.valueOf(tagObject.getPosition()));
            }
        } else if (view.getId() == R.id.play_icon) {
            TagObject tagObject2 = (TagObject) view.getTag();
            if (tagObject2 != null) {
                af.a(this.mContext, ((GaanaActivity) this.mContext).getCurrentFragment()).a((int) R.id.playMenu, tagObject2.getItem());
            }
        }
    }

    private int getScreenWidthinDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }
}

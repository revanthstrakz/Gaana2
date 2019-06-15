package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track.Artist;
import com.library.controls.CrossFadeImageView;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.an;
import com.managers.ap.a;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerStatus.PlayerStates;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Map;

public class FavouriteSongsItemView extends SongsItemView implements OnClickListener {
    private int mLayoutResourceId = R.layout.view_item_list_row;
    private int mViewType = -1;

    public static class FavouriteSongsItemHolder extends ViewHolder {
        private CrossFadeImageView mCrossFadeImageIcon;
        private ImageView menuFavourite;
        private ImageView menuIndicator;
        private CrossFadeImageView menuThumb;
        private TextView tvSubtitle;
        private TextView tvTitle;

        public FavouriteSongsItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvTitle = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f53grid.item.tv.genere);
            this.menuFavourite = (ImageView) view.findViewById(R.id.favourite_item);
            this.menuThumb = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.menuIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
        }
    }

    public FavouriteSongsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mLayoutId = R.layout.view_item_list_row;
    }

    public View getEmptyView(View view, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return view == null ? inflateView(this.mLayoutResourceId, viewGroup) : view;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public String getArtistNames(Item item) {
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        String language = item.getLanguage();
        if (arrayList == null) {
            return str;
        }
        int size = arrayList.size();
        String str2 = str;
        for (int i = 0; i < size; i++) {
            if (((EntityInfo) arrayList.get(i)).getKey().equals("artist")) {
                ArrayList arrayList2 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                if (arrayList2 != null && arrayList2.size() > 0) {
                    String str3;
                    int i2;
                    if (arrayList2.get(0) instanceof Artist) {
                        arrayList2 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                        str3 = str2;
                        for (i2 = 0; i2 < arrayList2.size(); i2++) {
                            StringBuilder stringBuilder;
                            if (i2 == 0) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(str3);
                                stringBuilder.append(Constants.a(((Artist) arrayList2.get(i2)).name, language));
                                str3 = stringBuilder.toString();
                            } else {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(str3);
                                stringBuilder.append(", ");
                                stringBuilder.append(Constants.a(((Artist) arrayList2.get(i2)).name, language));
                                str3 = stringBuilder.toString();
                            }
                        }
                    } else {
                        str3 = str2;
                        for (i2 = 0; i2 < arrayList2.size(); i2++) {
                            Map map = (Map) arrayList2.get(i2);
                            StringBuilder stringBuilder2;
                            if (i2 == 0) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(str3);
                                stringBuilder2.append(Constants.a((String) map.get("name"), language));
                                str3 = stringBuilder2.toString();
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(str3);
                                stringBuilder2.append(", ");
                                stringBuilder2.append(Constants.a((String) map.get("name"), language));
                                str3 = stringBuilder2.toString();
                            }
                        }
                    }
                    str2 = str3;
                }
            }
        }
        return str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d4  */
    public android.view.View getPoplatedView(android.support.v7.widget.RecyclerView.ViewHolder r9, com.gaana.models.BusinessObject r10, android.view.ViewGroup r11) {
        /*
        r8 = this;
        r9 = (com.gaana.view.item.FavouriteSongsItemView.FavouriteSongsItemHolder) r9;
        r11 = r9.itemView;
        r8.mView = r11;
        r11 = r9.tvTitle;
        r0 = r9.tvSubtitle;
        r1 = r10 instanceof com.gaana.models.Item;
        r2 = 0;
        if (r1 == 0) goto L_0x00ec;
    L_0x0013:
        r1 = r10;
        r1 = (com.gaana.models.Item) r1;
        if (r1 == 0) goto L_0x001f;
    L_0x0018:
        r3 = r9.mCrossFadeImageIcon;
        r3.setTag(r1);
    L_0x001f:
        r3 = r9.mCrossFadeImageIcon;
        r3 = r3 instanceof com.gaana.view.item.SquareImageView;
        if (r3 == 0) goto L_0x003b;
    L_0x0027:
        r3 = r9.mCrossFadeImageIcon;
        r3 = (com.gaana.view.item.SquareImageView) r3;
        r4 = r1.getArtwork();
        r5 = r8.mAppState;
        r5 = r5.isAppInOfflineMode();
        r3.bindImage(r4, r5);
        goto L_0x0054;
    L_0x003b:
        r3 = r9.mCrossFadeImageIcon;
        r3 = r3 instanceof com.library.controls.CrossFadeImageView;
        if (r3 == 0) goto L_0x0054;
    L_0x0043:
        r3 = r9.mCrossFadeImageIcon;
        r4 = r1.getArtwork();
        r5 = r8.mAppState;
        r5 = r5.isAppInOfflineMode();
        r3.bindImage(r4, r5);
    L_0x0054:
        if (r11 == 0) goto L_0x005d;
    L_0x0056:
        r3 = r1.getName();
        r11.setText(r3);
    L_0x005d:
        if (r0 == 0) goto L_0x00ec;
    L_0x005f:
        r3 = r8.getArtistNames(r1);
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x0073;
    L_0x0069:
        r4 = "null";
        r4 = r3.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0073;
    L_0x0071:
        r3 = "";
    L_0x0073:
        r1 = r1.getEntityInfo();
        r1 = (java.util.ArrayList) r1;
        if (r1 == 0) goto L_0x00d0;
    L_0x007b:
        r4 = r1.size();
        r5 = r2;
    L_0x0080:
        if (r5 >= r4) goto L_0x00d0;
    L_0x0082:
        r6 = r1.get(r5);
        r6 = (com.gaana.models.EntityInfo) r6;
        r6 = r6.getKey();
        r7 = "parental_warning";
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x00cd;
    L_0x0094:
        r4 = r1.get(r5);
        r4 = (com.gaana.models.EntityInfo) r4;
        r4 = r4.getValue();
        r4 = r4 instanceof java.lang.Double;
        if (r4 == 0) goto L_0x00bc;
    L_0x00a2:
        r1 = r1.get(r5);
        r1 = (com.gaana.models.EntityInfo) r1;
        r1 = r1.getValue();
        r1 = (java.lang.Double) r1;
        r4 = r1.doubleValue();
        r6 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r1 = java.lang.Double.compare(r4, r6);
        if (r1 != 0) goto L_0x00d0;
    L_0x00ba:
        r1 = 1;
        goto L_0x00d1;
    L_0x00bc:
        r1 = r1.get(r5);
        r1 = (com.gaana.models.EntityInfo) r1;
        r1 = r1.getValue();
        r4 = "1";
        r1 = r1.equals(r4);
        goto L_0x00d1;
    L_0x00cd:
        r5 = r5 + 1;
        goto L_0x0080;
    L_0x00d0:
        r1 = r2;
    L_0x00d1:
        r4 = 0;
        if (r1 == 0) goto L_0x00e6;
    L_0x00d4:
        r1 = r8.mContext;
        r1 = r1.getResources();
        r5 = com.utilities.Util.Y();
        r1 = r1.getDrawable(r5);
        r0.setCompoundDrawablesWithIntrinsicBounds(r1, r4, r4, r4);
        goto L_0x00e9;
    L_0x00e6:
        r0.setCompoundDrawablesWithIntrinsicBounds(r4, r4, r4, r4);
    L_0x00e9:
        r0.setText(r3);
    L_0x00ec:
        r10 = (com.gaana.models.Item) r10;
        r0 = r9.menuFavourite;
        r1 = r10.getBusinessObjId();
        r0.setTag(r1);
        r1 = new com.gaana.view.item.FavouriteSongsItemView$1;
        r1.<init>(r0, r10);
        r0.setOnClickListener(r1);
        r1 = r10.isLocalMedia();
        if (r1 == 0) goto L_0x010d;
    L_0x0107:
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x0110;
    L_0x010d:
        r0.setVisibility(r2);
    L_0x0110:
        r1 = r10.isFavorite();
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x0121;
    L_0x011a:
        r1 = 2131232245; // 0x7f0805f5 float:1.8080594E38 double:1.0529686356E-314;
        r0.setImageResource(r1);
        goto L_0x013e;
    L_0x0121:
        r1 = r8.mContext;
        r2 = com.gaana.R.styleable.VectorDrawables;
        r1 = r1.obtainStyledAttributes(r2);
        r2 = r8.getContext();
        r3 = 49;
        r4 = -1;
        r3 = r1.getResourceId(r3, r4);
        r2 = android.support.v4.content.ContextCompat.getDrawable(r2, r3);
        r0.setImageDrawable(r2);
        r1.recycle();
    L_0x013e:
        r0 = r8.mView;
        r0.setOnClickListener(r8);
        r0 = r9.menuThumb;
        r8.setMusicPlayingAnimation(r0, r10, r11);
        r9 = r9.itemView;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.FavouriteSongsItemView.getPoplatedView(android.support.v7.widget.RecyclerView$ViewHolder, com.gaana.models.BusinessObject, android.view.ViewGroup):android.view.View");
    }

    public void setFavorite(final ImageView imageView, final Item item) {
        if (item != null) {
            item.setBusinessObjType(BusinessObjectType.Tracks);
            af a = af.a(this.mContext, null);
            a.a("Player Screen");
            a.b(item.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) item, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (item == null || !item.isFavorite().booleanValue()) {
                        TypedArray obtainStyledAttributes = FavouriteSongsItemView.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(FavouriteSongsItemView.this.getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                        an.a().a("click", "ac", "", "player", "", "unfav", "", "");
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    if (imageView != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(FavouriteSongsItemView.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        imageView.startAnimation(loadAnimation);
                    }
                    an.a().a("click", "ac", "", "player", "", "fav", "", "");
                }
            });
        }
    }

    public void onClick(View view) {
        u.a().a("ForYou", "Latest Played", "Play");
        if (((BusinessObject) view.getTag()) != null) {
            super.onClick(view);
        }
    }

    private void setMusicPlayingAnimation(ImageView imageView, BusinessObject businessObject, TextView textView) {
        if (imageView != null) {
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            if (i != null && i.h().equals(entityId)) {
                if (((GaanaActivity) this.mContext).getPlayerStates() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    textView.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
                    AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.mContext, R.drawable.ic_equalizer_white_36dp);
                    DrawableCompat.setTintList(animationDrawable, Util.b(true));
                    imageView.setImageDrawable(animationDrawable);
                    imageView.setVisibility(0);
                    animationDrawable.start();
                } else {
                    if (Constants.l) {
                        textView.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color_white));
                    } else {
                        textView.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color));
                    }
                    if (imageView.getAnimation() != null) {
                        imageView.getAnimation().cancel();
                    }
                    imageView.setVisibility(8);
                }
            } else if (imageView.getVisibility() == 0) {
                if (Constants.l) {
                    textView.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color_white));
                } else {
                    textView.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color));
                }
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
            }
        }
    }
}

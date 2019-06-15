package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c;
import com.facebook.share.internal.ShareConstants;
import com.fragments.ActivityFeedActivityFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.ProfileFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.ProfileUsers.ProfileUser;
import com.gaana.models.Radios.Radio;
import com.gaana.models.UserActivities.UserActivity;
import com.gaana.view.item.BaseItemView.ActivityListHolder;
import com.gaana.view.item.BaseItemView.EmptyMessageHolder;
import com.gaana.view.item.BaseItemView.FriendsActivityListHolder;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.ap;
import com.services.d;
import com.services.l.s;
import com.utilities.Util;

public class ActivityItemView extends BaseItemView {
    private String mHeader;

    public ActivityItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mHeader = "";
        this.mLayoutId = R.layout.view_item_list_activity;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        return getPoplatedViewListing(view, businessObject, viewGroup);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        UserActivity userActivity = (UserActivity) businessObject;
        ActivityListHolder activityListHolder = (ActivityListHolder) viewHolder;
        TextView textView = activityListHolder.listItemName;
        TextView textView2 = activityListHolder.listItemDesc;
        CrossFadeImageView crossFadeImageView = activityListHolder.crossFadeImageView;
        String toUpperCase = userActivity.getItemType().toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toUpperCase);
        stringBuilder.append(" ");
        stringBuilder.append(Html.fromHtml("&#8226;"));
        stringBuilder.append(" ");
        toUpperCase = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(toUpperCase);
        stringBuilder.append(userActivity.getActivityTimeStamp());
        toUpperCase = stringBuilder.toString();
        textView.setText(userActivity.getItemName());
        textView2.setText(toUpperCase);
        crossFadeImageView.bindImage(userActivity.getItemArtwork());
        this.mView.setTag(userActivity);
        this.mView.setOnClickListener(this);
        return this.mView;
    }

    public View bindEmptyView(ViewHolder viewHolder, String str) {
        EmptyMessageHolder emptyMessageHolder = (EmptyMessageHolder) viewHolder;
        emptyMessageHolder.emptyMessageText.setText(str);
        return emptyMessageHolder.itemView;
    }

    public View getPoplatedViewListing(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.view_item_list_activity, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        UserActivity userActivity = (UserActivity) businessObject;
        TextView textView = (TextView) view.findViewById(R.id.itemName);
        TextView textView2 = (TextView) view.findViewById(R.id.itemDesc);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.itemImg);
        String toUpperCase = userActivity.getItemType().toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toUpperCase);
        stringBuilder.append(" ");
        stringBuilder.append(Html.fromHtml("&#8226;"));
        stringBuilder.append(" ");
        toUpperCase = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(toUpperCase);
        stringBuilder.append(userActivity.getActivityTimeStamp());
        toUpperCase = stringBuilder.toString();
        textView.setText(userActivity.getItemName());
        textView2.setText(toUpperCase);
        crossFadeImageView.bindImage(userActivity.getItemArtwork());
        view.setTag(userActivity);
        view.setOnClickListener(this);
        return view;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject) {
        FriendsActivityListHolder friendsActivityListHolder = (FriendsActivityListHolder) viewHolder;
        this.mView = friendsActivityListHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        UserActivity userActivity = (UserActivity) businessObject;
        LinearLayout linearLayout = friendsActivityListHolder.userImagesMultiple;
        CrossFadeImageView crossFadeImageView = friendsActivityListHolder.imgUser;
        TextView textView = friendsActivityListHolder.tvItemName;
        TextView textView2 = friendsActivityListHolder.tvDurationAgo;
        linearLayout.setVisibility(8);
        if (!(userActivity == null || userActivity.getUsers() == null || userActivity.getUsers().size() <= 0)) {
            int i;
            linearLayout.setVisibility(0);
            linearLayout.removeAllViews();
            LayoutParams layoutParams = new LayoutParams(d.a().a(40), d.a().a(40));
            for (i = 0; i < Math.min(userActivity.getUsers().size(), 4); i++) {
                CrossFadeImageView crossFadeImageView2 = new CrossFadeImageView(this.mContext);
                crossFadeImageView2.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.default_user_image));
                if (i != 0) {
                    layoutParams.setMargins(d.a().a(8), 0, 0, 0);
                }
                crossFadeImageView2.setLayoutParams(layoutParams);
                ProfileUser profileUser = (ProfileUser) userActivity.getUsers().get(i);
                crossFadeImageView2.bindImage(profileUser.getArtwork(), this.mAppState.isAppInOfflineMode());
                crossFadeImageView2.setScaleType(ScaleType.FIT_XY);
                crossFadeImageView2.setTag(profileUser);
                crossFadeImageView2.setOnClickListener(this);
                linearLayout.addView(crossFadeImageView2);
            }
            TextView textView3 = new TextView(this.mContext);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.setMargins(d.a().a(8), 0, 0, 0);
            textView3.setLayoutParams(layoutParams2);
            CharSequence charSequence = "";
            CharSequence charSequence2 = null;
            StringBuilder stringBuilder;
            if (userActivity.getUsers().size() == 1) {
                StringBuilder stringBuilder2;
                if (!TextUtils.isEmpty(((ProfileUser) userActivity.getUsers().get(0)).getName())) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((ProfileUser) userActivity.getUsers().get(0)).getName());
                    stringBuilder.append("\n");
                    charSequence = stringBuilder.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getActivityTag())) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(charSequence);
                    stringBuilder2.append(userActivity.getActivityTag());
                    stringBuilder2.append(" ");
                    charSequence = stringBuilder2.toString();
                }
                if (!(TextUtils.isEmpty(userActivity.getItemType()) || charSequence.contains(userActivity.getItemType()))) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(charSequence);
                    stringBuilder2.append(userActivity.getItemType());
                    charSequence = stringBuilder2.toString();
                }
                charSequence2 = new SpannableString(charSequence);
                if (!TextUtils.isEmpty(((ProfileUser) userActivity.getUsers().get(0)).getName())) {
                    charSequence2.setSpan(new StyleSpan(1), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                    charSequence2.setSpan(new RelativeSizeSpan(1.1f), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                    charSequence2.setSpan(new ForegroundColorSpan(-1), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                }
            } else if (userActivity.getUsers().size() > 1) {
                StringBuilder stringBuilder3;
                stringBuilder = new StringBuilder();
                stringBuilder.append(userActivity.getUsers().size());
                stringBuilder.append(this.mContext.getString(R.string.friends_text));
                charSequence = stringBuilder.toString();
                i = charSequence.length();
                if (!TextUtils.isEmpty(userActivity.getActivityTag())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getActivityTag());
                    stringBuilder3.append(" ");
                    charSequence = stringBuilder3.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getItemName())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getItemName());
                    stringBuilder3.append(" ");
                    charSequence = stringBuilder3.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getItemType())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getItemType());
                    charSequence = stringBuilder3.toString();
                }
                CharSequence spannableString = new SpannableString(charSequence);
                spannableString.setSpan(new StyleSpan(1), 0, i, 33);
                spannableString.setSpan(new RelativeSizeSpan(1.1f), 0, i, 33);
                spannableString.setSpan(new ForegroundColorSpan(-1), 0, i, 33);
                charSequence2 = spannableString;
            }
            textView3.setText(charSequence2);
            textView3.setTextSize(13.0f);
            linearLayout.addView(textView3);
        }
        String activityTimeStamp = userActivity.getActivityTimeStamp();
        textView.setText(userActivity.getItemName());
        textView2.setText(activityTimeStamp);
        crossFadeImageView.bindImage(userActivity.getItemArtwork(), this.mAppState.isAppInOfflineMode());
        this.mView.setTag(userActivity);
        return this.mView;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.view_item_activity_small, view, viewGroup);
        }
        super.getPoplatedView(view, (BusinessObject) businessObject.getArrListBusinessObj().get(0));
        UserActivity userActivity = (UserActivity) ((BusinessObject) businessObject.getArrListBusinessObj().get(0));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.userImagesMultiple);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgUser);
        TextView textView = (TextView) view.findViewById(R.id.tvItemName);
        TextView textView2 = (TextView) view.findViewById(R.id.tvDurationAgo);
        linearLayout.setVisibility(8);
        if (!(userActivity == null || userActivity.getUsers() == null || userActivity.getUsers().size() <= 0)) {
            int i;
            linearLayout.setVisibility(0);
            linearLayout.removeAllViews();
            LayoutParams layoutParams = new LayoutParams(d.a().a(40), d.a().a(40));
            for (i = 0; i < Math.min(userActivity.getUsers().size(), 4); i++) {
                CrossFadeImageView crossFadeImageView2 = new CrossFadeImageView(this.mContext);
                crossFadeImageView2.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.default_user_image));
                if (i != 0) {
                    layoutParams.setMargins(d.a().a(8), 0, 0, 0);
                }
                crossFadeImageView2.setLayoutParams(layoutParams);
                ProfileUser profileUser = (ProfileUser) userActivity.getUsers().get(i);
                crossFadeImageView2.bindImage(profileUser.getArtwork(), this.mAppState.isAppInOfflineMode());
                crossFadeImageView2.setScaleType(ScaleType.FIT_XY);
                crossFadeImageView2.setTag(profileUser);
                crossFadeImageView2.setOnClickListener(this);
                linearLayout.addView(crossFadeImageView2);
            }
            TextView textView3 = new TextView(this.mContext);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.setMargins(d.a().a(8), 0, 0, 0);
            textView3.setLayoutParams(layoutParams2);
            CharSequence charSequence = "";
            CharSequence charSequence2 = null;
            StringBuilder stringBuilder;
            if (userActivity.getUsers().size() == 1) {
                StringBuilder stringBuilder2;
                if (!TextUtils.isEmpty(((ProfileUser) userActivity.getUsers().get(0)).getName())) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((ProfileUser) userActivity.getUsers().get(0)).getName());
                    stringBuilder.append("\n");
                    charSequence = stringBuilder.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getActivityTag())) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(charSequence);
                    stringBuilder2.append(userActivity.getActivityTag());
                    stringBuilder2.append(" ");
                    charSequence = stringBuilder2.toString();
                }
                if (!(TextUtils.isEmpty(userActivity.getItemType()) || charSequence.contains(userActivity.getItemType()))) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(charSequence);
                    stringBuilder2.append(userActivity.getItemType());
                    charSequence = stringBuilder2.toString();
                }
                charSequence2 = new SpannableString(charSequence);
                if (!TextUtils.isEmpty(((ProfileUser) userActivity.getUsers().get(0)).getName())) {
                    charSequence2.setSpan(new StyleSpan(1), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                    charSequence2.setSpan(new RelativeSizeSpan(1.1f), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                    charSequence2.setSpan(new ForegroundColorSpan(-1), 0, ((ProfileUser) userActivity.getUsers().get(0)).getName().length(), 33);
                }
            } else if (userActivity.getUsers().size() > 1) {
                StringBuilder stringBuilder3;
                stringBuilder = new StringBuilder();
                stringBuilder.append(userActivity.getUsers().size());
                stringBuilder.append(this.mContext.getString(R.string.friends_text));
                charSequence = stringBuilder.toString();
                i = charSequence.length();
                if (!TextUtils.isEmpty(userActivity.getActivityTag())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getActivityTag());
                    stringBuilder3.append(" ");
                    charSequence = stringBuilder3.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getItemName())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getItemName());
                    stringBuilder3.append(" ");
                    charSequence = stringBuilder3.toString();
                }
                if (!TextUtils.isEmpty(userActivity.getItemType())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(charSequence);
                    stringBuilder3.append(userActivity.getItemType());
                    charSequence = stringBuilder3.toString();
                }
                CharSequence spannableString = new SpannableString(charSequence);
                spannableString.setSpan(new StyleSpan(1), 0, i, 33);
                spannableString.setSpan(new RelativeSizeSpan(1.1f), 0, i, 33);
                spannableString.setSpan(new ForegroundColorSpan(-1), 0, i, 33);
                charSequence2 = spannableString;
            }
            textView3.setText(charSequence2);
            textView3.setTextSize(13.0f);
            linearLayout.addView(textView3);
        }
        String activityTimeStamp = userActivity.getActivityTimeStamp();
        textView.setText(userActivity.getItemName());
        textView2.setText(activityTimeStamp);
        crossFadeImageView.bindImage(userActivity.getItemArtwork(), this.mAppState.isAppInOfflineMode());
        view.setTag(userActivity);
        return view;
    }

    public View getEmptyView(View view, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return view == null ? inflateView(R.layout.view_card_item_activity, viewGroup) : view;
    }

    public View getPopulatedHomeCardView(View view, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        if (view == null) {
            view = super.getNewView(R.layout.view_card_item_activity, viewGroup, businessObject);
            view.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0, 0, 0);
        }
        this.mHeader = str;
        view.setTag(businessObject);
        UserActivity userActivity = (UserActivity) businessObject;
        SquareImageView squareImageView = (SquareImageView) view.findViewById(R.id.imgArtwork);
        TextView textView = (TextView) view.findViewById(R.id.itemAction);
        TextView textView2 = (TextView) view.findViewById(R.id.itemName);
        TextView textView3 = (TextView) view.findViewById(R.id.tvTimeStamp);
        if (userActivity.getUsers().size() > 0) {
            CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgUser);
            crossFadeImageView.setVisibility(0);
            crossFadeImageView.setOnClickListener(this);
            crossFadeImageView.setTag(userActivity.getUsers().get(0));
            TextView textView4 = (TextView) view.findViewById(R.id.userName);
            textView4.setVisibility(0);
            textView4.setOnClickListener(this);
            textView4.setText(((ProfileUser) userActivity.getUsers().get(0)).getName());
            textView4.setTag(userActivity.getUsers().get(0));
            textView.setVisibility(0);
            textView.setOnClickListener(this);
            textView.setTag(userActivity.getUsers().get(0));
            crossFadeImageView.bindImage(((ProfileUser) userActivity.getUsers().get(0)).getArtwork(), this.mAppState.isAppInOfflineMode());
        } else {
            view.findViewById(R.id.imgUser).setVisibility(8);
            view.findViewById(R.id.userName).setVisibility(8);
            textView.setVisibility(8);
        }
        String trim = userActivity.getActivityTag().trim();
        squareImageView.bindImage(userActivity.getItemArtwork(), this.mAppState.isAppInOfflineMode());
        textView2.setText(userActivity.getItemName());
        textView.setText(trim);
        textView3.setText(userActivity.getActivityTimeStamp());
        return view;
    }

    public void onClick(View view) {
        super.onClick(view);
        executeRequest(view);
    }

    private void executeRequest(View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if ((businessObject instanceof ProfileUser) || view.getId() == R.id.imgUser || view.getId() == R.id.userName || view.getId() == R.id.itemAction) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
            } else if (Util.j(this.mContext)) {
                if (((BaseActivity) this.mContext).currentScreen.startsWith("Activity")) {
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Album Detail", "Friends Activity");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder.append(" - ");
                    stringBuilder.append(((BaseActivity) this.mContext).currentPagerView);
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Album Detail", stringBuilder.toString());
                }
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_PROFILE_ORIGIN_MYPROFILE", ShareConstants.PEOPLE_IDS);
                bundle.putSerializable("EXTRA_PROFILE_USER_BUSINESS_OBJECT", businessObject);
                BaseGaanaFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(profileFragment);
            } else {
                ap.a().f(this.mContext);
            }
        } else if (businessObject instanceof UserActivity) {
            UserActivity userActivity = (UserActivity) businessObject;
            if (userActivity != null) {
                String itemType = userActivity.getItemType();
                String itemId = userActivity.getItemId();
                if (itemType != null && ((itemType.equals("album") || itemType.equals("playlist")) && DownloadManager.c().h(Integer.parseInt(itemId)) != null)) {
                    BusinessObject a = DownloadManager.c().a(BusinessObjectType.Playlists, itemId);
                    if (a != null) {
                        populateListing(a);
                        return;
                    }
                }
                if (this.mAppState.isAppInOfflineMode()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
                } else if (Util.j(this.mContext)) {
                    if (!TextUtils.isEmpty(itemType) && itemType.equalsIgnoreCase("radio")) {
                        itemType = userActivity.getRadioType();
                    }
                    URLManager a2 = Constants.a(itemType, itemId, false);
                    if (a2 != null) {
                        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading_string_text));
                        i.a().a(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                ((BaseActivity) ActivityItemView.this.mContext).hideProgressDialog();
                                if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                                    ActivityItemView.this.populateListing((BusinessObject) businessObject.getArrListBusinessObj().get(0));
                                }
                            }
                        }, a2);
                    }
                } else {
                    ap.a().f(this.mContext);
                }
            }
        }
    }

    private void populateListing(BusinessObject businessObject) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (businessObject instanceof Album) {
            this.mListingComponents = Constants.b();
            populateAlbumListing(businessObject);
            if (((BaseActivity) this.mContext).currentScreen.startsWith("Activity")) {
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Album Detail", "Friends Activity");
            } else if (((BaseActivity) this.mContext).currentScreen.startsWith("MyMusicScreen")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Album -");
                stringBuilder.append(businessObject.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("MyMusic", "My Activity Click", stringBuilder.toString());
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder2.append(" - ");
                stringBuilder2.append(this.mHeader);
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Album Detail", stringBuilder2.toString());
            }
        } else if (businessObject instanceof Artist) {
            this.mListingComponents = Constants.a("", businessObject.isLocalMedia());
            populateArtistListing(businessObject);
            if (((BaseActivity) this.mContext).currentScreen.startsWith("Activity")) {
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", "Friends Activity");
            } else if (((BaseActivity) this.mContext).currentScreen.startsWith("MyMusicScreen")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Artist -");
                stringBuilder.append(businessObject.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("MyMusic", "My Activity Click", stringBuilder.toString());
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder2.append(" - ");
                stringBuilder2.append(this.mHeader);
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", stringBuilder2.toString());
            }
        } else if (businessObject instanceof Playlist) {
            this.mListingComponents = Constants.e();
            Playlist playlist = (Playlist) businessObject;
            if (playlist.isGaanaSpecial()) {
                populateSpecialGaanaListing(playlist);
            } else {
                populatePlaylistListing(playlist);
            }
            if (((BaseActivity) this.mContext).currentScreen.startsWith("Activity")) {
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Playlist Detail", "Friends Activity");
            } else if (((BaseActivity) this.mContext).currentScreen.startsWith("MyMusicScreen")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Playlist -");
                stringBuilder.append(businessObject.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("MyMusic", "My Activity Click", stringBuilder.toString());
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder2.append(" - ");
                stringBuilder2.append(this.mHeader);
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Playlist Detail", stringBuilder2.toString());
            }
        } else if (businessObject instanceof Radio) {
            BusinessObject businessObject2 = (Radio) businessObject;
            if (businessObject2.getType().equals(c.d.c)) {
                if (((BaseActivity) this.mContext).currentScreen.startsWith("MyMusicScreen")) {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Radio Mirchi -");
                    stringBuilder3.append(businessObject.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("MyMusic", "My Activity Click", stringBuilder3.toString());
                }
                ad.a(this.mContext).a(businessObject2);
            } else {
                String replace = "https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject2.getBusinessObjId()).replace("<radio_type>", businessObject2.getType());
                if (((BaseActivity) this.mContext).currentScreen.startsWith("MyMusicScreen")) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Gaana Radio -");
                    stringBuilder2.append(businessObject.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("MyMusic", "My Activity Click", stringBuilder2.toString());
                }
                ad.a(this.mContext).a(replace, SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject2);
            }
            this.mListingComponents = Constants.a((Radio) businessObject2);
            this.mListingComponents.a(businessObject2);
            populateRadioListing(businessObject2);
        }
    }

    public String getSectionName() {
        if (this.mFragment instanceof ActivityFeedActivityFragment) {
            return PAGE_SORCE_NAME.FRIEND_ACTIVITY.name();
        }
        if (this.mFragment instanceof ProfileFragment) {
            return PLAYOUT_SECTION_TYPE.OTHER_PROFILE.name();
        }
        return PLAYOUT_SECTION_TYPE.MY_ACTVITY.name();
    }
}

package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Notifications.Notification;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.OffersView.OffersViewHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.library.controls.CircularImageView;
import com.library.controls.CrossFadeImageView;
import com.managers.aa;
import com.managers.ap;
import com.managers.u;
import com.services.c;
import com.services.l.i;
import com.utilities.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationItemView extends BaseItemView implements OnClickListener {
    public static final int VIEW_TYPE_OFFLINE_PLAY_NOTIF = 6;
    private i listener;
    private OffersView mOffersView;
    private final float notifSeenAlpha;

    public static class DownloadNotifHolder extends ViewHolder {
        private CrossFadeImageView image_noti_bg;
        private CrossFadeImageView image_noti_image;
        private CrossFadeImageView image_noti_logo_multi;
        private CrossFadeImageView image_noti_logo_single;
        private TextView subtitle;
        private TextView title;
        private TextView tvItemDate;

        public DownloadNotifHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.subtitle = (TextView) view.findViewById(R.id.subtitle);
            this.tvItemDate = (TextView) view.findViewById(R.id.tvItemDate);
            this.image_noti_bg = (CrossFadeImageView) view.findViewById(R.id.image_noti_bg);
            this.image_noti_image = (CrossFadeImageView) view.findViewById(R.id.image_noti_image);
            this.image_noti_logo_multi = (CrossFadeImageView) view.findViewById(R.id.image_noti_logo_multi);
            this.image_noti_logo_single = (CrossFadeImageView) view.findViewById(R.id.image_noti_logo_single);
        }
    }

    public static class NotificationItemHolder extends ViewHolder {
        private ImageView crown_user_badge;
        private ImageView imgAcceptRequest;
        private ImageView imgRejectRequest;
        private LinearLayout llSocialRequest;
        private CrossFadeImageView mCrossFadeImageIcon;
        private CircularImageView profilePhoto;
        private TextView tvItemDate;
        private TextView tvItemDetails;
        private TextView tvItemName;

        public NotificationItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.profilePhoto = (CircularImageView) view.findViewById(R.id.imgPhotoIcon);
            this.tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            this.tvItemDetails = (TextView) view.findViewById(R.id.tvItemDetails);
            this.tvItemDate = (TextView) view.findViewById(R.id.tvItemDate);
            this.llSocialRequest = (LinearLayout) view.findViewById(R.id.llSocialRequest);
            this.imgAcceptRequest = (ImageView) view.findViewById(R.id.imgRequestAccept);
            this.imgRejectRequest = (ImageView) view.findViewById(R.id.imgRequestReject);
            this.crown_user_badge = (ImageView) view.findViewById(R.id.crown_user_badge);
        }
    }

    public NotificationItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mOffersView = null;
        this.notifSeenAlpha = 0.55f;
        this.listener = null;
        this.mLayoutId = R.layout.view_item_notif;
    }

    public NotificationItemView(Context context, BaseGaanaFragment baseGaanaFragment, Bundle bundle) {
        super(context, baseGaanaFragment);
        this.mOffersView = null;
        this.notifSeenAlpha = 0.55f;
        this.listener = null;
        this.mLayoutId = R.layout.view_item_notif;
    }

    public void setNotificationListener(i iVar) {
        this.listener = iVar;
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            if (this.mOffersView == null) {
                this.mOffersView = new OffersView(this.mContext, this.mFragment);
            }
            return this.mOffersView.createViewHolder(viewGroup, i);
        } else if (i == 6) {
            return this.mInflater.inflate(R.layout.download_notification_item_view, viewGroup, false);
        } else {
            return super.createViewHolder(viewGroup, i);
        }
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        ViewHolder viewHolder2 = viewHolder;
        BusinessObject businessObject2 = businessObject;
        TypedValue typedValue;
        if (viewHolder2 instanceof OffersViewHolder) {
            if (this.mOffersView == null) {
                this.mOffersView = new OffersView(this.mContext, this.mFragment);
            }
            this.mOffersView.setOnClickListener(null);
            this.mOffersView.setOnTouchListener(null);
            return this.mOffersView.getPoplatedView(viewHolder2, businessObject2, viewGroup);
        } else if (viewHolder2 instanceof DownloadNotifHolder) {
            DownloadNotifHolder downloadNotifHolder = (DownloadNotifHolder) viewHolder2;
            Notification notification = (Notification) businessObject2;
            try {
                JSONObject jSONObject = new JSONObject(notification.getMessageDetails());
                if (jSONObject.has("notificationTrackData")) {
                    StringBuilder stringBuilder;
                    ArrayList arrListBusinessObj = ((Tracks) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(jSONObject.getString("notificationTrackData"), Tracks.class)).getArrListBusinessObj();
                    String trackTitle = ((Track) arrListBusinessObj.get(0)).getTrackTitle();
                    String artwork = ((Track) arrListBusinessObj.get(0)).getArtwork();
                    if (trackTitle.length() > 22) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(trackTitle.substring(0, 19));
                        stringBuilder.append("...");
                        trackTitle = stringBuilder.toString();
                    }
                    if (notification.hasSeen()) {
                        TypedValue typedValue2 = new TypedValue();
                        this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue2, true);
                        downloadNotifHolder.title.setTextColor(typedValue2.data);
                        downloadNotifHolder.subtitle.setTextColor(typedValue2.data);
                        downloadNotifHolder.itemView.setAlpha(0.55f);
                    } else {
                        typedValue = new TypedValue();
                        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                        downloadNotifHolder.title.setTextColor(typedValue.data);
                        downloadNotifHolder.subtitle.setTextColor(typedValue.data);
                        downloadNotifHolder.itemView.setAlpha(1.0f);
                    }
                    if (arrListBusinessObj.size() > 1) {
                        downloadNotifHolder.title.setText(this.mContext.getString(R.string.download_noti_plural_title, new Object[]{Integer.valueOf(arrListBusinessObj.size())}));
                        TextView access$100 = downloadNotifHolder.subtitle;
                        Context context = this.mContext;
                        Object[] objArr = new Object[1];
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("'");
                        stringBuilder.append(trackTitle);
                        stringBuilder.append("'");
                        objArr[0] = stringBuilder.toString();
                        access$100.setText(context.getString(R.string.download_noti_plural_subtitle, objArr));
                        downloadNotifHolder.subtitle.setAlpha(0.5f);
                        downloadNotifHolder.image_noti_bg.setVisibility(0);
                        if (Constants.l) {
                            downloadNotifHolder.image_noti_bg.setBackgroundColor(this.mContext.getResources().getColor(R.color.black_alfa_10));
                        } else {
                            downloadNotifHolder.image_noti_bg.setBackgroundColor(this.mContext.getResources().getColor(R.color.white_alfa_20));
                        }
                        downloadNotifHolder.image_noti_logo_multi.setVisibility(0);
                        downloadNotifHolder.image_noti_logo_single.setVisibility(8);
                    } else {
                        downloadNotifHolder.title.setText(this.mContext.getString(R.string.download_noti_singular_title, new Object[]{trackTitle}));
                        downloadNotifHolder.subtitle.setText(this.mContext.getString(R.string.download_noti_singular_subtitle));
                        downloadNotifHolder.subtitle.setAlpha(0.5f);
                        downloadNotifHolder.image_noti_bg.setVisibility(8);
                        downloadNotifHolder.image_noti_logo_multi.setVisibility(8);
                        downloadNotifHolder.image_noti_logo_single.setVisibility(0);
                    }
                    if (!TextUtils.isEmpty(artwork)) {
                        downloadNotifHolder.image_noti_image.setScaleType(ScaleType.CENTER_CROP);
                        downloadNotifHolder.image_noti_image.bindImage(artwork);
                    }
                    downloadNotifHolder.tvItemDate.setText(getDateLine(notification.getTimeStamp()));
                    downloadNotifHolder.itemView.setTag(businessObject2);
                    downloadNotifHolder.itemView.setOnClickListener(this);
                }
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
            return downloadNotifHolder.itemView;
        } else {
            View view = viewHolder2.itemView;
            super.getPoplatedView(view, businessObject2);
            if (viewHolder2 instanceof NotificationItemHolder) {
                int i;
                NotificationItemHolder notificationItemHolder = (NotificationItemHolder) viewHolder2;
                Notification notification2 = (Notification) businessObject2;
                CrossFadeImageView access$700 = notificationItemHolder.mCrossFadeImageIcon;
                CircularImageView access$800 = notificationItemHolder.profilePhoto;
                TextView access$900 = notificationItemHolder.tvItemName;
                TextView access$1000 = notificationItemHolder.tvItemDetails;
                LinearLayout access$1100 = notificationItemHolder.llSocialRequest;
                ImageView access$1200 = notificationItemHolder.imgAcceptRequest;
                ImageView access$1300 = notificationItemHolder.imgRejectRequest;
                TextView access$1400 = notificationItemHolder.tvItemDate;
                ImageView access$1500 = notificationItemHolder.crown_user_badge;
                try {
                    access$1400.setText(getDateLine(notification2.getTimeStamp()));
                    i = 8;
                } catch (Exception unused) {
                    i = 8;
                    access$1400.setVisibility(8);
                }
                access$700.setVisibility(0);
                access$800.setVisibility(i);
                access$1000.setVisibility(0);
                access$1100.setVisibility(i);
                access$1200.setVisibility(i);
                access$1300.setVisibility(i);
                access$800.setBlankImage();
                if (notification2.hasSeen()) {
                    typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
                    access$900.setTextColor(typedValue.data);
                    access$1000.setTextColor(typedValue.data);
                    view.setAlpha(0.55f);
                } else {
                    typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                    access$900.setTextColor(typedValue.data);
                    access$1000.setTextColor(typedValue.data);
                    view.setAlpha(1.0f);
                }
                access$900.setText(notification2.getMessage());
                if (TextUtils.isEmpty(notification2.getMessageDetails())) {
                    access$1000.setVisibility(8);
                } else {
                    access$1000.setText(notification2.getMessageDetails());
                    access$1000.setAlpha(0.5f);
                }
                access$700.setScaleType(ScaleType.CENTER_CROP);
                if (!TextUtils.isEmpty(notification2.getItemartwork())) {
                    access$700.bindImage(notification2.getItemartwork(), this.mAppState.isAppInOfflineMode());
                } else if (notification2.getNotificationType() == null || !notification2.getNotificationType().equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
                    access$700.setImageResource(Util.x());
                } else {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                    access$700.setImageDrawable(obtainStyledAttributes.getDrawable(0));
                    obtainStyledAttributes.recycle();
                }
                access$1500.setVisibility(8);
                view.setTag(notification2);
            }
            return view;
        }
    }

    public String getDateLine(String str) {
        Date date = new Date(Long.parseLong(str) * 1000);
        String format = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(date);
        try {
            long currentTimeMillis = (System.currentTimeMillis() - date.getTime()) / 60000;
            long j = currentTimeMillis / 60;
            long j2 = j / 24;
            StringBuilder stringBuilder;
            if (j2 > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(j2);
                stringBuilder.append(" ");
                stringBuilder.append(j2 > 1 ? getContext().getString(R.string.DAYS_AGO) : getContext().getString(R.string.DAY_AGO));
                return stringBuilder.toString();
            } else if (j > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(j);
                stringBuilder.append(" ");
                stringBuilder.append(j > 1 ? getContext().getString(R.string.HOURS_AGO) : getContext().getString(R.string.HOUR_AGO));
                return stringBuilder.toString();
            } else if (currentTimeMillis < 0) {
                return format;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(currentTimeMillis);
                stringBuilder.append(" ");
                stringBuilder.append(currentTimeMillis > 1 ? getContext().getString(R.string.MINUTES_AGO) : getContext().getString(R.string.MINUTE_AGO));
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return format;
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        Notification notification = (Notification) view.getTag();
        this.mBusinessObject = (BusinessObject) view.getTag();
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This notification");
        } else if (Util.j(this.mContext)) {
            view.setAlpha(0.55f);
            if (!notification.hasSeen()) {
                aa.a().a(notification.getTimeStampInMilliSeconds());
            }
            if (notification.getType().equalsIgnoreCase("offline_play_notif")) {
                u.a().b("Download Notification", "DN_Clicks");
                aa.a().a(notification.getTimeStampInMilliSeconds());
                aa.a().a(this.mContext, notification.getMessageDetails());
                return;
            }
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Notification clicked", notification.getMessage());
            c.a(this.mContext, true).a(this.mContext, notification, this.mAppState);
        } else {
            ap.a().f(this.mContext);
        }
    }
}

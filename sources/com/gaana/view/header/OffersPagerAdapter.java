package com.gaana.view.header;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Notifications;
import com.gaana.models.Notifications.Notification;
import com.managers.ab;
import com.managers.ap;
import com.services.c;
import com.utilities.Util;

public class OffersPagerAdapter extends PagerAdapter implements OnClickListener {
    private LayoutInflater inflater;
    private Context mContext;
    float offerSeenAlpha = 0.55f;
    private Notifications offersList;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public OffersPagerAdapter(Context context) {
        this.mContext = context;
        this.offersList = ab.a().b();
        this.inflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
    }

    public int getCount() {
        if (this.offersList == null || this.offersList.getArrListBusinessObj() == null) {
            return 0;
        }
        if (this.offersList.getArrListBusinessObj().size() < 5) {
            return this.offersList.getArrListBusinessObj().size();
        }
        return 5;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.inflater.inflate(R.layout.view_offers_item, viewGroup, false);
        inflate.findViewById(R.id.offer_background_top).setAlpha(0.1f);
        inflate.findViewById(R.id.offer_background_bottom).setAlpha(0.05f);
        Notification notification = (Notification) this.offersList.getArrListBusinessObj().get(i);
        if (notification.hasSeen()) {
            inflate.setAlpha(this.offerSeenAlpha);
        }
        TextView textView = (TextView) inflate.findViewById(R.id.item_offer_header);
        if (notification.getMessage() != null) {
            textView.setText(notification.getMessage());
            textView.setTypeface(null, 1);
        }
        if (notification.getMessageDetails() != null) {
            ((TextView) inflate.findViewById(R.id.item_offer_details)).setText(notification.getMessageDetails());
        }
        inflate.setOnClickListener(this);
        inflate.setTag(notification);
        viewGroup.addView(inflate);
        return inflate;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void onClick(View view) {
        Notification notification = (Notification) view.getTag();
        GaanaApplication instance = GaanaApplication.getInstance();
        if (!(notification == null || notification.getMessage() == null)) {
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Offer clicked", notification.getMessage());
        }
        if (instance.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This offer");
        } else if (Util.j(this.mContext)) {
            view.setAlpha(this.offerSeenAlpha);
            if (!notification.hasSeen()) {
                ab.a().a(notification.getTimeStampInMilliSeconds());
            }
            c.a(this.mContext, true).a(this.mContext, notification, instance);
        } else {
            ap.a().f(this.mContext);
        }
    }
}

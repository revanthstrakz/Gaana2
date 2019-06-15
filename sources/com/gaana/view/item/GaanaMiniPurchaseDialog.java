package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaMiniPurchaseFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.Notifications.Notification;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.controls.CrossFadeImageView;
import com.managers.aa;
import com.managers.aj;
import com.moengage.inapp.InAppMessage;
import com.moengage.inapp.InAppTracker;
import org.json.JSONException;
import org.json.JSONObject;

public class GaanaMiniPurchaseDialog extends Dialog {
    private String entityId = "";
    private String entity_type = "";
    private String ga_title = "";
    private Button mBtnSubscribe;
    private Context mContext;
    private CrossFadeImageView mImgProductIcon;
    private TextView mTxtDesc;
    private TextView mTxtTitle;

    public GaanaMiniPurchaseDialog(Context context, InAppMessage inAppMessage) {
        super(context);
        this.mContext = context;
        requestWindowFeature(1);
        setContentView(R.layout.gaanamini_purchase_dlg);
        init(inAppMessage);
    }

    private void init(final InAppMessage inAppMessage) {
        try {
            final JSONObject jSONObject = new JSONObject(inAppMessage.content);
            this.mImgProductIcon = (CrossFadeImageView) findViewById(R.id.imgArtwork);
            this.mTxtTitle = (TextView) findViewById(R.id.txt_title);
            this.mTxtDesc = (TextView) findViewById(R.id.txt_desc);
            this.mBtnSubscribe = (Button) findViewById(R.id.btn_purchase);
            String str = "";
            String str2 = "";
            String str3 = "";
            CharSequence charSequence = "";
            if (jSONObject != null) {
                if (jSONObject.has("title")) {
                    str = jSONObject.getString("title");
                }
                if (jSONObject.has("ga_title")) {
                    this.ga_title = jSONObject.getString("ga_title");
                }
                if (jSONObject.has("artwork")) {
                    str2 = jSONObject.getString("artwork");
                }
                if (jSONObject.has("desc")) {
                    str3 = jSONObject.getString("desc");
                }
                if (jSONObject.has("cta_text")) {
                    charSequence = jSONObject.getString("cta_text");
                }
                if (jSONObject.has("entity_id")) {
                    this.entityId = jSONObject.getString("entity_id");
                }
                if (jSONObject.has("entity_id")) {
                    this.entity_type = jSONObject.getString("entity_type");
                }
            }
            String str4 = str2;
            str2 = str;
            str = str3;
            str3 = str4;
            this.mImgProductIcon.bindImage(str3);
            this.mTxtTitle.setText(str2);
            this.mBtnSubscribe.setText(charSequence);
            this.mTxtDesc.setText(str);
            StringBuilder stringBuilder;
            if (this.entity_type.equalsIgnoreCase("PL")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.ga_title);
                stringBuilder.append("-");
                stringBuilder.append(this.entityId);
                ((BaseActivity) this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "PlaylistPackPopup");
            } else if (this.entity_type.equalsIgnoreCase("AL")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.ga_title);
                stringBuilder.append("-");
                stringBuilder.append(this.entityId);
                ((BaseActivity) this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "AlbumPackPopup");
            } else if (this.entity_type.equalsIgnoreCase("AR")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.ga_title);
                stringBuilder.append("-");
                stringBuilder.append(this.entityId);
                ((BaseActivity) this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "ArtistPackPopup");
            }
            createAndAddNotification(str2, str3, charSequence, this.entity_type, this.entityId);
            this.mBtnSubscribe.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (TextUtils.isEmpty(GaanaMiniPurchaseDialog.this.entity_type)) {
                        aj.a().a(GaanaMiniPurchaseDialog.this.mContext, GaanaMiniPurchaseDialog.this.mContext.getResources().getString(R.string.some_error_occurred));
                    } else {
                        StringBuilder stringBuilder;
                        if (GaanaMiniPurchaseDialog.this.entity_type.equalsIgnoreCase("PL")) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.ga_title);
                            stringBuilder.append("-");
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.entityId);
                            ((BaseActivity) GaanaMiniPurchaseDialog.this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "PlaylistPackPurchase");
                        } else if (GaanaMiniPurchaseDialog.this.entity_type.equalsIgnoreCase("AL")) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.ga_title);
                            stringBuilder.append("-");
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.entityId);
                            ((BaseActivity) GaanaMiniPurchaseDialog.this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "AlbumPackPurchase");
                        } else if (GaanaMiniPurchaseDialog.this.entity_type.equalsIgnoreCase("AR")) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.ga_title);
                            stringBuilder.append("-");
                            stringBuilder.append(GaanaMiniPurchaseDialog.this.entityId);
                            ((BaseActivity) GaanaMiniPurchaseDialog.this.mContext).sendGAEvent("GaanaMiniProduct", stringBuilder.toString(), "ArtistPackPurchase");
                        }
                        BaseGaanaFragment gaanaMiniPurchaseFragment = new GaanaMiniPurchaseFragment();
                        gaanaMiniPurchaseFragment.setArguments(GaanaMiniPurchaseFragment.a(GaanaMiniPurchaseDialog.this.entityId, GaanaMiniPurchaseDialog.this.entity_type));
                        ((GaanaActivity) GaanaMiniPurchaseDialog.this.mContext).displayFragment(gaanaMiniPurchaseFragment);
                    }
                    if (jSONObject != null) {
                        InAppTracker.getInstance(GaanaMiniPurchaseDialog.this.mContext).trackInAppClicked(inAppMessage);
                    }
                    GaanaMiniPurchaseDialog.this.dismiss();
                }
            });
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void createAndAddNotification(String str, String str2, String str3, String str4, String str5) {
        Notification notification = new Notification();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": Click here to ");
        stringBuilder.append(str3);
        notification.setMessage(stringBuilder.toString());
        notification.setItemartwork(str2);
        notification.setTimestamp(System.currentTimeMillis());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("gaana://view/gaanamini/");
        stringBuilder2.append(str5);
        stringBuilder2.append("/");
        stringBuilder2.append(str4);
        notification.setAction_url_mobile(stringBuilder2.toString());
        aa.a().a(notification, true);
    }
}

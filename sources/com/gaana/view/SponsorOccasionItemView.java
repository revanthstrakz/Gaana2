package com.gaana.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.constants.c.c;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.library.controls.CrossFadeImageView;
import com.managers.ColombiaAdViewManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.an;
import com.managers.e;
import com.managers.u;
import com.services.d;
import com.services.l.ag;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class SponsorOccasionItemView extends Adapter<SponsorOccasionItemViewHolder> {
    private String GA_CardName = "";
    private Context mContext;
    private a mDynamicView;
    private ArrayList<Item> mItemList = null;
    private int minimum_check = 5;
    private int size = 0;

    public class SponsorOccasionItemViewHolder extends ViewHolder implements OnClickListener {
        public FrameLayout frameLayout;
        public CrossFadeImageView squareImageView;

        public SponsorOccasionItemViewHolder(View view) {
            super(view);
            this.squareImageView = (CrossFadeImageView) view.findViewById(R.id.squareImageView);
            this.frameLayout = (FrameLayout) view.findViewById(R.id.container);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            SponsorOccasionItemView.this.onViewClick(getLayoutPosition());
        }
    }

    public SponsorOccasionItemView(Context context, ArrayList<Item> arrayList, a aVar) {
        this.mContext = context;
        this.mItemList = arrayList;
        this.mDynamicView = aVar;
    }

    public SponsorOccasionItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SponsorOccasionItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.sponsored_occasion_item_view, viewGroup, false));
    }

    public void onBindViewHolder(SponsorOccasionItemViewHolder sponsorOccasionItemViewHolder, int i) {
        if (i == 0) {
            sponsorOccasionItemViewHolder.itemView.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0, 0, 0);
        } else {
            sponsorOccasionItemViewHolder.itemView.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp7), 0, 0, 0);
        }
        sponsorOccasionItemViewHolder.squareImageView.bindImage(((Item) this.mItemList.get(i)).getArtwork());
        handleGa(i, sponsorOccasionItemViewHolder);
    }

    private void handleGa(int i, SponsorOccasionItemViewHolder sponsorOccasionItemViewHolder) {
        CharSequence charSequence = "";
        String str = "";
        List entityInfo = ((Item) this.mItemList.get(i)).getEntityInfo();
        if (entityInfo != null) {
            int size = entityInfo.size();
            String str2 = str;
            str = charSequence;
            for (int i2 = 0; i2 < size; i2++) {
                if (((EntityInfo) entityInfo.get(i2)).getKey().equals("tracker_adcode_ctn")) {
                    str = (String) ((EntityInfo) entityInfo.get(i2)).getValue();
                } else if (((EntityInfo) entityInfo.get(i2)).getKey().equals("tracker_adcode_dfp")) {
                    str2 = (String) ((EntityInfo) entityInfo.get(i2)).getValue();
                }
            }
            charSequence = str;
            str = str2;
        }
        if (charSequence != null && !TextUtils.isEmpty(charSequence)) {
            e.a().a(sponsorOccasionItemViewHolder.frameLayout, this.mContext, Long.parseLong(charSequence));
        } else if (!(str == null || TextUtils.isEmpty(str))) {
            ColombiaAdViewManager.a().a(sponsorOccasionItemViewHolder.frameLayout, this.mContext, str);
        }
        this.size++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.GA_CardName);
        stringBuilder.append(",");
        stringBuilder.append(((Item) this.mItemList.get(i)).getName());
        this.GA_CardName = stringBuilder.toString();
        if (this.size >= this.minimum_check) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            long b = d.a().b(0, "PREFERENCE_KEY_SESSION_SPONSORED_OCCASION_TIME", false);
            if (b == 0 || (valueOf.longValue() / 1000) - (b / 1000) > 30) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("SOP-");
                stringBuilder2.append(this.GA_CardName);
                u.a().a("Browse", stringBuilder2.toString(), "Impression");
                d.a().a(valueOf.longValue(), "PREFERENCE_KEY_SESSION_SPONSORED_OCCASION_TIME", false);
            }
        }
    }

    public int getItemCount() {
        return this.mItemList.size();
    }

    public void onViewClick(int i) {
        if (((Item) this.mItemList.get(i)).getEntityType().equalsIgnoreCase(c.k)) {
            Item item = (Item) this.mItemList.get(i);
            CharSequence charSequence = "";
            List entityInfo = item.getEntityInfo();
            if (entityInfo != null) {
                for (int i2 = 0; i2 < entityInfo.size(); i2++) {
                    if (((EntityInfo) entityInfo.get(i2)).getKey().equals("url")) {
                        charSequence = (String) ((EntityInfo) entityInfo.get(i2)).getValue();
                        break;
                    }
                }
            }
            if (!TextUtils.isEmpty(charSequence)) {
                ((GaanaActivity) this.mContext).displayFragment(JukeSeeAllFragment.newInstance(charSequence, item.getName(), BusinessObjectType.GenericItems.name(), false));
            }
        } else if (((Item) this.mItemList.get(i)).getEntityType().equalsIgnoreCase(c.i)) {
            if (((Item) this.mItemList.get(i)).getEntityInfo() != null) {
                showFragment(getOccaisonUrl(((Item) this.mItemList.get(i)).getEntityInfo()), getIsSponsored(((Item) this.mItemList.get(i)).getEntityInfo()));
            }
        } else if (((Item) this.mItemList.get(i)).getEntityType().equalsIgnoreCase(c.e)) {
            Util.a(Util.a(this.mContext, (ArrayList) ((Item) this.mItemList.get(i)).getEntityInfo()), getMandatoryLogin((ArrayList) ((Item) this.mItemList.get(i)).getEntityInfo()), getInAppWeb((ArrayList) ((Item) this.mItemList.get(i)).getEntityInfo()), this.mContext);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SOP-");
        stringBuilder.append(((Item) this.mItemList.get(i)).getName());
        u.a().a("Browse", stringBuilder.toString(), "click");
        an.a().a("click", "en", this.mDynamicView.y(), an.a().a(an.a().a), ((Item) this.mItemList.get(i)).getBusinessObjId(), ((Item) this.mItemList.get(i)).getEntityType(), String.valueOf(i), "");
    }

    private void showFragment(final String str, final String str2) {
        if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode() && !TextUtils.isEmpty(str) && str.contains("occasion")) {
            com.dynamicview.c.a().a(new ag() {
                public void onOccasionResponse() {
                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("OCCASION_URL", str);
                    bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                    bundle.putString("OCCASION_IS_SPONSORED", str2);
                    dynamicOccasionFragment.setArguments(bundle);
                    ((GaanaActivity) SponsorOccasionItemView.this.mContext).displayFragment(dynamicOccasionFragment);
                }

                public void onOccasionError() {
                    aj.a().a(SponsorOccasionItemView.this.mContext, SponsorOccasionItemView.this.mContext.getResources().getString(R.string.error_download_no_internet));
                }
            }, str, null, false);
        }
    }

    private String getMandatoryLogin(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("login_flag")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
    }

    private String getInAppWeb(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("app_url_view")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
    }

    private String getOccaisonUrl(List<EntityInfo> list) {
        for (int i = 0; i < list.size(); i++) {
            if (((EntityInfo) list.get(i)).getKey().equals("url")) {
                return ((EntityInfo) list.get(i)).getValue().toString();
            }
        }
        return null;
    }

    private String getIsSponsored(List<EntityInfo> list) {
        if (list.size() <= 1) {
            return "-1";
        }
        EntityInfo entityInfo = (EntityInfo) list.get(1);
        return entityInfo.getKey().equals("is_sponsored") ? entityInfo.getValue().toString() : "-1";
    }
}

package com.gaana.view.header;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.e;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.a.i;
import com.bumptech.glide.request.f;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.PaymentProductDetailModel.CarouselOfferConfig;
import com.gaana.view.item.GenericCarouselView;
import com.library.controls.CrossFadeImageView;
import com.services.c;
import java.util.ArrayList;

public class GenericCarouselAdapter extends PagerAdapter implements OnClickListener {
    private ArrayList<CarouselOfferConfig> carouselData;
    private GenericCarouselView carouselListener;
    private LayoutInflater inflater;
    private Context mContext;
    private int mLayoutId = -1;

    public class TagObject {
        CarouselOfferConfig item;
        int position;

        public TagObject(CarouselOfferConfig carouselOfferConfig, int i) {
            this.item = carouselOfferConfig;
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }

        public CarouselOfferConfig getItem() {
            return this.item;
        }
    }

    public int getItemPosition(@NonNull Object obj) {
        return -2;
    }

    public GenericCarouselAdapter(Context context, ArrayList<CarouselOfferConfig> arrayList) {
        this.mContext = context;
        this.carouselData = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void setCarouselData(ArrayList<CarouselOfferConfig> arrayList) {
        this.carouselData = arrayList;
        notifyDataSetChanged();
    }

    public void setCarouselListener(GenericCarouselView genericCarouselView) {
        this.carouselListener = genericCarouselView;
    }

    public void setLayoutId(int i) {
        this.mLayoutId = i;
    }

    public int getCount() {
        return this.carouselData != null ? this.carouselData.size() : 0;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        FrameLayout frameLayout = (FrameLayout) this.inflater.inflate(this.mLayoutId, viewGroup, false);
        ImageView imageView = (CrossFadeImageView) frameLayout.findViewById(R.id.carouselImage);
        String offerUrl = ((CarouselOfferConfig) this.carouselData.get(i)).getOfferUrl();
        e.c(this.mContext.getApplicationContext()).load(offerUrl).apply(new f().placeholder(imageView.getDrawable())).listener(new com.bumptech.glide.request.e<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, i<Drawable> iVar, boolean z) {
                return false;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, i<Drawable> iVar, DataSource dataSource, boolean z) {
                return false;
            }
        }).into(imageView);
        frameLayout.setTag(new TagObject((CarouselOfferConfig) this.carouselData.get(i), i));
        frameLayout.setOnClickListener(this);
        ((ViewPager) viewGroup).addView(frameLayout);
        return frameLayout;
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == ((View) obj);
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        ((ViewPager) viewGroup).removeView((View) obj);
    }

    public void onClick(View view) {
        if (this.carouselListener != null && (view.getTag() instanceof TagObject)) {
            TagObject tagObject = (TagObject) view.getTag();
            this.carouselListener.setItemPosition(tagObject.getPosition());
            if (tagObject.getItem().getOfferProduct() != null) {
                this.carouselListener.onClickPerformed(view, tagObject.getItem().getOfferProduct());
            } else if (tagObject.getItem().getAppDeepLink() != null) {
                c.a(this.mContext).a(this.mContext, tagObject.getItem().getAppDeepLink(), GaanaApplication.getInstance());
            }
        }
    }
}

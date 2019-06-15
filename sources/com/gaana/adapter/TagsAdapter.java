package com.gaana.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track.Tags;
import com.gaana.models.Tracks.Track.TopArtists;
import com.gaana.models.Tracks.Track.TopLanguage;
import com.managers.u;
import java.util.ArrayList;

public class TagsAdapter extends Adapter<ViewHolder> {
    private boolean isRevampedDetailPage = false;
    private String language;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private OnTagClickListener onTagClickListener;
    private int rowLayout;
    private ArrayList<BusinessObject> tags;

    public interface OnTagClickListener {
        void onTagClick(ArrayList<BusinessObject> arrayList);
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.section_label);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public TagsAdapter(ArrayList<BusinessObject> arrayList, String str, int i, Context context, OnTagClickListener onTagClickListener, BaseGaanaFragment baseGaanaFragment) {
        this.tags = arrayList;
        this.rowLayout = i;
        this.mContext = context;
        this.onTagClickListener = onTagClickListener;
        this.language = str;
        this.mFragment = baseGaanaFragment;
        if (this.mFragment instanceof RevampedDetailListing) {
            this.isRevampedDetailPage = true;
        }
    }

    public void clearData() {
        if (this.tags != null) {
            this.tags.clear();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.rowLayout, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        int[] iArr = new int[]{R.attr.tags_button_attr, R.attr.tag_txt_color};
        viewHolder.title.setTypeface(null, 1);
        TextView textView;
        StringBuilder stringBuilder;
        TypedArray obtainStyledAttributes;
        if (this.tags.get(i) instanceof Tags) {
            Tags tags = (Tags) this.tags.get(i);
            textView = viewHolder.title;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.isRevampedDetailPage ? "" : "#");
            stringBuilder.append(tags.getTag_name(this.language));
            textView.setText(stringBuilder.toString());
            if (!tags.isSelected()) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(iArr);
                viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                obtainStyledAttributes.recycle();
            } else if (Constants.l) {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            } else {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.tags_selected_white));
            }
        } else if (this.tags.get(i) instanceof TopArtists) {
            TopArtists topArtists = (TopArtists) this.tags.get(i);
            textView = viewHolder.title;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.isRevampedDetailPage ? "" : "#");
            stringBuilder.append(topArtists.getArtist_name(this.language));
            textView.setText(stringBuilder.toString());
            if (!topArtists.isSelected()) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(iArr);
                viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                obtainStyledAttributes.recycle();
            } else if (Constants.l) {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            } else {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.tags_selected_white));
            }
        } else if (this.tags.get(i) instanceof TopLanguage) {
            TopLanguage topLanguage = (TopLanguage) this.tags.get(i);
            textView = viewHolder.title;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.isRevampedDetailPage ? "" : "#");
            stringBuilder.append(topLanguage.getLang_name(this.language));
            textView.setText(stringBuilder.toString());
            if (!topLanguage.isSelected()) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(iArr);
                viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                obtainStyledAttributes.recycle();
            } else if (Constants.l) {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            } else {
                viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                viewHolder.title.setTextColor(ContextCompat.getColor(this.mContext, R.color.tags_selected_white));
            }
        }
        viewHolder.title.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TypedArray obtainStyledAttributes;
                if (TagsAdapter.this.tags.get(viewHolder.getAdapterPosition()) instanceof Tags) {
                    Tags tags = (Tags) TagsAdapter.this.tags.get(viewHolder.getAdapterPosition());
                    tags.setSelected(tags.isSelected() ^ 1);
                    u.a().a("Tag", "Playlist Detail", tags.getEnglishName());
                    if (!tags.isSelected()) {
                        obtainStyledAttributes = TagsAdapter.this.mContext.obtainStyledAttributes(new int[]{R.attr.tags_button_attr, R.attr.tag_txt_color});
                        viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                        viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                        obtainStyledAttributes.recycle();
                    } else if (Constants.l) {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                    } else {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.tags_selected_white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                    }
                } else if (TagsAdapter.this.tags.get(viewHolder.getAdapterPosition()) instanceof TopArtists) {
                    TopArtists topArtists = (TopArtists) TagsAdapter.this.tags.get(viewHolder.getAdapterPosition());
                    topArtists.setSelected(topArtists.isSelected() ^ 1);
                    u.a().a("Tag", "Playlist Detail", topArtists.getEnglishName());
                    if (!topArtists.isSelected()) {
                        obtainStyledAttributes = TagsAdapter.this.mContext.obtainStyledAttributes(new int[]{R.attr.tags_button_attr, R.attr.tag_txt_color});
                        viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                        viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                        obtainStyledAttributes.recycle();
                    } else if (Constants.l) {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                    } else {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.tags_selected_white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                    }
                } else if (TagsAdapter.this.tags.get(viewHolder.getAdapterPosition()) instanceof TopLanguage) {
                    TopLanguage topLanguage = (TopLanguage) TagsAdapter.this.tags.get(viewHolder.getAdapterPosition());
                    topLanguage.setSelected(topLanguage.isSelected() ^ 1);
                    u.a().a("Tag", "Playlist Detail", topLanguage.getEnglishName());
                    if (!topLanguage.isSelected()) {
                        obtainStyledAttributes = TagsAdapter.this.mContext.obtainStyledAttributes(new int[]{R.attr.tags_button_attr, R.attr.tag_txt_color});
                        viewHolder.title.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
                        viewHolder.title.setTextColor(obtainStyledAttributes.getColor(1, -1));
                        obtainStyledAttributes.recycle();
                    } else if (Constants.l) {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected_white);
                    } else {
                        viewHolder.title.setTextColor(ContextCompat.getColor(TagsAdapter.this.mContext, R.color.tags_selected_white));
                        viewHolder.title.setBackgroundResource(R.drawable.rounded_button_tags_selected);
                    }
                }
                TagsAdapter.this.onTagClickListener.onTagClick(TagsAdapter.this.tags);
            }
        });
    }

    public int getItemCount() {
        return this.tags == null ? 0 : this.tags.size();
    }
}

package com.gaana.view.item;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.b.e;
import com.b.i;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaMiniSetupFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.CustomTextView;
import com.library.controls.CrossFadeImageView;
import com.til.colombia.android.internal.h;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.c;

public final class GaanaMiniListView extends BaseItemView implements OnClickListener, OnCheckedChangeListener {
    private HashMap _$_findViewCache;
    private boolean checkMost;
    private boolean checkRecent;
    public GaanaMiniSetupFragment fragment;
    private boolean mostExpanded;
    private OfflineTrack offlineTrack;
    private boolean recentExpanded;
    private ArrayList<?> trackArrayList;

    public static final class GaanaMiniChildViewHolder extends ViewHolder {
        private CheckBox mCheckboxTrack;
        private CrossFadeImageView mImgLeft;
        private CustomTextView mTxtGenre;
        private TextView mTxtHeader;

        public GaanaMiniChildViewHolder(View view) {
            c.b(view, "itemView");
            super(view);
            Object findViewById = view.findViewById(R.id.trackname);
            c.a(findViewById, "itemView.findViewById(R.id.trackname)");
            this.mTxtHeader = (TextView) findViewById;
            findViewById = view.findViewById(R.id.genere);
            c.a(findViewById, "itemView.findViewById(R.id.genere)");
            this.mTxtGenre = (CustomTextView) findViewById;
            findViewById = view.findViewById(R.id.thumb);
            c.a(findViewById, "itemView.findViewById(R.id.thumb)");
            this.mImgLeft = (CrossFadeImageView) findViewById;
            Object findViewById2 = view.findViewById(R.id.checkbox);
            c.a(findViewById2, "itemView.findViewById(R.id.checkbox)");
            this.mCheckboxTrack = (CheckBox) findViewById2;
        }

        public final TextView getMTxtHeader() {
            return this.mTxtHeader;
        }

        public final void setMTxtHeader(TextView textView) {
            c.b(textView, "<set-?>");
            this.mTxtHeader = textView;
        }

        public final CrossFadeImageView getMImgLeft() {
            return this.mImgLeft;
        }

        public final void setMImgLeft(CrossFadeImageView crossFadeImageView) {
            c.b(crossFadeImageView, "<set-?>");
            this.mImgLeft = crossFadeImageView;
        }

        public final CheckBox getMCheckboxTrack() {
            return this.mCheckboxTrack;
        }

        public final void setMCheckboxTrack(CheckBox checkBox) {
            c.b(checkBox, "<set-?>");
            this.mCheckboxTrack = checkBox;
        }

        public final CustomTextView getMTxtGenre() {
            return this.mTxtGenre;
        }

        public final void setMTxtGenre(CustomTextView customTextView) {
            c.b(customTextView, "<set-?>");
            this.mTxtGenre = customTextView;
        }
    }

    public static final class GaanaMiniParentViewHolder extends ViewHolder {
        private TextView belowHeader;
        private CheckBox mCheckbox;
        private RelativeLayout mMainLayout;
        private TextView mTxtHeader;

        public GaanaMiniParentViewHolder(View view) {
            c.b(view, "itemView");
            super(view);
            Object findViewById = view.findViewById(R.id.txt_header);
            c.a(findViewById, "itemView.findViewById(R.id.txt_header)");
            this.mTxtHeader = (TextView) findViewById;
            findViewById = view.findViewById(R.id.txt_header_below);
            c.a(findViewById, "itemView.findViewById(R.id.txt_header_below)");
            this.belowHeader = (TextView) findViewById;
            findViewById = view.findViewById(R.id.checkbox_setup);
            c.a(findViewById, "itemView.findViewById(R.id.checkbox_setup)");
            this.mCheckbox = (CheckBox) findViewById;
            Object findViewById2 = view.findViewById(R.id.main_layout);
            c.a(findViewById2, "itemView.findViewById(R.id.main_layout)");
            this.mMainLayout = (RelativeLayout) findViewById2;
        }

        public final TextView getMTxtHeader() {
            return this.mTxtHeader;
        }

        public final void setMTxtHeader(TextView textView) {
            c.b(textView, "<set-?>");
            this.mTxtHeader = textView;
        }

        public final TextView getBelowHeader() {
            return this.belowHeader;
        }

        public final void setBelowHeader(TextView textView) {
            c.b(textView, "<set-?>");
            this.belowHeader = textView;
        }

        public final CheckBox getMCheckbox() {
            return this.mCheckbox;
        }

        public final void setMCheckbox(CheckBox checkBox) {
            c.b(checkBox, "<set-?>");
            this.mCheckbox = checkBox;
        }

        public final RelativeLayout getMMainLayout() {
            return this.mMainLayout;
        }

        public final void setMMainLayout(RelativeLayout relativeLayout) {
            c.b(relativeLayout, "<set-?>");
            this.mMainLayout = relativeLayout;
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == null) {
            c.a();
        }
        if (compoundButton.getId() == R.id.checkbox_setup) {
            Object tag = compoundButton.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) tag;
            GaanaMiniSetupFragment gaanaMiniSetupFragment;
            if (str.equals("most")) {
                this.checkMost = z;
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                gaanaMiniSetupFragment.a(0, z);
            } else if (str.equals("recent")) {
                this.checkRecent = z;
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                gaanaMiniSetupFragment.a(1, z);
            }
        }
    }

    public void onClick(View view) {
        if (view == null) {
            c.a();
        }
        Object tag;
        GaanaMiniSetupFragment gaanaMiniSetupFragment;
        if (view.getId() == R.id.main_layout) {
            tag = view.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) tag;
            if (str.equals("most")) {
                this.mostExpanded ^= 1;
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                gaanaMiniSetupFragment.b(0);
            } else if (str.equals("recent")) {
                this.recentExpanded ^= 1;
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                gaanaMiniSetupFragment.b(1);
            } else {
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                gaanaMiniSetupFragment.b(2);
            }
        } else if (view.getId() == R.id.checkbox) {
            tag = view.getTag();
            Track track = new Track();
            Object tag2;
            if (tag instanceof OfflineTrack) {
                this.offlineTrack = (OfflineTrack) tag;
                OfflineTrack offlineTrack = this.offlineTrack;
                if (offlineTrack == null) {
                    c.a();
                }
                track.setName(offlineTrack.getName());
                offlineTrack = this.offlineTrack;
                if (offlineTrack == null) {
                    c.a();
                }
                track.setArtwork(offlineTrack.getImageUrl());
                offlineTrack = this.offlineTrack;
                if (offlineTrack == null) {
                    c.a();
                }
                track.setBusinessObjId(offlineTrack.getBusinessObjId());
                offlineTrack = this.offlineTrack;
                if (offlineTrack == null) {
                    c.a();
                }
                track.setIsSelected(Boolean.valueOf(offlineTrack.isSelected() ^ 1));
                gaanaMiniSetupFragment = this.fragment;
                if (gaanaMiniSetupFragment == null) {
                    c.b("fragment");
                }
                boolean isChecked = ((CheckBox) view).isChecked();
                tag2 = view.getTag(R.id.mini_state);
                if (tag2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                gaanaMiniSetupFragment.a(track, isChecked, ((Integer) tag2).intValue());
            } else if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Tracks.Track");
            } else {
                Track track2 = (Track) tag;
                track2.setIsSelected(Boolean.valueOf(track2.isSelected().booleanValue() ^ 1));
                CheckBox checkBox = (CheckBox) view;
                Object isSelected = track2.isSelected();
                c.a(isSelected, "tr.isSelected");
                checkBox.setChecked(isSelected.booleanValue());
                GaanaMiniSetupFragment gaanaMiniSetupFragment2 = this.fragment;
                if (gaanaMiniSetupFragment2 == null) {
                    c.b("fragment");
                }
                boolean isChecked2 = checkBox.isChecked();
                tag2 = view.getTag(R.id.mini_state);
                if (tag2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                gaanaMiniSetupFragment2.a(track2, isChecked2, ((Integer) tag2).intValue());
            }
        }
        super.onClick(view);
    }

    public final GaanaMiniSetupFragment getFragment() {
        GaanaMiniSetupFragment gaanaMiniSetupFragment = this.fragment;
        if (gaanaMiniSetupFragment == null) {
            c.b("fragment");
        }
        return gaanaMiniSetupFragment;
    }

    public final void setFragment(GaanaMiniSetupFragment gaanaMiniSetupFragment) {
        c.b(gaanaMiniSetupFragment, "<set-?>");
        this.fragment = gaanaMiniSetupFragment;
    }

    public final boolean getMostExpanded() {
        return this.mostExpanded;
    }

    public final void setMostExpanded(boolean z) {
        this.mostExpanded = z;
    }

    public final boolean getRecentExpanded() {
        return this.recentExpanded;
    }

    public final void setRecentExpanded(boolean z) {
        this.recentExpanded = z;
    }

    public final boolean getCheckMost() {
        return this.checkMost;
    }

    public final void setCheckMost(boolean z) {
        this.checkMost = z;
    }

    public final boolean getCheckRecent() {
        return this.checkRecent;
    }

    public final void setCheckRecent(boolean z) {
        this.checkRecent = z;
    }

    public final OfflineTrack getOfflineTrack() {
        return this.offlineTrack;
    }

    public final void setOfflineTrack(OfflineTrack offlineTrack) {
        this.offlineTrack = offlineTrack;
    }

    public GaanaMiniListView(Context context, BaseGaanaFragment baseGaanaFragment) {
        c.b(context, "context");
        c.b(baseGaanaFragment, "fragment");
        super(context, baseGaanaFragment);
        this.checkMost = true;
        this.checkRecent = true;
        this.mLayoutId = R.layout.item_setup_mini_header;
        this.fragment = (GaanaMiniSetupFragment) baseGaanaFragment;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject) {
        c.b(viewHolder, "holder");
        c.b(businessObject, "businessObj");
        if (viewHolder instanceof GaanaMiniParentViewHolder) {
            GaanaMiniParentViewHolder gaanaMiniParentViewHolder = (GaanaMiniParentViewHolder) viewHolder;
            TextView belowHeader = gaanaMiniParentViewHolder.getBelowHeader();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(businessObject.getCount());
            stringBuilder.append(" Song Selected");
            belowHeader.setText(stringBuilder.toString());
            gaanaMiniParentViewHolder.getMTxtHeader().setTypeface(Typeface.DEFAULT_BOLD);
            if (businessObject.getEmptyMsg().equals("most_played")) {
                gaanaMiniParentViewHolder.getMTxtHeader().setText("Most played downloads");
                gaanaMiniParentViewHolder.getMCheckbox().setTag("most");
                gaanaMiniParentViewHolder.getMCheckbox().setVisibility(0);
                gaanaMiniParentViewHolder.getMCheckbox().setChecked(this.checkMost);
                gaanaMiniParentViewHolder.getMCheckbox().setOnCheckedChangeListener(this);
                gaanaMiniParentViewHolder.getMMainLayout().setTag("most");
                gaanaMiniParentViewHolder.getMMainLayout().setOnClickListener(this);
            } else if (businessObject.getEmptyMsg().equals("recently_downloaded")) {
                gaanaMiniParentViewHolder.getMTxtHeader().setText("Recent downloads");
                gaanaMiniParentViewHolder.getMCheckbox().setTag("recent");
                gaanaMiniParentViewHolder.getMCheckbox().setChecked(this.checkRecent);
                gaanaMiniParentViewHolder.getMCheckbox().setOnCheckedChangeListener(this);
                gaanaMiniParentViewHolder.getMCheckbox().setVisibility(0);
                gaanaMiniParentViewHolder.getMMainLayout().setTag("recent");
                gaanaMiniParentViewHolder.getMMainLayout().setOnClickListener(this);
            } else {
                gaanaMiniParentViewHolder.getMTxtHeader().setText("All downloads");
                gaanaMiniParentViewHolder.getMCheckbox().setTag(h.l);
                gaanaMiniParentViewHolder.getMCheckbox().setVisibility(8);
                gaanaMiniParentViewHolder.getMMainLayout().setTag(h.l);
            }
        } else if (viewHolder instanceof GaanaMiniChildViewHolder) {
            TextAppearanceSpan textAppearanceSpan;
            Object obj;
            e eVar;
            TextAppearanceSpan textAppearanceSpan2;
            GaanaMiniChildViewHolder gaanaMiniChildViewHolder;
            String name;
            if (businessObject instanceof OfflineTrack) {
                OfflineTrack offlineTrack = (OfflineTrack) businessObject;
                textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.gaana_item_firstline_normal);
                obj = this.mContext;
                c.a(obj, "mContext");
                eVar = new e(i.a(obj.getAssets(), "fonts/Roboto-Medium.ttf"));
                textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.gaana_item_secondline);
                gaanaMiniChildViewHolder = (GaanaMiniChildViewHolder) viewHolder;
                TextView mTxtHeader = gaanaMiniChildViewHolder.getMTxtHeader();
                gaanaMiniChildViewHolder.getMImgLeft().setVisibility(0);
                gaanaMiniChildViewHolder.getMImgLeft().bindImage(offlineTrack.getImageUrl());
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(offlineTrack.getName());
                String albumName = offlineTrack.getAlbumName();
                String name2 = offlineTrack.getName();
                if (name2 == null) {
                    c.a();
                }
                spannableStringBuilder.setSpan(textAppearanceSpan, 0, name2.length(), 17);
                name = offlineTrack.getName();
                if (name == null) {
                    c.a();
                }
                spannableStringBuilder.setSpan(eVar, 0, name.length(), 17);
                mTxtHeader.setText(spannableStringBuilder);
                gaanaMiniChildViewHolder.getMTxtGenre().setText(albumName);
                gaanaMiniChildViewHolder.getMCheckboxTrack().setChecked(offlineTrack.isSelected());
                gaanaMiniChildViewHolder.getMCheckboxTrack().setTag(offlineTrack);
                if (offlineTrack.getEmptyMsg().equals("recently_downloaded")) {
                    gaanaMiniChildViewHolder.getMCheckboxTrack().setTag(R.id.mini_state, Integer.valueOf(1));
                } else {
                    gaanaMiniChildViewHolder.getMCheckboxTrack().setTag(R.id.mini_state, Integer.valueOf(2));
                }
                gaanaMiniChildViewHolder.getMCheckboxTrack().setOnClickListener(this);
            } else if (businessObject instanceof Track) {
                Track track = (Track) businessObject;
                textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.gaana_item_firstline_normal);
                obj = this.mContext;
                c.a(obj, "mContext");
                eVar = new e(i.a(obj.getAssets(), "fonts/Roboto-Medium.ttf"));
                textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.gaana_item_secondline);
                gaanaMiniChildViewHolder = (GaanaMiniChildViewHolder) viewHolder;
                TextView mTxtHeader2 = gaanaMiniChildViewHolder.getMTxtHeader();
                gaanaMiniChildViewHolder.getMImgLeft().setVisibility(0);
                gaanaMiniChildViewHolder.getMImgLeft().bindImage(track.atw);
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                spannableStringBuilder2.append(track.getName());
                CharSequence albumTitle = track.getAlbumTitle();
                spannableStringBuilder2.append("\n").append(albumTitle);
                String name3 = track.getName();
                if (name3 == null) {
                    c.a();
                }
                spannableStringBuilder2.setSpan(textAppearanceSpan, 0, name3.length(), 17);
                name = track.getName();
                if (name == null) {
                    c.a();
                }
                spannableStringBuilder2.setSpan(eVar, 0, name.length(), 17);
                String name4 = track.getName();
                if (name4 == null) {
                    c.a();
                }
                spannableStringBuilder2.setSpan(textAppearanceSpan2, name4.length(), spannableStringBuilder2.toString().length(), 17);
                mTxtHeader2.setText(spannableStringBuilder2);
                gaanaMiniChildViewHolder.getMTxtGenre().setText(albumTitle);
                CheckBox mCheckboxTrack = gaanaMiniChildViewHolder.getMCheckboxTrack();
                Object isSelected = track.isSelected();
                c.a(isSelected, "offlineTrack.isSelected");
                mCheckboxTrack.setChecked(isSelected.booleanValue());
                gaanaMiniChildViewHolder.getMCheckboxTrack().setTag(track);
                gaanaMiniChildViewHolder.getMCheckboxTrack().setTag(R.id.mini_state, Integer.valueOf(0));
                gaanaMiniChildViewHolder.getMCheckboxTrack().setOnClickListener(this);
            }
        }
        Object poplatedView = super.getPoplatedView(viewHolder, businessObject);
        c.a(poplatedView, "super.getPoplatedView(holder, businessObj)");
        return poplatedView;
    }
}

package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.PersonaDetailsFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.PersonaDedications.PersonaDedication;
import com.gaana.models.Playlists.Playlist;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager.BusinessObjectType;

public class TwoLineView extends BaseItemView {

    public static class TwoLineHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public TextView txtSubtitle;
        public TextView txtTitle;

        public TwoLineHolder(View view) {
            super(view);
            this.txtTitle = (TextView) view.findViewById(R.id.txt_title);
            this.txtSubtitle = (TextView) view.findViewById(R.id.txt_subtitle);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.img_artwork);
        }
    }

    public TwoLineView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_two_line_circular_img;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        TwoLineHolder twoLineHolder = (TwoLineHolder) viewHolder;
        PersonaDedication personaDedication = (PersonaDedication) businessObject;
        twoLineHolder.txtTitle.setText(personaDedication.getPersonaTitle());
        twoLineHolder.txtSubtitle.setText(personaDedication.getPersonaDescription());
        twoLineHolder.crossFadeImageView.bindImage(personaDedication.getArtwork());
        return this.mView;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.mFragment instanceof PersonaDedicationFragment) {
            PersonaDedication personaDedication = (PersonaDedication) view.getTag();
            Playlist playlist = new Playlist();
            playlist.setName(personaDedication.getRawPersonaTitle());
            playlist.setBusinessObjId(personaDedication.getPlaylistId());
            playlist.setArtwork(personaDedication.getArtwork());
            playlist.setPlaylistId(personaDedication.getPlaylistId());
            playlist.setSeokey(personaDedication.getPlaylistSeokey());
            playlist.setBusinessObjType(BusinessObjectType.Playlists);
            String personaTitle = personaDedication.getPersonaTitle();
            if (personaDedication.getGender() == 1) {
                personaTitle = this.mContext.getString(R.string.he_like, new Object[]{personaTitle});
            } else {
                personaTitle = this.mContext.getString(R.string.she_like, new Object[]{personaTitle});
            }
            Bundle a = PersonaDetailsFragment.a(playlist, "", ((PersonaDedicationFragment) this.mFragment).a(), personaTitle);
            BaseGaanaFragment personaDetailsFragment = new PersonaDetailsFragment();
            a.putString("page_title", personaDedication.getEnglishPersonaTitle());
            personaDetailsFragment.setArguments(a);
            ((GaanaActivity) this.mContext).displayFragment(personaDetailsFragment);
        }
    }
}

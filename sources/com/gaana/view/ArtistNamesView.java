package com.gaana.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.MoreInfo.Composer;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import java.util.ArrayList;
import java.util.Iterator;

public class ArtistNamesView extends LinearLayout {
    IArtistClickHandler _artistClickHandler;
    private ArrayList<ContextMenuArtist> _artistList;
    int _currentArtistIndex = 0;
    private LinearLayout artist_names_container;
    private BusinessObject mBusinessObject;
    private Context mContext;

    public class ContextMenuArtist {
        public String ArtistId;
        public String ArtistName;
    }

    public interface IArtistClickHandler {
        void ArtistClicked(String str, String str2);
    }

    public ArtistNamesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setArtistClickListener(IArtistClickHandler iArtistClickHandler) {
        this._artistClickHandler = iArtistClickHandler;
    }

    public ArtistNamesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ArtistNamesView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this._currentArtistIndex = 0;
    }

    public void initContainer(View view) {
        this.artist_names_container = (LinearLayout) view.findViewById(R.id.singer_names);
    }

    public void setBussinessObject(BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
    }

    public void setTitle(View view, String str) {
        ((TextView) view.findViewById(R.id.title)).setText(str);
    }

    public void addArtistNames(int i) {
        setArtistList();
        if (this._artistList.size() >= this._currentArtistIndex + 1) {
            CharSequence fromHtml;
            ContextMenuArtist contextMenuArtist = (ContextMenuArtist) this._artistList.get(this._currentArtistIndex);
            this._currentArtistIndex++;
            TextView textView = new TextView(this.mContext);
            textView.setTag(contextMenuArtist);
            textView.setTextSize(2, 14.0f);
            String str = Constants.l ? "black" : "white";
            StringBuilder stringBuilder;
            if (this.artist_names_container.getChildCount() == 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("<u><font color=\"");
                stringBuilder.append(str);
                stringBuilder.append("\">");
                stringBuilder.append(contextMenuArtist.ArtistName.trim());
                stringBuilder.append("</font></u>");
                fromHtml = Html.fromHtml(stringBuilder.toString(), null, null);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(" , <u><font color=\"");
                stringBuilder.append(str);
                stringBuilder.append("\">");
                stringBuilder.append(contextMenuArtist.ArtistName.trim());
                stringBuilder.append("</font></u>");
                fromHtml = Html.fromHtml(stringBuilder.toString(), null, null);
            }
            textView.setText(fromHtml);
            textView.measure(0, 0);
            int measuredWidth = textView.getMeasuredWidth();
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ContextMenuArtist contextMenuArtist = (ContextMenuArtist) ((TextView) view).getTag();
                    if (ArtistNamesView.this._artistClickHandler != null) {
                        ArtistNamesView.this._artistClickHandler.ArtistClicked(contextMenuArtist.ArtistId, contextMenuArtist.ArtistName);
                    }
                }
            });
            this.artist_names_container.measure(-2, -2);
            if (i >= measuredWidth + this.artist_names_container.getMeasuredWidth()) {
                this.artist_names_container.addView(textView);
            }
        }
    }

    private void setArtistList() {
        if (this._artistList == null) {
            ArrayList arrayList = new ArrayList();
            Iterator it;
            ContextMenuArtist contextMenuArtist;
            switch (this.mBusinessObject.getBusinessObjType()) {
                case Tracks:
                    if (!(this.mBusinessObject instanceof Track) || ((Track) this.mBusinessObject).getArtists() == null) {
                        if (this.mBusinessObject instanceof OfflineTrack) {
                            OfflineTrack offlineTrack = (OfflineTrack) this.mBusinessObject;
                            for (String str : offlineTrack.getArtistName().split(",")) {
                                ContextMenuArtist contextMenuArtist2 = new ContextMenuArtist();
                                contextMenuArtist2.ArtistName = str;
                                contextMenuArtist2.ArtistId = offlineTrack.getArtistId();
                                arrayList.add(contextMenuArtist2);
                            }
                            break;
                        }
                    }
                    it = ((Track) this.mBusinessObject).getArtists().iterator();
                    while (it.hasNext()) {
                        Artist artist = (Artist) it.next();
                        contextMenuArtist = new ContextMenuArtist();
                        contextMenuArtist.ArtistName = artist.getName(((Track) this.mBusinessObject).getLanguage());
                        contextMenuArtist.ArtistId = artist.artist_id;
                        arrayList.add(contextMenuArtist);
                    }
                    break;
                    break;
                case Albums:
                    ArrayList composers = ((Album) this.mBusinessObject).getComposers();
                    if (composers != null) {
                        it = composers.iterator();
                        while (it.hasNext()) {
                            Composer composer = (Composer) it.next();
                            contextMenuArtist = new ContextMenuArtist();
                            contextMenuArtist.ArtistName = composer.getName();
                            contextMenuArtist.ArtistId = composer.getEId();
                            arrayList.add(contextMenuArtist);
                        }
                        break;
                    }
                    break;
            }
            this._artistList = arrayList;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        addArtistNames(MeasureSpec.getSize(i));
    }
}

package com.gaana.juke;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class JukePlaylists extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("all_party_playlist")
    private ArrayList<JukePlaylist> arrListPlaylist;
    @SerializedName("nick_name")
    private String title;

    public ArrayList<JukePlaylist> getArrListBusinessObj() {
        return this.arrListPlaylist;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListPlaylist = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof JukePlaylist) {
                this.arrListPlaylist.add((JukePlaylist) businessObject);
            }
        }
    }
}

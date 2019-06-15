package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class RadioLiveDetails implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("radios")
    private ArrayList<Radio> arrListRadio;
    @SerializedName("status")
    public String status;
    @SerializedName("uts")
    public String user_token_status;

    public static class Radio {
        @SerializedName("image")
        private String artWork;
        @SerializedName("id")
        private String radioId;
        @SerializedName("radio_theme")
        private String radioTheme;
        @SerializedName("radio_stream")
        private String radio_stream;
        @SerializedName("user_favorite")
        private String user_favorite;

        public String getRadioId() {
            return this.radioId;
        }

        public void setRadioId(String str) {
            this.radioId = str;
        }

        public String getName() {
            return Constants.b(this.radioTheme);
        }

        public String getRawName() {
            return this.radioTheme;
        }

        public String getEnglishName() {
            return Constants.a(this.radioTheme);
        }

        public void setName(String str) {
            this.radioTheme = str;
        }

        public String getArtwork() {
            return this.artWork;
        }

        public void setArtwork(String str) {
            this.artWork = str;
        }

        public String getRadioStream() {
            return this.radio_stream;
        }

        public void setRadioStream(String str) {
            this.radio_stream = str;
        }
    }

    public ArrayList<Radio> getArrListRadioObj() {
        return this.arrListRadio;
    }

    public void setArrListBusinessObj(ArrayList<Radio> arrayList) {
        this.arrListRadio = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.arrListRadio.add((Radio) it.next());
        }
    }

    public String getUserTokenStatus() {
        return this.user_token_status;
    }

    public String getStatus() {
        return this.status;
    }
}

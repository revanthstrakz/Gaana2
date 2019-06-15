package com.helpshift.campaigns.models;

import com.google.android.gms.cast.HlsSegmentFormat;
import com.helpshift.util.l;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class CampaignSyncModel implements Serializable {
    private static final long serialVersionUID = 2;
    public String a;
    public String b;
    public long c;
    public long d = Long.MAX_VALUE;
    private boolean e;

    public CampaignSyncModel(JSONObject jSONObject) {
        try {
            this.a = jSONObject.getString("cid");
            this.b = jSONObject.getString("creative-url");
            this.c = jSONObject.getLong(HlsSegmentFormat.TS);
            this.d = jSONObject.optLong("expires", Long.MAX_VALUE);
            this.e = false;
        } catch (JSONException e) {
            l.a("Helpshift_CampSyncMod", "Exception in initializing model with json object : ", e);
        }
    }

    public boolean a() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.a);
        objectOutputStream.writeUTF(this.b);
        objectOutputStream.writeLong(this.c);
        objectOutputStream.writeBoolean(this.e);
        objectOutputStream.writeLong(this.d);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.a = objectInputStream.readUTF();
        this.b = objectInputStream.readUTF();
        this.c = objectInputStream.readLong();
        this.e = objectInputStream.readBoolean();
        try {
            this.d = objectInputStream.readLong();
        } catch (EOFException unused) {
            this.d = Long.MAX_VALUE;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof CampaignSyncModel)) {
            return false;
        }
        CampaignSyncModel campaignSyncModel = (CampaignSyncModel) obj;
        if (this.e == campaignSyncModel.e && this.a.equals(campaignSyncModel.a) && this.b.equals(campaignSyncModel.b) && this.c == campaignSyncModel.c && this.d == campaignSyncModel.d) {
            z = true;
        }
        return z;
    }
}

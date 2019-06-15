package com.helpshift.campaigns.models;

import android.app.Activity;
import com.helpshift.c;
import com.helpshift.enums.ACTION_TYPE;
import com.helpshift.executors.ActionExecutor;
import com.helpshift.util.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ActionModel implements Serializable {
    private static final String f = "ActionModel";
    private static final long serialVersionUID = 1;
    public String a;
    public ACTION_TYPE b;
    public String c;
    public String d;
    public boolean e;
    private String g;
    private ActionExecutor h;

    ActionModel(JSONObject jSONObject) {
        try {
            this.g = jSONObject.getString("id");
            this.a = jSONObject.getString("t");
            this.b = ACTION_TYPE.getEnum(jSONObject.getInt("a"));
            this.c = jSONObject.optString("d", "");
            this.d = jSONObject.getString("c");
            this.e = jSONObject.getBoolean("g");
            this.h = c.b();
        } catch (JSONException e) {
            l.a(f, "Exception while creating actionType object from json : ", e);
        }
    }

    public void a(Activity activity) {
        if (this.h != null) {
            this.h.a(activity, this.b, this.c);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.g);
        objectOutputStream.writeUTF(this.a);
        objectOutputStream.writeObject(this.b);
        objectOutputStream.writeUTF(this.c);
        objectOutputStream.writeUTF(this.d);
        objectOutputStream.writeBoolean(this.e);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.c = objectInputStream.readUTF();
        this.a = objectInputStream.readUTF();
        this.b = (ACTION_TYPE) objectInputStream.readObject();
        this.c = objectInputStream.readUTF();
        this.d = objectInputStream.readUTF();
        this.e = objectInputStream.readBoolean();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ActionModel)) {
            return false;
        }
        ActionModel actionModel = (ActionModel) obj;
        boolean z = this.g.equals(actionModel.g) && this.a.equals(actionModel.a) && this.b == actionModel.b && this.c.equals(actionModel.c) && this.d.equals(actionModel.d) && this.e == actionModel.e;
        if (this.h != null) {
            if (!(z && actionModel.h != null && this.h.getClass().getName().equals(actionModel.h.getClass().getName()))) {
                return false;
            }
        } else if (!(z && actionModel.h == null)) {
            return false;
        }
        return true;
    }
}

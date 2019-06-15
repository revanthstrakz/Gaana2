package com.helpshift.campaigns.models;

import android.location.Location;
import android.text.TextUtils;
import com.helpshift.campaigns.m.a.b;
import com.helpshift.util.i;
import com.helpshift.util.s;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PropertyValue implements Serializable {
    private static final long serialVersionUID = 2;
    private Object a;
    private String b;
    private Integer c;

    public PropertyValue(Object obj) {
        this.a = obj;
        this.b = "u";
        this.c = b.a;
        if (obj instanceof String) {
            String trim = ((String) obj).trim();
            if (!TextUtils.isEmpty(trim)) {
                this.b = "s";
                this.a = trim;
            }
        } else if (obj instanceof Long) {
            this.b = "n";
        } else if (obj instanceof Boolean) {
            this.b = com.helpshift.support.webkit.b.a;
        } else if (obj instanceof Date) {
            this.b = "d";
        } else if (obj instanceof Location) {
            this.b = "l";
            this.a = s.a((Location) obj);
        }
        if (this.b.equals("u")) {
            this.a = null;
        }
    }

    public PropertyValue(String str, String str2) {
        this.b = str;
        if (!(str2 == null || str == null)) {
            this.a = a(str2.trim());
        }
        if (this.a == null) {
            this.b = "u";
        }
        this.c = b.a;
    }

    public Object a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }

    public void a(Integer num) {
        if (num != null && b.d.contains(num)) {
            this.c = num;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof PropertyValue)) {
            return false;
        }
        PropertyValue propertyValue = (PropertyValue) obj;
        if (this.c.equals(propertyValue.c) && this.b.equals(propertyValue.b) && this.a.equals(propertyValue.a)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        if (this.a == null) {
            return null;
        }
        String obj = this.a.toString();
        if (this.b.equals("d")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(((Date) this.a).getTime());
            return stringBuilder.toString();
        } else if (!this.b.equals("l")) {
            return obj;
        } else {
            Location location = (Location) this.a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(location.getLatitude());
            stringBuilder2.append(",");
            stringBuilder2.append(location.getLongitude());
            return stringBuilder2.toString();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{  }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A:{SYNTHETIC, Splitter:B:32:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A:{SYNTHETIC, Splitter:B:28:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{  }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A:{SYNTHETIC, Splitter:B:32:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A:{SYNTHETIC, Splitter:B:28:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{  }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A:{SYNTHETIC, Splitter:B:32:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A:{SYNTHETIC, Splitter:B:28:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{  }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A:{SYNTHETIC, Splitter:B:32:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A:{SYNTHETIC, Splitter:B:28:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A:{Catch:{  }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A:{SYNTHETIC, Splitter:B:32:0x0083} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A:{SYNTHETIC, Splitter:B:28:0x0055} */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    private java.lang.Object a(java.lang.String r7) {
        /*
        r6 = this;
        r0 = r6.b;
        r1 = r0.hashCode();
        r2 = 98;
        r3 = 1;
        r4 = 0;
        if (r1 == r2) goto L_0x0045;
    L_0x000c:
        r2 = 100;
        if (r1 == r2) goto L_0x003b;
    L_0x0010:
        r2 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r1 == r2) goto L_0x0031;
    L_0x0014:
        r2 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r1 == r2) goto L_0x0027;
    L_0x0018:
        r2 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 == r2) goto L_0x001d;
    L_0x001c:
        goto L_0x004f;
    L_0x001d:
        r1 = "s";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004f;
    L_0x0025:
        r0 = r4;
        goto L_0x0050;
    L_0x0027:
        r1 = "n";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004f;
    L_0x002f:
        r0 = r3;
        goto L_0x0050;
    L_0x0031:
        r1 = "l";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004f;
    L_0x0039:
        r0 = 4;
        goto L_0x0050;
    L_0x003b:
        r1 = "d";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004f;
    L_0x0043:
        r0 = 2;
        goto L_0x0050;
    L_0x0045:
        r1 = "b";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x004f;
    L_0x004d:
        r0 = 3;
        goto L_0x0050;
    L_0x004f:
        r0 = -1;
    L_0x0050:
        r1 = 0;
        switch(r0) {
            case 0: goto L_0x0097;
            case 1: goto L_0x008e;
            case 2: goto L_0x0083;
            case 3: goto L_0x0079;
            case 4: goto L_0x0055;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x009e;
    L_0x0055:
        r0 = ",";
        r7 = r7.split(r0);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r0 = new android.location.Location;	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r2 = "";
        r0.<init>(r2);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r2 = r7[r4];	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r4 = java.lang.Double.parseDouble(r2);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r0.setLatitude(r4);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r7 = r7[r3];	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r2 = java.lang.Double.parseDouble(r7);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r0.setLongitude(r2);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        r7 = com.helpshift.util.s.a(r0);	 Catch:{ ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e, ArrayIndexOutOfBoundsException | NumberFormatException -> 0x009e }
        goto L_0x0081;
    L_0x0079:
        r7 = java.lang.Boolean.parseBoolean(r7);
        r7 = java.lang.Boolean.valueOf(r7);
    L_0x0081:
        r1 = r7;
        goto L_0x009e;
    L_0x0083:
        r0 = new java.util.Date;	 Catch:{  }
        r2 = java.lang.Long.parseLong(r7);	 Catch:{  }
        r0.<init>(r2);	 Catch:{  }
        r1 = r0;
        goto L_0x009e;
    L_0x008e:
        r2 = java.lang.Long.parseLong(r7);	 Catch:{  }
        r7 = java.lang.Long.valueOf(r2);	 Catch:{  }
        goto L_0x0081;
    L_0x0097:
        r0 = android.text.TextUtils.isEmpty(r7);
        if (r0 != 0) goto L_0x009e;
    L_0x009d:
        goto L_0x0081;
    L_0x009e:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.models.PropertyValue.a(java.lang.String):java.lang.Object");
    }

    public ArrayList d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, this.b);
        if (this.b.equals("l")) {
            Location location = (Location) this.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(location.getLatitude());
            stringBuilder.append(",");
            stringBuilder.append(location.getLongitude());
            arrayList.add(1, stringBuilder.toString());
        } else if (this.b.equals("d")) {
            arrayList.add(1, i.b.format((Date) this.a));
        } else {
            arrayList.add(1, this.a);
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    public boolean a(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r5 instanceof java.lang.String;
        r1 = 1;
        r2 = 0;
        if (r0 == 0) goto L_0x003a;
    L_0x0006:
        r0 = r4.b;
        r3 = "s";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x001a;
    L_0x0010:
        r0 = r4.b;
        r3 = "u";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x003a;
    L_0x001a:
        r5 = (java.lang.String) r5;
        r5 = r5.trim();
        r0 = r5;
        r0 = (java.lang.String) r0;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0037;
    L_0x0029:
        r0 = r4.a;
        r0 = r5.equals(r0);
        if (r0 != 0) goto L_0x0037;
    L_0x0031:
        r0 = "s";
        r4.b = r0;
        goto L_0x00d7;
    L_0x0037:
        r1 = r2;
        goto L_0x00d7;
    L_0x003a:
        r0 = r5 instanceof java.lang.Long;
        if (r0 == 0) goto L_0x0060;
    L_0x003e:
        r0 = r4.b;
        r3 = "n";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x0052;
    L_0x0048:
        r0 = r4.b;
        r3 = "u";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0060;
    L_0x0052:
        r0 = r4.a;
        r0 = r5.equals(r0);
        if (r0 != 0) goto L_0x0060;
    L_0x005a:
        r0 = "n";
        r4.b = r0;
        goto L_0x00d7;
    L_0x0060:
        r0 = r5 instanceof java.lang.Boolean;
        if (r0 == 0) goto L_0x0085;
    L_0x0064:
        r0 = r4.b;
        r3 = "b";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x0078;
    L_0x006e:
        r0 = r4.b;
        r3 = "u";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0085;
    L_0x0078:
        r0 = r4.a;
        r0 = r5.equals(r0);
        if (r0 != 0) goto L_0x0085;
    L_0x0080:
        r0 = "b";
        r4.b = r0;
        goto L_0x00d7;
    L_0x0085:
        r0 = r5 instanceof java.util.Date;
        if (r0 == 0) goto L_0x00aa;
    L_0x0089:
        r0 = r4.b;
        r3 = "d";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x009d;
    L_0x0093:
        r0 = r4.b;
        r3 = "u";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x00aa;
    L_0x009d:
        r0 = r4.a;
        r0 = r5.equals(r0);
        if (r0 != 0) goto L_0x00aa;
    L_0x00a5:
        r0 = "d";
        r4.b = r0;
        goto L_0x00d7;
    L_0x00aa:
        r0 = r5 instanceof android.location.Location;
        if (r0 == 0) goto L_0x0037;
    L_0x00ae:
        r0 = r4.b;
        r3 = "l";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x00c2;
    L_0x00b8:
        r0 = r4.b;
        r3 = "u";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0037;
    L_0x00c2:
        r0 = r4.a;
        r0 = (android.location.Location) r0;
        r3 = r5;
        r3 = (android.location.Location) r3;
        r0 = com.helpshift.util.s.b(r0, r3);
        if (r0 != 0) goto L_0x0037;
    L_0x00cf:
        r5 = "l";
        r4.b = r5;
        r5 = com.helpshift.util.s.a(r3);
    L_0x00d7:
        if (r1 == 0) goto L_0x00df;
    L_0x00d9:
        r4.a = r5;
        r5 = com.helpshift.campaigns.m.a.b.a;
        r4.c = r5;
    L_0x00df:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.models.PropertyValue.a(java.lang.Object):boolean");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.a);
        objectOutputStream.writeInt(this.c.intValue());
        objectOutputStream.writeUTF(this.b);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.a = objectInputStream.readObject();
        this.c = Integer.valueOf(objectInputStream.readInt());
        this.b = objectInputStream.readUTF();
    }
}

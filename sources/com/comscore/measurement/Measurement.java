package com.comscore.measurement;

import com.comscore.analytics.Core;
import com.comscore.utils.Constants;
import com.comscore.utils.Date;
import java.util.HashMap;

public class Measurement {
    protected HashMap<String, Label> a = new HashMap();
    protected String b;
    protected long c;

    protected Measurement(Core core) {
        setPixelURL(core.getPixelURL());
        this.c = Date.unixTime();
    }

    /* Access modifiers changed, original: protected */
    public Boolean a(String str, long j) {
        return a(str, Long.toString(j));
    }

    /* Access modifiers changed, original: protected */
    public Boolean a(String str, String str2) {
        Label label = (Label) this.a.get(str);
        return Boolean.valueOf(label != null ? label.value.equals(str2) : false);
    }

    /* Access modifiers changed, original: protected */
    public void a(Label label) {
        if (this.a.get(label.name) == null) {
            this.a.put(label.name, label);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x0015 in {2, 4, 5} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    protected void a(java.lang.String r1, java.lang.String r2, java.lang.Boolean r3, java.lang.Boolean r4) {
        /*
        r0 = this;
        r4 = r4.booleanValue();
        if (r4 == 0) goto L_0x000f;
        r4 = new com.comscore.measurement.PrivilegedLabel;
        r4.<init>(r1, r2, r3);
        r0.a(r4);
        return;
        r4 = new com.comscore.measurement.Label;
        r4.<init>(r1, r2, r3);
        goto L_0x000b;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.measurement.Measurement.a(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean):void");
    }

    /* Access modifiers changed, original: protected */
    public void a(HashMap<String, String> hashMap) {
        a((HashMap) hashMap, false);
    }

    /* Access modifiers changed, original: protected */
    public void a(HashMap<String, String> hashMap, boolean z) {
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                setLabel(str, (String) hashMap.get(str), Boolean.valueOf(z));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(String str, String str2, Boolean bool, Boolean bool2) {
        removeLabel(str, bool, bool2);
        a(str, str2, bool, bool2);
    }

    public Label getLabel(String str) {
        return (Label) this.a.get(str);
    }

    public String getPixelURL() {
        return this.b;
    }

    public Boolean hasLabel(String str) {
        return Boolean.valueOf(this.a.containsKey(str));
    }

    public void removeLabel(String str, Boolean bool, Boolean bool2) {
        Label label = (Label) this.a.get(str);
        if (label == null) {
            return;
        }
        if (!(label instanceof PrivilegedLabel) || bool2.booleanValue() || bool.booleanValue()) {
            this.a.remove(str);
        }
    }

    public String retrieveLabelsAsString(String[] strArr) {
        Label label;
        StringBuilder stringBuilder = new StringBuilder();
        HashMap hashMap = new HashMap(this.a);
        for (Object obj : strArr) {
            label = (Label) hashMap.get(obj);
            if (label != null) {
                stringBuilder.append(label.pack());
                hashMap.remove(obj);
            }
        }
        if (strArr != Constants.LABELS_ORDER) {
            for (Object obj2 : Constants.LABELS_ORDER) {
                label = (Label) hashMap.get(obj2);
                if (label != null) {
                    stringBuilder.append(label.pack());
                    hashMap.remove(obj2);
                }
            }
        }
        for (Label pack : hashMap.values()) {
            stringBuilder.append(pack.pack());
        }
        return (stringBuilder.length() <= 0 || stringBuilder.charAt(0) != '&') ? stringBuilder.toString() : stringBuilder.substring(1);
    }

    public void setLabel(Label label) {
        removeLabel(label.name, label.aggregate, Boolean.valueOf(label instanceof PrivilegedLabel));
        a(label);
    }

    public void setLabel(String str, String str2) {
        setLabel(str, str2, Boolean.valueOf(false));
    }

    public void setLabel(String str, String str2, Boolean bool) {
        removeLabel(str, bool, Boolean.valueOf(false));
        a(str, str2, bool, Boolean.valueOf(false));
    }

    public void setPixelURL(String str) {
        this.b = str;
    }
}

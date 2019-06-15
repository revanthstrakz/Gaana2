package com.comscore.applications;

import com.comscore.analytics.Core;
import com.comscore.measurement.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AggregateMeasurement extends ApplicationMeasurement {
    protected AggregateMeasurement(Core core, EventType eventType, String str) {
        super(core, eventType, str);
    }

    private Boolean a(String str) {
        String str2 = "0123456789";
        for (int i = 0; i < str.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str.charAt(i));
            if (!str2.contains(stringBuilder.toString())) {
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(true);
    }

    private Boolean b(String str) {
        return str == null ? Boolean.valueOf(false) : !str.contains(",") ? Boolean.valueOf(false) : !str.contains(" ") ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    private Boolean b(String str, String str2) {
        return Boolean.valueOf(str.contains(str2));
    }

    private String c(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(str2);
        for (String str22 : c(str)) {
            if (stringBuilder.toString().contains(str22)) {
                String[] split = stringBuilder.toString().split(";");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].contains(str22)) {
                        String[] split2 = split[i].split(":");
                        Integer valueOf = Integer.valueOf(Integer.valueOf(split2[1]).intValue() + 1);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(split2[0]);
                        stringBuilder2.append(":");
                        stringBuilder2.append(valueOf);
                        stringBuilder.replace(stringBuilder.indexOf(split[i]), stringBuilder.indexOf(split[i]) + split[i].length(), stringBuilder2.toString());
                    }
                }
            } else {
                if (!stringBuilder.toString().equals("")) {
                    stringBuilder.append(";");
                }
                stringBuilder.append(str22);
                stringBuilder.append(":1");
            }
        }
        return stringBuilder.toString();
    }

    private List<String> c(String str) {
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList();
        for (Object add : split) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void aggregateLabels(List<Label> list) {
        for (Label label : list) {
            String str;
            Label label2 = (Label) this.a.get(label.name);
            if (label2 != null) {
                String num;
                if (a(label2.value).booleanValue() && a(label.value).booleanValue()) {
                    Integer valueOf = Integer.valueOf(Integer.valueOf(label2.value).intValue() + Integer.valueOf(label.value).intValue());
                    str = label2.name;
                    num = valueOf.toString();
                } else if (b(label.value).booleanValue()) {
                    str = c(label.value, label2.value);
                } else if (!b(label2.value, label.value).booleanValue()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(label2.value);
                    stringBuilder.append(",");
                    stringBuilder.append(label.value);
                    num = stringBuilder.toString();
                    str = label2.name;
                }
                setLabel(str, num, Boolean.valueOf(true));
            } else if (b(label.value).booleanValue()) {
                str = c(label.value, "");
            } else {
                setLabel(label);
            }
            setLabel(label.name, str, Boolean.valueOf(true));
        }
    }

    public void formatLists() {
        ArrayList arrayList = new ArrayList();
        for (Label label : this.a.values()) {
            if (b(label.value).booleanValue()) {
                arrayList.add(label);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Label label2 = (Label) it.next();
            setLabel(label2.name, c(label2.value, ""), Boolean.valueOf(true));
        }
    }

    public List<Label> getAggregateLabels() {
        ArrayList arrayList = new ArrayList();
        for (Label label : this.a.values()) {
            if (label.aggregate.booleanValue()) {
                arrayList.add(label);
            }
        }
        return arrayList;
    }
}

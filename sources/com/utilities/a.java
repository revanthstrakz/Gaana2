package com.utilities;

import java.util.Calendar;
import java.util.Date;

public class a {

    public static class a {
        private int a;
        private int b;
        private int c;

        private a() {
        }

        public a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        public int a() {
            return this.c;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.c);
            stringBuilder.append(" Years, ");
            stringBuilder.append(this.b);
            stringBuilder.append(" Months, ");
            stringBuilder.append(this.a);
            stringBuilder.append(" Days");
            return stringBuilder.toString();
        }
    }

    public static a a(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(date.getTime());
        long currentTimeMillis = System.currentTimeMillis();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(currentTimeMillis);
        int i = instance2.get(1) - instance.get(1);
        int i2 = instance2.get(2) + 1;
        int i3 = instance.get(2) + 1;
        int i4 = i2 - i3;
        if (i4 < 0) {
            i--;
            i4 = (12 - i3) + i2;
            if (instance2.get(5) < instance.get(5)) {
                i4--;
            }
        } else if (i4 == 0 && instance2.get(5) < instance.get(5)) {
            i--;
            i4 = 11;
        }
        int i5 = 0;
        if (instance2.get(5) > instance.get(5)) {
            i5 = instance2.get(5) - instance.get(5);
        } else if (instance2.get(5) < instance.get(5)) {
            i2 = instance2.get(5);
            instance2.add(2, -1);
            i5 = (instance2.getActualMaximum(5) - instance.get(5)) + i2;
        } else if (i4 == 12) {
            i++;
            i4 = 0;
        }
        return new a(i5, i4, i);
    }
}

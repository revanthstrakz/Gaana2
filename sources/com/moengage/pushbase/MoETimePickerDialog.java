package com.moengage.pushbase;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.widget.TimePicker;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Calendar;

public class MoETimePickerDialog extends TimePickerDialog {
    private Calendar calendar = Calendar.getInstance();
    private int currentHour = 0;
    private int currentMinute = 0;
    private DateFormat dateFormat;
    private int maxHour = 25;
    private int maxMinute = 25;
    private int minHour = -1;
    private int minMinute = -1;

    public MoETimePickerDialog(Context context, OnTimeSetListener onTimeSetListener, int i, int i2, boolean z) {
        super(context, onTimeSetListener, i, i2, z);
        this.currentHour = i;
        this.currentMinute = i2;
        this.dateFormat = DateFormat.getTimeInstance(3);
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mTimePicker");
            declaredField.setAccessible(true);
            ((TimePicker) declaredField.get(this)).setOnTimeChangedListener(this);
        } catch (Exception | IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
        }
    }

    public void setMin(int i, int i2) {
        this.minHour = i;
        this.minMinute = i2;
    }

    public void setMax(int i, int i2) {
        this.maxHour = i;
        this.maxMinute = i2;
    }

    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        Object obj = (i < this.minHour || (i == this.minHour && i2 < this.minMinute)) ? null : 1;
        if (i > this.maxHour || (i == this.maxHour && i2 > this.maxMinute)) {
            obj = null;
        }
        if (obj != null) {
            this.currentHour = i;
            this.currentMinute = i2;
        }
        updateTime(this.currentHour, this.currentMinute);
        updateDialogTitle(timePicker, this.currentHour, this.currentMinute);
    }

    private void updateDialogTitle(TimePicker timePicker, int i, int i2) {
        this.calendar.set(11, i);
        this.calendar.set(12, i2);
        setTitle(this.dateFormat.format(this.calendar.getTime()));
    }
}

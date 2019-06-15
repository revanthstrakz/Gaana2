package com.moengage.pushbase.fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.widget.TimePicker;
import com.moengage.core.Logger;
import com.moengage.pushbase.MoETimePickerDialog;
import com.moengage.pushbase.push.MoEPushWorker;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements OnTimeSetListener {
    private static boolean timeDialogShown = false;
    private final String DAY = "day";
    private final String MONTH = "month";
    private final String YEAR = "year";
    private Bundle extras;

    public Dialog onCreateDialog(Bundle bundle) {
        this.extras = getArguments();
        if (this.extras == null) {
            Logger.e("TimePickerFragment$onCreateDialog : Extras is null");
            dismiss();
        }
        timeDialogShown = true;
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        MoETimePickerDialog moETimePickerDialog = new MoETimePickerDialog(new ContextThemeWrapper(getActivity(), 16973934), this, i, i2, DateFormat.is24HourFormat(getActivity()));
        moETimePickerDialog.setMin(i, i2);
        return moETimePickerDialog;
    }

    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        int i3 = this.extras.getInt("year");
        int i4 = this.extras.getInt("month");
        int i5 = this.extras.getInt("day");
        this.extras.remove("year");
        this.extras.remove("month");
        this.extras.remove("day");
        Calendar instance = Calendar.getInstance();
        instance.set(i3, i4, i5, i, i2, 0);
        long timeInMillis = instance.getTimeInMillis();
        Intent intent = new Intent(getActivity(), MoEPushWorker.class);
        intent.setAction(MoEPushWorker.EXTRA_SERVICE_NOTIFY);
        intent.putExtras(this.extras);
        PendingIntent service = PendingIntent.getService(getActivity(), (int) System.currentTimeMillis(), intent, 134217728);
        FragmentActivity activity = getActivity();
        getActivity();
        ((AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, timeInMillis, service);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TimePickerFragment : Reminder set at :");
        stringBuilder.append(instance.getTime());
        Logger.v(stringBuilder.toString());
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (getActivity() != null) {
            getActivity().finish();
        }
        Logger.v("TimePickerFragment$onDismiss: PushTracker:Completed");
        timeDialogShown = false;
    }

    public static boolean isTimeDialogShown() {
        return timeDialogShown;
    }
}

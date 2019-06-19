package com.moengage.pushbase.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
import com.moengage.core.Logger;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
    private static boolean dateDialogShown;
    private final String DAY = "day";
    private final String MONTH = "month";
    private final String YEAR = "year";
    private Bundle extras;

    public Dialog onCreateDialog(Bundle bundle) {
        this.extras = getArguments();
        if (this.extras == null) {
            dismiss();
            Logger.v("DatePickerFragment$onCreateDialog : Extras is null");
        }
        dateDialogShown = true;
        Calendar instance = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(getActivity(), 16973934), this, instance.get(1), instance.get(2), instance.get(5));
        if (VERSION.SDK_INT >= 11) {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        return datePickerDialog;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.extras.putInt("year", i);
        this.extras.putInt("month", i2);
        this.extras.putInt("day", i3);
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(this.extras);
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!TimePickerFragment.isTimeDialogShown()) {
            if (getActivity() != null) {
                getActivity().finish();
            }
            Logger.v("DatePickerFragment$onDismiss: PushTracker:Completed");
        }
        dateDialogShown = false;
    }

    public static boolean isDateDialogShown() {
        return dateDialogShown;
    }
}

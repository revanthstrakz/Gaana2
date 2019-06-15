package com.moengage.pushbase.fragments;

import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.ContextThemeWrapper;
import com.moengage.core.Logger;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.push.MoEPushWorker;
import com.moengage.pushbase.push.PushActionManager;
import java.util.Calendar;

public class LaterDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle bundle) {
        StringBuilder stringBuilder;
        String stringBuilder2;
        long timeInMillis;
        long timeInMillis2;
        long timeInMillis3;
        CharSequence[] charSequenceArr;
        long[] jArr;
        final Bundle arguments = getArguments();
        if (arguments == null) {
            dismiss();
            Logger.v("LaterDialogFragment$onCreateDialog : Extras is null");
        }
        int andRemoveSnoozeExtra = getAndRemoveSnoozeExtra(arguments, PushActionMapperConstants.ACTION_SNOOZE_TIME);
        int andRemoveSnoozeExtra2 = getAndRemoveSnoozeExtra(arguments, PushActionMapperConstants.ACTION_SNOOZE_TODAY);
        int andRemoveSnoozeExtra3 = getAndRemoveSnoozeExtra(arguments, PushActionMapperConstants.ACTION_SNOOZE_TOMORROW);
        arguments.putBoolean(PushActionMapperConstants.KEY_RENOTIFY, true);
        Intent intent = new Intent(getActivity(), MoEPushWorker.class);
        intent.setAction(MoEPushWorker.EXTRA_SERVICE_NOTIFY);
        intent.putExtras(arguments);
        final PendingIntent service = PendingIntent.getService(getActivity(), (int) System.currentTimeMillis(), intent, 134217728);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Remind in ");
        stringBuilder3.append(andRemoveSnoozeExtra);
        stringBuilder3.append(" hour");
        String stringBuilder4 = stringBuilder3.toString();
        int i = Calendar.getInstance().get(11) + andRemoveSnoozeExtra2;
        String str = "AM";
        if (andRemoveSnoozeExtra2 != -1 && i > 11) {
            str = "PM";
        }
        if (i > 12) {
            i -= 12;
        }
        int i2 = Calendar.getInstance().get(12);
        if (i2 < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Today (");
            stringBuilder.append(i);
            stringBuilder.append(":0");
            stringBuilder.append(i2);
            stringBuilder.append(str);
            stringBuilder.append(")");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Today (");
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(i2);
            stringBuilder.append(str);
            stringBuilder.append(")");
            stringBuilder2 = stringBuilder.toString();
        }
        str = "AM";
        if (andRemoveSnoozeExtra3 != -1 && andRemoveSnoozeExtra3 > 11) {
            str = "PM";
        }
        i2 = andRemoveSnoozeExtra3 > 12 ? andRemoveSnoozeExtra3 - 12 : andRemoveSnoozeExtra3;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Tomorrow (");
        stringBuilder.append(i2);
        stringBuilder.append(str);
        stringBuilder.append(")");
        str = stringBuilder.toString();
        if (andRemoveSnoozeExtra != -1) {
            Calendar instance = Calendar.getInstance();
            instance.add(11, andRemoveSnoozeExtra);
            timeInMillis = instance.getTimeInMillis();
        } else {
            timeInMillis = -1;
        }
        if (andRemoveSnoozeExtra2 != -1) {
            Calendar instance2 = Calendar.getInstance();
            instance2.add(12, andRemoveSnoozeExtra2 * 60);
            timeInMillis2 = instance2.getTimeInMillis();
        } else {
            timeInMillis2 = -1;
        }
        if (andRemoveSnoozeExtra3 != -1) {
            Calendar instance3 = Calendar.getInstance();
            instance3.add(5, 1);
            instance3.set(11, andRemoveSnoozeExtra3);
            instance3.set(12, 0);
            timeInMillis3 = instance3.getTimeInMillis();
        } else {
            timeInMillis3 = -1;
        }
        String str2 = "Pick a date and time";
        if (andRemoveSnoozeExtra == -1 || andRemoveSnoozeExtra2 == -1 || andRemoveSnoozeExtra3 == -1) {
            long[] jArr2;
            if (andRemoveSnoozeExtra == -1 && andRemoveSnoozeExtra2 != -1 && andRemoveSnoozeExtra3 != -1) {
                charSequenceArr = new CharSequence[]{stringBuilder2, str, str2};
                jArr2 = new long[]{timeInMillis2, timeInMillis3, -1};
            } else if (andRemoveSnoozeExtra != -1 && andRemoveSnoozeExtra2 == -1 && andRemoveSnoozeExtra3 != -1) {
                charSequenceArr = new CharSequence[]{stringBuilder4, str, str2};
                jArr2 = new long[]{timeInMillis, timeInMillis3, -1};
            } else if (andRemoveSnoozeExtra != -1 && andRemoveSnoozeExtra2 != -1 && andRemoveSnoozeExtra3 == -1) {
                charSequenceArr = new CharSequence[]{stringBuilder4, stringBuilder2, str2};
                jArr2 = new long[]{timeInMillis, timeInMillis2, -1};
            } else if (andRemoveSnoozeExtra != -1 && andRemoveSnoozeExtra2 == -1 && andRemoveSnoozeExtra3 == -1) {
                charSequenceArr = new CharSequence[]{stringBuilder4, str2};
                jArr = new long[]{timeInMillis, -1};
            } else if (andRemoveSnoozeExtra == -1 && andRemoveSnoozeExtra2 != -1 && andRemoveSnoozeExtra3 == -1) {
                charSequenceArr = new CharSequence[]{stringBuilder2, str2};
                jArr = new long[]{timeInMillis2, -1};
            } else if (andRemoveSnoozeExtra == -1 && andRemoveSnoozeExtra2 == -1 && andRemoveSnoozeExtra3 != -1) {
                charSequenceArr = new CharSequence[]{str, str2};
                jArr = new long[]{timeInMillis3, -1};
            } else {
                Calendar instance4 = Calendar.getInstance();
                instance4.add(11, 1);
                long timeInMillis4 = instance4.getTimeInMillis();
                jArr = new long[]{timeInMillis4, -1};
                charSequenceArr = new CharSequence[]{"Remind in an hour", str2};
            }
            jArr = jArr2;
        } else {
            charSequenceArr = new CharSequence[]{stringBuilder4, stringBuilder2, str, str2};
            jArr = new long[]{timeInMillis, timeInMillis2, timeInMillis3, -1};
        }
        Builder builder = new Builder(new ContextThemeWrapper(getActivity(), 16973939));
        builder.setTitle("Later").setItems(charSequenceArr, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                long j = jArr[i];
                if (j != -1) {
                    FragmentActivity activity = LaterDialogFragment.this.getActivity();
                    LaterDialogFragment.this.getActivity();
                    ((AlarmManager) activity.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, j, service);
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(j);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("LaterDialogFragment : Reminder set at :");
                    stringBuilder.append(instance.getTime());
                    Logger.v(stringBuilder.toString());
                    return;
                }
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setArguments(arguments);
                datePickerFragment.show(LaterDialogFragment.this.getActivity().getSupportFragmentManager(), "datePicker");
            }
        });
        return builder.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!DatePickerFragment.isDateDialogShown()) {
            if (getActivity() != null) {
                getActivity().finish();
            }
            Logger.v("LaterDialogFragment$onDismiss : PushTracker:Completed");
        }
        PushActionManager.dialogShown = false;
    }

    private int getAndRemoveSnoozeExtra(Bundle bundle, String str) {
        int i = bundle.getInt(str);
        bundle.remove(str);
        return i;
    }
}

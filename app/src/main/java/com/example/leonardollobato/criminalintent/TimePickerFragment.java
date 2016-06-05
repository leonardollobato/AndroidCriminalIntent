package com.example.leonardollobato.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by leonardollobato on 6/5/16.
 */
public class TimePickerFragment extends DialogFragment {
    private static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.leonardolobato.criminalintent.time";
    private TimePicker mTimePicker;

    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null)
                return;

        Intent i = new Intent();
        i.putExtra(EXTRA_TIME, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,i);
    }

    public static TimePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment tf = new TimePickerFragment();
        tf.setArguments(args);

        return tf;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date time = (Date) getArguments().getSerializable(ARG_TIME);

        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minutes);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Time of Crime")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = mTimePicker.getCurrentHour();
                        int minutes = mTimePicker.getCurrentMinute();

                        Date date = new GregorianCalendar(0,0,0, hour, minutes).getTime();
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }
}

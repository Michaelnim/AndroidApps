package android.bignerdranch.criminalintent;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;

    private void sendResult(int resultCode, int hour, int min) {
        if(getTargetFragment() == null)
            return;

        Intent intent = new Intent();
        intent.putExtra("Hour", hour);
        intent.putExtra("Min", min);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), resultCode, intent);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker)v.findViewById(R.id.dialog_time_picker);


        return new AlertDialog.Builder(getActivity())
                .setView(v)

                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = mTimePicker.getCurrentHour();
                                int min = mTimePicker.getCurrentMinute();

                                /*Fragment fragment = new Fragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("Hour", hour);
                                bundle.putInt("Min", min);
                                fragment.setArguments(bundle);*/
                                sendResult(Activity.RESULT_OK, hour,min);
                            }
                        })
                .create();
    }

}

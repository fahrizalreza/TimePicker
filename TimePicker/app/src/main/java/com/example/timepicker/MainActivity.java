package com.example.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Calendar currentTime;
    int hour,minute;
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) this.<View>findViewById(R.id.timeTextView);

        currentTime = Calendar.getInstance();

        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);

        selectTimeFormat(hour);

        tv.setText(hour + " : " + minute + " " + format);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        selectTimeFormat(hourOfDay);
                        tv.setText(hourOfDay + " : " + minute + " " + format);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    public void selectTimeFormat(int hour){
        if(hour == 0){
            hour += 12;
            format = "AM";
        } else if(hour == 12){
            format = "PM";
        } else if(hour > 12){
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
    }
}

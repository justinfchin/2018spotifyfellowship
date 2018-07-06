// for adding new events

package com.justinfchin.calendarapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.justinfchin.calendarapp.R;
import com.justinfchin.calendarapp.retrofit.ApiService;
import com.justinfchin.calendarapp.retrofit.EventItem;
import com.justinfchin.calendarapp.retrofit.RetrofitNetwork;

import java.time.LocalTime;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add extends Activity{

    ApiService apiService = RetrofitNetwork.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        // Updated Title Header
        TextView tvDayDate = findViewById(R.id.dayDate);
        tvDayDate.setText(getIntent().getExtras().getString("DAY_DATE"));

        // Cancel Button
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get Current Time to show in Time Buttons
        LocalTime now = LocalTime.now();
        String hhmm = String.format(Locale.US,"%02d",now.getHour())+":"+
                String.format(Locale.US,"%02d",now.getMinute());

        // Start Time Button
        final Button startTimeButton = findViewById(R.id.startTimeButton);
        startTimeButton.setText(hhmm);
        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(startTimeButton);
            }
        });

        // End Time Button
        final Button endTimeButton = findViewById(R.id.endTimeButton);
        endTimeButton.setText(hhmm);
        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(endTimeButton);
            }
        });

        // Submit Button
        final Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find views
                EditText etTitle = findViewById(R.id.addTitle);
                TextView tvDescription = findViewById(R.id.description);
                // get inputs
                String title = etTitle.getText().toString();
                String date = getIntent().getExtras().getString("DATE");
                String startTime = startTimeButton.getText().toString();
                String endTime = endTimeButton.getText().toString();
                String description = tvDescription.getText().toString();
                // create event
                submitClick(new EventItem(title,date,startTime,endTime,description));
            }
        });
    }

    /** Creates TimePickerDialog when Button Clicked
     *
     * @param b a Button
     */
    public void timePicker(final Button b){

        AlertDialog timeDialog = new TimePickerDialog(Add.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str = String.format(Locale.US,"%02d",hourOfDay)+
                        ":"+ String.format(Locale.US,"%02d",minute);

                b.setText(str);
            }
        },LocalTime.now().getHour(),LocalTime.now().getMinute(), true);

        timeDialog.show();
    }

    /** Submit Clicking
     *
     * @param eventItem to be created
     */
    void submitClick(EventItem eventItem){
        ApiService apiService = RetrofitNetwork.getService();
        apiService.postEvent(eventItem).enqueue(new Callback<EventItem>() {
            @Override
            public void onResponse(Call<EventItem> call, Response<EventItem> response) {
                Toast.makeText(Add.this,"Event Successfully Added...",Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Call<EventItem> call, Throwable t) {
                Toast.makeText(Add.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }


}

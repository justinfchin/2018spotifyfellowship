package com.justinfchin.calendarapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.justinfchin.calendarapp.R;
import com.justinfchin.calendarapp.retrofit.ApiService;
import com.justinfchin.calendarapp.retrofit.EventItem;
import com.justinfchin.calendarapp.retrofit.RetrofitNetwork;

import java.time.LocalDate;



public class MainActivity extends AppCompatActivity {


    LocalDate today = LocalDate.now();
    int currentYear = today.getYear();
    int currentMonth = today.getMonthValue();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update Month and Year with Current
        updateMonthYear();
        createDays();


    }

    /** Updates the Month and Year of the Header
     *
     */
    void updateMonthYear(){

        // get Month and Year
        String month = String.valueOf(today.getMonth());
        String year = String.valueOf(today.getYear());
        String monthYear = month+" "+year;

        // update the view
        TextView tv = findViewById(R.id.monthyear);
        tv.setText(monthYear);



    }

    /** Create the days of the week
     *
     */
    void createDays() {

        // create an array of weeks
        int numWeeks = 6;
        LinearLayout[] weeks = new LinearLayout[numWeeks];

        // populate weeks
        weeks[0] = findViewById(R.id.wk1);
        weeks[1] = findViewById(R.id.wk2);
        weeks[2] = findViewById(R.id.wk3);
        weeks[3] = findViewById(R.id.wk4);
        weeks[4] = findViewById(R.id.wk5);
        weeks[5] = findViewById(R.id.wk6);

        // create array of buttons (7 days a wk for 6 wks)
        Button[] days = new Button[7 * numWeeks];

        // Create Layout Parameter
        LinearLayout.LayoutParams daysParameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        daysParameters.weight = 1;

        // add day buttons
        int index = 0; // for storing days

        for (int week = 0; week < numWeeks; week++) {
            for (int day = 0; day < 7; day++) {
                days[index] = new Button(MainActivity.this);
                days[index].setLayoutParams(daysParameters);

                // add button to the week
                weeks[week].addView(days[index]);
                index++;

            }
        }

        // get first day of the month
        int firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1)
                .getDayOfWeek().getValue() % 7;


        boolean flag = false;
        int dayNumber = 1;
        for (int i = firstDay; i < today.lengthOfMonth() + firstDay; i++) {
            days[i].setText(String.valueOf(dayNumber));
            days[i].setTag(R.id.tagDayDate, LocalDate.of(currentYear, currentMonth, dayNumber).getDayOfWeek() + " " + dayNumber);
            days[i].setTag(R.id.tagDate, LocalDate.of(currentYear, currentMonth, dayNumber));
            // add click
            days[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDayClick(v);
                }
            });
            dayNumber++;
        }
    }

    /**
     *
     * @param v View
     */
    public void onDayClick(View v){
        Intent intent = new Intent(this, PopUp.class);

        // pass day of week and date
        intent.putExtra("DAY_DATE",v.getTag(R.id.tagDayDate).toString());
        intent.putExtra("DATE",v.getTag(R.id.tagDate).toString());
        startActivity(intent);

    }

}

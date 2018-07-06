package com.justinfchin.calendarapp.activity;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.justinfchin.calendarapp.R;
import com.justinfchin.calendarapp.retrofit.ApiService;
import com.justinfchin.calendarapp.retrofit.EventItem;
import com.justinfchin.calendarapp.retrofit.RetrofitNetwork;


import java.time.LocalDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    // July hardcoded
    LocalDate today = LocalDate.of(2018, 8,1);
    int currentYear = today.getYear();
    int currentMonth = today.getMonthValue();
    int numWeeks = 6;
    // get weekday of first day
    int firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1)
            .getDayOfWeek().getValue() % 7;

    View[] days = new View[numWeeks * 7];
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Update Month and Year with Current
        updateMonthYear();
        createDays();
        getEvents();

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
        LinearLayout[] weeks = new LinearLayout[numWeeks];

        // populate weeks
        weeks[0] = findViewById(R.id.wk1);
        weeks[1] = findViewById(R.id.wk2);
        weeks[2] = findViewById(R.id.wk3);
        weeks[3] = findViewById(R.id.wk4);
        weeks[4] = findViewById(R.id.wk5);
        weeks[5] = findViewById(R.id.wk6);

        // add day buttons

        int index = 0; // for storing days

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (int week = 0; week < numWeeks; week++) {
            for (int day = 0; day < 7; day++) {
                days[index] = layoutInflater.inflate(R.layout.day_layout, weeks[week],false);
                weeks[week].addView(days[index]);
                index++;
            }
        }


        int dayNumber = 1;
        for (int i = firstDay; i < today.lengthOfMonth() + firstDay; i++) {
            days[i].setVisibility(View.VISIBLE);
            days[i].setClickable(true);
            TextView tvDay = days[i].findViewById(R.id.dayNumber);

            tvDay.setText(String.valueOf(dayNumber));


            days[i].setTag(R.id.tagDayDate, LocalDate.of(currentYear, currentMonth, dayNumber).getDayOfWeek() + " " + dayNumber);
            days[i].setTag(R.id.tagDate, LocalDate.of(currentYear, currentMonth, dayNumber));

            dayNumber++;
        }
    }

    /** Day Click Button
     *
     */
    public void onDayClick(View v){
        Intent intent = new Intent(this, PopUp.class);
        date = v.getTag(R.id.tagDate).toString();
        // pass day of week and date
        intent.putExtra("DAY_DATE",v.getTag(R.id.tagDayDate).toString());
        intent.putExtra("DATE",date);
        startActivityForResult(intent,0);

    }

    /** For startActivityForResult
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 0)
            if (resultCode == Activity.RESULT_OK){
                updateEvents();
            }
    }

    /** Updated the Event Counts by 1 after Adding
     *
     */
    void updateEvents(){
        TextView tvCount = days[Integer.valueOf(date.substring(date.length()-2))+firstDay-1].findViewById(R.id.eventCount);
        tvCount.setTag(Integer.valueOf(tvCount.getTag().toString())+1);
        String str = "+"+tvCount.getTag().toString();
        tvCount.setText(str);
    }

    /** Get Events and Publish Count
     *
     */

    public void getEvents(){
        ApiService apiService = RetrofitNetwork.getService();
        apiService.getAllEvents().enqueue(new Callback<List<EventItem>>() {
            @Override
            public void onResponse(Call<List<EventItem>> call, Response<List<EventItem>> response) {



                for(EventItem eventItem : response.body()){
                    String day = eventItem.getEventDate();

                    View dayView = days[Integer.valueOf(day.substring(day.length()-2))+firstDay-1];

                    TextView tvCount = dayView.findViewById(R.id.eventCount);

                    tvCount.setTag(Integer.valueOf(tvCount.getTag().toString())+1);

                }

                for (View day : days){
                    TextView temp = day.findViewById(R.id.eventCount);
                    String str = "+"+temp.getTag().toString();
                    temp.setText(str);


                }


            }

            @Override
            public void onFailure(Call<List<EventItem>> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }


}

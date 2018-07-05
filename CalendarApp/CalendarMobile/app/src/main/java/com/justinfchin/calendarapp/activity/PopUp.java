package com.justinfchin.calendarapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.justinfchin.calendarapp.R;
import com.justinfchin.calendarapp.RVAdapter;
import com.justinfchin.calendarapp.retrofit.ApiService;
import com.justinfchin.calendarapp.retrofit.EventItem;
import com.justinfchin.calendarapp.retrofit.RetrofitNetwork;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopUp extends Activity{

    RecyclerView recyclerView;
    List<EventItem> temp = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);

        scaleWindow();

        // Update the TextView Header
        TextView dayDate = findViewById(R.id.dayDate);
        dayDate.setText(getIntent().getExtras().getString("DAY_DATE"));

        // Populate the Items from Server
        // Retrofit
        ApiService apiService = RetrofitNetwork.getService();

        // RecyclerView


        recyclerView = findViewById(R.id.eventsList);
        //for performance®
        recyclerView.setHasFixedSize(true);
        //layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adapter
        recyclerView.setAdapter(new RVAdapter(temp));


        // Get All Events
        apiService.getAllEvents().enqueue(new Callback<List<EventItem>>() {
            @Override
            public void onResponse(Call<List<EventItem>> call, Response<List<EventItem>> response) {

                Log.d("test",response.body().toString());
                for (EventItem eventItem : response.body()){
                    if (eventItem.getEventDate().compareTo(getIntent().getExtras().getString("DATE")) == 0){
                        temp.add(eventItem);
                    }
                }

                //Adapter
                recyclerView.setAdapter(new RVAdapter(temp));


            }

            @Override
            public void onFailure(Call<List<EventItem>> call, Throwable t) {

                Log.d("Error",t.getMessage());
            }
        });


        // FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                fabClick(v);
            }
        });

    }

    /** Handle Window Size
     *
     */
    void scaleWindow(){
        // scale the popup size down
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.7));
    }


    /** Action for FAB
     *
     */
    public void fabClick(View v) {
        Intent intent = new Intent(this, Add.class);

        intent.putExtra("DAY_DATE",getIntent().getExtras().getString("DAY_DATE").toString());
        intent.putExtra("DATE",getIntent().getExtras().getString("DATE").toString());
        startActivity(intent);
        finish();

    }


}

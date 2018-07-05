// Interface between Java/Http-Json

package com.justinfchin.calendarapp.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/events")
    Call<List<EventItem>> getAllEvents();

    @GET("/events")
    Call<List<EventItem>> getEvent(@Query("event_date") String event_date);

    @POST("/events")
    Call<EventItem> postEvent(@Body EventItem eventItem);

    @PUT("/events/:id")
    Call<List<EventItem>> putEvent(@Path("id") String id, @Body EventItem eventItem);

    @DELETE("/events/:id")
    Call<EventItem> deleteEvent(@Path("id") String id);


}

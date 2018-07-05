// DataModel - POJO

package com.justinfchin.calendarapp.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventItem {


    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("eventTitle")
    private String eventTitle;

    @Expose
    @SerializedName("eventDate")
    private String eventDate;

    @Expose
    @SerializedName("startTime")
    private String startTime;

    @Expose
    @SerializedName("endTime")
    private String endTime;

    @Expose
    @SerializedName("eventDescription")
    private String eventDescription;

    public EventItem(){
        this.eventTitle = "";
        this.eventDate = "";
        this.startTime = "";
        this.endTime = "";
        this.eventDescription= "";
    }

    public EventItem(String eventTitle, String eventDate, String startTime, String endTime, String eventDescription){
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDescription = eventDescription;

    }



    /* ============================
        SETTERS & GETTERS
       ============================
     */

    //ID
    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}

    //EventTitle
    public String getEventTitle(){return this.eventTitle;}
    public void setEventTitle(String eventTitle){this.eventTitle = eventTitle;}

    //EventDate
    public String getEventDate(){return this.eventDate;}
    public void setEventDate(String eventDate){this.eventDate = eventDate;}

    //StartTime
    public String getStartTime(){return this.startTime;}
    public void setStartTime(String startTime){this.startTime = startTime;}

    //EndTime
    public String getEndTime(){return this.endTime;}
    public void setEndTime(String endTime){this.endTime = endTime;}

    //EventDescription
    public String getEventDescription(){return this.eventDescription;}
    public void setEventDescription(String eventDescription){this.eventDescription = eventDescription;}


}

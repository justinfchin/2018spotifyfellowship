# CalendarApp
- A calendar android app 
    - make sure to have json server running first

### Contents
1. [Media Showing Usage](#pics)
2. [CalendarMobile](#cm)
3. [CalendarBackEnd](#cb)

<a name = "pics"/>

## Media Showing Usage
- Home View > After Clicking a Day > After Clicking an Event
<p>
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/main.png" width="250" height="400">
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/day.png" width="250" height="400">
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/event.png" width="250" height="400">
</p>
- After Clicking the FloatingActionButton > After Adding a Day > GIF
<p>
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/fab.png" width="250" height="400">
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/post.png" width="250" height="400">
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/overview.gif" width="250" height="400">
</p>

<a name = "cm" />

## [CalendarMobile](https://github.com/justinfchin/2018spotifyfellowship/tree/master/CalendarApp/CalendarMobile)


- Additional Specs:
    - Too many events
        - Fixed with a counter of events on main and allowed user to scroll through the events after clicking on the day to expand
    - Correct Date on Correct Days
        - Found the first day that the month starts and labeled the dates starting there
- Additional Notes:
    - included 6 rows instead of 5 rows since months like December 2018 will require 6 weeks 

<a name = "cb" />

## [CalendarBackend](https://github.com/justinfchin/2018spotifyfellowship/tree/master/CalendarApp/CalendarBackend)

- I used a [JSON server](https://github.com/typicode/json-server) for the backend
- To run server, in terminal:
    - `json-server --watch db.json`
- To access server, in browser:
    - `http://localhost:3000`
- Example JSON data
<img src="https://github.com/justinfchin/2018spotifyfellowship/blob/master/CalendarApp/images/json.png" width="300" height="300">

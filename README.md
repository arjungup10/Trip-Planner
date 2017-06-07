**Trip Planner** <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/app/src/main/res/mipmap-xhdpi/ic_launcher.png" height="64">
================

**Introduction**
----------------
As an exploration into Android development and software processes, this project aims to help a group plan a trip together. A user can specify the events they want to complete in a day, and how long they want to spend doing each event, and the program will create an optimized itinerary for them. It will use the google maps API to account for travel time and notify a user when they should leave for the next event. In addition, the google maps API will also suggest events in an area to help plan a trip. The project will also allow users to set a budget for each trip, and enter in payments to allow for easy division of money by all individuals on the trip.

**Implemented Features**
------------------------
* Allow users to create events based on location and time
* Create an optimal itinerary based on events the user wants to do
* Allow users to set a budget and keep track of payments
* Evenly divides costs between individuals of a group

**Planned Features**
--------------------
* Notify users when to leave for their next event
* Assist users in finding things to do in an area

**System Architecture**
-----------------------
The program currently saves data only internally to the user's device. It communicates with Google Maps API in order to receive time estimates and the optimized itinerary. It leverages the Google Places API in order to assist users with selecting locations for events. 

*Design Patterns*
* MVP: Separation of responsibilities of each part of the system
* Singleton: Singleton allows easy access of information throughout the program, additionally allowing for easy reading/writing
* Adapter: Communicate information between views and models


*Deployment Diagram*

![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/SoftwareArchDiagram.png?raw=true)


*Class Diagram*

![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/ClassDiagram.png?raw=true)


Screen Captures
---------------

*Displays all created trips*

<img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/TripScreen.png?raw=true" width="480">


*Event screens and creation screens*

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/eventsScreen.png?raw=true" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/eventLocation.png?raw=true" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createEvent.png?raw=true" width="480"> |
|:---:|:---:|:---:|


*Example itinerary*

<img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/itinerary.png?raw=true" width="480">


*People screens and creation screens*

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/peopleScreen.png?raw=true" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createPerson.png?raw=true" width="480"> |
|:---:|:---:|


*Payment and repayments screens

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/Payments.png?raw=true" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createPayment.png?raw=true" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/Repayments.png?raw=true" width="480"> |
|:---:|:---:|:---:|









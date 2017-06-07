**Trip Planner**
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
The system will save data internally to the user's device. The application will communicate with Google Maps API in order to receive time estimates. As location, will be necessary for the functionality of the application, it will leverage Google Maps and Google Play Services in order to properly notify the user.

*Deployment diagram of the program. As the program develops this is likely to change.*
![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/SoftwareArchDiagram.png)

*Class diagram of the project. Similar to the deployment diagram, this is subject to change*
![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/ClassDiagram.png)

Screen Captures
---------------
<img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/TripScreen.png?raw=true" width="480">

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/eventsScreen.png" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/eventLocation.png" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createEvent.png" width="480"> |
|:---:|:---:|:---:|

<img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/itinerary.png" width="480">

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/peopleScreen.png" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createPerson.png" width="480"> |
|:---:|:---:|

| <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/Payments.png" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/createPayment.png" width="480"> | <img src="https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/Repayments.png" width="480"> |
|:---:|:---:|:---:|









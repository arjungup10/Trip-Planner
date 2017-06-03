Trip Planner
===========

Introduction
------------
As an exploration into Android development and software processes, this project aims to help a group plan a trip together. A user can specify the events they want to complete in a day, and how long they want to spend doing them, and the program will create an itinerary for them. It will use the google maps API to account for travel time and notify a user when they should leave for the next event. Using the google maps API, it will also suggest events in an area to help plan a trip. The project will also allow users to set a budget, and enter in payments to allow for easy division of money by all individuals on the trip.

Planned Features
----------------
* Allow users to create events based on location and time
* Create an optimal itinerary based on events the user wants to do
* Notify users when to leave for their next event
* Assist users in finding things to do in an area
* Allow users to set a budget and keep track of payments
* Assist users in evenly dividing costs between individuals of a group 
* Allow users to share the itinerary with others


Basic Initial System Architecture
-----------------------------------
The system will save data internally to the user's device. In the future, database integration seems likely. the application will communicate with Google Maps API in order to receive time estimates. As location, will be necessary for the functionality of the application, it will leverage Google Maps and Google Play Services in order to properly notify the user.

Below is a basic system architecture diagram of the program. As the program develops this is very likely to change.
![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/Software%20Arch%20Diagram.png)

Below is an initial class diagram of the project. Similar to the architecture diagram, this is subject to change as the project changes and evolves. 
![alt text](https://github.com/cpe305Spring17/spring2017-project-arjungup10/blob/master/ReadMeFiles/TripPlanner%20Class%20Diagram.png)


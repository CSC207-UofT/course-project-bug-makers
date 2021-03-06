# Scenario Walk-through

Consider a scenario when an existing user wants to add a single course to a new timetable:
![](walkthrough.assets/myCourseApp.png)

 ### Login & Query Course Summary 
From the CMD line interface, the user will first log in to their account using their username and password. This prompts the `UserServiceController` to take this information to the `UserRequestProcessor`, which will locate the user’s account by
using the `UserDAO` to access all the user accounts stored in the MongoDB `bugMakerProject.userSheet`.
Once logged in, the user can search for the course they want to add to their timetable by the course code, for example,
“CSC207F”. The `CourseServiceController` will process the course code, then call on the `CourseInformationGenerator`,
which will get the course description by using the `CourseDAO` to access the `UofT API`.

###### Figure 1: Login
![](walkthrough.assets/sw1.gif)

###### Figure 2: Query Course Summary
![](walkthrough.assets/sw2.gif)

 ### Course Planning and Presenting Calendar
To add a specific section to a user’s course list, the `CourseServiceController` will process the section course code,
then call on the `CoursePlanner` to create a new schedule. Once the `CoursePlanner` creates a schedule with the help from `ScheduleUpdater`. Then the `CourseServiceController` will call on the `UserRequestProcessor` to locate the user’s account and store the new `Schedule`
in the user’s schedule list, subsequently, `UserDAO` will sync with the user database for this update. When a user wants to create a timetable, the `CalendarPresenter` will process this action by using the
`CalendarFactory` to generate a calendar. To do this, the `CalendarPresenter` will take in user's latest `Schedule` map with specific time slot(s) for each section. It will pass the user `Schedule` map into the
`CalendarFactory`, which will create a timetable for the user. Note that `CalendarFactory` offers a variety of calendar types and customizations. 

###### Figure 3: Course Planning 
![](walkthrough.assets/sw3.gif)

###### Figure 4: Present Calendar 
![](walkthrough.assets/SW4.gif)

###### Note: Submitting Reviews [Reserved for Phase1/2]






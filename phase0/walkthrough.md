# Scenario Walk-through

Consider a scenario when an existing user wants to add a single course to a new timetable:

From the GUI, the user will first login to their account using their username and password. This prompts the `User 
Service Controller` to take this information to the `User Request Processor`, which will locate the user’s account by 
using the `User DAO` to access all the user accounts stored in the MongoDB `User Database`. 

Once logged in, the user can search for the course they want to add to their timetable by the course code, for example,
“CSC207F”. The `Course Service Controller` will process the course code, then call on the `Course Information Generator`, 
which will get the course description by using the `Course DAO` to access the `UofT API`.

To add a specific section to a user’s course list, the `Course Service Controller` will process the section course code,
then call on the `Course Planner` to create a new schedule. Once the `Course Planner` creates a schedule, the `Course 
Service Controller` will call on the `User Request Processor` to locate the user’s account and store the new schedule 
in the user’s schedule list. 

When a user wants to create a timetable, the `Calendar Presenter` will process this action by using the 
`Calendar Factory`to generate a calendar. To do this, the `Calendar Presenter` will first locate the user’s schedule 
list using the `User Request Processor`. Once it has the user’s course list, it will use the 
`Course Information Generator`to get the schedule map of the section, which has information about the specific time 
slot(s) of each schedule. Once it has this information, it will pass the user account and section times into the 
`Calendar Factory`, which will create a timetable for the user. 







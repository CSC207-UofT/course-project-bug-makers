# Progress Report

![](progress_report.assets/myCourseApp.jpg)

![](progress_report.assets/CourseAppMindMap.png)

## Goal for `Phase0` 

- [x] User Service: 
  - [x] Instantiate a remote MongoDB for user information storage
  - [x] Connect the database
  - [x] Perform user register and login
  - [x] Password encryption
  - [x] Store user information
  - [x] Course List modification
- [x] Course Service: 
  - [x] Querying and presenting course information from UofT
    - [x] Course sections, section schedules
    - [x] Course description, course perquisite
    - [x] Comprehensive course summary
  - [x] Course planning
    - [x] Manually plan course
- [x] Calendar Service: 
  - [x] Present a work-day Calendar
- [x] CMD Driver:
  - [x] Implement it
  

## Table of Contents
1. [Specification Summary](#Specification-Summary)
2. [CRC Model](#CRC-Model)
3. [Scenario Walk Through](#Scenario-Walk-Through)
4. [Skeleton Program](#Skeleton-Program-&-Successful-Designs)
6. [Open Questions](#Open-Questions)
7. [Next Steps](#Next-Steps)


## Specification Summary
For our CSC207 project, we are planning to build a course planning app that allows students to plan and review their courses. A build-in connection to UofT API will present comprehensive course information to users, while user information is safely stored in a remote database. Along with an auto-scheduling algorithm that can help with course planning, a timetable visualization function gives a convenient course scheduling experience. Based on student reviews and user information, our app can also recommend specific courses tailored to the user’s program requirements. Alongside the course planning function, our app aims to provide a platform for students to connect and network with their fellow peers.

Click [here](progress_report.md) for details.

## CRC Model
- We established a clean structure with 3 controllers, 7 use cases and 3 entities with 1 CMD line driver for `Phase 0`.
  - 3 Controllers refer to our three main businesses, which are User Service, Calendar Service and Course Service.
    - User Service (`UserServiceController`): Responsible for user requests processing, e.g. register, login, retrieve user information.
    - Calendar Service (`CalendarPresenter`): Generate a timetable reflecting our current course schedule.
    - Course Service (`CourseServiceController`): Give course information. Help to schedule our course timetable.
  - 7 Use Cases are implemented to assist controllers.
    - User Service: `UserRequest`, with `UserDAO`, helps to interact Database to query/modify user data.
    - Calendar Service: `Calendar Factory` adopts the *Factory Design Pattern* to produce readable timetable representation.
    - Course Service: `CourseInformationGenerater`, with `CourseDAO`, helps to interact with UofT Art&Sci API to query the course information. `CoursePlanner` and `ScheduleUpdater` work together to provide auto-scheduling service based on course list.
  - 3 entities are implemented to organize information.
    - `User` will help to organize the user information, including course list and wish list. `Course` will help to organize the course information. `Schedule` will help to organize the schedule information. 
- We applied interface-oriented principle, where we segregate the dependencies by interfaces.
- Here is our software architecture diagram (for implemented part), showing the layer segregation:
![](progress_report.assets/CD.jpg)
- Here is our class dependency overview (for implemented part).
![](progress_report.assets/main.png)

Click [here](crcCards/crcWalkThrough.md) for details.

## Scenario Walk Through
![](progress_report.assets/myCourseApp.png)
- User will register/login an account, and then query course information. User can add their desired courses to course/wish list. After our algorithm generating a course schedule for the user, user can get a variety of timetables to help with user's time management. Note that, in the future, user will be able to access a forum to write reviews/rate courses.

Click [here](walkthrough.md) for details.


## Skeleton Program & Successful Designs
![](progress_report.assets/CourseAppMindMap.png)
- We have `7.7k` lines implemented/optimized in our `Phase 0` project in total.
- [Kuan Pang & Michael Fang] We have successfully constructed a remote MongoDB to manage our user information, alongside password encryption and stale connection to the User Database.
- [Li Quan Soh & Sherry You] We have constructed a comprehensive CMD line interface, well adopting our backend services. We have provided over 20 commands for our users (Check `teamDocumentation` for details).
- [Kevin Cheng & Kuan Pang] We have connected our application with the UofT API for course information query, as well as a complete auto-scheduling algorithm (algorithm will be updated in `Phase 1/2`, preview is in our branch).
- [Jiaming Weng & Sherry You] We have adopted the *Factory Design Pattern* in our Calendar Service, where our structure supports a variety of timetable choices and customizations.
- We have followed clean structure and solid principle thorough out our `Phase 0` stage. Especially we have segregated the dependency by interfaces.
- All of our teammates have participated in the structure design and programming. Sherry You contributed a lot to our team documentations. 


## Open Questions
- After profiling our app performance, we found that the Database response is too time-consuming. We are looking for solutions to improve our Database response speed.
- We are looking for more efficient algorithm for course planning(scheduling).
- In case we want to build a forum in our app for course review, we may need extra support on data-synthesize.

## Next Steps
- Discuss the course review service's feasibility, as well as, the implementation of course recommendation system;
- Optimize our application's performance, e.g. DB response time;
- Implement more timetable choices, including Day timetable, Day to-do list, etc.;
- Provide more course information for users to help them with their course planning decisions;
- Implement GUI, and port our application to Android platform.



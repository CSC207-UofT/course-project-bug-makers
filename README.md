## myCourseApp - Course Planning App for UofT Students

![myCourseApp](README.assets/myCourseApp.jpg)

> Note that this project requires `JDK16` . Dependences can be found in  `build.gradle ` . Please refer to `teamDocumentation` for details.

## What is myCourseApp?

![](README.assets/myCourseApp.png)
For our CSC207 project, we are planning to build a course planning app that allows students to plan and review their courses. A build-in connection to UofT API will present comprehensive course information to users, while user information is safely stored in a remote database. Along with an auto-scheduling algorithm that can help with course planning, a timetable visualization function gives a convenient course scheduling experience. Based on student reviews and user information, our app can also recommend specific courses tailored to the user’s program requirements. Alongside the course planning function, our app aims to provide a platform for students to connect and network with their fellow peers. 



## Progress Summary & Design Highlights
![Course_app](README.assets/CourseAppMindMap.png)

## System Requirements

- *JDK16*
- Dependency
  - `mongodb-driver:3.12.10` : User Database Access
  - `gson:2.8.8` : JSON Support



## Sample Usage

We have a test account for you!
1. Run `Main.java`. Try to log in our test account!
   - Username: `ReadMeDemo`;
   - Password: `1234567890`.
2. Explore `CSC207F`'s summary!
```
$ getCourseGeneralInformation CSC207F
```
3. Check our intelligent pre-set course list (and then *Star* our Project)!
```
$ getCourseList
``` 
4. Try our auto-generated calendar and take a screenshot (share it to your Ins story)! 
```
$ presentCalendar F Workday
```

Here's our little GIF instruction:
![](README.assets/readmedemo.gif)

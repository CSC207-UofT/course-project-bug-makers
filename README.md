## myCourseApp - Course Planning Platform for UofT Students

![myCourseApp](README.assets/p1demo.jpeg)

> Note that this project requires `JDK1.8` and internet connection for could database operation and machine learning model inference. Dependencies can be found in  `build.gradle` . Please refer to `teamDocumentation` for details.

## What is myCourseApp?

![](README.assets/myCourseApp.png)
For our CSC207 project, we are planning to build a course planning app that allows students to plan and review their courses. A build-in connection to UofT API will present comprehensive course information to users, while user information is safely stored in a remote database. Course planning functionality is provided through our auto-scheduling algorithm. Timetable visualization functionality gives user a convenient course scheduling experience. Students can rate and write reviews on courses they have taken before. Based on these reviews and the user's information, our machine learning model strives to recommend instructor tailored to the user’s request and program requirements. In addition, our app aims to provide a platform for students to connect and network with their fellow peers.



## Progress Summary & Design Highlights
![Course_app](README.assets/myCourseAppIntrop1.png)

## System Requirements

- *JDK1.8*
- Dependencies
  - `mongodb-driver:3.12.10` : User Database Access
  - `gson:2.8.8` : JSON Support
  - `junit-jupiter-api:5.8.1'`: JUNIT test
  - `httpcore:4.4.14'` & `httpclient:4.5.13`: Apache HTTP request support



## Sample Usage for our CMD shell

We have a test account for you!
1. Run `Main.java` (in `main` branch ). Try to log in our test account!
   - Username: `ReadMeDemo`;
   - Password: `1234567890`.
2. Explore `CSC207F`'s summary!
```
$ getCourseGeneralInformation CSC207F
```
3. Check our excellent pre-set course list (and then *Star* our Project)!
```
$ getCourseList
``` 
4. Try our auto-generated calendar and take a screenshot (share it to your Ins story)! 
```
$ presentCalendar F Workday
```
5. Click [here](teamDocumentation/cmdDocumentation.md) for more interesting commands (make sure you have `JDK 1.8`)!

Here's our little GIF instruction:
![](README.assets/readmedemo.gif)

## Sample Usage for our web interface (Preview)

> Please note that this is a preview.

We also have a test account for you in our web interface! 

We have a test account for you!
1. Run `SpringbootApp.java` (in `dev/springboot` branch ). Try to log in our test account!
    - Username: `ReadMeDemo`;
    - Password: `1234567890`.
2. Explore `CSC207F`'s summary!

3. Check our excellent pre-set course list (and then *Star* our Project)!

4. Try our auto-generated calendar and take a screenshot!

![](README.assets/webdemo.gif)
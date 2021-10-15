# userService Documentation

## Design Goal
- Provide connection with the user database
- Perform login/register actions
- Query user information, including user course list, wish list, schedule list and so on

## Overall Structure
- The overall structure can be divided into three parts, each is segregated with abstraction (by interface-orientated programming.)
- Omitting the interface abstraction, the dependency from higher level to lower level is: 
  - `UserServiceController`
  - `UserRequestProcessor`
  - `UserDaoImpl`
- For presenting general user information and verification, please refer to `UserServiceController`.
- For querying specific user information and updating information, please refer to `UserRequestProcessor`.
- For most cases, it is not necessary to call the `UserDaoImpl`. Treat it as a black box, XD. 

## UserServiceController
- General Responsibility:
  * Perform Login/Register actions.
  * Present User Information (wish list, course list, schedule list).
  * Update User Information
      * insert objects into wish list, course list, schedule list;
      * remove objects from wish list, course list, schedule list;
      * clear objects from wish list, course list, schedule list;

- If you want to perform login action, try `.userLogin(String username, String password)`. It will return `true` iff 
that's a successful login for given password and username.
  - Reasons for unsuccessful login:
    - Wrong username/password
    - or, there is no such record in the database.
    - or... the database is not working, check the control panel of mongo db.

- If you want to perform register action, try `.userRegister(String username, String password)`. It will return `true` iff
  that's a successful register for given password and username.
    - Reasons for unsuccessful register:
        - There is an existing user, with the same password, in the DB.
        - or... the database is not working, check the control panel of mongo db.
- If you wish to present the user information (in the format of a `String`):
  - Due to process simplification and laziness, please only call these methods after user verification(login, register). 
  - Course List: `.getCourseList(String username)`, will give you string representation of course list.
  - Wish List: `.getWishList(String username)`, will give you a string representation of the wish list.
  - Schedule List: `.getScheduleList(String username)`, will give you a string representation of the schedule list.
- If you wish to get the latest `Schedule`, I mean the *Object*, you may call `.getLatestSchedule(String username)`, it wil give you the `Schedule` obj. It is handy when it comes to schedule update and calendar present.
- If you wish to modify the course/wish, you may try `.rmCourse(String username, String courseCode)`,  `rmWish(String username, String courseCode)`. 
- If your wish to clear up the course/wish list, you may try `userClearCourseList(String username)` and `userClearWishList(String username)`.

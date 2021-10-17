# courseService Documentation

## Design Goal
- Provide connection with UofT API
- Perform course information querying
- Perform course planning

## Overall Structure
- The overall structure can be divided into three parts, each is segregated with abstraction (by interface-orientated programming.)
- Omitting the interface abstraction, the dependency from higher level to lower level is:
    - `CrouseServiceController`
    - `CourseInformationGenerator`, `Schedule Updator`, `CoursePlanner`
    - `CourseDaoimpl`
- For presenting general course information and course scheduling, please refer to `CourseServiceController`.
- For querying specific course information, course planning, updating `Schedule`, please refer to `CourseInformationGenerator` and `CoursePlanner` and `ScheduleUpdater`.
- For most cases, it is not necessary to call the `CourseDaoImpl`. Treat it as a black box, XD.

## UserServiceController
- General Responsibility:
  * Summaries and present information, such as Course General Information Table, Course Schedule Summary, Section Information.


- If you wish to perform course planning, you may want to try `planCourse(String username)`.I will give you a completed `Schedule` and set the `Schedule` at the head of the user's schedule list.
- If you wish to visualize a schedule, or check its summary, you may want to try `getScheduleSummary(Schedule schedule)`. It will give a String to you.
- if you wish to get information for a specific section, you may want to try `getSectionInformation(String courseCodeWSection)`. It will return to you a String representation of the section requested.
- If you wish to get summary of a course, you may want to try `getCourseGeneralInformation(String courseCode)`. It will return you a comprehensive summary table of details of the course.
- If you wish to add course/wish, you may try `addCourse(String username, String courseCode)` or `addWish(String username, String courseCode)`. They will return ture iff the process goes thorough.
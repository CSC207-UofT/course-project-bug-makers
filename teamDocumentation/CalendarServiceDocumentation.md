# CalendarService Documentation

## Design Goal
- Provide customized visual representations of calendars, which aims to support users to make better course planning decisions and help them achieve better time management.
- Generate Calendars using Schedule List from `userService`.

## Overall Structure
- The overall structure can be divided into two parts, following interface-orientated programming
  - `CalendarPresenter`
  - `CalendarFactory`
- For visually presenting a Calendar, please refer to `CalendarPresenter`
  - Decorator Design Pattern was incorporated in this controller to provide a flexible extensive functionality, 
enabling customizations based on termType.
- For processing specific user's schedule list into a Calendar, please refer to the abstract `CalendarFactory`
  - We use simple Factory Design pattern in the abstract `CalendarFactory`, enables its 3 subclasses to generate calendars in **3 different styles** as **7 specific types**.
    - `WorkdayCalendar` generates a 5-day calendar from Monday to Friday.
    - `WeekCalendar`generates a 7-day calendar from Sunday to Saturday.
    - The abstract `SingledayCalendar` apply Factory Design pattern for producing one-day calendars. Its 5 subclasses each generate a one-day calendar for one specific workday.
      - `MondayCalendar` generates a one-day calendar for Monday.
      - `TuesdayCalendar` generates a one-day calendar for Tuesday.
      - `WednesdayCalendar` generates a one-day calendar for Wednesday.
      - `ThursdayCalendar`  generates a one-day calendar for Thursday.
      - `FridayCalendar`  generates a one-day calendar for Friday.

## CalendarPresenter
- General Responsibility:
  - Process the schedule list from `UserService`and presenting a target calendar based on parameters.


- When you want to have a Calendar presenting in the commandline shell, you use the public method `.presentCalendar()`
in the `CalendarPresenter` class.
  - This controller method takes 3 parameters, `String termType`, `String calendarType` and `Map<String, Map<String, Map<String, ArrayList<String>>>> rawSchedule`. Note that `rawSchedule` is the `scheduleMap` attribute of `Schedule` Type (Might be handy when programming the command in the driver).
  - By modifying the `termType` you can change the term(s) presented the Calendar,
    - `"F"` for fall term, 
    - `"S"` for winter term, 
    - `"Y"` for both of  terms, please be aware that it gives **two** calendars.

  - You can choose different format arguing 7 kinds of `calendarType`,
    - `"Workday"` for a workday calendar,
    - For `SingledayCalendar` , you HAVE to specify the exact weekday
      - Note that `SingledayCalendar` itself is abstract so `"Singleday"` does **NOT** make sense.
      - `"Monday"` for only Monday on the calendar, etc.
      - `"Tuesday"`
      - `"Wednesday"`
      - `"Thursday"` 
      - `"Friday"`
    - `"Week"` for a 7-day calendar of the whole week, from Sunday to Saturday.




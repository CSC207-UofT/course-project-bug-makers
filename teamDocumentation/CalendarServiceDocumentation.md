# CalendarService Documentation

## Design Goal
- Provide visual representation of Calendars
- Generate Calendars using Schedule List from `userService`

## Overall Structure
- The overall structure can be divided into two parts, following interface-orientated programming
  - `CalendarPresenter`
  - `CalendarFactory`
- For visually presenting a Calendar, please refer to `CalendarPresenter`
- For processing specific user's schedule list into a Calendar, please refer to `CalendarFactory`, where we use simple 
Factory Design pattern and Decorator Design Pattern to increase extensibility.

##CalendarPresenter
- General Responsibility:
  - Process the schedule list from `UserService`and presenting a target calendar based on parameters.


- When you want to have a Calendar presenting in the commandline shell, you use the public method `.presentCalendar()`
in the `CalendarPresenter` class.
  - The method takes 3 parameters, `String termType`, `String calendarType` and `Map<String, Map<String, Map<String, ArrayList<String>>>> rawSchedule`. Note that `rawSchedule` is the `scheduleMap` attribute of `Schedule` Type (Might be handy when programming the command in the driver).
  - By modifying the `termType` you can change the term(s) presented the Calendar,
    - `"F"` for fall term, 
    - `"S"` for winter term, 
    - `"Y"` for both of  terms, please be aware that it gives **two** calendars.

  - You can choose different format arguing `calendarType`, so for instant, `"WorkDay"` is the only available choice as we have only implemented this subclass, `WorkdayCalendar`. 


- The rest of the code are functional, but they will not be accessed or used directly by users.


Thats it, ((((;°Д°))))




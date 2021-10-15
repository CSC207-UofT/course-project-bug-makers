### [CalendarService] CalendarPresenter Documentation

When you want to have a Calendar present in the commandline shell, you use the public method `.presentCalendar()`
in the `CalendarPresenter` class. 

- The method takes 3 parameters, `String termType`, `String calendarType` and `Map<String, Map<String, Map<String, ArrayList<String>>>> rawSchedule`. Note that `rawSchedule` is the `scheduleMap` attribute of `Schedule` Type (Might be handy when programming the command in the driver).
- By modifying the `termType` you can change the term(s) presented the Calendar,
  - "F" for fall term, 
  - "S" for winter term, 
  - "Y" for both of  terms, please be aware that it gives **two** calendars.

- You can choose different format arguing `calendarType`, so for instant, `"WorkDay"` is the only available choice as we have only implemented this subclass, `WorkdayCalendar`. 



Thats it, ((((;°Д°))))




# CMD Line Driver Documentation

- `userClearCourseList`; Clear the user's course list.
- `clearScheduleList`; Clear the user's schedule list.
- `rmCourse`; Remove given course from the course list. It takes one arg, which is the course code.
- `rmWish`; Remove given wish from the course list. It takes one arg, which is the course code.
- `getCourseList`; Give course list.
- `getWishList`; Give wish list.
- `getScheduleList`; Give schedule list.
- `addCourse`; Add given course to the course list. It takes one arg, which is the course code.
  - eg. `addCourse CSC207FLEC0101` or `addCourse CSC207F`. (Note for `Phase 0`, please use the former format, as it is manually scheduling the courses)
- `addWish`; Add given course to the wish list. It takes one arg, which is the course code.
- `getLatestSchedule`; Give the latest schedule in the user account.
- `getCourseGeneralInformation`; Give the course information summary. It takes one arg, which is the course code.
- `getSectionInformation`; Give the section information summary. It takes one arg, which is the course code.
- `planCourse`; Plan the course. Note that, the algorithm would prioritize the course in the course list, then schedule the ones in wish list.
  - Special note for `Phase 0`: the current algorithm requires *Manual* scheduling, the auto-scheduling is for preview. Please check our branch for details.
- `presentCalendar`; Show the calendar for the latest schedule. It takes two args. First one is for the term type (`F`/`S`/`Y`). Second one is for the calendar type (`Workday`, etc.)
  - Note that, for term arg, `F` or `S` would give one single calendar while `Y` gives both.
    - eg. `presentCalendar F Workday`


Please Enjoy,

Team `bugMaker`



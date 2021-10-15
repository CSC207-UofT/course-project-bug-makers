# Specification

### 1. Introduction
For our CSC207 project, we are planning to build a course planning app that allows students to plan and review their 
courses. Based on student reviews and user information, our app can also recommend specific courses tailored to the 
user’s program requirements. Alongside the course planning function, our app aims to provide a platform for students 
to connect and network with their fellow peers. 

### 2. Registration
When a user registers for an account, they will be required to create a username and password so that they can save 
any changes to their account then log back in when needed. The user account will store information such as the user’s 
ID, courses taken, courses wishlist, program and year, as well as their current timetable.

### 3. Main Function
The main function of our app is to allow students to add specific courses to their timetable. Each course has a name 
and a course ID, as well as other details about the course such as its breadth category, distribution category, 
co-requisites, delivery instructions, course description and list of sections. Each section has a section ID and can
be further classified as one of the three following categories: lecture, tutorial, or lab. Each lecture section will 
have an assigned professor, while tutorials and labs will have an assigned teaching assistant (TA).

### 4. Scheduling 
When a user wants to plan a new schedule, our app will generate a calendar that will allow students to visualize their 
current timetable. Each schedule has a course, a section, and a time slot. When a student wants to plan a schedule into
their timetable, the schedule will be added to the user’s current timetable, which is a list of their current schedules.
Students can search up information such as a course’s description, as well as their current timetable and courses they 
have previously taken. 

### 5. Submitting Reviews
Alongside planning their timetable, students can submit reviews of their courses on the reviews forum. Each review 
should contain a rating out of 5 and an optional comment made by the user about the course. Once a review is submitted, 
it will be posted onto the reviews forum, where other students can publicly view. Based on the rating of the courses, 
our app will use an algorithm to recommend specific courses to a user. The algorithm will also take into account the 
user’s program, year as well as time availability for the respected course. 





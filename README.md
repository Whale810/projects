# Classroom Booking System

- Guo Zikang, 18206115
- Jin Chenzhe, 18206139
- You may view the demo operation in [implmentation Video.mp4] 

## Working Software Part

The whole code of the project has been uploaded to GitLab and Github.

## Functions implemented

### Log in winodw

* A window to let you enter your id and password to login in the application or choose to register function.
* If this is your first time to use this application, you have to register an account first.

### Register window

* A window to let you register an account for this application
* when you click the confirm button, application will record your account into database and you can use this account to log in this application.

### Main window

In the main window, the system is divided into two parts: Setting and Timetable.

- Setting -- It is about the opeartion related to Timetable, including search, add, edit and select the data of the timetable.
- Timetable -- It is about the display of the timetable. When you use the search function, then the result will be displayed at this area.

### Setting

* If user selects All and clicks the search button, then every booking that stored in the database will be displayed at the timetable area.
If user selects "search by teacher", enters the name of the teacher and clicks the search button, then every booking related to this teacher will be displayed at the timetable area.
If user selects "search by classgroup", enters the name of the classroom and clicks the search button, then every booking related to this classroom will be displayed at the timetable area.

* If user clicks Add button, user can add a new booking into database.
In Add Booking window, user can enter the date and section to search the availablle classroom. Then user can choose the teacher, classgroup and classroom.
When these are all done, the system will record this booking into database.

* If user selects an exist booking in the timetable and clicks Edit button, then user can change the information about this booking or delete this booking.
In Edit Booking window, the information about this booking will be displayed firstly. Then user can change the information the user wants.
When user clicks confirm button, the system will upload this booking to the database.
When user clicks delete button, the system will delete this booking in the datavase.

* If user clicks The previous day button, then the date of timetable will change to the previous day.

* If user clicks The next day button, then the date of timetable will change to the next day.

* If user clicks Exit button, user can choose to exit this application or go back to log in window.

### Timetable

* If user clicks one exist booking, then it will be displayed in the pink part of the window. It means this booking is been selected and user can use Edit function.

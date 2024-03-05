# NASA APOD Web App
This web application allows a user to view NASA's Astronomy Picture of the Day for any date since the beginnings of the program on June 16, 1995. Upon selecting a date, the feature image/video is displayed
alongside the title and explanation of the image, as well as the copyright information if applicable. The user is able to view the images for as many dates as they desire before exiting the program. This application
uses the APOD API from NASA to fetch all data concerning the Pictures of the Day. The documentation for this API can be found [here](https://github.com/nasa/apod-api).

To run this application, start by cloning this project and ensuring that you are in the parent directory. Next, run *./gradle bootRun* if on MacOS or *./gradlew bootRun* if on Windows. You will now be able to view 
the application at *localhost:8080*.

# FoodApp
This application is an implementation for requirements found in requirements.txt.
It has two projects frontend (restaurant-app) and backend, root of app folder.

### System requirements
* Git version 2.23 or above see [install Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
* Java version 1.8 or above see [install Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
).
* Npm version 6.12.0 or above see [install NPM](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm).
* Maven version 3.6.0 or above see [install Maven](https://maven.apache.org/install.html).

### Fetching the project
Open a terminal and execute ```git clone https://github.com/madalinaotvos91/food-app.git```
### Execute steps
##### Backend
The file ```pom.xml``` contains the project dependencies.
In resources folder, there is an ```application.properties``` file, where the port can be configured.The changes must be updated
on UI side also where the API requests are made, in this case restaurant.service.ts.
* To build and pack the project, from project root, open a command window and execute ```mvn clean install```.
* If the package phase run without errors, then start the application by executing ```mvn spring-boot:run```

Wait until the initialization and startup happens.
##### Frontend

The project is packed in restaurant-app. Open a command window at the given location and execute:
* ```npm install``` to install the project dependencies on frontend side.
* If the installation executed successfully then execute ```ng serve```. The application is available on
[http://localhost:4200](http://localhost:4200) :shipit:.


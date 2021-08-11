
# Contacts Web Service

The REST API is able to perform all of the operations in the given instructions.

The application includes a built-in H2 database, and the contact from the provided example ("Harold Francis Gilkey") is stored with ID `1001`. 


## Project Setup
* Clone the project and run `./gradlew bootRun` to start up the web service 
* The application will serve from http://localhost:8080




## Testing
Unit tests have been written for the main CRUD operations.
With additional time available, I would write the following:
* More unit tests for the `ContactsServiceImpl` to cover the remaining scenarios 
* Unit tests for the REST controller (`ContactsController`)
* Integration tests for service / data layer




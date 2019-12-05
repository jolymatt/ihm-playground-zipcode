# ihm-playground-zipcode
Microservice to discover USA zipcodes based on a Google Map Polygon

Guideline on Deploying & Running.

1) Setup MySQL and Run the scripts in src/main/resources/db/zipcodes.sql
2) Change the username/password/server ip address in SpringJdbcConfig.java class
3) Run ZipCodeApp.java class 
4) Open browser and issue http://localhost:8080/map-poc.html

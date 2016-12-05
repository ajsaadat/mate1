In order to run the tests execute the following command:
mvn test
A load test can be perform if httpcore-ab is installed.
go to the test folder and execute the following command
	ab -p loadTest -c 10 -n 10 -t 1000 https://localhost:4430/ajand
	

In order to run the application execute the following command:
mvn spring-boot:run
this will run the application within spring internal tomcat server.
There is no front end to this application so in order to post/get data
a tool like soap-ui or postman should be used. 

There are two ways to post data to the system
1) via URL
	https://localhost:4430/{bucketname}/{data}
	
2) via body and post method
	https://localhost:4430/{bucketname}
	and body containing data. 
	
To get data from the system use the following URL:
	https://localhost:4430/{bucketname} [get method]


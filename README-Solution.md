
#WBAA-Assignment 

- A Spring boot application running in a container *(meetup-rsvp-be)* which streams rsvp data from meetup.com

- Calculates the most popular meetup locations , trending topics using Apache Flink streaming framework.

- Uses Apache Flink Producers to push data to kafka topics **favourite-locations & trending-topics**

- Uses Spring Kafka Consumers to fetch data from kafka topics **favourite-locations & trending-topics** and feed it to web sockets.

- Interacts with  kafka containers *(zookeeper and kafka)* for pushing and consuming this real time data

- Uses A NodeJs App running in a container *(meetup-rsvp-fe)* to talk to these web_socket

- Uses google map APIs _(HeatMapLayer & GeoCharts)_ to plot the data in heatOverlay and as regions respectively.


``System Pre-requisites`` :
- You need to have the `mvn,docker,docker-compose` on your path ( and may be 'JAVA_HOME' variable is set as path variable pointing to JDK directory).
- Advise to stay away from ING network to pull all maven, npm dependencies and images from docker hub.

`` Steps to run the application `` :

1. Unzip the WBAA-Assignment.zip , to see two folders ``wbaa-meetup-rsvp-fe`` & ``wbaa-meetup-rsvp-be``
2. Go to FE NodeJs App by ``cd wbaa-meetup-rsvp-fe`` 
3. Build the frontend image using `` docker build -t jeevan/meetup-rsvp-fe . `` ( don't worry about the npm ``WARN`` logs), when build succeeds
4. Go back to Spring boot App by ``cd ../wbaa-meetup-rsvp-be``
5. Do`` mvn clean package`` to get spring boot application executable Jar , when build succeeds
6. Build the backend image using `` docker build -t jeevan/meetup-rsvp-be . `` , when build succeeds
7. Compose the set of images mentioned in `docker-compose.yml` file in current folder
 using `` docker-compose up -d``
8. The above step takes a while(10-15 minutes) to pull external images from ``docker hub``
9. In any browser hit at the address bar ``http://localhost:5555/locations.html``  to see heat map overlay for popular meet-up locations in the world
10. In any browser hit at the address bar ``http://localhost:5555/topics.html``  to see heat map overlay for popular meet-up locations in the world
11. ``docker-compose down`` to remove them


#Appendix :
- We can also make the Front end served from spring boot app itself using thymeleaf templates by using webSocketJS and stomp protocol dependencies to get the real time data.
But as in requirements it was mentioned to make separate FE and BE apps.
- For example hit and check ``http://localhost:5656/locations`` or `` http://localhost:5656/rsvp``( to access from BE container exposing thymeleaf templates) 

``TODO`` :
- persist data in DB ( like mongo Db or cassandra )


# Stack & Flow diagram :
[Flow Diagram](demo/demo.pdf)



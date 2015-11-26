# TwitterClone
<pre>
Developed using Spring-boot , Angular js ,Bootstrap ,HTML 
1. IDE used Intellij IDEA 13
2. Database Used : Postgres

3.	CREATE TABLE users
(
  email character varying(50) NOT NULL,
  username serial NOT NULL,
  name character varying(50),
  password character varying(50),
  phone character varying(20),
  profileimgurl character varying(250),
  acstatus character varying(50),
  CONSTRAINT users_pkey PRIMARY KEY (email)
)

4.	CREATE TABLE tweets
(
  username integer NOT NULL,
  timest timestamp without time zone NOT NULL,
  message text,
  CONSTRAINT tweets_pkey PRIMARY KEY (username, timest)
)



5.	CREATE TABLE follows
(
  id serial NOT NULL,
  username integer,
  followsto integer,
  CONSTRAINT follows_pkey PRIMARY KEY (id)
)



6.	CREATE SEQUENCE hibernate_sequence  INCREMENT 1
7.	Go to src/main/resources/application.properties  and change the 
		spring.mail.username=<YOUR EMAIL ID>
		spring.mail.password=<YOUR PASSWORD>
8.	Go to src/main/java/fareye/emailTest.java    and change the
		IP address to your current IP address in 'String mailBody' .
9. 	External jar used : org.json.jar.
10. 	Change the Database name , username and Password in /src/main/resources/application.properties file

		spring.datasource.url
		spring.datasource.name
		spring.datasource.password

11. 	I have used java 1.8
	If you are using previous version of java ,make change in pom.xml and few more setting go to bellow link. 
	http://stackoverflow.com/questions/25878045/errorjava-invalid-source-release-8-in-intellij-what-does-it-mean

	</pre>

# TwitterClone
Developed using Spring-boot , Angular js ,Bootstrap ,HTML 

1. IDE used Intellij IDEA 
2. Database Used : Postgres

3.	CREATE TABLE users
(
  email character varying(50) NOT NULL,
  username serial NOT NULL,
  name character varying(50),
  password character varying(50),
  phone character varying(20),
  profileimgurl character varying(250),
  followsto text,
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
7. 	External jar used : org.json.jar
	

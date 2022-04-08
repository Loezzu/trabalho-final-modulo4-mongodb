CREATE TABLE ADDRESS(
  ID_ADDRESS SERIAL PRIMARY KEY,
  STREET text NOT NULL,
  HOUSE_NUMBER numeric  NOT NULL,
  CITY text NOT null,
  CEP text not null
);


CREATE TABLE PERSONINFO (
  ID_PERSONINFO SERIAL PRIMARY KEY,
  REALNAME text NOT NULL,
  AGE numeric  NOT NULL,
  EMAIL text  UNIQUE NOT NULL
  );
 
 
 CREATE TABLE TINDEV_USER (
  USER_ID SERIAL PRIMARY KEY,
  USERNAME text UNIQUE NOT NULL,
  PASSWORD text NOT NULL,
  PERSONINFO_ID INTEGER UNIQUE NOT NULL,
  ADDRESS_ID INTEGER UNIQUE NOT NULL,
  PROGLANGS text NOT NULL,
  GENDER text NOT NULL,
  PREF text NOT NULL,
  CONSTRAINT fk_address FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ID_ADDRESS),
  CONSTRAINT fk_personInfo FOREIGN KEY (PERSONINFO_ID) REFERENCES PERSONINFO (ID_PERSONINFO)
  );
 
 
CREATE TABLE LIKE_TINDEV_USER(
 ID_LIKE SERIAL PRIMARY KEY,
 USER_ID INTEGER NOT NULL,
 LIKED_USER_ID INTEGER NOT NULL,
 CONSTRAINT fk_userid FOREIGN KEY(USER_ID) REFERENCES TINDEV_USER(USER_ID),
 CONSTRAINT fk_liked_userid FOREIGN KEY(LIKED_USER_ID) REFERENCES TINDEV_USER(USER_ID)
);

CREATE TABLE MATCH_TINDEV_USER(
 ID_MATCH SERIAL PRIMARY KEY,
 USER_ID_FIRST INTEGER NOT NULL,
 USER_ID_SECOND INTEGER NOT NULL,
 CONSTRAINT fk_userid_first FOREIGN KEY(USER_ID_FIRST) REFERENCES TINDEV_USER(USER_ID),
 CONSTRAINT fk_userid_second FOREIGN KEY(USER_ID_SECOND) REFERENCES TINDEV_USER(USER_ID)
);


insert into address (street, house_number, city, cep)
values ('rua sla', 123, 'gravataí','12245566'),
       ('rua nsei', 321, 'cachoeirinha','12134455');

insert into personinfo(realname, age, email)
values ('guilherme', 50, 'gui@mail.com'),
		('pablo', 20, 'pablo@mail.com');

insert into tindev_user(username, password, personinfo_id, address_id, proglangs, gender, pref)
values ('gui', 'gui123', 1, 1, 'JAVA', 'MALE', 'BOTH'),
	   ('pablo', 'pablo123', 2, 2, 'JAVA', 'MALE', 'BOTH');
	  
insert into like_tindev_user(user_id, liked_user_id)
values (1,2)
	  
drop table match_tindev_user;
drop table like_tindev_user;
drop table tindev_user;
drop table address;
drop table personinfo; 
	  
	  
	
 

use newservletjdbc2023 ;


CREATE TABLE role(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL , 
    code VARCHAR(255) NOT NULL ,
    createdDate TIMESTAMP NULL , 
    modifiedDate timestamp NULL ,
    createBy VARCHAR(255) NULL ,
    modifiedBy VARCHAR(255) NULL 
);

CREATE TABLE user (
	id bigint NOT NULL PRIMARY KEY auto_increment ,
    username varchar(255) NOT NULL ,
    password varchar(255) NOT NULL ,
    fullname varchar(255) NOT NULL ,
    status int NOT NULL ,
    createdDate TIMESTAMP NULL , 
    modifiedDate timestamp NULL ,
    createBy VARCHAR(255) NULL ,
    modifiedBy VARCHAR(255) NULL ,
    roleid bigint NOT NULL 
);






CREATE TABLE news (
	id bigint NOT NULL PRIMARY KEY auto_increment ,
	title VARCHAR(255)  NULL ,
    thumbnail varchar(255)  NULL ,
    shortdescription TEXT  NULL ,
    content TEXT  NULL ,
    createdDate TIMESTAMP NULL , 
    modifiedDate timestamp NULL ,
    createBy VARCHAR(255) NULL ,
    modifiedBy VARCHAR(255) NULL ,
    categoryid bigint NOT NULL 
);




CREATE TABLE category (
	id bigint NOT NULL PRIMARY KEY auto_increment ,
	name VARCHAR(255)  NULL ,
    code varchar(255)  NULL ,
    createdDate TIMESTAMP NULL , 
    modifiedDate timestamp NULL ,
    createBy VARCHAR(255) NULL ,
    modifiedBy VARCHAR(255) NULL 
);
 


CREATE TABLE comment(
	id bigint NOT NULL PRIMARY KEY auto_increment ,
    content TEXT NOT NULL ,
    createdDate TIMESTAMP NULL , 
    modifiedDate timestamp NULL ,
    createBy VARCHAR(255) NULL ,
    modifiedBy VARCHAR(255) NULL ,
    userid bigint NOT NULL ,
    newid bigint NOT NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY(roleid) REFERENCES role(id) ;
ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id) ;
ALTER TABLE comment ADD CONSTRAINT fk_user_comment FOREIGN KEY (userid) REFERENCES user(id) ;
ALTER TABLE comment ADD CONSTRAINT fk_news_comment FOREIGN KEY (newid) REFERENCES news(id) ;




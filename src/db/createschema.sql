DROP TABLE TONY_PART;
DROP TABLE TONY_FILE_CONTENT;

DROP TRIGGER TRG_TONY_FILE_CONTENT;
DROP TRIGGER TRG_TONY_PART;

DROP SEQUENCE SEQ_TONY_FILE_CONTENT;
DROP SEQUENCE SEQ_TONY_PART;

CREATE TABLE TONY_FILE_CONTENT (
  ID  NUMBER(19,0) NOT NULL PRIMARY KEY,
  NAME VARCHAR2(200) NOT NULL,
  CONTENT BLOB
);
CREATE SEQUENCE SEQ_TONY_FILE_CONTENT START WITH 1 INCREMENT BY 1;
create or replace trigger TRG_TONY_FILE_CONTENT before insert on TONY_FILE_CONTENT for each row when (NEW.ID is null) begin 	:NEW.ID := SEQ_TONY_FILE_CONTENT.NEXTVAL; end;

CREATE TABLE TONY_PART (
  ID  NUMBER(19,0) NOT NULL PRIMARY KEY,
  NAME VARCHAR2(200) NOT NULL
);
CREATE SEQUENCE SEQ_TONY_PART START WITH 1 INCREMENT BY 1;
create or replace trigger TRG_TONY_PART before insert on TONY_PART for each row when (NEW.ID is null) begin 	:NEW.ID := SEQ_TONY_PART.NEXTVAL; end;

alter table TONY_PART add FILE_CONTENT_ID NUMBER(19, 0);
alter table TONY_PART add constraint FK_FILE_CONTENT foreign key (FILE_CONTENT_ID) references FILE_CONTENT(ID);


create table TONY_ORG_ENTITY (
  ORG_ID NUMBER(19, 0) NOT NULL PRIMARY KEY,
  ORG_CD VARCHAR2(25) NOT NULL,
  TOR_ID NUMBER(19, 0) NOT NULL
)
;

CREATE TABLE TONY_ORG_ENTITY_TYPE(
  TOR_ID NUMERIC(19, 0) NOT NULL PRIMARY KEY,
  CD_SP2 VARCHAR2(25) NOT NULL,
  NAME VARCHAR2(150) NOT NULL
)
;



--
-- BEGIN: configuration name "custom.module.ADBConfigurationResource" , schema name "DBPoller1"
--
-- Wed Jun 02 22:14:49 EDT 2021
CREATE TABLE P_CUSTOMER AS SELECT CUSTOMER_ID ,CUSTOMER_NO ,FIRST_NAME ,LAST_NAME FROM 
CUSTOMER 
WHERE 1 > 9
/
-- Wed Jun 02 22:14:49 EDT 2021
ALTER TABLE P_CUSTOMER ADD (
	ADB_SEQUENCE INTEGER NOT NULL,
	ADB_TIMESTAMP TIMESTAMP WITH TIME ZONE NULL,
	ADB_OPCODE INTEGER NOT NULL,
	ADB_REF_OBJECT VARCHAR2(64) NULL,
	ADB_L_DELIVERY_STATUS CHAR NULL
)
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE UNIQUE INDEX IDX1_P_CUSTOMER ON P_CUSTOMER (ADB_SEQUENCE)
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE UNIQUE INDEX IDX2_P_CUSTOMER ON P_CUSTOMER (ADB_L_DELIVERY_STATUS, ADB_SEQUENCE)
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE SEQUENCE P_CUSTOMER_SEQ 
	START WITH 1 
	INCREMENT BY 1 
	NOMAXVALUE 
	ORDER 
	NOCYCLE 
	CACHE 10
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE OR REPLACE TRIGGER TR1_P_CUSTOMER AFTER INSERT OR DELETE OR UPDATE OF CUSTOMER_NO,FIRST_NAME,LAST_NAME ON CUSTOMER
FOR EACH ROW
DECLARE
	updating_key_fields EXCEPTION;
BEGIN
	IF INSERTING THEN
	INSERT INTO CUSTOMUSER.P_CUSTOMER VALUES (
		:NEW.CUSTOMER_ID,
		:NEW.CUSTOMER_NO,
		:NEW.FIRST_NAME,
		:NEW.LAST_NAME,
		CUSTOMUSER.P_CUSTOMER_SEQ.NEXTVAL,
		SYSTIMESTAMP,
		1,
		NULL,
		'N');
	END IF;
	EXCEPTION
		WHEN updating_key_fields THEN
			raise_application_error(-20300, 'ActiveDB Error: cannot update key fields of source table.');
END TR1_P_CUSTOMER;
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE OR REPLACE TRIGGER TR2_P_CUSTOMER AFTER INSERT OR DELETE OR UPDATE OF CUSTOMER_NO,FIRST_NAME,LAST_NAME ON CUSTOMER
FOR EACH ROW
DECLARE
	updating_key_fields EXCEPTION;
BEGIN
	IF UPDATING THEN
		IF UPDATING('CUSTOMER_ID') THEN
			RAISE updating_key_fields;
		END IF;
	INSERT INTO CUSTOMUSER.P_CUSTOMER VALUES (
			:OLD.CUSTOMER_ID,
			:NEW.CUSTOMER_NO,
			:NEW.FIRST_NAME,
			:NEW.LAST_NAME,
		CUSTOMUSER.P_CUSTOMER_SEQ.NEXTVAL,
		SYSTIMESTAMP,
		2,
		NULL,
		'N');
	END IF;
	EXCEPTION
		WHEN updating_key_fields THEN
			raise_application_error(-20300, 'ActiveDB Error: cannot update key fields of source table.');
END TR2_P_CUSTOMER;
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE OR REPLACE TRIGGER TR3_P_CUSTOMER AFTER INSERT OR DELETE OR UPDATE OF CUSTOMER_NO,FIRST_NAME,LAST_NAME ON CUSTOMER
FOR EACH ROW
DECLARE
	updating_key_fields EXCEPTION;
BEGIN
	IF DELETING THEN
	INSERT INTO CUSTOMUSER.P_CUSTOMER VALUES (
		:OLD.CUSTOMER_ID,
		:OLD.CUSTOMER_NO,
		:OLD.FIRST_NAME,
		:OLD.LAST_NAME,
		CUSTOMUSER.P_CUSTOMER_SEQ.NEXTVAL,
		SYSTIMESTAMP,
		3,
		NULL,
		'N');
	END IF;
	EXCEPTION
		WHEN updating_key_fields THEN
			raise_application_error(-20300, 'ActiveDB Error: cannot update key fields of source table.');
END TR3_P_CUSTOMER;
/
-- Wed Jun 02 22:14:49 EDT 2021
CREATE TABLE P_CUSTOMER_MUTEX (COL1 int)
/

--
-- END: configuration name "custom.module.ADBConfigurationResource" , schema name "DBPoller1"
--

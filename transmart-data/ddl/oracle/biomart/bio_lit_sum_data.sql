--
-- Type: TABLE; Owner: BIOMART; Name: BIO_LIT_SUM_DATA
--
 CREATE TABLE "BIOMART"."BIO_LIT_SUM_DATA" 
  (	"BIO_LIT_SUM_DATA_ID" NUMBER(18,0) NOT NULL ENABLE, 
"ETL_ID" NVARCHAR2(50), 
"DISEASE_SITE" NVARCHAR2(250), 
"TARGET" NVARCHAR2(50), 
"VARIANT" NVARCHAR2(50), 
"DATA_TYPE" NVARCHAR2(50), 
"ALTERATION_TYPE" NVARCHAR2(100), 
"TOTAL_FREQUENCY" NVARCHAR2(50), 
"TOTAL_AFFECTED_CASES" NVARCHAR2(50), 
"SUMMARY" NVARCHAR2(1000), 
 CONSTRAINT "BIO_LIT_SUM_DATA_PK" PRIMARY KEY ("BIO_LIT_SUM_DATA_ID")
 USING INDEX
 TABLESPACE "INDX"  ENABLE
  ) SEGMENT CREATION IMMEDIATE
 TABLESPACE "TRANSMART" ;

--
-- Type: TRIGGER; Owner: BIOMART; Name: TRG_BIO_LIT_SUM_DATA_ID
--
  CREATE OR REPLACE TRIGGER "BIOMART"."TRG_BIO_LIT_SUM_DATA_ID" 
before insert on "BIO_LIT_SUM_DATA"
for each row
begin
     if inserting then
       if :NEW."BIO_LIT_SUM_DATA_ID" is null then
          select SEQ_BIO_DATA_ID.nextval into :NEW."BIO_LIT_SUM_DATA_ID" from dual;
       end if;
    end if; end;







/
ALTER TRIGGER "BIOMART"."TRG_BIO_LIT_SUM_DATA_ID" ENABLE;
 
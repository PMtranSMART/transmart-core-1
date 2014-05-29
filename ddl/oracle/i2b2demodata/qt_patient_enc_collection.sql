--
-- Type: TABLE; Owner: I2B2DEMODATA; Name: QT_PATIENT_ENC_COLLECTION
--
 CREATE TABLE "I2B2DEMODATA"."QT_PATIENT_ENC_COLLECTION" 
  (	"PATIENT_ENC_COLL_ID" NUMBER(10,0) NOT NULL ENABLE, 
"RESULT_INSTANCE_ID" NUMBER(5,0), 
"SET_INDEX" NUMBER(10,0), 
"PATIENT_NUM" NUMBER(10,0), 
"ENCOUNTER_NUM" NUMBER(10,0),
CONSTRAINT "QT_PATIENT_ENC_COLL_PK" PRIMARY KEY ("PATIENT_ENC_COLL_ID")
  ) SEGMENT CREATION IMMEDIATE
 TABLESPACE "I2B2_DATA" ;
--
-- Type: REF_CONSTRAINT; Owner: I2B2DEMODATA; Name: QT_FK_PEC_RI
--
ALTER TABLE "I2B2DEMODATA"."QT_PATIENT_ENC_COLLECTION" ADD CONSTRAINT "QT_FK_PEC_RI" FOREIGN KEY ("RESULT_INSTANCE_ID")
 REFERENCES "I2B2DEMODATA"."QT_QUERY_RESULT_INSTANCE" ("RESULT_INSTANCE_ID") ENABLE;
--
-- Type: TRIGGER; Owner: I2B2DEMODATA; Name: TRG_QT_PEC_PEC_ID
--
  CREATE OR REPLACE TRIGGER "I2B2DEMODATA"."TRG_QT_PEC_PEC_ID" 
   before insert on "I2B2DEMODATA"."QT_PATIENT_ENC_COLLECTION" 
   for each row 
begin  
   if inserting then 
      if :NEW."PATIENT_ENC_COLL_ID" is null then 
         select QT_SQ_QPER_PECID.nextval into :NEW."PATIENT_ENC_COLL_ID" from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "I2B2DEMODATA"."TRG_QT_PEC_PEC_ID" ENABLE;

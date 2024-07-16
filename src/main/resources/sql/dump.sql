-- Adminer 4.8.1 PostgreSQL 16.2 (Debian 16.2-1.pgdg120+2) dump

DROP TABLE IF EXISTS "event";
DROP SEQUENCE IF EXISTS event_id_seq;
CREATE SEQUENCE event_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 585 CACHE 1;

CREATE TABLE "public"."event"
(
    "id"            bigint DEFAULT nextval('event_id_seq') NOT NULL,
    "date_created"  timestamp(6)                           NOT NULL,
    "description"   character varying(255),
    "text_color"    character varying(255),
    "dictionary_id" bigint,
    CONSTRAINT "event_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "event_dictionary";
DROP SEQUENCE IF EXISTS event_dictionary_id_seq;
CREATE SEQUENCE event_dictionary_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 32 CACHE 1;

CREATE TABLE "public"."event_dictionary"
(
    "id"               bigint DEFAULT nextval('event_dictionary_id_seq') NOT NULL,
    "date_created"     timestamp(6)                                      NOT NULL,
    "description"      character varying(255),
    "is_active"        boolean                                           NOT NULL,
    "name"             character varying(255)                            NOT NULL,
    "record_id"        bigint                                            NOT NULL,
    "uuid"             character varying(255),
    "type_vaccination" character varying(255),
    CONSTRAINT "event_dictionary_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "favorite_article";
DROP SEQUENCE IF EXISTS favorite_article_id_seq;
CREATE SEQUENCE favorite_article_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 19 CACHE 1;

CREATE TABLE "public"."favorite_article"
(
    "id"         bigint DEFAULT nextval('favorite_article_id_seq') NOT NULL,
    "article_id" bigint,
    "user_login" character varying(255),
    CONSTRAINT "favorite_article_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "not_exist_parent";
DROP SEQUENCE IF EXISTS not_exist_parent_id_seq;
CREATE SEQUENCE not_exist_parent_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 30 CACHE 1;

CREATE TABLE "public"."not_exist_parent"
(
    "id"          bigint DEFAULT nextval('not_exist_parent_id_seq') NOT NULL,
    "description" character varying(255),
    "name"        character varying(255),
    "sex"         character varying(255),
    CONSTRAINT "not_exist_parent_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "notification";
DROP SEQUENCE IF EXISTS notification_id_seq;
CREATE SEQUENCE notification_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 318 CACHE 1;

CREATE TABLE "public"."notification"
(
    "id"          bigint DEFAULT nextval('notification_id_seq') NOT NULL,
    "description" character varying(255),
    "is_active"   boolean                                       NOT NULL,
    "name"        character varying(255),
    "record_id"   bigint,
    "user_login"  character varying(255),
    "create_date" timestamp(6),
    "update_date" timestamp(6),
    CONSTRAINT "notification_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "notification_time";
DROP SEQUENCE IF EXISTS notification_time_id_seq;
CREATE SEQUENCE notification_time_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1255 CACHE 1;

CREATE TABLE "public"."notification_time"
(
    "id"              bigint DEFAULT nextval('notification_time_id_seq') NOT NULL,
    "date"            timestamp(6),
    "event_id"        bigint,
    "state_id"        bigint,
    "time"            timestamp(6),
    "notification_id" bigint,
    "record_id"       bigint,
    CONSTRAINT "notification_time_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "pedigree";
DROP SEQUENCE IF EXISTS pedigree_id_seq;
CREATE SEQUENCE pedigree_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 37 CACHE 1;

CREATE TABLE "public"."pedigree"
(
    "id"                      bigint DEFAULT nextval('pedigree_id_seq') NOT NULL,
    "parent_exist_one_id"     bigint,
    "parent_exist_two_id"     bigint,
    "parent_not_exist_one_id" bigint,
    "parent_not_exist_two_id" bigint,
    "record_id"               bigint,
    CONSTRAINT "pedigree_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "record";
DROP SEQUENCE IF EXISTS record_id_seq;
CREATE SEQUENCE record_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 67 CACHE 1;

CREATE TABLE "public"."record"
(
    "id"             bigint DEFAULT nextval('record_id_seq') NOT NULL,
    "create_date"    timestamp(6),
    "update_date"    timestamp(6),
    "data_birthday"  timestamp(6),
    "description"    character varying(255),
    "full_name"      character varying(255),
    "has_pedigree"   boolean                                 NOT NULL,
    "name"           character varying(255)                  NOT NULL,
    "sex"            character varying(255),
    "user_login"     character varying(255)                  NOT NULL,
    "uuid_avatar"    character varying(255),
    "animal_type"    character varying(255),
    "breed"          character varying(255),
    "has_exhibition" boolean                                 NOT NULL,
    CONSTRAINT "record_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "state";
DROP SEQUENCE IF EXISTS state_id_seq;
CREATE SEQUENCE state_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 78 CACHE 1;

CREATE TABLE "public"."state"
(
    "id"            bigint DEFAULT nextval('state_id_seq') NOT NULL,
    "date_created"  timestamp(6),
    "dictionary_id" bigint,
    "description"   character varying(255),
    "value"         double precision                       NOT NULL,
    CONSTRAINT "state_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "state_dictionary";
DROP SEQUENCE IF EXISTS state_dictionary_id_seq;
CREATE SEQUENCE state_dictionary_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 48 CACHE 1;

CREATE TABLE "public"."state_dictionary"
(
    "id"           bigint DEFAULT nextval('state_dictionary_id_seq') NOT NULL,
    "date_created" timestamp(6),
    "is_active"    boolean                                           NOT NULL,
    "name"         character varying(255),
    "record_id"    bigint,
    "uuid"         character varying(255),
    "description"  character varying(255),
    CONSTRAINT "state_dictionary_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


ALTER TABLE ONLY "public"."event" ADD CONSTRAINT "fk_event_dictionary_id_to_id" FOREIGN KEY (dictionary_id) REFERENCES event_dictionary(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."notification_time" ADD CONSTRAINT "fk_time_notification_id_to_id" FOREIGN KEY (notification_id) REFERENCES notification(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."state" ADD CONSTRAINT "fk_state_dictionary_id_to_id" FOREIGN KEY (dictionary_id) REFERENCES state_dictionary(id) NOT DEFERRABLE;

-- 2024-07-16 10:34:35.650037+00
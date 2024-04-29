-- Adminer 4.8.1 PostgreSQL 16.2 (Debian 16.2-1.pgdg120+2) dump

\connect
"records";

DROP TABLE IF EXISTS "record";
DROP SEQUENCE IF EXISTS record_id_seq;
CREATE SEQUENCE record_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."record"
(
    "has_pedigree"  boolean                                 NOT NULL,
    "create_date"   timestamp(6),
    "data_birthday" timestamp(6),
    "id"            bigint DEFAULT nextval('record_id_seq') NOT NULL,
    "update_date"   timestamp(6),
    "description"   character varying(255),
    "full_name"     character varying(255),
    "name"          character varying(255)                  NOT NULL,
    "sex"           character varying(255),
    "user_login"    character varying(255)                  NOT NULL,
    "uuid_avatar"   character varying(255),
    CONSTRAINT "record_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "record" ("has_pedigree", "create_date", "data_birthday", "id", "update_date", "description", "full_name",
                      "name", "sex", "user_login", "uuid_avatar")
VALUES ('f', '2024-04-15 12:12:22.630946', '2018-05-02 13:36:00', 7, '2024-04-21 18:56:02.937203',
        'Черепаха красноухая', 'Женя Марьевна', 'Женя', 'FEMALE', 'user1@mail.com',
        'f1996e19-fb0d-4bd7-b86f-90a914be24fe'),
       ('f', '2024-04-21 18:31:23.169049', NULL, 32, '2024-04-21 18:56:02.969388', 'красноухая черепаха', NULL,
        'Черемать', 'FEMALE', 'user1@mail.com', '24ec8b16-20ec-43ba-8540-00af50410b8a'),
       ('f', '2024-04-15 12:11:42.859605', '2011-04-19 14:23:00', 5, '2024-04-21 18:59:03.621544', 'Что-то с персом',
        'Кошка', 'Кошка', 'FEMALE', 'user1@mail.com', '4c472274-1c31-4e83-bfe4-0827d9dd0adb'),
       ('f', '2024-04-19 11:41:31.552262', NULL, 28, '2024-04-21 18:59:03.660734', NULL, NULL, 'Аквариум', NULL,
        'user1@mail.com', 'a0a8c41e-fb4e-4245-8b1a-40e9d10563de'),
       ('f', '2024-04-19 11:38:11.116022', NULL, 27, '2024-04-21 18:17:28.597495', NULL, NULL, 'No name', NULL,
        'user1@mail.com', '00000000-0000-0000-0000-000000000000');

-- 2024-04-21 16:18:38.380867+00
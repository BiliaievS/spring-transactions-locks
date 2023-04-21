CREATE SCHEMA demo;
CREATE TABLE IF NOT EXISTS demo.tech_star(
    id         SERIAL NOT NULL,
    firstname  varchar NOT NULL,
    lastname   varchar NOT NULL,
    talkname   varchar NOT NULL,
    likes      int8 NULL,
    created    timestamp NULL,
    updated    timestamp NULL,

    CONSTRAINT tech_star_pk PRIMARY KEY (id),
    CONSTRAINT talkname_unique UNIQUE (talkname)
);

create TABLE IF NOT EXISTS demo.history(
    id         SERIAL NOT NULL,
    talkname   varchar NULL,
    likes      int8 NULL,
    status     varchar NULL,
    created    timestamp NULL,

    CONSTRAINT history_pk PRIMARY KEY (id)
);

delete from demo.tech_star;
insert into demo.tech_star (id, firstname, lastname, talkname, likes, created, updated) values (1, 'John', 'Doe', 'Spring best developer',  0, now(), now());
delete from demo.history;

select  * from tech_star;
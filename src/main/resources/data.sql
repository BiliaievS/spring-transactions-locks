create schema demo;

drop table if exists demo.tech_star;
drop table if exists demo.history;

create table if not exists demo.tech_star(
    id         serial not null,
    firstname  varchar not null,
    lastname   varchar not null,
    technology   varchar not null,
    likes      int8 null,
    created    timestamp null,
    updated    timestamp null,

    constraint tech_star_pk primary key (id),
    constraint technology_unique unique (technology)
);

create table if not exists demo.history(
    id         serial not null,
    technology   varchar null,
    likes      int8 null,
    status     varchar null,
    created    timestamp null,

    constraint history_pk primary key (id)
);

delete from demo.tech_star;
insert into demo.tech_star (id, firstname, lastname, technology, likes, created, updated) values (1, 'John', 'Doe', 'Spring best developer',  0, now(), now());
delete from demo.history;

select  * from tech_star;
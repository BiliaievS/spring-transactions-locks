--create schema demo;

--drop table if exists demo.tech_star;
--drop table if exists demo.history;

create table if not exists demo.tech_star(
    id          serial not null,
    code        varchar not null,
    firstname   varchar not null,
    lastname    varchar not null,
    technology  varchar not null,
    votes       int8 null,
    created     timestamp null,
    updated     timestamp null,

    constraint tech_star_pk primary key (id)--,
--     constraint technology_unique unique (technology)
);

create table if not exists demo.history(
    id          serial not null,
    code        varchar not null,
    technology  varchar null,
    votes       int8 null,
    status      varchar null,
    created     timestamp null,

    constraint history_pk primary key (id)
);

delete from demo.tech_star;
insert into demo.tech_star (id, code, firstname, lastname, technology, votes, created, updated) values (1, 'WP001', 'John', 'Doe', 'Spring best developer',  0, now(), now());
insert into demo.tech_star (id, code, firstname, lastname, technology, votes, created, updated) values (2, 'WC123', 'Dave', 'Doe', 'best developer',  0, now(), now());
delete from demo.history;

select  * from tech_star;
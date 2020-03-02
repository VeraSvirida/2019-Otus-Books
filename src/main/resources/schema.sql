create table writers(
    id bigserial,
    fullname varchar(255),
    birthday varchar (100),
    primary key (id)
);

create table genres(
    id bigserial,
    name varchar(255),
    primary key (id)
);
create table books(
    id bigserial,
    title varchar(300),
    description varchar (8000),
    writer_id bigint references writers (id),
    genre_id bigint references genres (id),
    primary key (id)
);


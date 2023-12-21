create table stock (
    id text primary key,
    ticker varchar(6) not null,
    name varchar(100) not null,
    currency char(3) not null,
    exchange varchar(10) not null,
    create_at timestamp not null,
    updated_at timestamp not null
);
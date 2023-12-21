create table exchange (
    id text primary key,
    mic char(4) unique not null,
    country varchar(60) not null,
    currency char(3) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);
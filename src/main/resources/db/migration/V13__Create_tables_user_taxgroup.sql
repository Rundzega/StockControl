create table investor (
    id text primary key,
    first_name varchar(60) not null,
    last_name varchar(60) not null,
    email text unique not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table tax_group (
    id text primary key,
    description text not null,
    rate decimal(3, 2) not null,
    darf_code smallint not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

alter table stock add column tax_group_id text not null;

alter table stock add constraint stock_tax_group_fk foreign key (tax_group_id) references stock(id);
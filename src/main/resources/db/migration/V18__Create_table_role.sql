create table role(
    id varchar(10) primary key,
    description varchar(60) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

alter table account add column role_id varchar(10) not null;

alter table account add constraint account_role_fk foreign key (role_id) references role(id);
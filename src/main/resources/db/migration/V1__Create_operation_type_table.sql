create table operation_type (
    id text primary key,
    description varchar(20) not null,
    created_at timestamp not null,
    updated_at timestamp not null 
);

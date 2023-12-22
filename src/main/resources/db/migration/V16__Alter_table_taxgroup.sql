alter table stock drop constraint stock_tax_group_fk;

alter table stock alter column tax_group_id type smallint USING (NULLIF(tax_group_id, '')::smallint);

drop table tax_group;

create table tax_group (
    id smallint primary key,
    description text not null,
    exemption_cents bigint not null,
    rate decimal(3, 2) not null,
    darf_code smallint not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

alter table stock add constraint stock_tax_group_fk foreign key (tax_group_id) references tax_group(id);
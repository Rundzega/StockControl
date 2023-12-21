alter table operation_type drop constraint operation_type_pkey;
alter table operation_type drop column id;
alter table operation_type drop constraint operation_type_description_key;
alter table operation_type add primary key (description);

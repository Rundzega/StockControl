create table operation(
    id text primary key,
    account_id text not null references account(id),
    stock_id text not null references stock(id),
    operation_type_description varchar(20) not null references operation_type(description),
    op_date date not null,
    quantity integer not null,
    unit_value_cents integer not null,
    currency_conversion_rate decimal(15, 5) not null,
    unit_value_cents_brl integer not null,
    expenses_cents integer,
    withheld_tax_cents_brl integer,
    created_at timestamp not null,
    updated_at timestamp not null
);
alter table stock rename column exchange to exchange_id;
alter table stock drop column currency;
alter table stock add constraint fk_stock_exchange foreign key (exchange_id) references exchange (id);
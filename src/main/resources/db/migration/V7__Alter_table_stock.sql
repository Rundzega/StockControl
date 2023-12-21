alter table stock add constraint un_ticker unique(exchange, ticker);
create unique index idx_ticker on stock (exchange, ticker);
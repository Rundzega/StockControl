alter table investor rename to account;

alter table account rename constraint investor_pkey to account_pkey;

alter table account rename constraint investor_email_key to account_email_key;

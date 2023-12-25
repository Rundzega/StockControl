alter table account drop constraint account_google_id_key, drop column google_id, add column email text not null, add column provider text not null, add column provider_id text not null;

alter table account add constraint account_un_provider_provider unique(provider, provider_id);

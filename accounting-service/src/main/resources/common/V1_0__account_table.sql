/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists account
(
    id            bigserial    not null primary key,
    public_id     varchar(255) not null unique,
    first_name    varchar(64),
    last_name     varchar(64),
    wallet_amount decimal      not null default 0,
    calculated_at timestamptz  not null default now(),
    updated_at    timestamptz  not null default now(),
    created_at    timestamptz  not null default now()
);

/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists worker
(
    id         bigserial    not null primary key,
    public_id  varchar(255) not null unique,
    first_name varchar(64)  not null,
    last_name  varchar(64)  not null,
    role       varchar(64)  not null,
    status     varchar(64)  not null,
    updated_at timestamptz  not null default now(),
    created_at timestamptz  not null default now()
);

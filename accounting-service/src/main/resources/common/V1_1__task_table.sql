/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists task
(
    id             bigserial    not null primary key,
    public_id      varchar(255) not null unique,
    user_public_id varchar(255) not null,
    penalty_rate   decimal      not null,
    award_rate     decimal      not null,
    description    text,
    updated_at     timestamptz  not null default now(),
    created_at     timestamptz  not null default now()
);


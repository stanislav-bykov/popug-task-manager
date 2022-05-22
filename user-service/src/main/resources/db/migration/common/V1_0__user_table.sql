/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists user_account
(
    id         bigserial    not null primary key,
    public_id  varchar(255) not null,
    username   varchar(64)  not null unique,
    first_name varchar(64)  not null,
    last_name  varchar(64)  not null,
    email      varchar(64)  not null,
    role       varchar(64)  not null,
    status     varchar(64)  not null,
    updated_at timestamptz  not null default now(),
    created_at timestamptz  not null default now()
);

create table if not exists user_external_account_cfg_task
(
    id              bigserial   not null primary key,
    user_account_id bigint      not null,
    cfg_step        varchar(64) not null,
    updated_at      timestamptz not null default now(),
    created_at      timestamptz not null default now(),


    constraint fk__user_external_account_cfg_task_user_id__user_account_id
        foreign key (id) references user_account (id)
)

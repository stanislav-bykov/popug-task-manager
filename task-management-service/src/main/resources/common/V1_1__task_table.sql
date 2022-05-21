/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists task
(
    id          bigserial    not null primary key,
    public_id   varchar(255) not null unique,
    worker_id   bigint       not null,
    description text         not null,
    status      varchar(64)  not null,
    updated_at  timestamptz  not null default now(),
    created_at  timestamptz  not null default now(),

    constraint fk__task_worker_id__worker_id
        foreign key (worker_id) references worker (id)
);


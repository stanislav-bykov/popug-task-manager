/**
  FK__ChildTable_childColumn__ParentTable_parentColumn
 */

create table if not exists transaction
(
    id         bigserial    not null primary key,
    public_id  varchar(255) not null unique,
    account_id bigint       not null,
    task_id    bigint,
    debit      decimal      not null default 0,
    credit     decimal      not null default 0,
    type       varchar(64)  not null,
    created_at timestamp    not null default now(),

    constraint fk__transaction_account_id__account_id
        foreign key (account_id) references account (id),

    constraint fk__transaction_task_id__task_id
        foreign key (task_id) references task (id)
);

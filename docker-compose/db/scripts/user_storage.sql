-- user service
create database user_storage encoding utf8;
grant all privileges on database user_storage to popuguser;

-- task management service
create database task_management_storage encoding utf8;
grant all privileges on database task_management_storage to popuguser;

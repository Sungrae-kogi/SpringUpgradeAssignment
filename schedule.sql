create database schedules;
create table schedules (
    schedule_id bigint not null auto_increment,
    created_at datetime(6),
    modified_at datetime(6),
    contents varchar(255) not null,
    title varchar(255) not null,
    username varchar(255) not null,
    primary key (schedule_id)
) engine=InnoDB
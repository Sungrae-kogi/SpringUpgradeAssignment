create database schedules;
create table comments (
        comment_id bigint not null auto_increment,
        created_at datetime(6),
        modified_at datetime(6),
        comment varchar(255) not null,
        username varchar(255) not null,
        schedule_id bigint,
        primary key (comment_id)
    ) engine=InnoDB


create table schedules (
        schedule_id bigint not null auto_increment,
        created_at datetime(6),
        modified_at datetime(6),
        contents varchar(255) not null,
        title varchar(255) not null,
        primary key (schedule_id)
    ) engine=InnoDB

create table users (
        user_id bigint not null auto_increment,
        created_at datetime(6),
        modified_at datetime(6),
        email varchar(255) not null,
        username varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

create table user_schedule (
        id bigint not null auto_increment,
        schedule_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

alter table comments 
       add constraint FKbef7m370enopdpf7yp6nmv0oo 
       foreign key (schedule_id) 
       references schedules (schedule_id)

alter table user_schedule 
       add constraint FKnsho894ifxkscx21ww2agif6j 
       foreign key (schedule_id) 
       references schedules (schedule_id)

alter table user_schedule 
       add constraint FK4wuna1k6hut7vaufl8neitnc1 
       foreign key (user_id) 
       references users (user_id)

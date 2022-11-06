drop table users;
create table users(
    id serial primary key,
    email varchar(37) unique,
    username varchar(40),
    password varchar(200),
    gender varchar(6),
    country varchar(20),
    sport varchar(25),
    category varchar(25) default 'no category',
    workExp int default 0,
    class varchar(50) default 'no class',
    countOfGroups int default 0,
    status varchar(6) default 'user'
);

create table groups(
    id serial primary key,
    name varchar(20),
    count int,
    userId int references users(id)
);
drop table achievements cascade ;
create table achievements(
     id serial primary key,
     name varchar(20),
     description varchar(60),
     rvalue int
);
drop table user_achievements;
create table user_achievements(
     userId int references users(id),
     achId int references achievements(id),
     value int default 0
);
create table days(
    id serial primary key,
    name varchar(12)
);
insert into days(name) values ('Monday'), ('Tuesday'), ('Wednesday'), ('Thursday'), ('Friday'), ('Saturday'), ('Sunday') ;

drop table user_schedule;
create table user_schedule(
    userId int references users(id),
    day int references days(id),
    hourStart int,
    minuteStart int,
    hourEnd int,
    minuteEnd int,
    workGroup varchar(20)
);
